package com.tianque.baseInfo.familyInfo.controller;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.familyInfo.domain.GroupFamily;
import com.tianque.baseInfo.familyInfo.domain.SearchGroupFamilyVo;
import com.tianque.baseInfo.familyInfo.service.GroupFamilyService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("prototype")
@Namespace("/baseinfo/searchGroupFamily")
@Controller("searchGroupFamilyController")
@Transactional
public class SearchGroupFamilyController extends BaseAction {

	private Long orgId;

	private SearchGroupFamilyVo searchGroupFamilyVo;
	@Autowired
	private GroupFamilyService groupFamilyService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Action(value = "searchGroupFamilyInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage" }) })
	public String searchGroupFamilyInfo() {
		if (null == orgId && null == searchGroupFamilyVo) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(groupFamilyService
					.pageGroupFamiliesBySearchGroupFamilyVoAndOrgId(searchGroupFamilyVo, orgId,
							page, rows, sidx, sord), organizationDubboService,
					new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	@Action(value = "vagueSearchGroupFamilyInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage" }) })
	public String vagueSearchGroupFamilyInfo() {
		if (null == orgId && null == searchGroupFamilyVo) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(groupFamilyService
					.pageGroupFamiliesByFullSearchGroupFamilyVoAndOrgId(searchGroupFamilyVo, orgId,
							page, rows, sidx, sord), organizationDubboService,
					new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	private PageInfo<GroupFamily> emptyPage(int pageSize) {
		PageInfo<GroupFamily> pageInfo = new PageInfo<GroupFamily>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<GroupFamily>());
		return pageInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public SearchGroupFamilyVo getSearchGroupFamilyVo() {
		return searchGroupFamilyVo;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public void setSearchGroupFamilyVo(SearchGroupFamilyVo searchGroupFamilyVo) {
		this.searchGroupFamilyVo = searchGroupFamilyVo;
	}
}
