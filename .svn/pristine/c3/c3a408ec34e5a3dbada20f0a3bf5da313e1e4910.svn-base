package com.tianque.fourTeams.fourTeamsIssue.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsSupervisionAdministration;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsSupervisionAdministrationService;

/**
 * 
 * @author 龙振东
 * @说明：日常监督管理制度控制类
 */
@Controller("fourTeamsSupervisionAdministrationController")
@Scope("request")
@Namespace("/fourTeams/supervisionAdministrationManage")
public class FourTeamsSupervisionAdministrationController extends BaseAction {

	@Autowired
	private FourTeamsSupervisionAdministrationService supervisionAdministrationService;

	private FourTeamsSupervisionAdministration supervisionAdministration;

	@Action(value = "maintainSupervisionAdministration", results = {

			@Result(name = "success", type = "json", params = { "root",
					"supervisionAdministration", "ignoreHierarchy", "false" }),

			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainSupervisionAdministration() throws Exception {
		supervisionAdministration = supervisionAdministrationService
				.maintainSupervisionAdministration(supervisionAdministration);
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/supervisionManagement/maintainSupervisionAdministration.jsp"),
			@Result(name = "view", type = "json", params = { "root",
					"supervisionAdministration", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (null == supervisionAdministration) {
			errorMessage = "参数错误";
			return ERROR;
		}
		supervisionAdministration = supervisionAdministrationService
				.getSupervisionAdministrationByOrgId(supervisionAdministration
						.getOrgId());
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return DialogMode.VIEW_MODE;
		}
		return SUCCESS;
	}

	public void setSupervisionAdministration(
			FourTeamsSupervisionAdministration supervisionAdministration) {
		this.supervisionAdministration = supervisionAdministration;
	}

	public FourTeamsSupervisionAdministration getSupervisionAdministration() {
		return supervisionAdministration;
	}

}
