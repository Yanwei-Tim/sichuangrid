package com.tianque.plugin.judgmentAnalysis.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.analysis.api.ScheduleJobLogDubboService;
import com.tianque.analysis.api.ScheduleLogDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.ScheduleJobLog;
import com.tianque.plugin.judgmentAnalysis.domain.ScheduleLog;

@Scope("prototype")
@Namespace("/judgmentAnalysis/scheduleLogManage")
@Controller("scheduleLogController")
public class ScheduleLogController extends BaseAction {

	@Autowired
	private ScheduleLogDubboService scheduleLogService;
	@Autowired
	private ScheduleJobLogDubboService scheduleJobLogService;

	private Long id;
	private ScheduleLog scheduleLog;
	private ScheduleJobLog scheduleJobLog;

	@Action(value = "findScheduleLogForPage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findScheduleLogForPage() {
		try {
			PageInfo<ScheduleLog> pager = scheduleLogService
					.findScheduleLogForPage(page, rows, sidx, sord, scheduleLog);
			gridPage = new GridPage(pager);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "查询失败!";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "search", location = "/template/judgmentAnalysis/scheduleLog/scheduleLogSearchDlg.ftl"),
			@Result(name = "listJobLog", location = "/template/judgmentAnalysis/scheduleLog/scheduleJobLogList.ftl"),
			@Result(name = "viewJobLog", location = "/template/judgmentAnalysis/scheduleLog/scheduleJobLogMaintainDlg.ftl") })
	public String dispatch() throws Exception {

		if ("search".equals(mode)) {
			return "search";
		}
		if (id == null) {
			errorMessage = "请选择要查询的数据";
			return ERROR;
		}

		if ("listJobLog".equals(mode)) {
			return "listJobLog";
		} else if ("viewJobLog".equals(mode)) {
			scheduleJobLog = scheduleJobLogService.getScheduleJobLogbById(id);
			return "viewJobLog";
		}

		return mode;
	}

	@Action(value = "findScheduleJobLogForPage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findScheduleJobLogForPage() {
		try {
			PageInfo<ScheduleJobLog> pager = scheduleJobLogService
					.findScheduleJobLogForPage(page, rows, sidx, sord,
							scheduleJobLog);
			gridPage = new GridPage(pager);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "查询失败!";
			return ERROR;
		}
		return SUCCESS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ScheduleLog getScheduleLog() {
		return scheduleLog;
	}

	public void setScheduleLog(ScheduleLog scheduleLog) {
		this.scheduleLog = scheduleLog;
	}

	public ScheduleJobLog getScheduleJobLog() {
		return scheduleJobLog;
	}

	public void setScheduleJobLog(ScheduleJobLog scheduleJobLog) {
		this.scheduleJobLog = scheduleJobLog;
	}

}
