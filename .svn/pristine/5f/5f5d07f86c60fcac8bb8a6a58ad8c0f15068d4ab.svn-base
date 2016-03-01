package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class ServiceManagementTypes extends BaseProperty {

	public final static int SERVICE_MANAGEMENT_CITY = 12;
	public final static int SERVICE_MANAGEMENT_TOWN = 13;
	public final static int SERVICE_MANAGEMENT_VILLAGE = 14;

	public final static String SERVICE_MANAGEMENT_CITY_NAME = "社会服务管理中心建设(县及以上使用)";
	public final static String SERVICE_MANAGEMENT_TOWN_NAME = "社会服务管理中心建设(乡镇使用)";
	public final static String SERVICE_MANAGEMENT_VILLAGE_NAME = "社会服务管理室（站）建设(村社区使用)";

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(SERVICE_MANAGEMENT_CITY, SERVICE_MANAGEMENT_CITY_NAME));
		properties.add(getInternalProperty(SERVICE_MANAGEMENT_TOWN, SERVICE_MANAGEMENT_TOWN_NAME));
		properties.add(getInternalProperty(SERVICE_MANAGEMENT_VILLAGE,
				SERVICE_MANAGEMENT_VILLAGE_NAME));
		return properties;
	}

	public final static String DAILY_DIRECTORY_TYPES_KEY = PropertyTypes.DAILYDIRECTORY_TYPE;
}
