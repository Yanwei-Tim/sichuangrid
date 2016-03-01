package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class WorkingRecordSubmitstate extends BaseProperty {

	public final static String KEY = PropertyTypes.WORKING_RECORD_SUBMITSTATE;
	/**
	 */
	public final static int HAS_NOT_SUBMIT = 0;
	/**
	 */
	public final static int HAS_SUBMIT = 1;
	/**
	 */
	public final static int BACK_STATE = 2;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(HAS_NOT_SUBMIT, "未上报"));
		properties.add(getInternalProperty(HAS_SUBMIT, "已上报"));
		properties.add(getInternalProperty(BACK_STATE, "回退"));
		return properties;
	}
}
