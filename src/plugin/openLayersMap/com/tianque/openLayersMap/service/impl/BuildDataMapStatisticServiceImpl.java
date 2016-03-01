package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.BuildDataTwoDimensionMapDao;
import com.tianque.openLayersMap.dao.HousePropertyTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.openLayersMap.util.OpenLayersGetPoints;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.shard.util.ShardConversion;

/**
 * 楼宇统计
 * 
 * @date 2013-3-18
 */
@Service("buildDataMapStatisticService")
public class BuildDataMapStatisticServiceImpl extends
		AbstractTownshipsUpStatisticService {

	@Autowired
	private BuildDataTwoDimensionMapDao buildDataTwoDimensionMapDao;
	@Autowired
	private HousePropertyTwoDimensionMapDao housePropertyTwoDimensionMapDao;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer,
							SearchInfoVo searchVo) {
						return buildDataTwoDimensionMapDao
								.countBoundedTwoDimensionMapPageInfoByOrgInternalCode(gis2dLayer
										.getOrgInternalCode());
					}
				});
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForScreenBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer,
							SearchInfoVo searchVo) {
						return buildDataTwoDimensionMapDao
								.statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
										gis2dLayer.getOrgInternalCode(),
										searchVo.getSearchValue());
					}
				});
	}

	/**
	 * 通过组织机构ID统计二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @return List<StatisticInfoVo>
	 */
	@Override
	public List<StatisticInfoVo> statisticInfoByOrgId(Long orgId) {
		if (null == orgId) {
			throw new BusinessValidationException("组织机构id不能为空!");
		}
		List<StatisticInfoVo> satisticInfoVoList = new ArrayList<StatisticInfoVo>();
		Set<String> key = getModuleKeys(GisGlobalValueMap.KEY_HOUSEPROPERTY);
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		Integer countNum = 0;
		// 得到已绑定和未绑定楼宇数据
		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			String moduleName = null;
			String type = (String) it.next();
			if (type == GisGlobalValueMap.BOUNDBUILDING) {
				moduleName = GisGlobalValueMap.keyHouse
						.get(GisGlobalValueMap.BOUNDBUILDING);
				countNum = buildDataTwoDimensionMapDao
						.countBoundedTwoDimensionMapPageInfoByOrgInternalCode(organization
								.getOrgInternalCode());
			} else if (type == GisGlobalValueMap.UNBOUNDBUILDING) {
				moduleName = GisGlobalValueMap.keyHouse
						.get(GisGlobalValueMap.UNBOUNDBUILDING);
				countNum = buildDataTwoDimensionMapDao
						.countUnBoundedTwoDimensionMapPageInfoByOrgInternalCode(organization
								.getOrgInternalCode());
			}
			if (moduleName != null)
				satisticInfoVoList.add(setStatisticInfoVoProperty(countNum,
						type, moduleName));
		}
		return satisticInfoVoList;
	}

	@Override
	public HighchartColumnVo statisticColumnChartInfoByOrgId(Long orgId,
			String typeName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过组织机构ID和类型统计二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @param typeName
	 * @return List<StatisticInfoVo>
	 */
	@Override
	public List<StatisticInfoVo> statisticInfoForAreaBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoByOrgIdAndTypeName(searchVo, new Callback() {
			@Override
			public Integer call(Gis2DLayer gis2dLayer, SearchInfoVo searchVo) {
				Integer sunNum = 0;
				if (GisGlobalValueMap.BOUNDBUILDING.equals(searchVo
						.getTypeName())) {// 已绑楼宇
					sunNum = buildDataTwoDimensionMapDao
							.countBoundedTwoDimensionMapPageInfoByOrgInternalCode(gis2dLayer
									.getOrgInternalCode());
				} else if (GisGlobalValueMap.UNBOUNDBUILDING.equals(searchVo
						.getTypeName())) {// 未绑楼宇
					sunNum = buildDataTwoDimensionMapDao
							.countUnBoundedTwoDimensionMapPageInfoByOrgInternalCode(gis2dLayer
									.getOrgInternalCode());
				}
				return sunNum;
			}
		});
	}

	@Override
	public List<StatisticInfoVo> statisticGeneralCategoryInfoByOrgId(Long orgId) {
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
		if (screenCoordinateVo == null
				|| screenCoordinateVo.getPoints() == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		Integer countNum = 0;
		// 通过区域最大和最小坐标获取地图数据
		List<BuildDataInfoVo> buildDataInfoVos = buildDataTwoDimensionMapDao
				.findBuildDataInfoVosByOrgCodeAndScreenCoordinateVo(
						org.getOrgInternalCode(), screenCoordinateVo);
		List<BuildDataInfoVo> buildDataList = new ArrayList<BuildDataInfoVo>();

		for (int i = 0; i < buildDataInfoVos.size(); i++) {
			BuildDataInfoVo buildDataInfoVo = buildDataInfoVos.get(i);
			boolean isPoinInPolygon = false;
			if (buildDataInfoVo != null && buildDataInfoVo.getLon() != null
					&& buildDataInfoVo.getLat() != null
					&& buildDataInfoVo.getLon2() != null
					&& buildDataInfoVo.getLat2() != null) {
				if (screenCoordinateVo.getSearchInfoVo().getGisType()
						.equals("3D")) {
					isPoinInPolygon = OpenLayersGetPoints.isPointInPolygon(
							Double.valueOf(buildDataInfoVo.getLon()),
							Double.valueOf(buildDataInfoVo.getLat()),
							screenCoordinateVo.getPoints());
				} else if (screenCoordinateVo.getSearchInfoVo().getGisType()
						.equals("2D")) {
					isPoinInPolygon = OpenLayersGetPoints.isPointInPolygon(
							Double.valueOf(buildDataInfoVo.getLon2()),
							Double.valueOf(buildDataInfoVo.getLat2()),
							screenCoordinateVo.getPoints());
				}

			}

			if (isPoinInPolygon) {
				buildDataList.add(buildDataInfoVo);
			}
		}
		countNum = buildDataList.size();
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

		return buildDataTwoDimensionMapDao.countBuildDataByOrgCode(org
				.getOrgInternalCode());
	}

	/**
	 * 通过组织机构ID和类型饼图统计二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @param typeName
	 * @return List<Object[]>
	 */
	@Override
	public List<Object[]> getStatisticPieChartInfo(Long orgId, String typeName) {
		List<Object[]> importantPersonlPieDatas = new ArrayList<Object[]>();
		double sum = getStatisticPieSum(organizationDubboService
				.getSimpleOrgById(orgId).getOrgInternalCode(), typeName);
		String[] orgCodes = getOrgArraysByParentId(orgId);
		for (int i = 0; i < orgCodes.length; i++) {
			Object[] importantPersonlData = new Object[2];
			double importantPersonlCount = getStatisticPieSum(orgCodes[i],
					typeName);

			dataAssembly(orgId, sum, i, importantPersonlData,
					importantPersonlCount);
			importantPersonlPieDatas.add(importantPersonlData);
		}
		return importantPersonlPieDatas;
	}

	/**
	 * 根据表名和组织机构得到饼图的总和
	 * 
	 * @param orgInternalCode
	 *            组织机构
	 * @param tableName
	 *            表名
	 * @return int 返回饼图总和
	 */
	private int getStatisticPieSum(String orgInternalCode, String tableName) {
		int count = 0;
		if (tableName.equals(GisGlobalValueMap.BOUNDBUILDING)) {
			count = buildDataTwoDimensionMapDao
					.countBoundedTwoDimensionMapPageInfoByOrgInternalCode(orgInternalCode);
		} else if (tableName.equals(GisGlobalValueMap.UNBOUNDBUILDING)) {
			count = buildDataTwoDimensionMapDao
					.countUnBoundedTwoDimensionMapPageInfoByOrgInternalCode(orgInternalCode);
		} else if (tableName.equals(GisGlobalValueMap.BOUNDHOUSE)) {
			count = housePropertyTwoDimensionMapDao
					.countBoundedTwoDimensionMapPageInfoByOrgInternalCode(
							orgInternalCode, shardConversion
									.getShardCodeByOrgCode(orgInternalCode));
		} else if (tableName.equals(GisGlobalValueMap.UNBOUNDHOUSE)) {
			count = housePropertyTwoDimensionMapDao
					.countUnBoundedTwoDimensionMapPageInfoByOrgInternalCode(
							orgInternalCode, shardConversion
									.getShardCodeByOrgCode(orgInternalCode));
		}
		return count;
	}
}
