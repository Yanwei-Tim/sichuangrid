package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.HourseInfoDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.HourseInfo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.util.OpenLayersGetPoints;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;

/**
 * 房屋 统计实现类
 * 
 * @date 2013-3-18
 */
@Transactional
@Service("hourseInfoMapStatisticService")
public class HourseInfoMapStatisticServiceImpl extends
		AbstractTownshipsUpStatisticService {

	@Autowired
	private HourseInfoDao hourseInfoDao;

	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer,
							SearchInfoVo searchVo) {
						return hourseInfoDao
								.countHourseInfosTwoDimensionMapByOrgInternalCode(gis2dLayer
										.getOrgInternalCode());
					}
				});
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

	@Override
	public HighchartColumnVo statisticColumnChartInfoByOrgId(Long orgId,
			String typeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatisticInfoVo> statisticInfoByOrgId(Long orgId) {
		// TODO Auto-generated method stub
		return null;
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
		// 通过区域最大和最小坐标获取地图数据。初步筛选地图数据
		List<HourseInfo> hourseInfos = hourseInfoDao
				.findHourseInfosByOrgCodeAndScreenCoordinateVo(
						org.getOrgInternalCode(), screenCoordinateVo);
		for (HourseInfo info : hourseInfos) {
			if (null != info.getTypeId()) {
				int internalId = propertyDictService.getPropertyDictById(
						Long.valueOf(info.getTypeId())).getInternalId();
				info.getBuildDataInfoVo().getType()
						.setId(Long.valueOf(internalId));
			}
		}
		int countNum = 0;
		List<HourseInfo> hourseList = new ArrayList<HourseInfo>();
		for (int i = 0; i < hourseInfos.size(); i++) {
			HourseInfo hourseInfo = hourseInfos.get(i);
			// 精确筛选地图数据，将符合需求的数据放入一个新的集合
			boolean isPointInPolygon = false;
			if (hourseInfo != null && hourseInfo.getLon() != null
					&& hourseInfo.getLat() != null
					&& hourseInfo.getLon2() != null
					&& hourseInfo.getLat2() != null) {
				if (screenCoordinateVo.getSearchInfoVo().getGisType()
						.equals("3D")) {
					isPointInPolygon = OpenLayersGetPoints.isPointInPolygon(
							Double.valueOf(hourseInfo.getLon()),
							Double.valueOf(hourseInfo.getLat()),
							screenCoordinateVo.getPoints());
				} else if (screenCoordinateVo.getSearchInfoVo().getGisType()
						.equals("2D")) {
					isPointInPolygon = OpenLayersGetPoints.isPointInPolygon(
							Double.valueOf(hourseInfo.getLon2()),
							Double.valueOf(hourseInfo.getLat2()),
							screenCoordinateVo.getPoints());
				}

			}

			if (isPointInPolygon) {
				hourseList.add(hourseInfo);
			}
		}
		if (hourseList.size() > 0) {
			countNum = hourseList.size();
		}
		return countNum;
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForScreenBySearchVo(
			SearchInfoVo searchVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForAreaBySearchVo(
			SearchInfoVo searchVo) {
		// TODO Auto-generated method stub
		return null;
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
			throw new BusinessValidationException("orgId不能为空!");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		return hourseInfoDao
				.countHourseInfosTwoDimensionMapByOrgInternalCode(org
						.getOrgInternalCode());
	}

}
