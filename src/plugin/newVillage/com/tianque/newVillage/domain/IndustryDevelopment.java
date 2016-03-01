package com.tianque.newVillage.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * @ClassName: IndustryDevelopment
 * @Description: 产业发展
 */
public class IndustryDevelopment extends BaseDomain {
	private Organization organization;// 组织机构
	private NewVillageBasic newVillageBasic;// 基本信息
	private Double grainCrops;// 粮食作物亩数
	private Double economicCrops;// 经济作物亩数
	
	
	private Integer scalePlanting;// 适度规模种植户数
	
	private Integer pigNum;// 生猪数
	private Integer cattleSheepNum;// 牛羊数
	private Integer poultryNum;// 小家禽数
	private Double aquaticProductNum;// 水产数
	private Integer scaleBreed;// 适度规模养殖户
	
	private Integer specialistNum;// 专业合作组织数
	private Integer familyFarmNum;// 家庭农场(个数)
	private Integer plantingNum;// 种养大户户数
	
	private Double characteristicPlanting;//特色产业种植数
	private Integer plantingHouseholdsCount;//种植户总数
	private Integer farmHouseholds;//养殖户总数
	private Integer productProcessing;//农产品加工企业
	private Integer countyCorporateChampion;//其中县级以上龙头企业
	private Integer industrialOrganization;//产业化经营组织带动农户数
	private Integer villaggioBoutiqueHotel;//乡村酒店
	private Integer agritainment;//农家乐
	private Integer householdEmployment;//带动农户就业人数
	
	public Double getCharacteristicPlanting() {
		return characteristicPlanting==null?0:characteristicPlanting;
	}

	public void setCharacteristicPlanting(Double characteristicPlanting) {
		this.characteristicPlanting = characteristicPlanting;
	}

	public Integer getPlantingHouseholdsCount() {
		return plantingHouseholdsCount==null?0:plantingHouseholdsCount;
	}

	public void setPlantingHouseholdsCount(Integer plantingHouseholdsCount) {
		this.plantingHouseholdsCount = plantingHouseholdsCount;
	}

	public Integer getFarmHouseholds() {
		return farmHouseholds==null?0:farmHouseholds;
	}

	public void setFarmHouseholds(Integer farmHouseholds) {
		this.farmHouseholds = farmHouseholds;
	}

	public Integer getProductProcessing() {
		return productProcessing==null?0:productProcessing;
	}

	public void setProductProcessing(Integer productProcessing) {
		this.productProcessing = productProcessing;
	}

	public Integer getCountyCorporateChampion() {
		return countyCorporateChampion==null?0:countyCorporateChampion;
	}

	public void setCountyCorporateChampion(Integer countyCorporateChampion) {
		this.countyCorporateChampion = countyCorporateChampion;
	}

	public Integer getIndustrialOrganization() {
		return industrialOrganization==null?0:industrialOrganization;
	}

	public void setIndustrialOrganization(Integer industrialOrganization) {
		this.industrialOrganization = industrialOrganization;
	}

	public Integer getVillaggioBoutiqueHotel() {
		return villaggioBoutiqueHotel==null?0:villaggioBoutiqueHotel;
	}

	public void setVillaggioBoutiqueHotel(Integer villaggioBoutiqueHotel) {
		this.villaggioBoutiqueHotel = villaggioBoutiqueHotel;
	}

	public Integer getAgritainment() {
		return agritainment==null?0:agritainment;
	}

	public void setAgritainment(Integer agritainment) {
		this.agritainment = agritainment;
	}

	public Integer getHouseholdEmployment() {
		return householdEmployment==null?0:householdEmployment;
	}

	public void setHouseholdEmployment(Integer householdEmployment) {
		this.householdEmployment = householdEmployment;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public NewVillageBasic getNewVillageBasic() {
		return newVillageBasic;
	}

	public void setNewVillageBasic(NewVillageBasic newVillageBasic) {
		this.newVillageBasic = newVillageBasic;
	}

	public Double getGrainCrops() {
		return grainCrops == null ? 0 : grainCrops;
	}

	public void setGrainCrops(Double grainCrops) {
		this.grainCrops = grainCrops;
	}

	public Double getEconomicCrops() {
		return economicCrops == null ? 0 : economicCrops;
	}

	public void setEconomicCrops(Double economicCrops) {
		this.economicCrops = economicCrops;
	}

	public Integer getScalePlanting() {
		return scalePlanting == null ? 0 : scalePlanting;
	}

	public void setScalePlanting(Integer scalePlanting) {
		this.scalePlanting = scalePlanting;
	}

	public Integer getPigNum() {
		return pigNum == null ? 0 : pigNum;
	}

	public void setPigNum(Integer pigNum) {
		this.pigNum = pigNum;
	}

	public Integer getCattleSheepNum() {
		return cattleSheepNum == null ? 0 : cattleSheepNum;
	}

	public void setCattleSheepNum(Integer cattleSheepNum) {
		this.cattleSheepNum = cattleSheepNum;
	}

	public Integer getPoultryNum() {
		return poultryNum == null ? 0 : poultryNum;
	}

	public void setPoultryNum(Integer poultryNum) {
		this.poultryNum = poultryNum;
	}

	public Integer getScaleBreed() {
		return scaleBreed == null ? 0 : scaleBreed;
	}

	public void setScaleBreed(Integer scaleBreed) {
		this.scaleBreed = scaleBreed;
	}

	public Integer getSpecialistNum() {
		return specialistNum == null ? 0 : specialistNum;
	}

	public void setSpecialistNum(Integer specialistNum) {
		this.specialistNum = specialistNum;
	}

	public Integer getFamilyFarmNum() {
		return familyFarmNum == null ? 0 : familyFarmNum;
	}

	public void setFamilyFarmNum(Integer familyFarmNum) {
		this.familyFarmNum = familyFarmNum;
	}

	public Integer getPlantingNum() {
		return plantingNum == null ? 0 : plantingNum;
	}

	public void setPlantingNum(Integer plantingNum) {
		this.plantingNum = plantingNum;
	}

	public Double getAquaticProductNum() {
		return aquaticProductNum == null ? 0 : aquaticProductNum;
	}

	public void setAquaticProductNum(Double aquaticProductNum) {
		this.aquaticProductNum = aquaticProductNum;
	}

}