package com.tianque.plugin.taskList.domain;

import com.tianque.domain.PropertyDict;



public class PositiveInfoRecord extends TermerRecord{
	/**
	 * 用于手机接口返回被回复数量
	 */
	private long replyCount;
	/** 有无生活来源 **/
	private Long livelihoodOrNot;
	/** 家属联系电话 **/
	private String familyPhone;
	/** 生活来源方式 **/
	private PropertyDict livelihoodWay;
	/**
	 * 帮扶人员
	 */
	private String helpPeople;
	/**
	 * 身份证号码
	 */
	private String idCard;
	/**
	 * 电话号码
	 */
	private String phone;
	/***
	 * 人口信息刑释人员Id
	 */
	private Long positiveInfoId;
	
	private String createUserName;
	
	public Long getLivelihoodOrNot() {
		return livelihoodOrNot;
	}
	public void setLivelihoodOrNot(Long livelihoodOrNot) {
		this.livelihoodOrNot = livelihoodOrNot;
	}
	public String getFamilyPhone() {
		return familyPhone;
	}
	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}
	public PropertyDict getLivelihoodWay() {
		return livelihoodWay;
	}
	public void setLivelihoodWay(PropertyDict livelihoodWay) {
		this.livelihoodWay = livelihoodWay;
	}
	public long getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(long replyCount) {
		this.replyCount = replyCount;
	}
	public String getHelpPeople() {
		return helpPeople;
	}
	public void setHelpPeople(String helpPeople) {
		this.helpPeople = helpPeople;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Long getPositiveInfoId() {
		return positiveInfoId;
	}
	public void setPositiveInfoId(Long positiveInfoId) {
		this.positiveInfoId = positiveInfoId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	
	
}
