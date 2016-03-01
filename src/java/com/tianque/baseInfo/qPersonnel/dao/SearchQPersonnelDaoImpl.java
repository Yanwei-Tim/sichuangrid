package com.tianque.baseInfo.qPersonnel.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.qPersonnel.domain.QPersonnel;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchQPersonnelVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.util.PopulationType;

@Repository("searchQPersonnelDao")
public class SearchQPersonnelDaoImpl extends AbstractBaseDao implements
		SearchQPersonnelDao {

	@Override
	public PageInfo<QPersonnel> searchQPersonnel(
			SearchQPersonnelVo queryPopulation, int pageNum, int pageSize,
			String sortField, String order) {
		if (queryPopulation == null) {
			return emptyPage(pageSize);
		}
		if (queryPopulation.getIsDeath() != null) {
			if (queryPopulation.getIsDeath() == -1) {
				queryPopulation.setIsDeath(null);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", queryPopulation.getOrgInternalCode());
		map.put("idCardNo", queryPopulation.getIdCardNo());
		map.put("queryQopulation", queryPopulation);
		map.put("sortField", sortField);
		map.put("order", order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchQPersonnel.countSearchQPersonnel", map);
		int pageCount = 0;
		count(countNum, pageSize, pageCount, pageNum);
		List<QPersonnel> list = getSqlMapClientTemplate().queryForList(
				"searchQPersonnel.searchQPersonnels", map,
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

	private PageInfo<QPersonnel> createPageInfo(int countNum, int pageSize,
			int pageNum, List list) {
		PageInfo<QPersonnel> pageInfo = new PageInfo<QPersonnel>();
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setResult(list);
		return pageInfo;
	}

	private PageInfo<QPersonnel> emptyPage(int pageSize) {
		PageInfo<QPersonnel> pageInfo = new PageInfo<QPersonnel>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<QPersonnel>());
		return pageInfo;
	}

	@Override
	public List<QPersonnel> searchQPersonnelForExport(
			SearchQPersonnelVo queryQopulation, Integer page, Integer rows,
			String sortField, String order) {
		if (null == queryQopulation) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("attentionPopulationType", PopulationType.Q_PERSONNEL);
		map.put("orgInternalCode", queryQopulation.getOrgInternalCode());
		map.put("queryQopulation", queryQopulation);
		map.put("sortField", sortField);
		map.put("order", order);
		if (page == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchQPersonnel.searchQPersonnels", map);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchQPersonnel.searchQPersonnels", map,
					(page - 1) * rows, rows);
		}
	}

	@Override
	public int getCountQPersonnelByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map) {
		if (!StringUtil.isStringAvaliable(orgInternalCode)) {
			throw new BusinessValidationException("orgInternalCode 不能为空 ");
		}
		map.put("orgInternalCode", orgInternalCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchQPersonnel.getCountQPersonnelByOrgInternalCodeAndMap",
				map);
	}

	@Override
	public Integer getCount(SearchQPersonnelVo personnelVo) {
		if (null == personnelVo) {
			return null;
		}
		if (personnelVo.getIsDeath() != null) {
			if (personnelVo.getIsDeath() == -1) {
				personnelVo.setIsDeath(null);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", personnelVo.getOrgInternalCode());
		map.put("idCardNo", personnelVo.getIdCardNo());
		map.put("queryQopulation", personnelVo);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchQPersonnel.countSearchQPersonnel", map);

	}
}
