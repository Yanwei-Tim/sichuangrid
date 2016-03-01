package com.tianque.domain.vo;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class BasePersonnelSearchCondition extends BaseDomain implements Serializable {
	private String name;

	private Long genderId;

	private String orgInternalCode;

	private String idCardNo;
	/**
	 * 户籍地-省
	 */
	private String nativeProvince;
	/**
	 * 户籍地-省
	 */
	private String nativeCity;
	/**
	 * 户籍地-省
	 */
	private String nativeDistrict;
	/**
	 * 固定电话
	 */
	private String telephone;
	/**
	 * 手机号码
	 */
	private String mobileNumber;
	/**
	 * 工作单位
	 */
	private String workUnit;
	/**
	 * 关注原因
	 */
	private String attentionReason;
	/**
	 * 户籍派出所
	 */
	private String nativePoliceStation;
	/**
	 * 户籍地详细地址
	 */
	private String nativePlaceAddress;
	/**
	 * 现居住地
	 */
	private String currentAddress;
	/**
	 * 出生日期 开始
	 */
	private Date birthdayStart;
	/**
	 * 出生日期 结束
	 */
	private Date birthdayEnd;
	/**
	 * 抵达日期开始
	 */
	private Date arrivalTimeStart;

	/**
	 * 出生日期结束
	 */
	private Date arrivalTimeEnd;

	/**
	 * 离开日期开始
	 */
	private Date leaveTimeStart;
	/**
	 * 离开日期结束
	 */
	private Date leaveTimeEnd;
	/**
	 * 国籍
	 */
	private String nationality;

	/**
	 * 证件种类
	 */
	private PropertyDict certificateType;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 是否关注
	 */
	private Long isEmphasis;

	/**
	 * 证件号码
	 */
	private String certificateNo;
	/**
	 * 职业
	 */
	private PropertyDict profession;
	
	/**
	 * 分表编码
	 */
	private String shardCode;

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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public PropertyDict getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(PropertyDict certificateType) {
		this.certificateType = certificateType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getGenderId() {
		return genderId;
	}

	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getNativeProvince() {
		return nativeProvince;
	}

	public void setNativeProvince(String nativeProvince) {
		this.nativeProvince = nativeProvince;
	}

	public String getNativeCity() {
		return nativeCity;
	}

	public void setNativeCity(String nativeCity) {
		this.nativeCity = nativeCity;
	}

	public String getNativeDistrict() {
		return nativeDistrict;
	}

	public void setNativeDistrict(String nativeDistrict) {
		this.nativeDistrict = nativeDistrict;
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

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getAttentionReason() {
		return attentionReason;
	}

	public void setAttentionReason(String attentionReason) {
		this.attentionReason = attentionReason;
	}

	public String getNativePoliceStation() {
		return nativePoliceStation;
	}

	public void setNativePoliceStation(String nativePoliceStation) {
		this.nativePoliceStation = nativePoliceStation;
	}

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
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

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getShardCode() {
		return shardCode;
	}

	public void setShardCode(String shardCode) {
		this.shardCode = shardCode;
	}

}
