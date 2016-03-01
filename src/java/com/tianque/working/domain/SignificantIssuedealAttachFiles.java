package com.tianque.working.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.SignificantIssuedeal;

public class SignificantIssuedealAttachFiles extends BaseDomain {

	private static final long serialVersionUID = -8528227433278135689L;

	private Long id;

	private SignificantIssuedeal significantIssuedealWorkingRecord;

	private String fileName;

	private String fileactualUrl;
	/** 文件大小 */
	private Long fileSize;

	/** 附件层级 **/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		if (fileName.indexOf(",") == 0) {
			fileName = fileName.substring(1);
		}
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

	public SignificantIssuedeal getSignificantIssuedealWorkingRecord() {
		return significantIssuedealWorkingRecord;
	}

	public void setSignificantIssuedealWorkingRecord(
			SignificantIssuedeal significantIssuedealWorkingRecord) {
		this.significantIssuedealWorkingRecord = significantIssuedealWorkingRecord;
	}
}
