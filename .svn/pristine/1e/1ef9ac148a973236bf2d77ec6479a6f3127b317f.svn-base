package com.tianque.baseInfo.scenicManage.scenicSpot.controller;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.baseInfo.scenicManage.scenicSpot.service.SearchScenicSpotService;
import com.tianque.baseInfo.scenicManage.scenicSpot.vo.SearchScenicSpotVo;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Scope("prototype")
@Controller("searchScenicSpotController")
@Namespace("/baseinfo/searchScenicSpot")
public class SearchScenicSpotController extends SearchBaseAction {

	@Autowired
	private SearchScenicSpotService searchScenicSpotService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	private Long organizationId;
	private SearchScenicSpotVo searchScenicSpotVo;

	@PermissionFilter(ename = "searchScenicSpot")
	@Action(value = "findScenicSpotsByQueryCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findScenicSpotsByQueryCondition() {
		if (organizationId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		} else {
			searchCommonality();
		}
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "igonreHierarch", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String fastSearch() {
		if (searchScenicSpotVo == null) {
			gridPage = new GridPage(emptyRectificativePersonPage(rows));
			return SUCCESS;
		} else {
			searchCommonality();
		}
		return SUCCESS;
	}

	private void searchCommonality() {
		Organization org = organizationDubboService.getSimpleOrgById(organizationId);
		if (searchScenicSpotVo == null)
			searchScenicSpotVo = new SearchScenicSpotVo();
		searchScenicSpotVo.setOrgInternalCode(org.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchScenicSpotService.findScenicSpotListForSearch(
						searchScenicSpotVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organizationId));
	}

	private PageInfo<ScenicSpot> emptyRectificativePersonPage(Integer pageSize) {
		PageInfo<ScenicSpot> pageInfo = new PageInfo<ScenicSpot>();
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<ScenicSpot>());
		pageInfo.setTotalRowSize(0);
		return pageInfo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public SearchScenicSpotVo getSearchScenicSpotVo() {
		return searchScenicSpotVo;
	}

	public void setSearchScenicSpotVo(SearchScenicSpotVo searchScenicSpotVo) {
		this.searchScenicSpotVo = searchScenicSpotVo;
	}

}
