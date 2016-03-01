package com.tianque.partyBuilding.organsParty.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.partyBuilding.members.constant.MemberType;
import com.tianque.partyBuilding.members.service.MemberAssociatePartyOrgService;
import com.tianque.partyBuilding.organsParty.domain.OrgansParty;
import com.tianque.partyBuilding.organsParty.domain.vo.SearchOrgansPartyVo;
import com.tianque.partyBuilding.organsParty.service.OrgansPartyService;

/**
 * 机关党组织表:服务前端控制类
 * 
 * @author
 * @date 2014-02-10 16:00:06
 */
@Namespace("/organsPartyManage")
@Scope("request")
@Controller("organsPartyController")
public class OrgansPartyController extends BaseAction {

	@Autowired
	private OrgansPartyService organsPartyService;
	@Autowired
	private MemberAssociatePartyOrgService memberAssociatePartyOrgService;

	private OrgansParty organsParty;
	private SearchOrgansPartyVo searchOrgansPartyVo;
	private String ids;
	private String id;
	private String oldPartyOrg;

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	@Action(value = "getOrgansPartyById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"organsParty", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "organsPartyManagement")
	public String getOrgansPartyById() throws Exception {
		if (id == null || "".equals(id.trim())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		organsParty = organsPartyService.get(Long.parseLong(id));
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addOrgansParty", results = {
			@Result(name = "success", type = "json", params = { "root",
					"organsParty", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addOrgansParty")
	public String addOrgansParty() throws Exception {
		organsParty = organsPartyService.add(organsParty);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateOrgansParty", results = {
			@Result(name = "success", type = "json", params = { "root",
					"organsParty", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateOrgansParty")
	public String updateOrgansParty() throws Exception {
		organsParty = organsPartyService.update(organsParty);
		memberAssociatePartyOrgService.updatePartyorgByPartyOrgTypeAndPartyorg(
				MemberType.TWO_NEW_ORGNIZATION_PARTY_ORG, oldPartyOrg,
				organsParty.getName());
		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	@Action(value = "deleteOrgansPartyById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteOrgansParty")
	public String deleteOrgansPartyById() throws Exception {
		if (id == null || "".equals(id.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		organsPartyService.delete(Long.parseLong(id));
		return SUCCESS;
	}

	/**
	 * id加密根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteOrgansPartyByIdsEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteOrgansParty")
	public String deleteOrgansPartyByIdsEncrypt() throws Exception {
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
		organsPartyService.delete(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteOrgansPartyByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteOrgansParty")
	public String deleteOrgansPartyByIds() throws Exception {
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
		organsPartyService.delete(idsLong);
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
	@Action(value = "findOrgansPartyPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "organPartyOrganizationBaseManagement")
	public String findOrgansPartyPagerBySearchVo() throws Exception {
		if (searchOrgansPartyVo == null
				|| searchOrgansPartyVo.getOrgid() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage(organsPartyService.findPagerBySearchVo(
				searchOrgansPartyVo, page, rows, sidx, sord));
		return SUCCESS;
	}

	/**
	 * id加密控制跳转
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchByEncrypt", results = {
			@Result(name = "edit", location = "/partyBuilding/organsParty/organsPartyManage/organsPartyDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/organsParty/organsPartyManage/organsPartyView.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchByEncrypt() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return DialogMode.ADD_MODE;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			organsParty = organsPartyService.get(organsParty.getId());
			if (organsParty.getSuperior() != null)
				organsParty.setSuperior(organsPartyService.get(organsParty
						.getSuperior().getId()));
			return DialogMode.EDIT_MODE;
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			organsParty = organsPartyService.get(organsParty.getId());
			if (organsParty.getSuperior() != null)
				organsParty.setSuperior(organsPartyService.get(organsParty
						.getSuperior().getId()));
			return DialogMode.VIEW_MODE;
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return DialogMode.SEARCH_MODE;
		}
		return ERROR;
	}

	@Actions({ @Action(value = "dispatch", results = {
			@Result(name = "edit", location = "/partyBuilding/organsParty/organsPartyManage/organsPartyDlg.jsp"),
			@Result(name = "search", location = "/partyBuilding/organsParty/organsPartyManage/searchOrgansPartyDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/organsParty/organsPartyManage/organsPartyView.jsp"),
			@Result(name = "add", location = "/partyBuilding/organsParty/organsPartyManage/organsPartyDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return DialogMode.ADD_MODE;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			organsParty = organsPartyService.get(organsParty.getId());
			if (organsParty.getSuperior() != null)
				organsParty.setSuperior(organsPartyService.get(organsParty
						.getSuperior().getId()));
			return DialogMode.EDIT_MODE;
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			organsParty = organsPartyService.get(organsParty.getId());
			if (organsParty.getSuperior() != null)
				organsParty.setSuperior(organsPartyService.get(organsParty
						.getSuperior().getId()));
			return DialogMode.VIEW_MODE;
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return DialogMode.SEARCH_MODE;
		}
		return ERROR;
	}

	@Action(value = "checkOrgansPartyHas", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	// 查看数据库中是否存在该单位
	public String checkOrgansPartyHas() throws Exception {
		if (organsParty == null) {
			return ERROR;
		}
		boolean isRight = organsPartyService.checkOrgansParty(organsParty);
		if (isRight) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public OrgansParty getOrgansParty() {
		return organsParty;
	}

	public void setOrgansParty(OrgansParty organsParty) {
		this.organsParty = organsParty;
	}

	public SearchOrgansPartyVo getSearchOrgansPartyVo() {
		return searchOrgansPartyVo;
	}

	public void setSearchOrgansPartyVo(SearchOrgansPartyVo searchOrgansPartyVo) {
		this.searchOrgansPartyVo = searchOrgansPartyVo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOldPartyOrg() {
		return oldPartyOrg;
	}

	public void setOldPartyOrg(String oldPartyOrg) {
		this.oldPartyOrg = oldPartyOrg;
	}

	/*
	 * public String getId() { return id; }
	 * 
	 * public void setId(String id) { this.id = id; }
	 */

}
