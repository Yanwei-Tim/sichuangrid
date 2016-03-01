package com.tianque.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.base.BaseDomain;

public class SearchOverseaPersonnelVo extends BaseDomain implements
		Serializable {
	/** 姓名 */
	private String name;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 英文名 */
	private String englishName;
	/** 中文名 */
	private String chineseName;
	/** 性别 */
	private Long gender;
	/** 出生日期 开始 */
	private Date birthdayStart;
	/** 出生日期 结束 */
	private Date birthdayEnd;
	/**
	 * 证件种类
	 */
	private Long certificateTypeId;
	/**
	 * 证件号码
	 */
	private String certificateNo;
	/** 证件有效期开始时间 */
	private Date certificateStrartDay;
	/** 证件有效期结束时间 */
	private Date certificateEndDay;
	/**
	 * 国籍
	 */
	private String nationality;
	/** 流入原因 */
	private String inflowReason;
	/** 常住地址 */
	private String currentAddress;
	// 应该把父类的 职业和证件号码字段放到这？这两个字段是我在父类加的

	/** 查询时排序字段 */
	private String sortField;
	/** 查询排序 */
	private String order;
	/**
	 * 是否注销
	 */
	private Long logOut;
	/**
	 * 职业
	 */
	private Long professionId;

	/** 固定电话 */
	private String telephone;
	/** 手机号码 */
	private String mobileNumber;
	/**
	 * 抵达时间，范围开始时间
	 */
	private Date arrivalTimeStart;
	/**
	 * 抵达时间，范围结束时间
	 */
	private Date arrivalTimeEnd;

	/**
	 * 离开时间 ，时间段开始时间
	 */
	private Date leaveTimeStart;
	/**
	 * 离开时间 ，时间段开始时间
	 */
	private Date leaveTimeEnd;
	/** 工作单位 */
	private String workUnit;
	private String fastSearchKeyWords;// 姓名、身份证

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
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

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public Date getCertificateStrartDay() {
		return certificateStrartDay;
	}

	public void setCertificateStrartDay(Date certificateStrartDay) {
		this.certificateStrartDay = certificateStrartDay;
	}

	public Date getCertificateEndDay() {
		return certificateEndDay;
	}

	public void setCertificateEndDay(Date certificateEndDay) {
		this.certificateEndDay = certificateEndDay;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getInflowReason() {
		return inflowReason;
	}

	public void setInflowReason(String inflowReason) {
		this.inflowReason = inflowReason;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Long getLogOut() {
		return logOut;
	}

	public void setLogOut(Long logOut) {
		this.logOut = logOut;
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

	public Date getArrivalTimeStart() {
		return arrivalTimeStart;
	}

	public void setArrivalTimeStart(Date arrivalTimeStart) {
		this.arrivalTimeStart = arrivalTimeStart;
	}

	public Date getArrivalTimeEnd() {
		return arrivalTimeEnd;
	}

	public void setArrivalTimeEnd(Date arrivalTimeEnd) {
		this.arrivalTimeEnd = arrivalTimeEnd;
	}

	public Date getLeaveTimeStart() {
		return leaveTimeStart;
	}

	public void setLeaveTimeStart(Date leaveTimeStart) {
		this.leaveTimeStart = leaveTimeStart;
	}

	public Date getLeaveTimeEnd() {
		return leaveTimeEnd;
	}

	public void setLeaveTimeEnd(Date leaveTimeEnd) {
		this.leaveTimeEnd = leaveTimeEnd;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Long getCertificateTypeId() {
		return certificateTypeId;
	}

	public void setCertificateTypeId(Long certificateTypeId) {
		this.certificateTypeId = certificateTypeId;
	}

	public Long getProfessionId() {
		return professionId;
	}

	public void setProfessionId(Long professionId) {
		this.professionId = professionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

}
