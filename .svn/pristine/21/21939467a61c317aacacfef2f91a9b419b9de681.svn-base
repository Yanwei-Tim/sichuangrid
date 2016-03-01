package com.tianque.partyBuilding.organizations.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;

/**
 * 区域内主要党组织资源:实体类(PARTYRESOURCE)
 * 
 * @author
 * @date 2014-01-15 09:22:20
 */
public class Partyresource extends BaseDomain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 联系电话(TELEPHONE) */
	private String telephone;
	private Organization organization;
	/** 负责人(MANAGER) */
	private String manager;
	/** 组织名称(NAME) */
	private String name;
	/** 地点(ADDRESS) */
	private String address;
	/** 备注(REMARK) */
	private String remark;

	/** get 联系电话(telephone) */
	public String getTelephone() {
		return telephone;
	}

	/** set 联系电话(TELEPHONE) */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/** get 负责人(manager) */
	public String getManager() {
		return manager;
	}

	/** set 负责人(MANAGER) */
	public void setManager(String manager) {
		this.manager = manager;
	}

	/** get 组织名称(name) */
	public String getName() {
		return name;
	}

	/** set 组织名称(NAME) */
	public void setName(String name) {
		this.name = name;
	}

	/** get 地点(address) */
	public String getAddress() {
		return address;
	}

	/** set 地点(ADDRESS) */
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

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Organization getOrganization() {
		return organization;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.organization.getOrgInternalCode(), null);
	}
}
