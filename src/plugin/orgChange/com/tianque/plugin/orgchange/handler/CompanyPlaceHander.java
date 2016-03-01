package com.tianque.plugin.orgchange.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("companyPlaceHander")
public class CompanyPlaceHander extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(CompanyPlaceHander.class);
	// 统计脚本
	private final static String COUNT_ORG_SQL = "SELECT COUNT(*) FROM  #TABLENAME# WHERE #ORGIDCOLUMN#=#OLDORGID# AND #ORGCODECOLUMN#=#OLDORGCODE#";

	// 更新脚本
	private final static String UPDATE_ORG_SQL = "UPDATE #TABLENAME# SET #ORGIDCOLUMN#=#NEWORGID#,#ORGCODECOLUMN#=#NEWORGCODE# WHERE #ORGIDCOLUMN#=#OLDORGID# AND #ORGCODECOLUMN#=#OLDORGCODE#";
	// 重名更新脚本
	private final static String UPDATE_ORG_NAME_ID_SQL = "UPDATE #TABLENAME# SET #ORGIDCOLUMN#=#NEWORGID#,#ORGCODECOLUMN#=#NEWORGCODE#,NAME = NAME || #ORGNAME# WHERE ID = #ID#";
	// 查询需要转移的目标ID（重复）
	// private String SELECT_ID_TRANSFER_TARGET_SQL =
	// "SELECT ID FROM (SELECT CP.ID, CPB.NAME,CPB.ORG,CPB.ORGINTERNALCODE,CP.CLASSIFICATION FROM COMPANYPLACEBASE CPB,COMPANYPLACE CP WHERE  CPB.ID = CP.BASEID) WHERE NAME =#NAME# AND #ORGIDCOLUMN#=#NEWORGID# AND ID != #ID#";
	// 维护keyplace脚本
	private final static String UPDATE_KEYPLACE_SQL = "UPDATE KEYPLACES SET NAME = NAME || #ORGNAME# WHERE ORGID=#OLDORGID# ";
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public void init(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin init.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		String countSql = orgMapping.getModuleTable().getCountScript();
		if (!StringUtil.isStringAvaliable(countSql)) {
			countSql = COUNT_ORG_SQL;
		}
		countSql = OrgChangeUtils.replaceScript(orgMapping, countSql);
		int count = commonHandlerDAO.getCount(OrgChangeUtils.replaceScript(
				orgMapping, countSql));
		if (count > 0) {
			orgMapping.setHasData(true);
		} else {
			orgMapping.setHasData(false);
		}
		log.appendSuccessDesc("需要更新的数量[" + count + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end init.orgMapping:{}", orgMapping);
		}
	}

	@Override
	public void backup(OrgMapping orgMapping) {
		// 实际上只做迁移，没有删除操作，只重写
	}

	// 迁移
	@Override
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin transfer.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		int changeCount = 0;
		String updateSql = OrgChangeUtils.replaceScript(orgMapping,
				UPDATE_ORG_SQL);
		if (StringUtil.isStringAvaliable(updateSql)) {
			changeCount = commonHandlerDAO.updateData(updateSql);
			// keyplace (keyplaces表中的数据统一在房屋中更新这里只对合并时 做特殊处理)
			// String updateKeyplaceSql =
			// OrgChangeUtils.replaceScript(orgMapping,
			// UPDATE_KEYPLACE_SQL.replace("#ORGNAME#", "''"));
			// synchronizedKeyplaces(updateKeyplaceSql);
		}
		log.appendSuccessDesc("更新数量[" + changeCount + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end transfer.orgMapping:{}", orgMapping);
		}
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
		int updateCount = 0;
		String repeatedSql = orgMapping.getModuleTable().getSelectScript();
		List<String> repeatedIds = commonHandlerDAO
				.queryStringForList(OrgChangeUtils.replaceScript(orgMapping,
						repeatedSql));
		String updateKeyplaceSql = "";
		if (repeatedIds != null && repeatedIds.size() > 0) {
			Organization oldOrg = organizationDubboService
					.getSimpleOrgById(orgMapping.getOldOrgId());
			for (String id : repeatedIds) {
				String updateSql = OrgChangeUtils.replaceScript(
						orgMapping,
						UPDATE_ORG_NAME_ID_SQL.replace("#ORGNAME#",
								"'来自" + oldOrg.getOrgName() + "'").replace(
								"#ID#", id));
				updateCount += commonHandlerDAO.updateData(updateSql);
				// keyplace
				updateKeyplaceSql = OrgChangeUtils
						.replaceScript(
								orgMapping,
								UPDATE_KEYPLACE_SQL.replace("#ORGNAME#", "'来自"
										+ oldOrg.getOrgName() + "'")
										+ " AND ID = "
										+ id
										+ " AND TYPE NOT IN ('RENTALHOUSE','NEWSOCIETYFEDERATIONS','NEWECONOMICORGANIZATIONS')");
				synchronizedKeyplaces(updateKeyplaceSql);
			}
		}
		updateCount += commonHandlerDAO.updateData(OrgChangeUtils
				.replaceScript(orgMapping, UPDATE_ORG_SQL));
		// keyplace (keyplaces表中的数据统一在房屋中更新这里只对合并时 做特殊处理)
		// updateKeyplaceSql = OrgChangeUtils.replaceScript(orgMapping,
		// UPDATE_KEYPLACE_SQL.replace("#ORGNAME#", "''"));
		// synchronizedKeyplaces(updateKeyplaceSql);
		log.appendSuccessDesc("更新数量[" + updateCount + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end merge.orgMapping:{}", orgMapping);
		}
	}

	// 同步keyplaces
	private int synchronizedKeyplaces(String updateSql) {
		if (StringUtil.isStringAvaliable(updateSql)) {
			return commonHandlerDAO.updateData(updateSql);
		}
		return 0;
	}
}
