package com.tianque.partyBuilding.doubleRegActivities.domain;

import java.util.Date;

import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 党组织报到表:实体类(PARTYORG_REPORT)
 * 
 * @author
 * @date 2014-02-25 11:13:54
 */
public class PartyOrgReport extends com.tianque.core.base.BaseDomain implements
		java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 注销时间(LOGOUTTIME) */
	private java.util.Date logOutTime;
	/** 所属网格(ORGID) */
	private Organization organization;
	/** 党组织类别(PARTYORGTYPE) */
	private PropertyDict partyOrgType;
	/** 是否注销(ISEMPHASIS) */
	private Boolean isEmphasis;
	/** 联系电话(TELEPHONE) */
	private String telephone;
	/** 所属网格编号(ORGINTERNALCODE) */
	private String orgInternalCode;
	/** 单位名称(NAME) */
	private String name;
	/** 联系人(CONTRACTOR) */
	private String contractor;
	/** 类型(TYPE) */
	private String type;
	/** 地址(ADDRESS) */
	private String address;
	/** 注销原因(LOGOUTREASON) */
	private String logOutReason;
	/** 认领服务项目(CLAIMSERVICEPROJECTIDS) */
	private String claimServiceProjectIds;
	/** 备注(REMARK) */
	private String remark;

	/** 认领服务项目名称 */
	private String claimServiceProjectName;

	/** 是否认领项目 */
	private Boolean isClaimProject;

	public PartyOrgReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PartyOrgReport(Date logOutTime, Organization organization,
			PropertyDict partyOrgType, Boolean isEmphasis, String telephone,
			String orgInternalCode, String name, String contractor,
			String type, String address, String logOutReason,
			String claimServiceProjectIds, String remark,
			String claimServiceProjectName) {
		this.logOutTime = logOutTime;
		this.organization = organization;
		this.partyOrgType = partyOrgType;
		this.isEmphasis = isEmphasis;
		this.telephone = telephone;
		this.orgInternalCode = orgInternalCode;
		this.name = name;
		this.contractor = contractor;
		this.type = type;
		this.address = address;
		this.logOutReason = logOutReason;
		this.claimServiceProjectIds = claimServiceProjectIds;
		this.remark = remark;
		this.claimServiceProjectName = claimServiceProjectName;
	}

	/** get 注销时间(logOutTime) */
	@JSON(format = "yyyy-MM-dd")
	public java.util.Date getLogOutTime() {
		return logOutTime;
	}

	/** set 注销时间(LOGOUTTIME) */
	public void setLogOutTime(java.util.Date logOutTime) {
		this.logOutTime = logOutTime;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/** get 党组织类别(partyOrgType) */
	public PropertyDict getPartyOrgType() {
		return partyOrgType;
	}

	/** set 党组织类别(PARTYORGTYPE) */
	public void setPartyOrgType(PropertyDict partyOrgType) {
		this.partyOrgType = partyOrgType;
	}

	/** get 是否注销(isEmphasis) */
	public Boolean getIsEmphasis() {
		return isEmphasis;
	}

	/** set 是否注销(ISEMPHASIS) */
	public void setIsEmphasis(Boolean isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	/** get 联系电话(telephone) */
	public String getTelephone() {
		return telephone;
	}

	/** set 联系电话(TELEPHONE) */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/** get 所属网格编号(orgInternalCode) */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号(ORGINTERNALCODE) */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	/** get 单位名称(name) */
	public String getName() {
		return name;
	}

	/** set 单位名称(NAME) */
	public void setName(String name) {
		this.name = name;
	}

	/** get 联系人(contractor) */
	public String getContractor() {
		return contractor;
	}

	/** set 联系人(CONTRACTOR) */
	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	/** get 类型(type) */
	public String getType() {
		return type;
	}

	/** set 类型(TYPE) */
	public void setType(String type) {
		this.type = type;
	}

	/** get 地址(address) */
	public String getAddress() {
		return address;
	}

	/** set 地址(ADDRESS) */
	public void setAddress(String address) {
		this.address = address;
	}

	/** get 注销原因(logOutReason) */
	public String getLogOutReason() {
		return logOutReason;
	}

	/** set 注销原因(LOGOUTREASON) */
	public void setLogOutReason(String logOutReason) {
		this.logOutReason = logOutReason;
	}

	/** get 认领服务项目(claimServiceProjectIds) */
	public String getClaimServiceProjectIds() {
		return claimServiceProjectIds;
	}

	/** set 认领服务项目(CLAIMSERVICEPROJECTIDS) */
	public void setClaimServiceProjectIds(String claimServiceProjectIds) {
		this.claimServiceProjectIds = claimServiceProjectIds;
	}

	/** get 备注(remark) */
	public String getRemark() {
		return remark;
	}

	/** set 备注(REMARK) */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsClaimProject() {
		return isClaimProject;
	}

	public void setIsClaimProject(Boolean isClaimProject) {
		this.isClaimProject = isClaimProject;
	}

	public String getClaimServiceProjectName() {
		return claimServiceProjectName;
	}

	public void setClaimServiceProjectName(String claimServiceProjectName) {
		this.claimServiceProjectName = claimServiceProjectName;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.orgInternalCode, null);
	}
}
