package com.tianque.gis.controller;

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
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.gis.domain.vo.PeripheryVo;
import com.tianque.gis.service.SearchPeripheryService;

@Namespace("/gis/gisPeripheryInfoManager")
@Transactional
@Scope("request")
@Controller("gisPeripheryInfoController")
public class GisPeripheryInfoController extends BaseAction {
	@Autowired
	private Map<String, SearchPeripheryService> searchPeripheryServiceMap;
	private PageInfo<PeripheryVo> peripheryPageInfo;
	private PeripheryVo peripheryVo;
	private List<PeripheryVo> peripheryVoList;
	private String type;

	@Action(value = "getPageInfoForSearchPeriphery", results = {
			@Result(name = "success", params = { "root", "gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String getPageInfoForSearchPeriphery() {
		peripheryPageInfo = searchPeripheryServiceMap.get(getServiceName())
				.findPeripherysByTypeAndRange(peripheryVo, page, rows, sidx, sord);
		gridPage = new GridPage<PeripheryVo>(peripheryPageInfo);
		return SUCCESS;
	}

	@Action(value = "getListForSearchPeriphery", results = {
			@Result(name = "success", params = { "root", "peripheryVoList", "ignoreHierarchy",
					"false", "excludeNullProperties", "true" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String getListForSearchPeriphery() {
		peripheryVoList = searchPeripheryServiceMap.get(getServiceName())
				.findPeripherysByTypeAndRange(peripheryVo, sidx, sord);
		return SUCCESS;
	}

	@Action(value = "getPeripheryList", results = {
			@Result(name = "success", location = "/gis3D/peripheryInfo/peripheryList.jsp"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String getPeripheryList() {
		peripheryVoList = searchPeripheryServiceMap.get(getServiceName()).getPeripheryList(
				peripheryVo);
		return SUCCESS;
	}

	private String getServiceName() {
		return type + "Service";
	}

	public PageInfo<PeripheryVo> getPeripheryPageInfo() {
		return peripheryPageInfo;
	}

	public void setPeripheryPageInfo(PageInfo<PeripheryVo> peripheryPageInfo) {
		this.peripheryPageInfo = peripheryPageInfo;
	}

	public PeripheryVo getPeripheryVo() {
		return peripheryVo;
	}

	public void setPeripheryVo(PeripheryVo peripheryVo) {
		this.peripheryVo = peripheryVo;
	}

	public List<PeripheryVo> getPeripheryVoList() {
		return peripheryVoList;
	}

	public void setPeripheryVoList(List<PeripheryVo> peripheryVoList) {
		this.peripheryVoList = peripheryVoList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
