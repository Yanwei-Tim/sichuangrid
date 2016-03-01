package com.tianque.gis.domain.vo;

import java.util.List;

public class BuildingInfoVo {

	private long buildingId;

	private int actualCompanyCount;

	private List<BuildingHouseVo> buildingHouseVos;

	public BuildingInfoVo(long buildingId, int actualCompanyCount,
			List<BuildingHouseVo> buildingHouseVos) {
		super();
		this.buildingId = buildingId;
		this.actualCompanyCount = actualCompanyCount;
		this.buildingHouseVos = buildingHouseVos;
	}

	public BuildingInfoVo() {
		super();
	}

	public long getBuildingId() {
		return buildingId;
	}

	public List<BuildingHouseVo> getBuildingHouseVos() {
		return buildingHouseVos;
	}

	public void setBuildingId(long buildingId) {
		this.buildingId = buildingId;
	}

	public void setBuildingHouseVos(List<BuildingHouseVo> buildingHouseVos) {
		this.buildingHouseVos = buildingHouseVos;
	}

	public void setActualCompanyCount(int actualCompanyCount) {
		this.actualCompanyCount = actualCompanyCount;
	}

	public int getActualCompanyCount() {
		return actualCompanyCount;
	}

	public int getActualHouseCount() {
		return null == getBuildingHouseVos() ? 0 : getBuildingHouseVos().size();
	}

	public int getRentalHouseCount() {
		int rentalHouseCount = 0;
		if (null != getBuildingHouseVos() && getBuildingHouseVos().size() > 0) {
			for (BuildingHouseVo vo : getBuildingHouseVos()) {
				if (vo.getIsRentalHouse()) {
					rentalHouseCount++;
				}
			}
		}
		return rentalHouseCount;
	}

	public int getHouseholdstaffCount() {
		int householdstaffCount = 0;
		if (null != getBuildingHouseVos() && getBuildingHouseVos().size() > 0) {
			for (BuildingHouseVo vo : getBuildingHouseVos()) {
				householdstaffCount += vo.getHouseholdstaffCount();
			}
		}
		return householdstaffCount;
	}

	public int getFloatingPopulationCount() {
		int floatingPopulationCount = 0;
		if (null != getBuildingHouseVos() && getBuildingHouseVos().size() > 0) {
			for (BuildingHouseVo vo : getBuildingHouseVos()) {
				floatingPopulationCount += vo.getFloatingPopulationCount();
			}
		}
		return floatingPopulationCount;
	}

	public int getUnsettledPopulationCount() {
		int unsettledPopulationCount = 0;
		if (null != getBuildingHouseVos() && getBuildingHouseVos().size() > 0) {
			for (BuildingHouseVo vo : getBuildingHouseVos()) {
				unsettledPopulationCount += vo.getUnsettledPopulationCount();
			}
		}
		return unsettledPopulationCount;
	}

	public int getOverseaPersonnelCount() {
		int overseaPersonnelCount = 0;
		if (null != getBuildingHouseVos() && getBuildingHouseVos().size() > 0) {
			for (BuildingHouseVo vo : getBuildingHouseVos()) {
				overseaPersonnelCount += vo.getOverseaPersonnelCount();
			}
		}
		return overseaPersonnelCount;
	}

	public int getEmphasisPopulationCount() {
		int emphasisPopulationCount = 0;
		if (null != getBuildingHouseVos() && getBuildingHouseVos().size() > 0) {
			for (BuildingHouseVo vo : getBuildingHouseVos()) {
				emphasisPopulationCount += vo.getEmphasisPopulationCount();
			}
		}
		return emphasisPopulationCount;
	}

	public int getNurturesWomenCount() {
		int nurturesWomenCount = 0;
		if (null != getBuildingHouseVos() && getBuildingHouseVos().size() > 0) {
			for (BuildingHouseVo vo : getBuildingHouseVos()) {
				nurturesWomenCount += vo.getNurturesWomenCount();
			}
		}
		return nurturesWomenCount;
	}

	public int getSolicitudeObjectCount() {
		int solicitudeObjectCount = 0;
		if (null != getBuildingHouseVos() && getBuildingHouseVos().size() > 0) {
			for (BuildingHouseVo vo : getBuildingHouseVos()) {
				solicitudeObjectCount += vo.getSolicitudeObjectCount();
			}
		}
		return solicitudeObjectCount;
	}

	public int getActualPopulationCount() {
		return getHouseholdstaffCount() + getFloatingPopulationCount()
				+ getUnsettledPopulationCount() + getOverseaPersonnelCount();
	}

}
