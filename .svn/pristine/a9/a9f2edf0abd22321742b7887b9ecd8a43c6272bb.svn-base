package com.tianque.tqSearch.common;

import java.util.HashMap;
import java.util.Map;

import com.tianque.tqSearch.constant.TqSolrSearchType;

/**
 * 
 * @作者:龙振东
 * @功能:
 * @时间:2015-6-2 下午01:48:46
 * @邮箱:longzhendong@hztianque.com
 */
public class IndexFieldAndObjectFieldMapping {
	/**
	 * value:solr中schema定义的fields key:实体对象中的属性
	 */
	private static Map<String, String> issueMap = new HashMap<String, String>();
	/**
	 * 系统中所有模块的映射关系
	 */
	private static Map<String, Map> systemMap = new HashMap<String, Map>();

	static {
		issueMap.put("superviseLevel", "superviseLevel");
		issueMap.put("issueId", "issueId");
		issueMap.put("serialNumber", "serialNumber");
		issueMap.put("subject", "subject");
		issueMap.put("status", "status");
		issueMap.put("occurDate", "occurDate");
		issueMap.put("currentOrg.id", "currentOrg");
		issueMap.put("occurOrg.id", "occurOrg");
		issueMap.put("targeOrg.id", "targetOrg");
		issueMap.put("dealTime", "dealTime");
		issueMap.put("sourceKind.id", "sourceKind");
		issueMap.put("issueStepId", "issueStepId");
		issueMap.put("supervisionState", "superviseLevel");
		issueMap.put("dealState", "stateCode");
		issueMap.put("delayState", "delayState");
		issueMap.put("issueType.issueTypeDomain.id", "issueTypedDomainId");
		issueMap.put("issueType.id", "issueTypeId");
		issueMap.put("createOrg.id", "createOrg");
		issueMap.put("publicltycass", "publicltycass");
		issueMap.put("evaluateType", "evaluateType");
		issueMap.put("urgent", "urgent");
		issueMap.put("eventState", "eventState");
	}
	static {
		systemMap.put(TqSolrSearchType.ISSUE_TYPE, issueMap);
	}

	public static Map<String, String> getModeMap(String searchType) {
		return systemMap.get(searchType);
	}
}
