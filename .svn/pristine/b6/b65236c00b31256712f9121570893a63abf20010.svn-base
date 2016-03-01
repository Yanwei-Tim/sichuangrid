package com.tianque.dao;

import java.util.Date;
import java.util.List;

import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.IssueTypeStanal;
import com.tianque.domain.Organization;
import com.tianque.report.queryVo.OrgGroupedObject;

public interface IssueTypeStanalDao {
	public List<Organization> findOrganizations();

	public IssueTypeStanal getIssueStartTime();

	public IssueTypeStanal addIssueTypeStanal(IssueTypeStanal issueTypeStanal);

	public IssueTypeStanal getSimpleIssueTypeStanalById(Long id);

	public List<IssueTypeDomain> findIssueTypeDomains();

	public List<IssueTypeStanal> stanalIssueTypeDomains(Long orgId, Date startDate, Date endDate);

	public List<IssueTypeStanal> stanalIssueTypes(Long orgId, Long domainId, Date startDate,
			Date endDate);

	public void deleteIssueTypeStanalByStartEndDateOrgCode(Date startDate, Date endDate,
			String orgCode);

	public List<Organization> findOrganizationsByOrgInternalCode(String orgInternalCode);

	public void deleteAllStatIssueByOrgId(long orgId);

	public List<IssueTypeStanal> getIssueTypeDomains();

	public List<OrgGroupedObject> statIssueCountByTimeGroupOrgAndType(String orgInternalCode,
			Date startDate, Date endDate);

	public List<OrgGroupedObject> getPeriodIssueTypeStatInfoByOrgCodeGroupByTypeDomain(
			String orgCode, Long year, Long month);

	public List<OrgGroupedObject> getPeriodIssueTypeStatInfoByOrgIdGroupByTypeDomain(Long year,
			Long month, Long orgId);

	public List<OrgGroupedObject> getPeriodIssueTypeStatInfoListByOrgCodeGroupByTypeDomain(
			String orgCode, Date startDate, Date endDate);

	public List<OrgGroupedObject> getPeriodIssueTypeStatInfoListByOrgIdGroupByTypeDomain(
			Date startDate, Date endDate, Long orgId);

	public List<OrgGroupedObject> getPeriodIssueTypeStatDetailInfoByOrgAndTypeDomain(
			String orgInternalCode, Long domainid, Date startDate, Date endDate);

	public List<OrgGroupedObject> getPeriodIssueTypeStatDetailInfoByOrgIdAndTypeDomain(Long orgId,
			Long domainid, Date startDate, Date endDate);

}
