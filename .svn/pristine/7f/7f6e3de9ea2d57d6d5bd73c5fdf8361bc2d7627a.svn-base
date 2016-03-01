package com.tianque.upgradeContent.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.upgradeContent.domain.UpgradeContent;
import com.tianque.upgradeContent.domain.UserCheckUpgradeContent;
import com.tianque.upgradeContent.service.UpgradeContentService;
import com.tianque.upgradeContent.service.UserCheckUpgradeContentService;

@Namespace("/sysadmin/userCheckUpgradeContentManage")
@Scope("request")
@Controller("userCheckUpgradeContentController")
public class UserCheckUpgradeContentController extends BaseAction {
	public final static Logger logger = LoggerFactory
			.getLogger(UserCheckUpgradeContentController.class);

	@Autowired
	private UserCheckUpgradeContentService userCheckUpgradeContentService;
	@Autowired
	private UpgradeContentService upgradeContentService;

	private UserCheckUpgradeContent userCheckUpgradeContent;
	private UpgradeContent upgradeContent;
	private Long userId;

	@Actions({ @Action(value = "queryUserCheckUpgradeContentForList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"userCheckUpgradeContent", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String queryUserCheckUpgradeContentForList() throws Exception {
		userCheckUpgradeContent = userCheckUpgradeContentService
				.getUserCheckUpgradeContentByUserIdAndUpgradeContentId(userId);
		return SUCCESS;

	}

	@Action(value = "findTheLatestUpgradeContent", results = { @Result(name = "view", location = "/sysadmin/upgradeContent/userCheckUpgradeContentView.jsp") })
	public String findTheLatestUpgradeContent() throws Exception {
		userCheckUpgradeContent = userCheckUpgradeContentService
				.getUserCheckUpgradeContentByUserIdAndUpgradeContentId(userId);
		return "view";
	}

	@Actions({ @Action(value = "saveUserCheckUpgradeContent", results = {
			@Result(type = "json", name = "success", params = { "root",
					"userCheckUpgradeContent", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String saveUserCheckUpgradeContent() throws Exception {
		if (userCheckUpgradeContent == null) {
			errorMessage = "参数错误!";
			return ERROR;
		}
		userCheckUpgradeContentService
				.updateUserCheckUpgradeContent(userCheckUpgradeContent);

		return "success";
	}

	public UserCheckUpgradeContent getUserCheckUpgradeContent() {
		return userCheckUpgradeContent;
	}

	public void setUserCheckUpgradeContent(
			UserCheckUpgradeContent userCheckUpgradeContent) {
		this.userCheckUpgradeContent = userCheckUpgradeContent;
	}

	public UpgradeContent getUpgradeContent() {
		return upgradeContent;
	}

	public void setUpgradeContent(UpgradeContent upgradeContent) {
		this.upgradeContent = upgradeContent;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
