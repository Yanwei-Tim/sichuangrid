package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.HistoricalIssue;
import com.tianque.issue.vo.IssueViewObject;

public interface HistoricalIssueDao {

	public HistoricalIssue addHistoricalIssue(HistoricalIssue historicalIssue);

	public HistoricalIssue getSimpleHistoricalIssueById(Long id);

	public PageInfo<IssueViewObject> findHistoricalIssueForPage(Long orgId, Integer page,
			Integer rows, String sidx, String sord);

	public void deleteHistoricalIssueByIssueIdAndOrgId(Long issueId, Long orgId);

	public void deleteHistoricalIssueByIssueId(Long issueId);

	public HistoricalIssue findHistoricalIssueByIssueIdAndOrgId(Long issueId, Long orgId);
}
