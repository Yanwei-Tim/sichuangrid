package com.tianque.controller.convert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;

public class PopulationTypeConverter {

	private final static Logger logger = LoggerFactory.getLogger(PopulationTypeConverter.class);

	public static HouseholdStaff convertAttentionPopulationToHouseholdStaff(
			AttentionPopulation population) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		HouseholdStaff householdStaff = new HouseholdStaff();

		// frm中方法名称
		String frmMtdName;
		// 方法描述 包括修饰符、返回值、方法名、参数
		String frmGeneric;
		// 返回值类型
		String returnType;

		// dest中方法名称
		String destMtdName;
		// 方法对应的对象名称
		String destFieldName;
		// 方法描述 包括修饰符、返回值、方法名、参数
		String destGeneric;
		// 参数类型
		String paramType;

		Method[] methods = Countrymen.class.getMethods();
		for (Method destMtd : methods) {
			destMtdName = destMtd.getName(); // 获得方法名称

			if (destMtdName.startsWith("set")) { // 判断方法是否为setter方法
				destFieldName = destMtdName.replace("set", ""); // 取得变量名称（即去掉setter方法中的“set”）
				logger.info(destFieldName);
				destGeneric = destMtd.toGenericString(); // 取得方法描述 包括修饰符、返回值、方法名、参数
				logger.info(destGeneric);
				paramType = destGeneric.split(destMtdName)[1]; // 取得方法名称之后的字符串
				paramType = paramType.substring(1, paramType.length() - 1); // 去掉左右括号 得到参数类型
				logger.info(paramType);
				for (Method frmMtd : methods) {
					frmMtdName = frmMtd.getName(); // 获得方法名称
					// 判断frmMtdName方法是否为getter方法并且变量名称(第一个字母)等于frm中的对象名称
					if (frmMtdName.startsWith("get")
							&& frmMtdName.replace("get", "").equals(destFieldName)) {
						frmGeneric = frmMtd.toGenericString(); // 获得getter方法的返回值
						logger.info(frmGeneric);
						// 取得以空格split后数组倒数第一个字符串，即方法的返回值
						returnType = frmGeneric.split(" ")[frmGeneric.split(" ").length - 2];
						logger.info(returnType);
						// 判断frm中getter方法的返回值类型是否等于dest对象中变量的setter方法的参数类型
						if (paramType != null && paramType.equals(returnType)) {
							// 将frm对象中getter方法的返回值为参数调用dest对象中同名变量的setter方法
							destMtd.invoke(householdStaff,
									frmMtd.invoke(population, (Object[]) null));
						}
					}
				}
			}
		}
		return householdStaff;
	}

	public static FloatingPopulation convertAttentionPopulationToFloatingPopulation(
			AttentionPopulation population) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		FloatingPopulation floatingPopulation = new FloatingPopulation();

		// frm中方法名称
		String frmMtdName;
		// 方法描述 包括修饰符、返回值、方法名、参数
		String frmGeneric;
		// 返回值类型
		String returnType;

		// dest中方法名称
		String destMtdName;
		// 方法对应的对象名称
		String destFieldName;
		// 方法描述 包括修饰符、返回值、方法名、参数
		String destGeneric;
		// 参数类型
		String paramType;

		Method[] methods = Countrymen.class.getMethods();
		for (Method destMtd : methods) {
			destMtdName = destMtd.getName(); // 获得方法名称

			if (destMtdName.startsWith("set")) { // 判断方法是否为setter方法
				destFieldName = destMtdName.replace("set", ""); // 取得变量名称（即去掉setter方法中的“set”）
				logger.info(destFieldName);
				destGeneric = destMtd.toGenericString(); // 取得方法描述 包括修饰符、返回值、方法名、参数
				logger.info(destGeneric);
				paramType = destGeneric.split(destMtdName)[1]; // 取得方法名称之后的字符串
				paramType = paramType.substring(1, paramType.length() - 1); // 去掉左右括号 得到参数类型
				logger.info(paramType);
				for (Method frmMtd : methods) {
					frmMtdName = frmMtd.getName(); // 获得方法名称
					// 判断frmMtdName方法是否为getter方法并且变量名称(第一个字母)等于frm中的对象名称
					if (frmMtdName.startsWith("get")
							&& frmMtdName.replace("get", "").equals(destFieldName)) {
						frmGeneric = frmMtd.toGenericString(); // 获得getter方法的返回值
						logger.info(frmGeneric);
						// 取得以空格split后数组倒数第一个字符串，即方法的返回值
						returnType = frmGeneric.split(" ")[frmGeneric.split(" ").length - 2];
						logger.info(returnType);
						// 判断frm中getter方法的返回值类型是否等于dest对象中变量的setter方法的参数类型
						if (paramType != null && paramType.equals(returnType)) {
							// 将frm对象中getter方法的返回值为参数调用dest对象中同名变量的setter方法
							destMtd.invoke(floatingPopulation,
									frmMtd.invoke(population, (Object[]) null));
						}
					}
				}
			}
		}
		return floatingPopulation;
	}
}
