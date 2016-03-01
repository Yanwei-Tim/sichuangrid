package com.tianque.baseInfo.overseaPersonnel.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class OverseaPersonnel extends Countrymen {

	private String actualType;
	/**
	 * 国籍
	 */
	private String nationality;

	/**
	 * 证件种类
	 */
	private PropertyDict certificateType;

	/**
	 * 证件号码
	 */
	private String certificateNo;

	/**
	 * 抵达时间
	 */
	private Date arrivalTime;

	/**
	 * 离开时间
	 */
	private Date leaveTime;
	/**
	 * 是否注销
	 */
	private Long logOut;

	/**
	 * 职业
	 */
	private PropertyDict profession;

	/** 英文名 */
	private String englishName;
	/** 证件有效期开始时间 */
	private Date certificateStrartDay;
	/** 证件有效期结束时间 */
	private Date certificateEndDay;
	/** 流入原因 */
	private String inflowReason;
	// private String idCardNo;

	/**
	 * 邮箱
	 */
	private String mail;

	/**
	 * 户籍祥址 private String nativePlaceAddress;
	 */

	@JSON(format = "yyyy-MM-dd")
	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Long getLogOut() {
		return logOut;
	}

	public void setLogOut(Long logOut) {
		this.logOut = logOut;
	}

	public PropertyDict getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(PropertyDict certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public PropertyDict getProfession() {
		return profession;
	}

	public void setProfession(PropertyDict profession) {
		this.profession = profession;
	}

	public OverseaPersonnel() {

	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getCertificateStrartDay() {
		return certificateStrartDay;
	}

	public void setCertificateStrartDay(Date certificateStrartDay) {
		this.certificateStrartDay = certificateStrartDay;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getCertificateEndDay() {
		return certificateEndDay;
	}

	public void setCertificateEndDay(Date certificateEndDay) {
		this.certificateEndDay = certificateEndDay;
	}

	public String getInflowReason() {
		return inflowReason;
	}

	public void setInflowReason(String inflowReason) {
		this.inflowReason = inflowReason;
	}

	public String getActualType() {
		return actualType;
	}

	public void setActualType(String actualType) {
		this.actualType = actualType;
	}

	public String getIdCardNo() {
		return getCertificateNo();
	}

	// fateson add 对导入新增会有问题，这个不合理
	// @Override
	// public String getName() {
	// if (super.getName() == null || super.getName() == "") {
	// return getEnglishName();
	// }
	// return super.getName();
	// }

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}