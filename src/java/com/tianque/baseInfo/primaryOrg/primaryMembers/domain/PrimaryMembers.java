package com.tianque.baseInfo.primaryOrg.primaryMembers.domain;

import java.util.Date;
import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class PrimaryMembers extends BaseDomain {

	private Organization org;

	private String orgCode;
	/** 姓名 */
	private String name;
	/** 性别 */
	private PropertyDict gender;
	/** 出生日期 */
	private Date birthday;
	/** 职务 */
	private PropertyDict dutyId;
	/** 部门党委等级 */
	private PropertyDict departmentPartyLevel;
	/** 职位 */
	private String position;
	/** 手机 */
	private String mobile;
	/** 电话 */
	private String telephone;
	/** 出生年 */
	private Long year;
	/** 出生年 */
	private Long years;
	/** 现有成员卸任成员判断 */
	private Long isHaveJob;
	/** 备注 */
	private String remark;
	/** 身份证号 */
	private String idcardNo;

	/** 排序字段 */
	private Integer seq;

	/** 名族 */
	private PropertyDict nation;

	/** 政治面貌 */
	private PropertyDict politicalBackground;

	/** 文化程度 */
	private PropertyDict schooling;

	/** 组织大类 */
	private List<PrimaryMembersOrgType> primaryMembersOrgTypes;

	private String primaryOrgName;

	/** 组织名称 */
	private List<String> primaryMembersOrgName;
	/** 更新是页面显示用所在组织ID */
	private String primaryOrgIds;
	/** 更新是页面显示用所在组织名 */
	private String primaryOrgNames;
	/** 仅显示本级、所有下辖、直属下辖 */
	private String displayLevel;
	/** 关联表id（一个组织和一个人员的关联表的Id(是唯一的)）为排序做准备 */
	private Long relevantId;

	public String getPrimaryOrgIds() {
		return primaryOrgIds;
	}

	public void setPrimaryOrgIds(String primaryOrgIds) {
		this.primaryOrgIds = primaryOrgIds;
	}

	public String getPrimaryOrgNames() {
		return primaryOrgNames;
	}

	public void setPrimaryOrgNames(String primaryOrgNames) {
		this.primaryOrgNames = primaryOrgNames;
	}

	public List<String> getPrimaryMembersOrgName() {
		return primaryMembersOrgName;
	}

	public void setPrimaryMembersOrgName(List<String> primaryMembersOrgName) {
		this.primaryMembersOrgName = primaryMembersOrgName;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public PropertyDict getDutyId() {
		return dutyId;
	}

	public void setDutyId(PropertyDict dutyId) {
		this.dutyId = dutyId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getIsHaveJob() {
		return isHaveJob;
	}

	public void setIsHaveJob(Long isHaveJob) {
		this.isHaveJob = isHaveJob;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public PropertyDict getNation() {
		return nation;
	}

	public void setNation(PropertyDict nation) {
		this.nation = nation;
	}

	public PropertyDict getPoliticalBackground() {
		return politicalBackground;
	}

	public void setPoliticalBackground(PropertyDict politicalBackground) {
		this.politicalBackground = politicalBackground;
	}

	public PropertyDict getSchooling() {
		return schooling;
	}

	public void setSchooling(PropertyDict schooling) {
		this.schooling = schooling;
	}

	public List<PrimaryMembersOrgType> getPrimaryMembersOrgTypes() {
		return primaryMembersOrgTypes;
	}

	public void setPrimaryMembersOrgTypes(
			List<PrimaryMembersOrgType> primaryMembersOrgTypes) {
		this.primaryMembersOrgTypes = primaryMembersOrgTypes;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Long getYears() {
		return years;
	}

	public void setYears(Long years) {
		this.years = years;
	}

	public String getPrimaryOrgName() {
		return primaryOrgName;
	}

	public void setPrimaryOrgName(String primaryOrgName) {
		this.primaryOrgName = primaryOrgName;
	}

	public PropertyDict getDepartmentPartyLevel() {
		return departmentPartyLevel;
	}

	public void setDepartmentPartyLevel(PropertyDict departmentPartyLevel) {
		this.departmentPartyLevel = departmentPartyLevel;
	}

	public String getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(String displayLevel) {
		this.displayLevel = displayLevel;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.orgCode, null);
	}

	public Long getRelevantId() {
		return relevantId;
	}

	public void setRelevantId(Long relevantId) {
		this.relevantId = relevantId;
	}

}
