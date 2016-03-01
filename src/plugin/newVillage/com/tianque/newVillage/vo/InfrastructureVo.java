package com.tianque.newVillage.vo;

import com.tianque.core.base.BaseDomain;

public class InfrastructureVo extends BaseDomain {

	private double villageRoad;// 村社道路
	private double villageHardenedRoad;// 村路-硬化路(公里)
	private double villageMudKnotRoad;// 村路-泥结路(公里)

	private int weirPoolNum;// 塘湖堰池(数量)
	private double canalLength;// 水渠总长度(公里)
	private int drinkingWaterNum;// 生活饮用水已解决人户数

	private int isPowerGrid;// 电网是否改造(0未改造 1已改造)
	private int biogasNum;// 沼气(口)
	private int involveHouseCount;// 三建四改设计户数

	private int isBroadbandVillage;// 是否宽带乡村(0不是1是)
	private int cableTvCount;// 有线电视
	private int broadbandCount;// 宽带

	public double getVillageRoad() {
		return villageRoad;
	}

	public void setVillageRoad(double villageRoad) {
		this.villageRoad = villageRoad;
	}

	public double getVillageHardenedRoad() {
		return villageHardenedRoad;
	}

	public void setVillageHardenedRoad(double villageHardenedRoad) {
		this.villageHardenedRoad = villageHardenedRoad;
	}

	public double getVillageMudKnotRoad() {
		return villageMudKnotRoad;
	}

	public void setVillageMudKnotRoad(double villageMudKnotRoad) {
		this.villageMudKnotRoad = villageMudKnotRoad;
	}

	public int getWeirPoolNum() {
		return weirPoolNum;
	}

	public void setWeirPoolNum(int weirPoolNum) {
		this.weirPoolNum = weirPoolNum;
	}

	public double getCanalLength() {
		return canalLength;
	}

	public void setCanalLength(double canalLength) {
		this.canalLength = canalLength;
	}

	public int getDrinkingWaterNum() {
		return drinkingWaterNum;
	}

	public void setDrinkingWaterNum(int drinkingWaterNum) {
		this.drinkingWaterNum = drinkingWaterNum;
	}

	public int getIsPowerGrid() {
		return isPowerGrid;
	}

	public void setIsPowerGrid(int isPowerGrid) {
		this.isPowerGrid = isPowerGrid;
	}

	public int getBiogasNum() {
		return biogasNum;
	}

	public void setBiogasNum(int biogasNum) {
		this.biogasNum = biogasNum;
	}

	public int getInvolveHouseCount() {
		return involveHouseCount;
	}

	public void setInvolveHouseCount(int involveHouseCount) {
		this.involveHouseCount = involveHouseCount;
	}

	public int getIsBroadbandVillage() {
		return isBroadbandVillage;
	}

	public void setIsBroadbandVillage(int isBroadbandVillage) {
		this.isBroadbandVillage = isBroadbandVillage;
	}

	public int getCableTvCount() {
		return cableTvCount;
	}

	public void setCableTvCount(int cableTvCount) {
		this.cableTvCount = cableTvCount;
	}

	public int getBroadbandCount() {
		return broadbandCount;
	}

	public void setBroadbandCount(int broadbandCount) {
		this.broadbandCount = broadbandCount;
	}

}
