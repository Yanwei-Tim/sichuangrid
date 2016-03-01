package com.tianque.partyBuilding.organizations.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 两新组织党组织表:实体类(PARTY_ORG_MEMBER)
 * 
 * @author
 * @date 2014-01-14 15:44:08
 */
public class PartyOrgMember extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	private Organization organization;
	/** 联系电话(TELEPHONE) */
	private String telephone;
	/** 联系手机(MOBILENUMBER) */
	private String mobileNumber;
	/** (ORGID) */
	private Long orgId;
	/** 党组织表id(PARTYORGID) */
	private Long partyOrgId;
	/** (ORGINTERNALCODE) */
	private String orgInternalCode;
	/** 姓名(NAME) */
	private String name;

	public PartyOrgMember() {

	}

	public PartyOrgMember(Date creatDate, Date updateDate, String telephone, String mobileNumber, Long orgId,
			Long partyOrgId, String orgInternalCode, String name, String createUser, String updateUser) {
		this.telephone = telephone;
		this.mobileNumber = mobileNumber;
		this.orgId = orgId;
		this.partyOrgId = partyOrgId;
		this.orgInternalCode = orgInternalCode;
		this.name = name;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/** get 联系电话(telephone) */
	public String getTelephone() {
		return telephone;
	}

	/** set 联系电话(TELEPHONE) */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/** get 联系手机(mobileNumber) */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/** set 联系手机(MOBILENUMBER) */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/** get (orgId) */
	public Long getOrgId() {
		return orgId;
	}

	/** set (ORGID) */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/** get 党组织表id(partyOrgId) */
	public Long getPartyOrgId() {
		return partyOrgId;
	}

	/** set 党组织表id(PARTYORGID) */
	public void setPartyOrgId(Long partyOrgId) {
		this.partyOrgId = partyOrgId;
	}

	/** get (orgInternalCode) */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set (ORGINTERNALCODE) */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	/** get 姓名(name) */
	public String getName() {
		return name;
	}

	/** set 姓名(NAME) */
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}
}
