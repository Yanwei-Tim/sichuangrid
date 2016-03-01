package com.tianque.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.core.util.DateUtil;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class ObjectConvertMapUtil {

	public static <T> Map<String, String> convertPopulation(T obj, Class cls)
			throws Exception {
		Map<String, String> map = convertThis(obj, cls);
		Class superCls = cls.getSuperclass();
		if (superCls != null) {
			map.putAll(convertPopulation(obj, superCls));
		}
		return map;
	}

	public static <T> Map<String, String> convertThis(T obj, Class cls)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			if (String.class.equals(field.getType())) {
				fillStringToMap(map, field, obj);
			} else if (PropertyDict.class.equals(field.getType())) {
				fillPropertyDictToMap(map, field, obj);
			} else if (CensusRegisterFamily.class.equals(field.getType())) {
				fillCensusRegisterFamilyToMap(map, field, obj);
			} else if (Date.class.equals(field.getType())) {
				fillDateToMap(map, field, obj);
			} else if (Long.class.equals(field.getType())) {
				fillLongToMap(map, field, obj);
			} else if (LogoutDetail.class.equals(field.getType())) {
				fillLogoutDetailToMap(map, field, obj);
			} else if (Organization.class.equals(field.getType())) {
				fillOrganizationToMap(map, field, obj);
			}
		}
		return map;
	}

	private static void fillStringToMap(Map<String, String> map, Field field,
			Object obj) throws Exception {
		Method m = (Method) obj.getClass().getMethod(
				"get" + getMethodName(field.getName()));
		String value = (String) m.invoke(obj);
		map.put(field.getName().toUpperCase(), value);
	}

	private static void fillLongToMap(Map<String, String> map, Field field,
			Object obj) throws Exception {
		Method m = (Method) obj.getClass().getMethod(
				"get" + getMethodName(field.getName()));
		Long value = (Long) m.invoke(obj);
		if ("id".equals(field.getName())) {
			map.put("DATAID", value == null ? null : value.toString());
		} else {
			map.put(field.getName().toUpperCase(),
					value == null ? null : value.toString());
		}
	}

	private static void fillPropertyDictToMap(Map<String, String> map,
			Field field, Object obj) throws Exception {
		Method m = (Method) obj.getClass().getMethod(
				"get" + getMethodName(field.getName()));
		PropertyDict value = (PropertyDict) m.invoke(obj);
		map.put(field.getName().toUpperCase(),
				value == null ? null : value.getId() == null ? null : value
						.getId().toString());
	}

	private static void fillCensusRegisterFamilyToMap(Map<String, String> map,
			Field field, Object obj) throws Exception {
		Method m = (Method) obj.getClass().getMethod(
				"get" + getMethodName(field.getName()));
		CensusRegisterFamily value = (CensusRegisterFamily) m.invoke(obj);
		map.put("FAMILYID", value == null ? null : value.getId() == null ? null
				: value.getId().toString());
	}

	private static void fillLogoutDetailToMap(Map<String, String> map,
			Field field, Object obj) throws Exception {
		Method m = (Method) obj.getClass().getMethod(
				"get" + getMethodName(field.getName()));
		LogoutDetail value = (LogoutDetail) m.invoke(obj);
		map.put("LOGOUT", value == null ? null
				: value.getLogout() == null ? null : value.getLogout()
						.toString());
		map.put("LOGOUTREASON",
				value == null ? null : value.getLogoutReason() == null ? null
						: value.getLogoutReason());
		map.put("LOGOUTDATE",
				value == null ? null : value.getLogoutDate() == null ? null
						: DateUtil.toString(value.getLogoutDate(),
								"yyyy-MM-dd HH:mm:ss"));
	}

	private static void fillOrganizationToMap(Map<String, String> map,
			Field field, Object obj) throws Exception {
		Method m = (Method) obj.getClass().getMethod(
				"get" + getMethodName(field.getName()));
		Organization value = (Organization) m.invoke(obj);
		map.put("ORGID", value == null ? null : value.getId() == null ? null
				: value.getId().toString());
	}

	private static void fillDateToMap(Map<String, String> map, Field field,
			Object obj) throws Exception {
		Method m = (Method) obj.getClass().getMethod(
				"get" + getMethodName(field.getName()));
		Date value = (Date) m.invoke(obj);
		map.put(field.getName().toUpperCase(),
				value == null ? null : DateUtil.toString(value,
						"yyyy-MM-dd HH:mm:ss"));
	}

	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

	public static void main(String[] args) throws Exception {
		HouseholdStaff hh = new HouseholdStaff();
		hh.setActualtype("sss");
		hh.setBirthday(new Date());
		PropertyDict rr = new PropertyDict();
		rr.setId(1333L);
		hh.setRelationShipWithHead(rr);
		hh.setId(22L);
		FloatingPopulation ff = new FloatingPopulation();
		ObjectConvertMapUtil.convertPopulation(hh, hh.getClass());
	}
}
