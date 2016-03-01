package com.tianque.plugin.orgchange.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.util.CacheKeyGenerator;
import com.tianque.core.util.ThreadVariable;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 
 * @ClassName: PrimaryMembersHandler
 * @Description: TODO成员库迁移合并handler
 * @author wanggz
 * @date 2014-11-13 上午10:28:26
 */
@Component("primaryMembersHandler")
public class PrimaryMembersHandler extends AbstractOrgChangeHandler {

	private final static Logger logger = LoggerFactory
			.getLogger(PrimaryMembersHandler.class);
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CacheService cacheService;

	@Override
	public void merge(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin merge.orgMapping:{}", orgMapping);
		}
		excute(orgMapping);
		if (logger.isDebugEnabled()) {
			logger.debug("end merge.");
		}
	}

	@Override
	public void transfer(OrgMapping orgMapping) {
		if (logger.isDebugEnabled()) {
			logger.debug("begin transfer.orgMapping:{}", orgMapping);
		}
		excute(orgMapping);
		if (logger.isDebugEnabled()) {
			logger.debug("end transfer.");
		}
	}

	private void excute(OrgMapping orgMapping) {
		OrgChangeLog log = orgMapping.getOrgChangeLog();
		Long actualRoot = ThreadVariable.getUser().getOrganization().getId();
		cacheService.remove(CacheKeyGenerator.generateCacheKeyFromString(
				ControllerHelper.class,
				actualRoot + "_" + orgMapping.getNewOrgId()));
		String newOrgName = ControllerHelper.getOrganizationRelativeName(
				orgMapping.getNewOrgId(), organizationDubboService);
		String updatePrimaryMembersSql = " update #tableName# p set p.orgname='"
				+ newOrgName
				+ "',#ORGIDCOLUMN#=#NEWORGID#,#ORGCODECOLUMN#=#NEWORGCODE# where p.orgid=#oldOrgId# ";
		updatePrimaryMembersSql = OrgChangeUtils.replaceScript(orgMapping,
				updatePrimaryMembersSql);
		int conutUpdateNum = commonHandlerDAO
				.updateData(updatePrimaryMembersSql);
		log.appendSuccessDesc("更新数据量[" + conutUpdateNum + "]");
	}

}
