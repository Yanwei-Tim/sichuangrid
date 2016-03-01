package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class RegradedPoints extends BaseDomain {

	private String content;
	private Organization regradedOrg;
	private String regradedOrgInternalCode;
	private String regradedUser;
	private String regradedUserRealName;
	private Date regradedDate;
	private String regradeReason;
	private Integer pointType;
	private Double points;
	private Organization targeOrg;
	private String targeOrgInternalCode;
	private String regradedType;
	private String keyString;
	private Date applicationDate;
	private Long logId;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Organization getRegradedOrg() {
		return regradedOrg;
	}

	public void setRegradedOrg(Organization regradedOrg) {
		this.regradedOrg = regradedOrg;
	}

	public String getRegradedOrgInternalCode() {
		return regradedOrgInternalCode;
	}

	public void setRegradedOrgInternalCode(String regradedOrgInternalCode) {
		this.regradedOrgInternalCode = regradedOrgInternalCode;
	}

	public String getRegradedUser() {
		return regradedUser;
	}

	public void setRegradedUser(String regradedUser) {
		this.regradedUser = regradedUser;
	}

	public String getRegradedUserRealName() {
		return regradedUserRealName;
	}

	public void setRegradedUserRealName(String regradedUserRealName) {
		this.regradedUserRealName = regradedUserRealName;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getRegradedDate() {
		return regradedDate;
	}

	public void setRegradedDate(Date regradedDate) {
		this.regradedDate = regradedDate;
	}

	public String getRegradeReason() {
		return regradeReason;
	}

	public void setRegradeReason(String regradeReason) {
		this.regradeReason = regradeReason;
	}

	public Integer getPointType() {
		return pointType;
	}

	public void setPointType(Integer pointType) {
		this.pointType = pointType;
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public Organization getTargeOrg() {
		return targeOrg;
	}

	public void setTargeOrg(Organization targeOrg) {
		this.targeOrg = targeOrg;
	}

	public String getTargeOrgInternalCode() {
		return targeOrgInternalCode;
	}

	public void setTargeOrgInternalCode(String targeOrgInternalCode) {
		this.targeOrgInternalCode = targeOrgInternalCode;
	}

	public String getRegradedType() {
		return regradedType;
	}

	public void setRegradedType(String regradedType) {
		this.regradedType = regradedType;
	}

	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

}
