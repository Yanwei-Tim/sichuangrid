package com.tianque.fourTeams.fourTeamsIssue.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

public interface SearchFourTeamsIssueByLevelService {

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
	PageInfo<FourTeamsIssueViewObject> findJurisdictionsNeedDo(
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
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionAuditDelay(
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
	PageInfo<FourTeamsIssueViewObject> findJurisdictionsDone(String statusType,
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
	PageInfo<FourTeamsIssueViewObject> findJurisdictionsCompleted(
			String statusType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord);

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
	PageInfo<FourTeamsIssueViewObject> findJurisdictionsSubmit(
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
	PageInfo<FourTeamsIssueViewObject> findJurisdictionsAssgin(
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
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsSkipIssue(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows,
			String sidx, String sord);

}
