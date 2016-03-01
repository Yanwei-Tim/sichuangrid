package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.dao.AbstractDao;
import com.tianque.openLayersMap.dao.CityComponentsTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.CityComponentsInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.util.BaiduMapUtil;

@SuppressWarnings({ "unchecked" })
@Repository("cityComponentsTwoDimensionMapDao")
public class CityComponentsTwoDimensionMapDaoImpl extends AbstractDao implements CityComponentsTwoDimensionMapDao {
	
	@Override
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCode(String orgInternalCode,String partType,String partName) {
		Map map = new HashMap();
		map.put("orgInternalCode", orgInternalCode);
		if("hasVideo".equals(partType)){
			map.put("hasVideo", 1);//有视频流的
			partType=null;
		}else if("".equals(partType)||partType==null||"null".equals(partType)){
			partType=null;
		}
		if("".equals(partName)||partName==null||"null".equals(partName)){
			partName=null;
		}
		map.put("partType", partType);
		map.put("partName", partName);
		return (Integer) queryForCount(
				"cityComponentsTwoDimensionMap.countBoundedTwoDimensionMapInfoByOrgInternalCode", map);
	}

	@Override
	public PageInfo<CityComponentsInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo(String orgInternalCode,
			ScreenCoordinateVo screenCoordinateVo, Integer pageNum, Integer pageSize, String sidx, String sord,String partType,String partName) {

		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				screenCoordinateVo, null, null, "false", null);
		if("hasVideo".equals(partType)){
			map.put("hasVideo", 1);//有视频流的
			partType=null;
		}else if("".equals(partType)||partType==null||"null".equals(partType)){
			partType=null;
		}
		if("".equals(partName)||partName==null||"null".equals(partName)){
			partName=null;
		}
		map.put("partType", partType);
		map.put("partName", partName);
		return queryForPageInfo(
				"cityComponentsTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
				"cityComponentsTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo", 
				pageNum, pageSize, map);
	}
	
	@Override
	public PageInfo<CityComponentsInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(String mainTableName,
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo, String searchValue, Integer pageNum, Integer pageSize,
			String sidx, String sord) {

		Map<String, Object> map = getMap(orgInternalCode, null, sidx, sord,
				screenCoordinateVo, searchValue, null, "false", null);
		if("dustbinHasVideo".equals(mainTableName)){
			map.put("hasVideo", 1);
		}

		return queryForPageInfo(
				"cityComponentsTwoDimensionMap.countTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue",
				"cityComponentsTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue", pageNum,
				pageSize, map);
	}
	
	@Override
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(String tableName,String orgInternalCode, String searchValue) {
		Map<String, Object> map = getMap(orgInternalCode, null, null, null,
				null, searchValue, null, null, null);
		if("dustbinHasVideo".equals(tableName)){
			map.put("hasVideo", 1);
		}
		return (Integer) queryForCount(
				"cityComponentsTwoDimensionMap.countBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue", map);
	}
	
	@Override
	public boolean updateTwoDimensionMap(CityComponentsInfoVo cityComponentsInfoVo) {
		return updateForBind("cityComponentsTwoDimensionMap.updateTwoDimensionMap", cityComponentsInfoVo) > 0;
	}
	
	@Override
	public CityComponentsInfoVo getViewPopupInfoById(Long id) {
		return (CityComponentsInfoVo) getSqlMapClientTemplate().queryForObject("cityComponentsTwoDimensionMap.getViewPopupInfoById", id);
	}
	
	@Override
	public Integer statisticTwoDimensionMapInfoSumByOrgInternalCode(String orgInternalCode) {
		Integer sumNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"cityComponentsTwoDimensionMap.countTwoDimensionMapInfoSumByOrgInternalCode", orgInternalCode);
		return sumNum;
	}

	@Override
	public List<CityComponentsInfoVo> findCityComponentsInfoVosByOrgCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo) {
		Map<String, Object> map = getMap(orgInternalCode, null, null, null,
				screenCoordinateVo, null, null, null, null);
		return queryForList("cityComponentsTwoDimensionMap.findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo",
				map);
	}

	@Override
	public PageInfo<CityComponentsInfoVo> findTwoDimensionMapCircumInfoByRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize, String sidx, String sord) {
		try{
			Map<String, Object> map = new HashMap();
			map.put("circumInfoVo", circumInfoVo);
			map.put("PI", BaiduMapUtil.PI);
			map.put("R", BaiduMapUtil.R);
			map.put("sidx", sidx);
			map.put("sord", sord);
			map.put("pageNum", pageNum);
			map.put("pageSize", pageSize);
			if(circumInfoVo.getElements().contains("-1")){//有视频监控
				map.put("hasVideo", 1);
			}
			List<CityComponentsInfoVo> list = queryForList(
					"cityComponentsTwoDimensionMap.findTwoDimensionMapCircumInfoByRangeAndCenterLonLat", map, pageNum, pageSize);
			return new PageInfo<CityComponentsInfoVo>(pageSize, pageSize, list.size(), list);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
