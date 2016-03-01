package com.tianque.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;

@SuppressWarnings("serial")
public class SearchElderlyPeopleVo extends BaseDomain implements Serializable {
	/** 姓名 */
	private String name;
	/** 身份证ID */
	private String idCardNo;
	/** 性别 */
	private Long gender;
	/** 出生日期 开始 */
	private Date birthdayStart;
	/** 出生日期 结束 */
	private Date birthdayEnd;
	/** 老年人证号 */
	private String elderPeopleId;

	/** 人员情况 */
	private Long peopleType;
	/** 目前状况 */
	private Long currentStatus;
	/** 居住情况 */
	private Long liveStatus;

	/** 配偶情况 */
	private Long spouseStatus;
	/** 经济来源 */
	private Long incomeSource;
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
	/** 固定电话 */
	private String telephone;
	/** 手机号码 */
	private String mobileNumber;

	/** 参加工作日期 开始 */
	private Date enterWorkDateStart;
	/** 参加工作日期 结束 */
	private Date enterWorkDateEnd;
	/** 离退休日期 开始 */
	private Date retireDateStart;
	/** 离退休日期 结束 */
	private Date retireDateEnd;
	/** 退休金区间 */
	private Double minPension;
	private Double maxPension;

	/** 离退休单位及职务 */
	private String retireUnitAndDuty;
	private String zhiwu;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 民族 */
	private Long nation;
	/** 整治面貌 */
	private Long politicalBackground;
	/** 婚姻状况 */
	private Long maritalState;
	/** 职业 */
	private Long career;
	/** 工作单位 */
	private String workUnit;
	/** 最低身高 */
	private Integer minStature;
	/** 最高身高 */
	private Integer maxStature;
	/** 信仰 */
	private Long faith;
	/** 邮箱 */
	private String email;
	/** 户口类别 */
	private Long residenceType;
	/** 是否死亡 */
	private Long isDeath;
	/** 文化程度 */
	private Long schooling;
	/** 血型 */
	private Long bloodType;
	/** 是否注销 */
	private Long isEmphasis;
	/** 查询时排序字段 */
	private String sortField;
	/** 查询排序 */
	private String order;
	/** 身份证号码，姓名 */
	private String fastSearchKeyWords;
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

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
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

	public String getElderPeopleId() {
		return elderPeopleId;
	}

	public void setElderPeopleId(String elderPeopleId) {
		this.elderPeopleId = elderPeopleId;
	}

	public Long getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(Long peopleType) {
		this.peopleType = peopleType;
	}

	public Long getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Long currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Long getLiveStatus() {
		return liveStatus;
	}

	public void setLiveStatus(Long liveStatus) {
		this.liveStatus = liveStatus;
	}

	public Long getSpouseStatus() {
		return spouseStatus;
	}

	public void setSpouseStatus(Long spouseStatus) {
		this.spouseStatus = spouseStatus;
	}

	public Long getIncomeSource() {
		return incomeSource;
	}

	public void setIncomeSource(Long incomeSource) {
		this.incomeSource = incomeSource;
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

	public Date getEnterWorkDateStart() {
		return enterWorkDateStart;
	}

	public void setEnterWorkDateStart(Date enterWorkDateStart) {
		this.enterWorkDateStart = enterWorkDateStart;
	}

	public Date getEnterWorkDateEnd() {
		return enterWorkDateEnd;
	}

	public void setEnterWorkDateEnd(Date enterWorkDateEnd) {
		this.enterWorkDateEnd = enterWorkDateEnd;
	}

	public Date getRetireDateStart() {
		return retireDateStart;
	}

	public void setRetireDateStart(Date retireDateStart) {
		this.retireDateStart = retireDateStart;
	}

	public Date getRetireDateEnd() {
		return retireDateEnd;
	}

	public void setRetireDateEnd(Date retireDateEnd) {
		this.retireDateEnd = retireDateEnd;
	}

	public Double getMinPension() {
		return minPension;
	}

	public void setMinPension(Double minPension) {
		this.minPension = minPension;
	}

	public Double getMaxPension() {
		return maxPension;
	}

	public void setMaxPension(Double maxPension) {
		this.maxPension = maxPension;
	}

	public String getRetireUnitAndDuty() {
		return retireUnitAndDuty;
	}

	public void setRetireUnitAndDuty(String retireUnitAndDuty) {
		this.retireUnitAndDuty = retireUnitAndDuty;
	}

	public String getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
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

	public Long getSchooling() {
		return schooling;
	}

	public void setSchooling(Long schooling) {
		this.schooling = schooling;
	}

	public Long getBloodType() {
		return bloodType;
	}

	public void setBloodType(Long bloodType) {
		this.bloodType = bloodType;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	@Override
	public String getSortField() {
		return sortField;
	}

	@Override
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	@Override
	public String getOrder() {
		return order;
	}

	@Override
	public void setOrder(String order) {
		this.order = order;
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
		return BaseInfoTables.ELDERLYPEOPLE_KEY;
	}
}
