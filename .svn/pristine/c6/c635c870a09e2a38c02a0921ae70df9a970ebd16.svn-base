package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.openLayersMap.dao.CommonTwoDimensionMapManageDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisModuleVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.service.SysGisFunctionService;
import com.tianque.openLayersMap.service.SysModuleManageService;
import com.tianque.openLayersMap.util.FunctionType;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;

/**
 * 公共乡镇级别以上统计
 * 
 * @author 张忠祥(zhangzhongxiang@hztianque.com)
 * @date 2013-3-18
 */
@Service("commonMapStatisticService")
public class CommonTownshipsUpStaticServiceImpl extends
		AbstractCommonTownshipsUpStaticService {

	@Autowired
	@Qualifier("commonTwoDimensionMapManageDao")
	private CommonTwoDimensionMapManageDao commonTwoDimensionMapManageDao;
	@Autowired
	private SysModuleManageService sysModuleManageService;
	@Autowired
	private SysGisFunctionService sysGisFunctionService;

	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(SearchInfoVo searchVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForScreenBySearchVo(SearchInfoVo searchVo) {
		List<Gis2DLayer> gis2DLayers = getGis2DLayers(searchVo.getOrgId(), searchVo.getScreenVo());
		GisModuleVo gisModuleVo = sysModuleManageService
				.getModuleByTableName(tableName);
		GisFunction gisFunction = sysGisFunctionService
				.getGisFunctionByModuleIdAndFunctionType(gisModuleVo.getId(),
						FunctionType.REFINESEARCH);

		List<StatisticInfoVo> gridSatisticInfoList = new ArrayList<StatisticInfoVo>();
		for (int i = 0; i < gis2DLayers.size(); i++) {
			Gis2DLayer gis2dLayer = gis2DLayers.get(i);
			if (gis2dLayer != null && gis2dLayer.getId() != null) {
				Integer boundNum = commonTwoDimensionMapManageDao
						.countBoundedTwoDimensionMapByOrgInternalCodeAndTablenameAndSearchValue(
								gis2dLayer.getOrgInternalCode(), tableName,
								searchVo.getSearchValue(), gisFunction.getSearchFieldA(),
								gisFunction.getSearchFieldB(),
								gisFunction.getFunctionType(),
								searchVo.getScreenVo());

				gridSatisticInfoList.add(setStatisticInfoVoProperty(gis2dLayer,
						boundNum));
			}
		}
		return gridSatisticInfoList;
	}

	@Override
	public List<StatisticInfoVo> statisticInfoByOrgId(Long orgId) {
		List<StatisticInfoVo> gridSatisticInfoList = new ArrayList<StatisticInfoVo>();
		List<Gis2DLayer> gis2DLayers = getGis2DLayers(orgId, screenCoordinateVo);
		for (int i = 0; i < gis2DLayers.size(); i++) {
			Gis2DLayer gis2dLayer = gis2DLayers.get(i);
			if (gis2dLayer != null && gis2dLayer.getId() != null) {
				Integer boundNum = commonTwoDimensionMapManageDao
						.countBoundedTwoDimensionMapByOrgInternalCodeAndTablename(
								gis2dLayer.getOrgInternalCode(), tableName);

				gridSatisticInfoList.add(setStatisticInfoVoProperty(gis2dLayer,
						boundNum));
			}
		}
		return gridSatisticInfoList;
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForAreaBySearchVo(SearchInfoVo searchVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatisticInfoVo> statisticGeneralCategoryInfoByOrgId(Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HighchartColumnVo statisticColumnChartInfoByOrgId(Long orgId,
			String typeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer statisticInfoByOrgIdAndPoints(
			ScreenCoordinateVo screenCoordinateVo, Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer statisticInfoSumByOrgId(Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getStatisticPieChartInfo(Long orgId, String typeName) {
		// TODO Auto-generated method stub
		return null;
	}

}
