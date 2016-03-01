package com.tianque.statAnalyse.issueManage.listManage.service;

import java.util.Date;
import java.util.List;

import com.tianque.statAnalyse.issueManage.listManage.domain.IssueLevelStat;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueTypeStat;

public interface IssueLevelStatService {

	public List<IssueTypeStat> findIssueLevelStatsByYearAndMonthAndParentOrgId(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType);

	public List<IssueTypeStat> findHistoryIssueLevelStatsByYearAndMonthAndParentOrgId(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType);

	public List<IssueLevelStat> findHistoryIssueLevelStatsByYearAndMonthAndParentOrgIdNew(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType);
}
