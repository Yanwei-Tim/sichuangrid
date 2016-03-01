package com.tianque.issue.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.issue.domain.PacificUnionFounding;
import com.tianque.issue.service.PacificUnionFoundingService;

@Controller("pacificUnionFoundingController")
@Scope("request")
@Namespace("/issues/pacificUnionFoundingManage")
public class PacificUnionFoundingController extends BaseAction {
	private List<PacificUnionFounding> pacificUnionFoundings;
	private Integer year;
	private Long parentOrgId;

	@Autowired
	private PacificUnionFoundingService pacificUnionFoundingService;

	@Action(value = "findPacificUnionFoundings", results = { @Result(name = "success", location = "/issue/pacificUnionFounding/pacificUnionFoundingHighVillageList.jsp") })
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

	public List<PacificUnionFounding> getPacificUnionFoundings() {
		return pacificUnionFoundings;
	}

	public void setPacificUnionFoundings(
			List<PacificUnionFounding> pacificUnionFoundings) {
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
