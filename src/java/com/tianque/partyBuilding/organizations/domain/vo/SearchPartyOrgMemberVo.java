package com.tianque.partyBuilding.organizations.domain.vo;


/**
 * 两新组织党组织表:SrarchVO(PARTY_ORG_MEMBER)
 * 
 * @author 
 * @date 2014-01-14 15:44:08
 */
public class SearchPartyOrgMemberVo extends com.tianque.core.base.BaseDomain {
	
	/** 最大  */
	private java.util.Date		maxCreatDate;
	/** 最小  */
	private java.util.Date		minCreatDate;		
	/** 最大  */
	private java.util.Date		maxUpdateDate;
	/** 最小  */
	private java.util.Date		minUpdateDate;		
	/** 联系电话 */
	private String		telephone;			
	/** 联系手机 */
	private String		mobileNumber;			
	/**  */
	private Long		orgId;			
	/** 党组织表id */
	private Long		partyOrgId;			
	/**  */
	private String		orgInternalCode;			
	/** 姓名 */
	private String		name;			
	/**  */
	private String		createUser;			
	/**  */
	private String		updateUser;			
	
	
	/** get 最大  */
	public java.util.Date getMaxCreatDate() {
		return maxCreatDate;
	}
	/** set 最大  */
	public void setMaxCreatDate(java.util.Date maxCreatDate) {
		this.maxCreatDate = maxCreatDate;
	}
	/** get 最小  */
	public java.util.Date getMinCreatDate() {
		return minCreatDate;
	}
	/** set 最小  */
	public void setMinCreatDate(java.util.Date minCreatDate) {
		this.minCreatDate = minCreatDate;
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
	
	/** get 联系电话 */
	public String getTelephone() {
		return telephone;
	}
	/** set 联系电话 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/** get 联系手机 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/** set 联系手机 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	/** get  */
	public Long getOrgId() {
		return orgId;
	}
	/** set  */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	/** get 党组织表id */
	public Long getPartyOrgId() {
		return partyOrgId;
	}
	/** set 党组织表id */
	public void setPartyOrgId(Long partyOrgId) {
		this.partyOrgId = partyOrgId;
	}
	
	/** get  */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}
	/** set  */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}
	
	/** get 姓名 */
	public String getName() {
		return name;
	}
	/** set 姓名 */
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
	
}
