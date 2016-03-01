package com.tianque.baseInfo.rentalHouse.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

/**
 * 出租房信息
 */
public class RentalHouse extends HouseInfo {
	public RentalHouse() {
		setHouseType(BaseInfoTables.RENTALHOUSE_KEY);
	}

	/** 出租房用途 */
	private PropertyDict usage;

	/** 租赁备案证号 */
	private String houseFileNum;

	/** 出租人类型 */
	private PropertyDict lessorType;

	/** 出租人 */
	private String rentalPerson;

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

	/** 隐患等级 */
	private PropertyDict hiddenDangerLevel;

	/** 管理类别 */
	private PropertyDict mangerTypes;

	/** 出租时间 */
	private Date lessorDate;

	/** 出租间数 */
	private Long roomNumber;

	/** 月租金 */
	private Double rentMonth;

	/** 有无消防通道 */
	private Long isFireChannels;

	/** 有无安全通道 */
	private Long isSafetyChannel;

	/** 是否签订治安责任保证书 */
	private Long isSignGuarantee;

	/** 有无消防通道字符串非持久化 */
	private String isFireChannelsString;

	/** 有无安全通道字符串非持久化 */
	private String isSafetyChannelString;

	/** 是否签订治安责任保证书 字符串非持久化 */
	private String isSignGuaranteeString;
	
	/** 是否注销 */
	private Long isEmphasis;

//	/** 居住人数 */
//	private Integer memberNum;
	/** 是否有服务成员 */
	private Long hasServiceTeamMember;
	/** 最新服务时间 */
	private Date lastRecordTime;

	/** 实有房屋 id */
	private Long houseId;

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
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

	public String getRentalPerson() {
		return rentalPerson;
	}

	public void setRentalPerson(String rentalPerson) {
		this.rentalPerson = rentalPerson;
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

	public PropertyDict getHiddenDangerLevel() {
		return hiddenDangerLevel;
	}

	public void setHiddenDangerLevel(PropertyDict hiddenDangerLevel) {
		this.hiddenDangerLevel = hiddenDangerLevel;
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

	public Long getIsFireChannels() {
		return isFireChannels;
	}

	public void setIsFireChannels(Long isFireChannels) {
		this.isFireChannels = isFireChannels;
	}

	public Double getRentMonth() {
		return rentMonth;
	}

	public void setRentMonth(Double rentMonth) {
		if (null != rentMonth) {
			this.rentMonth = rentMonth;
		}
	}

	public Long getIsSafetyChannel() {
		return isSafetyChannel;
	}

	public void setIsSafetyChannel(Long isSafetyChannel) {
		this.isSafetyChannel = isSafetyChannel;
	}

	public Long getIsSignGuarantee() {
		return isSignGuarantee;
	}

	public void setIsSignGuarantee(Long isSignGuarantee) {
		this.isSignGuarantee = isSignGuarantee;
	}

//	public Integer getMemberNum() {
//		return memberNum;
//	}
//
//	public void setMemberNum(Integer memberNum) {
//		this.memberNum = memberNum;
//	}

	public Long getHasServiceTeamMember() {
		return hasServiceTeamMember;
	}

	public void setHasServiceTeamMember(Long hasServiceTeamMember) {
		this.hasServiceTeamMember = hasServiceTeamMember;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLastRecordTime() {
		return lastRecordTime;
	}

	public void setLastRecordTime(Date lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
	}

	// fateson add start
	/** 出租房类别 */
	private PropertyDict rentalType;
	/** 出租房性质 */
	private PropertyDict rentalHouseProperty;
	/** 隐患情况 */
	private String hiddenRectification;
	/** 登记日期 */
	private Date registDate;
	/** 限住人数 */
	private Integer limitPersons;

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

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Integer getLimitPersons() {
		return limitPersons;
	}

	public void setLimitPersons(Integer limitPersons) {
		this.limitPersons = limitPersons;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getHouseInfoEncryptId() {

		return BaseDomainIdEncryptUtil.encryptDomainId(this.houseId,
				super.getOrgInternalCode(), null);

	}

	public String getIsFireChannelsString() {
		return isFireChannelsString;
	}

	public void setIsFireChannelsString(String isFireChannelsString) {
		this.isFireChannelsString = isFireChannelsString;
	}

	public String getIsSafetyChannelString() {
		return isSafetyChannelString;
	}

	public void setIsSafetyChannelString(String isSafetyChannelString) {
		this.isSafetyChannelString = isSafetyChannelString;
	}

	public String getIsSignGuaranteeString() {
		return isSignGuaranteeString;
	}

	public void setIsSignGuaranteeString(String isSignGuaranteeString) {
		this.isSignGuaranteeString = isSignGuaranteeString;
	}
	


}
