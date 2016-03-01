package com.tianque.issue.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.issue.dao.TopIssueDao;
import com.tianque.issue.domain.TopIssue;

@Repository("TopIssueDao")
public class TopIssueDaoImpl extends AbstractBaseDao implements TopIssueDao {

	@Override
	public TopIssue addTopIssue(TopIssue topIssue) {
		Long id = (Long) getSqlMapClientTemplate().insert("topIssue.addTopIssue", topIssue);
		return getTopIssueById(id);
	}

	@Override
	public TopIssue getTopIssue(TopIssue topIssue) {
		return (TopIssue) getSqlMapClientTemplate()
				.queryForObject("topIssue.getTopIssue", topIssue);
	}

	@Override
	public TopIssue getTopIssueById(Long id) {
		return (TopIssue) getSqlMapClientTemplate().queryForObject("topIssue.getTopIssueById", id);
	}

	@Override
	public TopIssue updateTopIssue(TopIssue topIssue) {
		getSqlMapClientTemplate().update("topIssue.updateTopIssue", topIssue);
		return getTopIssue(topIssue);
	}

	@Override
	public boolean deleteTopIssueById(Long id) {
		int rs = getSqlMapClientTemplate().delete("topIssue.deleteTopIssueById", id);
		if (rs == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteTopIssue(Long issueId, Long orgId, int issueTag) {
		TopIssue topIssue = new TopIssue(issueId, issueTag, orgId);
		topIssue = getTopIssue(topIssue);
		if (topIssue != null && topIssue.getId() != null) {
			return deleteTopIssueById(topIssue.getId());
		}

		return false;

	}

}
