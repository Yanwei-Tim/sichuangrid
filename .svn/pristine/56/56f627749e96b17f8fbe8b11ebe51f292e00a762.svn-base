package com.tianque.fourTeams.fourTeamsIssue.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsAdministrativeOrgTimeLimitStandard;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsAdministrativeOrgTimeLimitStandardService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * @说明：行政部门时限标准控制类
 * @author 龙振东
 * 
 */
@Transactional
@Controller("fourTeamsAdministrativeOrgTimeLimitStandardController")
@Scope("prototype")
@Namespace("/fourTeams/administrativeOrgTimeLimitStandardManage")
public class FourTeamsAdministrativeOrgTimeLimitStandardController extends
		BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(FourTeamsAdministrativeOrgTimeLimitStandardController.class);

	@Autowired
	private FourTeamsAdministrativeOrgTimeLimitStandardService administrativeOrgTimeLimitStandardService;
	@Autowired
	private PropertyDictService propertyDictService;

	private Organization userOrganization;
	private FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard;
	private String selectedIds;
	private Boolean deleteState;

	@Action(value = "findAdministrativeOrgTimeLimitStandardList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findAdministrativeOrgTimeLimitStandardList() throws Exception {
		gridPage = new GridPage(
				administrativeOrgTimeLimitStandardService
						.findAdministrativeOrgTimeLimitStandardByUserOrganization(
								userOrganization, page, rows, sidx, sord));
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueAccessConfig/maintainAdministrativeOrgTimeLimitStandardDlg.jsp"),
			@Result(name = "edit", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueAccessConfig/maintainAdministrativeOrgTimeLimitStandardDlg1.jsp") })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			prepareAdd();
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)
				|| DialogMode.VIEW1_MODE.equalsIgnoreCase(mode)) {
			administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardService
					.getAdministrativeOrgTimeLimitStandardById(administrativeOrgTimeLimitStandard
							.getId());
			return DialogMode.VIEW1_MODE.equalsIgnoreCase(mode) ? DialogMode.EDIT_MODE
					: SUCCESS;
		}
		return SUCCESS;
	}

	private void prepareAdd() {
		administrativeOrgTimeLimitStandard = new FourTeamsAdministrativeOrgTimeLimitStandard();
		Organization organization = ThreadVariable.getOrganization();
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId());
		administrativeOrgTimeLimitStandard.setCreateOrg(organization);
		administrativeOrgTimeLimitStandard
				.setUseLevel(propertyDictService
						.findPropertyDictByDomainNameAndInternalIds(
								PropertyTypes.ORGANIZATION_LEVEL,
								new int[] { propertyDict.getInternalId() - 10 })
						.get(0));
	}

	@PermissionFilters({
			@PermissionFilter(ename = "addAdministrativeOrgTimeLimitStandard", actionName = "addAdministrativeOrgTimeLimitStandard"),
			@PermissionFilter(ename = "updateAdministrativeOrgTimeLimitStandard", actionName = "updateAdministrativeOrgTimeLimitStandard") })
	@Actions({
			@Action(value = "addAdministrativeOrgTimeLimitStandard", results = {
					@Result(name = "success", type = "json", params = { "root",
							"administrativeOrgTimeLimitStandard",
							"ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "updateAdministrativeOrgTimeLimitStandard", results = {
					@Result(name = "success", type = "json", params = { "root",
							"administrativeOrgTimeLimitStandard",
							"ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String maintainAdministrativeOrgTimeLimitStandard() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardService
					.addAdministrativeOrgTimeLimitStandard(administrativeOrgTimeLimitStandard);
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)
				|| DialogMode.VIEW1_MODE.equalsIgnoreCase(mode)) {
			administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardService
					.updateAdministrativeOrgTimeLimitStandard(administrativeOrgTimeLimitStandard);
			return SUCCESS;
		}
		return ERROR;
	}

	@PermissionFilter(ename = "deleteAdministrativeOrgTimeLimitStandard")
	@Action(value = "deleteAdministrativeOrgTimeLimitStandardByIds", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteState", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteAdministrativeOrgTimeLimitStandardByIds()
			throws Exception {
		if (null == selectedIds) {
			deleteState = false;
			errorMessage = "请选择一条记录再删除!";
			return ERROR;
		}
		deleteState = administrativeOrgTimeLimitStandardService
				.deleteAdministrativeOrgTimeLimitStandardByIds(analyze(selectedIds));
		return SUCCESS;
	}

	@Action(value = "validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId", results = {
			@Result(type = "json", name = "success", params = { "root",
					"result", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId()
			throws Exception {
		if (administrativeOrgTimeLimitStandard == null) {
			errorMessage = "操作失败，请联系管理员！";
			return ERROR;
		}
		result = administrativeOrgTimeLimitStandardService
				.validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId(administrativeOrgTimeLimitStandard);
		return SUCCESS;
	}

	public Organization getUserOrganization() {
		return userOrganization;
	}

	public void setUserOrganization(Organization userOrganization) {
		this.userOrganization = userOrganization;
	}

	public void setAdministrativeOrgTimeLimitStandard(
			FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard) {
		this.administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandard;
	}

	public FourTeamsAdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandard() {
		return administrativeOrgTimeLimitStandard;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setDeleteState(Boolean deleteState) {
		this.deleteState = deleteState;
	}

	public Boolean getDeleteState() {
		return deleteState;
	}
}
