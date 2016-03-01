package com.tianque.fourTeams.fourTeamsIssue.domain;

import com.tianque.core.base.BaseDomain;

public class FourTeamsIssueRelatedPeople extends BaseDomain {

	/** 相关人员姓名 */
	private String name;
	/** 联系电话 */
	private String telephone;
	/** 固话 */
	private String fixPhone;
	/** 事件 */
	private FourTeamsIssueNew issue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public FourTeamsIssueNew getIssue() {
		return issue;
	}

	public void setIssue(FourTeamsIssueNew issue) {
		this.issue = issue;
	}

	public void setFixPhone(String fixPhone) {
		this.fixPhone = fixPhone;
	}

	public String getFixPhone() {
		return fixPhone;
	}
}
