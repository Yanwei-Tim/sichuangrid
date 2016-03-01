package com.tianque.issue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.issue.dao.IssueLogDao;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueMap;
import com.tianque.issue.state.IssueStepGroup;

@Repository("IssueLogDao")
public class IssueLogDaoImpl extends AbstractBaseDao implements IssueLogDao {

	@Override
	public IssueLogNew addLog(IssueLogNew log) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"issuelog.addIssueLog", log);
		return getIssueLogById(id);
	}

	@Override
	public IssueLogNew updateLog(IssueLogNew log) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"issuelog.updateIssueLog", log);
		return getIssueLogById(log.getId());
	}

	@Override
	public IssueLogNew getIssueLogById(Long id) {
		return (IssueLogNew) getSqlMapClientTemplate().queryForObject(
				"issuelog.getIssueLogById", id);
	}

	@Override
	public List<IssueLogNew> loadIssueOperationLogsByIssueId(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"issuelog.loadIssueOperationLogsByIssueId", id);
	}

	@Override
	public boolean deleteIssueLogByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete("issuelog.deleteIssueLogByIssueId",
				issueId);
		return true;
	}

	@Override
	public List<IssueLogNew> getIssueLogsByStepId(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"issuelog.getIssueLogsByStepId", id);
	}

	@Override
	public List<IssueLogNew> findIssueDealLogByIssueMapAndIssueStepGroup(
			IssueMap issueMap, IssueStepGroup group) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", group.getIssue().getId());
		map.put("orgId", issueMap.getOrgId());
		map.put("startId", group.getEntyLog().getId());
		map.put("endId", group.getOutLog() == null ? null : group.getOutLog()
				.getId());
		return getSqlMapClientTemplate().queryForList(
				"issuelog.getIssueLogsByIssuMap", map);
	}

}
