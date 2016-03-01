package com.tianque.working.domain;

import com.tianque.core.base.BaseDomain;

public class OptionalObj extends BaseDomain {
	// 文件ID
	private Long documentId;
	// 手动添加的部门id
	private String optionalOrgIds;
	// 发文对象是主送还是抄送
	private String postObj;

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getPostObj() {
		return postObj;
	}

	public void setPostObj(String postObj) {
		this.postObj = postObj;
	}

	public String getOptionalOrgIds() {
		return optionalOrgIds;
	}

	public void setOptionalOrgIds(String optionalOrgIds) {
		this.optionalOrgIds = optionalOrgIds;
	}

}
