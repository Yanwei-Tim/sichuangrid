package com.tianque.domain;

import java.io.Serializable;

/**
 * 人房关联关系表
 */
@SuppressWarnings("serial")
public class HouseHasActualPopulation implements Serializable {

	/** 人员大类 */
	private String personType;

	/** 实口类型 */
	private String populationType;

	/** 房屋ID */
	private Long houseId;

	/** 实口ID */
	private Long populationId;

	/** 是否将该居住信息应用于人员信息 */
	private Long defaultLivingHouse;

	public HouseHasActualPopulation(String personType, String populationType, Long houseId,
			Long populationId, Long defaultLivingHouse) {
		super();
		this.personType = personType;
		this.populationType = populationType;
		this.houseId = houseId;
		this.populationId = populationId;
		this.defaultLivingHouse = defaultLivingHouse;
	}

	public HouseHasActualPopulation() {

	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public Long getPopulationId() {
		return populationId;
	}

	public void setPopulationId(Long populationId) {
		this.populationId = populationId;
	}

	public Long getDefaultLivingHouse() {
		return defaultLivingHouse;
	}

	public void setDefaultLivingHouse(Long defaultLivingHouse) {
		this.defaultLivingHouse = defaultLivingHouse;
	}
}
