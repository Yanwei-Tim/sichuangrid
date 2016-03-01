package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class ResourcePoolPremissionType extends BaseProperty {
	public final static int OWN = 0;
	public final static int DIRECTLY_UNDER = 1;
	public final static int ALL_UNDER = 2;

	public final static String OWN_LEVEL = "本级";
	public final static String DIRECTLY_UNDER_LEVEL = "直属下级";
	public final static String ALL_UNDER_LEVEL = "所有下辖";

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(OWN, OWN_LEVEL));
		properties.add(getInternalProperty(DIRECTLY_UNDER, DIRECTLY_UNDER_LEVEL));
		properties.add(getInternalProperty(ALL_UNDER, ALL_UNDER_LEVEL));
		return properties;
	}

	public final static String RESOURCEPOOL_VIEWOBJ_TYPE_KEY = PropertyTypes.RESOURCEPOOL_VIEWOBJ_TYPE;

}
