package com.tianque.userAccountLoginDetail.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

public class UserAccountLoginDetail extends BaseDomain{

	private Organization organization;
	private Long userId;
	private String userName;
	private String name;
	private Integer weekCount;
	private Integer monthCount;
	private Date activationTime;
	private Date lastLoginTime;//最后登录时间
	private Integer pcUsable;//PC是否可登录
	private Integer mobileUsable;//手机是否可登录
	
	public Integer getPcUsable() {
		return pcUsable;
	}
	public void setPcUsable(Integer pcUsable) {
		this.pcUsable = pcUsable;
	}
	public Integer getMobileUsable() {
		return mobileUsable;
	}
	public void setMobileUsable(Integer mobileUsable) {
		this.mobileUsable = mobileUsable;
	}
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getActivationTime() {
		return activationTime;
	}
	public void setActivationTime(Date activationTime) {
		this.activationTime = activationTime;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getWeekCount() {
		return weekCount;
	}
	public void setWeekCount(Integer weekCount) {
		this.weekCount = weekCount;
	}
	public Integer getMonthCount() {
		return monthCount;
	}
	public void setMonthCount(Integer monthCount) {
		this.monthCount = monthCount;
	}
	
	
}
