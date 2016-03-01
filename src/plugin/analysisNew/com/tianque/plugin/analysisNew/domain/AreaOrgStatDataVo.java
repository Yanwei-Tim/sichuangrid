package com.tianque.plugin.analysisNew.domain;

import java.io.Serializable;

import com.tianque.domain.Organization;

public class AreaOrgStatDataVo implements Serializable {
	private Organization org;
	private int provinceCount;// 省
	private int cityCount;// 市
	private int districtCount;// 县区个数
	private int townCount;// 镇个数
	private int villageCount;// 村，社区个数
	private int gridCount;// 网格

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public int getProvinceCount() {
		return provinceCount;
	}

	public void setProvinceCount(int provinceCount) {
		this.provinceCount = provinceCount;
	}

	public int getCityCount() {
		return cityCount;
	}

	public void setCityCount(int cityCount) {
		this.cityCount = cityCount;
	}

	public int getDistrictCount() {
		return districtCount;
	}

	public void setDistrictCount(int districtCount) {
		this.districtCount = districtCount;
	}

	public int getTownCount() {
		return townCount;
	}

	public void setTownCount(int townCount) {
		this.townCount = townCount;
	}

	public int getVillageCount() {
		return villageCount;
	}

	public void setVillageCount(int villageCount) {
		this.villageCount = villageCount;
	}

	public int getGridCount() {
		return gridCount;
	}

	public void setGridCount(int gridCount) {
		this.gridCount = gridCount;
	}

}
