package com.tianque.partyBuilding.organizations.domain.vo;


/**
 * 两新组织党组织表:SrarchVO(NEW_PARTY_ORG)
 * 
 * @author 
 * @date 2014-01-14 15:44:08
 */
public class SearchNewPartyOrgVo extends com.tianque.core.base.BaseDomain {
	
	/** 最大  */
	private java.util.Date		maxCreateDate;
	/** 最小  */
	private java.util.Date		minCreateDate;		
	/** 最大  */
	private java.util.Date		maxUpdateDate;
	/** 最小  */
	private java.util.Date		minUpdateDate;		
	/**  */
	private Long		orgId;			
	/** 党组织类别 */
	private Long		type;			
	/**  */
	private String		orgInternalCode;			
	/** 组织名称 */
	private String		name;			
	/**  */
	private String		createUser;			
	/**  */
	private String		updateUser;			
	/** 地址 */
	private String		address;			
	/** 备注 */
	private String		remark;			
	
	
	/** get 最大  */
	public java.util.Date getMaxCreateDate() {
		return maxCreateDate;
	}
	/** set 最大  */
	public void setMaxCreateDate(java.util.Date maxCreateDate) {
		this.maxCreateDate = maxCreateDate;
	}
	/** get 最小  */
	public java.util.Date getMinCreateDate() {
		return minCreateDate;
	}
	/** set 最小  */
	public void setMinCreateDate(java.util.Date minCreateDate) {
		this.minCreateDate = minCreateDate;
	}
	
	/** get 最大  */
	public java.util.Date getMaxUpdateDate() {
		return maxUpdateDate;
	}
	/** set 最大  */
	public void setMaxUpdateDate(java.util.Date maxUpdateDate) {
		this.maxUpdateDate = maxUpdateDate;
	}
	/** get 最小  */
	public java.util.Date getMinUpdateDate() {
		return minUpdateDate;
	}
	/** set 最小  */
	public void setMinUpdateDate(java.util.Date minUpdateDate) {
		this.minUpdateDate = minUpdateDate;
	}
	
	/** get  */
	public Long getOrgId() {
		return orgId;
	}
	/** set  */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	/** get 党组织类别 */
	public Long getType() {
		return type;
	}
	/** set 党组织类别 */
	public void setType(Long type) {
		this.type = type;
	}
	
	/** get  */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}
	/** set  */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}
	
	/** get 组织名称 */
	public String getName() {
		return name;
	}
	/** set 组织名称 */
	public void setName(String name) {
		this.name = name;
	}
	
	/** get  */
	public String getCreateUser() {
		return createUser;
	}
	/** set  */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	/** get  */
	public String getUpdateUser() {
		return updateUser;
	}
	/** set  */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	/** get 地址 */
	public String getAddress() {
		return address;
	}
	/** set 地址 */
	public void setAddress(String address) {
		this.address = address;
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
