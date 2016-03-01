package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;
import com.tianque.issue.constants.IssueTypes;

public class SpecialPopulations extends BaseProperty {

	public final static String SPECIALPOPULATIONS_KEY = IssueTypes.SPECIALPOPULATIONS;

	public final static int ONE = 1;
	public final static int TWO = 2;
	public final static int THREE = 3;
	public final static int FOUR = 4;
	public final static int FIVE = 5;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalItems() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(ONE, "严重精神障碍患者是否落实"));
		properties.add(getInternalProperty(TWO, "社区帮教落实"));
		properties.add(getInternalProperty(THREE, "刑释"));
		properties.add(getInternalProperty(FOUR, "吸毒人员"));
		properties.add(getInternalProperty(FIVE, "重点青少年"));
		return properties;
	}
}
