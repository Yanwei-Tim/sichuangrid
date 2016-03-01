package com.tianque.openLayersMap.controller;

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
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.service.BuildDataService;

/**
 * 建筑物个性化控制层
 * 
 */
@Namespace("/twoDimensionMap/buildDataTwoDimensionMapManage")
@Transactional
@Scope("prototype")
@Controller("buildDataTwoDimensionMapController")
public class BuildDataTwoDimensionMapController extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private BuildDataService buildDataService;

	private Long orgId;
	private BuildDataInfoVo buildDataInfoVo;
	private String queryStr;

	/**
	 * 根据根据经纬度获取建筑物信息
	 * 
	 * @return buildDataInfoVo
	 */
	@Action(value = "getBuildDataInfoVoByCenterLonLat", results = {
			@Result(name = "success", type = "json", params = { "root",
					"buildDataInfoVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getBuildDataInfoVoByLonLat() {
		if (buildDataInfoVo == null || buildDataInfoVo.getLat() == null
				|| buildDataInfoVo.getLon() == null) {
			this.errorMessage = "经纬度不能为空";
			return ERROR;
		}
		buildDataInfoVo = buildDataService.getBuildDataInfoVoByHourseInfoId(
				buildDataInfoVo, orgId);
		return SUCCESS;
	}

	@Action(value = "getUnBoundedBuilddatas", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getUnBoundedBuilddatas() {
		if (queryStr == null || orgId == null) {
			this.errorMessage = "查询语句不能为空或网格id不能为空!";
			return ERROR;
		}
		PageInfo<BuildDataInfoVo> pageInfo = buildDataService
				.findUnBoundBuilddatasByStr(queryStr, page, rows, sidx, sord,
						orgId);
		gridPage = new GridPage<BuildDataInfoVo>(pageInfo);
		return SUCCESS;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public BuildDataInfoVo getBuildDataInfoVo() {
		return buildDataInfoVo;
	}

	public void setBuildDataInfoVo(BuildDataInfoVo buildDataInfoVo) {
		this.buildDataInfoVo = buildDataInfoVo;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

}
