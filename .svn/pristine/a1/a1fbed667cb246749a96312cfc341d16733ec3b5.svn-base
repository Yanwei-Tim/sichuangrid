package com.tianque.partyBuilding.activityRecords.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;

/**
 * 组织活动类
 * */
public class ActivityRecords extends BaseDomain {

	/** 组织机构 */
	private Organization organization;
	/** 组织id */
	private Long organizationId;
	/** 组织类型，区分是两新组织，还是党组织等 */
	private String organizationType;
	/** 组织的名称 */
	private String organizationName;
	/** 组织活动时间 */
	private Date activityDate;
	/** 组织活动地点 */
	private String activityPlace;
	/** 组织活动主题 */
	private String activityTheme;
	/** 活动组织者 */
	private String organizer;
	/** 活动参与者 */
	private String participant;
	/** 活动内容 */
	private String details;

	/** 是否有附件 */
	private Boolean isAttachment;

	/** 附件 */
	private List<ActivityRecordsAttachFiles> activityRecordFiles = new ArrayList<ActivityRecordsAttachFiles>();

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getActivityPlace() {
		return activityPlace;
	}

	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}

	public String getActivityTheme() {
		return activityTheme;
	}

	public void setActivityTheme(String activityTheme) {
		this.activityTheme = activityTheme;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Boolean getIsAttachment() {
		return isAttachment;
	}

	public void setIsAttachment(Boolean isAttachment) {
		this.isAttachment = isAttachment;
	}

	public List<ActivityRecordsAttachFiles> getActivityRecordFiles() {
		return activityRecordFiles;
	}

	public void setActivityRecordFiles(
			List<ActivityRecordsAttachFiles> activityRecordFiles) {
		this.activityRecordFiles = activityRecordFiles;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.organization.getOrgInternalCode(), null);
	}
}
