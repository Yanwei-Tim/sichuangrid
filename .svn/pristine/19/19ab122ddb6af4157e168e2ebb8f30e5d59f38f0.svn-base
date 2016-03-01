package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class JiasPartyBuildBarometerType extends BaseProperty {
	public final static int QING = 0;
	public final static int DUOYUN = 1;
	public final static int YIN = 2;
	public final static int YU = 3;
	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(QING, "晴"));
		properties.add(getInternalProperty(DUOYUN, "多云"));
		properties.add(getInternalProperty(YIN, "阴"));
		properties.add(getInternalProperty(YU, "雨"));
		return properties;
	}

}
