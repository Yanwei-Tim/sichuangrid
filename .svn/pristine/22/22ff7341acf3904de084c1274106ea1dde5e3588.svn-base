package com.tianque.plugin.analysisNew.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryType {
	public static final int LEVEL_DEAL = 1;// 各层级办理
	public static final int AREA_DEAL = 2;// 各区域办理
	public static final int LEVEL_CLASSIFICATION = 3;// 各层级分类
	public static final int AREA_CLASSIFICATION = 4;// 各区域分类
	public static final int STEP = 5;// 按流程统计
	public static final int MONTH_ON_MONTH = 6;// 环比
	public static final int YEAR_ON_YEAR = 7;// 同比
	public static final int AREA_DEAL_HISTORY = 8;// 办理历史
	public static final int AREA_CLASSIFICATION_HISTORY = 9;// 类型历史
	public static final int STEP_HISTORY = 10;// 流程历史

	public static final Map<Object, String> STATMENT = new HashMap<Object, String>();
	public static final List<Integer> HISTORY = new ArrayList<Integer>();
	public static final List<Integer> SCALE = new ArrayList<Integer>();
	static {
		STATMENT.put(LEVEL_DEAL, "getIssueLevelStatsByYearAndMonthAndOrgCode");
		STATMENT.put(AREA_DEAL, "getIssueAreaStatsByYearAndMonthAndOrgCode");
		STATMENT.put(LEVEL_CLASSIFICATION,
				"getIssueLevelTypeStatsByYearAndMonthAndOrgCode");
		STATMENT.put(AREA_CLASSIFICATION,
				"getIssueAreaTypeStatsByYearAndMonthAndOrgCode");
		STATMENT.put(STEP, "getIssueStepStatsByYearAndMonthAndOrgCode");
		STATMENT.put(MONTH_ON_MONTH, "getIssueMomStatsByYearAndMonthAndOrgCode");
		STATMENT.put(YEAR_ON_YEAR, "getIssueYoyStatsByYearAndMonthAndOrgCode");
		STATMENT.put(AREA_DEAL_HISTORY,
				"getIssueAreaStatsByYearAndMonthAndOrgId");
		STATMENT.put(AREA_CLASSIFICATION_HISTORY,
				"getIssueAreaTypeStatsByYearAndMonthAndOrgId");
		STATMENT.put(STEP_HISTORY, "getIssueStepStatsByYearAndMonthAndOrgId");

		SCALE.add(LEVEL_DEAL);
		SCALE.add(AREA_DEAL);
		SCALE.add(LEVEL_CLASSIFICATION);
		SCALE.add(AREA_CLASSIFICATION);

		HISTORY.add(AREA_DEAL_HISTORY);
		HISTORY.add(AREA_CLASSIFICATION_HISTORY);
		HISTORY.add(STEP_HISTORY);
	}
}
