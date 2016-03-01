package com.tianque.fourTeams.fourTeamsIssue.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAccessConfig;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueAccessConfigService;

@Controller("fourTeamsIssueAccessConfigController")
@Scope("request")
@Namespace("/fourTeams/issueAccessConfigManage")
public class FourTeamsIssueAccessConfigController extends BaseAction {

	@Autowired
	private FourTeamsIssueAccessConfigService issueAccessConfigService;

	private FourTeamsIssueAccessConfig issueAccessConfig;

	@PermissionFilter(ename = "addSet")
	@Action(value = "saveSet", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueAccessConfig", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addIssueAccessConfig() throws Exception {
		issueAccessConfig = issueAccessConfigService
				.addIssueAccessConfig(issueAccessConfig);
		return SUCCESS;
	}

	@Action(value = "dispatch", results = { @Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueAccessConfig/issueAccessConfigList.jsp") })
	public String dispatch() throws Exception {
		issueAccessConfig = issueAccessConfigService.getIssueAccessConfig();
		if (issueAccessConfig == null) {
			issueAccessConfig = new FourTeamsIssueAccessConfig();
		}
		return SUCCESS;
	}

	public FourTeamsIssueAccessConfig getIssueAccessConfig() {
		return issueAccessConfig;
	}

	public void setIssueAccessConfig(
			FourTeamsIssueAccessConfig issueAccessConfig) {
		this.issueAccessConfig = issueAccessConfig;
	}

}
