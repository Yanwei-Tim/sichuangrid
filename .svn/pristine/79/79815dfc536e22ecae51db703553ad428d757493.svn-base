package com.tianque.plugin.orgchange.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.oproject.framework.orm.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.OrgChangeInfo;
import com.tianque.plugin.orgchange.domain.ProgressInfo;
import com.tianque.plugin.orgchange.service.OrgChangeHandlerService;
import com.tianque.plugin.orgchange.service.OrgChangeInfoService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月24日
 */
@Namespace("/orgchange/orgChangeInfoManage")
@Scope("prototype")
@Controller("orgChangeInfoController")
public class OrgChangeInfoController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(OrgChangeInfoController.class);
	@Autowired
	private OrgChangeInfoService orgChangeInfoService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private OrgChangeHandlerService orgChangeHandlerService;
	@Autowired
	private PropertyDictService propertyDictService;

	private Organization organization;
	private PageResult<OrgChangeInfo> pageInfo;
	@Autowired
	private CacheService cacheService;
	private ProgressInfo progressInfo;
	/** 区分是迁移还是合并 */
	private String operate;
	private String path;
	private String orgIds;
	private Long changeId;
	/** 目标org */
	private Organization targetOrganization;
	/** 判断是否是同一级别 */
	private boolean sameLevel = false;
	private String mode;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/template/transfersAndMergeForOrganization/orgTransfersAndMergeList.ftl"),
			@Result(name = "doJob", location = "/template/transfersAndMergeForOrganization/orgDoJobList.ftl"),
			@Result(name = "compare", location = "/template/transfersAndMergeForOrganization/orgCompareList.ftl") })
	public String dispatch() {
		if (DialogMode.ORGANIZATION_DOJOB.equals(mode)
				|| DialogMode.ORGANIZATION_COMPARE.equals(mode)) {
			return mode;
		}
		organization = organizationDubboService.getSimpleOrgById(id);
		return SUCCESS;
	}

	/* add by 组织机构迁移合并迭代 begin** */
	@Action(value = "findCityOrgId", results = { @Result(name = "success", type = "json", params = {
			"root", "organization", "ignoreHierarchy", "false" }) })
	public String findCityOrgId() throws Exception {
		organization = organizationDubboService
				.getCityOrgByAreaOrgId(organization.getId());
		return SUCCESS;

	}

	@Action(value = "findOrgToCityRelativeNameByOrgId", results = { @Result(name = "success", type = "json", params = {
			"root", "path", "ignoreHierarchy", "false" }) })
	public String findOrgToCityRelativeNameByOrgId() throws Exception {
		if (id == null)
			return ERROR;
		Organization org = organizationDubboService.getFullOrgById(id);
		Long Level = (long) org.getOrgLevel().getInternalId();
		path = org.getOrgName();
		while (org.getParentOrg() != null
				&& Level.longValue() < Long
						.valueOf(OrganizationLevel.CITY + "")) {
			org = organizationDubboService.getSimpleOrgById(org.getParentOrg()
					.getId());
			Level = org.getOrgLevel().getId();
			path = org.getOrgName() + "->" + path;
		}
		return SUCCESS;
	}

	@Action(value = "ajaxOrgPage", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String ajaxOrgPage() throws Exception {
		List<Organization> orgList = new ArrayList<Organization>();
		String[] orgIdsArray = orgIds.split(",");
		if (StringUtil.isStringAvaliable(orgIds)) {
			for (String id : orgIdsArray) {
				Organization org = organizationDubboService
						.getSimpleOrgById(Long.valueOf(id));
				orgList.add(org);
			}
		}
		gridPage = new GridPage(orgList);
		return SUCCESS;
	}

	@Action(value = "findNoDealInfoList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findNoDealInfoList() throws Exception {
		gridPage = new GridPage(orgChangeInfoService.findNoDealInfoList(
				OrgChangeUtils.IS_DEAL, page, rows, sidx, sord));
		return SUCCESS;
	}

	@Action(value = "deleteOrgChangeInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String deleteOrgChangeInfo() throws Exception {
		if (changeId == null) {
			errorMessage = "传入删除参数有误";
			return ERROR;
		}
		orgChangeInfoService.deleteOrgChangeInfoByIds(changeId);
		return SUCCESS;
	}

	@Action(value = "judgeIsSameLevel", results = {
			@Result(name = "success", type = "json", params = { "root",
					"sameLevel" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String judgeIsSameLevel() throws Exception {
		if (organization == null || targetOrganization == null
				|| organization.getId() == null
				|| targetOrganization.getId() == null) {
			this.errorMessage = "操作失败，请刷新页面重试！";
			return ERROR;
		}
		int orgLevel = propertyDictService.getPropertyDictById(
				organizationDubboService.getSimpleOrgById(organization.getId())
						.getOrgLevel().getId()).getInternalId();

		int sourceLevel = propertyDictService.getPropertyDictById(
				organizationDubboService
						.getSimpleOrgById(targetOrganization.getId())
						.getOrgLevel().getId()).getInternalId();
		conditonSuccess(sourceLevel, orgLevel);
		return SUCCESS;
	}

	private void conditonSuccess(int sourceLevel, int targetLevel) {
		if (DialogMode.ORGANIZATIONS_MEGER.equals(operate)) {
			if (sourceLevel == targetLevel) {
				sameLevel = true;
			}
		} else if (DialogMode.ORGANIZATIONS_TRANSFERS.equals(operate)) {
			if ((sourceLevel < targetLevel)
					&& (sourceLevel == (targetLevel - 10))) {
				sameLevel = true;
			}
		}

	}

	@Action(value = "equalsTargetOrgCurrentParentOrg", results = {
			@Result(name = "success", type = "json", params = { "root",
					"result" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String equalsTargetOrgCurrentParentOrg() throws Exception {
		if (organization == null || targetOrganization == null
				|| organization.getId() == null
				|| targetOrganization.getId() == null) {
			this.errorMessage = "操作失败，请刷新页面重试！";
			return ERROR;
		}
		Organization targetOrg = organizationDubboService
				.getSimpleOrgById(targetOrganization.getId());
		if (targetOrg.getParentOrg().getId().equals(organization.getId())) {
			result = true;
		} else {
			result = false;
		}
		return SUCCESS;
	}

	public String showOrgChangeInfo() {
		return null;
	}

	@Action(value = "addOrgChangeInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addOrgChangeInfo() throws Exception {
		if (organization == null || organization.getId() == null
				|| !StringUtil.isStringAvaliable(orgIds)) {
			this.errorMessage = "操作失败，请刷新页面重试！";
			return ERROR;
		}
		OrgChangeInfo orgChangeInfo = new OrgChangeInfo();
		orgChangeInfo.setSourceOrgId(organization.getId());
		orgChangeInfoService.addOrganizationChangeInfo(organization, orgIds,
				operate);
		return SUCCESS;
	}

	@Action(value = "executeJob", results = {
			@Result(name = "success", type = "json", params = { "root",
					"progressInfo" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String executeJob() throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("begin executeJob.ids:{}", changeId);
		if (changeId == null) {
			errorMessage = "传入参数不存在";
			return ERROR;
		}
		orgChangeHandlerService.executeChange(changeId);
		if (logger.isDebugEnabled())
			logger.debug("end executeJob.");
		return SUCCESS;
	}

	@Action(value = "getProgressInfoByChangeId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"progressInfo" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getProgressInfoByChangeId() {
		getProgressFormat();
		return SUCCESS;
	}

	private void getProgressFormat() {
		Object object = cacheService.get(OrgChangeUtils.ORGCHANGE_PROGRESS
				+ changeId);
		if (object instanceof ProgressInfo) {
			progressInfo = (ProgressInfo) object;
		} else {
			progressInfo = new ProgressInfo();
			progressInfo.setIsException(OrgChangeUtils.ORGCHANGE_EXCEPTION);
		}
		if (progressInfo.getProgress() == OrgChangeUtils.ORGCHANGE_DONE
				|| progressInfo.getIsException() == OrgChangeUtils.ORGCHANGE_EXCEPTION) {
			cacheService.remove(OrgChangeUtils.ORGCHANGE_PROGRESS + changeId);
		}
		List<String> moduleList = progressInfo.getCompleteModules();
		if (moduleList == null) {
			moduleList = new ArrayList<String>();
		}
		String message = "";
		if (moduleList != null && moduleList.size() != 0) {
			for (String moduleName : moduleList) {
				message = moduleName + "执行完成。\n" + message;
			}
		}
		if (progressInfo.getCurrentModule() != null) {
			if (progressInfo.getIsException() == OrgChangeUtils.ORGCHANGE_EXCEPTION) {
				message = progressInfo.getCurrentModule()
						+ "执行失败，所有数据回滚，请查看日志了解详情！\n" + message;
			} else if (progressInfo.getProgress() != OrgChangeUtils.ORGCHANGE_DONE) {
				message = progressInfo.getCurrentModule() + "正在执行...\n"
						+ message;
			} else {
				message = "执行成功。\n" + message;
			}
		}
		progressInfo.setCompleteModuleString(message);
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public Organization getTargetOrganization() {
		return targetOrganization;
	}

	public void setTargetOrganization(Organization targetOrganization) {
		this.targetOrganization = targetOrganization;
	}

	public boolean isSameLevel() {
		return sameLevel;
	}

	public void setSameLevel(boolean sameLevel) {
		this.sameLevel = sameLevel;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public PageResult<OrgChangeInfo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageResult<OrgChangeInfo> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Long getChangeId() {
		return changeId;
	}

	public void setChangeId(Long changeId) {
		this.changeId = changeId;
	}

	public ProgressInfo getProgressInfo() {
		return progressInfo;
	}

	public void setProgressInfo(ProgressInfo progressInfo) {
		this.progressInfo = progressInfo;
	}

}
