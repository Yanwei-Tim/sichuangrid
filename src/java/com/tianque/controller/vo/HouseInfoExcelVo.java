package com.tianque.controller.vo;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;

public class HouseInfoExcelVo extends HouseInfo {
	private String water;
	private String electricity;
	private String gas;
	private String warm;

	public String getWater() {
		return water;
	}

	public void setWater(String water) {
		this.water = water;
	}

	public String getElectricity() {
		return electricity;
	}

	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}

	public String getGas() {
		return gas;
	}

	public void setGas(String gas) {
		this.gas = gas;
	}

	public String getWarm() {
		return warm;
	}

	public void setWarm(String warm) {
		this.warm = warm;
	}
}
