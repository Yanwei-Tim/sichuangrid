package com.tianque.party.controller;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ServiceTeam;
import com.tianque.domain.TeamInfo;
import com.tianque.domain.VolunteerTeam;
import com.tianque.domain.constants.TeamType;
import com.tianque.party.service.PartyTeamService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("partyTeamController")
@Scope("prototype")
@Namespace("/partyBuilding/partyTeamManage")
public class PartyTeamController extends BaseAction {

	private TeamInfo team;

	@Autowired
	private PartyTeamService partyTeamService;
	private Long organizationId;

	private Long teamId;

	private String orgName;

	private String teamType;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Action(value = "list", results = { @Result(name = "success", location = "/partyBuilding/team/teamList.jsp") })
	public String list() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			orgName = organizationDubboService.getSimpleOrgById(organizationId)
					.getOrgName();
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					this.partyTeamService.findTeamInfosByOwnerOrgIdAndTeamType(
							organizationId, page, rows, "name", "desc",
							teamType), organizationDubboService,
					new String[] { "organization" }, organizationId));

		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/partyBuilding/team/maintanceTeamDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/team/teamDetail.jsp") })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			team = new TeamInfo();
			team.setOwnerOrg(ThreadVariable.getSession().getOrganization());
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			initTeam();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			initTeam();
			orgName = organizationDubboService.getSimpleOrgById(organizationId)
					.getOrgName();
			return "view";
		}
		return SUCCESS;
	}

	private void initTeam() {
		team = partyTeamService.getPartyTeamById(teamId);
		team.getOwnerOrg().setOrgName(
				ControllerHelper.getOrganizationRelativeName(team.getOwnerOrg()
						.getId(), organizationDubboService));
		organizationId = team.getOwnerOrg().getId();
		if (ServiceTeam.class.isAssignableFrom(team.getClass())) {
			teamType = TeamType.SERVICE_TEAM;
		} else if (VolunteerTeam.class.isAssignableFrom(team.getClass())) {
			teamType = TeamType.VOLUNTEER_TEAM;

		}
	}

	@PermissionFilter(ename = "addPartyTeam")
	@Action(value = "addTeam", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addPartyTeam() throws Exception {
		team = partyTeamService.addPartyTeam(team, teamType);
		return SUCCESS;

	}

	@PermissionFilter(ename = "editPartyTeam")
	@Action(value = "editTeam", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String editPartyTeam() throws Exception {
		team = partyTeamService.updateTeamInfo(team, teamType);
		return SUCCESS;

	}

	@PermissionFilter(ename = "deletePartyTeam")
	@Action(value = "deletePartyTeam", results = {
			@Result(name = "success", type = "json", params = { "root",
					"teamId", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePartyTeam() throws Exception {
		partyTeamService.deleteTeamInfoById(teamId);
		return SUCCESS;

	}

	private PageInfo<TeamInfo> emptyPage(int pageSize) {
		PageInfo<TeamInfo> pageInfo = new PageInfo<TeamInfo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<TeamInfo>());
		return pageInfo;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public TeamInfo getTeam() {
		return team;
	}

	public void setTeam(TeamInfo team) {
		this.team = team;
	}

	public String getTeamType() {
		return teamType;
	}

	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
