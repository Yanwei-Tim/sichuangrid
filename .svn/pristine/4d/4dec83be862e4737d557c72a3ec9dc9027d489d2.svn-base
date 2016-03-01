package com.tianque.baseInfo.scenicManage.scenicTraffic.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.baseInfo.scenicManage.scenicTraffic.domain.ScenicTraffic;
import com.tianque.baseInfo.scenicManage.scenicTraffic.service.ScenicTrafficService;
import com.tianque.baseInfo.scenicManage.scenicTraffic.vo.SearchScenicTrafficVo;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @ClassName: ScenicTrafficController
 * @Description: 景区交通
 * @author wangxiaohu wsmalltiger@163.com
 * @date Oct 31, 2013 10:23:35 AM
 */
@Namespace("/baseinfo/scenicTrafficManage")
public class ScenicTrafficController extends BaseAction {
	private Long organizationId;
	private String locationIds;
	private ScenicTraffic scenicTraffic;
	private SearchScenicTrafficVo location;
	@Autowired
	private ScenicTrafficService scenicTrafficService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/***************************************************************************
	 * 页面点击添加，修改，查看等
	 */
	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/baseinfo/scenicManage/scenicTraffic/scenicTrafficDlg.jsp"),
			@Result(name = "search", location = "/baseinfo/scenicManage/scenicTraffic/scenicTrafficSearchDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/scenicManage/scenicTraffic/commonView.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (null != id) {
			scenicTraffic = scenicTrafficService.getScenicTrafficById(id);
			scenicTraffic.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(scenicTraffic
							.getOrganization().getId(), organizationDubboService));
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

	/***************************************************************************
	 * 页面点击修改，查看等(解密調用)
	 */
	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/scenicManage/scenicTraffic/scenicTrafficDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/scenicManage/scenicTraffic/commonView.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		if (null != id) {
			scenicTraffic = scenicTrafficService.getScenicTrafficById(id);
			scenicTraffic.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(scenicTraffic
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
	 * 解密調用
	 * 
	 * @return
	 */
	@Action(value = "viewScenicTraffic", results = { @Result(name = "viewBase", location = "/baseinfo/scenicManage/scenicTraffic/scenicTrafficView.jsp") })
	@EncryptAnnotation
	public String viewScenicSpot() throws Exception {
		if ("viewBase".equals(mode)) {
			scenicTraffic = scenicTrafficService.getScenicTrafficById(location
					.getId());
			scenicTraffic.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(scenicTraffic
							.getOrganization().getId(), organizationDubboService));
		}
		return "viewBase";
	}

	@Action(value = "viewScenicTrafficForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"scenicTraffic", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String viewScenicTrafficForMobile() throws Exception {
		if (null == scenicTraffic || null == scenicTraffic.getId()) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		scenicTraffic = scenicTrafficService.getScenicTrafficById(scenicTraffic
				.getId());
		scenicTraffic.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(scenicTraffic
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	/**
	 * 列表页面的显示
	 */
	@Action(value = "scenicTrafficList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String scenicTrafficList() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			location.setOrganization(organizationDubboService
					.getSimpleOrgById(organizationId));
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					scenicTrafficService.searchScenicTrafficForPage(page, rows,
							sidx, sord, location), organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	private PageInfo<ScenicTraffic> emptyPage(int pageSize) {
		PageInfo<ScenicTraffic> pageInfo = new PageInfo<ScenicTraffic>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<ScenicTraffic>());
		return pageInfo;
	}

	@Action(value = "deleteScenicTrafficByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteScenicTraffic", actionName = "deleteScenicTrafficByIds")
	public String deleteScenicTrafficByIds() throws Exception {
		scenicTrafficService.deleteScenicTrafficByIds(analyzeLocationIds());
		return SUCCESS;

	}

	private Long[] analyzeLocationIds() {
		if (locationIds == null) {
			return new Long[] {};
		}
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
			@PermissionFilter(ename = "addScenicTraffic", actionName = "saveScenicTraffic"),
			@PermissionFilter(ename = "updateScenicTraffic", actionName = "saveScenicTraffic") })
	@Action(value = "saveScenicTraffic", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainBaseInfo() throws Exception {
		scenicTraffic.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(scenicTraffic.getOrganization(),
						organizationDubboService));
		if (DialogMode.ADD_MODE.equals(mode)) {
			scenicTraffic = scenicTrafficService
					.addScenicTraffic(scenicTraffic);
		}
		if (DialogMode.EDIT_MODE.equals(mode)) {
			if (null == scenicTraffic || null == scenicTraffic.getId()) {
				errorMessage = "参数错误";
				return ERROR;
			}
			scenicTraffic = scenicTrafficService
					.updateScenicTraffic(scenicTraffic);
		}
		return SUCCESS;
	}

	@Action(value = "getScenicTrafficById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getScenicTrafficById() throws Exception {
		scenicTraffic = scenicTrafficService.getScenicTrafficById(id);
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
		scenicTraffic = scenicTrafficService.updateEmphasiseById(
				analyzeLocationIds(), location);
		return SUCCESS;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public ScenicTraffic getScenicTraffic() {
		return scenicTraffic;
	}

	public void setScenicTraffic(ScenicTraffic scenicTraffic) {
		this.scenicTraffic = scenicTraffic;
	}

	public SearchScenicTrafficVo getLocation() {
		return location;
	}

	public void setLocation(SearchScenicTrafficVo location) {
		this.location = location;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}
}
