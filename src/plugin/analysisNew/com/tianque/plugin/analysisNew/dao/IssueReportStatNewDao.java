package com.tianque.plugin.analysisNew.dao;

import java.util.List;

import com.tianque.plugin.analysisNew.domain.IssueAreaStatNew;

public interface IssueReportStatNewDao {
	public void addIssueHandleStats(List<IssueAreaStatNew> issueAreaStats);

	public void addIssueClassificationStats(List<IssueAreaStatNew> issueAreaStats);

	public void addIssueStepStats(List<IssueAreaStatNew> issueAreaStats);

	public IssueAreaStatNew getHistoryIssueAreaStatsByYearAndMonthAndOrgCode(
			Integer year, Integer month, String orgInternalCode,
			Integer queryType);
}
