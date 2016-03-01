package com.tianque.plugin.dataManage.location.actualHouseTemp.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.location.base.domain.LocationTempBaseDomain;

/**
 * 实有房屋
 */
public class ActualHouseTemp extends LocationTempBaseDomain {
	public ActualHouseTemp() {
		setLocationType(BaseInfoTables.ACTUALHOUSE_KEY);
	}

	private ClaimDetail claimDetail;
	/** 住房编号 */
	private String houseCode;
	/** 常住地址类型 */
	private PropertyDict addressType;
	/** 幢 */
	private String block;
	/** 单元 */
	private String unit;
	/** 室 */
	private String room;
	/** 小区 */
	private String community;
	/** 是否是出租房(实有房屋比出租房多的一个属性) */
	private Boolean isRentalHouse;
	/** 出租人（出租房时有效） */
	private String rentalPerson;
	/** 隐患程度（出租房时有效） */
	private PropertyDict hiddenDangerLevel;
	/** 建筑物名称 */
	private String buildingName;
	/** 建筑物用途 */
	private PropertyDict buildingUses;
	/** 物业管理单位名称 */
	private String propertyName;
	/** 业主身份证号 */
	private String houseOwnerIdCardNo;
	/** 业主联系电话 */
	private String telephone;
	/** 房屋户型 */
	private String houseDoorModel;
	/** 建成年份 */
	private String builtYear;
	/** 本户建筑面积 */
	private Double houseArea;
	/** 房屋结构 */
	private PropertyDict houseStructure;
	/** 房屋用途 */
	private PropertyDict houseUses;
	/** 房屋来源 */
	private PropertyDict houseSource;
	/** 自有产权 */
	private PropertyDict ownProperty;
	/** 租赁公房 */
	private PropertyDict rentalBuildings;
	/** 房屋凭证 */
	private PropertyDict housingVouchers;
	/** 房屋权证号 */
	private String houseRightNumber;
	/** 房屋权证发证时间 */
	private Date houseRightNumberDate;
	/** 土地凭证 */
	private PropertyDict landDocuments;

	/** 土地权证号 */
	private String landRightsNumbe;

	/** 土地权证发证时间 */
	private Date landRightsNumbeDate;

	/** 产权人类型 */
	private PropertyDict propertyTypes;

	/** 产权人证件类型 */
	private PropertyDict certificateType;

	/** 产权人证件号码 */
	private String certificateNumbe;
	/** 产权人联系电话 */
	private String propertyPersonTel;

	/** 产权人联系手机 */
	private String propertyPersonMobile;
	/** 出租房用途 */
	private PropertyDict usage;

	/** 租赁备案证号 */
	private String houseFileNum;

	/** 出租人类型 */
	private PropertyDict lessorType;
	/** 出租人证件类型 */
	private PropertyDict rentalCertificateType;

	/** 出租人证件号码 */
	private String rentalCertificateNumbe;
	/** 出租人联系电话 */
	private String rentalTelephone;

	/** 出租人联系手机 */
	private String rentalMobileNumber;
	/** 出租人联系地址 */
	private String lessorAddress;
	/** 出租房类别 */
	private PropertyDict rentalType;

	/** 出租房性质 */
	private PropertyDict rentalHouseProperty;
	/** 管理类别 */
	private PropertyDict mangerTypes;
	/** 登记日期 */
	private Date registDate;
	/** 出租时间 */
	private Date lessorDate;
	/** 出租间数 */
	private Long roomNumber;
	/** 限住人数 */
	private Integer limitPersons;
	/** 月租金 */
	private Double rentMonth;
	/** 隐患情况 */
	private String hiddenRectification;
	/** 是否签订治安责任保证书 */
	private Long isSignGuarantee;
	/** 有无安全通道 */
	private Long isSafetyChannel;
	/** 有无消防通道 */
	private Long isFireChannels;

	private String houseAddress;// 房产证地址

	/** 房屋图片 */
	private String imgUrl;

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public PropertyDict getAddressType() {
		return addressType;
	}

	public void setAddressType(PropertyDict addressType) {
		this.addressType = addressType;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public Boolean getIsRentalHouse() {
		return isRentalHouse;
	}

	public void setIsRentalHouse(Boolean isRentalHouse) {
		this.isRentalHouse = isRentalHouse;
	}

	public String getRentalPerson() {
		return rentalPerson;
	}

	public void setRentalPerson(String rentalPerson) {
		this.rentalPerson = rentalPerson;
	}

	public PropertyDict getHiddenDangerLevel() {
		return hiddenDangerLevel;
	}

	public void setHiddenDangerLevel(PropertyDict hiddenDangerLevel) {
		this.hiddenDangerLevel = hiddenDangerLevel;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public PropertyDict getBuildingUses() {
		return buildingUses;
	}

	public void setBuildingUses(PropertyDict buildingUses) {
		this.buildingUses = buildingUses;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getHouseOwnerIdCardNo() {
		return houseOwnerIdCardNo;
	}

	public void setHouseOwnerIdCardNo(String houseOwnerIdCardNo) {
		this.houseOwnerIdCardNo = houseOwnerIdCardNo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHouseDoorModel() {
		return houseDoorModel;
	}

	public void setHouseDoorModel(String houseDoorModel) {
		this.houseDoorModel = houseDoorModel;
	}

	public String getBuiltYear() {
		return builtYear;
	}

	public void setBuiltYear(String builtYear) {
		this.builtYear = builtYear;
	}

	public Double getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(Double houseArea) {
		this.houseArea = houseArea;
	}

	public PropertyDict getHouseStructure() {
		return houseStructure;
	}

	public void setHouseStructure(PropertyDict houseStructure) {
		this.houseStructure = houseStructure;
	}

	public PropertyDict getHouseUses() {
		return houseUses;
	}

	public void setHouseUses(PropertyDict houseUses) {
		this.houseUses = houseUses;
	}

	public PropertyDict getHouseSource() {
		return houseSource;
	}

	public void setHouseSource(PropertyDict houseSource) {
		this.houseSource = houseSource;
	}

	public PropertyDict getOwnProperty() {
		return ownProperty;
	}

	public void setOwnProperty(PropertyDict ownProperty) {
		this.ownProperty = ownProperty;
	}

	public PropertyDict getRentalBuildings() {
		return rentalBuildings;
	}

	public void setRentalBuildings(PropertyDict rentalBuildings) {
		this.rentalBuildings = rentalBuildings;
	}

	public PropertyDict getHousingVouchers() {
		return housingVouchers;
	}

	public void setHousingVouchers(PropertyDict housingVouchers) {
		this.housingVouchers = housingVouchers;
	}

	public String getHouseRightNumber() {
		return houseRightNumber;
	}

	public void setHouseRightNumber(String houseRightNumber) {
		this.houseRightNumber = houseRightNumber;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getHouseRightNumberDate() {
		return houseRightNumberDate;
	}

	public void setHouseRightNumberDate(Date houseRightNumberDate) {
		this.houseRightNumberDate = houseRightNumberDate;
	}

	public PropertyDict getLandDocuments() {
		return landDocuments;
	}

	public void setLandDocuments(PropertyDict landDocuments) {
		this.landDocuments = landDocuments;
	}

	public String getLandRightsNumbe() {
		return landRightsNumbe;
	}

	public void setLandRightsNumbe(String landRightsNumbe) {
		this.landRightsNumbe = landRightsNumbe;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLandRightsNumbeDate() {
		return landRightsNumbeDate;
	}

	public void setLandRightsNumbeDate(Date landRightsNumbeDate) {
		this.landRightsNumbeDate = landRightsNumbeDate;
	}

	public PropertyDict getPropertyTypes() {
		return propertyTypes;
	}

	public void setPropertyTypes(PropertyDict propertyTypes) {
		this.propertyTypes = propertyTypes;
	}

	public PropertyDict getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(PropertyDict certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNumbe() {
		return certificateNumbe;
	}

	public void setCertificateNumbe(String certificateNumbe) {
		this.certificateNumbe = certificateNumbe;
	}

	public String getPropertyPersonTel() {
		return propertyPersonTel;
	}

	public void setPropertyPersonTel(String propertyPersonTel) {
		this.propertyPersonTel = propertyPersonTel;
	}

	public String getPropertyPersonMobile() {
		return propertyPersonMobile;
	}

	public void setPropertyPersonMobile(String propertyPersonMobile) {
		this.propertyPersonMobile = propertyPersonMobile;
	}

	public PropertyDict getUsage() {
		return usage;
	}

	public void setUsage(PropertyDict usage) {
		this.usage = usage;
	}

	public String getHouseFileNum() {
		return houseFileNum;
	}

	public void setHouseFileNum(String houseFileNum) {
		this.houseFileNum = houseFileNum;
	}

	public PropertyDict getLessorType() {
		return lessorType;
	}

	public void setLessorType(PropertyDict lessorType) {
		this.lessorType = lessorType;
	}

	public PropertyDict getRentalCertificateType() {
		return rentalCertificateType;
	}

	public void setRentalCertificateType(PropertyDict rentalCertificateType) {
		this.rentalCertificateType = rentalCertificateType;
	}

	public String getRentalCertificateNumbe() {
		return rentalCertificateNumbe;
	}

	public void setRentalCertificateNumbe(String rentalCertificateNumbe) {
		this.rentalCertificateNumbe = rentalCertificateNumbe;
	}

	public String getRentalTelephone() {
		return rentalTelephone;
	}

	public void setRentalTelephone(String rentalTelephone) {
		this.rentalTelephone = rentalTelephone;
	}

	public String getRentalMobileNumber() {
		return rentalMobileNumber;
	}

	public void setRentalMobileNumber(String rentalMobileNumber) {
		this.rentalMobileNumber = rentalMobileNumber;
	}

	public String getLessorAddress() {
		return lessorAddress;
	}

	public void setLessorAddress(String lessorAddress) {
		this.lessorAddress = lessorAddress;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public PropertyDict getMangerTypes() {
		return mangerTypes;
	}

	public void setMangerTypes(PropertyDict mangerTypes) {
		this.mangerTypes = mangerTypes;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLessorDate() {
		return lessorDate;
	}

	public void setLessorDate(Date lessorDate) {
		this.lessorDate = lessorDate;
	}

	public Long getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Long roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Long getIsSignGuarantee() {
		return isSignGuarantee;
	}

	public void setIsSignGuarantee(Long isSignGuarantee) {
		this.isSignGuarantee = isSignGuarantee;
	}

	public Long getIsSafetyChannel() {
		return isSafetyChannel;
	}

	public void setIsSafetyChannel(Long isSafetyChannel) {
		this.isSafetyChannel = isSafetyChannel;
	}

	public Long getIsFireChannels() {
		return isFireChannels;
	}

	public void setIsFireChannels(Long isFireChannels) {
		this.isFireChannels = isFireChannels;
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

	public PropertyDict getRentalType() {
		return rentalType;
	}

	public void setRentalType(PropertyDict rentalType) {
		this.rentalType = rentalType;
	}

	public PropertyDict getRentalHouseProperty() {
		return rentalHouseProperty;
	}

	public void setRentalHouseProperty(PropertyDict rentalHouseProperty) {
		this.rentalHouseProperty = rentalHouseProperty;
	}

	public String getHiddenRectification() {
		return hiddenRectification;
	}

	public void setHiddenRectification(String hiddenRectification) {
		this.hiddenRectification = hiddenRectification;
	}

	public Integer getLimitPersons() {
		return limitPersons;
	}

	public void setLimitPersons(Integer limitPersons) {
		this.limitPersons = limitPersons;
	}

	public Double getRentMonth() {
		return rentMonth;
	}

	public void setRentMonth(Double rentMonth) {
		this.rentMonth = rentMonth;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
