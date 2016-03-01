package com.tianque.issue.domain;

import com.tianque.core.base.BaseDomain;

public class SupervisionAdministration extends BaseDomain {
	private Long orgId;
	private String orgCode;
	private String content;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
