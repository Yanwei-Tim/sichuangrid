package com.tianque.baseInfo.publicPlace.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.GisInfo;
import com.tianque.domain.LocationBaseInfo;
import com.tianque.domain.PropertyDict;

/**
 * 公共场所
 */
public class PublicPlace extends LocationBaseInfo {

	public PublicPlace() {
		setLocationType(BaseInfoTables.PUBLICPLACE_KEY);
	}

	/** 所属网格内置码 */
	private String orgInternalCode;
	/** 场所名称 */
	private String placeName;
	/*** 产所地址 */
	private String placeAddress;

	/** 领取执照时间 */
	private Date licenseDate;
	/** 负责人 */
	private String manager;
	/** 开业时间 */
	private Date openingDate;
	/** 公共产所类别 */
	private String category;
	/** 备案登记号码 */
	private String registrationNumber;
	/** 场所等级 */
	private String placeLevel;
	/** 建筑物结构 */
	private String buildingStructure;
	/** 总面积 */
	private Double totalArea;
	/** 营业面积 */
	private Double operationArea;
	/** 是否有 小件寄存处 */
	private PropertyDict cloakroom;
	/** 核定人数 */
	private Long vouchedPeopleCount;
	/** 包间数 */
	private Long privateRoomCount;
	/** 周围环境 */
	private String surrounding;
	/** 通道口 */
	private String passageway;
	/** 内部结构 */
	private String innerStructure;
	/** 注销原因 */
	private String logOutReason;
	/** 注销时间 */
	private Date logOutTime;
	/** 备注 */
	private String remark;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;

	private String imgUrl;
	/** gis */
	private GisInfo gisInfo;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getPlaceLevel() {
		return placeLevel;
	}

	public void setPlaceLevel(String placeLevel) {
		this.placeLevel = placeLevel;
	}

	public String getBuildingStructure() {
		return buildingStructure;
	}

	public void setBuildingStructure(String buildingStructure) {
		this.buildingStructure = buildingStructure;
	}

	public Double getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(Double totalArea) {
		this.totalArea = totalArea;
	}

	public Double getOperationArea() {
		return operationArea;
	}

	public void setOperationArea(Double operationArea) {
		this.operationArea = operationArea;
	}

	public PropertyDict getCloakroom() {
		return cloakroom;
	}

	public void setCloakroom(PropertyDict cloakroom) {
		this.cloakroom = cloakroom;
	}

	public Long getVouchedPeopleCount() {
		return vouchedPeopleCount;
	}

	public void setVouchedPeopleCount(Long vouchedPeopleCount) {
		this.vouchedPeopleCount = vouchedPeopleCount;
	}

	public Long getPrivateRoomCount() {
		return privateRoomCount;
	}

	public void setPrivateRoomCount(Long privateRoomCount) {
		this.privateRoomCount = privateRoomCount;
	}

	public String getSurrounding() {
		return surrounding;
	}

	public void setSurrounding(String surrounding) {
		this.surrounding = surrounding;
	}

	public String getPassageway() {
		return passageway;
	}

	public void setPassageway(String passageway) {
		this.passageway = passageway;
	}

	public String getInnerStructure() {
		return innerStructure;
	}

	public void setInnerStructure(String innerStructure) {
		this.innerStructure = innerStructure;
	}

	public String getLogOutReason() {
		return logOutReason;
	}

	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLogOutTime() {
		return logOutTime;
	}

	public void setLogOutTime(Date logOutTime) {
		this.logOutTime = logOutTime;
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
		if (null != fullPinyin && fullPinyin.length() >= 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (null != simplePinyin && simplePinyin.length() >= 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}

	public String getPlaceAddress() {
		return placeAddress;
	}

	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	// id orgcode加密
	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(),
				getOrgInternalCode(), null);
	}

}
