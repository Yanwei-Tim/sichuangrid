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
import com.tianque.openLayersMap.dao.PublicSecurityTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.PublicSecurityInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.util.BaiduMapUtil;

/**
 * 二维地图公安部件dao方法的实现
 * 
 * 
 */
@Repository("publicSecurityTwoDimensionMapDao")
public class PublicSecurityTwoDimensionMapDaoImpl extends AbstractDao implements
		PublicSecurityTwoDimensionMapDao {

	@Autowired
	private CacheService cacheService;

	@Override
	public PageInfo<PublicSecurityInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		Map<String, Object> map = getMap(orgInternalCode, typeName, sidx, sord,
				screenCoordinateVo, null, null, "false", null);
		return queryForPageInfo(
				"publicSecurityTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
				"publicSecurityTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
				pageNum, pageSize, map);
	}

	@Override
	public PageInfo<PublicSecurityInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, List list) {
		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				screenCoordinateVo, searchValue, null, "false", null);
		map.put("publicList", list);

		return queryForPageInfo(
				"publicSecurityTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue",
				"publicSecurityTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue",
				pageNum, pageSize, map);
	}

	@Override
	public PublicSecurityInfoVo getViewPopupInfoByIdAndTableName(Long id,
			String tableName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("tableName", tableName);
		return (PublicSecurityInfoVo) getSqlMapClientTemplate()
				.queryForObject(
						"publicSecurityTwoDimensionMap.getViewPopupInfoByIdAndTableName",
						map);
	}

	@Override
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName) {
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						MemCacheConstant.COUNT_TWODIMENSIONMAPPAGEINFOBYORGINTERNALCODEANDSCREENCOORDINATEVO,
						typeName, orgInternalCode == null ? "null"
								: orgInternalCode, "true");
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		if (count == null) {
			Map<String, Object> map = getStatisticParamMap(orgInternalCode,
					typeName, null);
			map.put("isBound", "true");
			count = (Integer) queryForCount(
					"publicSecurityTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
					map);
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);

		}
		return count;
	}

	@Override
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, String typeName) {
		Map<String, Object> map = getMap(orgInternalCode, typeName, null, null,
				screenCoordinateVo, searchValue, null, "true", null);
		return (Integer) queryForCount(
				"publicSecurityTwoDimensionMap.statisticTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue",
				map);
	}

	@Override
	public boolean updateTwoDimensionMap(
			PublicSecurityInfoVo publicSecurityInfoVo) {
		return updateForBind(
				"publicSecurityTwoDimensionMap.updateTwoDimensionMap",
				publicSecurityInfoVo) > 0;
	}

	@Override
	public PageInfo<PublicSecurityInfoVo> findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = getPeripheryMap(circumInfoVo, sidx, sord);
		return queryForPageInfo(
				"publicSecurityTwoDimensionMap.countTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat",
				"publicSecurityTwoDimensionMap.findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat",
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
		map.put("typeList", circumInfoVo.getElements().replaceAll("\'", "")
				.split(","));
		map.put("PI", BaiduMapUtil.PI);
		map.put("R", BaiduMapUtil.R);
		return map;
	}
}
