package com.tianque.approval.domain;

import com.tianque.core.base.BaseDomain;

public class ItemAttachment extends BaseDomain {

	private Item item;
	private String fileName;
	private String fileActualUrl;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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
		this.fileActualUrl = fileActualUrl.replace("\\", "/");
	}

}
