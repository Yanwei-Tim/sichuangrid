package com.tianque.xichang.working.parameterEvaluation.timeLimit.constant;

import java.util.HashMap;
import java.util.Map;

public class UseLevel {

	public final static Map<String, String> orgLevelMap = new HashMap<String, String>();

	public final static Map<String, String> orgTypeMap = new HashMap<String, String>();

	public static final String ORG_LEVEL_DISTRICT = "县（区）";
	public static final String ORG_LEVEL_TOWN = "乡镇（街道）";
	public static final String ORG_LEVEL_VILLAGE = "村（社区）";

	public static final String DEPARTMENT_LEVEL_VILLAGE = "村、社区";
	public static final String DEPARTMENT_LEVEL_TOWN = "乡镇、街道";
	public static final String DEPARTMENT_LEVEL_DISTRICT_FUNCTION = "区县市职能部门";
	public static final String DEPARTMENT_LEVEL_TOWN_FUNCTION = "乡镇街道职能部门";
	public static final String DEPARTMENT_LEVEL_DISTRICT = "台账办公室";

	public static final String ORG_TYPE_ADMINISTRATIVE = "行政区域";
	public static final String ORG_TYPE_FUNCTIONAL = "职能部门";

	static {
		orgLevelMap.put(DEPARTMENT_LEVEL_VILLAGE, ORG_LEVEL_VILLAGE);
		orgLevelMap.put(DEPARTMENT_LEVEL_TOWN, ORG_LEVEL_TOWN);
		orgLevelMap.put(DEPARTMENT_LEVEL_DISTRICT_FUNCTION, ORG_LEVEL_DISTRICT);
		orgLevelMap.put(DEPARTMENT_LEVEL_TOWN_FUNCTION, ORG_LEVEL_TOWN);
		orgLevelMap.put(DEPARTMENT_LEVEL_DISTRICT, ORG_LEVEL_DISTRICT);

		orgTypeMap.put(DEPARTMENT_LEVEL_VILLAGE, ORG_TYPE_ADMINISTRATIVE);
		orgTypeMap.put(DEPARTMENT_LEVEL_TOWN, ORG_TYPE_ADMINISTRATIVE);
		orgTypeMap.put(DEPARTMENT_LEVEL_DISTRICT_FUNCTION, ORG_TYPE_FUNCTIONAL);
		orgTypeMap.put(DEPARTMENT_LEVEL_TOWN_FUNCTION, ORG_TYPE_FUNCTIONAL);
		orgTypeMap.put(DEPARTMENT_LEVEL_DISTRICT, ORG_TYPE_ADMINISTRATIVE);

	}

	public static String getOrgLevelDisplayName(String name) {
		return orgLevelMap.get(name);
	}

	public static String getOrgTypeDisplayName(String name) {
		return orgTypeMap.get(name);
	}
}
