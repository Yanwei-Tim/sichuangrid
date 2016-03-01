package com.tianque.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchIssueDao;
import com.tianque.domain.vo.SearchCommandCenterIssueVoNew;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.vo.IssueViewObject;

@Repository("searchIssueDao")
public class SearchIssueDaoImpl extends AbstractBaseDao implements SearchIssueDao {

	@Override
	public PageInfo<IssueViewObject> searchIssuesNew(SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countIssues", searchIssueVo);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> issues = null;
		issues = getSqlMapClientTemplate().queryForList("searchIssueNew.searchIssues",
				searchIssueVo, (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> searchCommandCenterIssuesNew(
			SearchCommandCenterIssueVoNew searchIssueVo, Integer page, Integer rows, String sidx,
			String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countCommandCenterIssuesNew", searchIssueVo);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> issues = null;
		issues = getSqlMapClientTemplate().queryForList(
				"searchIssueNew.searchCommandCenterIssuesNew", searchIssueVo, (page - 1) * rows,
				rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> searchJurisdictionsIssues(SearchIssueVoNew searchIssueVo,
			Integer page, Integer rows, String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countJurisdictionsIssues", searchIssueVo);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> issues = getSqlMapClientTemplate().queryForList(
				"searchIssueNew.searchJurisdictionsIssues", searchIssueVo, (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> searchDoneIssues(SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countDoneIssues", searchIssueVo);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> issues = getSqlMapClientTemplate().queryForList(
				"searchIssueNew.searchDoneIssues", searchIssueVo, (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> searchCommandCenterDoneIssues(
			SearchCommandCenterIssueVoNew searchIssueVo, Integer page, Integer rows, String sidx,
			String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countCommandCenterDoneIssues", searchIssueVo);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> issues = getSqlMapClientTemplate().queryForList(
				"searchIssueNew.searchCommandCenterDoneIssues", searchIssueVo, (page - 1) * rows,
				rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> searchJurisdictionsDoneIssues(SearchIssueVoNew searchIssueVo,
			Integer page, Integer rows, String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countJurisdictionsDoneIssues", searchIssueVo);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> issues = getSqlMapClientTemplate().queryForList(
				"searchIssueNew.searchJurisdictionsDoneIssues", searchIssueVo, (page - 1) * rows,
				rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> searchMyCompletedIssues(SearchIssueVoNew searchIssueVo,
			Integer page, Integer rows, String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countMyCompletedIssues", searchIssueVo);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> issues = getSqlMapClientTemplate().queryForList(
				"searchIssueNew.searchMyCompletedIssues", searchIssueVo, (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> searchHistoricalIssueNew(SearchIssueVoNew searchIssueVo,
			Integer page, Integer rows, String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countHistoricalIssues", searchIssueVo);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> issues = null;
		issues = getSqlMapClientTemplate().queryForList("searchIssueNew.searchHistoricalIssues",
				searchIssueVo, (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> searchPublicltyCassIssueNew(SearchIssueVoNew searchIssueVo,
			Integer page, Integer rows, String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countPublicltyCassIssues", searchIssueVo);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> issues = null;
		issues = getSqlMapClientTemplate().queryForList("searchIssueNew.searchPublicltyCassIssues",
				searchIssueVo, (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

}
