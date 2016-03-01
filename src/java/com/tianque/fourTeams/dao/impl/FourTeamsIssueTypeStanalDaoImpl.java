package com.tianque.fourTeams.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.IssueTypeStanal;
import com.tianque.domain.Organization;
import com.tianque.fourTeams.dao.FourTeamsIssueTypeStanalDao;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueTypes;
import com.tianque.report.queryVo.OrgGroupedObject;

@SuppressWarnings("unchecked")
@Repository("fourTeamsIssueTypeStanalDao")
public class FourTeamsIssueTypeStanalDaoImpl extends AbstractBaseDao implements
		FourTeamsIssueTypeStanalDao {

	@Override
	public List<Organization> findOrganizations() {
		return getSqlMapClientTemplate().queryForList(
				"issueTypeStanal.findOrganizations");
	}

	@Override
	public IssueTypeStanal getIssueStartTime() {
		return (IssueTypeStanal) getSqlMapClientTemplate().queryForObject(
				"issueTypeStanal.getIssueStartTime");
	}

	@Override
	public IssueTypeStanal addIssueTypeStanal(IssueTypeStanal issueTypeStanal) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"issueTypeStanal.addIssueTypeStanal", issueTypeStanal);
		return getSimpleIssueTypeStanalById(id);
	}

	@Override
	public IssueTypeStanal getSimpleIssueTypeStanalById(Long id) {
		return (IssueTypeStanal) getSqlMapClientTemplate().queryForObject(
				"issueTypeStanal.getSimpleIssueTypeStanalById", id);
	}

	@Override
	public List<IssueTypeDomain> findIssueTypeDomains() {
		return getSqlMapClientTemplate().queryForList(
				"issueTypeStanal.findIssueTypeDomains", FourTeamsIssueTypes.CORE_MODULE);
	}

	@Override
	public List<IssueTypeStanal> stanalIssueTypeDomains(Long orgId,
			Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("issueType", FourTeamsIssueTypes.CORE_MODULE);
		return getSqlMapClientTemplate().queryForList(
				"issueTypeStanal.stanalIssueTypeDomains", map);
	}

	@Override
	public List<IssueTypeStanal> stanalIssueTypes(Long orgId, Long domainId,
			Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("domainId", domainId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList(
				"issueTypeStanal.stanalIssueTypes", map);
	}

	@Override
	public void deleteIssueTypeStanalByStartEndDateOrgCode(Date startDate,
			Date endDate, String orgCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("orgInternalCode", orgCode);
		getSqlMapClientTemplate().delete(
				"issueTypeStanal.deleteIssueTypeStanalByStartEndDateOrgCode",
				map);

	}

	@Override
	public List<Organization> findOrganizationsByOrgInternalCode(
			String orgInternalCode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate().queryForList(
				"issueTypeStanal.findOrganizationsByOrgInternalCode", map);
	}

	@Override
	public void deleteAllStatIssueByOrgId(long orgId) {
		getSqlMapClientTemplate().delete(
				"issueTypeStanal.deleteAllStatIssueByOrgId", orgId);

	}

	@Override
	public List<IssueTypeStanal> getIssueTypeDomains() {
		return getSqlMapClientTemplate().queryForList(
				"issueTypeStanal.getIssueTypeDomains", FourTeamsIssueTypes.CORE_MODULE);
	}

	@Override
	public List<OrgGroupedObject> statIssueCountByTimeGroupOrgAndType(
			String orgInternalCode, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList(
				"issueTypeStanal.statIssueCountByTimeGroupOrgAndType", map);
	}

	@Override
	public List<OrgGroupedObject> getPeriodIssueTypeStatInfoByOrgCodeGroupByTypeDomain(
			String orgCode, Long year, Long month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgcode", orgCode);
		map.put("year", year);
		map.put("month", month);
		return getSqlMapClientTemplate()
				.queryForList(
						"issueTypeStanal.getPeriodIssueTypeStatInfoByOrgCodeGroupByTypeDomain",
						map);
	}

	@Override
	public List<OrgGroupedObject> getPeriodIssueTypeStatInfoListByOrgCodeGroupByTypeDomain(
			String orgCode, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgcode", orgCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate()
				.queryForList(
						"issueTypeStanal.getPeriodIssueTypeStatInfoListByOrgCodeGroupByTypeDomain",
						map);
	}

	@Override
	public List<OrgGroupedObject> getPeriodIssueTypeStatDetailInfoByOrgAndTypeDomain(
			String orgInternalCode, Long domainid, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgcode", orgInternalCode);
		map.put("domainid", domainid);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate()
				.queryForList(
						"issueTypeStanal.getPeriodIssueTypeStatDetailInfoByOrgAndTypeDomain",
						map);
	}

	@Override
	public List<OrgGroupedObject> getPeriodIssueTypeStatInfoByOrgIdGroupByTypeDomain(
			Long year, Long month, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("year", year);
		map.put("month", month);
		return getSqlMapClientTemplate()
				.queryForList(
						"issueTypeStanal.getPeriodIssueTypeStatInfoByOrgIdGroupByTypeDomain",
						map);
	}

	@Override
	public List<OrgGroupedObject> getPeriodIssueTypeStatInfoListByOrgIdGroupByTypeDomain(
			Date startDate, Date endDate, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate()
				.queryForList(
						"issueTypeStanal.getPeriodIssueTypeStatInfoListByOrgIdGroupByTypeDomain",
						map);
	}

	@Override
	public List<OrgGroupedObject> getPeriodIssueTypeStatDetailInfoByOrgIdAndTypeDomain(
			Long orgId, Long domainid, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("domainid", domainid);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate()
				.queryForList(
						"issueTypeStanal.getPeriodIssueTypeStatDetailInfoByOrgIdAndTypeDomain",
						map);
	}

}