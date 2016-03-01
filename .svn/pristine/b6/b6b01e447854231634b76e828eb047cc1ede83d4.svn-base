package com.tianque.fourTeams.fourTeamsIssue.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsPacificUnionFounding;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsPacificUnionFoundingService;

@Controller("fourTeamsPacificUnionFoundingController")
@Scope("request")
@Namespace("/fourTeams/issues/pacificUnionFoundingManage")
public class FourTeamsPacificUnionFoundingController extends BaseAction {
	private List<FourTeamsPacificUnionFounding> pacificUnionFoundings;
	private Integer year;
	private Long parentOrgId;

	@Autowired
	private FourTeamsPacificUnionFoundingService pacificUnionFoundingService;

	@Action(value = "findPacificUnionFoundings", results = { @Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/pacificUnionFounding/pacificUnionFoundingHighVillageList.jsp") })
	public String findPacificUnionFoundings() throws Exception {
		pacificUnionFoundings = pacificUnionFoundingService
				.findPacificUnionFoundingsByYearAndParentOrgId(year,
						parentOrgId);
		return SUCCESS;
	}

	@Action(value = "addPacificUnionFoundings", results = { @Result(name = "success", type = "json", params = {
			"root", "opreationResult", "ignoreHierarchy", "false" }) })
	public String addPacificUnionFoundings() throws Exception {
		if (pacificUnionFoundings == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		opreationResult = pacificUnionFoundingService.addPacificUnionFoundings(
				year, parentOrgId, pacificUnionFoundings);
		return SUCCESS;
	}

	public List<FourTeamsPacificUnionFounding> getPacificUnionFoundings() {
		return pacificUnionFoundings;
	}

	public void setPacificUnionFoundings(
			List<FourTeamsPacificUnionFounding> pacificUnionFoundings) {
		this.pacificUnionFoundings = pacificUnionFoundings;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

}
