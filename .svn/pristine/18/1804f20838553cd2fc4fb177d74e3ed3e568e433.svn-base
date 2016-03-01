package com.tianque.plugin.dataManage.location.publicPlaceTemp.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.location.placeCommon.domain.PlaceCommonTemp;

/**
 * 公共场所
 */
public class PublicPlaceTemp extends PlaceCommonTemp {
	public PublicPlaceTemp() {
		setLocationType(BaseInfoTables.PUBLICPLACE_KEY);
	}

	private ClaimDetail claimDetail;
	/** 领取执照时间 */
	private Date licenseDate;
	/** 开业时间 */
	private Date openingDate;
	/** 公共场所类别 */
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
	/** 通道口 */
	private String passageway;
	/** 内部结构 */
	private String innerStructure;
	/** 周围环境 */
	private String surrounding;

	@JSON(format = "yyyy-MM-dd")
	public Date getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
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

	public String getSurrounding() {
		return surrounding;
	}

	public void setSurrounding(String surrounding) {
		this.surrounding = surrounding;
	}

	public ClaimDetail getClaimDetail() {
		if (null == claimDetail) {
			claimDetail = new ClaimDetail();
		}
		return claimDetail;
	}

	public void setClaimDetail(ClaimDetail claimDetail) {
		this.claimDetail = claimDetail;
	}

}
