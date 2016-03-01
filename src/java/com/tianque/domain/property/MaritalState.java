package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

/**
 * 系统婚姻状况属性
 * 
 * @author kent
 */
public class MaritalState extends BaseProperty {
	public final static String MARITAL_STATUS_KEY = PropertyTypes.MARITAL_STATUS;

	public final static int NEVERMARRIED = 1;
	public final static int ALREADYMARRIED = 0;
	public final static int UNKNOWN = 2;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(NEVERMARRIED, "未婚"));
		properties.add(getInternalProperty(ALREADYMARRIED, "结过婚"));
		properties.add(getInternalProperty(UNKNOWN, "不明"));
		return properties;
	}

}
