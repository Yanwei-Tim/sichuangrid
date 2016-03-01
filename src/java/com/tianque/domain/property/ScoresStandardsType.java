package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class ScoresStandardsType extends BaseProperty {

	public final static int GREAT = 0;
	public final static int GOOD = 1;
	public final static int QULIFIED = 2;
	public final static int FAILED = 3;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(GREAT, "优秀"));
		properties.add(getInternalProperty(GOOD, "良好"));
		properties.add(getInternalProperty(QULIFIED, "合格"));
		properties.add(getInternalProperty(FAILED, "不合格"));
		return properties;
	}

	public final static String SCORES_STANDARDS_KEY = PropertyTypes.SCORES_STANDARDS;
}
