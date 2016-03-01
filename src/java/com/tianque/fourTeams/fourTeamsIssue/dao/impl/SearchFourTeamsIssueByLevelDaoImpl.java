package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.fourTeams.fourTeamsIssue.constants.FourTeamsIssueTag;
import com.tianque.fourTeams.fourTeamsIssue.dao.SearchFourTeamsIssueByLevelDao;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueSourceState;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;
import com.tianque.issue.constants.IssueConstants;

@Repository("fourTeamsSearchIssueByLevelDao")
public class SearchFourTeamsIssueByLevelDaoImpl extends AbstractBaseDao
		implements SearchFourTeamsIssueByLevelDao {

	private PageInfo<FourTeamsIssueViewObject> createIssueVoPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<FourTeamsIssueViewObject> result = new PageInfo<FourTeamsIssueViewObject>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsNeedDo(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(FourTeamsIssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setIssueTag(FourTeamsIssueTag.NEEDDO_ISSUE);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsNeedDoCount(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchFourTeamsIssueByLevel.findJurisdictionsNeedDo",
				searchIssueVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionAuditDelay(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsAuditDelayCount(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchFourTeamsIssueByLevel.findJurisdictionAuditDelay",
				searchIssueVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsSkipIssue(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		// searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setIssueTag(FourTeamsIssueTag.SKIP_ISSUE);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSkipIssue(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchFourTeamsIssueByLevel.findJurisdictionsSkipIssue",
				searchIssueVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsDone(
			String statusType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows) {
		searchIssueVo.setCompleteCode(FourTeamsIssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setIssueTag(FourTeamsIssueTag.DONE_ISSUE);
		String tablePrefix = getTablePrefix(statusType);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsDone(tablePrefix, searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				tablePrefix + ".findJurisdictionsDone", searchIssueVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsCompleted(
			String statusType, SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows) {
		searchIssueVo.setCompleteCode(FourTeamsIssueState.ISSUECOMPLETE_CODE);
		searchIssueVo.setIssueTag(FourTeamsIssueTag.COMPLETED_ISSUE);
		String tablePrefix = getTablePrefix(statusType);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsCompleted(tablePrefix, searchIssueVo), rows,
				page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				tablePrefix + ".findJurisdictionsCompleted", searchIssueVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsSubmit(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(FourTeamsIssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setSubmit(FourTeamsIssueSourceState.submit);
		searchIssueVo.setIssueTag(FourTeamsIssueTag.SUBMIT_ISSUE);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSubmit(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchFourTeamsIssueByLevel.findJurisdictionsSubmit",
				searchIssueVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findJurisdictionsAssgin(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(FourTeamsIssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setAssgin(FourTeamsIssueSourceState.assign);
		searchIssueVo.setIssueTag(FourTeamsIssueTag.ASSIGN_ISSUE);
		PageInfo<FourTeamsIssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsAssginCount(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchFourTeamsIssueByLevel.findJurisdictionsAssgin",
				searchIssueVo, (page - 1) * rows, rows));
		return result;
	}

	private int getJurisdictionsNeedDoCount(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchFourTeamsIssueByLevel.countJurisdictionsNeedDo",
				searchIssueVo);
	}

	private int getJurisdictionsAuditDelayCount(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchFourTeamsIssueByLevel.countJurisdictionsAuditDelay",
				searchIssueVo);
	}

	private int getJurisdictionsSkipIssue(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchFourTeamsIssueByLevel.countJurisdictionsSkipIssue",
				searchIssueVo);
	}

	private int getJurisdictionsDone(String tablePrefix,
			SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				tablePrefix + ".countJurisdictionsDone", searchIssueVo);
	}

	private int getJurisdictionsCompleted(String tablePrefix,
			SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				tablePrefix + ".countJurisdictionsCompleted", searchIssueVo);
	}

	private int getJurisdictionsSubmit(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchFourTeamsIssueByLevel.countJurisdictionsSubmit",
				searchIssueVo);
	}

	private int getJurisdictionsAssginCount(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchFourTeamsIssueByLevel.countJurisdictionsAssgin",
				searchIssueVo);
	}

	/** 获取查询表的前缀 */
	private String getTablePrefix(String statusType) {
		if (IssueConstants.STATUSTYPE_HISTORY.equals(statusType)) {
			return "searchFourTeamsIssueByLevel_history";
		}
		return "searchFourTeamsIssueByLevel";
	}
}
