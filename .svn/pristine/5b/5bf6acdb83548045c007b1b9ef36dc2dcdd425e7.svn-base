package com.tianque.working.domain;

import com.tianque.core.base.BaseDomain;

public class DocumentsHasAttachFiles extends BaseDomain {

	private static final long serialVersionUID = 1L;
	private String fileName;

	private String fileActualUrl;

	private Long documentId;

	private Long fileId;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
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

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DocumentsHasAttachFiles) {
			DocumentsHasAttachFiles s = (DocumentsHasAttachFiles) obj;
			if (this.fileId.equals(s.fileId)) {// ==是指对象的地址是否相等，equals判断的是值是否相等
				return true;
			}
		}
		return false;
	}

	public String getFileName() {
		return fileName;
	}

}
