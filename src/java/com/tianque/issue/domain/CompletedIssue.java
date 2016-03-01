package com.tianque.issue.domain;

import java.io.Serializable;
import java.util.Date;

import com.tianque.issue.vo.IssueViewObject;

public class CompletedIssue extends IssueViewObject implements Serializable {

	private Long porgId;

	private Long torgId;

	private String createOrginternalCode;

	private Long crateOrgFunctionalType;

	private Long createOrgLevel;

	private Date topDate;

	/** 是否是历史事件，1标示已经迁移是历史事件 */
	private String moveMark;
	
	private String currentOrginternalCode;

	private Long currentOrgFunctionalType;

	private Long currentOrgLevel;

	
	public String getCreateOrginternalCode() {
		return createOrginternalCode;
	}

	public void setCreateOrginternalCode(String createOrginternalCode) {
		this.createOrginternalCode = createOrginternalCode;
	}

	public Long getPorgId() {
		return porgId;
	}

	public void setPorgId(Long porgId) {
		this.porgId = porgId;
	}

	public Long getTorgId() {
		return torgId;
	}

	public void setTorgId(Long torgId) {
		this.torgId = torgId;
	}



	public Date getTopDate() {
		return topDate;
	}

	public void setTopDate(Date topDate) {
		this.topDate = topDate;
	}


	public Long getCrateOrgFunctionalType() {
		return crateOrgFunctionalType;
	}

	public void setCrateOrgFunctionalType(Long crateOrgFunctionalType) {
		this.crateOrgFunctionalType = crateOrgFunctionalType;
	}

	public Long getCreateOrgLevel() {
		return createOrgLevel;
	}

	public void setCreateOrgLevel(Long createOrgLevel) {
		this.createOrgLevel = createOrgLevel;
	}

	public String getMoveMark() {
		return moveMark;
	}

	public void setMoveMark(String moveMark) {
		this.moveMark = moveMark;
	}

	public String getCurrentOrginternalCode() {
		return currentOrginternalCode;
	}

	public void setCurrentOrginternalCode(String currentOrginternalCode) {
		this.currentOrginternalCode = currentOrginternalCode;
	}

	public Long getCurrentOrgFunctionalType() {
		return currentOrgFunctionalType;
	}

	public void setCurrentOrgFunctionalType(Long currentOrgFunctionalType) {
		this.currentOrgFunctionalType = currentOrgFunctionalType;
	}

	public Long getCurrentOrgLevel() {
		return currentOrgLevel;
	}

	public void setCurrentOrgLevel(Long currentOrgLevel) {
		this.currentOrgLevel = currentOrgLevel;
	}

}
