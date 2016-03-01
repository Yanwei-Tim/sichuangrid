package com.tianque.plugin.tqSearch.sqlMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tianque.plugin.tqSearch.constants.TqSearchUtil;

public class DeleteSqlMap {
	/**删除--房屋信息*/
	public final static String HOUSEINFO_KEY = "HOUSEINFO";
	/**删除--楼宇信息*/
	public final static String BUILDDATAS_KEY = "BUILDDATAS";
	/**删除--单位场所*/
	public final static String COMPANYPLACE_KEY = "COMPANYPLACE";
	/**删除--旅游景点*/
	public final static String SCENICSPOT_KEY="SCENICSPOT";
	/**删除--景区配套设施信息*/
	public final static String SCENICEQUIPMENT_KEY="SCENICEQUIPMENT";
	/**删除--景区交通*/
	public final static String SCENICTRAFFIC_KEY = "SCENICTRAFFIC";
	/**删除--社会组织*/
	public final static String NEWSOCIETYORG_KEY="NEWSOCIETYORG";
	/**删除--新经济组织*/
	public final static String NEWECONOMICORG_KEY="NEWECONOMICORG";
	/**删除--城市部件*/
	public final static String DUSTBIN_KEY = "DUSTBIN";
	/**删除--天网*/
	public final static String SKYNET_KEY = "SKYNET";
	/**删除--卡口*/
	public final static String BAYONET_KEY = "BAYONET";
	/**删除--抓拍系统*/
	public final static String SNAPSHOTSYSTEM_KEY = "SNAPSHOTSYSTEM";
	/**删除--事件*/
	public final static String ISSUES_KEY = "ISSUES";
	/**删除--服务记录*/
	public final static String SERVICERECORD_KEY = "SERVICERECORD";
	/**删除--户籍家庭信息*/
	public final static String HOUSEHOLDFAMILY_KEY="HOUSEHOLDFAMILY";
	/**删除--户籍人口*/
	public final static String HOUSEHOLDSTAFF_KEY="HOUSEHOLDSTAFF";
	/**删除--流动人口*/
	public final static String FLOATINGPOPULATION_KEY="FLOATINGPOPULATION";
	/**删除--未落户人口*/
	public final static String UNSETTLEDPOPULATION_KEY="UNSETTLEDPOPULATION";
	/**删除--流动人口*/
	public final static String OVERSEAPERSONNEL_KEY="OVERSEAPERSONNEL";
	/**删除--民生诉求*/
	public final static String PEOPLEASPIRATIONS_KEY="PEOPLEASPIRATIONS";
	/**删除--困难群众信息*/
	public final static String POORPEOPLE_KEY="POORPEOPLE";
	/**删除--稳定工作台账*/
	public final static String STEADYWORK_KEY="STEADYWORK";
	/**删除--成员库*/
	public final static String PRIMARYMEMBERS_KEY="PRIMARYMEMBERS";
	/**删除--党员*/
	public final static String PARTYMEMBERS_KEY="PARTYMEMBERS";
	/**删除监控视频*/
	public final static String VIDEOSYSTEM_KEY = "VIDEOSYSTEM";
	
	/**
	 * Map<key,value>
	 * key：统一搜索DBD存储字段名
	 * value：社管系统对象字段名
	 * 操作说明：
	 *		为Map中的value赋值，	在统一搜索中根据Map<key,value> 实现删除
	 */
	public static Map<String,String> idFields = new HashMap<String,String>();
	static{
		idFields.put("keyId", "id");
	}
	public static Map<String,String> skynetIdFields = new HashMap<String,String>();
	static{
		skynetIdFields.put("keyId", "id");
		skynetIdFields.put("moduleType", "SKYNET");
	}
	public static Map<String,String> bayonetIdFields = new HashMap<String,String>();
	static{
		bayonetIdFields.put("keyId", "id");
		bayonetIdFields.put("moduleType", "BAYONET");
	}
	public static Map<String,String> snapshotSystemIdFields = new HashMap<String,String>();
	static{
		snapshotSystemIdFields.put("keyId", "id");
		snapshotSystemIdFields.put("moduleType", "SNAPSHOTSYSTEM");
	}
	public static Map<String,String> companyPlaceIdFields = new HashMap<String,String>();
	static{
		companyPlaceIdFields.put("keyId", "id");
		companyPlaceIdFields.put("classification", "{0 TO *}");
	}
	public static Map<String,String> scenicspotIdFields = new HashMap<String,String>();
	static{
		scenicspotIdFields.put("keyId", "id");
		scenicspotIdFields.put("classificationen", "SCENICSPOTS");
	}
	public static Map<String,String> scenicequipmentIdFields = new HashMap<String,String>();
	static{
		scenicequipmentIdFields.put("keyId", "id");
		scenicequipmentIdFields.put("classificationen", "SCENICEQUIPMENT");
	}
	public static Map<String,String> scenicTrafficIdFields = new HashMap<String,String>();
	static{
		scenicTrafficIdFields.put("keyId", "id");
		scenicTrafficIdFields.put("classificationen", "SCENICTRAFFIC");
	}
	public static Map<String,String> newSocietyOrgIdFields = new HashMap<String,String>();
	static{
		newSocietyOrgIdFields.put("keyId", "id");
		newSocietyOrgIdFields.put("classificationen", "NEWSOCIETYFEDERATIONS");
	}
	public static Map<String,String> newEconomicOrgIdFields = new HashMap<String,String>();
	static{
		newEconomicOrgIdFields.put("keyId", "id");
		newEconomicOrgIdFields.put("classificationen", "NEWECONOMICORGANIZATIONS");
	}
	
	public static Map<String,String> householdStaffIdFields = new HashMap<String,String>();
	static{
		householdStaffIdFields.put("keyId", "id");
		householdStaffIdFields.put("dataType", "householdStaff");
	}
	public static Map<String,String> floatingPopulationIdFields = new HashMap<String,String>();
	static{
		floatingPopulationIdFields.put("keyId", "id");
		floatingPopulationIdFields.put("dataType", "floatingPopulation");
	}
	public static Map<String,String> unsettledPopulationIdFields = new HashMap<String,String>();
	static{
		unsettledPopulationIdFields.put("keyId", "id");
		unsettledPopulationIdFields.put("dataType", "unsettledPopulation");
	}
	public static Map<String,String> overseaPersonnelIdFields = new HashMap<String,String>();
	static{
		overseaPersonnelIdFields.put("keyId", "id");
		overseaPersonnelIdFields.put("dataType", "overseaStaff");
	}
	
	public static Map<String,String> peopleAspirationsIdFields = new HashMap<String,String>();
	static{
		peopleAspirationsIdFields.put("keyId", "id");
		peopleAspirationsIdFields.put("dataType", "PEOPLEASPIRATION");
	}
	public static Map<String,String> poorpeopleIdFields = new HashMap<String,String>();
	static{
		poorpeopleIdFields.put("keyId", "id");
		poorpeopleIdFields.put("dataType", "POORPEOPLE");
	}
	public static Map<String,String> steadyWorkIdFields = new HashMap<String,String>();
	static{
		steadyWorkIdFields.put("keyId", "id");
		steadyWorkIdFields.put("dataType", "STEADYWORK");
	}
	public static Map<String,String> partyMembersIdFields = new HashMap<String,String>();
	static{
		partyMembersIdFields.put("keyId", "id");
		partyMembersIdFields.put("partyorgType", "partyorgType");
	}
	
	public static List<String> getSql(SolrSqlMap sqlMap,Object data){
		List<String> resultList = new ArrayList<String>();
		Map<String,String> idFields = sqlMap.getFormatMap();
		List<Map> dataList = formatData(sqlMap,data);
		for (Map dataMap:dataList) {
			StringBuffer sqlSb = new StringBuffer();
			for (Iterator<Entry<String, String>>  it = idFields.entrySet().iterator();it.hasNext(); ) {
				Entry<String, String> entry = it.next();
				String indexKey = entry.getKey();
				String dataKey = entry.getValue();
				Object value = dataMap.get(dataKey);
				sqlSb.append((sqlSb.length()>0)?" AND ":"");
				sqlSb.append("(").append(indexKey).append(":").append((value!=null)?value+"":dataKey).append(")");
			}
			resultList.add(sqlSb.toString());
		}
		return resultList;
	}
	
	private static List<Map> formatData(SolrSqlMap sqlMap,Object args){
		List<Map> list = new ArrayList<Map>();
		Object data = ((Object[])args)[0];
		if(DeleteSqlMap.COMPANYPLACE_KEY.equals(sqlMap.getKey())){
			String[] ids = ((String)data).split(",");
			Object type = ((Object[])args)[1];
			for (int i = 0; i < ids.length; i++) {
				Map dataMap = new HashMap();
				dataMap.put("id", ids[i]);
				dataMap.put("type", type);
				list.add(dataMap);
			}
			return list;
		}else if(DeleteSqlMap.PARTYMEMBERS_KEY.equals(sqlMap.getKey())){
			List<Long> ids = (List<Long>) ((Object[])args)[1];
			for (int i = 0; i < ids.size(); i++) {
				Map dataMap = new HashMap();
				dataMap.put("id", ids.get(i));
				dataMap.put("partyorgType", data);
				list.add(dataMap);
			}
			return list;
		}
		if(data instanceof Map){
			list.add((Map) data);
		}else if(data instanceof List){
			List longData = (List) data;
			for (int i = 0; i < longData.size(); i++) {
				Map dataMap = new HashMap();
				dataMap.put("id", longData.get(i));
				list.add(dataMap);
			}
		}else if(data instanceof Long[]){
			Long[] longData = (Long[]) data;
			for (int i = 0; i < longData.length; i++) {
				Map dataMap = new HashMap();
				dataMap.put("id", longData[i]);
				list.add(dataMap);
			}
		}else if(data instanceof Long){
			Map dataMap = new HashMap();
			dataMap.put("id", data);
			list.add(dataMap);
		}else if(data instanceof String){
			String[] ids = ((String)data).split(",");
			for (int i = 0; i < ids.length; i++) {
				Map dataMap = new HashMap();
				dataMap.put("id", ids[i]);
				list.add(dataMap);
			}
		}else{
			list.add(TqSearchUtil.toMap(data));
		}
		return list;
	}
}
