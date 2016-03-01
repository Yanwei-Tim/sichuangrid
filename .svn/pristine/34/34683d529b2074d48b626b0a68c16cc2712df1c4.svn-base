package com.tianque.statAnalyse.issueManage.listManage.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.PropertyDict;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueAreaStat;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueLevelStat;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueTypeStat;
import com.tianque.statAnalyse.issueManage.listManage.service.IssueReportService;

@Namespace("/statAnalyse/issueReport")
@Controller("issueReportController")
@Scope("request")
public class IssueReportController extends BaseAction {
	private Integer year;
	private Integer month;
	private Long parentOrgId;
	private Integer queryType;
	private PropertyDict reportDateType;
	private List<IssueAreaStat> issueAreaStats;
	private List<IssueTypeStat> issueTypeStats;

	private List<IssueLevelStat> issueLevelStats;

	@Autowired
	private IssueReportService issueReportService;

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

	@Action(value = "getDataColumnByAreaNew", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueAreaStats", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getDataColumnByAreaNew() {
		if (parentOrgId == null || year == null || month == null
				|| reportDateType == null || reportDateType.getId() == null) {
			return ERROR;
		}

		issueAreaStats = issueReportService
				.findIssueAreaStatsByYearAndMonthAndParentOrgIdNew(year, month,
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

	@Action(value = "getDataColumnByLevelNew", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueLevelStats", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getDataColumnByLevelNew() {
		if (parentOrgId == null || year == null || month == null
				|| reportDateType == null || reportDateType.getId() == null) {
			return ERROR;
		}
		issueLevelStats = issueReportService
				.findIssueLevelStatsByYearAndMonthAndParentOrgIdNew(year,
						month, parentOrgId, queryType, reportDateType);

		return SUCCESS;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public List<IssueAreaStat> getIssueAreaStats() {
		return issueAreaStats;
	}

	public void setIssueAreaStats(List<IssueAreaStat> issueAreaStats) {
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

	public List<IssueLevelStat> getIssueLevelStats() {
		return issueLevelStats;
	}

	public void setIssueLevelStats(List<IssueLevelStat> issueLevelStats) {
		this.issueLevelStats = issueLevelStats;
	}

}
