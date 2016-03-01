package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.PersonTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.service.impl.AbstractTownshipsUpStatisticService;
import com.tianque.openLayersMap.service.impl.Callback;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.HighchartDataColumnVo;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;
import com.tianque.plugin.analyzing.service.LeaderViewService;
import com.tianque.shard.util.ShardConversion;
import com.tianque.shard.util.ShardTables;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 人口信息统计实现类
 * 
 * @author jiangjinling
 * 
 */
@Service("personMapStatisticService")
public class PersonMapStatisticServiceImpl extends
		AbstractTownshipsUpStatisticService {

	@Autowired
	private PersonTwoDimensionMapDao personTwoDimensionMapDao;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;
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
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
					private List<GisTypeManage> gisTypeManageList = null;

					private List<GisTypeManage> getGisTypeManageList() {
						if (gisTypeManageList == null) {
							GisTypeManage gisTypeManage = new GisTypeManage();
							gisTypeManage
									.setInnerKey(GisGlobalValueMap.PERSONAL_MODE);
							gisTypeManageList = sysGisTypeManageService
									.getNeedGisTypeManagesByInnerType(gisTypeManage);
						}
						return gisTypeManageList;
					}

					@Override
					public Integer call(Gis2DLayer gis2dLayer,
							SearchInfoVo searchVo) {
						Integer countNum = 0;
						List<GisTypeManage> gisTypeManageList = getGisTypeManageList();
						if (gisTypeManageList.size() == 0) {
							countNum = countStatisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
									gis2dLayer, searchVo.getSearchValue(), "",
									"");
						} else {
							for (int j = 0; j < gisTypeManageList.size(); j++) {
								Integer boundNum = countStatisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
										gis2dLayer,
										searchVo.getSearchValue(),
										gisTypeManageList.get(j).getTableName(),
										gisTypeManageList.get(j).getKey());
								countNum = countNum + boundNum;
							}
						}
						return countNum;
					}
				});
	}

	private Integer countStatisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			Gis2DLayer gis2dLayer, String searchValue, String tableName,
			String personType) {
		Integer countNum = 0;
		if (shardConversion.isOverCity(gis2dLayer.getOrganization())) {
			List<Organization> orgs = organizationDubboService
					.findOrgsByOrgTypeAndOrgLevelAndParentId(
							OrganizationType.ADMINISTRATIVE_REGION,
							OrganizationLevel.CITY, gis2dLayer
									.getOrganization().getId());
			for (Organization org : orgs) {
				Integer boundNum = personTwoDimensionMapDao
						.statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
								org.getOrgInternalCode(), searchValue,
								tableName, personType,
								shardConversion.getShardCode(org.getId()));
				countNum = countNum + boundNum;
			}
		} else {
			countNum = personTwoDimensionMapDao
					.statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
							gis2dLayer.getOrgInternalCode(), searchValue,
							tableName, personType, shardConversion
									.getShardCode(gis2dLayer.getOrganization()
											.getId()));
		}
		return countNum;
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer,
							SearchInfoVo searchVo) {
						Integer countNum = 0;
						if (ShardTables.SHARD_HOUSEHOLDSTAFFS
								.equalsIgnoreCase(searchVo.getTableName())) {
							if (shardConversion.isOverCity(gis2dLayer
									.getOrganization())) {
								List<Organization> orgs = organizationDubboService
										.findOrgsByOrgTypeAndOrgLevelAndParentId(
												OrganizationType.ADMINISTRATIVE_REGION,
												OrganizationLevel.CITY,
												gis2dLayer.getOrganization()
														.getId());
								for (Organization org : orgs) {
									Integer boundNum = personTwoDimensionMapDao
											.statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
													org.getOrgInternalCode(),
													searchVo.getTableName(),
													shardConversion
															.getShardCode(org
																	.getId()));
									countNum = countNum + boundNum;
								}
							} else {
								countNum = personTwoDimensionMapDao
										.statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
												gis2dLayer.getOrgInternalCode(),
												searchVo.getTableName(),
												shardConversion
														.getShardCode(gis2dLayer
																.getOrganization()
																.getId()));
							}
						} else {
							countNum = personTwoDimensionMapDao
									.statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
											gis2dLayer.getOrgInternalCode(),
											searchVo.getTableName(), null);
						}
						return countNum;
					}
				});
	}

	@Override
	public List<StatisticInfoVo> statisticInfoByOrgId(Long orgId) {
		if (null == orgId) {
			throw new BusinessValidationException("组织机构id不能为空!");
		}
		List<StatisticInfoVo> satisticInfoVoList = new ArrayList<StatisticInfoVo>();
		/** 判断是否是网格层级 */
		boolean isGrid = isGridByOrgId(orgId);
		// if(PermissionFilter.isHasPermission(ThreadVariable.getUser().getId(),
		// "actualPopulation", permissionService)){
		GisTypeManage gisPersonTypeManage = new GisTypeManage();
		gisPersonTypeManage.setInnerKey(GisGlobalValueMap.PERSONAL_MODE);
		List<GisTypeManage> gisPersonTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(gisPersonTypeManage);
		int sum = 0;
		StatisticInfoVo infoSum = setStatisticInfoVoProperty(0, "person", "");
		satisticInfoVoList.add(infoSum);
		for (int i = 0; i < gisPersonTypeManageList.size(); i++) {
			String type = gisPersonTypeManageList.get(i).getKey();
			Integer countKeyPerson = 0;
			// Integer countKeyPerson = personTwoDimensionMapDao
			// .countTwoDimensionMapPageInfoByOrgId(
			// organization.getOrgInternalCode(), type);
			/** 直接从领导视图的缓存中取 */
			List<StatisticsBaseInfoAddCountVo> datas = leaderViewService
					.statisticsBaseInfoAddCountByOrgIdForArea(orgId, type,
							isGrid);
			if (datas != null && datas.size() > 0) {
				countKeyPerson = datas.get(datas.size() - 1)
						.getAttentionCount();
			}
			StatisticInfoVo info = setStatisticInfoVoProperty(countKeyPerson,
					type, "");
			info.setGisTypeManage(gisPersonTypeManageList.get(i));
			satisticInfoVoList.add(info);
			sum += countKeyPerson;
		}
		GisTypeManage sumTypeManager = new GisTypeManage();
		sumTypeManager.setTableName("person");
		sumTypeManager.setName("人口信息");
		infoSum.setGisTypeManage(sumTypeManager);
		infoSum.setSumNum(sum);
		// }

		return satisticInfoVoList;
	}

	@Override
	public Integer statisticInfoByOrgIdAndPoints(
			ScreenCoordinateVo screenCoordinateVo, Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForAreaBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoByOrgIdAndTypeName(searchVo, new Callback() {
			public Integer call(Gis2DLayer gis2dLayer, SearchInfoVo searchVo) {
				Integer sunNum = personTwoDimensionMapDao
						.countTwoDimensionMapPageInfoByOrgId(gis2dLayer
								.getOrgInternalCode(), searchVo.getTypeName(),
								shardConversion
										.getShardCodeByOrgCode(gis2dLayer
												.getOrgInternalCode()));
				return sunNum;
			}
		});
	}

	@Override
	public List<StatisticInfoVo> statisticGeneralCategoryInfoByOrgId(Long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HighchartColumnVo statisticColumnChartInfoByOrgId(Long orgId,
			String typeName) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		highchartColumn.setModuleName(GisGlobalValueMap
				.getTypeDisplayNames(GisGlobalValueMap.PERSON_KEY));
		highchartColumn.setSeries(getPersonTypeColumnDataByOrgId(orgId));

		highchartColumn.setCategories(getPersonType());
		return highchartColumn;
	}

	/**
	 * 得到人口信息的所有类型名称
	 * 
	 * @return String[]
	 */
	private String[] getPersonType() {
		GisTypeManage gisTypeManage = new GisTypeManage();
		gisTypeManage.setInnerKey(GisGlobalValueMap.PERSONAL_MODE);
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(gisTypeManage);
		String[] moduleName = new String[gisTypeManageList.size()];

		for (int i = 0; i < gisTypeManageList.size(); i++) {
			moduleName[i] = gisTypeManageList.get(i).getName();
		}
		return moduleName;
	}

	/**
	 * 根据组织机构Id得到人员信息各个类型的柱状图数据
	 * 
	 * @param orgId
	 * @return List<HighchartDataColumnVo>
	 */
	private List<HighchartDataColumnVo> getPersonTypeColumnDataByOrgId(
			Long orgId) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		positiveinfoColumns.addAll(getPersonColumnDataByOrgs(organization));
		return positiveinfoColumns;
	}

	/**
	 * 根据组织机构得到人口信息的柱状图数据
	 * 
	 * @param organization
	 * @return List<HighchartDataColumnVo>
	 */
	private List<HighchartDataColumnVo> getPersonColumnDataByOrgs(
			Organization organization) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		GisTypeManage domain = new GisTypeManage();
		domain.setInnerKey("person");
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(domain);
		int[] data = new int[gisTypeManageList.size()];
		/** 判断是否是网格层级 */
		boolean isGrid = isGridByOrgId(organization.getId());
		for (int i = 0; i < gisTypeManageList.size(); i++) {
			String type = gisTypeManageList.get(i).getKey();
			data[i] = 0;
			// data[i] = personService
			// .countTwoDimensionMapPageInfoByOrgInternalCodeAndTableName(
			// organization.getOrgInternalCode(), type);
			/** 直接从领导视图的缓存中取 */
			List<StatisticsBaseInfoAddCountVo> datas = leaderViewService
					.statisticsBaseInfoAddCountByOrgIdForArea(
							organization.getId(), type, isGrid);
			if (datas != null && datas.size() > 0) {
				data[i] = datas.get(datas.size() - 1).getAttentionCount();
			}
		}
		positiveinfoColumnData.setData(data);
		positiveinfoColumnData.setName(GisGlobalValueMap
				.getTypeDisplayNames(GisGlobalValueMap.PERSON_KEY));
		positiveinfoColumns.add(positiveinfoColumnData);

		return positiveinfoColumns;
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
