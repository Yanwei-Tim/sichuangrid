package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.dangerousGoodsPractitioner.dao.SearchDangerousGoodsPractitionerDao;
import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDangerousGoodsPractitioner;
import com.tianque.exception.base.BusinessValidationException;

@Repository("searchDangerousGoodsPractitionerDao")
public class SearchDangerousGoodsPractitionerDaoImpl extends AbstractBaseDao
		implements SearchDangerousGoodsPractitionerDao {

	@Override
	public PageInfo<DangerousGoodsPractitioner> searchDangerousGoodsPractitioner(
			SearchDangerousGoodsPractitioner queryPopulation, int pageNum,
			int pageSize, String sortField, String order) {
		if (queryPopulation == null) {
			return emptyPage(pageSize);
		}
		if (queryPopulation.getIsDeath() != null) {
			if (queryPopulation.getIsDeath() == -1) {
				queryPopulation.setIsDeath(null);
			}
		}
		queryPopulation.setSortField(sortField);
		queryPopulation.setOrder(order);
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchDangerousGoodsPractitioner.countSearchDangerousGoodsPractitioner",
						queryPopulation);
		int pageCount = 0;
		count(countNum, pageSize, pageCount, pageNum);
		List<DangerousGoodsPractitioner> list = getSqlMapClientTemplate()
				.queryForList(
						"searchDangerousGoodsPractitioner.searchDangerousGoodsPractitioners",
						queryPopulation, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(countNum, pageSize, pageNum, list);
	}

	private int count(int countNum, int pageSize, int pageCount, int pageNum) {
		if (countNum / pageSize == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		return pageNum;
	}

	private PageInfo<DangerousGoodsPractitioner> createPageInfo(int countNum,
			int pageSize, int pageNum, List list) {
		PageInfo<DangerousGoodsPractitioner> pageInfo = new PageInfo<DangerousGoodsPractitioner>();
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setResult(list);
		return pageInfo;
	}

	private PageInfo<DangerousGoodsPractitioner> emptyPage(int pageSize) {
		PageInfo<DangerousGoodsPractitioner> pageInfo = new PageInfo<DangerousGoodsPractitioner>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<DangerousGoodsPractitioner>());
		return pageInfo;
	}

	private PageInfo<DangerousGoodsPractitioner> emptyDangerousGoodsPractitionerPage(
			int pageSize) {
		PageInfo<DangerousGoodsPractitioner> pageInfo = new PageInfo<DangerousGoodsPractitioner>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<DangerousGoodsPractitioner>());
		return pageInfo;

	}

	@Override
	public List<DangerousGoodsPractitioner> searchDangerousGoodsPractitionerForExport(
			SearchDangerousGoodsPractitioner queryQopulation, Integer page,
			Integer rows, String sortField, String order) {
		if (null == queryQopulation) {
			return null;
		}
		queryQopulation.setSortField(sortField);
		queryQopulation.setOrder(order);
		if (page == null) {
			return getSqlMapClientTemplate()
					.queryForList(
							"searchDangerousGoodsPractitioner.searchDangerousGoodsPractitioners",
							queryQopulation);
		} else {
			return getSqlMapClientTemplate()
					.queryForList(
							"searchDangerousGoodsPractitioner.searchDangerousGoodsPractitioners",
							queryQopulation, (page - 1) * rows, rows);
		}
	}

	@Override
	public int getCountDangerousGoodsPractitionerByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map) {
		if (!StringUtil.isStringAvaliable(orgInternalCode)) {
			throw new BusinessValidationException("orgInternalCode涓嶈兘涓虹┖");
		}
		map.put("orgInternalCode", orgInternalCode);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchDangerousGoodsPractitioner.getCountDangerousGoodsByOrgInternalCodeAndMap",
						map);
	}

	@Override
	public Integer getCount(SearchDangerousGoodsPractitioner practitionerVo) {
		// TODO Auto-generated method stub
		if (null == practitionerVo) {
			return 0;
		}
		if (practitionerVo.getIsDeath() != null) {
			if (practitionerVo.getIsDeath() == -1) {
				practitionerVo.setIsDeath(null);
			}
		}
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchDangerousGoodsPractitioner.countSearchDangerousGoodsPractitioner",
						practitionerVo);
	}
}
