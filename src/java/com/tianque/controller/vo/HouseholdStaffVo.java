package com.tianque.controller.vo;

import java.util.Date;

import com.tianque.core.util.DateUtil;
import com.tianque.domain.Organization;

/**
 * 户籍人员的查询vo
 * 
 * @author T26
 */
public class HouseholdStaffVo {
	/** 所属责任区 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	private String idStr;
	private String name;
	private String idCardNo;
	private Long gender;
	private String telephone;
	private String mobileNumber;
	private Long schooling;
	private String province;
	private String city;
	private String district;
	private String currentAddress;
	private String nativePlaceAddress;
	private Long logout;
	private Long isDeath;
	private Long outGone;
	private String birthday;
	private String endBirthday;
	private String accountNumber;
	private String address;
	private String otherAddress;
	private String formerName;
	private Long politicalBackground;
	private Long maritalState;
	private Long residenceType;
	private Long nation;
	private Long faith;
	private Long healthState;
	private Long bloodType;
	private String email;
	private String school;
	private Long career;
	private Long residentStatus;
	private String workUnit;
	private String usedName;
	private String fastSearchKeyWords;// 姓名、身份证
	/** 是否外出 **/
	private Long outGoneBoolean;
	/** 外出原因 **/
	private Long outReasonsId;
	/** 外出时间 起 **/
	private Date reasonsDateStart;
	/** 外出时间 止 **/
	private Date reasonsDateEnd;
	/** 外出去向 **/
	private String outProvince;
	private String outCity;
	private String outDistrict;

	private Long logOut;

	/** 注销时间 起 **/
	private Date logoutDateStart;
	/** 注销时间 止 **/
	private Date logoutDateEnd;

	private String logoutReason;

	private String conditionType;// 条件类型(快速检索)

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

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

	public Long getSchooling() {
		return schooling;
	}

	public void setSchooling(Long schooling) {
		this.schooling = schooling;
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

	public String getUsedName() {
		return usedName;
	}

	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Long getLogout() {
		return logout;
	}

	public void setLogout(Long logout) {
		this.logout = logout;
	}

	public Long getIsDeath() {
		return isDeath;
	}

	public void setIsDeath(Long isDeath) {
		this.isDeath = isDeath;
	}

	public Long getOutGone() {
		return outGone;
	}

	public void setOutGone(Long outGone) {
		this.outGone = outGone;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEndBirthday() {
		return endBirthday;
	}

	public void setEndBirthday(String endBirthday) {
		this.endBirthday = endBirthday;
	}

	public String getOtherAddress() {
		return otherAddress;
	}

	public void setOtherAddress(String otherAddress) {
		this.otherAddress = otherAddress;
	}

	public String getFormerName() {
		return formerName;
	}

	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}

	public Long getPoliticalBackground() {
		return politicalBackground;
	}

	public void setPoliticalBackground(Long politicalBackground) {
		this.politicalBackground = politicalBackground;
	}

	public Long getMaritalState() {
		return maritalState;
	}

	public void setMaritalState(Long maritalState) {
		this.maritalState = maritalState;
	}

	public Long getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(Long residenceType) {
		this.residenceType = residenceType;
	}

	public Long getNation() {
		return nation;
	}

	public void setNation(Long nation) {
		this.nation = nation;
	}

	public Long getFaith() {
		return faith;
	}

	public void setFaith(Long faith) {
		this.faith = faith;
	}

	public Long getHealthState() {
		return healthState;
	}

	public void setHealthState(Long healthState) {
		this.healthState = healthState;
	}

	public Long getBloodType() {
		return bloodType;
	}

	public void setBloodType(Long bloodType) {
		this.bloodType = bloodType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCareer() {
		return career;
	}

	public void setCareer(Long career) {
		this.career = career;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	public Long getResidentStatus() {
		return residentStatus;
	}

	public void setResidentStatus(Long residentStatus) {
		this.residentStatus = residentStatus;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

	public Long getOutReasonsId() {
		return outReasonsId;
	}

	public void setOutReasonsId(Long outReasonsId) {
		this.outReasonsId = outReasonsId;
	}

	public Date getReasonsDateStart() {
		return reasonsDateStart;
	}

	public void setReasonsDateStart(Date reasonsDateStart) {
		this.reasonsDateStart = reasonsDateStart;
	}

	public Date getReasonsDateEnd() {
		return reasonsDateEnd;
	}

	public void setReasonsDateEnd(Date reasonsDateEnd) {
		this.reasonsDateEnd = reasonsDateEnd;
	}

	public String getOutProvince() {
		return outProvince;
	}

	public void setOutProvince(String outProvince) {
		this.outProvince = outProvince;
	}

	public String getOutCity() {
		return outCity;
	}

	public void setOutCity(String outCity) {
		this.outCity = outCity;
	}

	public String getOutDistrict() {
		return outDistrict;
	}

	public void setOutDistrict(String outDistrict) {
		this.outDistrict = outDistrict;
	}

	public Long getLogOut() {
		return logOut;
	}

	public void setLogOut(Long logOut) {
		this.logOut = logOut;
	}

	public Date getLogoutDateStart() {
		return logoutDateStart;
	}

	public void setLogoutDateStart(Date logoutDateStart) {
		this.logoutDateStart = logoutDateStart;
	}

	public Date getLogoutDateEnd() {
		return logoutDateEnd;
	}

	public void setLogoutDateEnd(Date logoutDateEnd) {
		this.logoutDateEnd = DateUtil.getEndTime(logoutDateEnd);
	}

	public String getLogoutReason() {
		return logoutReason;
	}

	public void setLogoutReason(String logoutReason) {
		this.logoutReason = logoutReason;
	}

	public void setOutGoneBoolean(Long outGoneBoolean) {
		this.outGoneBoolean = outGoneBoolean;
	}

	public Long getOutGoneBoolean() {
		return outGoneBoolean;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

}
