package com.tianque.issue.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.dao.IssueExtractionDao;

@Repository("issueExtractionDao")
public class IssueExtractionDaoImpl extends AbstractBaseDao implements
		IssueExtractionDao {

	@Override
	public List<Long> queryCompletedHistoryIssuesIds(Date date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endDate", date);
		return getSqlMapClientTemplate().queryForList(
				"issueExtraction.queryCompletedHistoryIssuesIds", map);
	}

	@Override
	public Long migrationIssuesDatas(List<Long> issueIds) {
		return (Long) getSqlMapClientTemplate().insert(
				"issueExtraction.migrationIssuesDatas", issueIds);
	}

	@Override
	public Long migrationIssueStepsDatas(List<Long> issueIds) {
		return (Long) getSqlMapClientTemplate().insert(
				"issueExtraction.migrationIssueStepsDatas", issueIds);
	}

	@Override
	public Long migrationIssueLogsDatas(List<Long> issueIds) {
		return (Long) getSqlMapClientTemplate().insert(
				"issueExtraction.migrationIssueLogsDatas", issueIds);
	}

	@Override
	public Long migrationIssueStepsGroupDatas(List<Long> issueIds) {
		return (Long) getSqlMapClientTemplate().insert(
				"issueExtraction.migrationIssueStepsGroupDatas", issueIds);
	}

	@Override
	public Long migrationCompletedissueDatas(List<Long> issueIds) {
		return (Long) getSqlMapClientTemplate().insert(
				"issueExtraction.migrationCompletedissueDatas", issueIds);
	}

	@Override
	public int deleteIssuesDatasByIdList(List<Long> issueIds) {
		return getSqlMapClientTemplate().delete(
				"issueExtraction.deleteIssuesDatasByIdList", issueIds);
	}

	@Override
	public int deleteIssueStepsDatasByIdList(List<Long> issueIds) {
		return getSqlMapClientTemplate().delete(
				"issueExtraction.deleteIssueStepsDatasByIdList", issueIds);
	}

	@Override
	public int deleteIssueLogsDatasByIdList(List<Long> issueIds) {
		return getSqlMapClientTemplate().delete(
				"issueExtraction.deleteIssueLogsDatasByIdList", issueIds);
	}

	@Override
	public int deleteIssueStepsGroupDatasByIdList(List<Long> issueIds) {
		return getSqlMapClientTemplate().delete(
				"issueExtraction.deleteIssueStepsGroupDatasByIdList", issueIds);
	}

	@Override
	public int deleteCompletedissueDatasByIdList(List<Long> issueIds) {
		return getSqlMapClientTemplate().delete(
				"issueExtraction.deleteCompletedissueDatasByIdList", issueIds);
	}

	@Override
	public int updateMarkCompletedissueDatas(List<Long> issueIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("moveMark", IssueConstants.MOVEMARK_OK);
		map.put("issueIds", issueIds);
		return getSqlMapClientTemplate().update(
				"issueExtraction.updateMarkCompletedissueDatas", map);
	}
}
