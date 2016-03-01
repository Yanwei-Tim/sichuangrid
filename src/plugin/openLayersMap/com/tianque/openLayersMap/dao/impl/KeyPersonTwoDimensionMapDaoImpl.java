package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.dao.AbstractDao;
import com.tianque.openLayersMap.dao.KeyPersonTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.KeyPersonInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.util.BaiduMapUtil;
import com.tianque.shard.util.ShardTables;

/**
 * 二维地图 重点人员dao方法的实现
 * 
 * @author jiangjinling
 * 
 */
@SuppressWarnings({ "unchecked" })
@Repository("keyPersonTwoDimensionMapDao")
public class KeyPersonTwoDimensionMapDaoImpl extends AbstractDao implements
		KeyPersonTwoDimensionMapDao {
	@Autowired
	private CacheService cacheService;

	@Override
	public PageInfo<KeyPersonInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord, String shardCode) {
		Map<String, Object> map = getMap(orgInternalCode, typeName, sidx, sord,
				screenCoordinateVo, null, null, "false", shardCode);
		return queryForPageInfo(
				"keyPersonTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
				"keyPersonTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
				pageNum, pageSize, map);
	}

	@Override
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, String shardCode) {
		Map<String, Object> map = getStatisticParamMap(orgInternalCode,
				typeName, shardCode);
		return (Integer) queryForCount(
				"keyPersonTwoDimensionMap.countTwoDimensionMapInfoByOrgInternalCodeAndTypeName",
				map);
	}

	@Override
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, String shardCode) {
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						MemCacheConstant.COUNT_TWODIMENSIONMAPPAGEINFOBYORGINTERNALCODEANDSCREENCOORDINATEVOANDTYPENAME_POINT,
						typeName, orgInternalCode == null ? "null"
								: orgInternalCode);
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		count = null;
		if (count == null) {
			Map<String, Object> map = getStatisticParamMap(orgInternalCode,
					typeName, shardCode);
			count = (Integer) queryForCount(
					"keyPersonTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
					map);
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);

		}
		return count;
	}

	@Override
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, String typeName, String shardCode) {
		Map<String, Object> map = getMap(orgInternalCode, typeName, null, null,
				screenCoordinateVo, searchValue, null, null, shardCode);
		return (Integer) queryForCount(
				"keyPersonTwoDimensionMap.statisticTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue",
				map);
	}

	@Override
	public PageInfo<KeyPersonInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord, String shardCode) {
		Map<String, Object> map = getMap(orgInternalCode, typeName, null, null,
				null, null, null, "false", shardCode);
		return queryForPageInfo(
				"keyPersonTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName",
				"keyPersonTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName",
				pageNum, pageSize, map);
	}

	@Override
	public PageInfo<KeyPersonInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, List list, String shardCode) {

		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				screenCoordinateVo, searchValue, null, "false", shardCode);
		map.put("personList", list);
		return queryForPageInfo(
				"keyPersonTwoDimensionMap.countPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
				"keyPersonTwoDimensionMap.findPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
				pageNum, pageSize, map);
	}

	@Override
	public PageInfo<KeyPersonInfoVo> findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord, String shardCode) {
		Map<String, Object> map = getPeripheryMap(circumInfoVo, sidx, sord);
		map.put("shardCode", shardCode);
		return queryForPageInfo(
				"keyPersonTwoDimensionMap.countTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat",
				"keyPersonTwoDimensionMap.findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat",
				pageNum, pageSize, map);
	}

	/**
	 * 获取周边信息放入map
	 * 
	 * @param moduleType
	 * @param coordinates
	 * @param range
	 * @param sidx
	 * @param sord
	 * @return map
	 */
	private Map<String, Object> getPeripheryMap(CircumInfoVo circumInfoVo,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("circumInfoVo", circumInfoVo);
		map.put("PI", BaiduMapUtil.PI);
		map.put("R", BaiduMapUtil.R);
		return map;
	}

	@Override
	public Integer countTwoDimensionMapPageInfoByOrgId(String orgInternalCode,
			String tableName, String shardCode) {
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						MemCacheConstant.COUNT_TWODIMENSIONMAPPAGEINFOBYORGINTERNALCODEANDTYPENAME_POINT,
						tableName, orgInternalCode == null ? "null"
								: orgInternalCode);
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		if (count == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgInternalCode", orgInternalCode);
			if (ShardTables.isShardTables(tableName) && shardCode != null) {
				map.put("typeName", tableName + "_" + shardCode);
			} else {
				map.put("typeName", tableName);
			}
			map.put("shardCode", shardCode);
			count = (Integer) queryForCount(
					"keyPersonTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName",
					map);
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);
		}
		return count;
	}

	@Override
	public Integer countGeneralCategoryTwoDimensionMapInfoByOrgInternalCode(
			String orgInternalCode) {
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"keyPersonTwoDimensionMap.countGeneralCategoryTwoDimensionMapInfoByOrgInternalCode",
						orgInternalCode);
	}

	@Override
	public List<KeyPersonInfoVo> findKeyPersonInfoVosByOrgCodeAndScreenCoordinateVoAndTypeName(
			String orgCode, ScreenCoordinateVo screenCoordinateVo,
			String typeName, String shardCode) {
		Map<String, Object> map = getMap(orgCode, typeName, null, null,
				screenCoordinateVo, null, null, "true", shardCode);
		return queryForList(
				"keyPersonTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
				map);
	}

	@Override
	public KeyPersonInfoVo getViewPopupInfoByIdAndTableName(Long id,
			String tableName, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		if(ShardTables.isShardTables(tableName)){
			map.put("tableName", tableName + "_" + shardCode);
		}else{
			map.put("tableName", tableName);
		}
		map.put("shardCode", shardCode);
		return (KeyPersonInfoVo) getSqlMapClientTemplate().queryForObject(
				"keyPersonTwoDimensionMap.getViewPopupInfoByIdAndType", map);
	}

}
