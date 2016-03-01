package com.tianque.fourTeams.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsHistoricalIssue;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

public interface FourTeamsHistoricalIssueService {

	public FourTeamsHistoricalIssue addHistoricalIssue(FourTeamsHistoricalIssue historicalIssue);

	public PageInfo<FourTeamsIssueViewObject> findHistoricalIssueForPage(Long orgId,
			Integer page, Integer rows, String sidx, String sord);

	public void deleteHistoricalIssueByIssueIdAndOrgId(Long issueId, Long orgId);

	public FourTeamsHistoricalIssue findHistoricalIssueByIssueIdAndOrgId(Long issueId,
			Long orgId);

	// public boolean isHistorical(Long issue);
}
