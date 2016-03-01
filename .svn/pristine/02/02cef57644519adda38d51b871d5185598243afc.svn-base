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
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.vo.HouseLivingTotalInfo;
import com.tianque.gis.domain.vo.HouseInfoVo;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.GisService;
import com.tianque.gis.service.SearchGisHouseInfoService;

@Namespace("/gis/gisHouseInfoManager")
@Transactional
@Scope("request")
@Controller("gisHouseInfoController")
public class GisHouseInfoController extends BaseAction {
	@Autowired
	private SearchGisHouseInfoService searchHouseInfoService;
	@Autowired
	private GisService gisService;

	private PageInfo<HouseInfoVo> houseInfoVoPageInfo;
	private List<HouseInfoVo> houseInfoList;
	private List<HouseLivingTotalInfo> popupCountInfoList;
	private PageInfo<PopulationVo> pageInfo;
	private HouseInfoVo houseInfoVo;
	private String type;
	private Long orgId;
	private String queryStr;
	private String actulaType;
	private Long houseId;
	private GisInfo gisInfo;

	@Action(value = "bindHouseOnMap", results = { @Result(name = "success", params = { "root",
			"true", "ignoreHierarchy", "false" }, type = "json") })
	public String bindHouseOnMap() {
		gisService.bindHouseOnMap(houseId, gisInfo);
		return SUCCESS;
	}

	@Action(value = "unBindHouseOnMap", results = {
			@Result(name = "success", params = { "root", "true", "ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String unBindHouseOnMap() {
		gisService.unBindHouseOnMap(houseId);
		return SUCCESS;
	}

	/**
	 * 根据orgId和queryStr 去检索住房
	 * 
	 * @return
	 */
	@Action(value = "getPageInfoByQueryStrForSearchHouseInfo", results = {
			@Result(name = "success", params = { "root", "gridPage", "ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String getPageInfoByQueryStrForSearchHouseInfo() {
		houseInfoVoPageInfo = searchHouseInfoService.getPageInfoByQueryStrForSearchHouseInfo(orgId,
				queryStr, page, rows, sidx, sord);
		gridPage = new GridPage<HouseInfoVo>(houseInfoVoPageInfo);
		return SUCCESS;
	}

	/**
	 * 查找gis 实有房屋列表，根据orgId获取
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "searchkeyHouseInfoListByorgId", results = {
			@Result(name = "success", params = { "root", "gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String searchkeyHouseInfoListByorgId() {
		houseInfoVoPageInfo = searchHouseInfoService.searchkeyHouseInfoListByorgId(orgId, type,
				page, rows, sidx, sord);
		gridPage = new GridPage<HouseInfoVo>(houseInfoVoPageInfo);
		return SUCCESS;
	}

	/**
	 * Gis2D 根据父类orgId和actualType 去分类统计实有人口,房屋,单位
	 */
	@Action(value = "countActualPopulationByParentOrgIdAndActulaType", results = {
			@Result(name = "success", params = { "root", "popupCountInfoList", "ignoreHierarchy",
					"false", "excludeNullProperties", "true" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String countActualPopulationByParentOrgIdAndActulaType() {
		popupCountInfoList = searchHouseInfoService
				.countActualPopulationByParentOrgIdAndActulaType(orgId, actulaType);
		return SUCCESS;
	}

	/**
	 * 未调用
	 * 
	 * @return success
	 */
	@Action(value = "getHouseById", results = { @Result(name = "success", location = "/edushiGis/information/gisPlaceInformation.jsp") })
	public String getHouseById() {
		houseInfoVo = gisService.getHouseById(houseId);
		return SUCCESS;
	}

	/**
	 * 未调用
	 * 
	 * @return success
	 */
	@Action(value = "getKeyPlaceById", results = { @Result(name = "success", location = "/edushiGis/information/gisPlaceInformation.jsp") })
	public String getKeyPlaceById() {
		houseInfoVo = gisService.getKeyPlaceByIdAndType(houseId, type);
		return SUCCESS;
	}

	/**
	 * 根据orgId显示实有房屋层
	 * 
	 * @return
	 */
	@Action(value = "listAllBindingActualHouse", results = {
			@Result(name = "success", params = { "root", "houseInfoList", "ignoreHierarchy",
					"false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String listAllBindingActualHouse() {
		if (null == orgId) {
			errorMessage = "请选择网格";
			return ERROR;
		}
		houseInfoList = searchHouseInfoService.findAllBindingHouseInfoBy(orgId);
		return SUCCESS;
	}

	public PageInfo<HouseInfoVo> getHouseInfoVoPageInfo() {
		return houseInfoVoPageInfo;
	}

	public void setHouseInfoVoPageInfo(PageInfo<HouseInfoVo> houseInfoVoPageInfo) {
		this.houseInfoVoPageInfo = houseInfoVoPageInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public PageInfo<PopulationVo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<PopulationVo> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public HouseInfoVo getHouseInfoVo() {
		return houseInfoVo;
	}

	public void setHouseInfoVo(HouseInfoVo houseInfoVo) {
		this.houseInfoVo = houseInfoVo;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public List<HouseInfoVo> getHouseInfoList() {
		return houseInfoList;
	}

	public void setHouseInfoList(List<HouseInfoVo> houseInfoList) {
		this.houseInfoList = houseInfoList;
	}

	public String getActulaType() {
		return actulaType;
	}

	public void setActulaType(String actulaType) {
		this.actulaType = actulaType;
	}

	public List<HouseLivingTotalInfo> getPopupCountInfoList() {
		return popupCountInfoList;
	}

	public void setPopupCountInfoList(List<HouseLivingTotalInfo> popupCountInfoList) {
		this.popupCountInfoList = popupCountInfoList;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

}
