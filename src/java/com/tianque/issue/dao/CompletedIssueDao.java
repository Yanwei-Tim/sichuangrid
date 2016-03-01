package com.tianque.issue.dao;

import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.CompletedIssue;

/**
 * 已办结 事件
 * 
 * @author Evan
 * 
 */
public interface CompletedIssueDao {

	/**
	 * 添加 已办结 事件
	 * 
	 * @param completedIssue
	 * @return
	 */
	CompletedIssue addCompletedIssue(CompletedIssue completedIssue);

	PageInfo<CompletedIssue> findCompletedIssueByPage(Long orgId,
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Long issueType);

	/**
	 * 
	 * @param param
	 * @return
	 */
	CompletedIssue buildCompletedIssueByIssueId(Map<String, Object> param);

	/**
	 * 分页查找 已办结 事件
	 * 
	 * @param org
	 * 
	 * @param keyId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param leaderView
	 * @param functionalOrgType
	 * @param orgLevel
	 * @param issueType
	 * @return
	 */
	PageInfo<CompletedIssue> findCompletedIssueByPage(Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long orgLevel, Long functionalOrgType, String leaderView,
			Long sourceType, Long issueType, String statusType,
			int isCurrentOrg, int orgCodeFindLevel, Long searchOrgId,
			String searchOrgCode);

	/**
	 * 
	 * @param topIssue
	 * @return
	 */
	boolean topIssue(Map<String, Object> topIssue);

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
	 * 从已办结中获取待评分的事件
	 * 
	 * @param org
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
	 * @param isCurrentOrg
	 * @return
	 */
	public PageInfo<CompletedIssue> findGradeDelayIssueByPage(Organization org,
			Integer page, Integer rows, String sidx, String sord,
			Long orgLevel, Long functionalOrgType, String leaderView,
			Long sourceType, Long issueType, String evaluationType,
			int isCurrentOrg, int orgCodeFindLevel, Long searchOrgId,
			String searchOrgCode);

	/***
	 * 更新事件转入三本台帐后 事件的状态
	 * 
	 * @return
	 */
	public void updateEventStateByIssueId(Long issueId);
}
