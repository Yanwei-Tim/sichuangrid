package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.BuildDataTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.domian.vo.GisBoundVo;
import com.tianque.openLayersMap.domian.vo.HousePropertyInfoVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.HousePropertyService;
import com.tianque.openLayersMap.service.MapBindingManageService;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.openLayersMap.util.GisType;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 建筑物绑定、解绑
 * 
 * @date 2013-3-18
 */
@Service("buildDataMapBindingManageService")
public class BuildDataMapBindingManageServiceImpl extends BaseService implements
		MapBindingManageService<BuildDataInfoVo> {

	@Autowired
	private BuildDataTwoDimensionMapDao buildDataTwoDimensionMapDao;
	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public BuildDataInfoVo boundTwoDimensionMap(GisBoundVo gisBoundVo,
			String type) {
		if (gisBoundVo == null || gisBoundVo.getIds() == null
				|| gisBoundVo.getIds()[0] == null) {
			throw new BusinessValidationException("id不能为空");
		}
		Long id = Long.valueOf(gisBoundVo.getIds()[0]);
		BuildDataInfoVo buildDataInfoVo = new BuildDataInfoVo();
		buildDataInfoVo.setId(id);
		// 特殊说明：楼宇绑定时统一传的三维地图坐标，然后转二维
		buildDataInfoVo.setFeatureId(gisBoundVo.getBuildDataId() + "");
		GisTransformatUtil.autoFillGisBound(gisBoundVo);

		buildDataInfoVo.setLat(gisBoundVo.getLat());
		buildDataInfoVo.setLon(gisBoundVo.getLon());
		buildDataInfoVo.setLon2(gisBoundVo.getLon2());
		buildDataInfoVo.setLat2(gisBoundVo.getLat2());
		buildDataInfoVo = buildDataTwoDimensionMapDao
				.updateBuildDataTwoDimensionMap(buildDataInfoVo);
		synchronousHousePropertyInfo(id, gisBoundVo.getLon(),
				gisBoundVo.getLat(), gisBoundVo.getLon2(),
				gisBoundVo.getLat2(), gisBoundVo.getOrgId());

		if (buildDataInfoVo.getTypeId() != null
				&& !"".equals(buildDataInfoVo.getTypeId())) {
			int internalId = propertyDictService.getPropertyDictById(
					Long.valueOf(buildDataInfoVo.getTypeId())).getInternalId();
			buildDataInfoVo.getType().setId(Long.valueOf(internalId));
			buildDataInfoVo.setTypeId(internalId + "");
		}

		return buildDataInfoVo;
	}

	@Override
	public BuildDataInfoVo boundTwoDimensionMap(String[] ids, String lon,
			String lat, String type, Long buildDataId, String gisType) {
		if (ids == null || ids.length == 0 || ids[0] == null) {
			throw new BusinessValidationException("id不能为空");
		}
		BuildDataInfoVo buildDataInfoVo = new BuildDataInfoVo();
		buildDataInfoVo.setId(Long.valueOf(ids[0]));
		// 特殊说明：楼宇绑定时统一传的三维地图坐标，然后转二维
		buildDataInfoVo.setFeatureId(buildDataId + "");
		if (GisType.M3D.equals(gisType)) {// 三维坐标转二维
			buildDataInfoVo.setLat(lat);
			buildDataInfoVo.setLon(lon);
			String[] lonlat2 = GisTransformatUtil.to2DPoint(lon, lat);
			buildDataInfoVo.setLon2(lonlat2[0]);
			buildDataInfoVo.setLat2(lonlat2[1]);
			buildDataInfoVo = buildDataTwoDimensionMapDao
					.updateBuildDataTwoDimensionMap(buildDataInfoVo);
			// 方法未被调用暂注释
			// synchronousHousePropertyInfo(Long.valueOf(ids[0]), lon, lat,
			// lonlat2[0], lonlat2[1]);

		} else {// 二维坐标转三维
			String[] lonlat3 = GisTransformatUtil.to25DPoint(lon, lat);
			buildDataInfoVo.setLat2(lat);
			buildDataInfoVo.setLon2(lon);
			buildDataInfoVo.setLon(lonlat3[0]);
			buildDataInfoVo.setLat(lonlat3[1]);
			buildDataInfoVo = buildDataTwoDimensionMapDao
					.updateBuildDataTwoDimensionMap(buildDataInfoVo);
			// 方法未被调用暂注释
			// synchronousHousePropertyInfo(Long.valueOf(ids[0]), lonlat3[0],
			// lonlat3[1], lon, lat);
		}

		if (buildDataInfoVo.getTypeId() != null
				&& !"".equals(buildDataInfoVo.getTypeId())) {
			int internalId = propertyDictService.getPropertyDictById(
					Long.valueOf(buildDataInfoVo.getTypeId())).getInternalId();
			buildDataInfoVo.getType().setId(Long.valueOf(internalId));
			buildDataInfoVo.setTypeId(internalId + "");
		}

		return buildDataInfoVo;
	}

	@Override
	public BuildDataInfoVo unBoundTwoDimensionMap(Long id, String type,
			Long orgId) {
		if (id == null) {
			throw new BusinessValidationException("请选中楼宇");
		}
		BuildDataInfoVo buildDataInfoVo = new BuildDataInfoVo();
		buildDataInfoVo.setId(id);
		buildDataInfoVo = buildDataTwoDimensionMapDao
				.updateBuildDataTwoDimensionMap(buildDataInfoVo);
		if (buildDataInfoVo != null) {
			synchronousHousePropertyInfo(id, null, null, null, null, orgId);
		}
		return buildDataInfoVo;
	}

	/**
	 * 更新住房信息
	 * 
	 * @param builddatasId
	 * @param lon
	 * @param lat
	 * @param featureId
	 */
	private void synchronousHousePropertyInfo(Long builddatasId, String lon,
			String lat, String lon2, String lat2, Long orgId) {
		HousePropertyInfoVo housePropertyInfoVo = new HousePropertyInfoVo();
		housePropertyInfoVo.setBuilddatasId(builddatasId);
		housePropertyInfoVo.setLon(lon);
		housePropertyInfoVo.setLat(lat);
		housePropertyInfoVo.setLon2(lon2);
		housePropertyInfoVo.setLat2(lat2);
		housePropertyService.synchronousHousePropertyInfoTwoDimensionMap(
				housePropertyInfoVo, shardConversion.getShardCode(orgId));
	}
}
