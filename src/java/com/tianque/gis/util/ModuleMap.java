package com.tianque.gis.util;

import java.util.ArrayList;
import java.util.List;

public enum ModuleMap {
	RECTIFICATIVE_PERSON("RECTIFICATIVEPERSON", "社区矫正人员"), DRUGGY_PERSON(
			"DRUGGY", "吸毒人员"), MENTAL_PATIENT("MENTAL_PATIENT", "严重精神障碍患者"), POSITIVE_INFO(
			"POSITIVE_INFO", "刑释人员"), SUPERIOR_VISIT("SUPERIOR_VISIT", "重点上访人员"), DANGEROUS_GOODS_PRACTITIONER(
			"DANGEROUS_GOODS_PRACTITIONER", "危险品从业人员"), IDLE_YOUTH(
			"IDLE_YOUTH", "重点青少年"), RELIGION_BELIEF("RELIGION_BELIEF", "民族宗教人员"), OTHER_ATTENTION_PERSONNE(
			"otherAttentionPersonne", "其他关注人员"), ENTERPRISE_ABOVE(
			"ENTERPRISEKEY", "规上企业"), ENTERPRISE_PROTECTION_KEY(
			"PROTECTIONKEY", "保护重点"), ENTERPRISE_SAFETY_PRODUCTION_KEY(
			"SAFETYPRODUCTIONKEY", "安全生产重点"), ENTERPRISE_FIRE_SAFETY_KEY(
			"FIRESAFETYKEY", "消防安全重点"), ENTERPRISE_SECURITY_KEY("SECURITYKEY",
			"治安重点"), PLACE_LETTING_HOUSE("LETTING_HOUSE", "出租房"), PLACE_SCHOOL(
			"SCHOOL", "学校"), PLACE_RELIGION("RELIGION", "宗教场所"), PLACE_HOSPITAL(
			"HOSPITAL", "医院"), PLACE_ADMINISTRATIVE_INSTITUTION(
			"ADMINISTRATIVE_INSTITUTION", "行政事业单位"), PLACE_OTHER_LOCALE(
			"OTHER_LOCALE", "其它重点场所"), ACTUAL_HOUSEHOLDSTAFF(
			"HOUSE_HOLD_STAFF", "户籍人口"), ACTUAL_FLOATINGPOPULATION(
			"FLOATING_POPULATION", "流动人口"), ACTUAL_UNSETTLEDPOPULATION(
			"UNSETTLED_POPULATIONS", "未落户人口"), ACTUAL_OVERSEASTAFF(
			"OVERSEA_STAFF", "境外人员"),

	PLACE_HOUSE_INFO("houseInfo", "住房信息");

	private String moduleType;

	private String moduleName;

	private ModuleMap(String moduleType, String moduleName) {
		this.moduleType = moduleType;
		this.moduleName = moduleName;
	}

	public String getModuleType() {
		return moduleType;
	}

	public String getModuleName() {
		return moduleName;
	}

	// 遍历所有的中文名称
	public static List<String> getShowNameList() {
		List<String> list = new ArrayList<String>();
		for (ModuleMap g : ModuleMap.values()) {
			System.out.println(g.getModuleName());
			list.add(g.getModuleName());
		}

		return list;
	}

	// 遍历所有的英文名称
	public static List<String> getNameList() {
		List<String> list = new ArrayList<String>();
		for (ModuleMap g : ModuleMap.values()) {
			System.out.println(g.getModuleType());
			list.add(g.getModuleType());
		}

		return list;
	}

	// 视图显示模块名称
	public static String getModuleName(String moduleTye) {
		String moduleName = "";
		for (ModuleMap mp : ModuleMap.values()) {
			if (mp.getModuleType().equals(moduleTye)) {
				moduleName = mp.getModuleName();
				break;
			}

		}
		return moduleName;
	}

	public static Integer judgeFromPersonOrPlase(String moduleType) {
		List<String> personTypes = new ArrayList<String>();
		personTypes.add(ModuleMap.RECTIFICATIVE_PERSON.getModuleType());
		personTypes.add(ModuleMap.DRUGGY_PERSON.getModuleType());
		personTypes.add(ModuleMap.MENTAL_PATIENT.getModuleType());
		personTypes.add(ModuleMap.POSITIVE_INFO.getModuleType());
		personTypes.add(ModuleMap.SUPERIOR_VISIT.getModuleType());
		personTypes.add(ModuleMap.DANGEROUS_GOODS_PRACTITIONER.getModuleType());
		personTypes.add(ModuleMap.IDLE_YOUTH.getModuleType());
		personTypes.add(ModuleMap.RELIGION_BELIEF.getModuleType());
		personTypes.add(ModuleMap.OTHER_ATTENTION_PERSONNE.getModuleType());
		personTypes.add(ModuleMap.ACTUAL_HOUSEHOLDSTAFF.getModuleType());
		personTypes.add(ModuleMap.ACTUAL_FLOATINGPOPULATION.getModuleType());
		personTypes.add(ModuleMap.ACTUAL_UNSETTLEDPOPULATION.getModuleType());
		personTypes.add(ModuleMap.ACTUAL_OVERSEASTAFF.getModuleType());

		List<String> placeTypes = new ArrayList<String>();
		placeTypes.add(ModuleMap.ENTERPRISE_ABOVE.getModuleType());
		placeTypes.add(ModuleMap.ENTERPRISE_PROTECTION_KEY.getModuleType());
		placeTypes.add(ModuleMap.ENTERPRISE_SAFETY_PRODUCTION_KEY
				.getModuleType());
		placeTypes.add(ModuleMap.ENTERPRISE_FIRE_SAFETY_KEY.getModuleType());
		placeTypes.add(ModuleMap.ENTERPRISE_SECURITY_KEY.getModuleType());
		placeTypes.add(ModuleMap.PLACE_LETTING_HOUSE.getModuleType());
		placeTypes.add(ModuleMap.PLACE_SCHOOL.getModuleType());
		placeTypes.add(ModuleMap.PLACE_RELIGION.getModuleType());
		placeTypes.add(ModuleMap.PLACE_HOSPITAL.getModuleType());
		placeTypes.add(ModuleMap.PLACE_HOUSE_INFO.getModuleType());
		placeTypes.add(ModuleMap.PLACE_OTHER_LOCALE.getModuleType());
		if (personTypes.contains(moduleType))
			return 1;
		if (placeTypes.contains(moduleType))
			return -1;
		return 0;
	}
}
