package com.tianque.statAnalyse.issueManage.listManage.dao;

import java.util.List;

import com.tianque.statAnalyse.issueManage.listManage.domain.IssueAreaStat;

public interface IssueReportStatDao {
	public void addIssueHandleStats(List<IssueAreaStat> issueAreaStats);

	public void addIssueClassificationStats(List<IssueAreaStat> issueAreaStats);

	public void addIssueStepStats(List<IssueAreaStat> issueAreaStats);

	public IssueAreaStat getHistoryIssueAreaStatsByYearAndMonthAndOrgCode(
			Integer year, Integer month, String orgInternalCode,
			Integer queryType);
}
