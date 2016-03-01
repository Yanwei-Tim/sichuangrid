package com.tianque.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;
import com.tianque.search.vo.QuickFilterType;

@SuppressWarnings("serial")
public class SearchIdleYouthVo extends BaseDomain implements Serializable {

	/** 人员类型 */
	private List<String> staffTypeId;

	private Long isEmphasis;

	private String name;
	private String usedName;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;

	/** 性别 */
	private PropertyDict gender;
	private String orgInternalCode;
	private String idCardNo;
	private String province;

	private String city;

	private String district;
	/*
	 * 健康状况
	 */
	private PropertyDict healthState;

	/** 是否死亡 */
	private Long isDeath;
	/**
	 * 固定电话
	 */
	private String telephone;
	/**
	 * 手机号码
	 */
	private String mobileNumber;
	/**
	 * 工作单位
	 */
	private String workUnit;

	private PropertyDict career;
	private String otherAddress;
	/**
	 * 户籍地详细地址
	 */
	private String nativePlaceAddress;
	/**
	 * 婚姻状况
	 */
	private PropertyDict maritalState;
	/**
	 * 身高
	 */
	private Integer statureStart;

	private Integer statureEnd;
	/**
	 * 民族
	 */
	private PropertyDict nation;
	/**
	 * 宗教信仰
	 */
	private PropertyDict faith;
	/**
	 * 文化程度
	 */
	private PropertyDict schooling;
	/**
	 * 血型
	 */
	private PropertyDict bloodType;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 备注
	 */
	private String remark;
	/** 常住地址 */
	/** 类型 */
	private PropertyDict currentAddressType;
	/**
	 * 小区
	 */
	private String community;
	/**
	 * 幢
	 */
	private String block;
	/**
	 * 单元
	 */
	private String unit;
	/**
	 * 室
	 */
	private String room;
	/** 常住地址为其他类型 */
	private String currentAddress;
	/**
	 * 出生日期 开始
	 */
	private Date birthdayStart;
	/**
	 * 出生日期 结束
	 */
	private Date birthdayEnd;
	/**
	 * 政治面貌
	 */
	private PropertyDict politicalBackground;
	private String fastSearchKeyWords;// 姓名、身份证
	/** 是否有服务成员 */
	private Long hasServiceTeamMember;
	/** 是否有服务记录 */
	private Long hasServiceTeamRecord;
	/** 是否在校住宿 */
	private Boolean isStayInSchool;
	private QuickFilterType hasIsStayInSchoolType;
	/** 学校名称 */
	private String schoolName;

	public Boolean getIsStayInSchool() {
		if (hasIsStayInSchoolType != null)
			return hasIsStayInSchoolType.toBoolean();
		return isStayInSchool;
	}

	public void setIsStayInSchool(Boolean isStayInSchool) {
		if (hasIsStayInSchoolType != null)
			this.isStayInSchool = hasIsStayInSchoolType.toBoolean();
		else
			this.isStayInSchool = isStayInSchool;
	}

	public QuickFilterType getHasIsStayInSchoolType() {
		return hasIsStayInSchoolType;
	}

	public void setHasIsStayInSchoolType(QuickFilterType hasIsStayInSchoolType) {
		this.hasIsStayInSchoolType = hasIsStayInSchoolType;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsedName() {
		return usedName;
	}

	public void setUsedName(String usedName) {
		this.usedName = usedName;
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

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
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

	public PropertyDict getHealthState() {
		return healthState;
	}

	public void setHealthState(PropertyDict healthState) {
		this.healthState = healthState;
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

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public PropertyDict getCareer() {
		return career;
	}

	public void setCareer(PropertyDict career) {
		this.career = career;
	}

	public String getOtherAddress() {
		return otherAddress;
	}

	public void setOtherAddress(String otherAddress) {
		this.otherAddress = otherAddress;
	}

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
	}

	public PropertyDict getMaritalState() {
		return maritalState;
	}

	public void setMaritalState(PropertyDict maritalState) {
		this.maritalState = maritalState;
	}

	public Integer getStatureStart() {
		return statureStart;
	}

	public void setStatureStart(Integer statureStart) {
		this.statureStart = statureStart;
	}

	public Integer getStatureEnd() {
		return statureEnd;
	}

	public void setStatureEnd(Integer statureEnd) {
		this.statureEnd = statureEnd;
	}

	public PropertyDict getNation() {
		return nation;
	}

	public void setNation(PropertyDict nation) {
		this.nation = nation;
	}

	public PropertyDict getFaith() {
		return faith;
	}

	public void setFaith(PropertyDict faith) {
		this.faith = faith;
	}

	public PropertyDict getSchooling() {
		return schooling;
	}

	public void setSchooling(PropertyDict schooling) {
		this.schooling = schooling;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PropertyDict getCurrentAddressType() {
		return currentAddressType;
	}

	public void setCurrentAddressType(PropertyDict currentAddressType) {
		this.currentAddressType = currentAddressType;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
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

	public PropertyDict getPoliticalBackground() {
		return politicalBackground;
	}

	public void setPoliticalBackground(PropertyDict politicalBackground) {
		this.politicalBackground = politicalBackground;
	}

	public List<String> getStaffTypeId() {
		return staffTypeId;
	}

	public void setStaffTypeId(List<String> staffTypeId) {
		this.staffTypeId = staffTypeId;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
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
		return BaseInfoTables.IDLEYOUTH_KEY;
	}
}
