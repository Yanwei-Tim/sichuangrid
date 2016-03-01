package com.tianque.fourTeams.fourTeamsIssue.controller;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueApplyDelay;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueApplyDelayService;
import com.tianque.service.WorkCalendarService;

/**
 * @ClassName: IssueApplyDelayController
 * @Description: 延期申请控制器
 * @author wangxiaohu wsmalltiger@163.com
 * @date Nov 25, 2013 3:29:52 PM
 */
@Scope("prototype")
@Namespace("/fourTeamsIssueApplyDelay")
public class FourTeamsIssueApplyDelayController extends BaseAction {

	private Integer workDay = 0;

	@Autowired
	private WorkCalendarService workCalendarService;
	@Autowired
	private FourTeamsIssueApplyDelayService issueApplyDelayService;

	private FourTeamsIssueApplyDelay issueApplyDelay;// 申请延期实体
	private Long issueStepId;
	private Long orgId;
	private int auditDelayCount;

	@Action(value = "getWorkDayByDate", results = {
			@Result(name = "success", type = "json", params = { "root",
					"workDay" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getWorkDayByDate() throws Exception {
		if (issueApplyDelay != null && issueApplyDelay.getDelayDate() != null) {
			workDay = workCalendarService.getWorkDaysFromStartTimeToEndTime(
					new Date(), issueApplyDelay.getDelayDate());
		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/fourTeamsManage/fourTeamsIssueSkipruleManage/issueManage/auditDelayDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (issueStepId == null) {
			errorMessage = "操作失败，请联系管理员！";
			return ERROR;
		}
		issueApplyDelay = issueApplyDelayService
				.getIssueApplyDelayByIssueStepIdAndDelayState(issueStepId);
		return SUCCESS;
	}

	@Actions({
			@Action(value = "applyDelay", results = {
					@Result(name = "success", type = "json", params = { "root",
							"issueApplyDelay", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "applyDelayForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String applyDelay() throws Exception {
		if (issueApplyDelay != null && issueApplyDelay.getIssueStepId() != null) {
			issueApplyDelay = issueApplyDelayService
					.applyDelay(issueApplyDelay);
		} else {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "auditDelay", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueApplyDelay", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String auditDelay() throws Exception {
		if (issueApplyDelay != null && issueApplyDelay.getId() != null) {
			issueApplyDelay = issueApplyDelayService
					.auditDelay(issueApplyDelay);
		} else {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "getJurisdictionsAuditDelayCount", results = {
			@Result(type = "json", name = "success", params = { "root",
					"auditDelayCount", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getJurisdictionsAuditDelayCount() throws Exception {
		setAuditDelayCount(issueApplyDelayService
				.getJurisdictionsAuditDelayCount(orgId));
		return SUCCESS;
	}

	@Action(value = "findIssueDelayList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findIssueDelayList() throws Exception {
		if (issueStepId == null) {
			errorMessage = "操作失败，请联系管理员！";
			return ERROR;
		}
		gridPage = new GridPage(issueApplyDelayService.findIssueDelayList(
				issueStepId, page, rows, sidx, sord));
		return SUCCESS;
	}

	public Integer getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}

	public FourTeamsIssueApplyDelay getIssueApplyDelay() {
		return issueApplyDelay;
	}

	public void setIssueApplyDelay(FourTeamsIssueApplyDelay issueApplyDelay) {
		this.issueApplyDelay = issueApplyDelay;
	}

	public Long getIssueStepId() {
		return issueStepId;
	}

	public void setIssueStepId(Long issueStepId) {
		this.issueStepId = issueStepId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setAuditDelayCount(int auditDelayCount) {
		this.auditDelayCount = auditDelayCount;
	}

	public int getAuditDelayCount() {
		return auditDelayCount;
	}

}