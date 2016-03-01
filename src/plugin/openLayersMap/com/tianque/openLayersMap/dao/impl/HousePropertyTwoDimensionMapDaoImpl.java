package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.AbstractDao;
import com.tianque.openLayersMap.dao.HousePropertyTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.HousePropertyInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.util.GisGlobalValueMap;

/**
 * 二维地图 住房dao方法的实现
 * 
 * @author zhanghuafei
 */
@SuppressWarnings({ "unchecked" })
@Repository("housePropertyTwoDimensionMapDao")
public class HousePropertyTwoDimensionMapDaoImpl extends AbstractDao implements
		HousePropertyTwoDimensionMapDao {
	@Autowired
	private CacheService cacheService;

	@Override
	public PageInfo<HousePropertyInfoVo> findBoundedTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord) {

		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				null, null, null, null, null);

		return queryForPageInfo(
				"hoursePropertyTwoDimensionMap.countBoundedTwoDimensionMapPageInfoByOrgInternalCode",
				"hoursePropertyTwoDimensionMap.findBoundedTwoDimensionMapPageInfoByOrgInternalCode",
				pageNum, pageSize, map);
	}

	@Override
	public Integer countBoundedTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, String shardCode) {
		Map<String, Object> map = getStatisticParamMap(orgInternalCode, null,
				shardCode);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"hoursePropertyTwoDimensionMap.countBoundedTwoDimensionMapPageInfoByOrgInternalCode",
						map);
	}

	@Override
	public Integer countUnBoundedTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, String shardCode) {
		Map<String, Object> map = getStatisticParamMap(orgInternalCode, null,
				shardCode);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"hoursePropertyTwoDimensionMap.countUnBoundedTwoDimensionMapPageInfoByOrgInternalCode",
						map);
	}

	@Override
	public PageInfo<HousePropertyInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			int isRentalHouse, String shardCode) {
		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				screenCoordinateVo, null, null, null, shardCode);
		map.put("isRentalHouse", isRentalHouse);
		return queryForPageInfo(
				"hoursePropertyTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
				"hoursePropertyTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
				pageNum, pageSize, map);
	}

	@Override
	public PageInfo<HousePropertyInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String shardCode) {

		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				screenCoordinateVo, searchValue, null, "false", shardCode);
		return queryForPageInfo(
				"hoursePropertyTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue",
				"hoursePropertyTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue",
				pageNum, pageSize, map);
	}

	@Override
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
			String orgInternalCode, String tableName, Boolean isBound,
			String shardCode) {
		int isrentalhouse = 2;
		if (GisGlobalValueMap.RENTALHOUSE_MODE.equalsIgnoreCase(tableName)) {
			isrentalhouse = 1;
		} else if (GisGlobalValueMap.HOUSEINFO_MODE.equalsIgnoreCase(tableName)) {
			isrentalhouse = 0;
		}
		String cacheKey = MemCacheConstant
				.generateCacheKeyFromMethodName(
						Integer.class,
						tableName,
						MemCacheConstant.COUNT_BOUNDEDTWODIMENSIONMAPINFOBYORGINTERNALCODE,
						orgInternalCode == null ? "null" : orgInternalCode,
						isBound.toString());
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		if (count == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgInternalCode", orgInternalCode);
			map.put("isRentalHouse", isrentalhouse);
			map.put("isBound", isBound);
			map.put("shardCode", shardCode);
			count = (Integer) queryForCount(
					"hoursePropertyTwoDimensionMap.countBoundedTwoDimensionMapInfoByOrgInternalCode",
					map);
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);
		}
		return count;
	}

	@Override
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue, String shardCode) {

		Map<String, Object> map = getMap(orgInternalCode, null, null, null,
				null, searchValue, null, null, shardCode);
		return (Integer) this
				.getSqlMapClientTemplate()
				.queryForObject(
						"hoursePropertyTwoDimensionMap.countBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue",
						map);
	}

	@Override
	public Integer statisticBoundedTwoDimensionMapInfoByBuildingId(
			Long buildingId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buildingId", buildingId);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"hoursePropertyTwoDimensionMap.countBoundedTwoDimensionMapInfoByBuildingId",
						map);
	}

	@Override
	public void updateHousePropertyInfoVoTwoDimensionMap(
			HousePropertyInfoVo housePropertyInfoVo, String shardCode) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("centerLon", housePropertyInfoVo.getLon());
		map.put("centerLat", housePropertyInfoVo.getLat());
		map.put("builddatasId", housePropertyInfoVo.getBuilddatasId());
		map.put("centerLon2", housePropertyInfoVo.getLon2());
		map.put("centerLat2", housePropertyInfoVo.getLat2());
		map.put("shardCode", shardCode);
		getSqlMapClientTemplate()
				.update("hoursePropertyTwoDimensionMap.updateHousePropertyInfoVoByBuilddatasId",
						map);
	}

	@Override
	public HousePropertyInfoVo getHousePropertyInfoById(Long id,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("shardCode", shardCode);
		return (HousePropertyInfoVo) getSqlMapClientTemplate().queryForObject(
				"hoursePropertyTwoDimensionMap.getHousePropertyInfoById", map);
	}

	@Override
	public List<HousePropertyInfoVo> findHousePropertyInfoVosByOrgCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String shardCode) {

		Map<String, Object> map = getMap(orgInternalCode, null, null, null,
				screenCoordinateVo, null, null, "true", shardCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"hoursePropertyTwoDimensionMap.findHousePropertyInfoVosByOrgCodeAndMaxAndMinLonLatArrys",
						map);
	}

	@Override
	public Integer countHousePropertyByOrgCode(String orgInternalCode,
			String shardCode) {
		String cacheKey = MemCacheConstant.generateCacheKeyFromMethodName(
				Integer.class, MemCacheConstant.COUNT_HOUSEPROPERTYBYORGCODE,
				orgInternalCode == null ? "null" : orgInternalCode);
		Integer count = (Integer) cacheService.get(
				MemCacheConstant.MAP_NAMESPACE, cacheKey);
		if (count == null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgInternalCode", orgInternalCode);
			map.put("shardCode", shardCode);
			count = (Integer) getSqlMapClientTemplate()
					.queryForObject(
							"hoursePropertyTwoDimensionMap.countHousePropertyByOrgCode",
							map);
			cacheService.set(MemCacheConstant.MAP_NAMESPACE, cacheKey, count);
		}
		return count;
	}

	@Override
	public PageInfo<HousePropertyInfoVo> findUnBoundedTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				null, null, null, null, null);
		return queryForPageInfo(
				"hoursePropertyTwoDimensionMap.countUnBoundedTwoDimensionMapPageInfoByOrgInternalCode",
				"hoursePropertyTwoDimensionMap.findUnBoundedTwoDimensionMapPageInfoByOrgInternalCode",
				pageNum, pageSize, map);
	}

	private void setIsRentalHouse(List<HousePropertyInfoVo> list) {
		for (int i = 0; i < list.size(); i++) {
			HousePropertyInfoVo housePropertyInfoVo = list.get(i);
			if (housePropertyInfoVo.getIsRentalHouse() != null) {
				if (housePropertyInfoVo.getIsRentalHouse().equals("0"))
					housePropertyInfoVo.setIsRentalHouse("不是");
				else
					housePropertyInfoVo.setIsRentalHouse("是");
			}
		}
	}

	@Override
	public PageInfo<HousePropertyInfoVo> findHouseInfoTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, String typeName, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		int isrentalhouse = 2;
		if (GisGlobalValueMap.RENTALHOUSE_MODE.equalsIgnoreCase(typeName)) {
			isrentalhouse = 1;
		} else if (GisGlobalValueMap.HOUSEINFO_MODE.equalsIgnoreCase(typeName)) {
			isrentalhouse = 0;
		}
		map.put("orgInternalCode", orgInternalCode);
		map.put("isRentalHouse", isrentalhouse);
		map.put("sortField", null);
		map.put("order", null);
		map.put("shardCode", shardCode);
		return queryForPageInfo(
				"hoursePropertyTwoDimensionMap.countFindHouseInfoTwoDimensionMapPageInfoByOrgInternalCode",
				"hoursePropertyTwoDimensionMap.findHouseInfoTwoDimensionMapPageInfoByOrgInternalCode",
				pageNum, pageSize, map);
	}

	@Override
	public Integer countHousePropertyByBuildDataId(Long buildDataId,
			String shardCode) {
		if (buildDataId == null) {
			throw new BusinessValidationException("楼宇id不能为空！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buildDataId", buildDataId);
		map.put("shardCode", shardCode);
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"hoursePropertyTwoDimensionMap.countHousePropertyByBuildDataId",
						map);
	}

	@Override
	public List<HousePropertyInfoVo> findHousePropertysByCenterLonLat(
			String centerLon, String centerLat, String gisType) {
		if (!StringUtil.isStringAvaliable(centerLon)
				|| !StringUtil.isStringAvaliable(centerLat)) {
			throw new BusinessValidationException("经纬度信息不能为空！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("centerLon", centerLon);
		map.put("centerLat", centerLat);
		map.put("gisType", gisType);
		return getSqlMapClientTemplate()
				.queryForList(
						"hoursePropertyTwoDimensionMap.findHousePropertysByCenterLonLat",
						map);
	}

	@Override
	public PageInfo<HousePropertyInfoVo> findTwoDimensionMapPageInfoByBuildingId(
			Long buildingId, Integer pageNum, Integer pageSize, String sidx,
			String sord, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buildingId", buildingId);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("shardCode", shardCode);
		PageInfo<HousePropertyInfoVo> pageInfo = queryForPageInfo(
				"hoursePropertyTwoDimensionMap.countBoundedTwoDimensionMapInfoByBuildingId",
				"hoursePropertyTwoDimensionMap.findTwoDimensionMapPageInfoByBuildingId",
				pageNum, pageSize, map);
		setIsRentalHouse(pageInfo.getResult());
		return pageInfo;
	}

	@Override
	public List<HousePropertyInfoVo> findTwoDimensionMapByBuildingId(
			Long buildingId, String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buildingId", buildingId);
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"hoursePropertyTwoDimensionMap.findTwoDimensionMapByBuildingId",
						map);
	}
}
