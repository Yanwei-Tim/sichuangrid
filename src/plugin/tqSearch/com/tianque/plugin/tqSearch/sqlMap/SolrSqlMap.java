package com.tianque.plugin.tqSearch.sqlMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.issue.domain.IssueNew;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.domian.vo.CityComponentsInfoVo;
import com.tianque.openLayersMap.domian.vo.HousePropertyInfoVo;
import com.tianque.openLayersMap.domian.vo.IssueInfoVo;
import com.tianque.openLayersMap.domian.vo.KeyPersonInfoVo;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.domian.vo.PersonInfoVo;
import com.tianque.openLayersMap.domian.vo.PublicSecurityInfoVo;
import com.tianque.plugin.tqSearch.constants.TqSearchType;
import com.tianque.plugin.tqSearch.constants.TqSearchUtil;
import com.tianque.plugin.tqSearch.domain.TqSearchVo;
import com.tianque.publicSecurity.domain.Bayonet;
import com.tianque.publicSecurity.domain.Skynet;
import com.tianque.publicSecurity.domain.SnapshotSystem;
import com.tianque.service.util.PopulationCatalog;

public enum SolrSqlMap {
	/**
	 * 查询SQL配置
	 */
	BUILDDATA("buildDataTwoDimensionMap",TqSearchType.BUILDING_KEY,"buildingName,address",BuildDataInfoVo.class,SearchSqlMap.buildDataInfoVoResult),
	HOURSEPROPERTY("hoursePropertyTwoDimensionMap",TqSearchType.HOUSE_KEY,"houseOwner,address,rentalPerson",HousePropertyInfoVo.class,SearchSqlMap.housePropertyInfoVoResult),
	PERSON("personTwoDimensionMap",TqSearchType.POPULATION_KEY,"name,idCardNo",PersonInfoVo.class,SearchSqlMap.personInfoVoResult),
	KEYPERSON("keyPersonTwoDimensionMap",TqSearchType.POPULATION_KEY,"name,idCardNo",KeyPersonInfoVo.class,SearchSqlMap.keyPersonInfoVoResult),
	ISSUE("issueTwoDimensionMap",TqSearchType.ISSUE_KEY,"subject,serialNumber",IssueInfoVo.class,SearchSqlMap.issueInfoVoResult),
	KEYPLACE("keyPlaceTwoDimensionMap",TqSearchType.PLACE_KEY,"name,address",KeyPlaceInfoVo.class,SearchSqlMap.keyPlaceInfoVoResult),
	CITYCOMPONENT("cityComponentsTwoDimensionMap",TqSearchType.DUSTBIN_KEY,"dustbinCode,address",CityComponentsInfoVo.class,SearchSqlMap.cityComponentsInfoVoResult),
	PUBLICSECURITY("publicSecurityTwoDimensionMap",TqSearchType.PUBLICSECURITY_KEY,"code,address",PublicSecurityInfoVo.class,SearchSqlMap.publicSecurityInfoVoResult),
	/**
	 * 修改SQL配置
	 */
	UPDATE_LONLAT(UpdateSqlMap.COMMON_BIND_KEY,null,"",Map.class,UpdateSqlMap.lonlatMap),
	
	UPDATE_DUSTBIN(UpdateSqlMap.DUSTBIN_KEY,TqSearchType.DUSTBIN_KEY,"",Map.class,UpdateSqlMap.dustbinIndexMap),
	UPDATE_SKYNET(UpdateSqlMap.SKYNET_KEY,TqSearchType.PUBLICSECURITY_KEY,"",Map.class,UpdateSqlMap.publicSecurityIndexMap),
	UPDATE_BAYONET(UpdateSqlMap.BAYONET_KEY,TqSearchType.PUBLICSECURITY_KEY,"",Map.class,UpdateSqlMap.publicSecurityIndexMap),
	UPDATE_SNAPSHOTSYSTEM(UpdateSqlMap.SNAPSHOTSYSTEM_KEY,TqSearchType.PUBLICSECURITY_KEY,"",Map.class,UpdateSqlMap.publicSecurityIndexMap),
	
	UPDATE_ISSUES(UpdateSqlMap.ISSUE_KEY,TqSearchType.ISSUE_KEY,"",Map.class,UpdateSqlMap.issueIndexMap),
	UPDATE_KEYPLACE(UpdateSqlMap.KEYPLACE_KEY,TqSearchType.PLACE_KEY,"",Map.class,UpdateSqlMap.keyPlaceIndexMap),
	UPDATE_BUILDDATA(UpdateSqlMap.BUILDING_KEY,TqSearchType.BUILDING_KEY,"",Map.class,UpdateSqlMap.builddataIndexMap),
	UPDATE_HOUSEINFO(UpdateSqlMap.HOUSEINFO_KEY,TqSearchType.HOUSE_KEY,"",Map.class,UpdateSqlMap.houseinfoIndexMap),
	
	UPDATE_HOUSEHOLDSTAFF(UpdateSqlMap.HOUSEHOLDSTAFF_KEY,TqSearchType.HOUSEHOLDSTAFF,"",Map.class,UpdateSqlMap.householdStaffIndexMap),
	UPDATE_FLOATINGPOPULATION(UpdateSqlMap.FLOATINGPOPULATION_KEY,TqSearchType.FLOATINGPOPULATION,"",Map.class,UpdateSqlMap.floatingPopulationIndexMap),
	UPDATE_UNSETTLEDPOPULATION(UpdateSqlMap.UNSETTLEDPOPULATION_KEY,TqSearchType.UNSETTLEDPOPULATION,"",Map.class,UpdateSqlMap.unsettledPopulationIndexMap),
	UPDATE_OVERSEASTAFF(UpdateSqlMap.OVERSEAPERSONNEL_KEY,TqSearchType.OVERSEAPERSONNEL,"",Map.class,UpdateSqlMap.overseaStaffIndexMap),
	
	UPDATE_POPULATION_TYPE(UpdateSqlMap.POPULATION_TYPE_KEY,TqSearchType.POPULATION_KEY,"",Map.class,UpdateSqlMap.populationTypeMap),
	/**
	 * 删除SQL配置
	 */
	DELETE_DUSTBIN(DeleteSqlMap.DUSTBIN_KEY,TqSearchType.DUSTBIN_KEY,"",Map.class,DeleteSqlMap.idFields),
	DELETE_SKYNET(DeleteSqlMap.SKYNET_KEY,TqSearchType.PUBLICSECURITY_KEY,"",Map.class,DeleteSqlMap.skynetIdFields),
	DELETE_BAYONET(DeleteSqlMap.BAYONET_KEY,TqSearchType.PUBLICSECURITY_KEY,"",Map.class,DeleteSqlMap.bayonetIdFields),
	DELETE_SNAPSHOTSYSTEM(DeleteSqlMap.SNAPSHOTSYSTEM_KEY,TqSearchType.PUBLICSECURITY_KEY,"",Map.class,DeleteSqlMap.snapshotSystemIdFields),
	
	DELETE_COMPANYPLACE(DeleteSqlMap.COMPANYPLACE_KEY,TqSearchType.PLACE_KEY,"",Map.class,DeleteSqlMap.companyPlaceIdFields),
	DELETE_SCENICSPOT(DeleteSqlMap.SCENICSPOT_KEY,TqSearchType.PLACE_KEY,"",Map.class,DeleteSqlMap.scenicspotIdFields),
	DELETE_SCENICEQUIPMENT(DeleteSqlMap.SCENICEQUIPMENT_KEY,TqSearchType.PLACE_KEY,"",Map.class,DeleteSqlMap.scenicequipmentIdFields),
	DELETE_SCENICTRAFFIC(DeleteSqlMap.SCENICTRAFFIC_KEY,TqSearchType.PLACE_KEY,"",Map.class,DeleteSqlMap.scenicTrafficIdFields),
	DELETE_NEWSOCIETYORG(DeleteSqlMap.NEWSOCIETYORG_KEY,TqSearchType.PLACE_KEY,"",Map.class,DeleteSqlMap.newSocietyOrgIdFields),
	DELETE_NEWECONOMICORG(DeleteSqlMap.NEWECONOMICORG_KEY,TqSearchType.PLACE_KEY,"",Map.class,DeleteSqlMap.newEconomicOrgIdFields),
	
	DELETE_HOUSEINFO(DeleteSqlMap.HOUSEINFO_KEY,TqSearchType.HOUSE_KEY,"",Map.class,DeleteSqlMap.idFields),
	DELETE_BUILDDATAS(DeleteSqlMap.BUILDDATAS_KEY,TqSearchType.BUILDING_KEY,"",Map.class,DeleteSqlMap.idFields),
	
	DELETE_SERVICERECORD(DeleteSqlMap.SERVICERECORD_KEY,TqSearchType.SERVICERECORD_KEY,"",Map.class,DeleteSqlMap.idFields),
	DELETE_ISSUES(DeleteSqlMap.ISSUES_KEY,TqSearchType.ISSUE_KEY,"",Map.class,DeleteSqlMap.idFields),
	
	DELETE_HOUSEHOLDFAMILY(DeleteSqlMap.HOUSEHOLDFAMILY_KEY,TqSearchType.HOUSEHOLDFAMILY_KEY,"",Map.class,DeleteSqlMap.idFields),
	DELETE_HOUSEHOLDSTAFF(DeleteSqlMap.HOUSEHOLDSTAFF_KEY,TqSearchType.POPULATION_KEY,"",Map.class,DeleteSqlMap.householdStaffIdFields),
	DELETE_FLOATINGPOPULATION(DeleteSqlMap.FLOATINGPOPULATION_KEY,TqSearchType.POPULATION_KEY,"",Map.class,DeleteSqlMap.floatingPopulationIdFields),
	DELETE_UNSETTLEDPOPULATION(DeleteSqlMap.UNSETTLEDPOPULATION_KEY,TqSearchType.POPULATION_KEY,"",Map.class,DeleteSqlMap.unsettledPopulationIdFields),
	DELETE_OVERSEAPERSONNEL(DeleteSqlMap.OVERSEAPERSONNEL_KEY,TqSearchType.POPULATION_KEY,"",Map.class,DeleteSqlMap.overseaPersonnelIdFields),
	
	DELETE_PEOPLEASPIRATIONS(DeleteSqlMap.PEOPLEASPIRATIONS_KEY,TqSearchType.ACCOUNT_KEY,"",Map.class,DeleteSqlMap.peopleAspirationsIdFields),
	DELETE_POORPEOPLE(DeleteSqlMap.POORPEOPLE_KEY,TqSearchType.ACCOUNT_KEY,"",Map.class,DeleteSqlMap.poorpeopleIdFields),
	DELETE_STEADYWORK(DeleteSqlMap.STEADYWORK_KEY,TqSearchType.ACCOUNT_KEY,"",Map.class,DeleteSqlMap.steadyWorkIdFields),
	
	DELETE_PRIMARYMEMBERS(DeleteSqlMap.PRIMARYMEMBERS_KEY,TqSearchType.PRIMARYMEMBERS_KEY,"",Map.class,DeleteSqlMap.idFields),
	DELETE_PARTYMEMBERS(DeleteSqlMap.PARTYMEMBERS_KEY,TqSearchType.PARTYMEMBERS_KEY,"",Map.class,DeleteSqlMap.partyMembersIdFields)
	;
	
	private String key;
	private String searchType;
	private String searchFields;
	private Class beanClass;
	private Map<String,String> formatMap;
	
	private SolrSqlMap(String key, String searchType, String searchFields,
			Class beanClass, Map<String, String> formatMap) {
		this.key = key;
		this.searchType = searchType;
		this.searchFields = searchFields;
		this.beanClass = beanClass;
		this.formatMap = formatMap;
	}
/*
	private static Map<String,String> replaceFields = new HashMap<String, String>();
	static{
		replaceFields.put("centerlon", "lon");
		replaceFields.put("centerlon2", "lon2");
		replaceFields.put("centerlat", "lat");
		replaceFields.put("centerlat2", "lat2");
	}
*/	
	public static SolrSqlMap getBySqlKey(String sqlKey){
		return getBySqlKey(sqlKey,"");
	}
	public static SolrSqlMap getDeleteEnum(String key){
		return getBySqlKey(key,"DELETE_");
	}
	public static SolrSqlMap getUpdateEnum(Object object,String key){
		SolrSqlMap sqlMap = getBySqlKey(key,"UPDATE_");
		if((sqlMap==null || sqlMap.getSearchType()==null)&& object!=null){
			sqlMap = getUpdateEnumBySearchVo(object,key);
		}
		return sqlMap;
	}
	
	private static SolrSqlMap getBySqlKey(String sqlKey,String mode){
		String key = sqlKey;
		key = (StringUtil.isStringAvaliable(sqlKey))?key.split("\\.")[0]:"";
		mode=(mode==null)?"":mode;
		SolrSqlMap[] sqlMaps = SolrSqlMap.values();
		for (int i = 0; i < sqlMaps.length; i++) {
			SolrSqlMap sqlMap = sqlMaps[i];
			if(sqlMap.name().startsWith(mode) && (sqlMap.getKey().equalsIgnoreCase(key) || sqlMap.getKey().equalsIgnoreCase(sqlKey))){
				return sqlMap;
			}
		}
		return null;
	}
	
	public static SolrSqlMap getUpdateEnumBySearchVo(Object data,String key){
		Object[] datas = (data instanceof Object[])?(Object[])data:new Object[]{data};
		SolrSqlMap sqlMap = null;
		Object searchVo =datas[0];
		if(searchVo instanceof CityComponentsInfoVo){
			sqlMap = SolrSqlMap.UPDATE_DUSTBIN;
		}else if(searchVo instanceof KeyPlaceInfoVo){
			sqlMap = SolrSqlMap.UPDATE_KEYPLACE;
		}else if(searchVo instanceof Builddatas){
			sqlMap = SolrSqlMap.UPDATE_BUILDDATA;
		}else if(searchVo instanceof IssueNew){
			sqlMap = SolrSqlMap.UPDATE_ISSUES;
		}else if(searchVo instanceof Skynet){
			sqlMap = SolrSqlMap.UPDATE_SKYNET;
		}else if(searchVo instanceof Bayonet){
			sqlMap = SolrSqlMap.UPDATE_BAYONET;
		}else if(searchVo instanceof SnapshotSystem){
			sqlMap = SolrSqlMap.UPDATE_SNAPSHOTSYSTEM;
		}else if(TqSearchType.HOUSE_KEY.equals(key)){
			sqlMap = SolrSqlMap.UPDATE_HOUSEINFO;
		}else {
			Map parameters = (searchVo instanceof Map)?(Map) searchVo:TqSearchUtil.toMap(searchVo);
			String updateSqlKey = (String) parameters.get("tableName");
			if(searchVo instanceof PopulationCatalog){
				updateSqlKey = (String) parameters.get("catalog");
			}else if(UpdateSqlMap.HOUSE_POPULATION_KEY.equals(key)){
				if(searchVo instanceof HouseHasActualPopulation){
					updateSqlKey = (String) parameters.get("populationType");
				}else{
					updateSqlKey = (String)searchVo;
				}
			}
			sqlMap= getBySqlKey(updateSqlKey,"UPDATE_");
		}
		return sqlMap;
	}
	
	public static TqSearchVo getSearchVo(String key,Map<String, Object> parameters){
		SolrSqlMap sqlMap = getBySqlKey(key);
		return SearchSqlMap.getSearchVo(sqlMap, parameters);
	}
	
	public static List<String> getDeleteVo(String key,Object parameters){
		SolrSqlMap sqlMap = SolrSqlMap.getDeleteEnum(key);
		return DeleteSqlMap.getSql(sqlMap,parameters);
	}
	
	public static Map<String, String> getUpdateVo(String key,Object parameters){
		SolrSqlMap sqlMap = SolrSqlMap.getUpdateEnum(parameters,key);
		return UpdateSqlMap.getSql(sqlMap,parameters);
	}
	
	public static List toResult(String statementName, List<Map<String, Object>> mapList, TqSearchVo searchVo) {
		List result = new ArrayList();
		if(mapList==null) return result;
		SolrSqlMap sqlMap = getBySqlKey(statementName);
		Class beanClass = sqlMap.getBeanClass();
		Map<String,String> formatMap = sqlMap.getFormatMap();
		for (Map map : mapList) {
			result.addAll(0, SearchSqlMap.toResult(beanClass, map,formatMap, searchVo));
		}
		return result;
	}
	
	public static Object toResult(String statementName,Map map, TqSearchVo searchVo){
		SolrSqlMap sqlMap = getBySqlKey(statementName);
		Class beanClass = sqlMap.getBeanClass();
		Map<String,String> formatMap = sqlMap.getFormatMap();
		List result = SearchSqlMap.toResult(beanClass, map,formatMap, searchVo);
		return (result!=null && result.size()>0)?result.get(0):null;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchFields() {
		return searchFields;
	}

	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

	public Map<String, String> getFormatMap() {
		return formatMap;
	}

	public void setFormatMap(Map<String, String> formatMap) {
		this.formatMap = formatMap;
	}
	
}
