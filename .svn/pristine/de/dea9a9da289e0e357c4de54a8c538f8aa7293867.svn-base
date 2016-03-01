package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.openLayersMap.dao.PublicSecurityTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;

/**
 * 二维地图 公共部件统计方法的实现
 * 
 */
@Service("publicSecurityMapStatisticService")
public class PublicSecurityMapStatisticServiceImpl extends
		AbstractTownshipsUpStatisticService {

	@Autowired
	private PublicSecurityTwoDimensionMapDao publicSecurityTwoDimensionMapDao;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;

	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo, new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer, SearchInfoVo searchVo) {
						return publicSecurityTwoDimensionMapDao
								.statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
										gis2dLayer.getOrgInternalCode(),
										searchVo.getTypeName());
					}
				});
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForScreenBySearchVo(SearchInfoVo searchVo){
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo, new Callback(
						searchVo.getTableName()) {
					private List<GisTypeManage> gisTypeManageList = null;

					private List<GisTypeManage> getGisTypeManageList(
							String publicSecurityType) {
						if (gisTypeManageList == null) {
							GisTypeManage gisTypeManage = new GisTypeManage();
							gisTypeManage.setInnerKey(publicSecurityType);
							gisTypeManageList = sysGisTypeManageService
									.getNeedGisTypeManagesByInnerType(gisTypeManage);
						}
						return gisTypeManageList;
					}
					@Override
					public Integer call(Gis2DLayer gis2dLayer,SearchInfoVo searchVo) {
						Integer countNum = 0;
						List<GisTypeManage> gisTypeManageList = getGisTypeManageList(tempParam
								.toString());
						if (gisTypeManageList != null) {
							for (int j = 0; j < gisTypeManageList.size(); j++) {
								Integer boundNum = publicSecurityTwoDimensionMapDao
										.statisticTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
												gis2dLayer.getOrgInternalCode(),
												searchVo.getScreenVo(),
												searchVo.getSearchValue(), gisTypeManageList
														.get(j).getTableName());
								countNum = countNum + boundNum;
							}
						}
						return countNum;
					}
				});
	}

	@Override
	public List<StatisticInfoVo> statisticInfoByOrgId(Long orgId) {
		// TODO Auto-generated method stub
		return null;
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
			ScreenCoordinateVo screenCoordinateVo, Long id) {
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
