package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class LicenseSituation extends BaseProperty {
	public final static String LICENSE_SITUATION_KEY = PropertyTypes.LICENSE_SITUATION;
	public final static int ALREADYLICENSE = 0;
	public final static int NEVERLICENSE = 1;
	public final static int UNKNOWN = 2;
	public final static int ALREADYLICENSE_1 = 3;
	public final static int ALREADYLICENSE_2 = 4;
	public final static int CHILDRENIN = 5;
	public final static int CHILDRENOUT = 6;
	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(ALREADYLICENSE, "领证，现有效"));
		properties.add(getInternalProperty(NEVERLICENSE, "未领证"));
		properties.add(getInternalProperty(UNKNOWN, "未知"));
		properties.add(getInternalProperty(ALREADYLICENSE_1, "领证，超14周岁"));
		properties.add(getInternalProperty(ALREADYLICENSE_2, "领证，子女死亡"));
		properties.add(getInternalProperty(CHILDRENIN, " 计内二孩，证废"));
		properties.add(getInternalProperty(CHILDRENOUT, " 计外二孩，证废"));
		return properties;
	}
}
