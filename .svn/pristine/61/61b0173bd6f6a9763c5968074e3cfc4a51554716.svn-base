package com.tianque.issue.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.issue.domain.SupervisionAdministration;
import com.tianque.issue.service.SupervisionAdministrationService;

/**
 * 
 * @author 龙振东
 * @说明：日常监督管理制度控制类
 */
@Controller("supervisionAdministrationController")
@Scope("request")
@Namespace("/supervisionAdministrationManage")
public class SupervisionAdministrationController extends BaseAction {

	@Autowired
	private SupervisionAdministrationService supervisionAdministrationService;

	private SupervisionAdministration supervisionAdministration;

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
			@Result(name = "success", location = "/issue/supervisionManagement/maintainSupervisionAdministration.jsp"),
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
			SupervisionAdministration supervisionAdministration) {
		this.supervisionAdministration = supervisionAdministration;
	}

	public SupervisionAdministration getSupervisionAdministration() {
		return supervisionAdministration;
	}

}
