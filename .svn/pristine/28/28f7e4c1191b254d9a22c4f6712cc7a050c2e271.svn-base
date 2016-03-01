package com.tianque.domain;

import java.util.Date;

import com.tianque.core.util.BaseDomainIdEncryptUtil;

/**
 * 医院
 */

@SuppressWarnings("serial")
public class Hospital extends LocationBaseInfo {
	/** 医院等级 */
	private PropertyDict level;
	/** 医院性质 */
	private PropertyDict kind;
	/** 医院类型 */
	private PropertyDict type;
	/** 是否医保 */
	private boolean isMedicalInsurance;
	/** 所属网格编号 */
	private String orgInternalCode;
	/** 医院别名 */
	private String anotherName;
	/** 医院名称 */
	private String hospitalName;
	/** 医院地址 */
	private String address;
	/** 医院院长 */
	private String director;
	/** 所属单位 */
	private String affiliatedUnit;
	/** 联系人姓名 */
	private String contactName;
	/** 联系人固定电话 */
	private String telephone;
	/** 联系人手机号码 */
	private String mobileNumber;
	/** 联系人电子邮件 */
	private String email;
	/** 传真 */
	private String fax;
	/** 备注 */
	private String remark;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;

	/** 注销原因 */
	private String logOutReason;
	/** 注销时间 */
	private Date logOutTime;
	/** 图像路径 */
	private String imgUrl;

	/** 注销状态 */
	private Long isLogOut;

	// 是否有治安负责人
	private Long hasServiceTeamMember;
	// 是否有巡场记录
	private Long hasServiceRecord;

	private String importantLocationType;

	public String getLogOutReason() {
		return logOutReason;
	}

	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}

	public Date getLogOutTime() {
		return logOutTime;
	}

	public void setLogOutTime(Date logOutTime) {
		this.logOutTime = logOutTime;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getIsLogOut() {
		return isLogOut;
	}

	public void setIsLogOut(Long isLogOut) {
		this.isLogOut = isLogOut;
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

	public String getImportantLocationType() {
		return importantLocationType;
	}

	public void setImportantLocationType(String importantLocationType) {
		this.importantLocationType = importantLocationType;
	}

	public Hospital() {
		setImportantLocationType("HOSPITAL");
	}

	public PropertyDict getLevel() {
		return level;
	}

	public void setLevel(PropertyDict level) {
		this.level = level;
	}

	public PropertyDict getKind() {
		return kind;
	}

	public void setKind(PropertyDict kind) {
		this.kind = kind;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public boolean isMedicalInsurance() {
		return isMedicalInsurance;
	}

	public void setMedicalInsurance(boolean isMedicalInsurance) {
		this.isMedicalInsurance = isMedicalInsurance;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getAnotherName() {
		return anotherName;
	}

	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getAffiliatedUnit() {
		return affiliatedUnit;
	}

	public void setAffiliatedUnit(String affiliatedUnit) {
		this.affiliatedUnit = affiliatedUnit;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	// id orgcode加密
	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(),
				getOrgInternalCode(), null);
	}

}
