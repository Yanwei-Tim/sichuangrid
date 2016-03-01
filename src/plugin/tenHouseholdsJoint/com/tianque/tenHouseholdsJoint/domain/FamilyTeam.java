package com.tianque.tenHouseholdsJoint.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class FamilyTeam extends BaseDomain {

	private String teamCode;//编码
	private String teamName;//名称
	private Integer houseHolds;//户数
	private String houseMaster;//联户长
	private String houseMastCertificateNo;//联户长证件号
	private PropertyDict alarmCenter;//接警中心
	private Organization organization;//组织机构
	
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public String getTeamCode() {
		return teamCode;
	}
	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Integer getHouseHolds() {
		return houseHolds;
	}
	public void setHouseHolds(Integer houseHolds) {
		this.houseHolds = houseHolds;
	}
	public String getHouseMaster() {
		return houseMaster;
	}
	public void setHouseMaster(String houseMaster) {
		this.houseMaster = houseMaster;
	}
	public String getHouseMastCertificateNo() {
		return houseMastCertificateNo;
	}
	public void setHouseMastCertificateNo(String houseMastCertificateNo) {
		this.houseMastCertificateNo = houseMastCertificateNo;
	}
	public PropertyDict getAlarmCenter() {
		return alarmCenter;
	}
	public void setAlarmCenter(PropertyDict alarmCenter) {
		this.alarmCenter = alarmCenter;
	}
	
	
}
