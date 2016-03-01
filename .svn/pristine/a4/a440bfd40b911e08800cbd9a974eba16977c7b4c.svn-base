package com.tianque.publicSecurity.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.buildDatas.domain.OpenLayersMapInfo;
import com.tianque.domain.Organization;

/**
 * 公安部件:公共实体类(SKYNET)
 * 
 * @author
 */
public class publicSecurityCommon extends com.tianque.core.base.BaseDomain
		implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 注销时间(LOGOUTTIME) */
	private java.util.Date logoutTime;
	/** 所属网格(ORGID) */
	private Organization organization;
	/** 是否注销(ISEMPHASIS) */
	private Boolean isEmphasis;
	/** 所属网格编号(ORGINTERNALCODE) */
	private String orgInternalCode;

	/** 地址(ADDRESS) */
	private String address;
	/** 注销原因(LOGOUTREASON) */
	private String logoutReason;
	/** 公安类型 (type) */
	private String type;

	private OpenLayersMapInfo openLayersMapInfo;

	/** 是否绑定 */
	private Integer isBind;

	public publicSecurityCommon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public publicSecurityCommon(Date logoutTime, Organization organization,
			Boolean isEmphasis, String orgInternalCode,
			OpenLayersMapInfo openLayersMapInfo, String address,
			String logoutReason, Integer isBind, String type) {
		super();
		this.logoutTime = logoutTime;
		this.organization = organization;
		this.isEmphasis = isEmphasis;
		this.orgInternalCode = orgInternalCode;
		this.openLayersMapInfo = openLayersMapInfo;
		this.address = address;
		this.logoutReason = logoutReason;
		this.isBind = isBind;
		this.type = type;
	}

	/** get 注销时间(logoutTime) */
	@JSON(format = "yyyy-MM-dd")
	public java.util.Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(java.util.Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Boolean getIsEmphasis() {
		return isEmphasis;
	}

	public void setIsEmphasis(Boolean isEmphasis) {
		this.isEmphasis = isEmphasis;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogoutReason() {
		return logoutReason;
	}

	public void setLogoutReason(String logoutReason) {
		this.logoutReason = logoutReason;
	}

	public OpenLayersMapInfo getOpenLayersMapInfo() {
		return openLayersMapInfo;
	}

	public void setOpenLayersMapInfo(OpenLayersMapInfo openLayersMapInfo) {
		this.openLayersMapInfo = openLayersMapInfo;
	}

	public Integer getIsBind() {
		return isBind;
	}

	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
