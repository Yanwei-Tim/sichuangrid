package com.tianque.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.core.util.DateUtil;
import com.tianque.domain.PropertyDict;

public class Arrays {
	public static boolean hasObjectInArray(Object[] array, Object obj) {
		if (null == array || array.length < 0 || null == obj) {
			return false;
		}
		for (Object arr : array) {
			if (obj.equals(arr)) {
				return true;
			}
		}
		return false;
	}

	public static String getStringValueFromArray(Map<String, Object> map,
			String key) {
		Object value = map.get(key);
		if (null != value) {
			String[] strings = (String[]) value;
			if (null == strings || strings.length == 0) {
				return null;
			}
			return strings[0];
		}
		return null;
	}

	public static String[] getStringArrayValueFromArray(
			Map<String, Object> map, String key) {
		Object value = map.get(key);
		if (null != value) {
			String[] strings = (String[]) value;
			if (null == strings || strings.length == 0) {
				return null;
			}
			return strings;
		}
		return null;
	}

	public static Long getLongValueFromArray(Map<String, Object> map, String key) {
		Object value = map.get(key);
		if (null != value) {
			String[] strings = (String[]) value;
			if (null == strings || strings.length == 0
					|| "".equals(strings[0].trim())) {
				return null;
			}
			return Long.valueOf(strings[0]);
		}
		return null;
	}

	public static PropertyDict getPropertyDictFromArray(
			Map<String, Object> map, String key) {
		Object value = map.get(key);
		if (null != value) {
			String[] strings = (String[]) value;
			if (null == strings || strings.length == 0
					|| "".equals(strings[0].trim())) {
				return null;
			}
			PropertyDict propertyDict = new PropertyDict();
			propertyDict.setId(Long.valueOf(strings[0]));
			return propertyDict;
		}
		return null;
	}

	public static List<PropertyDict> getPropertyDictsFromArray(
			Map<String, Object> map, String key) {
		Object value = map.get(key);
		if (null != value) {
			String[] strings = (String[]) value;
			/*
			 * if (null == strings || strings.length == 0 ||
			 * "".equals(strings[0].trim())) { return null; }
			 */
			List<PropertyDict> dicts = new ArrayList<PropertyDict>();
			for (int i = 0; i < strings.length; i++) {
				if (null != strings[i] && !"".equals(strings[i])) {
					PropertyDict propertyDict = new PropertyDict();
					propertyDict.setId(Long.valueOf(strings[i]));
					dicts.add(propertyDict);
				}
			}
			return dicts;
		}
		return null;
	}

	public static Double getDoubleValueFromArray(Map<String, Object> map,
			String key) {
		Object value = map.get(key);
		if (null != value) {
			String[] strings = (String[]) value;
			if (null == strings || strings.length == 0
					|| "".equals(strings[0].trim())) {
				return null;
			}
			return Double.valueOf(strings[0]);
		}
		return null;
	}

	public static Date getDateValueFromArray(Map<String, Object> map, String key) {
		Object value = map.get(key);
		if (null != value) {
			String[] strings = (String[]) value;
			if (null == strings || strings.length == 0
					|| "".equals(strings[0].trim())) {
				return null;
			}
			return DateUtil.parseDate(strings[0], "yyyy-MM-dd");
		}
		return null;
	}

	public static Boolean getBooleanValueFromArray(Map<String, Object> map,
			String key) {
		Object value = map.get(key);
		if (null != value) {
			String[] strings = (String[]) value;
			if (null == strings || strings.length == 0
					|| "".equals(strings[0].trim())) {
				return null;
			}
			return Boolean.valueOf(strings[0]);
		}
		return null;
	}

	public static int getIntValueFromArray(Map<String, Object> map, String key) {
		Object value = map.get(key);
		if (null != value) {
			String[] strings = (String[]) value;
			if (null == strings || strings.length == 0
					|| "".equals(strings[0].trim())) {
				return 0;
			}
			return Integer.valueOf(strings[0]);
		}
		return 0;
	}

	public static List<Long> getLongsFromArray(Map<String, Object> map,
			String key) {
		Object value = map.get(key);
		if (null != value) {
			Long[] strings = null;
			if (value instanceof String[]) {
				String[] val = (String[]) value;
				strings = new Long[val.length];
				for (int i = 0; i < val.length; i++) {
					if (null != val[i] && !"".equals(val[i]))
						strings[i] = Long.valueOf(val[i]);
				}
			} else {
				strings = (Long[]) value;
			}
			if (null == strings || strings.length == 0) {
				return null;
			}
			List<Long> longIds = new ArrayList<Long>();
			for (int i = 0; i < strings.length; i++) {
				longIds.add(strings[i]);
			}
			return longIds;
		}
		return null;
	}
}
