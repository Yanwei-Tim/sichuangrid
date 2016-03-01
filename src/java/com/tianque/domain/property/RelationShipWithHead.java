package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class RelationShipWithHead extends BaseProperty {
	public final static String KEY = PropertyTypes.RELATION_SHIP_WITH_HEAD;

	public final static int HEADER = 1;
	public final static int RELATION_SHIP_WITH_HEAD = 2;
	public final static int OTHER = 3;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(HEADER, "户主"));
		properties.add(getInternalProperty(RELATION_SHIP_WITH_HEAD, "与户主关系"));
		return properties;
	}
}
