package com.tianque.supervisorManage.supervisorInfo;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class SupervisorInfoVo {

	private Long id;// 新增，点击“下一步”，监管人员页签接收当前新增人员的id

	private Long memberId;// 服务人员Id

	private Long teamId;// 组织Id

	private Long populationId;// 服务对象Id

	private Long locationId;// 场所Id

	private String idCardNo;

	private String name;// 人员名字

	private PropertyDict gender;

	private String mobile;

	private PropertyDict position;

	private PropertyDict teamClazz;// 组织大类

	private PropertyDict teamType;// 组织类别

	private String teamName;// 组织名称

	private String populationType;// 人口类型

	private String locationType;// 场所类型

	private String orgInternalCode;// 服务人员的orgInternalCode

	private String homePhone;

	private String duties;// 服务人员的职位
	/** 所属网格 */
	private Organization organization;

	private String memberPosition;

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

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PropertyDict getTeamClazz() {
		return teamClazz;
	}

	public void setTeamClazz(PropertyDict teamClazz) {
		this.teamClazz = teamClazz;
	}

	public void setMemberPosition(String memberPosition) {
		this.memberPosition = memberPosition;
	}

	public String getMemberPosition() {
		return memberPosition;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
}
