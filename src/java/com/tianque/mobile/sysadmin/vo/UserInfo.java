package com.tianque.mobile.sysadmin.vo;

import com.tianque.domain.User;

public class UserInfo extends User {
	private String update_mode;
	private String project_area;

	public String getUpdate_mode() {
		return update_mode;
	}

	public void setUpdate_mode(String updateMode) {
		update_mode = updateMode;
	}

	public String getProject_area() {
		return project_area;
	}

	public void setProject_area(String projectArea) {
		project_area = projectArea;
	}

}
