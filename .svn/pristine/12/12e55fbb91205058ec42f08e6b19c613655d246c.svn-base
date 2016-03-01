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
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.domain.IssueNew;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("searchTestIndividuallyControllerNew")
@Scope("prototype")
@Transactional
public class SearchTestIndividuallyControllerNew extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private SearchServiceDaoNew searchServiceDaoNew;

	private SearchIssueVo searchTestIndividuallyVo;

	private String testIndividuallyType = "contradiction";

	private Long orgId;

	private String requestType;

	public String searchTestIndividually() {
		if (searchTestIndividuallyVo != null
				&& searchTestIndividuallyVo.getIssueTypeDomainName() != null) {
			if (requestType == null || "mine".equalsIgnoreCase(requestType)) {
				searchTestIndividuallyByOrgId();
			} else if ("administer".equalsIgnoreCase(requestType)) {
				searchTestIndividuallyByOrgInternalCode();
			}
		} else
			gridPage = new GridPage(emptyIssuePage(rows));
		return SUCCESS;
	}

	private void searchTestIndividuallyByOrgId() {
		if (IssueTypes.CONTRADICTION.equalsIgnoreCase(searchTestIndividuallyVo
				.getIssueTypeDomainName()))
			searchContradictionsByOrgId();
		else
			gridPage = new GridPage(emptyIssuePage(rows));
	}

	private void searchContradictionsByOrgId() {
		if (checkSearchMineOrganization()) {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(searchServiceDaoNew
					.searchServicesByOrgId(searchTestIndividuallyVo, page, rows, sidx, sord),
					organizationDubboService, new String[] { "occurOrg" }, orgId));
		} else {
			gridPage = new GridPage(emptyIssuePage(rows));
		}
	}

	private void searchTestIndividuallyByOrgInternalCode() {
		if (IssueTypes.CONTRADICTION.equalsIgnoreCase(searchTestIndividuallyVo
				.getIssueTypeDomainName()))
			searchContradictionsByOrgInternalCode();
		else
			gridPage = new GridPage(emptyIssuePage(rows));
	}

	private void searchContradictionsByOrgInternalCode() {
		if (checkSearchAdministerOrganization()) {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(searchServiceDaoNew
					.searchServicesByOrgInternalCode(searchTestIndividuallyVo, page, rows, sidx,
							sord), organizationDubboService, new String[] { "occurOrg" }, orgId));
		} else {
			gridPage = new GridPage(emptyIssuePage(rows));
		}
	}

	private boolean checkSearchMineOrganization() {
		Organization org = getOrganization();
		if (null != org) {
			org.setOrgInternalCode(null);
			searchTestIndividuallyVo.setOrganization(org);
			return true;
		} else
			return false;
	}

	private boolean checkSearchAdministerOrganization() {
		Organization org = getOrganization();
		if (null != org) {
			org.setId(null);
			searchTestIndividuallyVo.setOrganization(org);
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

	public SearchIssueVo getSearchTestIndividuallyVo() {
		return searchTestIndividuallyVo;
	}

	public void setSearchTestIndividuallyVo(SearchIssueVo searchTestIndividuallyVo) {
		this.searchTestIndividuallyVo = searchTestIndividuallyVo;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getTestIndividuallyType() {
		return testIndividuallyType;
	}

	public void setTestIndividuallyType(String testIndividuallyType) {
		this.testIndividuallyType = testIndividuallyType;
	}

}
