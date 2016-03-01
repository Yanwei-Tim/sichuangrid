package com.tianque.plugin.analysisNew.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.IssueLineColumnVo;
import com.tianque.plugin.analysisNew.service.IssueAnalysisChartNewService;

@Controller("issueAnalysisChartNewController")
@Scope("request")
@Namespace("/issueAnalysisChartManageNew")
public class IssueAnalysisChartNewController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(IssueAnalysisChartNewController.class);
	private Integer year;
	private Integer month;
	private Long orgId;
	private Integer quarter;// 季度
	private HighchartColumnVo highchartColumnVo;
	private String searchType;// 查询方式
	private List<Object[]> highchartPieVo;
	private Long issueTypeDomainId;// 如果等于0，则表示根据大类统计数据
	private IssueLineColumnVo issueLineColumnVo;

	@Autowired
	private IssueAnalysisChartNewService issueAnalysisChartService;

	@Action(value = "getStatisticColumn", results = {
			@Result(type = "json", name = "success", params = { "root",
					"highchartColumnVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getStatisticColumn() throws Exception {
		highchartColumnVo = issueAnalysisChartService.getStatisticColumn(orgId,
				year, month, quarter, searchType);
		return SUCCESS;
	}

	@Action(value = "getStatisticPie", results = {
			@Result(type = "json", name = "success", params = { "root",
					"highchartPieVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getStatisticPie() throws Exception {
		highchartPieVo = issueAnalysisChartService.getStatisticPie(orgId, year,
				month, quarter, searchType, issueTypeDomainId);
		return SUCCESS;
	}

	@Action(value = "getIssueTendencyChart", results = {
			@Result(type = "json", name = "success", params = { "root",
					"highchartColumnVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getIssueTendencyChart() throws Exception {
		highchartColumnVo = issueAnalysisChartService.getIssueTendencyChart(
				orgId, searchType);
		return SUCCESS;
	}

	/**
	 * 
	 * @说明:同比
	 */
	@Action(value = "getIssueComparedSameChart", results = {
			@Result(type = "json", name = "success", params = { "root",
					"issueLineColumnVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getIssueComparedSameChart() throws Exception {
		issueLineColumnVo = issueAnalysisChartService
				.getIssueComparedSameChart(orgId, year, month, quarter,
						searchType);
		return SUCCESS;
	}

	/**
	 * 
	 * @说明:环比
	 */
	@Action(value = "getIssueSequentialChart", results = {
			@Result(type = "json", name = "success", params = { "root",
					"issueLineColumnVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getIssueSequentialChart() throws Exception {
		issueLineColumnVo = issueAnalysisChartService.getIssueSequentialChart(
				orgId, year, month, quarter, searchType);
		return SUCCESS;
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public void setHighchartColumnVo(HighchartColumnVo highchartColumnVo) {
		this.highchartColumnVo = highchartColumnVo;
	}

	public HighchartColumnVo getHighchartColumnVo() {
		return highchartColumnVo;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public Integer getQuarter() {
		return quarter;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setHighchartPieVo(List<Object[]> highchartPieVo) {
		this.highchartPieVo = highchartPieVo;
	}

	public List<Object[]> getHighchartPieVo() {
		return highchartPieVo;
	}

	public void setIssueTypeDomainId(Long issueTypeDomainId) {
		this.issueTypeDomainId = issueTypeDomainId;
	}

	public Long getIssueTypeDomainId() {
		return issueTypeDomainId;
	}

	public void setIssueLineColumnVo(IssueLineColumnVo issueLineColumnVo) {
		this.issueLineColumnVo = issueLineColumnVo;
	}

	public IssueLineColumnVo getIssueLineColumnVo() {
		return issueLineColumnVo;
	}

}
