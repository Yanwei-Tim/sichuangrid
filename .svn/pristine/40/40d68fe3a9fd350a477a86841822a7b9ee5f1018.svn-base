package com.tianque.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchServiceDaoNew;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchIssueVo;
import com.tianque.issue.domain.IssueNew;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("searchPeopleLiveServiceController")
@Scope("prototype")
@Transactional
public class SearchPeopleLiveServiceController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private SearchServiceDaoNew searchServiceDaoNew;

	private SearchIssueVo searchPeopleLiveServiceVo;

	private String requestType;

	public SearchIssueVo getSearchPeopleLiveServiceVo() {
		return searchPeopleLiveServiceVo;
	}

	public void setSearchPeopleLiveServiceVo(SearchIssueVo searchPeopleLiveServiceVo) {
		this.searchPeopleLiveServiceVo = searchPeopleLiveServiceVo;
	}

	private Long orgId;

	public String searchPeopleLiveService() {
		if (requestType == null || "mine".equalsIgnoreCase(requestType)) {
			searchContradictionOrPeopleLiveServicesByOrgId();
		} else if ("administer".equalsIgnoreCase(requestType)) {
			searchContradictionOrPeopleLiveServicesByOrgInternalCode();
		}
		return SUCCESS;
	}

	private void searchContradictionOrPeopleLiveServicesByOrgId() {
		if (checkSearchMineOrganization()) {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(searchServiceDaoNew
					.searchServicesByOrgId(searchPeopleLiveServiceVo, page, rows, sidx, sord),
					organizationDubboService, new String[] { "occurOrg" }, orgId));
		} else {
			gridPage = new GridPage(emptyIssuePage(rows));
		}
	}

	private void searchContradictionOrPeopleLiveServicesByOrgInternalCode() {
		if (checkSearchAdministerOrganization()) {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(searchServiceDaoNew
					.searchServicesByOrgInternalCode(searchPeopleLiveServiceVo, page, rows, sidx,
							sord), organizationDubboService, new String[] { "occurOrg" }, orgId));
		} else {
			gridPage = new GridPage(emptyIssuePage(rows));
		}
	}

	private boolean checkSearchMineOrganization() {
		Organization org = getOrganization();
		if (null != org) {
			org.setOrgInternalCode(null);
			searchPeopleLiveServiceVo.setOrganization(org);
			return true;
		} else
			return false;
	}

	private boolean checkSearchAdministerOrganization() {
		Organization org = getOrganization();
		if (null != org) {
			org.setId(null);
			searchPeopleLiveServiceVo.setOrganization(org);
			return true;
		} else
			return false;
	}

	private Organization getOrganization() {
		if (null != orgId) {
			return organizationDubboService.getSimpleOrgById(orgId);
		} else {
			return null;
		}
	}

	private PageInfo<IssueNew> emptyIssuePage(int pageSize) {
		PageInfo<IssueNew> pageInfo = new PageInfo<IssueNew>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<IssueNew>());
		return pageInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

}
