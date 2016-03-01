package com.tianque.partyBuilding.baseInfos.domain.vo;


/**
 * 基本情况表:SrarchVO(BASIC_CASE)
 * 
 * @author 
 * @date 2014-01-14 15:32:52
 */
public class SearchBasicCaseVo extends com.tianque.core.base.BaseDomain {
	
	/** 最大 创建时间 */
	private java.util.Date		maxCreateDate;
	/** 最小 创建时间 */
	private java.util.Date		minCreateDate;		
	/** 最大 修改时间 */
	private java.util.Date		maxUpdateDate;
	/** 最小 修改时间 */
	private java.util.Date		minUpdateDate;		
	/** 所属网格 */
	private Long		orgId;			
	/** 所属网格编号 */
	private String		orgInternalCode;			
	/** 创建人 */
	private String		createUser;			
	/** 修改人 */
	private String		updateUser;			
	/** 基本情况图片地址 */
	private String		imgUrl;			
	/** 基本情况 */
	private String		baseCase;			
	
	
	/** get 最大 创建时间 */
	public java.util.Date getMaxCreateDate() {
		return maxCreateDate;
	}
	/** set 最大 创建时间 */
	public void setMaxCreateDate(java.util.Date maxCreateDate) {
		this.maxCreateDate = maxCreateDate;
	}
	/** get 最小 创建时间 */
	public java.util.Date getMinCreateDate() {
		return minCreateDate;
	}
	/** set 最小 创建时间 */
	public void setMinCreateDate(java.util.Date minCreateDate) {
		this.minCreateDate = minCreateDate;
	}
	
	/** get 最大 修改时间 */
	public java.util.Date getMaxUpdateDate() {
		return maxUpdateDate;
	}
	/** set 最大 修改时间 */
	public void setMaxUpdateDate(java.util.Date maxUpdateDate) {
		this.maxUpdateDate = maxUpdateDate;
	}
	/** get 最小 修改时间 */
	public java.util.Date getMinUpdateDate() {
		return minUpdateDate;
	}
	/** set 最小 修改时间 */
	public void setMinUpdateDate(java.util.Date minUpdateDate) {
		this.minUpdateDate = minUpdateDate;
	}
	
	/** get 所属网格 */
	public Long getOrgId() {
		return orgId;
	}
	/** set 所属网格 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	/** get 所属网格编号 */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}
	/** set 所属网格编号 */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}
	
	/** get 创建人 */
	public String getCreateUser() {
		return createUser;
	}
	/** set 创建人 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	/** get 修改人 */
	public String getUpdateUser() {
		return updateUser;
	}
	/** set 修改人 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	/** get 基本情况图片地址 */
	public String getImgUrl() {
		return imgUrl;
	}
	/** set 基本情况图片地址 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	/** get 基本情况 */
	public String getBaseCase() {
		return baseCase;
	}
	/** set 基本情况 */
	public void setBaseCase(String baseCase) {
		this.baseCase = baseCase;
	}
	
}
