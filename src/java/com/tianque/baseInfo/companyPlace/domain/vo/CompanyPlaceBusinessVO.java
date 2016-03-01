package com.tianque.baseInfo.companyPlace.domain.vo;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceBusiness;
import com.tianque.baseInfo.companyPlace.domain.ContaminationInfo;

public class CompanyPlaceBusinessVO {
	private CompanyPlaceBusiness proKeyCompanyPlaceBusiness;
	private String[] proKeyAttachFiles;
	private String proKeyAttachFilesForMobile;
	private Long delProKeyID;

	private CompanyPlaceBusiness fireSafetyKeyCompanyPlaceBusiness;
	private String[] fireSafetyKeyAttachFiles;
	private String fireSafetyKeyAttachFilesForMobile;
	private Long delfirKeyID;

	private CompanyPlaceBusiness securityKeyCompanyPlaceBusiness;
	private String[] securityKeyAttachFiles;
	private String securityKeyAttachFilesForMobile;
	private Long delsecKeyID;

	private CompanyPlaceBusiness pollSourceCompanyPlaceBusiness;
	private String[] pollSourceAttachFiles;
	private String pollSourceAttachFilesForMobile;
	private Long delpollKeyID;

	private ContaminationInfo contaminationInfo;

	public CompanyPlaceBusiness getProKeyCompanyPlaceBusiness() {
		return proKeyCompanyPlaceBusiness;
	}

	public void setProKeyCompanyPlaceBusiness(
			CompanyPlaceBusiness proKeyCompanyPlaceBusiness) {
		this.proKeyCompanyPlaceBusiness = proKeyCompanyPlaceBusiness;
	}

	public CompanyPlaceBusiness getFireSafetyKeyCompanyPlaceBusiness() {
		return fireSafetyKeyCompanyPlaceBusiness;
	}

	public void setFireSafetyKeyCompanyPlaceBusiness(
			CompanyPlaceBusiness fireSafetyKeyCompanyPlaceBusiness) {
		this.fireSafetyKeyCompanyPlaceBusiness = fireSafetyKeyCompanyPlaceBusiness;
	}

	public CompanyPlaceBusiness getSecurityKeyCompanyPlaceBusiness() {
		return securityKeyCompanyPlaceBusiness;
	}

	public void setSecurityKeyCompanyPlaceBusiness(
			CompanyPlaceBusiness securityKeyCompanyPlaceBusiness) {
		this.securityKeyCompanyPlaceBusiness = securityKeyCompanyPlaceBusiness;
	}

	public CompanyPlaceBusiness getPollSourceCompanyPlaceBusiness() {
		return pollSourceCompanyPlaceBusiness;
	}

	public void setPollSourceCompanyPlaceBusiness(
			CompanyPlaceBusiness pollSourceCompanyPlaceBusiness) {
		this.pollSourceCompanyPlaceBusiness = pollSourceCompanyPlaceBusiness;
	}

	public String[] getProKeyAttachFiles() {
		return proKeyAttachFiles;
	}

	public void setProKeyAttachFiles(String[] proKeyAttachFiles) {
		this.proKeyAttachFiles = proKeyAttachFiles;
	}

	public String[] getFireSafetyKeyAttachFiles() {
		return fireSafetyKeyAttachFiles;
	}

	public void setFireSafetyKeyAttachFiles(String[] fireSafetyKeyAttachFiles) {
		this.fireSafetyKeyAttachFiles = fireSafetyKeyAttachFiles;
	}

	public String[] getSecurityKeyAttachFiles() {
		return securityKeyAttachFiles;
	}

	public void setSecurityKeyAttachFiles(String[] securityKeyAttachFiles) {
		this.securityKeyAttachFiles = securityKeyAttachFiles;
	}

	public String[] getPollSourceAttachFiles() {
		return pollSourceAttachFiles;
	}

	public void setPollSourceAttachFiles(String[] pollSourceAttachFiles) {
		this.pollSourceAttachFiles = pollSourceAttachFiles;
	}

	public String getProKeyAttachFilesForMobile() {
		return proKeyAttachFilesForMobile;
	}

	public void setProKeyAttachFilesForMobile(String proKeyAttachFilesForMobile) {
		this.proKeyAttachFilesForMobile = proKeyAttachFilesForMobile;
	}

	public String getFireSafetyKeyAttachFilesForMobile() {
		return fireSafetyKeyAttachFilesForMobile;
	}

	public void setFireSafetyKeyAttachFilesForMobile(
			String fireSafetyKeyAttachFilesForMobile) {
		this.fireSafetyKeyAttachFilesForMobile = fireSafetyKeyAttachFilesForMobile;
	}

	public String getSecurityKeyAttachFilesForMobile() {
		return securityKeyAttachFilesForMobile;
	}

	public void setSecurityKeyAttachFilesForMobile(
			String securityKeyAttachFilesForMobile) {
		this.securityKeyAttachFilesForMobile = securityKeyAttachFilesForMobile;
	}

	public String getPollSourceAttachFilesForMobile() {
		return pollSourceAttachFilesForMobile;
	}

	public void setPollSourceAttachFilesForMobile(
			String pollSourceAttachFilesForMobile) {
		this.pollSourceAttachFilesForMobile = pollSourceAttachFilesForMobile;
	}

	public Long getDelProKeyID() {
		return delProKeyID;
	}

	public void setDelProKeyID(Long delProKeyID) {
		this.delProKeyID = delProKeyID;
	}

	public Long getDelfirKeyID() {
		return delfirKeyID;
	}

	public void setDelfirKeyID(Long delfirKeyID) {
		this.delfirKeyID = delfirKeyID;
	}

	public Long getDelsecKeyID() {
		return delsecKeyID;
	}

	public void setDelsecKeyID(Long delsecKeyID) {
		this.delsecKeyID = delsecKeyID;
	}

	public Long getDelpollKeyID() {
		return delpollKeyID;
	}

	public void setDelpollKeyID(Long delpollKeyID) {
		this.delpollKeyID = delpollKeyID;
	}

	public ContaminationInfo getContaminationInfo() {
		return contaminationInfo;
	}

	public void setContaminationInfo(ContaminationInfo contaminationInfo) {
		this.contaminationInfo = contaminationInfo;
	}
}
