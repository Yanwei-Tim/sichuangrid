package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class NewSocietyType extends BaseProperty {
	public final static int SOCIETY_GROUP = 0;
	public final static int FOUNDATION_GROUP = 1;
	public final static int NON_ENTERPRISE = 2;
	public final static int AGENT_ORGANIZATION = 3;
	public final static int ACTIVITY_TEAM = 4;
	public final static int OTHER = 5;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(SOCIETY_GROUP, "慈善类"));
		properties.add(getInternalProperty(FOUNDATION_GROUP, "科技类"));
		properties.add(getInternalProperty(NON_ENTERPRISE, "社区公益类"));
		properties.add(getInternalProperty(AGENT_ORGANIZATION, "文体类"));
		properties.add(getInternalProperty(ACTIVITY_TEAM, "法律服务"));
		properties.add(getInternalProperty(OTHER, "民生服务类"));
		return properties;
	}

	public final static String NEW_SOCIRTY_TYPE_KEY = PropertyTypes.NEW_SOCIETY_TYPE;
}
