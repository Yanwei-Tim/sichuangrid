package com.tianque.issue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.issue.dao.IssueDao;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.service.SearchIssueStepService;

@Transactional
@Service("searchIssueStepService")
public class SearchIssueStepServiceImpl implements SearchIssueStepService {
	@Autowired
	private IssueDao issueDao;

	@Override
	public List<IssueStep> searchIssueStepsByIssueId(Long issueId) {
		if (issueId == null) {
			return null;
		}
		return issueDao.searchIssueStepsByIssueId(issueId);
	}

	@Override
	public List<IssueStep> searchAllIssueStepsByStepId(Long stepId) {
		if (stepId == null) {
			return null;
		}
		return issueDao.searchAllIssueStepsByStepId(stepId);
	}

	@Override
	public IssueStep findIssueStepByIssueId(Long issueId) {
		if (issueId == null) {
			return null;
		}
		return issueDao.findIssueStepByIssueId(issueId);
	}

}
