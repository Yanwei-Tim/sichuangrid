package com.tianque.working.domain;

import com.tianque.core.base.BaseDomain;

public class DailyLogAttachFile extends BaseDomain {
	/** 工作台帐 */
	private WorkingRecord workingRecord;
	/** 工作台帐 */
	private DailyLog dailyLog;
	/** 工作台帐年度目录 */
	private DailyYear dailyYear;
	/** 文件名称 */
	private String fileName;
	/** 文件路径 */
	private String fileActualUrl;
	/** 文件大小 */
	private Long fileSize;

	public DailyLog getDailyLog() {
		return dailyLog;
	}

	public void setDailyLog(DailyLog dailyLog) {
		this.dailyLog = dailyLog;
	}

	public DailyYear getDailyYear() {
		return dailyYear;
	}

	public void setDailyYear(DailyYear dailyYear) {
		this.dailyYear = dailyYear;
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

	public WorkingRecord getWorkingRecord() {
		return workingRecord;
	}

	public void setWorkingRecord(WorkingRecord workingRecord) {
		this.workingRecord = workingRecord;
	}

}
