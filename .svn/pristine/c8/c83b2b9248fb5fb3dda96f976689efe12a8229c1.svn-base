package com.tianque.plugin.analysisNew.service;

import java.util.List;

import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.IssueLineColumnVo;

public interface IssueAnalysisChartNewService {

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
}
