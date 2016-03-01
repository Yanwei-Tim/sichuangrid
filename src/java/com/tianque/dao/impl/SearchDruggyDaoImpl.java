package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.druggy.dao.SearchDruggyDao;
import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDruggyVo;

@Repository("searchDruggyDao")
public class SearchDruggyDaoImpl extends AbstractBaseDao implements
		SearchDruggyDao {

	@Override
	public PageInfo<Druggy> findDruggysByQueryCondition(
			SearchDruggyVo searchDruggyVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		searchDruggyVo.setSortField(sidx);
		searchDruggyVo.setOrder(sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchDruggy.countDruggys", searchDruggyVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		// pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<Druggy> list = getSqlMapClientTemplate().queryForList(
				"searchDruggy.searchDruggys", searchDruggyVo,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<Druggy> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<Druggy> pageInfo = new PageInfo<Druggy>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public List<Druggy> searchDruggysForExport(SearchDruggyVo searchDruggyVo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		// fateson update searchDruggyVo == null 就 new
		if (searchDruggyVo == null) {
			searchDruggyVo = new SearchDruggyVo();
		}
		searchDruggyVo.setSortField(sidx);
		searchDruggyVo.setOrder(sord);

		if (pageNum == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchDruggy.searchDruggys", searchDruggyVo);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchDruggy.searchDruggys", searchDruggyVo,
					(pageNum - 1) * pageSize, pageSize);
		}
	}

	public List findSuperiorVisitByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate().queryForList(
				"searchDruggy.findDruggysByNameAndPinyinAndOrgInternalCode",
				map);
	}

	@Override
	public Integer getCount(SearchDruggyVo searchDruggyVo) {
		if (null == searchDruggyVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchDruggy.countDruggys", searchDruggyVo);

	}

	@Override
	public Long getDetoxiCateCaseCount(String orgInternalCode, Long type) {
		if (null == orgInternalCode) {
			return null;
		}
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgInternalCode", orgInternalCode);
		query.put("type", type);
		return (null == (Long) getSqlMapClientTemplate().queryForObject(
				"searchDruggy.getDetoxiCateCaseCount", query)) ? 0L
				: (Long) getSqlMapClientTemplate().queryForObject(
						"searchDruggy.getDetoxiCateCaseCount", query);
	}

	@Override
	public Long getHelpedCount(String orgInternalCode, Long type) {
		if (null == orgInternalCode) {
			return null;
		}
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgInternalCode", orgInternalCode);
		query.put("type", type);
		return (null == (Long) getSqlMapClientTemplate().queryForObject(
				"searchDruggy.getHelpedCount", query)) ? 0L
				: (Long) getSqlMapClientTemplate().queryForObject(
						"searchDruggy.getHelpedCount", query);

	}

	@Override
	public Long getNotHelpedCount(String orgInternalCode, Long type) {
		if (null == orgInternalCode) {
			return null;
		}
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgInternalCode", orgInternalCode);
		query.put("type", type);
		return (null == (Long) getSqlMapClientTemplate().queryForObject(
				"searchDruggy.getNotHelpedCount", query)) ? 0L
				: (Long) getSqlMapClientTemplate().queryForObject(
						"searchDruggy.getNotHelpedCount", query);

	}

	private Map getFastConditionMap(SearchDruggyVo searchDruggyVo,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (searchDruggyVo == null) {
			return map;
		}
		map.put("orgInternalCode", searchDruggyVo.getOrgInternalCode());
		map.put("name", searchDruggyVo.getName());
		map.put("idCardNo", searchDruggyVo.getIdCardNo());
		map.put("isEmphasis", searchDruggyVo.getIsEmphasis());
		map.put("fastSearchKeyWords", searchDruggyVo.getFastSearchKeyWords());
		map.put("populationType", BaseInfoTables.DRUGGY_KEY);// 用于关联查询服务人员
		if (!StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		return map;
	}

	private PageInfo<Druggy> emptyPage(int pageSize) {
		PageInfo<Druggy> pageInfo = new PageInfo<Druggy>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Druggy>());
		return pageInfo;
	}

	@Override
	public PageInfo<Druggy> fastSearchDruggy(SearchDruggyVo searchDruggyVo,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		if (searchDruggyVo == null) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = getFastConditionMap(searchDruggyVo,
				sortField, order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchDruggy.countFastSearchDurggy", map);
		@SuppressWarnings("unchecked")
		List<Druggy> list = getSqlMapClientTemplate().queryForList(
				"searchDruggy.searchFastDruggy", map, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}
}
