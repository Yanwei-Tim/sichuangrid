package com.tianque.baseInfo.rectificativePerson.vo;

import java.util.Date;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class SupervisorsOfrectificativeVo {
	private Long memberId;// 服务人员Id
	private Long teamId;// 组织Id
	private Long populationId;// 服务对象Id
	private String idCardNo;
	private String name;// 人员名字
	private PropertyDict gender;
	private String mobile;
	private PropertyDict position;
	private PropertyDict teamType;// 组织类别
	private PropertyDict teamClazz;;// 组织大类
	private String teamName;// 组织名称
	private String populationType;// 人口名称
	private Long isEmphasis;
	private String orgInternalCode;
	private Date birthday;
	private String homePhone;
	private String presentAddress;
	private String divisionDuties;
	private String duties;
	/** 所属网格 */
	private Organization organization;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public PropertyDict getPosition() {
		return position;
	}

	public void setPosition(PropertyDict position) {
		this.position = position;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public PropertyDict getTeamType() {
		return teamType;
	}

	public void setTeamType(PropertyDict teamType) {
		this.teamType = teamType;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public PropertyDict getTeamClazz() {
		return teamClazz;
	}

	public void setTeamClazz(PropertyDict teamClazz) {
		this.teamClazz = teamClazz;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getDivisionDuties() {
		return divisionDuties;
	}

	public void setDivisionDuties(String divisionDuties) {
		this.divisionDuties = divisionDuties;
	}

	public String getDuties() {
		return duties;
	}

	public void setDuties(String duties) {
		this.duties = duties;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getPopulationId() {
		return populationId;
	}

	public void setPopulationId(Long populationId) {
		this.populationId = populationId;
	}

}
