package com.tianque.service.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.publicSecurity.constant.PublicSecurityType;

public final class PopulationCatalog implements Serializable {
	public final static String ALL_POPULATION = "all_population";
	public final static String ALL_ACTUAL_POPULATION = "all_actual_population";
	public final static String ALL_ATTENTION_POPULATION = "all_attention_population";// 重点人员

	public final static String ALL_PRIMARYORGANIZATIONS = "all_primaryOrganizations";

	public final static String ALL_IMPORTANT_PLACE = "all_important_place";// 重点场所
	public final static String ALL_ENTERPRISE = "all_enterprise";// 企业（只包含规上规下企业）

	public final static String ALL_UNEMPLOYED_POPULATION = "all_unemployed_population";// 失业人员
	public final static String ALL_BIRTH_POPULATION = "all_birth_population";// 计生
	public final static String ALL_CIVIL_POPULATION = "all_civil_population";// 民政
	public final static String ALL_LOVINGCARE_POPULATION = "all_lovingCare_population";// 关怀人员
	public final static String LOCAL_HOUSE = "local_house";// 房屋
	public final static String ACTUAL_COMPANY = "actual_company";// 实有单位
	public final static String DOUBLE_NEW = "double_new";// 两新组织
	public final static String ALL_YOUTH_POPULATION = "all_youth_population";// 青少年
	// FXJ
	public final static String ALL_FXJ = "all_fxj";// fxj人员
	// 见义勇为
	public final static String ALL_GOODSAMARITAN = "all_goodSamaritan";// 见义勇为人员

	public final static String ALL_PUBLICSECURITY = "all_publicsecurity";// 公安部件

	// 设置户籍人员的分类字段
	public final static String HOUSEHOLDSTAFFS_TYPE = "residentstatus";
	// public final static String NEW_ECONOMIC="new_economic";//新经济组织
	// public final static String ACTUAL_POPULATION="actual_population";
	// private final static String SPECIAL_POPULATION="special_population";
	// private final static String IMPORTANT_POPULATION="impotant_population";
	// private final static String IMPORTANT_YOUTH="impotant_youth";
	// private final static String FAMILY_PLANNING="family_planning";
	// private final static String CIVIL_SERVICE="civil_service";

	private final static int ALL_TAG = -1;
	private final static int ALL_ACTUAL_POPULATION_TAG = -10;
	private final static int ALL_ATTENTION_POPULATION_TAG = -100;
	private final static int ALL_BIRTH_POPULATION_TAG = -1000;
	private final static int ALL_CIVIL_POPULATION_TAG = -10000;
	private final static int ALL_LOVINGCARE_POPULATION_TAG = -100000;
	private final static int ALL_UNEMPLOYED_POPULATION_TAG = -1000000;

	private final static int ALL_IMPORTANT_PLACE_TAG = -10000000;
	private final static int ALL_ENTERPRISE_TAG = -100000000;
	private final static int ALL_FXJ_TAG = -1000000000;
	private final static int ALL_GOODSAMARITAN_TAG = -1100000000;

	// private final static int ACTUAL_POPULATION_TAG=0;
	private final static int HOUSEHOULD_POPULATION_TAG = 1;
	private final static int FLOATING_POPULATION_TAG = 2;
	private final static int UNHOUSEHOULD_POPULATION_TAG = 3;
	private final static int OVERSEA_POPULATION_TAG = 4;

	// private final static int SPECIAL_POPULATION_TAG=100;
	private final static int RECTIFICATIVE_TAG = 101;
	private final static int POSITIVE_TAG = 102;
	private final static int MENTAL_TAG = 103;
	private final static int DRUGGY_TAG = 104;
	private final static int HANDICAPPED_TAG = 105;
	private final static int UNEMPLOYED_PEOPLE_TAG = 106;
	private final static int OPTIMAL_OBJECT_TAG = 107;
	private final static int AIDSPOPULATIONS_TAG = 108;

	// private final static int IMPORTANT_POPULATION_TAG=200;
	private final static int OVERSEA_PERSON_TAG = 201;
	private final static int SUPERIORVISIT_TAG = 202;
	private final static int DANGEROUS_GOODS_PRACTITIONER_TAG = 203;
	private final static int OTHER_ATTENTION_PERSONNEL_TAG = 204;

	// private final static int IMPORTANT_YOUTH_TAG=300;
	private final static int IDLE_YOUTH_TAG = 301;
	// private final static int FAMILY_PLANNING_TAG=400;
	private final static int BEARING_WOMEN_TAG = 401;

	// private final static int CIVIL_SERVICE_TAG=500;
	private final static int AID_NEED_POPULATION_TAG = 501;
	private final static int ELDERLY_PEOPLE_TAG = 502;

	private final static int DANGEROUSCHEMICALUNIT_TAG = 601;
	private final static int ALL_YOUTH_POPULATION_TAG = 611;

	private final static int ACTUAL_HOUSE_TAG = 701;
	private final static int LETTING_HOUSE_TAG = 702;
	private final static int BUILD_DATA_TAG = 703;

	private final static int ACTUAL_COMPANY_TAG = 801;

	private final static int NEW_ECONOMIC_TAG = 901;
	private final static int NEW_SOCIETY_TAG = 902;

	private final static int SCHOOL_TAG = 911;
	private final static int SAFETYPRODUCTIONKEY_TAG = 912;
	private final static int FIRESAFETYKEY_TAG = 913;
	private final static int SECURITYKEY_TAG = 914;
	private final static int OTHERLOCALE_TAG = 915;
	private final static int DANGEROUSCHEMICALSUNIT_TAG = 916;
	private final static int INTERNETBAR_TAG = 917;
	private final static int PUBLICPLACE_TAG = 918;
	private final static int HOSPITAL_TAG = 919;

	private final static int ENTERPRISEKEY_TAG = 921;
	private final static int ENTERPRISEDOWNKEY_TAG = 922;

	private final static int SKYNET_TAG = 1001;
	private final static int BAYONET_TAG = 1002;
	private final static int SNAPSHOTSYSTEM_TAG = 1003;

	private final static int PRIMARYORGANIZATION_TAG = 2004;// 组织机构
	private final static int DEPARTMENTPARTY_TAG = 2005;// 组织机构-党委部门

	// FXJ
	private final static int FPERSONNEL_TAG = 3001;// F
	private final static int QPERSONNEL_TAG = 3002;// Q
	private final static int MPERSONNEL_TAG = 3003;// M

	// 见义勇为
	private final static int GOODSAMARITAN_TAG = 4001;

	private final static Map<String, PopulationCatalog> allCatalogs = new HashMap<String, PopulationCatalog>();
	private final static Map<String, PopulationCatalog> unemployCatalogs = new HashMap<String, PopulationCatalog>();
	private final static Map<String, PopulationCatalog> civilCatalogs = new HashMap<String, PopulationCatalog>();
	private final static Map<String, PopulationCatalog> lovingCareCatalogs = new HashMap<String, PopulationCatalog>();
	private final static Map<String, PopulationCatalog> birthCatalogs = new HashMap<String, PopulationCatalog>();
	private final static Map<String, PopulationCatalog> attentionCatalogs = new HashMap<String, PopulationCatalog>();
	private final static Map<String, PopulationCatalog> actualCatalogs = new HashMap<String, PopulationCatalog>();
	private final static Map<String, PopulationCatalog> doubleNewCatalogs = new HashMap<String, PopulationCatalog>();
	private final static Map<String, PopulationCatalog> importantPlaceCatalogs = new HashMap<String, PopulationCatalog>();
	private final static Map<String, PopulationCatalog> enterpriseCatalogs = new HashMap<String, PopulationCatalog>();
	private final static Map<String, PopulationCatalog> youthCatalogs = new HashMap<String, PopulationCatalog>();
	private final static Map<String, PopulationCatalog> publicSecurityCatalogs = new HashMap<String, PopulationCatalog>();

	private final static Map<String, Map<String, PopulationCatalog>> keyCatalogs = new HashMap<String, Map<String, PopulationCatalog>>();
	static {
		doubleNewCatalogs.put(PopulationType.NEW_ECONOMIC,
				new PopulationCatalog(DOUBLE_NEW, PopulationType.NEW_ECONOMIC,
						NEW_ECONOMIC_TAG, new StatisticListSetting(
								"neweconomicorganizations",
								PropertyTypes.NEWECONOMICORGANIZATIONS_STYLE,
								"style", "name")));
		doubleNewCatalogs.put(PopulationType.NEW_SOCIETY,
				new PopulationCatalog(DOUBLE_NEW, PopulationType.NEW_SOCIETY,
						NEW_SOCIETY_TAG, new StatisticListSetting(
								"newsocietyorganizations",
								PropertyTypes.SOCIETY_GROUP, "type", "name")));

		allCatalogs.put(ALL_POPULATION, new PopulationCatalog(ALL_POPULATION,
				ALL_POPULATION, ALL_TAG));
		allCatalogs.put(ALL_ACTUAL_POPULATION, new PopulationCatalog(
				ALL_POPULATION, ALL_ACTUAL_POPULATION,
				ALL_ACTUAL_POPULATION_TAG));
		allCatalogs.put(ALL_ATTENTION_POPULATION, new PopulationCatalog(
				ALL_POPULATION, ALL_ATTENTION_POPULATION,
				ALL_ATTENTION_POPULATION_TAG));
		// FXJ
		allCatalogs.put(ALL_FXJ, new PopulationCatalog(ALL_POPULATION, ALL_FXJ,
				ALL_FXJ_TAG));

		allCatalogs.put(ALL_GOODSAMARITAN, new PopulationCatalog(
				ALL_POPULATION, ALL_GOODSAMARITAN, ALL_GOODSAMARITAN_TAG));

		allCatalogs.put(ALL_IMPORTANT_PLACE, new PopulationCatalog(
				ALL_POPULATION, ALL_IMPORTANT_PLACE, ALL_IMPORTANT_PLACE_TAG));

		allCatalogs.put(ALL_ENTERPRISE, new PopulationCatalog(ALL_POPULATION,
				ALL_ENTERPRISE, ALL_ENTERPRISE_TAG));

		allCatalogs.put(ALL_UNEMPLOYED_POPULATION, new PopulationCatalog(
				ALL_POPULATION, ALL_UNEMPLOYED_POPULATION,
				ALL_UNEMPLOYED_POPULATION_TAG));
		allCatalogs
				.put(ALL_BIRTH_POPULATION, new PopulationCatalog(
						ALL_POPULATION, ALL_BIRTH_POPULATION,
						ALL_BIRTH_POPULATION_TAG));
		allCatalogs
				.put(ALL_CIVIL_POPULATION, new PopulationCatalog(
						ALL_POPULATION, ALL_CIVIL_POPULATION,
						ALL_CIVIL_POPULATION_TAG));
		allCatalogs.put(ALL_LOVINGCARE_POPULATION, new PopulationCatalog(
				ALL_POPULATION, ALL_LOVINGCARE_POPULATION,
				ALL_LOVINGCARE_POPULATION_TAG));
		allCatalogs
				.put(ALL_YOUTH_POPULATION, new PopulationCatalog(
						ALL_POPULATION, ALL_YOUTH_POPULATION,
						ALL_YOUTH_POPULATION_TAG, new StatisticListSetting(
								null, PropertyTypes.YOUTH_AGES, null)));

		// 设置户籍人员的分类属性
		actualCatalogs.put(PopulationType.HOUSEHOLD_STAFF,
				new PopulationCatalog(ALL_ACTUAL_POPULATION,
						PopulationType.HOUSEHOLD_STAFF,
						HOUSEHOULD_POPULATION_TAG, new StatisticListSetting(
								"householdStaffs",
								BaseInfoTables.HOUSEHOLDSTAFF_KEY,
								PropertyTypes.RESIDENT_STATUS,
								HOUSEHOLDSTAFFS_TYPE)));

		actualCatalogs.put(PopulationType.PRIMARYORGANIZATIONS,
				new PopulationCatalog(ALL_PRIMARYORGANIZATIONS,
						PopulationType.PRIMARYORGANIZATIONS,
						PRIMARYORGANIZATION_TAG, new StatisticListSetting(
								"primaryOrganizations",
								BaseInfoTables.PRIMARYORGANIZATIONS,
								"teamClass",
								PopulationType.PRIMARYORGANIZATIONS)));

		actualCatalogs.put(PopulationType.DEPARTMENTPARTY,
				new PopulationCatalog(ALL_PRIMARYORGANIZATIONS,
						PopulationType.DEPARTMENTPARTY, DEPARTMENTPARTY_TAG,
						new StatisticListSetting("primaryOrganizations",
								BaseInfoTables.DEPARTMENTPARTY, "teamtype",
								PopulationType.DEPARTMENTPARTY)));

		actualCatalogs.put(PopulationType.FLOATING_POPULATION,
				new PopulationCatalog(ALL_ACTUAL_POPULATION,
						PopulationType.FLOATING_POPULATION,
						FLOATING_POPULATION_TAG, new StatisticListSetting(
								"floatingPopulations")));
		actualCatalogs.put(PopulationType.UNSETTLED_POPULATION,
				new PopulationCatalog(ALL_ACTUAL_POPULATION,
						PopulationType.UNSETTLED_POPULATION,
						UNHOUSEHOULD_POPULATION_TAG, new StatisticListSetting(
								"unsettledPopulations")));
		actualCatalogs.put(PopulationType.OVERSEA_STAFF, new PopulationCatalog(
				ALL_ACTUAL_POPULATION, PopulationType.OVERSEA_STAFF,
				OVERSEA_PERSON_TAG,
				new StatisticListSetting("overseaPersonnel")));

		civilCatalogs.put(PopulationType.AID_NEED_POPULATION,
				new PopulationCatalog(ALL_CIVIL_POPULATION,
						PopulationType.AID_NEED_POPULATION,
						AID_NEED_POPULATION_TAG, new StatisticListSetting(
								"aidneedpopulation", PropertyTypes.AIDRREASON,
								"aidReason")));
		civilCatalogs.put(PopulationType.ELDERLY_PEOPLE, new PopulationCatalog(
				ALL_CIVIL_POPULATION, PopulationType.ELDERLY_PEOPLE,
				ELDERLY_PEOPLE_TAG, new StatisticListSetting("elderlypeople")));
		civilCatalogs.put(PopulationType.HANDICAPPED, new PopulationCatalog(
				ALL_CIVIL_POPULATION, PopulationType.HANDICAPPED,
				HANDICAPPED_TAG, new StatisticListSetting("handicappeds",
						PropertyTypes.DISABILITY_TYPE, "disabilityType")));
		civilCatalogs
				.put(PopulationType.OPTIMAL_OBJECT, new PopulationCatalog(
						ALL_CIVIL_POPULATION, PopulationType.OPTIMAL_OBJECT,
						OPTIMAL_OBJECT_TAG, new StatisticListSetting(
								"optimalObjects")));
		unemployCatalogs.put(PopulationType.UNEMPLOYED_PEOPLE,
				new PopulationCatalog(ALL_UNEMPLOYED_POPULATION,
						PopulationType.UNEMPLOYED_PEOPLE,
						UNEMPLOYED_PEOPLE_TAG, new StatisticListSetting(
								"unemployedpeople")));

		birthCatalogs.put(PopulationType.NURTURES_WOMEN, new PopulationCatalog(
				ALL_BIRTH_POPULATION, PopulationType.NURTURES_WOMEN,
				BEARING_WOMEN_TAG, new StatisticListSetting("nurtureswomen")));

		lovingCareCatalogs.put(PopulationType.AID_NEED_POPULATION,
				new PopulationCatalog(ALL_LOVINGCARE_POPULATION,
						PopulationType.AID_NEED_POPULATION,
						AID_NEED_POPULATION_TAG, new StatisticListSetting(
								"aidneedpopulation", PropertyTypes.AIDRREASON,
								"aidReason")));
		lovingCareCatalogs.put(PopulationType.ELDERLY_PEOPLE,
				new PopulationCatalog(ALL_LOVINGCARE_POPULATION,
						PopulationType.ELDERLY_PEOPLE, ELDERLY_PEOPLE_TAG,
						new StatisticListSetting("elderlypeople")));
		lovingCareCatalogs
				.put(PopulationType.HANDICAPPED, new PopulationCatalog(
						ALL_LOVINGCARE_POPULATION, PopulationType.HANDICAPPED,
						HANDICAPPED_TAG, new StatisticListSetting(
								"handicappeds", PropertyTypes.DISABILITY_TYPE,
								"disabilityType")));
		lovingCareCatalogs.put(PopulationType.OPTIMAL_OBJECT,
				new PopulationCatalog(ALL_LOVINGCARE_POPULATION,
						PopulationType.OPTIMAL_OBJECT, OPTIMAL_OBJECT_TAG,
						new StatisticListSetting("optimalObjects")));
		attentionCatalogs.put(PopulationType.POSITIVE_INFO,
				new PopulationCatalog(ALL_ATTENTION_POPULATION,
						PopulationType.POSITIVE_INFO, POSITIVE_TAG,
						new StatisticListSetting("positiveinfos",
								PropertyTypes.POSITIVEINFO, "positiveinfotype",
								null,
								new String[] { "keyName", "expectValue" },
								new Object[] { "iscrime", 1 })));
		attentionCatalogs.put(PopulationType.RECTIFICATIVE_POPULATION,
				new PopulationCatalog(ALL_ATTENTION_POPULATION,
						PopulationType.RECTIFICATIVE_POPULATION,
						RECTIFICATIVE_TAG, new StatisticListSetting(
								"rectificativepersons",
								PropertyTypes.EXECUTE_TYPE, "executetype")));
		attentionCatalogs.put(PopulationType.MENTAL_PATIENT,
				new PopulationCatalog(ALL_ATTENTION_POPULATION,
						PopulationType.MENTAL_PATIENT, MENTAL_TAG,
						new StatisticListSetting("mentalpatients",
								PropertyTypes.MENTALPATIENT_DANGEROUS_LEVEL,
								"dangerLevel")));
		attentionCatalogs.put(PopulationType.DRUGGY, new PopulationCatalog(
				ALL_ATTENTION_POPULATION, PopulationType.DRUGGY, DRUGGY_TAG,
				new StatisticListSetting("druggys",
						PropertyTypes.DETOXICATE_CASE, "detoxicateCase")));
		attentionCatalogs.put(PopulationType.AIDSPOPULATIONS,
				new PopulationCatalog(ALL_ATTENTION_POPULATION,
						PopulationType.AIDSPOPULATIONS, AIDSPOPULATIONS_TAG,
						new StatisticListSetting("aidspopulations")));
		attentionCatalogs.put(PopulationType.AIDSPOPULATIONS + "s",
				new PopulationCatalog(ALL_ATTENTION_POPULATION,
						PopulationType.AIDSPOPULATIONS, AIDSPOPULATIONS_TAG,
						new StatisticListSetting("aidspopulations")));

		attentionCatalogs.put(PopulationType.IDLE_YOUTH, new PopulationCatalog(
				ALL_ATTENTION_POPULATION, PopulationType.IDLE_YOUTH,
				IDLE_YOUTH_TAG, new StatisticListSetting("idleyouths",
						PropertyTypes.IDLE_YOUTH_AGE_GROUP, null)));
		attentionCatalogs.put(PopulationType.SUPERIOR_VISIT,
				new PopulationCatalog(ALL_ATTENTION_POPULATION,
						PopulationType.SUPERIOR_VISIT, SUPERIORVISIT_TAG,
						new StatisticListSetting("superiorVisits")));
		attentionCatalogs.put(PopulationType.DANGEROUS_GOODS_PRACTITIONER,
				new PopulationCatalog(ALL_ATTENTION_POPULATION,
						PopulationType.DANGEROUS_GOODS_PRACTITIONER,
						DANGEROUS_GOODS_PRACTITIONER_TAG,
						new StatisticListSetting("dangerousGoodsPractitioners",
								PropertyTypes.DANGEROUS_WORKING_TYPE,
								"dangerousWorkingType")));

		// fateson add other_attention_personnel
		attentionCatalogs.put(PopulationType.OTHER_ATTENTION_PERSONNEL,
				new PopulationCatalog(ALL_ATTENTION_POPULATION,
						PopulationType.OTHER_ATTENTION_PERSONNEL,
						OTHER_ATTENTION_PERSONNEL_TAG,
						new StatisticListSetting("otherAttentionPersonnels")));
		// FXJ
		attentionCatalogs
				.put(PopulationType.F_PERSONNEL, new PopulationCatalog(ALL_FXJ,
						PopulationType.F_PERSONNEL, FPERSONNEL_TAG,
						new StatisticListSetting("fPersonnels")));
		attentionCatalogs
				.put(PopulationType.Q_PERSONNEL, new PopulationCatalog(ALL_FXJ,
						PopulationType.Q_PERSONNEL, QPERSONNEL_TAG,
						new StatisticListSetting("qPersonnels")));
		attentionCatalogs
				.put(PopulationType.M_PERSONNEL, new PopulationCatalog(ALL_FXJ,
						PopulationType.M_PERSONNEL, MPERSONNEL_TAG,
						new StatisticListSetting("mPersonnels")));

		// 见义勇为
		attentionCatalogs.put(PopulationType.GOOD_SAMARITAN,
				new PopulationCatalog(ALL_GOODSAMARITAN,
						PopulationType.GOOD_SAMARITAN, GOODSAMARITAN_TAG,
						new StatisticListSetting("goodSamaritan")));

		importantPlaceCatalogs.put(PopulationType.SCHOOL,
				new PopulationCatalog(ALL_IMPORTANT_PLACE,
						PopulationType.SCHOOL, SCHOOL_TAG,
						new StatisticListSetting("schools", "chineseName")));
		importantPlaceCatalogs.put(PopulationType.HOSPITAL,
				new PopulationCatalog(ALL_IMPORTANT_PLACE,
						PopulationType.HOSPITAL, HOSPITAL_TAG,
						new StatisticListSetting("hospitals", "hospitalName")));
		importantPlaceCatalogs.put(PopulationType.SAFETYPRODUCTIONKEY,
				new PopulationCatalog(ALL_IMPORTANT_PLACE,
						PopulationType.SAFETYPRODUCTIONKEY,
						SAFETYPRODUCTIONKEY_TAG, new StatisticListSetting(
								"enterprises", "name")));
		importantPlaceCatalogs.put(PopulationType.FIRESAFETYKEY,
				new PopulationCatalog(ALL_IMPORTANT_PLACE,
						PopulationType.FIRESAFETYKEY, FIRESAFETYKEY_TAG,
						new StatisticListSetting("enterprises", "name")));
		importantPlaceCatalogs.put(PopulationType.SECURITYKEY,
				new PopulationCatalog(ALL_IMPORTANT_PLACE,
						PopulationType.SECURITYKEY, SECURITYKEY_TAG,
						new StatisticListSetting("enterprises", "name")));
		importantPlaceCatalogs.put(PopulationType.OTHERLOCALE,
				new PopulationCatalog(ALL_IMPORTANT_PLACE,
						PopulationType.OTHERLOCALE, OTHERLOCALE_TAG,
						new StatisticListSetting("otherLocales", "name")));
		importantPlaceCatalogs.put(PopulationType.DANGEROUSCHEMICALSUNIT,
				new PopulationCatalog(ALL_IMPORTANT_PLACE,
						PopulationType.DANGEROUSCHEMICALSUNIT,
						DANGEROUSCHEMICALSUNIT_TAG, new StatisticListSetting(
								"dangerousChemicalsUnit", "unitName")));
		importantPlaceCatalogs.put(PopulationType.INTERNETBAR,
				new PopulationCatalog(ALL_IMPORTANT_PLACE,
						PopulationType.INTERNETBAR, INTERNETBAR_TAG,
						new StatisticListSetting("internetBar", "placeName")));
		importantPlaceCatalogs.put(PopulationType.PUBLICPLACE,
				new PopulationCatalog(ALL_IMPORTANT_PLACE,
						PopulationType.PUBLICPLACE, PUBLICPLACE_TAG,
						new StatisticListSetting("publicPlace", "placeName")));

		enterpriseCatalogs.put(PopulationType.ENTERPRISEDOWNKEY,
				new PopulationCatalog(ALL_ENTERPRISE,
						PopulationType.ENTERPRISEDOWNKEY,
						ENTERPRISEDOWNKEY_TAG, new StatisticListSetting(
								"enterprises", "name")));

		enterpriseCatalogs.put(PopulationType.ENTERPRISEKEY,
				new PopulationCatalog(ALL_ENTERPRISE,
						PopulationType.ENTERPRISEKEY, ENTERPRISEKEY_TAG,
						new StatisticListSetting("enterprises", "name")));

		doubleNewCatalogs.put(PopulationType.NEWSOCIETYORGANIZATIONS,
				new PopulationCatalog(DOUBLE_NEW,
						PopulationType.NEWSOCIETYORGANIZATIONS,
						NEW_ECONOMIC_TAG, new StatisticListSetting(
								"newsocietyorganizations", "name")));

		doubleNewCatalogs.put(PopulationType.NEWECONOMICORGANIZATIONS,
				new PopulationCatalog(DOUBLE_NEW,
						PopulationType.NEWECONOMICORGANIZATIONS,
						NEW_ECONOMIC_TAG, new StatisticListSetting(
								"neweconomicorganizations", "name")));

		allCatalogs.put(BaseInfoTables.BUILDDATA_KEY, new PopulationCatalog(
				LOCAL_HOUSE, BaseInfoTables.BUILDDATA_KEY, BUILD_DATA_TAG,
				new StatisticListSetting("builddatas")));

		attentionCatalogs.put(PopulationType.AIDSPOPULATIONS,
				new PopulationCatalog(ALL_ATTENTION_POPULATION,
						PopulationType.AIDSPOPULATIONS, AIDSPOPULATIONS_TAG,
						new StatisticListSetting("aidspopulations",
								PropertyTypes.CRIMETYPE, "crimeType")));// 艾滋病人员

		youthCatalogs.put(PopulationType.HOUSEHOLD_STAFF,
				new PopulationCatalog(ALL_ACTUAL_POPULATION,
						PopulationType.HOUSEHOLD_STAFF,
						HOUSEHOULD_POPULATION_TAG, new StatisticListSetting(
								"householdStaffs")));
		youthCatalogs.put(PopulationType.FLOATING_POPULATION,
				new PopulationCatalog(ALL_ACTUAL_POPULATION,
						PopulationType.FLOATING_POPULATION,
						FLOATING_POPULATION_TAG, new StatisticListSetting(
								"floatingPopulations")));
		youthCatalogs.put(PopulationType.UNSETTLED_POPULATION,
				new PopulationCatalog(ALL_ACTUAL_POPULATION,
						PopulationType.UNSETTLED_POPULATION,
						UNHOUSEHOULD_POPULATION_TAG, new StatisticListSetting(
								"unsettledPopulations")));

		publicSecurityCatalogs.put(PublicSecurityType.SKYNET,
				new PopulationCatalog(ALL_PUBLICSECURITY,
						PublicSecurityType.SKYNET, SKYNET_TAG,
						new StatisticListSetting("skynet")));

		publicSecurityCatalogs.put(PublicSecurityType.BAYONET,
				new PopulationCatalog(ALL_PUBLICSECURITY,
						PublicSecurityType.BAYONET, BAYONET_TAG,
						new StatisticListSetting("bayonet")));

		publicSecurityCatalogs.put(PublicSecurityType.SNAPSHOTSYSTEM,
				new PopulationCatalog(ALL_PUBLICSECURITY,
						PublicSecurityType.SNAPSHOTSYSTEM, SNAPSHOTSYSTEM_TAG,
						new StatisticListSetting("snapshotSystem")));

		allCatalogs.putAll(actualCatalogs);
		allCatalogs.putAll(civilCatalogs);
		allCatalogs.putAll(birthCatalogs);
		allCatalogs.putAll(unemployCatalogs);
		allCatalogs.putAll(attentionCatalogs);
		allCatalogs.putAll(lovingCareCatalogs);
		allCatalogs.putAll(doubleNewCatalogs);
		allCatalogs.putAll(importantPlaceCatalogs);
		allCatalogs.putAll(enterpriseCatalogs);
		allCatalogs.putAll(publicSecurityCatalogs);
		// allCatalogs.put(, new
		// PopulationCatalog(ALL_POPULATION,PopulationType.DANGEROUS_GOODS_PRACTITIONER,DANGEROUS_GOODS_PRACTITIONER_TAG));

		allCatalogs.put(PopulationType.ACTUAL_HOUSE, new PopulationCatalog(
				LOCAL_HOUSE, PopulationType.ACTUAL_HOUSE, ACTUAL_HOUSE_TAG,
				new StatisticListSetting("houseinfo", PropertyTypes.HOUSE_TYPE,
						new String[] { "keyName", "expectValue" },
						new Object[] { "isrentalhouse", 1 })));
		allCatalogs.put(PopulationType.RENTAL_HOUSE,
				new PopulationCatalog(LOCAL_HOUSE, PopulationType.RENTAL_HOUSE,
						LETTING_HOUSE_TAG, new StatisticListSetting(
								"rentalHouse",
								PropertyTypes.HIDDEN_TROUBLE_LEVEL,
								"hiddenDangerLevel")));
		allCatalogs.put(PopulationType.ACTUAL_COMPANY, new PopulationCatalog(
				ACTUAL_COMPANY, PopulationType.ACTUAL_COMPANY,
				ACTUAL_COMPANY_TAG, new StatisticListSetting("actualCompany",
						"companyName")));
		// allCatalogs.put(PopulationType.NEW_ECONOMIC, new
		// PopulationCatalog(DOUBLE_NEW,PopulationType.NEW_ECONOMIC,NEW_ECONOMIC_TAG,new
		// StatisticListSetting("neweconomicorganizations",
		// PropertyTypes.NEWECONOMICORGANIZATIONS_STYLE, "style")));
		allCatalogs.putAll(actualCatalogs);
		keyCatalogs.put(ALL_CIVIL_POPULATION, civilCatalogs);
		keyCatalogs.put(ALL_BIRTH_POPULATION, birthCatalogs);
		keyCatalogs.put(ALL_UNEMPLOYED_POPULATION, unemployCatalogs);
		keyCatalogs.put(ALL_ATTENTION_POPULATION, attentionCatalogs);
		keyCatalogs.put(ALL_LOVINGCARE_POPULATION, lovingCareCatalogs);
		keyCatalogs.put(ALL_IMPORTANT_PLACE, importantPlaceCatalogs);
		keyCatalogs.put(ALL_ENTERPRISE, enterpriseCatalogs);
		keyCatalogs.put(ALL_PUBLICSECURITY, publicSecurityCatalogs);

	}

	public final static PopulationCatalog POPULATION = allCatalogs
			.get(ALL_POPULATION);
	public final static PopulationCatalog ACTUAL_POPULATION = allCatalogs
			.get(ALL_ACTUAL_POPULATION);
	public final static PopulationCatalog ATTENTION_POPULATION = allCatalogs
			.get(ALL_ATTENTION_POPULATION);
	public final static PopulationCatalog CIVIL_POPULATION = allCatalogs
			.get(ALL_CIVIL_POPULATION);
	public final static PopulationCatalog BIRTH_POPULATION = allCatalogs
			.get(ALL_BIRTH_POPULATION);
	public final static PopulationCatalog UNEMPLOYED_POPULATION = allCatalogs
			.get(ALL_UNEMPLOYED_POPULATION);
	public final static PopulationCatalog LOVINGCARE_POPULATION = allCatalogs
			.get(ALL_LOVINGCARE_POPULATION);
	public final static PopulationCatalog IMPORTANT_PLACE = allCatalogs
			.get(ALL_IMPORTANT_PLACE);
	public final static PopulationCatalog ENTERPRISE = allCatalogs
			.get(ALL_ENTERPRISE);

	public final static PopulationCatalog HOUSEHOULD_POPULATION = allCatalogs
			.get(PopulationType.HOUSEHOLD_STAFF);
	public final static PopulationCatalog FLOATING_POPULATION = allCatalogs
			.get(PopulationType.FLOATING_POPULATION);
	public final static PopulationCatalog UNHOUSEHOULD_POPULATION = allCatalogs
			.get(PopulationType.UNSETTLED_POPULATION);
	public final static PopulationCatalog OVERSEA_POPULATION = allCatalogs
			.get(PopulationType.OVERSEA_STAFF);

	public final static PopulationCatalog RECTIFICATIVE = allCatalogs
			.get(PopulationType.RECTIFICATIVE_POPULATION);
	public final static PopulationCatalog POSITIVE = allCatalogs
			.get(PopulationType.POSITIVE_INFO);
	public final static PopulationCatalog MENTAL = allCatalogs
			.get(PopulationType.MENTAL_PATIENT);
	public final static PopulationCatalog DRUGGY = allCatalogs
			.get(PopulationType.DRUGGY);

	public final static PopulationCatalog SCHOOL = allCatalogs
			.get(PopulationType.SCHOOL);
	public final static PopulationCatalog SAFETYPRODUCTIONKEY = allCatalogs
			.get(PopulationType.SAFETYPRODUCTIONKEY);
	public final static PopulationCatalog FIRESAFETYKEY = allCatalogs
			.get(PopulationType.FIRESAFETYKEY);
	public final static PopulationCatalog SECURITYKEY = allCatalogs
			.get(PopulationType.SECURITYKEY);
	public final static PopulationCatalog OTHERLOCALE = allCatalogs
			.get(PopulationType.OTHERLOCALE);
	public final static PopulationCatalog OVERSEA_PERSON = allCatalogs
			.get(PopulationType.OVERSEA_STAFF);
	public final static PopulationCatalog SUPERIORVISIT = allCatalogs
			.get(PopulationType.SUPERIOR_VISIT);
	public final static PopulationCatalog DANGEROUS_GOODS_PRACTITIONER = allCatalogs
			.get(PopulationType.DANGEROUS_GOODS_PRACTITIONER);

	public final static PopulationCatalog IDLE_YOUTH = allCatalogs
			.get(PopulationType.IDLE_YOUTH);

	public final static PopulationCatalog OTHER_ATTENTION_PERSONNEL = allCatalogs
			.get(PopulationType.OTHER_ATTENTION_PERSONNEL);

	public final static PopulationCatalog NURTURES_WOMEN = allCatalogs
			.get(PopulationType.NURTURES_WOMEN);

	public final static PopulationCatalog AID_NEED_POPULATION = allCatalogs
			.get(PopulationType.AID_NEED_POPULATION);
	public final static PopulationCatalog UNEMPLOYED_PEOPLE = allCatalogs
			.get(PopulationType.UNEMPLOYED_PEOPLE);
	public final static PopulationCatalog OPTIMAL_OBJECT = allCatalogs
			.get(PopulationType.OPTIMAL_OBJECT);
	public final static PopulationCatalog HANDICAPPED = allCatalogs
			.get(PopulationType.HANDICAPPED);
	public final static PopulationCatalog ELDERLY_PEOPLE = allCatalogs
			.get(PopulationType.ELDERLY_PEOPLE);

	public final static PopulationCatalog NEW_ECONOMIC = doubleNewCatalogs
			.get(PopulationType.NEW_ECONOMIC);
	public final static PopulationCatalog NEW_SOCIETY = doubleNewCatalogs
			.get(PopulationType.NEW_SOCIETY);

	public final static PopulationCatalog AIDS_POPULATIONS = allCatalogs
			.get(PopulationType.AIDSPOPULATIONS);

	public final static PopulationCatalog PUBLICSECURITY = allCatalogs
			.get(ALL_PUBLICSECURITY);

	// FXJ
	public final static PopulationCatalog F_PERSONNEL = allCatalogs
			.get(PopulationType.F_PERSONNEL);
	public final static PopulationCatalog Q_PERSONNEL = allCatalogs
			.get(PopulationType.Q_PERSONNEL);
	public final static PopulationCatalog M_PERSONNEL = allCatalogs
			.get(PopulationType.M_PERSONNEL);
	// 见义勇为
	public final static PopulationCatalog GOOD_SAMARITAN = allCatalogs
			.get(PopulationType.GOOD_SAMARITAN);

	public static PopulationCatalog parse(String type) {
		PopulationCatalog populationCatalog = allCatalogs.get(type);
		if (populationCatalog == null && type != null) {
			populationCatalog = allCatalogs.get(type.toUpperCase());
		}
		return populationCatalog;
	}

	private String parentCatalog;

	private String catalog;
	private int catalogTag;
	private StatisticListSetting statisticListSetting;

	private PopulationCatalog(String parent, String catalog, int code) {
		this.parentCatalog = parent;
		this.catalog = catalog;
		this.catalogTag = code;

	}

	private PopulationCatalog(String parent, String catalog, int code,
			StatisticListSetting statisticListSetting) {
		// this.parentCatalog=parent;
		// this.catalog=catalog;
		// this.catalogTag=code;
		this(parent, catalog, code);
		this.statisticListSetting = statisticListSetting;
	}

	public String getParentCatalog() {
		return parentCatalog;
	}

	public void setParentCatalog(String parentCatalog) {
		this.parentCatalog = parentCatalog;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public int getCatalogTag() {
		return catalogTag;
	}

	public void setCatalogTag(int catalogTag) {
		this.catalogTag = catalogTag;
	}

	public String getDisplayName() {
		return toString();
	}

	public String toString() {
		switch (catalogTag) {
		case PRIMARYORGANIZATION_TAG:
			return "组织大类";
		case ALL_TAG:
			return "人口";
		case ALL_ACTUAL_POPULATION_TAG:
			return "人口信息";
		case ALL_ATTENTION_POPULATION_TAG:
			return "特殊人群";
		case ALL_BIRTH_POPULATION_TAG:
			return "计生";
		case ALL_CIVIL_POPULATION_TAG:
			return "关怀对象";
		case ALL_LOVINGCARE_POPULATION_TAG:
			return "关怀人员";
		case ALL_UNEMPLOYED_POPULATION_TAG:
			return "失业人员";

		case HOUSEHOULD_POPULATION_TAG:
			return "户籍人口";
		case FLOATING_POPULATION_TAG:
			return "流动人口";
		case UNHOUSEHOULD_POPULATION_TAG:
			return "未落户人口";
		case OVERSEA_POPULATION_TAG:
			return "境外人口";

		case RECTIFICATIVE_TAG:
			return "社区矫正人员";
		case POSITIVE_TAG:
			return "刑释人员";
		case MENTAL_TAG:
			return "肇事肇祸精神病";
		case DRUGGY_TAG:
			return "吸毒人员";
		case AIDSPOPULATIONS_TAG:
			return "艾滋病人员";
		case OTHER_ATTENTION_PERSONNEL_TAG:
			return "其他人员";
		case OVERSEA_PERSON_TAG:
			return "境外人员";
		case SUPERIORVISIT_TAG:
			return "重点上访人员";
		case DANGEROUS_GOODS_PRACTITIONER_TAG:
			return "危险品从业人员";
		case BUILD_DATA_TAG:
			return "楼宇";
		case IDLE_YOUTH_TAG:
			return "重点青少年";
		case BEARING_WOMEN_TAG:
			return "育龄妇女";
		case AID_NEED_POPULATION_TAG:
			return "需救助对象";
		case ELDERLY_PEOPLE_TAG:
			return "老年人";
		case DANGEROUSCHEMICALUNIT_TAG:
			return "危险化学品单位";
		case HANDICAPPED_TAG:
			return "残疾人";
		case UNEMPLOYED_PEOPLE_TAG:
			return "失业人员";
		case OPTIMAL_OBJECT_TAG:
			return "优抚对象";
		case ALL_FXJ_TAG:
			return "FXJ";
		case FPERSONNEL_TAG:
			return "F";
		case QPERSONNEL_TAG:
			return "Q";
		case MPERSONNEL_TAG:
			return "M";
		case GOODSAMARITAN_TAG:
			return "见义勇为";
		case ACTUAL_COMPANY_TAG:
			return "实有单位";

		case NEW_ECONOMIC_TAG:
			return "新经济组织";
		case NEW_SOCIETY_TAG:
			return "社会组织";
		case ALL_YOUTH_POPULATION_TAG:
			return "青少年";
		case SKYNET_TAG:
			return "天网";
		case BAYONET_TAG:
			return "卡口";
		case SNAPSHOTSYSTEM_TAG:
			return "抓拍系统";

		default:
			return "";
		}
	}

	public String getTableName() {
		switch (catalogTag) {
		case PRIMARYORGANIZATION_TAG:
			return "primaryorganizations";// 组织机构表
		case HOUSEHOULD_POPULATION_TAG:
			return "householdstaffs";
		case FLOATING_POPULATION_TAG:
			return "floatingpopulations";
		case UNHOUSEHOULD_POPULATION_TAG:
			return "unsettledpopulations";
		case OVERSEA_POPULATION_TAG:
			return "overseapersonnel";

		case RECTIFICATIVE_TAG:
			return "rectificativepersons";
		case POSITIVE_TAG:
			return "positiveinfos";
		case MENTAL_TAG:
			return "mentalpatients";
		case DRUGGY_TAG:
			return "druggys";
		case AIDSPOPULATIONS_TAG:
			return "aidspopulations";

		case OVERSEA_PERSON_TAG:
			return "overseapersonnel";
		case SUPERIORVISIT_TAG:
			return "superiorvisits";
		case DANGEROUS_GOODS_PRACTITIONER_TAG:
			return "dangerousgoodspractitioners";
		case OTHER_ATTENTION_PERSONNEL_TAG:
			return "otherAttentionPersonnels";
		case IDLE_YOUTH_TAG:
			return "idleyouths";

		case BEARING_WOMEN_TAG:
			return "nurturesWomen";

		case AID_NEED_POPULATION_TAG:
			return "aidNeedPopulation";
		case ELDERLY_PEOPLE_TAG:
			return "elderlyPeople";
		case UNEMPLOYED_PEOPLE_TAG:
			return "unemployedPeople";
		case DANGEROUSCHEMICALUNIT_TAG:
			return "dangerousChemicalsUnit";
		case OPTIMAL_OBJECT_TAG:
			return "optimalObjects";

		case HANDICAPPED_TAG:
			return "handicappeds";
		case FPERSONNEL_TAG:
			return "fPersonnels";
		case QPERSONNEL_TAG:
			return "qPersonnels";
		case MPERSONNEL_TAG:
			return "mPersonnels";
		case GOODSAMARITAN_TAG:
			return "goodSamaritan";
		case ACTUAL_COMPANY_TAG:
			return "actualCompany";

		case NEW_ECONOMIC_TAG:
			return "neweconomicorganizations";
		case NEW_SOCIETY_TAG:
			return "newsocietyorganizations";
		default:
			return "";
		}
	}

	public String getViewPopulationUrl() {
		switch (catalogTag) {
		case HOUSEHOULD_POPULATION_TAG:
			return "/baseinfo/householdStaff/dispath.action?mode=view&population.id=";
		case FLOATING_POPULATION_TAG:
			return "/baseinfo/floatingPopulationManage/dispath.action?mode=view&population.id=";
		case OVERSEA_POPULATION_TAG:
			return "/baseinfo/overseaPersonnelManage/dispatch.action?mode=view&overseaPersonnel.id=";
		case UNHOUSEHOULD_POPULATION_TAG:
			return "/baseinfo/unsettledPopulationManage/dispatchOperate.action?mode=view&unsettledPopulation.id=";

		case RECTIFICATIVE_TAG:
			return "positiveinfos";
		case POSITIVE_TAG:
			return "rectificativepersons";
		case MENTAL_TAG:
			return "mentalpatients";
		case DRUGGY_TAG:
			return "druggys";
		case AIDSPOPULATIONS_TAG:
			return "aidspopulations";

		case OVERSEA_PERSON_TAG:
			return "/baseinfo/overseaPersonnelManage/dispatch.action?mode=view&overseaPersonnel.id=";
		case SUPERIORVISIT_TAG:
			return "superiorvisits";
		case DANGEROUS_GOODS_PRACTITIONER_TAG:
			return "dangerousgoodspractitioners";

		case IDLE_YOUTH_TAG:
			return "idleyouths";

		case BEARING_WOMEN_TAG:
			return "育龄妇女";

		case AID_NEED_POPULATION_TAG:
			return "需救助对象";
		case ELDERLY_PEOPLE_TAG:
			return "老年人";

		case DANGEROUSCHEMICALUNIT_TAG:
			return "dangerousChemicalsUnit";

		default:
			return "";
		}
	}

	/**
	 * 获取所有实有人员的PopulationCatalogList
	 * 
	 * @return
	 */
	public static List<PopulationCatalog> getAllActualPopulationCatalog() {
		return new ArrayList<PopulationCatalog>(actualCatalogs.values());
	}

	/**
	 * 获取所有重点人员的PopulationCatalogList
	 * 
	 * @return
	 */
	public static List<PopulationCatalog> getAllAttentionPopulationCatalog() {
		return new ArrayList<PopulationCatalog>(attentionCatalogs.values());
	}

	/**
	 * 获取与青少年有关的PopulationCatalogList
	 */
	public static List<PopulationCatalog> getAllYouthPopulationCatalog() {
		return new ArrayList<PopulationCatalog>(youthCatalogs.values());
	}

	/**
	 * 获取所有失业的PopulationCatalogList
	 * 
	 * @return
	 */
	public static List<PopulationCatalog> getAllUnemployPopulationCatalog() {
		return new ArrayList<PopulationCatalog>(unemployCatalogs.values());
	}

	/**
	 * 获取所有计生的PopulationCatalogList
	 * 
	 * @return
	 */
	public static List<PopulationCatalog> getAllBirthPopulationCatalog() {
		return new ArrayList<PopulationCatalog>(birthCatalogs.values());
	}

	/**
	 * 获取所有民政的PopulationCatalogList
	 * 
	 * @return
	 */
	public static List<PopulationCatalog> getAllCivilPopulationCatalog() {

		return new ArrayList<PopulationCatalog>(civilCatalogs.values());
	}

	/**
	 * 获取所有关怀对象的PopulationCatalogList
	 * 
	 * @return
	 */
	public static List<PopulationCatalog> getAllLovingCarePopulationCatalog() {
		return new ArrayList<PopulationCatalog>(lovingCareCatalogs.values());
	}

	/**
	 * 获取所有两新组织的PopulationCatalogList
	 * 
	 * @return
	 */
	public static List<PopulationCatalog> getAllDoubleNewPopulationCatalog() {
		return new ArrayList<PopulationCatalog>(doubleNewCatalogs.values());
	}

	/**
	 * 获取根据传入allCatalogs的参数获得的中文名称
	 */
	public static PopulationCatalog populationCatalog(String catalog) {
		return allCatalogs.get(catalog);
	}

	public static Map<String, PopulationCatalog> getCatalogs(String keyName) {
		return keyCatalogs.get(keyName);
	}

	public StatisticListSetting getStatisticListSetting() {
		return statisticListSetting;
	}

	public void setStatisticListSetting(
			StatisticListSetting statisticListSetting) {
		this.statisticListSetting = statisticListSetting;
	}

}
