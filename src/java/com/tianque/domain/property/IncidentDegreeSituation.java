package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class IncidentDegreeSituation extends BaseProperty {
	public final static String INCIDENT_DEGREE_KEY = PropertyTypes.INCIDENT_DEGREE;
	public final static int ZERO_DEGREE = 0;
	public final static int FIRET_DEGREE = 1;
	public final static int SECOND_DEGREE = 2;
	public final static int THRID_DEGREE = 3;
	public final static int FOURTH_DEGREE = 4;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(FIRET_DEGREE, "I级"));
		properties.add(getInternalProperty(SECOND_DEGREE, "II级"));
		properties.add(getInternalProperty(THRID_DEGREE, "III级"));
		properties.add(getInternalProperty(FOURTH_DEGREE, "IV级"));
		return properties;
	}
}
