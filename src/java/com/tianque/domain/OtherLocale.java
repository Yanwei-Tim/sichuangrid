package com.tianque.domain;

import java.util.Date;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.BaseInfoTables;

@SuppressWarnings("serial")
public class OtherLocale extends LocationBaseInfo {

	public OtherLocale() {
		setLocationType(BaseInfoTables.OTHERLOCALE_KEY);
	}

	private String orgInternalCode;
	/** 场所名称 */
	private String name;
	private String fullPinyin;
	private String simplePinyin;
	private String address;
	/** 联系人 */
	private String contactPerson;
	/** 联系手机 */
	private String mobileNumber;
	/** 联系电话 */
	private String telephone;
	/** 场所类型 */
	private PropertyDict type;
	/** 备注 */
	private String remark;

	/** 注销原因 */
	private String logOutReason;
	/** 注销时间 */
	private Date logOutTime;
	/** 图像路径 */
	private String imgUrl;
	/** 从业人员数 */
	private Long practitionerNumber;

	public Long getPractitionerNumber() {
		return practitionerNumber;
	}

	public void setPractitionerNumber(Long practitionerNumber) {
		this.practitionerNumber = practitionerNumber;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

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

	// id orgcode加密
	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(),
				getOrgInternalCode(), null);
	}
}
