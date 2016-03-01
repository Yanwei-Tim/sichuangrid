package com.tianque.issue.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.issue.domain.IssueAccessConfig;
import com.tianque.issue.service.IssueAccessConfigService;

@Controller("issueAccessConfigController")
@Scope("request")
@Namespace("/issueAccessConfigManage")
public class IssueAccessConfigController extends BaseAction {

	@Autowired
	private IssueAccessConfigService issueAccessConfigService;

	private IssueAccessConfig issueAccessConfig;

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

	@Action(value = "dispatch", results = { @Result(name = "success", location = "/issue/issueAccessConfig/issueAccessConfigList.jsp") })
	public String dispatch() throws Exception {
		issueAccessConfig = issueAccessConfigService.getIssueAccessConfig();
		if (issueAccessConfig == null) {
			issueAccessConfig = new IssueAccessConfig();
		}
		return SUCCESS;
	}

	public IssueAccessConfig getIssueAccessConfig() {
		return issueAccessConfig;
	}

	public void setIssueAccessConfig(IssueAccessConfig issueAccessConfig) {
		this.issueAccessConfig = issueAccessConfig;
	}

}
