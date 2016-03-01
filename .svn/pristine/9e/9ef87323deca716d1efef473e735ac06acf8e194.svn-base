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

import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Dustbin;
import com.tianque.service.DustbinService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 垃圾箱控制类。
 */
@Namespace("/baseinfo/dustbinManage")
@Scope("request")
@Controller("dustbinController")
@Transactional
public class DustbinController extends BaseAction {
	private Long organizationId;
	protected String locationIds;
	@Autowired
	private DustbinService dustbinService;
	private Dustbin dustbin;
	private Dustbin location;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private String partType;
	private Long id;
	private String content;

	/**
	 * 列表
	 * 
	 * @return SUCCESS
	 */
	@Action(value = "dustbinList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgId() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					dustbinService.findDustbinForPageByOrgInternalCode(
							organizationId, page, rows, sidx, sord,
							location.getIsEmphasis(), partType),
					organizationDubboService, new String[] { "organization" }, null));

		}
		return SUCCESS;
	}

	private PageInfo<Dustbin> emptyPage(int pageSize) {
		PageInfo<Dustbin> pageInfo = new PageInfo<Dustbin>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Dustbin>());
		return pageInfo;
	}

	/*
	 * 页面请求
	 */

	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/digitalCity/dustbinManagement/mainDustbinDlg.jsp"),
			@Result(name = "search", location = "/digitalCity/dustbinManagement/searchDustbinDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/dustbinManagement/dustbinViewDlg.jsp"),
			@Result(name = "video", location = "/openLayersMap/gisHeader/maintainVideotapeDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {

		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
			dustbin = dustbinService.getDustbinById(location.getId());
			dustbin.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(dustbin
							.getOrganization().getId(), organizationDubboService));
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())) {
			return "view";
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(getMode())) {
			return "search";
		} else if ("video".equalsIgnoreCase(getMode())) {
			content = dustbinService.getVideoParamterById(id);
			return "video";
		}
		return SUCCESS;
	}

	/*
	 * 页面请求(解密调用)
	 */

	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/digitalCity/dustbinManagement/mainDustbinDlg.jsp"),
			@Result(name = "view", location = "/digitalCity/dustbinManagement/dustbinViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {

		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
			dustbin = dustbinService.getDustbinById(location.getId());
			dustbin.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(dustbin
							.getOrganization().getId(), organizationDubboService));

		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())) {
			location = dustbinService.getDustbinById(location.getId());
			return "view";
		}
		return SUCCESS;
	}

	/*
	 * 新增 、修改
	 */
	@PermissionFilter(ename = "addDustbin")
	@Action(value = "addDustbin", results = {
			@Result(name = "success", type = "json", params = { "root",
					"dustbin", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addDustbin() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			dustbin = dustbinService.addDustbin(dustbin);
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			dustbin.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(dustbin
							.getOrganization().getId(), organizationDubboService));
			dustbin = dustbinService.updateDustbin(dustbin);
			return SUCCESS;

		}
		return SUCCESS;
	}

	/**
	 * 删除环卫环保
	 * 
	 * @return SUCCESS
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteDustbin")
	@Action(value = "deleteDustbinByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteByIds() throws Exception {
		dustbinService.deleteDustbinByIds(analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * 查看
	 * 
	 * @return SUCCESS
	 */
	@Actions({ @Action(value = "viewDustbin", results = { @Result(name = "success", location = "/digitalCity/dustbinManagement/dustbinView.jsp") }) })
	@EncryptAnnotation
	public String viewDustbin() throws Exception {
		if (location != null && location.getId() != null) {
			location = dustbinService.getDustbinById(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		return SUCCESS;
	}

	// 注销、取消注销

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		dustbinService.updateEmphasiseByIds(analyzePopulationIds(),
				location.getIsEmphasis() ? 1L : 0L, location.getLogOutReason(),
				location.getLogOutTime());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "updateEmphasiseByEncryptId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseByEncryptId() throws Exception {
		dustbinService.updateEmphasiseByIds(analyzePopulationIds(),
				location.getIsEmphasis() ? 1L : 0L, location.getLogOutReason(),
				location.getLogOutTime());
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

	public Dustbin getDustbin() {
		return dustbin;
	}

	public void setDustbin(Dustbin dustbin) {
		this.dustbin = dustbin;
	}

	public Dustbin getLocation() {
		return location;
	}

	public void setLocation(Dustbin location) {
		this.location = location;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
