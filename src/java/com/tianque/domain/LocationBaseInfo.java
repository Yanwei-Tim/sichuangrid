package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class LocationBaseInfo extends BaseDomain {

	private Organization organization;

	private String locationType;

	// 是否注销,true表示是
	private Boolean isEmphasis;

	/** 关注程度 */
	private PropertyDict attentionExtent;

	private Long sourcesState;

	/** 是否有检察人员 */
	private Long hasServiceTeamMember;
	/** 最新巡场时间 */
	private Date lastRecordTime;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public Boolean getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Boolean isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public PropertyDict getAttentionExtent() {
		return attentionExtent;
	}

	public void setAttentionExtent(PropertyDict attentionExtent) {
		this.attentionExtent = attentionExtent;
	}

	public Long getSourcesState() {
		return sourcesState;
	}

	public void setSourcesState(Long sourcesState) {
		this.sourcesState = sourcesState;
	}

	public Long getHasServiceTeamMember() {
		return hasServiceTeamMember;
	}

	public void setHasServiceTeamMember(Long hasServiceTeamMember) {
		this.hasServiceTeamMember = hasServiceTeamMember;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLastRecordTime() {
		return lastRecordTime;
	}

	public void setLastRecordTime(Date lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}
}
