package com.tianque.plugin.orgchange.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrgVo;
import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrganizations;
import com.tianque.baseInfo.primaryOrg.primaryMembers.service.PrimaryMembersOrgTypeService;
import com.tianque.baseInfo.primaryOrg.service.PrimaryOrgService;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.common.PrimaryOrganizationsOrgChangeStat;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("primaryOrganizationsHandler")
public class PrimaryOrganizationsHandler extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(PrimaryOrganizationsHandler.class);

	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PrimaryOrgService primaryOrgService;
	@Autowired
	private PrimaryMembersOrgTypeService primaryMembersOrgTypeService;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	public static String DELETE_SQL = "delete from #tableName# p1 where orgId = #oldOrgId#  and exists "
			+ "(select * from #tableName# p2  where p2.orgId = "
			+ " #newOrgId# and p1.detailname = p2.detailname  and p1.teamclass = p2.teamclass  and p1.teamtype = p2.teamtype ";
	private final static String UPDATE_ID_CODE_PARENTID_SQL = "UPDATE #TABLENAME# SET #ORGIDCOLUMN#=#NEWORGID#,#ORGCODECOLUMN#=#NEWORGCODE#,PARENTID=#NEWPARENTID# WHERE #ORGIDCOLUMN# = #OLDORGID#";

	@Override
	public void init(OrgMapping orgMapping) {
		// 把冗余的parentid值放入对象
		if (orgMapping != null && orgMapping.getNewOrgId() != null) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgMapping.getNewOrgId());
			if (organization != null && organization.getParentOrg() != null
					&& organization.getParentOrg().getId() != null) {
				orgMapping.setNewParentOrgId(organization.getParentOrg()
						.getId());
			}
		}
		super.init(orgMapping);
	}

	@Override
	public void transfer(OrgMapping orgMapping) {
		orgMapping.getModuleTable()
				.setUpdateScript(UPDATE_ID_CODE_PARENTID_SQL);
		super.transfer(orgMapping);
	}

	// 合并
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
		Map<String, List<PropertyDict>> bigTypes = getFourValuesByDict();
		Map<String, List<PropertyDict>> leftBigTypes = getBigTypesByDomain();
		ModuleTable moduleTable = orgMapping.getModuleTable();
		String deleteSql = moduleTable.getCountScript();

		if (!StringUtil.isStringAvaliable(deleteSql)) {
			deleteSql = DELETE_SQL;
		}
		int delVal = 0;

		for (String bigTypeKey : leftBigTypes.keySet()) {
			for (String typeKey : bigTypes.keySet()) {
				if ("fourValue".equals(bigTypeKey)
						&& ("comprehensive".equals(typeKey) || "leaderTeam"
								.equals(typeKey))) {
					List<PrimaryOrganizations> fourPrimaryOrganizations = primaryOrgService
							.findAllPrimaryOrgForOrgChange(
									orgMapping.getOldOrgId(),
									orgMapping.getNewOrgId(), "1");
					updatePrimarymembersorgtypeOrgId(orgMapping,
							fourPrimaryOrganizations);

					backup(orgMapping, "four");
					deleteSql = deleteSql
							+ " and p1.departmenttype = p2.departmenttype) and #oldOrgId# <> #newOrgId#";
					deleteSql = OrgChangeUtils.replaceScript(orgMapping,
							deleteSql);
					delVal += commonHandlerDAO.deleteData(deleteSql);
					deleteSql = DELETE_SQL;

				} else if ("threeValue".equals(bigTypeKey)) {
					List<PrimaryOrganizations> threePrimaryOrganizations = primaryOrgService
							.findAllPrimaryOrgForOrgChange(
									orgMapping.getOldOrgId(),
									orgMapping.getNewOrgId(), "");
					updatePrimarymembersorgtypeOrgId(orgMapping,
							threePrimaryOrganizations);

					backup(orgMapping, "three");
					deleteSql = deleteSql + " ) and #oldOrgId# <> #newOrgId#";
					deleteSql = OrgChangeUtils.replaceScript(orgMapping,
							deleteSql);
					delVal += commonHandlerDAO.deleteData(deleteSql);
					deleteSql = DELETE_SQL;

				}
			}
		}
		log.appendSuccessDesc("删除重复数据" + delVal);
		int num;
		// 再执行更新语句
		String updateSql = UPDATE_ID_CODE_PARENTID_SQL;
		updateSql = OrgChangeUtils.replaceScript(orgMapping, updateSql);
		if ("UPDATE".equals(OrgChangeUtils.getFirstWord(updateSql))) {
			num = commonHandlerDAO.updateData(updateSql);
			log.setSuccessDesc(log.getDescription() + "更新数据量[" + num + "]");
		} else {
			log.setErrorDesc(log.getDescription() + "更新脚本错误：非update开头的更新语句");
			throw new OrgChangeHandlerException(log);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("end merge.orgMapping:{}", orgMapping);
		}
	}

	private void updatePrimarymembersorgtypeOrgId(OrgMapping orgMapping,
			List<PrimaryOrganizations> primaryOrganizations) {
		for (PrimaryOrganizations po : primaryOrganizations) {
			PrimaryOrgVo pvo = new PrimaryOrgVo();
			pvo.setTeamClazz(po.getTeamClazz());
			pvo.setTeamType(po.getTeamType());
			pvo.setDetailName(po.getDetailName());
			if (po.getDepartmentType() != null) {
				pvo.setDepartmentType(po.getDepartmentType());
			}

			// 查询出目标部门的重复数据
			List<PrimaryOrganizations> targetPoList = primaryOrgService
					.findPrimaryOrgByOrgIdAndDetailnameForOrgChange(pvo,
							orgMapping.getNewOrgId());
			if (targetPoList.size() > 0) {
				PrimaryOrganizations targetPo = targetPoList.get(0);
				primaryMembersOrgTypeService
						.updatePrimarymembersorgtypeOrgIdForOrgChange(
								targetPo.getId(), po.getId());
			}
		}
	}

	private Map<String, List<PropertyDict>> getFourValuesByDict() {
		/** 综治办的类别的集合 */
		List<PropertyDict> comprehensiveDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.MANAGEMENT_TYPE);
		/** 专项工作小组类别集合 */
		List<PropertyDict> leaderTeamDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.LEADERGROUP_TYPE);

		List<PropertyDict> comprehensiveLists = new ArrayList<PropertyDict>();// 综治办list集合
		List<PropertyDict> leaderTeamLists = new ArrayList<PropertyDict>();// 专项工作小组List集合

		Map<String, List<PropertyDict>> map = new HashMap<String, List<PropertyDict>>();
		for (PropertyDict dict : comprehensiveDicts) {
			if (PrimaryOrganizationsOrgChangeStat.comprehensives.contains(dict
					.getDisplayName())) {
				comprehensiveLists.add(dict);

			}
		}
		for (PropertyDict dict : leaderTeamDicts) {
			if (PrimaryOrganizationsOrgChangeStat.leaderTeams.contains(dict
					.getDisplayName())) {
				leaderTeamLists.add(dict);

			}
		}
		map.put("comprehensive", comprehensiveLists);
		map.put("leaderTeam", leaderTeamLists);
		return map;
	}

	private Map<String, List<PropertyDict>> getBigTypesByDomain() {
		/** 左边菜单栏，大类 */
		List<PropertyDict> bigTypes = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.TEAMCLAZZ);

		List<PropertyDict> threeValues = new ArrayList<PropertyDict>();
		List<PropertyDict> fourValues = new ArrayList<PropertyDict>();

		Map<String, List<PropertyDict>> map = new HashMap<String, List<PropertyDict>>();
		for (PropertyDict dict : bigTypes) {
			if (PrimaryOrganizationsOrgChangeStat.threeValues.contains(dict
					.getInternalId())) {
				threeValues.add(dict);
			} else if (PrimaryOrganizationsOrgChangeStat.fourValues
					.contains(dict.getInternalId())) {
				fourValues.add(dict);
			}
		}
		map.put("threeValue", threeValues);
		map.put("fourValue", fourValues);
		return map;
	}

	private void backup(OrgMapping orgMapping, String type) {
		ModuleTable moduleTable = orgMapping.getModuleTable();
		String delSql = "select id dataId,"
				+ moduleTable.getOrgIdColumn()
				+ " dataOrgId,'' expansionData FROM  #tableName# p1 where orgId = #oldOrgId#  and exists "
				+ "(select * from #tableName# p2  where p2.orgId = "
				+ " #newOrgId# and p1.detailname = p2.detailname  and p1.teamclass = p2.teamclass  and p1.teamtype = p2.teamtype ";
		if ("three".equals(type)) {
			delSql = delSql + " ) and #oldOrgId# <> #newOrgId#";
		} else if ("four".equals(type)) {
			delSql = delSql
					+ " and p1.departmenttype = p2.departmenttype) and #oldOrgId# <> #newOrgId#";
		}
		delSql = OrgChangeUtils.replaceScript(orgMapping, delSql);
		moduleTable.setSelectScript(delSql);
		moduleTable.setTableName("PRIMARYORGANIZATIONS");
		backupBaseDataService.addBackupBaseData(orgMapping);
	}

}
