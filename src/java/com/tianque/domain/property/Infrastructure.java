package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class Infrastructure extends BaseProperty {
	public final static String INFRASTRUCTURE_KEY = PropertyTypes.INFRASTRUCTURE;

	public final static int WATER = 10;
	public final static int ELECTRICITY = 20;
	public final static int GAS = 30;
	public final static int WARM = 40;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(WATER, "水"));
		properties.add(getInternalProperty(ELECTRICITY, "电"));
		properties.add(getInternalProperty(GAS, "气"));
		properties.add(getInternalProperty(WARM, "暖"));
		return properties;
	}
}
