package com.tianque.partyBuilding.activityRecords.domain.vo;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 组织活动的搜索类
 * */
public class ActivityRecordsVo extends BaseDomain {
	/** 组织机构 */
	private Organization organization;
	/** 组织id */
	private Long organizationId;
	/** 组织类型，区分是两新组织，还是党组织等 */
	private String organizationType;
	/** 组织的名称 */
	private String organizationName;
	/** 组织活动时间开始 */
	private Date activityDateStart;
	/** 组织活动时间结束 */
	private Date activityDateEnd;
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
	/** 需要关联组织的对应的表名称 */
	private String table;
	/** 对应表名的组织名称对应的字段名称 */
	private String name;

	/** 是否有附件 */
	private Boolean isAttachment;

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

	public Date getActivityDateStart() {
		return activityDateStart;
	}

	public void setActivityDateStart(Date activityDateStart) {
		this.activityDateStart = activityDateStart;
	}

	public Date getActivityDateEnd() {
		return activityDateEnd;
	}

	public void setActivityDateEnd(Date activityDateEnd) {
		this.activityDateEnd = activityDateEnd;
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

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
