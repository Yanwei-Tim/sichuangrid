package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

/**
 * 人户状态
 * 
 * @author Kent
 */
public class ResidentStatus extends BaseProperty {
	public final static String KEY = PropertyTypes.RESIDENT_STATUS;
	/**
	 * 人户同在
	 */
	public final static int HAS_RESIDENT_HAS_FAMILY = 0;
	/**
	 * 户在人不在
	 */
	public final static int NO_RESIDENT_HAS_FAMILY = 1;
	/**
	 * 人在户不在
	 */
	public final static int RESIDENT_NO_FAMILY = 2;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(HAS_RESIDENT_HAS_FAMILY, "人户同在"));
		properties.add(getInternalProperty(NO_RESIDENT_HAS_FAMILY, "户在人不在"));
		properties.add(getInternalProperty(RESIDENT_NO_FAMILY, "人在户不在"));
		return properties;
	}

}
