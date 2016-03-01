package com.tianque.plugin.tqSearch.constants;

import java.util.HashMap;
import java.util.Map;

import com.tianque.core.util.StringUtil;

public enum RelationShipConfig {
	HOUSE_SHIP_POPULATION("housePopulation","houseId:{keyId}",false),
	HOUSE_SHIP_BUILDING("houseBuilding","keyId:{builddatasId}",false),
	HOUSE_SHIP_SERVICERECORD("houseServiceRecord","objectId:{rentalhouseId} AND objectType:RENTALHOUSE",true),
	HOUSE_SHIP_PLACE("housePlace","address:{address}",true),
	HOUSE_SHIP_DUSTBIN("houseDustbin","address:{address}",true),
	HOUSE_SHIP_ISSUE("houseIssue","occurLocation:{address}",true),
	
	ISSUE_SHIP_POPULATION("issuePopulation","[idCardNo_Org:{idCardNos}]",true),
	ISSUE_SHIP_HOUSE("issueHouse","address:{occurLocation}",true),
	ISSUE_SHIP_ISSUEEVALUATE("issueEvaluate","issueId:{keyId}",false),
	
	POPULATION_SHIP_HOUSEHOLDFAMILY("populationHouseholdFamily","keyId:{familyId}",false),
	POPULATION_SHIP_HOUSE("populationHouse","keyId:{houseId}",false),
	POPULATION_SHIP_BUILDING("populationBuilding","keyId:{builddatasId}",false),
	POPULATION_SHIP_SERVICERECORD("populationServiceRecord","[serviceRecord:{populationTypes}]",true),
	POPULATION_SHIP_ISSUE("populationIssue","[populationType_Id:{populationTypes}]",true),
	POPULATION_SHIP_PRIMARYMEMBER("populationPrimaryMember","idCardNo:{idCardNo}",true),
	POPULATION_SHIP_PARTYMEMBER("populationPartyMember","idCardNo:{idCardNo}",false),
	POPULATION_SHIP_ACCOUNT("populationAccount","idCardNo:*{idCardNo}*",true);
	
	private static Map<String,String> titleMap = new HashMap<String, String>();
	static{
		titleMap.put(TqSearchType.HOUSE_KEY, "address");
		titleMap.put(TqSearchType.ISSUE_KEY, "subject");
		titleMap.put(null, "name");
	}
	
	private String key;
	/**
	 * 当需要替换key:value值时，使用中括号，例如 [serviceRecord:{populationTypes}]
	 * 当需要替换value值时，使用大括号，例如 serviceRecord:{populationTypes}
	 */
	private String sql;
	/**
	 * 查看关联关系的详情信息时，是否以列表的形式显示
	 */
	private boolean isList;

	private RelationShipConfig(String key, String sql, boolean isList) {
		this.key = key;
		this.sql = sql;
		this.isList = isList;
	}
	
	public static RelationShipConfig getByKey(String key){
		RelationShipConfig[] relationShips = RelationShipConfig.values();
		for (int i = 0; i < relationShips.length; i++) {
			if(relationShips[i].getKey().equals(key)){
				return relationShips[i];
			}
		}
		return null;
	}
	
	public static String getSqlByKey(String key,Map<String, Object> domain){
		RelationShipConfig relationShip = getByKey(key);
		if(relationShip!=null){
			String sql = relationShip.getSql();
			while( sql.indexOf("{")>-1){
				int start = sql.indexOf("{");
				int end = sql.indexOf("}");
				String fieldName = sql.substring(start+1, end);
				Object fieldValue = domain.get(fieldName);
				if(fieldValue==null){
					return null;
				}
				sql = sql.replace("{"+fieldName+"}", String.valueOf(fieldValue));
			}
			while( sql.indexOf("[")>-1){
				int start = sql.indexOf("[");
				int end = sql.indexOf("]");
				String subSql = sql.substring(start+1, end);
				String[] nameAndValue = 	subSql.split(":");
				String field = nameAndValue[0].trim();
				String value = nameAndValue[1].trim();
				String[] searchs =value.split(",");
				StringBuffer sb = new StringBuffer();
				for (String search : searchs) {
					sb.append((sb.length() > 0) ? " OR " : "");
					String[] typeAndId = search.split("-");
					if ("serviceRecord".equals(field)) {
						sb.append("(objectType:" + typeAndId[0] + " AND objectId:" + typeAndId[1].split("@")[0] + ")");
					} else if ("idCardNo_Org".equals(field)) {
						sb.append("(populationTypes:*" + search+ "*)");
					}else if("populationType_Id".equals(field)){
						sb.append("(idCardNos:*" + search+ "*)");
					}
				}
				sql = sql.replace("["+subSql+"]", String.valueOf(sb.toString()));
			}
			return sql;
		}
		return null;
	}
	
	public static String getTitleByKey(String key,Map<String, Object> domain){
		String field = titleMap.get(key);
		if(!StringUtil.isStringAvaliable(field)){
			field = titleMap.get(null);
		}
		return String.valueOf(domain.get(field));
	}
	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public boolean isList() {
		return isList;
	}

	public void setList(boolean isList) {
		this.isList = isList;
	}

}
