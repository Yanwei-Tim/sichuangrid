package com.tianque.xichang.working.steadyWork.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

public class PeopleInfo extends BaseDomain {
	/** 姓名(NAME) */
	private String name;
	/** 身份证(IDCARDNO) */
	private String idCardNo;
	/** 性别(GENDER) */
	private PropertyDict gender;
	/** 联系电话(MOBILENUMBER) */
	private String mobileNumber;
	/** 出生年月(BIRTHDAY) */
	private Date birthDay;
	/** 是否党员，0否 1是(ISPARTYMEMBER) */
	private Boolean isPartyMember;
	/** 常住地址(PERMANENTADDRESS) */
	private String permanentAddress;
	/** 职业或身份(POSITION) */
	private PropertyDict position;

	private Long steadyWorkId;

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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Boolean getIsPartyMember() {
		return isPartyMember;
	}

	public void setIsPartyMember(Boolean isPartyMember) {
		this.isPartyMember = isPartyMember;
	}

	public PropertyDict getPosition() {
		return position;
	}

	public void setPosition(PropertyDict position) {
		this.position = position;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Long getSteadyWorkId() {
		return steadyWorkId;
	}

	public void setSteadyWorkId(Long steadyWorkId) {
		this.steadyWorkId = steadyWorkId;
	}
}
