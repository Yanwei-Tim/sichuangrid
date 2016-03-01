package com.tianque.search.vo;

import com.tianque.core.util.StringUtil;

public class ActualPopulationSearchCondition {
	private QuickFilterType deathStatus = QuickFilterType.LIVING;
	private QuickFilterType registeStatus = QuickFilterType.REGISTED;

	private Long rootOrgId;

	private String certificationNumber;

	private String name;

	private Integer gender;
	/*** 户口号 */
	private String accountNumber;

	public QuickFilterType getDeathStatus() {
		return deathStatus;
	}

	public void setDeathStatus(QuickFilterType deathStatus) {
		this.deathStatus = deathStatus;
	}

	public QuickFilterType getRegisteStatus() {
		return registeStatus;
	}

	public void setRegisteStatus(QuickFilterType registeStatus) {
		this.registeStatus = registeStatus;
	}

	public Long getRootOrgId() {
		return rootOrgId;
	}

	public void setRootOrgId(Long rootOrgId) {
		this.rootOrgId = rootOrgId;
	}

	public String getCertificationNumber() {
		return certificationNumber;
	}

	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public boolean isEmptyCondition() {
		return !StringUtil.isStringAvaliable(getName())
				&& !StringUtil.isStringAvaliable(getCertificationNumber());
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
