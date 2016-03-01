package com.tianque.gis.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.core.base.BaseAction;
import com.tianque.domain.BuildingData;
import com.tianque.domain.GisInfo;
import com.tianque.gis.domain.vo.BuildingInfoVo;
import com.tianque.gis.domain.vo.RoundBuildingInfo;
import com.tianque.gis.service.BuildingDataService;
import com.tianque.gis.service.SearchGisHouseInfoService;
import com.tianque.gis.util.ModuleMap;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("buildingDataController")
@Namespace("/gis3D/buildingInfoManage")
public class BuildingDataController extends BaseAction {
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	private BuildingDataService buildingDataService;
	@Autowired
	private SearchGisHouseInfoService searchHouseInfoService;

	@Autowired
	private ActualHouseService houseACInfoService;

	private BuildingData buildingData;

	private Long buildingId;

	private String boundModuleType;

	private Double pointX;

	private Double pointY;

	private Integer status;

	private BuildingInfoVo buildingInfoVo;

	private String isSuccessStr;

	private RoundBuildingInfo roundBuildingInfo;

	private GisInfo minOption, maxOption;

	@Action(value = "dispatchOperate", results = { @Result(name = "success", location = "/gis3D/buildingInfo/viewBuildingData.jsp") })
	public String dispatch() throws Exception {
		buildingData.setBuildingId(buildingId);
		buildingData.setHouseSum(buildingDataService
				.countHouseByBuildingId(buildingId));
		status = ModuleMap.judgeFromPersonOrPlase(boundModuleType);
		return SUCCESS;
	}

	@Action(value = "searchRoundBuildingInfo", results = { @Result(name = "success", location = "/gis3D/includes/roundingData.jsp") })
	public String searchRoundBuildingInfo() throws Exception {
		roundBuildingInfo = searchHouseInfoService.getRoundHouseInfo(minOption,
				maxOption);
		return SUCCESS;
	}

	@Action(value = "searchISRoundExistBuildingInfo", results = {
			@Result(name = "success", params = { "root", "isSuccessStr" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String searchISRoundExistBuildingInfo() throws Exception {
		List<HouseInfo> houses = houseACInfoService.searchMapScope(minOption,
				maxOption);
		if (houses == null || houses.size() < 1) {
			return ERROR;
		}
		isSuccessStr = SUCCESS;
		return SUCCESS;
	}

	@Action(value = "getBuildingDataByBuildingId", results = { @Result(name = "success", type = "json", params = {
			"root", "buildingData", "ignoreHierarchy", "false" }) })
	public String getBuildingDataByBuildingId() {
		buildingData = buildingDataService
				.getBuildingDataByBuildingId(buildingId);
		status = ModuleMap.judgeFromPersonOrPlase(boundModuleType);
		return SUCCESS;
	}

	/**
	 * 根据热区ID查询楼宇信息
	 * 
	 * @return
	 */
	@Action(value = "getBuildingInfoVoFromHotSpot", results = { @Result(name = "success", location = "/gis3D/buildingInfo/gisBuildingformation.jsp") })
	public String getBuildingInfoVoFromHotSpot() {
		buildingInfoVo = searchHouseInfoService
				.getBuildingInfoVoByBuildingId(buildingId);
		return SUCCESS;
	}

	public BuildingData getBuildingData() {
		return buildingData;
	}

	public void setBuildingData(BuildingData buildingData) {
		this.buildingData = buildingData;
	}

	public void setBoundModuleType(String boundModuleType) {
		this.boundModuleType = boundModuleType;
	}

	public String getBoundModuleType() {
		return boundModuleType;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public Double getPointX() {
		return pointX;
	}

	public void setPointX(Double pointX) {
		this.pointX = pointX;
	}

	public Double getPointY() {
		return pointY;
	}

	public void setPointY(Double pointY) {
		this.pointY = pointY;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setBuildingInfoVo(BuildingInfoVo buildingInfoVo) {
		this.buildingInfoVo = buildingInfoVo;
	}

	public BuildingInfoVo getBuildingInfoVo() {
		return buildingInfoVo;
	}

	public String getIsSuccessStr() {
		return isSuccessStr;
	}

	public void setIsSuccessStr(String isSuccessStr) {
		this.isSuccessStr = isSuccessStr;
	}

	public RoundBuildingInfo getRoundBuildingInfo() {
		return roundBuildingInfo;
	}

	public void setRoundBuildingInfo(RoundBuildingInfo roundBuildingInfo) {
		this.roundBuildingInfo = roundBuildingInfo;
	}

	public GisInfo getMinOption() {
		return minOption;
	}

	public void setMinOption(GisInfo minOption) {
		this.minOption = minOption;
	}

	public GisInfo getMaxOption() {
		return maxOption;
	}

	public void setMaxOption(GisInfo maxOption) {
		this.maxOption = maxOption;
	}

}