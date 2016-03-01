package com.tianque.plugin.analysisNew.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.analysisNew.domain.IssueAreaStatNew;
import com.tianque.plugin.analysisNew.domain.IssueTypeStat;
import com.tianque.plugin.analysisNew.service.IssueReportNewService;

@Namespace("/statAnalyse/issueReportNew")
@Controller("issueReportNewController")
@Scope("request")
public class IssueReportNewController extends BaseAction {
	private Integer year;
	private Integer month;
	private Long parentOrgId;
	private Integer queryType;
	private PropertyDict reportDateType;
	private List<IssueAreaStatNew> issueAreaStats;
	private List<IssueTypeStat> issueTypeStats;

	@Autowired
	private IssueReportNewService issueReportService;

	@Action(value = "getDataColumnByArea", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueAreaStats", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getDataColumnByArea() {
		if (parentOrgId == null || year == null || month == null
				|| reportDateType == null || reportDateType.getId() == null) {
			return ERROR;
		}

		issueAreaStats = issueReportService
				.findIssueAreaStatsByYearAndMonthAndParentOrgId(year, month,
						parentOrgId, queryType, reportDateType);
		return SUCCESS;
	}

	@Action(value = "getDataColumnByLevel", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueTypeStats", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getDataColumnByLevel() {
		if (parentOrgId == null || year == null || month == null
				|| reportDateType == null || reportDateType.getId() == null) {
			return ERROR;
		}

		issueTypeStats = issueReportService
				.findIssueLevelStatsByYearAndMonthAndParentOrgId(year, month,
						parentOrgId, queryType, reportDateType);
		return SUCCESS;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public List<IssueAreaStatNew> getIssueAreaStats() {
		return issueAreaStats;
	}

	public void setIssueAreaStats(List<IssueAreaStatNew> issueAreaStats) {
		this.issueAreaStats = issueAreaStats;
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

	public List<IssueTypeStat> getIssueTypeStats() {
		return issueTypeStats;
	}

	public void setIssueTypeStats(List<IssueTypeStat> issueTypeStats) {
		this.issueTypeStats = issueTypeStats;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

	public PropertyDict getReportDateType() {
		return reportDateType;
	}

	public void setReportDateType(PropertyDict reportDateType) {
		this.reportDateType = reportDateType;
	}

}
