package com.tianque.plugin.analyzing.controller;

import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.plugin.analyzing.domain.UserActivateReport;
import com.tianque.plugin.analyzing.service.UserActivateReportService;

/**
 * @Description:用户覆盖率控制层
 * @author zhangyouwei@hztianque.com
 * @date: 2015-1-16 上午12:18:45
 */
@Namespace("/userActivateReportManage")
@Controller("userActivateReportController")
public class UserActivateReportController extends BaseAction {

	private List<UserActivateReport> listUserActivateReport = null;
	private int year;
	private int month;
	private Integer orgLevelDistance;
	private String sortName;// 排序的字段
	private String sort;// 升序还是降序
	@Autowired
	private UserActivateReportService userActivateReportService;

	/***
	 * 字段排序 除了按组织机构排序外，所有字段排序通用
	 */
	@Action(value = "userActivateReportSort", results = {
			@Result(name = "success", type = "json", params = { "root",
					"listUserActivateReport" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String userActivateReportSort() throws Exception {
		if (!StringUtil.isStringAvaliable(sortName)) {
			listUserActivateReport = userActivateReportService
					.getUserActivateReportList(year, month, orgLevelDistance);
			return SUCCESS;
		}
		if (validateOtherDate()) {
			errorMessage = "所选时间不正确，请重新选择";
			return ERROR;
		}
		listUserActivateReport = userActivateReportService
				.getUserActivateReportSort(year, month, orgLevelDistance,
						sortName, sort);
		return SUCCESS;
	}

	/**
	 * 报表展示
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "getUserActivateReportList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"listUserActivateReport" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getUserActivateReportList() throws Exception {
		if (validateOtherDate()) {
			this.errorMessage = "所选时间数据统计失败";
			return ERROR;
		}
		listUserActivateReport = userActivateReportService
				.getUserActivateReportList(year, month, orgLevelDistance);
		return SUCCESS;

	}

	/**
	 * 生成表格
	 * 
	 * @return
	 */
	@Action(value = "createUserActivateReportList", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String createUserActivateReportList() throws Exception {

		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month >= (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生产！";
			return ERROR;
		}
		userActivateReportService.createUserActivateReportList(year, month);
		return SUCCESS;
	}

	/***
	 * 验证所统计的时间段是否大于当前月
	 * 
	 * @return
	 */
	private boolean validateOtherDate() {
		return year == Calendar.getInstance().get(Calendar.YEAR)
				&& month >= Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public List<UserActivateReport> getListUserActivateReport() {
		return listUserActivateReport;
	}

	public void setListUserActivateReport(
			List<UserActivateReport> listUserActivateReport) {
		this.listUserActivateReport = listUserActivateReport;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Integer getOrgLevelDistance() {
		return orgLevelDistance;
	}

	public void setOrgLevelDistance(Integer orgLevelDistance) {
		this.orgLevelDistance = orgLevelDistance;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
