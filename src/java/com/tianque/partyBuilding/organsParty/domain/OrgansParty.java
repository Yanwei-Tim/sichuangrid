package com.tianque.partyBuilding.organsParty.domain;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.PropertyDict;

/**
 * 机关党组织表:实体类(ORGANS_PARTY)
 * 
 * @author
 * @date 2014-02-10 16:00:06
 */
public class OrgansParty extends com.tianque.core.base.BaseDomain implements
		java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 联系电话(TELEPHONE) */
	private String telephone;
	/** (ORGID) */
	private Long orgid;
	/** 所属部门(DEPARTMENT) */
	private PropertyDict department;
	/** 党组织类型(TYPE) */
	private PropertyDict type;
	/** 上级党组织(SUPERIOR) */
	private OrgansParty superior;
	/** (ORGINTERNALCODE) */
	private String orginternalcode;
	/** 党组织名称(NAME) */
	private String name;
	/** 联系人(CONTACT) */
	private String contact;
	/** (CREATEUSER) */
	private String remark;

	/**** 下辖组织 **/
	private String juniorParty;
	/***** 党员数 */
	private Integer memberCount;

	/** get 联系电话(telephone) */
	public String getTelephone() {
		return telephone;
	}

	/** set 联系电话(TELEPHONE) */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/** get (orgid) */
	public Long getOrgid() {
		return orgid;
	}

	/** set (ORGID) */
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	/** get (orginternalcode) */
	public String getOrginternalcode() {
		return orginternalcode;
	}

	/** set (ORGINTERNALCODE) */
	public void setOrginternalcode(String orginternalcode) {
		this.orginternalcode = orginternalcode;
	}

	/** get 党组织名称(name) */
	public String getName() {
		return name;
	}

	/** set 党组织名称(NAME) */
	public void setName(String name) {
		this.name = name;
	}

	/** get 联系人(contact) */
	public String getContact() {
		return contact;
	}

	/** set 联系人(CONTACT) */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/** get 备注(remark) */
	public String getRemark() {
		return remark;
	}

	/** set 备注(REMARK) */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

	public void setDepartment(PropertyDict department) {
		this.department = department;
	}

	public PropertyDict getDepartment() {
		return department;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setJuniorParty(String juniorParty) {
		this.juniorParty = juniorParty;
	}

	public String getJuniorParty() {
		return juniorParty;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setSuperior(OrgansParty superior) {
		this.superior = superior;
	}

	public OrgansParty getSuperior() {
		return superior;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(),
				this.orginternalcode, null);
	}
}
