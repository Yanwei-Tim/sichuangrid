package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.dao.AbstractDao;
import com.tianque.openLayersMap.dao.CommonTwoDimensionMapManageDao;
import com.tianque.openLayersMap.domian.CommonEntityInfoVo;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

@SuppressWarnings({ "unchecked" })
@Repository("commonTwoDimensionMapManageDao")
public class CommonTwoDimensionMapManageDaoImpl extends AbstractDao implements
		CommonTwoDimensionMapManageDao {

	@Override
	public Boolean updateDomainByTableName(Map<String, Object> map) {
		return updateForBind(
				"commonTwoDimensionMapManage.updateDomainByTableName", map) > 0;
	}

	@Override
	public Object getDomainByTableNameAndFields(Map<String, Object> map) {
		if (map == null) {
			return null;
		}
		Object obj = getSqlMapClientTemplate().queryForObject(
				"commonTwoDimensionMapManage.getDomainByTableNameAndFields", map);
		return obj;
	}

	@Override
	public PageInfo<CommonEntityInfoVo> findCommonEntityInfoByGisModuleVoAndOrgCodeAndScreenCoordinate(
			GisFunction gisFunction, String orgInternalCode, String orgFiled,
			ScreenCoordinateVo screenCoordinateVo, String tableName, Integer pageNum,
			Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = createMap(gisFunction, orgInternalCode, orgFiled,
				screenCoordinateVo, tableName, sidx, sord, null);

		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"commonTwoDimensionMapManage.countCommonEntityInfoByGisModuleVoAndOrgCodeAndScreenCoordinate",
						map);
		List<CommonEntityInfoVo> list = getSqlMapClientTemplate()
				.queryForList(
						"commonTwoDimensionMapManage.findCommonEntityInfoByGisModuleVoAndOrgCodeAndScreenCoordinate",
						map, (pageNum - 1) * pageSize, pageSize);

		return new PageInfo(pageNum, pageSize, countNum, list);
	}

	private Map<String, Object> createMap(GisFunction gisFunction, String orgInternalCode,
			String orgFiled, ScreenCoordinateVo screenCoordinateVo, String tableName, String sidx,
			String sord, String searchValue) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("screenCoordinateVo", screenCoordinateVo);
		map.put("searchInfoVo", screenCoordinateVo.getSearchInfoVo());

		map.put("fieldA", gisFunction.getFieldA());
		map.put("fieldNameA", gisFunction.getFieldNameA());
		map.put("fieldB", gisFunction.getFieldB());
		map.put("fieldNameB", gisFunction.getFieldNameB());
		map.put("fieldC", gisFunction.getFieldC());
		map.put("fieldNameC", gisFunction.getFieldNameC());
		map.put("fieldD", gisFunction.getFieldD());
		map.put("fieldNameD", gisFunction.getFieldNameD());
		map.put("fieldE", gisFunction.getFieldE());
		map.put("fieldNameE", gisFunction.getFieldNameE());
		map.put("titleName", gisFunction.getTitleName());
		map.put("titleValue", gisFunction.getTitleValue());
		map.put("functionType", gisFunction.getFunctionType());
		map.put("searchFieldA", gisFunction.getSearchFieldA());
		map.put("searchFieldB", gisFunction.getSearchFieldB());
		map.put("orgFiled", orgFiled);
		map.put("tableName", tableName);
		map.put("searchValue", searchValue);
		map.put("orgInternalCode", orgInternalCode);
		map.put("isBound", false);
		map.put("sidx", sidx);
		map.put("sord", sord);

		return map;
	}

	@Override
	public Integer countBoundedTwoDimensionMapByOrgInternalCodeAndTablename(String orgInternalCode,
			String tableName) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("tableName", tableName);

		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"commonTwoDimensionMapManage.countBoundedTwoDimensionMapByOrgInternalCodeAndTablename",
						map);
	}

	@Override
	public PageInfo<CommonEntityInfoVo> findTwoDimensionMapPageInfoByOrgCodeAndScreenCoordinateAndModuleTypeAndSearchValue(
			GisFunction gisFunction, String orgInternalCode, String orgFiled,
			ScreenCoordinateVo screenCoordinateVo, String tableName, String searchValue,
			Integer pageNum, Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = createMap(gisFunction, orgInternalCode, orgFiled,
				screenCoordinateVo, tableName, sidx, sord, searchValue);

		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"commonTwoDimensionMapManage.countTwoDimensionMapPageInfoByOrgCodeAndScreenCoordinateAndModuleTypeAndSearchValue",
						map);
		List<CommonEntityInfoVo> list = getSqlMapClientTemplate()
				.queryForList(
						"commonTwoDimensionMapManage.findTwoDimensionMapPageInfoByOrgCodeAndScreenCoordinateAndModuleTypeAndSearchValue",
						map, (pageNum - 1) * pageSize, pageSize);

		return new PageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public Integer countBoundedTwoDimensionMapByOrgInternalCodeAndTablenameAndSearchValue(
			String orgInternalCode, String tableName, String searchValue, String searchFieldA,
			String searchFieldB, String functionType, ScreenCoordinateVo screenCoordinateVo) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("tableName", tableName);
		map.put("searchValue", searchValue);
		map.put("searchFieldA", searchFieldA);
		map.put("searchFieldB", searchFieldB);
		map.put("functionType", functionType);
		map.put("screenCoordinateVo", screenCoordinateVo);
		map.put("searchInfoVo", screenCoordinateVo.getSearchInfoVo());
		map.put("isBound", "true");

		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"commonTwoDimensionMapManage.countBoundedTwoDimensionMapByOrgInternalCodeAndTablenameAndSearchValue",
						map);
	}

}
