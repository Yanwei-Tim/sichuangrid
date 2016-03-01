package com.tianque.baseInfo.goodSamaritan.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.goodSamaritan.domain.GoodSamaritan;
import com.tianque.baseInfo.goodSamaritan.domain.SearchGoodSamaritanVo;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("searchGoodSamaritanDao")
public class SearchGoodSamaritanDaoImpl extends AbstractBaseDao implements
		SearchGoodSamaritanDao {
	@Override
	public PageInfo<GoodSamaritan> searchGoodSamaritan(
			SearchGoodSamaritanVo queryPopulation, int pageNum, int pageSize,
			String sortField, String order) {
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
				.queryForObject("searchGoodSamaritan.countSearchGoodSamaritan",
						queryPopulation);
		int pageCount = 0;
		count(countNum, pageSize, pageCount, pageNum);
		List<GoodSamaritan> list = getSqlMapClientTemplate().queryForList(
				"searchGoodSamaritan.searchGoodSamaritans", queryPopulation,
				(pageNum - 1) * pageSize, pageSize);
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

	private PageInfo<GoodSamaritan> createPageInfo(int countNum, int pageSize,
			int pageNum, List list) {
		PageInfo<GoodSamaritan> pageInfo = new PageInfo<GoodSamaritan>();
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setResult(list);
		return pageInfo;
	}

	private PageInfo<GoodSamaritan> emptyPage(int pageSize) {
		PageInfo<GoodSamaritan> pageInfo = new PageInfo<GoodSamaritan>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<GoodSamaritan>());
		return pageInfo;
	}

	@Override
	public List<GoodSamaritan> searchGoodSamaritanForExport(
			SearchGoodSamaritanVo queryQopulation, Integer page, Integer rows,
			String sortField, String order) {
		if (queryQopulation == null) {
			return null;
		}
		queryQopulation.setSortField(sortField);
		queryQopulation.setOrder(order);
		if (page == null) {
			return getSqlMapClientTemplate()
					.queryForList("searchGoodSamaritan.searchGoodSamaritans",
							queryQopulation);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchGoodSamaritan.searchGoodSamaritans",
					queryQopulation, (page - 1) * rows, rows);
		}
	}

	@Override
	public int getCountGoodSamaritanByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map) {
		if (!StringUtil.isStringAvaliable(orgInternalCode)) {
			throw new BusinessValidationException("orgInternalCode 不能为空 ");
		}
		map.put("orgInternalCode", orgInternalCode);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchGoodSamaritan.getCountGoodSamaritanByOrgInternalCodeAndMap",
						map);
	}

	@Override
	public Integer getCount(SearchGoodSamaritanVo personnelVo) {
		if (null == personnelVo) {
			return null;
		}
		if (personnelVo.getIsDeath() != null) {
			if (personnelVo.getIsDeath() == -1) {
				personnelVo.setIsDeath(null);
			}
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchGoodSamaritan.countSearchGoodSamaritan", personnelVo);

	}
}
