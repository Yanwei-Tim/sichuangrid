package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.service.Gis2DLayerService;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;

@Service("gridLayerMapStatisticService")
public class GridLayerMapStatisticServiceImpl extends
		AbstractTownshipsUpStatisticService {
	@Autowired
	private Gis2DLayerService gis2DLayerService;

	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo, new Callback() {
					private PropertyDict gridDict = null;

					private PropertyDict getGridDict() {
						if (gridDict == null) {
							List<PropertyDict> dictList = propertyDictService
									.findPropertyDictByDomainNameAndInternalId(
											PropertyTypes.ORGANIZATION_LEVEL,
											OrganizationLevel.GRID);
							if (dictList != null && dictList.size() > 0) {
								gridDict = dictList.get(dictList.size() - 1);
							}
						}
						return gridDict;
					}

					@Override
					public Integer call(Gis2DLayer gis2dLayer, SearchInfoVo searchVo) {
						return gis2DLayerService.countByOrgCodeAndOrgLevel(gis2dLayer.getOrgInternalCode(),
										getGridDict().getId());
					}
				});
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForScreenBySearchVo(SearchInfoVo searchVo) {
		// TODO Auto-generated method stub
		return null;
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
