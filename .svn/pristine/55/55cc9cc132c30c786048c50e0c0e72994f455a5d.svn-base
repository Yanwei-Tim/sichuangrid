package com.tianque.baseInfo.laborEmploymentUnit.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.laborEmploymentUnit.domain.LaborEmploymentUnit;
import com.tianque.baseInfo.laborEmploymentUnit.domain.SearchLaborEmploymentUnitVo;
import com.tianque.baseInfo.laborEmploymentUnit.service.SearchLaborEmploymentUnitService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Scope("prototype")
@Controller("searchLaborEmploymentUnitController")
@Namespace("/baseinfo/searchLaborEmploymentUnit")
public class SearchLaborEmploymentUnitController extends BaseAction {
	@Autowired
	private SearchLaborEmploymentUnitService searchLaborEmploymentUnitService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;

	private Long organizationId;
	private SearchLaborEmploymentUnitVo searchLaborEmploymentUnitVo;
	private boolean pageOnly;

	/**
	 * 根据查询条件分页查询实有单位
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "searchLaborEmploymentUnit")
	@Action(value = "findLaborEmploymentUnitsByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findLaborEmploymentUnitsByQueryCondition() {
		if (searchLaborEmploymentUnitVo == null && organizationId == null) {
			this.errorMessage = "参数错误";
		}
		Organization organization = organizationDubboService.getSimpleOrgById(organizationId);
		searchLaborEmploymentUnitVo.setOrgInternalCode(organization.getOrgInternalCode());
		PageInfo<LaborEmploymentUnit> pageInfo = ControllerHelper.processAllOrgRelativeName(
				searchLaborEmploymentUnitService.findLaborEmploymentUnitByQueryCondition(
						searchLaborEmploymentUnitVo, page, rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearch() {
		return findLaborEmploymentUnitsByQueryCondition();
	}

	// set/get方法
	public SearchLaborEmploymentUnitService getSearchLaborEmploymentUnitService() {
		return searchLaborEmploymentUnitService;
	}

	public void setSearchLaborEmploymentUnitService(
			SearchLaborEmploymentUnitService searchLaborEmploymentUnitService) {
		this.searchLaborEmploymentUnitService = searchLaborEmploymentUnitService;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public SystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(SystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public SearchLaborEmploymentUnitVo getSearchLaborEmploymentUnitVo() {
		return searchLaborEmploymentUnitVo;
	}

	public void setSearchLaborEmploymentUnitVo(
			SearchLaborEmploymentUnitVo searchLaborEmploymentUnitVo) {
		this.searchLaborEmploymentUnitVo = searchLaborEmploymentUnitVo;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

}
