package com.tianque.gis.domain.vo;

public class HousePopulationVo {

	private String personType;
	private Long houseId;
	private Long populationId;
	private String populationType;
	private boolean defaultLivingHouse;
	private int centerX;
	private int centerY;

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

	public boolean isDefaultLivingHouse() {
		return defaultLivingHouse;
	}

	public void setDefaultLivingHouse(boolean defaultLivingHouse) {
		this.defaultLivingHouse = defaultLivingHouse;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

}
