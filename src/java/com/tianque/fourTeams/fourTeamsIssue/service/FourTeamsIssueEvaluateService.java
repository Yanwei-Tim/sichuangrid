package com.tianque.fourTeams.fourTeamsIssue.service;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueEvaluate;

public interface FourTeamsIssueEvaluateService {

	/**
	 * 保存事件的评价
	 * 
	 * @param issueEvaluate
	 *            事件评价
	 * @return
	 */
	public FourTeamsIssueEvaluate evaluate(FourTeamsIssueEvaluate issueEvaluate);

	/**
	 * 获取事件的评价
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	public FourTeamsIssueEvaluate getIssueEvaluateByIssueId(Long issueId);
}
