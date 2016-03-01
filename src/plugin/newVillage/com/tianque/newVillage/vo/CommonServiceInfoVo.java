package com.tianque.newVillage.vo;

import com.tianque.core.base.BaseDomain;

public class CommonServiceInfoVo extends BaseDomain {

	/** 公共服务字段 */
	private int villageSchool;// 村小
	private int kindergarten;// 幼儿园
	private int highSchool;// 中学

	private int clinic;// 卫生所

	private int socialWorkCenter;// 公共服务中心
	private int library;// 图书馆

	private double fitnessSquare;// 健身广场(平方米)
	private int fitnessEquipment;// 健身器材(套)
	private int entertainmentRoom;// 文化活动室
	private int farmerSupermarket;// 农家超市

	public int getVillageSchool() {
		return villageSchool;
	}

	public void setVillageSchool(int villageSchool) {
		this.villageSchool = villageSchool;
	}

	public int getKindergarten() {
		return kindergarten;
	}

	public void setKindergarten(int kindergarten) {
		this.kindergarten = kindergarten;
	}

	public int getHighSchool() {
		return highSchool;
	}

	public void setHighSchool(int highSchool) {
		this.highSchool = highSchool;
	}

	public int getClinic() {
		return clinic;
	}

	public void setClinic(int clinic) {
		this.clinic = clinic;
	}

	public int getSocialWorkCenter() {
		return socialWorkCenter;
	}

	public void setSocialWorkCenter(int socialWorkCenter) {
		this.socialWorkCenter = socialWorkCenter;
	}

	public int getLibrary() {
		return library;
	}

	public void setLibrary(int library) {
		this.library = library;
	}

	public double getFitnessSquare() {
		return fitnessSquare;
	}

	public void setFitnessSquare(double fitnessSquare) {
		this.fitnessSquare = fitnessSquare;
	}

	public int getFitnessEquipment() {
		return fitnessEquipment;
	}

	public void setFitnessEquipment(int fitnessEquipment) {
		this.fitnessEquipment = fitnessEquipment;
	}

	public int getEntertainmentRoom() {
		return entertainmentRoom;
	}

	public void setEntertainmentRoom(int entertainmentRoom) {
		this.entertainmentRoom = entertainmentRoom;
	}

	public int getFarmerSupermarket() {
		return farmerSupermarket;
	}

	public void setFarmerSupermarket(int farmerSupermarket) {
		this.farmerSupermarket = farmerSupermarket;
	}

}
