package com.tianque.working.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;

public class DailyYear extends BaseDomain {

	private String name;
	private String remark;
	private Organization makedOrganization;
	private String makedOrgInternalCode;
	/**
	 * 年度时间
	 */
	private Integer yearDate;
	/**
	 * 年度工作目录使用状态
	 */
	private Long status;
	/**
	 * 年度工作目录名称简拼
	 */
	private String simplePinyin;
	/**
	 * 年度工作目录名称全拼
	 */
	private String fullPinyin;
	/**
	 * 台帐启动提醒时间
	 */
	private Date reminderDate;
	/**
	 * 是否自动启动帐号
	 */
	private Long whetherAutoStart;

	private String serviceManagement;
	private String checkName;
	private String issueDealName;

	private String managementSubmit;
	private String checkNameSubmit;
	private String issueDealNameSubmit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Organization getMakedOrganization() {
		return makedOrganization;
	}

	public void setMakedOrganization(Organization makedOrganization) {
		this.makedOrganization = makedOrganization;
	}

	public String getMakedOrgInternalCode() {
		return makedOrgInternalCode;
	}

	public void setMakedOrgInternalCode(String makedOrgInternalCode) {
		this.makedOrgInternalCode = makedOrgInternalCode;
	}

	public Integer getYearDate() {
		return yearDate;
	}

	public void setYearDate(Integer yearDate) {
		this.yearDate = yearDate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public Date getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(Date reminderDate) {
		this.reminderDate = reminderDate;
	}

	public Long getWhetherAutoStart() {
		return whetherAutoStart;
	}

	public void setWhetherAutoStart(Long whetherAutoStart) {
		this.whetherAutoStart = whetherAutoStart;
	}

	public String getServiceManagement() {
		return serviceManagement;
	}

	public void setServiceManagement(String serviceManagement) {
		this.serviceManagement = serviceManagement;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getIssueDealName() {
		return issueDealName;
	}

	public void setIssueDealName(String issueDealName) {
		this.issueDealName = issueDealName;
	}

	public String getManagementSubmit() {
		return managementSubmit;
	}

	public void setManagementSubmit(String managementSubmit) {
		this.managementSubmit = managementSubmit;
	}

	public String getCheckNameSubmit() {
		return checkNameSubmit;
	}

	public void setCheckNameSubmit(String checkNameSubmit) {
		this.checkNameSubmit = checkNameSubmit;
	}

	public String getIssueDealNameSubmit() {
		return issueDealNameSubmit;
	}

	public void setIssueDealNameSubmit(String issueDealNameSubmit) {
		this.issueDealNameSubmit = issueDealNameSubmit;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.makedOrgInternalCode, null);
	}

}
