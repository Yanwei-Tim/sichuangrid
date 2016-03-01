package com.tianque.baseInfo.emergencyShelter.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.emergencyShelter.domain.EmergencyShelter;
import com.tianque.baseInfo.emergencyShelter.service.EmergencyShelterService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/emergencyShelterManage")
@Transactional
@Scope("request")
@Controller("emergencyShelterController")
public class EmergencyShelterController extends BaseAction {

	private EmergencyShelter location;
	@Autowired
	private EmergencyShelterService emergencyShelterService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private Long organizationId;
	private Boolean hasDuplicatePopulation;
	private String locationIds;

	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/baseinfo/emergencyShelter/emergencyShelterDlg.jsp"),
			@Result(name = "search", location = "/baseinfo/emergencyShelter/searchEmergencyShelterDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/emergencyShelter/emergencyShelterViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (location != null && location.getId() != null) {
			location = emergencyShelterService.getEmergencyShelterById(location
					.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		if ("addBusinessData".equals(mode) || "editBusinessData".equals(mode)) {
			return "businessData";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		}
		return SUCCESS;
	}

	@Action(value = "emergencyShelterList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			Boolean isEmphasis = null;
			if (location != null && location.getIsEmphasis() != null) {
				isEmphasis = location.getIsEmphasis();
			}
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					emergencyShelterService
							.findEmergencyShelterForPageByOrgInternalCode(
									organizationId, page, rows, sidx, sord,
									isEmphasis), organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	@Action(value = "deleteEmergencyShelterByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		emergencyShelterService.deleteEmergencyShelter(analyzeLocationIds());
		return SUCCESS;
	}

	@Action(value = "hasDuplicatePopulation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicatePopulation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicatePopulation = emergencyShelterService
				.hasDuplicateEmergencyShelter(organizationId,
						location.getName(), location.getId());
		return SUCCESS;
	}

	@PermissionFilter(ename = "addEmergencyShelter")
	@Action(value = "maintainBaseInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (location != null) {
				location.getOrganization().setId(organizationId);
				location = emergencyShelterService
						.addEmergencyShelter(location);
				return SUCCESS;
			}
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (location != null) {
				location.getOrganization().setId(organizationId);
				location = emergencyShelterService
						.updateEmergencyShelter(location);
				return SUCCESS;
			}
		}
		return ERROR;
	}

	/**
	 * 修改注销状态
	 */
	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		emergencyShelterService.updateEmphasisByIdList(analyzeLocationIds(),
				location);
		return SUCCESS;
	}

	/**
	 * 查看应急避难场所记录
	 */

	@Action(value = "viewEmergencyShelter", results = { @Result(name = "success", location = "/baseinfo/emergencyShelter/emergencyShelterView.jsp") })
	public String viewInfo() throws Exception {
		if (location != null && location.getId() != null) {
			location = emergencyShelterService.getEmergencyShelterById(location
					.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		if ("businessData".equals(mode)) {
			return "businessData";

		}
		return SUCCESS;
	}

	public EmergencyShelter getLocation() {
		return location;
	}

	public void setLocation(EmergencyShelter location) {
		this.location = location;
	}

	private PageInfo<EmergencyShelter> emptyPage(int pageSize) {
		PageInfo<EmergencyShelter> pageInfo = new PageInfo<EmergencyShelter>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<EmergencyShelter>());
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

	public Boolean getHasDuplicatePopulation() {
		return hasDuplicatePopulation;
	}

	public void setHasDuplicatePopulation(Boolean hasDuplicatePopulation) {
		this.hasDuplicatePopulation = hasDuplicatePopulation;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

}
