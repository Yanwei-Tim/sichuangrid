package com.tianque.fourTeams.fourTeamsIssue.service;

import java.util.List;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueRelatedPeople;

public interface FourTeamsIssueRelatedPeopleService {

	public void addIssueRelatedPeople(FourTeamsIssueRelatedPeople issueRelatedPeople);

	public void deleteIssueRelatedPeopleByIssueId(Long issueId);

	public List<FourTeamsIssueRelatedPeople> findIssueRelatedPeopleByIssueId(Long issueId);

}
