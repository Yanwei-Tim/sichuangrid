package com.tianque.peopleLog.controller;

import java.util.Date;

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
import com.tianque.peopleLog.domain.CommentLog;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.service.PeopleLogService;
import com.tianque.peopleLog.service.SubLogService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 实有单位控制类
 */
@Namespace("/peopleLog/peopleLogManage")
@Transactional
@Scope("request")
@Controller("subLogController")
public class SubLogController extends BaseAction {

	@Autowired
	private SubLogService subLogService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PeopleLogService peopleLogService;

	private Long organizationId;
	private boolean isPeer;
	private Long logId;
	private PeopleLog log;
	private CommentLog comment;

	/**
	 * id加密页面功能跳转
	 */
	@EncryptAnnotation
	@Action(value = "dispatchActionByEncrypt", results = {
			@Result(name = "addComment", location = "/peopleLog/subLog/commentLog.jsp"),
			@Result(name = "search", location = "/peopleLog/subLog/searchSubLogDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperateByEncrypt() throws Exception {
		if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		} else if (DialogMode.ADD_MODE.equals(mode)) {
			if (logId != null) {
				log = subLogService.findLogByLogId(logId);
				log.setOrganization(organizationDubboService.getSimpleOrgById(log
						.getOrganization().getId()));
				log.setPeopleLogFiles(peopleLogService
						.findPeopleLogAttachFilesByPeopleLogId(log.getId()));
			}
			return "addComment";
		}
		return SUCCESS;
	}

	/**
	 * 页面功能跳转
	 */

	@Action(value = "dispatchAction", results = {
			@Result(name = "addComment", location = "/peopleLog/subLog/commentLog.jsp"),
			@Result(name = "search", location = "/peopleLog/subLog/searchSubLogDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		} else if (DialogMode.ADD_MODE.equals(mode)) {
			if (logId != null) {
				log = subLogService.findLogByLogId(logId);
				log.setOrganization(organizationDubboService.getSimpleOrgById(log
						.getOrganization().getId()));
				log.setPeopleLogFiles(peopleLogService
						.findPeopleLogAttachFilesByPeopleLogId(log.getId()));
			}
			return "addComment";
		}
		return SUCCESS;
	}

	/**
	 * 保存评论
	 */
	@Action(value = "saveComment", results = { @Result(type = "json", name = "success", params = {
			"root", "log", "ignoreHierarchy", "false", "excludeNullProperties",
			"true" }) })
	public String addCommentList() throws Exception {
		if (comment.getComments() != null) {
			Date commentDate = new Date();
			Long userId = ThreadVariable.getSession().getUserId();
			String commentName = ThreadVariable.getSession().getUserRealName();

			comment.setCommentTime(commentDate);
			comment.setUserId(userId);
			comment.setCommentUser(commentName);
			comment.setLogId(logId);
			comment = subLogService.addComments(comment);
			log = subLogService.findLogByLogId(comment.getLogId());
			return SUCCESS;
		}
		return ERROR;
	}

	/**
	 * 查找记录用于jqGrid显示
	 */
	@Action(value = "subLogList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findGridPageByOrgId() throws Exception {

		if (organizationId == null) {
			gridPage = new GridPage();
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					subLogService.findSubLogListByOrgInternalCode(
							organizationId, page, rows, sidx, sord, isPeer),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	/**
	 * 查找记录用于subLogLost4Bench的jqGrid显示
	 */
	@Action(value = "subLogList4Bench", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgId4Bench() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				subLogService.findSubLogListByOrgInternalCode4Bench(
						ThreadVariable.getUser().getOrgInternalCode(), page,
						rows, sidx, sord, isPeer), organizationDubboService,
				new String[] { "organization" }, organizationId));
		return SUCCESS;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public boolean getIsPeer() {
		return isPeer;
	}

	public SubLogService getSubLogService() {
		return subLogService;
	}

	public void setSubLogService(SubLogService subLogService) {
		this.subLogService = subLogService;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public PeopleLog getLog() {
		return log;
	}

	public void setIsPeer(boolean isPeer) {
		this.isPeer = isPeer;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public void setLog(PeopleLog log) {
		this.log = log;
	}

	public CommentLog getComment() {
		return comment;
	}

	public void setComment(CommentLog comment) {
		this.comment = comment;
	}

	public PeopleLogService getPeopleLogService() {
		return peopleLogService;
	}

	public void setPeopleLogService(PeopleLogService peopleLogService) {
		this.peopleLogService = peopleLogService;
	}
}
