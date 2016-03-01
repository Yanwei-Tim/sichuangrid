package com.tianque.fourTeams.fourTeamsIssue.domain;

import java.io.Serializable;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.base.BaseDomain;

/**
 * 四支队伍事件类型越级短信提醒:实体类(ISSUE_MESSAGE_REMIND)
 * 
 * @author
 * 
 */
public class FourTeamsIssueMessageRemind extends BaseDomain implements
		Serializable {
	private static final long serialVersionUID = 1L;

	/** 联系手机(CONTACTS_MOBILE) */
	private String contactsMobile;
	/** 事件类型越级规则ID(ISSUE_SKIPRULE_ID) */
	private Long issueSkipruleId;
	/** 联系人姓名(CONTACTS_NAME) */
	private String contactsName;

	public FourTeamsIssueMessageRemind() {

	}

	public FourTeamsIssueMessageRemind(String contactsMobile,
			Long issueSkipruleId, String contactsName) {
		this.contactsMobile = contactsMobile;
		this.issueSkipruleId = issueSkipruleId;
		this.contactsName = contactsName;
	}

	/** get 联系手机(contactsMobile) */
	public String getContactsMobile() {
		return contactsMobile;
	}

	/** set 联系手机(CONTACTS_MOBILE) */
	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}

	/** get 事件类型越级规则ID(issueSkipruleId) */
	public Long getIssueSkipruleId() {
		return issueSkipruleId;
	}

	/** set 事件类型越级规则ID(ISSUE_SKIPRULE_ID) */
	public void setIssueSkipruleId(Long issueSkipruleId) {
		this.issueSkipruleId = issueSkipruleId;
	}

	/** get 联系人姓名(contactsName) */
	public String getContactsName() {
		return contactsName;
	}

	/** set 联系人姓名(CONTACTS_NAME) */
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}
}
