package com.tianque.gis.domain.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.domain.GisInfo;

public class IssueNewsVo {
	private String keyPersonType;
	private GisInfo gisInfo;
	private boolean enableBind;
	private Long orgId;
	/** 发生时间 */
	private Date occurDate;
	/** 主题 */
	private String subject;
	/** 发生地点 */
	private String occurLocation;
	private Long issueId;
	/** 当前部门org显示名称 */
	private String currentOrgDisplayName;
	/** 时间状态 */
	private String statecode;
	/** 主要参与人员 */
	private String maincharacters;

	private String domainname;

	public String getDomainname() {
		return domainname;
	}

	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}

	public String getCurrentOrgDisplayName() {
		return currentOrgDisplayName;
	}

	public void setCurrentOrgDisplayName(String currentOrgDisplayName) {
		this.currentOrgDisplayName = currentOrgDisplayName;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getMaincharacters() {
		return maincharacters;
	}

	public void setMaincharacters(String maincharacters) {
		this.maincharacters = maincharacters;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public boolean isEnableBind() {
		return enableBind;
	}

	public void setEnableBind(boolean enableBind) {
		this.enableBind = enableBind;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public String getKeyPersonType() {
		return keyPersonType;
	}

	public void setKeyPersonType(String keyPersonType) {
		this.keyPersonType = keyPersonType;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOccurLocation() {
		return occurLocation;
	}

	public void setOccurLocation(String occurLocation) {
		this.occurLocation = occurLocation;
	}

}
