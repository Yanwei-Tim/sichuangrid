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
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.service.CircumSearchService;

@Namespace("/gis/twoDimensionMapCircumSearchCommonManage")
@Transactional
@Scope("prototype")
@Controller("circumSearchController")
public class CircumSearchController<T> extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private Map<String, CircumSearchService<T>> circumSearchService;
	private String modeType;
	private CircumInfoVo circumInfoVo;

	/**
	 * 二维地图周边查询
	 * 
	 * @return circumInfoList
	 */
	@Action(value = "findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" ,"excludeNullProperties","true"}),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat() {
		if (validateNull(circumInfoVo,circumInfoVo.getElements(), circumInfoVo.getRange(), 
				circumInfoVo.getLon(), circumInfoVo.getLat(), modeType)) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		gridPage = new GridPage<T>(circumSearchService.get(modeType)
				.findCircumInfoByElementsAndRangeAndCenterLonLat(circumInfoVo, page, rows, sidx, sord));
		return SUCCESS;
	}
	
	private boolean validateNull(Object ...objects) {
		for(Object obj : objects){
			if(obj == null)
				return true;
			else if ((obj instanceof String) && ((String)obj).trim().length()==0)
				return true;
			else if ((obj instanceof Organization) && ((Organization)obj).getId() == null)
				return true;
		}
		return false;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public CircumInfoVo getCircumInfoVo() {
		return circumInfoVo;
	}

	public void setCircumInfoVo(CircumInfoVo circumInfoVo) {
		this.circumInfoVo = circumInfoVo;
	}
}
