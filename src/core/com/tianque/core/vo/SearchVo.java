package com.tianque.core.vo;

import com.tianque.core.base.BaseDomain;

public class SearchVo extends BaseDomain {
	private Long searchId;
	private String orgInternalCode;
	private String searchCondition;

	public Long getSearchId() {
		return searchId;
	}

	public void setSearchId(Long orgId) {
		this.searchId = orgId;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

}
