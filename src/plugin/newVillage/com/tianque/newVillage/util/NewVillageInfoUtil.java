package com.tianque.newVillage.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class NewVillageInfoUtil {

	public static Object dealInfo(Object basicInfo, Object dealData)
			throws Exception {
		Field[] basicInfoFiled = basicInfo.getClass().getDeclaredFields();
		Field[] dealDataFiled = dealData.getClass().getDeclaredFields();
		for (int i = 0; i < basicInfoFiled.length; i++) {
			String basicName = dealDataFiled[i].getName(); // 获取属性的名字
			String basicType = basicInfoFiled[i].getGenericType().toString(); // 获取属性的类型
			if (basicType.equals("class java.lang.Integer")
					|| basicType.equals("class java.lang.Double")) {
				for (int j = 0; j < dealDataFiled.length; j++) {
					String name = dealDataFiled[j].getName(); // 获取属性的名字
					if (basicName.equals(name)) {
						name = name.substring(0, 1).toUpperCase()
								+ name.substring(1); // 将属性的首字符大写，方便构造get，set方法
						basicName = basicName.substring(0, 1).toUpperCase()
								+ basicName.substring(1); // 将属性的首字符大写，方便构造get，set方法
						Method m = dealData.getClass().getMethod("get" + name);
						Method basicMode = basicInfo.getClass().getMethod(
								"get" + basicName);

						Integer tatal = 0;
						Double dabTatal = 0D;
						if (basicType.equals("class java.lang.Integer")) {
							Method setBasicMode = basicInfo.getClass()
									.getMethod("set" + name, Integer.class);
							Integer value = (Integer) m.invoke(dealData); // 获取需要增加的值
							Integer basicValue = (Integer) basicMode
									.invoke(basicInfo); // 获取原值
							if (value == null) {
								value = 0;
							}
							if (basicValue == null) {
								basicValue = 0;
							}
							tatal = value + basicValue;
							setBasicMode.invoke(basicInfo, tatal);
						} else if (basicType.equals("class java.lang.Double")) {
							Method setBasicMode = basicInfo.getClass()
									.getMethod("set" + name, Double.class);
							Double value = (Double) m.invoke(dealData); // 获取需要增加的值
							Double basicValue = (Double) basicMode
									.invoke(basicInfo); // 获取原值
							if (value == null) {
								value = 0D;
							}
							if (basicValue == null) {
								basicValue = 0D;
							}
							dabTatal = value + basicValue;
							setBasicMode.invoke(basicInfo, dabTatal);
						}

					}
				}
			}
		}
		return basicInfo;
	}

	//将集合中的同一数据对象类型中的属性值累加为一个对象
	public static <T> T dealList(List<T> list) throws Exception {
		if (list == null || list.size() == 0) {
			return null;
		}
		if (list.size() == 1) {
			return list.get(0);
		}
		Field[] fileds = list.get(0).getClass().getDeclaredFields();
		T firstT = list.get(0);
		for (Field field : fileds) {
			String fieldType = field.getGenericType().toString();
			//以下几种属性类型需要累加
			PropertyDescriptor propDesc = new PropertyDescriptor(
					field.getName(), firstT.getClass());
			if (fieldType.equals("class java.lang.Integer")) {
				//利用内省机制读值复制
				for (int i = 1; i < list.size(); i++) {
					propDesc.getWriteMethod().invoke(
							firstT,
							(Integer) propDesc.getReadMethod().invoke(
									list.get(i))
									+ (Integer) propDesc.getReadMethod()
											.invoke(firstT));
				}
			} else if (fieldType.equals("class java.lang.Double")) {
				for (int i = 1; i < list.size(); i++) {
					propDesc.getWriteMethod().invoke(
							firstT,
							(Double) (propDesc.getReadMethod().invoke(
									list.get(i)) == null ? 0 : propDesc
									.getReadMethod().invoke(list.get(i)))
									+ (Double) (propDesc.getReadMethod()
											.invoke(firstT) == null ? 0
											: propDesc.getReadMethod().invoke(
													firstT)));
				}
			} else if (fieldType.equals("class java.lang.Long")) {
				for (int i = 1; i < list.size(); i++) {
					propDesc.getWriteMethod().invoke(
							firstT,
							(Long) propDesc.getReadMethod().invoke(list.get(i))
									+ (Long) propDesc.getReadMethod().invoke(
											firstT));
				}
			}
		}
		return firstT;
	}
}
