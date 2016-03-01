package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Hospital;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchHospitalVo;
import com.tianque.service.HospitalService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/hospitalManage")
@Controller("hospitalController")
@Scope("prototype")
@Transactional
public class HospitalsController extends BaseAction {
	private Long organizationId;
	private Boolean hasDuplicateLocation;
	private String locationIds;

	private Hospital location;

	@Autowired
	private HospitalService HospitalService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private SearchHospitalVo searchHospitalVo;
	private String houseCode;
	@Autowired
	protected ActualHouseService houseInfoService;
	private String supervisorName; // 治安负责员名称
	private String locationType; // 场所类型名称
	private String superviceRecordName; // 巡场情况

	/***
	 * 页面点击添加，修改，查看等
	 */
	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/siteinfo/hospital/HospitalDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/siteinfo/hospital/HospitalSearchDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/siteinfo/hospital/HospitalViewDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/siteinfo/hospital/HospitalViewDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		ajaxUrl = "hasDuplicateHospitalLocation";
		if (null != id) {
			location = HospitalService.getHospitalById(id);
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
			// if (location.getPlaceId() != null &&
			// !location.getId().equals(0l)) {
			// houseCode = houseInfoService.getHouseInfoById(location.getId())
			// .getHouseCode();
			// }
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

	/***
	 * 页面点击修改，查看等(解密调用)
	 */

	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/siteinfo/hospital/HospitalDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/siteinfo/hospital/HospitalViewDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		ajaxUrl = "hasDuplicateHospitalLocation";
		if (null != id) {
			location = HospitalService.getHospitalById(id);
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		} else if (location != null && location.getId() != null) {
			location = HospitalService.getHospitalById(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	/**
	 * 验证同一个网格下是否有名称相同的医院
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateHospitalLocation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicateLocation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicateLocation() throws Exception {
		if (null == organizationId || null == location) {
			errorMessage = "组织机构ID,或location为空";
			return ERROR;
		}
		hasDuplicateLocation = HospitalService.hasDuplicateHospital(
				organizationId, location.getHospitalName(), location.getId());
		return SUCCESS;
	}

	@Action(value = "hospitalList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	/**
	 * 列表页面的显示
	 */
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		searchHospitalVo = new SearchHospitalVo();
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		if (location != null) {
			searchHospitalVo.setIsEmphasis(location.getIsEmphasis());
		}
		if (null == organizationId) {
			gridPage = new GridPage(emptyPage(rows));
		} else {

			searchHospitalVo.setOrgInternalCode(organization
					.getOrgInternalCode());
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					HospitalService.searchHospitalForPage(page, rows, sidx,
							sord, searchHospitalVo), organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	private PageInfo<Hospital> emptyPage(int pageSize) {
		PageInfo<Hospital> pageInfo = new PageInfo<Hospital>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Hospital>());
		return pageInfo;
	}

	@Action(value = "deleteHospitalByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String deleteByIds() throws Exception {
		HospitalService.deleteHospitalByIds(analyzeLocationIds());
		return SUCCESS;
	}

	private Long[] analyzeLocationIds() {
		String[] deleteIds = locationIds.split(",");
		List<Long> idList;
		if (deleteIds[0].equals("")) {
			idList = initTargetId(deleteIds, 1);
		} else {
			idList = initTargetId(deleteIds, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "addHospital", actionName = "saveHospital"),
			@PermissionFilter(ename = "updateHospital", actionName = "saveHospital") })
	@Action(value = "saveHospital", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		location.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
				location.getOrganization(), organizationDubboService));
		if (DialogMode.ADD_MODE.equals(mode)) {
			location = HospitalService.addHospital(location);
		}
		if (DialogMode.EDIT_MODE.equals(mode)) {
			if (null == location) {
				return ERROR;
			}
			location = HospitalService.updateHospital(location);

		}
		return SUCCESS;
	}

	@Action(value = "getHospitalById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getHospitalById() throws Exception {
		location = HospitalService.getHospitalById(id);
		return SUCCESS;

	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	/**
	 * 注销和取消注销的方法,单个注销
	 */
	public String updateEmphasiseById() throws Exception {
		location = HospitalService.updateEmphasiseById(analyzeLocationIds()[0],
				location);
		return SUCCESS;
	}

	@Action(value = "viewPublicPlace", results = { @Result(name = "success", location = "/baseinfo/siteinfo/hospital/HospitalView.jsp") })
	@EncryptAnnotation
	public String viewInfo() throws Exception {
		if (null != location && null != location.getId()) {
			location = HospitalService.getHospitalById(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		return SUCCESS;
	}

	@Action(value = "viewHospitalForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String viewHospitalForMobile() throws Exception {
		if (null == location || null == location.getId()) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		location = HospitalService.getHospitalById(location.getId());
		location.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(location
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	public Hospital getLocation() {
		return location;
	}

	public void setLocation(Hospital location) {
		this.location = location;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public String getLocationType() {
		return locationType;
	}

	public String getSuperviceRecordName() {
		return superviceRecordName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public void setSuperviceRecordName(String superviceRecordName) {
		this.superviceRecordName = superviceRecordName;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Boolean getHasDuplicateLocation() {
		return hasDuplicateLocation;
	}

	public void setHasDuplicateLocation(Boolean hasDuplicateLocation) {
		this.hasDuplicateLocation = hasDuplicateLocation;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}
}
