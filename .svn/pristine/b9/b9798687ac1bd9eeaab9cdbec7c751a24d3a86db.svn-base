package com.tianque.plugin.dataManage.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.tianque.core.base.BaseDomain;
import com.tianque.exception.base.SystemUtilException;

public class DataManageConvertUtil {
	public static void convert(Class cls, Object target, Object source, String[] exceptArgs) {
		copyPropertiesWithOutRecursion(cls, target, source, exceptArgs);
		Class superCls = cls.getSuperclass();
		if (!superCls.equals(BaseDomain.class)) {
			convert(superCls, target, source, exceptArgs);
		}
		if (superCls.equals(BaseDomain.class)) {
			copyPropertiesWithOutRecursion(superCls, target, source, exceptArgs);
		}
	}

	public static void copyPropertiesWithOutRecursion(Class cls, Object target, Object source,
			String[] exceptArgs) {
		try {

			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				if (null != exceptArgs) {
					boolean next = false;
					for (int i = 0; i < exceptArgs.length; i++) {
						if (exceptArgs[i].equals(field.getName())) {
							next = true;
						}
					}
					if (next) {
						continue;
					}
				}
				int mod = field.getModifiers();
				if ((mod & Modifier.FINAL) != 0) {
					continue;
				}
				field.setAccessible(true);
				if (null != field.get(source)) {
					Field targetField = null;
					targetField = getTargetField(target.getClass(), field.getName());
					if (null == targetField) {
						continue;
					}
					targetField.setAccessible(true);
					targetField.set(target, field.get(source));
				}
			}
		} catch (Exception e) {
			throw new SystemUtilException("异常信息", e);
		}
	}

	private static Field getTargetField(Class cls, String fieldName) {
		Field targetField = null;
		try {
			targetField = cls.getDeclaredField(fieldName);
		} catch (Exception e) {
			if (cls.equals(BaseDomain.class)) {
				return null;
			} else {
				return getTargetField(cls.getSuperclass(), fieldName);
			}
		}
		return targetField;
	}
}
