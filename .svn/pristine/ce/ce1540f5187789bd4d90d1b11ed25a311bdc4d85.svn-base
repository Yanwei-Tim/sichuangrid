package com.tianque.baseInfo.laborEmploymentUnit.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.laborEmploymentUnit.domain.LaborEmploymentUnit;
import com.tianque.baseInfo.laborEmploymentUnit.service.LaborEmploymentUnitService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/laborEmploymentUnitManage")
@Transactional
@Scope("request")
@Controller("laborEmploymentUnitController")
public class LaborEmploymentUnitController extends BaseAction {

	private Long organizationId;
	private Boolean hasDuplicateLocation;
	private String locationIds;

	private LaborEmploymentUnit location;
	@Autowired
	private LaborEmploymentUnitService laborEmploymentUnitService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/**
	 * 实现页面跳转
	 */
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/baseinfo/laborEmploymentUnit/laborEmploymentUnitDlg.jsp"),
			@Result(name = "businessData", location = "/baseinfo/laborEmploymentUnit/laborEmploymentBusinessDataDlg.jsp"),
			@Result(name = "search", location = "/baseinfo/laborEmploymentUnit/searchLaborEmploymentUnitDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/laborEmploymentUnit/laborEmploymentUnitViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (location != null && location.getId() != null) {
			location = laborEmploymentUnitService
					.getLaborEmploymentUnitById(location.getId());
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

	/**
	 * 查找记录用于jqGrid显示
	 */
	@Action(value = "laborEmploymentUnitList", results = { @Result(type = "json", name = "success", params = {
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
					laborEmploymentUnitService
							.findLaborEmploymentUnitForPageByOrgInternalCode(
									organizationId, page, rows, sidx, sord,
									isEmphasis), organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	/**
	 * 批量删除劳动用工单位记录
	 */
	@Action(value = "deleteLaborEmploymentUnitByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		laborEmploymentUnitService
				.deleteLaborEmploymentUnit(analyzeLocationIds());
		return SUCCESS;
	}

	/**
	 * 验证劳动用工单位唯一性
	 */
	@Action(value = "hasDuplicateLocation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasDuplicateLocation" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String hasDuplicateLocation() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicateLocation = laborEmploymentUnitService
				.hasDuplicateLaborEmploymentUnit(organizationId,
						location.getCompanyName(), location.getId());
		return SUCCESS;
	}

	/**
	 * 新增/修改劳动用工单位
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "addLaborEmploymentUnit")
	@Action(value = "maintainBaseInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (location != null) {
				location.getOrganization().setId(organizationId);
				location = laborEmploymentUnitService
						.addLaborEmploymentUnit(location);
				return SUCCESS;
			}
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (location != null) {
				location.getOrganization().setId(organizationId);
				location = laborEmploymentUnitService
						.updateLaborEmploymentUnit(location);
				return SUCCESS;
			}
		}
		return ERROR;
	}

	@PermissionFilter(ename = "addLaborEmploymentUnit")
	@Action(value = "maintainBusinessInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBusinessInfo() throws Exception {
		if ("addBusinessData".equals(mode) || "editBusinessData".equals(mode)) {
			if (location != null) {
				location = laborEmploymentUnitService
						.updateBusinessData(location);
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
	public String updateEmphasiseById() throws Exception {
		laborEmploymentUnitService.updateEmphasisByIdList(analyzeLocationIds(),
				location);
		return SUCCESS;
	}

	/**
	 * 查看劳动用工单位记录
	 */

	@Action(value = "viewLaborEmploymentUnit", results = {
			@Result(name = "success", location = "/baseinfo/laborEmploymentUnit/laborEmploymentUnitView.jsp"),
			@Result(name = "businessData", location = "/baseinfo/laborEmploymentUnit/businessDataView.jsp") })
	public String viewInfo() throws Exception {
		if (location != null && location.getId() != null) {
			location = laborEmploymentUnitService
					.getLaborEmploymentUnitById(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		if ("businessData".equals(mode)) {
			return "businessData";

		}
		return SUCCESS;
	}

	public LaborEmploymentUnit getLocation() {
		return location;
	}

	public void setLocation(LaborEmploymentUnit location) {
		this.location = location;
	}

	private PageInfo<LaborEmploymentUnit> emptyPage(int pageSize) {
		PageInfo<LaborEmploymentUnit> pageInfo = new PageInfo<LaborEmploymentUnit>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<LaborEmploymentUnit>());
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
