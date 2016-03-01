package com.tianque.baseInfo.mPersonnel.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.mPersonnel.domain.MPersonnel;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchMPersonnelVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.util.PopulationType;

@Repository("searchMPersonnelDao")
public class SearchMPersonnelDaoImpl extends AbstractBaseDao implements
		SearchMPersonnelDao {

	@Override
	public PageInfo<MPersonnel> searchMPersonnel(
			SearchMPersonnelVo queryPopulation, int pageNum, int pageSize,
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
				"searchMPersonnel.countSearchMPersonnel", map);
		int pageCount = 0;
		count(countNum, pageSize, pageCount, pageNum);
		List<MPersonnel> list = getSqlMapClientTemplate().queryForList(
				"searchMPersonnel.searchMPersonnels", map,
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

	private PageInfo<MPersonnel> createPageInfo(int countNum, int pageSize,
			int pageNum, List list) {
		PageInfo<MPersonnel> pageInfo = new PageInfo<MPersonnel>();
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setResult(list);
		return pageInfo;
	}

	private PageInfo<MPersonnel> emptyPage(int pageSize) {
		PageInfo<MPersonnel> pageInfo = new PageInfo<MPersonnel>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<MPersonnel>());
		return pageInfo;
	}

	@Override
	public List<MPersonnel> searchMPersonnelForExport(
			SearchMPersonnelVo queryQopulation, Integer page, Integer rows,
			String sortField, String order) {
		if (null == queryQopulation) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("attentionPopulationType", PopulationType.M_PERSONNEL);
		map.put("orgInternalCode", queryQopulation.getOrgInternalCode());
		map.put("queryQopulation", queryQopulation);
		map.put("sortField", sortField);
		map.put("order", order);
		if (page == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchMPersonnel.searchMPersonnels", map);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchMPersonnel.searchMPersonnels", map,
					(page - 1) * rows, rows);
		}
	}

	@Override
	public int getCountMPersonnelByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map) {
		if (!StringUtil.isStringAvaliable(orgInternalCode)) {
			throw new BusinessValidationException("orgInternalCode 不能为空 ");
		}
		map.put("orgInternalCode", orgInternalCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchMPersonnel.getCountMPersonnelByOrgInternalCodeAndMap",
				map);
	}

	@Override
	public Integer getCount(SearchMPersonnelVo personnelVo) {
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
				"searchMPersonnel.countSearchMPersonnel", map);

	}
}
