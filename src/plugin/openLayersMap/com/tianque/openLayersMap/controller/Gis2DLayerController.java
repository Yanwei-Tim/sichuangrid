package com.tianque.openLayersMap.controller;

import java.io.File;
import java.util.ArrayList;
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
import com.tianque.domain.Organization;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.openLayersMap.service.Gis2DLayerService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * gis二维地图区域管理
 */
@Namespace("/system/gis2DLayerManage")
@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("gis2DLayerController")
public class Gis2DLayerController extends BaseAction {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private Gis2DLayerService gis2DLayerService;
	private Gis2DLayer gis2DLayer;
	private Organization organization;
	private ScreenCoordinateVo screenCoordinateVo;
	private List<StatisticInfoVo> statisticList;
	private List<Gis2DLayer> gis2dLayers = new ArrayList<Gis2DLayer>();
	private List<Organization> orgs;
	private String gisType;// 地图2D、3D的判断
	private File upload;
	private String uploadFileName;

	@Action(value = "dispatch", results = {
			@Result(name = "view", type = "json", params = { "root",
					"gis2DLayer", "ignoreHierarchy", "false" }),
			@Result(name = "success", location = "/openLayersMap/system/layerManage/maintainLayerDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (organization == null || organization.getId() == null) {
			this.errorMessage = "请选择一个组织机构";
			return ERROR;
		}
		if (DialogMode.VIEW_MODE.equals(mode)) {
			gis2DLayer = gis2DLayerService.getByOrgId(organization.getId(),
					gisType);
			return "view";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			Gis2DLayer layer = gis2DLayerService.getByOrgId(
					organization.getId(), gisType);
			gis2DLayer.setRemark(layer.getRemark());
			gis2DLayer.setOrganization(layer.getOrganization());
			gis2DLayer.setOrgInternalCode(layer.getOrgInternalCode());
		} else if (DialogMode.ADD_MODE.equals(mode)) {
			organization = organizationDubboService
					.getSimpleOrgById(organization.getId());
			gis2DLayer.setOrganization(organization);
			gis2DLayer.setOrgInternalCode(organization.getOrgInternalCode());
			gis2DLayer.setRemark(organization.getOrgName());
		}
		return SUCCESS;
	}

	@Action(value = "updateGis2DLayer", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gis2DLayer", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateGis2DLayer() throws Exception {
		if (gis2DLayer.getOrganization() == null
				|| gis2DLayer.getOrganization().getId() == null) {
			this.errorMessage = "请选择一个组织机构";
			return ERROR;
		}
		gis2DLayer = gis2DLayerService.updateGis2DLayer(gis2DLayer);
		return SUCCESS;
	}

	@Action(value = "addGis2DLayer", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gis2DLayer", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addGis2DLayer() throws Exception {
		if (gis2DLayer == null || gis2DLayer.getOrganization() == null
				|| gis2DLayer.getOrganization().getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		gis2DLayer = gis2DLayerService.addGis2DLayer(gis2DLayer);
		return SUCCESS;
	}

	@Action(value = "deleteGis2DLayerByOrganizationId", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteGis2DLayerByOrganizationId() throws Exception {
		if (organization == null || organization.getId() == null) {
			this.errorMessage = "请选择一个组织机构";
			return ERROR;
		}
		gis2DLayerService.deleteUndersByOrgId(organization.getId());
		return SUCCESS;
	}

	@Action(value = "getGis2DLayerByOrganizationId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gis2DLayer", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getGis2DLayerByOrganizationId() throws Exception {
		if (organization == null || organization.getId() == null) {
			this.errorMessage = "请选择一个组织机构";
			return ERROR;
		}
		gis2DLayer = gis2DLayerService
				.getByOrgId(organization.getId(), gisType);
		if (gis2DLayer != null && gis2DLayer.getPoints() != null) {
			gis2DLayer.setOrganization(organizationDubboService
					.getSimpleOrgById(organization.getId()));
		}
		return SUCCESS;
	}

	/**
	 * 通过当前地图级别和中心坐标获取当前屏幕内的地图区域信息（用于地图和组织机构树联动）
	 */
	@Action(value = "getGis2DLayerByCenterPointAndZoom", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gis2DLayer", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getGis2DLayerByCenterPointAndZoom() throws Exception {
		if (gis2DLayer == null || gis2DLayer.getZoom() == null
				|| gis2DLayer.getCenterX() == null
				|| gis2DLayer.getCenterY() == null) {
			this.errorMessage = "参数错误，请联系管理员!";
			return ERROR;
		}
		gis2DLayer = gis2DLayerService.getByCenterPointAndZoom(gis2DLayer,
				gisType);
		return SUCCESS;
	}

	/**
	 * 通过orgId获取下级地图区域对象集合
	 */
	@Action(value = "getUnderGis2DLayersByOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gis2dLayers", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getUnderGis2DLayersByOrgId() throws Exception {
		if (null == organization || organization.getId() == null) {
			this.errorMessage = "组织机构id不能为空";
			return ERROR;
		}
		List<Gis2DLayer> gis2dLayersTwo = gis2DLayerService.findUndersByOrgId(
				organization.getId(), gisType);
		for (int i = 0; i < gis2dLayersTwo.size(); i++) {
			Gis2DLayer gis2dLayerTwo = gis2dLayersTwo.get(i);
			Organization org = gis2dLayerTwo.getOrganization();
			if (org != null && org.getId() != null) {
				gis2dLayerTwo.setOrganization(organizationDubboService
						.getSimpleOrgById(org.getId()));
			}
			gis2dLayers.add(gis2dLayerTwo);
		}
		return SUCCESS;
	}

	/**
	 * 根据部门编号(departmentno)查询所有的子组织机构列表
	 * 
	 * @return
	 */
	@Action(value = "findOrgsByDepartmentNo", results = {
			@Result(name = "success", type = "json", params = { "root", "orgs",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findOrgsByDepartmentNo() throws Exception {
		if (organization == null || organization.getDepartmentNo() == null) {
			this.errorMessage = "组织机构的部门编号不能为空";
			return ERROR;
		}
		orgs = organizationDubboService.findOrgsByDepartmentNo(organization
				.getDepartmentNo());
		return SUCCESS;
	}

	/**
	 * 根据组织机构id删除本级区域
	 * 
	 * @return
	 */
	@Action(value = "deleteSameLevelLayerByOrgId", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteSameLevelLayerByOrgId() throws Exception {
		if (organization == null || organization.getId() == null) {
			this.errorMessage = "组织机构不能为空!";
			return ERROR;
		}
		gis2DLayerService.deleteByOrgId(organization.getId());

		return SUCCESS;
	}

	/**
	 * 导入组织机构文件
	 * 
	 * @return
	 */
	@Action(value = "importGis2DLayer", results = {
			@Result(name = "success", type = "json", params = { "root", "id",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String importGis2DLayer() throws Exception {
		if (upload == null) {
			this.errorMessage = "上传文件不能为空!";
			return ERROR;
		}
		id = gis2DLayerService
				.importGis2DLayer(gisType, upload, uploadFileName);
		return SUCCESS;
	}

	public Gis2DLayer getGis2DLayer() {
		return gis2DLayer;
	}

	public void setGis2DLayer(Gis2DLayer gis2dLayer) {
		gis2DLayer = gis2dLayer;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public ScreenCoordinateVo getScreenCoordinateVo() {
		return screenCoordinateVo;
	}

	public void setScreenCoordinateVo(ScreenCoordinateVo screenCoordinateVo) {
		this.screenCoordinateVo = screenCoordinateVo;
	}

	public List<StatisticInfoVo> getStatisticList() {
		return statisticList;
	}

	public void setStatisticList(List<StatisticInfoVo> statisticList) {
		this.statisticList = statisticList;
	}

	public List<Gis2DLayer> getGis2dLayers() {
		return gis2dLayers;
	}

	public void setGis2dLayers(List<Gis2DLayer> gis2dLayers) {
		this.gis2dLayers = gis2dLayers;
	}

	public List<Organization> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<Organization> orgs) {
		this.orgs = orgs;
	}

	public String getGisType() {
		return gisType;
	}

	public void setGisType(String gisType) {
		this.gisType = gisType;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

}
