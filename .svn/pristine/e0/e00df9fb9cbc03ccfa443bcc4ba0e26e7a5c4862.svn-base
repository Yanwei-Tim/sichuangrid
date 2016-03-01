package com.tianque.partyBuilding.organizations.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 街道社区党组织表:实体类(TOWN_PARTY_ORGS)
 * 
 * @author
 * @date 2014-01-14 14:35:25
 */
public class TownPartyOrg extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	private Organization organization;
	/** 成立时间(FOUNDDATE) */
	private Date foundDate;
	/** 身份证号码(IDCARDNO) */
	private String idCardNo;
	/** 联系电话(TELEPHONE) */
	private String telephone;
	/** 联系手机(MOBILENUMBER) */
	private String mobileNumber;
	/** (ORGID) */
	private Long orgId;
	/** 党组织类型(TYPE) */
	private PropertyDict type;
	/** (ORGINTERNALCODE) */
	private String orgInternalCode;
	/** 党组织名称(NAME) */
	private String name;
	/** 党支部书记(SECRETARY) */
	private String secretary;
	/** 党组织地址(ADDRESS) */
	private String address;
	/** 支部成员 */
	private List<PartyOrgMember> members;
	/** 党员renshu */
	private Integer memberNum;

	public TownPartyOrg() {

	}

	public TownPartyOrg(Date foundDate, Date createDate, Date updateDate,
			String idCardNo, String telephone, String mobileNumber, Long orgId,
			PropertyDict type, String orgInternalCode, String name,
			String secretary, String createUser, String updateUser,
			String address) {
		this.foundDate = foundDate;
		this.idCardNo = idCardNo;
		this.telephone = telephone;
		this.mobileNumber = mobileNumber;
		this.orgId = orgId;
		this.type = type;
		this.orgInternalCode = orgInternalCode;
		this.name = name;
		this.secretary = secretary;
		this.address = address;
	}

	public List<PartyOrgMember> getMembers() {
		return members;
	}

	public void setMembers(List<PartyOrgMember> members) {
		this.members = members;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/** get 成立时间(foundDate) */
	@JSON(format = "yyyy-MM-dd")
	public Date getFoundDate() {
		return foundDate;
	}

	/** set 成立时间(FOUNDDATE) */
	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}

	/** get 身份证号码(idCardNo) */
	public String getIdCardNo() {
		return idCardNo;
	}

	/** set 身份证号码(IDCARDNO) */
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
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

	/** get 党组织类型(type) */
	public PropertyDict getType() {
		return type;
	}

	/** set 党组织类型(TYPE) */
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

	/** get 党组织名称(name) */
	public String getName() {
		return name;
	}

	/** set 党组织名称(NAME) */
	public void setName(String name) {
		this.name = name;
	}

	/** get 党支部书记(secretary) */
	public String getSecretary() {
		return secretary;
	}

	/** set 党支部书记(SECRETARY) */
	public void setSecretary(String secretary) {
		this.secretary = secretary;
	}

	/** get 党组织地址(address) */
	public String getAddress() {
		return address;
	}

	/** set 党组织地址(ADDRESS) */
	public void setAddress(String address) {
		this.address = address;
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
