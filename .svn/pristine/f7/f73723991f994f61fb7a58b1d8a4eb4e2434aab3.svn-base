package com.tianque.baseInfo.familyInfo.domain;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.PropertyDict;

/**
 * 团队家庭人员关系
 * 
 * @author guwenbiao
 */
public class GroupFamilyHasPopulation {

	private String id;

	private Long familyId;

	private PropertyDict familyRelation;

	private Long populationId;

	private String populationType;

	private Countrymen population;

	public GroupFamilyHasPopulation(Long familyId, PropertyDict familyRelation, Long populationId,
			String populationType) {
		super();
		this.familyId = familyId;
		this.familyRelation = familyRelation;
		this.populationId = populationId;
		this.populationType = populationType;
	}

	public GroupFamilyHasPopulation() {
		super();
	}

	public Long getFamilyId() {
		return familyId;
	}

	public PropertyDict getFamilyRelation() {
		return familyRelation;
	}

	public Long getPopulationId() {
		return populationId;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setFamilyId(Long familyId) {
		this.familyId = familyId;
	}

	public void setFamilyRelation(PropertyDict familyRelation) {
		this.familyRelation = familyRelation;
	}

	public void setPopulationId(Long populationId) {
		this.populationId = populationId;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public Countrymen getPopulation() {
		return population;
	}

	public void setPopulation(Countrymen population) {
		this.population = population;
	}

	public String getId() {
		id = population.getId() + "_" + population.getActualPopulationType();
		return id;
	}
	
	public String getPopulationIdEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(this.populationId,
				null, null);
	}
}
