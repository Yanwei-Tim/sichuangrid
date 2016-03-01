package com.tianque.fourTeams.fourTeamsIssue.service;

import java.util.List;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;

public interface SearchFourTeamsIssueStepService {

	List<FourTeamsIssueStep> searchIssueStepsByIssueId(Long issueId);

	List<FourTeamsIssueStep> searchAllIssueStepsByStepId(Long stepId);
}
