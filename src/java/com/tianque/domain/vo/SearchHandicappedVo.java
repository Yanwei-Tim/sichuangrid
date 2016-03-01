package com.tianque.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.search.vo.QuickFilterType;

/**
 * 残疾人查询类
 * 
 * @author Administrator
 */
@SuppressWarnings("serial")
public class SearchHandicappedVo extends BaseDomain implements Serializable {
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 身份证ID */
	private String idCardNo;
	/** 姓名 */
	private String name;
	/** 出生日期 开始 */
	private Date birthdayStart;
	/** 出生日期 结束 */
	private Date birthdayEnd;
	/** 性别 */
	private Long genderId;
	/** 残疾时间 开始 */
	private Date disabilityDateStart;
	/** 残疾时间 结束 */
	private Date disabilityDateEnd;
	/** 残疾原因 */
	private String disabilityReason;
	/** 残疾类别 */
	private Long disabilityType;
	/** 残疾状况 */
	private Long disabilityId;
	/** 残疾证持有 */
	private Boolean hasDisabilityCard;

	private QuickFilterType hasDisabilityCardType;
	/** 残疾证号 */
	private String disabilityCardNo;
	/** 技能特长 */
	private Long skillProfileId;
	/** 劳动能力 */
	private Long workProfileId;
	/** 户籍地 */
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 区县 */
	private String district;
	/** 户籍地详细地址 */
	private String nativePlaceAddress;
	/** 常住地址 */
	private String currentAddress;

	/** 民族 */
	private Long nation;
	/** 政治面貌 */
	private Long politicalBackground;
	/** 婚姻状况 */
	private Long maritalState;
	/** 文化程度 */
	private Long schooling;
	/** 职业 */
	private Long career;
	/** 工作单位 */
	private String workUnit;
	/** 最低身高 */
	private Integer minStature;
	/** 最高身高 */
	private Integer maxStature;
	/** 血型 */
	private Long bloodType;
	/** 信仰 */
	private Long faith;
	/** 邮箱 */
	private String email;
	/** 户口类别 */
	private Long residenceType;
	/** 是否死亡 */
	private Long isDeath;
	/** 固定电话 */
	private String telephone;
	/** 手机号码 */
	private String mobileNumber;

	/** 是否注销 */
	private Long isEmphasis;
	/** 姓名，身份证号码 */
	private String fastSearchKeyWords;
	/** 是否有服务成员 */
	private Long hasServiceTeamMember;
	/** 是否有服务记录 */
	private Long hasServiceTeamRecord;

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
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

	public Date getDisabilityDateStart() {
		return disabilityDateStart;
	}

	public void setDisabilityDateStart(Date disabilityDateStart) {
		this.disabilityDateStart = disabilityDateStart;
	}

	public Date getDisabilityDateEnd() {
		return disabilityDateEnd;
	}

	public void setDisabilityDateEnd(Date disabilityDateEnd) {
		this.disabilityDateEnd = disabilityDateEnd;
	}

	public String getDisabilityReason() {
		return disabilityReason;
	}

	public void setDisabilityReason(String disabilityReason) {
		this.disabilityReason = disabilityReason;
	}

	public Long getDisabilityType() {
		return disabilityType;
	}

	public void setDisabilityType(Long disabilityType) {
		this.disabilityType = disabilityType;
	}

	public Boolean getHasDisabilityCard() {
		if (hasDisabilityCardType != null) {
			return hasDisabilityCardType.toBoolean();
		} else
			return null;
	}

	public void setHasDisabilityCard(Boolean hasDisabilityCard) {
		if (hasDisabilityCardType != null) {
			this.hasDisabilityCard = hasDisabilityCardType.toBoolean();
		}
	}

	public QuickFilterType getHasDisabilityCardType() {
		return hasDisabilityCardType;
	}

	public void setHasDisabilityCardType(QuickFilterType hasDisabilityCardType) {
		this.hasDisabilityCardType = hasDisabilityCardType;
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

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public Long getNation() {
		return nation;
	}

	public void setNation(Long nation) {
		this.nation = nation;
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

	public Long getSchooling() {
		return schooling;
	}

	public void setSchooling(Long schooling) {
		this.schooling = schooling;
	}

	public Long getCareer() {
		return career;
	}

	public void setCareer(Long career) {
		this.career = career;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
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

	public Long getBloodType() {
		return bloodType;
	}

	public void setBloodType(Long bloodType) {
		this.bloodType = bloodType;
	}

	public Long getFaith() {
		return faith;
	}

	public void setFaith(Long faith) {
		this.faith = faith;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(Long residenceType) {
		this.residenceType = residenceType;
	}

	public Long getIsDeath() {
		return isDeath;
	}

	public void setIsDeath(Long isDeath) {
		this.isDeath = isDeath;
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

	public String getDisabilityCardNo() {
		return disabilityCardNo;
	}

	public void setDisabilityCardNo(String disabilityCardNo) {
		this.disabilityCardNo = disabilityCardNo;
	}

	public Long getGenderId() {
		return genderId;
	}

	public void setGenderId(Long genderId) {
		this.genderId = genderId;
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

	public Long getDisabilityId() {
		return disabilityId;
	}

	public void setDisabilityId(Long disabilityId) {
		this.disabilityId = disabilityId;
	}

	public Long getSkillProfileId() {
		return skillProfileId;
	}

	public void setSkillProfileId(Long skillProfileId) {
		this.skillProfileId = skillProfileId;
	}

	public Long getWorkProfileId() {
		return workProfileId;
	}

	public void setWorkProfileId(Long workProfileId) {
		this.workProfileId = workProfileId;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
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

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

	public String getAttentionPopulationType() {
		return BaseInfoTables.HANDICAPPED_KEY;
	}
}