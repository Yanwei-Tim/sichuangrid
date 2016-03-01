package com.tianque.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;

@SuppressWarnings("serial")
public class SearchUnemployedPeopleVo extends BaseDomain implements
		Serializable {

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

	private String unemployedPeopleType;
	/** 就业/失业登记证号 */
	private String registerNumber;

	/** 参加工作日期 开始 */
	private Date enterWorkDateStart;
	/** 参加工作日期 结束 */
	private Date enterWorkDateEnd;
	/** 原工作单位名称 */
	private String originalWorkUnit;
	/** 原单位类型 */
	private String originalWorkUnitType;
	/** 职 称 */
	private String title;
	/** 失业时间 */
	private Date unemploymentDateStart;
	private Date unemploymentDateEnd;
	/** 技术等级 */
	private String technologyLevel;
	private String specialtySkills;
	/** 失业原因 */
	private String unemploymentReason;
	/** 拟就业意向 */
	private List<String> employmentIntention;
	/** 拟参加培训意向 */
	private List<String> trainingIntention;
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

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
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

	public String getOriginalWorkUnit() {
		return originalWorkUnit;
	}

	public void setOriginalWorkUnit(String originalWorkUnit) {
		this.originalWorkUnit = originalWorkUnit;
	}

	public String getOriginalWorkUnitType() {
		return originalWorkUnitType;
	}

	public void setOriginalWorkUnitType(String originalWorkUnitType) {
		this.originalWorkUnitType = originalWorkUnitType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUnemploymentDateStart() {
		return unemploymentDateStart;
	}

	public void setUnemploymentDateStart(Date unemploymentDateStart) {
		this.unemploymentDateStart = unemploymentDateStart;
	}

	public Date getUnemploymentDateEnd() {
		return unemploymentDateEnd;
	}

	public void setUnemploymentDateEnd(Date unemploymentDateEnd) {
		this.unemploymentDateEnd = unemploymentDateEnd;
	}

	public String getTechnologyLevel() {
		return technologyLevel;
	}

	public void setTechnologyLevel(String technologyLevel) {
		this.technologyLevel = technologyLevel;
	}

	public String getUnemploymentReason() {
		return unemploymentReason;
	}

	public void setUnemploymentReason(String unemploymentReason) {
		this.unemploymentReason = unemploymentReason;
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

	public String getUnemployedPeopleType() {
		return unemployedPeopleType;
	}

	public void setUnemployedPeopleType(String unemployedPeopleType) {
		this.unemployedPeopleType = unemployedPeopleType;
	}

	public String getSpecialtySkills() {
		return specialtySkills;
	}

	public void setSpecialtySkills(String specialtySkills) {
		this.specialtySkills = specialtySkills;
	}

	public List<String> getEmploymentIntention() {
		return employmentIntention;
	}

	public void setEmploymentIntention(List<String> employmentIntention) {
		this.employmentIntention = employmentIntention;
	}

	public List<String> getTrainingIntention() {
		return trainingIntention;
	}

	public void setTrainingIntention(List<String> trainingIntention) {
		this.trainingIntention = trainingIntention;
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
		return BaseInfoTables.UNEMPLOYEDPEOPLE_KEY;
	}
}
