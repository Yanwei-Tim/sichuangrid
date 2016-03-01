package com.tianque.fourTeams.fourTeamsIssue.state;

import com.tianque.domain.Organization;

public class FourTeamsIssueStepInfo {

	private Organization operationOrg;

	private int superviseLevel;

	public Organization getOperationOrg() {
		return operationOrg;
	}

	public void setOperationOrg(Organization operationOrg) {
		this.operationOrg = operationOrg;
	}

	public int getSuperviseLevel() {
		return superviseLevel;
	}

	public void setSuperviseLevel(int superviseLevel) {
		this.superviseLevel = superviseLevel;
	}

}
