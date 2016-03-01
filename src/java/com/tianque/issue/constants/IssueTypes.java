package com.tianque.issue.constants;

import java.util.HashMap;
import java.util.Map;

public class IssueTypes {
	/** 服务审批模块 */
	public final static String ITEM_MODULE = "item";
	/** 事件模块 */
	public final static String CORE_MODULE = "core";
	/** 城市管理模块 */
	public final static String DIGITALCITY_MODULE = "digtal_city";
	/** 呼叫中心模块 */
	public final static String CALLCENTER_MODULE = "callcenter";
	/** 四川个性化 */
	public final static String SI_CHUAN = "si_chuan";

	/** 事件模块中事件类型大类 */
	public final static String ITEM = "一站审批";
	public final static String CONTRADICTION = "矛盾纠纷";
	public final static String SECURITYTROUBLE = "治安、安全隐患";
	public final static String PEOPLELIVE_SERVICE = "民生服务";
	public final static String OTHERTYPE = "其他";
	/** 城市管理模块中事件类型大类 */
	public final static String ENVIRONMENT = "市容环境";
	public final static String ADVERTISEMENT = "宣传广告";
	public final static String ENGINEERING = "施工管理";
	public final static String EMERGENCY = "突发事件";
	public final static String STREET = "街面秩序";
	public final static String CITY_OTHER = "其他";
	/** 呼叫中心模块中的事件类型大类 */
	public final static String POLITICAL_AFFAIRS = "政治综合";
	public final static String ECONOMIC_AFFAIRS = "经济综合";
	public final static String PROPAGANDA = "宣传舆论";
	public final static String INSPECTION = "纪检监察";
	public final static String LAW = "政法";
	public final static String SOCIAL_SECURITY = "劳动社保";
	public final static String EDUCATION = "教育";
	public final static String HYGIENISM = "卫生计生";
	public final static String SCIENCE = "科技文体";
	public final static String PERSONNEL_AFFAIRS = "组织人事";
	public final static String TRAFFIC = "交通能源环保";
	public final static String CIVIADMINISTRATION = "民政";
	public final static String AGRICULTURE = "农村农业";
	public final static String IRRIGATION_WORKS = "国土资源水利农业";
	public final static String CITY_CONSTRUCT = "城乡建设";
	public final static String INFORMATION_INDUSTRY = "信息产业";
	public final static String TOUR = "商贸旅游";
	public final static String CALLCENTER_OTHER = "其他";
	public final static String RESOLVETHECONTRADICTIONS = "矛盾劝解";
	public final static String SECURITYPRECAUTIONS = "参与治安防控";
	public final static String SPECIALPOPULATIONS = "参与特殊人群服务管理";
	public final static String SOCIALCONDITIONS = "社情民意收集";
	public final static String POLICIESANDLAWS = "政策法规宣传";
	public final static String EMERGENCIES = "突发事件报告";
	public final static String OTHERMANAGE = "其它";

	public final static Map<String, String> map = new HashMap<String, String>();
	static {
		map.put("contradiction", PEOPLELIVE_SERVICE);
		map.put("resolveTheContradictions", RESOLVETHECONTRADICTIONS);
		map.put("securityPrecautions", SECURITYPRECAUTIONS);
		map.put("specialPopulations", SPECIALPOPULATIONS);
		map.put("socialConditions", SOCIALCONDITIONS);
		map.put("policiesAndLaws", POLICIESANDLAWS);
		map.put("emergencies", EMERGENCIES);
		map.put("otherManage", OTHERMANAGE);
	}

	public static String getChineseName(String name) {
		return map.get(name);
	}

}
