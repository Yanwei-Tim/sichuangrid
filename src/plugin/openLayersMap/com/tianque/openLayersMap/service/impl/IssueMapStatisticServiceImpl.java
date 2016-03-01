package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.state.IssueState;
import com.tianque.openLayersMap.dao.IssueTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.IssueInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.openLayersMap.util.GisProperties;
import com.tianque.openLayersMap.util.GisType;
import com.tianque.openLayersMap.util.OpenLayersGetPoints;
import com.tianque.openLayersMap.util.PermissionFilter;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.HighchartDataColumnVo;
import com.tianque.state.IssueQueryState;
import com.tianque.sysadmin.service.PermissionService;

/**
 * @功能：根据条件统计事件的数量，包括图层、辖区分布、搜索、柱状图、饼状图
 * @edit by longzhendong
 */
@Service("issueMapStatisticService")
public class IssueMapStatisticServiceImpl extends
		AbstractTownshipsUpStatisticService {
	public final static Logger logger = LoggerFactory
			.getLogger(IssueMapStatisticServiceImpl.class);

	@Autowired
	private IssueTwoDimensionMapDao issueTwoDimensionMapDao;
	@Autowired
	private PermissionService permissionService;

	@Override
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(
			SearchInfoVo searchVo) {
		return statisticInfoBySearchVoGroupByGis2DLayer(searchVo,
				new Callback() {
					@Override
					public Integer call(Gis2DLayer gis2dLayer,
							SearchInfoVo searchVo) {
						return issueTwoDimensionMapDao
								.statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
										gis2dLayer.getOrgInternalCode(),
										searchVo.getTypeName());
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
						return issueTwoDimensionMapDao
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
		try {
			List<StatisticInfoVo> satisticInfoVoList = new ArrayList<StatisticInfoVo>();
			Set<String> key = getModuleKeys(GisGlobalValueMap.KEY_ISSUE);
			boolean isGridOrg = organizationDubboService
					.isGridOrganization(orgId);
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgId);
			Integer countNum = 0;
			// 得到事件辖区分布数据
			for (Iterator<String> it = key.iterator(); it.hasNext();) {
				String type = (String) it.next();
				Long dealState = getDealState(type);
				List<Long> dealStateList = getDealStateList(type);
				String moduleName = getModuleName(type,
						organization.getOrgName());
				if (type.equals(GisGlobalValueMap.PERSONFORTHING)
						&& PermissionFilter.isHasPermission(ThreadVariable
								.getUser().getId(), "myBacklogIssueGis",
								permissionService)) {// 个人待办
					countNum = issueTwoDimensionMapDao
							.countMyNeedDoTwoDimensionMapPageInfoByOrgIdAndTypeName(
									organization.getId(), dealState);
					satisticInfoVoList.add(setStatisticInfoVoProperty(countNum,
							type, moduleName));
				} else if (type.equals(GisGlobalValueMap.PERSONALREADYTHING)
						&& PermissionFilter.isHasPermission(ThreadVariable
								.getUser().getId(), "myFinishIssueGis",
								permissionService)) {// 个人已办
					countNum = issueTwoDimensionMapDao
							.countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealStateList(
									organization.getId(), dealStateList);
					satisticInfoVoList.add(setStatisticInfoVoProperty(countNum,
							type, moduleName));
				} else if (type.equals(GisGlobalValueMap.GONETHROUGH)
						&& PermissionFilter.isHasPermission(ThreadVariable
								.getUser().getId(), "myEndFinishIssueGis",
								permissionService)) {// 我的已办结
					countNum = issueTwoDimensionMapDao
							.countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
									organization.getId(),
									organization.getOrgInternalCode(),
									dealState);
					satisticInfoVoList.add(setStatisticInfoVoProperty(countNum,
							type, moduleName));
				} else if (!isGridOrg
						&& type.equals(GisGlobalValueMap.FORTHING)
						&& PermissionFilter.isHasPermission(ThreadVariable
								.getUser().getId(), "backlogIssueGis",
								permissionService)) {// 下辖待办
					countNum = issueTwoDimensionMapDao
							.countMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgIdAndDealState(
									organization.getId(),
									organization.getOrgInternalCode(),
									dealState);
					satisticInfoVoList.add(setStatisticInfoVoProperty(countNum,
							type, moduleName));
				} else if (!isGridOrg
						&& type.equals(GisGlobalValueMap.ALREADYTHING)
						&& PermissionFilter.isHasPermission(ThreadVariable
								.getUser().getId(), "finishIssueGis",
								permissionService)) {// 下辖已办结
					countNum = issueTwoDimensionMapDao
							.countMyJurisdictionsDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
									organization.getId(),
									organization.getOrgInternalCode(),
									dealState);
					satisticInfoVoList.add(setStatisticInfoVoProperty(countNum,
							type, moduleName));
				}
			}
			return satisticInfoVoList;
		} catch (Exception e) {
			logger.error("通过组织机构ID统计二维地图数据（主要应用于辖区分布） 报错：", e);
			throw new ServiceValidationException(
					"通过组织机构ID统计二维地图数据（主要应用于辖区分布） 报错：", e);
		}
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
				Integer sumNum = 0;
				Long dealState = getDealState(searchVo.getTypeName());
				List<Long> dealStateList = getDealStateList(searchVo
						.getTypeName());
				if (GisGlobalValueMap.PERSONFORTHING.equals(searchVo
						.getTypeName())) {// 个人待办
					sumNum = issueTwoDimensionMapDao
							.countMyNeedDoTwoDimensionMapPageInfoByOrgIdAndTypeName(
									gis2dLayer.getOrganization().getId(),
									dealState);
				} else if (GisGlobalValueMap.PERSONALREADYTHING.equals(searchVo
						.getTypeName())) {// 个人已办
					sumNum = issueTwoDimensionMapDao
							.countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealStateList(
									gis2dLayer.getOrganization().getId(),
									dealStateList);
				} else if (GisGlobalValueMap.FORTHING.equals(searchVo
						.getTypeName())) {// 下辖待办
					sumNum = issueTwoDimensionMapDao
							.countMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgIdAndDealState(
									gis2dLayer.getOrganization().getId(),
									gis2dLayer.getOrgInternalCode(), dealState);
				} else if (GisGlobalValueMap.ALREADYTHING.equals(searchVo
						.getTypeName())) {// 下辖已办
					sumNum = issueTwoDimensionMapDao
							.countMyJurisdictionsDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
									gis2dLayer.getOrganization().getId(),
									gis2dLayer.getOrgInternalCode(), dealState);
				}
				return sumNum;
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
				.getTypeDisplayNames(GisGlobalValueMap.ISSUE_KEY));
		highchartColumn.setCategories(getIssueType());
		highchartColumn.setSeries(getIssueTypeColumnDataByOrgId(orgId));
		if (isGridDownOrganization(orgId)) {
			String[] ary = new String[2];
			ary[0] = highchartColumn.getCategories()[0];
			ary[1] = highchartColumn.getCategories()[1];
			highchartColumn.setCategories(ary);
			List<HighchartDataColumnVo> list = highchartColumn.getSeries();
			int[] ary1 = new int[2];
			int[] ary2 = list.get(0).getData();
			ary1[0] = ary2[0];
			ary1[1] = ary2[1];
			list.get(0).setData(ary1);
		}
		return highchartColumn;
	}

	/**
	 * 根据组织机构Id得到事件 各个类型 的柱状体数据
	 * 
	 * @param orgId
	 * @return List<HighchartDataColumnVo>
	 */
	private List<HighchartDataColumnVo> getIssueTypeColumnDataByOrgId(Long orgId) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		positiveinfoColumns.addAll(getIssueColumnDataByOrgs(organization));
		return positiveinfoColumns;
	}

	/**
	 * @功能：通过组织机构获取各类事件的数量，用于柱状图
	 * @param organization
	 * @return HighchartDataColumnVo
	 */
	@SuppressWarnings("unchecked")
	private List<HighchartDataColumnVo> getIssueColumnDataByOrgs(
			Organization organization) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		Map<String, String> keyPerson = (Map<String, String>) GisGlobalValueMap.KEY_MODULE_MAP
				.get(GisGlobalValueMap.KEY_ISSUE);
		Set<String> key = keyPerson.keySet();
		int[] data = new int[key.size()];
		int i = 0;
		for (Iterator<String> it = key.iterator(); it.hasNext();) {
			String type = (String) it.next();
			Long dealState = getDealState(type);
			List<Long> dealStateList = getDealStateList(type);
			if (type.equals(GisGlobalValueMap.PERSONFORTHING)) {// 个人待办
				data[i] = issueTwoDimensionMapDao
						.countMyNeedDoTwoDimensionMapPageInfoByOrgIdAndTypeName(
								organization.getId(), dealState);
			} else if (type.equals(GisGlobalValueMap.PERSONALREADYTHING)) {// 个人已办
				data[i] = issueTwoDimensionMapDao
						.countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealStateList(
								organization.getId(), dealStateList);
			} else if (type.equals(GisGlobalValueMap.GONETHROUGH)) {// 已办结
				data[i] = issueTwoDimensionMapDao
						.countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
								organization.getId(),
								organization.getOrgInternalCode(), dealState);
			} else if (type.equals(GisGlobalValueMap.FORTHING)) {// 下辖待办
				data[i] = issueTwoDimensionMapDao
						.countMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgIdAndDealState(
								organization.getId(),
								organization.getOrgInternalCode(), dealState);
			} else if (type.equals(GisGlobalValueMap.ALREADYTHING)) {// 下辖已办
				data[i] = issueTwoDimensionMapDao
						.countMyJurisdictionsDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
								organization.getId(),
								organization.getOrgInternalCode(), dealState);
			}
			positiveinfoColumnData.setData(data);
			positiveinfoColumnData.setName(GisGlobalValueMap
					.getTypeDisplayNames(GisGlobalValueMap.ISSUE_KEY));
			i++;
		}
		positiveinfoColumns.add(positiveinfoColumnData);

		return positiveinfoColumns;
	}

	/**
	 * 得到事件的所有类型名称
	 * 
	 * @return String[]
	 */
	private String[] getIssueType() {
		String[] moduleName = new String[GisGlobalValueMap.keyIssue.size()];
		int i = 0;
		for (Entry<String, String> entry : GisGlobalValueMap.keyIssue
				.entrySet()) {
			entry.getKey();
			moduleName[i] = entry.getValue();
			i++;
		}
		return moduleName;
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
		if (screenCoordinateVo == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		int countNum = 0;
		// 通过区域最大和最小坐标和人员类型获取地图数据。初步筛选地图数据
		List<IssueInfoVo> issueInfoVos = issueTwoDimensionMapDao
				.findIssueInfoVosByOrgCodeAndScreenCoordinateVo(
						org.getOrgInternalCode(), screenCoordinateVo);
		List<IssueInfoVo> issueList = new ArrayList<IssueInfoVo>();
		for (int i = 0; i < issueInfoVos.size(); i++) {
			IssueInfoVo issueInfoVo = issueInfoVos.get(i);
			// 精确筛选地图数据，将符合需求的数据放入一个新的集合
			boolean isPointInPolygon = false;
			if (issueInfoVo != null && issueInfoVo.getLon() != null
					&& issueInfoVo.getLat() != null
					&& issueInfoVo.getLon2() != null
					&& issueInfoVo.getLat2() != null) {
				if (screenCoordinateVo.getSearchInfoVo().getGisType()
						.equals(GisType.M3D)) {
					isPointInPolygon = OpenLayersGetPoints.isPointInPolygon(
							Double.valueOf(issueInfoVo.getLon()),
							Double.valueOf(issueInfoVo.getLat()),
							screenCoordinateVo.getPoints());
				} else if (screenCoordinateVo.getSearchInfoVo().getGisType()
						.equals(GisType.M2D)) {
					isPointInPolygon = OpenLayersGetPoints.isPointInPolygon(
							Double.valueOf(issueInfoVo.getLon2()),
							Double.valueOf(issueInfoVo.getLat2()),
							screenCoordinateVo.getPoints());
				}

			}

			if (isPointInPolygon) {
				issueList.add(issueInfoVo);
			}
		}
		if (issueList.size() > 0) {
			countNum = issueList.size();
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
		return issueTwoDimensionMapDao
				.statisticTwoDimensionMapInfoSumByOrgInternalCode(org
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
		String[] orgCodes = getOrgArraysByParentId(orgId);
		Long[] orgIds = getOrgIdsArraysByParentId(orgId);
		int sum = 0;
		for (int i = 0; i < orgCodes.length; i++) {
			sum = sum
					+ getStatisticTwoDimensionMapPieSum(orgCodes[i], orgIds[i],
							typeName);
		}

		for (int i = 0; i < orgCodes.length; i++) {
			Object[] importantPersonlData = new Object[2];
			double importantPersonlCount = getStatisticTwoDimensionMapPieSum(
					orgCodes[i], orgIds[i], typeName);

			dataAssembly(orgId, sum, i, importantPersonlData,
					importantPersonlCount);
			importantPersonlPieDatas.add(importantPersonlData);
		}
		return importantPersonlPieDatas;
	}

	/**
	 * 根据父类Id得到所有下级的组织机构
	 * 
	 * @param orgId
	 * @return String[] 得到所有下级的组织机构
	 */
	private Long[] getOrgIdsArraysByParentId(Long orgId) {
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		Long[] orgIds = new Long[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			orgIds[i] = organizations.get(i).getId();
		}
		return orgIds;
	}

	/**
	 * @说明：获取四类事件的数量，用于饼状图
	 * @param orgInternalCode
	 * @param orgId
	 * @param typeName
	 * @return 事件数量
	 */
	private int getStatisticTwoDimensionMapPieSum(String orgInternalCode,
			Long orgId, String typeName) {
		Integer sum = 0;
		Long dealState = getDealState(typeName);
		List<Long> dealStateList = getDealStateList(typeName);
		if (typeName.equals(GisGlobalValueMap.PERSONFORTHING)) {// 个人待办
			sum = issueTwoDimensionMapDao
					.countMyNeedDoTwoDimensionMapPageInfoByOrgIdAndTypeName(
							orgId, dealState);
		} else if (typeName.equals(GisGlobalValueMap.PERSONALREADYTHING)) {// 个人已办
			sum = issueTwoDimensionMapDao
					.countMyDoneTwoDimensionMapPageInfoByOrgIdAndDealStateList(
							orgId, dealStateList);
		} else if (typeName.equals(GisGlobalValueMap.FORTHING)) {// 下辖待办
			sum = issueTwoDimensionMapDao
					.countMyJurisdictionsNeedDoTwoDimensionMapPageInfoByOrgIdAndDealState(
							orgId, orgInternalCode, dealState);
		} else if (typeName.equals(GisGlobalValueMap.ALREADYTHING)) {// 下辖已办
			sum = issueTwoDimensionMapDao
					.countMyJurisdictionsDoneTwoDimensionMapPageInfoByOrgIdAndDealState(
							orgId, orgInternalCode, dealState);
		}
		return sum;
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

	private Long getDealState(String type) {
		Long dealState = null;
		if (type.equals(GisGlobalValueMap.PERSONFORTHING)) {// 个人待办
			dealState = IssueQueryState.MY_NEED_DO;
			if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0")) {
				dealState = new Long(IssueState.STEPCOMPLETE_CODE);
			}
		} else if (type.equals(GisGlobalValueMap.FORTHING)) {// 下辖待办
			dealState = IssueQueryState.MY_JURISDICTIONS_NEED_DO;
			if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0")) {
				dealState = new Long(IssueState.STEPCOMPLETE_CODE);
			}
		} else if (type.equals(GisGlobalValueMap.GONETHROUGH)) {// 我的已办结
			dealState = IssueQueryState.MY_JURISDICTIONS_DONE;
			if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0")) {
				dealState = new Long(IssueState.ISSUECOMPLETE_CODE);
			}
		} else if (type.equals(GisGlobalValueMap.ALREADYTHING)) {// 下辖已办结
			dealState = IssueQueryState.MY_JURISDICTIONS_DONE;
			if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0")) {
				dealState = new Long(IssueState.ISSUECOMPLETE_CODE);
			}
		}
		return dealState;
	}

	private List<Long> getDealStateList(String type) {
		List<Long> dealStateList = new ArrayList<Long>();
		if (type.equals(GisGlobalValueMap.PERSONALREADYTHING)) {// 个人已办
			dealStateList.add(IssueQueryState.MY_DONE_ONE);
			dealStateList.add(IssueQueryState.MY_DONE_THREE);
			if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0")) {
				dealStateList = new ArrayList<Long>();
				dealStateList.add(new Long(IssueState.STEPCOMPLETE_CODE));
			}
		}
		return dealStateList;
	}

	private String getModuleName(String type, String orgName) {
		String moduleName = null;
		if (type.equals(GisGlobalValueMap.PERSONFORTHING)) {// 个人待办
			moduleName = orgName
					+ GisGlobalValueMap.keyIssue
							.get(GisGlobalValueMap.PERSONFORTHING);
		} else if (type.equals(GisGlobalValueMap.PERSONALREADYTHING)) {// 个人已办
			moduleName = orgName
					+ GisGlobalValueMap.keyIssue
							.get(GisGlobalValueMap.PERSONALREADYTHING);
		} else if (type.equals(GisGlobalValueMap.GONETHROUGH)) {// 我的已办结
			moduleName = orgName
					+ GisGlobalValueMap.keyIssue
							.get(GisGlobalValueMap.GONETHROUGH);
		} else if (type.equals(GisGlobalValueMap.FORTHING)) {// 下辖待办
			moduleName = GisGlobalValueMap.keyIssue
					.get(GisGlobalValueMap.FORTHING);
		} else if (type.equals(GisGlobalValueMap.ALREADYTHING)) {// 下辖已办结
			moduleName = GisGlobalValueMap.keyIssue
					.get(GisGlobalValueMap.ALREADYTHING);
		}
		return moduleName;
	}
}
