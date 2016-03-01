package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.dangerousChemicalsUnit.dao.SearchDangerousChemicalsUnitDao;
import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDangerousChemicalsUnitVo;

@Repository("searchDangerousChemicalsUnitDao")
public class SearchDangerousChemicalsUnitDaoImpl extends AbstractBaseDao
		implements SearchDangerousChemicalsUnitDao {

	@Override
	public PageInfo<DangerousChemicalsUnit> findDangerousChemicalsUnitByQueryCondition(
			SearchDangerousChemicalsUnitVo searchDangerousChemicalsUnitVo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("orgInternalCode",
		 * searchDangerousChemicalsUnitVo.getOrgInternalCode());
		 * map.put("unitName", searchDangerousChemicalsUnitVo.getUnitName());
		 * map.put("unitAddress",
		 * searchDangerousChemicalsUnitVo.getUnitAddress());
		 * map.put("superintendent",
		 * searchDangerousChemicalsUnitVo.getSuperintendent());
		 * map.put("telephone", searchDangerousChemicalsUnitVo.getTelephone());
		 * map.put("unitType", searchDangerousChemicalsUnitVo.getUnitType());
		 * map.put("commodityType",
		 * searchDangerousChemicalsUnitVo.getCommodityType());
		 * map.put("copyScope", searchDangerousChemicalsUnitVo.getCopyScope());
		 * map.put("storageDevice",
		 * searchDangerousChemicalsUnitVo.getStorageDevice());
		 * map.put("isEmphasis",
		 * searchDangerousChemicalsUnitVo.getIsEmphasis());
		 * map.put("logOutReason",
		 * searchDangerousChemicalsUnitVo.getLogOutReason());
		 * map.put("startLogOutTime",
		 * searchDangerousChemicalsUnitVo.getStartLogOutTime());
		 * map.put("endLogOutTime",
		 * searchDangerousChemicalsUnitVo.getEndLogOutTime()); map.put("order",
		 * sord); map.put("sortField", sidx);
		 */
		searchDangerousChemicalsUnitVo.setSortField(sidx);
		searchDangerousChemicalsUnitVo.setOrder(sord);
		Integer countNum = 0;
		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchdangerousChemicalsUnit.countDangerousChemicalsUnit",
				searchDangerousChemicalsUnitVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<Druggy> list = getSqlMapClientTemplate().queryForList(
				"searchdangerousChemicalsUnit.searchDangerousChemicalsUnit",
				searchDangerousChemicalsUnitVo, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<DangerousChemicalsUnit> fastSearchDangerousChemicalsUnit(
			SearchDangerousChemicalsUnitVo searchDangerousChemicalsUnitVo,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		if (searchDangerousChemicalsUnitVo == null) {
			return emptyPage(pageSize);
		}
		searchDangerousChemicalsUnitVo.setSortField(sortField);
		searchDangerousChemicalsUnitVo.setOrder(order);
		Integer countNum = 0;
		// fateson update
		// countDangerousChemicalsUnit---->countFastSearchDangerousChemicalsUnit
		countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchdangerousChemicalsUnit.countFastSearchDangerousChemicalsUnit",
						searchDangerousChemicalsUnitVo);
		@SuppressWarnings("unchecked")
		List<DangerousChemicalsUnit> list = getSqlMapClientTemplate()
				.queryForList(
						"searchdangerousChemicalsUnit.searchFastDangerousChemicalsUnit",
						searchDangerousChemicalsUnitVo,
						(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public List<DangerousChemicalsUnit> searchDangerousChemicalsUnitForExport(
			SearchDangerousChemicalsUnitVo searchDangerousChemicalsUnitVo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		/* Map<String, Object> map = new HashMap<String, Object>(); */
		if (searchDangerousChemicalsUnitVo != null) {
			/*
			 * map.put("orgInternalCode",
			 * searchDangerousChemicalsUnitVo.getOrgInternalCode());
			 * map.put("unitName",
			 * searchDangerousChemicalsUnitVo.getUnitName());
			 * map.put("unitAddress",
			 * searchDangerousChemicalsUnitVo.getUnitAddress());
			 * map.put("superintendent",
			 * searchDangerousChemicalsUnitVo.getSuperintendent());
			 * map.put("telephone",
			 * searchDangerousChemicalsUnitVo.getTelephone());
			 * map.put("unitType",
			 * searchDangerousChemicalsUnitVo.getUnitType());
			 * map.put("commodityType",
			 * searchDangerousChemicalsUnitVo.getCommodityType());
			 * map.put("copyScope",
			 * searchDangerousChemicalsUnitVo.getCopyScope());
			 * map.put("storageDevice",
			 * searchDangerousChemicalsUnitVo.getStorageDevice());
			 * map.put("isEmphasis",
			 * searchDangerousChemicalsUnitVo.getIsEmphasis());
			 * map.put("logOutReason",
			 * searchDangerousChemicalsUnitVo.getLogOutReason());
			 * map.put("startLogOutTime",
			 * searchDangerousChemicalsUnitVo.getStartLogOutTime());
			 * map.put("endLogOutTime",
			 * searchDangerousChemicalsUnitVo.getEndLogOutTime());
			 * map.put("attentionExtentId",
			 * searchDangerousChemicalsUnitVo.getAttentionExtentId());
			 * map.put("sortField", sidx); map.put("order", sord);
			 */
			searchDangerousChemicalsUnitVo.setSortField(sidx);
			searchDangerousChemicalsUnitVo.setOrder(sord);
		}

		if (pageNum == null) {
			return getSqlMapClientTemplate()
					.queryForList(
							"searchdangerousChemicalsUnit.searchDangerousChemicalsUnit",
							searchDangerousChemicalsUnitVo);
		} else {
			return getSqlMapClientTemplate()
					.queryForList(
							"searchdangerousChemicalsUnit.searchDangerousChemicalsUnit",
							searchDangerousChemicalsUnitVo,
							(pageNum - 1) * pageSize, pageSize);
		}
	}

	private PageInfo<DangerousChemicalsUnit> emptyPage(int pageSize) {
		PageInfo<DangerousChemicalsUnit> pageInfo = new PageInfo<DangerousChemicalsUnit>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<DangerousChemicalsUnit>());
		return pageInfo;
	}

	private PageInfo<DangerousChemicalsUnit> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<DangerousChemicalsUnit> pageInfo = new PageInfo<DangerousChemicalsUnit>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private Map getFastConditionMap(
			SearchDangerousChemicalsUnitVo searchDangerousChemicalsUnitVo,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (searchDangerousChemicalsUnitVo == null) {
			return map;
		}
		map.put("orgInternalCode",
				searchDangerousChemicalsUnitVo.getOrgInternalCode());
		map.put("unitName", searchDangerousChemicalsUnitVo.getUnitName());
		map.put("isEmphasis", searchDangerousChemicalsUnitVo.getIsEmphasis());
		// 排序字段非空的判断
		if (StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		return map;
	}

	@Override
	public Integer getCount(
			SearchDangerousChemicalsUnitVo searchDangerousChemicalsUnitVo) {
		// TODO Auto-generated method stub
		if (null == searchDangerousChemicalsUnitVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchdangerousChemicalsUnit.countDangerousChemicalsUnit",
				searchDangerousChemicalsUnitVo);
	}
}
