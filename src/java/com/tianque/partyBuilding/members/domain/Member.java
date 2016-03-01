package com.tianque.partyBuilding.members.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 成员信息
 * 
 * @author yulei
 * 
 */
public class Member extends BaseDomain {
	/** 身份证号码 */
	private String idCardNo;
	/** 姓名 */
	private String name;
	/** 性别 */
	private PropertyDict gender;
	/** 民族 */
	private PropertyDict nation;
	/** 出生日期 */
	private Date birthday;
	/** 学历 */
	private PropertyDict schooling;
	/** 学位 */
	private String degree;
	/** 专业技术职务 */
	private String specialPosition;
	/** 联系电话 */
	private String telephone;
	/** 联系手机 */
	private String mobileNumber;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 县 */
	private String district;
	/** 现居地址 */
	private String nativePlaceAddress;
	/** 所属部门 */
	private PropertyDict belongOrg;
	/** 所在党支部 */
	private String partyOrg;
	/** 党内职务 */
	private PropertyDict partyPosition;
	/** 入党日期 */
	private Date joinPartyDate;
	/** 单位、职务或职业 */
	private String career;
	/** 党费交纳至 */
	private Date endDate;
	/** 奖惩情况 */
	private String rewardAndPunishment;
	/** 参加组织生活情况 */
	private String accedingSituation;
	/** 进入当前党支部日期 */
	private Date joinPartyBranchDate;
	/** 民主评议情况 */
	private PropertyDict democracy;
	/** 是否困难党员 */
	private Boolean isHandicap;
	/** 是否建国前老党员 */
	private Boolean isOld;
	/** 是否出国出境 */
	private Boolean isOversea;
	/** 双报到至指定组织机构 */
	private Organization reportOrganization;
	/** 是否注销 */
	private Integer logOut;
	/** 党组织类别 */
	private Integer partyOrgType;
	/** 图片链接地址 */
	private String imgUrl;
	private Long associateId;

	private Long orgId;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public PropertyDict getNation() {
		return nation;
	}

	public void setNation(PropertyDict nation) {
		this.nation = nation;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public PropertyDict getSchooling() {
		return schooling;
	}

	public void setSchooling(PropertyDict schooling) {
		this.schooling = schooling;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getSpecialPosition() {
		return specialPosition;
	}

	public void setSpecialPosition(String specialPosition) {
		this.specialPosition = specialPosition;
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

	public PropertyDict getBelongOrg() {
		return belongOrg;
	}

	public void setBelongOrg(PropertyDict belongOrg) {
		this.belongOrg = belongOrg;
	}

	public String getPartyOrg() {
		return partyOrg;
	}

	public void setPartyOrg(String partyOrg) {
		this.partyOrg = partyOrg;
	}

	public PropertyDict getPartyPosition() {
		return partyPosition;
	}

	public void setPartyPosition(PropertyDict partyPosition) {
		this.partyPosition = partyPosition;
	}

	public Date getJoinPartyDate() {
		return joinPartyDate;
	}

	public void setJoinPartyDate(Date joinPartyDate) {
		this.joinPartyDate = joinPartyDate;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRewardAndPunishment() {
		return rewardAndPunishment;
	}

	public void setRewardAndPunishment(String rewardAndPunishment) {
		this.rewardAndPunishment = rewardAndPunishment;
	}

	public String getAccedingSituation() {
		return accedingSituation;
	}

	public void setAccedingSituation(String accedingSituation) {
		this.accedingSituation = accedingSituation;
	}

	public Date getJoinPartyBranchDate() {
		return joinPartyBranchDate;
	}

	public void setJoinPartyBranchDate(Date joinPartyBranchDate) {
		this.joinPartyBranchDate = joinPartyBranchDate;
	}

	public PropertyDict getDemocracy() {
		return democracy;
	}

	public void setDemocracy(PropertyDict democracy) {
		this.democracy = democracy;
	}

	public Boolean getIsHandicap() {
		return isHandicap;
	}

	public void setIsHandicap(Boolean isHandicap) {
		this.isHandicap = isHandicap;
	}

	public Boolean getIsOld() {
		return isOld;
	}

	public void setIsOld(Boolean isOld) {
		this.isOld = isOld;
	}

	public Boolean getIsOversea() {
		return isOversea;
	}

	public void setIsOversea(Boolean isOversea) {
		this.isOversea = isOversea;
	}

	public Organization getReportOrganization() {
		return reportOrganization;
	}

	public void setReportOrganization(Organization reportOrganization) {
		this.reportOrganization = reportOrganization;
	}

	public Integer getLogOut() {
		return logOut;
	}

	public void setLogOut(Integer logOut) {
		this.logOut = logOut;
	}

	public Integer getPartyOrgType() {
		return partyOrgType;
	}

	public void setPartyOrgType(Integer partyOrgType) {
		this.partyOrgType = partyOrgType;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(Long associateId) {
		this.associateId = associateId;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(), null,
				null);
	}
}
