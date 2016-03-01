package com.tianque.plugin.orgchange.handler;

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
 * @ClassName: IssueCountHandler
 * @Description: TODO研判分析事件统计handler
 * @author wanggz
 * @date 2014-10-23 下午03:43:08
 */
@Component("issueCountHandler")
public class IssueCountHandler extends AbstractOrgChangeHandler {

	private final static Logger logger = LoggerFactory
			.getLogger(IssueCountHandler.class);
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin transfer.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();

		countNeedUpdateNum(orgMapping);

		Organization childOrg = organizationDubboService
				.getSimpleOrgById(orgMapping.getOldOrgId());
		Long newParentOrgId = 0l;
		if (childOrg != null && childOrg.getParentOrg() != null) {
			newParentOrgId = childOrg.getParentOrg().getId();
		}
		String updateParentIdAndCodeSql = "update #tableName# i set i.orginternalcode=#newOrgCode# ,i.parentorgid="
				+ newParentOrgId + " where i.orgid=#oldOrgId#";
		updateParentIdAndCodeSql = OrgChangeUtils.replaceScript(orgMapping,
				updateParentIdAndCodeSql);
		int num = commonHandlerDAO.updateData(updateParentIdAndCodeSql);
		log.appendSuccessDesc("更新数据量[" + num + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end transfer.");
		}
	}

	@Override
	public void merge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin merge.orgMapping:{}", orgMapping);
		}
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		countNeedUpdateNum(orgMapping);
		Organization childOrg = organizationDubboService
				.getSimpleOrgById(orgMapping.getNewOrgId());
		Long newParentOrgId = 0l;
		if (childOrg != null && childOrg.getParentOrg() != null) {
			newParentOrgId = childOrg.getParentOrg().getId();
		}
		String updateParentIdAndOrgIdAndCodeSql = "update #tableName# i set i.orgid=#newOrgId#, i.orginternalcode=#newOrgCode# ,i.parentorgid="
				+ newParentOrgId + " where i.orgid=#oldOrgId#";
		updateParentIdAndOrgIdAndCodeSql = OrgChangeUtils.replaceScript(
				orgMapping, updateParentIdAndOrgIdAndCodeSql);
		int num = commonHandlerDAO.updateData(updateParentIdAndOrgIdAndCodeSql);
		log.appendSuccessDesc("更新数据量[" + num + "]");
		if (logger.isDebugEnabled()) {
			logger.debug("end merge.");
		}
	}

	private void countNeedUpdateNum(OrgMapping orgMapping) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		String countUpdateNum = "select count(*) from #tableName# i where i.orgid=#oldOrgId#";
		countUpdateNum = OrgChangeUtils.replaceScript(orgMapping,
				countUpdateNum);
		int count = commonHandlerDAO.getCount(countUpdateNum);
		log.appendSuccessDesc("需更新数据量[" + count + "]");
	}

	@Override
	public void init(OrgMapping orgMapping) {
	}

}
