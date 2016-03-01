package com.tianque.partyBuilding.baseInfos.domain.vo;

/**
 * 社区基本情况表:SrarchVO(DISTRICT_BASICCASE)
 * 
 * @author
 * @date 2014-01-14 15:24:54
 */
public class SearchDistrictBasiccaseVo extends com.tianque.core.base.BaseDomain {

	/** 最大 创建时间 */
	private java.util.Date maxCreateDate;
	/** 最小 创建时间 */
	private java.util.Date minCreateDate;
	/** 最大 修改时间 */
	private java.util.Date maxUpdateDate;
	/** 最小 修改时间 */
	private java.util.Date minUpdateDate;
	/** 所属网格 */
	private Long orgId;
	/** 所属网格编号 */
	private String orgInternalCode;
	/** 社区基本情况图片地址 */
	private String imgUrl;
	/** 全区党组织及党员情况 */
	private String partyOrgsAndMembersCase;
	/** 机关党建情况 */
	private String administrativeCases;
	/** 街道党建情况 */
	private String streetPartySituation;
	/** 系统党建情况 */
	private String systemConstruction;
	/** 区域党建情况 */
	private String regionalPartySituation;
	/** 志愿者情况 */
	private String volunteersSituation;
	/** 双报到情况 */
	private String doubleRegistrationSituation;

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

	/** get 社区基本情况图片地址 */
	public String getImgUrl() {
		return imgUrl;
	}

	/** set 社区基本情况图片地址 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/** get 全区党组织及党员情况 */
	public String getPartyOrgsAndMembersCase() {
		return partyOrgsAndMembersCase;
	}

	/** set 全区党组织及党员情况 */
	public void setPartyOrgsAndMembersCase(String partyOrgsAndMembersCase) {
		this.partyOrgsAndMembersCase = partyOrgsAndMembersCase;
	}

	/** get 机关党建情况 */
	public String getAdministrativeCases() {
		return administrativeCases;
	}

	/** set 机关党建情况 */
	public void setAdministrativeCases(String administrativeCases) {
		this.administrativeCases = administrativeCases;
	}

	/** get 街道党建情况 */
	public String getStreetPartySituation() {
		return streetPartySituation;
	}

	/** set 街道党建情况 */
	public void setStreetPartySituation(String streetPartySituation) {
		this.streetPartySituation = streetPartySituation;
	}

	/** get 系统党建情况 */
	public String getSystemConstruction() {
		return systemConstruction;
	}

	/** set 系统党建情况 */
	public void setSystemConstruction(String systemConstruction) {
		this.systemConstruction = systemConstruction;
	}

	/** get 区域党建情况 */
	public String getRegionalPartySituation() {
		return regionalPartySituation;
	}

	/** set 区域党建情况 */
	public void setRegionalPartySituation(String regionalPartySituation) {
		this.regionalPartySituation = regionalPartySituation;
	}

	/** get 志愿者情况 */
	public String getVolunteersSituation() {
		return volunteersSituation;
	}

	/** set 志愿者情况 */
	public void setVolunteersSituation(String volunteersSituation) {
		this.volunteersSituation = volunteersSituation;
	}

	/** get 双报到情况 */
	public String getDoubleRegistrationSituation() {
		return doubleRegistrationSituation;
	}

	/** set 双报到情况 */
	public void setDoubleRegistrationSituation(
			String doubleRegistrationSituation) {
		this.doubleRegistrationSituation = doubleRegistrationSituation;
	}

}
