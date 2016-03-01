package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class HouseInfoType extends BaseProperty {

	public final static int OTHER = 1;
	public final static int OWNLIVE_HOUSE = 2;
	public final static int RENTAL_HOUSE = 3;
	public final static int COMPANY_HOUSE = 4;
	public final static int IDLE_HOUSE = 5;
	public final static int ACTUAL_HOUSE = 6;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(OTHER, "其他"));
		properties.add(getInternalProperty(OWNLIVE_HOUSE, "自住房"));
		properties.add(getInternalProperty(RENTAL_HOUSE, "出租房"));
		properties.add(getInternalProperty(COMPANY_HOUSE, "集体（单位福利）房"));
		properties.add(getInternalProperty(IDLE_HOUSE, "闲置房"));
		properties.add(getInternalProperty(ACTUAL_HOUSE, "实有房屋"));
		return properties;
	}

}
