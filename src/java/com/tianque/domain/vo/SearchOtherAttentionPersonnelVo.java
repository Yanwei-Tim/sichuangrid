package com.tianque.domain.vo;

import java.util.Date;

import org.mongodb.morphia.annotations.NotSaved;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class SearchOtherAttentionPersonnelVo extends BaseDomain {

	private Organization organization;
	private Long orgId;

	private String name;

	private String idCardNo;

	private String workUnit;

	private String workingCertificate;

	private String workingCertificateNo;

	private Date startAvailableDate;

	private Date endAvailableDate;

	private PropertyDict gender;

	private Date startBirthday;

	private Date endBirthday;

	private PropertyDict career;

	private String province;

	private String city;

	private String district;

	private String nativePlaceAddress;

	private String currentAddress;

	private Long empsisState;
	private String fastSearchKeyWords;// 姓名、身份证
	private Long isEmphasis;// 注销状态
	private Long isDeath;// 死亡状态
	/** 业务人员类型 */
	@NotSaved
	private String populationType;
	private String attentionReason;

	public String getAttentionReason() {
		return attentionReason;
	}

	public void setAttentionReason(String attentionReason) {
		this.attentionReason = attentionReason;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	private String orgInternalCode;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getWorkingCertificate() {
		return workingCertificate;
	}

	public void setWorkingCertificate(String workingCertificate) {
		this.workingCertificate = workingCertificate;
	}

	public String getWorkingCertificateNo() {
		return workingCertificateNo;
	}

	public void setWorkingCertificateNo(String workingCertificateNo) {
		this.workingCertificateNo = workingCertificateNo;
	}

	public Date getStartAvailableDate() {
		return startAvailableDate;
	}

	public void setStartAvailableDate(Date startAvailableDate) {
		this.startAvailableDate = startAvailableDate;
	}

	public Date getEndAvailableDate() {
		return endAvailableDate;
	}

	public void setEndAvailableDate(Date endAvailableDate) {
		this.endAvailableDate = endAvailableDate;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public Date getStartBirthday() {
		return startBirthday;
	}

	public void setStartBirthday(Date startBirthday) {
		this.startBirthday = startBirthday;
	}

	public Date getEndBirthday() {
		return endBirthday;
	}

	public void setEndBirthday(Date endBirthday) {
		this.endBirthday = endBirthday;
	}

	public PropertyDict getCareer() {
		return career;
	}

	public void setCareer(PropertyDict career) {
		this.career = career;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public Long getEmpsisState() {
		return empsisState;
	}

	public void setEmpsisState(Long empsisState) {
		this.empsisState = empsisState;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public Long getIsDeath() {
		return isDeath;
	}

	public void setIsDeath(Long isDeath) {
		this.isDeath = isDeath;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

}
