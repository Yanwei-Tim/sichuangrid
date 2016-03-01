package com.tianque.issue.service;

import com.tianque.issue.domain.IssueEvaluate;

public interface IssueEvaluateService {

	/**
	 * 保存事件的评价
	 * 
	 * @param issueEvaluate
	 *            事件评价
	 * @return
	 */
	public IssueEvaluate evaluate(IssueEvaluate issueEvaluate);

	/**
	 * 保存评分操作事件的评价
	 * 
	 * @param issueEvaluate
	 *            事件评价
	 * @return
	 */
	public IssueEvaluate evaluateForGrade(IssueEvaluate issueEvaluate);

	/**
	 * 获取事件的评价
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	public IssueEvaluate getIssueEvaluateByIssueId(Long issueId);

	/**
	 * 获取待评分事件的评价
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	public IssueEvaluate getIssueEvaluateByIssueIdForGrade(Long issueId);
}
