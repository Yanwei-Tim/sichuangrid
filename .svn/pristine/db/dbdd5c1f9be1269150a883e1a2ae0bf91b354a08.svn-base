package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.AbstractDao;
import com.tianque.openLayersMap.dao.BuildDataTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

/**
 * 二维地图楼宇dao方法的实现
 * 
 * @author zhanghuafei
 */
@SuppressWarnings({ "unchecked" })
@Repository("buildDataTwoDimensionMapDao")
public class BuildDataTwoDimensionMapDaoImpl extends AbstractDao implements
		BuildDataTwoDimensionMapDao {

	@Override
	public PageInfo<BuildDataInfoVo> findBoundedTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orginternalcode", orgInternalCode);
		map.put("sortField", sidx);
		map.put("order", sord);
		return queryForPageInfo(
				"buildDataTwoDimensionMap.countBoundedTwoDimensionMapPageInfoByOrgInternalCode",
				"buildDataTwoDimensionMap.findBoundedTwoDimensionMapPageInfoByOrgInternalCode",
				pageNum, pageSize, map);
	}

	@Override
	public Integer countBoundedTwoDimensionMapPageInfoByOrgInternalCode(String orgInternalCode) {

		Map<String, Object> map = getStatisticParamMap(orgInternalCode, null,
				null);
		return (Integer) queryForCount(
				"buildDataTwoDimensionMap.countBoundedTwoDimensionMapPageInfoByOrgInternalCode",
				map);
	}

	@Override
	public Integer countUnBoundedTwoDimensionMapPageInfoByOrgInternalCode(String orgInternalCode) {

		Map<String, Object> map = getStatisticParamMap(orgInternalCode, null,
				null);
		return (Integer) queryForCount(
				"buildDataTwoDimensionMap.countUnBoundedTwoDimensionMapPageInfoByOrgInternalCode",
				map);
	}

	@Override
	public PageInfo<BuildDataInfoVo> findUnBoundTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				null, null, null, null, null);
		return queryForPageInfo(
				"buildDataTwoDimensionMap.countUnBoundTwoDimensionMapPageInfoByOrgInternalCode",
				"buildDataTwoDimensionMap.findUnBoundTwoDimensionMapPageInfoByOrgInternalCode",
				pageNum, pageSize, map);
	}

	@Override
	public BuildDataInfoVo updateBuildDataTwoDimensionMap(BuildDataInfoVo buildDataInfoVo) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("centerLon", buildDataInfoVo.getLon());
		map.put("centerLat", buildDataInfoVo.getLat());
		map.put("centerLon2", buildDataInfoVo.getLon2());
		map.put("centerLat2", buildDataInfoVo.getLat2());
		map.put("buildingid", buildDataInfoVo.getFeatureId());
		map.put("id", buildDataInfoVo.getId());
		map.put("tableName", "builddatas");
		updateForBind("buildDataTwoDimensionMap.updateBuildDataTwoDimensionMap",
				map);
		return getBuildDataInfoById(buildDataInfoVo.getId());
	}

	@Override
	public BuildDataInfoVo getBuildDataInfoById(Long id) {
		return (BuildDataInfoVo) getSqlMapClientTemplate().queryForObject(
				"buildDataTwoDimensionMap.getBuildDataInfoById", id);
	}

	@Override
	public List<BuildDataInfoVo> findBuildDataInfoVosByOrgCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo) {
		Map<String, Object> map = getMap(orgInternalCode, null, null, null,
				screenCoordinateVo, null, null, "true", null);
		List<BuildDataInfoVo> list = queryForList(
				"buildDataTwoDimensionMap.findBuildDataInfoVosByOrgCodeAndMaxAndMinLonLatArrays",
				map);
		return list;
	}

	@Override
	public Integer countBuildDataByOrgCode(String orgInternalCode) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"buildDataTwoDimensionMap.countBuildDataByOrgCode", orgInternalCode);
	}

	@Override
	public PageInfo<BuildDataInfoVo> findUnBoundBuilddatasByStr(String str, Integer pageNum,
			Integer pageSize, String sortField, String order, String orgInternalCode) {
		Map<String, Object> map = getMap(orgInternalCode, null, sortField,
				order, null, null, null, null, null);
		map.put("str", str);

		return queryForPageInfo("buildDataTwoDimensionMap.countUnBoundBuilddatas",
				"buildDataTwoDimensionMap.findUnBoundBuilddatasByStr", pageNum, pageSize, map);
	}

	@Override
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue) {

		Map<String, Object> map = getParamMap(orgInternalCode, searchValue, null, null);

		return (Integer)queryForCount(
						"buildDataTwoDimensionMap.countBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue",
						map);
	}

	@Override
	public PageInfo<BuildDataInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo, String searchValue,
			Integer pageNum, Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				screenCoordinateVo, searchValue, null, "true", null);
		return queryForPageInfo(
				"buildDataTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue",
				"buildDataTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue",
				pageNum, pageSize, map);
	}

	@Override
	public BuildDataInfoVo getBuildDataInfoVoByCenterLonLat(String centerLon, String centerLat,
			String gisType) {
		if (!StringUtil.isStringAvaliable(centerLon) || !StringUtil.isStringAvaliable(centerLat)) {
			throw new BusinessValidationException("经纬度信息不能为空！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("centerLon", centerLon);
		map.put("centerLat", centerLat);
		map.put("gisType", gisType);
		return (BuildDataInfoVo) getSqlMapClientTemplate().queryForObject(
				"buildDataTwoDimensionMap.getBuildDataInfoVoByCenterLonLat", map);
	}

	@Override
	public BuildDataInfoVo getBuildDataInfoByHourseInfoId(Long hourseInfoId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hourseInfoId", hourseInfoId.toString());
		return (BuildDataInfoVo) getSqlMapClientTemplate().queryForObject(
				"buildDataTwoDimensionMap.getBuildDataInfoByHourseInfoId", map);
	}

}
