package com.tianque.tenHouseholdsJoint.domain;


public class SendMsgInfo extends BaseMsgInfo {
	/**用户*/
	private FamilyInfo user;
	/**团队*/
	private FamilyTeam team;
	/**是否通知到*/
	private Boolean isReport;
	
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
	public Boolean getIsReport() {
		return isReport;
	}
	public void setIsReport(Boolean isReport) {
		this.isReport = isReport;
	}
	
}
