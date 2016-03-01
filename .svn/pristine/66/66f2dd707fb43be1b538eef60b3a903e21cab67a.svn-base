package com.tianque.systemOperateLog.util;

import com.tianque.core.datatransfer.DataType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;

public class TransferUtilPop {
	// 判断是否是实口：
	public static boolean isActualPopulation(String type) {
		type = type.equals(ConstantsPop.OVERSEAPERSONNEL_KEY) ? PopulationType.OVERSEA_STAFF
				: type;
		PopulationCatalog catalog = PopulationCatalog.parse(type);
		return PopulationCatalog.ALL_ACTUAL_POPULATION.equals(catalog
				.getParentCatalog());
	}

	// 判断是否是重点人员：
	public static boolean isAllAttentionPopulation(String type) {
		type = type.equals(ConstantsPop.OVERSEAPERSONNEL_KEY) ? PopulationType.OVERSEA_STAFF
				: type;
		PopulationCatalog catalog = PopulationCatalog.parse(type);
		if (catalog != null) {
			return PopulationCatalog.ALL_ATTENTION_POPULATION.equals(catalog
					.getParentCatalog())
					|| PopulationCatalog.ALL_LOVINGCARE_POPULATION
							.equals(catalog.getParentCatalog())
					|| PopulationCatalog.ALL_CIVIL_POPULATION.equals(catalog
							.getParentCatalog())
					|| PopulationCatalog.ALL_BIRTH_POPULATION.equals(catalog
							.getParentCatalog())
					|| PopulationCatalog.ALL_UNEMPLOYED_POPULATION
							.equals(catalog.getParentCatalog())
					|| PopulationCatalog.ALL_FXJ.endsWith(catalog
							.getParentCatalog())
					|| PopulationCatalog.ALL_GOODSAMARITAN.endsWith(catalog
							.getParentCatalog());
		}
		return false;
	}

	// 判断是否是组织场所
	public static boolean isOrgLocation(String type) {
		PopulationCatalog catalog = PopulationCatalog.parse(type);
		return PopulationCatalog.ALL_IMPORTANT_PLACE.equals(catalog
				.getParentCatalog());
	}

	// 首字母大写
	public static String UpperFirstWord(String str) {
		return str.substring(0, 1).toUpperCase()
				+ str.substring(1, str.length());
	}

	public static String[][] downLoadData() {
		String[][] excelColumArray = {
				{ "0", "", "吸毒人员表", "", "", "true", "0", "35" },
				{ "0", "name", "姓名", "", "", "true" },
				{ "1", "organization", "所属网格", DataType.DATA_TYPE_ORG, "",
						"true" },
				{ "2", "gender", "性别", DataType.DATA_TYPE_DICT,
						PropertyTypes.GENDER, "true" },
				{ "3", "birthday", "出生日期", DataType.DATA_TYPE_DATE, "", "true" },
				{ "4", "idCardNo", "身份证号码", "", "", "true" },
				{ "5", "drugReason", "吸毒原因", DataType.DATA_TYPE_DICT,
						PropertyTypes.DRUG_REASON, "true" } };
		return excelColumArray;
	}

	// 特殊的 get****ById
	public static String getMethodNameByType(String type) {
		String name = "get%sById";
		String MethodName = "";
		if (ConstantsPop.RENTALHOUSE_KEY.equals(type)
				|| ConstantsPop.RENTALHOUSE_KEY.equals(type)) {
			MethodName = "getHouseInfoById";
		} else if (isEnterPrise(type)) {
			MethodName = "getEnterpriseById";
		} else if (ConstantsPop.PUBLICPLACE_KEY.equals(type)) {
			MethodName = "getPlaceById";
		} else if (ConstantsPop.NEWSOCIETYORGANIZATIONS_KEY.equals(type)) {
			MethodName = "getSimpleNewSocietyOrganizations";
		} else if (ConstantsPop.DANGEROUSGOODSPRACTITIONER_KEY.equals(type)) {
			MethodName = "getSimpleDangerousGoodsPractitionerById";
		} else if (ConstantsPop.OTHERATTENTIONPERSONNEL_KEY.equals(type)) {
			MethodName = "getSimpleOtherAttentionPersonnelById";
		} else if (ConstantsPop.ACTUALHOUSE_KEY.equals(type)
				|| ConstantsPop.RENTALHOUSE_KEY.equals(type)) {
			MethodName = "getHouseInfoById";
		} else {
			String newType = type.substring(0, 1).toUpperCase()
					+ type.substring(1);
			MethodName = String.format(name, newType);
		}

		return MethodName;
	}

	public static boolean isEnterPrise(String type) {
		if (ConstantsPop.SAFETYPRODUCTIONKEY_KEY.equals(type)
				|| ConstantsPop.FIRESAFETYKEY_KEY.equals(type)
				|| ConstantsPop.SECURITYKEY_KEY.equals(type)
				|| ConstantsPop.ENTERPRISEKEY_KEY.equals(type)
				|| ConstantsPop.ENTERPRISEDOWNKEY_KEY.equals(type)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getBigType(String type) {
		if (isActualPopulation(type) || isAllAttentionPopulation(type)) {
			return "person";
		}
		return "location";
	}

}
