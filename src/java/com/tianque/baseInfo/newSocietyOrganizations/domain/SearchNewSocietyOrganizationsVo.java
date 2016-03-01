package com.tianque.baseInfo.newSocietyOrganizations.domain;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.LocationBaseInfo;
import com.tianque.domain.Organization;

@SuppressWarnings("serial")
public class SearchNewSocietyOrganizationsVo extends LocationBaseInfo implements Serializable {
	private String orgInternalCode;
	private Organization organization;
	/** 组织名称 */
	private String unitName;
	/** 住所 */
	private String address;
	/** 联系手机 */
	private String legalPersonMobileNumber;

	private Long typeId;
	// TODO 组织子类别
	private Long subType;
	private String legalPerson;
	/** 业务主管单位 */
	private String chargeUnit;
	/** 登记证号码 */
	private String registrationNumber;
	/** 有效期起始时间始 */
	private Date startValidityStartDate;
	/** 有效期起始时间末 */
	private Date endValidityStartDate;
	/** 有效期结束时间始 */
	private Date startValidityEndDate;
	/** 有效期结束时间末 */
	private Date endValidityEndDate;
	/** 最小面积 */
	private String minArea;
	/** 最大面积 */
	private String maxArea;
	/** 最小成员数 */
	private String minPersonNum;
	/** 最大成员数 */
	private String maxPersonNum;
	/** 最小党员人数 */
	private String minPartyNum;
	/** 最大党员人数 */
	private String maxPartyNum;
	/**
	 * 主要职责
	 */
	private String mainResponsibilities;
	/** 固定电话 */
	private String legalPersonTelephone;
	/* 是否有治安负责人 */
	private Long hasServiceTeamMember;
	/* 是否有巡场记录 */
	private Long hasServiceRecord;
	/* 对象类型 */
	private String objectType;

	public SearchNewSocietyOrganizationsVo() {
		setObjectType(BaseInfoTables.NEWSOCIETYORGANIZATIONS_KEY);
	}

	public Long getSubType() {
		return subType;
	}

	public void setSubType(Long subType) {
		this.subType = subType;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getLegalPersonMobileNumber() {
		return legalPersonMobileNumber;
	}

	public void setLegalPersonMobileNumber(String legalPersonMobileNumber) {
		this.legalPersonMobileNumber = legalPersonMobileNumber;
	}

	public String getChargeUnit() {
		return chargeUnit;
	}

	public void setChargeUnit(String chargeUnit) {
		this.chargeUnit = chargeUnit;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Date getStartValidityStartDate() {
		return startValidityStartDate;
	}

	public Date getEndValidityStartDate() {
		return endValidityStartDate;
	}

	public Date getStartValidityEndDate() {
		return startValidityEndDate;
	}

	public Date getEndValidityEndDate() {
		return endValidityEndDate;
	}

	public void setStartValidityStartDate(Date startValidityStartDate) {
		this.startValidityStartDate = startValidityStartDate;
	}

	public void setEndValidityStartDate(Date endValidityStartDate) {
		this.endValidityStartDate = endValidityStartDate;
	}

	public void setStartValidityEndDate(Date startValidityEndDate) {
		this.startValidityEndDate = startValidityEndDate;
	}

	public void setEndValidityEndDate(Date endValidityEndDate) {
		this.endValidityEndDate = endValidityEndDate;
	}

	public String getMinArea() {
		return minArea;
	}

	public String getMaxArea() {
		return maxArea;
	}

	public String getMaxPersonNum() {
		return maxPersonNum;
	}

	public String getMinPartyNum() {
		return minPartyNum;
	}

	public String getMaxPartyNum() {
		return maxPartyNum;
	}

	public void setMinArea(String minArea) {
		this.minArea = minArea;
	}

	public void setMaxArea(String maxArea) {
		this.maxArea = maxArea;
	}

	public void setMaxPersonNum(String maxPersonNum) {
		this.maxPersonNum = maxPersonNum;
	}

	public void setMinPartyNum(String minPartyNum) {
		this.minPartyNum = minPartyNum;
	}

	public void setMaxPartyNum(String maxPartyNum) {
		this.maxPartyNum = maxPartyNum;
	}

	public String getMinPersonNum() {
		return minPersonNum;
	}

	public void setMinPersonNum(String minPersonNum) {
		this.minPersonNum = minPersonNum;
	}

	public String getMainResponsibilities() {
		return mainResponsibilities;
	}

	public void setMainResponsibilities(String mainResponsibilities) {
		this.mainResponsibilities = mainResponsibilities;
	}

	public String getLegalPersonTelephone() {
		return legalPersonTelephone;
	}

	public void setLegalPersonTelephone(String legalPersonTelephone) {
		this.legalPersonTelephone = legalPersonTelephone;
	}

	public Long getHasServiceTeamMember() {
		return hasServiceTeamMember;
	}

	public void setHasServiceTeamMember(Long hasServiceTeamMember) {
		this.hasServiceTeamMember = hasServiceTeamMember;
	}

	public Long getHasServiceRecord() {
		return hasServiceRecord;
	}

	public void setHasServiceRecord(Long hasServiceRecord) {
		this.hasServiceRecord = hasServiceRecord;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

}
