package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PublicltyCassDao;
import com.tianque.issue.domain.PublicltyCass;
import com.tianque.issue.vo.IssueViewObject;

@Repository("publicltyCassDao")
public class PublicltyCassDaoImpl extends AbstractBaseDao implements
		PublicltyCassDao {

	@Override
	public PublicltyCass addPublicltyCass(PublicltyCass publicltyCass) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"publicltyCass.addPublicltyCass", publicltyCass);
		return getSimplePublicltyCassById(id);
	}

	@Override
	public PageInfo<IssueViewObject> findPublicltyCassForPage(Long orgId,
			Integer page, Integer rows, String sidx, String sord) {
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
		List<IssueViewObject> list = getSqlMapClientTemplate().queryForList(
				"publicltyCass.findPublicltyCassForPage", map,
				(page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PublicltyCass getSimplePublicltyCassById(Long id) {
		return (PublicltyCass) getSqlMapClientTemplate().queryForObject(
				"publicltyCass.findPublicltyCassById", id);
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
	public List<PublicltyCass> findPublicltyCassByIssueIdAndOrgId(Long issueId,
			Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("orgId", orgId);
		List<PublicltyCass> publicltyCass = null;
		publicltyCass = getSqlMapClientTemplate().queryForList(
				"publicltyCass.findPublicltyCassByIssueIdAndOrgId", map);
		return publicltyCass;

	}

}
