package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class CertificateType extends BaseProperty {

	public final static String CERTIFICATEHOLDTYPE_KEY = PropertyTypes.CERTIFICATEHOLD_TYPE;
	public final static int HOUSEHOLDMIGRATION_CARD = 0;
	public final static int POSITIVE_CARD = 1;
	public final static int RECOVERY_CARD = 2;
	public final static int BIRTH_CARD = 3;
	public final static int UNKNOWN = 4;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(HOUSEHOLDMIGRATION_CARD, "户口迁移证"));
		properties.add(getInternalProperty(POSITIVE_CARD, "刑释解教证"));
		properties.add(getInternalProperty(RECOVERY_CARD, "复转证"));
		properties.add(getInternalProperty(BIRTH_CARD, "出生证"));
		properties.add(getInternalProperty(UNKNOWN, "其他"));

		return properties;
	}

}
