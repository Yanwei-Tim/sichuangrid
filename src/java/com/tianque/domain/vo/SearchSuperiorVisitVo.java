package com.tianque.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class SearchSuperiorVisitVo extends BaseDomain implements Serializable {
	/** 所属责任区 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 身份证 */
	private String idCardNo;
	/** 姓名 */
	private String name;
	/** 手机号码 */
	private String mobileNumber;
	private String telephone;
	/** 性别 */
	private PropertyDict gender;
	/** 出生日期 */
	private Date birthdayStart;
	private Date birthdayEnd;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 区县 */
	private String district;
	/** 是否关注 */
	private Long isEmphasis;

	/** 户籍地详址 */
	private String nativePlaceAddress;
	/** 现在居住地 */
	private String currentAddress;
	private String visitReason;
	private PropertyDict visitState;
	private Long visitType;

	private PropertyDict bloodType;
	private PropertyDict nation;
	private PropertyDict politicalBackground;
	private PropertyDict maritalState;
	private PropertyDict schooling;
	private PropertyDict career;
	private String workUnit;
	private Integer minStature;
	private Integer maxStature;
	private PropertyDict faith;
	private String email;
	private String fastSearchKeyWords;// 姓名、身份证
	private Long isDeath;// 死亡状态
	/** 是否有服务成员 */
	private Long hasServiceTeamMember;
	/** 是否有服务记录 */
	private Long hasServiceTeamRecord;

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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
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

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getVisitReason() {
		return visitReason;
	}

	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public PropertyDict getCareer() {
		return career;
	}

	public void setCareer(PropertyDict career) {
		this.career = career;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public PropertyDict getVisitState() {
		return visitState;
	}

	public void setVisitState(PropertyDict visitState) {
		this.visitState = visitState;
	}

	public Long getVisitType() {
		return visitType;
	}

	public void setVisitType(Long visitType) {
		this.visitType = visitType;
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public PropertyDict getBloodType() {
		return bloodType;
	}

	public void setBloodType(PropertyDict bloodType) {
		this.bloodType = bloodType;
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

	public Integer getMinStature() {
		return minStature;
	}

	public void setMinStature(Integer minStature) {
		this.minStature = minStature;
	}

	public Integer getMaxStature() {
		return maxStature;
	}

	public void setMaxStature(Integer maxStature) {
		this.maxStature = maxStature;
	}

	public PropertyDict getFaith() {
		return faith;
	}

	public void setFaith(PropertyDict faith) {
		this.faith = faith;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdayStart() {
		return birthdayStart;
	}

	public void setBirthdayStart(Date birthdayStart) {
		this.birthdayStart = birthdayStart;
	}

	public Date getBirthdayEnd() {
		return birthdayEnd;
	}

	public void setBirthdayEnd(Date birthdayEnd) {
		this.birthdayEnd = birthdayEnd;
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

	public void setIsDeath(Long isDeath) {
		this.isDeath = isDeath;
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

	public String getAttentionPopulationType() {
		return BaseInfoTables.SUPERIORVISIT_KEY;
	}
}
