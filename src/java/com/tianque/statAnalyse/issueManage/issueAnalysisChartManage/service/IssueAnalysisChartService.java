package com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.service;

import java.util.List;

import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.domain.IssueLineColumnVo;

public interface IssueAnalysisChartService {

	public HighchartColumnVo getStatisticColumn(Long orgId, Integer year,
			Integer month, Integer quarter, String searchType);

	public List<Object[]> getStatisticPie(Long orgId, Integer year,
			Integer month, Integer quarter, String searchType,
			Long issueTypeDomainId);

	/**
	 * @说明：job 生成事件统计历史数据
	 */
	public void createIssueAnalysisChartData();

	public HighchartColumnVo getIssueTendencyChart(Long orgId, String searchType);

	public IssueLineColumnVo getIssueComparedSameChart(Long orgId,
			Integer year, Integer month, Integer quarter, String searchType);

	public IssueLineColumnVo getIssueSequentialChart(Long orgId, Integer year,
			Integer month, Integer quarter, String searchType);

	/**
	 * 为手机端提供的单个大类的饼状图展示
	 * 
	 * @param orgId
	 * @param year
	 * @param month
	 * @param issueType
	 * @return
	 */
	public List<Object[]> getStatisticPieToMobile(Long orgId, int year,
			int month, String issueType);

	/**
	 * 为手机端提供的单个大类的柱状图展示
	 * 
	 * @param orgId
	 * @param year
	 * @param month
	 * @param issueType
	 * @return
	 */
	public HighchartColumnVo getStatisticColumnForMobile(Long orgId, int year,
			int month, String issueType);

	/**
	 * 为手机端提供事件环比趋势图
	 * 
	 * @param orgId
	 * @param year
	 * @param month
	 * @param issueType
	 * @return
	 */
	public IssueLineColumnVo getIssueSequentialChartForMobile(Long orgId,
			Integer year, Integer month, String issueType);
}
