package com.tianque.mobile.leaderAnalysis.impl;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.util.StringUtil;
import com.tianque.leaderAnalysis.domain.GeneralSituation;
import com.tianque.leaderAnalysis.domain.IssueAnalysisToMobile;
import com.tianque.leaderAnalysis.domain.IssueTotalCaseListData;
import com.tianque.leaderAnalysis.service.LeaderAnalysisService;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.leaderAnalysis.LeaderAnalysisAdapter;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.domain.IssueLineColumnVo;
import com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.service.IssueAnalysisChartService;

@Scope("request")
@Namespace("/mobile/leaderAnalysisManage")
@Controller("leaderAnalysisAdapter")
public class LeaderAnalysisAdapterImpl extends BaseMobileAction implements
		LeaderAnalysisAdapter {

	@Autowired
	private LeaderAnalysisService leaderAnalysisService;
	@Autowired
	private IssueAnalysisChartService issueAnalysisChartService;

	public Long orgId;
	public boolean isGrid;
	public String tableName;
	public Integer orgLevelDistance;
	public int year;
	public int month;
	public int childType;// 子类，判断是否是
	/** 事件大类类型对应的英文标识 */
	public String issueType;

	public Map<Integer, List<GeneralSituation>> generalSituationData;

	/** 事件各个大类的列表 */
	public List<IssueAnalysisToMobile> issueAnalysisSituationData;

	public Map<String, Object> datasDetails;
	public List<Object[]> highchartPieVo;
	public HighchartColumnVo highchartColumnVo;
	public IssueLineColumnVo issueLineColumnVo;
	public List<IssueTotalCaseListData> issueTotalCaseListDatas;

	@Override
	@Action(value = "getGeneralSituation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"generalSituationData", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getGeneralSituation() throws Exception {
		if (orgId == null) {
			errorMessage = "组织机构ID为空";
			return ERROR;
		}
		generalSituationData = leaderAnalysisService.getGeneralSituationDatas(
				orgId, isGrid);
		return SUCCESS;
	}

	@Override
	@Action(value = "getPopulationDatasDetails", results = {
			@Result(name = "success", type = "json", params = { "root",
					"datasDetails", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getPopulationDatasDetails() throws Exception {
		datasDetails = leaderAnalysisService.getPopulationDatasDetails(
				tableName, orgLevelDistance, orgId, year, month);
		return SUCCESS;
	}

	/**
	 * 
	 * 事件各个大类的列表
	 * */

	@Action(value = "getIssueDatasList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueAnalysisSituationData", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String getIssueDatasList() throws Exception {
		if (orgId == null || !StringUtil.isStringAvaliable(issueType)) {
			errorMessage = "参数错误";
			return ERROR;
		}
		issueAnalysisSituationData = leaderAnalysisService.getIssueDatasList(
				issueType, orgId, year, month);
		return SUCCESS;
	}

	@Action(value = "getStatisticPie", results = {
			@Result(type = "json", name = "success", params = { "root",
					"highchartPieVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String getStatisticPie() throws Exception {
		highchartPieVo = issueAnalysisChartService.getStatisticPieToMobile(
				orgId, year, month, issueType);
		return SUCCESS;
	}

	@Action(value = "getStatisticColumn", results = {
			@Result(type = "json", name = "success", params = { "root",
					"highchartColumnVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String getStatisticColumn() throws Exception {
		highchartColumnVo = issueAnalysisChartService
				.getStatisticColumnForMobile(orgId, year, month, issueType);
		return SUCCESS;
	}

	/**
	 * 
	 * @说明:环比趋势图
	 */
	@Action(value = "getIssueSequentialChart", results = {
			@Result(type = "json", name = "success", params = { "root",
					"issueLineColumnVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String getIssueSequentialChart() throws Exception {
		issueLineColumnVo = issueAnalysisChartService
				.getIssueSequentialChartForMobile(orgId, year, month, issueType);
		return SUCCESS;
	}

	/**
	 * 事件总况列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "getIssueTotalCaseList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"issueTotalCaseListDatas", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String getIssueTotalCaseList() throws Exception {
		issueTotalCaseListDatas = leaderAnalysisService
				.getIssueTotalCaseListForMobile(orgId, year, month);
		return SUCCESS;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public boolean isGrid() {
		return isGrid;
	}

	public void setGrid(boolean isGrid) {
		this.isGrid = isGrid;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getOrgLevelDistance() {
		return orgLevelDistance;
	}

	public void setOrgLevelDistance(Integer orgLevelDistance) {
		this.orgLevelDistance = orgLevelDistance;
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

	public int getChildType() {
		return childType;
	}

	public void setChildType(int childType) {
		this.childType = childType;
	}

	public Map<Integer, List<GeneralSituation>> getGeneralSituationData() {
		return generalSituationData;
	}

	public void setGeneralSituationData(
			Map<Integer, List<GeneralSituation>> generalSituationData) {
		this.generalSituationData = generalSituationData;
	}

	public Map<String, Object> getDatasDetails() {
		return datasDetails;
	}

	public void setDatasDetails(Map<String, Object> datasDetails) {
		this.datasDetails = datasDetails;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public List<IssueAnalysisToMobile> getIssueAnalysisSituationData() {
		return issueAnalysisSituationData;
	}

	public void setIssueAnalysisSituationData(
			List<IssueAnalysisToMobile> issueAnalysisSituationData) {
		this.issueAnalysisSituationData = issueAnalysisSituationData;
	}

	public List<Object[]> getHighchartPieVo() {
		return highchartPieVo;
	}

	public void setHighchartPieVo(List<Object[]> highchartPieVo) {
		this.highchartPieVo = highchartPieVo;
	}

	public HighchartColumnVo getHighchartColumnVo() {
		return highchartColumnVo;
	}

	public void setHighchartColumnVo(HighchartColumnVo highchartColumnVo) {
		this.highchartColumnVo = highchartColumnVo;
	}

	public IssueLineColumnVo getIssueLineColumnVo() {
		return issueLineColumnVo;
	}

	public void setIssueLineColumnVo(IssueLineColumnVo issueLineColumnVo) {
		this.issueLineColumnVo = issueLineColumnVo;
	}

	public List<IssueTotalCaseListData> getIssueTotalCaseListDatas() {
		return issueTotalCaseListDatas;
	}

	public void setIssueTotalCaseListDatas(
			List<IssueTotalCaseListData> issueTotalCaseListDatas) {
		this.issueTotalCaseListDatas = issueTotalCaseListDatas;
	}

}
