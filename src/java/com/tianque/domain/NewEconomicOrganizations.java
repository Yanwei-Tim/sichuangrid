package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.NumbericUtil;
import com.tianque.core.util.StringUtil;

public class NewEconomicOrganizations extends Countrymen {
	/** 名称 */
	private String name;
	/** 住所 */
	private String residence;
	/** 营业执照号码 */
	private String licenseNumber;
	/** 有效期开始 */
	private Date validityStartDate;
	/** 有效期结束 */
	private Date validityEndDate;
	/** 类别 */
	private PropertyDict style;
	/** 负责人 */
	private String chief;
	/** 传真号码 */
	private String foxNumber;
	/** 所属网格编号 */
	private String orgInternalCode;
	private Organization organization;
	/** 面积 */
	private Double area;
	/** 从业人数 */
	private Integer employeeNumber;
	/** 党员人数 */
	private Integer partyMemberNumber;
	/** 简介 */
	private String introduction;
	/** 获得荣耀 */
	private String honor;
	/** 备注 */
	private String remark;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;
	/** 固定电话 */
	private String telephone;
	/** 手机号码 */
	private String mobileNumber;
	/** 是否注销 */
	private Long isEmphasis;
	
	private Date lastRecordTime;

	@JSON(format = "yyyy-MM-dd")
	public Date getLastRecordTime() {
		return lastRecordTime;
	}

	public void setLastRecordTime(Date lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}

	/** 图像路径 */
	private String imgUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getValidityStartDate() {
		return validityStartDate;
	}

	public void setValidityStartDate(Date validityStartDate) {
		this.validityStartDate = validityStartDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getValidityEndDate() {
		return validityEndDate;
	}

	public void setValidityEndDate(Date validityEndDate) {
		this.validityEndDate = validityEndDate;
	}

	public PropertyDict getStyle() {
		return style;
	}

	public void setStyle(PropertyDict style) {
		this.style = style;
	}

	public String getChief() {
		return chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}

	public String getFoxNumber() {
		return foxNumber;
	}

	public void setFoxNumber(String foxNumber) {
		this.foxNumber = foxNumber;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public Integer getPartyMemberNumber() {
		return partyMemberNumber;
	}

	public void setPartyMemberNumber(Integer partyMemberNumber) {
		this.partyMemberNumber = partyMemberNumber;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setFullPinyin(String fullPinyin) {
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getFullPinyin() {
		return fullPinyin;
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

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getAreaStringValue() {
		if (getArea() == null) {
			return null;
		}
		String value = NumbericUtil.toString(getArea(), 2);
		if (value.substring(value.length() - 3).equals(".00")) {
			return value.substring(0, value.length() - 3);
		} else if (value.substring(value.length() - 1).equals("0")) {
			return value.substring(0, value.length() - 1);
		}
		return value;
	}

	public String getEncryptId() {
		String orgCode = this.orgInternalCode;
		if (!StringUtil.isStringAvaliable(orgCode) && organization != null) {
			orgCode = this.organization.getOrgInternalCode();
		}
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), orgCode, null);
	}

}
