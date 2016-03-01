package com.tianque.plugin.orgchange.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.dataManage.util.DataManageBaseInfo;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.constant.DataManageDBOrgChangeStat;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @Description:组织机构迁移合并公用类
 * @author zhangyouwei@hztian.com
 * @date: 2014-10-11 上午10:37:22
 */
@Component("dataManageDBOrgChangeHandler")
public class DataManageDBOrgChangeHandler extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(DataManageDBOrgChangeHandler.class);
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/** 数据管理各个模块的对应的表 */
	private static List<DataManageBaseInfo> dataManageBaseInfos;

	/**
	 * 初始化(由于全部的表统一处理在此处不能够去做处理)
	 */
	public void init(OrgMapping orgMapping) {
		try {
			dataManageBaseInfos = DataManageBaseInfoUtil.getXmlList();
			/** 填充出迁移合并前后组织机构所对应的县级组织机构Id */
			fillOrgMappingDistrictOrgId(orgMapping);

		} catch (Exception e) {
			throw new ServiceValidationException("数据管理迁移合并初始化错误", e);
		}
	}

	/**
	 * 填充出迁移合并前后组织机构所对应的县级组织机构Id
	 * 
	 * @param orgMapping
	 */
	private void fillOrgMappingDistrictOrgId(OrgMapping orgMapping) {
		orgMapping.setNewDistrictOrgId(organizationDubboService
				.findOrganizationByOrgIdAndNum(orgMapping.getNewOrgId(),
						OrganizationType.DISTRICT_LEVEL).get(0).getId());
	}

	/**
	 * 迁移方法
	 */
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin transfer.");
		try {
			distributeTransfer(orgMapping);
		} catch (Exception e) {
			throw new ServiceValidationException("数据管理迁移合并迁移错误", e);
		}
		if (logger.isDebugEnabled())
			logger.debug("end transfer.");
	}

	/**
	 * 分发迁移
	 * 
	 * @param orgMapping
	 */
	private void distributeTransfer(OrgMapping orgMapping) {
		for (DataManageBaseInfo dataManageBaseInfo : dataManageBaseInfos) {
			orgMapping.getModuleTable().setTableName(
					dataManageBaseInfo.getTable());
			OrgChangeLog log = orgMapping.getOrgChangeLog();
			log.appendSuccessDesc("<br />================================="
					+ "<br />迁移表[" + orgMapping.getModuleTable().getTableName()
					+ "]");
			convertOrgIdColumByMode(orgMapping, dataManageBaseInfo);
			commonDeal(orgMapping);
		}
	}

	/**
	 * 合并
	 * 
	 * @param orgMapping
	 * 
	 */
	public void merge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled())
			logger.debug("begin merge.");
		try {
			distributeMerge(orgMapping);
		} catch (Exception e) {
			throw new ServiceValidationException("数据管理迁移合并合并错误", e);
		}
		if (logger.isDebugEnabled())
			logger.debug("end merge.");
	}

	/**
	 * 分发去合并（主要分为人口、单位场所、社会组织、新经济组织、其他【部件信息、楼宇信息、出租房、实有房屋】）
	 */
	private void distributeMerge(OrgMapping orgMapping) {
		for (DataManageBaseInfo dataManageBaseInfo : dataManageBaseInfos) {
			orgMapping.getModuleTable().setTableName(
					dataManageBaseInfo.getTable());
			OrgChangeLog log = orgMapping.getOrgChangeLog();
			log.appendSuccessDesc("<br />================================="
					+ "<br />表[" + orgMapping.getModuleTable().getTableName()
					+ "]");
			convertOrgIdColumByMode(orgMapping, dataManageBaseInfo);
			if ("population".equals(dataManageBaseInfo.getMode())) {// 人口相关
				if ("overseaPersonnelTemp".equals(dataManageBaseInfo.getType())) {
					overseaPersonnelMerge(orgMapping);
				} else {
					populationMerge(orgMapping);
				}
			} else if ("companyPlace".equals(dataManageBaseInfo.getMode())) {// 单位场所
				companyPlaceMerge(dataManageBaseInfo, orgMapping);
			} else if ("newSocietyOrganizationsTemp".equals(dataManageBaseInfo
					.getType())) {// 社会组织
				newSocietyOrganizationMerge(dataManageBaseInfo, orgMapping);
			} else if ("newEconomicOrganizationsTemp".equals(dataManageBaseInfo
					.getType())) {// 新经济组织
				newEconomicOrganizationMerge(dataManageBaseInfo, orgMapping);
			} else if ("dustbin".equals(dataManageBaseInfo.getMode())
					|| "builddatas".equals(dataManageBaseInfo.getMode())
					|| "actualHouseTemp".equals(dataManageBaseInfo.getType())
					|| "rentalHouseTemp".equals(dataManageBaseInfo.getType())) {// 其他【部件信息、楼宇信息、出租房、实有房屋】
				otherMerge(dataManageBaseInfo, orgMapping);
			}
		}
	}

	/**
	 * 根据不同的模块设置orgId字段
	 * 
	 * @param orgMapping
	 * @param dataManageBaseInfo
	 */
	private void convertOrgIdColumByMode(OrgMapping orgMapping,
			DataManageBaseInfo dataManageBaseInfo) {

		if ("companyPlace".equals(dataManageBaseInfo.getMode())) {// 单位场所特殊处理
			orgMapping.getModuleTable().setOrgIdColumn("ORG");
		} else if ("builddatas".equals(dataManageBaseInfo.getMode())) {// 楼宇
			orgMapping.getModuleTable().setOrgIdColumn("ORGANIZATION");
		} else {
			orgMapping.getModuleTable().setOrgIdColumn("ORGID");
		}

	}

	/**
	 * 人口相关的数据管理合并
	 * 
	 * @param dataManageBaseInfo
	 * @param orgMapping
	 */
	private void populationMerge(OrgMapping orgMapping) {

		// 处理人口相关重复数据
		dealDuplicateData(
				DataManageDBOrgChangeStat.COUNT_DUPLICATE_FOR_POPULATION_SQL,
				DataManageDBOrgChangeStat.CHANGE_DUPLICATE_FOR_POPULATION_SQL,
				orgMapping);

		// 公共的方法，单纯的迁移操作也可以调用
		commonDeal(orgMapping);

	}

	/**
	 * 境外人员的数据管理合并
	 * 
	 * @param dataManageBaseInfo
	 * @param orgMapping
	 */
	private void overseaPersonnelMerge(OrgMapping orgMapping) {

		// 处理人口相关重复数据
		dealDuplicateData(
				DataManageDBOrgChangeStat.COUNT_DUPLICATE_FOR_OVERSEAPERSONNEL_SQL,
				DataManageDBOrgChangeStat.CHANGE_DUPLICATE_FOR_OVERSEAPERSONNEL_SQL,
				orgMapping);

		// 公共的方法，单纯的迁移操作也可以调用
		commonDeal(orgMapping);

	}

	/**
	 * 单位场所数据管理合并
	 * 
	 * @param dataManageBaseInfo
	 * @param orgMapping
	 */
	private void companyPlaceMerge(DataManageBaseInfo dataManageBaseInfo,
			OrgMapping orgMapping) {
		// 处理人口相关重复数据
		dealDuplicateData(
				DataManageDBOrgChangeStat.COUNT_DUPLICATE_FOR_COMPANYPLACE_SQL,
				DataManageDBOrgChangeStat.CHANGE_DUPLICATE_FOR_COMPANYPLACE_SQL,
				orgMapping);

		// 公共的方法，单纯的迁移操作也可以调用
		commonDeal(orgMapping);
	}

	/**
	 * 社会组织数据管理合并
	 * 
	 * @param dataManageBaseInfo
	 * @param orgMapping
	 */
	private void newSocietyOrganizationMerge(
			DataManageBaseInfo dataManageBaseInfo, OrgMapping orgMapping) {
		// 处理人口相关重复数据
		dealDuplicateData(
				DataManageDBOrgChangeStat.COUNT_DUPLICATE_FOR_NEWSOCIETYORGANIZATION_SQL,
				DataManageDBOrgChangeStat.CHANGE_DUPLICATE_FOR_NEWSOCIETYORGANIZATION_SQL,
				orgMapping);

		// 公共的方法，单纯的迁移操作也可以调用
		commonDeal(orgMapping);

	}

	/**
	 * 新经济组织数据管理合并
	 * 
	 * @param dataManageBaseInfo
	 * @param orgMapping
	 */
	private void newEconomicOrganizationMerge(
			DataManageBaseInfo dataManageBaseInfo, OrgMapping orgMapping) {
		dealDuplicateData(
				DataManageDBOrgChangeStat.COUNT_DUPLICATE_FOR_NEWECONOMICORGANIZATION_SQL,
				DataManageDBOrgChangeStat.CHANGE_DUPLICATE_FOR_NEWECONOMICORGANIZATION_SQL,
				orgMapping);
		/** 处理名称为来自A网格 */
		dealDuplicateDataName(
				DataManageDBOrgChangeStat.COUNT_DUPLICATE_FOR_NEWECONOMICORGANIZATION_FOR_NAME_SQL,
				DataManageDBOrgChangeStat.CHANGE_DUPLICATE_FOR_NEWECONOMICORGANIZATION_FOR_NAME_SQL,
				orgMapping);

		// 公共的方法，单纯的迁移操作也可以调用
		commonDeal(orgMapping);

	}

	/**
	 * 处理只有名称重复为来自A网格
	 * 
	 * @param coutSql
	 * @param updateSql
	 */
	private void dealDuplicateDataName(String coutSql, String updateSql,
			OrgMapping orgMapping) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		log.appendSuccessDesc("当前处理操作为[处理名称为来自A网格]");
		Organization oldOrg = organizationDubboService
				.getSimpleOrgById(orgMapping.getOldOrgId());
		updateSql = updateSql.replace("#CHANGEORGNAME#",
				"'来自" + oldOrg.getOrgName() + "'");
		chanageData(coutSql, updateSql, orgMapping);
	}

	/**
	 * 其他数据管理合并【部件信息、楼宇信息、出租房、实有房屋】
	 * 
	 * @param dataManageBaseInfo
	 * @param orgMapping
	 */
	private void otherMerge(DataManageBaseInfo dataManageBaseInfo,
			OrgMapping orgMapping) {
		commonDeal(orgMapping);

	}

	/**
	 * 重复数据处理后就相当于迁移操作（直接修改即可），故提出公共方法
	 * 
	 * @param orgMapping
	 */
	private void commonDeal(OrgMapping orgMapping) {
		// 处理数据所在的组织机构orgid
		dealOrgIdAndOrgCode(DataManageDBOrgChangeStat.COUNT_ORGID_SQL,
				DataManageDBOrgChangeStat.CHANGE_ORGID_SQL, orgMapping);
		if (!orgMapping.getNewOrgId().equals(orgMapping.getOldOrgId())) {
			// 处理认领人网格id claimorgid
			dealClaimOrgId(DataManageDBOrgChangeStat.COUNT_CLAIMORGID_SQL,
					DataManageDBOrgChangeStat.CHANGE_CLAIMORGID_SQL, orgMapping);
			// 处理importdepartmentid(导入部门Id)
			dealImportDepartmentId(
					DataManageDBOrgChangeStat.COUNT_IMPORTDEPARTMENTID_SQL,
					DataManageDBOrgChangeStat.CHANGE_IMPORTDEPARTMENTID_SQL,
					orgMapping);
			// 处理oldorgid(刚导入时的所属 网格Id)
			dealOldOrgId(DataManageDBOrgChangeStat.COUNT_OLDORGID_SQL,
					DataManageDBOrgChangeStat.CHANGE_OLDORGID_SQL, orgMapping);
		}
		if (!orgMapping.getNewDistrictOrgId().equals(
				orgMapping.getOldDistrictOrgId())) {
			// 处理districtorgid(导入时的到县区的组织机构)
			dealDistrictOrgId(
					DataManageDBOrgChangeStat.COUNT_DISTRICTORGID_SQL,
					DataManageDBOrgChangeStat.CHANGE_DISTRICTORGID_SQL,
					orgMapping);
		}
	}

	/**
	 * 处理重复数据
	 * 
	 * @param coutSql
	 * @param updateSql
	 */
	private void dealDuplicateData(String coutSql, String updateSql,
			OrgMapping orgMapping) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		log.appendSuccessDesc("当前处理操作为[处理重复数据]");
		chanageData(coutSql, updateSql, orgMapping);
	}

	/**
	 * 处理数据所在的组织机构orgid
	 * 
	 * @param coutSql
	 * @param updateSql
	 */
	private void dealOrgIdAndOrgCode(String coutSql, String updateSql,
			OrgMapping orgMapping) {

		OrgChangeLog log = orgMapping.getOrgChangeLog();
		log.appendSuccessDesc("当前处理操作为[修改数据所在的组织机构orgid)]");
		chanageData(coutSql, updateSql, orgMapping);
	}

	/**
	 * 处理认领人网格id claimorgid
	 * 
	 * @param coutSql
	 * @param updateSql
	 */
	private void dealClaimOrgId(String coutSql, String updateSql,
			OrgMapping orgMapping) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		log.appendSuccessDesc("当前处理操作为[修改claimorgid(认领人所在网格id)]");
		chanageData(coutSql, updateSql, orgMapping);

	}

	/**
	 * 处理importdepartmentid(导入部门Id)
	 * 
	 * @param coutSql
	 * @param updateSql
	 */
	private void dealImportDepartmentId(String coutSql, String updateSql,
			OrgMapping orgMapping) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		log.appendSuccessDesc("当前处理操作为[修改importdepartmentid(导入部门Id)]");
		chanageData(coutSql, updateSql, orgMapping);
	}

	/**
	 * 处理oldorgid(刚导入时的所属 网格Id)
	 * 
	 * @param coutSql
	 * @param updateSql
	 */
	private void dealOldOrgId(String coutSql, String updateSql,
			OrgMapping orgMapping) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		log.appendSuccessDesc("当前处理操作为[修改oldorgid(刚导入时的所属 网格Id)]");
		chanageData(coutSql, updateSql, orgMapping);

	}

	/**
	 * 处理districtorgid(导入时的到县区的组织机构)
	 * 
	 * @param coutSql
	 * @param updateSql
	 */

	private void dealDistrictOrgId(String coutSql, String updateSql,
			OrgMapping orgMapping) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		log.appendSuccessDesc("当前处理操作为[修改districtorgid(导入时的到县区的组织机构)]");
		chanageData(coutSql, updateSql, orgMapping);
	}

	/**
	 * 公用的修改方法
	 * 
	 * @param coutSql
	 *            统计语句
	 * @param updateSql
	 *            修改语句
	 * @param orgMapping
	 */
	private void chanageData(String coutSql, String updateSql,
			OrgMapping orgMapping) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		if (!"SELECT".equals(OrgChangeUtils.getFirstWord(coutSql))) {
			log.setErrorDesc(log.getDescription() + "COUNT脚本错误：非SELECT开头的查询语句");
			throw new OrgChangeHandlerException(log);
		}
		if (!"UPDATE".equals(OrgChangeUtils.getFirstWord(updateSql))) {
			log.setErrorDesc(log.getDescription() + "更新脚本错误：非update开头的更新语句");
			throw new OrgChangeHandlerException(log);
		}
		String countSqlResult = OrgChangeUtils.replaceScript(orgMapping,
				coutSql);
		int countResult = commonHandlerDAO.getCount(countSqlResult);
		log.appendSuccessDesc("需要变更的数据量[" + countResult + "]");
		int updateResult = 0;
		if (countResult > 0) {
			String updateSqlResult = OrgChangeUtils.replaceScript(orgMapping,
					updateSql);
			updateResult = commonHandlerDAO.updateData(updateSqlResult);
		}
		log.appendSuccessDesc("更新数量为[" + updateResult + "]<br />");
	}
}