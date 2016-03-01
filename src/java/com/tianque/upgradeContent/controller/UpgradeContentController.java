package com.tianque.upgradeContent.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.oproject.framework.orm.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.upgradeContent.domain.UpgradeContent;
import com.tianque.upgradeContent.domain.VO.UpgradeContentVO;
import com.tianque.upgradeContent.service.UpgradeContentService;

@Namespace("/sysadmin/upgradeContentManage")
@Scope("request")
@Controller("upgradeContentController")
public class UpgradeContentController extends BaseAction {
	public final static Logger logger = LoggerFactory
			.getLogger(UpgradeContentController.class);

	@Autowired
	private UpgradeContentService upgradeContentService;

	private UpgradeContentVO upgradeContentVO;
	private UpgradeContent upgradeContent;
	private String ids;
	private Long userId;

	@Actions({ @Action(value = "queryUpgradeContentList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String queryUpgradeContentList() throws Exception {
		PageResult<UpgradeContent> pageResult = upgradeContentService
				.queryUpgradeContenForPageResult(upgradeContentVO,
						defaultSortAndPage());
		gridPage = new GridPage(pageResult);
		return SUCCESS;

	}

	@Action(value = "dispatch", results = {
			@Result(name = "edit", location = "/sysadmin/upgradeContent/upgradeContentDlg.jsp"),
			@Result(name = "add", location = "/sysadmin/upgradeContent/upgradeContentDlg.jsp"),
			@Result(name = "view", location = "/sysadmin/upgradeContent/upgradeContentView.jsp"),
			@Result(name = "printWord", location = "/sysadmin/upgradeContent/printWordUpgradeContentDlg.jsp") })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return mode;
		}
		if (id == null) {
			errorMessage = "参数错误!";
			return ERROR;
		}
		upgradeContent = upgradeContentService.getUpgradeContentById(id);

		return mode;
	}

	@Action(value = "maintainUpgradeContent", results = {
			@Result(type = "json", name = "success", params = { "root",
					"upgradeContent", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainupgradeContent() throws Exception {
		if (upgradeContent == null) {
			errorMessage = "参数错误!";
			return ERROR;
		}
		if (DialogMode.EDIT_MODE.equals(mode)) {
			upgradeContentService.updateUpgradeContent(upgradeContent);
		} else {
			upgradeContent = upgradeContentService
					.addUpgradeContent(upgradeContent);
		}
		return SUCCESS;
	}

	@Action(value = "deleteUpgradeContent", results = {
			@Result(type = "json", name = "success", params = { "root",
					"upgradeContent", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteUpgradeContent() throws Exception {
		if (ids == null) {
			errorMessage = "参数错误!";
			return ERROR;
		}
		upgradeContentService.deleteUpgradeContent(analyzeToList(ids));
		return SUCCESS;
	}

	@Action(value = "updateIsUpgradUpgradeContent", results = {
			@Result(type = "json", name = "success", params = { "root",
					"upgradeContent", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateIsUpgradUpgradeContent() throws Exception {
		if (upgradeContent == null) {
			errorMessage = "参数错误!";
			return ERROR;
		}
		upgradeContentService.updateIsUpgradUpgradeContent(upgradeContent,
				userId);
		return SUCCESS;
	}

	public UpgradeContentVO getUpgradeContentVO() {
		return upgradeContentVO;
	}

	public void setUpgradeContentVO(UpgradeContentVO upgradeContentVO) {
		this.upgradeContentVO = upgradeContentVO;
	}

	public UpgradeContent getUpgradeContent() {
		return upgradeContent;
	}

	public void setUpgradeContent(UpgradeContent upgradeContent) {
		this.upgradeContent = upgradeContent;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
