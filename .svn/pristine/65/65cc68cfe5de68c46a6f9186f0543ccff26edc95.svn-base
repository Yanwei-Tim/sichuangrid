package com.tianque.plugin.analysisNew.dao;

import java.util.Date;
import java.util.List;

import com.tianque.domain.IssueDealStat;
import com.tianque.domain.IssueMonthEndDealingHistory;

public interface IssueStatNewDao {

	public List<IssueDealStat> findIssueDealStatsByOrgInternalCodeMonthStartDate(
			String orgInternalCode, Date statStart);

	public void deleteIssueDealStatsByOrgInternalCodeMonthStartDate(
			String orgInternalCode, Date monthStart);

	public IssueDealStat sumIssueDealStatsByOrgInternalCodeAndStatDateAndOrgType(
			String orgInternalCode, Long id, Date monthStart);

	public IssueDealStat getIssueDealStatsByOrgIdAndStatDate(Long orgId,
			Date monthStart);

	public IssueMonthEndDealingHistory addMonthDealingLogRecord(
			IssueMonthEndDealingHistory record);

	public List<IssueMonthEndDealingHistory> findMonthDealingLogRecordsByOrgInternalCodeMonthStartDate(
			String orgInternalCode, Date statStart);

	public void addIssueDealStats(List<IssueDealStat> result);

	public List<IssueDealStat> findIssueDealStatsByOrgId(Long id);

	public void deleteIssueDealStatsByOrgId(Long orgId);

}
