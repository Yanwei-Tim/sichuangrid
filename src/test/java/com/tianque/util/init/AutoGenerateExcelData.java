package com.tianque.util.init;

import java.lang.reflect.Field;
import java.util.Date;

import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.core.base.BaseDomain;

public class AutoGenerateExcelData {

	/**
	 * @param args
	 * @throws Exception
	 */

	private static int Index = 0;

	public static void main(String[] args) throws Exception {
		// Map<String, String> map = ExcelInit.getPropertyColumnMap();
		for (int i = 0; i < 10; i++) {
			AutoGenerateExcelData.Index = i;
			Druggy druggy = new Druggy();
			generatePropertiesWithRecursion(Druggy.class, druggy, null);
			System.out.println(druggy);
		}
	}

	private static void copyPropertiesWithOutRecursion(Class cls, Object target,
			String[] excludeProperties) throws Exception {
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			if (!(null != excludeProperties && excludeProperties.length > 0 && includeStr(
					field.getName(), excludeProperties))) {
				System.out.println(field.getName());
				field.setAccessible(true);
				if (field.getType().equals(String.class)) {
					field.set(target, "aaaaaa" + AutoGenerateExcelData.Index);
				}
				if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
					field.set(target, 1 + AutoGenerateExcelData.Index);
				}
				if (field.getType().equals(Long.class) || field.getType().equals(Long.class)) {
					field.set(target, new Long(1 + AutoGenerateExcelData.Index));
				}
				if (field.getType().equals(Date.class)) {
					field.set(target, new Date());
				}
				if (BaseDomain.class.isAssignableFrom(field.getType())) {
					BaseDomain baseDomain = (BaseDomain) field.getType().newInstance();
					baseDomain.setId(1L);
					field.set(target, baseDomain);
				}
			}
		}
	}

	private static boolean includeStr(String str, String[] array) {
		for (int i = 0; i < array.length; i++)
			if (str.equals(array[i]))
				return true;
		return false;
	}

	private static void generatePropertiesWithRecursion(Class cls, Object target,
			String[] excludeProperties) throws Exception {
		copyPropertiesWithOutRecursion(cls, target, excludeProperties);
		Class superCls = cls.getSuperclass();
		if (!superCls.equals(BaseDomain.class)) {
			generatePropertiesWithRecursion(superCls, target, excludeProperties);
		}
	}
}
