package com.tianque.plugin.transfer.util;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.baseInfo.overseaPersonnel.service.OverseaPersonnelService;
import com.tianque.baseInfo.unsettledPopulation.service.UnsettledPopulationService;
import com.tianque.core.datatransfer.DataType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.service.PopulationProccessorService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;

public class TransferUtil {
	private static Logger logger = LoggerFactory.getLogger(TransferUtil.class);

	// 判断是否是实口：
	public static boolean isActualPopulation(String type) {
		type = type.equals(Constants.OVERSEAPERSONNEL_KEY) ? PopulationType.OVERSEA_STAFF
				: type;
		PopulationCatalog catalog = PopulationCatalog.parse(type);
		return PopulationCatalog.ALL_ACTUAL_POPULATION.equals(catalog
				.getParentCatalog());
	}

	// 判断是否是重点人员：
	public static boolean isAllAttentionPopulation(String type) {
		type = type.equals(Constants.OVERSEAPERSONNEL_KEY) ? PopulationType.OVERSEA_STAFF
				: type;
		PopulationCatalog catalog = PopulationCatalog.parse(type);
		return PopulationCatalog.ALL_ATTENTION_POPULATION.equals(catalog
				.getParentCatalog())
				|| PopulationCatalog.ALL_LOVINGCARE_POPULATION.equals(catalog
						.getParentCatalog())
				|| PopulationCatalog.ALL_CIVIL_POPULATION.equals(catalog
						.getParentCatalog())
				|| PopulationCatalog.ALL_BIRTH_POPULATION.equals(catalog
						.getParentCatalog())
				|| PopulationCatalog.ALL_UNEMPLOYED_POPULATION.equals(catalog
						.getParentCatalog())
				|| PopulationCatalog.ALL_FXJ.equals(catalog.getParentCatalog())
				|| PopulationCatalog.ALL_GOODSAMARITAN.equals(catalog
						.getParentCatalog());
	}

	// 判断是否是组织场所
	public static boolean isOrgLocation(String type) {
		PopulationCatalog catalog = PopulationCatalog.parse(type);
		return PopulationCatalog.ALL_IMPORTANT_PLACE.equals(catalog
				.getParentCatalog());
	}

	// 判断是否是公安部件
	public static boolean isPublicSecurity(String type) {
		PopulationCatalog catalog = PopulationCatalog.parse(type);
		return PopulationCatalog.ALL_PUBLICSECURITY.equals(catalog
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
		if (Constants.RENTALHOUSE_KEY.equals(type)
				|| Constants.RENTALHOUSE_KEY.equals(type)) {
			MethodName = "getHouseInfoById";
		} else if (isEnterPrise(type)) {
			MethodName = "getEnterpriseById";
		} else if (Constants.PUBLICPLACE_KEY.equals(type)) {
			MethodName = "getPlaceById";
		} else if (Constants.NEWSOCIETYORGANIZATIONS_KEY.equals(type)) {
			MethodName = "getSimpleNewSocietyOrganizations";
		} else if (Constants.DANGEROUSGOODSPRACTITIONER_KEY.equals(type)) {
			MethodName = "getSimpleDangerousGoodsPractitionerById";
		} else if (Constants.OTHERATTENTIONPERSONNEL_KEY.equals(type)) {
			MethodName = "getSimpleOtherAttentionPersonnelById";
		} else if (Constants.ACTUALHOUSE_KEY.equals(type)
				|| Constants.RENTALHOUSE_KEY.equals(type)) {
			MethodName = "getHouseInfoById";
		} else {
			String newType = type.substring(0, 1).toUpperCase()
					+ type.substring(1);
			MethodName = String.format(name, newType);
		}

		return MethodName;
	}

	public static boolean isEnterPrise(String type) {
		if (Constants.SAFETYPRODUCTIONKEY_KEY.equals(type)
				|| Constants.FIRESAFETYKEY_KEY.equals(type)
				|| Constants.SECURITYKEY_KEY.equals(type)
				|| Constants.ENTERPRISEKEY_KEY.equals(type)
				|| Constants.ENTERPRISEDOWNKEY_KEY.equals(type)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getBigType(String type) {
		if (isActualPopulation(type) || isAllAttentionPopulation(type)) {
			return "person";
		} else if (isPublicSecurity(type)) {
			return "publicSecurity";
		}
		return "location";
	}

	/**
	 * 根据TYPE类型 和 编号 查询人口
	 * 
	 * @param populationType
	 * @param id
	 * @return
	 */
	public static People getPeopleByPopulationTypeAndId(String populationType,
			Long id) {
		People people = null;

		String type = populationType;
		if (BaseInfoTables.OVERSEAPERSONNEL_KEY.equals(populationType)) {
			type = PopulationType.OVERSEA_PERSONNEL;
		}
		if (BaseInfoTables.AIDSPOPULATIONS_KEY.equals(populationType)) {
			type = PopulationType.AIDSPOPULATIONS_KEY;
		}

		String methodName = "get" + StringUtil.firstCharUpperCase(type)
				+ "ById";
		try {
			Method[] methods = null;
			HouseholdStaffService householdStaffService = null;
			FloatingPopulationService floatingPopulationService = null;
			UnsettledPopulationService unsettledPopulationService = null;
			OverseaPersonnelService overseaPersonnelService = null;
			PopulationProccessorService populationProccessorService = null;

			if (BaseInfoTables.HOUSEHOLDSTAFF_KEY.equals(type)) {
				householdStaffService = (HouseholdStaffService) SpringBeanUtil
						.getBeanFromSpringByBeanName(populationType + "Service");
				methods = householdStaffService.getClass().getMethods();
			} else if (BaseInfoTables.FLOATINGPOPULATION_KEY.equals(type)) {
				floatingPopulationService = (FloatingPopulationService) SpringBeanUtil
						.getBeanFromSpringByBeanName(populationType + "Service");
				methods = floatingPopulationService.getClass().getMethods();
			} else if (BaseInfoTables.UNSETTEDPOPULATION_KEY.equals(type)) {
				unsettledPopulationService = (UnsettledPopulationService) SpringBeanUtil
						.getBeanFromSpringByBeanName(populationType + "Service");
				methods = unsettledPopulationService.getClass().getMethods();
			} else if (PopulationType.OVERSEA_PERSONNEL.equals(type)) {
				// 境外人员特殊处理
				overseaPersonnelService = (OverseaPersonnelService) SpringBeanUtil
						.getBeanFromSpringByBeanName("overseaStaffService");
				methods = overseaPersonnelService.getClass().getMethods();
			} else {
				// 艾滋病也要特殊处理
				if (PopulationType.AIDSPOPULATIONS_KEY.equals(type)) {
					populationType = "aidspopulation";
				}
				populationProccessorService = (PopulationProccessorService) SpringBeanUtil
						.getBeanFromSpringByBeanName(populationType + "Service");
				methods = populationProccessorService.getClass().getMethods();
			}

			boolean matchMethod = false;
			for (Method method : methods) {
				Class[] paramClasses = method.getParameterTypes();
				if (methodName.toUpperCase().equals(
						method.getName().toUpperCase())
						&& paramClasses != null && paramClasses.length == 1) {
					if (householdStaffService != null) {
						people = (People) method.invoke(householdStaffService,
								id);
					} else if (floatingPopulationService != null) {
						people = (People) method.invoke(
								floatingPopulationService, id);
					} else if (unsettledPopulationService != null) {
						people = (People) method.invoke(
								unsettledPopulationService, id);
					} else if (overseaPersonnelService != null) {
						people = (People) method.invoke(
								overseaPersonnelService, id);
					} else {
						people = (People) method.invoke(
								populationProccessorService, id);
					}
					matchMethod = true;
					break;
				}
			}
			if (!matchMethod) {
				throw new RuntimeException("列表缓存或CONT缓存中查询对应的人口，未找到对应方法["
						+ methodName + "]！");
			}
		} catch (Exception e) {
			logger.error("列表缓存或CONT缓存中查询对应的人口，未找到对应方法[" + methodName + "]！", e);
		}
		return people == null ? new People() : people;
	}
}
