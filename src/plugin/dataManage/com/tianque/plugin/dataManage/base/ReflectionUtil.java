package com.tianque.plugin.dataManage.base;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.tianque.core.util.SpringBeanUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.SystemUtilException;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.location.companyPlaceTemp.domain.CompanyPlaceTemp;
import com.tianque.plugin.dataManage.location.enterpriseTemp.domain.EnterpriseTemp;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;

public class ReflectionUtil {
	private static final Logger logger = Logger.getLogger(ReflectionUtil.class);

	/**
	 * 根据方法名称执行某个对象的get方法
	 * 
	 * @param population
	 * @param methodName
	 * @return
	 * @throws Exception
	 */
	public static <T> Object executeTargetGetMethod(T population,
			String methodName) {
		try {
			Method method = population.getClass().getMethod(methodName);
			return method.invoke(population);
		} catch (Exception e) {
			throw new SystemUtilException("获取"
					+ population.getClass().getSimpleName() + "的" + methodName
					+ "出错");
		}
	}

	/**
	 * 根据方法名和值，给目标对象设置string类型的属性值
	 * 
	 * @param population
	 * @param methodName
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static <T> void executeTargetSetStringProperty(T population,
			String methodName, String value) throws Exception {
		Method method = population.getClass().getMethod(methodName,
				String.class);
		method.invoke(population, new Object[] { value });
	}

	public static <T> Object executePopulationHasDuplicateMethod(T population)
			throws Exception {
		Object target = SpringBeanUtil
				.getBeanFromSpringByBeanName(ReflectionUtil
						.getServiceBeanName(population));

		Long orgId = getTargetOrganization(population).getId();
		String idCardNo = (String) executeTargetGetMethod(population,
				"getIdCardNo");

		String hasDuplicateMethod = "hasDuplicate"
				+ ReflectionUtil.getClassNameRemoveTempSuffix(population);

		Method method = target.getClass().getMethod(hasDuplicateMethod,
				Long.class, String.class);
		return method.invoke(target, new Object[] { orgId, idCardNo });

	}

	public static <T> Object executeOverseaPersonnelHasDuplicateMethod(
			T population) throws Exception {
		Object target = SpringBeanUtil
				.getBeanFromSpringByBeanName(ReflectionUtil
						.getServiceBeanName(population));

		Long orgId = getTargetOrganization(population).getId();
		String certificateNo = (String) executeTargetGetMethod(population,
				"getCertificateNo");
		PropertyDict certificateType = (PropertyDict) executeTargetGetMethod(
				population, "getCertificateType");
		Long certificateTypes = certificateType.getId();

		String hasDuplicateMethod = "hasDuplicate"
				+ ReflectionUtil.getClassNameRemoveTempSuffix(population);

		Method method = target.getClass().getMethod(hasDuplicateMethod,
				Long.class, Long.class, String.class);
		return method.invoke(target, new Object[] { orgId, certificateTypes,
				certificateNo });

	}

	public static <T> Object executeLocationHasDuplicateMethod(T population,
			String searchArg) throws Exception {
		String beanName = "";
		if (population instanceof EnterpriseTemp) {
			beanName = "enterpriseService";
		} else {
			beanName = ReflectionUtil.getServiceBeanName(population);
		}
		Object target = SpringBeanUtil.getBeanFromSpringByBeanName(beanName);
		Long orgId = getTargetOrganization(population).getId();
		if ("getPlaceName".equals(searchArg)) {
			try {
				searchArg = (String) executeTargetGetMethod(population,
						"getPlaceName");
			} catch (Exception e) {
				try {
					searchArg = (String) executeTargetGetMethod(population,
							"getCompanyName");
				} catch (Exception e1) {
					try {
						searchArg = (String) executeTargetGetMethod(population,
								"getUnitName");
					} catch (Exception e2) {
						searchArg = (String) executeTargetGetMethod(population,
								"getName");
					}
				}
			}
		}
		String hasDuplicateMethod = "hasDuplicate"
				+ ReflectionUtil.getClassNameRemoveTempSuffix(population);
		// 未三大重点场所配置的条件选择
		String className = population.getClass().getSimpleName();
		if ("SafetyProductionTemp".equals(className)) {
			Method method = target.getClass().getMethod(hasDuplicateMethod,
					Long.class, String.class, String.class);
			return method.invoke(target, new Object[] { orgId, searchArg,
					"safetyProductionKey" });
		} else if ("SecurityEnterpriseTemp".equals(className)) {
			Method method = target.getClass().getMethod(hasDuplicateMethod,
					Long.class, String.class, String.class);
			return method.invoke(target, new Object[] { orgId, searchArg,
					"securityKey" });
		} else if ("EnterpriseKeyTemp".equals(className)) {
			Method method = target.getClass().getMethod(hasDuplicateMethod,
					Long.class, String.class, String.class);
			return method.invoke(target, new Object[] { orgId, searchArg,
					"enterpriseKey" });
		} else if ("EnterpriseDownKeyTemp".equals(className)) {
			Method method = target.getClass().getMethod(hasDuplicateMethod,
					Long.class, String.class, String.class);
			return method.invoke(target, new Object[] { orgId, searchArg,
					"enterpriseDownKey" });
		} else if ("CompanyPlaceTemp".equals(className)) {
			Method method = target.getClass().getMethod(hasDuplicateMethod,
					Long.class, String.class, Long.class);
			CompanyPlaceTemp companyPlace = (CompanyPlaceTemp) population;
			return method.invoke(target, new Object[] { orgId, searchArg,
					companyPlace.getClassifiCation().getId() });
		} else {
			Method method = target.getClass().getMethod(hasDuplicateMethod,
					Long.class, String.class);
			return method.invoke(target, new Object[] { orgId, searchArg });
		}
	}

	/**
	 * 设置某个对象的organization
	 * 
	 * @param population
	 * @param org
	 */
	public static <T> void setTargetOrganization(T population, Organization org) {
		Method method;
		try {
			method = population.getClass().getMethod("setOrganization",
					Organization.class);
			method.invoke(population, new Object[] { org });
		} catch (Exception e) {
			throw new SystemUtilException("给对象"
					+ population.getClass().getSimpleName() + "设置组织机构出错");
		}
	}

	/**
	 * 获取某个对象的Organization
	 * 
	 * @param population
	 * @return
	 * @throws Exception
	 */
	public static <T> Organization getTargetOrganization(T population)
			throws Exception {
		return (Organization) executeTargetGetMethod(population,
				"getOrganization");
	}

	/**
	 * 获取某个对象的生分证号码
	 * 
	 * @param population
	 * @return
	 * @throws Exception
	 */
	public static <T> String getTargetIdCardNo(T population) throws Exception {
		return (String) executeTargetGetMethod(population, "getIdCardNo");
	}

	/**
	 * 获取某个对象的证件种类
	 * 
	 * @param population
	 * @return
	 * @throws Exception
	 */
	public static <T> PropertyDict getTargetCertificateType(T population)
			throws Exception {
		return (PropertyDict) executeTargetGetMethod(population,
				"getCertificateType");
	}

	/**
	 * 获取某个对象的证件号码
	 * 
	 * @param population
	 * @return
	 * @throws Exception
	 */
	public static <T> String getTargetCertificateNo(T population)
			throws Exception {
		return (String) executeTargetGetMethod(population, "getCertificateNo");
	}

	/**
	 * 获取某个对象的认领状态详细信息
	 * 
	 * @param population
	 * @return
	 * @throws Exception
	 */
	public static <T> ClaimDetail getTargetClaimDetail(T population) {
		ClaimDetail claimDetail = (ClaimDetail) executeTargetGetMethod(
				population, "getClaimDetail");
		if (null == claimDetail) {
			claimDetail = new ClaimDetail();
		}
		return claimDetail;
	}

	public static <T> String getServiceBeanName(T population) {
		if (population.getClass().getSimpleName()
				.equals(DataManageBaseInfoTypes.OVERSEA_PERSONNEL_TEMP)) {
			return "overseaStaffService";
		} else if (population.getClass().getSimpleName()
				.equals(DataManageBaseInfoTypes.AIDS_POPULCATIONS_TEMP)) {
			return "aidspopulationService";
		} else {
			return getServiceBeanRemoveTempSuffix(population) + "Service";
		}

	}

	public static <T> String getServiceBeanRemoveTempSuffix(T population) {
		return conventClassNameFirstLower(getClassNameRemoveTempSuffix(population));
	}

	/**
	 * 获取去掉temp后缀之后的类名 DruggyTemp->Druggy
	 * 
	 * @param population
	 * @return
	 */
	public static <T> String getClassNameRemoveTempSuffix(T population) {
		String className = population.getClass().getSimpleName();
		if ("SafetyProductionTemp".equals(className)
				|| "SecurityEnterpriseTemp".equals(className)
				|| "EnterpriseKeyTemp".equals(className)
				|| "EnterpriseDownKeyTemp".equals(className)) {
			return "Enterprise";
		} else {
			return className.substring(0, className.lastIndexOf("Temp"));
		}
	}

	/**
	 * 获取类名 DruggyTemp
	 * 
	 * @param temp
	 * @return
	 */
	public static <T> String getClassNameSuffix(T temp) {
		return temp.getClass().getSimpleName();
	}

	public static <T> String conventClassNameFirstLower(String className) {
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}

	/**
	 * 设置某个对象的actualPopulationType
	 * 
	 * @param population
	 * @param actualPopulationType
	 */
	public static <T> void setActualPopulationType(T population,
			String actualPopulationType) {
		Method method;
		try {
			method = population.getClass().getMethod("setActualPopulationType",
					String.class);
			method.invoke(population, new Object[] { actualPopulationType });
		} catch (Exception e) {
			logger.error("给对象" + population.getClass().getSimpleName()
					+ "设置actualPopulationType出错", e);
			throw new SystemUtilException("给对象"
					+ population.getClass().getSimpleName() + "设置实口类型出错");
		}
	}
}
