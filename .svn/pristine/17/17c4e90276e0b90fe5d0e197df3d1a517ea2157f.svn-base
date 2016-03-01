package com.tianque.plugin.analysisNew.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IssueAnalysisChartNewDao {

	public List<Map<String, Object>> getStatisticColumnFromHistory(Long orgId,
			Integer year, Integer month, Integer quarter, String issueTypeDomain);

	public List<Map<String, Object>> getStatisticColumnFromRealTime(Long orgId,
			Date startDate, Date endDate, String issueTypeDomain);

	public List<Map<String, Object>> getAllIssueDomainStatisticPieFromHistory(
			String orgCode, Integer year, Integer month, Integer quarter);

	public List<Map<String, Object>> getAllIssueDomainStatisticPieFromRealTime(
			String orgCode, Date startDate, Date endDate);

	public List<Map<String, Object>> getAllIssueTypeStatisticPieFromHistory(
			String orgCode, Integer year, Integer month, Integer quarter,
			Long issueTypeDomainId);

	public List<Map<String, Object>> getAllIssueTypeStatisticPieFromRealTime(
			String orgCode, Date startDate, Date endDate, Long issueTypeDomainId);

	public void createIssueAnalysisChartData();

	public Integer getIssueTendencyCountFromHistory(String orgCode,
			Integer year, Integer month, Integer quarter);
}
