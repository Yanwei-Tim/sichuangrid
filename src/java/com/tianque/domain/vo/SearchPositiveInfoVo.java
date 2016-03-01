package com.tianque.domain.vo;

import java.io.Serializable;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class SearchPositiveInfoVo extends BaseDomain implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *
	 */
	private String idCardNo;
	private String name;
	private PropertyDict positiveInfosType;
	private String mobileNumber;
	private String caseReason;
	private String imprisonmentDate;
	private PropertyDict agoProfession;
	private String laborEduAddress;
	private String noResettlementReason;
	private Date releaseOrBackDate;
	private Date endReleaseOrBackDate;
	private Date resettlementDate;
	private Date endResettlementDate;
	private Date crimeDate;
	private Date endCrimeDate;
	private PropertyDict gender;
	private PropertyDict career;
	private String workUnit;
	private String province;
	private String city;
	private String district;
	private String nativePlaceAddress;
	private String currentlyAddress;
	private Date birthday;
	private Date endBirthday;
	private Organization organization;
	private PropertyDict schooling;
	private Long isRepeat = 0L;
	private Date createDate;
	private Date updateDate;
	private String householdId;
	private String nativePlace;
	private String remark;
	private String fullPinyin;
	private String simplePinyin;
	private String orgInternalCode;
	private String createUser;
	private String updateUser;
	private String telephone;
	private String nativePoliceStation;
	private String helpEducator;
	private String educatorTelephone;
	private String educatorMobileNumber;
	private Boolean isCrime;
	private Long isEmphasis;
	private String fastSearchKeyWords;// 姓名、身份证
	private Long isDeath;// 死亡状态
	/** 是否有服务成员 */
	private Long hasServiceTeamMember;
	/** 是否有服务记录 */
	private Long hasServiceTeamRecord;

	public PropertyDict getPositiveInfosType() {
		return positiveInfosType;
	}

	public void setPositiveInfosType(PropertyDict positiveInfosType) {
		this.positiveInfosType = positiveInfosType;
	}

	public Date getEndBirthday() {
		return endBirthday;
	}

	public void setEndBirthday(Date endBirthday) {
		this.endBirthday = endBirthday;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public Date getEndReleaseOrBackDate() {
		return endReleaseOrBackDate;
	}

	public void setEndReleaseOrBackDate(Date endReleaseOrBackDate) {
		this.endReleaseOrBackDate = endReleaseOrBackDate;
	}

	public Date getEndResettlementDate() {
		return endResettlementDate;
	}

	public void setEndResettlementDate(Date endResettlementDate) {
		this.endResettlementDate = endResettlementDate;
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

	public PropertyDict getSchooling() {
		return schooling;
	}

	public void setSchooling(PropertyDict schooling) {
		this.schooling = schooling;
	}

	public Long getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(Long isRepeat) {
		this.isRepeat = isRepeat;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getReleaseOrBackDate() {
		return releaseOrBackDate;
	}

	public void setReleaseOrBackDate(Date releaseOrBackDate) {
		this.releaseOrBackDate = releaseOrBackDate;
	}

	public Date getResettlementDate() {
		return resettlementDate;
	}

	public void setResettlementDate(Date resettlementDate) {
		this.resettlementDate = resettlementDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getHouseholdId() {
		return householdId;
	}

	public void setHouseholdId(String householdId) {
		this.householdId = householdId;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLaborEduAddress() {
		return laborEduAddress;
	}

	public void setLaborEduAddress(String laborEduAddress) {
		this.laborEduAddress = laborEduAddress;
	}

	public String getImprisonmentDate() {
		return imprisonmentDate;
	}

	public void setImprisonmentDate(String imprisonmentDate) {
		this.imprisonmentDate = imprisonmentDate;
	}

	public String getCaseReason() {
		return caseReason;
	}

	public void setCaseReason(String caseReason) {
		this.caseReason = caseReason;
	}

	public PropertyDict getAgoProfession() {
		return agoProfession;
	}

	public void setAgoProfession(PropertyDict agoProfession) {
		this.agoProfession = agoProfession;
	}

	public String getCurrentlyAddress() {
		return currentlyAddress;
	}

	public void setCurrentlyAddress(String currentlyAddress) {
		this.currentlyAddress = currentlyAddress;
	}

	public String getNoResettlementReason() {
		return noResettlementReason;
	}

	public void setNoResettlementReason(String noResettlementReason) {
		this.noResettlementReason = noResettlementReason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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

	public String getNativePoliceStation() {
		return nativePoliceStation;
	}

	public void setNativePoliceStation(String nativePoliceStation) {
		this.nativePoliceStation = nativePoliceStation;
	}

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
	}

	public String getHelpEducator() {
		return helpEducator;
	}

	public void setHelpEducator(String helpEducator) {
		this.helpEducator = helpEducator;
	}

	public String getEducatorTelephone() {
		return educatorTelephone;
	}

	public void setEducatorTelephone(String educatorTelephone) {
		this.educatorTelephone = educatorTelephone;
	}

	public String getEducatorMobileNumber() {
		return educatorMobileNumber;
	}

	public void setEducatorMobileNumber(String educatorMobileNumber) {
		this.educatorMobileNumber = educatorMobileNumber;
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

	public Boolean getIsCrime() {
		return isCrime;
	}

	public void setIsCrime(Boolean isCrime) {
		this.isCrime = isCrime;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public Date getCrimeDate() {
		return crimeDate;
	}

	public void setCrimeDate(Date crimeDate) {
		this.crimeDate = crimeDate;
	}

	public Date getEndCrimeDate() {
		return endCrimeDate;
	}

	public void setEndCrimeDate(Date endCrimeDate) {
		this.endCrimeDate = endCrimeDate;
	}

	public PropertyDict getCareer() {
		return career;
	}

	public void setCareer(PropertyDict career) {
		this.career = career;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
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

	public String getAttentionPopulationType() {
		return BaseInfoTables.POSITIVEINFO_KEY;
	}

}
