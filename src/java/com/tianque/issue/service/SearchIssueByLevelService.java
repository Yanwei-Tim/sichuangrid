package com.tianque.issue.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.domain.CompletedIssue;
import com.tianque.issue.vo.IssueViewObject;

public interface SearchIssueByLevelService {

	/**
	 * 代办
	 * 
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsNeedDo(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 待审核
	 * 
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueViewObject> findJurisdictionAuditDelay(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 待跟进
	 * 
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsFollow(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 已办
	 * 
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsDone(String statusType,
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 已办结
	 * 
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<CompletedIssue> findJurisdictionsCompleted(Long keyId,
			Long issueType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 从solr查询已办结
	 * 
	 * @param keyId
	 * @param issueType
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsCompletedFromSolr(Long keyId,
			Long issueType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 宣传事例（已办结中抽取）
	 * 
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsPublicltyCassDone(
			Long issueType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 超时事件（红黄牌督办的事件）
	 * 
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findTimeOutIssue(SearchIssueVoNew searchIssueVo,
			Integer superviseType, Integer page, Integer rows, String sidx,
			String sord);

	/**
	 * 待评分
	 * 
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<CompletedIssue> findJurisdictionsGradeDelay(Long keyId,
			Long issueType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 从solr查询待评分
	 * 
	 * @param keyId
	 * @param issueType
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsGradeDelayFromSolr(Long keyId,
			Long issueType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 待验证
	 * 
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsVerification(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 上报
	 * 
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsSubmit(String statusType,
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 上级交办
	 * 
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<IssueViewObject> findJurisdictionsAssgin(String statusType,
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord);

	/**
	 * 越级
	 * 
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueViewObject> findJurisdictionsSkipIssue(
			String statusType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord);

}
