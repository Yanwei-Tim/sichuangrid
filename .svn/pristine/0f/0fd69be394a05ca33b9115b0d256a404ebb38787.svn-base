package com.tianque.partyBuilding.organizations.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.partyBuilding.organizations.domain.PartyOrgMember;
import com.tianque.partyBuilding.organizations.domain.vo.SearchPartyOrgMemberVo;
import com.tianque.partyBuilding.organizations.service.PartyOrgMemberService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 两新组织党组织表:服务前端控制类
 * 
 * @author
 * @date 2014-01-14 15:44:08
 */
@Namespace("/partyOrgMemberManage")
@Scope("request")
@Controller("partyOrgMemberController")
public class PartyOrgMemberController extends BaseAction {

	@Autowired
	private PartyOrgMemberService partyOrgMemberService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private PartyOrgMember partyOrgMember;
	private SearchPartyOrgMemberVo searchpartyOrgMemberVo;
	private String ids;
	private Long id;
	private Long orgId;

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	@Action(value = "getPartyOrgMemberById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"partyOrgMember", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "partyOrgMemberManagement")
	public String getPartyOrgMemberById() throws Exception {
		if (id == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		partyOrgMember = partyOrgMemberService.get(id);
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addPartyOrgMember", results = {
			@Result(name = "success", type = "json", params = { "root",
					"partyOrgMember", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addPartyOrgMember")
	public String addPartyOrgMember() throws Exception {
		if (partyOrgMember == null
				|| !checkOrganization(partyOrgMember.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		partyOrgMember = partyOrgMemberService.add(partyOrgMember);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updatePartyOrgMember", results = {
			@Result(name = "success", type = "json", params = { "root",
					"partyOrgMember", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updatePartyOrgMember")
	public String updatePartyOrgMember() throws Exception {
		if (partyOrgMember == null || partyOrgMember.getId() == null
				|| !checkOrganization(partyOrgMember.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		partyOrgMember = partyOrgMemberService.update(partyOrgMember);
		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	@Action(value = "deletePartyOrgMemberById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deletePartyOrgMember")
	public String deletePartyOrgMemberById() throws Exception {
		if (id == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		partyOrgMemberService.delete(id);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deletePartyOrgMemberByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deletePartyOrgMember")
	public String deletePartyOrgMemberByIds() throws Exception {
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
		partyOrgMemberService.delete(idsLong);
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
	@Action(value = "findPartyOrgMemberPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "partyOrgMemberManagement")
	public String findPartyOrgMemberPagerBySearchVo() throws Exception {
		if (searchpartyOrgMemberVo == null || orgId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		if (organization == null) {
			this.errorMessage = "网格不存在";
			return ERROR;
		}
		searchpartyOrgMemberVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				partyOrgMemberService.findPagerBySearchVo(
						searchpartyOrgMemberVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" }, orgId));
		return SUCCESS;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public PartyOrgMember getPartyOrgMember() {
		return partyOrgMember;
	}

	public void setPartyOrgMember(PartyOrgMember partyOrgMember) {
		this.partyOrgMember = partyOrgMember;
	}

	public SearchPartyOrgMemberVo getSearchPartyOrgMemberVo() {
		return searchpartyOrgMemberVo;
	}

	public void setSearchPartyOrgMemberVo(
			SearchPartyOrgMemberVo searchpartyOrgMemberVo) {
		this.searchpartyOrgMemberVo = searchpartyOrgMemberVo;
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
