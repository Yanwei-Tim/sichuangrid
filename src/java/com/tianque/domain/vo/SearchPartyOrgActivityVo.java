package com.tianque.domain.vo;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

@SuppressWarnings("serial")
public class SearchPartyOrgActivityVo extends BaseDomain {

	/** 所属网格 */
	private Organization organization;

	/** 活动时间从 */
	private Date activityDate;

	/** 活动时间到 */
	private Date endActivityDate;

	/** 活动主题 */
	private String activitySubject;

	/** 活动地点 */
	private String activityAddr;

	/** 组织者 */
	private String organizers;

	/** 参与者 */
	private String participants;

	/** 活动内容 */
	private String activeContent;

	/** 所属责任区编号 */
	private String orgInternalCode;

	/** 活动所在年度 */
	private String yearActivity;

	public String getYearActivity() {
		return yearActivity;
	}

	public void setYearActivity(String yearActivity) {
		this.yearActivity = yearActivity;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public Date getEndActivityDate() {
		return endActivityDate;
	}

	public void setEndActivityDate(Date endActivityDate) {
		this.endActivityDate = endActivityDate;
	}

	public String getActivitySubject() {
		return activitySubject;
	}

	public void setActivitySubject(String activitySubject) {
		this.activitySubject = activitySubject;
	}

	public String getActivityAddr() {
		return activityAddr;
	}

	public void setActivityAddr(String activityAddr) {
		this.activityAddr = activityAddr;
	}

	public String getOrganizers() {
		return organizers;
	}

	public void setOrganizers(String organizers) {
		this.organizers = organizers;
	}

	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public String getActiveContent() {
		return activeContent;
	}

	public void setActiveContent(String activeContent) {
		this.activeContent = activeContent;
	}
}
