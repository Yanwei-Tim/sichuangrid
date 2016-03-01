package com.tianque.plugin.serviceTeam.serviceRecord.domain;

import com.tianque.core.base.BaseDomain;

public class ServiceRecordAttachment extends BaseDomain {

	private Long recordId;

	private Long fileSize;

	private String fileName;

	private String fileActualUrl;

	public Long getRecordId() {
		return recordId;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileActualUrl() {
		return fileActualUrl;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileActualUrl(String fileActualUrl) {
		this.fileActualUrl = fileActualUrl;
	}

}
