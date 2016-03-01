package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;
import com.tianque.issue.constants.IssueTypes;

public class Emergencies extends BaseProperty {

	public final static String EMERGENCIES_KEY = IssueTypes.EMERGENCIES;

	public final static int ONE = 1;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalItems() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(ONE, "突发事件"));
		return properties;
	}
}
