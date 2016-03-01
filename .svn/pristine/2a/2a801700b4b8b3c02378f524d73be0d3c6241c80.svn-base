package com.tianque.openLayersMap.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.HourseInfo;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.service.BuildDataService;
import com.tianque.openLayersMap.service.HourseInfoService;
import com.tianque.openLayersMap.service.HousePropertyService;
import com.tianque.openLayersMap.service.KeyPlaceService;
import com.tianque.openLayersMap.service.PersonService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.service.TownshipsUnderSearchService;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.openLayersMap.util.GisType;
import com.tianque.openLayersMap.util.ServiceImplModeType;
import com.tianque.sysadmin.service.PropertyDictService;

@Namespace("/gis/hourseTwoDimensionMapManage")
@Transactional
@Scope("prototype")
@Controller("hourseTwoDimensionMapController")
public class HourseTwoDimensionMapController extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private HourseInfoService hourseInfoService;
	@Autowired
	private Map<String, TownshipsUnderSearchService<HourseInfo>> hourseTwoDimensionMapSearchService;
	@Autowired
	private KeyPlaceService keyPlaceService;
	@Autowired
	private PersonService personService;
	@Autowired
	private BuildDataService buildDataService;
	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;
	@Autowired
	private PropertyDictService propertyDictService;
	private Organization organization;
	private HourseInfo hourseInfo;
	private ScreenCoordinateVo screenCoordinateVo;
	private Map<String, Integer> keyPlace;
	private Integer houseNum = 0;
	private Integer personSum = 0;
	private Integer householdStaffCount = 0;// 常口数量
	private Integer floatingPopulationCount = 0;// 流口数量
	private Integer unsettledPopulationCount = 0;// 未落户数量
	private Integer overseaStaffCount = 0;// 境外人员数量
	private BuildDataInfoVo buildDataInfoVo;
	private List<StatisticInfoVo> statisticList;
	private List<GisTypeManage> gisTypeManagePlaceList; // 类型管理对象集合
	private String featureId;
	private String gisType;
	private Long buildDataId;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/openLayersMap/gisInformation/viewFeature.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (buildDataInfoVo == null
				|| !(StringUtil.isStringAvaliable(buildDataInfoVo.getLon()) || StringUtil
						.isStringAvaliable(buildDataInfoVo.getLat()))) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		BuildDataInfoVo buildDataInfoVoOld = buildDataInfoVo;
		keyPlace = keyPlaceService.countKeyPlacesByHourseId(buildDataId);
		buildDataInfoVo = buildDataService
				.getBuildDataInfoByBuildingid(buildDataId);
		if (buildDataInfoVo != null && buildDataInfoVo.getId() != null) {
			if (buildDataInfoVo.getType() != null
					&& buildDataInfoVo.getType().getId() != null) {
				PropertyDict propertyDict = propertyDictService
						.getPropertyDictByDomainNameAndDictId(
								PropertyTypes.BUILDDATAS_TYPE, buildDataInfoVo
										.getType().getId());
				buildDataInfoVo.setType(propertyDict);
			}
			if (GisType.M2D.equals(buildDataInfoVoOld.getGisType())) {// gisType为3D时，传三维坐标
				buildDataInfoVo.setLon(buildDataInfoVo.getLon2());
				buildDataInfoVo.setLat(buildDataInfoVo.getLat2());
			}
			// String centerLon = buildDataInfoVo.getLon();
			// String centerLat = buildDataInfoVo.getLat();
			houseNum = housePropertyService.countHousePropertyByBuildDataId(
					buildDataInfoVo.getId(), organization.getId());
			if (houseNum != 0) {
				if (ThreadVariable.getOrganization().getOrgLevel()
						.getInternalId() < OrganizationLevel.CITY) {
					householdStaffCount = personService
							.countPopulationByBuildDataId(
									buildDataInfoVo.getId(),
									GisGlobalValueMap.HOUSEHOLDSTAFF,
									organization.getId());
					floatingPopulationCount = personService
							.countPopulationByBuildDataId(
									buildDataInfoVo.getId(),
									GisGlobalValueMap.FLOATINGPOPULATION,
									organization.getId());
				}
				unsettledPopulationCount = personService
						.countPopulationByBuildDataId(buildDataInfoVo.getId(),
								GisGlobalValueMap.UNSETTLEDPOPULATION,
								organization.getId());
				overseaStaffCount = personService.countPopulationByBuildDataId(
						buildDataInfoVo.getId(),
						GisGlobalValueMap.OVERSEASTAFF, organization.getId());

				personSum = householdStaffCount + floatingPopulationCount
						+ unsettledPopulationCount + overseaStaffCount;
			}
		} else {
			buildDataInfoVo = buildDataInfoVoOld;
		}
		List<GisTypeManage> companyPlaceBaseTypeManages = new ArrayList<GisTypeManage>();
		GisTypeManage companyPlaceBaseTypeManage = new GisTypeManage();
		companyPlaceBaseTypeManage
				.setInnerKey(GisGlobalValueMap.NEW_COMPANY_PLACE_MODE);
		companyPlaceBaseTypeManages = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(companyPlaceBaseTypeManage);// 场所子类

		List<GisTypeManage> unitPlaceBaseGisTypeManages = new ArrayList<GisTypeManage>();
		GisTypeManage unitPlaceBaseGisTypeManage = new GisTypeManage();
		unitPlaceBaseGisTypeManage
				.setInnerKey(GisGlobalValueMap.NEW_UNIT_PLACE_MODE);
		unitPlaceBaseGisTypeManages = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(unitPlaceBaseGisTypeManage);// 单位子类

		List<GisTypeManage> keyCompanyPlaceBaseGisTypeManages = new ArrayList<GisTypeManage>();
		GisTypeManage keyCompanyPlaceBaseGisTypeManage = new GisTypeManage();
		keyCompanyPlaceBaseGisTypeManage
				.setInnerKey(GisGlobalValueMap.KEY_COMPANY_PLACE_MODE);
		keyCompanyPlaceBaseGisTypeManages = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(keyCompanyPlaceBaseGisTypeManage);// 单位场所
																					// 重点单位场所

		List<GisTypeManage> sizedEnterprisePlaceBaseGisTypeManages = new ArrayList<GisTypeManage>();
		GisTypeManage sizedEnterprisePlaceBaseGisTypeManage = new GisTypeManage();
		sizedEnterprisePlaceBaseGisTypeManage
				.setInnerKey(GisGlobalValueMap.SIZED_ENTERPRISE_PLACE_MODE);
		sizedEnterprisePlaceBaseGisTypeManages = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(sizedEnterprisePlaceBaseGisTypeManage);// //
																							// 单位场所
																							// 规模企业

		List<GisTypeManage> twoNewGroupGisTypeManages = new ArrayList<GisTypeManage>();
		GisTypeManage twoNewGroupGisTypeManage = new GisTypeManage();
		twoNewGroupGisTypeManage.setInnerKey(GisGlobalValueMap.TWO_NEWGROUP);
		twoNewGroupGisTypeManages = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(twoNewGroupGisTypeManage);// 两新组织子类

		List<GisTypeManage> scenicsManageGisTypeManages = new ArrayList<GisTypeManage>();
		GisTypeManage scenicsManageGisTypeManage = new GisTypeManage();
		scenicsManageGisTypeManage
				.setInnerKey(GisGlobalValueMap.SCENICS_MANAGE);
		scenicsManageGisTypeManages = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(scenicsManageGisTypeManage);// 景区管理

		List<GisTypeManage> list = new ArrayList<GisTypeManage>();

		list.addAll(companyPlaceBaseTypeManages);
		list.addAll(unitPlaceBaseGisTypeManages);
		list.addAll(keyCompanyPlaceBaseGisTypeManages);
		list.addAll(sizedEnterprisePlaceBaseGisTypeManages);
		list.addAll(twoNewGroupGisTypeManages);
		list.addAll(scenicsManageGisTypeManages);
		gisTypeManagePlaceList = list;
		return SUCCESS;
	}

	@Action(value = "addHourseInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hourseInfo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addHourseInfo() throws Exception {
		if (hourseInfo == null || hourseInfo.getOrganization() == null
				|| hourseInfo.getOrganization().getId() == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		hourseInfo = hourseInfoService.addHourseInfo(hourseInfo, gisType);
		return SUCCESS;
	}

	@Action(value = "deleteHourseInfoById", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteHourseInfoById() throws Exception {
		if (hourseInfo == null || hourseInfo.getId() == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		hourseInfoService.deleteHourseInfoById(hourseInfo.getId());
		return SUCCESS;
	}

	@Action(value = "findTownUnderHoursePageInfoBySearchValueAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findTownUnderHoursePageInfoBySearchValueAndOrgId()
			throws Exception {
		if (null == organization || organization.getId() == null
				|| null == screenCoordinateVo) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage<HourseInfo>(hourseTwoDimensionMapSearchService
				.get(ServiceImplModeType.HOURSEINFO_SEARCH)
				.findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
						organization.getId(), screenCoordinateVo, null, null,
						page, rows, sidx, sord));
		return SUCCESS;
	}

	@Action(value = "findUnboundHouseInfoByOrganizationId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findUnboundHouseInfoByOrganizationId() {
		if (null == organization || organization.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}

		gridPage = new GridPage<HourseInfo>(
				hourseInfoService
						.findUnboundHouseInfoByOrganizationId(organization
								.getId()));
		return SUCCESS;
	}

	@Action(value = "getHourseInfoById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hourseInfo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getHourseInfoById() {
		if (featureId == null || featureId.trim().equals("")) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		hourseInfo = hourseInfoService.getHourseInfoById(Long
				.parseLong(featureId));
		return SUCCESS;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public HourseInfo getHourseInfo() {
		return hourseInfo;
	}

	public void setHourseInfo(HourseInfo hourseInfo) {
		this.hourseInfo = hourseInfo;
	}

	public ScreenCoordinateVo getScreenCoordinateVo() {
		return screenCoordinateVo;
	}

	public void setScreenCoordinateVo(ScreenCoordinateVo screenCoordinateVo) {
		this.screenCoordinateVo = screenCoordinateVo;
	}

	public Integer getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(Integer houseNum) {
		this.houseNum = houseNum;
	}

	public BuildDataInfoVo getBuildDataInfoVo() {
		return buildDataInfoVo;
	}

	public void setBuildDataInfoVo(BuildDataInfoVo buildDataInfoVo) {
		this.buildDataInfoVo = buildDataInfoVo;
	}

	public Map<String, Integer> getKeyPlace() {
		return keyPlace;
	}

	public void setKeyPlace(Map<String, Integer> keyPlace) {
		this.keyPlace = keyPlace;
	}

	public List<StatisticInfoVo> getStatisticList() {
		return statisticList;
	}

	public void setStatisticList(List<StatisticInfoVo> statisticList) {
		this.statisticList = statisticList;
	}

	public List<GisTypeManage> getGisTypeManagePlaceList() {
		return gisTypeManagePlaceList;
	}

	public void setGisTypeManagePlaceList(
			List<GisTypeManage> gisTypeManagePlaceList) {
		this.gisTypeManagePlaceList = gisTypeManagePlaceList;
	}

	public Integer getPersonSum() {
		return personSum;
	}

	public void setPersonSum(Integer personSum) {
		this.personSum = personSum;
	}

	public Integer getHouseholdStaffCount() {
		return householdStaffCount;
	}

	public void setHouseholdStaffCount(Integer householdStaffCount) {
		this.householdStaffCount = householdStaffCount;
	}

	public Integer getUnsettledPopulationCount() {
		return unsettledPopulationCount;
	}

	public void setUnsettledPopulationCount(Integer unsettledPopulationCount) {
		this.unsettledPopulationCount = unsettledPopulationCount;
	}

	public Integer getOverseaStaffCount() {
		return overseaStaffCount;
	}

	public void setOverseaStaffCount(Integer overseaStaffCount) {
		this.overseaStaffCount = overseaStaffCount;
	}

	public Integer getFloatingPopulationCount() {
		return floatingPopulationCount;
	}

	public void setFloatingPopulationCount(Integer floatingPopulationCount) {
		this.floatingPopulationCount = floatingPopulationCount;
	}

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	public void setGisType(String gisType) {
		this.gisType = gisType;
	}

	public String getGisType() {
		return gisType;
	}

	public void setBuildDataId(Long buildDataId) {
		this.buildDataId = buildDataId;
	}

	public Long getBuildDataId() {
		return buildDataId;
	}

}
