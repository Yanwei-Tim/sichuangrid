package com.tianque.openLayersMap.controller;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.Organization;
import com.tianque.openLayersMap.domian.vo.DrawAreaStatisticVo;
import com.tianque.openLayersMap.domian.vo.JurisdictionStatisticVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.service.TownshipsUpStatisticService;
import com.tianque.openLayersMap.service.impl.AbstractCommonTownshipsUpStaticService;
import com.tianque.openLayersMap.util.DrawAreaStatisticHelper;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 统计
 * 
 * @author 张忠祥(zhangzhongxiang@hztianque.com)
 * @date 2013-3-19
 */
@Namespace("/gis/twoDimensionMapStatisticCommonManage")
@Transactional
@Scope("prototype")
@Controller("statisticController")
public class StatisticController extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private Map<String, TownshipsUpStatisticService> townshipsUpStatisticService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private ScreenCoordinateVo screenCoordinateVo;
	private List<StatisticInfoVo> statisticList;
	private Organization organization;
	private String searchValue;
	private String modeType; // Service层调用的类型
	private String childTableName;
	private String typeName;
	private HighchartColumnVo personnelColumn;
	private List<Object[]> personnelPie;
	private String points;
	private String tableName;
	private String gisType;
	private DrawAreaStatisticVo drawAreaStatisticVo = new DrawAreaStatisticVo();

	/**
	 * 统计二维地图数据（乡镇以上搜索应用）
	 * 
	 * @return statisticList
	 */
	@Action(value = "statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndSearchValue", results = {
			@Result(name = "success", type = "json", params = { "root",
					"statisticList", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndSearchValue() {
		if (validateNull(organization, searchValue, modeType)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		TownshipsUpStatisticService upStatisticService = townshipsUpStatisticService
				.get(modeType);
		if (upStatisticService instanceof AbstractCommonTownshipsUpStaticService) {
			((AbstractCommonTownshipsUpStaticService) upStatisticService)
					.initParams(tableName, null);
		}
		SearchInfoVo searchVo = new SearchInfoVo(gisType, organization.getId(),
				screenCoordinateVo, searchValue, null, tableName);
		statisticList = townshipsUpStatisticService.get(modeType)
				.statisticInfoForScreenBySearchVo(searchVo);
		return SUCCESS;
	}

	/**
	 * 统计二维地图数据（乡镇以上图层应用）
	 * 
	 * @return statisticList
	 */
	@Action(value = "statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndTypeName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"statisticList", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndTypeName() {
		if (validateNull(organization, modeType)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		if ("dustbinHasVideo".equals(tableName)) {
			typeName = "hasVideo";
		}
		SearchInfoVo searchVo = new SearchInfoVo(gisType, organization.getId(),
				screenCoordinateVo, null, typeName, childTableName);
		statisticList = townshipsUpStatisticService.get(modeType)
				.statisticInfoForLayerBySearchVo(searchVo);
		return SUCCESS;
	}

	/**
	 * 柱状图统计
	 */
	@Action(value = "getStatisticTwoDimensionMapColumnChartInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"personnelColumn", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getStatisticTwoDimensionMapColumnChartInfo() {
		if (validateNull(organization, modeType)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		personnelColumn = townshipsUpStatisticService
				.get(modeType)
				.statisticColumnChartInfoByOrgId(organization.getId(), typeName);
		return SUCCESS;

	}

	/**
	 * 饼状图统计
	 */
	@Action(value = "getStatisticTwoDimensionMapPieChartInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"personnelPie", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getStatisticTwoDimensionMapPieChartInfo() {
		if (validateNull(organization, modeType)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		personnelColumn = townshipsUpStatisticService
				.get(modeType)
				.statisticColumnChartInfoByOrgId(organization.getId(), typeName);
		personnelPie = DrawAreaStatisticHelper
				.convertColumn2Pie(personnelColumn);
		return SUCCESS;
	}

	/**
	 * 通过组织机构ID和类型统计二维地图数据（主要应用于辖区分布）
	 * 
	 * @return
	 */
	@Action(value = "statisticTwoDimensionMapInfoByOrgIdAndTypeName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"statisticList", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String statisticTwoDimensionMapInfoByOrgIdAndTypeName() {
		if (validateNull(organization, modeType, typeName)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		SearchInfoVo searchVo = new SearchInfoVo(gisType, organization.getId(),
				typeName);
		statisticList = townshipsUpStatisticService.get(modeType)
				.statisticInfoForAreaBySearchVo(searchVo);
		return SUCCESS;
	}

	/**
	 * 通过组织机构ID和坐标集合获取二维地图数据（主要应用于画区域统计）
	 * 
	 * @return statisticList
	 */
	@Action(value = "statisticTwoDimensionMapInfoByOrgIdAndPoints", results = {
			@Result(name = "success", location = "/openLayersMap/gisInformation/countData.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String statisticTwoDimensionMapInfoByOrgIdAndPoints() {
		if (validateNull(organization, points)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}

		drawAreaStatisticVo = DrawAreaStatisticHelper.countBoundMapNum(
				drawAreaStatisticVo, points, organization,
				townshipsUpStatisticService);
		drawAreaStatisticVo = DrawAreaStatisticHelper.countSum(
				drawAreaStatisticVo, points, organization,
				townshipsUpStatisticService);

		return SUCCESS;
	}

	@Actions({
			@Action(value = "countGridLayerInfoByOrgId", results = {
					@Result(name = "success", location = "/openLayersMap/gisInformation/viewVillageProfile.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "countGridLayerInfoByOrgIdForPopup", results = {
					@Result(name = "success", location = "/openLayersMap/product_3.0/popupTextStatisticInfo.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "countGridLayerInfoByOrgIdForProduct", results = {
					@Result(name = "success", location = "/openLayersMap/product_3.0/statisticInfo.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String countGridLayerInfoByOrgId() {
		if (validateNull(organization)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		drawAreaStatisticVo.setOrganization(organization);
		drawAreaStatisticVo = DrawAreaStatisticHelper.countMapSum(
				drawAreaStatisticVo, points, organization,
				townshipsUpStatisticService);

		return SUCCESS;
	}

	private JurisdictionStatisticVo jurisdictionStatisticVo;

	@Action(value = "countJurisdictionStatisticByOrgId", results = {
			@Result(name = "success", location = "/openLayersMap/product_3.0/popupJurisdictionStatisticInfo.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String countJurisdictionStatisticByOrgId() {
		if (validateNull(organization)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		jurisdictionStatisticVo = new JurisdictionStatisticVo();
		jurisdictionStatisticVo.setOrganization(organization);
		jurisdictionStatisticVo = DrawAreaStatisticHelper
				.countJurisdictionMapSum(jurisdictionStatisticVo, organization,
						townshipsUpStatisticService);

		return SUCCESS;
	}

	/**
	 * 乡镇以上统计通用查询
	 * 
	 * @return
	 */
	@Action(value = "statisticTwoDimensionMapInfoByOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"statisticList", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String statisticTwoDimensionMapInfoByOrgId() {
		if (validateNull(organization, tableName, screenCoordinateVo)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		TownshipsUpStatisticService upStatisticService = townshipsUpStatisticService
				.get(modeType);
		if (upStatisticService instanceof AbstractCommonTownshipsUpStaticService) {
			((AbstractCommonTownshipsUpStaticService) upStatisticService)
					.initParams(tableName, screenCoordinateVo);
		}
		statisticList = upStatisticService.statisticInfoByOrgId(organization
				.getId());
		return SUCCESS;
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
	
	public JurisdictionStatisticVo getJurisdictionStatisticVo() {
		return jurisdictionStatisticVo;
	}

	public void setJurisdictionStatisticVo(
			JurisdictionStatisticVo jurisdictionStatisticVo) {
		this.jurisdictionStatisticVo = jurisdictionStatisticVo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<StatisticInfoVo> getStatisticList() {
		return statisticList;
	}

	public void setStatisticList(List<StatisticInfoVo> statisticList) {
		this.statisticList = statisticList;
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

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public HighchartColumnVo getPersonnelColumn() {
		return personnelColumn;
	}

	public void setPersonnelColumn(HighchartColumnVo personnelColumn) {
		this.personnelColumn = personnelColumn;
	}

	public List<Object[]> getPersonnelPie() {
		return personnelPie;
	}

	public void setPersonnelPie(List<Object[]> personnelPie) {
		this.personnelPie = personnelPie;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public DrawAreaStatisticVo getDrawAreaStatisticVo() {
		return drawAreaStatisticVo;
	}

	public void setDrawAreaStatisticVo(DrawAreaStatisticVo drawAreaStatisticVo) {
		this.drawAreaStatisticVo = drawAreaStatisticVo;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getChildTableName() {
		return childTableName;
	}

	public void setChildTableName(String childTableName) {
		this.childTableName = childTableName;
	}

	public String getGisType() {
		return gisType;
	}

	public void setGisType(String gisType) {
		this.gisType = gisType;
	}

}
