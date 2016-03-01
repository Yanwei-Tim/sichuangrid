package com.tianque.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.RegradedPointsDao;
import com.tianque.domain.RegradedPoints;
import com.tianque.state.RegradedType;

@Repository("regradedPointsDao")
public class RegradedPointsDaoImpl extends AbstractBaseDao implements
		RegradedPointsDao {

	@Override
	public RegradedPoints addRegradedPoints(RegradedPoints regradedPoints) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"regradedPoints.regradedPoints", regradedPoints);
		return getRegradedPointsById(id);
	}

	@Override
	public PageInfo<RegradedPoints> findRegradedPointsByOrgIdAndStatDate(
			Long orgId, String statDate, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		Map query = new HashMap();
		query.put("orgId", orgId);
		query.put("statDate", statDate);
		if (sortField != null) {
			query.put("sortField", sortField);
			query.put("order", order);
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"regradedPoints.countRegradedPoints", query);
		int pageCount = 0;
		if (pageSize != 0 && countNum > 0)
			pageCount = (countNum - 1) / pageSize + 1;
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<RegradedPoints> result = getSqlMapClientTemplate().queryForList(
				"regradedPoints.findRegradedPointsByOrgIdAndStatDate", query,
				(pageNum - 1) * pageSize, pageSize);
		PageInfo<RegradedPoints> pageInfo = new PageInfo<RegradedPoints>();
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(result);
		pageInfo.setTotalRowSize(countNum);
		return pageInfo;
	}

	@Override
	public PageInfo<RegradedPoints> findRegradedPointsByOrgIdAndStatDate(
			Long orgId, Date startDate, Date endDate, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		Map query = new HashMap();
		query.put("orgId", orgId);
		query.put("startDate", startDate);
		query.put("endDate", endDate);
		if (sortField != null) {
			query.put("sortField", sortField);
			query.put("order", order);
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"regradedPoints.countRegradedPointsByOrgIdAndIntervalDate",
				query);
		int pageCount = 0;
		if (pageSize != 0 && countNum > 0)
			pageCount = (countNum - 1) / pageSize + 1;
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<RegradedPoints> result = getSqlMapClientTemplate().queryForList(
				"regradedPoints.findRegradedPointsByOrgIdAndIntervalDate",
				query, (pageNum - 1) * pageSize, pageSize);
		PageInfo<RegradedPoints> pageInfo = new PageInfo<RegradedPoints>();
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(result);
		pageInfo.setTotalRowSize(countNum);
		return pageInfo;
	}

	@Override
	public RegradedPoints getRegradedPointsById(Long id) {
		return (RegradedPoints) getSqlMapClientTemplate().queryForObject(
				"regradedPoints.getRegradedPointsById", id);
	}

	@Override
	public List<RegradedPoints> queryRegradedPointsForListByIds(
			List<Long> idList) {
		return getSqlMapClientTemplate().queryForList(
				"regradedPoints.queryRegradedPointsForListByIds", idList);
	}

	@Override
	public void deleteRegradedPointByLogId(Long logId) {
		HashMap map = new HashMap();
		map.put("logId", logId);
		map.put("regradedbyperson", RegradedType.REGRADEDBYPERSON);
		getSqlMapClientTemplate().delete(
				"regradedPoints.deleteRegradedPointByLogId", map);
	}

	@Override
	public GridPage queryIssueGradeHistoryForPageResultByIssueId(Long keyId,
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyId", keyId);
		// map.put("regradedType", RegradedType.REGRADEDBYPERSON);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"regradedPoints.queryIssueGradeHistoryForPageResultByIssueIdCount",
						map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<RegradedPoints> list = getSqlMapClientTemplate().queryForList(
				"regradedPoints.queryIssueGradeHistoryForPageResultByIssueId",
				map, (page - 1) * rows, rows);
		PageInfo<RegradedPoints> pageInfo = new PageInfo<RegradedPoints>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return new GridPage(pageInfo);
	}

	@Override
	public GridPage queryIssueGradeHistoryForPageResultByHistoryIssueId(
			Long keyId, Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyId", keyId);
		// map.put("regradedType", RegradedType.REGRADEDBYPERSON);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"regradedPoints.queryIssueGradeHistoryForPageResultByHistoryIssueIdCount",
						map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<RegradedPoints> list = getSqlMapClientTemplate()
				.queryForList(
						"regradedPoints.queryIssueGradeHistoryForPageResultByHistoryIssueId",
						map, (page - 1) * rows, rows);
		PageInfo<RegradedPoints> pageInfo = new PageInfo<RegradedPoints>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return new GridPage(pageInfo);
	}

	@Override
	public String issueGradeIsVisabled(Long keyId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyId", keyId);
		map.put("sysDate", new Date());
		return (String) getSqlMapClientTemplate().queryForObject(
				"regradedPoints.issueGradeIsVisabled", map);
	}
}
