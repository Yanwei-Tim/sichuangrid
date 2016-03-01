package com.tianque.service;

import java.util.Date;
import java.util.List;

import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.IssueTypeStanal;

public interface IssueTypeStanalService {
	public void addMonthIssueTypeStanals(int year, int month);

	public void reCreateMonthIssueTypeStanalsByOrgId(Long orgId, int year,
			int month);

	List<List<IssueTypeStanal>> getPeriodIssueTypeStatInfoByOrgGroupByTypeDomain(
			Long orgId, Date startDate, Date endDate, Long level);

	List<IssueTypeStanal> getPeriodIssueTypeStatInfoListByOrgGroupByTypeDomain(
			Long orgId, Date startDate, Date endDate, Long level);

	public List<IssueTypeStanal> getPeriodIssueTypeStatDetailInfoByOrgAndTypeDomain(
			Long orgId, Long domainid, Date date, Date date2, Long level);

	public List<IssueTypeDomain> findIssueTypeDomains();

	public void deleteAllStatIssueByOrgId(long orgId);

}
