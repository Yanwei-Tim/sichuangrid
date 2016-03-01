package com.tianque.baseInfo.primaryOrg.primaryMembers.domain;

public class PrimaryOrgOption {
	private Long id;
	private String primaryOrgType;
	private String primaryOrgName;
	private Integer isSynchronize;
	private Integer isFourLevelPlatform;

	public Integer getIsSynchronize() {
		return isSynchronize;
	}

	public void setIsSynchronize(Integer isSynchronize) {
		this.isSynchronize = isSynchronize;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimaryOrgType() {
		return primaryOrgType;
	}

	public void setPrimaryOrgType(String primaryOrgType) {
		this.primaryOrgType = primaryOrgType;
	}

	public String getPrimaryOrgName() {
		return primaryOrgName;
	}

	public void setPrimaryOrgName(String primaryOrgName) {
		this.primaryOrgName = primaryOrgName;
	}

	public Integer getIsFourLevelPlatform() {
		return isFourLevelPlatform;
	}

	public void setIsFourLevelPlatform(Integer isFourLevelPlatform) {
		this.isFourLevelPlatform = isFourLevelPlatform;
	}

}
