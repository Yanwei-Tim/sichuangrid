package com.tianque.baseInfo.primaryOrg.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;

/**
 * 四级平台表:实体类(FOURLEVELPLATFORM)
 * 
 * @author
 * @date 2014-03-10 09:38:17
 */
public class FourLevelPlatform extends com.tianque.core.base.BaseDomain
		implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 所属网格(ORGID) */
	private Organization organization;
	/** 排序(SEQ) */
	private Long seq;
	/** 所属网格编号(ORGINTERNALCODE) */
	private String orgInternalCode;
	/** 平台名称(NAME) */
	private String name;
	/** 组织类型(TEAMTYPE) */
	private Long teamType;
	/** 备注(REMARK) */
	private String remark;
	/** 仅显示本级、所有下辖、直属下辖 */
	private String displayLevel;
	/** 组织类型名称(TEAMTYPENAME) */
	private String teamTypeName;
	/** 成员数 */
	private Integer memberNum;

	/** 参与管理数 */
	private Integer fourthAccount;

	private String supervisorydepartment;

	public FourLevelPlatform() {

	}

	public FourLevelPlatform(Organization organization, Long seq,
			String orgInternalCode, String name, Long teamType, String remark,
			String teamTypeName) {
		this.organization = organization;
		this.seq = seq;
		this.orgInternalCode = orgInternalCode;
		this.name = name;
		this.teamType = teamType;
		this.remark = remark;
		this.teamTypeName = teamTypeName;
	}

	/** get 所属网格(orgId) */
	public Organization getOrganization() {
		return organization;
	}

	/** set 所属网格(ORGID) */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/** get 排序(seq) */
	public Long getSeq() {
		return seq;
	}

	/** set 排序(SEQ) */
	public void setSeq(Long seq) {
		this.seq = seq;
	}

	/** get 所属网格编号(orgInternalCode) */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号(ORGINTERNALCODE) */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	/** get 平台名称(name) */
	public String getName() {
		return name;
	}

	/** set 平台名称(NAME) */
	public void setName(String name) {
		this.name = name;
	}

	/** get 组织类型(teamType) */
	public Long getTeamType() {
		return teamType;
	}

	/** set 组织类型(TEAMTYPE) */
	public void setTeamType(Long teamType) {
		this.teamType = teamType;
	}

	/** get 备注(remark) */
	public String getRemark() {
		return remark;
	}

	/** set 备注(REMARK) */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(String displayLevel) {
		this.displayLevel = displayLevel;
	}

	public String getTeamTypeName() {
		return teamTypeName;
	}

	public void setTeamTypeName(String teamTypeName) {
		this.teamTypeName = teamTypeName;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	public Integer getFourthAccount() {
		return fourthAccount;
	}

	public void setFourthAccount(Integer fourthAccount) {
		this.fourthAccount = fourthAccount;
	}

	public String getSupervisorydepartment() {
		return supervisorydepartment;
	}

	public void setSupervisorydepartment(String supervisorydepartment) {
		this.supervisorydepartment = supervisorydepartment;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

	public String getEncryptId() {
		String orgCode = this.orgInternalCode;
		if (!StringUtil.isStringAvaliable(orgCode) && organization != null) {
			orgCode = this.organization.getOrgInternalCode();
		}
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), orgCode, null);
	}
}
