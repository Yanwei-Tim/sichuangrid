package com.tianque.party.controller;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyOrgActivity;
import com.tianque.domain.vo.SearchPartyOrgActivityVo;
import com.tianque.service.PartyOrgActivityService;
import com.tianque.service.PartyOrgInfoService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@SuppressWarnings("serial")
@Controller("partyBuildingActivityController")
@Scope("prototype")
@Namespace("/partyBuilding/activityManage")
public class PartyOrgActivityController extends BaseAction {

	private Long organizationId;
	private SearchPartyOrgActivityVo searchPartyOrgActivityVo;
	private PartyOrgActivity population;

	@Autowired
	private PartyOrgActivityService partyOrgActivityService;
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	private PartyOrgInfoService partyOrgInfoService;

	@Action(value = "list", results = { @Result(name = "success", location = "/partyBuilding/activity/list.jsp") })
	public String findPartyOrgActivityForPageByOrgId() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					this.partyOrgActivityService
							.findPartyOrgActivityForPageByOrgId(organizationId,
									page, rows, sidx, sord, "2012"),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/partyBuilding/activity/maintain.jsp"),
			@Result(name = "search", location = "/baseinfo/partyConstruction/currentParty/searchPartyOrgActivityDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/partyConstruction/currentParty/maintainPartyMemberInfoPrintDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/partyConstruction/currentParty/partyOrgActivityView.jsp") })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			this.procOrganization();
			population.setPartyOrgInfo(this.partyOrgInfoService
					.getPartyOrgInfoByOrgId(population.getOrganization()
							.getId()));
		} else if (DialogMode.EDIT_MODE.endsWith(mode)) {
			this.getPartyOrgActivityInfo();
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			this.procOrganization();
			return "search";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			this.getPartyOrgActivityInfo();
			return "view";
		}
		return SUCCESS;
	}

	private void getPartyOrgActivityInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = this.partyOrgActivityService
					.getPartyOrgActivityById(population.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(), organizationDubboService));
		}
	}

	private void procOrganization() throws Exception {
		Organization organization = new Organization();
		organization.setId(organizationId);
		organization.setOrgName(ControllerHelper.getOrganizationRelativeName(
				organization.getId(), organizationDubboService));
		population = new PartyOrgActivity();
		population.setOrganization(organization);
	}

	private PageInfo<PartyOrgActivity> emptyPage(int pageSize) throws Exception {
		PageInfo<PartyOrgActivity> pageInfo = new PageInfo<PartyOrgActivity>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PartyOrgActivity>());
		return pageInfo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public SearchPartyOrgActivityVo getSearchPartyOrgActivityVo() {
		return searchPartyOrgActivityVo;
	}

	public void setSearchPartyOrgActivityVo(
			SearchPartyOrgActivityVo searchPartyOrgActivityVo) {
		this.searchPartyOrgActivityVo = searchPartyOrgActivityVo;
	}

	public PartyOrgActivity getPopulation() {
		return population;
	}

	public void setPopulation(PartyOrgActivity population) {
		this.population = population;
	}
}
