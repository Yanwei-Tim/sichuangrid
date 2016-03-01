package com.tianque.fourTeams.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsHistoricalIssue;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

public interface FourTeamsHistoricalIssueDao {

	public FourTeamsHistoricalIssue addHistoricalIssue(FourTeamsHistoricalIssue historicalIssue);

	public FourTeamsHistoricalIssue getSimpleHistoricalIssueById(Long id);

	public PageInfo<FourTeamsIssueViewObject> findHistoricalIssueForPage(Long orgId,
			Integer page, Integer rows, String sidx, String sord);

	public void deleteHistoricalIssueByIssueIdAndOrgId(Long issueId, Long orgId);

	public void deleteHistoricalIssueByIssueId(Long issueId);

	public FourTeamsHistoricalIssue findHistoricalIssueByIssueIdAndOrgId(Long issueId,
			Long orgId);
}
