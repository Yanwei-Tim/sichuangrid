package com.tianque.workBench.dailyWorkNotice.controller;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.ReceiveDocumentsService;

@Namespace("/workBench/dailyWorkNotice")
@Transactional
@Scope("request")
@Controller("dailyWorkNotice")
public class DailyWorkNoticeController extends BaseAction {
	private Integer overdueReports;
	private Integer willOverdueReports;
	private Integer notsignDocs;
	private Integer notReadDocs;

	@Autowired
	private ReceiveDocumentsService receiveDocumentsService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;

	/**
	 * 查找日常工作数据用于显示
	 */
	@Action(value = "findDailyWorkNoticeInfo", results = {
			@Result(name = "success", location = "/workBench/module/workBench-centre/dailyWorkNotice.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findDailyWorkNoticeInfo() throws Exception {
		Map<String, Integer> report = dailyDirectoryService
				.statisticsReport(ThreadVariable.getSession().getOrganization()
						.getId());
		overdueReports = report.get("already");
		willOverdueReports = report.get("goingTo");
		Map<String, Object> jsonMap = receiveDocumentsService
				.caculateDocuments();
		notsignDocs = (Integer) jsonMap.get("notsignDocs");
		notReadDocs = (Integer) jsonMap.get("notReadDocs");
		return SUCCESS;
	}

	public Integer getOverdueReports() {
		return overdueReports;
	}

	public void setOverdueReports(Integer overdueReports) {
		this.overdueReports = overdueReports;
	}

	public Integer getWillOverdueReports() {
		return willOverdueReports;
	}

	public void setWillOverdueReports(Integer willOverdueReports) {
		this.willOverdueReports = willOverdueReports;
	}

	public Integer getNotsignDocs() {
		return notsignDocs;
	}

	public void setNotsignDocs(Integer notsignDocs) {
		this.notsignDocs = notsignDocs;
	}

	public Integer getNotReadDocs() {
		return notReadDocs;
	}

	public void setNotReadDocs(Integer notReadDocs) {
		this.notReadDocs = notReadDocs;
	}

}
