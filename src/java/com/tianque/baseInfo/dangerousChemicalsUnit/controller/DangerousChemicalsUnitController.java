package com.tianque.baseInfo.dangerousChemicalsUnit.controller;

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

import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.baseInfo.dangerousChemicalsUnit.service.DangerousChemicalsUnitService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 危险化学品控制类。
 */
@Namespace("/baseinfo/dangerousChemicalsUnitManage")
@Scope("request")
@Controller("dangerousChemicalsUnitController")
@Transactional
public class DangerousChemicalsUnitController extends BaseAction {
	private Long orgId;
	private Long organizationId;
	protected String locationIds;
	@Autowired
	private DangerousChemicalsUnitService dangerousChemicalsUnitService;
	private DangerousChemicalsUnit dangerousChemicalsUnit;
	private DangerousChemicalsUnit location;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private String supervisorName; // 治安负责员名称
	private String locationType; // 场所类型名称
	private String superviceRecordName; // 巡场情况
	private boolean exsite; // 是否存在重复单位名称

	/**
	 * 列表
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "dangerousChemicalsUnitList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			if (null != location) {
				gridPage = new GridPage(
						ControllerHelper.processAllOrgRelativeName(
								dangerousChemicalsUnitService
										.findDangerChemUnitForPageByOrgInternalCode(
												organizationId, page, rows,
												sidx, sord,
												location.getIsEmphasis()),
								organizationDubboService,
								new String[] { "organization" }, organizationId));
			} else {
				gridPage = new GridPage(
						ControllerHelper.processAllOrgRelativeName(
								dangerousChemicalsUnitService
										.findDangerChemUnitForPageByOrgInternalCode(
												organizationId, page, rows,
												sidx, sord, null),
								organizationDubboService,
								new String[] { "organization" }, organizationId));

			}
		}
		return SUCCESS;
	}

	private PageInfo<DangerousChemicalsUnit> emptyPage(int pageSize) {
		PageInfo<DangerousChemicalsUnit> pageInfo = new PageInfo<DangerousChemicalsUnit>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<DangerousChemicalsUnit>());
		return pageInfo;
	}

	/*
	 * 页面请求
	 */
	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/siteinfo/dangerousChemicalsUnit/mainDangerousChemicalsUnitDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/siteinfo/dangerousChemicalsUnit/searchDangerousChemicalsUnitDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/siteinfo/dangerousChemicalsUnit/DangerousChemicalsUnitViewDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/siteinfo/dangerousChemicalsUnit/DangerousChemicalsUnitViewDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if (id == null && location != null && location.getId() != null) {
			id = location.getId();
		}
		if (null != id) {
			dangerousChemicalsUnit = dangerousChemicalsUnitService
					.getDangerousChemicalsUnitById(id);
			dangerousChemicalsUnit.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							dangerousChemicalsUnit.getOrganization().getId(),
							organizationDubboService));
			this.orgId = dangerousChemicalsUnit.getOrganization().getId();
		}
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			return "print";
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			return "search";
		}
		return SUCCESS;
	}

	/*
	 * 页面请求(解密调用)
	 */

	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/siteinfo/dangerousChemicalsUnit/mainDangerousChemicalsUnitDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/siteinfo/dangerousChemicalsUnit/DangerousChemicalsUnitViewDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		if (id == null && location != null && location.getId() != null) {
			id = location.getId();
		}
		if (null != id) {
			dangerousChemicalsUnit = dangerousChemicalsUnitService
					.getDangerousChemicalsUnitById(id);
			dangerousChemicalsUnit.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							dangerousChemicalsUnit.getOrganization().getId(),
							organizationDubboService));
			this.orgId = dangerousChemicalsUnit.getOrganization().getId();
		}
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	/*
	 * 新增 、修改
	 */
	@PermissionFilter(ename = "addDangerousChemicalsUnit")
	@Action(value = "addDangerousChemicalsUnit", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addDangerousChemicalsUnit() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			dangerousChemicalsUnit.setIsEmphasis(false);
			dangerousChemicalsUnit
					.setOrganization(this.constructOrganization());
			dangerousChemicalsUnit = dangerousChemicalsUnitService
					.addDangerousChemicalsUnit(dangerousChemicalsUnit);
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			dangerousChemicalsUnit.setIsEmphasis(false);
			dangerousChemicalsUnit.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							dangerousChemicalsUnit.getOrganization().getId(),
							organizationDubboService));
			dangerousChemicalsUnit = dangerousChemicalsUnitService
					.updateDangerousChemicalsUnit(dangerousChemicalsUnit);
			return SUCCESS;

		}
		return SUCCESS;
	}

	private Organization constructOrganization() {
		Organization result = new Organization();
		result.setId(organizationId);
		return result;
	}

	/**
	 * 验证单位名称唯一性
	 * 
	 * @return
	 */
	@Action(value = "hasDangerousChemicals", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDangerousChemicals() throws Exception {
		if (dangerousChemicalsUnit == null
				|| dangerousChemicalsUnit.getOrganization() == null
				|| dangerousChemicalsUnit.getOrganization().getId() == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		} else {
			exsite = dangerousChemicalsUnitService.hasDangerousChemicalsUnit(
					dangerousChemicalsUnit.getOrganization().getId(),
					dangerousChemicalsUnit.getId(),
					dangerousChemicalsUnit.getUnitName());
			if (exsite) {
				return ERROR;
			} else {
				return SUCCESS;
			}
		}

	}

	/**
	 * 删除危险化学品单位
	 * 
	 * @return SUCCESS
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteDangerousChemicalsUnit")
	@Action(value = "deleteDangerousChemicalsUnitByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		dangerousChemicalsUnitService
				.deleteDangerousChemicalsUnitByIds(analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return SUCCESS
	 */
	@Actions({ @Action(value = "viewDangerousChemicalsUnit", results = { @Result(name = "success", location = "/baseinfo/siteinfo/dangerousChemicalsUnit/DangerousChemicalsUnitView.jsp") }) })
	@EncryptAnnotation
	public String viewDangerousChemicalsUnit() throws Exception {
		if (location != null && location.getId() != null) {
			location = dangerousChemicalsUnitService
					.getDangerousChemicalsUnitById(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		return SUCCESS;
	}

	@Action(value = "getDangerousChemicalsUnitById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getDangerousChemicalsUnitById() throws Exception {
		if (location != null && location.getId() != null) {
			location = dangerousChemicalsUnitService
					.getDangerousChemicalsUnitById(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		return SUCCESS;
	}

	/*
	 * 注销、取消注销
	 */
	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		dangerousChemicalsUnitService.updateEmphasiseByIds(
				analyzePopulationIds(), location);
		return SUCCESS;
	}

	private Long[] analyzePopulationIds() {
		String[] deleteId = locationIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public DangerousChemicalsUnit getDangerousChemicalsUnit() {
		return dangerousChemicalsUnit;
	}

	public void setDangerousChemicalsUnit(
			DangerousChemicalsUnit dangerousChemicalsUnit) {
		this.dangerousChemicalsUnit = dangerousChemicalsUnit;
	}

	public DangerousChemicalsUnit getLocation() {
		return location;
	}

	public void setLocation(DangerousChemicalsUnit location) {
		this.location = location;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getSuperviceRecordName() {
		return superviceRecordName;
	}

	public void setSuperviceRecordName(String superviceRecordName) {
		this.superviceRecordName = superviceRecordName;
	}

	public boolean isExsite() {
		return exsite;
	}

	public void setExsite(boolean exsite) {
		this.exsite = exsite;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
