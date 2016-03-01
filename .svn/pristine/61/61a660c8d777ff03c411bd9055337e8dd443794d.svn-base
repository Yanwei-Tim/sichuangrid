package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class UnSettedReason extends BaseProperty {
	public final static String UNSETTEDREASON_KEY = PropertyTypes.UNSETTEDREASON;
	public final static int CERTIFICATE_NO = 0;
	public final static int CERTIFICATE_LOST = 1;
	public final static int BIRTH_NO = 2;
	public final static int OUTFLOW_CANCELLE = 3;
	public final static int UNKNOWN = 4;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(CERTIFICATE_NO, "持证未落"));
		properties.add(getInternalProperty(CERTIFICATE_LOST, "证件遗失"));
		properties.add(getInternalProperty(BIRTH_NO, "出生未落"));
		properties.add(getInternalProperty(OUTFLOW_CANCELLE, "外流注销"));
		properties.add(getInternalProperty(UNKNOWN, "其他"));

		return properties;
	}
}
