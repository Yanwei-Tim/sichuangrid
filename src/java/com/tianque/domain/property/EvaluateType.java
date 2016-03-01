package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class EvaluateType extends BaseProperty {
	public final static int STANDARD_EVALUATE = 0;
	public final static int NORMAL_EVALUATE = 1;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(STANDARD_EVALUATE, "考核标准"));
		properties.add(getInternalProperty(NORMAL_EVALUATE, "考核结果"));
		return properties;
	}

	public final static String EVALUATE_TYPE_KEY = PropertyTypes.EVALUATE_TYPE;
}
