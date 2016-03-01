package com.tianque.baseInfo.primaryOrg.primaryMembers.domain;

import com.tianque.core.base.BaseDomain;

public class PrimaryMembersOrgType extends BaseDomain {
	private Long primaryMembersId;

	private Long primaryOrgId;

	private Long isFourLevelPlatform;

	// 组织名称 更新时页面显示用
	private String detailName;

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public PrimaryMembersOrgType() {

	}

	public PrimaryMembersOrgType(Long primaryMembersId, Long primaryOrgId) {
		this.primaryMembersId = primaryMembersId;
		this.primaryOrgId = primaryOrgId;
	}

	public Long getPrimaryMembersId() {
		return primaryMembersId;
	}

	public void setPrimaryMembersId(Long primaryMembersId) {
		this.primaryMembersId = primaryMembersId;
	}

	public Long getPrimaryOrgId() {
		return primaryOrgId;
	}

	public void setPrimaryOrgId(Long primaryOrgId) {
		this.primaryOrgId = primaryOrgId;
	}

	public Long getIsFourLevelPlatform() {
		return isFourLevelPlatform;
	}

	public void setIsFourLevelPlatform(Long isFourLevelPlatform) {
		this.isFourLevelPlatform = isFourLevelPlatform;
	}
}
