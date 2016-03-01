package com.tianque.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.tianque.core.base.BaseAction;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Permission;
import com.tianque.sysadmin.service.PermissionService;

@Namespaces({ @Namespace("/sysadmin/menuManage"),
		@Namespace("/hotModuel/sysadmin/menuManage") })
@Controller("menuController")
@Transactional
@Scope("prototype")
public class MenuController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(MenuController.class);
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private PermissionService permissionService;
	private String gisUrl;
	private String unifiedSearchUrl;
	private String ename;
	private String cname;
	private List<Permission> permissions;
	private Map<String, List<Permission>> childMap = new HashMap<String, List<Permission>>();
	private Map<String, List<Permission>> grandsonMap = new HashMap<String, List<Permission>>();

	private String menuJson;

	@Actions(value = {
			@Action(value = "getHighLevelBaseInfoMenuList", results = { @Result(location = "/baseinfo/middleLevelSideBar.jsp", name = "success") }),
			@Action(value = "getLowLevelBaseInfoMenuList", results = { @Result(location = "/baseinfo/lowLevelSideBar.jsp", name = "success") }),
			@Action(value = "getLowLevelBaseInfoMenuListByPageList", results = { @Result(location = "/includes/pageList.jsp", name = "success") }),
			@Action(value = "getNavigationListForHaiNing", results = { @Result(location = "/login/haining/navigation.jsp", name = "success") }),
			@Action(value = "getNavigationMap", results = { @Result(location = "/workBench/common/list.jsp", name = "success") }),
			@Action(value = "getLeftMenuList", results = { @Result(location = "/includes/leftMenuList.jsp", name = "success") }) })
	public String getBaseInfoMenuList() throws Exception {
		if (ename != null) {
			cname = permissionService.findPermissionByEname(ename).getCname();
		}
		permissions = permissionService
				.getChildMenuByEnameAndExcludeButtons(ename);
		if ("socialManagement".equals(ename)) {
			permissions = permissionService
					.getChildMenuByEnameAndExcludeButtons(permissions.get(0)
							.getEname());
		}
		getChildMapByEname();
		gisUrl = globalSettingService.getGlobalValue(GlobalSetting.GIS_URL);
		unifiedSearchUrl = globalSettingService
				.getGlobalValue(GlobalSetting.UNIFIEDSEARCH_URL);
		return SUCCESS;
	}

	@Action(value = "getLeftMenuListToJson", results = { @Result(location = "/includes/leftMenuList.jsp", name = "success") })
	public String getLeftMenuListToJson() throws Exception {
		Long start = System.currentTimeMillis();
		Long permissionId = null;
		if (ename != null) {
			Permission permission = permissionService
					.findPermissionByEname(ename);
			if (permission != null) {
				cname = permission.getCname();
				permissionId = permission.getId();
			}
		}
		menuJson = new Gson().toJson(permissionService
				.getUserPermissionTreeByPermissionId(permissionId));
		menuJson = menuJson.replace("A_P_NAME", ename);
		gisUrl = globalSettingService.getGlobalValue(GlobalSetting.GIS_URL);
		logger.error("菜单获取耗时:" + (System.currentTimeMillis() - start));
		return SUCCESS;
	}

	private Map<String, Object> makePermissionToJson(Permission permission,
			List<String> permissionList) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!permissionList.contains(permission.getEname())
				&& !ThreadVariable.getUser().isAdmin()) {
			return map;
		}
		map.put("name", permission.getCname());
		map.put("ename", permission.getEname());
		map.put("baseUrl", permission.getNormalUrl());
		map.put("leaderUrl", permission.getLeaderUrl());
		map.put("gridUrl", permission.getGridUrl());
		map.put("pename", ename);
		return map;
	}

	@Action(value = "getNavigationList", results = { @Result(location = "/includes/navigation.jsp", name = "success") })
	public String getNavigationList() throws Exception {
		permissions = permissionService
				.getChildMenuByEnameAndExcludeButtons(ename);
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		List<String> permissionList = permissionService
				.findPermissionsEnameByUserId(ThreadVariable.getUser().getId());
		for (Permission permission : permissions) {
			Map<String, Object> jsonMap = makePermissionToJson(permission,
					permissionList);
			if (null == jsonMap || jsonMap.size() < 1) {
				continue;
			}
			jsonList.add(jsonMap);
		}
		menuJson = new Gson().toJson(jsonList);
		unifiedSearchUrl = globalSettingService
				.getGlobalValue(GlobalSetting.UNIFIEDSEARCH_URL);
		return SUCCESS;
	}

	@Actions(value = { @Action(value = "getTabByParentEname", results = { @Result(location = "/includes/tabList.jsp", name = "success") }) })
	public String getMenuListByParentId() throws Exception {
		permissions = permissionService.getChildMenuByEname(ename);
		return SUCCESS;
	}

	private void getChildMapByEname() {
		for (Permission permission : permissions) {
			List<Permission> parents = permissionService
					.getChildMenuByEnameAndExcludeButtons(permission.getEname());
			for (Permission parent : parents) {
				List<Permission> grandsons = permissionService
						.getChildMenuByEnameAndExcludeButtons(parent.getEname());
				grandsonMap.put(parent.getEname(), grandsons);
			}
			childMap.put(permission.getEname(), parents);
		}
	}

	@Action(value = "findPermissionsEnameByUserId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"permissions", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPermissionsEnameByUserId() throws Exception {
		permissions = permissionService.findAllPermissionsByCurrentUserRoleId(
				ThreadVariable.getUser().getId(), 0);
		return SUCCESS;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Map<String, List<Permission>> getChildMap() {
		return childMap;
	}

	public void setChildMap(Map<String, List<Permission>> childMap) {
		this.childMap = childMap;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Map<String, List<Permission>> getGrandsonMap() {
		return grandsonMap;
	}

	public void setGrandsonMap(Map<String, List<Permission>> grandsonMap) {
		this.grandsonMap = grandsonMap;
	}

	public String getGisUrl() {
		return gisUrl;
	}

	public void setGisUrl(String gisUrl) {
		this.gisUrl = gisUrl;
	}

	public String getUnifiedSearchUrl() {
		return unifiedSearchUrl;
	}

	public void setUnifiedSearchUrl(String unifiedSearchUrl) {
		this.unifiedSearchUrl = unifiedSearchUrl;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getMenuJson() {
		return menuJson;
	}

	public void setMenuJson(String menuJson) {
		this.menuJson = menuJson;
	}

}
