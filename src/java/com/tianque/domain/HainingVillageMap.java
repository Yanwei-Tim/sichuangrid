package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

public class HainingVillageMap extends BaseDomain {
	private String departmentNo;
	private String imgUrl;
	private boolean disposeSuccess;
	private String disposeResult;
	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDisposeResult() {
		return disposeResult;
	}

	public void setDisposeResult(String disposeResult) {
		this.disposeResult = disposeResult;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public boolean getDisposeSuccess() {
		return disposeSuccess;
	}

	public void setDisposeSuccess(boolean disposeSuccess) {
		this.disposeSuccess = disposeSuccess;
	}

}
