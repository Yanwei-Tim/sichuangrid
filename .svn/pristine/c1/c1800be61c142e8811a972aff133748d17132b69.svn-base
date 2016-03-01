package com.tianque.baseInfo.base.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;
import org.mongodb.morphia.annotations.NotSaved;

import com.tianque.baseInfo.familyInfo.domain.GroupFamily;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.PropertyDict;

/**
 * 国人
 * 
 * @author tqkaifa
 */
public class Countrymen extends People {

	@NotSaved
	private String sid;
	/** 实口类型 */
	@NotSaved
	private String actualPopulationType;
	/** 关注人员类型中文名 */
	@NotSaved
	private String attentionPopulationTypeCname;
	/** 其它地址 */
	private String otherAddress;
	/** 职业 */
	private PropertyDict career;
	/** 是否死亡 */
	private boolean isDeath;
	/** 政治面貌 */
	//@Property("political")
	private PropertyDict politicalBackground;
	/** 婚姻状况 */
	private PropertyDict maritalState;
	/** 曾用名 */
	private String usedName;
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
	@NotSaved
	private String nativePoliceStation;
	/** 省 */
	private String province;
	/** 市 */
	@NotSaved
	private String city;
	/** 区县 */
	@NotSaved
	private String district;
	/** 户籍地址 */
	@NotSaved
	private String nativeLocation;
	/** 家庭信息 */
	@NotSaved
	private GroupFamily groupFamily;
	/** 是否有服务成员 */
	@NotSaved
	private Long hasServiceTeamMember;
	/** 最新服务时间 */
	@NotSaved
	private Date lastRecordTime;
	private LogoutDetail logoutDetail;
	/** 业务人员类型 */
	@NotSaved
	private String populationType;
	/** 基础信息ID */
	@NotSaved
	private Long baseInfoId;
	/** 地址信息ID */
	@NotSaved
	private Long addressInfoId;

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public String getAttentionPopulationTypeCname() {
		return attentionPopulationTypeCname;
	}

	public void setAttentionPopulationTypeCname(
			String attentionPopulationTypeCname) {
		this.attentionPopulationTypeCname = attentionPopulationTypeCname;
	}

	public String getActualPopulationType() {
		return actualPopulationType;
	}

	public void setActualPopulationType(String actualPopulationType) {
		this.actualPopulationType = actualPopulationType;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLastRecordTime() {
		return lastRecordTime;
	}

	public void setLastRecordTime(Date lastRecordTime) {
		this.lastRecordTime = lastRecordTime;
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

	public String getNativePoliceStation() {
		return nativePoliceStation;
	}

	public void setNativePoliceStation(String nativePoliceStation) {
		this.nativePoliceStation = nativePoliceStation;
	}

	public String getDetailNativeAddress() {
		StringBuffer sb = new StringBuffer(
				this.getProvince() != null ? this.getProvince() : "");
		sb.append(this.getCity() != null ? this.getCity() : "").append(
				this.getDistrict() != null ? this.getDistrict() : "");
		return sb.toString();
	}

	public void setNativeLocation(String nativeLocation) {
		this.nativeLocation = nativeLocation;
	}

	public String getNativeLocation() {
		return nativeLocation;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setGroupFamily(GroupFamily groupFamily) {
		this.groupFamily = groupFamily;
	}

	public GroupFamily getGroupFamily() {
		return groupFamily;
	}

	public LogoutDetail getLogoutDetail() {
		return logoutDetail;
	}

	public Long getHasServiceTeamMember() {
		return hasServiceTeamMember;
	}

	public void setHasServiceTeamMember(Long hasServiceTeamMember) {
		this.hasServiceTeamMember = hasServiceTeamMember;
	}

	public void setLogoutDetail(LogoutDetail logoutDetail) {
		this.logoutDetail = logoutDetail;
	}

	public Long getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(Long baseInfoId) {
		this.baseInfoId = baseInfoId;
	}

	public Long getAddressInfoId() {
		return addressInfoId;
	}

	public void setAddressInfoId(Long addressInfoId) {
		this.addressInfoId = addressInfoId;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				super.getOrgInternalCode(), null);
	}
}
