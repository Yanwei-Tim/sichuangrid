package com.tianque.openLayersMap.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.AbstractDao;
import com.tianque.openLayersMap.dao.KeyPlaceTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.util.BaiduMapUtil;
import com.tianque.openLayersMap.util.ModuleMap;

/**
 * 二维地图 重点场所dao方法的实现
 * 
 * @author jiangjinling
 */
@SuppressWarnings({ "unchecked" })
@Repository("keyPlaceTwoDimensionMapDao")
public class KeyPlaceTwoDimensionMapDaoImpl extends AbstractDao implements
		KeyPlaceTwoDimensionMapDao {

	@Autowired
	private CacheService cacheService;

	@Override
	public PageInfo<KeyPlaceInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = getMap(orgInternalCode, typeName, null, null,
				null, null, null, "false", null);

		return queryForPageInfo(
				"keyPlaceTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName",
				"keyPlaceTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName",
				pageNum, pageSize, map);
	}

	@Override
	public Integer countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName) {
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						MemCacheConstant.COUNT_TWODIMENSIONMAPPAGEINFOBYORGINTERNALCODEANDTYPENAME_COMPANYPLACE,
						typeName, orgInternalCode == null ? "null"
								: orgInternalCode);
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		if (count == null) {
			Map<String, Object> map = getStatisticParamMap(orgInternalCode,
					typeName, null);
			count = (Integer) queryForCount(
					"keyPlaceTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName",
					map);
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);
		}
		return count;
	}

	@Override
	public PageInfo<KeyPlaceInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord) {

		Map<String, Object> map = getMap(orgInternalCode, typeName, sidx, sord,
				screenCoordinateVo, null, null, "false", null);

		return queryForPageInfo(
				"keyPlaceTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
				"keyPlaceTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
				pageNum, pageSize, map);
	}

	@Override
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue, List<String> typeList) {

		Map<String, Object> map = getMap(orgInternalCode, null, null, null,
				null, searchValue, null, "false", null);
		map.put("typeList", typeList);
		return (Integer) queryForCount(
				"keyPlaceTwoDimensionMap.countTwoDimensionMapInfoByOrgInternalCodeAndSearchValueAndCheck",
				map);
	}

	@Override
	public PageInfo<KeyPlaceInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			List<String> typeList, String searchValue, Integer pageNum,
			Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				screenCoordinateVo, searchValue, null, "false", null);
		map.put("typeList", typeList);
		return queryForPageInfo(
				"keyPlaceTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValueAndCheck",
				"keyPlaceTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValueAndCheck",
				pageNum, pageSize, map);
	}

	@Override
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName(
			String orgInternalCode, String typeName) {
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						MemCacheConstant.COUNT_TWODIMENSIONMAPINFOBYORGINTERNALCODEANDSCREENCOORDINATEVOANDTYPENAME_COMPANYPLACE,
						typeName, orgInternalCode == null ? "null"
								: orgInternalCode);
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		if (count == null) {
			Map<String, Object> map = getStatisticParamMap(orgInternalCode,
					typeName, null);
			count = (Integer) queryForCount(
					"keyPlaceTwoDimensionMap.countTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
					map);
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);
		}
		return count;
	}

	@Override
	public boolean updateTwoDimensionMap(KeyPlaceInfoVo keyPlaceInfoVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", keyPlaceInfoVo.getId());
		map.put("lon", keyPlaceInfoVo.getLon());
		map.put("lat", keyPlaceInfoVo.getLat());
		map.put("lon2", keyPlaceInfoVo.getLon2());
		map.put("lat2", keyPlaceInfoVo.getLat2());
		map.put("buildDataId", keyPlaceInfoVo.getBuildDataId());
		map.put("buildingId", keyPlaceInfoVo.getBuildingId());
		map.put("type", keyPlaceInfoVo.getType());
		if (ModulTypes.allCompanyPlaceMapKey.contains(keyPlaceInfoVo.getType())) {
			map.remove("type");
			map.put("allType", ModulTypes.allCompanyPlaceMapKey);
			return updateForBind(
					"keyPlaceTwoDimensionMap.updateTwoDimensionMap", map) > 0;
		} else {
			return updateForBind(
					"keyPlaceTwoDimensionMap.updateTwoDimensionMap", map) > 0;
		}
	}

	@Override
	public PageInfo<KeyPlaceInfoVo> findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {

		Map<String, Object> map = getPeripheryMap(circumInfoVo, sidx, sord);

		return queryForPageInfo(
				"keyPlaceTwoDimensionMap.countTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat",
				"keyPlaceTwoDimensionMap.findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat",
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
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName) {

		Map<String, Object> map = getStatisticParamMap(orgInternalCode,
				typeName, null);
		return (Integer) queryForCount(
				"keyPlaceTwoDimensionMap.countTwoDimensionMapInfoByOrgInternalCodeAndTypeName",
				map);
	}

	@Override
	public List<KeyPlaceInfoVo> findKeyPlaceInfoVosByOrgCodeAndScreenCoordinateVo(
			String orgCode, ScreenCoordinateVo screenCoordinateVo) {
		Map<String, Object> map = getMap(orgCode, null, null, null,
				screenCoordinateVo, null, null, "true", null);
		return queryForList(
				"keyPlaceTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
				map);
	}

	@Override
	public List<KeyPlaceInfoVo> findKeyPlaceInfoVosByOrgCodeAndMaxAndMinLonLatArrys(
			String orgCode, Double[] maxAndMinLonLatArrys, String typeName) {
		Map<String, Object> map = getMap(orgCode, typeName, null, null,
				new ScreenCoordinateVo(maxAndMinLonLatArrys[2],
						maxAndMinLonLatArrys[0], maxAndMinLonLatArrys[3],
						maxAndMinLonLatArrys[1]), null, null, "true", null);
		return queryForList(
				"keyPlaceTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName",
				map);
	}

	@Override
	public PageInfo<KeyPlaceInfoVo> findUnBoundKeyPlaceByOrgInternalCodeAndType(
			String orgInternalCode, String type, Integer page, Integer rows,
			String sidx, String sord, String queryStr) {
		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				null, queryStr, null, "false", null);
		map.put("type", type);

		return queryForPageInfo(
				"keyPlaceTwoDimensionMap.countUnBoundKeyPlaceByOrgInternalCodeAndType",
				"keyPlaceTwoDimensionMap.findUnBoundKeyPlaceByOrgInternalCodeAndType",
				page, rows, map);
	}

	@Override
	public KeyPlaceInfoVo getViewPopupInfoByIdAndType(Long id, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		return (KeyPlaceInfoVo) getSqlMapClientTemplate().queryForObject(
				"keyPlaceTwoDimensionMap.getViewPopupInfoByIdAndType", map);
	}

	@Override
	public Map<String, Integer> countKeyPlacesByCenterLonLat(String centerLon,
			String centerLat, String gisType) {
		if (!StringUtil.isStringAvaliable(centerLon)
				|| !StringUtil.isStringAvaliable(centerLat)) {
			throw new BusinessValidationException("经纬度信息不能为空！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("centerLon", centerLon);
		map.put("centerLat", centerLat);
		map.put("types", initTypes());
		map.put("gisType", gisType);
		return (Map<String, Integer>) getSqlMapClientTemplate().queryForMap(
				"keyPlaceTwoDimensionMap.countKeyPlacesByCenterLonLat", map,
				"key", "value");
	}

	@Override
	public PageInfo<KeyPlaceInfoVo> findBoundKeyPlaceByOrgInternalCodeAndTypeName(
			String orgInternalCode, Long buildDataId, String typeName,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = getMap(orgInternalCode, typeName, sidx, sord,
				null, null, buildDataId, "false", null);
		return queryForPageInfo(
				"keyPlaceTwoDimensionMap.countBoundKeyPlaceByOrgInternalCodeAndTypeName",
				"keyPlaceTwoDimensionMap.findBoundKeyPlaceByOrgInternalCodeAndTypeName",
				pageNum, pageSize, map);
	}

	/**
	 * 初始化场所的子类类型
	 * 
	 * @return List<String>
	 */
	private List<String> initTypes() {
		List<String> types = new ArrayList<String>();
		types.add(ModuleMap.ENTERPRISE_ABOVE.getModuleType());
		types.add(ModuleMap.ENTERPRISE_PROTECTION_KEY.getModuleType());
		types.add(ModuleMap.ENTERPRISE_SAFETY_PRODUCTION_KEY.getModuleType());
		types.add(ModuleMap.ENTERPRISE_FIRE_SAFETY_KEY.getModuleType());
		types.add(ModuleMap.ENTERPRISE_SECURITY_KEY.getModuleType());
		types.add(ModuleMap.PLACE_LETTING_HOUSE.getModuleType());
		types.add(ModuleMap.PLACE_SCHOOL.getModuleType());
		types.add(ModuleMap.PLACE_RELIGION.getModuleType());
		types.add(ModuleMap.PLACE_HOSPITAL.getModuleType());
		types.add(ModuleMap.PLACE_ADMINISTRATIVE_INSTITUTION.getModuleType());
		types.add(ModuleMap.PLACE_OTHER_LOCALE.getModuleType());

		return types;
	}

	@Override
	public Map<String, Integer> countKeyPlacesByHourseId(Long hourseId) {
		if (hourseId == null) {
			throw new BusinessValidationException("经纬度信息不能为空！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("types", initTypes());
		map.put("hourseId", hourseId);
		return (Map<String, Integer>) getSqlMapClientTemplate().queryForMap(
				"keyPlaceTwoDimensionMap.countKeyPlacesByHourseId", map, "key",
				"value");

	}

}
