package com.tianque.issue.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.issue.dao.IssueRelatedPeopleDao;
import com.tianque.issue.domain.IssueRelatedPeople;

@Repository("issueRelatedPeopleDao")
public class IssueRelatedPeopleDaoImpl extends AbstractBaseDao implements
		IssueRelatedPeopleDao {

	@Override
	public void addIssueRelatedPeople(IssueRelatedPeople issueRelatedPeople) {

		getSqlMapClientTemplate().insert(
				"issueRelatedPeople.addIssueRelatedPeople", issueRelatedPeople);
	}

	@Override
	public void deleteIssueRelatedPeopleByIssueId(Long issueId) {
		getSqlMapClientTemplate()
				.delete("issueRelatedPeople.deleteIssueRelatedPeopleByIssueId",
						issueId);
	}

	@Override
	public List<IssueRelatedPeople> findIssueRelatedPeopleByIssueId(Long issueId) {

		return getSqlMapClientTemplate().queryForList(
				"issueRelatedPeople.findIssueRelatedPeopleByIssueId", issueId);
	}

}
