package com.tianque.fourTeams.fourTeamsManage.controller;

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
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeamMembers;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeams;
import com.tianque.fourTeams.fourTeamsManage.service.FourTeamsService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/fourTeamsManage")
@Scope("request")
@Controller("fourTeamsController")
public class FourTeamsController extends BaseAction {
	private Long id;
	private Long parentId;
	private String ids;
	private Long organizationId;
	private String names;
	private String fourteamsType;
	private FourTeams fourTeams;
	private FourTeamMembers fourTeamMembers;
	private Long orgId;
	private Long currentOrgId;
	private Organization organization;
	private Boolean hasTeamName = false;
	private String screeningLevel;
	@Autowired
	private FourTeamsService fourTeamsService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/**
	 * 队伍一览
	 */
	@Action(value = "teamManagementList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String teamManagementList() throws Exception {
		PageInfo<FourTeams> pageInfo = null;
		if (organizationId == null) {
			pageInfo = new PageInfo<FourTeams>();
		} else {
			if (fourTeams == null) {
				fourTeams = new FourTeams();
			}
			fourTeams.setOrganization(new Organization());
			fourTeams.getOrganization().setId(organizationId);
			fourTeams.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							organizationId, organizationDubboService));

			pageInfo = fourTeamsService.findFourTeams(fourTeams, page, rows,
					sidx, sord);
		}
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "screeningFourteamsManagementList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String screeningFourteamsManagementList() throws Exception {
		if (!validateData()) {
			return ERROR;
		}
		PageInfo<FourTeams> pageInfo = ControllerHelper
				.proccessRelativeOrgNameByPageInfo(fourTeamsService
						.findScreeningFourteamsForPageInfo(fourteamsType,
								organizationId, screeningLevel, page, rows,
								ids, fourteamsType), organizationDubboService);

		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private boolean validateData() {
		if (fourteamsType == null || fourteamsType.trim().length() == 0
				|| organizationId == null || screeningLevel == null
				|| screeningLevel.trim().length() == 0) {
			errorMessage = "筛选失败，请重试";
			return false;
		}
		return true;
	}

	/**
	 * 队伍高级检索
	 * 
	 * @return
	 */
	@Action(value = "searchFourTeams", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String searchFourTeams() throws Exception {
		PageInfo<FourTeams> pageInfo = null;
		if (organizationId == null) {
			pageInfo = new PageInfo<FourTeams>();
		} else {
			if (fourTeams == null) {
				fourTeams = new FourTeams();
			}
			fourTeams.setOrganization(new Organization());
			fourTeams.getOrganization().setId(organizationId);
			fourTeams.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							organizationId, organizationDubboService));

			pageInfo = fourTeamsService.findSearchFourTeams(fourTeams, page,
					rows, sidx, sord);
		}
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 维护队伍检索
	 * 
	 * @return
	 */
	@Action(value = "searchTeamName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String searchTeamName() throws Exception {
		PageInfo<FourTeams> pageInfo = null;
		if (organizationId == null) {
			pageInfo = new PageInfo<FourTeams>();
		} else {
			if (fourTeams == null) {
				fourTeams = new FourTeams();
			}
			if (id != null) {
				fourTeams.setParentTeamId(id);
			}
			if (names != null) {
				fourTeams.setNames(names);
			}
			fourTeams.setOrganization(new Organization());
			fourTeams.getOrganization().setId(organizationId);
			fourTeams.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							organizationId, organizationDubboService));

			pageInfo = fourTeamsService.findSearchTeamName(fourTeams, page,
					rows, sidx, sord);
		}
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 维护成员检索
	 * 
	 * @return
	 */
	@Action(value = "searchTeamMemberName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String searchTeamMemberName() throws Exception {
		PageInfo<FourTeamMembers> pageInfo = fourTeamsService
				.searchTeamMemberName(id, orgId, names, page, rows, sidx, sord);
		if (pageInfo == null) {
			pageInfo = new PageInfo<FourTeamMembers>();
		}
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/***
	 * 通过名称和网格ID查询是否存在相同名称队伍
	 */
	@Action(value = "repeatTeamName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasTeamName" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String repeatTeamName() throws Exception {
		if (fourTeams == null || fourTeams.getOrganization() == null
				|| fourTeams.getOrganization().getId() == null) {
			errorMessage = "验证名称重复出错，请重试";
			return ERROR;
		}
		hasTeamName = fourTeamsService.repeatTeamName(fourTeams.getId(),
				fourTeams.getNames(), fourTeams.getOrganization().getId());
		return SUCCESS;
	}

	/**
	 * 四支队伍高级检索
	 * 
	 * @return
	 */
	@Action(value = "searchFourTeamMembers", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String searchFourTeamMembers() throws Exception {
		PageInfo<FourTeams> pageInfo = null;
		if (fourTeams == null) {
			fourTeams = new FourTeams();
		}
		if (id != null) {
			fourTeams.setParentTeamId(id);
		}
		pageInfo = ControllerHelper.proccessRelativeOrgNameByPageInfo(
				fourTeamsService.findSearchFourTeamMembers(fourTeams,
						screeningLevel, page, rows, sidx, sord),
				organizationDubboService);
		if (pageInfo == null) {
			pageInfo = new PageInfo<FourTeams>();
		}
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 队伍一览
	 * 
	 * @return
	 */
	@Action(value = "subTeamManagementList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String subTeamManagementList() throws Exception {
		PageInfo<FourTeams> pageInfo = null;
		if (organizationId == null) {
			pageInfo = new PageInfo<FourTeams>();
		} else {
			if (fourTeams == null) {
				fourTeams = new FourTeams();
			}
			if (id != null) {
				fourTeams.setParentTeamId(id);
			}
			fourTeams.setOrganization(new Organization());
			fourTeams.getOrganization().setId(organizationId);
			fourTeams.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							organizationId, organizationDubboService));

			pageInfo = fourTeamsService.findSubFourTeams(fourTeams, page, rows,
					sidx, sord);
		}
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Actions(value = { @Action(value = "maintainAddList", results = {
			@Result(name = "success", location = "/fourTeamsManage/maintainAddDlg.jsp"),
			@Result(name = "view", location = "/fourTeamsManage/viewMaintenanceFourTeamDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String maintainAddList() throws Exception {
		if (fourTeams == null) {
			errorMessage = "操作失敗，請重試";
			return ERROR;
		}
		if (DialogMode.ADD_MODE.equals(mode)) {
			fourTeams = fourTeamsService.getFourTeamMembersByType(fourTeams
					.getTeamType());
			if (fourTeams == null || currentOrgId == null) {
				errorMessage = "新增失败，请重试";
				return ERROR;
			}
			parentId = fourTeams.getId();
			fourteamsType = fourTeams.getTeamType();
			fourTeams = new FourTeams();
			organization = organizationDubboService.getSimpleOrgById(currentOrgId);
		} else if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			if (fourTeams.getId() != null) {
				fourTeams = fourTeamsService.findFourTeamAdd(fourTeams.getId());
			} else {
				errorMessage = "修改失败，请重试";
				return ERROR;
			}
			return DialogMode.VIEW_MODE.equals(mode) ? DialogMode.VIEW_MODE
					: SUCCESS;
		}
		return SUCCESS;
	}

	/**
	 * 队伍新增
	 * 
	 * @return
	 */
	@Action(value = "addTeam", results = {
			@Result(name = "success", type = "json", params = { "root",
					"fourTeams", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String addTeam() throws Exception {
		if (fourTeams == null) {
			errorMessage = "新增队伍失败，请重试";
			return ERROR;
		}
		fourTeams = fourTeamsService.addTeam(fourTeams);
		return SUCCESS;
	}

	@Action(value = "updateTeam", results = {
			@Result(name = "success", type = "json", params = { "root",
					"fourTeams", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String updateTeam() throws Exception {
		if (fourTeams == null) {
			errorMessage = "修改队伍失败，请重试!";
			return ERROR;
		}
		fourTeams = fourTeamsService.editTeam(fourTeams);
		return SUCCESS;
	}

	/**
	 * 维护队伍批量删除
	 * 
	 * @return
	 */
	@Action(value = "deleteFourTeams", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String deleteFourTeams() throws Exception {
		if (parentId == null) {
			errorMessage = "操作失败，请重试";
			return ERROR;
		}
		if (ids == null) {
			errorMessage = "请至少选择一条需要删除的数据";
			return ERROR;
		}
		fourTeamsService.deleteFourTeams(ids.split(","), parentId);
		return SUCCESS;
	}

	// /**
	// * 维护队伍删除
	// *
	// * @return
	// */
	// @Action(value = "deleteFourTeam", results = {
	// @Result(name = "success", type = "json", params = { "root", "true",
	// "ignoreHierarchy", "false" }),
	// @Result(name = "error", type = "json", params = { "root",
	// "errorMessage", "ignoreHierarchy", "false" }) })
	// public String deleteFourTeam() throws Exception {
	// if (id == null || parentId == null) {
	// errorMessage = "删除失败，请重试";
	// return ERROR;
	// return SUCCESS;
	// }

	@Actions(value = { @Action(value = "maintainEditList", results = {
			@Result(location = "/fourTeamsManage/maintainEditDlg.jsp", name = "success"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String maintainEditList() throws Exception {
		if (id != null) {
			fourTeams = fourTeamsService.findFourTeamEdit(id);
		}
		return SUCCESS;
	}

	/**
	 * 查看维护队伍信息
	 * 
	 * @return
	 */
	@Actions(value = { @Action(value = "viewFourTeam", results = {
			@Result(location = "/fourTeamsManage/viewMaintenanceFourTeamDlg.jsp", name = "success"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String viewFourTeam() throws Exception {
		if (id != null) {
			fourTeams = fourTeamsService.findFourTeamView(id);
		}
		return SUCCESS;
	}

	/**
	 * 查看维护成员信息
	 * 
	 * @return
	 */
	@Actions(value = { @Action(value = "viewManageFourTeamMember", results = {
			@Result(location = "/fourTeamsManage/viewManageFourTeamMemberDlg.jsp", name = "success"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String viewManageFourTeamMember() throws Exception {
		if (id != null) {
			fourTeams = fourTeamsService.findFourTeamView(id);
		}
		return SUCCESS;
	}

	/**
	 * 维护队伍修改
	 * 
	 * @return
	 */
	@Action(value = "editTeam", results = {
			@Result(name = "success", type = "json", params = { "root",
					"fourTeams", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String editTeam() throws Exception {
		if (fourTeams == null) {
			errorMessage = "操作失败，请重试！";
			return ERROR;
		}
		fourTeams.setOrganization(new Organization());
		fourTeams.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(fourTeams
						.getOrganization().getId(), organizationDubboService));
		fourTeams = fourTeamsService.editTeam(fourTeams);
		return SUCCESS;
	}

	/**
	 * 四支队伍一览
	 * 
	 * @return
	 */
	@Action(value = "serviceTeamManagementList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String serviceTeamManagementList() throws Exception {
		PageInfo<FourTeams> pageInfo = ControllerHelper
				.proccessRelativeOrgNameByPageInfo(fourTeamsService
						.findserviceFourTeams(fourTeams, organizationId,
								screeningLevel, page, rows, sidx, sord),
						organizationDubboService);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 维护成员一览
	 * 
	 * @return
	 */
	@Action(value = "teamMemberManagementList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String teamMemberManagementList() throws Exception {
		PageInfo<FourTeamMembers> pageInfo = fourTeamsService
				.findMemberFourTeams(id, page, rows, sidx, sord);
		if (pageInfo == null) {
			pageInfo = new PageInfo<FourTeamMembers>();
		}
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Actions(value = { @Action(value = "maintainAddMemberList", results = {
			@Result(location = "/fourTeamsManage/maintainAddMemberDlg.jsp", name = "success"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String maintainAddMemberList() throws Exception {
		if (id != null) {
			fourTeams = fourTeamsService.findFourTeamEdit(id);
		}
		return SUCCESS;
	}

	/**
	 * 维护成员新增
	 * 
	 * @return
	 */
	@Action(value = "addTeamMember", results = {
			@Result(name = "success", type = "json", params = { "root",
					"fourTeamMembers", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addTeamMember() throws Exception {
		if (fourTeamMembers == null) {
			errorMessage = "操作失败，请重试!";
			return ERROR;
		}
		if (id != null) {
			fourTeamMembers.setTeamId(id);
			fourTeamMembers = fourTeamsService.addTeamMember(fourTeamMembers);
		} else {
			errorMessage = "成员新增失败，请重试";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 维护成员批量删除
	 * 
	 * @return
	 */
	@Action(value = "deleteFourTeamMembers", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String deleteFourTeamMembers() throws Exception {
		if (ids == null) {
			errorMessage = "请选择一条数据进行删除";
			return ERROR;
		}
		if (id == null) {
			errorMessage = "删除失败，请重试";
			return ERROR;
		}
		if (!fourTeamsService.deleteFourTeamMembers(ids.split(","), id)) {
			errorMessage = "四支队伍成员不能删除！";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 维护成员删除
	 * 
	 * @return
	 */
	@Action(value = "deleteFourTeamMember", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String deleteFourTeamMember() throws Exception {
		if (id == null) {
			errorMessage = "请选择一条数据进行删除";
			return ERROR;
		}
		if (parentId == null) {
			errorMessage = "删除失败，请重试";
			return ERROR;
		}
		fourTeamsService.deleteFourTeamMembers(id.toString().split(","),
				parentId);
		return SUCCESS;
	}

	@Actions(value = { @Action(value = "maintainMemberEditList", results = {
			@Result(location = "/fourTeamsManage/maintainEditMemberDlg.jsp", name = "success"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String maintainMemberEditList() throws Exception {
		if (id != null) {
			fourTeamMembers = fourTeamsService.findFourTeamMemberEdit(id);
		}
		return SUCCESS;
	}

	/**
	 * 维护队伍成员修改
	 */
	@Action(value = "editTeamMember", results = {
			@Result(name = "success", type = "json", params = { "root",
					"fourTeamMembers", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String editTeamMember() throws Exception {
		if (fourTeamMembers == null) {
			errorMessage = "修改出错，请重试";
			return ERROR;
		}
		fourTeamMembers = fourTeamsService.editTeamMember(fourTeamMembers);
		fourTeams = fourTeamsService.findFourTeamEdit(id);
		return SUCCESS;
	}

	/**
	 * 事件办理，办结选择四支队伍
	 * 
	 * @return
	 */
	@Action(value = "searchFourTeamsForIssues", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String searchFourTeamsForIssues() throws Exception {
		if (fourTeams == null || fourTeams.getOrganization() == null
				|| fourTeams.getOrganization().getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		PageInfo<FourTeams> pageInfo = fourTeamsService
				.findTeamsByConditionForIssue(fourTeams, page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public void setFourTeams(FourTeams fourTeams) {
		this.fourTeams = fourTeams;
	}

	public FourTeams getFourTeams() {
		return fourTeams;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setFourTeamMembers(FourTeamMembers fourTeamMembers) {
		this.fourTeamMembers = fourTeamMembers;
	}

	public FourTeamMembers getFourTeamMembers() {
		return fourTeamMembers;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getFourteamsType() {
		return fourteamsType;
	}

	public void setFourteamsType(String fourteamsType) {
		this.fourteamsType = fourteamsType;
	}

	public Long getCurrentOrgId() {
		return currentOrgId;
	}

	public void setCurrentOrgId(Long currentOrgId) {
		this.currentOrgId = currentOrgId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Boolean getHasTeamName() {
		return hasTeamName;
	}

	public void setHasTeamName(Boolean hasTeamName) {
		this.hasTeamName = hasTeamName;
	}

	public String getScreeningLevel() {
		return screeningLevel;
	}

	public void setScreeningLevel(String screeningLevel) {
		this.screeningLevel = screeningLevel;
	}

}
