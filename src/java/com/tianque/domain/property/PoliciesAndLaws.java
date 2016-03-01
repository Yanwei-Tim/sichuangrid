package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;
import com.tianque.issue.constants.IssueTypes;

public class PoliciesAndLaws extends BaseProperty {

	public final static String POLICIESANDLAWS_KEY = IssueTypes.POLICIESANDLAWS;

	public final static int ONE = 1;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalItems() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(ONE, "咨询、宣传"));
		return properties;
	}
}
