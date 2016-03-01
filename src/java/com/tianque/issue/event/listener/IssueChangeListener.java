package com.tianque.issue.event.listener;

import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.event.IssueChangeEvent;
import com.tianque.issue.state.IssueStepGroup;

/***
 * 事件监听器
 */
public interface IssueChangeListener {
	/**
	 * 事件进入时
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            事件处理步骤
	 */

	void onEntry(IssueNew issue, IssueStep step);

	/**
	 * 事件删除之前
	 * 
	 * @param issue
	 *            事件
	 */
	void beforeRemove(IssueNew issue);

	/**
	 * 事件状态发生改变时
	 * 
	 * @param issue
	 *            事件
	 * @param steps
	 *            事件处理步骤组
	 * @param event
	 */
	void onChanged(IssueNew issue, IssueStepGroup steps, IssueChangeEvent event);

	/**
	 * 事件结案时
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            事件处理步骤
	 * @param event
	 */
	void onComplete(IssueNew issue, IssueStep step, IssueChangeEvent event);

	/**
	 * 事件验证时
	 * 
	 * @param issue
	 *            事件
	 * @param step
	 *            事件处理步骤
	 * @param event
	 */
	void onVerification(IssueNew issue, IssueStep step, IssueChangeEvent event);
}
