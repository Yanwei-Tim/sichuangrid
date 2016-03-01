package com.tianque.openLayersMap.controller;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.Organization;
import com.tianque.openLayersMap.domian.vo.GisBoundVo;
import com.tianque.openLayersMap.service.MapBindingManageService;
import com.tianque.openLayersMap.service.impl.AbstractCommonMapBindingManageService;

@Namespace("/openLayersMap/twoDimensionMapManageManage")
@Transactional
@Scope("prototype")
@Controller("mapBindingManageController")
public class MapBindingManageController<T> extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private Map<String, MapBindingManageService<T>> mapBindingManageService;
	private Long id;
	private String modeType; // 模块类型
	private String lon; // 经度
	private String lat; // 纬度
	private String[] ids; // id集合，用于批量绑定
	private String type; // 子模块类型
	private T returnValue; // 返回值
	private Long buildDataId;// 建筑物ID
	private String tableName; // 表名
	private String functionType;
	private String event; // 绑定类型
	private String gisType;// 2D 3D
	private GisBoundVo gisBoundVo;// 地图绑定

	/**
	 * 二维地图绑定
	 * 
	 * @return returnValue
	 */
	@Action(value = "boundTwoDimensionMap", results = {
			@Result(name = "success", type = "json", params = { "root",
					"returnValue", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String boundTwoDimensionMap() {
		if (validateNull(gisBoundVo, modeType)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		MapBindingManageService<T> bindingManageService = mapBindingManageService
				.get(modeType);
		if (bindingManageService instanceof AbstractCommonMapBindingManageService) {
			((AbstractCommonMapBindingManageService<T>) bindingManageService)
					.initParams(tableName, functionType, event);
		}
		// returnValue =
		// mapBindingManageService.get(modeType).boundTwoDimensionMap(ids, lon,
		// lat, type, buildDataId,gisType);
		returnValue = mapBindingManageService.get(modeType)
				.boundTwoDimensionMap(gisBoundVo, type);
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

	/**
	 * 二维地图解除绑定
	 * 
	 * @return returnValue
	 */
	@Action(value = "unBoundTwoDimensionMap", results = {
			@Result(name = "success", type = "json", params = { "root",
					"returnValue", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String unBoundTwoDimensionMap() {
		if (validateNull(id, modeType)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		MapBindingManageService<T> bindingManageService = mapBindingManageService
				.get(modeType);
		if (bindingManageService instanceof AbstractCommonMapBindingManageService) {
			((AbstractCommonMapBindingManageService<T>) bindingManageService)
					.initParams(tableName, functionType, event);
		}
		returnValue = mapBindingManageService.get(modeType)
				.unBoundTwoDimensionMap(id, type, gisBoundVo.getOrgId());
		return SUCCESS;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public T getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(T returnValue) {
		this.returnValue = returnValue;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Long getBuildDataId() {
		return buildDataId;
	}

	public void setBuildDataId(Long buildDataId) {
		this.buildDataId = buildDataId;
	}

	public void setGisType(String gisType) {
		this.gisType = gisType;
	}

	public String getGisType() {
		return gisType;
	}

	public GisBoundVo getGisBoundVo() {
		return gisBoundVo;
	}

	public void setGisBoundVo(GisBoundVo gisBoundVo) {
		this.gisBoundVo = gisBoundVo;
	}

}
