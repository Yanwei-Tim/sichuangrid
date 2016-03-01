package com.tianque.issue.dao;

import com.tianque.issue.domain.TopIssue;

public interface TopIssueDao {

	/**
	 * 新增事件置顶
	 * 
	 * @param topIssue
	 *        事件置顶
	 * @return
	 */
	public TopIssue addTopIssue(TopIssue topIssue);

	/**
	 * 获取事件置顶
	 * 
	 * @param topIssue
	 *        事件置顶
	 * @return
	 */
	public TopIssue getTopIssue(TopIssue topIssue);

	/**
	 * 获取事件置顶
	 * 
	 * @param id
	 *        事件置顶id
	 * @return
	 */
	public TopIssue getTopIssueById(Long id);

	/**
	 * 修改事件置顶
	 * 
	 * @param topIssue
	 *        事件置顶
	 * @return
	 */
	public TopIssue updateTopIssue(TopIssue topIssue);

	/**
	 * 删除事件置顶
	 * 
	 * @param id
	 *        事件置顶id
	 * @return
	 */
	public boolean deleteTopIssueById(Long id);

	/**
	 * 删除事件置顶
	 * 
	 * @param issueId
	 *        事件id
	 * @param orgId
	 *        部门id
	 * @param issueTag
	 *        事件模块
	 * @return
	 */
	public boolean deleteTopIssue(Long issueId, Long orgId, int issueTag);
}
