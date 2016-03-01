package com.tianque.party.controller;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.service.PartyMemberInfoService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("partyInfomationController")
@Scope("prototype")
@Namespace("/partyBuilding/partyInfoManage")
public class PartyInfomationController extends BaseAction {

	private Long organizationId;

	private Long memberId;

	private PartyMemberInfo member;

	private String orgName;
	@Autowired
	private PartyMemberInfoService partyMemberInfoService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Action(value = "list", results = { @Result(name = "success", location = "/partyBuilding/partyMember/list.jsp") })
	public String list() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			orgName = organizationDubboService.getSimpleOrgById(organizationId)
					.getOrgName();
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					this.partyMemberInfoService
							.findPartyMemberInfoForPageByOrgId(organizationId,
									page, rows, "id", "asc", 0L),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/partyBuilding/partyMember/simplePartyMemberDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/partyMember/viewMemberDlg.jsp") })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			member = new PartyMemberInfo();
			member.setOrganization(ThreadVariable.getSession()
					.getOrganization());
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			member = partyMemberInfoService.getPartyMemberInfoById(memberId);
			member.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(member
							.getOrganization().getId(), organizationDubboService));
			organizationId = member.getOrganization().getId();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			member = partyMemberInfoService.getPartyMemberInfoById(memberId);
			member.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(member
							.getOrganization().getId(), organizationDubboService));
			organizationId = member.getOrganization().getId();
			return "view";
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "addCityPartyConstructionPopulation")
	@Action(value = "addMember", results = { @Result(type = "json", name = "success", params = {
			"root", "true" }) })
	public String addPartyMember() throws Exception {
		member = partyMemberInfoService.addPartyMemberInfo(member);
		return SUCCESS;

	}

	@Action(value = "deletePartyMember", results = {
			@Result(name = "success", type = "json", params = { "root",
					"memberId", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePartyMember() throws Exception {
		partyMemberInfoService.deletePartyMemberInfoById(memberId);
		return SUCCESS;
	}

	@PermissionFilter(ename = "editCityPartyConstructionPopulation")
	@Action(value = "editMember", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String editMember() throws Exception {
		member = partyMemberInfoService.updatePartyMemberInfo(member);
		return SUCCESS;
	}

	private PageInfo<PartyMemberInfo> emptyPage(int pageSize) {
		PageInfo<PartyMemberInfo> pageInfo = new PageInfo<PartyMemberInfo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PartyMemberInfo>());
		return pageInfo;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public PartyMemberInfo getMember() {
		return member;
	}

	public void setMember(PartyMemberInfo member) {
		this.member = member;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
