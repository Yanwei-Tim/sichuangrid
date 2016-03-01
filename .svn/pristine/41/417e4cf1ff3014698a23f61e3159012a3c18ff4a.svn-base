package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class CurrentHouseSource extends BaseProperty {

	public final static int OWN_PROPERTY = 1;
	public final static int RENTAL_BUILDINGS = 2;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(OWN_PROPERTY, "自有产权"));
		properties.add(getInternalProperty(RENTAL_BUILDINGS, "租赁公房"));

		return properties;
	}

}
