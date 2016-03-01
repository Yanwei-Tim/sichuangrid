package com.tianque.gis.domain.vo;

import java.util.List;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.issue.domain.IssueNew;

public class RoundBuildingInfo {

	private List<BuildingHouseVo> buildingHouseVos;

	private int householdstaffAllCount;

	private int floatingPopulationAllCount;

	private int unsettledPopulationAllCount;

	private int actualALLPopulationCount;

	private int overseaPersonnelAllCount;

	private int unitAllRoom;

	private List<ActualCompany> allCompanyList;

	private int allCompanyCount;

	private List<IssueNew> issueNewList;

	private int issueCount;

	/**
	 * 统计周边的所有的房屋信息
	 */
	public void countALLData() {

		if (buildingHouseVos != null) {
			for (BuildingHouseVo bh : buildingHouseVos) {
				householdstaffAllCount += bh.getHouseholdstaffCount();
				floatingPopulationAllCount += bh.getFloatingPopulationCount();
				unsettledPopulationAllCount += bh.getUnsettledPopulationCount();
				overseaPersonnelAllCount += bh.getOverseaPersonnelCount();
			}
			unitAllRoom = buildingHouseVos.size();
		}

		if (allCompanyList != null) {
			allCompanyCount = allCompanyList.size();
		}
		if (issueNewList != null) {
			issueCount = issueNewList.size();
		}
		actualALLPopulationCount = householdstaffAllCount + floatingPopulationAllCount
				+ unsettledPopulationAllCount + overseaPersonnelAllCount;
	}

	public List<BuildingHouseVo> getBuildingHouseVos() {
		return buildingHouseVos;
	}

	public void setBuildingHouseVos(List<BuildingHouseVo> buildingHouseVos) {
		this.buildingHouseVos = buildingHouseVos;
	}

	public int getHouseholdstaffAllCount() {
		return householdstaffAllCount;
	}

	public void setHouseholdstaffAllCount(int householdstaffAllCount) {
		this.householdstaffAllCount = householdstaffAllCount;
	}

	public int getFloatingPopulationAllCount() {
		return floatingPopulationAllCount;
	}

	public void setFloatingPopulationAllCount(int floatingPopulationAllCount) {
		this.floatingPopulationAllCount = floatingPopulationAllCount;
	}

	public int getUnsettledPopulationAllCount() {
		return unsettledPopulationAllCount;
	}

	public void setUnsettledPopulationAllCount(int unsettledPopulationAllCount) {
		this.unsettledPopulationAllCount = unsettledPopulationAllCount;
	}

	public int getActualALLPopulationCount() {
		return actualALLPopulationCount;
	}

	public void setActualALLPopulationCount(int actualALLPopulationCount) {
		this.actualALLPopulationCount = actualALLPopulationCount;
	}

	public int getOverseaPersonnelAllCount() {
		return overseaPersonnelAllCount;
	}

	public void setOverseaPersonnelAllCount(int overseaPersonnelAllCount) {
		this.overseaPersonnelAllCount = overseaPersonnelAllCount;
	}

	public int getUnitAllRoom() {
		return unitAllRoom;
	}

	public void setUnitAllRoom(int unitAllRoom) {
		this.unitAllRoom = unitAllRoom;
	}

	public List<ActualCompany> getAllCompanyList() {
		return allCompanyList;
	}

	public void setAllCompanyList(List<ActualCompany> allCompanyList) {
		this.allCompanyList = allCompanyList;
	}

	public int getAllCompanyCount() {
		return allCompanyCount;
	}

	public void setAllCompanyCount(int allCompanyCount) {
		this.allCompanyCount = allCompanyCount;
	}

	public List<IssueNew> getIssueNewList() {
		return issueNewList;
	}

	public void setIssueNewList(List<IssueNew> issueNewList) {
		this.issueNewList = issueNewList;
	}

	public int getIssueCount() {
		return issueCount;
	}

	public void setIssueCount(int issueCount) {
		this.issueCount = issueCount;
	}

}
