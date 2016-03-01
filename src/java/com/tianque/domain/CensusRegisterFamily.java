package com.tianque.domain;

import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.StringUtil;

/**
 * 户籍家庭
 */
@SuppressWarnings("serial")
public class CensusRegisterFamily extends BaseDomain {

	/** 所属网格 **/
	private Organization organization;
	/** 户口号 **/
	private String accountNumber;
	/** 户主身份证号 **/
	private String idCardNo;
	/** 户主姓名 **/
	private String householdName;
	/** 家庭电话 **/
	private String homePhone;
	/** 省 **/
	private String province;
	/** 市 **/
	private String city;
	/** 区县 **/
	private String district;
	/** 手机号码 **/
	private String mobileNumber;
	/** 固定电话 **/
	private String telePhone;
	/** 常住地址 **/
	private String currentAddress;
	/** 所属网格编号 **/
	private String orgInternalCode;
	/** 家庭称号 **/
	private List<PropertyDict> houseMarchList;

	public List<PropertyDict> getHouseMarchList() {
		return houseMarchList;
	}

	public void setHouseMarchList(List<PropertyDict> houseMarchList) {
		this.houseMarchList = houseMarchList;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getHouseholdName() {
		return householdName;
	}

	public void setHouseholdName(String householdName) {
		this.householdName = householdName;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getEncryptId() {
		String orgCode = this.orgInternalCode;
		if(!StringUtil.isStringAvaliable(orgCode) && organization != null){
			orgCode = this.organization.getOrgInternalCode();
		}
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), orgCode,null);
	}
}
