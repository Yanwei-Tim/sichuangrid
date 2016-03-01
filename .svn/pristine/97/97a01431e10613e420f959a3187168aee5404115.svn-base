package com.tianque.baseInfo.scenicManage.scenicEquipment.controller;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.scenicManage.scenicEquipment.domain.ScenicEquipment;
import com.tianque.baseInfo.scenicManage.scenicEquipment.service.ScenicEquipmentService;
import com.tianque.baseInfo.scenicManage.scenicEquipment.vo.SearchScenicEquipmentVo;
import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.baseInfo.scenicManage.scenicSpot.service.ScenicSpotService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/scenicEquipmentManage")
@Scope("request")
@Controller("scenicEquipmentController")
public class ScenicEquipmentController extends BaseAction {
	private Long organizationId;
	private String locationIds;
	private ScenicEquipment location;
	@Autowired
	private ScenicEquipmentService scenicEquipmentService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ScenicSpotService scenicSpotService;
	private SearchScenicEquipmentVo searchScenicEquipmentVo;

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/scenicManage/scenicEquipment/scenicEquipmentDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/scenicManage/scenicEquipment/scenicEquipmentSearchDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/scenicManage/scenicEquipment/commonView.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/siteinfo/internetBar/internetBarViewDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {

		if (null != id) {
			location = scenicEquipmentService.getScenicEquipmentById(id);
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
			getAroundScenicIdsByAroundScenicName(location);
		}
		if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	/**
	 * 解密調用
	 * 
	 * @return
	 */

	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/scenicManage/scenicEquipment/scenicEquipmentDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/scenicManage/scenicEquipment/commonView.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		if (null != id) {
			location = scenicEquipmentService.getScenicEquipmentById(id);
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
			getAroundScenicIdsByAroundScenicName(location);

		}
		if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	@Action(value = "viewScenicEquipment", results = { @Result(name = "viewBase", location = "/baseinfo/scenicManage/scenicEquipment/scenicEquipmentView.jsp") })
	@EncryptAnnotation
	public String viewScenicSpot() throws Exception {
		if ("viewBase".equals(mode)) {
			location = scenicEquipmentService.getScenicEquipmentById(location
					.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		return "viewBase";
	}

	@Action(value = "viewScenicEquipmentForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String viewScenicEquipmentForMobile() throws Exception {
		if (null == location || null == location.getId()) {
			errorMessage = "参数错误";
			return ERROR;
		}
		location = scenicEquipmentService.getScenicEquipmentById(location
				.getId());
		location.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(location
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "scenicEquipmentList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		searchScenicEquipmentVo = new SearchScenicEquipmentVo();
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		if (location != null) {
			searchScenicEquipmentVo.setIsEmphasis(location.getIsEmphasis());
		}
		if (null == organizationId) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			searchScenicEquipmentVo.setOrgInternalCode(organization
					.getOrgInternalCode());
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					scenicEquipmentService.searchScenicEquipmentForPage(page,
							rows, sidx, sord, searchScenicEquipmentVo),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	private PageInfo<ScenicEquipment> emptyPage(int pageSize) {
		PageInfo<ScenicEquipment> pageInfo = new PageInfo<ScenicEquipment>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<ScenicEquipment>());
		return pageInfo;
	}

	@Action(value = "deleteScenicEquipmentByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String deleteByIds() throws Exception {
		scenicEquipmentService.deleteScenicEquipmentByIds(analyze(locationIds));
		return SUCCESS;

	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "addScenicEquipment", actionName = "saveScenicEquipment"),
			@PermissionFilter(ename = "updateScenicEquipment", actionName = "saveScenicEquipment") })
	@Action(value = "saveScenicEquipment", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		location.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
				location.getOrganization(), organizationDubboService));
		if (DialogMode.ADD_MODE.equals(mode)) {
			location = scenicEquipmentService.addScenicEquipment(location);
		}
		if (DialogMode.EDIT_MODE.equals(mode)) {
			if (null == location) {
				return ERROR;
			}
			location = scenicEquipmentService.updateScenicEquipment(location);

		}
		return SUCCESS;
	}

	@Action(value = "getScenicEquipmentById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getScenicEquipmentById() throws Exception {
		location = scenicEquipmentService.getScenicEquipmentById(id);
		return SUCCESS;

	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		scenicEquipmentService.updateEmphasisByIdList(analyze(locationIds),
				location);
		return SUCCESS;
	}

	/**
	 * 根据周边景点的名称得到周边景点的id
	 * 
	 * @param location
	 */
	private void getAroundScenicIdsByAroundScenicName(ScenicEquipment location) {
		if (location.getAroundScenic() != null
				&& location.getAroundScenic() != "") {
			String[] scenicSpotNames = location.getAroundScenic().split(";");
			String scenicSpotIds = new String();
			for (String scenicSpotName : scenicSpotNames) {
				ScenicSpot scenicSpot = scenicSpotService
						.findScenicSpotByNameAndOrgId(scenicSpotName, location
								.getOrganization().getId());
				if (scenicSpot != null) {
					scenicSpotIds += scenicSpot.getId();
				}
			}
			location.setAroundScenicIds(scenicSpotIds);
		}
	}

	public ScenicEquipment getLocation() {
		return location;
	}

	public void setLocation(ScenicEquipment location) {
		this.location = location;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

}
