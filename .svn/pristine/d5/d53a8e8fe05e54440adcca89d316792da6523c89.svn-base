package com.tianque.baseInfo.actualCompany.controller;

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

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.actualCompany.service.ActualCompanyService;
import com.tianque.baseInfo.druggy.domain.Druggy;
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
 * 实有单位控制类
 */
@Namespace("/baseinfo/actualCompanyManage")
@Transactional
@Scope("request")
@Controller("actualCompanyController")
public class ActualCompanyController extends BaseAction {

	private Long organizationId;
	private Boolean hasDuplicateLocation;
	private String locationIds;
	private ActualCompany location;

	@Autowired
	private ActualCompanyService actualCompanyService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/**
	 * 实现页面跳转
	 */
	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/actualCompany/actualCompanyDlg.jsp"),
					@Result(name = "KeyCrucialPosition", location = "/baseinfo/actualCompany/actualCompanyKeyCPDlg.jsp"),
					@Result(name = "PreventionFacilities", location = "/baseinfo/actualCompany/actualCompanyPFDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/actualCompany/searchActualCompanyDlg.jsp"),
					@Result(name = "statistic", location = "/baseinfo/actualCompany/statisticSearchActualCompanyList.jsp"),
					@Result(name = "view", location = "/baseinfo/actualCompany/actualCompanyViewDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = {
					@Result(name = "view", location = "/baseinfo/actualCompany/actualCompanyViewDlg_gis.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String dispatchOperate() {
		dispatchOperateMethod();
		if ("addKeyCrucialPosition".equals(mode)
				|| "editKeyCrucialPosition".equals(mode)) {
			return "KeyCrucialPosition";
		} else if ("addPreventionFacilities".equals(mode)
				|| "editPreventionFacilities".equals(mode)) {
			return "PreventionFacilities";

		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		} else if (DialogMode.STATISTIC.equals(mode)) {
			return "statistic";
		} else if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	/**
	 * 实现页面跳转(用于解密调用)
	 */
	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/actualCompany/actualCompanyDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/actualCompany/actualCompanyViewDlg.jsp") })
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() {
		dispatchOperateMethod();
		if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	private void dispatchOperateMethod() {
		if (null != id) {
			location = actualCompanyService.getActualCompanyById(id);
		} else if (location != null && location.getId() != null) {
			location = actualCompanyService.getActualCompanyById(location
					.getId());
		}
		if (null == location) {
			location = new ActualCompany();
			location.setOrganization(new Organization());
		}
		if (null != location.getOrganization() && null != organizationId) {
			location.getOrganization().setId(organizationId);
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
	}

	/**
	 * 查找记录用于jqGrid显示
	 */
	@Action(value = "actualCompanyList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgIdAndIsEmphasis() {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			Boolean isEmphasis = null;
			if (location != null && location.getIsEmphasis() != null) {
				isEmphasis = location.getIsEmphasis();
			}
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					actualCompanyService
							.findActualCompanyForPageByOrgInternalCode(
									organizationId, page, rows, sidx, sord,
									isEmphasis), organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	/**
	 * 批量删除实有单位记录(解密调用)
	 */

	@Action(value = "deleteActualCompanyByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String deleteByIds() {
		actualCompanyService.deleteActualCompany(analyzeLocationIds());
		return SUCCESS;
	}

	/**
	 * 验证实有单位唯一性
	 */
	@Action(value = "hasDuplicateLocation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicateLocation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicateLocation = actualCompanyService.hasDuplicateActualCompany(
				organizationId, location.getCompanyName(), location.getId());
		return SUCCESS;
	}

	/**
	 * 新增/修改实有单位
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "addActualCompany")
	@Action(value = "maintainBaseInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() {
		if (null != location && location.getId() != null) {
			location.getOrganization().setId(organizationId);
			location = actualCompanyService.updateActualCompany(location);
		} else {
			location.getOrganization().setId(organizationId);
			location = actualCompanyService.addActualCompany(location);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "addActualCompany")
	@Action(value = "maintainBusinessInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() {
		if ("addKeyCrucialPosition".equals(mode)
				|| "editKeyCrucialPosition".equals(mode)) {
			if (location != null) {
				location = actualCompanyService
						.updateKeyCrucialPosition(location);
				return SUCCESS;
			}
		} else if ("addPreventionFacilities".equals(mode)
				|| "editPreventionFacilities".equals(mode)) {
			if (location != null) {
				location = actualCompanyService
						.updatePreventionFacilities(location);
				return SUCCESS;
			}
		}
		return null;
	}

	/**
	 * 修改注销状态
	 */
	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() {
		actualCompanyService.updateEmphasisByIdList(analyzeLocationIds(),
				location);
		return SUCCESS;
	}

	/**
	 * 查看实有单位记录
	 */

	@Action(value = "viewActualCompany", results = {
			@Result(name = "success", location = "/baseinfo/actualCompany/actualCompanyView.jsp"),
			@Result(name = "KeyCrucialPosition", location = "/baseinfo/actualCompany/keyCrucialPositionView.jsp"),
			@Result(name = "PreventionFacilities", location = "/baseinfo/actualCompany/preventionFacilitiesView.jsp") })
	public String viewInfo() {
		if (location != null && location.getId() != null) {
			location = actualCompanyService.getActualCompanyById(location
					.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		if ("KeyCrucialPosition".equals(mode)) {
			return "KeyCrucialPosition";

		} else if ("PreventionFacilities".equals(mode)) {
			return "PreventionFacilities";
		}
		return SUCCESS;
	}

	/**
	 * 查看实有单位记录(解密调用)
	 */

	@Action(value = "viewActualCompanyByEncrypt", results = { @Result(name = "success", location = "/baseinfo/actualCompany/actualCompanyView.jsp") })
	@EncryptAnnotation
	public String viewInfoByEncrypt() {
		if (location != null && location.getId() != null) {
			location = actualCompanyService.getActualCompanyById(location
					.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		return SUCCESS;
	}

	@Action(value = "findActualCompanysPractitioners", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findActualCompanysPractitioners() {
		if (location == null || location.getId() == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(
					actualCompanyService.findActualCompanysPractitioners(
							location, page, rows, sidx, sord));
		}
		return SUCCESS;
	}

	@Action(value = "findActualCompanysAddPractitionersForList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findActualCompanysAddPractitionersForList() {
		if (location == null || location.getId() == null) {
			gridPage = new GridPage(emptyPage(rows));
			locationIds = "";
		} else {
			gridPage = new GridPage(
					actualCompanyService
							.findActualCompanysAddPractitionersForList(
									location, page, rows, sidx, sord));
		}
		return SUCCESS;
	}

	@Action(value = "maintainPractitioners", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String maintainPractitioners() {
		if (location != null && location.getId() != null) {
			actualCompanyService.saveActualCompanysPractitioners(
					location.getId(), locationIds);
		}
		return SUCCESS;
	}

	@Action(value = "addPersonForPractitionerst", results = { @Result(name = "success", location = "/baseinfo/actualCompany/addPersonForPractitionerst.jsp") })
	public String addPersonForPractitionerst() {
		if (location != null && location.getId() != null) {
			locationIds = actualCompanyService
					.getActualCompanysAddPractitionersById(location.getId());
		}
		return SUCCESS;
	}

	@Action(value = "delPersonForPractitionerst", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String delPersonForPractitionerst() {
		if (location != null && location.getId() != null) {
			actualCompanyService.delPersonForPractitionerst(location.getId(),
					locationIds);
		}
		return SUCCESS;
	}

	public ActualCompany getLocation() {
		return location;
	}

	public void setLocation(ActualCompany location) {
		this.location = location;
	}

	private PageInfo<Druggy> emptyPage(int pageSize) {
		PageInfo<Druggy> pageInfo = new PageInfo<Druggy>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Druggy>());
		return pageInfo;
	}

	private List<Long> analyzeLocationIds() {
		String[] deleteId = locationIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		return idList;
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

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

	public Boolean getHasDuplicateLocation() {
		return hasDuplicateLocation;
	}

	public void setHasDuplicateLocation(Boolean hasDuplicateLocation) {
		this.hasDuplicateLocation = hasDuplicateLocation;
	}
}
