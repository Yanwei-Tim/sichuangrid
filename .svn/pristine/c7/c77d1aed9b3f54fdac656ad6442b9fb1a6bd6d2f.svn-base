package com.tianque.openLayersMap.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.AbstractDao;
import com.tianque.openLayersMap.dao.PersonTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.PersonInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.shard.util.ShardTables;

/**
 * 二维地图 人口信息dao方法的实现
 * 
 * @author jiangjinling
 */
@SuppressWarnings({ "unchecked" })
@Repository("personTwoDimensionMapDao")
public class PersonTwoDimensionMapDaoImpl extends AbstractDao implements
		PersonTwoDimensionMapDao {

	@Autowired
	private CacheService cacheService;

	@Override
	public PageInfo<PersonInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String tableName, List list, String shardCode) {

		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				screenCoordinateVo, searchValue, null, "true", shardCode);
		map.put("personList", list);
		// map.put("startRow", (pageNum - 1) * pageSize);
		// map.put("endRow", pageNum * pageSize);
		if (tableName.equals(GisGlobalValueMap.keyPersonalType
				.get("overseaStaff"))) {// 如果是表名是境外人员,执行以下操作

			return queryForPageInfo(
					"personTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValueForOverseaPersonnel",
					"personTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValueForOverseaPersonnel",
					pageNum, pageSize, map);
		} else {
			return queryForPageInfo(
					"personTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue",
					"personTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue",
					pageNum, pageSize, map);
		}
	}

	@Override
	public PageInfo<PersonInfoVo> findPersonsByHouseId(Long houseId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", houseId);
		map.put("shardCode", shardCode);
		return page(
				getSqlMapClientTemplate().queryForList(
						"personTwoDimensionMap.findPersonByHouseId", map),
				pageNum, pageSize);
	}

	@Override
	public PageInfo<PersonInfoVo> findPersonsByHouseIdAndType(Long houseId,
			String tableName, String personTypeName, String typeName,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", houseId);
		map.put("tableName", tableName);
		map.put("typeName", typeName);
		map.put("personTypeName", personTypeName);
		if (ShardTables.SHARD_HOUSEHOLDSTAFFS.equalsIgnoreCase(tableName)) {
			map.put("tableName", tableName + "_" + shardCode);
		}
		map.put("shardCode", shardCode);
		if (typeName.equals(GisGlobalValueMap.OVERSEASTAFF)) {
			return page(
					getSqlMapClientTemplate()
							.queryForList(
									"personTwoDimensionMap.findOverseaStaffByHouseIdAndTableName",
									map), pageNum, pageSize);
		} else {
			return page(
					getSqlMapClientTemplate()
							.queryForList(
									"personTwoDimensionMap.findPersonByHouseIdAndTableName",
									map), pageNum, pageSize);
		}
	}

	public static <T> PageInfo<T> page(List<T> result, int currentPage,
			int pageSize) {
		if (result == null) {
			return new PageInfo<T>(1, pageSize, 0, new ArrayList<T>());
		}
		int total = result.size();
		pageSize = pageSize > 0 ? pageSize : 20;
		int pageNum = total % pageSize == 0 ? total / pageSize : total
				/ pageSize + 1;
		currentPage = currentPage > pageNum ? pageNum : currentPage;
		int begin = (currentPage > 1 ? ((currentPage - 1) * pageSize + 1) : 1);
		int end = begin + pageSize;
		end = end > total + 1 ? total + 1 : end;
		List<T> page_result = new ArrayList<T>();
		page_result = result.subList(begin - 1, end - 1);
		return new PageInfo<T>(pageNum, pageSize, total, page_result);
	}

	@Override
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue) {

		Map<String, Object> map = getParamMap(orgInternalCode, searchValue,
				null, null);
		return (Integer) queryForCount(
				"personTwoDimensionMap.countTwoDimensionMapInfoByOrgInternalCodeAndSearchValue",
				map);
	}

	@Override
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue, String tableName,
			String personType, String shardCode) {

		Map<String, Object> map = getParamMap(orgInternalCode, searchValue,
				tableName, personType);
		if (ShardTables.SHARD_HOUSEHOLDSTAFFS.equalsIgnoreCase(tableName)) {
			map.put("tableName", tableName + "_" + shardCode);
		}
		map.put("shardCode", shardCode);
		if (tableName.equals(GisGlobalValueMap.keyPersonalType
				.get("overseaStaff"))) {// 如果是表名是境外人员
			return (Integer) queryForCount(
					"personTwoDimensionMap.countBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValueForOverseaPersonnel",
					map);
		} else {
			return (Integer) queryForCount(
					"personTwoDimensionMap.countBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue",
					map);
		}
	}

	@Override
	public PersonInfoVo getViewPopupInfoByIdAndTableName(Long id,
			String tableName, String personType, String shardCode) {
		Map<String, Object> map = getParamMap(null, null, tableName, personType);
		map.put("id", id);
		PersonInfoVo personInfoVo = null;
		if (ShardTables.SHARD_HOUSEHOLDSTAFFS.equalsIgnoreCase(tableName)) {
			map.put("tableName", tableName + "_" + shardCode);
		}
		map.put("shardCode", shardCode);
		if (tableName.equals(GisGlobalValueMap.keyPersonalType
				.get("overseaStaff"))) {// 如果是表名是境外人员
			personInfoVo = (PersonInfoVo) getSqlMapClientTemplate()
					.queryForObject(
							"personTwoDimensionMap.getViewPopupInfoByIdAndTableNameForOverseaPersonnel",
							map);
		} else {
			personInfoVo = (PersonInfoVo) getSqlMapClientTemplate()
					.queryForObject(
							"personTwoDimensionMap.getViewPopupInfoByIdAndTableName",
							map);
		}
		return personInfoVo;
	}

	// @Override
	// public Integer countTwoDimensionMapPageInfoByOrgId(String
	// orgInternalCode,
	// String tableName) {
	// if (null == orgInternalCode || null == tableName) {
	// throw new BusinessValidationException("组织机构code或表名不能为空!");
	// }
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("orgInternalCode", orgInternalCode);
	// map.put("typeName", tableName);
	//
	// return (Integer) getSqlMapClientTemplate()
	// .queryForObject(
	// "personTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName",
	// map);
	// }

	@Override
	public Integer countTwoDimensionMapPageInfoByOrgId(String orgInternalCode,
			String tableName, String shardCode) {
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						MemCacheConstant.COUNT_TWODIMENSIONMAPPAGEINFOBYORGINTERNALCODEANDTYPENAME_POPULATION,
						tableName, orgInternalCode == null ? "null"
								: orgInternalCode);
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		if (count == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgInternalCode", orgInternalCode);
			map.put("typeName", tableName);
			if (ShardTables.SHARD_HOUSEHOLDSTAFFS.equalsIgnoreCase(tableName)) {
				map.put("typeName", tableName + "_" + shardCode);
			}
			count = (Integer) queryForCount(
					"personTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName",
					map);
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);
		}
		return count;

	}

	@Override
	public Integer countHouseHasPopulation(Long houseId, String type) {
		if (null == houseId || null == type) {
			throw new BusinessValidationException("住房ID或人员类型不能为空!");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", houseId);
		map.put("populationType", type);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"personTwoDimensionMap.countHouseHasPopulation", map);
	}

	@Override
	public PageInfo<PersonInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String typeName, String personTypeName, Integer pageNum,
			Integer pageSize, String sidx, String sord, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("personTypeName", personTypeName);
		map.put("typeName", typeName);
		map.put("screenCoordinateVo", screenCoordinateVo);
		map.put("searchInfoVo",
				(screenCoordinateVo != null) ? screenCoordinateVo
						.getSearchInfoVo() : null);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("isBound", false);// 查询未绑定的数据
		map.put("shardCode", shardCode);
		return queryForPageInfo(
				"personTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
				"personTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
				pageNum, pageSize, map);
	}

	@Override
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, String shardCode) {
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						MemCacheConstant.COUNT_BOUNDEDTWODIMENSIONMAPINFOBYORGINTERNALCODEANDTYPENAME_POPULATION,
						typeName, orgInternalCode == null ? "null"
								: orgInternalCode);
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		if (count == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgInternalCode", orgInternalCode);
			map.put("tableName", typeName);
			map.put("shardCode", shardCode);
			if (ShardTables.SHARD_HOUSEHOLDSTAFFS.equalsIgnoreCase(typeName)) {
				map.put("tableName", typeName + "_" + shardCode);
			}
			count = (Integer) queryForCount(
					"personTwoDimensionMap.countBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName",
					map);
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);

		}

		return count;
	}

	@Override
	public PageInfo<PersonInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord, String personTypeName,
			String shardCode) {
		Map<String, Object> map = getMap(orgInternalCode, typeName, null, null,
				null, null, null, "false", shardCode);
		map.put("personType", GisGlobalValueMap.getPersonalType(typeName));
		map.put("tableName", typeName);
		map.put("personTypeName", personTypeName);
		// map.put("startRow", (pageNum - 1) * pageSize);
		// map.put("endRow", pageNum * pageSize);
		if (typeName.equals(GisGlobalValueMap.keyPersonalType
				.get("overseaStaff"))) {// 如果是表名是境外人员,执行以下操作
			return queryForPageInfo(
					"personTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeNameForOverseaPersonnel",
					"personTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeNameForOverseaPersonnel",
					pageNum, pageSize, map);
		} else {
			return queryForPageInfo(
					"personTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeNameTwo",
					"personTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName",
					pageNum, pageSize, map);
		}
	}

	@Override
	public Integer countPopulationByBuildDataId(Long buildDataId, String type,
			String shardCode) {
		if (buildDataId == null) {
			throw new BusinessValidationException("楼宇id不能为空！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buildDataId", buildDataId);
		map.put("populationType", type);
		map.put("shardCode", shardCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"personTwoDimensionMap.countPopulationByBuildDataId", map);
	}

}
