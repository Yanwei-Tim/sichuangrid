package com.tianque.aidsPopulations.domain.vo;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * :SrarchVO(AIDSPOPULATIONS)
 * 
 * @author liaoshanshan
 * @date 2013-06-09 16:34:04
 */
public class SearchAidspopulationsVo extends BaseDomain {

	/** 所属责任区 */
	private Organization organization;
	/** 所属责任区编号 */
	private String orgInternalCode;
	/** 最大 出生日期 */
	private Date birthday;
	/** 最小 出生日期 */
	private Date endBirthday;
	/** 常住地址商品房 单元 */
	private String unit;
	/** 所属网格 */
	private Long orgid;
	/** 性别 */
	private Long gender;
	/** 婚姻状况 */
	private Long maritalState;
	/** 民族 */
	private Long nation;
	/** 常住地址类型 */
	private Long currentAddressType;
	/** 政治面貌 */
	private Long politicalBackground;
	/** 文化程度 */
	private Long residenceType;
	/** 宗教信仰 */
	private Long faith;
	/** 职业 */
	private Long career;
	/** 感染途径 */
	private Long infectway;
	/** 违法情况 */
	private Long violationsofthelaw;
	/** 犯罪类型 */
	private Long crimetype;
	/** 收治机构所属层级 */
	private Long receivedlevel;
	/** 帮扶情况 */
	private Long helpcircumstances;
	/** 常住地址商品房 幢 */
	private String block;
	/** 常住地址商品房 室 */
	private String room;
	/** 联系方式(手机) */
	private String mobileNumber;
	/** 实口类型 */
	private String actualPopulationType;
	/** 姓名 */
	private String name;
	/** 证件号码 */
	private String idCardNo;
	/** 曾用名\别名 */
	private String usedName;
	/** 户籍地址(省) */
	private String province;
	/** 户籍地址(市) */
	private String city;
	/** 户籍地址(县) */
	private String district;
	/** 地址编号 */
	private String addressno;
	/** 常住地址商品房 小区 */
	private String community;
	/** 收治机构 */
	private String receivedorganization;
	/** 联系方式(电话) */
	private String telephone;
	/** 户籍详址 */
	private String nativePlaceAddress;
	/** 常住地址 */
	private String currentAddress;
	/** 临时居所 */
	private String otherAddress;
	private String workUnit;
	/** 是否关注 */
	private Long isEmphasis;

	/** 检索关键字 */
	private String fastSearchKeyWords;
	/** 关注程度 */
	private Long attentionextent;
	/** 死亡状态 */
	private Long isDeath;
	/** 是否有服务成员 */
	private Long hasServiceTeamMember;
	/** 是否有服务记录 */
	private Long hasServiceTeamRecord;
	/** 文化程度 */
	private PropertyDict schooling;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getEndBirthday() {
		return endBirthday;
	}

	public void setEndBirthday(Date endBirthday) {
		this.endBirthday = endBirthday;
	}

	/** get 常住地址商品房 单元 */
	public String getUnit() {
		return unit;
	}

	/** set 常住地址商品房 单元 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/** get 所属网格 */
	public Long getOrgid() {
		return orgid;
	}

	/** set 所属网格 */
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	/** get 性别 */
	public Long getGender() {
		return gender;
	}

	/** set 性别 */
	public void setGender(Long gender) {
		this.gender = gender;
	}

	public Long getAttentionextent() {
		return attentionextent;
	}

	public void setAttentionextent(Long attentionextent) {
		this.attentionextent = attentionextent;
	}

	/** get 民族 */
	public Long getNation() {
		return nation;
	}

	/** set 民族 */
	public void setNation(Long nation) {
		this.nation = nation;
	}

	/** get 宗教信仰 */
	public Long getFaith() {
		return faith;
	}

	/** set 宗教信仰 */
	public void setFaith(Long faith) {
		this.faith = faith;
	}

	/** get 职业 */
	public Long getCareer() {
		return career;
	}

	/** set 职业 */
	public void setCareer(Long career) {
		this.career = career;
	}

	/** get 感染途径 */
	public Long getInfectway() {
		return infectway;
	}

	/** set 感染途径 */
	public void setInfectway(Long infectway) {
		this.infectway = infectway;
	}

	public Long getViolationsofthelaw() {
		return violationsofthelaw;
	}

	public void setViolationsofthelaw(Long violationsofthelaw) {
		this.violationsofthelaw = violationsofthelaw;
	}

	/** get 犯罪类型 */
	public Long getCrimetype() {
		return crimetype;
	}

	/** set 犯罪类型 */
	public void setCrimetype(Long crimetype) {
		this.crimetype = crimetype;
	}

	/** get 收治机构所属层级 */
	public Long getReceivedlevel() {
		return receivedlevel;
	}

	/** set 收治机构所属层级 */
	public void setReceivedlevel(Long receivedlevel) {
		this.receivedlevel = receivedlevel;
	}

	/** get 帮扶情况 */
	public Long getHelpcircumstances() {
		return helpcircumstances;
	}

	/** set 帮扶情况 */
	public void setHelpcircumstances(Long helpcircumstances) {
		this.helpcircumstances = helpcircumstances;
	}

	/** get 常住地址商品房 幢 */
	public String getBlock() {
		return block;
	}

	/** set 常住地址商品房 幢 */
	public void setBlock(String block) {
		this.block = block;
	}

	/** get 常住地址商品房 室 */
	public String getRoom() {
		return room;
	}

	/** set 常住地址商品房 室 */
	public void setRoom(String room) {
		this.room = room;
	}

	/** get 实口类型 */
	public String getactualPopulationType() {
		return actualPopulationType;
	}

	/** set 实口类型 */
	public void setactualPopulationType(String actualPopulationType) {
		this.actualPopulationType = actualPopulationType;
	}

	/** get 姓名 */
	public String getName() {
		return name;
	}

	/** set 姓名 */
	public void setName(String name) {
		this.name = name;
	}

	/** get 证件号码 */
	public String getidCardNo() {
		return idCardNo;
	}

	/** set 证件号码 */
	public void setidCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	/** get 户籍地址(省) */
	public String getProvince() {
		return province;
	}

	/** set 户籍地址(省) */
	public void setProvince(String province) {
		this.province = province;
	}

	/** get 户籍地址(市) */
	public String getCity() {
		return city;
	}

	/** set 户籍地址(市) */
	public void setCity(String city) {
		this.city = city;
	}

	/** get 户籍地址(县) */
	public String getDistrict() {
		return district;
	}

	/** set 户籍地址(县) */
	public void setDistrict(String district) {
		this.district = district;
	}

	/** get 地址编号 */
	public String getAddressno() {
		return addressno;
	}

	/** set 地址编号 */
	public void setAddressno(String addressno) {
		this.addressno = addressno;
	}

	/** get 常住地址商品房 小区 */
	public String getCommunity() {
		return community;
	}

	/** set 常住地址商品房 小区 */
	public void setCommunity(String community) {
		this.community = community;
	}

	/** get 收治机构 */
	public String getReceivedorganization() {
		return receivedorganization;
	}

	/** set 收治机构 */
	public void setReceivedorganization(String receivedorganization) {
		this.receivedorganization = receivedorganization;
	}

	/** get 联系方式(电话) */
	public String getTelephone() {
		return telephone;
	}

	/** set 联系方式(电话) */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public Long getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Long isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public Long getMaritalState() {
		return maritalState;
	}

	public void setMaritalState(Long maritalState) {
		this.maritalState = maritalState;
	}

	public Long getCurrentAddressType() {
		return currentAddressType;
	}

	public void setCurrentAddressType(Long currentAddressType) {
		this.currentAddressType = currentAddressType;
	}

	public Long getPoliticalBackground() {
		return politicalBackground;
	}

	public void setPoliticalBackground(Long politicalBackground) {
		this.politicalBackground = politicalBackground;
	}

	public Long getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(Long residenceType) {
		this.residenceType = residenceType;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getActualPopulationType() {
		return actualPopulationType;
	}

	public void setActualPopulationType(String actualPopulationType) {
		this.actualPopulationType = actualPopulationType;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getUsedName() {
		return usedName;
	}

	public void setUsedName(String usedName) {
		this.usedName = usedName;
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

	public String getOtherAddress() {
		return otherAddress;
	}

	public void setOtherAddress(String otherAddress) {
		this.otherAddress = otherAddress;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
	}

	public Long getIsDeath() {
		return isDeath;
	}

	public void setIsDeath(Long isDeath) {
		this.isDeath = isDeath;
	}

	public Long getHasServiceTeamMember() {
		return hasServiceTeamMember;
	}

	public void setHasServiceTeamMember(Long hasServiceTeamMember) {
		this.hasServiceTeamMember = hasServiceTeamMember;
	}

	public Long getHasServiceTeamRecord() {
		return hasServiceTeamRecord;
	}

	public void setHasServiceTeamRecord(Long hasServiceTeamRecord) {
		this.hasServiceTeamRecord = hasServiceTeamRecord;
	}

	public PropertyDict getSchooling() {
		return schooling;
	}

	public void setSchooling(PropertyDict schooling) {
		this.schooling = schooling;
	}

	public String getAttentionPopulationType() {
		return BaseInfoTables.AIDSPOPULATIONS_KEY;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

}
