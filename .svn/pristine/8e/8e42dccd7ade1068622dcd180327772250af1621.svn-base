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
import com.tianque.gis.domain.vo.ActualUnitVo;
import com.tianque.gis.service.GisService;
import com.tianque.gis.service.SearchGisActualUnitService;

@Controller("gisActualUnitController")
@Scope("request")
@Transactional
@Namespace("/gis/gisActualUnitManager")
public class GisActualUnitController extends BaseAction {
	@Autowired
	private SearchGisActualUnitService searchGisActualUnitService;
	@Autowired
	private GisService gisService;
	private PageInfo<ActualUnitVo> unitPage;
	private List<ActualUnitVo> actualUnitVos;
	private ActualUnitVo actualUnitVo;
	private Long unitId;
	private String queryStr;
	private Long orgId;
	private GisInfo gisInfo;
	private String currentTime;
	private Boolean showed;

	/**
	 * 事件处理查询，根据查询条件查询实有单位
	 * 
	 * @return
	 */
	@Action(value = "searchActualUnitforGisByorgIdAndQueryStr", results = {
			@Result(name = "success", params = { "root", "gridPage", "ignoreHierarchy", "flase",
					"excludeNullProperties", "true" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String searchActualUnitforGisByorgIdAndQueryStr() {
		unitPage = searchGisActualUnitService.searchActualUnitforGisByorgIdAndQueryStr(queryStr,
				orgId, page, rows, sidx, sord);
		gridPage = new GridPage<ActualUnitVo>(unitPage);
		return SUCCESS;
	}

	/**
	 * 事件处理列表查询，根据orgId查询实有单位
	 * 
	 * @return
	 */
	@Action(value = "searchKeyUnitListSearchByOrgId", results = {
			@Result(name = "success", params = { "root", "gridPage", "ignoreHierarchy", "flase",
					"excludeNullProperties", "true" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String searchKeyUnitListSearchByOrgId() {
		unitPage = searchGisActualUnitService.searchKeyUnitListSearchByOrgId(orgId, page, rows,
				sidx, sord);
		gridPage = new GridPage<ActualUnitVo>(unitPage);
		return SUCCESS;
	}

	@Action(value = "listAllBindingActualUnitVo", results = {
			@Result(name = "success", params = { "root", "actualUnitVos", "ignoreHierarchy",
					"flase", "excludeNullProperties", "true" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String listAllBindingActualUnitVo() {
		if (null == orgId || orgId < 0L) {
			errorMessage = "请选择组织机构";
			return ERROR;
		}
		actualUnitVos = searchGisActualUnitService.listAllBindingActualUnitVo(orgId);
		return SUCCESS;
	}

	/**
	 * 获取绑定后的实有单位信息
	 * 
	 * @return
	 */
	@Action(value = "getActualUnitDatialInfoByUnitId", results = {
			@Result(name = "success", location = "/gis3D//actualUnit/actualUnitViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getActualUnitDatialInfoByUnitId() {
		if (null != unitId) {
			actualUnitVo = searchGisActualUnitService
					.getActualUnitDatialInfoByUnitId(unitId, orgId);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/**
	 * @return
	 */
	@Action(value = "bindActualUnitOnMap", results = {
			@Result(name = "success", params = { "root", "true", "ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String bindActualUnitOnMap() {
		gisService.bindActualUnitOnMap(unitId, gisInfo);
		return SUCCESS;
	}

	@Action(value = "unBindActualUnitOnMap", results = {
			@Result(name = "success", params = { "root", "true", "ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String unBindActualUnitOnMap() {
		gisService.unBindActualUnitOnMap(unitId);
		return SUCCESS;
	}

	public PageInfo<ActualUnitVo> getUnitPage() {
		return unitPage;
	}

	public void setUnitPage(PageInfo<ActualUnitVo> unitPage) {
		this.unitPage = unitPage;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public ActualUnitVo getActualUnitVo() {
		return actualUnitVo;
	}

	public void setActualUnitVo(ActualUnitVo actualUnitVo) {
		this.actualUnitVo = actualUnitVo;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public void setActualUnitVos(List<ActualUnitVo> actualUnitVos) {
		this.actualUnitVos = actualUnitVos;
	}

	public List<ActualUnitVo> getActualUnitVos() {
		return actualUnitVos;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setShowed(Boolean showed) {
		this.showed = showed;
	}

	public Boolean getShowed() {
		return showed;
	}
}
