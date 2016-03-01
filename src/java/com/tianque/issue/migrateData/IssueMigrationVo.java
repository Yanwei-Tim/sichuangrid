package com.tianque.issue.migrateData;

public class IssueMigrationVo {

	private Long issueStepId;
	private Long emergencyLevel;// issue的重大危险程度

	public void setIssueStepId(Long issueStepId) {
		this.issueStepId = issueStepId;
	}

	public Long getIssueStepId() {
		return issueStepId;
	}

	public void setEmergencyLevel(Long emergencyLevel) {
		this.emergencyLevel = emergencyLevel;
	}

	public Long getEmergencyLevel() {
		return emergencyLevel;
	}

}
