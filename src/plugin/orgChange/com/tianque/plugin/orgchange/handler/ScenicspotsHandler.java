package com.tianque.plugin.orgchange.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.domain.Organization;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 
 * @author LG 单位场所 旅游景点
 */
@Component("scenicspotsHandler")
public class ScenicspotsHandler extends AbstractOrgChangeHandler {
	private final static Logger logger = LoggerFactory
			.getLogger(ScenicspotsHandler.class);

	@Autowired
	private OrganizationDubboService organizationDubboService;

	// 迁移合并脚本
	private final static String UPDATE_ORG_SQL = "UPDATE #TABLENAME# SET #ORGIDCOLUMN#=#NEWORGID#,#ORGCODECOLUMN#=#NEWORGCODE# WHERE #ORGIDCOLUMN#=#OLDORGID# AND #ORGCODECOLUMN#=#OLDORGCODE#";

	private final static String UPDATE_ORG_NAME_ID_SQL = "UPDATE #TABLENAME# SET #ORGIDCOLUMN#=#NEWORGID#,#ORGCODECOLUMN#=#NEWORGCODE#,NAME = NAME || #ORGNAME# WHERE ID = #ID#";
	// 统计所有
	private final static String COUNT_ORG_SQL = "SELECT COUNT(*) FROM #TABLENAME#  WHERE #ORGIDCOLUMN#=#OLDORGID# AND #ORGCODECOLUMN#=#OLDORGCODE#";
	// 删除重复
	// private final static String DELETE_ID_SQL = "DELETE FROM #TABLENAME#";
	// 统计所有（ID）
	// private final static String SELECT_ORG_SQL =
	// "SELECT ID FROM #TABLENAME#  WHERE #ORGIDCOLUMN#=#OLDORGID# AND #ORGCODECOLUMN#=#OLDORGCODE#";
	// 统计重复（ID）
	private final static String SELECT_REPEATED_SQL = "SELECT ID AS RESULT FROM #TABLENAME# SOURCETABLE WHERE EXISTS (SELECT NAME FROM SCENICSPOTS TARGETTABLE WHERE TARGETTABLE.ORGID = #NEWORGID#  AND TARGETTABLE.NAME = SOURCETABLE.NAME)AND SOURCETABLE.ORGID = #OLDORGID# AND #OLDORGID# <> #NEWORGID#";

	@Override
	public void init(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin init.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		int countChange = commonHandlerDAO.getCount(OrgChangeUtils
				.replaceScript(orgMapping, COUNT_ORG_SQL));
		if (countChange > 0) {
			orgMapping.setHasData(true);
		} else {
			orgMapping.setHasData(false);
		}
		log.appendSuccessDesc("需要变更新数量[" + countChange + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end init.orgMapping:{}", orgMapping);
		}
	}

	@Override
	public void backup(OrgMapping orgMapping) {
		// 实际上只做迁移，不用备份
	}

	@Override
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin transfer.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		int changeCount = commonHandlerDAO.updateData(OrgChangeUtils
				.replaceScript(orgMapping, UPDATE_ORG_SQL));
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
		Organization oldOrg = organizationDubboService
				.getSimpleOrgById(orgMapping.getOldOrgId());
		// 查询出有重复名称的记录
		List<String> repeatedIds = commonHandlerDAO
				.queryStringForList(OrgChangeUtils.replaceScript(orgMapping,
						SELECT_REPEATED_SQL));
		if (!emptyList(repeatedIds)) {
			for (String id : repeatedIds) {
				String updateSql = UPDATE_ORG_NAME_ID_SQL.replace("#ORGNAME#",
						"'来自" + oldOrg.getOrgName() + "'").replace("#ID#", id);
				updateCount += commonHandlerDAO.updateData(OrgChangeUtils
						.replaceScript(orgMapping, updateSql));
			}
		}
		updateCount += commonHandlerDAO.updateData(OrgChangeUtils
				.replaceScript(orgMapping, UPDATE_ORG_SQL));
		log.appendSuccessDesc("更新数量[" + updateCount + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end merge.orgMapping:{}", orgMapping);
		}
	}

	private boolean emptyList(List list) {
		if (null != list && list.size() > 0) {
			return false;
		}
		return true;
	}
}
