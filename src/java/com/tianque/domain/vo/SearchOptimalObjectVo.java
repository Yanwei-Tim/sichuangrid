package com.tianque.domain.vo;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class SearchOptimalObjectVo extends BaseDomain {
	/** 优待证号 */
	private String optimalCardNo;

	/** 优抚类型 */
	private PropertyDict optimalCardType;

	/** 劳动能力 */
	private PropertyDict laborAbility;

	/** 生活能力 */
	private PropertyDict abilityLiving;

	/** 就业情况 */
	private PropertyDict employmentSituation;

	/** 供养情况 */
	private PropertyDict supportSituation;

	/** 月生活费 从 */
	private Long monthLivingExpenses;

	/** 月生活费 到 */
	private Long endMonthLivingExpenses;

	/** 身高从 */
	private Long stature;

	/** 身高到 */
	private Long endStature;

	/** 血型 */
	private PropertyDict bloodtype;

	/** 宗教信仰 */
	private PropertyDict faith;

	/** 户口类别 */
	private PropertyDict residencetype;

	/** 电子邮箱 */
	private String email;

	/** 民族 */
	private PropertyDict nation;

	/** 政治面貌 */
	private PropertyDict politicalbackground;

	/** 婚姻状况 */
	private PropertyDict maritalstate;

	/** 文化程度 */
	private PropertyDict schooling;

	/** 工作单位 */
	private PropertyDict workunit;

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

	/** 固定电话 */
	private String telephone;

	/** 性别 */
	private PropertyDict gender;

	/** 出生日期 从 */
	private Date birthday;

	/** 出生日期 到 */
	private Date endBirthday;

	/** 是否关注 */
	private Long isEmphasis;

	/** 是否死亡 */
	private Long isDeath;

	/** 省 */
	private String province;

	/** 市 */
	private String city;

	/** 区县 */
	private String district;

	/** 工作单位 */
	private String workUnit;

	/** 职业 */
	private PropertyDict career;

	/** 户籍地详址 */
	private String nativePlaceAddress;

	/** 现在居住地 */
	private String currentAddress;
	/** 身份证号码，姓名 */
	private String fastSearchKeyWords;
	/** 是否有服务成员 */
	private Long hasServiceTeamMember;
	/** 是否有服务记录 */
	private Long hasServiceTeamRecord;

	public Long getIsDeath() {
		return isDeath;
	}

	public void setIsDeath(Long isDeath) {
		this.isDeath = isDeath;
	}

	public String getOptimalCardNo() {
		return optimalCardNo;
	}

	public Long getStature() {
		return stature;
	}

	public void setStature(Long stature) {
		this.stature = stature;
	}

	public Long getEndStature() {
		return endStature;
	}

	public void setEndStature(Long endStature) {
		this.endStature = endStature;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public PropertyDict getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(PropertyDict bloodtype) {
		this.bloodtype = bloodtype;
	}

	public PropertyDict getFaith() {
		return faith;
	}

	public void setFaith(PropertyDict faith) {
		this.faith = faith;
	}

	public PropertyDict getResidencetype() {
		return residencetype;
	}

	public void setResidencetype(PropertyDict residencetype) {
		this.residencetype = residencetype;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PropertyDict getNation() {
		return nation;
	}

	public void setNation(PropertyDict nation) {
		this.nation = nation;
	}

	public PropertyDict getPoliticalbackground() {
		return politicalbackground;
	}

	public void setPoliticalbackground(PropertyDict politicalbackground) {
		this.politicalbackground = politicalbackground;
	}

	public PropertyDict getMaritalstate() {
		return maritalstate;
	}

	public void setMaritalstate(PropertyDict maritalstate) {
		this.maritalstate = maritalstate;
	}

	public PropertyDict getSchooling() {
		return schooling;
	}

	public void setSchooling(PropertyDict schooling) {
		this.schooling = schooling;
	}

	public PropertyDict getWorkunit() {
		return workunit;
	}

	public void setWorkunit(PropertyDict workunit) {
		this.workunit = workunit;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getEndMonthLivingExpenses() {
		return endMonthLivingExpenses;
	}

	public void setEndMonthLivingExpenses(Long endMonthLivingExpenses) {
		this.endMonthLivingExpenses = endMonthLivingExpenses;
	}

	public void setOptimalCardNo(String optimalCardNo) {
		this.optimalCardNo = optimalCardNo;
	}

	public PropertyDict getOptimalCardType() {
		return optimalCardType;
	}

	public void setOptimalCardType(PropertyDict optimalCardType) {
		this.optimalCardType = optimalCardType;
	}

	public PropertyDict getLaborAbility() {
		return laborAbility;
	}

	public void setLaborAbility(PropertyDict laborAbility) {
		this.laborAbility = laborAbility;
	}

	public PropertyDict getAbilityLiving() {
		return abilityLiving;
	}

	public void setAbilityLiving(PropertyDict abilityLiving) {
		this.abilityLiving = abilityLiving;
	}

	public PropertyDict getEmploymentSituation() {
		return employmentSituation;
	}

	public void setEmploymentSituation(PropertyDict employmentSituation) {
		this.employmentSituation = employmentSituation;
	}

	public PropertyDict getSupportSituation() {
		return supportSituation;
	}

	public void setSupportSituation(PropertyDict supportSituation) {
		this.supportSituation = supportSituation;
	}

	public Long getMonthLivingExpenses() {
		return monthLivingExpenses;
	}

	public void setMonthLivingExpenses(Long monthLivingExpenses) {
		this.monthLivingExpenses = monthLivingExpenses;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getEndBirthday() {
		return endBirthday;
	}

	public void setEndBirthday(Date endBirthday) {
		this.endBirthday = endBirthday;
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

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
	}

	public String getCurrentAddress() {
		return currentAddress;
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

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

	public String getAttentionPopulationType() {
		return BaseInfoTables.OPTIMALOBJECT_KEY;
	}
}
