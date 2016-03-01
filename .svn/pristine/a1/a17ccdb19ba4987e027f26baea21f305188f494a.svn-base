package com.tianque.plugin.analysisNew.dao;

import java.util.Date;

import com.tianque.domain.Organization;
import com.tianque.plugin.analysisNew.domain.IssueAreaStatNew;

public interface IssueAreaStatNewDao {
	public IssueAreaStatNew getIssueAreaStatsByYearAndMonthAndOrgCode(
			Date startDate, Date endDate, Organization organization,
			Integer queryType);

	public IssueAreaStatNew getHistoryIssueAreaStatsByYearAndMonthAndOrgCode(
			Date startDate, Date endDate, String orgInternalCode,
			Integer queryType);
}
