package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.dao.KeyPlaceTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.service.SysGisFunctionService;

/**
 * 二维地图 重点场所周边搜索方法的实现
 * 
 * @author jiangjinling
 * 
 */
@Service("keyPlaceMapCircumSearchService")
public class KeyPlaceCircumSearchServiceImpl extends
		AbstractCircumSearchService<KeyPlaceInfoVo> {

	@Autowired
	private KeyPlaceTwoDimensionMapDao 	keyPlaceTwoDimensionMapDao;
	@Autowired
	private SysGisFunctionService sysGisFunctionService;

	@Override
	protected PageInfo<KeyPlaceInfoVo> doFindCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum,Integer pageSize, String sidx, String sord) {
		PageInfo<KeyPlaceInfoVo> pageInfo = keyPlaceTwoDimensionMapDao
				.findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat(
						circumInfoVo, pageNum, pageSize,sidx, sord);

		for (int i = 0; i < pageInfo.getResult().size(); i++) {// 得到keyPlaceInfoVo的typeName
			GisTypeManage gisTypeManage = new GisTypeManage();
			gisTypeManage.setKey(pageInfo.getResult().get(i).getType());

			gisTypeManage = sysGisTypeManageService
					.getGisTypeManagesByKey(gisTypeManage);
			GisFunction gisFunction = sysGisFunctionService
					.getGisFunctionBySonClassIdAndFunctionType(
							gisTypeManage.getId(), circumInfoVo.getFunctionType());

			String typeName = gisTypeManage.getName();
			pageInfo.getResult().get(i).setTypeName(typeName);
			pageInfo.getResult().get(i).setViewUrl(gisFunction.getDetailsUrl());
		}
		return pageInfo;
	}
}