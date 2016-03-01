package com.tianque.plugin.tqSearch.sqlMap;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.openLayersMap.domian.Point;
import com.tianque.openLayersMap.util.GisType;
import com.tianque.plugin.tqSearch.constants.TqSearchType;
import com.tianque.plugin.tqSearch.constants.TqSearchUtil;
import com.tianque.plugin.tqSearch.domain.TqSearchVo;

public class SearchResultMap {
	public static Map<String,String> buildDataInfoVoResult = new HashMap<String,String>();
	static{
		buildDataInfoVoResult.put("id","keyId");
		buildDataInfoVoResult.put("name","buildingName");
		buildDataInfoVoResult.put("address","address");
		buildDataInfoVoResult.put("type.id","type");
		buildDataInfoVoResult.put("typeId","type");
		buildDataInfoVoResult.put("lon","centerLon");
		buildDataInfoVoResult.put("lat","centerLat");
		buildDataInfoVoResult.put("lon2","centerLon2");
		buildDataInfoVoResult.put("lat2","centerLat2");
		buildDataInfoVoResult.put("isLayout","isLayout");
	}
	public static Map<String,String> housePropertyInfoVoResult = new HashMap<String, String>();
	static{
		housePropertyInfoVoResult.put("id", "keyId");
		housePropertyInfoVoResult.put("houseName", "houseOwner");
		housePropertyInfoVoResult.put("address", "address");
		housePropertyInfoVoResult.put("lon","centerLon");
		housePropertyInfoVoResult.put("lat","centerLat");
		housePropertyInfoVoResult.put("lon2","centerLon2");
		housePropertyInfoVoResult.put("lat2","centerLat2");
		housePropertyInfoVoResult.put("builddatasId","builddatasId");
		housePropertyInfoVoResult.put("hiddenDangerLevel","hiddenDangerLevel");
		housePropertyInfoVoResult.put("name","name");
		housePropertyInfoVoResult.put("buildingName","buildingName");
		housePropertyInfoVoResult.put("isRentalHouse","isRentalHouse");
		housePropertyInfoVoResult.put("rentalPerson","rentalPerson");
		housePropertyInfoVoResult.put("houseCode","houseCode");
		housePropertyInfoVoResult.put("orgInternalCode","orgInternalCode");
	}
	public static Map<String,String> personInfoVoResult = new HashMap<String, String>();
	static{
		personInfoVoResult.put("id", "keyId");
		personInfoVoResult.put("name", "name");
		personInfoVoResult.put("idCardNo", "idCardNo");
		personInfoVoResult.put("address", "currentAddress");
		personInfoVoResult.put("genderId.id", "gender");
		personInfoVoResult.put("lon","centerLon");
		personInfoVoResult.put("lat","centerLat");
		personInfoVoResult.put("lon2","centerLon2");
		personInfoVoResult.put("lat2","centerLat2");
		personInfoVoResult.put("orgInternalCode", "orgInternalCode");
		personInfoVoResult.put("certificateTypeId.id", "certificateType");
		personInfoVoResult.put("englishName", "englishName");
		personInfoVoResult.put("personTypeName", "personTypeName");
		personInfoVoResult.put("personType", "dataType");
	}
	public static Map<String,String> keyPersonInfoVoResult = new HashMap<String, String>();
	static{
		keyPersonInfoVoResult.put("id", "populationTypes");
		keyPersonInfoVoResult.put("name", "name");
		keyPersonInfoVoResult.put("idCardNo", "idCardNo");
		keyPersonInfoVoResult.put("gender.id", "gender");
		keyPersonInfoVoResult.put("genderName", "genderName");
		keyPersonInfoVoResult.put("houseId", "houseId");
		keyPersonInfoVoResult.put("address", "address");
		keyPersonInfoVoResult.put("lon","centerLon");
		keyPersonInfoVoResult.put("lat","centerLat");
		keyPersonInfoVoResult.put("lon2","centerLon2");
		keyPersonInfoVoResult.put("lat2","centerLat2");
		keyPersonInfoVoResult.put("orgInternalCode", "orgInternalCode");
		keyPersonInfoVoResult.put("distance", "distance");
		keyPersonInfoVoResult.put("type", "type");
		keyPersonInfoVoResult.put("typeName", "typeName");
	}
	public static Map<String,String> issueInfoVoResult = new HashMap<String, String>();
	static{
		issueInfoVoResult.put("issueId", "keyId");
		issueInfoVoResult.put("serialNumber", "serialNumber");
		issueInfoVoResult.put("subject", "subject");
		issueInfoVoResult.put("lon","centerLon");
		issueInfoVoResult.put("lat","centerLat");
		issueInfoVoResult.put("lon2","centerLon2");
		issueInfoVoResult.put("lat2","centerLat2");
		issueInfoVoResult.put("issueType", "issueTypeName");
		issueInfoVoResult.put("organization.id", "occurOrgId");
		issueInfoVoResult.put("occurLocation", "occurLocation");
		issueInfoVoResult.put("status", "status");
		issueInfoVoResult.put("issueLogId", "issueLogId");
		issueInfoVoResult.put("type", "type");
	}
	
	public static Map<String,String> keyPlaceInfoVoResult = new HashMap<String, String>();
	static{
		keyPlaceInfoVoResult.put("id", "keyId");
		keyPlaceInfoVoResult.put("type", "classificationen");
		keyPlaceInfoVoResult.put("name", "name");
		keyPlaceInfoVoResult.put("address", "address");
		keyPlaceInfoVoResult.put("lon","centerLon");
		keyPlaceInfoVoResult.put("lat","centerLat");
		keyPlaceInfoVoResult.put("lon2","centerLon2");
		keyPlaceInfoVoResult.put("lat2","centerLat2");
		keyPlaceInfoVoResult.put("buildDataId", "buildDataId");
		keyPlaceInfoVoResult.put("organization.id", "orgId");
		keyPlaceInfoVoResult.put("orgInternalCode", "orgInternalCode");
		keyPlaceInfoVoResult.put("distance", "distance");
		keyPlaceInfoVoResult.put("typeName", "typeName");
	}
	public static Map<String,String> cityComponentsInfoVoResult = new HashMap<String, String>();
	static{
		cityComponentsInfoVoResult.put("id", "keyId");
		cityComponentsInfoVoResult.put("lon","centerLon");
		cityComponentsInfoVoResult.put("lat","centerLat");
		cityComponentsInfoVoResult.put("lon2","centerLon2");
		cityComponentsInfoVoResult.put("lat2","centerLat2");
		cityComponentsInfoVoResult.put("organization.id", "orgId");
		cityComponentsInfoVoResult.put("orgInternalCode", "orgInternalCode");
		cityComponentsInfoVoResult.put("partType.id", "partType");
		cityComponentsInfoVoResult.put("partName.id", "partName");
		cityComponentsInfoVoResult.put("dustbinCode", "dustbinCode");
		cityComponentsInfoVoResult.put("deptName", "deptName");
		cityComponentsInfoVoResult.put("ownershipUnitName", "ownershipUnitName");
		cityComponentsInfoVoResult.put("maintenanceUnitName", "maintenanceUnitName");
		cityComponentsInfoVoResult.put("address", "address");
		cityComponentsInfoVoResult.put("imgUrl", "imgUrl");
		cityComponentsInfoVoResult.put("distance", "distance");
	}
	public static Map<String,String> publicSecurityInfoVoResult = new HashMap<String, String>();
	static{
		publicSecurityInfoVoResult.put("id", "keyId");
		publicSecurityInfoVoResult.put("lon","centerLon");
		publicSecurityInfoVoResult.put("lat","centerLat");
		publicSecurityInfoVoResult.put("lon2","centerLon2");
		publicSecurityInfoVoResult.put("lat2","centerLat2");
		publicSecurityInfoVoResult.put("code", "code");
		publicSecurityInfoVoResult.put("address", "address");
		publicSecurityInfoVoResult.put("type", "moduleType");
		publicSecurityInfoVoResult.put("organization.id", "orgId");
		publicSecurityInfoVoResult.put("distance", "distance");
	}
	public static Map<String,String> hourseInfoResult = new HashMap<String, String>();
	static{
		hourseInfoResult.put("id", "keyId");
		hourseInfoResult.put("lon","centerLon");
		hourseInfoResult.put("lat","centerLat");
		hourseInfoResult.put("lon2","centerLon2");
		hourseInfoResult.put("lat2","centerLat2");
		hourseInfoResult.put("organization.id", "orgId");
		hourseInfoResult.put("orgInternalCode", "orgInternalCode");
	}
	
	public static List<Object> toResult(Class beanClazz,Map map,Map<String,String> formatMap, TqSearchVo searchVo){
		List<Object> resultList = new ArrayList<Object>();
		List<Map<String,Object>> list = formatResult(beanClazz,map,formatMap, searchVo);
		for (Map<String,Object> resultMap : list) {
			resultList.add(TqSearchUtil.toBean(beanClazz, resultMap));
		}
		return resultList;
	}

	private static List<Map<String,Object>> formatResult(Class clazz,Map dataMap,Map<String,String> formatMap, TqSearchVo searchVo){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String,Object> result = new HashMap<String, Object>();
		result.putAll(dataMap);
		if(formatMap==null){
			list.add(dataMap);
			return list;
		}
		for (Iterator<Entry<String, String>>  it = formatMap.entrySet().iterator();it.hasNext(); ) {
			Entry<String, String> entry = it.next();
			String formatMapKey = entry.getKey();
			String dataMapKey = entry.getValue();
			Object value = dataMap.get(dataMapKey);
			if(formatMapKey.equals("distance") && searchVo.getSearchs().containsKey("range")){
				String gisType = (String) searchVo.getSearchs().get("gisType");
				Point point1 = new Point(searchVo.getSearchs().get("lon"), searchVo.getSearchs().get("lat"));
				Point point2 = null;
				if(GisType.M3D.equals(gisType)){
					point2 = new Point(result.get("lon"), result.get("lat"));
				}else{
					point2 = new Point(result.get("lon2"), result.get("lat2"));
				}
				double distance = point1.distanceTo(point2);
				DecimalFormat fmt=new DecimalFormat("0.##");
				result.put("distance", fmt.format(distance));
			}
			if(value==null){
				continue;
			}
			result.put(formatMapKey, value);
			if(formatMapKey.endsWith(".id") && dataMapKey.trim().equalsIgnoreCase("orgId")){
				Organization org= new Organization();
				org.setId(Long.parseLong(value+""));
				result.put(formatMapKey.split("\\.")[0], org);
			}else if(formatMapKey.endsWith(".id")){
				PropertyDict propertyDict = new PropertyDict();
				propertyDict.setId(Long.parseLong(value+""));
				result.put(formatMapKey.split("\\.")[0], propertyDict);
			}
			if(TqSearchType.POPULATION_KEY.equals(searchVo.getType())){
				formatPopulationResultMap(result, searchVo,formatMapKey,dataMapKey);
			}else if(TqSearchType.PLACE_KEY.equals(searchVo.getType())){
				formatKeyPlaceResultMap(result, searchVo,formatMapKey,dataMapKey);
			}else if(TqSearchType.ISSUE_KEY.equals(searchVo.getType())){
				if(formatMapKey.endsWith(".id") && dataMapKey.trim().equalsIgnoreCase("occurOrgId")){
					Organization org= new Organization();
					org.setId(Long.parseLong(value+""));
					result.put(formatMapKey.split("\\.")[0], org);
				}
			}
		}
		if(result.get("lon")!=null){
			Object lon = result.get("lon");
			Object lat = result.get("lat");
			Object lon2 = result.get("lon2");
			Object lat2 = result.get("lat2");
			result.put("lon", ("0".equals(lon.toString()))?null:lon);
			result.put("lat", ("0".equals(lat.toString()))?null:lat);
			result.put("lon2", ("0".equals(lon2.toString()))?null:lon2);
			result.put("lat2", ("0".equals(lat2.toString()))?null:lat2);
		}
		List<String[]> typeIds = (List<String[]>) result.get("typeIds");
		if(typeIds!=null && typeIds.size()>0){
			for (String[] type_id : typeIds) {
				Map<String,Object> typeResult = new HashMap<String, Object>();
				String type =  (type_id.length==2)?type_id[0]:"";
				result.put("id", (type_id.length==2)?type_id[1].split("@")[0]:"");
				result.put("type",type);
				result.put("typeName", BaseInfoTables.getTypeDisplayNames(type));
				typeResult.putAll(result);
				list.add(typeResult);
			}
		}else{
			list.add(result);
		}
		return list;
	}
	private static void formatKeyPlaceResultMap(Map result, TqSearchVo searchVo,String formatMapKey,String dataMapKey){
		Object value = result.get(dataMapKey);
		if(formatMapKey.equals("type") && value!=null){
			String type = value+"";
			String typeName = BaseInfoTables.getTypeDisplayNames(type);
			if(type.startsWith("NEW") && typeName==null){
				typeName = BaseInfoTables.getTypeDisplayNames(type.substring(3));
			}
			result.put("typeName",typeName);
		}
	}
	private static void formatPopulationResultMap(Map result, TqSearchVo searchVo,String formatMapKey,String dataMapKey){
		Object value = result.get(dataMapKey);
		if(formatMapKey.equals("id") && dataMapKey.equals("populationTypes") && value!=null){
			List<String[]> typeIds = new ArrayList<String[]>();
			String[] tableTypes = searchVo.getKeyTableType().split(",");
			String[] populationTypes = (value+"").split(",");
			for (int k = 0; k < tableTypes.length; k++) {
				for (int i = 0; i < populationTypes.length; i++) {
					if(populationTypes[i].startsWith(tableTypes[k])){
						String[] type_id = populationTypes[i].split("-");
						result.put("id", (type_id.length==2)?type_id[1].split("@")[0]:"");
						typeIds.add(type_id);
						break;
					}
				}
			}
			result.put("typeIds", typeIds);
		}else if(formatMapKey.equals("personType") && value!=null){
			result.put("personTypeName",BaseInfoTables.getTypeDisplayNames(value+""));
		}
	}
}
