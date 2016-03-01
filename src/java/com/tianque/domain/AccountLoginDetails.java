package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class AccountLoginDetails extends BaseDomain {

	/** 组织机构信息 */
	private Organization organization;
	/** 用户名 */
	private String userName;
	/** 用户中文名 */
	private String name;
	/** 每月工作天数 */
	private Integer currentWorkDay;
	/** 每月登录天数 */
	private Integer loginDay;
	/** 账号最后登录时间 */
	private Date lastLoginTime;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCurrentWorkDay() {
		return currentWorkDay;
	}

	public void setCurrentWorkDay(Integer currentWorkDay) {
		this.currentWorkDay = currentWorkDay;
	}

	public Integer getLoginDay() {
		return loginDay;
	}

	public void setLoginDay(Integer loginDay) {
		this.loginDay = loginDay;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}
