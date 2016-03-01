package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HistoricalIssueDao;
import com.tianque.issue.domain.HistoricalIssue;
import com.tianque.issue.vo.IssueViewObject;

@Repository("historicalIssueDao")
public class HistoricalIssueDaoImpl extends AbstractBaseDao implements HistoricalIssueDao {

	@Override
	public HistoricalIssue addHistoricalIssue(HistoricalIssue historicalIssue) {
		Long id = (Long) getSqlMapClientTemplate().insert("historicalIssue.addHistoricalIssue",
				historicalIssue);
		return getSimpleHistoricalIssueById(id);
	}

	@Override
	public HistoricalIssue getSimpleHistoricalIssueById(Long id) {
		return (HistoricalIssue) getSqlMapClientTemplate().queryForObject(
				"historicalIssue.findHistoricalIssueById", id);
	}

	@Override
	public PageInfo<IssueViewObject> findHistoricalIssueForPage(Long orgId, Integer page,
			Integer rows, String sidx, String sord) {
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
		List<IssueViewObject> list = getSqlMapClientTemplate().queryForList(
				"historicalIssue.findHistoricalIssueForPage", map, (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
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
		getSqlMapClientTemplate().delete("historicalIssue.deleteHistoricalIssueByIssueIdAndOrgId",
				map);
	}

	@Override
	public HistoricalIssue findHistoricalIssueByIssueIdAndOrgId(Long issueId, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("orgId", orgId);
		return (HistoricalIssue) getSqlMapClientTemplate().queryForObject(
				"historicalIssue.findHistoricalIssueByIssueIdAndOrgId", map);
	}

	@Override
	public void deleteHistoricalIssueByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete("historicalIssue.deleteHistoricalIssueByIssueId", issueId);
	}
}
