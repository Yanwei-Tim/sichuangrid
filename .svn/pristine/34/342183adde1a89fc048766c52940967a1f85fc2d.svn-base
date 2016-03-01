package com.tianque.partyBuilding.organizations.domain;

import java.io.Serializable;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 两新组织党组织表:实体类(NEW_PARTY_ORG)
 * 
 * @author
 * @date 2014-01-14 15:44:08
 */
public class NewPartyOrg extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	private Organization organization;
	/** (ORGID) */
	private Long orgId;
	/** 党组织类别(TYPE) */
	private PropertyDict type;
	/** (ORGINTERNALCODE) */
	private String orgInternalCode;
	/** 组织名称(NAME) */
	private String name;
	/** 地址(ADDRESS) */
	private String address;
	/** 备注(REMARK) */
	private String remark;
	/** 成员人数 */
	private Integer memberNum;

	public NewPartyOrg() {

	}

	public NewPartyOrg(Long orgId, PropertyDict type, String orgInternalCode,
			String name, String address, String remark) {
		this.orgId = orgId;
		this.type = type;
		this.orgInternalCode = orgInternalCode;
		this.name = name;
		this.address = address;
		this.remark = remark;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/** get (orgId) */
	public Long getOrgId() {
		return orgId;
	}

	/** set (ORGID) */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/** get 党组织类别(type) */
	public PropertyDict getType() {
		return type;
	}

	/** set 党组织类别(TYPE) */
	public void setType(PropertyDict type) {
		this.type = type;
	}

	/** get (orgInternalCode) */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set (ORGINTERNALCODE) */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	/** get 组织名称(name) */
	public String getName() {
		return name;
	}

	/** set 组织名称(NAME) */
	public void setName(String name) {
		this.name = name;
	}

	/** get 地址(address) */
	public String getAddress() {
		return address;
	}

	/** set 地址(ADDRESS) */
	public void setAddress(String address) {
		this.address = address;
	}

	/** get 备注(remark) */
	public String getRemark() {
		return remark;
	}

	/** set 备注(REMARK) */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
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
