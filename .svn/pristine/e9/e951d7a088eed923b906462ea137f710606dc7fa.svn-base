package com.tianque.baseInfo.newSocietyOrganizations.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.GisInfo;
import com.tianque.domain.LocationBaseInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class NewSocietyOrganizations extends LocationBaseInfo {
	/** 所属网格 */
	private Organization organization;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 备注 */
	private String remark;
	/** 组织名称 */
	private String name;
	/** 住所 */
	private String address;
	/** 组织类别 */
	private PropertyDict type;
	/** 法定代表人（负责人）： */
	private String legalPerson;
	/** 固定电话 */
	private String legalPersonTelephone;
	/** 联系手机 */
	private String legalPersonMobileNumber;

	/** 组织子类别 */
	// TODO 待修改
	private PropertyDict subType;
	// private int subType;

	/** 业务主管单位 */
	private String chargeUnit;
	/** 登记证号码 */
	private String registrationNumber;
	/** 有效期开始 */
	private Date validityStartDate;
	/** 有效期 结束 */
	private Date validityEndDate;
	/** 成员数 */
	private Long personNum;
	/** 党员人数 */
	private Long partyNum;
	/** 简 介 */
	private String introduction;
	/** 获得荣誉 */
	private String honor;
	/** 注销时间 */
	private Date logOutTime;
	/** 注销原因 */
	private String logOutReason;
	/** gis */
	private GisInfo gisInfo;

	private String imgUrl;

	/**
	 * 主要职责
	 */
	private String mainResponsibilities;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization orgId) {
		this.organization = orgId;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalPersonTelephone() {
		return legalPersonTelephone;
	}

	public void setLegalPersonTelephone(String legalPersonTelephone) {
		this.legalPersonTelephone = legalPersonTelephone;
	}

	public String getLegalPersonMobileNumber() {
		return legalPersonMobileNumber;
	}

	public void setLegalPersonMobileNumber(String legalPersonMobileNumber) {
		this.legalPersonMobileNumber = legalPersonMobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	// public PropertyDict getSubType() {
	// return subType;
	// }

	public String getChargeUnit() {
		return chargeUnit;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	// public void setSubType(PropertyDict subType) {
	// this.subType = subType;
	// }

	public void setChargeUnit(String chargeUnit) {
		this.chargeUnit = chargeUnit;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public PropertyDict getSubType() {
		return subType;
	}

	public void setSubType(PropertyDict subType) {
		this.subType = subType;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getValidityStartDate() {
		return validityStartDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getValidityEndDate() {
		return validityEndDate;
	}

	public String getIntroduction() {
		return introduction;
	}

	public String getHonor() {
		return honor;
	}

	public void setValidityStartDate(Date validityStartDate) {
		this.validityStartDate = validityStartDate;
	}

	public void setValidityEndDate(Date validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

	public String getLogOutReason() {
		return logOutReason;
	}

	public Date getLogOutTime() {
		return logOutTime;
	}

	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}

	public void setLogOutTime(Date logOutTime) {
		this.logOutTime = logOutTime;
	}

	public Long getPersonNum() {
		return personNum;
	}

	public Long getPartyNum() {
		return partyNum;
	}

	public void setPersonNum(Long personNum) {
		this.personNum = personNum;
	}

	public void setPartyNum(Long partyNum) {
		this.partyNum = partyNum;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getMainResponsibilities() {
		return mainResponsibilities;
	}

	public void setMainResponsibilities(String mainResponsibilities) {
		this.mainResponsibilities = mainResponsibilities;
	}

	// id orgcode加密
	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(),
				getOrgInternalCode(), null);
	}

}
