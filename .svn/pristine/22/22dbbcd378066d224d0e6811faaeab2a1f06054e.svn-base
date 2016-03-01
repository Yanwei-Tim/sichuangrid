package com.tianque.issue.service;

import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.issue.domain.CompletedIssue;
import com.tianque.issue.domain.TopIssue;

/**
 * @author Evan
 * @date 2014-010-15
 * 
 */
public interface CompletedIssueService {
	/**
	 * 添加 已办结 事件
	 * 
	 * @param completedIssue
	 * @return
	 */
	CompletedIssue addCompletedIssue(CompletedIssue completedIssue);

	/**
	 * 通过IssueId组建completedIssue
	 * 
	 * @param issueViewObject
	 * @return
	 */
	CompletedIssue buildCompletedIssueByIssueId(Map<String, Object> param);

	/**
	 * 分页查找 已办结 事件
	 * 
	 * @param keyId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param issueType
	 */
	PageInfo<CompletedIssue> findCompletedIssueByPage(Long keyId, Integer page,
			Integer rows, String sidx, String sord, Long orgLevel,
			Long functionalOrgType, String leaderView, Long sourceType,
			Long issueType, String statusType, Integer viewProcess,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	/**
	 * 
	 * @param topIssue
	 * @param i
	 * @return
	 */
	boolean topIssue(TopIssue topIssue, int state);

	/**
	 * 已办结事件，设置为宣传案例
	 * 
	 * @param issueId
	 * @param publicltycass
	 * @return
	 */
	public void publicltycassIssue(Long issueId, int publicltycass);

	/**
	 * 删除已办结事件
	 * 
	 * @param issueId
	 */
	public boolean removeCompletedIssueByIssueId(Long issueId);

	/**
	 * 根据事件id获取已完结事件对象
	 * 
	 * @param issueId
	 * @return
	 */
	public CompletedIssue getCompletedByIssueId(Long issueId);

	/***
	 * 从已办结中获取待评分的数据项
	 * 
	 * @param keyId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param orgLevel
	 * @param functionalOrgType
	 * @param leaderView
	 * @param sourceType
	 * @param issueType
	 * @param evaluationType
	 * @param viewProcess
	 * @return
	 */
	public PageInfo<CompletedIssue> findGradeDelayIssueByPage(Long keyId,
			Integer page, Integer rows, String sidx, String sord,
			Long orgLevel, Long functionalOrgType, String leaderView,
			Long sourceType, Long issueType, String evaluationType,
			Integer viewProcess, int orgCodeFindLevel, Long searchOrgId,
			String searchOrgCode);

	/***
	 * 更新事件转入三本台帐后 事件的状态
	 * 
	 * @return
	 */
	public void updateEventStateByIssueId(Long issueId);
}
