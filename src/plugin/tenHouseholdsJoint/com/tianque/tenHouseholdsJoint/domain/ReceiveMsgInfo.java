package com.tianque.tenHouseholdsJoint.domain;


public class ReceiveMsgInfo extends BaseMsgInfo {

	private Boolean isDeal;

	private Boolean isReport;
	/**用户*/
	private FamilyInfo user;
	/**团队*/
	private FamilyTeam team;

	public Boolean getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(Boolean isDeal) {
		this.isDeal = isDeal;
	}

	public Boolean getIsReport() {
		return isReport;
	}

	public void setIsReport(Boolean isReport) {
		this.isReport = isReport;
	}

	public FamilyInfo getUser() {
		return user;
	}

	public void setUser(FamilyInfo user) {
		this.user = user;
	}

	public FamilyTeam getTeam() {
		return team;
	}

	public void setTeam(FamilyTeam team) {
		this.team = team;
	}

}
