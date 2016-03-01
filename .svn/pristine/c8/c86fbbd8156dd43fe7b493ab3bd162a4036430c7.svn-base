package com.tianque.statAnalyse.issueManage.listManage.service;

import java.util.Date;
import java.util.List;

import com.tianque.statAnalyse.issueManage.listManage.domain.IssueAreaStat;

public interface IssueAreaStatService {

	public List<IssueAreaStat> findIssueAreaStatsByYearAndMonthAndParentOrgId(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType,String type);

	public List<IssueAreaStat> findHistoryIssueAreaStatsByYearAndMonthAndParentOrgId(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType,String type);

	public List<IssueAreaStat> findHistoryIssueAreaStatsByYearAndMonthAndParentOrgIdNew(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType);
}
