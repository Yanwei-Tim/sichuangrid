package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchDustbinDao;
import com.tianque.domain.Dustbin;
import com.tianque.domain.vo.SearchDustbinVo;

@Repository("searchDustbinDao")
public class SearchDustbinDaoImpl extends AbstractBaseDao implements
		SearchDustbinDao {

	@Override
	public PageInfo<Dustbin> findDustbinByQueryCondition(
			SearchDustbinVo searchDustbinVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * map.put("orgInternalCode", searchDustbinVo.getOrgInternalCode());
		 * map.put("unitName", searchDustbinVo.getUnitName());
		 * map.put("unitAddress", searchDustbinVo.getUnitAddress());
		 * map.put("superintendent", searchDustbinVo.getSuperintendent());
		 * map.put("telephone", searchDustbinVo.getTelephone());
		 * map.put("unitType", searchDustbinVo.getUnitType());
		 * map.put("commodityType", searchDustbinVo.getCommodityType());
		 * map.put("copyScope", searchDustbinVo.getCopyScope());
		 * map.put("storageDevice", searchDustbinVo.getStorageDevice());
		 * map.put("isEmphasis", searchDustbinVo.getIsEmphasis());
		 * map.put("logOutReason", searchDustbinVo.getLogOutReason());
		 * map.put("startLogOutTime", searchDustbinVo.getStartLogOutTime());
		 * map.put("endLogOutTime", searchDustbinVo.getEndLogOutTime());
		 */
		map.put("order", sord);
		map.put("sortField", sidx);
		Integer countNum = 0;
		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"dustbin.countDustbin", map);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<Druggy> list = getSqlMapClientTemplate().queryForList(
				"dustbin.searchDustbin", map, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<Dustbin> fastSearchDustbin(SearchDustbinVo searchDustbinVo,
			Integer pageNum, Integer pageSize, String sortField, String order,
			String partType) {
		if (searchDustbinVo == null) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = getFastConditionMap(searchDustbinVo,
				partType, sortField, order);
		Integer countNum = 0;
		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"dustbin.countSearchDustbin", map);
		@SuppressWarnings("unchecked")
		List<Dustbin> list = getSqlMapClientTemplate().queryForList(
				"dustbin.searchFastDustbin", map, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public List<Dustbin> searchDustbinForExport(
			SearchDustbinVo searchDustbinVo, Integer pageNum, Integer pageSize,
			String sidx, String sord, String partType) {
		if (pageNum == null) {
			return getSqlMapClientTemplate().queryForList(
					"dustbin.searchDustbinPagerBySearchVo", searchDustbinVo);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"dustbin.searchDustbinPagerBySearchVo", searchDustbinVo,
					(pageNum - 1) * pageSize, pageSize);
		}
	}

	private PageInfo<Dustbin> emptyPage(int pageSize) {
		PageInfo<Dustbin> pageInfo = new PageInfo<Dustbin>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Dustbin>());
		return pageInfo;
	}

	private PageInfo<Dustbin> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<Dustbin> pageInfo = new PageInfo<Dustbin>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private Map getFastConditionMap(SearchDustbinVo searchDustbinVo,
			String partType, String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (searchDustbinVo == null) {
			return map;
		}
		map.put("partType", partType);
		map.put("partTypeId", searchDustbinVo.getPartTypeId());
		map.put("orgInternalCode", searchDustbinVo.getOrgInternalCode());
		map.put("isEmphasis", searchDustbinVo.getIsEmphasis());
		map.put("fastSearchKeyWords", searchDustbinVo.getFastSearchKeyWords());
		map.put("partName", searchDustbinVo.getPartName());
		if (!StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		return map;
	}

	@Override
	public Integer getCount(SearchDustbinVo searchDustbinVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"dustbin.countSearchDustbinPagerBySearchVo", searchDustbinVo);
	}
}
