package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class Insurance extends BaseProperty {
	public final static int PENSION = 1;
	public final static int MEDICAL = 2;
	public final static int UNEMPLOYMENT = 3;
	public final static int BUSINESS = 4;
	public final static int FERTILITY = 5;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(PENSION, "养老"));
		properties.add(getInternalProperty(MEDICAL, "医疗"));
		properties.add(getInternalProperty(UNEMPLOYMENT, "失业"));
		properties.add(getInternalProperty(BUSINESS, "工商"));
		properties.add(getInternalProperty(FERTILITY, "生育"));
		return properties;
	}
}
