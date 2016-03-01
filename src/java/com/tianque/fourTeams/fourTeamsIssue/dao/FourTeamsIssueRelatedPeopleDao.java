package com.tianque.fourTeams.fourTeamsIssue.dao;

import java.util.List;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueRelatedPeople;

public interface FourTeamsIssueRelatedPeopleDao {

	public void addIssueRelatedPeople(FourTeamsIssueRelatedPeople issueRelatedPeople);

	public void deleteIssueRelatedPeopleByIssueId(Long issueId);

	public List<FourTeamsIssueRelatedPeople> findIssueRelatedPeopleByIssueId(Long issueId);

}
