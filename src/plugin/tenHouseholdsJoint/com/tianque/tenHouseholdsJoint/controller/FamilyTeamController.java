package com.tianque.tenHouseholdsJoint.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tenHouseholdsJoint.domain.FamilyTeam;
import com.tianque.tenHouseholdsJoint.service.FamilyTeamService;

@Controller("familyTeamController")
@Namespace("/tenHouseholdsJoint/groupingCondition")
@Scope("request")
public class FamilyTeamController extends BaseAction {
	@Autowired
	private FamilyTeamService familyTeamService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private Long organizationId;
	private Long selectedId;
	private FamilyTeam familyTeam;
	private String ids;

	@Actions({ @Action(value = "queryFamilyTeamList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String queryFamilyTeamList() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage();
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(organizationId);
			PageResult<FamilyTeam> pageResult = familyTeamService
					.queryFamilyTeamForPageResult(organization,
							defaultSortAndPage());
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					pageResult, organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	@Action(value = "dispatchOperate", results = {
			@Result(name = "add", location = "/template/tenHouseholdsJoint/groupingCondition/mainFamilyTeamDlg.ftl"),
			@Result(name = "edit", location = "/template/tenHouseholdsJoint/groupingCondition/mainFamilyTeamDlg.ftl"),
			@Result(name = "view", location = "/template/tenHouseholdsJoint/groupingCondition/mainFamilyTeamDlg.ftl"),
			@Result(name = "search", location = "/template/tenHouseholdsJoint/groupingCondition/mainFamilyTeamDlg.ftl"),
			@Result(name = "manage", location = "/template/tenHouseholdsJoint/groupingCondition/manageFamilyInfoDlg.ftl"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)
				|| DialogMode.SEARCH_MODE.equals(mode)) {
			if (null == organizationId) {
				this.errorMessage = "分组信息传参出现错误！";
				return ERROR;
			}
			familyTeam = new FamilyTeam();
			familyTeam.setOrganization(organizationDubboService
					.getSimpleOrgById(organizationId));
			familyTeam.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							organizationId, organizationDubboService));
		} else if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			if (null == selectedId) {
				this.errorMessage = "分组信息传参出现错误！";
				return ERROR;
			}
			familyTeam = familyTeamService.getFamilyTeamById(selectedId);
			familyTeam.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(familyTeam
							.getOrganization().getId(),
							organizationDubboService));
		} else if (DialogMode.MANAGE.equals(mode)) {
			if (selectedId == null || organizationId == null) {
				this.errorMessage = "分组信息传参出现错误！";
				return ERROR;
			}
			return mode;
		}
		return mode;
	}

	@PermissionFilter(ename = "addGroupingCondition")
	@Action(value = "addFamilyTeam", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addFamilyTeam() throws Exception {
		if (null == familyTeam) {
			this.errorMessage = "分组对象为空！";
			return ERROR;
		}
		if (familyTeamService.addFamilyTeam(familyTeam) != null) {
			return SUCCESS;
		} else {
			this.errorMessage = "新增失败";
			return ERROR;
		}
	}

	@Action(value = "checkFamilyTeam", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String checkFamilyTeam() throws Exception {
		if (familyTeam == null) {
			this.errorMessage = "分组信息对象为空！";
			return ERROR;
		}
		if (!familyTeamService.checkFamilyTeam(familyTeam)) {
			return ERROR;
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateGroupingCondition")
	@Action(value = "updateFamilyTeam", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateFamilyTeam() throws Exception {
		if (null == familyTeam) {
			this.errorMessage = "分组对象为空！";
			return ERROR;
		}
		if (familyTeamService.updateFamilyTeam(familyTeam) > 0) {
			return SUCCESS;
		} else {
			this.errorMessage = "修改失败";
			return ERROR;
		}
	}

	@PermissionFilter(ename = "deleteGroupingCondition")
	@Action(value = "delFamilyTeamOperation", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String delFamilyTeamOperation() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)) {
			this.errorMessage = "分组信息的参数错误！";
			return ERROR;
		}
		familyTeamService.delteFamilyTeamByIds(ids);
		return SUCCESS;
	}

	@PermissionFilter(ename = "searchGroupingCondition")
	@Action(value = "searchFamilyTeamInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage" }) })
	public String searchFamilyTeamInfo() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage();
			return SUCCESS;
		} else {
			PageResult<FamilyTeam> pageResult = familyTeamService
					.querySearchFamilyTeamForPageResult(familyTeam,
							defaultSortAndPage());
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					pageResult, organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public FamilyTeam getFamilyTeam() {
		return familyTeam;
	}

	public void setFamilyTeam(FamilyTeam familyTeam) {
		this.familyTeam = familyTeam;
	}

	public Long getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Long selectedId) {
		this.selectedId = selectedId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
