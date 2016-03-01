package com.tianque.plugin.statistics.constants;

import java.util.HashMap;
import java.util.Map;

public class GeneralStituationConstants {
	
	/***
	 * 按月查询
	 */
	public static final Integer SEARCH_BY_MONTH = 0;
	/***
	 * 按季度查询
	 */
	public static final Integer SEARCH_BY_QUARTER=1;
	/***
	 * 
	 */
	public static final Integer SEARCH_BY_YEAR=2;
	/***
	 * 按全年查询
	 */
	public static final Integer SEARCH_BY_ALLYEAR=0;
	/***
	 * 按上半年查询
	 */
	public static final Integer SEARCH_BY_FIRSTHALF_YEAR=1;
	/***
	 * 按下半年查询
	 */
	public static final Integer SEARCH_BY_SECONDHALF_YEAR=2;
	/***
	 * 按上报统计
	 */
	public static final Integer SEARCH_BY_REPORT = 0;
	/***
	 * 按签收统计
	 */
	public static final Integer SEARCH_BY_SIGN = 1;
	/***
	 * 按签收与上报统计
	 */
	public static final Integer SEARCH_BY_ALL= 2;
	/***
	 * 派出所签收统计
	 */
	public static final Integer SEARCH_BY_POLICESIGN = 0;
	/***
	 * 卫生所签收统计
	 */
	public static final Integer SEARCH_BY_CLINICSIGN = 1;
	

	public static final String PANDECT_KEY = "pandect";//总览
	public static final String FLOATING_POPULATION_KEY="a_floating";//流动人口
	public static final String PROPAGANDAANDVERIFICATION_KEY ="a_propagandaAndVerification";//流口--宣传核查
	public static final String WORKINGSITUATION_KEY ="b_workingSituation";//流口--民警带领下开展工作情况
	public static final String EXCEPTIONALSITUATIONRECORD_KEY ="c_exceptionalSituationRecord";//流口--异常情形报告
	
	public static final String DRUGGY_KEY = "b_druggyTask";//吸毒人员
	public static final String SEVEREPSYCHOSIS_KEY = "c_mentalPatientTask";//重症精神病
	public static final String COMMUNITYSERVING_KEY = "d_termerRecord";//社区服刑人員
	public static final String EMANCIPISTS_KEY = "e_positiveInfoRecord";//刑释人员
	public static final String HIDDENDANGGER_KEY = "f_hiddendanger"; //发现治安隐患
	
	public static final Map<String,String> hasSubTypeMap = new HashMap<String,String>();//有三个子类别统计查询的表
	public static final Map<String,String> tableMap = new HashMap<String,String>();//只有一个大类别的表
	public static final Map<String,String> subTypeColumnMap = new HashMap<String,String>();//三个子类别中子类别字段
	/***
	 * 表名和表中签收字段存储的map
	 */
	public static final Map<String,String> columnMap = new HashMap<String,String>();
 	static{
 		hasSubTypeMap.put(WORKINGSITUATION_KEY, "workingSituation");
 		hasSubTypeMap.put(EXCEPTIONALSITUATIONRECORD_KEY, "exceptionalSituationRecord");
 		hasSubTypeMap.put(HIDDENDANGGER_KEY, "hiddendanger");
		
		tableMap.put(DRUGGY_KEY, "druggyTask");
		tableMap.put(PROPAGANDAANDVERIFICATION_KEY, "propagandaAndVerification");
		tableMap.put(COMMUNITYSERVING_KEY, "termerRecord");
		tableMap.put(EMANCIPISTS_KEY, "positiveInfoRecord");
		
		
		columnMap.put(PROPAGANDAANDVERIFICATION_KEY, "isHandle");
		columnMap.put(WORKINGSITUATION_KEY, "isHandle");
		columnMap.put(EXCEPTIONALSITUATIONRECORD_KEY, "status");
		columnMap.put(DRUGGY_KEY, "status");
		columnMap.put(COMMUNITYSERVING_KEY, "status");
		columnMap.put(EMANCIPISTS_KEY, "status");
		columnMap.put(HIDDENDANGGER_KEY, "isHandle");
		
		subTypeColumnMap.put(WORKINGSITUATION_KEY,"workcontent" );
		subTypeColumnMap.put(EXCEPTIONALSITUATIONRECORD_KEY,"exceptionsituation" );
		subTypeColumnMap.put(HIDDENDANGGER_KEY, "exceptiontype");
	}
	
}
