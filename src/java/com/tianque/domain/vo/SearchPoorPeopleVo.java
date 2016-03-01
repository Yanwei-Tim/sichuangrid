package com.tianque.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class SearchPoorPeopleVo extends BaseDomain implements Serializable {

	/** 所属责任区 */
	private Organization organization;
	/*** 姓名 */
	private String name;
	/*** 身份证号 */
	private String idCardNo;
	/*** 性别 */
	private PropertyDict gender;
	/*** 现在居住地 */
	private String currentlyAddress;
	/*** 固定电话 */
	private String telephone;
	/*** 是否低保户 */
	private Boolean isLowHousehold;
	/*** 困难原因 */
	private PropertyDict poorSource;
	/** 所属责任区编号 */
	private String orgInternalCode;
	private Date birthdayStart;
	private Date birthdayEnd;
	private Long isEmphasis;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getCurrentlyAddress() {
		return currentlyAddress;
	}

	public void setCurrentlyAddress(String currentlyAddress) {
		this.currentlyAddress = currentlyAddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Boolean getIsLowHousehold() {
		return isLowHousehold;
	}

	public void setIsLowHousehold(Boolean isLowHousehold) {
		this.isLowHousehold = isLowHousehold;
	}

	public PropertyDict getPoorSource() {
		return poorSource;
	}

	public void setPoorSource(PropertyDict poorSource) {
		this.poorSource = poorSource;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Date getBirthdayStart() {
		return birthdayStart;
	}

	public void setBirthdayStart(Date birthdayStart) {
		this.birthdayStart = birthdayStart;
	}

	public Date getBirthdayEnd() {
		return birthdayEnd;
	}

	public void setBirthdayEnd(Date birthdayEnd) {
		this.birthdayEnd = birthdayEnd;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

}
