package com.tianque.plugin.statistics.constants;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 任务清单研判分析查询条件定义类
 * @author szq
 */
public class TypeConstants {
	
	public static final String PANDECT_KEY = "pandect";//总览
	public static final String FLOATING_POPULATION_KEY="a_floating";//流动人口
	public static final String PROPAGANDAANDVERIFICATION_KEY ="a_propagandaAndVerification";//流口--宣传核查
	public static final String WORKINGSITUATION_KEY ="b_workingSituation";//流口--民警带领下开展工作情况
	public static final String EXCEPTIONALSITUATIONRECORD_KEY ="c_exceptionalSituationRecord";//流口--异常情形报告
	
	public static final String SPECIAL_KEY = "special";//特殊人群总览
	public static final String DRUGGY_KEY = "b_druggyTask";//吸毒人员
	public static final String SEVEREPSYCHOSIS_KEY = "c_mentalPatientTask";//重症精神病
	public static final String COMMUNITYSERVING_KEY = "d_termerRecord";//社区服刑人員
	public static final String EMANCIPISTS_KEY = "e_positiveInfoRecord";//刑释人员
	public static final String HIDDENDANGGER_KEY = "f_hiddendanger"; //发现治安隐患
	
	public static final Map<String,String> typeMapName = new LinkedHashMap<String,String>();//任务类别
	public static final Map<String,String> typeMapByName = new LinkedHashMap<String,String>();//流口子任务类别
	public static final Map<String,String> hiddenMapByName = new LinkedHashMap<String,String>();//隐患子任务类别
	public static final Map<String,String> specialMapByName = new LinkedHashMap<String, String>();//特殊人群任务类别
	
	public static final Map<String,String> typeMapByFloating = new LinkedHashMap<String,String>();//趋势图流口子任务类别
	public static final String allBasesicType = new String(FLOATING_POPULATION_KEY+","+DRUGGY_KEY+","+SEVEREPSYCHOSIS_KEY+","+COMMUNITYSERVING_KEY+","+
			EMANCIPISTS_KEY+","+HIDDENDANGGER_KEY);
	//特殊人群
	public static final String allSpecialType = new String(DRUGGY_KEY+","+SEVEREPSYCHOSIS_KEY+","+COMMUNITYSERVING_KEY+","+
			EMANCIPISTS_KEY);
	public static final String allFloatingType = new String(PROPAGANDAANDVERIFICATION_KEY+","+WORKINGSITUATION_KEY+","+EXCEPTIONALSITUATIONRECORD_KEY);
	static{
		typeMapName.put(PANDECT_KEY,"总览");
		typeMapName.put(FLOATING_POPULATION_KEY,"流动人口");
		typeMapName.put(DRUGGY_KEY,"吸毒人员");
		typeMapName.put(SEVEREPSYCHOSIS_KEY,"严重精神病障碍患者");
		typeMapName.put(COMMUNITYSERVING_KEY,"社区服刑人员");
		typeMapName.put(EMANCIPISTS_KEY,"刑释人员");
		typeMapName.put(HIDDENDANGGER_KEY,"发现治安隐患");
		
		typeMapByName.put(PANDECT_KEY,"总览");
		typeMapByName.put(PROPAGANDAANDVERIFICATION_KEY, "宣传核查");
		typeMapByName.put(WORKINGSITUATION_KEY, "民警带领下开展工作");
		typeMapByName.put(EXCEPTIONALSITUATIONRECORD_KEY, "异常情况报告");
		
		specialMapByName.put(SPECIAL_KEY, "总览");
		specialMapByName.put(DRUGGY_KEY,"吸毒人员");
		specialMapByName.put(SEVEREPSYCHOSIS_KEY,"严重精神病障碍患者");
		specialMapByName.put(COMMUNITYSERVING_KEY,"社区服刑人员");
		specialMapByName.put(EMANCIPISTS_KEY,"刑释人员");
		
		//趋势图流动人口总览
		typeMapByFloating.put(FLOATING_POPULATION_KEY,"总览");
		typeMapByFloating.put(PROPAGANDAANDVERIFICATION_KEY, "宣传核查");
		typeMapByFloating.put(WORKINGSITUATION_KEY, "民警带领下开展工作");
		typeMapByFloating.put(EXCEPTIONALSITUATIONRECORD_KEY, "异常情况报告");
	}
	
}
