package com.tianque.plugin.serviceTeam.serviceTeamMember.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class ServiceTeamMemberBase extends BaseDomain {
	/** 姓名 **/
	private String name;
	/** 性别 **/
	private PropertyDict gender;
	/** 手机 **/
	private String mobile;
	/** 家庭电话 **/
	private String homePhone;
	/** 职位 **/
	private String job;
	/** 区域id **/
	private String orgid;
	/** 区域编码 **/
	private String orgInternalCode;
	/** 备注 **/
	private String remark;
	/** 名称简拼 **/
	private String simplePinyin;
	/** 名称全拼 **/
	private String fullPinyin;
	/** 组织机构 **/
	private Organization org;
	/** 出生日期 **/
	private String birthday;

	public ServiceTeamMemberBase() {
		super();
	}

	public ServiceTeamMemberBase(Long id) {
		super.setId(id);
	}

	public String getName() {
		return name;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public String getMobile() {
		return mobile;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}