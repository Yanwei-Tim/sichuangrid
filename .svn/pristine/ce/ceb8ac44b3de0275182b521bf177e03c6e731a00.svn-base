package com.tianque.plugin.orgchange.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyYearService;

@Component("workingManageSystemHandler")
public class WorkingManageSystemHandler extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(WorkingManageSystemHandler.class);
	@Autowired
	private DailyYearService dailyYearService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private final static String COUNT_SQL = "SELECT COUNT(*) FROM #TABLENAME# WHERE #ORGIDCOLUMN# = #OLDORGID#";

	private final static String UPDATE_ID_CODE_SQL = "UPDATE #TABLENAME# SET #ORGIDCOLUMN#=#NEWORGID#,#ORGCODECOLUMN#=#NEWORGCODE# WHERE #ORGIDCOLUMN# = #OLDORGID#";
	private final static String DELETE_ID_SQL = "DELETE #TABLENAME# WHERE ID=#OLDID#";
	private final static String BACKUP_DELETE_SQL = "select id dataId,#OLDORGID# dataOrgId,'#EXPANSIONDATA#' expansionData from #TABLENAME# where ID= #OLDID#";
	private final static String FIND_REPEATDATA_IDS_SQL = "SELECT T1.ID ||','||T2.ID AS RESULT FROM (SELECT ID FROM #TABLENAME# WHERE #ORGIDCOLUMN# = #OLDORGID#) T1,(SELECT ID FROM #TABLENAME# WHERE #ORGIDCOLUMN# = #NEWORGID#) T2 WHERE #OLDORGID#!=#NEWORGID#";

	private List<Long> changedDirectoryIds = new ArrayList<Long>();

	@Override
	public void backup(OrgMapping orgMapping) {
	}

	@Override
	public void init(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin init.orgMapping:{}", orgMapping);
		ModuleTable moduleTable = orgMapping.getModuleTable();
		String countSql = moduleTable.getCountScript();
		// 判断是否配置统计语句，如果没有则用默认语句
		if (!StringUtil.isStringAvaliable(countSql)) {
			countSql = COUNT_SQL;
		}
		countSql = OrgChangeUtils.replaceScript(orgMapping, countSql);
		int count = commonHandlerDAO.getCount(countSql);
		int workingCount = 0;
		// 在当前层级的下去目录管理为空值,去查询工作台帐是否有值,为了上一层级的辖区目录处理
		if (count == 0) {
			String workingCountSql = "SELECT COUNT(*) FROM WORKINGRECORDS WHERE ORGID = "
					+ orgMapping.getOldOrgId().toString();
			workingCount = commonHandlerDAO.getCount(workingCountSql);
		}
		int sumCount = count + workingCount;
		if (sumCount > 0) {
			orgMapping.setHasData(true);
		} else {
			orgMapping.setHasData(false);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		log.setSuccessDesc(log.getDescription() + "需要变更的数据量[" + count + "]");
		if (logger.isDebugEnabled())
			logger.debug("end init.orgMapping:{}", orgMapping);
	}

	@Override
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin transfer.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();

		updateDailyDirectorys(orgMapping, true);

		String updateSql = orgMapping.getModuleTable().getUpdateScript();
		if (!StringUtil.isStringAvaliable(updateSql)) {
			updateSql = UPDATE_ID_CODE_SQL;
		}
		updateSql = OrgChangeUtils.replaceScript(orgMapping, updateSql);
		int num = commonHandlerDAO.updateData(updateSql);
		log.appendSuccessDesc("更新表【"
				+ orgMapping.getModuleTable().getTableName() + "】数据量[" + num
				+ "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end transfer.");
		}
	}

	@Override
	public void merge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin merge.orgMapping:{}", orgMapping);
		}
		if (!orgMapping.getNewOrgId().equals(orgMapping.getOldOrgId())) {
			List<String> ids = findRepeatDataIds(orgMapping);
			if (ids != null) {
				for (int i = 0; i < ids.size(); i++) {
					Map<String, Long> idMap = toIdsMap(ids.get(i));
					updatePrimaryMembersOrgType(orgMapping, idMap);
					backUpPartyOrg(BACKUP_DELETE_SQL, orgMapping,
							idMap.get("oldId"), null);
					deleteRepeatData(orgMapping, idMap);
				}
			}
			updateDailyDirectorys(orgMapping, false);
		}
		transfer(orgMapping);
		if (logger.isDebugEnabled()) {
			logger.debug("end merge.");
		}
	}

	private List<String> findRepeatDataIds(OrgMapping orgMapping) {
		String repeatIdsSQL = orgMapping.getModuleTable().getDeleteScript();
		if (!StringUtil.isStringAvaliable(repeatIdsSQL)) {
			repeatIdsSQL = FIND_REPEATDATA_IDS_SQL;
		}
		repeatIdsSQL = OrgChangeUtils.replaceScript(orgMapping, repeatIdsSQL);
		return commonHandlerDAO.queryStringForList(repeatIdsSQL);
	}

	private void deleteRepeatData(OrgMapping orgMapping, Map<String, Long> ids) {
		if ("DAILYYEARS".equals(orgMapping.getModuleTable().getTableName())) {
			return;
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		Long oldId = ids.get("oldId");
		String deleteSql = OrgChangeUtils.replaceScript(orgMapping,
				DELETE_ID_SQL);
		deleteSql = replaceOldIdAndNewId(deleteSql, oldId, null, null);
		int num = commonHandlerDAO.deleteData(deleteSql);
		log.appendSuccessDesc("删除表【"
				+ orgMapping.getModuleTable().getTableName() + "】数据量[" + num
				+ "]");
	}

	private void backUpPartyOrg(String backUpSQL, OrgMapping orgMapping,
			Long oldId, String expansionData) {
		String backupSql = OrgChangeUtils.replaceScript(orgMapping, backUpSQL);
		backupSql = replaceOldIdAndNewId(backupSql, oldId, null, expansionData);
		orgMapping.getModuleTable().setSelectScript(backupSql);
		backupBaseDataService.addBackupBaseData(orgMapping);
	}

	/** 我的台账 修改所属目录id */
	private void updateWorkingRecords(OrgMapping orgMapping,
			Long oldDailyDirectoryId, Long newDailyDirectoryId) {
		if ("DAILYYEARS".equals(orgMapping.getModuleTable().getTableName())) {
			OrgChangeLog log = orgMapping.getOrgChangeLog();
			if (!oldDailyDirectoryId.equals(newDailyDirectoryId)) {
				String updateSql = "update workingRecords set DAILYDIRECTORYID=#NEWID# where DAILYDIRECTORYID=#OLDID# and (orginternalcode=#NEWORGCODE# or orginternalcode=#OLDORGCODE#)";
				updateSql = OrgChangeUtils.replaceScript(orgMapping, updateSql);
				updateSql = replaceOldIdAndNewId(updateSql,
						oldDailyDirectoryId, newDailyDirectoryId, null);
				int num = commonHandlerDAO.updateData(updateSql);
				log.appendSuccessDesc("更新表【WORKINGRECORDS】数据量[" + num + "]");
			}
		}
	}

	/** 工作台帐目录 */
	private void updateDailyDirectorys(OrgMapping orgMapping,
			boolean isGetParentOrg) {
		String tableName = orgMapping.getModuleTable().getTableName();
		Long oldOrgId = orgMapping.getOldOrgId();
		Long newOrgId = orgMapping.getNewOrgId();
		if ("DAILYYEARS".equals(tableName)) {
			String oldAndNewSQL = "select T1.ID||','||T2.ID AS RESULT from (select * from DailyDirectorys where type is null and dailyyearid=#OLDID#) t1 ,(select * from DailyDirectorys where type is null  and dailyyearid=#NEWID#) t2 where t1.fullname=t2.fullname(+)";
			String oldOnlySQL = "select ID||','||ID AS RESULT from DailyDirectorys where type is null and dailyyearid=#OLDID#";
			String selectSQL = null;
			if (isGetParentOrg) {
				oldOrgId = orgMapping.getOldParentOrgId();
				Organization organization = organizationDubboService
						.getFullOrgById(newOrgId);
				newOrgId = organization.getParentOrg().getId();
			}
			List<DailyYear> oldDailyYears = dailyYearService
					.findDailyYearsByOrgId(oldOrgId);
			for (int i = 0; i < oldDailyYears.size(); i++) {
				List<DailyYear> newDailyYears = dailyYearService
						.findDailyYearsByOrgId(newOrgId);
				DailyYear dailyYear = oldDailyYears.get(i);
				Long oldDailyYearId = dailyYear.getId();
				Long newDailyYearId = null;
				Integer yearDate = dailyYear.getYearDate();
				for (int j = 0; j < newDailyYears.size(); j++) {
					DailyYear newDailyYear = newDailyYears.get(j);
					if (yearDate.equals(newDailyYear.getYearDate())
							&& newDailyYear.getStatus().equals(1L)) {
						newDailyYearId = newDailyYear.getId();
						selectSQL = oldAndNewSQL;
						break;
					}
				}
				if (selectSQL == null) {
					selectSQL = oldOnlySQL;
				}
				selectSQL = replaceOldIdAndNewId(selectSQL, oldDailyYearId,
						newDailyYearId, null);
				List<String> dailyDirectoryIds = commonHandlerDAO
						.queryStringForList(selectSQL);
				boolean isAddTempDailyyears = false;
				for (int k = 0; k < dailyDirectoryIds.size(); k++) {
					Map<String, Long> map = toIdsMap(dailyDirectoryIds.get(k));
					Long oldDailyDirectoryId = map.get("oldId");
					Long newDailyDirectoryId = map.get("newId");
					if (oldDailyDirectoryId.equals(newDailyDirectoryId)
							|| newDailyDirectoryId == null) {
						changedDirectoryIds.add(oldDailyDirectoryId);
						if (!isAddTempDailyyears) {
							Integer num = commonHandlerDAO
									.getCount("SELECT COUNT(*) FROM workingRecords where DAILYDIRECTORYID="
											+ oldDailyDirectoryId);
							isAddTempDailyyears = num > 0;
						}
					} else {
						updateWorkingRecords(orgMapping, oldDailyDirectoryId,
								newDailyDirectoryId);
					}
				}
				if (oldOrgId.equals(newOrgId)) {
					return;
				}
				updateDailyYear(isAddTempDailyyears, !isGetParentOrg,
						orgMapping, dailyYear, oldDailyYearId, oldOrgId,
						newOrgId);
			}
		}
	}

	private void updateDailyYear(boolean isAdd, boolean isDelete,
			OrgMapping orgMapping, DailyYear dailyYear, Long oldDailyYearId,
			Long oldOrgId, Long newOrgId) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		if (isAdd) {
			String oldDailyYearName = dailyYear.getName();
			dailyYear.setName(orgMapping.getOldOrgName() + oldDailyYearName);
			Organization org = organizationDubboService
					.getSimpleOrgById(newOrgId);
			dailyYear.setMakedOrganization(org);
			dailyYear.setMakedOrgInternalCode(org.getOrgInternalCode());
			dailyYear.setStatus(1L);
			if (isDelete) {
				// 改变Dailyyears的名称
				// 对应的DailyDirectorys和workingRecords因为都是通过id关联，可以不用修改
				backUpPartyOrg(BACKUP_DELETE_SQL, orgMapping, oldDailyYearId,
						null);
				dailyYearService.updateDailyYear(dailyYear);
				log.appendSuccessDesc("更新台账目录表【DAILYYEARS】name由["
						+ oldDailyYearName + "]变更为[" + dailyYear.getName()
						+ "]");
			} else {
				// org = organizationDubboService.getSimpleOrgById(oldOrgId);
				// dailyYear.setName(org.getOrgName() + oldDailyYearName);
				// 新增Dailyyears、及 DailyDirectorys
				// 迁移对应的workingRecords
				DailyYear newDailyYear = dailyYearService
						.addDailyYear(dailyYear);
				log.appendSuccessDesc("新增临时台账目录表【DAILYYEARS】name为["
						+ newDailyYear.getName() + "]");
				initDailyDirectory(orgMapping, oldDailyYearId, newDailyYear);
				newDailyYear = dailyYearService.startDailyYearById(newDailyYear
						.getId());
			}
		} else if (isDelete) {
			backUpPartyOrg(BACKUP_DELETE_SQL, orgMapping, oldDailyYearId, null);
			dailyYearService.stopDailyYearById(oldDailyYearId);
			dailyYearService.deleteDailyYearById(oldDailyYearId);
			log.appendSuccessDesc("删除台账目录表【DAILYYEARS】id为[" + oldDailyYearId
					+ "]");
		}
	}

	private void initDailyDirectory(OrgMapping orgMapping, Long templateId,
			DailyYear dailyYear) {
		if (dailyYear == null || dailyYear.getId() == null) {
			return;
		}
		List<DailyDirectory> list = dailyDirectoryService
				.findDailyTrunkDirectoryByDailyYearId(templateId);
		if (list == null || list.size() <= 0) {
			return;
		}
		List<DailyDirectory> lastDailyDirectorys = new ArrayList<DailyDirectory>();
		for (DailyDirectory temp : list) {
			if (temp.getType() != null && temp.getType().getId() != null) {
				addCopyDirectory(orgMapping, dailyYear, temp, null);
				lastDailyDirectorys.add(temp);
			}
		}
		list.removeAll(lastDailyDirectorys);
		for (DailyDirectory temp : list) {
			addCopyDirectory(orgMapping, dailyYear, temp, null);
		}
	}

	private void addCopyDirectory(OrgMapping orgMapping, DailyYear dailyYear,
			DailyDirectory directory, DailyDirectory parentDirectory) {
		Long oldDirectoryId = directory.getId();
		directory.setDailyYear(dailyYear);
		directory.setParentDailyDirectory(parentDirectory);
		List<DailyDirectory> subDailyDirectorys = dailyDirectoryService
				.findDailySubDirectoryByParentId(directory.getId());
		parentDirectory = dailyDirectoryService.addDailyDirectory(directory);
		if (changedDirectoryIds.contains(oldDirectoryId)) {
			updateWorkingRecords(orgMapping, oldDirectoryId,
					parentDirectory.getId());
		}
		for (DailyDirectory subDailyDirectory : subDailyDirectorys) {
			addCopyDirectory(orgMapping, dailyYear, subDailyDirectory,
					parentDirectory);
		}
	}

	/** 四级体系建设 成员库组织类型表 */
	private void updatePrimaryMembersOrgType(OrgMapping orgMapping,
			Map<String, Long> ids) {
		if ("FOURLEVELPLATFORM".equals(orgMapping.getModuleTable()
				.getTableName())) {
			OrgChangeLog log = orgMapping.getOrgChangeLog();
			Long oldId = ids.get("oldId");
			Long newId = ids.get("newId");
			String updateSql = "UPDATE primarymembersorgtype SET PRIMARYORGID=#NEWID# WHERE PRIMARYORGID = #OLDID#";
			updateSql = replaceOldIdAndNewId(updateSql, oldId, newId, null);
			int num = commonHandlerDAO.updateData(updateSql);
			log.appendSuccessDesc("更新四级体系建设 成员库组织类型表【PRIMARYMEMBERSORGTYPE】数据量["
					+ num + "]");
		}
	}

	private Map<String, Long> toIdsMap(String oldIdAndNewId) {
		String[] ids = oldIdAndNewId.split(",");
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("oldId",
				(ids != null && ids.length > 0) ? Long.parseLong(ids[0]) : null);
		map.put("newId",
				(ids != null && ids.length > 1) ? Long.parseLong(ids[1]) : null);
		return map;
	}

	private String replaceOldIdAndNewId(String sql, Long oldId, Long newId,
			String expansionData) {
		if (StringUtil.isStringAvaliable(sql)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("#OLDID#", oldId + "");
			map.put("#NEWID#", newId + "");
			map.put("#EXPANSIONDATA#", (expansionData == null) ? ""
					: expansionData);
			sql = OrgChangeUtils.replaceMapValue(map, sql);
		}
		return sql;
	}
}
