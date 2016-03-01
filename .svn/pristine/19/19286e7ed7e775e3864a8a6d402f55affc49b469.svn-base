package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class LettingcertificateType extends BaseProperty {
	public final static int BUSINESS_LICENSE = 1;
	public final static int IDENTITY_CARD = 2;
	public final static int OTHER = 3;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(BUSINESS_LICENSE, "营业执照"));
		properties.add(getInternalProperty(IDENTITY_CARD, "身份证"));
		properties.add(getInternalProperty(OTHER, "其他"));
		return properties;
	}
}
