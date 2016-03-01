package com.tianque.plugin.judgmentAnalysis.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.analysis.api.ScheduleJobExceptionDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.ScheduleJobException;

@Scope("prototype")
@Namespace("/judgmentAnalysis/scheduleJobException")
@Controller("scheduleJobExceptionController")
public class ScheduleJobExceptionController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(ScheduleJobExceptionController.class);
	@Autowired
	private ScheduleJobExceptionDubboService jobExceptionService;
	private ScheduleJobException scheduleJobException;

	@Action(value = "findScheduleJobExceptionForPage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "excludeNullProperties", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findScheduleJobExceptionForPage() {
		try {
			PageInfo<ScheduleJobException> pageInfo = jobExceptionService
					.findScheduleJobExceptionForPage(page, rows, sidx, sord,
							scheduleJobException);
			gridPage = new GridPage(pageInfo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * @return 跳转页面
	 */
	@Action(value = "viewScheduleJobException", results = {
			@Result(name = "success", location = "/template/judgmentAnalysis/scheduleJobException/maintainScheduleJobException.ftl"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String viewScheduleJobException() {
		try {
			if (scheduleJobException == null
					|| scheduleJobException.getId() == null) {
				errorMessage = "请填写数据！";
				return ERROR;
			}
			scheduleJobException = this.jobExceptionService
					.getScheduleJobExceptionById(scheduleJobException.getId());
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			errorMessage = (e instanceof ServiceException) ? e.getMessage()
					: "操作失败，请联系管理员";
			return ERROR;
		}
	}

	public ScheduleJobException getScheduleJobException() {
		return scheduleJobException;
	}

	public void setScheduleJobException(
			ScheduleJobException scheduleJobException) {
		this.scheduleJobException = scheduleJobException;
	}

}
