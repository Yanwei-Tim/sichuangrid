package com.tianque.domain.property;

import java.util.HashMap;
import java.util.Map;

public class WorkDiaryTypes {
	public final static String HELP_PERSONNEL = "重点人员走访帮教类";
	public final static String LOVINGCARE = "关怀对象走访服务类";
	public final static String UNEMPLOYED = "失业人员走访服务类";
	public final static String NURTURESWOMEN = "育龄妇女走访服务类";
	public final static String FLOORPERSONS = "重点场所走访巡防类";
	public final static String LETTINGHOUSE_FLOORPERSONS = "出租房走访巡防类";
	public final static String ENTERPRISE_FLOORPERSONS = "企业走访巡防类";
	public final static String NEWSOCIETY_FLOORPERSONS = "社会组织走访巡防类";
	public final static String ISSUE_DEAL = "事件调解类";
	public final static String OTHER = "其他";

	private static Map<String, String> objectMap = new HashMap<String, String>();

	public final static String TYPE_LOVINGCARE = "lovingcare";
	public final static String TYPE_OTHER = "other";
	public final static String TYPE_FLOORPERSONS = "floorperson";
	public final static String TYPE_HELPPERSONNEL = "helpprecord";
	public final static String TYPE_LETTINGHOUSE_FLOORPERSONS = "lettinghousefloorperson";
	public final static String TYPE_NEWSOCIETY_FLOORPERSONS = "newsocietyhelpprecord";
	public final static String TYPE_ENTERPRISE_FLOORPERSONS = "enterprisehelpprecord";
	public final static String TYPE_ISSUENEW = "issuenew";
	public final static String TYPE_ISSUEBUSINESS = "issuebusiness";
	public final static String TYPE_UNEMPLOYED = "unemployed";
	public final static String TYPE_NURTURESWOMEN = "nurtureswomen";
	static {
		objectMap.put(TYPE_OTHER, OTHER);
		objectMap.put(TYPE_FLOORPERSONS, FLOORPERSONS);
		objectMap.put(TYPE_HELPPERSONNEL, HELP_PERSONNEL);
		objectMap.put(TYPE_LOVINGCARE, LOVINGCARE);
		objectMap.put(TYPE_LETTINGHOUSE_FLOORPERSONS, LETTINGHOUSE_FLOORPERSONS);
		objectMap.put(TYPE_ENTERPRISE_FLOORPERSONS, ENTERPRISE_FLOORPERSONS);
		objectMap.put(TYPE_NEWSOCIETY_FLOORPERSONS, NEWSOCIETY_FLOORPERSONS);
		objectMap.put(TYPE_ISSUENEW, ISSUE_DEAL);
		objectMap.put(TYPE_UNEMPLOYED, UNEMPLOYED);
		objectMap.put(TYPE_NURTURESWOMEN, NURTURESWOMEN);
		objectMap.put(TYPE_ISSUEBUSINESS, "事件调解处理类");
	}

}
