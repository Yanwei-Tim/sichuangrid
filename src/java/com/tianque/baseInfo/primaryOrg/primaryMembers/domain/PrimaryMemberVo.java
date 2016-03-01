package com.tianque.baseInfo.primaryOrg.primaryMembers.domain;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.vo.BaseVo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class PrimaryMemberVo extends BaseVo implements Serializable {

	// 是否高级搜索
	private String primayOrgSerach;

	private String isPrimaryMember;

	/** 成员名称 */
	private String name;
	/** 联系手机号 */
	private String mobile;
	/** 联系电话 */
	private String telephone;
	/** 所属组织id */
	private Long primaryOrgId;
	/** 性别 **/
	private PropertyDict gender;
	/** 出生日期 **/
	private Date birthday;
	/** 政治面貌 */
	private PropertyDict politicalBackground;
	/** 文化程度 */
	private PropertyDict schooling;

	/** 身份证号 */
	private String idcardNo;
	/** 名族 */
	private PropertyDict nation;

	/** 组织机构 **/
	private Organization org;

	/** 职务ID **/
	private PropertyDict dutyId;

	// /** 所属组织id */
	private Long isHaveJob;

	/** 是否是四级平台 */
	private Long isFourLevelPlatform;

	/** 仅显示本级、所有下辖、直属下辖 */
	private String displayLevel;

	// 高级搜索时的组织id
	private String primaryOrgIds;
	private String primaryOrgName;
	// 高级搜索时的组织信息
	private String primaryorgInfo;
	// 组织对于的四级平台信息
	private String isFourLevelPlatforms;

	// 是否能否操作成员（用于查看维护成员列表时）
	private String isOperator;

	/** 搜索成员姓名或手机 */
	private String fastSearchKeyWords;

	private String orgCode;

	/** 排序字段 */
	private Integer seq;

	/** 查询条件组织大类类别 */
	private PropertyDict teamClazz;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrimaryOrgId() {
		return primaryOrgId;
	}

	public void setPrimaryOrgId(Long primaryOrgId) {
		this.primaryOrgId = primaryOrgId;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public PropertyDict getDutyId() {
		return dutyId;
	}

	public void setDutyId(PropertyDict dutyId) {
		this.dutyId = dutyId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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

	public Long getIsFourLevelPlatform() {
		return isFourLevelPlatform;
	}

	public void setIsFourLevelPlatform(Long isFourLevelPlatform) {
		this.isFourLevelPlatform = isFourLevelPlatform;
	}

	public String getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(String displayLevel) {
		this.displayLevel = displayLevel;
	}

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
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

	public Long getIsHaveJob() {
		return isHaveJob;
	}

	public void setIsHaveJob(Long isHaveJob) {
		this.isHaveJob = isHaveJob;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getPrimayOrgSerach() {
		return primayOrgSerach;
	}

	public void setPrimayOrgSerach(String primayOrgSerach) {
		this.primayOrgSerach = primayOrgSerach;
	}

	public String getPrimaryOrgIds() {
		return primaryOrgIds;
	}

	public void setPrimaryOrgIds(String primaryOrgIds) {
		this.primaryOrgIds = primaryOrgIds;
	}

	public String getPrimaryOrgName() {
		return primaryOrgName;
	}

	public void setPrimaryOrgName(String primaryOrgName) {
		this.primaryOrgName = primaryOrgName;
	}

	public String getIsPrimaryMember() {
		return isPrimaryMember;
	}

	public void setIsPrimaryMember(String isPrimaryMember) {
		this.isPrimaryMember = isPrimaryMember;
	}

	public String getIsOperator() {
		return isOperator;
	}

	public void setIsOperator(String isOperator) {
		this.isOperator = isOperator;
	}

	public String getPrimaryorgInfo() {
		return primaryorgInfo;
	}

	public void setPrimaryorgInfo(String primaryorgInfo) {
		this.primaryorgInfo = primaryorgInfo;
	}

	public String getIsFourLevelPlatforms() {
		return isFourLevelPlatforms;
	}

	public void setIsFourLevelPlatforms(String isFourLevelPlatforms) {
		this.isFourLevelPlatforms = isFourLevelPlatforms;
	}

	public PropertyDict getTeamClazz() {
		return teamClazz;
	}

	public void setTeamClazz(PropertyDict teamClazz) {
		this.teamClazz = teamClazz;
	}

}