package com.tianque.baseInfo.otherFacilities.controller;

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

import com.tianque.baseInfo.otherFacilities.domain.OtherFacilities;
import com.tianque.baseInfo.otherFacilities.service.OtherFacilitiesService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 其他设施控制类。
 */
@Namespace("/baseinfo/otherFacilitiesManage")
@Scope("request")
@Controller("otherFacilitiesController")
@Transactional
public class OtherFacilitiesController extends BaseAction {
	private Long organizationId;
	protected String locationIds;
	@Autowired
	private OtherFacilitiesService otherFacilitiesService;
	private OtherFacilities otherFacilities;
	private OtherFacilities location;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/**
	 * 列表
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "otherFacilitiesList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgId() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					otherFacilitiesService
							.findOtherFacilitiesForPageByOrgInternalCode(
									organizationId, page, rows, sidx, sord),
					organizationDubboService, new String[] { "organization" },
					organizationId));

		}
		return SUCCESS;
	}

	/**
	 * 分页
	 * 
	 * @param pageSize
	 * @return
	 */
	private PageInfo<OtherFacilities> emptyPage(int pageSize) {
		PageInfo<OtherFacilities> pageInfo = new PageInfo<OtherFacilities>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OtherFacilities>());
		return pageInfo;
	}

	/**
	 * 页面请求分发
	 */

	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/digitalCity/otherFacilitiesManagement/mainOtherFacilitiesDlg.jsp"),
			@Result(name = "search", location = "/digitalCity/otherFacilitiesManagement/searchOtherFacilitiesDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/otherFacilitiesManagement/otherFacilitiesViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {

		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			otherFacilities = otherFacilitiesService
					.getOtherFacilitiesById(location.getId());
			otherFacilities.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							otherFacilities.getOrganization().getId(),
							organizationDubboService));
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return "view";
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			return "search";
		}
		return SUCCESS;
	}

	/**
	 * 新增 、修改
	 */
	@PermissionFilter(ename = "addOtherFacilities")
	@Action(value = "addOtherFacilities", results = {
			@Result(name = "success", type = "json", params = { "root",
					"otherFacilities", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addOtherFacilities() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			// OtherFacilities.setIsEmphasis(true);
			otherFacilities = otherFacilitiesService
					.addOtherFacilities(otherFacilities);
			// 设置所属网格
			otherFacilities.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrg(
							otherFacilities.getOrganization(),
							organizationDubboService));
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			// otherFacilities.setIsEmphasis(true);

			otherFacilities = otherFacilitiesService
					.updateOtherFacilities(otherFacilities);
			// 设置所属网格
			otherFacilities.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrg(
							otherFacilities.getOrganization(),
							organizationDubboService));
			return SUCCESS;

		}
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return SUCCESS
	 */
	@Actions({ @Action(value = "viewOtherFacilities", results = { @Result(name = "success", location = "/digitalCity/otherFacilitiesManagement/otherFacilitiesView.jsp") }) })
	public String viewOtherFacilities() throws Exception {
		if (location != null && location.getId() != null) {
			location = otherFacilitiesService.getOtherFacilitiesById(location
					.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		return SUCCESS;
	}

	/***
	 * 验证是否有重复记录
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateOtherFacilitiesObjNum", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(type = "json", name = "error", params = { "root", "false" }) })
	public String hasDuplicateOtherFacilitiesObjNum() throws Exception {

		if (otherFacilities == null
				|| otherFacilities.getOrganization() == null
				|| otherFacilities.getOrganization().getId() == null) {
			return ERROR;
		} else {
			return otherFacilitiesService.hasDuplicateOtherFacilitiesObjNum(
					otherFacilities.getOrganization().getId(),
					otherFacilities.getObjNum(), otherFacilities.getId()) ? ERROR
					: SUCCESS;
		}

	}

	/**
	 * 删除
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "deleteOtherFacilities")
	@Action(value = "deleteOtherFacilitiesByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		otherFacilitiesService
				.deleteOtherFacilitiesByIds(analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * 处理删除id数组
	 * 
	 * @return
	 */
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

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

	public OtherFacilities getOtherFacilities() {
		return otherFacilities;
	}

	public void setOtherFacilities(OtherFacilities otherFacilities) {
		this.otherFacilities = otherFacilities;
	}

	public OtherFacilities getLocation() {
		return location;
	}

	public void setLocation(OtherFacilities location) {
		this.location = location;
	}

}
