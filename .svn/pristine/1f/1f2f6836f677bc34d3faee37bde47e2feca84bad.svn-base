package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.CityComponentsTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.CityComponentsInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.util.OpenLayersGetPoints;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;

/**
 * @功能：根据条件统计城市部件的数量，包括图层、搜索
 * @edit by longzhendong
 */
@Service("cityComponentsMapStatisticService")
public class CityComponentsMapStatisticServiceImpl extends
		AbstractTownshipsUpStatisticService {

	@Autowired
	private CityComponentsTwoDimensionMapDao cityComponentsTwoDimensionMapDao;

	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo, new Callback(
				searchVo.getTableName()) {
			@Override
			public Integer call(Gis2DLayer gis2dLayer, SearchInfoVo searchVo) {
				return cityComponentsTwoDimensionMapDao
						.statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
								gis2dLayer.getOrgInternalCode(),
								searchVo.getTypeName(), tempParam.toString());
			}
		});
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForScreenBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo, new Callback(
				searchVo.getTableName()) {
			@Override
			public Integer call(Gis2DLayer gis2dLayer, SearchInfoVo searchVo) {
				return cityComponentsTwoDimensionMapDao
						.statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
								tempParam.toString(),
								gis2dLayer.getOrgInternalCode(),
								searchVo.getSearchValue());
			}
		});
	}

	/**
	 * 通过组织机构ID和坐标集合获取二维地图数据（主要应用于画区域统计）
	 * 
	 * @param orgId
	 * @param points
	 * @return Integer
	 */
	@Override
	public Integer statisticInfoByOrgIdAndPoints(
			ScreenCoordinateVo screenCoordinateVo, Long orgId) {
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		int countNum = 0;
		// 通过区域最大和最小坐标和人员类型获取地图数据。初步筛选地图数据
		List<CityComponentsInfoVo> cityComponentsInfoVos = cityComponentsTwoDimensionMapDao
				.findCityComponentsInfoVosByOrgCodeAndScreenCoordinateVo(
						org.getOrgInternalCode(), screenCoordinateVo);
		List<CityComponentsInfoVo> cityComponentsInfoVoList = new ArrayList<CityComponentsInfoVo>();
		for (int i = 0; i < cityComponentsInfoVos.size(); i++) {
			CityComponentsInfoVo cityComponentsInfoVo = cityComponentsInfoVos
					.get(i);
			// 精确筛选地图数据，将符合需求的数据放入一个新的集合
			boolean isPointInPolygon = OpenLayersGetPoints.isPointInPolygon(
					Double.valueOf(cityComponentsInfoVo.getLon()),
					Double.valueOf(cityComponentsInfoVo.getLat()),
					screenCoordinateVo.getPoints());
			if (isPointInPolygon) {
				cityComponentsInfoVoList.add(cityComponentsInfoVo);
			}
		}
		if (cityComponentsInfoVoList.size() > 0) {
			countNum = cityComponentsInfoVoList.size();
		}

		return countNum;
	}

	/**
	 * 通过组织机构ID获取二维地图数据总数（主要应用于画区域统计）
	 * 
	 * @param orgId
	 * @return Integer
	 */
	@Override
	public Integer statisticInfoSumByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		return cityComponentsTwoDimensionMapDao
				.statisticTwoDimensionMapInfoSumByOrgInternalCode(org
						.getOrgInternalCode());
	}

	/**
	 * @功能：判断是否乡镇以下
	 * @param orgId
	 * @return
	 */
	public boolean isGridDownOrganization(Long orgId) {
		Organization org = getSimpleOrgById(orgId);
		int orgLevelInternald = propertyDictService
				.getPropertyDictByDomainNameAndDictId(
						PropertyTypes.ORGANIZATION_LEVEL,
						org.getOrgLevel().getId()).getInternalId();
		if (orgLevelInternald == OrganizationLevel.GRID) {
			return true;
		}
		return false;
	}

	public Organization getSimpleOrgById(Long id) {
		return organizationDubboService.getSimpleOrgById(id);
	}

	@Override
	public List<StatisticInfoVo> statisticInfoByOrgId(Long orgId) {
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
	public List<StatisticInfoVo> statisticInfoForAreaBySearchVo(
			SearchInfoVo searchVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getStatisticPieChartInfo(Long orgId, String typeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatisticInfoVo> statisticGeneralCategoryInfoByOrgId(Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}
}
