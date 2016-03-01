package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

public class TeamMember extends BaseDomain {

	private String name;

	private String idCardNo;

	private String phone;

	private String skillDesc;

	private String mobile;

	/**
	 * 职位
	 */
	private String teamPosition;

	private String workCompany;

	private String workPosition;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSkillDesc() {
		return skillDesc;
	}

	public void setSkillDesc(String skillDesc) {
		this.skillDesc = skillDesc;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTeamPosition() {
		return teamPosition;
	}

	public void setTeamPosition(String teamPosition) {
		this.teamPosition = teamPosition;
	}

	public String getWorkCompany() {
		return workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}

	public String getWorkPosition() {
		return workPosition;
	}

	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}

}
