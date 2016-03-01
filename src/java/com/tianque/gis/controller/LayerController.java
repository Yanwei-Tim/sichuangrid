package com.tianque.gis.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.domain.Layer;
import com.tianque.domain.Organization;
import com.tianque.gis.service.LayerService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("request")
@SuppressWarnings("serial")
@Controller("layerController")
@Namespace("/system/layerManage")
public class LayerController extends BaseAction {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private LayerService layerService;

	private Organization organization;

	private Layer layer;

	private List<Layer> layerList;

	private boolean boo;

	private String poinStr;

	private Integer orgLevel;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/gis3D/sysadmin/maintainLayer.jsp"),
			@Result(name = "view", type = "json", params = { "root", "layerList",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String dispatch() {
		if (organization == null || organization.getId() == null) {
			this.errorMessage = "请选择一个组织";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(organization.getId());
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (organization == null) {
				organization = new Organization();
				organization.setId(organization.getId());
			}
			if (layer == null) {
				layer = new Layer();
			}
			layer.setOrganization(organization);
			layer.setOrgInternalCode(organization.getOrgInternalCode());
		}

		if (DialogMode.VIEW_MODE.equals(mode)) {
			layerList = layerService.getLayerByOrganizationId(organization.getId());
			return "view";
		}
		return SUCCESS;
	}

	@Action(value = "getLayerPointsListByOrgId", results = {
			@Result(name = "success", type = "json", params = { "root", "layerList",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getLayerPointsListByOrgId() {
		// 判断是否是区县以上级别，是：获取所有区县图层集合对象，否：获取当前层级图层集合对象
		Boolean bool = organizationDubboService.ifGreaterThanDistrictOrgId(organization.getId());
		if (bool) {
			layerList = layerService.getDistrictLayerByOrgId(organization.getId());
		} else {
			layerList = layerService.getLayerByOrganizationId(organization.getId());
		}
		return SUCCESS;
	}

	@Action(value = "getLayerCenterCoordinateByOrgId", results = {
			@Result(name = "success", type = "json", params = { "root", "layer", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getLayerCenterCoordinateByOrgId() {
		layerList = layerService.getLayerByOrganizationId(organization.getId());
		if (null != layerList && layerList.size() > 0) {
			for (Layer ly : layerList) {
				if (null != ly.getCenterX() && null != ly.getCenterY()) {
					layer = ly;
					break;
				}
			}
		}
		return SUCCESS;
	}

	@Action(value = "addLayer", results = {
			@Result(type = "json", name = "success", params = { "root", "layer", "ignoreHierarchy",
					"false" }),
			@Result(type = "json", name = "error", params = { "root", "errorMessage" }) })
	public String addLayer() {
		layer = layerService.addLayer(layer);
		return SUCCESS;
	}

	@Action(value = "deleteLayerByOrganizationId", results = {
			@Result(type = "json", name = "success", params = { "root", "boo", "ignoreHierarchy",
					"false" }),
			@Result(type = "json", name = "error", params = { "root", "errorMessage" }) })
	public String deleteLayerByOrganizationId() {
		boo = layerService.deleteLayerByOrganizationId(organization.getId());
		return SUCCESS;
	}

	/**
	 * 获取上层级PointerStr
	 * 
	 * @return poinStr
	 */
	@Action(value = "getSuperiorPointsByOrgId", results = {
			@Result(name = "success", type = "json", params = { "root", "poinStr",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getSuperiorPointsByOrgId() {
		if (null == organization || null == organization.getId()) {
			this.errorMessage = "orgId不得为空!";
			return ERROR;
		}
		poinStr = layerService.getSuperiorPointsByOrgId(organization.getId());
		return SUCCESS;
	}

	@Action(value = "checkLevel", results = {
			@Result(name = "success", type = "json", params = { "root", "orgLevel",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String checkLevel() {
		if (null == organization || null == organization.getId()) {
			this.errorMessage = "orgId不得为空!";
			return ERROR;
		}
		orgLevel = layerService.checkLevel(organization.getId());
		return SUCCESS;
	}

	@Action(value = "getPointersByOrgId", results = {
			@Result(name = "success", type = "json", params = { "root", "poinStr",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getPointersByOrgId() {
		if (null == organization || null == organization.getId()) {
			this.errorMessage = "orgId不得为空!";
			return ERROR;
		}
		poinStr = layerService.getPointersByOrgId(organization.getId());
		return SUCCESS;
	}

	/**
	 * 显示组织机构已绑定的顶级图层
	 * 
	 * @return
	 */
	@Action(value = "listVisibleLayers", results = {
			@Result(name = "success", type = "json", params = { "root", "layerList",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String listVisibleLayers() {
		if (null == organization || null == organization.getId() || organization.getId() < 1L) {
			errorMessage = "请选择所在组织机构";
			return ERROR;
		}
		layerList = layerService.getVisibleLayerByOrgId(organization.getId());
		return SUCCESS;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Layer getLayer() {
		return layer;
	}

	public void setLayer(Layer layer) {
		this.layer = layer;
	}

	public List<Layer> getLayerList() {
		return layerList;
	}

	public void setLayerList(List<Layer> layerList) {
		this.layerList = layerList;
	}

	public boolean isBoo() {
		return boo;
	}

	public void setBoo(boolean boo) {
		this.boo = boo;
	}

	public String getPoinStr() {
		return poinStr;
	}

	public void setPoinStr(String poinStr) {
		this.poinStr = poinStr;
	}

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}
}
