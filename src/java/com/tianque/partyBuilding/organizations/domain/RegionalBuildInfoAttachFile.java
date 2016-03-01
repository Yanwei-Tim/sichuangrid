package com.tianque.partyBuilding.organizations.domain;

import com.tianque.core.base.BaseDomain;

/**
 * 区域联建情况附件
 * */
public class RegionalBuildInfoAttachFile extends BaseDomain {
	/* 区域联建情况id */
	private Long regionalBuildInfoId;
	/* 区域联建情况附件名称 */
	private String fileName;
	/* 区域联建情况附件URL */
	private String fileActualUrl;

	public Long getRegionalBuildInfoId() {
		return regionalBuildInfoId;
	}

	public void setRegionalBuildInfoId(Long regionalBuildInfoId) {
		this.regionalBuildInfoId = regionalBuildInfoId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileActualUrl() {
		return fileActualUrl;
	}

	public void setFileActualUrl(String fileActualUrl) {
		this.fileActualUrl = fileActualUrl;
	}
}
