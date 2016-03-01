package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.vo.TestIndividually;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.domain.IssueNew;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

@Controller("testIndividuallyControllerNew")
@Scope("prototype")
@Transactional
public class TestIndividuallyControllerNew extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private PermissionService permissionService;

	private Organization sourceOrganization;

	private IssueNew issue = new IssueNew();

	private User user;

	private String issueTypeDomainName = IssueTypes.CONTRADICTION;

	private String testIndividuallyType = "contradiction";

	private String requestType;

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

	public String preTestIndividuallyList() throws Exception {
		user = permissionService.getFullUserById(ThreadVariable.getSession()
				.getUserId());
		sourceOrganization = user.getOrganization() == null ? null : user
				.getOrganization();
		if (requestType == null || "".equals(requestType)) {
			requestType = "mine";
		}
		if (TestIndividually.CONTRADICTION
				.equalsIgnoreCase(testIndividuallyType)) {
			issueTypeDomainName = IssueTypes.CONTRADICTION;
		}
		return SUCCESS;
	}

	public IssueNew getIssue() {
		return issue;
	}

	public void setIssue(IssueNew issue) {
		this.issue = issue;
	}

	public Organization getSourceOrganization() {
		return sourceOrganization;
	}

	public void setSourceOrganization(Organization sourceOrganization) {
		this.sourceOrganization = sourceOrganization;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIssueTypeDomainName() {
		return issueTypeDomainName;
	}

	public void setIssueTypeDomainName(String issueTypeDomainName) {
		this.issueTypeDomainName = issueTypeDomainName;
	}

	public String getTestIndividuallyType() {
		return testIndividuallyType;
	}

	public void setTestIndividuallyType(String testIndividuallyType) {
		this.testIndividuallyType = testIndividuallyType;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

}
