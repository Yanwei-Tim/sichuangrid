package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsTopIssueDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsTopIssue;

@Repository("fourTeamsTopIssueDao")
public class FourTeamsTopIssueDaoImpl extends AbstractBaseDao implements
		FourTeamsTopIssueDao {

	@Override
	public FourTeamsTopIssue addTopIssue(FourTeamsTopIssue topIssue) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"fourTeamsTopIssue.addTopIssue", topIssue);
		return getTopIssueById(id);
	}

	@Override
	public FourTeamsTopIssue getTopIssue(FourTeamsTopIssue topIssue) {
		return (FourTeamsTopIssue) getSqlMapClientTemplate().queryForObject(
				"fourTeamsTopIssue.getTopIssue", topIssue);
	}

	@Override
	public FourTeamsTopIssue getTopIssueById(Long id) {
		return (FourTeamsTopIssue) getSqlMapClientTemplate().queryForObject(
				"fourTeamsTopIssue.getTopIssueById", id);
	}

	@Override
	public FourTeamsTopIssue updateTopIssue(FourTeamsTopIssue topIssue) {
		getSqlMapClientTemplate().update("fourTeamsTopIssue.updateTopIssue",
				topIssue);
		return getTopIssue(topIssue);
	}

	@Override
	public boolean deleteTopIssueById(Long id) {
		int rs = getSqlMapClientTemplate().delete(
				"fourTeamsTopIssue.deleteTopIssueById", id);
		if (rs == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTopIssue(Long issueId, Long orgId, int issueTag) {
		FourTeamsTopIssue topIssue = new FourTeamsTopIssue(issueId, issueTag,
				orgId);
		topIssue = getTopIssue(topIssue);
		if (topIssue != null && topIssue.getId() != null) {
			return deleteTopIssueById(topIssue.getId());
		}

		return false;

	}

}
