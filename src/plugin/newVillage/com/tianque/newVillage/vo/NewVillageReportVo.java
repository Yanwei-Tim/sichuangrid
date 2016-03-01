package com.tianque.newVillage.vo;

import com.tianque.core.base.BaseDomain;

public class NewVillageReportVo extends BaseDomain {

	/** 新农村建设字段 */
	private int settlementsNumber;// 聚居点数量
	private int settlementsBuild;// 新建数

	private int settlementsReform;// 改造数据

	private int settlementsProtect;// 保护数

	private int involvingFarmers;// 涉及农户

	private int houseCount;// 房屋总数

	private int masonryStructure;// 砖混结构户数

	private int noHouseCount;// 无房户数

	private int dangerousHouseCount;// 危房户数

	private int housingDifficultCount;// 住房困难户数

	private int lowRentHousing;// 廉租房数

	public int getSettlementsNumber() {
		return settlementsNumber;
	}

	public void setSettlementsNumber(int settlementsNumber) {
		this.settlementsNumber = settlementsNumber;
	}

	public int getSettlementsBuild() {
		return settlementsBuild;
	}

	public void setSettlementsBuild(int settlementsBuild) {
		this.settlementsBuild = settlementsBuild;
	}

	public int getSettlementsReform() {
		return settlementsReform;
	}

	public void setSettlementsReform(int settlementsReform) {
		this.settlementsReform = settlementsReform;
	}

	public int getSettlementsProtect() {
		return settlementsProtect;
	}

	public void setSettlementsProtect(int settlementsProtect) {
		this.settlementsProtect = settlementsProtect;
	}

	public int getInvolvingFarmers() {
		return involvingFarmers;
	}

	public void setInvolvingFarmers(int involvingFarmers) {
		this.involvingFarmers = involvingFarmers;
	}

	public int getHouseCount() {
		return houseCount;
	}

	public void setHouseCount(int houseCount) {
		this.houseCount = houseCount;
	}

	public int getMasonryStructure() {
		return masonryStructure;
	}

	public void setMasonryStructure(int masonryStructure) {
		this.masonryStructure = masonryStructure;
	}

	public int getNoHouseCount() {
		return noHouseCount;
	}

	public void setNoHouseCount(int noHouseCount) {
		this.noHouseCount = noHouseCount;
	}

	public int getDangerousHouseCount() {
		return dangerousHouseCount;
	}

	public void setDangerousHouseCount(int dangerousHouseCount) {
		this.dangerousHouseCount = dangerousHouseCount;
	}

	public int getHousingDifficultCount() {
		return housingDifficultCount;
	}

	public void setHousingDifficultCount(int housingDifficultCount) {
		this.housingDifficultCount = housingDifficultCount;
	}

	public int getLowRentHousing() {
		return lowRentHousing;
	}

	public void setLowRentHousing(int lowRentHousing) {
		this.lowRentHousing = lowRentHousing;
	}

}
