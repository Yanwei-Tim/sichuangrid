package com.tianque.partyBuilding.organizations.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.partyBuilding.organizations.domain.Partyresource;
import com.tianque.partyBuilding.organizations.domain.vo.SearchPartyresourceVo;
import com.tianque.partyBuilding.organizations.service.PartyresourceService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 区域内主要党组织资源:服务前端控制类
 * 
 * @author
 * @date 2014-01-15 09:22:20
 */
@Namespace("/partyresourceManage")
@Scope("request")
@Controller("partyresourceController")
public class PartyresourceController extends BaseAction {

	@Autowired
	private PartyresourceService partyresourceService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Partyresource partyresource;
	private SearchPartyresourceVo searchPartyresourceVo;
	private String ids;
	private Long id;
	private Long organizationId;

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	@Action(value = "getPartyresourceById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"partyresource", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "partyresourceManagement")
	public String getPartyresourceById() throws Exception {
		if (id == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		partyresource = partyresourceService.get(id);
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addPartyresource", results = {
			@Result(name = "success", type = "json", params = { "root",
					"partyresource", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addPartyresource")
	public String addPartyresource() throws Exception {
		if (partyresource == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		partyresource.setOrganization(organizationDubboService
				.getSimpleOrgById(partyresource.getOrganization().getId()));
		partyresource = partyresourceService.add(partyresource);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updatePartyresource", results = {
			@Result(name = "success", type = "json", params = { "root",
					"partyresource", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updatePartyresource")
	public String updatePartyresource() throws Exception {
		if (partyresource == null || partyresource.getId() == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		partyresource = partyresourceService.update(partyresource);
		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	@Action(value = "deletePartyresourceById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deletePartyresource")
	public String deletePartyresourceById() throws Exception {
		if (id == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		partyresourceService.delete(id);
		return SUCCESS;
	}

	/**
	 * id加密根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deletePartyresourceByIdsByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deletePartyresource")
	@EncryptAnnotation
	public String deletePartyresourceByIdsByEncrypt() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		partyresourceService.delete(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deletePartyresourceByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deletePartyresource")
	public String deletePartyresourceByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		partyresourceService.delete(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 * @param pager
	 *            Pager对象
	 * 
	 * @return Pager对象
	 */
	@Action(value = "findPartyresourcePagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "partyresourceManagement")
	public String findPartyresourcePagerBySearchVo() throws Exception {
		if (searchPartyresourceVo == null
				|| searchPartyresourceVo.getOrganization().getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				partyresourceService.findPagerBySearchVo(searchPartyresourceVo,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, organizationId));
		return SUCCESS;
	}

	/**
	 * id加密控制跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatchByEncrypt", results = {
			@Result(name = "edit", location = "/partyBuilding/organizations/maintainPartyresourceDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/organizations/partyresourceView.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	@EncryptAnnotation
	public String dispatchByEncrypt() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return DialogMode.ADD_MODE;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			partyresource = partyresourceService.get(partyresource.getId());
			return DialogMode.EDIT_MODE;
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			partyresource = partyresourceService.get(partyresource.getId());
			partyresource.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(partyresource
							.getOrganization().getId(), organizationDubboService));
			return DialogMode.VIEW_MODE;
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return DialogMode.SEARCH_MODE;
		}
		return ERROR;
	}

	@Actions({ @Action(value = "dispatch", results = {
			@Result(name = "edit", location = "/partyBuilding/organizations/maintainPartyresourceDlg.jsp"),
			@Result(name = "search", location = "/partyBuilding/organizations/searchPartyresourceDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/organizations/partyresourceView.jsp"),
			@Result(name = "add", location = "/partyBuilding/organizations/maintainPartyresourceDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return DialogMode.ADD_MODE;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			partyresource = partyresourceService.get(partyresource.getId());
			return DialogMode.EDIT_MODE;
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			partyresource = partyresourceService.get(partyresource.getId());
			partyresource.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrgId(partyresource
							.getOrganization().getId(), organizationDubboService));
			return DialogMode.VIEW_MODE;
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return DialogMode.SEARCH_MODE;
		}
		return ERROR;
	}

	public Partyresource getPartyresource() {
		return partyresource;
	}

	public void setPartyresource(Partyresource partyresource) {
		this.partyresource = partyresource;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSearchPartyresourceVo(
			SearchPartyresourceVo searchPartyresourceVo) {
		this.searchPartyresourceVo = searchPartyresourceVo;
	}

	public SearchPartyresourceVo getSearchPartyresourceVo() {
		return searchPartyresourceVo;
	}

}
