package com.tianque.leaderAnalysis.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.util.BaseInfoTables;

public class MobileModelTypes {
	/** 视图类型 **/
	public final static String VIEW_TABLE = "table";
	public final static String VIEW_COLUMN = "column";
	public final static String VIEW_PIE = "pie";
	public final static String VIEW_LINEAR = "linear";

	/*** 人口 ***/
	public final static String DISNAME_SUM = "合计";
	public final static String DISNAME_NURTURESWOMEN = "育龄妇女";
	public final static String DISNAME_UNEMPLOYEDPEOPLE = "失业人员";
	public final static String DISNAME_CIVIL = "关怀对象";
	public final static String DISNAME_ATTENTION = "特殊人群";
	public final static String DISNAME_FLOATING = "流动人口";
	public final static String DISNAME_HOUSEHOLDSTAFF = "户籍人口";

	public final static int TYPE_POPULATION = 1;
	/** 特殊人群 */
	public final static String POPULATION_TYPE_ATTENTION = "all_attention_population";
	public final static int TYPE_SPECIALPOPULATION = 2;
	/** 关怀对象 **/
	public final static String POPULATION_TYPE_CIVIL = "all_civil_population";
	public final static int TYPE_CIVIL = 3;

	/** 育龄妇女 **/
	public final static String POPULATION_TYPE_NURTURESWOMEN = "nurturesWomen";
	public final static int TYPE_NURTURESWOMEN = 4;
	/** 失业人员 **/
	public final static int TYPE_UNEMPLOYEDPEOPLE = 5;
	public final static String POPULATION_TYPE_UNEMPLOYEDPEOPLE = "unemployedPeople";

	/** 事件 **/
	public final static int TYPE_ISSUE = 6;
	/****************** 人口 **********************/
	public final static int POPULATION_GENERAL = 0;// 总况
	public final static int POPULATION_HOUSEHOLDSTAFF = 1;// 户籍
	public final static int POPULATION_FLOATING = 2;// 流动
	public final static int POPULATION_OVERSEA = 3;// 境外
	/******************** 特殊人群 ********************/
	public final static int SPECIALPOPULATION_GENERAL = 0;// 总况
	public final static int SPECIALPOPULATION_POSITIVEINFO = 1;// 刑事
	public final static int SPECIALPOPULATION_RECTIFICATIVE = 2;// 矫正
	public final static int SPECIALPOPULATION_MENTALPATIENT = 3;// 精神病
	public final static int SPECIALPOPULATION_DRUGGY = 4;// 吸毒
	public final static int SPECIALPOPULATION_IDLEYOUTH = 5;// 重点青少年
	public final static int SPECIALPOPULATION_DANGEROUSGOODS = 6;// 危险品从业
	// public final static int SPECIALPOPULATION_OTHERATTENTION = 7;// 其他
	// public final static int SPECIALPOPULATION_AIDS = 8;// 艾滋病
	/** 育龄妇女 **/
	public final static int NURTURESWOMEN_GENERAL = 0;// 总况
	/** 失业人员 **/
	public final static int UNEMPLOYEDPEOPLE_GENERAL = 0;
	/** 关怀对象 */
	public final static int CIVIL_GENERAL = 0;
	public final static int CIVIL_ELDERLYPEOPLE = 1;
	public final static int CIVIL_HANDICAPPED = 2;
	public final static int CIVIL_OPTIMALOBJECT = 3;
	public final static int CIVIL_AIDNEEDPOPULATION = 4;
	/******************** 事件 ********************/
	/** 事件总况 */
	public final static String ALL_ISSUE_MODEL = "allIssue";
	public final static String ALL_ISSUE = "事件总况";

	/** 民生服务 */
	public final static String PEOPLELIVE_SERVICE = "民生服务";
	public final static String PEOPLELIVE_SERVICE_MODEL = "peopleLive";
	/** 矛盾劝解 */
	public final static String RESOLVETHECONTRADICTIONS = "矛盾劝解";
	public final static String RESOLVETHECONTRADICTIONS_MODEL = "resolveTheContradiction";
	/** 参与治安防控 */
	public final static String SECURITYPRECAUTIONS = "参与治安防控";
	public final static String SECURITYPRECAUTIONS_MODEL = "securityPrecaution";
	/** 参与特殊人群服务管理 */
	public final static String SPECIALPOPULATIONS = "参与特殊人群服务管理";
	public final static String SPECIALPOPULATIONS_MODEL = "specialPopulation";
	/** 社情民意收集 */
	public final static String SOCIALCONDITIONS = "社情民意收集";
	public final static String SOCIALCONDITIONS_MODEL = "socialCondition";
	/** 政策法规宣传 */
	public final static String POLICIESANDLAWS = "政策法规宣传";
	public final static String POLICIESANDLAWS_MODEL = "policiesAndlaw";
	/** 突发事件报告 */
	public final static String EMERGENCIES = "突发事件报告";
	public final static String EMERGENCIES_MODEL = "emergencies";
	/** 其它 */
	public final static String OTHERMANAGE = "其它";
	public final static String OTHERMANAGE_MODEL = "otherManage";

	/** 事件各个分类 */
	public final static Map<String, String> ISSUE_MODEL_MAP = new HashMap<String, String>();
	// 人口
	public final static Map<String, Integer> POPULATION_TABLE_MAP = new HashMap<String, Integer>();
	// 特殊人群
	public final static Map<String, Integer> SPECIALPOPULATION_TABLE_MAP = new HashMap<String, Integer>();
	// 关怀对象
	public final static Map<String, Integer> CIVIL_TABLE_MAP = new HashMap<String, Integer>();
	// 育龄妇女
	public final static Map<String, Integer> NURTURESWOMEN_TABLE_MAP = new HashMap<String, Integer>();
	// 失业人员
	public final static Map<String, Integer> UNEMPLOYEDPEOPLE_TABLE_MAP = new HashMap<String, Integer>();

	public final static List<String> POPULATION_TYPE_LIST = new ArrayList<String>();

	/** 总况获取中文名称用 */
	public final static Map<String, String> CNAME_MAP = new HashMap<String, String>();

	/** 流动 */

	static {
		CNAME_MAP.put("floatingPopulation", "流动人口");
		CNAME_MAP.put("householdStaff", "户籍人口");
		CNAME_MAP.put("POPULATION", "人口总况");
		CNAME_MAP.put("mentalPatient", "严重精神障碍患者");
		CNAME_MAP.put("positiveInfo", "刑释解教人员");
		CNAME_MAP.put("IMPORTANTPERSONNEL", "特殊人群");
		CNAME_MAP.put("idleYouth", "重点青少年");
		CNAME_MAP.put("dangerousGoodsPractitioner", "危险品从业人员");
		CNAME_MAP.put("rectificativePerson", "社区矫正人员");
		CNAME_MAP.put("druggy", "吸毒人员");
		CNAME_MAP.put("handicapped", "残疾人");
		CNAME_MAP.put("aidNeedPopulation", "需救助人员");
		CNAME_MAP.put("elderlyPeople", "老年人");
		CNAME_MAP.put("optimalObject", "优抚对象");
		CNAME_MAP.put("CIVIL", "关怀对象");
		CNAME_MAP.put("nurturesWomen", "育妇人员");
		CNAME_MAP.put("unemployedPeople", "失业人员");

		/** 事件加入 */
		CNAME_MAP.putAll(ISSUE_MODEL_MAP);

		ISSUE_MODEL_MAP.put(PEOPLELIVE_SERVICE_MODEL, PEOPLELIVE_SERVICE);
		ISSUE_MODEL_MAP.put(RESOLVETHECONTRADICTIONS_MODEL,
				RESOLVETHECONTRADICTIONS);
		ISSUE_MODEL_MAP.put(SECURITYPRECAUTIONS_MODEL, SECURITYPRECAUTIONS);
		ISSUE_MODEL_MAP.put(SPECIALPOPULATIONS_MODEL, SPECIALPOPULATIONS);
		ISSUE_MODEL_MAP.put(SOCIALCONDITIONS_MODEL, SOCIALCONDITIONS);
		ISSUE_MODEL_MAP.put(POLICIESANDLAWS_MODEL, POLICIESANDLAWS);
		ISSUE_MODEL_MAP.put(EMERGENCIES_MODEL, EMERGENCIES);
		ISSUE_MODEL_MAP.put(OTHERMANAGE_MODEL, OTHERMANAGE);

		ISSUE_MODEL_MAP.put(ALL_ISSUE_MODEL, ALL_ISSUE);

		POPULATION_TABLE_MAP.put(BaseInfoTables.POPULATION_KEY,
				POPULATION_GENERAL);
		POPULATION_TABLE_MAP.put(BaseInfoTables.HOUSEHOLDSTAFF_KEY,
				POPULATION_HOUSEHOLDSTAFF);
		POPULATION_TABLE_MAP.put(BaseInfoTables.FLOATINGPOPULATION_KEY,
				POPULATION_FLOATING);

		SPECIALPOPULATION_TABLE_MAP.put(BaseInfoTables.IMPORTANTPERSONNEL_KEY,
				SPECIALPOPULATION_GENERAL);
		SPECIALPOPULATION_TABLE_MAP.put(BaseInfoTables.POSITIVEINFO_KEY,
				SPECIALPOPULATION_POSITIVEINFO);
		SPECIALPOPULATION_TABLE_MAP.put(BaseInfoTables.RECTIFICATIVEPERSON_KEY,
				SPECIALPOPULATION_RECTIFICATIVE);
		SPECIALPOPULATION_TABLE_MAP.put(BaseInfoTables.MENTALPATIENT_KEY,
				SPECIALPOPULATION_MENTALPATIENT);
		SPECIALPOPULATION_TABLE_MAP.put(BaseInfoTables.DRUGGY_KEY,
				SPECIALPOPULATION_DRUGGY);
		SPECIALPOPULATION_TABLE_MAP.put(BaseInfoTables.IDLEYOUTH_KEY,
				SPECIALPOPULATION_IDLEYOUTH);
		SPECIALPOPULATION_TABLE_MAP.put(
				BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY,
				SPECIALPOPULATION_DANGEROUSGOODS);

		NURTURESWOMEN_TABLE_MAP.put(BaseInfoTables.NURTURESWOMEN_KEY,
				NURTURESWOMEN_GENERAL);

		UNEMPLOYEDPEOPLE_TABLE_MAP.put(BaseInfoTables.UNEMPLOYEDPEOPLE_KEY,
				UNEMPLOYEDPEOPLE_GENERAL);

		CIVIL_TABLE_MAP.put(BaseInfoTables.CIVIL_KEY, CIVIL_GENERAL);
		CIVIL_TABLE_MAP.put(BaseInfoTables.ELDERLYPEOPLE_KEY,
				CIVIL_ELDERLYPEOPLE);
		CIVIL_TABLE_MAP.put(BaseInfoTables.HANDICAPPED_KEY, CIVIL_HANDICAPPED);
		CIVIL_TABLE_MAP.put(BaseInfoTables.OPTIMALOBJECT_KEY,
				CIVIL_OPTIMALOBJECT);
		CIVIL_TABLE_MAP.put(BaseInfoTables.AIDNEEDPOPULATION_KEY,
				CIVIL_AIDNEEDPOPULATION);

		POPULATION_TYPE_LIST.add(POPULATION_TYPE_ATTENTION);
		POPULATION_TYPE_LIST.add(POPULATION_TYPE_CIVIL);
		POPULATION_TYPE_LIST.add(POPULATION_TYPE_NURTURESWOMEN);
		POPULATION_TYPE_LIST.add(POPULATION_TYPE_UNEMPLOYEDPEOPLE);

		/********* 事件 *********/
	}
}
