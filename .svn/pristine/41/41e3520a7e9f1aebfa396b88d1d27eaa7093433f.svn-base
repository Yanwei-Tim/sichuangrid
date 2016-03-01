package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;
import com.tianque.issue.constants.IssueTypes;

public class SecurityPrecautions extends BaseProperty {

	public final static String SECURITYPRECAUTIONS_KEY = IssueTypes.SECURITYPRECAUTIONS;

	public final static int ONE = 1;
	public final static int TWO = 2;
	public final static int THREE = 3;
	public final static int FOUR = 4;
	public final static int FIVE = 5;
	public final static int SIX = 6;
	public final static int OTHER = 7;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalItems() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(ONE, "物防设施"));
		properties.add(getInternalProperty(TWO, "技防设施"));
		properties.add(getInternalProperty(THREE, "刑事治安案件发生情况"));
		properties.add(getInternalProperty(FOUR, "治安隐患或线索"));
		properties.add(getInternalProperty(FIVE, "守楼护院"));
		properties.add(getInternalProperty(SIX, "防毒控毒"));
		properties.add(getInternalProperty(OTHER, "其他"));
		return properties;
	}
}
