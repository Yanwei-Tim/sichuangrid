package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchCommandCenterIssueVoNew;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.vo.IssueViewObject;

public interface SearchIssueDao {

	public PageInfo<IssueViewObject> searchIssuesNew(SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 社情民意中心待办诉求高级搜索
	 * 
	 * @param searchIssueVo
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<IssueViewObject> searchCommandCenterIssuesNew(
			SearchCommandCenterIssueVoNew searchIssueVo, Integer page, Integer rows, String sidx,
			String sord);

	public PageInfo<IssueViewObject> searchHistoricalIssueNew(SearchIssueVoNew searchIssueVo,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<IssueViewObject> searchPublicltyCassIssueNew(SearchIssueVoNew searchIssueVo,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<IssueViewObject> searchJurisdictionsIssues(SearchIssueVoNew searchIssueVo,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<IssueViewObject> searchDoneIssues(SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord);

	public PageInfo<IssueViewObject> searchCommandCenterDoneIssues(
			SearchCommandCenterIssueVoNew searchIssueVo, Integer page, Integer rows, String sidx,
			String sord);

	public PageInfo<IssueViewObject> searchJurisdictionsDoneIssues(SearchIssueVoNew searchIssueVo,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<IssueViewObject> searchMyCompletedIssues(SearchIssueVoNew searchIssueVo,
			Integer page, Integer rows, String sidx, String sord);
}
