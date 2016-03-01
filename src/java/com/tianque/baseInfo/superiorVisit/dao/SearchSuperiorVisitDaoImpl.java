package com.tianque.baseInfo.superiorVisit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchSuperiorVisitVo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("searchSuperiorVisitDao")
public class SearchSuperiorVisitDaoImpl extends AbstractBaseDao implements
		SearchSuperiorVisitDao {

	@Override
	public PageInfo<SuperiorVisit> searchAttentionPersonnel(
			SearchSuperiorVisitVo searchSuperiorVisitVo, int pageNum,
			int pageSize, String sortField, String order) {
		if (searchSuperiorVisitVo == null)
			return emptyAttentionObjectPage(pageSize);
		searchSuperiorVisitVo.setSortField(sortField);
		searchSuperiorVisitVo.setOrder(order);
		PageInfo<SuperiorVisit> pageInfo = new PageInfo<SuperiorVisit>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchSuperiorVisitVo.countSuperiorVisit",
				searchSuperiorVisitVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		// pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<SuperiorVisit> list = getSqlMapClientTemplate().queryForList(
					"searchSuperiorVisitVo.searchSuperiorVisit",
					searchSuperiorVisitVo, (pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<SuperiorVisit>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<SuperiorVisit> emptyAttentionObjectPage(int pageSize) {
		PageInfo<SuperiorVisit> pageInfo = new PageInfo<SuperiorVisit>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SuperiorVisit>());
		return pageInfo;
	}

	public List<SuperiorVisit> searchSuperiorVisitsForExport(
			SearchSuperiorVisitVo condition, Integer page, Integer rows,
			String sortField, String order) {
		condition.setSortField(sortField);
		condition.setOrder(order);
		if (page == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchSuperiorVisitVo.searchSuperiorVisit", condition);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchSuperiorVisitVo.searchSuperiorVisit", condition,
					(page - 1) * rows, rows);
		}
	}

	public List findSuperiorVisitByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"searchSuperiorVisitVo.findSuperiorVisitNameAndPinyinAndOrgInternalCode",
						map);
	}

	@Override
	public int getCountSuperiorVisitByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map) {
		if (!StringUtil.isStringAvaliable(orgInternalCode)) {
			throw new BusinessValidationException("orgInternalCode不能为空");
		}
		map.put("orgInternalCode", orgInternalCode);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchSuperiorVisitVo.getCountSuperiorVisitByOrgInternalCodeAndMap",
						map);
	}

	@Override
	public PageInfo<SuperiorVisit> fastSearchSuperiorVisit(
			SearchSuperiorVisitVo searchSuperiorVisitVo, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		if (searchSuperiorVisitVo == null) {
			return emptyAttentionObjectPage(pageSize);
		}
		Map<String, Object> map = getFastConditionMap(searchSuperiorVisitVo,
				sortField, order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchSuperiorVisitVo.countFastSuperiorVisit", map);
		@SuppressWarnings("unchecked")
		List<SuperiorVisit> list = getSqlMapClientTemplate().queryForList(
				"searchSuperiorVisitVo.searchFastSuperiorVisit", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private Map getFastConditionMap(
			SearchSuperiorVisitVo searchSuperiorVisitVo, String sortField,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (searchSuperiorVisitVo == null) {
			return map;
		}
		map.put("orgInternalCode", searchSuperiorVisitVo.getOrgInternalCode());
		map.put("name", searchSuperiorVisitVo.getName());
		map.put("idCardNo", searchSuperiorVisitVo.getIdCardNo());
		map.put("isEmphasis", searchSuperiorVisitVo.getIsEmphasis());
		map.put("fastSearchKeyWords",
				searchSuperiorVisitVo.getFastSearchKeyWords());
		map.put("isDeath", searchSuperiorVisitVo.getIsDeath());
		map.put("populationType", BaseInfoTables.SUPERIORVISIT_KEY);
		if (!StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		return map;
	}

	private PageInfo<SuperiorVisit> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List<SuperiorVisit> list) {
		PageInfo<SuperiorVisit> pageInfo = new PageInfo<SuperiorVisit>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchSuperiorVisitVo visitVo) {
		// TODO Auto-generated method stub
		if (null == visitVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchSuperiorVisitVo.countSuperiorVisit", visitVo);
	}
}
