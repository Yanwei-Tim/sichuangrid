package com.tianque.issue.dao;

import com.tianque.issue.domain.IssueEvaluate;

public interface IssueEvaluateDao {

	public IssueEvaluate addIssueEvaluate(IssueEvaluate issueEvaluate);

	public IssueEvaluate updateIssueEvaluate(IssueEvaluate issueEvaluate);

	public IssueEvaluate getIssueEvaluateByIssueId(Long issueId);

	public IssueEvaluate getIssueEvaluateById(Long id);
	
	public void deleteIssueEvaluateByIssueId(Long issueId);

}
