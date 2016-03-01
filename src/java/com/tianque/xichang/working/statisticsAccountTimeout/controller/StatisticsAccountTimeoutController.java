package com.tianque.xichang.working.statisticsAccountTimeout.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import com.tianque.core.util.CalendarUtil;
import com.tianque.service.CommonService;
import com.tianque.xichang.working.statisticsAccountTimeout.service.StatisticsAccountTimeoutService;

@Namespace("/account/statisticsAccountTimeoutManage")
@Scope("request")
@Controller("statisticsAccountTimeoutController")
@Transactional
public class StatisticsAccountTimeoutController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(StatisticsAccountTimeoutController.class);

	@Autowired
	private StatisticsAccountTimeoutService statisticsAccountTimeoutService;

	@Autowired
	private CommonService commonService;

	private int minYear;
	private int minMonth;
	private int maxYear;
	private int maxMonth;
	private Integer nowYear;
	private Integer nowMonth;
	private Integer year;
	private Integer month;
	private List monthType;
	private int internalId;
	private Long parentOrgId;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/xichang/working/statisticsAccountTimeoutManage/performanceList.jsp"),
			@Result(type = "json", name = "error", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String dispatch() throws Exception {
		minYear = commonService.getSysBeginUseYear();
		minMonth = commonService.getSysBeginUseMonth();

		Calendar endCalendar = Calendar.getInstance();
		maxYear = endCalendar.get(Calendar.YEAR);
		maxMonth = endCalendar.get(Calendar.MONTH) + 1;

		String nowString = CalendarUtil.format(CalendarUtil.now("yyyy-MM-dd"));
		nowYear = new Integer(nowString.split("-")[0]);
		nowMonth = new Integer(nowString.split("-")[1]);
		return SUCCESS;
	}

	@Action(value = "getMonthTypeList", results = {
			@Result(type = "json", name = "success", params = { "root", "monthType",
					"ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String getMonthTypeList() throws Exception {

		monthType = new ArrayList<String>();

		for (int i = 1; i <= 12; i++) {
			monthType.add(String.valueOf(i));
		}
		return SUCCESS;
	}

	@Action(value = "statisticsAccountTimeoutList", results = {
			@Result(type = "json", name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	public String statisticsAccountTimeoutList() throws Exception {
		if (parentOrgId == null || year == null || month == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = statisticsAccountTimeoutService
				.findStatisticsAccountTimeoutByParentOrgIdAndTime(parentOrgId, internalId, year,
						month, sidx, sord);
		return SUCCESS;
	}

	public int getInternalId() {
		return internalId;
	}

	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}

	public int getMinYear() {
		return minYear;
	}

	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}

	public int getMinMonth() {
		return minMonth;
	}

	public void setMinMonth(int minMonth) {
		this.minMonth = minMonth;
	}

	public int getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}

	public int getMaxMonth() {
		return maxMonth;
	}

	public void setMaxMonth(int maxMonth) {
		this.maxMonth = maxMonth;
	}

	public Integer getNowYear() {
		return nowYear;
	}

	public void setNowYear(Integer nowYear) {
		this.nowYear = nowYear;
	}

	public Integer getNowMonth() {
		return nowMonth;
	}

	public void setNowMonth(Integer nowMonth) {
		this.nowMonth = nowMonth;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public List getMonthType() {
		return monthType;
	}

	public void setMonthType(List monthType) {
		this.monthType = monthType;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

}
