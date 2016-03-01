package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueLogDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMap;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;

@Repository("fourTeamsIssueLogDao")
public class FourTeamsIssueLogDaoImpl extends AbstractBaseDao implements
		FourTeamsIssueLogDao {

	@Override
	public FourTeamsIssueLogNew addLog(FourTeamsIssueLogNew log) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"fourTeamsIssuelog.addIssueLog", log);
		return getIssueLogById(id);
	}

	@Override
	public FourTeamsIssueLogNew updateLog(FourTeamsIssueLogNew log) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"fourTeamsIssuelog.updateIssueLog", log);
		return getIssueLogById(log.getId());
	}

	@Override
	public FourTeamsIssueLogNew getIssueLogById(Long id) {
		return (FourTeamsIssueLogNew) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssuelog.getIssueLogById", id);
	}

	@Override
	public List<FourTeamsIssueLogNew> loadIssueOperationLogsByIssueId(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssuelog.loadIssueOperationLogsByIssueId", id);
	}

	@Override
	public boolean deleteIssueLogByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete(
				"fourTeamsIssuelog.deleteIssueLogByIssueId", issueId);
		return true;
	}

	@Override
	public List<FourTeamsIssueLogNew> getIssueLogsByStepId(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssuelog.getIssueLogsByStepId", id);
	}

	@Override
	public List<FourTeamsIssueLogNew> findIssueDealLogByIssueMapAndIssueStepGroup(
			FourTeamsIssueMap issueMap, FourTeamsIssueStepGroup group) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", group.getIssue().getId());
		map.put("orgId", issueMap.getOrgId());
		map.put("startId", group.getEntyLog().getId());
		map.put("endId", group.getOutLog() == null ? null : group.getOutLog()
				.getId());
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssuelog.getIssueLogsByIssuMap", map);
	}

}
