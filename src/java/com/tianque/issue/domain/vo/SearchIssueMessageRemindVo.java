package com.tianque.issue.domain.vo;

/**
 * 事件类型越级短信提醒:SrarchVO(ISSUE_MESSAGE_REMIND)
 * 
 * @author
 * @date 2013-11-22 14:51:42
 */
public class SearchIssueMessageRemindVo extends
		com.tianque.core.base.BaseDomain {

	/** 最大 */
	private java.util.Date maxCreatedate;
	/** 最小 */
	private java.util.Date minCreatedate;
	/** 最大 */
	private java.util.Date maxUpdatedate;
	/** 最小 */
	private java.util.Date minUpdatedate;
	/** 联系手机 */
	private String contactsMobile;
	/** 事件类型越级规则ID */
	private Long issueSkipruleId;
	/** 联系人姓名 */
	private String contactsName;

	/** get 最大 */
	public java.util.Date getMaxCreatedate() {
		return maxCreatedate;
	}

	/** set 最大 */
	public void setMaxCreatedate(java.util.Date maxCreatedate) {
		this.maxCreatedate = maxCreatedate;
	}

	/** get 最小 */
	public java.util.Date getMinCreatedate() {
		return minCreatedate;
	}

	/** set 最小 */
	public void setMinCreatedate(java.util.Date minCreatedate) {
		this.minCreatedate = minCreatedate;
	}

	/** get 最大 */
	public java.util.Date getMaxUpdatedate() {
		return maxUpdatedate;
	}

	/** set 最大 */
	public void setMaxUpdatedate(java.util.Date maxUpdatedate) {
		this.maxUpdatedate = maxUpdatedate;
	}

	/** get 最小 */
	public java.util.Date getMinUpdatedate() {
		return minUpdatedate;
	}

	/** set 最小 */
	public void setMinUpdatedate(java.util.Date minUpdatedate) {
		this.minUpdatedate = minUpdatedate;
	}

	/** get 联系手机 */
	public String getContactsMobile() {
		return contactsMobile;
	}

	/** set 联系手机 */
	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}

	/** get 事件类型越级规则ID */
	public Long getIssueSkipruleId() {
		return issueSkipruleId;
	}

	/** set 事件类型越级规则ID */
	public void setIssueSkipruleId(Long issueSkipruleId) {
		this.issueSkipruleId = issueSkipruleId;
	}

	/** get 联系人姓名 */
	public String getContactsName() {
		return contactsName;
	}

	/** set 联系人姓名 */
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

}
