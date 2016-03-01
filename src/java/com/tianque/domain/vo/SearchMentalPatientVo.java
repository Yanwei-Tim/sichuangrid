package com.tianque.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.search.vo.QuickFilterType;

@SuppressWarnings("serial")
public class SearchMentalPatientVo extends BaseDomain implements Serializable {
	/** 身份证号 */
	private String idCardNo;
	/** 姓名 */
	private String name;
	/** 开始出生日期 */
	private Date startBirthday;
	/** 结束出生日期 */
	private Date endBirthday;
	/** 性别 */
	private Long genderId;
	/** 发病危险等级 */
	private Long dangerLevelId;
	/** 是否接受过精神病治疗 */
	private Boolean isTreat;
	private QuickFilterType hasIsTreatType;
	/** 康复机构 */
	private String cureDepartment;
	/** 省 */
	private String nativeProvince;
	/** 市 */
	private String nativeCity;
	/** 区县 */
	private String nativeDistrict;
	/** 户籍地详址 */
	private String nativePlaceAddress;
	/** 家庭住址 */
	private String currentAddress;
	/** 民族 */
	private PropertyDict nation;
	/** 政治面貌 */
	private PropertyDict politicalBackground;
	/** 婚姻状况 */
	private PropertyDict maritalState;
	/** 文化程度 */
	private PropertyDict schooling;
	/** 职业 */
	private PropertyDict career;
	/** 工作单位 */
	private String workUnit;
	private Long startStature;
	private Long endStature;
	/** 宗教信仰 */
	private PropertyDict faith;
	/** 血型 */
	private PropertyDict bloodType;
	private String fastSearchKeyWords;// 姓名、身份证
	private Long isDeath;// 死亡状态
	/** 是否有服务成员 */
	private Long hasServiceTeamMember;
	/** 是否有服务记录 */
	private Long hasServiceTeamRecord;
	/** 精神病类型 */
	private Long psychosisTypeId;

	public Long getPsychosisTypeId() {
		return psychosisTypeId;
	}

	public void setPsychosisTypeId(Long psychosisTypeId) {
		this.psychosisTypeId = psychosisTypeId;
	}

	public String getCureDepartment() {
		return cureDepartment;
	}

	public void setCureDepartment(String cureDepartment) {
		this.cureDepartment = cureDepartment;
	}

	public PropertyDict getNation() {
		return nation;
	}

	public void setNation(PropertyDict nation) {
		this.nation = nation;
	}

	public PropertyDict getPoliticalBackground() {
		return politicalBackground;
	}

	public void setPoliticalBackground(PropertyDict politicalBackground) {
		this.politicalBackground = politicalBackground;
	}

	public PropertyDict getMaritalState() {
		return maritalState;
	}

	public void setMaritalState(PropertyDict maritalState) {
		this.maritalState = maritalState;
	}

	public PropertyDict getSchooling() {
		return schooling;
	}

	public void setSchooling(PropertyDict schooling) {
		this.schooling = schooling;
	}

	public PropertyDict getCareer() {
		return career;
	}

	public void setCareer(PropertyDict career) {
		this.career = career;
	}

	public Long getStartStature() {
		return startStature;
	}

	public void setStartStature(Long startStature) {
		this.startStature = startStature;
	}

	public Long getEndStature() {
		return endStature;
	}

	public void setEndStature(Long endStature) {
		this.endStature = endStature;
	}

	public PropertyDict getFaith() {
		return faith;
	}

	public void setFaith(PropertyDict faith) {
		this.faith = faith;
	}

	public PropertyDict getBloodType() {
		return bloodType;
	}

	public void setBloodType(PropertyDict bloodType) {
		this.bloodType = bloodType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	private String email;
	/** 固定电话 */
	private String telephone;
	/** 手机号码 */
	private String mobileNumber;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 所属责任区 */
	private Organization organization;
	private Long isEmphasis;

	public Long getGenderId() {
		return genderId;
	}

	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}

	public Long getDangerLevelId() {
		return dangerLevelId;
	}

	public void setDangerLevelId(Long dangerLevelId) {
		this.dangerLevelId = dangerLevelId;
	}

	public Boolean getIsTreat() {
		return isTreat;
	}

	public void setIsTreat(Boolean isTreat) {
		this.isTreat = isTreat;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
	}

	public String getNativeProvince() {
		return nativeProvince;
	}

	public void setNativeProvince(String nativeProvince) {
		this.nativeProvince = nativeProvince;
	}

	public String getNativeCity() {
		return nativeCity;
	}

	public void setNativeCity(String nativeCity) {
		this.nativeCity = nativeCity;
	}

	public String getNativeDistrict() {
		return nativeDistrict;
	}

	public void setNativeDistrict(String nativeDistrict) {
		this.nativeDistrict = nativeDistrict;
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

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Date getStartBirthday() {
		return startBirthday;
	}

	public void setStartBirthday(Date startBirthday) {
		this.startBirthday = startBirthday;
	}

	public Date getEndBirthday() {
		return endBirthday;
	}

	public void setEndBirthday(Date endBirthday) {
		this.endBirthday = endBirthday;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public QuickFilterType getHasIsTreatType() {
		return hasIsTreatType;
	}

	public void setHasIsTreatType(QuickFilterType hasIsTreatType) {
		this.hasIsTreatType = hasIsTreatType;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

	public Long getIsDeath() {
		return isDeath;
	}

	public Long getHasServiceTeamMember() {
		return hasServiceTeamMember;
	}

	public void setHasServiceTeamMember(Long hasServiceTeamMember) {
		this.hasServiceTeamMember = hasServiceTeamMember;
	}

	public Long getHasServiceTeamRecord() {
		return hasServiceTeamRecord;
	}

	public void setHasServiceTeamRecord(Long hasServiceTeamRecord) {
		this.hasServiceTeamRecord = hasServiceTeamRecord;
	}

	public void setIsDeath(Long isDeath) {
		this.isDeath = isDeath;
	}

	public String getAttentionPopulationType() {
		return BaseInfoTables.MENTALPATIENT_KEY;
	}
}
