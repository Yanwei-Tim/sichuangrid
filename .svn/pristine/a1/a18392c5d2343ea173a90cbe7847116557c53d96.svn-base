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
import com.tianque.party.domain.PartyAbilityPerson;
import com.tianque.party.service.PartyAbilityPersonService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("partyAbilityPersonController")
@Scope("prototype")
@Namespace("/partyBuilding/abilityManage")
public class PartyAbilityPersonController extends BaseAction {

	private Long organizationId;
	private String orgName;
	private Long personId;

	private PartyAbilityPerson person;

	@Autowired
	private PartyAbilityPersonService partyAbilityPersonService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Action(value = "list", results = { @Result(name = "success", location = "/partyBuilding/abilityPerson/abilityPersonList.jsp") })
	public String list() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			orgName = organizationDubboService.getSimpleOrgById(organizationId)
					.getOrgName();
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					this.partyAbilityPersonService
							.findPartyAbilityPersonsByOrgId(organizationId,
									page, rows, "createDate", "desc"),
					organizationDubboService, new String[] { "organization" },
					organizationId));

		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/partyBuilding/abilityPerson/maintanceAbilityPersonDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/abilityPerson/abilityPersonDetail.jsp") })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			person = new PartyAbilityPerson();
			person.setOwnerOrg(ThreadVariable.getSession().getOrganization());
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			initTeam();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			initTeam();
			orgName = organizationDubboService.getSimpleOrgById(organizationId)
					.getOrgName();
			return "view";
		}
		return SUCCESS;
	}

	private void initTeam() {
		person = partyAbilityPersonService.getPartyAbilityPersonById(personId);
		person.getOwnerOrg().setOrgName(
				ControllerHelper.getOrganizationRelativeName(person
						.getOwnerOrg().getId(), organizationDubboService));
		organizationId = person.getOwnerOrg().getId();
	}

	@PermissionFilter(ename = "addAbilityPerson")
	@Action(value = "addPartyAbilityPerson", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addPartyAbilityPerson() throws Exception {
		person = partyAbilityPersonService.addPartyAbilityPerson(person);
		return SUCCESS;
	}

	@PermissionFilter(ename = "editAbilityPerson")
	@Action(value = "editPartyAbilityPerson", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String editPartyAbilityPerson() throws Exception {
		person = partyAbilityPersonService.updatePartyAbilityPerson(person);
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteAbilityPerson")
	@Action(value = "deletePartyAbilityPerson", results = {
			@Result(name = "success", type = "json", params = { "root",
					"teamId", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePartyAbilityPerson() throws Exception {
		partyAbilityPersonService.deletePartyAbilityPersonById(personId);
		return SUCCESS;
	}

	private PageInfo<PartyAbilityPerson> emptyPage(int pageSize) {
		PageInfo<PartyAbilityPerson> pageInfo = new PageInfo<PartyAbilityPerson>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PartyAbilityPerson>());
		return pageInfo;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public PartyAbilityPerson getPerson() {
		return person;
	}

	public void setPerson(PartyAbilityPerson person) {
		this.person = person;
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
