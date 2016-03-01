package com.tianque.xichang.working.peopleAspiration.domain.vo;

import com.tianque.domain.Organization;

/**
 * 民生诉求表:SrarchVO(PEOPLEASPIRATIONS)
 * 
 * @author
 * @date 2013-12-23 17:57:24
 */
public class SearchPeopleAspirationsVo extends com.tianque.core.base.BaseDomain {

	/** 最大 出生年月 */
	private java.util.Date maxBirthDay;
	/** 最小 出生年月 */
	private java.util.Date minBirthDay;
	/** 联系电话 */
	private String mobileNumber;
	/** 所属网格 */
	private Long orgId;
	/** 性别 */
	private Long genderId;
	/** 反应群体人数 */
	private Long responseGroupNo;
	/** 是否党员，0否 1是 */
	private Boolean isPartyMember;
	/** 职业或身份 */
	private Long positionId;
	/** 诉求人类别 */
	private Long appealHumanTypeId;
	/** 项目类别(大类) */
	private Long itemCategoryId;
	/** 项目类别(子类) */
	private Long itemCategorySubId;
	/** 编号 */
	private String serialNumber;
	/** 服务联系电话 */
	private String serverTelephone;
	/** 所属网格编号 */
	private String orgInternalCode;
	/** 姓名 */
	private String name;
	/** 身份证 */
	private String idCardNo;
	/** 服务联系人 */
	private String serverContractor;
	/** 服务职务 */
	private String serverJob;
	/** 常住地址 */
	private String permanentAddress;
	/** 服务联系人单位 */
	private String serverUnit;
	/** 主要愿望或诉求 */
	private String appealContent;
	/** 建表类型(CREATETABLETYPE) */
	private Long createTableTypeId;
	/** 台账类型 */
	private String accountType;
	/** 是否有情况记录 */
	private Boolean hasAccountLog;

	private Integer isFinish;

	/** 发生网格(OCCURORGID) */
	private Organization occurOrg;

	/** 仅显示本级、所有下辖、直属下辖 */
	private String displayLevel;

	/** get 最大 出生年月 */
	public java.util.Date getMaxBirthDay() {
		return maxBirthDay;
	}

	/** set 最大 出生年月 */
	public void setMaxBirthDay(java.util.Date maxBirthDay) {
		this.maxBirthDay = maxBirthDay;
	}

	/** get 最小 出生年月 */
	public java.util.Date getMinBirthDay() {
		return minBirthDay;
	}

	/** set 最小 出生年月 */
	public void setMinBirthDay(java.util.Date minBirthDay) {
		this.minBirthDay = minBirthDay;
	}

	/** get 联系电话 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/** set 联系电话 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/** get 所属网格 */
	public Long getOrgId() {
		return orgId;
	}

	/** set 所属网格 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/** get 反应群体人数 */
	public Long getResponseGroupNo() {
		return responseGroupNo;
	}

	/** set 反应群体人数 */
	public void setResponseGroupNo(Long responseGroupNo) {
		this.responseGroupNo = responseGroupNo;
	}

	/** get 是否党员，0否 1是 */
	public Boolean getIsPartyMember() {
		return isPartyMember;
	}

	/** set 是否党员，0否 1是 */
	public void setIsPartyMember(Boolean isPartyMember) {
		this.isPartyMember = isPartyMember;
	}

	/** get 编号 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/** set 编号 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/** get 服务联系电话 */
	public String getServerTelephone() {
		return serverTelephone;
	}

	/** set 服务联系电话 */
	public void setServerTelephone(String serverTelephone) {
		this.serverTelephone = serverTelephone;
	}

	/** get 所属网格编号 */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号 */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	/** get 姓名 */
	public String getName() {
		return name;
	}

	/** set 姓名 */
	public void setName(String name) {
		this.name = name;
	}

	/** get 身份证 */
	public String getIdCardNo() {
		return idCardNo;
	}

	/** set 身份证 */
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	/** get 服务联系人 */
	public String getServerContractor() {
		return serverContractor;
	}

	/** set 服务联系人 */
	public void setServerContractor(String serverContractor) {
		this.serverContractor = serverContractor;
	}

	/** get 服务职务 */
	public String getServerJob() {
		return serverJob;
	}

	/** set 服务职务 */
	public void setServerJob(String serverJob) {
		this.serverJob = serverJob;
	}

	/** get 常住地址 */
	public String getPermanentAddress() {
		return permanentAddress;
	}

	/** set 常住地址 */
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	/** get 服务联系人单位 */
	public String getServerUnit() {
		return serverUnit;
	}

	/** set 服务联系人单位 */
	public void setServerUnit(String serverUnit) {
		this.serverUnit = serverUnit;
	}

	/** get 主要愿望或诉求 */
	public String getAppealContent() {
		return appealContent;
	}

	/** set 主要愿望或诉求 */
	public void setAppealContent(String appealContent) {
		this.appealContent = appealContent;
	}

	public Long getCreateTableTypeId() {
		return createTableTypeId;
	}

	public void setCreateTableTypeId(Long createTableTypeId) {
		this.createTableTypeId = createTableTypeId;
	}

	public Long getGenderId() {
		return genderId;
	}

	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Long getAppealHumanTypeId() {
		return appealHumanTypeId;
	}

	public void setAppealHumanTypeId(Long appealHumanTypeId) {
		this.appealHumanTypeId = appealHumanTypeId;
	}

	public Long getItemCategoryId() {
		return itemCategoryId;
	}

	public void setItemCategoryId(Long itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}

	public Long getItemCategorySubId() {
		return itemCategorySubId;
	}

	public void setItemCategorySubId(Long itemCategorySubId) {
		this.itemCategorySubId = itemCategorySubId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Boolean getHasAccountLog() {
		return hasAccountLog;
	}

	public void setHasAccountLog(Boolean hasAccountLog) {
		this.hasAccountLog = hasAccountLog;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	public Integer getIsFinish() {
		return isFinish;
	}

	public Organization getOccurOrg() {
		return occurOrg;
	}

	public void setOccurOrg(Organization occurOrg) {
		this.occurOrg = occurOrg;
	}

	public String getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(String displayLevel) {
		this.displayLevel = displayLevel;
	}

}
