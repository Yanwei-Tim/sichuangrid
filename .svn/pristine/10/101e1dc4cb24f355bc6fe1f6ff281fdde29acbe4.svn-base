package com.tianque.fourTeams.fourTeamsIssue.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

public interface SearchFourTeamsIssueByLevelDao {

	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsNeedDo(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows);

	public PageInfo<FourTeamsIssueViewObject> findJurisdictionAuditDelay(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows);

	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsDone(
			String statusType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows);

	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsCompleted(
			String statusType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows);

	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsSubmit(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows);

	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsAssgin(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows);

	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsSkipIssue(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows);

}
