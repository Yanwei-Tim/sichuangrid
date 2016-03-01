package com.tianque.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchServiceDaoNew;
import com.tianque.domain.vo.SearchIssueVo;
import com.tianque.issue.vo.IssueViewObject;

@Repository("searchServiceDaoNew")
public class SearchServiceDaoImplNew extends AbstractBaseDao implements SearchServiceDaoNew {

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<IssueViewObject> searchServicesByOrgInternalCode(
			SearchIssueVo searchServicesVo, Integer page, Integer rows, String sidx, String sord) {
		if (sidx != null) {
			searchServicesVo.setSortField(sidx);
			searchServicesVo.setOrder(sord);
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchServicesNew.countServices", searchServicesVo);
		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> issues = getSqlMapClientTemplate().queryForList(
				"searchServicesNew.searchServices", searchServicesVo, (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> searchServicesByOrgId(SearchIssueVo searchServicesVo,
			Integer page, Integer rows, String sidx, String sord) {
		if (sidx != null) {
			searchServicesVo.setSortField(sidx);
			searchServicesVo.setOrder(sord);
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchServicesNew.countServices", searchServicesVo);
		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;

		List<IssueViewObject> issues = getSqlMapClientTemplate().queryForList(
				"searchServicesNew.searchServices", searchServicesVo, (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	// 治安，安全隐患
	@Override
	public PageInfo<IssueViewObject> searchSecuritytroubleByOrgInternalCode(
			SearchIssueVo searchSecuritytroubleVo, Integer page, Integer rows, String sidx,
			String sord) {
		if (sidx != null) {
			searchSecuritytroubleVo.setSortField(sidx);
			searchSecuritytroubleVo.setOrder(sord);
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchServicesNew.countServices", searchSecuritytroubleVo);
		int pageCount = 0;
		if (rows != 0 && countNum > 0)
			pageCount = (countNum - 1) / rows + 1;
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> issues = getSqlMapClientTemplate().queryForList(
				"searchServicesNew.searchServices", searchSecuritytroubleVo, (page - 1) * rows,
				rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

}
