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
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.service.KeyPlaceService;
import com.tianque.openLayersMap.util.ModuleMap;

/**
 * 重点场所的个性化调用
 * 
 * @author jiangjinling
 * 
 */
@Namespace("/twoDimensionMap/keyPlaceMapManage")
@Transactional
@Scope("prototype")
@Controller("keyPlaceMapController")
public class KeyPlaceMapController extends BaseAction {

	private static final long	serialVersionUID	= 1L;

	@Autowired
	private KeyPlaceService		keyPlaceService;

	private String				searchValue;				// 搜索条件
	private String				type;
	private Long				orgId;
	private String				locationName;				// 场所名

	/**
	 * 房屋--根据组织机构Id和重点场所的子类类型找到对应的未绑定的场所信息
	 * 
	 * @return
	 */
	
	@Action(value = "findUnBoundKeyPlaceByOrgIdAndType", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false"}),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findUnBoundKeyPlaceByOrgIdAndType() {
		if (orgId == null || type == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage<KeyPlaceInfoVo>(keyPlaceService.findUnBoundKeyPlaceByOrgIdAndType(orgId, type, page, rows, sidx,
				sord, searchValue));
		return SUCCESS;
	}

	/**
	 * 根据重点场所子类类型得到场所名称
	 * 
	 * @return
	 */
	@Action(value = "getLocationNameByType", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationName", "ignoreHierarchy", "false"}),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getLocationNameByType() {
		locationName = ModuleMap.getModuleName(type);
		return SUCCESS;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
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

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}
