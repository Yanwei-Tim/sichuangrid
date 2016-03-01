package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

@SuppressWarnings("serial")
public class ServicePerson extends BaseDomain {
	/**
	 * 所属责任区
	 */
	private Organization organization;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 职务
	 */
	private String position;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 所属责任区编号
	 */
	private String orgInternalCode;
	/**
	 * 全拼音
	 */
	private String fullPinyin;
	/**
	 * 姓名简拼
	 */
	private String simplePinyin;

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

}
