package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class BasePersonnel extends BaseDomain {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/** 所属责任区 */
	private Organization organization;
	/** 吸毒员姓名 */
	private String name;
	/** 身份证号 */
	private String idCardNo;
	/** 性别 */
	private PropertyDict gender;
	/** 出生日期 */
	private Date birthday;

	public BasePersonnel() {
		super();
	}

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

	@JSON(format = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}