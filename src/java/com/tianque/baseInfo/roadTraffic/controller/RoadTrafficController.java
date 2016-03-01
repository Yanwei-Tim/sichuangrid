package com.tianque.baseInfo.roadTraffic.controller;

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

import com.tianque.baseInfo.roadTraffic.domain.RoadTraffic;
import com.tianque.baseInfo.roadTraffic.service.RoadTrafficService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 公共设施控制类。
 */
@Namespace("/baseinfo/roadTrafficManage")
@Scope("request")
@Controller("roadTrafficController")
@Transactional
public class RoadTrafficController extends BaseAction {
	private Long organizationId;
	protected String locationIds;
	@Autowired
	private RoadTrafficService roadTrafficService;
	private RoadTraffic roadTraffic;
	private RoadTraffic location;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/**
	 * 列表
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "roadTrafficList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgId() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					roadTrafficService.findRoadTrafficForPageByOrgInternalCode(
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
	private PageInfo<RoadTraffic> emptyPage(int pageSize) {
		PageInfo<RoadTraffic> pageInfo = new PageInfo<RoadTraffic>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<RoadTraffic>());
		return pageInfo;
	}

	/**
	 * 页面请求分发
	 */

	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/digitalCity/roadTrafficManagement/mainRoadTrafficDlg.jsp"),
			@Result(name = "search", location = "/digitalCity/roadTrafficManagement/searchRoadTrafficDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/roadTrafficManagement/roadTrafficViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {

		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			roadTraffic = roadTrafficService.getRoadTrafficById(location
					.getId());
			roadTraffic.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(roadTraffic
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
	@PermissionFilter(ename = "addRoadTraffic")
	@Action(value = "addRoadTraffic", results = {
			@Result(name = "success", type = "json", params = { "root",
					"roadTraffic", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addRoadTraffic() {
		if (DialogMode.ADD_MODE.equals(mode)) {
			// RoadTraffic.setIsEmphasis(true);
			roadTraffic = roadTrafficService.addRoadTraffic(roadTraffic);
			// 设置所属网格
			roadTraffic
					.setOrganization(ControllerHelper
							.proccessRelativeOrgNameByOrg(
									roadTraffic.getOrganization(),
									organizationDubboService));
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			// roadTraffic.setIsEmphasis(true);

			roadTraffic = roadTrafficService.updateRoadTraffic(roadTraffic);
			// 设置所属网格
			roadTraffic
					.setOrganization(ControllerHelper
							.proccessRelativeOrgNameByOrg(
									roadTraffic.getOrganization(),
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
	@Actions({ @Action(value = "viewRoadTraffic", results = { @Result(name = "success", location = "/digitalCity/roadTrafficManagement/roadTrafficView.jsp") }) })
	public String viewRoadTraffic() throws Exception {
		if (location != null && location.getId() != null) {
			location = roadTrafficService.getRoadTrafficById(location.getId());
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
	@Action(value = "hasDuplicateRoadTrafficObjNum", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(type = "json", name = "error", params = { "root", "false" }) })
	public String hasDuplicateRoadTrafficObjNum() throws Exception {

		if (roadTraffic == null || roadTraffic.getOrganization() == null
				|| roadTraffic.getOrganization().getId() == null) {
			return ERROR;
		} else {
			return roadTrafficService.hasDuplicateRoadTrafficObjNum(roadTraffic
					.getOrganization().getId(), roadTraffic.getObjNum(),
					roadTraffic.getId()) ? ERROR : SUCCESS;
		}

	}

	/**
	 * 删除
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "deleteRoadTraffic")
	@Action(value = "deleteRoadTrafficByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		roadTrafficService.deleteRoadTrafficByIds(analyzePopulationIds());
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

	public RoadTraffic getRoadTraffic() {
		return roadTraffic;
	}

	public void setRoadTraffic(RoadTraffic roadTraffic) {
		this.roadTraffic = roadTraffic;
	}

	public RoadTraffic getLocation() {
		return location;
	}

	public void setLocation(RoadTraffic location) {
		this.location = location;
	}

}
