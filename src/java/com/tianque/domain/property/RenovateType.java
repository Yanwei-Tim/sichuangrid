package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class RenovateType extends BaseProperty {
	public final static String LISTENCORRECTION = "listedCorrection";
	public final static int RENOVATE_TYPE_PROVINCE = 1;
	public final static int RENOVATE_TYPE_CITY = 2;
	public final static int RENOVATE_TYPE_COUNTY = 3;

	public final static String PROVINCE_NAME = "省挂牌整治";
	public final static String CITY_NAME = "市挂牌整治";
	public final static String COUNTY_NAME = "县挂牌整治";

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(RENOVATE_TYPE_PROVINCE,
				PROVINCE_NAME));
		properties.add(getInternalProperty(RENOVATE_TYPE_CITY, CITY_NAME));
		properties.add(getInternalProperty(RENOVATE_TYPE_COUNTY, COUNTY_NAME));

		return properties;
	}

	public static String getRenovateTypeMap(Object key) {
		Integer keyInt = Integer.valueOf(key.toString());
		if (RENOVATE_TYPE_PROVINCE == keyInt) {
			return PROVINCE_NAME;
		} else if (RENOVATE_TYPE_CITY == keyInt) {
			return CITY_NAME;
		} else if (RENOVATE_TYPE_COUNTY == keyInt) {
			return COUNTY_NAME;
		} else {
			return null;
		}
	}
}
