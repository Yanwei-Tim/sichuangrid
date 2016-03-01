package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class DetailedRuleType extends BaseProperty {
	public final static int OTHER_TYPE = 0;
	public final static int DAILY_LOG = 1;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(OTHER_TYPE, "其他类型"));
		// properties.add(getInternalProperty(DAILY_LOG,"台帐类型"));
		return properties;
	}

	public final static String DETAILED_RULE_TYPE_KEY = PropertyTypes.DETAILED_RULE_TYPE;
}
