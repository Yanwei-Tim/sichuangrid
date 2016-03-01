package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.KeyPersonTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.KeyPersonInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.service.KeyPersonService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.util.GisCountViewCacheKey;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.openLayersMap.util.OpenLayersGetPoints;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.HighchartDataColumnVo;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;
import com.tianque.plugin.analyzing.service.LeaderViewService;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 二维地图 重点人员统计方法的实现
 * 
 * @author jiangjinling
 * 
 */
@Service("keyPersonMapStatisticService")
public class KeyPersonMapStatisticServiceImpl extends
		AbstractTownshipsUpStatisticService {

	@Autowired
	private KeyPersonTwoDimensionMapDao keyPersonTwoDimensionMapDao;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;
	@Autowired
	private KeyPersonService keyPersonService;

	@Autowired
	private LeaderViewService leaderViewService;

	@Autowired
	private ShardConversion shardConversion;
	@Autowired
	private OrganizationDubboService organizaitonService;

	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer,
							SearchInfoVo searchVo) {
						Integer countNum = 0;
						if (shardConversion.isOverCity(gis2dLayer
								.getOrganization())) {
							List<Organization> orgs = organizaitonService
									.findOrgsByOrgTypeAndOrgLevelAndParentId(
											OrganizationType.ADMINISTRATIVE_REGION,
											OrganizationLevel.CITY, gis2dLayer
													.getOrganization().getId());
							for (Organization org : orgs) {
								Integer boundNum = keyPersonTwoDimensionMapDao
										.statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
												org.getOrgInternalCode(),
												searchVo.getTypeName(),
												shardConversion
														.getShardCode(org
																.getId()));
								countNum = countNum + boundNum;
							}
						} else {
							countNum = keyPersonTwoDimensionMapDao
									.statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
											gis2dLayer.getOrgInternalCode(),
											searchVo.getTypeName(),
											shardConversion
													.getShardCode(gis2dLayer
															.getOrganization()
															.getId()));
						}
						return countNum;
					}
				});
	}

	@Override
	public List<StatisticInfoVo> statisticInfoForScreenBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo, new Callback(
				searchVo.getTableName()) {
			private List<GisTypeManage> gisTypeManageList = null;

			private List<GisTypeManage> getGisTypeManageList(
					String keyPersonType) {
				if (gisTypeManageList == null) {
					GisTypeManage gisTypeManage = new GisTypeManage();
					gisTypeManage.setInnerKey(keyPersonType);
					gisTypeManageList = sysGisTypeManageService
							.getNeedGisTypeManagesByInnerType(gisTypeManage);
				}
				return gisTypeManageList;
			}

			@Override
			public Integer call(Gis2DLayer gis2dLayer, SearchInfoVo searchVo) {
				Integer countNum = 0;
				List<GisTypeManage> gisTypeManageList = getGisTypeManageList(tempParam
						.toString());
				if (gisTypeManageList != null) {
					for (int j = 0; j < gisTypeManageList.size(); j++) {
						if (shardConversion.isOverCity(gis2dLayer
								.getOrganization())) {
							List<Organization> orgs = organizaitonService
									.findOrgsByOrgTypeAndOrgLevelAndParentId(
											OrganizationType.ADMINISTRATIVE_REGION,
											OrganizationLevel.CITY, gis2dLayer
													.getOrganization().getId());
							for (Organization org : orgs) {
								Integer boundNum = keyPersonTwoDimensionMapDao
										.statisticTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
												org.getOrgInternalCode(),
												searchVo.getScreenVo(),
												searchVo.getSearchValue(),
												gisTypeManageList.get(j)
														.getTableName(),
												shardConversion
														.getShardCode(org
																.getId()));
								countNum = countNum + boundNum;
							}
						} else {
							Integer boundNum = keyPersonTwoDimensionMapDao
									.statisticTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
											gis2dLayer.getOrgInternalCode(),
											searchVo.getScreenVo(), searchVo
													.getSearchValue(),
											gisTypeManageList.get(j)
													.getTableName(),
											shardConversion
													.getShardCode(gis2dLayer
															.getOrganization()
															.getId()));
							countNum = countNum + boundNum;
						}
					}
				}
				return countNum;
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
		/** 判断是否是网格层级 */
		boolean isGrid = isGridByOrgId(orgId);
		int sum = 0;

		// if(PermissionFilter.isHasPermission(ThreadVariable.getUser().getId(),
		// "peopleInformation", permissionService)){
		GisTypeManage gisTypeManage = new GisTypeManage();
		gisTypeManage.setInnerKey(GisGlobalValueMap.PERSON_MODE);
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(gisTypeManage);
		StatisticInfoVo infoKeySum = setStatisticInfoVoProperty(0,
				GisGlobalValueMap.PERSON_MODE, "");
		satisticInfoVoList.add(infoKeySum);
		Integer countKeyPerson;
		for (int i = 0; i < gisTypeManageList.size(); i++) {
			countKeyPerson = 0;
			String type = GisCountViewCacheKey.keyPersonMap
					.get(gisTypeManageList.get(i).getTableName());
			/** 直接从领导视图的缓存中取 */
			// Integer countKeyPerson = keyPersonTwoDimensionMapDao
			// .countTwoDimensionMapPageInfoByOrgId(
			// organization.getOrgInternalCode(), type);
			List<StatisticsBaseInfoAddCountVo> datas = leaderViewService
					.statisticsBaseInfo(orgId, type, isGrid);
			if (datas != null && datas.size() > 0) {
				countKeyPerson = datas.get(datas.size() - 1)
						.getAttentionCount();
			}
			StatisticInfoVo info = setStatisticInfoVoProperty(countKeyPerson,
					type, "");
			info.setGisTypeManage(gisTypeManageList.get(i));
			satisticInfoVoList.add(info);
			sum += countKeyPerson;
		}
		GisTypeManage sumKeyTypeManager = new GisTypeManage();
		sumKeyTypeManager.setTableName(GisGlobalValueMap.PERSON_MODE);
		sumKeyTypeManager
				.setName(GisGlobalValueMap.IMPORTANTPERSONNEL_DISPLAYNAME);
		infoKeySum.setGisTypeManage(sumKeyTypeManager);
		infoKeySum.setSumNum(sum);
		// }

		// if(PermissionFilter.isHasPermission(ThreadVariable.getUser().getId(),
		// "civiAdministrationManagement", permissionService)){
		sum = 0;
		GisTypeManage carePersonGisTypeManage = new GisTypeManage();
		carePersonGisTypeManage.setInnerKey(GisGlobalValueMap.CARE_PERSON);
		List<GisTypeManage> carePersonGisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(carePersonGisTypeManage);
		StatisticInfoVo infoCarePersonSum = setStatisticInfoVoProperty(0,
				GisGlobalValueMap.CARE_PERSON, "");
		satisticInfoVoList.add(infoCarePersonSum);
		Integer countCarePerson;
		for (int i = 0; i < carePersonGisTypeManageList.size(); i++) {
			countCarePerson = 0;
			String type = GisCountViewCacheKey.carePersonMap
					.get(carePersonGisTypeManageList.get(i).getTableName());
			/** 直接从领导视图的缓存中取 */
			// Integer countCarePerson = keyPersonTwoDimensionMapDao
			// .countTwoDimensionMapPageInfoByOrgId(
			// organization.getOrgInternalCode(), type);
			List<StatisticsBaseInfoAddCountVo> datas = leaderViewService
					.statisticsBaseInfo(orgId, type, isGrid);
			if (datas != null && datas.size() > 0) {
				countCarePerson = datas.get(datas.size() - 1)
						.getAttentionCount();
			}
			StatisticInfoVo info = setStatisticInfoVoProperty(countCarePerson,
					type, "");
			info.setGisTypeManage(carePersonGisTypeManageList.get(i));
			satisticInfoVoList.add(info);
			sum += countCarePerson;
		}
		GisTypeManage sumCarePersonManager = new GisTypeManage();
		sumCarePersonManager.setTableName(GisGlobalValueMap.CARE_PERSON);
		sumCarePersonManager.setName(GisGlobalValueMap.CARE_DISPLAYNAME);
		infoCarePersonSum.setGisTypeManage(sumCarePersonManager);
		infoCarePersonSum.setSumNum(sum);
		// }

		// if(PermissionFilter.isHasPermission(ThreadVariable.getUser().getId(),
		// "otherAttentionObjectManagement", permissionService)){
		sum = 0;
		Integer countOtherPerson;
		GisTypeManage otherPersonGisTypeManage = new GisTypeManage();
		otherPersonGisTypeManage.setInnerKey(GisGlobalValueMap.OTHER_PERSON);
		List<GisTypeManage> otherPersonGisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(otherPersonGisTypeManage);
		StatisticInfoVo infoOtherPersonSum = setStatisticInfoVoProperty(0,
				GisGlobalValueMap.OTHER_PERSON, "");
		satisticInfoVoList.add(infoOtherPersonSum);
		for (int i = 0; i < otherPersonGisTypeManageList.size(); i++) {
			countOtherPerson = 0;
			String type = GisCountViewCacheKey.otherPersonMap
					.get(otherPersonGisTypeManageList.get(i).getTableName());
			// Integer countOtherPerson = keyPersonTwoDimensionMapDao
			// .countTwoDimensionMapPageInfoByOrgId(
			// organization.getOrgInternalCode(), type);
			List<StatisticsBaseInfoAddCountVo> datas = leaderViewService
					.statisticsBaseInfo(orgId, type, isGrid);
			if (datas != null && datas.size() > 0) {
				countOtherPerson = datas.get(datas.size() - 1)
						.getAttentionCount();
			}

			StatisticInfoVo info = setStatisticInfoVoProperty(countOtherPerson,
					type, "");
			info.setGisTypeManage(otherPersonGisTypeManageList.get(i));
			satisticInfoVoList.add(info);
			sum += countOtherPerson;
		}
		GisTypeManage sumOtherPersonManager = new GisTypeManage();
		sumOtherPersonManager.setTableName(GisGlobalValueMap.OTHER_PERSON);
		sumOtherPersonManager.setName(GisGlobalValueMap.OTHER_DISPLAYNAME);
		infoOtherPersonSum.setGisTypeManage(sumOtherPersonManager);
		infoOtherPersonSum.setSumNum(sum);
		// }

		return satisticInfoVoList;
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
		highchartColumn.setSeries(getKeyPersonTypeColumnDataByOrgId(orgId,
				typeName));

		highchartColumn.setCategories(getKeyPersonType(typeName));
		return highchartColumn;
	}

	/**
	 * 得到重点人员的所有类型名称
	 * 
	 * @return String[]
	 */
	private String[] getKeyPersonType(String typeName) {
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
	 * 根据组织机构Id得到重点人员各个类型的柱状图数据
	 * 
	 * @param orgId
	 * @return List<HighchartDataColumnVo>
	 */
	private List<HighchartDataColumnVo> getKeyPersonTypeColumnDataByOrgId(
			Long orgId, String typeName) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		positiveinfoColumns.addAll(getKeyPersonColumnDataByOrgs(organization,
				typeName));
		return positiveinfoColumns;
	}

	/**
	 * 根据组织机构得到重点人员的柱状图数据
	 * 
	 * @param organization
	 * @return List<HighchartDataColumnVo>
	 */
	private List<HighchartDataColumnVo> getKeyPersonColumnDataByOrgs(
			Organization organization, String typeName) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		GisTypeManage domain = new GisTypeManage();
		domain.setInnerKey(typeName);
		/** 判断是否是网格层级 */
		boolean isGrid = isGridByOrgId(organization.getId());
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(domain);
		int[] data = new int[gisTypeManageList.size()];
		for (int i = 0; i < gisTypeManageList.size(); i++) {
			String type = GisCountViewCacheKey.allBusinessPersonMap
					.get(gisTypeManageList.get(i).getTableName());
			data[i] = 0;
			// data[i] = keyPersonService
			// .countTwoDimensionMapPageInfoByOrgInternalCodeAndTableName(
			// organization.getOrgInternalCode(), type);
			List<StatisticsBaseInfoAddCountVo> datas = leaderViewService
					.statisticsBaseInfo(organization.getId(), type, isGrid);
			if (datas != null && datas.size() > 0) {
				data[i] = datas.get(datas.size() - 1).getAttentionCount();
			}
			positiveinfoColumnData.setData(data);
			positiveinfoColumnData
					.setName(GisGlobalValueMap
							.getTypeDisplayNames(GisGlobalValueMap.IMPORTANTPERSONNEL_KEY));
		}
		positiveinfoColumns.add(positiveinfoColumnData);

		return positiveinfoColumns;
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
				// Integer sunNum =
				// keyPersonTwoDimensionMapDao.statisticTwoDimensionMapInfoByOrgInternalCodeAndTypeName(gis2dLayer.getOrgInternalCode(),
				// typeName);

				Integer sunNum = keyPersonTwoDimensionMapDao
						.countTwoDimensionMapPageInfoByOrgId(gis2dLayer
								.getOrgInternalCode(), searchVo.getTypeName(),
								shardConversion
										.getShardCodeByOrgCode(gis2dLayer
												.getOrgInternalCode()));
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
		List<Gis2DLayer> gis2DLayers = getGis2dLayers(orgId);
		for (int i = 0; i < gis2DLayers.size(); i++) {
			Gis2DLayer gis2dLayer = gis2DLayers.get(i);
			if (gis2dLayer != null && gis2dLayer.getId() != null) {
				Integer sumNum = keyPersonTwoDimensionMapDao
						.countGeneralCategoryTwoDimensionMapInfoByOrgInternalCode(gis2dLayer
								.getOrgInternalCode());

				gridSatisticInfoList.add(setStatisticInfoVoProperty(gis2dLayer,
						sumNum, ""));
			}
		}
		return gridSatisticInfoList;
	}

	/**
	 * 通过组织机构ID和坐标集合获取二维地图数据（主要应用于画区域统计）
	 * 
	 * @param orgId
	 * @param points
	 * @return Integer
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Integer statisticInfoByOrgIdAndPoints(
			ScreenCoordinateVo screenCoordinateVo, Long orgId) {
		if (orgId == null || screenCoordinateVo == null
				|| screenCoordinateVo.getPoints() == null) {
			throw new BusinessValidationException("参数错误!");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		Map<String, String> keyPerson = (Map<String, String>) GisGlobalValueMap.KEY_MODULE_MAP
				.get(GisGlobalValueMap.KEY_PERSON);
		int countNum = 0;
		// 循环所有子类数据并相加得到总数
		for (Iterator<String> it = keyPerson.keySet().iterator(); it.hasNext();) {
			String typeName = (String) it.next();
			// 通过区域最大和最小坐标和人员类型获取地图数据。初步筛选地图数据
			List<KeyPersonInfoVo> keyPersonInfoVos = keyPersonTwoDimensionMapDao
					.findKeyPersonInfoVosByOrgCodeAndScreenCoordinateVoAndTypeName(
							org.getOrgInternalCode(), screenCoordinateVo,
							typeName, shardConversion.getShardCode(org));
			// 设置
			setGender(keyPersonInfoVos);
			List<KeyPersonInfoVo> keyPersonList = new ArrayList<KeyPersonInfoVo>();
			for (int i = 0; i < keyPersonInfoVos.size(); i++) {
				KeyPersonInfoVo keyPersonInfoVo = keyPersonInfoVos.get(i);
				if (keyPersonInfoVo != null && keyPersonInfoVo.getLon() != null
						&& keyPersonInfoVo.getLat() != null
						&& keyPersonInfoVo.getLon2() != null
						&& keyPersonInfoVo.getLat2() != null) {
					// 精确筛选地图数据，将符合需求的数据放入一个新的集合
					boolean isPointInPolygon = false;
					if (screenCoordinateVo.getSearchInfoVo().getGisType()
							.equals("3D")) {
						isPointInPolygon = OpenLayersGetPoints
								.isPointInPolygon(
										Double.valueOf(keyPersonInfoVo.getLon()),
										Double.valueOf(keyPersonInfoVo.getLat()),
										screenCoordinateVo.getPoints());
					} else if (screenCoordinateVo.getSearchInfoVo()
							.getGisType().equals("2D")) {
						isPointInPolygon = OpenLayersGetPoints
								.isPointInPolygon(Double
										.valueOf(keyPersonInfoVo.getLon2()),
										Double.valueOf(keyPersonInfoVo
												.getLat2()), screenCoordinateVo
												.getPoints());
					}

					if (isPointInPolygon) {
						keyPersonList.add(keyPersonInfoVo);
					}
				}
			}
			if (keyPersonList.size() > 0) {
				countNum = countNum + keyPersonList.size();
			}
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
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		GisTypeManage gisTypeManage = new GisTypeManage();
		gisTypeManage.setInnerKey(GisGlobalValueMap.PERSON_MODE);
		List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(gisTypeManage);
		int countNum = 0;
		// 循环所有子类数据并相加得到总数
		for (int j = 0; j < gisTypeManageList.size(); j++) {
			String typeName = gisTypeManageList.get(j).getTableName();
			countNum += keyPersonTwoDimensionMapDao
					.countTwoDimensionMapPageInfoByOrgId(
							org.getOrgInternalCode(), typeName,
							shardConversion.getShardCode(org));
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
		return keyPersonTwoDimensionMapDao
				.statisticTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
						orgInternalCode, tableName,
						shardConversion.getShardCodeByOrgCode(orgInternalCode));
	}

	private void setGender(List<KeyPersonInfoVo> infos) {
		if (infos == null || infos.isEmpty()) {
			return;
		}
		for (Iterator<KeyPersonInfoVo> it = infos.iterator(); it.hasNext();) {
			KeyPersonInfoVo info = it.next();
			PropertyDict gender = info.getGender();
			if (gender == null) {
				return;
			}
			Long id = info.getGender().getId();
			PropertyDict dict = propertyDictService.getPropertyDictById(id);
			if (dict != null) {
				info.setGender(dict);
				info.setGenderName(dict.getDisplayName());
			}
		}
	}

}
