package com.tianque.fourTeams.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.dao.FourTeamsPublicltyCassDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsPublicltyCass;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;

@Repository("fourTeamsPublicltyCassDao")
public class FourTeamsPublicltyCassDaoImpl extends AbstractBaseDao implements
		FourTeamsPublicltyCassDao {

	@Override
	public FourTeamsPublicltyCass addPublicltyCass(
			FourTeamsPublicltyCass publicltyCass) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"publicltyCass.addPublicltyCass", publicltyCass);
		return getSimplePublicltyCassById(id);
	}

	@Override
	public PageInfo<FourTeamsIssueViewObject> findPublicltyCassForPage(
			Long orgId, Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"publicltyCass.findPublicltyCassForPageCount", map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<FourTeamsIssueViewObject> list = getSqlMapClientTemplate()
				.queryForList("publicltyCass.findPublicltyCassForPage", map,
						(page - 1) * rows, rows);
		PageInfo<FourTeamsIssueViewObject> pageInfo = new PageInfo<FourTeamsIssueViewObject>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public FourTeamsPublicltyCass getSimplePublicltyCassById(Long id) {
		return (FourTeamsPublicltyCass) getSqlMapClientTemplate()
				.queryForObject("publicltyCass.findPublicltyCassById", id);
	}

	@Override
	public void deletePublicltyCassByIssueIdAndOrgId(Long issueId, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("orgId", orgId);
		getSqlMapClientTemplate().delete(
				"publicltyCass.deletePublicltyCassByIssueIdAndOrgId", map);
	}

	@Override
	public void deletePublicltyCassByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete(
				"publicltyCass.deletePublicltyCassByIssueId", issueId);
	}

	@Override
	public List<FourTeamsPublicltyCass> findPublicltyCassByIssueIdAndOrgId(
			Long issueId, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("orgId", orgId);
		List<FourTeamsPublicltyCass> publicltyCass = null;
		publicltyCass = getSqlMapClientTemplate().queryForList(
				"publicltyCass.findPublicltyCassByIssueIdAndOrgId", map);
		return publicltyCass;

	}

}
