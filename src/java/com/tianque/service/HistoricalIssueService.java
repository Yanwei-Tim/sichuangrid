package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.HistoricalIssue;
import com.tianque.issue.vo.IssueViewObject;

public interface HistoricalIssueService {

	public HistoricalIssue addHistoricalIssue(HistoricalIssue historicalIssue);

	public PageInfo<IssueViewObject> findHistoricalIssueForPage(Long orgId, Integer page,
			Integer rows, String sidx, String sord);

	public void deleteHistoricalIssueByIssueIdAndOrgId(Long issueId, Long orgId);

	public HistoricalIssue findHistoricalIssueByIssueIdAndOrgId(Long issueId, Long orgId);

	// public boolean isHistorical(Long issue);
}
