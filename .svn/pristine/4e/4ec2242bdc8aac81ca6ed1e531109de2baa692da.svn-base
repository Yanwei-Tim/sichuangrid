package com.tianque.plugin.orgchange.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tianque.core.util.StringUtil;
import com.tianque.partyBuilding.activityRecords.constant.OrganizationType;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;

@Component("partyOrgHandler")
public class PartyOrgHandler extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(PartyOrgHandler.class);

	private final static String UPDATE_ID_CODE_SQL = "UPDATE #TABLENAME# SET #ORGIDCOLUMN#=#NEWORGID#,#ORGCODECOLUMN#=#NEWORGCODE# WHERE #ORGIDCOLUMN# = #OLDORGID#";
	private final static String DELETE_ID_SQL = "DELETE #TABLENAME# WHERE ID= #OLDID#";
	private final static String BACKUP_DELETE_SQL = "select id dataId,#OLDORGID# dataOrgId,'' expansionData from #TABLENAME# where ID= #OLDID#";
	private final static String FIND_REPEATDATA_IDS_SQL = "select t1.id ||','||t2.id AS RESULT from (select id,name from #TABLENAME# where #ORGIDCOLUMN# = #OLDORGID#) t1,(select id,name from #TABLENAME# where #ORGIDCOLUMN# = #NEWORGID#) t2 where t1.name = t2.name";

	@Override
	public void backup(OrgMapping orgMapping) {
	}

	@Override
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin transfer.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
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
					updateActivityRecords(orgMapping, idMap);
					updatePartyOrgMember(orgMapping, idMap);
					updateVolunteerMember(orgMapping, idMap);
					updateReportHasProject(orgMapping, idMap);
					updateMemberHasVolunteerJobs(orgMapping, idMap);
					backUpPartyOrg(BACKUP_DELETE_SQL, orgMapping, idMap);
					deleteRepeatData(orgMapping, idMap);
				}
			}
		}
		transfer(orgMapping);
		if (logger.isDebugEnabled()) {
			logger.debug("end merge.");
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

	private List<String> findRepeatDataIds(OrgMapping orgMapping) {
		String repeatIdsSQL = orgMapping.getModuleTable().getDeleteScript();
		if (!StringUtil.isStringAvaliable(repeatIdsSQL)) {
			repeatIdsSQL = FIND_REPEATDATA_IDS_SQL;
		}
		repeatIdsSQL = OrgChangeUtils.replaceScript(orgMapping, repeatIdsSQL);
		return commonHandlerDAO.queryStringForList(repeatIdsSQL);
	}

	private void deleteRepeatData(OrgMapping orgMapping, Map<String, Long> ids) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		Long oldId = ids.get("oldId");
		String deleteSql = OrgChangeUtils.replaceScript(orgMapping,
				DELETE_ID_SQL);
		deleteSql = replaceOldIdAndNewId(deleteSql, oldId, null);
		int num = commonHandlerDAO.deleteData(deleteSql);
		log.appendSuccessDesc("删除表【"
				+ orgMapping.getModuleTable().getTableName() + "】数据量[" + num
				+ "]");
	}

	private void backUpPartyOrg(String backUpSQL, OrgMapping orgMapping,
			Map<String, Long> ids) {
		Long oldId = ids.get("oldId");
		Long newId = ids.get("newId");
		String backupSql = OrgChangeUtils.replaceScript(orgMapping, backUpSQL);
		backupSql = replaceOldIdAndNewId(backupSql, oldId, newId);
		orgMapping.getModuleTable().setSelectScript(backupSql);
		backupBaseDataService.addBackupBaseData(orgMapping);
	}

	/** 服务记录 */
	private void updateActivityRecords(OrgMapping orgMapping,
			Map<String, Long> ids) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		Long oldId = ids.get("oldId");
		Long newId = ids.get("newId");
		String updateSql = "UPDATE activityRecords SET ORGANIZATIONID= #NEWID# WHERE ORGANIZATIONTYPE = '#TABLENAME#' and ORGANIZATIONID = #OLDID#";
		updateSql = OrgChangeUtils.replaceScript(orgMapping, updateSql);
		updateSql = replaceOldIdAndNewId(updateSql, oldId, newId);
		int num = commonHandlerDAO.updateData(updateSql);
		log.appendSuccessDesc("更新活动记录表【activityRecords】数据量[" + num + "]");
	}

	/** 街道社区党组织 班子成员 */
	private void updatePartyOrgMember(OrgMapping orgMapping,
			Map<String, Long> ids) {
		ModuleTable moduleTable = orgMapping.getModuleTable();
		if (OrganizationType.TOWN_PARTY_ORGANIZATION.equals(moduleTable
				.getTableName())) {
			OrgChangeLog log = orgMapping.getOrgChangeLog();
			Long oldId = ids.get("oldId");
			Long newId = ids.get("newId");
			String updateSql = "UPDATE PARTY_ORG_MEMBER SET partyOrgId= #NEWID# WHERE partyOrgId = #OLDID#";
			updateSql = replaceOldIdAndNewId(updateSql, oldId, newId);
			int num = commonHandlerDAO.updateData(updateSql);
			log.appendSuccessDesc("更新班子成员表【PARTY_ORG_MEMBER】数据量[" + num + "]");
		}
	}

	/** 志愿者队伍成员 */
	private void updateVolunteerMember(OrgMapping orgMapping,
			Map<String, Long> ids) {
		ModuleTable moduleTable = orgMapping.getModuleTable();
		if ("VOLUNTEER_TEAM".equals(moduleTable.getTableName())) {
			OrgChangeLog log = orgMapping.getOrgChangeLog();
			Long oldId = ids.get("oldId");
			Long newId = ids.get("newId");
			String deleteSqlWhere = "( select t1.id from (select * from VOLUNTEER_MEMBER where teamid= #OLDID#) t1,(select * from VOLUNTEER_MEMBER where teamid= #NEWID#) t2 where t1.memberid = t2.memberid)";
			String deleteSQL = "delete VOLUNTEER_MEMBER where id="
					+ deleteSqlWhere;
			String updateSql = "UPDATE VOLUNTEER_MEMBER SET teamid= #NEWID# WHERE teamid = #OLDID#";
			String backUpSQL = "select id dataId,#OLDORGID# dataOrgId,'' expansionData from #TABLENAME# where ID="
					+ deleteSqlWhere;
			deleteSQL = replaceOldIdAndNewId(deleteSQL, oldId, newId);
			updateSql = replaceOldIdAndNewId(updateSql, oldId, newId);
			backUpPartyOrg(backUpSQL, orgMapping, ids);
			int num = commonHandlerDAO.deleteData(deleteSQL);
			log.appendSuccessDesc("删除成员信息表【VOLUNTEER_MEMBER】数据量[" + num + "]");
			num = commonHandlerDAO.updateData(updateSql);
			log.appendSuccessDesc("更新成员信息表【VOLUNTEER_MEMBER】数据量[" + num + "]");
		}
	}

	/** 党组织报到和服务项目关联表 */
	private void updateReportHasProject(OrgMapping orgMapping,
			Map<String, Long> ids) {
		ModuleTable moduleTable = orgMapping.getModuleTable();
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		Long oldId = ids.get("oldId");
		Long newId = ids.get("newId");
		String updateSql = null;
		String deleteSql = null;
		if ("PARTYORG_REPORT".equals(moduleTable.getTableName())) {
			deleteSql = "DELETE REPORTHASPROJECT WHERE REPORTID = #OLDID# AND PROJECTID in ( SELECT O.PROJECTID FROM REPORTHASPROJECT N,REPORTHASPROJECT O WHERE N.REPORTID = #NEWID# AND O.REPORTID = #OLDID# AND N.PROJECTID = O.PROJECTID) ";
			updateSql = "UPDATE REPORTHASPROJECT SET REPORTID = #NEWID# WHERE REPORTID = #OLDID#";
		} else if ("SERVICEPROJECT".equals(moduleTable.getTableName())) {
			deleteSql = "DELETE REPORTHASPROJECT WHERE PROJECTID = #OLDID# AND REPORTID in ( SELECT O.REPORTID FROM REPORTHASPROJECT N,REPORTHASPROJECT O WHERE N.PROJECTID = #NEWID# AND O.PROJECTID = #OLDID# AND N.REPORTID = O.REPORTID) ";
			updateSql = "UPDATE REPORTHASPROJECT SET PROJECTID = #NEWID# WHERE PROJECTID = #OLDID#";
		}
		if (StringUtil.isStringAvaliable(updateSql)) {
			deleteSql = replaceOldIdAndNewId(deleteSql, oldId, newId);
			int num = commonHandlerDAO.deleteData(deleteSql);
			if (num > 0) {
				log.appendSuccessDesc("删除 党组织报到和服务项目关联表【REPORTHASPROJECT】数据量["
						+ num + "]");
			}
			updateSql = replaceOldIdAndNewId(updateSql, oldId, newId);
			num = commonHandlerDAO.updateData(updateSql);
			if (num > 0) {
				log.appendSuccessDesc("更新 党组织报到和服务项目关联表【REPORTHASPROJECT】数据量["
						+ num + "]");
			}
		}
	}

	/** 党员报到和志愿者岗位关联表 */
	private void updateMemberHasVolunteerJobs(OrgMapping orgMapping,
			Map<String, Long> ids) {
		ModuleTable moduleTable = orgMapping.getModuleTable();
		if ("VOLUNTEERJOBS".equals(moduleTable.getTableName())) {
			OrgChangeLog log = orgMapping.getOrgChangeLog();
			Long oldId = ids.get("oldId");
			Long newId = ids.get("newId");
			String updateSql = "UPDATE MEMBERHASVOLUNTEERJOBS SET VOLUNTEERJOBSID= #NEWID# WHERE VOLUNTEERJOBSID = #OLDID#";
			updateSql = replaceOldIdAndNewId(updateSql, oldId, newId);
			int num = commonHandlerDAO.updateData(updateSql);
			log.appendSuccessDesc("更新班子成员表【MEMBERHASVOLUNTEERJOBS】数据量[" + num
					+ "]");
		}
	}

	private String replaceOldIdAndNewId(String sql, Long oldId, Long newId) {
		if (StringUtil.isStringAvaliable(sql)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("#OLDID#", oldId + "");
			map.put("#NEWID#", newId + "");
			sql = OrgChangeUtils.replaceMapValue(map, sql);
		}
		return sql;
	}
}
