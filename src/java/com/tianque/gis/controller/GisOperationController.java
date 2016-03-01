package com.tianque.gis.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.GisInfo;
import com.tianque.gis.domain.vo.GisCountNumVo;
import com.tianque.gis.domain.vo.LocationVo;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.GisService;
import com.tianque.gis.service.SearchGisActualUnitService;
import com.tianque.gis.service.SearchGisHouseInfoService;
import com.tianque.gis.service.SearchGisIssueNewsService;

/**
 * @author john
 *         用于操作 房屋场所绑定解绑。
 */
@Controller("gisOperationController")
@Namespace("/gis/commonGisManage")
@Transactional
@Scope("request")
public class GisOperationController extends BaseAction {

	private static final long serialVersionUID = 3873493788231628543L;

	@Autowired
	private GisService gisService;
	@Autowired
	private SearchGisHouseInfoService searchHouseInfoService;
	@Autowired
	private SearchGisActualUnitService gisActualUnitService;
	@Autowired
	private SearchGisIssueNewsService searchIssueNewService;
	private PopulationVo populationVo;
	private String type;
	private LocationVo locationVo;
	private GisInfo gisInfo;
	private Boolean flag;
	private List<GisCountNumVo> gisCountList;
	private Long orgId;
	// 统计类型
	private String typeId;

	/**
	 * Gis3D 数据绑定
	 * 
	 * @return
	 */
	@Action(value = "bindPersonOnMap", results = {
			@Result(name = "success", params = { "root", "populationVo", "ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String bindPersonOnMap() {
		if ("organizationDubboService".equals(getServiceName())) {
			populationVo = gisService.bindPartyWorkOnMap(populationVo); // 党工委绑定
		} else if ("searchActualUnitService".equals(getServiceName())) {
			populationVo = gisService.bindAcualCompanyOnMap(populationVo); // 实有单位
		} else if ("searchGisIssueNewsService".equals(getServiceName())) {
			populationVo = gisService.bindIssueNewsOnMap(populationVo); // 事件处理
		} else {
			populationVo = gisService.bindPersonOnMap(populationVo); // 人口绑定
		}
		return SUCCESS;
	}

	/**
	 * Gis3D 人口解绑
	 * 
	 * @return
	 */
	@Action(value = "unBindPersonOnMap", results = {
			@Result(name = "success", params = { "root", "populationVo", "ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String unBindPersonOnMap() {
		if ("organizationDubboService".equals(getServiceName())) {
			populationVo = gisService.unBindOrgOnMap(populationVo);
		} else if ("searchActualUnitService".equals(getServiceName())) {
			populationVo = gisService.unBindActualCompanyOnMap(populationVo);
		} else if ("searchGisIssueNewsService".equals(getServiceName())) {
			populationVo = gisService.unBindIssueNewsOnMap(populationVo);
		} else {
			populationVo = gisService.unBindPersonOnMap(populationVo);
		}
		return SUCCESS;
	}

	/**
	 * Gis3D 场所绑定
	 * 
	 * @return
	 */
	@Action(value = "boundGisToLocation", results = {
			@Result(name = "success", params = { "root", "gisInfo", "ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String boundGisToLocation() {
		checkMethOfBoundGisToLocationParameter();
		if (null != errorMessage)
			return ERROR;
		gisInfo = gisService.boundGisToLocation(locationVo);
		return SUCCESS;
	}

	/**
	 * gis3D 场所解绑
	 * 
	 * @return
	 */
	@Action(value = "unboundGisToLocation", results = {
			@Result(name = "success", params = { "root", "flag", "ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String unboundGisToLocation() {
		checkMethodOfUnboundGisToLocation();
		if (null != errorMessage)
			return ERROR;
		flag = gisService.unboundGisToLocation(locationVo);
		return SUCCESS;
	}

	/**
	 * Gis3D 根据orgId和typeId 去分类统gis地图上需要展示数据
	 */
	@Action(value = "countActualPopulationByOrgIdAndActulaType", results = {
			@Result(name = "success", params = { "root", "gisCountList", "ignoreHierarchy",
					"false", "excludeNullProperties", "true" }, type = "json"),
			@Result(name = "htmlSuccess", location = "/gis3D/statistics.jsp"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String countActualPopulationByOrgIdAndActulaType() {
		if (typeId.equals("population") || typeId.equals("undefined")) {
			gisCountList = searchHouseInfoService.countActualPopulationByOrgIdAndActulaType(orgId);
		} else if (typeId.equals("actualHouse")) {
			gisCountList = searchHouseInfoService.countActualHouseByOrgId(orgId);
		} else if (typeId.equals("actualUnit")) {
			gisCountList = gisActualUnitService.countActualHouseByOrgId(orgId);
		} else if (typeId.equals("issueNews")) {
			gisCountList = searchIssueNewService.countActualHouseByOrgId(orgId);
		}
		return "htmlSuccess";
	}

	private void checkMethOfBoundGisToLocationParameter() {
		if (null == locationVo.getId())
			errorMessage = "绑定实例主键为空";
		if (null == locationVo.getType())
			errorMessage = "绑定实例类型为空";
		if (null == locationVo.getGisInfo().getBuildingId())
			errorMessage = "绑定buildingId为空";
		if (null == locationVo.getGisInfo().getCenterX())
			errorMessage = "绑定地图X轴坐标为空";
		if (null == locationVo.getGisInfo().getCenterY())
			errorMessage = "绑定地图Y轴坐标为空";

	}

	private void checkMethodOfUnboundGisToLocation() {
		if (null == locationVo.getId())
			errorMessage = "绑定实例主键为空";
		if (null == locationVo.getType())
			errorMessage = "绑定实例类型为空";
	}

	private String getServiceName() {
		return type + "Service";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PopulationVo getPopulationVo() {
		return populationVo;
	}

	public void setPopulationVo(PopulationVo populationVo) {
		this.populationVo = populationVo;
	}

	public LocationVo getLocationVo() {
		return locationVo;
	}

	public void setLocationVo(LocationVo locationVo) {
		this.locationVo = locationVo;
	}

	public GisService getGisService() {
		return gisService;
	}

	public void setGisService(GisService gisService) {
		this.gisService = gisService;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public List<GisCountNumVo> getGisCountList() {
		return gisCountList;
	}

	public void setGisCountList(List<GisCountNumVo> gisCountList) {
		this.gisCountList = gisCountList;
	}

}
