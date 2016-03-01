package com.tianque.versionCheck.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.versionCheck.domain.UserReadedVersion;
import com.tianque.versionCheck.service.VersionCheckService;

/**
 * 检查用户是否己读当前版本更新内容
 */
@Namespace("/versionCheckManage")
@Transactional
@Scope("request")
@Controller("versionCheckController")
public class VersionCheckController extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(VersionCheckController.class);

	@Autowired
	private VersionCheckService versionCheckService;
	private String currentVersion = GridProperties.CURRENT_VERSION;// 当前系统版本号
	private boolean hasRead;

	@Action(value = "getVersionIsReaded", results = {
			@Result(name = "isReaded", type = "json", params = { "root", "hasRead" }),
			@Result(name = "noRead", type = "json", params = { "root", "hasRead" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getVersionIsReaded() {
		if (isRead()) {
			hasRead = true;
			return "isReaded";
		} else {
			hasRead = false;
			return "noRead";
		}
	}

	/**
	 * 是否已读
	 * 
	 * @return
	 */
	public boolean isRead() {
		return versionCheckService.getReadedVersion(new UserReadedVersion(ThreadVariable.getUser()
				.getId(), currentVersion));
	}

	@Action(value = "addVersionReaded", results = {
			@Result(name = "success", location = "/versionCheck/version.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	/**
	 * 填加已读情况
	 */
	public void addVersionReaded() {
		versionCheckService.addVersionReaded(new UserReadedVersion(
				ThreadVariable.getUser().getId(), currentVersion));
	}

	public boolean isHasRead() {
		return hasRead;
	}

	public void setHasRead(boolean hasRead) {
		this.hasRead = hasRead;
	}

}
