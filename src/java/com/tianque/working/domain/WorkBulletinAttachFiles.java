package com.tianque.working.domain;

import com.tianque.core.base.BaseDomain;

public class WorkBulletinAttachFiles extends BaseDomain {

	private static final long serialVersionUID = -8528227433278135689L;

	private Long id;

	private Long bulletinId;

	private String fileName;

	private String fileactualUrl;
	/** 文件大小 */
	private Long fileSize;

	public Long getId() {
		return id;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBulletinId() {
		return bulletinId;
	}

	public void setBulletinId(Long bulletinId) {
		this.bulletinId = bulletinId;
	}

	public String getFileName() {
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

}