package com.tianque.plugin.orgchange.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.baseInfo.newSocietyOrganizations.service.NewSocietyOrganitionsService;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.BackupBaseData;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.OrgChangeInfo;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.domain.ServiceMemberWithObject;
import com.tianque.plugin.serviceTeam.serviceMemberWithObject.service.ServiceMemberWithObjectService;
import com.tianque.plugin.serviceTeam.serviceTeamHasObjects.domain.ServiceTeamHasObjects;
import com.tianque.plugin.serviceTeam.serviceTeamMember.service.ServiceTeamMemberService;
import com.tianque.solr.domain.DocumentType;

/**
 * 
 * @ClassName: NewSocietyOrganizationsHandler
 * @Description: TODO社会组织迁移合并handler
 * @author wanggz
 * @date 2014-10-18 下午01:21:15
 */
@Component("newSocietyOrganizationsHandler")
public class NewSocietyOrganizationsHandler extends AbstractOrgChangeHandler {

	private final static Logger logger = LoggerFactory
			.getLogger(NewSocietyOrganizationsHandler.class);

	private final static String UPDATE_ID_CODE_SQL = "UPDATE #TABLENAME# SET #ORGIDCOLUMN#=#NEWORGID#,#ORGCODECOLUMN#=#NEWORGCODE# WHERE #ORGIDCOLUMN# = #OLDORGID#";

	@Autowired
	private NewSocietyOrganitionsService newSocietyOrganitionsService;
	@Autowired
	private ServiceMemberWithObjectService serviceMemberWithObjectService;
	@Autowired
	private ServiceTeamMemberService serviceTeamMemberService;

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
		/** 查询出源部门与目标部门名字已经类型相同的重复数据 */
		List<NewSocietyOrganizations> repeatValues = newSocietyOrganitionsService
				.findNewSocietyOrganizationsListForOrgChange(
						orgMapping.getNewOrgId(), orgMapping.getOldOrgId());
		for (NewSocietyOrganizations sourceOrg : repeatValues) {
			/** 根据名字类型组织机构id查询出目标部门的重复数据 */
			List<NewSocietyOrganizations> targetValues = newSocietyOrganitionsService
					.findTargetNewSocietyOrganizationsListForOrgChange(
							sourceOrg, orgMapping.getNewOrgId());
			NewSocietyOrganizations targetVal = new NewSocietyOrganizations();
			if (targetValues.size() > 0) {
				targetVal = targetValues.get(0);
			}
			int countServiceMemberHasObject = 0;
			int countServiceTeamHasObject = 0;
			int countServiceRecordRelyObject = 0;
			int countNewSocietyOrganitions = 0;
			int countRepateServiceMemberHasObject = 0;
			int countRepeatServiceTeamHasObject = 0;

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("oldObjecttype", "NEWSOCIETYORGANIZATIONS");
			map.put("oldObjectid", sourceOrg.getId());
			map.put("newObjecttype", "NEWSOCIETYORGANIZATIONS");
			map.put("newObjectid", targetVal.getId());

			/** 修改关联表数据、删除重复数据 */

			// 备份、删除重复服务人员数据
			backupServiceMemberHasObject(orgMapping, map);
			String deleteRepateServiceMemberHasObjectSql = "delete serviceMemberHasObject t1 where  t1.objectid = "
					+ sourceOrg.getId()
					+ " and exists ("
					+ "select 1 from serviceMemberHasObject t2 where t2.objecttype=t1.objecttype and t1.teamid = t2.teamid and t1.memberid = t2.memberid "
					+ "and t2.objectid = "
					+ targetVal.getId()
					+ ") and t1.objecttype='NEWSOCIETYORGANIZATIONS' ";
			countRepateServiceMemberHasObject += commonHandlerDAO
					.deleteData(deleteRepateServiceMemberHasObjectSql);

			// 修改服务人员和服务对象关联关系表
			String updateServiceMemberHasObjectSql = "update serviceMemberHasObject sh set sh.objectid="
					+ targetVal.getId()
					+ " where sh.objectid="
					+ sourceOrg.getId() + " ";
			countServiceMemberHasObject += commonHandlerDAO
					.updateData(updateServiceMemberHasObjectSql);

			// 备份、删除重复服务团队关联关系
			backupServiceTeamHasObject(orgMapping, map);
			String deleteRepeatServiceTeamHasObjectSql = "delete serviceTeamHasObject t1 where  t1.objectid = "
					+ sourceOrg.getId()
					+ " and exists ("
					+ "select 1 from serviceTeamHasObject t2 where t2.objecttype=t1.objecttype and t1.teamid = t2.teamid and t1.memberid = t2.memberid "
					+ "and t2.objectid = "
					+ targetVal.getId()
					+ ") and t1.objecttype= 'NEWSOCIETYORGANIZATIONS' ";
			countRepeatServiceTeamHasObject += commonHandlerDAO
					.deleteData(deleteRepeatServiceTeamHasObjectSql);
			// 修改服务团队与服务对象关联关系表
			String updateServiceTeamHasObjectSql = "update serviceTeamHasObject sh set sh.objectid="
					+ targetVal.getId()
					+ " where sh.objectid= "
					+ sourceOrg.getId() + " ";
			countServiceTeamHasObject += commonHandlerDAO
					.updateData(updateServiceTeamHasObjectSql);

			// 修改服务记录和服务对象关联关系表
			String updateServiceRecordRelyObjectSql = "update serviceRecordRelyObject sr set sr.objectid="
					+ targetVal.getId()
					+ " ,sr.objectname='"
					+ targetVal.getName()
					+ "' where sr.objectid="
					+ sourceOrg.getId() + " ";
			countServiceRecordRelyObject += commonHandlerDAO
					.updateData(updateServiceRecordRelyObjectSql);

			/** 删除源部门的重复的社会组织数据 */
			backupMainTable(orgMapping, sourceOrg.getId());
			String deleteSql = "delete from newSocietyOrganizations n1 where n1.id="
					+ sourceOrg.getId() + " ";
			// deleteSql = OrgChangeUtils.replaceScript(orgMapping, deleteSql);
			countNewSocietyOrganitions += commonHandlerDAO
					.deleteData(deleteSql);

			// 备份keyplaces
			backupRelation(orgMapping, "keyplaces", sourceOrg.getId(), "");
			/** 删除keyplaces中对应的数据 */
			String deleteKeyPlacesSql = "delete from keyplaces k where k.id="
					+ sourceOrg.getId() + " and k.type='"
					+ DocumentType.NEWSOCIETYFEDERATIONS + "'";
			commonHandlerDAO.deleteData(deleteKeyPlacesSql);

			log.appendSuccessDesc("删除服务人员与服务对象的关联关系数据量["
					+ countRepateServiceMemberHasObject + "]");
			log.appendSuccessDesc("删除服务团队与服务对象的关联关系数据量["
					+ countRepeatServiceTeamHasObject + "]");
			log.appendSuccessDesc("修改服务人员与服务对象的关联关系数据量["
					+ countServiceMemberHasObject + "]");
			log.appendSuccessDesc("修改服务团队与服务对象的关联关系数据量["
					+ countServiceTeamHasObject + "]");
			log.appendSuccessDesc("修改服务记录与服务对象的关联关系数据量["
					+ countServiceRecordRelyObject + "]");
			log.appendSuccessDesc("删除源部门主表名称重复数据量["
					+ countNewSocietyOrganitions + "]");
		}
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

	private void backupMainTable(OrgMapping orgMapping, Long valId) {
		ModuleTable moduleTable = orgMapping.getModuleTable();
		String delSql = "select id dataId,"
				+ moduleTable.getOrgIdColumn()
				+ " dataOrgId,'' expansionData FROM newSocietyOrganizations  where id="
				+ valId + " ";
		moduleTable.setSelectScript(delSql);
		moduleTable.setTableName("newSocietyOrganizations");
		backupBaseDataService.addBackupBaseData(orgMapping);
	}

	private void backupServiceMemberHasObject(OrgMapping orgMapping, Map map) {
		List<ServiceMemberWithObject> serviceMemberWithObjectList = serviceMemberWithObjectService
				.queryServiceMemberWithObjectForList(map);
		if (serviceMemberWithObjectList != null
				&& serviceMemberWithObjectList.size() > 0) {
			for (ServiceMemberWithObject serviceMemberWithObject : serviceMemberWithObjectList) {
				String expansionData = "删除社会组织所对应的服务成员:OBJECTTYPE:"
						+ serviceMemberWithObject.getObjectType()
						+ "-OBJECTID:" + serviceMemberWithObject.getObjectId()
						+ "=TEAMID:" + serviceMemberWithObject.getTeamId()
						+ ",MEMBERID:" + serviceMemberWithObject.getMemberId();
				backupRelation(orgMapping, "serviceMemberHasObject",
						serviceMemberWithObject.getId(), expansionData);
			}
		}
	}

	private void backupServiceTeamHasObject(OrgMapping orgMapping, Map map) {
		List<ServiceTeamHasObjects> serviceTeamHasObjectsList = serviceTeamMemberService
				.queryServiceTeamHasObjectsForList(map);
		if (serviceTeamHasObjectsList != null
				&& serviceTeamHasObjectsList.size() > 0) {
			for (ServiceTeamHasObjects serviceTeamHasObjects : serviceTeamHasObjectsList) {
				String expansionData = "删除服务团队和服务对象的关联关系表:OBJECTTYPE:"
						+ serviceTeamHasObjects.getObjectType() + "-OBJECTID:"
						+ serviceTeamHasObjects.getObjectId() + "=TEAMID:"
						+ serviceTeamHasObjects.getTeamId() + ",MEMBERID:"
						+ serviceTeamHasObjects.getMemberId();
				backupRelation(orgMapping, "serviceTeamHasObject",
						serviceTeamHasObjects.getId(), expansionData);
			}
		}
	}

	/***
	 * 删除关系的数据备份
	 */
	private void backupRelation(OrgMapping orgMapping, String tableName,
			Long dataId, String expansionData) {
		BackupBaseData backupBaseData = new BackupBaseData();
		backupBaseData.setOrgChangeInfo(new OrgChangeInfo());
		backupBaseData.getOrgChangeInfo().setId(
				orgMapping.getOrgChangeInfo().getId());
		backupBaseData.setDataId(dataId);
		backupBaseData.setExpansionData(expansionData);
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
