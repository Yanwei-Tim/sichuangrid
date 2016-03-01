package com.tianque.domain.vo;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.domain.Organization;

@SuppressWarnings("serial")
public class AssessmentUserVo implements Serializable {
	private Long id;
	private String userName;
	private String name;
	private String password;
	private String mobile;
	private boolean isLock = false;

	private boolean isAdmin = false;
	private boolean changePassword;
	private boolean hasNewMessage;
	private String fullPinyin;
	private String simplePinyin;

	private Long credits1 = 0L;
	private Long credits2 = 0L;
	private String email;
	private Date lastLoginTime;
	private String lastLoginIp;
	private Date previousLoginTime;
	private String previousLoginIp;

	private Organization organization;

	private String workCompany;
	private String workPhone;
	private String homePhone;
	private String orgInternalCode;
	private Integer failureTimes = 0;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	private boolean assessmented;

	public boolean isAssessmented() {
		return assessmented;
	}

	public void setAssessmented(boolean assessmented) {
		this.assessmented = assessmented;
	}

	public boolean getHasNewMessage() {
		return hasNewMessage;
	}

	public void setHasNewMessage(boolean hasNewMessage) {
		this.hasNewMessage = hasNewMessage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isChangePassword() {
		return changePassword;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JSON(serialize = false, deserialize = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullPinyin() {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			return fullPinyin.substring(0, 30);
		}
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			return simplePinyin.substring(0, 10);
		}
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWorkCompany() {
		return workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@JSON(serialize = false, deserialize = false)
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public boolean isLock() {
		return isLock;
	}

	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Long getCredits1() {
		return credits1;
	}

	public void setCredits1(Long credits1) {
		this.credits1 = credits1;
	}

	public Long getCredits2() {
		return credits2;
	}

	public void setCredits2(Long credits2) {
		this.credits2 = credits2;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getPreviousLoginTime() {
		return previousLoginTime;
	}

	public void setPreviousLoginTime(Date previousLoginTime) {
		this.previousLoginTime = previousLoginTime;
	}

	public String getPreviousLoginIp() {
		return previousLoginIp;
	}

	public void setPreviousLoginIp(String previousLoginIp) {
		this.previousLoginIp = previousLoginIp;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Integer getFailureTimes() {
		return failureTimes;
	}

	public void setFailureTimes(Integer failureTimes) {
		this.failureTimes = failureTimes;
	}

}
