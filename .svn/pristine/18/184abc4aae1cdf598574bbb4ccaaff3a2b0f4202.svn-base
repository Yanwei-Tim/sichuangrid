package com.tianque.fourTeams.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.dao.FourTeamsHistoricalIssueDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsHistoricalIssue;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

@Repository("fourTeamsHistoricalIssueDao")
public class FourTeamsHistoricalIssueDaoImpl extends AbstractBaseDao implements
		FourTeamsHistoricalIssueDao {

	@Override
	public FourTeamsHistoricalIssue addHistoricalIssue(FourTeamsHistoricalIssue historicalIssue) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"historicalIssue.addHistoricalIssue", historicalIssue);
		return getSimpleHistoricalIssueById(id);
	}

	@Override
	public FourTeamsHistoricalIssue getSimpleHistoricalIssueById(Long id) {
		return (FourTeamsHistoricalIssue) getSqlMapClientTemplate().queryForObject(
				"historicalIssue.findHistoricalIssueById", id);
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findHistoricalIssueForPage(Long orgId,
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"historicalIssue.findHistoricalIssueForPageCount", map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<FourTeamsIssueViewObject> list = getSqlMapClientTemplate().queryForList(
				"historicalIssue.findHistoricalIssueForPage", map,
				(page - 1) * rows, rows);
		PageInfo<FourTeamsIssueViewObject> pageInfo = new PageInfo<FourTeamsIssueViewObject>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public void deleteHistoricalIssueByIssueIdAndOrgId(Long issueId, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("orgId", orgId);
		getSqlMapClientTemplate().delete(
				"historicalIssue.deleteHistoricalIssueByIssueIdAndOrgId", map);
	}

	@Override
	public FourTeamsHistoricalIssue findHistoricalIssueByIssueIdAndOrgId(Long issueId,
			Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("orgId", orgId);
		return (FourTeamsHistoricalIssue) getSqlMapClientTemplate().queryForObject(
				"historicalIssue.findHistoricalIssueByIssueIdAndOrgId", map);
	}

	@Override
	public void deleteHistoricalIssueByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete(
				"historicalIssue.deleteHistoricalIssueByIssueId", issueId);
	}
}
