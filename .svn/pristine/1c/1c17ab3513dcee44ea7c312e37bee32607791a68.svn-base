package com.tianque.plugin.orgchange.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.domain.Organization;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.BackupBaseData;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.OrgChangeInfo;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.service.NewEconomicOrganizationsService;
import com.tianque.solr.domain.DocumentType;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 
 * @ClassName: NewEconomicOrganizationsHandler
 * @Description: TODO非公有制经济组织迁移合并handler
 * @author wanggz
 * @date 2014-10-18 下午04:52:02
 */
@Component("newEconomicHandler")
public class NewEconomicOrganizationsHandler extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(NewEconomicOrganizationsHandler.class);
	private final static String UPDATE_ID_CODE_SQL = "UPDATE #TABLENAME# SET #ORGIDCOLUMN#=#NEWORGID#,#ORGCODECOLUMN#=#NEWORGCODE# WHERE #ORGIDCOLUMN# = #OLDORGID#";

	@Autowired
	private NewEconomicOrganizationsService newEconomicOrganizationsService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public void merge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin merge.");
		// 合并时如果判断orgid是否变更
		if (orgMapping.getNewOrgId().equals(orgMapping.getOldOrgId())) {
			transfer(orgMapping);
		} else {
			// 网格合并处理
			executeMerge(orgMapping);
		}
		if (logger.isDebugEnabled())
			logger.debug("end merge.");

	}

	public void executeMerge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin merge.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		/** 根据组织机构id和营业执照号查询重复数据 */
		List<NewEconomicOrganizations> sourceValues = newEconomicOrganizationsService
				.findRepeatNewEconomicOrganizationsForOrgChange(
						orgMapping.getNewOrgId(), orgMapping.getOldOrgId());
		int countServiceMemberHasObject = 0;
		int countServiceTeamHasObject = 0;
		int countServiceRecordRelyObject = 0;
		int countNewEconomicOrganizations = 0;

		for (NewEconomicOrganizations sourceVal : sourceValues) {
			/** 删除源部门重复数据的关联关表数据 */
			// 删除服务人员与服务对象的关联关系
			backup(orgMapping, sourceVal.getId(), "serviceMemberHasObject",
					sourceVal);
			String deleteServiceMemberHasObjectsql = "delete from serviceMemberHasObject sh where sh.objectid="
					+ sourceVal.getId() + "";
			countServiceMemberHasObject += commonHandlerDAO
					.deleteData(deleteServiceMemberHasObjectsql);
			// 删除服务团队与服务对象的关联关系
			backup(orgMapping, sourceVal.getId(), "serviceTeamHasObject",
					sourceVal);
			String deleteServiceTeamHasObjectSql = "delete from serviceTeamHasObject sh where sh.objectid="
					+ sourceVal.getId() + "";
			countServiceTeamHasObject += commonHandlerDAO
					.deleteData(deleteServiceTeamHasObjectSql);
			// 删除服务记录与服务对象的关联关系
			backup(orgMapping, sourceVal.getId(), "serviceRecordRelyObject",
					sourceVal);
			String deleteServiceRecordRelyObjectSql = "delete from serviceRecordRelyObject sr where sr.objectid="
					+ sourceVal.getId() + "";
			countServiceRecordRelyObject += commonHandlerDAO
					.deleteData(deleteServiceRecordRelyObjectSql);
			// 删除源部门主表营业执照号重复数据
			backupMainTable(orgMapping, sourceVal.getId());
			String deleteNewEconomicOrganizationsSql = "delete from newEconomicOrganizations ne where ne.id="
					+ sourceVal.getId() + "";
			countNewEconomicOrganizations += commonHandlerDAO
					.deleteData(deleteNewEconomicOrganizationsSql);

			backupKeypalces(orgMapping, "keyplaces", sourceVal.getId());
			/** 删除keyplaces中对应的数据 */
			String deleteKeyPlacesSql = "delete from keyplaces k where k.id="
					+ sourceVal.getId() + " and k.type='"
					+ DocumentType.NEWECONOMICORGANIZATIONS + "'";
			commonHandlerDAO.deleteData(deleteKeyPlacesSql);
		}
		/** 根据组织机构id和名称查询重复数据 */
		List<NewEconomicOrganizations> sourceNameValues = newEconomicOrganizationsService
				.findRepeatNewEconomicOrganizationsByNameForOrgChange(
						orgMapping.getNewOrgId(), orgMapping.getOldOrgId());
		int countName = 0;
		for (NewEconomicOrganizations sourceNameVal : sourceNameValues) {
			Organization org = organizationDubboService
					.getSimpleOrgById(orgMapping.getOldOrgId());
			// 修改源部门名称重复数据的名称
			String newName = sourceNameVal.getName();
			if (org != null) {
				newName = newName + "来自" + org.getOrgName();
			}
			String updateNameSql = " update newEconomicOrganizations ne set ne.name='"
					+ newName + "' where ne.id=" + sourceNameVal.getId() + " ";
			countName += commonHandlerDAO.updateData(updateNameSql);
			/** 更新keyplaces中对应的数据 */
			String updateKeyPlacesSql = "update keyplaces k set k.name = '"
					+ newName + "' where k.id=" + sourceNameVal.getId()
					+ " and k.type='" + DocumentType.NEWECONOMICORGANIZATIONS
					+ "'";
			commonHandlerDAO.updateData(updateKeyPlacesSql);

		}
		log.appendSuccessDesc("删除服务人员与服务对象的关联关系数据量["
				+ countServiceMemberHasObject + "]");
		log.appendSuccessDesc("删除服务团队与服务对象的关联关系数据量["
				+ countServiceTeamHasObject + "]");
		log.appendSuccessDesc("删除服务记录与服务对象的关联关系数据量["
				+ countServiceRecordRelyObject + "]");
		log.appendSuccessDesc("删除源部门主表营业执照号重复数据量["
				+ countNewEconomicOrganizations + "]");
		log.appendSuccessDesc("修改源部门名称重复数据量[" + countName + "]");
		transfer(orgMapping);
		if (logger.isDebugEnabled()) {
			logger.debug("end merge.");
		}
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
		log.setSuccessDesc(log.getDescription() + "更新数据量[" + num + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end transfer.");
		}
	}

	@Override
	public void backup(OrgMapping orgMapping) {
	}

	private void backup(OrgMapping orgMapping, Long id, String tableName,
			NewEconomicOrganizations ne) {
		ModuleTable moduleTable = orgMapping.getModuleTable();
		String backUpSql = "select t.id  AS RESULT from " + tableName
				+ " t where t.objectid= " + id + " ";
		List backUpValues = commonHandlerDAO.queryStringForList(backUpSql);
		Map<Long, List<String>> backUpMap = new HashMap<Long, List<String>>();
		backUpMap.put(id, backUpValues);
		String name = "";
		if ("serviceMemberHasObject".equals(tableName)) {
			name = "与服务人员与服务对象的关联关系";
		}
		if ("serviceTeamHasObject".equals(tableName)) {
			name = "与服务团队与服务对象的关联关系";
		}
		if ("serviceRecordRelyObject".equals(tableName)) {
			name = "与服务记录与服务对象的关联关系";
		}
		String expData = "删除非公有制经济组织" + name + "关联关系队列：" + backUpMap;

		if (ne.getOrganization() == null
				|| ne.getOrganization().getId() == null) {
			Organization org = new Organization();
			org.setId(0l);
			ne.setOrganization(org);
		}
		String delSql = " select id dataId," + ne.getOrganization().getId()
				+ " dataOrgId,'" + expData + "' expansionData FROM  "
				+ tableName + " where objectid=" + id + "   ";
		moduleTable.setSelectScript(delSql);
		moduleTable.setTableName(tableName);
		backupBaseDataService.addBackupBaseData(orgMapping);
	}

	private void backupMainTable(OrgMapping orgMapping, Long valId) {
		ModuleTable moduleTable = orgMapping.getModuleTable();
		String delSql = "select id dataId,"
				+ moduleTable.getOrgIdColumn()
				+ " dataOrgId,'' expansionData FROM newEconomicOrganizations  where id="
				+ valId + "";
		moduleTable.setSelectScript(delSql);
		moduleTable.setTableName("newEconomicOrganizations");
		backupBaseDataService.addBackupBaseData(orgMapping);
	}

	/***
	 * 删除keyplaces数据备份
	 */
	private void backupKeypalces(OrgMapping orgMapping, String tableName,
			Long dataId) {
		BackupBaseData backupBaseData = new BackupBaseData();
		backupBaseData.setOrgChangeInfo(new OrgChangeInfo());
		backupBaseData.getOrgChangeInfo().setId(
				orgMapping.getOrgChangeInfo().getId());
		backupBaseData.setDataId(dataId);
		backupBaseData.setTableName(tableName);
		backupBaseData.setOrganization(new Organization());
		backupBaseData.getOrganization().setId(orgMapping.getOldOrgId());
		backupBaseData.setCreateUser(orgMapping.getModuleTable()
				.getCreateUser());
		backupBaseData.setCreateDate(orgMapping.getModuleTable()
				.getCreateDate());
		backupBaseData.setUpdateUser(orgMapping.getModuleTable()
				.getUpdateUser());
		backupBaseData.setUpdateDate(orgMapping.getModuleTable()
				.getUpdateDate());
		backupBaseDataService.addBackupBaseDataRelation(backupBaseData);
	}
}
