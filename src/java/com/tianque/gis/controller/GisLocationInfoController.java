package com.tianque.gis.controller;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.PageInfo;
import com.tianque.gis.domain.vo.LocationVo;
import com.tianque.gis.service.SearchLocationService;
import com.tianque.gis.util.ModuleMap;

@Namespace("/gis/gisLocationInfoManager")
@Transactional
@Scope("request")
@Controller("gisLocationInfoController")
public class GisLocationInfoController extends BaseAction {
	@Autowired
	private Map<String, SearchLocationService> searchLocationServiceMap;
	private PageInfo<LocationVo> locationVoPageInfo;
	private Long orgId;
	private String type;
	private String queryStr;
	private LocationVo locationVo;

	@Action(value = "getKeyLocationListByOrgId", results = { @Result(name = "success", location = "/gis3D/unitInfo/locationListSearch.jsp") })
	public String getPageInfoForSearchLocation() {
		locationVoPageInfo = searchLocationServiceMap.get(getServiceName()).findLocationsByOrgId(
				orgId, page, rows, sidx, sord);
		return SUCCESS;
	}

	/**
	 * 根据orgId和queryStr 去检索场所
	 * 
	 * @return
	 */
	@Action(value = "getPageInfoByQueryStrForSearchLocation", results = { @Result(name = "success", location = "/gis3D/unitInfo/locationListSearchForQuery.jsp") })
	public String getPageInfoByQueryStrForSearchLocation() {
		locationVoPageInfo = searchLocationServiceMap.get(getServiceName())
				.findLocationsByOrgIdAndQueryStr(orgId, queryStr, page, rows, sidx, sord);
		return SUCCESS;
	}

	@Action(value = "getLocationNameByType", results = {
			@Result(name = "success", params = { "root", "locationVo", "ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String getLocationNameByType() {
		locationVo.setLocationNamekey(ModuleMap.getModuleName(locationVo.getType()));
		return SUCCESS;
	}

	private String getServiceName() {
		return type + "Service";
	}

	public PageInfo<LocationVo> getLocationVoPageInfo() {
		return locationVoPageInfo;
	}

	public void setLocationVoPageInfo(PageInfo<LocationVo> locationVoPageInfo) {
		this.locationVoPageInfo = locationVoPageInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public LocationVo getLocationVo() {
		return locationVo;
	}

	public void setLocationVo(LocationVo locationVo) {
		this.locationVo = locationVo;
	}

}
