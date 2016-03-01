package com.tianque.plugin.analysisNew.util;

import java.util.HashMap;
import java.util.Map;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.StringUtil;

public class PopulationType {

	private static Map<String, String[]> POPULATION_TYPE = new HashMap<String, String[]>();
	private static Map<String, String> POPULATION_TYPE_CNAME = new HashMap<String, String>();
	private static Map<String, String> ACTUAL_TYPE_CNAME = new HashMap<String, String>();

	/** 流动人口 */
	public final static String FLOATING_POPULATION = BaseInfoTables.FLOATINGPOPULATION_KEY;
	/** 户籍人口 */
	public final static String HOUSEHOLD_STAFF = BaseInfoTables.HOUSEHOLDSTAFF_KEY;
	/** 未落户人口 */
	public final static String UNSETTLED_POPULATION = BaseInfoTables.UNSETTEDPOPULATION_KEY;
	/** 境外人员 */
	public final static String OVERSEA_STAFF = BaseInfoTables.OVERSEAPERSONNEL_KEY;
	public final static String OVERSEA_PERSONNEL = "overseaPersonnel";// overseapersonnel

	public final static String SPECIAL_POPULATION = "specialPopulation";
	public final static String RECTIFICATIVE_POPULATION = BaseInfoTables.RECTIFICATIVEPERSON_KEY;
	public final static String POSITIVE_INFO = BaseInfoTables.POSITIVEINFO_KEY;
	public final static String MENTAL_PATIENT = BaseInfoTables.MENTALPATIENT_KEY;
	public final static String DRUGGY = BaseInfoTables.DRUGGY_KEY;
	public final static String OPTIMALOBJECT = BaseInfoTables.OPTIMALOBJECT_KEY;
	public final static String SCHOOL = BaseInfoTables.SCHOOL_KEY;
	public final static String HOSPITAL = BaseInfoTables.HOSPITAL_KEY;
	public final static String SAFETYPRODUCTIONKEY = BaseInfoTables.SAFETYPRODUCTIONKEY_KEY;
	public final static String FIRESAFETYKEY = BaseInfoTables.FIRESAFETYKEY_KEY;
	public final static String SECURITYKEY = BaseInfoTables.SECURITYKEY_KEY;
	public final static String OTHERLOCALE = BaseInfoTables.OTHERLOCALE_KEY;
	public final static String DANGEROUSCHEMICALSUNIT = BaseInfoTables.DANGEROUSCHEMICALSUNIT_KEY;
	public final static String INTERNETBAR = BaseInfoTables.INTERNETBAR_KEY;
	public final static String PUBLICPLACE = BaseInfoTables.PUBLICPLACE_KEY;
	public final static String ACTUALCOMPANY = BaseInfoTables.ACTUALCOMPANY_KEY;
	public final static String NEWSOCIETYORGANIZATIONS = BaseInfoTables.NEWSOCIETYORGANIZATIONS_KEY;
	public final static String NEWECONOMICORGANIZATIONS = BaseInfoTables.NEWECONOMICORGANIZATIONS_KEY;
	public final static String ENTERPRISEKEY = BaseInfoTables.ENTERPRISEKEY_KEY;
	public final static String ENTERPRISEDOWNKEY = BaseInfoTables.ENTERPRISEDOWNKEY_KEY;
	public final static String AIDSPOPULATIONS = BaseInfoTables.AIDSPOPULATIONS_KEY;
	public final static String AIDSPOPULATIONS_KEY = "aidspopulations";

	public final static String KEY_POPULATION = "keyPopulation";
	public final static String KEY_OPTIMAL_OBJECT = "keyOptimalObject";
	// public final static String OVERSEA_PERSON = "overseaPerson";
	public final static String SUPERIOR_VISIT = BaseInfoTables.SUPERIORVISIT_KEY;
	public final static String DANGEROUS_GOODS_PRACTITIONER = BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY;
	public final static String AID_NEED_POPULATION = BaseInfoTables.AIDNEEDPOPULATION_KEY;
	public final static String IDLE_YOUTH = BaseInfoTables.IDLEYOUTH_KEY;

	// fateson add 其他人员
	public final static String OTHER_ATTENTION_PERSONNEL = BaseInfoTables.OTHERATTENTIONPERSONNEL_KEY;

	public final static String FAMILY_PLANNING = "familyPlanning";
	public final static String NURTURES_WOMEN = BaseInfoTables.NURTURESWOMEN_KEY;

	public final static String CIVIC_SERVICE = "civicService";
	public final static String DIFFICULTIES_PEOPLE = "difficultiesPeople";
	public final static String ELDERLY_PEOPLE = BaseInfoTables.ELDERLYPEOPLE_KEY;
	public final static String HANDICAPPED = BaseInfoTables.HANDICAPPED_KEY;
	public final static String OPTIMAL_OBJECT = BaseInfoTables.OPTIMALOBJECT_KEY;
	public final static String OTHER_PEOPLE = "otherPeople";

	public final static String UNEMPLOYED_PEOPLE = BaseInfoTables.UNEMPLOYEDPEOPLE_KEY;
	// FXJ
	public final static String FXJ_POPULATION = "fxjPopulation";
	public final static String F_PERSONNEL = BaseInfoTables.FPERSONNEL_KEY;
	public final static String Q_PERSONNEL = BaseInfoTables.QPERSONNEL_KEY;
	public final static String M_PERSONNEL = BaseInfoTables.MPERSONNEL_KEY;
	// 见义勇为
	public final static String GOOD_SAMARITAN = BaseInfoTables.GOOD_SAMARITAN_KEY;

	// TODO 暂时放在这里，好不好
	public final static String ACTUAL_HOUSE = "actualHouse";
	public final static String RENTAL_HOUSE = "rentalHouse";
	public final static String ACTUAL_COMPANY = BaseInfoTables.ACTUALCOMPANY_KEY;
	public final static String NEW_ECONOMIC = "newEconomic";
	public final static String NEW_SOCIETY = "newSociety";
	public final static String ALL_YOUTH_POPULATION = "all_youth_population";// 青少年
	public final static String BUILD_DATA = "builddatas";

	// 组织机构
	public final static String PRIMARYORGANIZATIONS = "primaryOrganizations";
	public final static String DEPARTMENTPARTY = "departmentParty";

	static {
		ACTUAL_TYPE_CNAME.put(FLOATING_POPULATION, "流动人口");
		ACTUAL_TYPE_CNAME.put(HOUSEHOLD_STAFF, "户籍人口");
		ACTUAL_TYPE_CNAME.put(UNSETTLED_POPULATION, "未落户人口");
		ACTUAL_TYPE_CNAME.put(OVERSEA_STAFF, "境外人员");

		POPULATION_TYPE_CNAME.put(SPECIAL_POPULATION, "特殊人群");
		POPULATION_TYPE_CNAME.put(RECTIFICATIVE_POPULATION, "社区矫正人员");
		POPULATION_TYPE_CNAME.put(POSITIVE_INFO, "刑释人员");
		POPULATION_TYPE_CNAME.put(MENTAL_PATIENT, "严重精神障碍患者");
		POPULATION_TYPE_CNAME.put(DRUGGY, "吸毒人员");
		POPULATION_TYPE_CNAME.put(KEY_POPULATION, "特殊人群");
		POPULATION_TYPE_CNAME.put(KEY_OPTIMAL_OBJECT, "关怀对象");
		POPULATION_TYPE_CNAME.put(OVERSEA_STAFF, "境外人员");
		POPULATION_TYPE_CNAME.put(SUPERIOR_VISIT, "重点上访人员");
		POPULATION_TYPE_CNAME.put(DANGEROUS_GOODS_PRACTITIONER, "危险品从业人员");
		POPULATION_TYPE_CNAME.put(OTHER_ATTENTION_PERSONNEL, "其他人员");
		POPULATION_TYPE_CNAME.put(AID_NEED_POPULATION, "需救助人员");
		POPULATION_TYPE_CNAME.put(IDLE_YOUTH, "重点青少年");
		POPULATION_TYPE_CNAME.put(FAMILY_PLANNING, "计生");
		POPULATION_TYPE_CNAME.put(NURTURES_WOMEN, "育龄妇女");
		POPULATION_TYPE_CNAME.put(CIVIC_SERVICE, "民政");
		POPULATION_TYPE_CNAME.put(DIFFICULTIES_PEOPLE, "困难群众");
		POPULATION_TYPE_CNAME.put(ELDERLY_PEOPLE, "老年人");
		POPULATION_TYPE_CNAME.put(HANDICAPPED, "残疾人");
		POPULATION_TYPE_CNAME.put(OPTIMAL_OBJECT, "优抚对象");
		POPULATION_TYPE_CNAME.put(OTHER_PEOPLE, "其他人员");
		POPULATION_TYPE_CNAME.put(UNEMPLOYED_PEOPLE, "失业人员");
		POPULATION_TYPE_CNAME.put(AIDSPOPULATIONS, "艾滋病人员");
		POPULATION_TYPE_CNAME.put(AIDSPOPULATIONS_KEY, "艾滋病人员");
		// FXJ
		POPULATION_TYPE_CNAME.put(FXJ_POPULATION, "FXJ");
		POPULATION_TYPE_CNAME.put(F_PERSONNEL, "F");
		POPULATION_TYPE_CNAME.put(Q_PERSONNEL, "Q");
		POPULATION_TYPE_CNAME.put(M_PERSONNEL, "M");

		POPULATION_TYPE_CNAME.put(GOOD_SAMARITAN, "见义勇为");

		POPULATION_TYPE_CNAME.put(ACTUAL_HOUSE, "房屋信息");
		POPULATION_TYPE_CNAME.put(RENTAL_HOUSE, "出租房");
		POPULATION_TYPE_CNAME.put(ACTUAL_COMPANY, "实有单位");
		POPULATION_TYPE_CNAME.put(SCHOOL, "学校");
		POPULATION_TYPE_CNAME.put(NEW_ECONOMIC, "新经济组织");
		POPULATION_TYPE_CNAME.put(NEW_SOCIETY, "社会组织");
		POPULATION_TYPE_CNAME.put(ALL_YOUTH_POPULATION, "青少年");

		POPULATION_TYPE_CNAME.put(PRIMARYORGANIZATIONS, "组织机构总况");
		POPULATION_TYPE_CNAME.put(DEPARTMENTPARTY, "党委部门");

		// POPULATION_TYPE.put(SPECIAL_POPULATION, new
		// String[]{RECTIFICATIVE_POPULATION,POSITIVE_INFO,MENTAL_PATIENT,DRUGGY});
		POPULATION_TYPE.put(KEY_POPULATION, new String[] {
				RECTIFICATIVE_POPULATION, POSITIVE_INFO, MENTAL_PATIENT,
				DRUGGY, AIDSPOPULATIONS_KEY, DANGEROUS_GOODS_PRACTITIONER,
				SUPERIOR_VISIT, IDLE_YOUTH, OTHER_ATTENTION_PERSONNEL });
		POPULATION_TYPE.put(NURTURES_WOMEN, new String[] { NURTURES_WOMEN });
		POPULATION_TYPE.put(OPTIMAL_OBJECT, new String[] { ELDERLY_PEOPLE,
				HANDICAPPED, OPTIMAL_OBJECT, AID_NEED_POPULATION });
		POPULATION_TYPE.put(UNEMPLOYED_PEOPLE,
				new String[] { UNEMPLOYED_PEOPLE });
		POPULATION_TYPE.put(FXJ_POPULATION, new String[] { F_PERSONNEL,
				Q_PERSONNEL, M_PERSONNEL });

		POPULATION_TYPE.put(GOOD_SAMARITAN, new String[] { GOOD_SAMARITAN });
	}

	public static String[] get(String key) {
		return POPULATION_TYPE.get(key);
	}

	public static String getCnameByPopulationType(String populationType) {
		return POPULATION_TYPE_CNAME.get(populationType);
	}

	public static String getCnameByActualType(String keyPersonType) {
		return ACTUAL_TYPE_CNAME.get(keyPersonType);
	}

	// 将TYPE类型首字母转成大写并输出
	public static String getFirstCharUpperCaseTypeByType(String type) {
		if (BaseInfoTables.OVERSEAPERSONNEL_KEY.equals(type)) {
			type = OVERSEA_PERSONNEL;
		}
		if (BaseInfoTables.AIDSPOPULATIONS_KEY.equals(type)) {
			type = AIDSPOPULATIONS_KEY;
		}
		return StringUtil.firstCharUpperCase(type);
	}

}
