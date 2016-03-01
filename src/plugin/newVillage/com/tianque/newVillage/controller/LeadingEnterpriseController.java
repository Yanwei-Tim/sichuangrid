package com.tianque.newVillage.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.newVillage.domain.LeadingEnterprise;
import com.tianque.newVillage.service.LeadingEnterpriseService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("request")
@Namespace("/baseInfo/leadingEnterpriseManage")
@Controller("leadingEnterpriseController")
public class LeadingEnterpriseController extends BaseAction {

	private String ids;
	private LeadingEnterprise leadingEnterprise;
	private Organization organization;
	private Long searchType;

	@Autowired
	private LeadingEnterpriseService leadingEnterpriseService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/**
	 * 列表数据查询
	 */
	@Action(value = "findLeadingEnterpriseForPageInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findLeadingEnterpriseForPageInfo() throws Exception {
		if (organization == null || organization.getId() == null
				|| searchType == null) {
			errorMessage = "查询出错，未获得参数信息";
			return ERROR;
		}
		PageInfo<LeadingEnterprise> pageInfo = ControllerHelper
				.proccessRelativeOrgNameByPageInfo(leadingEnterpriseService
						.findLeadingEnterpriseForPageInfo(
								leadingEnterprise != null ? leadingEnterprise
										.getEnterpriseName() : null,
								searchType, organization, page, rows, sidx,
								sord), organizationDubboService);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/***
	 * 业务跳转
	 */
	@Actions(value = { @Action(value = "dispath", results = {
			@Result(name = "success", location = "/newCountryside/leadingEnterprise/maintainLeadingEnterprise.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String dispath() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (organization == null || organization.getId() == null) {
				errorMessage = "新增企业信息错误，未获得组织机构信息";
				return ERROR;
			}
		} else if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			if (leadingEnterprise == null || leadingEnterprise.getId() == null) {
				errorMessage = "操作失败，未获得参数信息";
				return ERROR;
			}
			leadingEnterprise = leadingEnterpriseService
					.getLeadingEnterpriseById(leadingEnterprise.getId());
		}
		return SUCCESS;
	}

	/***
	 * 新增或修改
	 * 
	 * @return
	 */
	@Action(value = "maintainLeadingEnterpaise", results = {
			@Result(name = "success", type = "json", params = { "root",
					"leadingEnterprise", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainLeadingEnterpaise() throws Exception {
		if (leadingEnterprise == null) {
			errorMessage = "操作失败，参数未获得";
			return ERROR;
		}
		if (leadingEnterprise.getId() == null) {
			leadingEnterpriseService.addLeadingEnterprise(leadingEnterprise);
		} else {
			leadingEnterpriseService.updateLeadingEnterprise(leadingEnterprise);
		}
		return SUCCESS;
	}

	/***
	 * 删除
	 * 
	 * @return
	 */
	@Action(value = "deleteLeadingEnterpriseByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String deleteLeadingEnterpriseByIds() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)) {
			errorMessage = "删除失败，未获得参数信息";
			return ERROR;
		}
		leadingEnterpriseService.deleteLeadingEnterpriseByIds(ids.split(","));
		return SUCCESS;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public LeadingEnterprise getLeadingEnterprise() {
		return leadingEnterprise;
	}

	public void setLeadingEnterprise(LeadingEnterprise leadingEnterprise) {
		this.leadingEnterprise = leadingEnterprise;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getSearchType() {
		return searchType;
	}

	public void setSearchType(Long searchType) {
		this.searchType = searchType;
	}

}
