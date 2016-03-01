package com.tianque.gis.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.solr.domain.DocumentType;

public class GisGlobalValue {
	public final static String KEY_POPULATION = "KEY_POPULAION";
	public final static String KEY_RANGE = "KEY_RANGE";
	public final static String KEY_HOUSEPROPERTY_DETAIL = "KEY_HOUSEPROPERTY_DETAIL";
	public final static String KEY_POPULATION_DETAIL = "KEY_POPULATION_DETAIL";
	public final static String KEY_ACTUALCOMPANY_DEAIL = "KEY_ACTUALCOMPANY_DEAIL";
	public final static String KEY_LOCATION = "KEY_LOCATION";
	public final static String KEY_LOCATION_DETAIL = "KEY_LOCATION_DETAIL";
	public final static String ACTUAL_POPULATION_DETAIL = "ACTUAL_POPULATION_DETAIL";
	public final static String GENERAL_HOUSE_INFO = "GENERAL_HOUSE_INFO";

	public final static String ACTUALHOUSE = "actualHouse";// 实有房屋
	public final static String RENTALHOUSE = "rentalHouse";// 出租房
	public final static String ACTUALUNIT = "ACTUALCOMPANY";// 实有单位
	public final static String ISSUENEWS = "issueNews";// 事件处理

	/* Gis二次检索分类 */
	public final static String CAREED_POPULATION = "all_civil_population";
	public final static String IMPORTANT_POPULATION = "all_attention_population";
	public final static String NURTURES_WOMAN = "all_birth_population";
	public final static String ISSUE_NEWS = "issue_news";

	public final static String PLACE_MODE = "keyPlaces";
	public final static String PERSON_MODE = "person";
	public final static String HOUSE_MODE = "houseInfo";
	public final static String GIS_API_PATH = "http://223.221.65.26:8001/Api2.aspx";
	public final static Integer SEARCH_PERIPHERY_NUM = 50;
	public final static Integer RANGE = 200;
	public final static String PERIPHERY_IMGURL = "/resource/js/widget/tqMap/style/images/defaultMarker.gif";
	public final static String PERIPHERY_PERSON_SERVICE = "commonPopulation";
	public final static String PERIPHERY_PLACE_SERVICE = "place";
	public final static String KEY_HOUSEPROPERTY_UNBOUND = "KEY_HOUSEPROPERTY_UNBOUND";

	public static Map<String, String> MODULE_MAP = new HashMap<String, String>();
	public static Map<String, String> GIS_MODULE_MAP = new LinkedHashMap<String, String>();
	public static Map<String, Object> KEY_MODULE_MAP = new HashMap<String, Object>();
	public static List<String> keyRange = new ArrayList<String>();

	public static Map<String, String> keyPopulation = new LinkedHashMap<String, String>();
	public static Map<String, String> keyPlace = new LinkedHashMap<String, String>();
	private static Map<String, String> keyLocation = new LinkedHashMap<String, String>();
	private static Map<String, String> generalLocation = new LinkedHashMap<String, String>();
	private static Map<String, String> keyLocationDetail = new HashMap<String, String>();
	private static Map<String, String> actualPopulationDetail = new HashMap<String, String>();
	private static Map<String, String> gisCountSearch = new LinkedHashMap<String, String>();

	/* Gis二次检索分类 */
	private static Map<String, String> careedPopulation = new LinkedHashMap<String, String>();
	private static Map<String, String> importantPopulation = new LinkedHashMap<String, String>();
	private static Map<String, String> nurturesWomen = new LinkedHashMap<String, String>();
	private static Map<String, String> issueNews = new LinkedHashMap<String, String>();

	public final static String ACTUAL_POPULATION = "ACTUAL_POPULATION";
	private static Map<String, String> actualPopulation = new LinkedHashMap<String, String>();
	private static String[] imgurlArray = new String[] {
			"resource/edushiGis/images/a.png",
			"resource/edushiGis/images/b.png",
			"resource/edushiGis/images/c.png",
			"resource/edushiGis/images/d.png",
			"resource/edushiGis/images/e.png",
			"resource/edushiGis/images/f.png",
			"resource/edushiGis/images/g.png",
			"resource/edushiGis/images/h.png",
			"resource/edushiGis/images/i.png",
			"resource/edushiGis/images/j.png" };

	static {

		// 用于gis统计
		gisCountSearch.put("actualHouse", "实有房屋");
		gisCountSearch.put("rentalHouse", "出租房");
		gisCountSearch.put("actualUnit", "实有单位");
		gisCountSearch.put("issueNews", "事件处理");

		MODULE_MAP.put(GENERAL_HOUSE_INFO, "普通住房");
		generalLocation.put("houseProperty", "住房信息");

		MODULE_MAP.put(ACTUAL_POPULATION, "人口信息");
		actualPopulation.put(BaseInfoTables.HOUSEHOLDSTAFF_KEY,
				BaseInfoTables.HOUSEHOLD_STAFFNAME);
		actualPopulation.put(BaseInfoTables.FLOATINGPOPULATION_KEY,
				BaseInfoTables.FLOATINGPOPULATION_DISPLAYNAME);
		actualPopulation.put(BaseInfoTables.UNSETTEDPOPULATION_KEY,
				BaseInfoTables.UNSETTEDPOPULATION_DISPLAYNAME);
		actualPopulation.put(BaseInfoTables.OVERSEAPERSONNEL_KEY,
				BaseInfoTables.OVERSEAPERSONNEL_DISPLAYNAME);

		KEY_MODULE_MAP.put(KEY_POPULATION, keyPopulation);
		KEY_MODULE_MAP.put(KEY_LOCATION, keyLocation);
		KEY_MODULE_MAP.put(GENERAL_HOUSE_INFO, generalLocation);
		KEY_MODULE_MAP.put(ACTUAL_POPULATION, actualPopulation);

		KEY_MODULE_MAP.put(KEY_RANGE, keyRange);
		keyRange.add("200");
		keyRange.add("1000");
		keyRange.add("5000");

		/* Gis二次检索分类 */
		KEY_MODULE_MAP.put(CAREED_POPULATION, careedPopulation);
		KEY_MODULE_MAP.put(IMPORTANT_POPULATION, importantPopulation);
		KEY_MODULE_MAP.put(NURTURES_WOMAN, nurturesWomen);
		KEY_MODULE_MAP.put(ISSUE_NEWS, issueNews);

		keyPopulation.put(BaseInfoTables.AIDNEEDPOPULATION_KEY,
				BaseInfoTables.AIDNEEDPOPULATION_DISPLAYNAME);
		keyPopulation.put(BaseInfoTables.UNEMPLOYEDPEOPLE_KEY,
				BaseInfoTables.UNEMPLOYEDPEOPLE_DISPLAYNAME);
		keyPopulation.put(BaseInfoTables.OPTIMALOBJECT_KEY,
				BaseInfoTables.OPTIMALOBJECT_DISPLAYNAME);
		keyPopulation.put(BaseInfoTables.HANDICAPPED_KEY,
				BaseInfoTables.HANDICAPPED_DISPLAYNAME);
		keyPopulation.put(BaseInfoTables.ELDERLYPEOPLE_KEY,
				BaseInfoTables.ELDERLYPEOPLE_DISPLAYNAME);
		keyPopulation.put(BaseInfoTables.DRUGGY_KEY,
				BaseInfoTables.DRUGGY_DISPLAYNAME);
		keyPopulation.put(BaseInfoTables.IDLEYOUTH_KEY,
				BaseInfoTables.IDLEYOUTH_DISPLAYNAME);
		keyPopulation.put(BaseInfoTables.RECTIFICATIVEPERSON_KEY,
				BaseInfoTables.RECTIFICATIVEPERSON_DISPLAYNAME);
		keyPopulation.put(BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY,
				BaseInfoTables.DANGEROUSGOODSPRACTITIONER_DISPLAYNAME);
		keyPopulation.put(BaseInfoTables.POSITIVEINFO_KEY,
				BaseInfoTables.POSITIVEINFO_DISPLAYNAME);
		keyPopulation.put(BaseInfoTables.SUPERIORVISIT_KEY,
				BaseInfoTables.SUPERIORVISIT_DISPLAYNAME);
		keyPopulation.put(BaseInfoTables.MENTALPATIENT_KEY,
				BaseInfoTables.MENTALPATIENT_DISPLAYNAME);
		keyPopulation.put(BaseInfoTables.NURTURESWOMEN_KEY,
				BaseInfoTables.NURTURESWOMEN_DISPLAYNAME);

		keyPopulation.put(BaseInfoTables.OTHERATTENTIONPERSONNEL_KEY,
				BaseInfoTables.OTHERATTENTIONPERSONNEL_DISPLAYNAME);// 其他人员
		keyPopulation.put(BaseInfoTables.AIDSPOPULATIONS_KEY,
				BaseInfoTables.AIDSPOPULATIONS_DISPLAYNAME);// 艾滋病人员

		keyPlace.put(DocumentType.SAFETYPRODUCTIONKEY.name(), "安全生产重点");
		keyPlace.put(DocumentType.FIRESAFETYKEY.name(), "消防安全重点");
		keyPlace.put(DocumentType.SECURITYKEY.name(), "治安重点");
		keyPlace.put(DocumentType.SCHOOL.name(), "学校");
		keyPlace.put(DocumentType.DANGEROUSCHEMICALSUNIT.name(), "危险化学品单位");
		keyPlace.put(DocumentType.INTERNETBAR.name(), "网吧");
		keyPlace.put(DocumentType.PUBLICPLACE.name(), "公共场所");
		keyPlace.put(DocumentType.OTHER_LOCALE.name(), "其他场所");

		GIS_MODULE_MAP.put(CAREED_POPULATION, "关怀对象");
		careedPopulation.put("all_civil_population", "全部");
		careedPopulation.put(BaseInfoTables.AIDNEEDPOPULATION_KEY,
				BaseInfoTables.AIDNEEDPOPULATION_DISPLAYNAME);
		careedPopulation.put(BaseInfoTables.UNEMPLOYEDPEOPLE_KEY,
				BaseInfoTables.UNEMPLOYEDPEOPLE_DISPLAYNAME);
		careedPopulation.put(BaseInfoTables.OPTIMALOBJECT_KEY,
				BaseInfoTables.OPTIMALOBJECT_DISPLAYNAME);
		careedPopulation.put(BaseInfoTables.HANDICAPPED_KEY,
				BaseInfoTables.HANDICAPPED_DISPLAYNAME);
		careedPopulation.put(BaseInfoTables.ELDERLYPEOPLE_KEY,
				BaseInfoTables.ELDERLYPEOPLE_DISPLAYNAME);

		GIS_MODULE_MAP.put(IMPORTANT_POPULATION, "特殊人群");
		importantPopulation.put("all_attention_population", "全部");
		importantPopulation.put(BaseInfoTables.DRUGGY_KEY,
				BaseInfoTables.DRUGGY_DISPLAYNAME);
		importantPopulation.put(BaseInfoTables.IDLEYOUTH_KEY,
				BaseInfoTables.IDLEYOUTH_DISPLAYNAME);
		importantPopulation.put(BaseInfoTables.RECTIFICATIVEPERSON_KEY,
				BaseInfoTables.RECTIFICATIVEPERSON_DISPLAYNAME);
		importantPopulation.put(BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY,
				BaseInfoTables.DANGEROUSGOODSPRACTITIONER_DISPLAYNAME);
		importantPopulation.put(BaseInfoTables.POSITIVEINFO_KEY,
				BaseInfoTables.POSITIVEINFO_DISPLAYNAME);
		importantPopulation.put(BaseInfoTables.SUPERIORVISIT_KEY,
				BaseInfoTables.SUPERIORVISIT_DISPLAYNAME);
		importantPopulation.put(BaseInfoTables.MENTALPATIENT_KEY,
				BaseInfoTables.MENTALPATIENT_DISPLAYNAME);

		GIS_MODULE_MAP.put(NURTURES_WOMAN, "育妇");
		nurturesWomen.put("all_birth_population", "全部");
		nurturesWomen.put(BaseInfoTables.NURTURESWOMEN_KEY,
				BaseInfoTables.NURTURESWOMEN_DISPLAYNAME);

		issueNews.put("", "全部");
		issueNews.put("completeIssue", "已完成");
		issueNews.put("uncompleteIssue", "未完成");

		// 详细信息地址
		KEY_MODULE_MAP
				.put(KEY_HOUSEPROPERTY_UNBOUND,
						"/baseinfo/actualHouseManage/dispatchOperate.action?mode=view&houseInfo.id=");
		KEY_MODULE_MAP
				.put(KEY_HOUSEPROPERTY_DETAIL,
						"/baseinfo/houseInfo/actualHouse/viewActualHouseDlg.jsp?mode=view&houseInfo.id=");
		KEY_MODULE_MAP
				.put(KEY_ACTUALCOMPANY_DEAIL,
						"/baseinfo/actualCompanyManage/dispatchOperate.action?mode=view&location.id=");
		KEY_MODULE_MAP
				.put(KEY_POPULATION_DETAIL,
						"/baseinfo/permanentResidentManage/dispatchOperate.action?mode=view&permanentResident.id=");

		KEY_MODULE_MAP.put(KEY_LOCATION_DETAIL, keyLocationDetail);

		actualPopulationDetail
				.put(ModuleMap.ACTUAL_HOUSEHOLDSTAFF.getModuleType(),
						"/baseinfo/householdStaff/dispath.action?mode=view&population.id=");
		actualPopulationDetail
				.put(ModuleMap.ACTUAL_FLOATINGPOPULATION.getModuleType(),
						"/baseinfo/floatingPopulationManage/dispath.action?mode=view&population.id=");
		actualPopulationDetail
				.put(ModuleMap.ACTUAL_UNSETTLEDPOPULATION.getModuleType(),
						"/baseinfo/unsettledPopulationManage/dispatchOperate.action?mode=view&unsettledPopulation.id=");
		actualPopulationDetail
				.put(ModuleMap.ACTUAL_OVERSEASTAFF.getModuleType(),
						"/baseinfo/overseaPersonnelManage/dispatch.action?mode=view&isHiddenPersonnelTrack=1&overseaPersonnel.id=");

		KEY_MODULE_MAP.put(ACTUAL_POPULATION_DETAIL, actualPopulationDetail);

	}

	public static String getBubblePath(Integer index) {
		return imgurlArray[index];
	}

	public static String getLocationNameByType(String type) {
		return keyLocation.get(type);
	}

	public static String getActualHOuseNameByType(String type) {
		return gisCountSearch.get(type);
	}

	public static Map<String, String> getGisPersonType(String keyPersonType) {
		return (LinkedHashMap) KEY_MODULE_MAP.get(keyPersonType);
	}

	public static String getKeyPlaceNameByType(String type) {
		return keyPlace.get(type);
	}
}
