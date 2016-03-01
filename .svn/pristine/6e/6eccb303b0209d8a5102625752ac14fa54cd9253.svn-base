package com.tianque.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.base.BaseDomain;
import com.tianque.exception.base.OperationFailedException;

public class PropertyUtil {

	/**
	 * 将source对象的非空属性赋值给target对象
	 */
	@Deprecated
	public static Object copySourceToTargetWithoutNullProperties(Object target,
			Object source) throws Exception {
		Map<String, Object> parameter = BeanUtils.describe(target);
		Set<Entry<String, Object>> entries = parameter.entrySet();
		Iterator<Entry<String, Object>> iterator = entries.iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = (Entry) iterator.next();
			String key = entry.getKey();
			Object value = PropertyUtils.getProperty(source, key);
			if (null != value) {
				BeanUtils.setProperty(target, key, value);
			}
		}
		return target;
	}

	/**
	 * 递归拷贝属性，直到父类BaseDomain为止
	 * 
	 * @param cls
	 *            类名
	 * @param target
	 *            copy到哪个对象
	 * @param source
	 *            从哪个对象copy
	 * @throws Exception
	 */
	public static void copyPropertiesWithRecursion(Class cls, Object target,
			Object source) {
		copyPropertiesWithOutRecursion(cls, target, source);
		Class superCls = cls.getSuperclass();
		if (!superCls.equals(BaseDomain.class)) {
			copyPropertiesWithRecursion(superCls, target, source);
		}
	}

	/**
	 * 递归拷贝属性
	 * 
	 * @param cls
	 *            类名
	 * @param target
	 *            copy到哪个对象
	 * @param source
	 *            从哪个对象copy
	 * @throws Exception
	 */
	public static void copyAllPropertiesWithRecursion(Class cls, Object target,
			Object source) {
		copyPropertiesWithOutRecursion(cls, target, source);
		Class superCls = cls.getSuperclass();
		if (!superCls.equals(BaseDomain.class)) {
			copyAllPropertiesWithRecursion(superCls, target, source);
		}
		if (superCls.equals(BaseDomain.class)) {
			copyPropertiesWithOutRecursion(superCls, target, source);

		}
	}

	/**
	 * 递归拷贝属性，直到父类BaseDomain为止
	 * 
	 * @param cls
	 *            类名
	 * @param target
	 *            copy到哪个对象
	 * @param source
	 *            从哪个对象copy
	 * @param excludeProperties
	 *            不包括的属性
	 * @throws Exception
	 */
	public static void copyPropertiesWithRecursion(Class cls, Object target,
			Object source, String[] excludeProperties) {
		copyPropertiesWithOutRecursion(cls, target, source, excludeProperties);
		Class superCls = cls.getSuperclass();
		if (!superCls.equals(BaseDomain.class)) {
			copyPropertiesWithRecursion(superCls, target, source,
					excludeProperties);
		}
	}

	/**
	 * 拷贝本类属性，不递归copy
	 * 
	 * @param cls
	 *            类名
	 * @param target
	 *            copy到哪个对象
	 * @param source
	 *            从哪个对象copy
	 * @param excludeProperties
	 *            不包括的属性
	 * @throws Exception
	 */
	public static void copyPropertiesWithOutRecursion(Class cls, Object target,
			Object source, String[] excludeProperties) {
		Field[] fields = cls.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (!(null != excludeProperties && excludeProperties.length > 0 && includeStr(
						field.getName(), excludeProperties))) {
					field.setAccessible(true);
					field.set(target, field.get(source));
				}
			}
		} catch (Exception e) {
			throw new OperationFailedException("属性复制异常", e);
		}
	}

	public static boolean includeStr(String str, String[] array) {
		for (int i = 0; i < array.length; i++)
			if (str.equals(array[i]))
				return true;
		return false;
	}

	/**
	 * 拷贝本类属性，不递归copy
	 * 
	 * @param cls
	 *            类名
	 * @param target
	 *            copy到哪个对象
	 * @param source
	 *            从哪个对象copy
	 * @throws Exception
	 */
	public static void copyPropertiesWithOutRecursion(Class cls, Object target,
			Object source) {
		try {
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if ((mod & Modifier.FINAL) != 0) {
					continue;
				}
				field.setAccessible(true);
				field.set(target, field.get(source));
			}
		} catch (Exception e) {
			throw new OperationFailedException("属性复制异常", e);
		}
	}

	public static void main(String[] args) {
		Field[] fields = Countrymen.class.getDeclaredFields();
		System.out.println(fields.length);
		for (Field field : fields) {
			System.out.println(field.getName());
		}
	}
}
