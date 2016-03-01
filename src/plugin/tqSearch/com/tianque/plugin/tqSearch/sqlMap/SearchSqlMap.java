package com.tianque.plugin.tqSearch.sqlMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tianque.core.util.StringUtil;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.util.GisCountViewCacheKey;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.openLayersMap.util.GisType;
import com.tianque.plugin.tqSearch.constants.TqSearchType;
import com.tianque.plugin.tqSearch.constants.TqSearchUtil;
import com.tianque.plugin.tqSearch.domain.TqSearchVo;

public class SearchSqlMap extends SearchResultMap{
	private static Map<String,String> personalType = new HashMap<String, String>();
	private static Map<String,String> keyPersonalType = new HashMap<String, String>();
	static{
		personalType.put("HOUSEHOLDSTAFFS","householdStaff");
		personalType.put("FLOATINGPOPULATIONS","floatingPopulation");
		personalType.put( "UNSETTLEDPOPULATIONS","unsettledPopulation");
		personalType.put("OVERSEAPERSONNEL","overseaStaff");
		keyPersonalType.putAll(GisCountViewCacheKey.allBusinessPersonMap);
	}
	
	public static TqSearchVo getSearchVo(SolrSqlMap sqlMap,Map<String, Object> parameters){
		Map<String, Object> parameterMap = formatParameters(sqlMap,parameters);
		TqSearchVo searchVo = new TqSearchVo();
		searchVo.setType(sqlMap.getSearchType());
		searchVo.setSql(formatScreenSQL(parameterMap));
		searchVo.setKeyTableType((String)parameterMap.remove("tableType"));
		if(parameterMap.get("startRow")!=null){
			parameterMap.remove("pageSize");
			parameterMap.remove("pageNum");
			int startRow= (Integer) parameterMap.remove("startRow");
			int endRow =  (Integer) parameterMap.remove("endRow");
			int pageSize = endRow-startRow;
			searchVo.setPage(endRow/pageSize);
			searchVo.setRows(pageSize);
		}
		if(parameterMap.get("sortField")!=null){
			String order = (String) parameterMap.remove("order");
			String sortField = (String) parameterMap.remove("sortField");
			searchVo.getSortFields().put(sortField, order);
		}
		if(parameterMap.get("searchInfoVo")!=null){
			Map<String,Object> map = TqSearchUtil.toMap(parameterMap.remove("searchInfoVo"));
			map.remove("gisType");
			parameterMap.putAll(map);
		}
		if(parameterMap.get("searchValue")!=null){
			searchVo.setSearchFields(sqlMap.getSearchFields());
			searchVo.setSearchText((String) parameterMap.remove("searchValue"));
		}
		if(parameterMap.get("circumInfoVo")!=null){
			parameterMap.remove("PI");
			parameterMap.remove("R");
			CircumInfoVo circumInfoVo = (CircumInfoVo) parameterMap.remove("circumInfoVo");
			parameterMap.put("lon",circumInfoVo.getLon());
			parameterMap.put("lat",circumInfoVo.getLat());
			parameterMap.put("range",circumInfoVo.getRange());
			parameterMap.put("gisType",circumInfoVo.getGisType());
		}
		parameterMap.remove("shardCode");
		searchVo.setSearchs(parameterMap);
		return searchVo;
	}
	
	private static Map<String, Object> formatParameters(SolrSqlMap sqlMap,Map<String, Object> parameters){
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.putAll(parameters);
		StringBuffer tableTypeSb = new StringBuffer();
		if(TqSearchType.PLACE_KEY.equals(sqlMap.getSearchType())){
			formatPlaceSearchVo(parameterMap);
		}else if(TqSearchType.POPULATION_KEY.equals(sqlMap.getSearchType())){
			formatPopulationSearchVo(parameterMap,tableTypeSb);
		}else if(TqSearchType.ISSUE_KEY.equals(sqlMap.getSearchType())){
			formatIssueSearchVo(parameterMap,tableTypeSb);
		}else if(TqSearchType.PUBLICSECURITY_KEY.equals(sqlMap.getSearchType())){
			formatPublicSecuritySearchVo(parameterMap,tableTypeSb);
		}else if(TqSearchType.DUSTBIN_KEY.equals(sqlMap.getSearchType())){
			formatDustbinSearchVo(parameterMap);
		}
		parameterMap.put("tableType", tableTypeSb.toString());
		if(parameterMap.get("sortField")!=null){
			String sortField = (String)parameterMap.get("sortField");
			if("centerlon".equalsIgnoreCase(sortField)){
				sortField = "lon";
			}else if("centerlon2".equalsIgnoreCase(sortField)){
				sortField = "lon2";
			}
			parameterMap.put("sortField", sortField);
		}
		if(parameterMap.containsKey("orgInternalCode")){
			parameterMap.put("orgInternalCode", (String)parameterMap.get("orgInternalCode")+"*");
		}
		return parameterMap;
	}
	
	private static String formatScreenSQL(Map<String, Object> parameters){
		StringBuffer screenSQL = new StringBuffer();
		if(parameters.get("screenCoordinateVo")!=null){
			ScreenCoordinateVo screenVo = (ScreenCoordinateVo) parameters.remove("screenCoordinateVo");
			if(screenVo!=null && parameters.get("searchInfoVo")!=null){
				SearchInfoVo searchInfoVo = (SearchInfoVo) parameters.get("searchInfoVo");
				String gisType = searchInfoVo.getGisType();
				if(GisType.M3D.equals(gisType)){
					screenSQL.append("lon:[").append(screenVo.getMinLon()).append(" TO ").append(screenVo.getMaxLon()).append("]");
					screenSQL.append(" AND ");
					screenSQL.append("lat:[").append(screenVo.getMinLat()).append(" TO ").append(screenVo.getMaxLat()).append("]");
				}else{
					screenSQL.append("lon2:[").append(screenVo.getMinLon()).append(" TO ").append(screenVo.getMaxLon()).append("]");
					screenSQL.append(" AND ");
					screenSQL.append("lat2:[").append(screenVo.getMinLat()).append(" TO ").append(screenVo.getMaxLat()).append("]");
				}
			}
		}
		Object isBound = parameters.remove("isBound");//是否只显示绑定过的数据
		if(isBound!=null){
			if("false".equals(isBound)){
				screenSQL = new StringBuffer((screenSQL.length()>0)?"("+screenSQL.toString()+")":"");
				screenSQL.append((screenSQL.length()>0)?" OR ":"");
				screenSQL.append("lon:0");
			}else if("true".equals(isBound) && screenSQL.length()==0){
				screenSQL.append("lon:{0 TO *}");
			}
		}
		return screenSQL.toString();
	}
	
	private static void formatDustbinSearchVo(Map parameterMap){
		if(parameterMap.containsKey("circumInfoVo")){
			CircumInfoVo circumInfoVo = (CircumInfoVo) parameterMap.get("circumInfoVo");
			String[] elements = circumInfoVo.getElements().split(",");
			StringBuffer partName = new StringBuffer();
			for (int i = 0; i < elements.length; i++) {
				String type = (String) elements[i].subSequence(1, elements[i].length()-1);
				if("-1".equals(type)){
					continue;
				}
				partName.append((partName.length()>0)?" OR partName:":"").append(type);
			}
			parameterMap.put("partName", partName.toString());
		}
	}
	private static void formatPublicSecuritySearchVo(Map parameterMap,StringBuffer tableTypeSb){
		String tableType = (String) parameterMap.remove("typeName");
		parameterMap.put("moduleType", tableType);
		parameterMap.remove("publicList");
		if(parameterMap.containsKey("circumInfoVo")){
			parameterMap.remove("typeList");
			CircumInfoVo circumInfoVo = (CircumInfoVo) parameterMap.get("circumInfoVo");
			String[] elements = circumInfoVo.getElements().split(",");
			StringBuffer moduleType = new StringBuffer();
			for (int i = 0; i < elements.length; i++) {
				String type = (String) elements[i].subSequence(1, elements[i].length()-1);
				moduleType.append((moduleType.length()>0)?" OR moduleType:":"").append(type);
			}
			parameterMap.put("moduleType", moduleType.toString());
		}
		tableTypeSb.append(tableType);
	}
	private static void formatIssueSearchVo(Map parameterMap,StringBuffer tableTypeSb){
		Object tableType = parameterMap.get("tableType");
		Object orgId = parameterMap.remove("orgId");
		Object orgInternalCode = parameterMap.remove("orgInternalCode");
		parameterMap.remove("dealState");
		parameterMap.remove("dealStateList");
		parameterMap.put("historic", 0);
		parameterMap.remove("targeOrgInternalCode");
		parameterMap.remove("targeOrgId");
		if("issues".equals(tableType)){
			parameterMap.put("targetOrgInternalCode", orgInternalCode);
		}else if("jurisdictionsIssue".equals(tableType)){
			parameterMap.put("targetOrgInternalCode", orgInternalCode+"*");
			parameterMap.put("stateCode", "{* TO "+500+"}");
		}else if (GisGlobalValueMap.PERSONFORTHING.equals(tableType)) {// 我的待办
			parameterMap.put("targetOrgId", orgId);
			parameterMap.put("stateCode", "{* TO "+500+"}");
		} else if (GisGlobalValueMap.PERSONALREADYTHING.equals(tableType)) {// 我的已办
			parameterMap.put("targetOrgId", orgId);
			parameterMap.put("stateCode", "["+500+" TO *]");
		} else if (GisGlobalValueMap.GONETHROUGH.equals(tableType)) {// 我的已办结
			parameterMap.put("createOrgInternalCode", orgInternalCode);
			parameterMap.put("stateCode", "["+1000+" TO *]");
		} else if (GisGlobalValueMap.FORTHING.equals(tableType)) {// 下辖待办
			parameterMap.put("targetOrgInternalCode", orgInternalCode+"*");
			parameterMap.put("stateCode", "{* TO "+500+"}");
		} else if (GisGlobalValueMap.ALREADYTHING.equals(tableType)) {// 下辖已办结
			parameterMap.put("createOrgInternalCode", orgInternalCode+"*");
			parameterMap.put("stateCode", "["+1000+" TO *]");
		}
		tableTypeSb.append(tableType);
	}
	
	private static void formatPopulationSearchVo(Map parameterMap,StringBuffer tableTypeSb){
		List<GisTypeManage> personList = (List<GisTypeManage>) parameterMap.remove("personList");
		List<Object> tableNames = new ArrayList<Object>();
		if(personList !=null && personList.size()>0){
			for (GisTypeManage gisType : personList) {
				tableNames.add(gisType.getTableName());
			}
		}
		tableNames.add(parameterMap.remove("typeName"));
		tableNames.add(parameterMap.remove("tableName"));
		parameterMap.remove("personType");
		parameterMap.remove("personTypeName");
		StringBuffer dataTypes = new StringBuffer();
		StringBuffer populationTypes = new StringBuffer();
		
		if(parameterMap.containsKey("circumInfoVo")){
			CircumInfoVo circumInfoVo = (CircumInfoVo) parameterMap.get("circumInfoVo");
			String[] elements = circumInfoVo.getElements().split(",");
			for (int i = 0; i < elements.length; i++) {
				String type = (String) elements[i].subSequence(1, elements[i].length()-1);
				for (Iterator<Entry<String, String>> it=keyPersonalType.entrySet().iterator();it.hasNext();) {
					Entry<String, String> entry = it.next();
					if(entry.getValue().equalsIgnoreCase(type)){
						populationTypes.append((populationTypes.length()>0)?" OR populationTypes:":"").append("*"+entry.getValue()+"*");
						tableTypeSb.append(tableTypeSb.length()>0?",":"").append(entry.getValue());
					}
				}
			}
		}
		for (Object tableName : tableNames) {
			if(StringUtil.isStringAvaliable(tableName+"") && personalType.containsKey(tableName)){
				dataTypes.append((dataTypes.length()>0)?" OR dataType:":"").append(personalType.get(tableName));
				tableTypeSb.append(tableTypeSb.length()>0?",":"").append(personalType.get(tableName));
			}else if(StringUtil.isStringAvaliable(tableName+"") && keyPersonalType.containsKey(tableName)){
				populationTypes.append((populationTypes.length()>0)?" OR populationTypes:":"").append("*"+keyPersonalType.get(tableName)+"*");
				tableTypeSb.append(tableTypeSb.length()>0?",":"").append(keyPersonalType.get(tableName));
			}
		}
		parameterMap.put("dataType", dataTypes.toString());
		parameterMap.put("populationTypes", populationTypes.toString());
	}
	
	private static void formatPlaceSearchVo(Map parameterMap){
		StringBuffer classificationen = new StringBuffer();
		if(parameterMap.containsKey("circumInfoVo")){
			List<Object> list = new ArrayList<Object>(); 
			CircumInfoVo circumInfoVo = (CircumInfoVo) parameterMap.get("circumInfoVo");
			String[] elements = circumInfoVo.getElements().split(",");
			for (int i = 0; i < elements.length; i++) {
				list.add(elements[i].subSequence(1, elements[i].length()-1));
			}
			parameterMap.put("typeList", list);
		}
		if(parameterMap.containsKey("typeList")){
			List<Object> typeList = (List)parameterMap.remove("typeList");
			for (Object type: typeList) {
				if(classificationen.length()>0){
					classificationen.append(" OR classificationen:");
				}
				classificationen.append(type);
			}
		}else if(parameterMap.get("typeName")!=null){
			classificationen.append(parameterMap.remove("typeName"));
		}else if(parameterMap.get("type")!=null){
			classificationen.append(parameterMap.remove("type"));
		}
		parameterMap.put("classificationen", classificationen.toString());
	}
}
