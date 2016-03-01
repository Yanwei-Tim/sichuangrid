package com.tianque.domain.vo;

import java.io.Serializable;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.service.util.PopulationCatalog;

public class HouseLivingPopulationInfo implements Serializable {

	private PopulationCatalog type;
	private String sid;
	private Long id;

	private Long orgId;
	private String orgName;
	private PropertyDict certificateType;
	private String typeCatalogName;
	private String populationtypes;
	private String certificateNumber;
	private Boolean isHaveHouse;
	private String noHouseReason;

	private String personName;

	private Long genderId;
	private String genderName;

	private Long nationId;
	private String nationName;

	private Long educationId;
	private String educationName;

	// private Long householdPlace;
	private String householdPlace;

	private Long professionId;
	private String professionName;

	private String workCompany;

	private boolean death;

	private boolean active;
	private String currAddress;
	private Long houseId;
	private String nativePlaceAddress;

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getCurrAddress() {
		return currAddress;
	}

	public void setCurrAddress(String currAddress) {
		this.currAddress = currAddress;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PopulationCatalog getType() {
		if (type == null && StringUtil.isStringAvaliable(typeCatalogName)) {
			type = PopulationCatalog.parse(typeCatalogName);
		}
		return type;
	}

	public void setType(PopulationCatalog type) {
		this.type = type;
	}

	public String getTypeCatalogName() {
		return typeCatalogName;
	}

	public void setTypeCatalogName(String typeCatalogName) {
		this.typeCatalogName = typeCatalogName;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Long getGenderId() {
		return genderId;
	}

	public void setGenderId(Long genderId) {
		this.genderId = genderId;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public Long getNationId() {
		return nationId;
	}

	public void setNationId(Long nationId) {
		this.nationId = nationId;
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public Long getEducationId() {
		return educationId;
	}

	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}

	public String getEducationName() {
		return educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	public String getHouseholdPlace() {
		return householdPlace;
	}

	public void setHouseholdPlace(String householdPlace) {
		this.householdPlace = householdPlace;
	}

	public Long getProfessionId() {
		return professionId;
	}

	public void setProfessionId(Long professionId) {
		this.professionId = professionId;
	}

	public String getProfessionName() {
		return professionName;
	}

	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}

	public String getWorkCompany() {
		return workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}

	public boolean isDeath() {
		return death;
	}

	public void setDeath(boolean death) {
		this.death = death;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUniqueId() {
		return getType().getCatalog() + "_" + getId();
	}

	public String getPopulationtypes() {
		return populationtypes;
	}

	public void setPopulationtypes(String populationtypes) {
		this.populationtypes = populationtypes;
	}

	public Boolean getIsHaveHouse() {
		return isHaveHouse;
	}

	public void setIsHaveHouse(Boolean isHaveHouse) {
		this.isHaveHouse = isHaveHouse;
	}

	public String getNoHouseReason() {
		return noHouseReason;
	}

	public void setNoHouseReason(String noHouseReason) {
		this.noHouseReason = noHouseReason;
	}

	public PropertyDict getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(PropertyDict certificateType) {
		this.certificateType = certificateType;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getNativePlaceAddress() {
		return nativePlaceAddress;
	}

	public void setNativePlaceAddress(String nativePlaceAddress) {
		this.nativePlaceAddress = nativePlaceAddress;
	}

}
