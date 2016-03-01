package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueRelatedPeopleDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueRelatedPeople;

@Repository("fourTeamsIssueRelatedPeopleDao")
public class FourTeamsIssueRelatedPeopleDaoImpl extends AbstractBaseDao
		implements FourTeamsIssueRelatedPeopleDao {

	@Override
	public void addIssueRelatedPeople(FourTeamsIssueRelatedPeople issueRelatedPeople) {

		getSqlMapClientTemplate().insert(
				"fourTeamsIssueRelatedPeople.addIssueRelatedPeople",
				issueRelatedPeople);
	}

	@Override
	public void deleteIssueRelatedPeopleByIssueId(Long issueId) {
		getSqlMapClientTemplate()
				.delete(
						"fourTeamsIssueRelatedPeople.deleteIssueRelatedPeopleByIssueId",
						issueId);
	}

	@Override
	public List<FourTeamsIssueRelatedPeople> findIssueRelatedPeopleByIssueId(Long issueId) {

		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssueRelatedPeople.findIssueRelatedPeopleByIssueId",
				issueId);
	}

}
