package com.tianque.newVillage.vo;

import java.io.Serializable;

import com.tianque.domain.Organization;

public class NewVillageAssessmentVo implements Serializable{
	private Organization organization;
	private Double cultivatedLandArea;// 耕地面积    --BasicYearInfo   最新
	private Integer totalHouseholdsNum;// 总户数      --BasicYearInfo   最新
	private Integer totalPeopleNum;// 村总人数    --BasicYearInfo   最新
	//累计==============================
	private Double characteristicPlanting;//特色产业种植数(累计数)   --IndustryDevelopment
	private String characteristicPlantingStr;//百分比=characteristicPlanting/cultivatedLandArea
	private Integer characteristicPlantingFraction;//得分
	
	private Double capitaHousingArea;//人均安全住房面积（累计） --NewVillage
	private Integer capitaHousingAreaFraction;//得分
	
	private Integer recreationalActivities;//每年组织群众性文体活动    活动参与总人数/（村总人数*80%） 取整  (累计)
	private Integer recreationalActivitiesPeople;//参加活动人数  --CommonServiceInfo
	private Integer recreationalActivitiesFraction;//得分
	
	private Double socialWorkCenterArea;//公共活动服务中心设施面积 累计  --CommonServiceInfo
	private Integer socialWorkCenterAreaFraction;//得分
	
	private Double villageRoad;// 村社道路 --Infrastructure 累计
	private Double villageHardenedRoad;//通村通组道路硬化路长度/总长度 --Infrastructure
	private String villageHardenedRoadStr;//=villageHardenedProportion/villageRoad
	private Integer villageHardenedFraction;//得分
	
	private Double drinkingWaterNum;//安全饮水全覆盖/总户数 --Infrastructure  累计
	private String drinkingWaterStr;//totalHouseholdsNum/drinkingWaterNum
	private Integer drinkingWaterNumFraction;//得分
	
	private Double sanitarySewage;//生活污水处理覆盖率=生活污水处理户数/总户数 --EnvironmentalReform 累计
	private String sanitarySewageStr;//sanitarySewage/totalHouseholdsNum
	private Integer sanitarySewageFraction;//得分
	
	private Integer involveHouseCount;//农村院落整治全覆盖=三建四改设计户数/总户数 --Infrastructure  累计
	private String involveHouseStr;//involveHouseCount/totalHouseholdsNum
	private Integer involveHouseCountFraction;//得分
	//累计==============================
	
	
	
	private Double householdIncome;///农村家庭经营性收入 （取最新一次数据） --FarmerPerIncomeInfo
	private Double agriculturalIncome;//农业主导产业收入（取最新一次数据）--FarmerPerIncomeInfo
	private String proportionOfIncomeStr;//agriculturalIncome/householdIncome
	private Integer proportionOfIncomeFraction;//得分
	
	private Double farmerPerIncome;//人均可支配收入（取最新一次数据）--FarmerPerIncomeInfo
	private Integer farmerPerIncomeFraction;//得分
	
	private Integer isAllStanding;//新村建设全覆盖 是否通过新建、改造、保护等形式推进新村建设全覆盖（取最新一次数据） --NewVillage
	private String isAllStandingStr;
	private Integer isAllStandingFraction;//得分
	
	private Integer houseProblemCount;//无房户、危房户、住房困难户住房问题全部解决 数值等于0 （最新）--NewVillage
	private String houseProblemCountStr;
	private Integer houseProblemFraction;//得分
	
	private Integer compulsoryEducationCount;//九年义务教育目标人群全覆盖  适龄/入学   --CommonServiceInfo (最新)
	private Integer inCompulsoryEducationCount;//九年义务教育适龄已入学人数 --CommonServiceInfo (最新)
	private Integer allCompulsoryEducationCount;//是否全覆盖
	private String compulsoryEducationStr;//=compulsoryEducationCount/inCompulsoryEducationCount
	private Integer compulsoryEducationFraction;//得分
	
	private Integer insuredNumber;//新农合参保率  参保人数/村总人数   --CommonServiceInfo (最新)
	private String insuredProportionStr;//=insuredProportion/totalPeopleNum
	private Integer insuredProportionFraction;//得分
	
	private Integer hasBuyInsurance;//新农保应保尽保  --CommonServiceInfo (最新)
	private String hasBuyInsuranceStr;
	private Integer hasBuyInsuranceFraction;//得分
	
	private Double surveySatisfaction;//基层党组织/党员调查满意度 （取最新一次数据） --OrganizationConstruction
	private String surveySatisfactionStr;
	private Integer surveySatisfactionFraction;//得分
	
	private Double threeSatisfaction;;//三务公开满意度 （取最新一次数据） --OrganizationConstruction
	private String threeSatisfactionStr;
	private Integer threeSatisfactionFraction;//得分
	
	
	private Integer isPowerGrid;//电网改造全覆盖  （取最新一次数据） --Infrastructure
	private String isPowerGridStr;
	private Integer isPowerGridFraction;//得分
	
	private Integer  treatmentPollution;//面源污染有效治理 （取最新一次数据） --EnvironmentalReform
	private String treatmentPollutionStr;
	private Integer treatmentPollutionFraction;//得分
	
	private Integer garbageDisposal;//生活垃圾处理全覆盖 （取最新一次数据） --EnvironmentalReform
	private String garbageDisposalStr;
	private Integer garbageDisposalFraction;//得分
	
	private Integer newVillageFraction;//综合得分
	
//	private Integer isAdopt;//验收结果
	private String isAdoptStr;//结果

	
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Double getCharacteristicPlanting() {
		return characteristicPlanting==null?0:characteristicPlanting;
	}

	public void setCharacteristicPlanting(Double characteristicPlanting) {
		this.characteristicPlanting = characteristicPlanting;
	}

	public Integer getCharacteristicPlantingFraction() {
		return characteristicPlantingFraction==null?0:characteristicPlantingFraction;
	}

	public void setCharacteristicPlantingFraction(
			Integer characteristicPlantingFraction) {
		this.characteristicPlantingFraction = characteristicPlantingFraction;
	}


	public Double getAgriculturalIncome() {
		return agriculturalIncome==null?0:agriculturalIncome;
	}

	public void setAgriculturalIncome(Double agriculturalIncome) {
		this.agriculturalIncome = agriculturalIncome;
	}

	public Integer getProportionOfIncomeFraction() {
		return proportionOfIncomeFraction==null?0:proportionOfIncomeFraction;
	}

	public void setProportionOfIncomeFraction(Integer proportionOfIncomeFraction) {
		this.proportionOfIncomeFraction = proportionOfIncomeFraction;
	}

	public Double getFarmerPerIncome() {
		return farmerPerIncome==null?0:farmerPerIncome;
	}

	public void setFarmerPerIncome(Double farmerPerIncome) {
		this.farmerPerIncome = farmerPerIncome;
	}

	public Integer getFarmerPerIncomeFraction() {
		return farmerPerIncomeFraction==null?0:farmerPerIncomeFraction;
	}

	public void setFarmerPerIncomeFraction(Integer farmerPerIncomeFraction) {
		this.farmerPerIncomeFraction = farmerPerIncomeFraction;
	}

	public Double getCapitaHousingArea() {
		return capitaHousingArea==null?0:capitaHousingArea;
	}

	public void setCapitaHousingArea(Double capitaHousingArea) {
		this.capitaHousingArea = capitaHousingArea;
	}

	public Integer getCapitaHousingAreaFraction() {
		return capitaHousingAreaFraction==null?0:capitaHousingAreaFraction;
	}

	public void setCapitaHousingAreaFraction(Integer capitaHousingAreaFraction) {
		this.capitaHousingAreaFraction = capitaHousingAreaFraction;
	}

	public Integer getHouseProblemCount() {
		return houseProblemCount==null?0:houseProblemCount;
	}

	public void setHouseProblemCount(Integer houseProblemCount) {
		this.houseProblemCount = houseProblemCount;
	}

	public Integer getHouseProblemFraction() {
		return houseProblemFraction==null?0:houseProblemFraction;
	}

	public void setHouseProblemFraction(Integer houseProblemFraction) {
		this.houseProblemFraction = houseProblemFraction;
	}

	public Integer getCompulsoryEducationCount() {
		return compulsoryEducationCount==null?0:compulsoryEducationCount;
	}

	public void setCompulsoryEducationCount(Integer compulsoryEducationCount) {
		this.compulsoryEducationCount = compulsoryEducationCount;
	}

	public Integer getCompulsoryEducationFraction() {
		return compulsoryEducationFraction==null?0:compulsoryEducationFraction;
	}

	public void setCompulsoryEducationFraction(Integer compulsoryEducationFraction) {
		this.compulsoryEducationFraction = compulsoryEducationFraction;
	}


	public Integer getInsuredNumber() {
		return insuredNumber==null?0:insuredNumber;
	}

	public void setInsuredNumber(Integer insuredNumber) {
		this.insuredNumber = insuredNumber;
	}

	public Integer getInsuredProportionFraction() {
		return insuredProportionFraction==null?0:insuredProportionFraction;
	}

	public void setInsuredProportionFraction(Integer insuredProportionFraction) {
		this.insuredProportionFraction = insuredProportionFraction;
	}

	public Integer getHasBuyInsurance() {
		return hasBuyInsurance==null?0:hasBuyInsurance;
	}

	public void setHasBuyInsurance(Integer hasBuyInsurance) {
		this.hasBuyInsurance = hasBuyInsurance;
	}

	public Integer getHasBuyInsuranceFraction() {
		return hasBuyInsuranceFraction==null?0:hasBuyInsuranceFraction;
	}

	public void setHasBuyInsuranceFraction(Integer hasBuyInsuranceFraction) {
		this.hasBuyInsuranceFraction = hasBuyInsuranceFraction;
	}

	public Integer getRecreationalActivities() {
		return recreationalActivities==null?0:recreationalActivities;
	}

	public void setRecreationalActivities(Integer recreationalActivities) {
		this.recreationalActivities = recreationalActivities;
	}

	public Integer getRecreationalActivitiesFraction() {
		return recreationalActivitiesFraction==null?0:recreationalActivitiesFraction;
	}

	public void setRecreationalActivitiesFraction(
			Integer recreationalActivitiesFraction) {
		this.recreationalActivitiesFraction = recreationalActivitiesFraction;
	}

	public Double getSocialWorkCenterArea() {
		return socialWorkCenterArea==null?0:socialWorkCenterArea;
	}

	public void setSocialWorkCenterArea(Double socialWorkCenterArea) {
		this.socialWorkCenterArea = socialWorkCenterArea;
	}

	public Integer getSocialWorkCenterAreaFraction() {
		return socialWorkCenterAreaFraction==null?0:socialWorkCenterAreaFraction;
	}

	public void setSocialWorkCenterAreaFraction(Integer socialWorkCenterAreaFraction) {
		this.socialWorkCenterAreaFraction = socialWorkCenterAreaFraction;
	}

	public Double getSurveySatisfaction() {
		return surveySatisfaction==null?0:surveySatisfaction;
	}

	public void setSurveySatisfaction(Double surveySatisfaction) {
		this.surveySatisfaction = surveySatisfaction;
	}

	public Integer getSurveySatisfactionFraction() {
		return surveySatisfactionFraction==null?0:surveySatisfactionFraction;
	}

	public void setSurveySatisfactionFraction(Integer surveySatisfactionFraction) {
		this.surveySatisfactionFraction = surveySatisfactionFraction;
	}

	public Double getThreeSatisfaction() {
		return threeSatisfaction==null?0:threeSatisfaction;
	}

	public void setThreeSatisfaction(Double threeSatisfaction) {
		this.threeSatisfaction = threeSatisfaction;
	}

	public Integer getThreeSatisfactionFraction() {
		return threeSatisfactionFraction==null?0:threeSatisfactionFraction;
	}

	public void setThreeSatisfactionFraction(Integer threeSatisfactionFraction) {
		this.threeSatisfactionFraction = threeSatisfactionFraction;
	}

	public Integer getIsAllStanding() {
		return isAllStanding==null?0:isAllStanding;
	}

	public void setIsAllStanding(Integer isAllStanding) {
		this.isAllStanding = isAllStanding;
	}

	public Integer getIsAllStandingFraction() {
		return isAllStandingFraction==null?0:isAllStandingFraction;
	}

	public void setIsAllStandingFraction(Integer isAllStandingFraction) {
		this.isAllStandingFraction = isAllStandingFraction;
	}


	public Integer getVillageHardenedFraction() {
		return villageHardenedFraction==null?0:villageHardenedFraction;
	}

	public void setVillageHardenedFraction(Integer villageHardenedFraction) {
		this.villageHardenedFraction = villageHardenedFraction;
	}

	public Double getDrinkingWaterNum() {
		return drinkingWaterNum==null?0:drinkingWaterNum;
	}

	public void setDrinkingWaterNum(Double drinkingWaterNum) {
		this.drinkingWaterNum = drinkingWaterNum;
	}

	public Integer getDrinkingWaterNumFraction() {
		return drinkingWaterNumFraction==null?0:drinkingWaterNumFraction;
	}

	public void setDrinkingWaterNumFraction(Integer drinkingWaterNumFraction) {
		this.drinkingWaterNumFraction = drinkingWaterNumFraction;
	}

	public Integer getIsPowerGrid() {
		return isPowerGrid==null?0:isPowerGrid;
	}

	public void setIsPowerGrid(Integer isPowerGrid) {
		this.isPowerGrid = isPowerGrid;
	}
	
	public String getIsPowerGridStr(){
		if(getIsPowerGrid()==0){
			return "否";
		}else{
			return "是";
		}
	}

	public Integer getIsPowerGridFraction() {
		return isPowerGridFraction==null?0:isPowerGridFraction;
	}

	public void setIsPowerGridFraction(Integer isPowerGridFraction) {
		this.isPowerGridFraction = isPowerGridFraction;
	}

	public Integer getTreatmentPollution() {
		return treatmentPollution==null?0:treatmentPollution;
	}

	public void setTreatmentPollution(Integer treatmentPollution) {
		this.treatmentPollution = treatmentPollution;
	}

	public Integer getTreatmentPollutionFraction() {
		return treatmentPollutionFraction==null?0:treatmentPollutionFraction;
	}

	public void setTreatmentPollutionFraction(Integer treatmentPollutionFraction) {
		this.treatmentPollutionFraction = treatmentPollutionFraction;
	}

	public Integer getGarbageDisposal() {
		return garbageDisposal==null?0:garbageDisposal;
	}

	public void setGarbageDisposal(Integer garbageDisposal) {
		this.garbageDisposal = garbageDisposal;
	}

	public Integer getGarbageDisposalFraction() {
		return garbageDisposalFraction==null?0:garbageDisposalFraction;
	}

	public void setGarbageDisposalFraction(Integer garbageDisposalFraction) {
		this.garbageDisposalFraction = garbageDisposalFraction;
	}

	public Double getSanitarySewage() {
		return sanitarySewage==null?0:sanitarySewage;
	}

	public void setSanitarySewage(Double sanitarySewage) {
		this.sanitarySewage = sanitarySewage;
	}

	public Integer getSanitarySewageFraction() {
		return sanitarySewageFraction==null?0:sanitarySewageFraction;
	}

	public void setSanitarySewageFraction(Integer sanitarySewageFraction) {
		this.sanitarySewageFraction = sanitarySewageFraction;
	}

	public Integer getInvolveHouseCount() {
		return involveHouseCount==null?0:involveHouseCount;
	}

	public void setInvolveHouseCount(Integer involveHouseCount) {
		this.involveHouseCount = involveHouseCount;
	}

	public Integer getInvolveHouseCountFraction() {
		return involveHouseCountFraction==null?0:involveHouseCountFraction;
	}

	public void setInvolveHouseCountFraction(Integer involveHouseCountFraction) {
		this.involveHouseCountFraction = involveHouseCountFraction;
	}

	public Integer getNewVillageFraction() {
		return newVillageFraction==null?0:newVillageFraction;
	}

	public void setNewVillageFraction(Integer newVillageFraction) {
		this.newVillageFraction = newVillageFraction;
	}

//	public Integer getIsAdopt() {
//		return isAdopt==null?0:isAdopt;
//	}
//
//	public void setIsAdopt(Integer isAdopt) {
//		this.isAdopt = isAdopt;
//	}

	public Double getCultivatedLandArea() {
		return cultivatedLandArea;
	}

	public void setCultivatedLandArea(Double cultivatedLandArea) {
		this.cultivatedLandArea = cultivatedLandArea;
	}

	public Integer getTotalHouseholdsNum() {
		return totalHouseholdsNum;
	}

	public void setTotalHouseholdsNum(Integer totalHouseholdsNum) {
		this.totalHouseholdsNum = totalHouseholdsNum;
	}

	public String getCharacteristicPlantingStr() {
		return characteristicPlantingStr;
	}

	public void setCharacteristicPlantingStr(String characteristicPlantingStr) {
		this.characteristicPlantingStr = characteristicPlantingStr;
	}
	
	public Double getHouseholdIncome() {
		return householdIncome;
	}

	public void setHouseholdIncome(Double householdIncome) {
		this.householdIncome = householdIncome;
	}

	public String getProportionOfIncomeStr() {
		return proportionOfIncomeStr;
	}

	public void setProportionOfIncomeStr(String proportionOfIncomeStr) {
		this.proportionOfIncomeStr = proportionOfIncomeStr;
	}

	public Integer getInCompulsoryEducationCount() {
		return inCompulsoryEducationCount;
	}

	public void setInCompulsoryEducationCount(Integer inCompulsoryEducationCount) {
		this.inCompulsoryEducationCount = inCompulsoryEducationCount;
	}

	public String getCompulsoryEducationStr() {
		return compulsoryEducationStr;
	}

	public void setCompulsoryEducationStr(String compulsoryEducationStr) {
		this.compulsoryEducationStr = compulsoryEducationStr;
	}

	public Integer getTotalPeopleNum() {
		return totalPeopleNum;
	}

	public void setTotalPeopleNum(Integer totalPeopleNum) {
		this.totalPeopleNum = totalPeopleNum;
	}

	public String getInsuredProportionStr() {
		return insuredProportionStr;
	}

	public void setInsuredProportionStr(String insuredProportionStr) {
		this.insuredProportionStr = insuredProportionStr;
	}

	public String getHasBuyInsuranceStr() {
		if(hasBuyInsurance==0){
			return "否";
		}else{
			return "是";
		}
//		return hasBuyInsuranceStr;
	}

	public void setHasBuyInsuranceStr(String hasBuyInsuranceStr) {
		this.hasBuyInsuranceStr = hasBuyInsuranceStr;
	}

	public Integer getRecreationalActivitiesPeople() {
		return recreationalActivitiesPeople;
	}

	public void setRecreationalActivitiesPeople(Integer recreationalActivitiesPeople) {
		this.recreationalActivitiesPeople = recreationalActivitiesPeople;
	}


	public Double getVillageRoad() {
		return villageRoad;
	}

	public void setVillageRoad(Double villageRoad) {
		this.villageRoad = villageRoad;
	}

	public String getDrinkingWaterStr() {
		return drinkingWaterStr;
	}

	public void setDrinkingWaterStr(String drinkingWaterStr) {
		this.drinkingWaterStr = drinkingWaterStr;
	}

	public String getSanitarySewageStr() {
		return sanitarySewageStr;
	}

	public void setSanitarySewageStr(String sanitarySewageStr) {
		this.sanitarySewageStr = sanitarySewageStr;
	}

	public String getInvolveHouseStr() {
		return involveHouseStr;
	}

	public void setInvolveHouseStr(String involveHouseStr) {
		this.involveHouseStr = involveHouseStr;
	}

	public String getIsAdoptStr() {
		return isAdoptStr;
	}

	public void setIsAdoptStr(String isAdoptStr) {
		this.isAdoptStr = isAdoptStr;
	}

	public Double getVillageHardenedRoad() {
		return villageHardenedRoad==null?0:villageHardenedRoad;
	}

	public void setVillageHardenedRoad(Double villageHardenedRoad) {
		this.villageHardenedRoad = villageHardenedRoad;
	}

	public String getVillageHardenedRoadStr() {
		return villageHardenedRoadStr;
	}

	public void setVillageHardenedRoadStr(String villageHardenedRoadStr) {
		this.villageHardenedRoadStr = villageHardenedRoadStr;
	}

	public String getIsAllStandingStr() {
		if(getIsAllStanding()==0){
			return "否";
		}else{
			return "是";
		}
	}
	
	public String getHouseProblemCountStr() {
		if(getHouseProblemCount()==0){
			return "是";
		}else{
			return "否";
		}
	}

	public Integer getAllCompulsoryEducationCount(){
		if(compulsoryEducationCount==inCompulsoryEducationCount){
			return 1;
		}
		return 0;
	}
	
	public String getTreatmentPollutionStr(){
		if(getTreatmentPollution()==0){
			return "否";
		}else{
			return "是";
		}
	}
	
	public String getGarbageDisposalStr(){
		if(getGarbageDisposal()==0){
			return "否";
		}else{
			return "是";
		}
	}
	
	public String getSurveySatisfactionStr(){
		return getSurveySatisfaction()+"%";
	}
	
	public String getThreeSatisfactionStr(){
		return getThreeSatisfaction()+"%";
	}
	
	
	
}
