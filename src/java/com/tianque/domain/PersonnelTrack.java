package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

@SuppressWarnings("serial")
public class PersonnelTrack extends BaseDomain {
	private String idcardNo;
	private Organization operationOrganization;
	private Organization personnelOrganization;
	private Organization oldPersonnelOrganization;
	private String personnelOrgInternalCode;
	private Long personnelId;
	private String personnelName;
	private String personnelType;
	private Integer operationType;
	private Integer personinitType;

	public Integer getPersoninitType() {
		return personinitType;
	}

	public void setPersoninitType(Integer personinitType) {
		this.personinitType = personinitType;
	}

	private String operationContent;
	private User operationUser;
	private Date operationDate;

	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public Long getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(Long personnelId) {
		this.personnelId = personnelId;
	}

	public String getPersonnelName() {
		return personnelName;
	}

	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}

	public String getPersonnelType() {
		return personnelType;
	}

	public void setPersonnelType(String personnelType) {
		this.personnelType = personnelType;
	}

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	public String getOperationContent() {
		return operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

	public User getOperationUser() {
		return operationUser;
	}

	public void setOperationUser(User operationUser) {
		this.operationUser = operationUser;
	}

	public Organization getOperationOrganization() {
		return operationOrganization;
	}

	public void setOperationOrganization(Organization operationOrganization) {
		this.operationOrganization = operationOrganization;
	}

	public String getPersonnelOrgInternalCode() {
		return personnelOrgInternalCode;
	}

	public void setPersonnelOrgInternalCode(String personnelOrgInternalCode) {
		this.personnelOrgInternalCode = personnelOrgInternalCode;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getOperationDate() {
		return operationDate;
	}

	public Organization getPersonnelOrganization() {
		return personnelOrganization;
	}

	public void setPersonnelOrganization(Organization personnelOrganization) {
		this.personnelOrganization = personnelOrganization;
	}

	public Organization getOldPersonnelOrganization() {
		return oldPersonnelOrganization;
	}

	public void setOldPersonnelOrganization(Organization oldPersonnelOrganization) {
		this.oldPersonnelOrganization = oldPersonnelOrganization;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}
}
