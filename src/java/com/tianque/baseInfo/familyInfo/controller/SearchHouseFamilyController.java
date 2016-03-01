package com.tianque.baseInfo.familyInfo.controller;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.familyInfo.domain.HouseFamily;
import com.tianque.baseInfo.familyInfo.service.HouseFamilyService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchFamilyHouseVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Scope("prototype")
@Controller("searchHouseFamilyController")
@Namespace("/baseinfo/searchHouseFamily")
public class SearchHouseFamilyController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private HouseFamilyService houseFamilyService;

	private Long orgId;

	private SearchFamilyHouseVo searchFamilyHouseVo;

	@Action(value = "searchFamilyHouseInfromation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchFamilyHouseInfromation() throws Exception {
		if (searchFamilyHouseVo == null)
			return ERROR;
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					houseFamilyService
							.findHouseFamilyByOrgIdAndSearchCondition(
									searchFamilyHouseVo, orgId, page, rows,
									sidx, sord), organizationDubboService,
					new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	@Action(value = "narrowlySearchHouseFamilyInfo", results = {
			@Result(name = "success", location = "/baseinfo/familyInfo/houseFamily/searchHouseFamily.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String narrowlySearchHouseFamilyInfo() throws Exception {
		return SUCCESS;
	}

	@Action(value = "searchSomeHouseFamilyInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchSomeHouseFamilyInfo() throws Exception {
		if (orgId == null && searchFamilyHouseVo == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					houseFamilyService
							.findHouseFamilyByOrgIdAndMinuteSearchCondition(
									searchFamilyHouseVo, orgId, page, rows,
									sidx, sord), organizationDubboService,
					new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	private PageInfo<HouseFamily> emptyPage(int pageSize) {
		PageInfo<HouseFamily> pageInfo = new PageInfo<HouseFamily>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<HouseFamily>());
		return pageInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchFamilyHouseVo getSearchFamilyHouseVo() {
		return searchFamilyHouseVo;
	}

	public void setSearchFamilyHouseVo(SearchFamilyHouseVo searchFamilyHouseVo) {
		this.searchFamilyHouseVo = searchFamilyHouseVo;
	}
}
