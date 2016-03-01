package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.BuildDataTwoDimensionMapDao;
import com.tianque.openLayersMap.dao.HousePropertyTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.HousePropertyInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.util.GisCountViewCacheKey;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.openLayersMap.util.OpenLayersGetPoints;
import com.tianque.openLayersMap.util.PermissionFilter;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.HighchartDataColumnVo;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;
import com.tianque.plugin.analyzing.service.LeaderViewService;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 房屋信息Service
 * 
 * @author zhanghuafei
 */
@Service("housePropertyMapStatisticService")
public class HousePropertyMapStatisticServiceImpl extends
		AbstractTownshipsUpStatisticService {
	@Autowired
	private HousePropertyTwoDimensionMapDao housePropertyTwoDimensionMapDao;
	@Autowired
	private BuildDataTwoDimensionMapDao buildDataTwoDimensionMapDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RentalHouseService rentalHouseService;

	@Autowired
	private LeaderViewService leaderViewService;
	@Autowired
	private ShardConversion shardConversion;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/**
	 * 通过组织机构ID、屏幕坐标集和搜索条件统计二维地图数据（主要应用于搜索）
	 * 
	 * @param orgId
	 * @param screenCoordinateVo
	 * @param searchValue
	 * @return List<StatisticInfoVo>
	 */
	@Override
	public List<StatisticInfoVo> statisticInfoForScreenBySearchVo(
			SearchInfoVo searchVo) {
		// 如果是实有单位,两新组织,企业 则传入场所类型
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer,
							SearchInfoVo searchVo) {
						if (shardConversion.isOverCity(gis2dLayer
								.getOrganization())) {
							Integer countNum = 0;
							List<Organization> orgs = organizationDubboService
									.findOrgsByOrgTypeAndOrgLevelAndParentId(
											OrganizationType.ADMINISTRATIVE_REGION,
											OrganizationLevel.CITY, gis2dLayer
													.getOrganization().getId());
							for (Organization org : orgs) {
								Integer boundNum = housePropertyTwoDimensionMapDao
										.statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
												org.getOrgInternalCode(),
												searchVo.getSearchValue(),
												shardConversion
														.getShardCode(org
																.getId()));
								countNum = countNum + boundNum;
							}
							return countNum;
						} else {
							return housePropertyTwoDimensionMapDao
									.statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
											gis2dLayer.getOrgInternalCode(),
											searchVo.getSearchValue(),
											shardConversion
													.getShardCodeByOrgCode(gis2dLayer
															.getOrgInternalCode()));
						}
					}
				});
	}

	/**
	 * 通过组织机构ID、屏幕坐标集和类型统计二维地图数据（主要应用于图层）
	 * 
	 * @param orgId
	 * @param screenCoordinateVo
	 * @param typeName
	 *            区分下面的子类
	 * @return List<StatisticInfoVo>
	 */
	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer,
							SearchInfoVo searchVo) {
						if (shardConversion.isOverCity(gis2dLayer
								.getOrganization())) {
							Integer countNum = 0;
							List<Organization> orgs = organizationDubboService
									.findOrgsByOrgTypeAndOrgLevelAndParentId(
											OrganizationType.ADMINISTRATIVE_REGION,
											OrganizationLevel.CITY, gis2dLayer
													.getOrganization().getId());
							for (Organization org : orgs) {
								Integer boundNum = housePropertyTwoDimensionMapDao
										.statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
												org.getOrgInternalCode(),
												searchVo.getTypeName(), true,
												shardConversion
														.getShardCode(org
																.getId()));
								countNum = countNum + boundNum;
							}
							return countNum;
						} else {
							return housePropertyTwoDimensionMapDao
									.statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
											gis2dLayer.getOrgInternalCode(),
											searchVo.getTypeName(),
											true,
											shardConversion
													.getShardCodeByOrgCode(gis2dLayer
															.getOrgInternalCode()));
						}
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
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		/** 判断是否是网格层级 */
		boolean isGrid = isGridByOrgId(orgId);

		Integer countNum = 0;
		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			String moduleName = null;
			String type = (String) it.next();
			if (type == GisGlobalValueMap.RENTALHOUSE_MODE
					&& PermissionFilter.isHasPermission(ThreadVariable
							.getUser().getId(), "rentalHouseManagement",
							permissionService)) {// 出租房
				/** 直接从领导视图的缓存中取 */
				// countNum = housePropertyTwoDimensionMapDao
				// .statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
				// organization.getOrgInternalCode(), type, false);
				List<StatisticsBaseInfoAddCountVo> datas = leaderViewService
						.statisticsBaseInfo(orgId,
								GisCountViewCacheKey.RENTALHOUSE_MODE, isGrid);
				if (datas != null && datas.size() > 0) {
					countNum = datas.get(datas.size() - 1).getAttentionCount();
				}

				moduleName = GisGlobalValueMap.keyHouse
						.get(GisGlobalValueMap.RENTALHOUSE_MODE);
			} else if (type == GisGlobalValueMap.HOUSEINFO_MODE
					&& PermissionFilter.isHasPermission(ThreadVariable
							.getUser().getId(), "actualHouseManagement",
							permissionService)) {// 非出租房
				// countNum = housePropertyTwoDimensionMapDao
				// .statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
				// organization.getOrgInternalCode(), type, false,
				// shardConversion.getShardCode(orgId));
				/** 直接从领导视图的缓存中取 */
				List<StatisticsBaseInfoAddCountVo> houseInfoDatas = leaderViewService
						.statisticsBaseInfo(orgId,
								GisCountViewCacheKey.ACTUALHOUSE_MODE, isGrid);
				List<StatisticsBaseInfoAddCountVo> rentalHouseDatas = leaderViewService
						.statisticsBaseInfo(orgId,
								GisCountViewCacheKey.RENTALHOUSE_MODE, isGrid);
				// 地图中非出租房的数量等于房屋总数减去出租房的数量
				if (houseInfoDatas != null && houseInfoDatas.size() > 0
						&& rentalHouseDatas != null
						&& rentalHouseDatas.size() > 0) {
					countNum = houseInfoDatas.get(houseInfoDatas.size() - 1)
							.getAttentionCount()
							- rentalHouseDatas.get(rentalHouseDatas.size() - 1)
									.getAttentionCount();
				}
				if (countNum < 0) {
					countNum = 0;
				}
				moduleName = GisGlobalValueMap.keyHouse
						.get(GisGlobalValueMap.HOUSEINFO_MODE);
			}
			if (moduleName != null)
				satisticInfoVoList.add(setStatisticInfoVoProperty(countNum,
						type, moduleName));
		}
		return satisticInfoVoList;
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
			public Integer call(Gis2DLayer gis2dLayer, SearchInfoVo searchVo) {
				Integer sunNum = 0;
				if (searchVo.getTypeName().equals(GisGlobalValueMap.BOUNDHOUSE)) {// 已绑住房
					sunNum = housePropertyTwoDimensionMapDao
							.countBoundedTwoDimensionMapPageInfoByOrgInternalCode(
									gis2dLayer.getOrgInternalCode(),
									shardConversion
											.getShardCodeByOrgCode(gis2dLayer
													.getOrgInternalCode()));
				} else if (searchVo.getTypeName().equals(
						GisGlobalValueMap.UNBOUNDHOUSE)) {// 未绑住房
					sunNum = housePropertyTwoDimensionMapDao
							.countUnBoundedTwoDimensionMapPageInfoByOrgInternalCode(
									gis2dLayer.getOrgInternalCode(),
									shardConversion
											.getShardCodeByOrgCode(gis2dLayer
													.getOrgInternalCode()));
				} else {
					sunNum = housePropertyTwoDimensionMapDao
							.statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
									gis2dLayer.getOrgInternalCode(), searchVo
											.getTypeName(), false,
									shardConversion
											.getShardCodeByOrgCode(gis2dLayer
													.getOrgInternalCode()));
				}
				return sunNum;
			}
		});
	}

	/**
	 * 通过组织机构ID柱状图统计二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @return HighchartColumnVo
	 */
	@Override
	public HighchartColumnVo statisticColumnChartInfoByOrgId(Long orgId,
			String typeName) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		highchartColumn.setModuleName(GisGlobalValueMap
				.getTypeDisplayNames(GisGlobalValueMap.HOUSE_KEY));
		highchartColumn.setSeries(getHousePropertyTypeColumnDataByOrgId(orgId));
		highchartColumn.setCategories(getHouseType());
		return highchartColumn;
	}

	/**
	 * 得到住房的所有类型名称
	 * 
	 * @return String[]
	 */
	private String[] getHouseType() {
		String[] moduleName = new String[GisGlobalValueMap.keyHouse.size()];
		int i = 0;
		for (Entry<String, String> entry : GisGlobalValueMap.keyHouse
				.entrySet()) {
			entry.getKey();
			moduleName[i] = entry.getValue();
			i++;
		}
		return moduleName;
	}

	/**
	 * 根据组织机构Id得到住房各个类型的柱状体数据
	 * 
	 * @param orgId
	 * @return List<HighchartDataColumnVo>
	 */
	private List<HighchartDataColumnVo> getHousePropertyTypeColumnDataByOrgId(
			Long orgId) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		positiveinfoColumns
				.addAll(getHousePropertyColumnDataByOrgs(organization));
		return positiveinfoColumns;
	}

	/**
	 * 根据组织机构得到住房的柱状图数据
	 * 
	 * @param organization
	 * @return List<HighchartDataColumnVo>
	 */
	@SuppressWarnings("unchecked")
	private List<HighchartDataColumnVo> getHousePropertyColumnDataByOrgs(
			Organization organization) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		Map<String, String> keyPerson = (Map<String, String>) GisGlobalValueMap.KEY_MODULE_MAP
				.get(GisGlobalValueMap.KEY_HOUSEPROPERTY);
		Set<String> key = keyPerson.keySet();
		int[] data = new int[key.size()];
		int i = 0;
		/** 判断是否是网格层级 */
		boolean isGrid = isGridByOrgId(organization.getId());
		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			String type = (String) it.next();
			data[i] = 0;
			if (type == GisGlobalValueMap.RENTALHOUSE_MODE) {// 出租房
				// data[i] = housePropertyTwoDimensionMapDao
				// .statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
				// organization.getOrgInternalCode(), type, false);
				/** 直接从领导视图的缓存中取 */
				List<StatisticsBaseInfoAddCountVo> datas = leaderViewService
						.statisticsBaseInfo(organization.getId(),
								GisCountViewCacheKey.RENTALHOUSE_MODE, isGrid);
				if (datas != null && datas.size() > 0) {
					data[i] = datas.get(datas.size() - 1).getAttentionCount();
				}
			} else if (type == GisGlobalValueMap.HOUSEINFO_MODE) {// 非出租房
				// data[i] = housePropertyTwoDimensionMapDao
				// .statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
				// organization.getOrgInternalCode(), type, false,
				// shardConversion.getShardCode(organization));
				/** 直接从领导视图的缓存中取 */
				List<StatisticsBaseInfoAddCountVo> datas = leaderViewService
						.statisticsBaseInfo(organization.getId(),
								GisCountViewCacheKey.ACTUALHOUSE_MODE, isGrid);
				if (datas != null && datas.size() > 0) {
					// 获取的实际上是房屋信息，减去出租房才是非出租房
					data[i] = datas.get(datas.size() - 1).getAttentionCount()
							- data[0];
				}
			}
			positiveinfoColumnData.setData(data);
			positiveinfoColumnData.setName(GisGlobalValueMap
					.getTypeDisplayNames(GisGlobalValueMap.HOUSE_KEY));
			i++;
		}
		positiveinfoColumns.add(positiveinfoColumnData);

		return positiveinfoColumns;
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
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		int countNum = 0;
		// 通过区域最大/最小坐标获取地图数据
		List<HousePropertyInfoVo> housePropertyInfoVos = housePropertyTwoDimensionMapDao
				.findHousePropertyInfoVosByOrgCodeAndScreenCoordinateVo(
						org.getOrgInternalCode(), screenCoordinateVo,
						shardConversion.getShardCode(orgId));
		setIsRentalHouse(housePropertyInfoVos);
		List<HousePropertyInfoVo> housePropertyList = new ArrayList<HousePropertyInfoVo>();
		for (int i = 0; i < housePropertyInfoVos.size(); i++) {
			HousePropertyInfoVo housePropertyInfoVo = housePropertyInfoVos
					.get(i);
			if (housePropertyInfoVo != null
					&& housePropertyInfoVo.getLon() != null
					&& housePropertyInfoVo.getLat() != null
					&& housePropertyInfoVo.getLon2() != null
					&& housePropertyInfoVo.getLat2() != null) {
				boolean isPointInPolygon = false;
				if (screenCoordinateVo.getSearchInfoVo().getGisType()
						.equals("3D")) {
					isPointInPolygon = OpenLayersGetPoints.isPointInPolygon(
							Double.valueOf(housePropertyInfoVo.getLon()),
							Double.valueOf(housePropertyInfoVo.getLat()),
							screenCoordinateVo.getPoints());
				} else if (screenCoordinateVo.getSearchInfoVo().getGisType()
						.equals("2D")) {
					isPointInPolygon = OpenLayersGetPoints.isPointInPolygon(
							Double.valueOf(housePropertyInfoVo.getLon2()),
							Double.valueOf(housePropertyInfoVo.getLat2()),
							screenCoordinateVo.getPoints());
				}

				if (isPointInPolygon) {
					housePropertyList.add(housePropertyInfoVo);
				}
			}

		}
		countNum = housePropertyList.size();
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
		if (shardConversion.isOverCity(org)) {
			Integer countNum = 0;
			List<Organization> orgs = organizationDubboService
					.findOrgsByOrgTypeAndOrgLevelAndParentId(
							OrganizationType.ADMINISTRATIVE_REGION,
							OrganizationLevel.CITY, org.getId());
			for (Organization temporg : orgs) {
				Integer boundNum = housePropertyTwoDimensionMapDao
						.countHousePropertyByOrgCode(
								temporg.getOrgInternalCode(),
								shardConversion.getShardCode(temporg.getId()));
				countNum = countNum + boundNum;
			}
			return countNum;
		} else {
			return housePropertyTwoDimensionMapDao.countHousePropertyByOrgCode(
					org.getOrgInternalCode(),
					shardConversion.getShardCode(orgId));
		}
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
		double sum = getStatisticTwoDimensionMapPieSum(organizationDubboService
				.getSimpleOrgById(orgId).getOrgInternalCode(), typeName);
		String[] orgCodes = getOrgArraysByParentId(orgId);
		for (int i = 0; i < orgCodes.length; i++) {
			Object[] importantPersonlData = new Object[2];
			double importantPersonlCount = getStatisticTwoDimensionMapPieSum(
					orgCodes[i], typeName);

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
	private int getStatisticTwoDimensionMapPieSum(String orgInternalCode,
			String tableName) {
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

	private void setIsRentalHouse(List<HousePropertyInfoVo> list) {
		for (int i = 0; i < list.size(); i++) {
			HousePropertyInfoVo housePropertyInfoVo = list.get(i);
			RentalHouse rentalHouse = rentalHouseService
					.getHouseInfoByHouseId(housePropertyInfoVo.getId());
			if (rentalHouse != null && rentalHouse.getHiddenDangerLevel()!=null){
				Long id = rentalHouse.getHiddenDangerLevel().getId();
				housePropertyInfoVo.setHiddendangerLevel(propertyDictService
						.getPropertyDictById(id).getDisplayName());
			}
			if (housePropertyInfoVo.getIsRentalHouse() != null) {
				if (housePropertyInfoVo.getIsRentalHouse().equals("0"))
					housePropertyInfoVo.setIsRentalHouse("不是");
				else
					housePropertyInfoVo.setIsRentalHouse("是");
			}
		}
	}
}
