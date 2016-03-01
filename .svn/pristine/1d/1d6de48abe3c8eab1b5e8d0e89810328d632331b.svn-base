package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class CurrentAddressType extends BaseProperty {
	public final static String ADDRESS_KEY = PropertyTypes.CURRENT_ADDRESS_TYPE;

	public final static int OTHER = 1;
	public final static int BUSINESS_HOUSE = 2;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(OTHER, "其他"));
		properties.add(getInternalProperty(BUSINESS_HOUSE, "商品房"));

		return properties;
	}

}
