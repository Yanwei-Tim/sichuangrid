package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.service.SearchFourTeamsIssueStepService;

@Transactional
@Service("SearchFourTeamsIssueStepService")
public class SearchFourTeamsIssueStepServiceImpl implements
		SearchFourTeamsIssueStepService {
	@Autowired
	private FourTeamsIssueDao issueDao;

	@Override
	public List<FourTeamsIssueStep> searchIssueStepsByIssueId(Long issueId) {
		if (issueId == null) {
			return null;
		}
		return issueDao.searchIssueStepsByIssueId(issueId);
	}

	@Override
	public List<FourTeamsIssueStep> searchAllIssueStepsByStepId(Long stepId) {
		if (stepId == null) {
			return null;
		}
		return issueDao.searchAllIssueStepsByStepId(stepId);
	}

}
