package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.IssueStatueStanalDao;
import com.tianque.domain.IssueStatueStanal;

@Repository("issueStatueStanalDao")
@SuppressWarnings("unchecked")
public class IssueStatueStanalDaoImpl extends AbstractBaseDao implements IssueStatueStanalDao {

	@Override
	public IssueStatueStanal addIssueStatueStanal(IssueStatueStanal issueStatueStanal) {
		Long id = (Long) getSqlMapClientTemplate().insert("issueStatueStanal.addIssueStatueStanal",
				issueStatueStanal);
		return getIssueStatueStanalById(id);
	}

	@Override
	public IssueStatueStanal getIssueStatueStanalById(Long id) {
		return (IssueStatueStanal) getSqlMapClientTemplate().queryForObject(
				"issueStatueStanal.getIssueStatueStanalById", id);
	}

	@Override
	public void deleteIssueStatueStanal(IssueStatueStanal issueStatueStanal) {
		Map map = new HashMap();
		map.put("orgInternalCode", issueStatueStanal.getOrganization().getOrgInternalCode());
		map.put("year", issueStatueStanal.getYear());
		map.put("month", issueStatueStanal.getMonth());
		getSqlMapClientTemplate().delete("issueStatueStanal.deleteIssueStatueStanal", map);
	}

	@Override
	public IssueStatueStanal getIssueStatueStanalStatCount(IssueStatueStanal issueStatueStanal) {
		Map map = new HashMap();
		map.put("orgId", issueStatueStanal.getOrganization().getId());
		map.put("startDate", issueStatueStanal.getIssueStatDate());
		map.put("endDate", issueStatueStanal.getEndDate());
		return (IssueStatueStanal) getSqlMapClientTemplate().queryForObject(
				"issueStatueStanal.getIssueStatueStanalStatCount", map);
	}

	@Override
	public IssueStatueStanal getIssueStatueStanalStatCountByYearAndMonth(Long orgId, Integer year,
			Integer month) {
		Map query = new HashMap();
		query.put("orgId", orgId);
		query.put("year", year);
		query.put("month", month);
		return (IssueStatueStanal) getSqlMapClientTemplate().queryForObject(
				"issueStatueStanal.getIssueStatueStanalStatCountByYearAndMonth", query);
	}
}
