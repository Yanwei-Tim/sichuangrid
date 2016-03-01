package com.tianque.plugin.dataManage.util;

import java.util.HashMap;
import java.util.Map;

public class BaseInfoActionDefine {
	private static Map<String, String> map = new HashMap<String, String>();
	static {
		map.put("saveAidNeedPopulationInfo",
				"/baseinfo/aidNeedPopulationManage/saveAidNeedPopulationInfo.action");
		map.put("maintainFloatingPopulationBaseInfo",
				"/baseinfo/floatingPopulationManage/maintainFloatingPopulationBaseInfo.action");
		map.put("maintainHouseholdStaffBaseInfo",
				"/baseinfo/householdStaff/maintainHouseholdStaffBaseInfo.action");
		map.put("mainElderPeopleBaseInfo",
				"/baseinfo/elderlyPeopleManage/mainElderPeopleBaseInfo.action");
		map.put("maintainDangerousGoodsPractitionerBaseInfo",
				"/baseinfo/dangerousGoodsPractitionerManage/maintainDangerousGoodsPractitionerBaseInfo.action");
		map.put("householdStaffBaseInfo", "/baseinfo/householdStaff/addPopulation.action");
		map.put("maintainRectificativePersonBaseInfo",
				"/baseinfo/rectificativePersonManage/maintainRectificativePersonBaseInfo.action");
		map.put("savePositiveInfoBaseInfo",
				"/baseinfo/positiveInfoManage/savePositiveInfoBaseInfo.action");
		map.put("maintainDruggyBaseInfo", "/baseinfo/druggyManage/maintainDruggyBaseInfo.action");
		map.put("maintainIdleYouthBaseInfo",
				"/baseinfo/idleYouthManage/maintainIdleYouthBaseInfo.action");
		map.put("maintainMentalPatientBaseInfo",
				"/baseinfo/mentalPatientManage/maintainMentalPatientBaseInfo.action");
		map.put("maintainNurturesWomenBaseInfo",
				"/baseinfo/nurturesWomenManage/maintainNurturesWomenBaseInfo.action");
		map.put("maintainOptimalObjectBaseInfo",
				"/baseinfo/optimalObjectManage/maintainOptimalObjectBaseInfo.action");
		map.put("maintainHandicappedBaseInfo",
				"/baseinfo/handicappedManage/maintainHandicappedBaseInfo.action");
		map.put("maintainPartyMemberBaseInfo",
				"/baseinfo/partyMemberInfoManage/maintainPartyMemberBaseInfo.action");
		map.put("mainUnemployedPeopleBaseInfo",
				"/baseinfo/unemployedPeopleManage/mainUnemployedPeopleBaseInfo.action");
		map.put("maintainSuperiorVisitBaseInfo",
				"/baseinfo/superiorVisitManage/maintainSuperiorVisitBaseInfo.action");
		map.put("saveServiceTeamMember", "/baseinfo/serviceTeamMember/saveServiceTeamMember.action");

		map.put("hasDuplicateAidNeedPopulation",
				"/baseinfo/aidNeedPopulationManage/hasDuplicateAidNeedPopulation.action");
		map.put("hasDuplicateOptimalObjectPopulation",
				"/baseinfo/optimalObjectManage/hasDuplicateOptimalObjectPopulation.action");
		map.put("hasDuplicateMentalPatientPopulation",
				"/baseinfo/mentalPatientManage/hasDuplicateMentalPatientPopulation.action");
		map.put("hasDuplicateFloatingPopulation",
				"/baseinfo/floatingPopulationManage/hasDuplicateFloatingPopulation.action");
		map.put("hasDuplicateHouseholdStaff",
				"/baseinfo/householdStaff/hasDuplicateHouseholdStaff.action");
		map.put("hasDuplicateElderlyPeople",
				"/baseinfo/elderlyPeopleManage/hasDuplicateElderlyPeople.action");
		map.put("hasDuplicateDangerousGoodsPractitioner",
				"/baseinfo/dangerousGoodsPractitionerManage/hasDuplicateDangerousGoodsPractitioner.action");
		map.put("hasDuplicatePositiveInfo",
				"/baseinfo/positiveInfoManage/hasDuplicatePositiveInfo.action");
		map.put("hasDuplicateHouseholdStaff", "/baseinfo/householdStaff/checkCardNoIsRepeat.action");
		map.put("hasDuplicateDruggyPopulation",
				"/baseinfo/druggyManage/hasDuplicateDruggyPopulation.action");
		map.put("hasDuplicateRectificativePersonPopulation",
				"/baseinfo/rectificativePersonManage/hasDuplicateRectificativePersonPopulation.action");
		map.put("hasDuplicateIdleYouth", "/baseinfo/idleYouthManage/hasDuplicateIdleYouth.action");
		map.put("hasDuplicateNurturesWomen",
				"/baseinfo/nurturesWomenManage/hasDuplicateNurturesWomen.action");
		map.put("hasDuplicateHandicapped",
				"/baseinfo/handicappedManage/hasDuplicateHandicapped.action");
		map.put("hasDuplicatePartyMemberInfoPopulation",
				"/baseinfo/partyMemberInfoManage/hasDuplicatePartyMemberInfoPopulation.action");
		map.put("hasDuplicateUnemployedPeople",
				"/baseinfo/unemployedPeopleManage/hasDuplicateUnemployedPeople.action");
		map.put("hasDuplicatePublicPlaceLocation",
				"/baseinfo/publicPlaceManage/hasDuplicatePublicPlaceLocation.action");
		map.put("hasDuplicateInternetBarLocation",
				"/baseinfo/internetBarManage/hasDuplicateInternetBarLocation.action");
		map.put("hasDuplicateSuperiorVisitPopulation",
				"/baseinfo/superiorVisitManage/hasDuplicateSuperiorVisitPopulation.action");
		map.put("hasDuplicateServiceTeamMember",
				"/baseinfo/serviceTeamMemberManage/hasDuplicateServiceTeamMember.action");

		// 数据管理类开始定义处
		map.put("hasDuplicateInternetBarTempLocation",
				"/plugin/dataManage/internetBarTempManage/hasDuplicateInternetBarTempLocation.action");
		// 数据管理验证是否有重复数据
		map.put("hasDuplicate", "/plugin/dataManage/dataManagePublicManage/hasDuplicate.action");

	}

	public static String getAction(String key) {
		return map.get(key);
	}
}
