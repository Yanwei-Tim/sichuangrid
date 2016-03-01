package com.tianque.baseInfo.unemployedPeople.domain;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class UnemployedPeople extends AttentionPopulation {
	private PropertyDict unemployedPeopleType;
	private String registerNumber;
	private Date upEnterWorkDate;
	private String originalWorkUnit;
	private String originalWorkUnitType;
	private String title;
	private Date unemploymentDate;

	private PropertyDict technologyLevel;
	private PropertyDict unemploymentReason;
	private String specialtySkills;
	private List<PropertyDict> employmentIntention;

	private List<PropertyDict> trainingIntention;
	private String participatedPrograms;

	/** 用于导出 */
	private String employmentIntentionString;
	private String trainingIntentionString;

	public UnemployedPeople() {
		setAttentionPopulationType(BaseInfoTables.UNEMPLOYEDPEOPLE_KEY);
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getUpEnterWorkDate() {
		return upEnterWorkDate;
	}

	public void setUpEnterWorkDate(Date upEnterWorkDate) {
		this.upEnterWorkDate = upEnterWorkDate;
	}

	public String getOriginalWorkUnit() {
		return originalWorkUnit;
	}

	public void setOriginalWorkUnit(String originalWorkUnit) {
		this.originalWorkUnit = originalWorkUnit;
	}

	public String getOriginalWorkUnitType() {
		return originalWorkUnitType;
	}

	public void setOriginalWorkUnitType(String originalWorkUnitType) {
		this.originalWorkUnitType = originalWorkUnitType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getUnemploymentDate() {
		return unemploymentDate;
	}

	public void setUnemploymentDate(Date unemploymentDate) {
		this.unemploymentDate = unemploymentDate;
	}

	public PropertyDict getTechnologyLevel() {
		return technologyLevel;
	}

	public void setTechnologyLevel(PropertyDict technologyLevel) {
		this.technologyLevel = technologyLevel;
	}

	public PropertyDict getUnemploymentReason() {
		return unemploymentReason;
	}

	public void setUnemploymentReason(PropertyDict unemploymentReason) {
		this.unemploymentReason = unemploymentReason;
	}

	public String getParticipatedPrograms() {
		return participatedPrograms;
	}

	public void setParticipatedPrograms(String participatedPrograms) {
		this.participatedPrograms = participatedPrograms;
	}

	public List<PropertyDict> getEmploymentIntention() {
		return employmentIntention;
	}

	public void setEmploymentIntention(List<PropertyDict> employmentIntention) {
		this.employmentIntention = employmentIntention;
	}

	public List<PropertyDict> getTrainingIntention() {
		return trainingIntention;
	}

	public void setTrainingIntention(List<PropertyDict> trainingIntention) {
		this.trainingIntention = trainingIntention;
	}

	public PropertyDict getUnemployedPeopleType() {
		return unemployedPeopleType;
	}

	public void setUnemployedPeopleType(PropertyDict unemployedPeopleType) {
		this.unemployedPeopleType = unemployedPeopleType;
	}

	public String getSpecialtySkills() {
		return specialtySkills;
	}

	public void setSpecialtySkills(String specialtySkills) {
		this.specialtySkills = specialtySkills;
	}

	public String getEmploymentIntentionString() {
		return employmentIntentionString;
	}

	public void setEmploymentIntentionString(String employmentIntentionString) {
		this.employmentIntentionString = employmentIntentionString;
	}

	public String getTrainingIntentionString() {
		return trainingIntentionString;
	}

	public void setTrainingIntentionString(String trainingIntentionString) {
		this.trainingIntentionString = trainingIntentionString;
	}

}
