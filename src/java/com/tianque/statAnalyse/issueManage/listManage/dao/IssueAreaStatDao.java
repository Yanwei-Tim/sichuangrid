package com.tianque.statAnalyse.issueManage.listManage.dao;

import java.util.Date;

import com.tianque.domain.Organization;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueAreaStat;

public interface IssueAreaStatDao {
	public IssueAreaStat getIssueAreaStatsByYearAndMonthAndOrgCode(
			Date startDate, Date endDate, Organization organization,
			Integer queryType);

	public IssueAreaStat getHistoryIssueAreaStatsByYearAndMonthAndOrgCode(
			Date startDate, Date endDate, String orgInternalCode,
			Integer queryType);

	public IssueAreaStat getHistoryIssueAreaStatsByYearAndMonthAndOrgCodeNew(
			Date startDate, Date endDate, String orgInternalCode,
			Integer queryType);
	public IssueAreaStat getIssueAreaStatsByYearAndMonthAndOrgId_history(
			Date startDate, Date endDate,Long orgId);
	public IssueAreaStat getIssueAreaTypeStatsByYearAndMonthAndOrgId_history(
			Date startDate, Date endDate,Long orgId);
	public IssueAreaStat getIssueStepStatsByYearAndMonthAndOrgId_history(
			Date startDate, Date endDate,Long orgId);
}
