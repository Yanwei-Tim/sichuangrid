package com.tianque.statAnalyse.issueManage.listManage.domain;

import java.io.Serializable;

import com.tianque.domain.PropertyDict;

public class IssueLevelStat implements Serializable {
	private PropertyDict orgLevelOrFunOrgType;
	private IssueAreaStat issueAreaStat;

	public PropertyDict getOrgLevelOrFunOrgType() {
		return orgLevelOrFunOrgType;
	}

	public void setOrgLevelOrFunOrgType(PropertyDict orgLevelOrFunOrgType) {
		this.orgLevelOrFunOrgType = orgLevelOrFunOrgType;
	}

	public IssueAreaStat getIssueAreaStat() {
		return issueAreaStat;
	}

	public void setIssueAreaStat(IssueAreaStat issueAreaStat) {
		this.issueAreaStat = issueAreaStat;
	}
}
