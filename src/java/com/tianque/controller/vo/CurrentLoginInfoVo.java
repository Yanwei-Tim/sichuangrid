package com.tianque.controller.vo;

import com.tianque.domain.PropertyDict;

public class CurrentLoginInfoVo {
	private Long userId;
	private Long orgId;
	private boolean yuhangUser;// 余杭区地图特有字段 是否为余杭区用户
	private String orgFullPyForTown;// 余杭区地图特有字段 fullpinyin 如果是余杭放回余杭区的
									// fullpinyin， 乡镇其以下的用户返回乡镇的 fullpinyin
	private PropertyDict orgLevel;
	private String departMentNo;

	public String getDepartMentNo() {
		return departMentNo;
	}

	public void setDepartMentNo(String departMentNo) {
		this.departMentNo = departMentNo;
	}

	public PropertyDict getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(PropertyDict orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public boolean isYuhangUser() {
		return yuhangUser;
	}

	public void setYuhangUser(boolean yuhangUser) {
		this.yuhangUser = yuhangUser;
	}

	public String getOrgFullPyForTown() {
		return orgFullPyForTown;
	}

	public void setOrgFullPyForTown(String orgFullPyForTown) {
		this.orgFullPyForTown = orgFullPyForTown;
	}

}
