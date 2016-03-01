package com.tianque.openLayersMap.domian.vo;


import java.io.Serializable;
import java.util.Date;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 二维地图     网格员队伍管理对象
 * @author jiangjinling
 *
 */
@SuppressWarnings("serial")
public class GirdTeamVo implements Serializable{
	private Long id;
	
	/** 经度 */
	private String lon;
	
	/** 纬度 */
	private String lat;
	
	private Organization organization;//组织机构
	private String memeberName;//网格成员名称
	private String idCardNo;//身份证号码
	private PropertyDict gender;//性别
	private Date birthDate;//出生日期
	private String phoneNumber;//联系电话
	private PropertyDict positionType;//专兼职情况:专职、兼职
	private PropertyDict politicalBackground;//政治面貌
	private PropertyDict education;//文化程度
	private boolean isActivated;//是否激活
	private Date activatedTime;//激活时间

	private Date registeredDate;//登记时间
	private String remark;//备注
	private String imageUrl;//头像地址
	
	private String userDefinedGridName;//存储系统中不存在的网格名称
	
	private String orgInternalCode;
	
	private String orgFullName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getMemeberName() {
		return memeberName;
	}

	public void setMemeberName(String memeberName) {
		this.memeberName = memeberName;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public PropertyDict getPositionType() {
		return positionType;
	}

	public void setPositionType(PropertyDict positionType) {
		this.positionType = positionType;
	}

	public PropertyDict getPoliticalBackground() {
		return politicalBackground;
	}

	public void setPoliticalBackground(PropertyDict politicalBackground) {
		this.politicalBackground = politicalBackground;
	}

	public PropertyDict getEducation() {
		return education;
	}

	public void setEducation(PropertyDict education) {
		this.education = education;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public Date getActivatedTime() {
		return activatedTime;
	}

	public void setActivatedTime(Date activatedTime) {
		this.activatedTime = activatedTime;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getUserDefinedGridName() {
		return userDefinedGridName;
	}

	public void setUserDefinedGridName(String userDefinedGridName) {
		this.userDefinedGridName = userDefinedGridName;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getOrgFullName() {
		return orgFullName;
	}

	public void setOrgFullName(String orgFullName) {
		this.orgFullName = orgFullName;
	}

	
}
