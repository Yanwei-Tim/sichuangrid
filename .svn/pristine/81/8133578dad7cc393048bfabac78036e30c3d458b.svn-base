package com.tianque.openLayersMap.controller;

import java.lang.reflect.Field;
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
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.openLayersMap.domian.CommonEntityInfoVo;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisModuleVo;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.HousePropertyInfoVo;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.domian.vo.PersonInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.service.HousePropertyService;
import com.tianque.openLayersMap.service.KeyPlaceService;
import com.tianque.openLayersMap.service.PersonService;
import com.tianque.openLayersMap.service.SysGisFunctionService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.service.SysModuleManageService;
import com.tianque.openLayersMap.service.TownshipsUnderSearchService;
import com.tianque.openLayersMap.service.impl.AbstractCommonTownshipsUnderSearchService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/gis/twoDimensionMapSearchCommonManage")
@Transactional
@Scope("prototype")
@Controller("searchController")
public class SearchController<T> extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private Map<String, TownshipsUnderSearchService<T>> townshipsUnderSearchService;
	@Autowired
	private SysModuleManageService sysModuleManageService;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;
	@Autowired
	private KeyPlaceService keyPlaceService;
	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private SysGisFunctionService sysGisFunctionService;
	@Autowired
	private PersonService personService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private ScreenCoordinateVo screenCoordinateVo;
	private Organization organization;
	private String searchValue;
	private String modeType;
	private String typeName;
	private String mainTableName;
	private String childTableName;
	private String keyType;
	private Boolean isSerachMode = false; // 是否为搜索功能
	private Long buildDataId;// 建筑物ID
	private String functionType;
	private boolean isBuiltIn = true; // 是否是内置模块
	private String centerLon;
	private String centerLat;

	/**
	 * 查找二维地图数据（乡镇以下搜索应用）
	 * 
	 * @return gridPage
	 */
	@Action(value = "findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@SuppressWarnings("unchecked")
	public String findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue()
			throws Exception {
		if (validateNull(organization, screenCoordinateVo, searchValue,
				modeType)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		TownshipsUnderSearchService<T> underSearchService = townshipsUnderSearchService
				.get(modeType);
		if (underSearchService instanceof AbstractCommonTownshipsUnderSearchService) {
			((AbstractCommonTownshipsUnderSearchService<T>) underSearchService)
					.initParams(mainTableName, functionType);
			isBuiltIn = false;
		}
		gridPage = new GridPage<T>(
				underSearchService
						.findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(
								organization.getId(), screenCoordinateVo,
								searchValue, page, rows, sidx, sord,
								mainTableName));

		if (isBuiltIn == true) {
			gridPage.setRows(transforCommonEntity(mainTableName));
		}
		return SUCCESS;
	}

	/**
	 * 查找二维地图数据（乡镇以下图层应用）
	 * 
	 * @return gridPage
	 */
	@Action(value = "findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndTypeName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@SuppressWarnings("unchecked")
	public String findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndTypeName()
			throws Exception {
		if (validateNull(organization, screenCoordinateVo, mainTableName,
				modeType)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		TownshipsUnderSearchService<T> underSearchService = townshipsUnderSearchService
				.get(modeType);
		if (underSearchService instanceof AbstractCommonTownshipsUnderSearchService) {
			((AbstractCommonTownshipsUnderSearchService<T>) underSearchService)
					.initParams(mainTableName, functionType);
			isBuiltIn = false;
		}
		if ("dustbinHasVideo".equals(mainTableName)) {
			typeName = "hasVideo";
		}
		gridPage = new GridPage<T>(townshipsUnderSearchService.get(modeType)
				.findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(
						organization.getId(), screenCoordinateVo, typeName,
						childTableName, page, rows, sidx, sord));

		if (isBuiltIn == true) {
			gridPage.setRows(transforCommonEntity(mainTableName));
		}
		return SUCCESS;
	}

	/**
	 * 查找二维地图数据（辖区分布应用）
	 * 
	 * @return gridPage
	 */
	@Action(value = "findTwoDimensionMapPageInfoByOrgIdAndTypeName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@SuppressWarnings("unchecked")
	public String findTwoDimensionMapPageInfoByOrgIdAndTypeName()
			throws Exception {
		if (validateNull(organization, mainTableName, modeType)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		gridPage = new GridPage<T>(townshipsUnderSearchService.get(modeType)
				.findPageInfoByOrgIdAndTypeName(organization.getId(), typeName,
						page, rows, sidx, sord));

		gridPage.setRows(transforCommonEntity(mainTableName));
		return SUCCESS;
	}

	/**
	 * 房屋--查询已绑定的场所
	 * 
	 * @param mainTableName
	 * @return
	 */
	@Action(value = "findBoundedKeyPalcesByOrgIdAndTypeName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@SuppressWarnings("unchecked")
	public String findBoundedKeyPalcesByOrgIdAndTypeName() throws Exception {
		if (validateNull(organization, mainTableName)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		gridPage = new GridPage<KeyPlaceInfoVo>(
				keyPlaceService.findBoundedKeyPalcesByOrgIdAndTypeName(
						organization.getId(), buildDataId, typeName, page,
						rows, sidx, sord));
		gridPage.setRows(transforCommonEntity(mainTableName));
		return SUCCESS;
	}

	/**
	 * 人员--查询已绑定的人员
	 */
	@Action(value = "findPersonByOrgIdAndTypeName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@SuppressWarnings("unchecked")
	public String findPersonByOrgIdAndTypeName() throws Exception {
		if (mainTableName == null) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		gridPage = new GridPage<PersonInfoVo>(
				personService.findPersonByOrgIdAndTypeName(
						organization.getId(), buildDataId, typeName, page,
						rows, sidx, sord));
		isSerachMode = true;
		gridPage.setRows(transforCommonEntity(mainTableName));
		return SUCCESS;
	}

	/**
	 * 查询建筑物下的房屋列表信息
	 * 
	 * @param mainTableName
	 * @return
	 */
	@Action(value = "findTwoDimensionMapPageInfoByBuildingId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@SuppressWarnings("unchecked")
	public String findTwoDimensionMapPageInfoByBuildingId() throws Exception {
		if (validateNull(organization, mainTableName, buildDataId)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		gridPage = new GridPage<HousePropertyInfoVo>(
				housePropertyService.findTwoDimensionMapPageInfoByBuildingId(
						organization.getId(), buildDataId, typeName, page,
						rows, sidx, sord));
		gridPage.setRows(transforCommonEntity(mainTableName));
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private List<CommonEntityInfoVo> transforCommonEntity(String mainTableName)
			throws Exception {
		GisModuleVo gisModuleVo = sysModuleManageService
				.getModuleByTableName(mainTableName);
		if (gisModuleVo == null || gisModuleVo.getId() == null) {
			this.errorMessage = "参数错误，请联系管理员!";
			return new ArrayList();
		}
		GisFunction gisFunction = null;
		Boolean isHasSonClass = gisModuleVo.getIsHasSonClass();
		if (!isSerachMode && isHasSonClass) {
			GisTypeManage gisTypeManage = sysGisTypeManageService
					.getGisTypeManageByTableNameAndKeyType(childTableName,
							keyType.toUpperCase());
			if (gisTypeManage != null) {
				gisFunction = sysGisFunctionService
						.getGisFunctionBySonClassIdAndFunctionType(
								gisTypeManage.getId(), functionType);
				gisModuleVo = changeToGisModeleVo(gisTypeManage, gisModuleVo);
			}
		}
		if (gisFunction == null) {
			gisFunction = sysGisFunctionService
					.getGisFunctionByModuleIdAndFunctionType(
							gisModuleVo.getId(), functionType);
		}

		List<CommonEntityInfoVo> commonEntityInfoList = new ArrayList<CommonEntityInfoVo>();
		List<T> list = gridPage.getRows();

		if (gisFunction != null) {
			for (int i = 0; i < list.size(); i++) {
				T objectVo = list.get(i);
				Field[] fields = objectVo.getClass().getDeclaredFields();
				CommonEntityInfoVo commonEntityInfoVo = new CommonEntityInfoVo();
				for (int j = 0; j < fields.length; j++) {
					fields[j].setAccessible(true);
					commonEntityInfoVo = setCommonEntityValue(gisFunction,
							commonEntityInfoVo, objectVo, fields[j]);
				}
				commonEntityInfoVo.setGisModuleManageId(gisFunction
						.getModuleId());
				commonEntityInfoVo.setModuleName(gisModuleVo.getModuleName());
				commonEntityInfoVo.setSonClassId(gisFunction.getSonClassId());
				commonEntityInfoVo.setDetailsUrl(gisFunction.getDetailsUrl());
				if(!StringUtil.isStringAvaliable(commonEntityInfoVo.getIconUrl())){
					commonEntityInfoVo.setIconUrl(gisFunction.getIconUrl());
				}
				commonEntityInfoVo.setIsViewIcon(gisFunction.getIsViewIcon());
				commonEntityInfoVo.setTableName(gisModuleVo.getTableName());
				commonEntityInfoVo.setBoundEventIsValid(gisFunction
						.getBoundEventIsValid());
				commonEntityInfoVo.setBoundEventName(gisFunction
						.getBoundEventName());
				commonEntityInfoVo.setUnBoundEventIsValid(gisFunction
						.getUnBoundEventIsValid());
				commonEntityInfoVo.setUnBoundEventName(gisFunction
						.getUnBoundEventName());
				commonEntityInfoVo
						.setEvent(gisFunction.getEvent() == null ? null
								: gisFunction.getEvent().getId());
				commonEntityInfoVo.setFunctionType(functionType);
				String orgInternalCode = commonEntityInfoVo
						.getOrgInternalCode();
				if (commonEntityInfoVo.getOrganization() != null
						&& (!StringUtil.isStringAvaliable(orgInternalCode) || "null"
								.equals(orgInternalCode))) {
					Long orgId = commonEntityInfoVo.getOrganization().getId();
					commonEntityInfoVo
							.setOrgInternalCode(organizationDubboService
									.getSimpleOrgById(orgId)
									.getOrgInternalCode());
				}
				commonEntityInfoList.add(commonEntityInfoVo);
			}
		}
		return commonEntityInfoList;
	}

	private GisModuleVo changeToGisModeleVo(GisTypeManage gisTypeManage,
			GisModuleVo gisModuleVo) {
		gisModuleVo.setGisTypeManageId(gisTypeManage.getId());
		gisModuleVo.setTableName(gisTypeManage.getTableName());
		gisModuleVo.setModuleName(gisTypeManage.getName());
		return gisModuleVo;
	}

	private CommonEntityInfoVo setCommonEntityValue(GisFunction gisFunction,
			CommonEntityInfoVo commonEntityInfoVo, T objectVo, Field field) {

		if (gisFunction != null && commonEntityInfoVo != null
				&& objectVo != null && field != null) {
			Object value = getObjectField(objectVo, field);
			if (field.getName().equals("id")) {
				commonEntityInfoVo.setId(Long.parseLong(String.valueOf(value)));
			} else if (field.getName().equals("issueId")) {
				commonEntityInfoVo.setId(Long.parseLong(String.valueOf(value)));
			} else if (field.getName().equals("typeId")) {
				commonEntityInfoVo.setTypeId(value != null ? String
						.valueOf(value) : null);
			} else if (field.getName().equals("partName")) {
				commonEntityInfoVo.setDustbinType(String.valueOf(value));
			} else if (field.getName().equals(gisFunction.getTitleValue())) {
				commonEntityInfoVo.setTitleName(gisFunction.getTitleName());
				commonEntityInfoVo.setTitleValue(value != null ? String
						.valueOf(value) : "");
			} else if (field.getName().equals(gisFunction.getSearchFieldA())) {
				commonEntityInfoVo.setSearchFieldAName(gisFunction
						.getSearchFieldAName());
				commonEntityInfoVo.setSearchFieldA(String.valueOf(value));
			} else if (field.getName().equals(gisFunction.getSearchFieldB())) {
				commonEntityInfoVo.setSearchFieldBName(gisFunction
						.getSearchFieldBName());
				commonEntityInfoVo.setSearchFieldB(String.valueOf(value));
			} else if (field.getName().equals("featureId")) {
				commonEntityInfoVo.setFeatureId(value != null ? String
						.valueOf(value) : "");
			} else if (field.getName().equals("lon")
					|| field.getName().equals("centerLon")
					|| field.getName().equals("centerX")) {
				commonEntityInfoVo.setLon(value != null ? String.valueOf(value)
						: "");
			} else if (field.getName().equals("lat")
					|| field.getName().equals("centerLat")
					|| field.getName().equals("centerY")) {
				commonEntityInfoVo.setLat(value != null ? String.valueOf(value)
						: "");
			} else if (field.getName().equals("lon2")
					|| field.getName().equals("centerLon2")
					|| field.getName().equals("centerX2")) {
				commonEntityInfoVo.setLon2(value != null ? String
						.valueOf(value) : "");
			} else if (field.getName().equals("lat2")
					|| field.getName().equals("centerLat2")
					|| field.getName().equals("centerY2")) {
				commonEntityInfoVo.setLat2(value != null ? String
						.valueOf(value) : "");
			} else if (field.getName().equals("type")) {
				commonEntityInfoVo.setModuleType(String.valueOf(value));
			} else if ((field.getName().equals("organization"))
					|| field.getName().equals("occurOrg")) {
				commonEntityInfoVo.setOrganization((Organization) value);
			} else if (field.getName().equals("points")) {
				commonEntityInfoVo.setPoints(String.valueOf(value));
			} else if (field.getName().equals("personType")) {
				commonEntityInfoVo.setModuleType(String.valueOf(value));
			} else if (field.getName().equals("orgInternalCode")) {
				commonEntityInfoVo.setOrgInternalCode(String.valueOf(value));
			}
			if (field.getName().equals(gisFunction.getFieldA())) {
				commonEntityInfoVo.setFieldNameA(gisFunction.getFieldNameA());
				commonEntityInfoVo.setFieldA(value != null ? String
						.valueOf(value) : "");
			}
			if (field.getName().equals(gisFunction.getFieldB())) {
				commonEntityInfoVo.setFieldNameB(gisFunction.getFieldNameB());
				commonEntityInfoVo.setFieldB(value != null ? String
						.valueOf(value) : "");
			}
			if (field.getName().equals(gisFunction.getFieldC())) {
				commonEntityInfoVo.setFieldNameC(gisFunction.getFieldNameC());
				commonEntityInfoVo.setFieldC(value != null ? String
						.valueOf(value) : "");
			}
			if (field.getName().equals(gisFunction.getFieldD())) {
				commonEntityInfoVo.setFieldNameD(gisFunction.getFieldNameD());
				commonEntityInfoVo.setFieldD(value != null ? String
						.valueOf(value) : "");
			}
			if (field.getName().equals(gisFunction.getFieldE())) {
				commonEntityInfoVo.setFieldNameE(gisFunction.getFieldNameE());
				commonEntityInfoVo.setFieldE(value != null ? String
						.valueOf(value) : "");
			}
			if(field.getName().equals("imageUrl")){
				commonEntityInfoVo.setIconUrl(value != null ? String
						.valueOf(value) : "gridTeamImgIsNull");
			}
		}
		return commonEntityInfoVo;
	}

	private Object getObjectField(T objectVo, Field field) {
		if (objectVo == null || field == null) {
			return null;
		}
		Object value = null;
		try {
			value = field.get(objectVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	private boolean validateNull(Object... objects) {
		for (Object obj : objects) {
			if (obj == null)
				return true;
			else if ((obj instanceof String)
					&& ((String) obj).trim().length() == 0)
				return true;
			else if ((obj instanceof Organization)
					&& ((Organization) obj).getId() == null)
				return true;
		}
		return false;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public ScreenCoordinateVo getScreenCoordinateVo() {
		return screenCoordinateVo;
	}

	public void setScreenCoordinateVo(ScreenCoordinateVo screenCoordinateVo) {
		this.screenCoordinateVo = screenCoordinateVo;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getMainTableName() {
		return mainTableName;
	}

	public void setMainTableName(String mainTableName) {
		this.mainTableName = mainTableName;
	}

	public String getChildTableName() {
		return childTableName;
	}

	public void setChildTableName(String childTableName) {
		this.childTableName = childTableName;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public Boolean getIsSerachMode() {
		return isSerachMode;
	}

	public void setIsSerachMode(Boolean isSerachMode) {
		this.isSerachMode = isSerachMode;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public Long getBuildDataId() {
		return buildDataId;
	}

	public void setBuildDataId(Long buildDataId) {
		this.buildDataId = buildDataId;
	}

	public String getCenterLon() {
		return centerLon;
	}

	public void setCenterLon(String centerLon) {
		this.centerLon = centerLon;
	}

	public String getCenterLat() {
		return centerLat;
	}

	public void setCenterLat(String centerLat) {
		this.centerLat = centerLat;
	}

}
