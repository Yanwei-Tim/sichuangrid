package com.tianque.fourTeams.fourTeamsIssue.domain.vo;

import java.util.Date;

import com.tianque.core.base.BaseDomain;

/**
 * 四支队伍事件类型越级短信提醒:SrarchVO(FOURTEAMS_ISSUE_MESSAGE_REMIND)
 * 
 * @author
 * 
 */
public class SearchFourTeamsIssueMessageRemindVo extends BaseDomain {

	/** 最大 */
	private Date maxCreatedate;
	/** 最小 */
	private Date minCreatedate;
	/** 最大 */
	private Date maxUpdatedate;
	/** 最小 */
	private Date minUpdatedate;
	/** 联系手机 */
	private String contactsMobile;
	/** 事件类型越级规则ID */
	private Long issueSkipruleId;
	/** 联系人姓名 */
	private String contactsName;

	/** get 最大 */
	public Date getMaxCreatedate() {
		return maxCreatedate;
	}

	/** set 最大 */
	public void setMaxCreatedate(Date maxCreatedate) {
		this.maxCreatedate = maxCreatedate;
	}

	/** get 最小 */
	public Date getMinCreatedate() {
		return minCreatedate;
	}

	/** set 最小 */
	public void setMinCreatedate(Date minCreatedate) {
		this.minCreatedate = minCreatedate;
	}

	/** get 最大 */
	public Date getMaxUpdatedate() {
		return maxUpdatedate;
	}

	/** set 最大 */
	public void setMaxUpdatedate(Date maxUpdatedate) {
		this.maxUpdatedate = maxUpdatedate;
	}

	/** get 最小 */
	public Date getMinUpdatedate() {
		return minUpdatedate;
	}

	/** set 最小 */
	public void setMinUpdatedate(Date minUpdatedate) {
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
