package com.tianque.peopleLog.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.Statistics;
import com.tianque.peopleLog.service.CommentLogService;
import com.tianque.peopleLog.service.PeopleLogService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 民情日志控制类
 */
@Namespace("/peopleLog/commentLogManage")
@Transactional
@Scope("request")
@Controller("commentLogController")
public class CommentLogController extends BaseAction {
	private CommentLog commentLog;
	private PeopleLog peopleLog;
	private Statistics statistics;
	private Organization organization;

	@Autowired
	private CommentLogService commentLogService;
	@Autowired
	private PeopleLogService peopleLogService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/**
	 * 查找记录用于评论列表的jqGrid显示
	 */
	@Action(value = "myCommentList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findGridPageByCommentId() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				commentLogService.findCommentLogForPageByUserId(ThreadVariable
						.getUser().getId(), page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				ThreadVariable.getSession().getOrganization().getId()));
		return SUCCESS;
	}

	/**
	 * id加密实现页面跳转
	 */
	@EncryptAnnotation
	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/peopleLog/myComment/peopleLogView.jsp"),
			@Result(name = "search", location = "/peopleLog/myComment/searchCommentLogDlg.jsp"),
			@Result(name = "view", location = "/peopleLog/myComment/peopleLogView.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperateByEncrypt() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode)) {
			peopleLog = commentLogService.findPeopleLogById(peopleLog.getId());
			peopleLog.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(peopleLog
							.getOrganization().getId(), organizationDubboService));
			peopleLog.setPeopleLogFiles(peopleLogService
					.findPeopleLogAttachFilesByPeopleLogId(peopleLog.getId()));
			return "view";
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		}

		return SUCCESS;
	}

	/**
	 * 实现页面跳转
	 */
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/peopleLog/myComment/peopleLogView.jsp"),
			@Result(name = "search", location = "/peopleLog/myComment/searchCommentLogDlg.jsp"),
			@Result(name = "view", location = "/peopleLog/myComment/peopleLogView.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode)) {
			peopleLog = commentLogService.findPeopleLogById(peopleLog.getId());
			peopleLog.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(peopleLog
							.getOrganization().getId(), organizationDubboService));
			peopleLog.setPeopleLogFiles(peopleLogService
					.findPeopleLogAttachFilesByPeopleLogId(peopleLog.getId()));
			return "view";
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		}

		return SUCCESS;
	}

	/**
	 * 手机查看
	 */
	@Action(value = "getPeopleLogById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"peopleLog", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getPeopleLogById() throws Exception {
		peopleLog = commentLogService.findPeopleLogById(peopleLog.getId());
		peopleLog.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(peopleLog
						.getOrganization().getId(), organizationDubboService));
		peopleLog.setPeopleLogFiles(peopleLogService
				.findPeopleLogAttachFilesByPeopleLogId(peopleLog.getId()));
		return SUCCESS;
	}

	/**
	 * 统计新增
	 */
	@Action(value = "getStatisticsByUserId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"statistics", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getStatisticsByUserId() throws Exception {
		statistics = commentLogService.findLogStatistics(ThreadVariable
				.getUser().getId());
		if (statistics != null) {
			return SUCCESS;
		} else {
			errorMessage = "统计新增数据出错！";
			return ERROR;
		}
	}

	/**
	 * 统计工作台
	 */
	@Action(value = "getStatisticsByOrgCode", results = {
			@Result(name = "success", type = "json", params = { "root",
					"statistics", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getStatisticsByOrgCode() throws Exception {
		statistics = commentLogService
				.findLogStatisticsByOrgCode(organizationDubboService
						.getSimpleOrgById(
								ThreadVariable.getSession().getOrganization()
										.getId()).getOrgInternalCode());
		if (statistics != null) {
			return SUCCESS;
		} else {
			errorMessage = "统计工作台数据出错！";
			return ERROR;
		}

	}

	public CommentLog getCommentLog() {
		return commentLog;
	}

	public void setCommentLog(CommentLog commentLog) {
		this.commentLog = commentLog;
	}

	public PeopleLog getPeopleLog() {
		return peopleLog;
	}

	public void setPeopleLog(PeopleLog peopleLog) {
		this.peopleLog = peopleLog;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
