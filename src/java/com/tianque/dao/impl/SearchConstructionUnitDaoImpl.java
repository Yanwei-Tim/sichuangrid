package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchConstructionUnitDao;
import com.tianque.domain.ConstructionUnit;
import com.tianque.domain.vo.SearchConstructionUnitVo;

@Repository("searchConstructionUnitDao")
public class SearchConstructionUnitDaoImpl extends AbstractBaseDao implements
		SearchConstructionUnitDao {

	@Override
	public PageInfo<ConstructionUnit> searchConstructionUnit(
			SearchConstructionUnitVo searchConstructionUnitVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (null == searchConstructionUnitVo) {
			return emptyOtherLocalePage(pageSize);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectName", searchConstructionUnitVo.getProjectName());
		map.put("projectAddress", searchConstructionUnitVo.getProjectAddress());
		map.put("developmentUnit", searchConstructionUnitVo.getDevelopmentUnit());
		map.put("developmentContactPerson", searchConstructionUnitVo.getDevelopmentContactPerson());
		map.put("developmentContactPersonPhone",
				searchConstructionUnitVo.getDevelopmentContactPersonPhone());
		map.put("contractor", searchConstructionUnitVo.getContractor());
		map.put("contractorContactPerson", searchConstructionUnitVo.getContractorContactPerson());
		map.put("contractorContactPersonPhone",
				searchConstructionUnitVo.getContractorContactPersonPhone());
		map.put("laborer", searchConstructionUnitVo.getLaborer());
		map.put("laborerContactPerson", searchConstructionUnitVo.getLaborerContactPerson());
		map.put("laborerContactPersonPhone",
				searchConstructionUnitVo.getLaborerContactPersonPhone());
		map.put("orgInternalCode", searchConstructionUnitVo.getOrgInternalCode());
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("isEmphasis", searchConstructionUnitVo.getIsEmphasis());
		PageInfo<ConstructionUnit> pageInfo = new PageInfo<ConstructionUnit>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchConstructionUnit.countConstructionUnits", map);

		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		if (countNum > 0) {
			List<ConstructionUnit> list = getSqlMapClientTemplate().queryForList(
					"searchConstructionUnit.searchConstructionUnits", map,
					(pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<ConstructionUnit>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<ConstructionUnit> emptyOtherLocalePage(int pageSize) {
		PageInfo<ConstructionUnit> pageInfo = new PageInfo<ConstructionUnit>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<ConstructionUnit>());
		return pageInfo;
	}

	@Override
	public List findConstructionUnitNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate().queryForList(
				"searchConstructionUnit.findConstructionUnitsNameAndPinyinAndOrgInternalCode", map);
	}

	@Override
	public List<ConstructionUnit> searchConstructionUnitForExport(
			SearchConstructionUnitVo searchConstructionUnitVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectName", searchConstructionUnitVo.getProjectName());
		map.put("projectAddress", searchConstructionUnitVo.getProjectAddress());
		map.put("developmentUnit", searchConstructionUnitVo.getDevelopmentUnit());
		map.put("developmentContactPerson", searchConstructionUnitVo.getDevelopmentContactPerson());
		map.put("developmentContactPersonPhone",
				searchConstructionUnitVo.getDevelopmentContactPersonPhone());
		map.put("contractor", searchConstructionUnitVo.getContractor());
		map.put("contractorContactPerson", searchConstructionUnitVo.getContractorContactPerson());
		map.put("contractorContactPersonPhone",
				searchConstructionUnitVo.getContractorContactPersonPhone());
		map.put("laborer", searchConstructionUnitVo.getLaborer());
		map.put("laborerContactPerson", searchConstructionUnitVo.getLaborerContactPerson());
		map.put("laborerContactPersonPhone",
				searchConstructionUnitVo.getLaborerContactPersonPhone());
		map.put("orgInternalCode", searchConstructionUnitVo.getOrgInternalCode());
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("isEmphasis", searchConstructionUnitVo.getIsEmphasis());
		if (pageNum == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchConstructionUnit.searchConstructionUnits", map);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchConstructionUnit.searchConstructionUnits", map,
					(pageNum - 1) * pageSize, pageSize);
		}
	}

}
