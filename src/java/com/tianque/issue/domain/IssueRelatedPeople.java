package com.tianque.issue.domain;

import com.tianque.core.base.BaseDomain;

public class IssueRelatedPeople extends BaseDomain {

	/** 相关人员姓名 */
	private String name;
	/** 联系电话 */
	private String telephone;
	/** 固话 */
	private String fixPhone;
	/** 事件 */
	private IssueNew issue;

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

	public IssueNew getIssue() {
		return issue;
	}

	public void setIssue(IssueNew issue) {
		this.issue = issue;
	}

	public void setFixPhone(String fixPhone) {
		this.fixPhone = fixPhone;
	}

	public String getFixPhone() {
		return fixPhone;
	}
}
