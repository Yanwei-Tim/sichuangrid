package com.tianque.gis.domain.vo;

import com.tianque.domain.GisInfo;
import com.tianque.domain.PropertyDict;

public class PopulationVo {
	private Long personId;
	private String name;
	private String idCardNo;
	private PropertyDict gender;
	private String address;
	private String houseCode;
	private boolean enableBind;
	private GisInfo gisInfo;
	private Long houseId;
	private Long orgId;
	private String imgUrl;
	private String genderName;
	private String certificateType;
	private String noHouseReason;
	/** 是否有房屋 */
	private Boolean isHaveHouse;
	/**
	 * 业务类型
	 */
	private String populationType;
	/**
	 * 实有人口类型
	 */
	private String actulType;
	/**
	 * serviceName的注解值
	 */
	private String keyPersonType;
	/**
	 * 党工委联系方式
	 */
	private String contactWay;
	/**
	 * 党工委备注
	 */
	private String remark;
	/**
	 * 组织机构类型
	 */
	private String orgTypeName;
	/**
	 * 组织结构名称
	 */
	private String orgLevelName;

	public String getKeyPersonType() {
		return keyPersonType;
	}

	public void setKeyPersonType(String keyPersonType) {
		this.keyPersonType = keyPersonType;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
	}

	public String getOrgLevelName() {
		return orgLevelName;
	}

	public void setOrgLevelName(String orgLevelName) {
		this.orgLevelName = orgLevelName;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isEnableBind() {
		return enableBind;
	}

	public void setEnableBind(boolean enableBind) {
		this.enableBind = enableBind;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getActulType() {
		return actulType;
	}

	public void setActulType(String actulType) {
		this.actulType = actulType;
	}

	public String getNoHouseReason() {
		return noHouseReason;
	}

	public void setNoHouseReason(String noHouseReason) {
		this.noHouseReason = noHouseReason;
	}

	public Boolean getIsHaveHouse() {
		return isHaveHouse;
	}

	public void setIsHaveHouse(Boolean isHaveHouse) {
		this.isHaveHouse = isHaveHouse;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

}
