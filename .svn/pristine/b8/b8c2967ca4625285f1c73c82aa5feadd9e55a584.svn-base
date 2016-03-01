package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

/**
 * 系统性别属性
 * 
 * @author kent
 */
public class Gender extends BaseProperty {
	public final static String GENDER_KEY = PropertyTypes.GENDER;

	public final static int MALE = 1;
	public final static int FEMALE = 0;
	public final static int UNKNOWN = 2;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(MALE, "男"));
		properties.add(getInternalProperty(FEMALE, "女"));
		properties.add(getInternalProperty(UNKNOWN, "不明"));
		return properties;
	}
}
