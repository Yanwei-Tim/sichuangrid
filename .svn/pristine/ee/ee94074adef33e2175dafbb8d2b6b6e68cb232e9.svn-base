package com.tianque.openLayersMap.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.PropertyDict;
import com.tianque.openLayersMap.domian.CommonEntityInfoVo;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisModuleVo;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.service.SysGisFunctionService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.service.SysModuleManageService;
import com.tianque.openLayersMap.util.FunctionType;
import com.tianque.openLayersMap.util.LayerChoose;
import com.tianque.openLayersMap.util.SystemProperty;

/**
 * 二维地图类别管理
 * 
 * @author yubin
 */
@Namespace("/gis/twoDimensionMapModuleManage")
@SuppressWarnings("serial")
@Scope("prototype")
@Transactional
@Controller("sysModuleManageController")
public class SysModuleManageController extends BaseAction {
	@Autowired
	private SysModuleManageService sysModuleManageService;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;
	@Autowired
	private SysGisFunctionService sysGisFunctionService;

	private GisModuleVo gisModuleVo;
	private Long id;
	private String mode;
	private List<PropertyDict> propertydictList;
	private Boolean flag;
	private GisTypeManage gisTypeManage;
	private List<GisModuleVo> gisModuleVoList;
	private List<CommonEntityInfoVo> headerSearchList;
	private String modeType;
	private GisFunction function;
	private List<GisFunction> gisFunctionList;
	private String userName;

	@Action(value = "addModule", results = {
			@Result(name = "success", type = "json", params = { "root", "gisModuleVo",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String addModule() throws Exception {
		if (null == gisModuleVo) {
			this.errorMessage = "gisModuleVo不能为空!";
			return ERROR;
		}
		gisModuleVo = sysModuleManageService.addModule(gisModuleVo);
		sysGisFunctionService.addFunction(function, gisModuleVo.getId(), null);

		return SUCCESS;
	}

	@Action(value = "deleteModule", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String deleteModule() throws Exception {
		if (null == id) {
			this.errorMessage = "id不能为空!";
			return ERROR;
		}
		sysModuleManageService.deleteModuleById(id);
		sysGisFunctionService.deleteGisFunctionByModuleId(id);
		if (null != gisTypeManage && null != gisTypeManage.getInnerKey()) {// 删除子类信息
			List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
					.getNeedGisTypeManagesByInnerType(gisTypeManage);
			for (int i = 0; i < gisTypeManageList.size(); i++) {
				deleteSonClassById(gisTypeManageList.get(i).getId());
			}
		}
		return SUCCESS;
	}

	/**
	 * 删除子类相关信息
	 * 
	 * @param sonClassId
	 */
	private void deleteSonClassById(Long sonClassId) {
		sysGisTypeManageService.deleteGisTypeManageById(sonClassId);
		sysGisFunctionService.deleteGisFunctionBySonClassId(sonClassId);
	}

	@Action(value = "updateModule", results = {
			@Result(name = "success", type = "json", params = { "root", "gisModuleVo",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String updateModule() throws Exception {
		if (null == gisModuleVo || null == gisModuleVo.getId()) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		gisModuleVo = sysModuleManageService.updateModule(gisModuleVo);
		sysGisFunctionService.updateFunction(function, gisModuleVo.getId(), null);
		return SUCCESS;
	}

	/**
	 * 根据mode得到新增修改查等操作的时候页面的显示
	 */
	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/openLayersMap/system/sysManage/maintainModuleDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String dispatch() throws Exception {
		if (null == mode) {
			this.errorMessage = "mode不能为空!";
			return ERROR;
		}
		if (DialogMode.VIEW_MODE.equals(mode) || DialogMode.EDIT_MODE.equals(mode)) {
			gisModuleVo = sysModuleManageService.getModuleById(gisModuleVo.getId());
			if (gisModuleVo.getIsSystemAttribute() == SystemProperty.systemProperty) {
				flag = true;// 如果是系统属性则返回true
			}

			function = sysGisFunctionService.getGisFunctionByModuleIdAndFunctionType(
					gisModuleVo.getId(), FunctionType.REFINESEARCH);

		}
		return SUCCESS;
	}

	@Action(value = "getGisModuleVoById", results = {
			@Result(name = "success", type = "json", params = { "root", "gisModuleVo",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getGisModuleVoById() throws Exception {
		if (null == gisModuleVo || null == gisModuleVo.getId()) {
			this.errorMessage = "id不能为空!";
			return ERROR;
		}
		gisModuleVo = sysModuleManageService.getModuleById(gisModuleVo.getId());
		return SUCCESS;
	}

	/**
	 * 判断大类表名是否存在
	 */
	@Action(value = "isExistTableName", results = {
			@Result(name = "success", type = "json", params = { "root", "flag", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String isExistTableName() throws Exception {
		if (null == gisModuleVo || null == gisModuleVo.getTableName()) {
			this.errorMessage = "表名不能为空!";
			return ERROR;
		}
		flag = sysModuleManageService.isExistTableName(gisModuleVo.getTableName());
		return SUCCESS;
	}

	/**
	 * 得到图层列表
	 * 
	 * @return String
	 */
	@Action(value = "findModule", results = {
			@Result(name = "success", type = "json", params = { "root", "gisModuleVoList",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findModule() throws Exception {
		gisModuleVoList = sysModuleManageService.findAllModule();
		for (int i = 0; i < gisModuleVoList.size(); i++) {// 得到子类list
			if (gisModuleVoList.get(i).getIsHasSonClass()
					&& null != gisModuleVoList.get(i).getTableName()
					&& gisModuleVoList.get(i).getIsBusinessLayer() == LayerChoose.BUSINESS_LAYER) {
				gisTypeManage = new GisTypeManage();
				gisTypeManage.setInnerKey(gisModuleVoList.get(i).getTableName());
				List<GisTypeManage> gisTypeManageList = sysGisTypeManageService
						.getNeedGisTypeManagesByInnerType(gisTypeManage);
				gisModuleVoList.get(i).setGisTypeManageList(gisTypeManageList);
			}
		}
		return SUCCESS;
	}

	/**
	 * 得到大类列表
	 * 
	 * @return String
	 */
	@Action(value = "findAllModule", results = {
			@Result(name = "success", location = "/openLayersMap/system/sysManage/gisModule.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findAllModule() throws Exception {
		gisModuleVoList = sysModuleManageService.findAllModule();
		userName = ThreadVariable.getUser().getUserName();
		return SUCCESS;
	}

	/**
	 * 得到二维地图详情搜索(搜索条件)列表
	 * 
	 * @return String
	 */
	@Action(value = "getGisModuleByIssearch", results = {
			@Result(name = "success", location = "/openLayersMap/gisHeader/gisHeader.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getGisModuleByIssearch() throws Exception {
		headerSearchList = sysModuleManageService.getGisModuleByIssearch();
		return SUCCESS;
	}

	/**
	 * 设置辖区名称
	 * 
	 * @return String
	 */
	@Action(value = "setJurisdictionName", results = {
			@Result(name = "success", type = "json", params = { "root", "gisModuleVoList",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String setJurisdictionName() throws Exception {
		if (null == modeType) {
			this.errorMessage = "modeType不能为空!";
			return ERROR;
		}
		gisModuleVoList = sysModuleManageService.getGisModuleByModeType(modeType);
		return SUCCESS;
	}

	public GisModuleVo getGisModuleVo() {
		return gisModuleVo;
	}

	public void setGisModuleVo(GisModuleVo gisModuleVo) {
		this.gisModuleVo = gisModuleVo;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public List<PropertyDict> getPropertydictList() {
		return propertydictList;
	}

	public void setPropertydictList(List<PropertyDict> propertydictList) {
		this.propertydictList = propertydictList;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public GisTypeManage getGisTypeManage() {
		return gisTypeManage;
	}

	public void setGisTypeManage(GisTypeManage gisTypeManage) {
		this.gisTypeManage = gisTypeManage;
	}

	public List<GisModuleVo> getGisModuleVoList() {
		return gisModuleVoList;
	}

	public void setGisModuleVoList(List<GisModuleVo> gisModuleVoList) {
		this.gisModuleVoList = gisModuleVoList;
	}

	public List<CommonEntityInfoVo> getHeaderSearchList() {
		return headerSearchList;
	}

	public void setHeaderSearchList(List<CommonEntityInfoVo> headerSearchList) {
		this.headerSearchList = headerSearchList;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public List<GisFunction> getGisFunctionList() {
		return gisFunctionList;
	}

	public void setGisFunctionList(List<GisFunction> gisFunctionList) {
		this.gisFunctionList = gisFunctionList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GisFunction getFunction() {
		return function;
	}

	public void setFunction(GisFunction function) {
		this.function = function;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
