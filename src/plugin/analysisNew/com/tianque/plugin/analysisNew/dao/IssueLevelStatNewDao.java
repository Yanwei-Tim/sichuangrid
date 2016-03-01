package com.tianque.plugin.analysisNew.dao;

import java.util.Date;

import com.tianque.domain.Organization;
import com.tianque.plugin.analysisNew.domain.IssueAreaStatNew;

public interface IssueLevelStatNewDao {
	public IssueAreaStatNew getIssueLevelStatsByYearAndMonthAndOrgCodeAndOrgLevel(
			Date startDate, Date endDate, Organization organization,
			Long orgLevelId, Integer queryType);

	public IssueAreaStatNew getIssueLevelStatsByYearAndMonthAndOrgCodeAndFunOrgType(
			Date startDate, Date endDate, Organization organization,
			Long funOrgType, Integer queryType);

	public IssueAreaStatNew getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndOrgLevel(
			Date startDate, Date endDate, Organization organization, Long id,
			Integer queryType);

	public IssueAreaStatNew getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndFunOrgType(
			Date startDate, Date endDate, Organization organization, Long id,
			Integer queryType);
}
