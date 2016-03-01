package com.tianque.partyBuilding.organsParty.domain.vo;

/**
 * 机关党组织表:SrarchVO(ORGANS_PARTY)
 * 
 * @author
 * @date 2014-02-10 16:00:06
 */
public class SearchOrgansPartyVo extends com.tianque.core.base.BaseDomain {

	/** 联系电话 */
	private String telephone;
	/**  */
	private Long orgid;
	/** 所属部门 */
	private Long department;
	/** 党组织类型 */
	private Long type;
	/** 上级党组织 */
	private Long superior;
	/**  */
	private String orginternalcode;
	/** 党组织名称 */
	private String name;
	/** 联系人 */
	private String contact;
	/** 备注 */
	private String remark;

	/** get 联系电话 */
	public String getTelephone() {
		return telephone;
	}

	/** set 联系电话 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/** get */
	public Long getOrgid() {
		return orgid;
	}

	/** set */
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	/** get 所属部门 */
	public Long getDepartment() {
		return department;
	}

	/** set 所属部门 */
	public void setDepartment(Long department) {
		this.department = department;
	}

	/** get 党组织类型 */
	public Long getType() {
		return type;
	}

	/** set 党组织类型 */
	public void setType(Long type) {
		this.type = type;
	}

	/** get 上级党组织 */
	public Long getSuperior() {
		return superior;
	}

	/** set 上级党组织 */
	public void setSuperior(Long superior) {
		this.superior = superior;
	}

	/** get */
	public String getOrginternalcode() {
		return orginternalcode;
	}

	/** set */
	public void setOrginternalcode(String orginternalcode) {
		this.orginternalcode = orginternalcode;
	}

	/** get 党组织名称 */
	public String getName() {
		return name;
	}

	/** set 党组织名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** get 联系人 */
	public String getContact() {
		return contact;
	}

	/** set 联系人 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/** get 备注 */
	public String getRemark() {
		return remark;
	}

	/** set 备注 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
