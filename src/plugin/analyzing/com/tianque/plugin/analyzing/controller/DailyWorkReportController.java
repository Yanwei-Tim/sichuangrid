package com.tianque.plugin.analyzing.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.plugin.analyzing.domain.DailyWork;
import com.tianque.plugin.analyzing.service.DailyWorkReportService;

/**
 * 网格化服务管理工作日常考核表控制类
 * 
 * @author T26
 */
@Scope("request")
@Namespace("/baseInfoStat/DailyWorkReportManage")
@Controller("dailyWorkReportController")
public class DailyWorkReportController extends BaseAction {

	private List<DailyWork> dataList;// 返回数据结果集

	@Autowired
	private DailyWorkReportService dailyWorkReportService;

	@Action(value = "getDailyWorkReportForList", results = { @Result(name = "success", type = "json", params = {
			"root", "dataList", "ignoreHierarchy", "false" }) })
	public String getDailyWorkReportForList() throws Exception {
		dataList = dailyWorkReportService.getDailyWorkReportForList();
		return SUCCESS;
	}

	public List<DailyWork> getDataList() {
		return dataList;
	}

	public void setDataList(List<DailyWork> dataList) {
		this.dataList = dataList;
	}

}
