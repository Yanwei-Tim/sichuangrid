package com.tianque.issue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.dao.SearchIssueHistoryByLevelDao;
import com.tianque.issue.service.SearchIssueHistoryByLevelService;
import com.tianque.issue.vo.IssueViewObject;

@Transactional
@Repository("searchIssueHistoryByLevelService")
public class SearchIssueHistoryByLevelServiceImpl implements
		SearchIssueHistoryByLevelService {
	@Autowired
	private SearchIssueHistoryByLevelDao searchIssueHistoryByLevelDao;

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsDoneHistory(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		return searchIssueHistoryByLevelDao.findJurisdictionsDoneHistory(
				searchIssueVo, page, rows);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsAssginHistory(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		return searchIssueHistoryByLevelDao.findJurisdictionsAssginHistory(
				searchIssueVo, page, rows);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsSkipIssueHistory(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		return searchIssueHistoryByLevelDao.findJurisdictionsSkipIssueHistory(
				searchIssueVo, page, rows);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsSubmitHistory(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		return searchIssueHistoryByLevelDao.findJurisdictionsSubmitHistory(
				searchIssueVo, page, rows);
	}
}
