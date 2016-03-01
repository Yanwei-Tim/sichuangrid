package com.tianque.baseInfo.actualCompany.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchActualCompanyVo;

@Repository("searchActualCompanyDao")
public class SearchActualCompanyDaoImpl extends AbstractBaseDao implements
		SearchActualCompanyDao {
	/**
	 * 查询、检索
	 */
	@Override
	public PageInfo<ActualCompany> findActualCompanyByQueryCondition(
			SearchActualCompanyVo searchActualCompanyVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		searchActualCompanyVo.setSortField(sidx);
		searchActualCompanyVo.setOrder(sord);
		List<ActualCompany> list = getSqlMapClientTemplate().queryForList(
				"searchActualCompany.search", searchActualCompanyVo,
				(pageNum - 1) * pageSize, pageSize);
		if (searchActualCompanyVo.getIsSecurityChief()) {
			standardizationList(list);
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchActualCompany.countSearch", searchActualCompanyVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	/**
	 * 查找数据用于导出
	 */
	@Override
	public List<ActualCompany> searchActualCompanysForExport(
			SearchActualCompanyVo searchActualCompanyVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		if (searchActualCompanyVo != null) {
			searchActualCompanyVo.setSortField(sidx);
			searchActualCompanyVo.setOrder(sord);
		}
		if (pageNum == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchActualCompany.search", searchActualCompanyVo);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchActualCompany.search", searchActualCompanyVo,
					(pageNum - 1) * pageSize, pageSize);
		}
	}

	private PageInfo<ActualCompany> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<ActualCompany> pageInfo = new PageInfo<ActualCompany>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private List<ActualCompany> standardizationList(List<ActualCompany> list) {
		for (int j = 0; j < list.size();) {
			if (list.get(j).getSecurityChief() == null
					|| list.get(j).getSecurityChief().trim().equals("")) {
				list.remove(j);
				continue;
			}
			j++;
		}
		return list;
	}

	@Override
	public List<ActualCompany> findIssueRelateObjectListByQueryCondition(
			SearchActualCompanyVo searchActualCompanyVo, String sidx,
			String sord) {
		searchActualCompanyVo.setSortField(sidx == null ? "id" : sidx);
		searchActualCompanyVo.setOrder(sidx == null ? "desc" : sidx);
		List<ActualCompany> list = getSqlMapClientTemplate().queryForList(
				"searchActualCompany.search", searchActualCompanyVo, 0, 10);
		return list;
	}

	@Override
	public Integer getCount(SearchActualCompanyVo searchVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchActualCompany.countSearch", searchVo);
	}
}
