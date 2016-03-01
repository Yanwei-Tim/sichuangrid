package com.tianque.newVillage.vo;

import com.tianque.core.base.BaseDomain;

public class IndustryDevelopmentVo extends BaseDomain {
	/** 产业发展字段 */
	private double grainCrops;// 粮食作物亩数
	private double economicCrops;// 经济作物亩数
	private int scalePlanting;// 适度规模种植户数
	private int pigNum;// 生猪数
	private int cattleSheepNum;// 牛羊数
	private int poultryNum;// 小家禽数
	private double aquaticProductNum;// 水产数
	private int scaleBreed;// 适度规模养殖户
	private int specialistNum;// 专业合作组织数
	private int familyFarmNum;// 家庭农场(个数)
	private int plantingNum;// 种养大户户数

	public double getGrainCrops() {
		return grainCrops;
	}

	public void setGrainCrops(double grainCrops) {
		this.grainCrops = grainCrops;
	}

	public double getEconomicCrops() {
		return economicCrops;
	}

	public void setEconomicCrops(double economicCrops) {
		this.economicCrops = economicCrops;
	}

	public int getScalePlanting() {
		return scalePlanting;
	}

	public void setScalePlanting(int scalePlanting) {
		this.scalePlanting = scalePlanting;
	}

	public int getPigNum() {
		return pigNum;
	}

	public void setPigNum(int pigNum) {
		this.pigNum = pigNum;
	}

	public int getCattleSheepNum() {
		return cattleSheepNum;
	}

	public void setCattleSheepNum(int cattleSheepNum) {
		this.cattleSheepNum = cattleSheepNum;
	}

	public int getPoultryNum() {
		return poultryNum;
	}

	public void setPoultryNum(int poultryNum) {
		this.poultryNum = poultryNum;
	}

	public double getAquaticProductNum() {
		return aquaticProductNum;
	}

	public void setAquaticProductNum(double aquaticProductNum) {
		this.aquaticProductNum = aquaticProductNum;
	}

	public int getScaleBreed() {
		return scaleBreed;
	}

	public void setScaleBreed(int scaleBreed) {
		this.scaleBreed = scaleBreed;
	}

	public int getSpecialistNum() {
		return specialistNum;
	}

	public void setSpecialistNum(int specialistNum) {
		this.specialistNum = specialistNum;
	}

	public int getFamilyFarmNum() {
		return familyFarmNum;
	}

	public void setFamilyFarmNum(int familyFarmNum) {
		this.familyFarmNum = familyFarmNum;
	}

	public int getPlantingNum() {
		return plantingNum;
	}

	public void setPlantingNum(int plantingNum) {
		this.plantingNum = plantingNum;
	}

}
