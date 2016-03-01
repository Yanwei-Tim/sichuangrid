package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class OrgLoginLog extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 网格内置编码 */
	private String orgInternalCode;
	/** 所属网格 */
	private Long orgId;
	private String orgName;
	/** 每天最后一次登录时间 */
	private Date lastLogginDate;
	/** 登录的用户id **/
	private Long userId;
	/** 登录的用户名 **/
	private String userName;

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getLastLogginDate() {
		return lastLogginDate;
	}

	public void setLastLogginDate(Date lastLogginDate) {
		this.lastLogginDate = lastLogginDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}