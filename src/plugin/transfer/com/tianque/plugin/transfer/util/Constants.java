package com.tianque.plugin.transfer.util;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	/** 实有人口 */
	public final static String HOUSEHOLDSTAFF_KEY = "householdStaff";
	public final static String FLOATINGPOPULATION_KEY = "floatingPopulation";
	public final static String UNSETTLEDPOPULATION_KEY = "unsettledPopulation";
	public final static String OVERSEAPERSONNEL_KEY = "overseaPersonnel";
	/** 业务人口 */
	// 重点人员
	public static final String POSITIVEINFO_KEY = "positiveInfo";
	public static final String RECTIFICATIVEPERSON_KEY = "rectificativePerson";
	public static final String MENTALPATIENT_KEY = "mentalPatient";
	public static final String DRUGGY_KEY = "druggy";
	public static final String AIDSPOPULATIONS_KEY = "aidspopulations";
	public static final String IDLEYOUTH_KEY = "idleYouth";
	public static final String SUPERIORVISIT_KEY = "superiorVisit";
	public static final String DANGEROUSGOODSPRACTITIONER_KEY = "dangerousGoodsPractitioner";
	public static final String OTHERATTENTIONPERSONNEL_KEY = "otherAttentionPersonnel";
	// 关怀对象
	public static final String ELDERLYPEOPLE_KEY = "elderlyPeople";
	public static final String HANDICAPPED_KEY = "handicapped";
	public static final String OPTIMALOBJECT_KEY = "optimalObject";
	public static final String AIDNEEDPOPULATION_KEY = "aidNeedPopulation";
	// 失业人员
	public static final String UNEMPLOYEDPEOPLE_KEY = "unemployedPeople";
	// 育龄妇女
	public static final String NURTURESWOMEN_KEY = "nurturesWomen";
	// FXJ
	public static final String FPERSONNEL_KEY = "fPersonnel";
	public static final String QPERSONNEL_KEY = "qPersonnel";
	public static final String MPERSONNEL_KEY = "mPersonnel";
	public static final String GOOD_SAMARITAN_KEY = "goodSamaritan";
	/** 组织场所 */
	// 实有单位
	public static final String ACTUALCOMPANY_KEY = "actualCompany";
	// 重点场所
	public static final String SAFETYPRODUCTIONKEY_KEY = "safetyProductionKey";
	public static final String FIRESAFETYKEY_KEY = "fireSafetyKey";
	public static final String SECURITYKEY_KEY = "securityKey";
	public static final String SCHOOL_KEY = "school";
	public static final String DANGEROUSCHEMICALSUNIT_KEY = "dangerousChemicalsUnit";
	public static final String INTERNETBAR_KEY = "internetBar";
	public static final String PUBLICPLACE_KEY = "publicPlace";
	public static final String OTHERLOCALE_KEY = "otherLocale";
	// 两新组织
	public static final String NEWSOCIETYORGANIZATIONS_KEY = "newSocietyOrganizations";
	public static final String NEWECONOMICORGANIZATIONS_KEY = "newEconomicOrganizations";
	// 企业
	public static final String ENTERPRISEKEY_KEY = "enterpriseKey";
	public static final String ENTERPRISEDOWNKEY_KEY = "enterpriseDownKey";
	/** 房屋 */
	public static final String ACTUALHOUSE_KEY = "actualHouse";// 实有
	public static final String RENTALHOUSE_KEY = "rentalHouse";// 出租
	/** 错误信息列表 */
	public static final String ERRORLIST = "errorList";
	/** 不进行转移操作的ID列表 */
	public static final String ERRORIDLIST = "errorIdList";

	private static Map<String, String> POPULATION_MAP = new HashMap<String, String>();
	static {
		POPULATION_MAP.put(HOUSEHOLDSTAFF_KEY, HOUSEHOLDSTAFF_KEY);
		POPULATION_MAP.put(FLOATINGPOPULATION_KEY, FLOATINGPOPULATION_KEY);
		POPULATION_MAP.put(UNSETTLEDPOPULATION_KEY, UNSETTLEDPOPULATION_KEY);
		POPULATION_MAP.put(OVERSEAPERSONNEL_KEY, OVERSEAPERSONNEL_KEY);
		POPULATION_MAP.put(POSITIVEINFO_KEY, POSITIVEINFO_KEY);
		POPULATION_MAP.put(RECTIFICATIVEPERSON_KEY, RECTIFICATIVEPERSON_KEY);
		POPULATION_MAP.put(MENTALPATIENT_KEY, MENTALPATIENT_KEY);
		POPULATION_MAP.put(DRUGGY_KEY, DRUGGY_KEY);
		POPULATION_MAP.put(AIDSPOPULATIONS_KEY, AIDSPOPULATIONS_KEY);
		POPULATION_MAP.put(IDLEYOUTH_KEY, IDLEYOUTH_KEY);
		POPULATION_MAP.put(SUPERIORVISIT_KEY, SUPERIORVISIT_KEY);
		POPULATION_MAP.put(DANGEROUSGOODSPRACTITIONER_KEY,
				DANGEROUSGOODSPRACTITIONER_KEY);
		POPULATION_MAP.put(OTHERATTENTIONPERSONNEL_KEY,
				OTHERATTENTIONPERSONNEL_KEY);
		POPULATION_MAP.put(ELDERLYPEOPLE_KEY, ELDERLYPEOPLE_KEY);
		POPULATION_MAP.put(HANDICAPPED_KEY, HANDICAPPED_KEY);
		POPULATION_MAP.put(OPTIMALOBJECT_KEY, OPTIMALOBJECT_KEY);
		POPULATION_MAP.put(AIDNEEDPOPULATION_KEY, AIDNEEDPOPULATION_KEY);
		POPULATION_MAP.put(UNEMPLOYEDPEOPLE_KEY, UNEMPLOYEDPEOPLE_KEY);
		POPULATION_MAP.put(NURTURESWOMEN_KEY, NURTURESWOMEN_KEY);
		POPULATION_MAP.put(FPERSONNEL_KEY, FPERSONNEL_KEY);
		POPULATION_MAP.put(QPERSONNEL_KEY, QPERSONNEL_KEY);
		POPULATION_MAP.put(MPERSONNEL_KEY, MPERSONNEL_KEY);
		POPULATION_MAP.put(GOOD_SAMARITAN_KEY, GOOD_SAMARITAN_KEY);
	}

	public static String getClassNameByType(String type) {
		return POPULATION_MAP.get(type);
	}
}
