package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.KeyPlaceTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.domian.vo.GisBoundVo;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.BuildDataService;
import com.tianque.openLayersMap.service.MapBindingManageService;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.openLayersMap.util.GisType;

/**
 * 二维地图 重点场所管理操作方法(如：绑定、解绑)的实现
 * 
 * @author jiangjinling
 * 
 */
@Service("keyPlaceMapBindingManageService")
public class KeyPlaceMapBindingManageServiceImpl extends BaseService implements
		MapBindingManageService<Boolean> {

	@Autowired
	private KeyPlaceTwoDimensionMapDao keyPlaceTwoDimensionMapDao;
	@Autowired
	private BuildDataService buildDataService;

	@Override
	public Boolean boundTwoDimensionMap(GisBoundVo gisBoundVo, String type) {
		if (gisBoundVo == null || gisBoundVo.getIds() == null || type == null) {
			throw new BusinessValidationException("参数错误!");
		}
		GisTransformatUtil.autoFillGisBound(gisBoundVo);
		boolean flag = true;
		String[] ids = gisBoundVo.getIds()[0].split(",");
		Long buildingId = null;
		Long buildDataId = gisBoundVo.getBuildDataId();
		if (buildDataId != null) {// buildDataId是建筑物id，buildingId是楼宇id
			BuildDataInfoVo buildDataInfoVo = buildDataService
					.getBuildDataInfoByBuildingid(buildDataId);
			if (buildDataInfoVo != null && buildDataInfoVo.getId() != null) {
				buildingId = buildDataInfoVo.getId();
			}
			for (String id : ids) {
				KeyPlaceInfoVo keyPlaceInfoVo = new KeyPlaceInfoVo(
						Long.valueOf(id), type, gisBoundVo.getLon(),
						gisBoundVo.getLat(), buildDataId, buildingId);
				keyPlaceInfoVo.setLon2(gisBoundVo.getLon2());
				keyPlaceInfoVo.setLat2(gisBoundVo.getLat2());
				flag = keyPlaceTwoDimensionMapDao
						.updateTwoDimensionMap(keyPlaceInfoVo);
				if (flag == false) {
					throw new BusinessValidationException("绑定失败!");
				}
			}
		}
		return flag;
	}

	@Override
	public Boolean boundTwoDimensionMap(String[] ids, String lon, String lat,
			String type, Long buildDataId, String gisType) {
		if (ids == null || type == null) {
			throw new BusinessValidationException("参数错误!");
		}
		boolean flag = true;
		ids = ids[0].split(",");
		Long buildingId = null;
		if (buildDataId != null) {// buildDataId是建筑物id，buildingId是楼宇id
			BuildDataInfoVo buildDataInfoVo = buildDataService
					.getBuildDataInfoByBuildingid(buildDataId);
			if (buildDataInfoVo != null && buildDataInfoVo.getId() != null) {
				buildingId = buildDataInfoVo.getId();
			}
			for (String id : ids) {
				if (GisType.M3D.equals(gisType)) {
					KeyPlaceInfoVo keyPlaceInfoVo = new KeyPlaceInfoVo(
							Long.valueOf(id), type, lon, lat, buildDataId,
							buildingId);
					String[] lonlat2 = GisTransformatUtil.to2DPoint(lon, lat);
					keyPlaceInfoVo.setLon2(lonlat2[0]);
					keyPlaceInfoVo.setLat2(lonlat2[1]);
					flag = keyPlaceTwoDimensionMapDao
							.updateTwoDimensionMap(keyPlaceInfoVo);
					if (flag == false) {
						throw new BusinessValidationException("绑定失败!");
					}
				} else {
					String[] lonlat3 = GisTransformatUtil.to25DPoint(lon, lat);
					KeyPlaceInfoVo keyPlaceInfoVo = new KeyPlaceInfoVo(
							Long.valueOf(id), type, lonlat3[0], lonlat3[1],
							buildDataId, buildingId);
					keyPlaceInfoVo.setLon2(lon);
					keyPlaceInfoVo.setLat2(lat);
					flag = keyPlaceTwoDimensionMapDao
							.updateTwoDimensionMap(keyPlaceInfoVo);
					if (flag == false) {
						throw new BusinessValidationException("绑定失败!");
					}
				}

			}
		}

		return flag;
	}

	@Override
	public Boolean unBoundTwoDimensionMap(Long id, String type, Long orgId) {
		if (id == null || type == null) {
			throw new BusinessValidationException("参数错误!");
		}
		KeyPlaceInfoVo keyPlaceInfoVo = new KeyPlaceInfoVo(id, type, null,
				null, null, null);
		return keyPlaceTwoDimensionMapDao.updateTwoDimensionMap(keyPlaceInfoVo);
	}

}
