package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.KeyPlaceTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.service.KeyPlaceService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.openLayersMap.util.OpenLayersGetPoints;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.HighchartDataColumnVo;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;
import com.tianque.plugin.analyzing.service.CompanyPlaceLeaderViewService;

/**
 * 二维地图 重点场所统计方法的实现
 * 
 * @author jiangjinling
 * 
 */
@Service("keyPlaceMapStatisticService")
public class KeyPlaceMapStatisticServiceImpl extends
		AbstractTownshipsUpStatisticService {

	@Autowired
	private KeyPlaceTwoDimensionMapDao keyPlaceTwoDimensionMapDao;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;
	@Autowired
	private KeyPlaceService keyPlaceService;
	@Autowired
	private CompanyPlaceLeaderViewService companyPlaceLeaderViewService;

	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer,
							SearchInfoVo searchVo) {
						return keyPlaceTwoDimensionMapDao
								.statisticTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName(
										gis2dLayer.getOrgInternalCode(),
										searchVo.getTypeName());
					}
				});
	}

	/**
	 * 根据类型名得到重点场所的相对应类型
	 * 
	 * @param typeName
	 *            类型名
	 * @return String 得到重点场所的相对应类型
	 */
	// private String getLocationTypeByTypeName(String typeName) {
	// String locationType = GisGlobalValueMap
	// .getLocationTypeByTypeName(typeName);
	// return locationType;
	// }

	@Override
	public List<StatisticInfoVo> statisticInfoForScreenBySearchVo(
			SearchInfoVo searchVo) {
		// 如果是实有单位,两新组织,企业 则传入场所类型
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer,
							SearchInfoVo searchVo) {
						List<String> typeList = getTypeList(searchVo
								.getTableName());
						return keyPlaceTwoDimensionMapDao
								.statisticTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
										gis2dLayer.getOrgInternalCode(),
										searchVo.getSearchValue(), typeList);
					}
				});
	}

	/**
	 * 通过组织机构ID统计二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @return List<StatisticInfoVo>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StatisticInfoVo> statisticInfoByOrgId(Long orgId) {
		if (null == orgId) {
			throw new BusinessValidationException("组织机构id不能为空!");
		}
		List<StatisticInfoVo> satisticInfoVoList = new ArrayList<StatisticInfoVo>();
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		int sum = 0;

		// 场所
		GisTypeManage gisTypeManage = new GisTypeManage();
		gisTypeManage.setInnerKey(GisGlobalValueMap.NEW_COMPANY_PLACE_MODE);
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(gisTypeManage);
		StatisticInfoVo infoKeySum = setStatisticInfoVoProperty(0,
				GisGlobalValueMap.NEW_COMPANY_PLACE_MODE, "");
		satisticInfoVoList.add(infoKeySum);
		Integer countKeyPlace;
		if (gisTypeManageList != null && gisTypeManageList.size() > 0) {
			for (int i = 0; i < gisTypeManageList.size(); i++) {
				String type = gisTypeManageList.get(i).getKey();
				countKeyPlace = 0;
				/** 直接从领导视图的缓存中取 */
				// Integer countKeyPlace = keyPlaceTwoDimensionMapDao
				// .countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
				// organization.getOrgInternalCode(), type);
				List<StatisticsBaseInfoAddCountVo> datas = companyPlaceLeaderViewService
						.statisticsBaseInfo(orgId, type);
				if (datas != null && datas.size() > 0) {
					countKeyPlace = datas.get(datas.size() - 1)
							.getAttentionCount();
				}
				StatisticInfoVo info = setStatisticInfoVoProperty(
						countKeyPlace, type, "");
				info.setGisTypeManage(gisTypeManageList.get(i));
				satisticInfoVoList.add(info);
				sum += countKeyPlace;
			}
		}

		GisTypeManage sumKeyTypeManager = new GisTypeManage();
		sumKeyTypeManager.setKey(GisGlobalValueMap.NEW_COMPANY_PLACE_MODE);
		sumKeyTypeManager
				.setName(GisGlobalValueMap.NEW_COMPANY_PLACE_DISPLAYNAME);
		infoKeySum.setGisTypeManage(sumKeyTypeManager);
		infoKeySum.setSumNum(sum);

		// 单位
		sum = 0;
		GisTypeManage unitGisTypeManage = new GisTypeManage();
		unitGisTypeManage.setInnerKey(GisGlobalValueMap.NEW_UNIT_PLACE_MODE);
		List<GisTypeManage> unitGisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(unitGisTypeManage);
		StatisticInfoVo unitSum = setStatisticInfoVoProperty(0,
				GisGlobalValueMap.NEW_UNIT_PLACE_MODE, "");
		satisticInfoVoList.add(unitSum);
		Integer countUnit;
		if (unitGisTypeManageList != null && unitGisTypeManageList.size() > 0) {
			for (int i = 0; i < unitGisTypeManageList.size(); i++) {
				countUnit = 0;
				String type = unitGisTypeManageList.get(i).getKey();
				// Integer countEnterprise = keyPlaceTwoDimensionMapDao
				// .countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
				// organization.getOrgInternalCode(), type);
				/** 直接从领导视图的缓存中取 */
				List<StatisticsBaseInfoAddCountVo> datas = companyPlaceLeaderViewService
						.statisticsBaseInfo(orgId, type);
				if (datas != null && datas.size() > 0) {
					countUnit = datas.get(datas.size() - 1).getAttentionCount();
				}
				StatisticInfoVo info = setStatisticInfoVoProperty(countUnit,
						type, "");
				info.setGisTypeManage(unitGisTypeManageList.get(i));
				satisticInfoVoList.add(info);
				sum += countUnit;
			}
		}

		GisTypeManage sumUnit = new GisTypeManage();
		sumUnit.setKey(GisGlobalValueMap.NEW_UNIT_PLACE_MODE);
		sumUnit.setName(GisGlobalValueMap.NEW_UNIT_PLACE_DISPLAYNAME);
		unitSum.setGisTypeManage(sumUnit);
		unitSum.setSumNum(sum);

		// 重点场所
		sum = 0;
		GisTypeManage keyCompanyPlaceManage = new GisTypeManage();
		keyCompanyPlaceManage
				.setInnerKey(GisGlobalValueMap.KEY_COMPANY_PLACE_MODE);
		List<GisTypeManage> keyCompanyPlaceManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(keyCompanyPlaceManage);
		StatisticInfoVo keyCompanyPlaceSum = setStatisticInfoVoProperty(0,
				GisGlobalValueMap.KEY_COMPANY_PLACE_MODE, "");
		satisticInfoVoList.add(keyCompanyPlaceSum);
		Integer countKeyCompany;
		if (keyCompanyPlaceManage != null
				&& keyCompanyPlaceManageList.size() > 0) {
			for (int i = 0; i < keyCompanyPlaceManageList.size(); i++) {
				String type = keyCompanyPlaceManageList.get(i).getKey();
				countKeyCompany = 0;
				// Integer countKeyPlace = keyPlaceTwoDimensionMapDao
				// .countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
				// organization.getOrgInternalCode(), type);
				/** 直接从领导视图的缓存中取 */
				List<StatisticsBaseInfoAddCountVo> datas = companyPlaceLeaderViewService
						.statisticsBaseInfo(orgId, type);
				if (datas != null && datas.size() > 0) {
					countKeyCompany = datas.get(datas.size() - 1)
							.getAttentionCount();
				}
				StatisticInfoVo info = setStatisticInfoVoProperty(
						countKeyCompany, type, "");
				info.setGisTypeManage(keyCompanyPlaceManageList.get(i));
				satisticInfoVoList.add(info);
				sum += countKeyCompany;
			}
		}

		GisTypeManage keyCompanyPlaceManager = new GisTypeManage();
		keyCompanyPlaceManager.setKey(GisGlobalValueMap.KEY_COMPANY_PLACE_MODE);
		keyCompanyPlaceManager
				.setName(GisGlobalValueMap.KEY_COMPANY_PLACE_DISPLAYNAME);
		keyCompanyPlaceSum.setGisTypeManage(keyCompanyPlaceManager);
		keyCompanyPlaceSum.setSumNum(sum);

		// 企业
		sum = 0;
		GisTypeManage enterpriseGisTypeManage = new GisTypeManage();
		enterpriseGisTypeManage
				.setInnerKey(GisGlobalValueMap.SIZED_ENTERPRISE_PLACE_MODE);
		List<GisTypeManage> enterpriseGisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(enterpriseGisTypeManage);
		StatisticInfoVo enterpriseSum = setStatisticInfoVoProperty(0,
				GisGlobalValueMap.SIZED_ENTERPRISE_PLACE_MODE, "");
		satisticInfoVoList.add(enterpriseSum);
		Integer countEnterprise;
		if (enterpriseGisTypeManageList != null
				&& enterpriseGisTypeManageList.size() > 0) {
			for (int i = 0; i < enterpriseGisTypeManageList.size(); i++) {
				String type = enterpriseGisTypeManageList.get(i).getKey();
				countEnterprise = 0;
				// Integer countEnterprise = keyPlaceTwoDimensionMapDao
				// .countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
				// organization.getOrgInternalCode(), type);
				/** 直接从领导视图的缓存中取 */
				List<StatisticsBaseInfoAddCountVo> datas = companyPlaceLeaderViewService
						.statisticsBaseInfo(orgId, type);
				if (datas != null && datas.size() > 0) {
					countEnterprise = datas.get(datas.size() - 1)
							.getAttentionCount();
				}
				StatisticInfoVo info = setStatisticInfoVoProperty(
						countEnterprise, type, "");
				info.setGisTypeManage(enterpriseGisTypeManageList.get(i));
				satisticInfoVoList.add(info);
				sum += countEnterprise;
			}
		}

		GisTypeManage sumEnterprise = new GisTypeManage();
		sumEnterprise.setKey(GisGlobalValueMap.SIZED_ENTERPRISE_PLACE_MODE);
		sumEnterprise
				.setName(GisGlobalValueMap.SIZED_ENTERPRISE_PLACE_DISPLAYNAME);
		enterpriseSum.setGisTypeManage(sumEnterprise);
		enterpriseSum.setSumNum(sum);

		// 两新组织
		sum = 0;
		GisTypeManage twoNewGroupGisTypeManage = new GisTypeManage();
		twoNewGroupGisTypeManage.setInnerKey(GisGlobalValueMap.TWO_NEWGROUP);
		List<GisTypeManage> twoNewGroupGisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(twoNewGroupGisTypeManage);
		StatisticInfoVo twoNewGroupSum = setStatisticInfoVoProperty(0,
				GisGlobalValueMap.TWO_NEWGROUP, "");
		satisticInfoVoList.add(twoNewGroupSum);
		if (twoNewGroupGisTypeManageList != null
				&& twoNewGroupGisTypeManageList.size() > 0) {
			for (int i = 0; i < twoNewGroupGisTypeManageList.size(); i++) {
				String type = twoNewGroupGisTypeManageList.get(i).getKey();
				Integer countTwoNewGroup = keyPlaceTwoDimensionMapDao
						.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
								organization.getOrgInternalCode(), type);
				StatisticInfoVo info = setStatisticInfoVoProperty(
						countTwoNewGroup, type, "");
				info.setGisTypeManage(twoNewGroupGisTypeManageList.get(i));
				satisticInfoVoList.add(info);
				sum += countTwoNewGroup;
			}
		}

		GisTypeManage sumTwoNewGroup = new GisTypeManage();
		sumTwoNewGroup.setKey(GisGlobalValueMap.TWO_NEWGROUP);
		sumTwoNewGroup.setName(GisGlobalValueMap.TWO_NEWGROUP_DISPLAYNAME);
		twoNewGroupSum.setGisTypeManage(sumTwoNewGroup);
		twoNewGroupSum.setSumNum(sum);

		// 景区管理
		sum = 0;
		GisTypeManage scenicsManageGisTypeManage = new GisTypeManage();
		scenicsManageGisTypeManage
				.setInnerKey(GisGlobalValueMap.SCENICS_MANAGE);
		List<GisTypeManage> scenicsManageGisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(scenicsManageGisTypeManage);
		StatisticInfoVo scenicsManageSum = setStatisticInfoVoProperty(0,
				GisGlobalValueMap.SCENICS_MANAGE, "");
		satisticInfoVoList.add(scenicsManageSum);
		if (scenicsManageGisTypeManageList != null
				&& scenicsManageGisTypeManageList.size() > 0) {
			for (int i = 0; i < scenicsManageGisTypeManageList.size(); i++) {
				String type = scenicsManageGisTypeManageList.get(i).getKey();
				Integer countscenicsManage = keyPlaceTwoDimensionMapDao
						.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
								organization.getOrgInternalCode(), type);
				StatisticInfoVo info = setStatisticInfoVoProperty(
						countscenicsManage, type, "");
				info.setGisTypeManage(scenicsManageGisTypeManageList.get(i));
				satisticInfoVoList.add(info);
				sum += countscenicsManage;
			}
		}

		GisTypeManage sumScenicsManage = new GisTypeManage();
		sumScenicsManage.setKey(GisGlobalValueMap.SCENICS_MANAGE);
		sumScenicsManage.setName(GisGlobalValueMap.SCENICS_MANAGE_DISPLAYNAME);
		scenicsManageSum.setGisTypeManage(sumScenicsManage);
		scenicsManageSum.setSumNum(sum);

		return satisticInfoVoList;
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForAreaBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoByOrgIdAndTypeName(searchVo, new Callback() {
			public Integer call(Gis2DLayer gis2dLayer, SearchInfoVo searchVo) {
				Integer sunNum = keyPlaceTwoDimensionMapDao
						.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
								gis2dLayer.getOrgInternalCode(),
								searchVo.getTypeName());
				return sunNum;
			}
		});
	}

	/**
	 * 通过组织机构ID统计二维地图数据（主要应用于辖区分布 总类别统计）
	 * 
	 * @param orgId
	 * @return List<StatisticInfoVo>
	 */
	@Override
	public List<StatisticInfoVo> statisticGeneralCategoryInfoByOrgId(Long orgId) {
		List<StatisticInfoVo> gridSatisticInfoList = new ArrayList<StatisticInfoVo>();
		List<Gis2DLayer> gis2DLayers = getGis2dLayers(orgId);// 通过组织机构Id获取地图区域对象
		for (int i = 0; i < gis2DLayers.size(); i++) {
			Gis2DLayer gis2dLayer = gis2DLayers.get(i);
			if (gis2dLayer != null && gis2dLayer.getId() != null) {
				Integer sumNum = keyPlaceTwoDimensionMapDao
						.statisticTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
								gis2dLayer.getOrgInternalCode(), null);// 根据组织机构统计重点场所的总数

				gridSatisticInfoList.add(setStatisticInfoVoProperty(gis2dLayer,
						sumNum, ""));
			}
		}
		return gridSatisticInfoList;
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
				.getTypeDisplayNames(typeName));
		highchartColumn.setSeries(getKeyPlaceTypeColumnDataByOrgId(orgId,
				typeName));
		highchartColumn.setCategories(getKeyPlaceType(typeName));
		return highchartColumn;
	}

	/**
	 * 根据组织机构Id得到重点场所各个类型的柱状体数据
	 * 
	 * @param orgId
	 * @return List<HighchartDataColumnVo>
	 */
	private List<HighchartDataColumnVo> getKeyPlaceTypeColumnDataByOrgId(
			Long orgId, String typeName) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		positiveinfoColumns.addAll(getKeyPlaceColumnDataByOrgs(organization,
				typeName));
		return positiveinfoColumns;
	}

	/**
	 * 根据组织机构得到重点场所的柱状图数据
	 * 
	 * @param organization
	 * @return List<HighchartDataColumnVo>
	 */
	private List<HighchartDataColumnVo> getKeyPlaceColumnDataByOrgs(
			Organization organization, String typeName) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		GisTypeManage domain = new GisTypeManage();
		domain.setInnerKey(typeName);
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(domain);
		int[] data = new int[gisTypeManageList.size()];
		for (int i = 0; i < gisTypeManageList.size(); i++) {
			data[i] = 0;
			String type = gisTypeManageList.get(i).getKey();
			if (GisGlobalValueMap.NEW_COMPANY_PLACE_MODE.equals(typeName)
					|| GisGlobalValueMap.NEW_UNIT_PLACE_MODE.equals(typeName)
					|| GisGlobalValueMap.KEY_COMPANY_PLACE_MODE
							.equals(typeName)
					|| GisGlobalValueMap.SIZED_ENTERPRISE_PLACE_MODE
							.equals(typeName)) {
				/** 直接从领导视图的缓存中取 */
				List<StatisticsBaseInfoAddCountVo> datas = companyPlaceLeaderViewService
						.statisticsBaseInfo(organization.getId(), type);
				if (datas != null && datas.size() > 0) {
					data[i] = datas.get(datas.size() - 1).getAttentionCount();
				}
			} else {
				data[i] = keyPlaceService
						.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
								organization.getOrgInternalCode(), type);
			}
			positiveinfoColumnData.setData(data);
			positiveinfoColumnData.setName(GisGlobalValueMap
					.getTypeDisplayNames(typeName));
		}

		positiveinfoColumns.add(positiveinfoColumnData);

		return positiveinfoColumns;
	}

	/**
	 * 得到重点场所的所有类型名称
	 * 
	 * @return String[]
	 */
	private String[] getKeyPlaceType(String typeName) {
		GisTypeManage gisTypeManage = new GisTypeManage();
		gisTypeManage.setInnerKey(typeName);
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(gisTypeManage);
		String[] moduleName = new String[gisTypeManageList.size()];

		for (int i = 0; i < gisTypeManageList.size(); i++) {
			moduleName[i] = gisTypeManageList.get(i).getName();
		}
		return moduleName;
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
		// 通过区域最大和最小坐标获取地图数据。初步筛选地图数据
		List<KeyPlaceInfoVo> keyPlaceInfoVos = keyPlaceTwoDimensionMapDao
				.findKeyPlaceInfoVosByOrgCodeAndScreenCoordinateVo(
						org.getOrgInternalCode(), screenCoordinateVo);
		List<KeyPlaceInfoVo> keyPlaceList = new ArrayList<KeyPlaceInfoVo>();
		for (int i = 0; i < keyPlaceInfoVos.size(); i++) {
			KeyPlaceInfoVo keyPlaceInfoVo = keyPlaceInfoVos.get(i);
			// 精确筛选地图数据，将符合需求的数据放入一个新的集合
			boolean isPointInPolygon = false;
			if (keyPlaceInfoVo != null && keyPlaceInfoVo.getLon() != null
					&& keyPlaceInfoVo.getLat() != null
					&& keyPlaceInfoVo.getLon2() != null
					&& keyPlaceInfoVo.getLat2() != null) {
				if (screenCoordinateVo.getSearchInfoVo().getGisType()
						.equals("3D")) {
					isPointInPolygon = OpenLayersGetPoints.isPointInPolygon(
							Double.valueOf(keyPlaceInfoVo.getLon()),
							Double.valueOf(keyPlaceInfoVo.getLat()),
							screenCoordinateVo.getPoints());
				} else if (screenCoordinateVo.getSearchInfoVo().getGisType()
						.equals("2D")) {
					isPointInPolygon = OpenLayersGetPoints.isPointInPolygon(
							Double.valueOf(keyPlaceInfoVo.getLon2()),
							Double.valueOf(keyPlaceInfoVo.getLat2()),
							screenCoordinateVo.getPoints());

				}
			}

			if (isPointInPolygon) {
				keyPlaceList.add(keyPlaceInfoVo);
			}
		}
		if (keyPlaceList.size() > 0) {
			countNum = keyPlaceList.size();
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
		List<GisTypeManage> gisTypeManageList = getPlacesSubclassList();// 得到组织场所的所有子类
		int countNum = 0;
		// 循环所有子类数据并相加得到总数
		for (int j = 0; j < gisTypeManageList.size(); j++) {
			String typeName = gisTypeManageList.get(j).getKey();
			countNum += keyPlaceTwoDimensionMapDao
					.countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
							org.getOrgInternalCode(), typeName);
		}
		return countNum;
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
		return keyPlaceTwoDimensionMapDao
				.statisticTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
						orgInternalCode, tableName);
	}

}
