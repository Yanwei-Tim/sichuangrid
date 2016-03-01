package com.tianque.working.domain;

import com.tianque.core.base.BaseDomain;

public class DailyAttachFiles extends BaseDomain {

	private static final long serialVersionUID = -8528227433278135689L;

	private Long id;
	private Long dailyId;// 台账ID
	private Long dailyDirectoryType;// 台账类型
	private String fileName;
	private String fileactualUrl;
	/** 文件大小 */
	private Long fileSize;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		if (fileName.indexOf(",") == 0) {
			fileName = fileName.substring(1);
		}
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileactualUrl() {
		if (null == fileactualUrl) {
			fileactualUrl = " ";
		}
		return fileactualUrl;
	}

	public void setFileactualUrl(String fileactualUrl) {
		this.fileactualUrl = fileactualUrl;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Long getDailyId() {
		return dailyId;
	}

	public void setDailyId(Long dailyId) {
		this.dailyId = dailyId;
	}

	public Long getDailyDirectoryType() {
		return dailyDirectoryType;
	}

	public void setDailyDirectoryType(Long dailyDirectoryType) {
		this.dailyDirectoryType = dailyDirectoryType;
	}
}
