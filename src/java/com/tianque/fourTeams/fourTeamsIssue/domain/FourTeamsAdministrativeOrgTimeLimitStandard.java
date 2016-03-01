package com.tianque.fourTeams.fourTeamsIssue.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/***
 * 事件绩效考核，行政部门实体类
 * 
 * @author 龙振东
 * 
 */
public class FourTeamsAdministrativeOrgTimeLimitStandard extends BaseDomain {

	/**
	 * 制定部门
	 */
	private Organization createOrg;

	/**
	 * 应用层级
	 */
	private PropertyDict useLevel;
	/**
	 * 应用层级内置编码
	 */
	private Long orgInternalId;
	/**
	 * 黄牌受理时限
	 */
	private Integer yellowLimitAccept;
	/**
	 * 黄牌办理时限
	 */
	private Integer yellowLimitHandle;
	/**
	 * 红牌受理时限
	 */
	private Integer redLimitAccept;
	/**
	 * 红牌办理时限
	 */
	private Integer redLimitHandle;
	/**
	 * 备注
	 */
	private String remark;
	/** 事件类型 */
	private Long issueDomainId;
	/** 事件类型中文名称 */
	private String issueDomainDisplayName;
	/** 事件子类中文名称 */
	private String issueTypeDisplayName;
	/** 事件子类 */
	private Long issueTypeId;

	public PropertyDict getUseLevel() {
		return useLevel;
	}

	public void setUseLevel(PropertyDict useLevel) {
		this.useLevel = useLevel;
	}

	public Long getOrgInternalId() {
		return orgInternalId;
	}

	public void setOrgInternalId(Long orgInternalId) {
		this.orgInternalId = orgInternalId;
	}

	public Integer getYellowLimitAccept() {
		return yellowLimitAccept;
	}

	public void setYellowLimitAccept(Integer yellowLimitAccept) {
		this.yellowLimitAccept = yellowLimitAccept;
	}

	public Integer getYellowLimitHandle() {
		return yellowLimitHandle;
	}

	public void setYellowLimitHandle(Integer yellowLimitHandle) {
		this.yellowLimitHandle = yellowLimitHandle;
	}

	public Integer getRedLimitAccept() {
		return redLimitAccept;
	}

	public void setRedLimitAccept(Integer redLimitAccept) {
		this.redLimitAccept = redLimitAccept;
	}

	public Integer getRedLimitHandle() {
		return redLimitHandle;
	}

	public void setRedLimitHandle(Integer redLimitHandle) {
		this.redLimitHandle = redLimitHandle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getIssueDomainId() {
		return issueDomainId;
	}

	public void setIssueDomainId(Long issueDomainId) {
		this.issueDomainId = issueDomainId;
	}

	public Long getIssueTypeId() {
		return issueTypeId;
	}

	public void setIssueTypeId(Long issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	public String getIssueDomainDisplayName() {
		return issueDomainDisplayName;
	}

	public void setIssueDomainDisplayName(String issueDomainDisplayName) {
		this.issueDomainDisplayName = issueDomainDisplayName;
	}

	public String getIssueTypeDisplayName() {
		return issueTypeDisplayName;
	}

	public void setIssueTypeDisplayName(String issueTypeDisplayName) {
		this.issueTypeDisplayName = issueTypeDisplayName;
	}

	public Organization getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(Organization createOrg) {
		this.createOrg = createOrg;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), null, null);
	}
}
