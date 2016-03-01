package com.tianque.fourTeams.fourTeamsIssue.event.listener;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.event.FourTeamsIssueChangeEvent;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;

/***
 * 事件监听器
 */
public interface FourTeamsIssueChangeListener {
	/**
	 * 事件进入时
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            事件处理步骤
	 */

	void onEntry(FourTeamsIssueNew issue, FourTeamsIssueStep step);

	/**
	 * 事件删除之前
	 * 
	 * @param issue
	 *            事件
	 */
	void beforeRemove(FourTeamsIssueNew issue);

	/**
	 * 事件状态发生改变时
	 * 
	 * @param issue
	 *            事件
	 * @param steps
	 *            事件处理步骤组
	 * @param event
	 */
	void onChanged(FourTeamsIssueNew issue, FourTeamsIssueStepGroup steps, FourTeamsIssueChangeEvent event);

	/**
	 * 事件结案时
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            事件处理步骤
	 * @param event
	 */
	void onComplete(FourTeamsIssueNew issue, FourTeamsIssueStep step, FourTeamsIssueChangeEvent event);
}
