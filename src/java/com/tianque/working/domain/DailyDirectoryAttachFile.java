package com.tianque.working.domain;

import java.io.Serializable;

public class DailyDirectoryAttachFile implements Serializable {
	/** 台帐目录 */
	private DailyDirectory dailyDirectory;
	/** 台帐年度目录 */
	private DailyYear dailyYear;
	/** 文件Id */
	private Long fileId;
	/** 文件名称 */
	private String fileName;
	/** 文件路径 */
	private String fileActualUrl;
	/** 文件大小 */
	private Long fileSize;

	public DailyYear getDailyYear() {
		return dailyYear;
	}

	public void setDailyYear(DailyYear dailyYear) {
		this.dailyYear = dailyYear;
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

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

}
