package com.tianque.plugin.judgmentAnalysis.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.analysis.api.ScheduleJobInfoDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.ScheduleJobInfo;
import com.tianque.plugin.judgmentAnalysis.util.DialogMode;

@Scope("prototype")
@Namespace("/judgmentAnalysis/scheduleJobInfoManage")
@Controller("scheduleJobInfoController")
public class ScheduleJobInfoController extends BaseAction {

	@Autowired
	private ScheduleJobInfoDubboService scheduleJobInfoService;

	private Long id;
	private ScheduleJobInfo scheduleJobInfo;

	@Action(value = "findScheduleJobInfoForPage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findScheduleJobInfoForPage() {
		try {
			PageInfo<ScheduleJobInfo> pager = scheduleJobInfoService
					.findScheduleJobInfoForPage(page, rows, sidx, sord,
							scheduleJobInfo);
			gridPage = new GridPage(pager);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "查询失败!";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "maintain", location = "/template/judgmentAnalysis/scheduleJobInfo/scheduleJobInfoAddDlg.ftl"),
			@Result(name = "search", location = "/template/judgmentAnalysis/scheduleJobInfo/scheduleJobInfoSearchDlg.ftl") })
	public String dispatch() throws Exception {
		if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		}
		if (DialogMode.ADD_MODE.equals(mode)) {
			return "maintain";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			return getScheduleJobInfoById();
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return getScheduleJobInfoById();
		}
		return ERROR;
	}

	private String getScheduleJobInfoById() {
		if (id == null) {
			errorMessage = "请选择要查询的数据";
			return ERROR;
		}
		try {
			scheduleJobInfo = scheduleJobInfoService.getScheduleJobInfoById(id);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "查询数据失败";
			return ERROR;
		}
		return "maintain";
	}

	@Action(value = "addScheduleJobInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"scheduleJobInfo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addScheduleJobInfo() {
		if (scheduleJobInfo == null) {
			errorMessage = "请填入数据再修改";
			return ERROR;
		}
		try {
			scheduleJobInfo = scheduleJobInfoService
					.addScheduleJobInfo(scheduleJobInfo);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "添加数据失败";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "editScheduleJobInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"scheduleJobInfo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String editScheduleJobInfo() {
		if (scheduleJobInfo == null) {
			errorMessage = "请填入数据再修改";
			return ERROR;
		}
		try {
			scheduleJobInfo = scheduleJobInfoService
					.updateScheduleJobInfo(scheduleJobInfo);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "添加数据失败";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "deleteScheduleJobInfoById", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteScheduleJobInfoById() {

		if (id == null || id.equals("")) {
			errorMessage = "请选择一条或者多条记录进行操作！";
			return ERROR;
		}
		try {
			scheduleJobInfoService.deleteScheduleJobInfoById(id);
		} catch (Exception e) {
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "删除失败!";
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

	public ScheduleJobInfo getScheduleJobInfo() {
		return scheduleJobInfo;
	}

	public void setScheduleJobInfo(ScheduleJobInfo scheduleJobInfo) {
		this.scheduleJobInfo = scheduleJobInfo;
	}

}
