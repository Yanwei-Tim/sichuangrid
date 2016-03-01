package com.tianque.statAnalyse.issueManage.listManage.dao;

import java.util.Date;

import com.tianque.domain.Organization;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueAreaStat;

public interface IssueLevelStatDao {
	public IssueAreaStat getIssueLevelStatsByYearAndMonthAndOrgCodeAndOrgLevel(
			Date startDate, Date endDate, Organization organization,
			Long orgLevelId, Integer queryType);

	public IssueAreaStat getIssueLevelStatsByYearAndMonthAndOrgCodeAndFunOrgType(
			Date startDate, Date endDate, Organization organization,
			Long funOrgType, Integer queryType);

	public IssueAreaStat getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndOrgLevel(
			Date startDate, Date endDate, Organization organization, Long id,
			Integer queryType);

	public IssueAreaStat getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndFunOrgType(
			Date startDate, Date endDate, Organization organization, Long id,
			Integer queryType);

	public IssueAreaStat getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndOrgLevelNew(
			Date startDate, Date endDate, Organization organization, Long id,
			Integer queryType);

	public IssueAreaStat getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndOrgLevelForAdminNew(
			Date startDate, Date endDate, Organization organization, Long id,
			Integer queryType);

	public IssueAreaStat getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndFunOrgTypeNew(
			Date startDate, Date endDate, Organization organization, Long id,
			Integer queryType);
}
