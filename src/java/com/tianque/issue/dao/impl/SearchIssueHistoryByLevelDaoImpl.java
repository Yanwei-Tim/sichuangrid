package com.tianque.issue.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.constants.IssueTag;
import com.tianque.issue.dao.SearchIssueHistoryByLevelDao;
import com.tianque.issue.state.IssueSourceState;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueViewObject;

@Repository("searchIssueHistoryByLevelDao")
public class SearchIssueHistoryByLevelDaoImpl extends AbstractBaseDao implements
		SearchIssueHistoryByLevelDao {
	private int getJurisdictionsDoneHistory(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueHistoryByLevel.countJurisdictionsDoneHistory",
				searchIssueVo);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsDoneHistory(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setIssueTag(IssueTag.DONE_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsDoneHistory(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueHistoryByLevel.findJurisdictionsDoneHistory",
				searchIssueVo, (page - 1) * rows, rows));
		return result;
	}

	private int getJurisdictionsSubmitHistory(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueHistoryByLevel.countJurisdictionsSubmitHistory",
				searchIssueVo);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsSubmitHistory(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setSubmit(IssueSourceState.submit);
		searchIssueVo.setIssueTag(IssueTag.SUBMIT_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSubmitHistory(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueHistoryByLevel.findJurisdictionsSubmitHistory",
				searchIssueVo, (page - 1) * rows, rows));
		return result;
	}

	private int getJurisdictionsAssginHistoryCount(
			SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueHistoryByLevel.countJurisdictionsAssginHistory",
				searchIssueVo);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsAssginHistory(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setAssgin(IssueSourceState.assign);
		searchIssueVo.setIssueTag(IssueTag.ASSIGN_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsAssginHistoryCount(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueHistoryByLevel.findJurisdictionsAssginHistory",
				searchIssueVo, (page - 1) * rows, rows));
		return result;
	}

	private int getJurisdictionsSkipIssueHistory(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueHistoryByLevel.countJurisdictionsSkipIssueHistory",
				searchIssueVo);
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsSkipIssueHistory(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		// searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setIssueTag(IssueTag.SKIP_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSkipIssueHistory(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueHistoryByLevel.findJurisdictionsSkipIssueHistory",
				searchIssueVo, (page - 1) * rows, rows));
		return result;
	}

	private PageInfo<IssueViewObject> createIssueVoPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<IssueViewObject> result = new PageInfo<IssueViewObject>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}
}
