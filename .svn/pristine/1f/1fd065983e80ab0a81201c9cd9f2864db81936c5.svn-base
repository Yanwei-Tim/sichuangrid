package com.tianque.gis.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tianque.issue.constants.IssueTypes;
import com.tianque.issue.state.IssueState;

public class GisCountTypeMapUtil {
	private static Map<String, String> gisCountMap = new LinkedHashMap<String, String>();
	private static Map<Integer, String> gisCountStateMap = new HashMap<Integer, String>();
	private static Map<String, Object> gisIssueFildsMap = new HashMap<String, Object>();

	public static String DEALING = "未完成";
	public static String ISSUECOMPLETE = "已完成";
	public static String UNCOMPLETEISSUE = "uncompleteIssue";
	public static String COMPLETEISSUE = "completeIssue";
	// 三类人口总称
	public static String TYPEDOMAINNAME = "typeDomainName";
	public static String PEOPLELIVEISSUE = "peopleliveIssue";
	public static String CONTRADICTIONISSUE = "contradictionIssue";
	public static String SECURITYISSUE = "securityIssue";

	public static String IMPORTANT_ISSUES = "important";
	public static String ISEMERGENCY_ISSUES = "isemergency";

	public static String TOTALNUM = "总计";
	// 重大事件
	public static String IMPORTANT = "重大事件";
	// 紧急事件
	public static String ISEMERGENCY = "紧急事件";

	static {

		gisCountStateMap.put(IssueState.DEALING, DEALING);
		gisCountStateMap.put(IssueState.ISSUECOMPLETE_CODE, ISSUECOMPLETE);

		// 中文转英文
		gisCountMap.put(TOTALNUM, "issueNews"); // 状态
		gisCountMap.put(DEALING, UNCOMPLETEISSUE);
		gisCountMap.put(ISSUECOMPLETE, COMPLETEISSUE); // 治安分类
		gisCountMap.put(IssueTypes.PEOPLELIVE_SERVICE, PEOPLELIVEISSUE);
		gisCountMap.put(IssueTypes.SECURITYTROUBLE, SECURITYISSUE);
		gisCountMap.put(IssueTypes.CONTRADICTION, CONTRADICTIONISSUE);
		gisCountMap.put(IMPORTANT, IMPORTANT_ISSUES);
		gisCountMap.put(ISEMERGENCY, ISEMERGENCY_ISSUES);

		// 英文转中文
		gisIssueFildsMap.put("issueNews", null);
		gisIssueFildsMap.put(UNCOMPLETEISSUE, IssueState.DEALING);
		gisIssueFildsMap.put(COMPLETEISSUE, IssueState.ISSUECOMPLETE_CODE);
		gisIssueFildsMap.put(PEOPLELIVEISSUE, IssueTypes.PEOPLELIVE_SERVICE);
		gisIssueFildsMap.put(CONTRADICTIONISSUE, IssueTypes.CONTRADICTION);
		gisIssueFildsMap.put(SECURITYISSUE, IssueTypes.SECURITYTROUBLE);
		gisIssueFildsMap.put(IMPORTANT_ISSUES, IssueState.DEALING);
		gisIssueFildsMap.put(ISEMERGENCY_ISSUES, IssueState.DEALING);

	}

	public static String gisCountState(int typeCname) {
		return gisCountStateMap.get(typeCname);
	}

	/** 传入中文名字，转换为英文 */
	public static String getGisCountType(String typeCname) {
		return gisCountMap.get(typeCname);
	}

	public static Object getGisIssueNewFilds(String typeCname) {
		return gisIssueFildsMap.get(typeCname);
	}

}