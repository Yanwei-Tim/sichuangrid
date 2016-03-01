package com.tianque.partyBuilding.baseInfos.domain;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 社区基本情况表:实体类(DISTRICT_BASICCASE)
 * 
 * @author
 * @date 2014-01-14 15:24:54
 */
public class DistrictBasiccase extends BaseDomain implements
		java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 所属网格(ORGID) */
	private Organization organization;
	/** 所属网格编号(ORGINTERNALCODE) */
	private String orgInternalCode;
	/** 社区基本情况图片地址(IMGURL) */
	private String imgUrl;
	/** 全区党组织及党员情况(PARTYORGSANDMEMBERSCASE) */
	private String partyOrgsAndMembersCase;
	/** 机关党建情况(ADMINISTRATIVECASES) */
	private String administrativeCases;
	/** 街道党建情况(STREETPARTYSITUATION) */
	private String streetPartySituation;
	/** 系统党建情况(SYSTEMCONSTRUCTION) */
	private String systemConstruction;
	/** 区域党建情况(REGIONALPARTYSITUATION) */
	private String regionalPartySituation;
	/** 志愿者情况(VOLUNTEERSSITUATION) */
	private String volunteersSituation;
	/** 双报到情况(DOUBLEREGISTRATIONSITUATION) */
	private String doubleRegistrationSituation;

	private List<CaseImageUpload> caseImageUploads = new ArrayList<CaseImageUpload>();

	public DistrictBasiccase() {

	}

	public DistrictBasiccase(Organization organization, String orgInternalCode,
			String imgUrl, String partyOrgsAndMembersCase,
			String administrativeCases, String streetPartySituation,
			String systemConstruction, String regionalPartySituation,
			String volunteersSituation, String doubleRegistrationSituation,
			List<CaseImageUpload> caseImageUploads) {
		this.organization = organization;
		this.orgInternalCode = orgInternalCode;
		this.imgUrl = imgUrl;
		this.partyOrgsAndMembersCase = partyOrgsAndMembersCase;
		this.administrativeCases = administrativeCases;
		this.streetPartySituation = streetPartySituation;
		this.systemConstruction = systemConstruction;
		this.regionalPartySituation = regionalPartySituation;
		this.volunteersSituation = volunteersSituation;
		this.doubleRegistrationSituation = doubleRegistrationSituation;
		this.caseImageUploads = caseImageUploads;
	}

	/** get 所属网格(orgId) */
	public Organization getOrganization() {
		return organization;
	}

	/** set 所属网格(ORGID) */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/** get 所属网格编号(orgInternalCode) */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号(ORGINTERNALCODE) */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	/** get 社区基本情况图片地址(imgUrl) */
	public String getImgUrl() {
		return imgUrl;
	}

	/** set 社区基本情况图片地址(IMGURL) */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/** get 全区党组织及党员情况(partyOrgsAndMembersCase) */
	public String getPartyOrgsAndMembersCase() {
		return partyOrgsAndMembersCase;
	}

	/** set 全区党组织及党员情况(PARTYORGSANDMEMBERSCASE) */
	public void setPartyOrgsAndMembersCase(String partyOrgsAndMembersCase) {
		this.partyOrgsAndMembersCase = partyOrgsAndMembersCase;
	}

	/** get 机关党建情况(administrativeCases) */
	public String getAdministrativeCases() {
		return administrativeCases;
	}

	/** set 机关党建情况(ADMINISTRATIVECASES) */
	public void setAdministrativeCases(String administrativeCases) {
		this.administrativeCases = administrativeCases;
	}

	/** get 街道党建情况(streetPartySituation) */
	public String getStreetPartySituation() {
		return streetPartySituation;
	}

	/** set 街道党建情况(STREETPARTYSITUATION) */
	public void setStreetPartySituation(String streetPartySituation) {
		this.streetPartySituation = streetPartySituation;
	}

	/** get 系统党建情况(systemConstruction) */
	public String getSystemConstruction() {
		return systemConstruction;
	}

	/** set 系统党建情况(SYSTEMCONSTRUCTION) */
	public void setSystemConstruction(String systemConstruction) {
		this.systemConstruction = systemConstruction;
	}

	/** get 区域党建情况(regionalPartySituation) */
	public String getRegionalPartySituation() {
		return regionalPartySituation;
	}

	/** set 区域党建情况(REGIONALPARTYSITUATION) */
	public void setRegionalPartySituation(String regionalPartySituation) {
		this.regionalPartySituation = regionalPartySituation;
	}

	/** get 志愿者情况(volunteersSituation) */
	public String getVolunteersSituation() {
		return volunteersSituation;
	}

	/** set 志愿者情况(VOLUNTEERSSITUATION) */
	public void setVolunteersSituation(String volunteersSituation) {
		this.volunteersSituation = volunteersSituation;
	}

	/** get 双报到情况(doubleRegistrationSituation) */
	public String getDoubleRegistrationSituation() {
		return doubleRegistrationSituation;
	}

	/** set 双报到情况(DOUBLEREGISTRATIONSITUATION) */
	public void setDoubleRegistrationSituation(
			String doubleRegistrationSituation) {
		this.doubleRegistrationSituation = doubleRegistrationSituation;
	}

	public List<CaseImageUpload> getCaseImageUploads() {
		return caseImageUploads;
	}

	public void setCaseImageUploads(List<CaseImageUpload> caseImageUploads) {
		this.caseImageUploads = caseImageUploads;
	}

}
