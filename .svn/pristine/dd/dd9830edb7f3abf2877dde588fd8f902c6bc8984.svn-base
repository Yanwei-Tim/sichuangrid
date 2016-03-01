package com.tianque.newVillage.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.StringUtil;
import com.tianque.newVillage.constant.ComprehensiveInfoConstant;
import com.tianque.newVillage.dao.NewVillageAcceptanceDao;
import com.tianque.newVillage.domain.ScoringRules;
import com.tianque.newVillage.service.NewVillageAcceptanceService;
import com.tianque.newVillage.service.ScoringRulesService;
import com.tianque.newVillage.vo.NewVillageAssessmentVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("newVillageAcceptanceService")
public class NewVillageAcceptanceServiceImpl implements
		NewVillageAcceptanceService {
	@Autowired
	private CacheService cacheService;
	@Autowired
	private NewVillageAcceptanceDao newVillageAcceptanceDao;
	@Autowired
	private ScoringRulesService scoringRulesService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Override
	public List<NewVillageAssessmentVo> findNewVillageAssessmentVoList(
			Integer year) {
		String key = MemCacheConstant.getNewVillageAcceptanceCachKey(MemCacheConstant.NEW_VILLAGE_ACCEPTANCE_KEY, year);
		List<NewVillageAssessmentVo> currentList = null;
		if(!StringUtil.isStringAvaliable(key)){
			currentList = (List<NewVillageAssessmentVo>) cacheService.get(MemCacheConstant.NEW_VILLAGE_ACCEPTANCE_NAMESPACE, key);
			if(currentList!=null){
				return currentList;
			}
		}
		List<NewVillageAssessmentVo> cumulativeList = newVillageAcceptanceDao.findCumulativeNewVillageBasic(year);
		currentList = newVillageAcceptanceDao.findcurrentNewVillageBasic(year);
		if(currentList!=null && currentList.size()!=0 
				&& cumulativeList!=null && cumulativeList.size()!=0){
			for(NewVillageAssessmentVo currentVo:currentList){
				for(NewVillageAssessmentVo cumulativeVo:cumulativeList){
					if(currentVo.getOrganization().getId().equals(cumulativeVo.getOrganization().getId())){
						currentVo.setCharacteristicPlanting(cumulativeVo.getCharacteristicPlanting());
						currentVo.setCapitaHousingArea(cumulativeVo.getCapitaHousingArea());
						currentVo.setRecreationalActivitiesPeople(cumulativeVo.getRecreationalActivitiesPeople());
						currentVo.setSocialWorkCenterArea(cumulativeVo.getSocialWorkCenterArea());
						currentVo.setVillageRoad(cumulativeVo.getVillageRoad());
						currentVo.setVillageHardenedRoad(cumulativeVo.getVillageHardenedRoad());
						currentVo.setDrinkingWaterNum(cumulativeVo.getDrinkingWaterNum());
						currentVo.setInvolveHouseCount(cumulativeVo.getInvolveHouseCount());
						currentVo.setSanitarySewage(cumulativeVo.getSanitarySewage());
					}
				}
			}
		}
		if(currentList!=null && currentList.size()!=0){
			List<ScoringRules> rulesList = scoringRulesService.getScoringRulesListByValues();
			for(NewVillageAssessmentVo currentVo:currentList){
				currentVo.setOrganization(organizationDubboService.getSimpleOrgById(currentVo.getOrganization().getId()));
				//计算特色产业 完成度
				currentVo.setCharacteristicPlantingStr(getLingRate(currentVo.getCharacteristicPlanting(),currentVo.getCultivatedLandArea()));
				currentVo.setCharacteristicPlantingFraction(getScoreByRules(currentVo.getCharacteristicPlanting(), currentVo.getCultivatedLandArea(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.CHARACTERISTICPLANTING)));
				//农业主导产业收入占农村家庭经营收入的比重
				currentVo.setProportionOfIncomeStr(getLingRate(currentVo.getAgriculturalIncome(),currentVo.getHouseholdIncome()));
				currentVo.setProportionOfIncomeFraction(getScoreByRules(currentVo.getAgriculturalIncome(),currentVo.getHouseholdIncome(), 
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.PROPORTIONOFINCOME)));
				//人均可支配收入
				currentVo.setFarmerPerIncomeFraction(getScore(currentVo.getFarmerPerIncome(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.FARMERPERINCOME)));
				//人均安全住房面积
				currentVo.setCapitaHousingAreaFraction(getScore(currentVo.getCapitaHousingArea(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.CAPITAHOUSINGAREA)));
				//无房户、危房户、住房困难户住房问题全部解决
				currentVo.setHouseProblemFraction(getBooleanScore(currentVo.getHouseProblemCount(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.HOUSINGSOLUTION)));
				//九年义务教育目标人群全覆盖
				currentVo.setCompulsoryEducationStr(getLingRate(currentVo.getInCompulsoryEducationCount(), currentVo.getCompulsoryEducationCount()));
				currentVo.setCompulsoryEducationFraction(getScore(getLingRateValue(currentVo.getInCompulsoryEducationCount(), currentVo.getCompulsoryEducationCount()),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.EDUCATIONCOUNTFULLCOVERAGE)));
				//新农合参保率
				currentVo.setInsuredProportionStr(getLingRate(currentVo.getInsuredNumber(), currentVo.getTotalPeopleNum()));
				currentVo.setInsuredProportionFraction(getScoreByRules(currentVo.getInsuredNumber(), currentVo.getTotalPeopleNum(), 
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.INSUREDPROPORTION)));
				//新农保应保尽保
				currentVo.setHasBuyInsuranceFraction(getScore(currentVo.getHasBuyInsurance(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.HASBUYINSURANCE)));
				//每年组织群众性文体活动
				currentVo.setRecreationalActivities(getRecreational(currentVo.getRecreationalActivitiesPeople(),currentVo.getTotalPeopleNum()));
				currentVo.setRecreationalActivitiesFraction(getScore(getRecreational(currentVo.getRecreationalActivitiesPeople(),currentVo.getTotalPeopleNum()),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.ACTIVITIESIDENTIFICATION)));
				//公共活动服务中心设施面积
				currentVo.setSocialWorkCenterAreaFraction(getScore(currentVo.getSocialWorkCenterArea(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.SOCIALWORKCENTERAREA)));
				//基层党组织/党员调查满意度
				currentVo.setSurveySatisfactionFraction(getScore(currentVo.getSurveySatisfaction(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.SURVEYSATISFACTION)));
				//三务公开满意度
				currentVo.setThreeSatisfactionFraction(getScore(currentVo.getThreeSatisfaction(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.THREESATISFACTION)));
				//新村建设全覆盖
				currentVo.setIsAllStandingFraction(getScore(currentVo.getIsAllStanding(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.ISALLSTANDING)));
				//通村通组道路硬化率
				currentVo.setVillageHardenedRoadStr(getLingRate(currentVo.getVillageHardenedRoad(),currentVo.getVillageRoad()));
				currentVo.setVillageHardenedFraction(getScoreByRules(currentVo.getVillageHardenedRoad(),currentVo.getVillageRoad(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.ROADHARDENINGRATE)));
				//安全饮水全覆盖
				currentVo.setDrinkingWaterStr(getLingRate(currentVo.getDrinkingWaterNum(),currentVo.getTotalHouseholdsNum()));
				currentVo.setDrinkingWaterNumFraction(getScoreByRules(currentVo.getDrinkingWaterNum(),currentVo.getTotalHouseholdsNum(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.DRINKINGCOVERAGE)));
				//电网改造全覆盖
				currentVo.setIsPowerGridFraction(getScore(currentVo.getIsPowerGrid(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.ISPOWERGRID)));
				//面源污染有效治理
				currentVo.setTreatmentPollutionFraction(getScore(currentVo.getTreatmentPollution(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.TREATMENTPOLLUTION)));
				//生活垃圾处理全覆盖
				currentVo.setGarbageDisposalFraction(getScore(currentVo.getGarbageDisposal(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.GARBAGEDISPOSAL)));
				//生活污水处理覆盖率
				currentVo.setSanitarySewageStr(getLingRate(currentVo.getSanitarySewage(),currentVo.getTotalHouseholdsNum()));
				currentVo.setSanitarySewageFraction(getScoreByRules(currentVo.getSanitarySewage(),currentVo.getTotalHouseholdsNum(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.SEWAGETREATMENTCOVERAGE)));
				//农村院落整治全覆盖
				currentVo.setInvolveHouseStr(getLingRate(currentVo.getInvolveHouseCount(),currentVo.getTotalHouseholdsNum()));
				currentVo.setInvolveHouseCountFraction(getScoreByRules(currentVo.getInvolveHouseCount(),currentVo.getTotalHouseholdsNum(),
						getRulesByColumn(rulesList, ComprehensiveInfoConstant.COURTYARDRENOVATIONCOVERAGE)));
				
				//计算综合得分
				currentVo.setNewVillageFraction(currentVo.getCharacteristicPlantingFraction()+currentVo.getCapitaHousingAreaFraction()
						+currentVo.getRecreationalActivitiesFraction()+currentVo.getSocialWorkCenterAreaFraction()
						+currentVo.getVillageHardenedFraction()+currentVo.getDrinkingWaterNumFraction()+
						currentVo.getSanitarySewageFraction()+currentVo.getInvolveHouseCountFraction()
						+currentVo.getProportionOfIncomeFraction()+currentVo.getFarmerPerIncomeFraction()
						+currentVo.getIsAllStandingFraction()+currentVo.getHouseProblemFraction()
						+currentVo.getCompulsoryEducationFraction()+currentVo.getInsuredProportionFraction()+currentVo.getHasBuyInsuranceFraction()
						+currentVo.getSurveySatisfactionFraction()+currentVo.getThreeSatisfactionFraction()
						+currentVo.getIsPowerGridFraction()+currentVo.getTreatmentPollutionFraction()+currentVo.getGarbageDisposalFraction());
				//评判
				if(currentVo.getHouseProblemCount()==0 && currentVo.getIsAllStanding()==1 && currentVo.getNewVillageFraction()>=85){
					currentVo.setIsAdoptStr(ComprehensiveInfoConstant.NEW_VILLAGE_ISACCEPTANCE);
				}else{
					currentVo.setIsAdoptStr(ComprehensiveInfoConstant.NEW_VILLAGE_NOTACCEPTANCE);
				}
			}
		}
		if(!StringUtil.isStringAvaliable(key)){
			cacheService.set(MemCacheConstant.NEW_VILLAGE_ACCEPTANCE_NAMESPACE, key, ModulTypes.CACHETIME, currentList);
		}
		return currentList;
	}
	
	private int getRecreational(int recreationalActivities,int totalPeople){
		if(recreationalActivities==0 || totalPeople==0){
			return 0;
		}else{
			return (int) (recreationalActivities/(totalPeople*0.8));
		}
	}
	
	private int getScoreByRules(double molecule ,double denominator,List<ScoringRules> rulesList){
		Double value=getLingRateValue(molecule, denominator);
		return getScore(value, rulesList);
	}
	
	/**
	 * 是否的选项计算得分
	 * @param value
	 * @param rulesList
	 * @return
	 */
	private int getBooleanScore(Integer value,List<ScoringRules> rulesList){
		int score = 0;
		for(ScoringRules rules:rulesList){
			if(value==0 && rules.getCalculationSymbol()==2 && rules.getScoringRangeStart()==1){
				score = rules.getFraction();
			}
		}
		return score;
	}
	
	private int getScore(double value,List<ScoringRules> rulesList){
		int score = 0;
		for(ScoringRules rules:rulesList){
			switch(rules.getCalculationSymbol()){
			case 0:if(value>rules.getScoringRangeStart()){score = rules.getFraction();}  break;
			case 1:if(value<rules.getScoringRangeStart()){score = rules.getFraction();}  break;
			case 2:if(value==rules.getScoringRangeStart()){score = rules.getFraction();}  break;
			case 3:if(value!=rules.getScoringRangeStart()){score = rules.getFraction();}  break;
			case 4:if(value>=rules.getScoringRangeStart() && value<=rules.getScoringRangeEnd()){score = rules.getFraction();}  break;
			case 5:if(value>=rules.getScoringRangeStart()){score = rules.getFraction();}  break;
			case 6:if(value<=rules.getScoringRangeStart()){score = rules.getFraction();}  break;
			default: break;
			}
		}
		return score;
	}
	
	private List<ScoringRules> getRulesByColumn(List<ScoringRules> rulesList,String column){
		List<ScoringRules> columnRules = new ArrayList<ScoringRules>();
		for(ScoringRules rules:rulesList){
			if(column.equals(rules.getCorrespondingValue())){
				columnRules.add(rules);
			}
		}
		return columnRules;
	}
	
	private String getLingRate(double firstData, double secondData) {
		DecimalFormat df = new DecimalFormat("######0.00");

		String chain = "";
		if (firstData == 0 || secondData == 0) {
			chain = "0.00%";
			return chain;
		}
		double data = firstData / secondData * 100;
		chain = df.format(data) + "%";
		return chain;
	}
	
	private Double getLingRateValue(double firstData, double secondData) {
		DecimalFormat df = new DecimalFormat("######0.00");

		if (firstData == 0 || secondData == 0) {
			return 0d;
		}
		double value = firstData / secondData * 100;
		BigDecimal  b  =   new   BigDecimal(value); 
		return b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
