package com.tianque.baseInfo.primaryOrg.domain;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.vo.BaseVo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class PrimaryOrgMemberVo extends BaseVo implements Serializable {
	/** 成员id */
	private Long id;
	/** 组织名称 */
	private String name;
	/** 组织名称 */
	private String mobile;
	/** 所属组织id */
	private Long primaryOrgId;
	/** 组织机构 **/
	private Organization org;
	/** 性别 **/
	private PropertyDict gender;
	/** 职务ID **/
	private PropertyDict dutyId;
	/** 职位 **/
	private String position;
	private String telephone;
	/** 创建用户 */
	private String createUser;
	/** 修改用户 */
	private String updateUser;
	/** 创建日期 */
	private Date createDate;
	/** 修改日期 */
	private Date updateDate;
	/** 所属组织id */
	private Long isHaveJob;
	/** 备注 **/
	private String remark;
	/** 记录数 */
	private int recordNum;
	/** 搜索成员姓名或手机 */
	private String fastSearchKeyWords;

	/**
	 * 出生年份
	 */
	private Long year;

	private String idcardNo;

	/** 排序字段 */
	private Integer seq;

	/** 是否是四级平台 */
	private Long isFourLevelPlatform;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	public int getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
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

	public String getFastSearchKeyWords() {
		return fastSearchKeyWords;
	}

	public void setFastSearchKeyWords(String fastSearchKeyWords) {
		this.fastSearchKeyWords = fastSearchKeyWords;
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

}