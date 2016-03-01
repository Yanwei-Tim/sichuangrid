package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.dao.AbstractDao;
import com.tianque.openLayersMap.dao.GpsTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.GpsInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.util.BaiduMapUtil;

/**
 * 二维地图 GPS dao方法的实现
 */
@SuppressWarnings({ "unchecked" })
@Repository("gpsTwoDimensionMapDao")
public class GpsTwoDimensionMapDaoImpl extends AbstractDao implements GpsTwoDimensionMapDao {
	@Override
	public PageInfo<GpsInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo, String typeName,
			Integer pageNum, Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = getMap(orgInternalCode, typeName, sidx, sord,
				screenCoordinateVo, null, null, "false", null);

		return getPageInfo(
				"gpsTwoDimensionMap.countTwoDimensionMapPageInfoByScreenCoordinateVoAndSearchValue",
				"gpsTwoDimensionMap.findTwoDimensionMapPageInfoByScreenCoordinateVoAndSearchValue",
				pageNum, pageSize, map);
	}

	@Override
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue) {

		Map<String, Object> map = getMap(orgInternalCode, null, null, null,
				null, searchValue, null, "false", null);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"gpsTwoDimensionMap.countTwoDimensionMapInfoByOrgInternalCodeAndSearchValue", map);
	}

	@Override
	public PageInfo<GpsInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo, String searchValue,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				screenCoordinateVo, searchValue, null, "false", null);

		return getPageInfo(
				"gpsTwoDimensionMap.countTwoDimensionMapPageInfoByScreenCoordinateVoAndSearchValue",
				"gpsTwoDimensionMap.findTwoDimensionMapPageInfoByScreenCoordinateVoAndSearchValue",
				pageNum, pageSize, map);
	}

	@Override
	public PageInfo<GpsInfoVo> findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = getPeripheryMap(circumInfoVo, sidx, sord);

		return getPageInfo(
				"gpsTwoDimensionMap.countTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat",
				"gpsTwoDimensionMap.findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat",
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
	private Map<String, Object> getPeripheryMap(CircumInfoVo circumInfoVo, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("circumInfoVo", circumInfoVo);
		map.put("PI", BaiduMapUtil.PI);
		map.put("R", BaiduMapUtil.R);
		return map;
	}

	@Override
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndTypeName(String orgInternalCode,
			String typeName) {

		Map<String, Object> map = getStatisticParamMap(orgInternalCode,
				typeName, null);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"gpsTwoDimensionMap.countTwoDimensionMapInfoByOrgInternalCodeAndSearchValue", map);
	}

	@Override
	public GpsInfoVo getViewPopupInfoByIdAndType(Long id, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		return (GpsInfoVo) getSqlMapClientTemplate().queryForObject(
				"gpsTwoDimensionMap.getViewPopupInfoByIdAndType", map);
	}

	@Override
	public PageInfo<GpsInfoVo> findTwoDimensionMapPageInfoByScreenCoordinateVoAndSearchInfoVo(
			ScreenCoordinateVo screenCoordinateVo, SearchInfoVo searchInfoVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("screenCoordinateVo", screenCoordinateVo);
		map.put("searchInfoVo", searchInfoVo);
		map.put("sortField", sidx);
		map.put("order", sord);
		return getPageInfo(
				"gpsTwoDimensionMap.countTwoDimensionMapPageInfoByScreenCoordinateVoAndSearchInfoVo",
				"gpsTwoDimensionMap.findTwoDimensionMapPageInfoByScreenCoordinateVoAndSearchInfoVo",
				pageNum, pageSize, map);
	}

	@Override
	public Integer statisticTwoDimensionMapInfoByUserNameAndTypeName(String userName,
			String typeName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("typeName", typeName);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"gpsTwoDimensionMap.countTwoDimensionMapInfoByUserNameAndTypeName", map);
	}

	@Override
	public Map<String, Integer> countKeyPlacesByCenterLonLat(String centerLon, String centerLat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(String orgInternalCode,
			String typeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<GpsInfoVo> findBoundKeyPlaceByOrgInternalCodeAndTypeName(
			String orgInternalCode, String centerLon, String centerLat, String typeName,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GpsInfoVo> findGpsInfoVosByOrgCodeAndMaxAndMinLonLatArrys(String orgCode,
			Double[] maxAndMinLonLatArrys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GpsInfoVo> findGpsInfoVosByOrgCodeAndMaxAndMinLonLatArrys(String orgCode,
			Double[] maxAndMinLonLatArrys, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<GpsInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<GpsInfoVo> findUnBoundKeyPlaceByOrgInternalCodeAndType(String orgInternalCode,
			String type, Integer page, Integer rows, String sidx, String sord, String queryStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTwoDimensionMap(GpsInfoVo infoVo) {
		// TODO Auto-generated method stub
		return false;
	}
}
