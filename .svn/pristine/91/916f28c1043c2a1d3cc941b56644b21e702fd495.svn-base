package com.tianque.baseInfo.landscaping.controller;

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

import com.tianque.baseInfo.landscaping.domain.Landscaping;
import com.tianque.baseInfo.landscaping.service.LandscapingService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 园林绿化控制类。
 */
@Namespace("/baseinfo/landscapingManage")
@Scope("request")
@Controller("landscapingController")
@Transactional
public class LandscapingController extends BaseAction {
	private Long organizationId;
	protected String locationIds;
	@Autowired
	private LandscapingService landscapingService;
	private Landscaping landscaping;
	private Landscaping location;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/**
	 * 列表
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "landscapingList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgId() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					landscapingService.findLandscapingForPageByOrgInternalCode(
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
	private PageInfo<Landscaping> emptyPage(int pageSize) {
		PageInfo<Landscaping> pageInfo = new PageInfo<Landscaping>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Landscaping>());
		return pageInfo;
	}

	/**
	 * 页面请求分发
	 */

	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/digitalCity/landscapingManagement/mainLandscapingDlg.jsp"),
			@Result(name = "search", location = "/digitalCity/landscapingManagement/searchLandscapingDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/landscapingManagement/landscapingViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {

		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			landscaping = landscapingService.getLandscapingById(location
					.getId());
			landscaping.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(landscaping
							.getOrganization().getId(), organizationDubboService));
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
	@PermissionFilter(ename = "addLandscaping")
	@Action(value = "addLandscaping", results = {
			@Result(name = "success", type = "json", params = { "root",
					"landscaping", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addLandscaping() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			// Landscaping.setIsEmphasis(true);
			landscaping = landscapingService.addLandscaping(landscaping);
			// 设置所属网格
			landscaping
					.setOrganization(ControllerHelper
							.proccessRelativeOrgNameByOrg(
									landscaping.getOrganization(),
									organizationDubboService));
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			// landscaping.setIsEmphasis(true);

			landscaping = landscapingService.updateLandscaping(landscaping);
			// 设置所属网格
			landscaping
					.setOrganization(ControllerHelper
							.proccessRelativeOrgNameByOrg(
									landscaping.getOrganization(),
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
	@Actions({ @Action(value = "viewLandscaping", results = { @Result(name = "success", location = "/digitalCity/landscapingManagement/landscapingView.jsp") }) })
	public String viewLandscaping() throws Exception {
		if (location != null && location.getId() != null) {
			location = landscapingService.getLandscapingById(location.getId());
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
	@Action(value = "hasDuplicateLandscapingObjNum", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(type = "json", name = "error", params = { "root", "false" }) })
	public String hasDuplicateLandscapingObjNum() throws Exception {

		if (landscaping == null || landscaping.getOrganization() == null
				|| landscaping.getOrganization().getId() == null) {
			return ERROR;
		} else {
			return landscapingService.hasDuplicateLandscapingObjNum(landscaping
					.getOrganization().getId(), landscaping.getObjNum(),
					landscaping.getId()) ? ERROR : SUCCESS;
		}

	}

	/**
	 * 删除
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "deleteLandscaping")
	@Action(value = "deleteLandscapingByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		landscapingService.deleteLandscapingByIds(analyzePopulationIds());
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

	public Landscaping getLandscaping() {
		return landscaping;
	}

	public void setLandscaping(Landscaping landscaping) {
		this.landscaping = landscaping;
	}

	public Landscaping getLocation() {
		return location;
	}

	public void setLocation(Landscaping location) {
		this.location = location;
	}

}
