package com.tianque.domain.vo;

import com.tianque.domain.IssueType;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueNew;

public class SearchIssueInvestigationMediateVo {
	public Organization getOrganizationInvestigation() {
		return organizationInvestigation;
	}

	public void setOrganizationInvestigation(Organization organizationInvestigation) {
		this.organizationInvestigation = organizationInvestigation;
	}

	public Organization getOrganizationMediate() {
		return organizationMediate;
	}

	public void setOrganizationMediate(Organization organizationMediate) {
		this.organizationMediate = organizationMediate;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public IssueNew getIssueNew() {
		return issueNew;
	}

	public void setIssueNew(IssueNew issueNew) {
		this.issueNew = issueNew;
	}

	public Long getDealType() {
		return dealType;
	}

	public void setDealType(Long dealType) {
		this.dealType = dealType;
	}

	public Long getDealState() {
		return dealState;
	}

	public void setDealState(Long dealState) {
		this.dealState = dealState;
	}

	private Organization organizationInvestigation;

	private Organization organizationMediate;

	private IssueType issueType;

	private IssueNew issueNew;

	private Long dealType;

	private Long dealState;
}
