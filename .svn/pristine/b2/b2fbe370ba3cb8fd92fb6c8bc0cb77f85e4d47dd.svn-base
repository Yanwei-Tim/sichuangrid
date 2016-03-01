package com.tianque.integrativeQuery.population.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.familyInfo.domain.GroupFamily;
import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 人的基本信息的查询
 */
public class PopulationBaseQueryVo extends BaseDomain {
	/** 姓名 */
	private String name;
	/** 身份证号 */
	private String idCardNo;
	/** 出生日期 */
	private Date birthday;
	/** 邮箱 */
	private String email;
	/** 所属网格 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 手机号码 */
	private String mobileNumber;
	/** 工作单位 */
	private String workUnit;
	/** 住房id（以房管人使用）不保存 */
	private Long houseId;
	/** 住房编号（流口根据编号自动填充房屋信息用） */
	private String houseCode;
	/** 常住地址类型 */
	private PropertyDict currentAddressType;
	/** 小区 */
	private String community;
	/** 幢 */
	private String block;
	/** 单元 */
	private String unit;
	/** 室 */
	private String room;
	/** 常住地址为其他类型 */
	private String currentAddress;
	/** 备注 */
	private String remark;
	/** 固定电话 */
	private String telephone;
	/** 图像路径 */
	private String imgUrl;
	/** 全拼 */
	private String fullPinyin;
	/** 简拼 */
	private String simplePinyin;
	/** 性别 */
	private PropertyDict gender;
	/** 户籍地详址 */
	private String nativePlaceAddress;
	/** 身高 */
	private Long stature;
	/** 是否有房屋 */
	private Boolean isHaveHouse;
	/** 无房原因 */
	private String noHouseReason;
	/** 其它地址 */
	private String otherAddress;
	/** 职业 */
	private PropertyDict career;
	/** 是否死亡 */
	private boolean isDeath;
	/** 政治面貌 */
	private PropertyDict politicalBackground;
	/** 婚姻状况 */
	private PropertyDict maritalState;
	/** 曾用名 */
	private String usedName;
	/** 户口类别 */
	private PropertyDict residenceType;
	/** 民族 */
	private PropertyDict nation;
	/** 宗教信仰 */
	private PropertyDict faith;
	/** 文化程度 */
	private PropertyDict schooling;
	/** 血型 */
	private PropertyDict bloodType;
	/** 户籍地 */
	/** 户籍地派出所 */
	private String nativePoliceStation;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 区县 */
	private String district;
	/** 户籍地址 */
	private String nativeLocation;
	/** 家庭信息 */
	private GroupFamily groupFamily;

	private LogoutDetail logoutDetail;

	public Boolean getIsHaveHouse() {
		return isHaveHouse;
	}

	public void setIsHaveHouse(Boolean isHaveHouse) {
		this.isHaveHouse = isHaveHouse;
	}

	public String getNoHouseReason() {
		return noHouseReason;
	}

	public void setNoHouseReason(String noHouseReason) {
		this.noHouseReason = noHouseReason;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
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

	@JSON(format = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
	}

	public Long getStature() {
		return stature;
	}

	public void setStature(Long stature) {
		this.stature = stature;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
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

	public void setCurrentAddressType(PropertyDict currentAddressType) {
		this.currentAddressType = currentAddressType;
	}

	public PropertyDict getCurrentAddressType() {
		return currentAddressType;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getOtherAddress() {
		return otherAddress;
	}

	public void setOtherAddress(String otherAddress) {
		this.otherAddress = otherAddress;
	}

	public PropertyDict getCareer() {
		return career;
	}

	public void setCareer(PropertyDict career) {
		this.career = career;
	}

	public boolean isDeath() {
		return isDeath;
	}

	public void setDeath(boolean isDeath) {
		this.isDeath = isDeath;
	}

	public PropertyDict getPoliticalBackground() {
		return politicalBackground;
	}

	public void setPoliticalBackground(PropertyDict politicalBackground) {
		this.politicalBackground = politicalBackground;
	}

	public PropertyDict getMaritalState() {
		return maritalState;
	}

	public void setMaritalState(PropertyDict maritalState) {
		this.maritalState = maritalState;
	}

	public String getUsedName() {
		return usedName;
	}

	public void setUsedName(String usedName) {
		this.usedName = usedName;
	}

	public PropertyDict getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(PropertyDict residenceType) {
		this.residenceType = residenceType;
	}

	public PropertyDict getNation() {
		return nation;
	}

	public void setNation(PropertyDict nation) {
		this.nation = nation;
	}

	public PropertyDict getFaith() {
		return faith;
	}

	public void setFaith(PropertyDict faith) {
		this.faith = faith;
	}

	public PropertyDict getSchooling() {
		return schooling;
	}

	public void setSchooling(PropertyDict schooling) {
		this.schooling = schooling;
	}

	public PropertyDict getBloodType() {
		return bloodType;
	}

	public void setBloodType(PropertyDict bloodType) {
		this.bloodType = bloodType;
	}

	public String getNativePoliceStation() {
		return nativePoliceStation;
	}

	public void setNativePoliceStation(String nativePoliceStation) {
		this.nativePoliceStation = nativePoliceStation;
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

	public String getNativeLocation() {
		return nativeLocation;
	}

	public void setNativeLocation(String nativeLocation) {
		this.nativeLocation = nativeLocation;
	}

	public GroupFamily getGroupFamily() {
		return groupFamily;
	}

	public void setGroupFamily(GroupFamily groupFamily) {
		this.groupFamily = groupFamily;
	}

	public LogoutDetail getLogoutDetail() {
		return logoutDetail;
	}

	public void setLogoutDetail(LogoutDetail logoutDetail) {
		this.logoutDetail = logoutDetail;
	}

}
