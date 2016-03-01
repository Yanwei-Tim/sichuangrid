package com.tianque.plugin.analysisNew.service;

import java.util.Date;
import java.util.List;

import com.tianque.plugin.analysisNew.domain.IssueTypeStat;

public interface IssueLevelStatNewService {

	public List<IssueTypeStat> findIssueLevelStatsByYearAndMonthAndParentOrgId(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType);

	public List<IssueTypeStat> findHistoryIssueLevelStatsByYearAndMonthAndParentOrgId(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType);
}
