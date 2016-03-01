package com.tianque.partyBuilding.systemPartys.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;

/**
 * 系统党建实体类
 * 
 * @author yulei
 * 
 */
public class SystemParty extends BaseDomain {

	/**
	 * 党组织名称
	 */
	private String partyName;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 联系手机
	 */
	private String mobile;
	/**
	 * 固定电话
	 */
	private String telephone;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 党组织类型
	 */
	private Integer partyOrgType;
	/**
	 * 所属部门
	 */
	private Organization organization;
	/**
	 * 
	 */
	private String orgInternalCode;

	/**
	 * 党员数
	 */
	private Integer partyMemeberSum;

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPartyOrgType() {
		return partyOrgType;
	}

	public void setPartyOrgType(Integer partyOrgType) {
		this.partyOrgType = partyOrgType;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Integer getPartyMemeberSum() {
		return partyMemeberSum;
	}

	public void setPartyMemeberSum(Integer partyMemeberSum) {
		this.partyMemeberSum = partyMemeberSum;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.orgInternalCode, null);
	}
}
