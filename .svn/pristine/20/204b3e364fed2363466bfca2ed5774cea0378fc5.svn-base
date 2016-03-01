package com.tianque.util;

import java.lang.reflect.Method;

import com.tianque.core.util.SpringBeanUtil;

public class PluginServiceHelpUtil {
	/**
	 * 系统调动插件工具(无返回值)
	 * 
	 * @param beanName
	 *        Spring名称
	 * @param methodName
	 *        要调用的方法名
	 * @param argTypes
	 *        参数的类型，数组
	 * @param args
	 *        不定项参数，个数、顺序与参数类型数组一致
	 * @throws Exception
	 */
	public static void doService(String beanName, String methodName, Class[] argTypes,
			Object... args) throws Exception {
		Object pluginService = SpringBeanUtil.getBeanFromSpringByBeanName(beanName);
		Method method = pluginService.getClass().getDeclaredMethod(methodName, argTypes);
		method.invoke(pluginService, args);
	}

	/**
	 * 系统调动插件工具(有返回值)
	 * 
	 * @param beanName
	 *        Spring名称
	 * @param methodName
	 *        要调用的方法名
	 * @param argTypes
	 *        参数的类型，数组
	 * @param args
	 *        不定项参数，个数、顺序与参数类型数组一致
	 * @throws Exception
	 */
	public static Object doServiceGetResult(String beanName, String methodName, Class[] argTypes,
			Object... args) throws Exception {
		Object pluginService = SpringBeanUtil.getBeanFromSpringByBeanName(beanName);
		Method method = pluginService.getClass().getDeclaredMethod(methodName, argTypes);
		return method.invoke(pluginService, args);
	}
}
