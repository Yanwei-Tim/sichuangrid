package com.tianque.baseInfo.newSocietyOrganizations.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.baseInfo.newSocietyOrganizations.service.NewSocietyOrganitionsService;
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
 * 新社会组织
 */
@Namespace("/baseinfo/newSocietyOrganizationsManage")
@Controller("newSocietyOrganizationsController")
@Scope("prototype")
@Transactional
@SuppressWarnings("serial")
public class NewSocietyOrganizationsController extends BaseAction {
	private Long organizationId;
	private Organization organization;
	private NewSocietyOrganizations newSocietyOrganizations;
	private NewSocietyOrganizations location;
	protected String locationIds;
	private String keyType = "newSocietyOrganizations";
	private String placeTypeName = "社会组织";

	@Autowired
	private NewSocietyOrganitionsService newSocietyOrganitionsService;
	@Autowired
	OrganizationDubboService organizationDubboService;

	/**
	 * 列表
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "newSocietyOrganizationsManagement")
	@Action(value = "newSocietyOrganizationsList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findNewSocietyOrganizationsListForListPage() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(getRows()));
		} else {
			if (null != location) {
				gridPage = new GridPage(
						ControllerHelper.processAllOrgRelativeName(
								newSocietyOrganitionsService
										.findNewSocietyOrganizationsForPageByOrgInternalCode(
												organizationId, page, rows,
												sidx, sord,
												location.getIsEmphasis()),
								organizationDubboService,
								new String[] { "organization" }, null));
			} else {
				gridPage = new GridPage(
						ControllerHelper.processAllOrgRelativeName(
								newSocietyOrganitionsService
										.findNewSocietyOrganizationsForPageByOrgInternalCode(
												organizationId, page, rows,
												sidx, sord, null),
								organizationDubboService,
								new String[] { "organization" }, null));
			}
		}
		return SUCCESS;
	}

	private PageInfo<NewSocietyOrganizations> emptyPage(int pageSize) {
		PageInfo<NewSocietyOrganizations> pageInfo = new PageInfo<NewSocietyOrganizations>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<NewSocietyOrganizations>());
		return pageInfo;
	}

	/**
	 * 页面请求
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/baseinfo/twoNewOrganization/newSocietyOrganizations/maintainNewSocietyOrganizationsDlg.jsp"),
			@Result(name = "search", location = "/baseinfo/twoNewOrganization/newSocietyOrganizations/searchNewSocietyOrganizationsDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/twoNewOrganization/newSocietyOrganizations/newSocietyOrganizationsViewDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (null != id) {
			newSocietyOrganizations = newSocietyOrganitionsService
					.getSimpleNewSocietyOrganizations(id);
			newSocietyOrganizations.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							newSocietyOrganizations.getOrganization().getId(),
							organizationDubboService));
		} else if (location != null && location.getId() != null) {
			location = newSocietyOrganitionsService
					.getSimpleNewSocietyOrganizations(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			return "print";
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			procOrganization();
			return "search";
		}
		return SUCCESS;
	}

	/**
	 * 页面请求
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/twoNewOrganization/newSocietyOrganizations/maintainNewSocietyOrganizationsDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/twoNewOrganization/newSocietyOrganizations/newSocietyOrganizationsViewDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		if (null != id) {
			newSocietyOrganizations = newSocietyOrganitionsService
					.getSimpleNewSocietyOrganizations(id);
			newSocietyOrganizations.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							newSocietyOrganizations.getOrganization().getId(),
							organizationDubboService));
		} else if (location != null && location.getId() != null) {
			location = newSocietyOrganitionsService
					.getSimpleNewSocietyOrganizations(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return "view";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	private void procOrganization() {
		Organization organization = new Organization();
		organization.setId(organizationId);
		organization.setOrgName(ControllerHelper.getOrganizationRelativeName(
				organization.getId(), organizationDubboService));
		newSocietyOrganizations = new NewSocietyOrganizations();
		newSocietyOrganizations.setOrganization(organization);
	}

	/**
	 * 新增
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "addNewSocietyOrganizations")
	@Action(value = "addNewSocietyOrganizations", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addNewSocietyOrganizations() throws Exception {
		newSocietyOrganizations.setOrganization(organization);
		newSocietyOrganizations = newSocietyOrganitionsService
				.addNewSocietyOrganizations(newSocietyOrganizations);
		newSocietyOrganizations.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(
						newSocietyOrganizations.getOrganization(),
						organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "hasDuplicateNewSocietyOrganizationsName", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDuplicateNewSocietyOrganizationsName() throws Exception {
		if (newSocietyOrganizations == null
				|| newSocietyOrganizations.getOrganization() == null
				|| newSocietyOrganizations.getOrganization().getId() == null) {
			this.errorMessage = "参数不对";
			return ERROR;
		} else {
			return newSocietyOrganitionsService
					.hasDuplicateNewSocietyOrganizationsName(
							newSocietyOrganizations.getOrganization().getId(),
							newSocietyOrganizations.getName(),
							newSocietyOrganizations.getId()) ? SUCCESS : ERROR;
		}
	}

	/**
	 * 修改
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "updateNewSocietyOrganizations")
	@Action(value = "updateNewSocietyOrganizations", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateNewSocietyOrganizations() throws Exception {
		newSocietyOrganizations.setOrganization(organization);
		newSocietyOrganizations = newSocietyOrganitionsService
				.updateNewSocietyOrganizations(newSocietyOrganizations);
		newSocietyOrganizations.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(
						newSocietyOrganizations.getOrganization(),
						organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "getNewSocietyOrganizationsById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"newSocietyOrganizations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getNewSocietyOrganizationsById() throws Exception {
		if (id == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		newSocietyOrganizations = newSocietyOrganitionsService
				.getSimpleNewSocietyOrganizations(id);
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return SUCCESS
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteNewSocietyOrganizations")
	@Action(value = "deleteNewSocietyOrganizationsByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteNewSocietyOrganizationsByIds() throws Exception {
		newSocietyOrganitionsService
				.deleteNewSocietyOrganizationsByIds(analyzeLocationIds());
		return SUCCESS;
	}

	private List<Long> analyzeLocationIds() {
		String[] selIds = locationIds.split(",");
		List<Long> deleteIds = new ArrayList<Long>();
		for (int i = 0; i < selIds.length; i++) {
			if (selIds[i].equals("")) {
				continue;
			}
			deleteIds.add(Long.valueOf(selIds[i]));
		}
		return deleteIds;
	}

	/**
	 * 注销、取消注销
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		newSocietyOrganitionsService.updateEmphasiseByIds(analyzeLocationIds(),
				location);
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return SUCCESS
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "viewNewSocietyOrganizations")
	@Action(value = "viewNewSocietyOrganizations", results = { @Result(name = "success", location = "/baseinfo/twoNewOrganization/newSocietyOrganizations/newSocietyOrganizationsView.jsp") })
	public String viewNewSocietyOrganizations() throws Exception {
		if (location != null && location.getId() != null) {
			location = newSocietyOrganitionsService
					.getSimpleNewSocietyOrganizations(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		return SUCCESS;
	}

	public Organization getOrganization() {
		return organization;
	}

	public NewSocietyOrganizations getNewSocietyOrganizations() {
		return newSocietyOrganizations;
	}

	public void setNewSocietyOrganizations(
			NewSocietyOrganizations newSocietyOrganizations) {
		this.newSocietyOrganizations = newSocietyOrganizations;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getPlaceTypeName() {
		return placeTypeName;
	}

	public void setPlaceTypeName(String placeTypeName) {
		this.placeTypeName = placeTypeName;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public NewSocietyOrganizations getLocation() {
		return location;
	}

	public void setLocation(NewSocietyOrganizations location) {
		this.location = location;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

	@Action(value = "shiftDispatch", results = {
			@Result(name = "success", location = "/baseinfo/twoNewOrganization/newSocietyOrganizations/shiftTree.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String shiftDispatch() throws Exception {
		if (organization == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		return SUCCESS;
	}

	@Action(value = "shiftPerson", results = {
			@Result(name = "success", type = "json", params = {}),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String shiftNewSocietyOrganization() throws Exception {

		String[] id = locationIds.split(",");
		Long[] ids = new Long[id.length];
		for (int i = 0; i < id.length; i++) {
			if (id[i].equals("")) {
				continue;
			}
			ids[i] = Long.parseLong(id[i]);
		}
		newSocietyOrganitionsService.shiftNewSocietyOrganization(ids,
				newSocietyOrganizations.getOrganization().getId());
		return SUCCESS;
	}

}
