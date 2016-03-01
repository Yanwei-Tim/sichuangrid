package com.tianque.domain.vo;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class SearchPartyMemberInfoVo extends BaseDomain {

	/** 身份证 */
	private String idCardNo;

	/** 姓名 */
	private String name;

	/** 出生日期 从 */
	private Date birthday;

	/** 出生日期 到 */
	private Date endBirthday;

	/** 性别 */
	private PropertyDict gender;

	/** 进入党支部时间从 */
	private Date joinPartyBranchDate;

	/** 进入党支部时间到 */
	private Date endJoinPartyBranchDate;

	/** 党员类型 */
	private PropertyDict partyMemberType;

	/** 入党时间从 */
	private Date joinPartyDate;

	/** 入党时间到 */
	private Date endJoinPartyDate;

	/** 培训情况 */
	private PropertyDict trainingState;

	/** 是否持流动党员证 1代表有 0没有 */
	private Long isFlowPartyCard;

	/** 流入地（单位）党支部 */
	private String flowPartyBranch;

	/** 流入时间从 */
	private Date flowPartyBranchDate;

	/** 流入时间到 */
	private Date endFlowPartyBranchDate;

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

	/** 手机号码 */
	private String mobileNumber;

	/** 固定电话 */
	private String telephone;

	/** 是否关注 */
	private Long isEmphasis;

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

	/** 快速检索关键字 */
	private String fastSearchKeyWords;

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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
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

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public Date getJoinPartyBranchDate() {
		return joinPartyBranchDate;
	}

	public void setJoinPartyBranchDate(Date joinPartyBranchDate) {
		this.joinPartyBranchDate = joinPartyBranchDate;
	}

	public Date getEndJoinPartyBranchDate() {
		return endJoinPartyBranchDate;
	}

	public void setEndJoinPartyBranchDate(Date endJoinPartyBranchDate) {
		this.endJoinPartyBranchDate = endJoinPartyBranchDate;
	}

	public PropertyDict getPartyMemberType() {
		return partyMemberType;
	}

	public void setPartyMemberType(PropertyDict partyMemberType) {
		this.partyMemberType = partyMemberType;
	}

	public Date getJoinPartyDate() {
		return joinPartyDate;
	}

	public void setJoinPartyDate(Date joinPartyDate) {
		this.joinPartyDate = joinPartyDate;
	}

	public Date getEndJoinPartyDate() {
		return endJoinPartyDate;
	}

	public void setEndJoinPartyDate(Date endJoinPartyDate) {
		this.endJoinPartyDate = endJoinPartyDate;
	}

	public PropertyDict getTrainingState() {
		return trainingState;
	}

	public void setTrainingState(PropertyDict trainingState) {
		this.trainingState = trainingState;
	}

	public Long getIsFlowPartyCard() {
		return isFlowPartyCard;
	}

	public void setIsFlowPartyCard(Long isFlowPartyCard) {
		this.isFlowPartyCard = isFlowPartyCard;
	}

	public String getFlowPartyBranch() {
		return flowPartyBranch;
	}

	public void setFlowPartyBranch(String flowPartyBranch) {
		this.flowPartyBranch = flowPartyBranch;
	}

	public Date getFlowPartyBranchDate() {
		return flowPartyBranchDate;
	}

	public void setFlowPartyBranchDate(Date flowPartyBranchDate) {
		this.flowPartyBranchDate = flowPartyBranchDate;
	}

	public Date getEndFlowPartyBranchDate() {
		return endFlowPartyBranchDate;
	}

	public void setEndFlowPartyBranchDate(Date endFlowPartyBranchDate) {
		this.endFlowPartyBranchDate = endFlowPartyBranchDate;
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

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

}
