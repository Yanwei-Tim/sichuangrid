package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.domain.IssueNew;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

@Controller("peopLiveServiceController")
@Scope("prototype")
@Transactional
public class PeopLiveServiceController extends BaseAction {
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Organization sourceOrganization;

	private IssueNew issueNew = new IssueNew();

	private User user;

	@Autowired
	private PermissionService permissionService;

	private String issueTypeDomainName = IssueTypes.PEOPLELIVE_SERVICE;

	public String dispatch() throws Exception {
		if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			if (sourceOrganization != null
					&& sourceOrganization.getId() != null) {
				sourceOrganization = organizationDubboService
						.getSimpleOrgById(sourceOrganization.getId());
			}
		}
		return SUCCESS;
	}

	public String prePeopLiveServiceList() throws Exception {
		user = permissionService.getFullUserById(ThreadVariable.getSession()
				.getUserId());
		sourceOrganization = user.getOrganization() == null ? null : user
				.getOrganization();
		return SUCCESS;
	}

	public IssueNew getIssueNew() {
		return issueNew;
	}

	public void setIssueNew(IssueNew IssueNew) {
		this.issueNew = IssueNew;
	}

	public String getIssueTypeDomainName() {
		return issueTypeDomainName;
	}

	public void setIssueTypeDomainName(String issueTypeDomainName) {
		this.issueTypeDomainName = issueTypeDomainName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Organization getSourceOrganization() {
		return sourceOrganization;
	}

	public void setSourceOrganization(Organization sourceOrganization) {
		this.sourceOrganization = sourceOrganization;
	}

}
