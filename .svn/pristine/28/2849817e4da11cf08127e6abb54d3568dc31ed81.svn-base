package com.tianque.issue.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.constants.IssueTag;
import com.tianque.issue.dao.SearchIssueByLevelDao;
import com.tianque.issue.domain.CompletedIssue;
import com.tianque.issue.state.IssueSourceState;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueViewObject;

@Repository("searchIssueByLevelDao")
public class SearchIssueByLevelDaoImpl extends AbstractBaseDao implements
		SearchIssueByLevelDao {

	private PageInfo<IssueViewObject> createIssueVoPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<IssueViewObject> result = new PageInfo<IssueViewObject>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	private PageInfo<CompletedIssue> createIssueVoPageInfoInstanceOfCompete(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<CompletedIssue> result = new PageInfo<CompletedIssue>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsNeedDo(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		// TODO Auto-generated method stub
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setIssueTag(IssueTag.NEEDDO_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsNeedDoCount(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueByLevel.findJurisdictionsNeedDo", searchIssueVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionAuditDelay(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setSuperviseLevel(IssueState.RED_CARD_SUPERVISE);
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsAuditDelayCount(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueByLevel.findJurisdictionAuditDelay", searchIssueVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsSkipIssue(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		// searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setIssueTag(IssueTag.SKIP_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSkipIssue(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueByLevel.findJurisdictionsSkipIssue", searchIssueVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsFollow(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);// 事件流程表中办理中的状态，值为500
		searchIssueVo.setVerification(IssueState.VERIFICATION);// 事件表中已验证状态，值为300
		searchIssueVo.setIssueCompleteCode(IssueState.ISSUECOMPLETE_CODE);// 事件流程表中已验证的状态，值为1000
		searchIssueVo.setIssueTag(IssueTag.DONE_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsFollow(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueByLevel.findJurisdictionsFollow", searchIssueVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsDone(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setIssueTag(IssueTag.DONE_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsDone(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueByLevel.findJurisdictionsDone", searchIssueVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<CompletedIssue> findJurisdictionsCompleted(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(IssueState.ISSUECOMPLETE_CODE);
		searchIssueVo.setIssueTag(IssueTag.COMPLETED_ISSUE);
		PageInfo<CompletedIssue> result = createIssueVoPageInfoInstanceOfCompete(
				getJurisdictionsCompleted(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueByLevel.findJurisdictionsCompleted", searchIssueVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsPublicltyCassDone(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(IssueState.ISSUECOMPLETE_CODE);
		searchIssueVo.setIssueTag(IssueTag.COMPLETED_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsPublicltyCassDone(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueByLevel.findJurisdictionsPublicltyCassDone",
				searchIssueVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<IssueViewObject> findTimeOutIssue(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setSuperviseLevel(IssueState.YELLOW_CARD_SUPERVISE);
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setVerification(IssueState.ISSUEVERIFICATION_CODE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getCountTimeOutIssue(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueByLevel.findTimeOutIssue", searchIssueVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<CompletedIssue> findJurisdictionsGradeDelay(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(IssueState.ISSUECOMPLETE_CODE);
		searchIssueVo.setIssueTag(IssueTag.COMPLETED_ISSUE);
		PageInfo<CompletedIssue> result = createIssueVoPageInfoInstanceOfCompete(
				getJurisdictionsGradeDelay(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueByLevel.findJurisdictionsGradeDelay",
				searchIssueVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsVerification(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(IssueState.ISSUEVERIFICATION_CODE);
		searchIssueVo.setIssueTag(IssueTag.COMPLETED_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsVerification(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueByLevel.findJurisdictionsVerification",
				searchIssueVo, (page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsSubmit(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setSubmit(IssueSourceState.submit);
		searchIssueVo.setIssueTag(IssueTag.SUBMIT_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsSubmit(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueByLevel.findJurisdictionsSubmit", searchIssueVo,
				(page - 1) * rows, rows));
		return result;
	}

	@Override
	public PageInfo<IssueViewObject> findJurisdictionsAssgin(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		searchIssueVo.setCompleteCode(IssueState.STEPCOMPLETE_CODE);
		searchIssueVo.setAssgin(IssueSourceState.assign);
		searchIssueVo.setIssueTag(IssueTag.ASSIGN_ISSUE);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(
				getJurisdictionsAssginCount(searchIssueVo), rows, page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"searchIssueByLevel.findJurisdictionsAssgin", searchIssueVo,
				(page - 1) * rows, rows));
		return result;
	}

	private int getJurisdictionsNeedDoCount(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueByLevel.countJurisdictionsNeedDo", searchIssueVo);
	}

	private int getJurisdictionsAuditDelayCount(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueByLevel.countJurisdictionsAuditDelay",
				searchIssueVo);
	}

	private int getJurisdictionsSkipIssue(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchIssueByLevel.countJurisdictionsSkipIssue",
						searchIssueVo);
	}

	private int getJurisdictionsFollow(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueByLevel.countJurisdictionsFollow", searchIssueVo);
	}

	private int getJurisdictionsDone(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueByLevel.countJurisdictionsDone", searchIssueVo);
	}

	private int getJurisdictionsCompleted(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchIssueByLevel.countJurisdictionsCompleted",
						searchIssueVo);
	}

	private int getJurisdictionsPublicltyCassDone(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueByLevel.countJurisdictionsPublicltyCassDone",
				searchIssueVo);
	}

	private int getCountTimeOutIssue(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueByLevel.countTimeOutIssue", searchIssueVo);
	}

	private int getJurisdictionsGradeDelay(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueByLevel.countJurisdictionsGradeDelay",
				searchIssueVo);
	}

	private int getJurisdictionsVerification(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueByLevel.countJurisdictionsVerification",
				searchIssueVo);
	}

	private int getJurisdictionsSubmit(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueByLevel.countJurisdictionsSubmit", searchIssueVo);
	}

	private int getJurisdictionsAssginCount(SearchIssueVoNew searchIssueVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueByLevel.countJurisdictionsAssgin", searchIssueVo);
	}
}
