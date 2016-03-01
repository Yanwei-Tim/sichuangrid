package com.tianque.newVillage.vo;

import com.tianque.core.base.BaseDomain;

public class EnvironmentalReformVo extends BaseDomain {
	/** 环境改造字段 */
	private int garbageTank;// 垃圾池个数
	private int toilets;// 公厕
	private int treatmentFacilities;// 污水处理设施

	public int getGarbageTank() {
		return garbageTank;
	}

	public void setGarbageTank(int garbageTank) {
		this.garbageTank = garbageTank;
	}

	public int getToilets() {
		return toilets;
	}

	public void setToilets(int toilets) {
		this.toilets = toilets;
	}

	public int getTreatmentFacilities() {
		return treatmentFacilities;
	}

	public void setTreatmentFacilities(int treatmentFacilities) {
		this.treatmentFacilities = treatmentFacilities;
	}
}
