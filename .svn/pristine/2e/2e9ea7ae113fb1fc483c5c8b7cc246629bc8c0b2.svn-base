package com.tianque.tenHouseholdsJoint.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tenHouseholdsJoint.domain.FamilyInfo;
import com.tianque.tenHouseholdsJoint.domain.FamilyTeam;
import com.tianque.tenHouseholdsJoint.service.FamilyInfoService;
import com.tianque.tenHouseholdsJoint.service.FamilyTeamService;

@Controller("familyInfoController")
@Namespace("/tenHouseholdsJoint/familyCondition")
@Scope("request")
public class FamilyInfoController extends BaseAction {
	@Autowired
	private FamilyInfoService familyInfoService;
	@Autowired
	private FamilyTeamService familyTeamService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private List<FamilyTeam> teamList;
	private Long organizationId;
	private FamilyInfo familyInfo;
	private Long id;
	private String idList;
	private Integer logoutStatus;
	private Boolean hasHelpLine;
	private Long teamId;
	private String operateSource;

	@Actions({ @Action(value = "queryFamilyInfoList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String queryFamilyInfoList() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage();
		} else {
			PageResult<FamilyInfo> pageResult = familyInfoService
					.queryFamilyInfoForPageResult(organizationId,
							defaultSortAndPage());
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					pageResult, organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	/***
	 * 注销或取消注销
	 */
	@Actions({ @Action(value = "updateFamilyLogout", results = { @Result(type = "json", name = "success", params = {
			"root", "true", "ignoreHierarchy", "false" }) }) })
	public String updateFamilyLogout() throws Exception {
		if (idList == null || idList.length() == 0) {
			errorMessage = "请至少选择一条数据进行操作";
			return ERROR;
		}
		if (logoutStatus == null) {
			errorMessage = "未明确定义操作类型";
			return ERROR;
		}
		familyInfoService.updateLogoutFamilyById(idList.split(","),
				logoutStatus);
		return SUCCESS;
	}

	/***
	 * 用户资料高级查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@Actions({ @Action(value = "searchFamilyInByCondition", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) }) })
	public String searchFamilyInByCondition() throws Exception {
		if (familyInfo == null || familyInfo.getOrganization() == null
				|| familyInfo.getOrganization().getId() == null) {
			errorMessage = "组织机构信息未获得";
			return ERROR;
		} else {
			PageResult<FamilyInfo> pageResult = familyInfoService
					.queryFamilyByConditionForPageResult(familyInfo, page, rows);
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					pageResult, organizationDubboService,
					new String[] { "organization" }, familyInfo
							.getOrganization().getId()));
		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "edit", location = "/template/tenHouseholdsJoint/familyCondition/mainFamilyInfoDlg.ftl"),
			@Result(name = "add", location = "/template/tenHouseholdsJoint/familyCondition/mainFamilyInfoDlg.ftl"),
			@Result(name = "view", location = "/template/tenHouseholdsJoint/familyCondition/mainFamilyInfoDlg.ftl"),
			@Result(name = "search", location = "/template/tenHouseholdsJoint/familyCondition/searchFamilyInfo.ftl"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (null == organizationId) {
				this.errorMessage = "传参出现错误！";
				return ERROR;
			}
			familyInfo.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							organizationId, organizationDubboService));
			if (teamId != null) {
				FamilyTeam team = familyTeamService.getFamilyTeamById(teamId);
				if (team != null) {
					teamList = new ArrayList<FamilyTeam>();
					teamList.add(team);
				}
			} else {
				teamList = familyTeamService
						.queryFamilyTeamByOrgIdForList(organizationId);
			}
			return getMode();
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			if (null == organizationId) {
				this.errorMessage = "传参出现错误！";
				return ERROR;
			}
			teamList = familyTeamService
					.queryFamilyTeamByOrgIdForList(organizationId);
			return mode;
		}
		if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			familyInfo = familyInfoService.getFamilyInfoById(id);
			familyInfo.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(familyInfo
							.getOrganization().getId(),
							organizationDubboService));
			teamList = familyTeamService
					.queryFamilyTeamByOrgIdForList(organizationId);
			return getMode();
		}
		return ERROR;
	}

	@Action(value = "addFamilyInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "familyInfo" }) })
	public String addFamilyInfo() throws Exception {
		if (organizationId == null) {
			this.errorMessage = "组织机构ID为空";
			return ERROR;
		}
		if (familyInfo == null) {
			this.errorMessage = "用户对象为空";
			return ERROR;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		familyInfo.setOrganization(organization);
		familyInfo = familyInfoService.addFamilyInfo(familyInfo);
		return SUCCESS;
	}

	@Action(value = "updateFamilyInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "familyInfo" }) })
	public String updateFamilyInfo() throws Exception {
		if (null == familyInfo || null == familyInfo.getId()
				|| null == familyInfo.getOrganization()
				|| null == familyInfo.getOrganization().getId()) {
			this.errorMessage = "用户信息参数传递错误";
			return ERROR;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(familyInfo.getOrganization().getId());
		familyInfo.setOrganization(organization);
		familyInfoService.updateFamilyInfo(familyInfo);
		return SUCCESS;
	}

	@Action(value = "deleteFamilyInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteFamilyInfo() throws Exception {
		if (idList == null || idList.length() == 0) {
			errorMessage = "请至少选中一条数据";
			return ERROR;
		}
		familyInfoService.deleteFamilyInfo(idList.split(","));
		return SUCCESS;
	}

	@Action(value = "checkHelpLine", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String checkHelpLine() throws Exception {
		if (null == familyInfo) {
			errorMessage = "用户信息对象为空";
			return ERROR;
		}
		if (!familyInfoService.checkHelpLine(familyInfo)) {
			return ERROR;
		}
		return SUCCESS;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public FamilyInfo getFamilyInfo() {
		return familyInfo;
	}

	public void setFamilyInfo(FamilyInfo familyInfo) {
		this.familyInfo = familyInfo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	public List<FamilyTeam> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<FamilyTeam> teamList) {
		this.teamList = teamList;
	}

	public Integer getLogoutStatus() {
		return logoutStatus;
	}

	public void setLogoutStatus(Integer logoutStatus) {
		this.logoutStatus = logoutStatus;
	}

	public Boolean getHasHelpLine() {
		return hasHelpLine;
	}

	public void setHasHelpLine(Boolean hasHelpLine) {
		this.hasHelpLine = hasHelpLine;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getOperateSource() {
		return operateSource;
	}

	public void setOperateSource(String operateSource) {
		this.operateSource = operateSource;
	}

}
