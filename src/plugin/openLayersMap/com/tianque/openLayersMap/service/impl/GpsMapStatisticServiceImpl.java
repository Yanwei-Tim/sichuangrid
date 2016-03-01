package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.openLayersMap.dao.GpsTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.sysadmin.service.PermissionService;

/**
 * @功能：根据条件统计视频的数量，包括图层、搜索
 */
@Service("gpsMapStatisticService")
public class GpsMapStatisticServiceImpl extends
		AbstractTownshipsUpStatisticService {

	@Autowired
	private GpsTwoDimensionMapDao gpsTwoDimensionMapDao;
	@Autowired
	private PermissionService permissionService;

	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo, new Callback() {
				@Override
				public Integer call(Gis2DLayer gis2dLayer,SearchInfoVo searchVo) {

						StringBuffer stringBuffer = new StringBuffer("'null'");
						Organization organization = new Organization();
						organization.setOrgInternalCode(gis2dLayer
								.getOrgInternalCode());
						User user = new User();
						user.setOrganization(organization);
						List<User> userList = permissionService
								.findUsersBylockStatus("0", user, 1, 10000,
										null, null).getResult();
						for (User domain : userList) {
							stringBuffer.append(",'" + domain.getUserName()
									+ "'");
						}
						return gpsTwoDimensionMapDao
								.statisticTwoDimensionMapInfoByUserNameAndTypeName(
										stringBuffer.toString(), searchVo.getTypeName());
					}
				});
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForScreenBySearchVo(SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo, new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer,SearchInfoVo searchVo) {
						String searchValue = searchVo.getSearchValue();
						StringBuffer stringBuffer = new StringBuffer("'"
								+ searchValue + "'");
						Organization organization = new Organization();
						organization.setOrgInternalCode(gis2dLayer
								.getOrgInternalCode());
						User user = new User();
						user.setOrganization(organization);
						user.setName(searchValue);
						List<User> userList = permissionService
								.findUsersBylockStatus("0", user, 1, 10000,
										null, null).getResult();
						if (searchValue != null
								&& !searchValue.trim().equals("")) {
							user.setName(null);
							user.setUserName(searchValue);
							userList.addAll(permissionService
									.findUsersBylockStatus("0", user, 1, 10000,
											null, null).getResult());
						}
						for (User domain : userList) {
							stringBuffer.append(",'" + domain.getUserName()
									+ "'");
						}
						return gpsTwoDimensionMapDao
								.statisticTwoDimensionMapInfoByUserNameAndTypeName(
										stringBuffer.toString(), null);
					}
				});
	}

	@Override
	public List<Object[]> getStatisticPieChartInfo(Long orgId, String typeName) {
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
	public List<StatisticInfoVo> statisticGeneralCategoryInfoByOrgId(Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatisticInfoVo> statisticInfoByOrgId(Long orgId) {
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
	public List<StatisticInfoVo> statisticInfoForAreaBySearchVo(SearchInfoVo searchVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer statisticInfoSumByOrgId(Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

}
