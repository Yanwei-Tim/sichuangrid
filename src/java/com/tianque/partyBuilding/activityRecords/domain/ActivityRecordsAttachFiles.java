package com.tianque.partyBuilding.activityRecords.domain;

import com.tianque.core.base.BaseDomain;

/**
 * 组织活动附件
 * */
public class ActivityRecordsAttachFiles extends BaseDomain {
	/* 组织活动id */
	private Long activityRecordId;
	/* 组织活动附件名称 */
	private String fileName;
	/* 组织活动附件URL */
	private String fileActualUrl;

	public Long getActivityRecordId() {
		return activityRecordId;
	}

	public void setActivityRecordId(Long activityRecordId) {
		this.activityRecordId = activityRecordId;
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
