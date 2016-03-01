package com.tianque.domain.vo;

import java.io.Serializable;

import com.tianque.service.util.PopulationCatalog;

public class PopulationLivingInHouseSimpleInfo implements Serializable {

	private PopulationCatalog type;

	private String certificateNumber;

	private String personName;

	private String gendarName;

	private String nationName;

	private String educationName;

	private String householdPlace;

	private String professionName;

	private String workCompany;

	public PopulationCatalog getType() {
		return type;
	}

	public void setType(PopulationCatalog type) {
		this.type = type;
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

	public String getGendarName() {
		return gendarName;
	}

	public void setGendarName(String gendarName) {
		this.gendarName = gendarName;
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
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

}
