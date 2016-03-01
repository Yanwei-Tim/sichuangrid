package com.tianque.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * 党组织活动记录
 */
@SuppressWarnings("serial")
public class PartyOrgActivity extends BaseDomain implements Serializable {

	/** 所属网格 */
	private Organization organization;

	/** 所属党支部 */
	private PartyOrgInfo partyOrgInfo;

	/** 活动时间 */
	private Date activityDate;

	/** 活动地点 */
	private String activityAddr;

	/** 活动主题 */
	private String activitySubject;

	/** 组织者 */
	private String organizers;

	/** 参与者 */
	private String participants;

	/** 活动内容 */
	private String activeContent;

	/** 附件集合 */
	private List<PartyOrgActivityFile> activityAttachFile = new ArrayList<PartyOrgActivityFile>();

	public PartyOrgActivity() {
	}

	public List<PartyOrgActivityFile> getActivityAttachFile() {
		return activityAttachFile;
	}

	public void setActivityAttachFile(List<PartyOrgActivityFile> activityAttachFile) {
		this.activityAttachFile = activityAttachFile;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public PartyOrgInfo getPartyOrgInfo() {
		return partyOrgInfo;
	}

	public void setPartyOrgInfo(PartyOrgInfo partyOrgInfo) {
		this.partyOrgInfo = partyOrgInfo;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getActivityAddr() {
		return activityAddr;
	}

	public void setActivityAddr(String activityAddr) {
		this.activityAddr = activityAddr;
	}

	public String getActivitySubject() {
		return activitySubject;
	}

	public void setActivitySubject(String activitySubject) {
		this.activitySubject = activitySubject;
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
