package com.tianque.fourTeams.fourTeamsIssue.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 历史遗留
 * 
 * @author Death_Soul
 */
public class FourTeamsHistoricalIssue extends BaseDomain {
	/** 服务办事 **/
	private FourTeamsIssueNew issueNew;
	/** 部门 **/
	private Organization organization;
	/** 服务办事日志 **/
	private FourTeamsIssueLogNew issueLogNew;
	/** code **/
	private String orgInternalCode;

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public FourTeamsIssueNew getIssueNew() {
		return issueNew;
	}

	public void setIssueNew(FourTeamsIssueNew issueNew) {
		this.issueNew = issueNew;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public FourTeamsIssueLogNew getIssueLogNew() {
		return issueLogNew;
	}

	public void setIssueLogNew(FourTeamsIssueLogNew issueLogNew) {
		this.issueLogNew = issueLogNew;
	}
}
