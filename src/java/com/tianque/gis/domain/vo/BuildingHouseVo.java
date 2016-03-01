package com.tianque.gis.domain.vo;

public class BuildingHouseVo {

	private Long houseId;

	private String houseCode;

	private Boolean isRentalHouse;

	private String unitRoom;

	private int householdstaffCount;

	private int floatingPopulationCount;

	private int unsettledPopulationCount;

	private int overseaPersonnelCount;

	private int emphasisPopulationCount;

	private int nurturesWomenCount;

	private int solicitudeObjectCount;

	public BuildingHouseVo() {
	}

	public BuildingHouseVo(Long houseId, String houseCode, Boolean isRentalHouse, String unitRoom) {
		this.houseId = houseId;
		this.houseCode = houseCode;
		this.isRentalHouse = isRentalHouse;
		this.unitRoom = unitRoom;
		this.householdstaffCount = 0;
		this.floatingPopulationCount = 0;
		this.unsettledPopulationCount = 0;
		this.overseaPersonnelCount = 0;
		this.emphasisPopulationCount = 0;
		this.nurturesWomenCount = 0;
		this.solicitudeObjectCount = 0;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public Boolean getIsRentalHouse() {
		return isRentalHouse;
	}

	public void setIsRentalHouse(Boolean isRentalHouse) {
		this.isRentalHouse = isRentalHouse;
	}

	public int getHouseholdstaffCount() {
		return householdstaffCount;
	}

	public void setUnitRoom(String unitRoom) {
		this.unitRoom = unitRoom;
	}

	public String getUnitRoom() {
		return unitRoom;
	}

	public void setHouseholdstaffCount(int householdstaffCount) {
		this.householdstaffCount = householdstaffCount;
	}

	public int getFloatingPopulationCount() {
		return floatingPopulationCount;
	}

	public void setFloatingPopulationCount(int floatingPopulationCount) {
		this.floatingPopulationCount = floatingPopulationCount;
	}

	public int getUnsettledPopulationCount() {
		return unsettledPopulationCount;
	}

	public void setUnsettledPopulationCount(int unsettledPopulationCount) {
		this.unsettledPopulationCount = unsettledPopulationCount;
	}

	public int getOverseaPersonnelCount() {
		return overseaPersonnelCount;
	}

	public void setOverseaPersonnelCount(int overseaPersonnelCount) {
		this.overseaPersonnelCount = overseaPersonnelCount;
	}

	public int getEmphasisPopulationCount() {
		return emphasisPopulationCount;
	}

	public int getNurturesWomenCount() {
		return nurturesWomenCount;
	}

	public int getSolicitudeObjectCount() {
		return solicitudeObjectCount;
	}

	public void setEmphasisPopulationCount(int emphasisPopulationCount) {
		this.emphasisPopulationCount = emphasisPopulationCount;
	}

	public void setNurturesWomenCount(int nurturesWomenCount) {
		this.nurturesWomenCount = nurturesWomenCount;
	}

	public void setSolicitudeObjectCount(int solicitudeObjectCount) {
		this.solicitudeObjectCount = solicitudeObjectCount;
	}

	public boolean isDwelledHouseholdStaff() {
		return getHouseholdstaffCount() > 0;
	}

	public boolean isDwelledFloatingPopulation() {
		return getFloatingPopulationCount() > 0;
	}

	public boolean isDwelledunsettledPopulation() {
		return getUnsettledPopulationCount() > 0;
	}

	public boolean isDwelledOverseaPersonnel() {
		return getOverseaPersonnelCount() > 0;
	}

	public boolean isDwelledEmphasisPopulation() {
		return getEmphasisPopulationCount() > 0;
	}

	public boolean isDwelledNurturesWomen() {
		return getNurturesWomenCount() > 0;
	}

	public boolean isDwelledSolicitudeObject() {
		return getSolicitudeObjectCount() > 0;
	}
}
