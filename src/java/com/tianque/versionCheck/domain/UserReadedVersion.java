package com.tianque.versionCheck.domain;

public class UserReadedVersion {
	public UserReadedVersion(Long userId, String version) {
		super();
		this.userId = userId;
		this.version = version;
	}

	public UserReadedVersion() {

	}

	private Long userId;// 用户编号

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	private String version;// 版本号

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
