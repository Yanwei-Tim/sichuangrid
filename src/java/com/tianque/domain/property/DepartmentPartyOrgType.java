package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class DepartmentPartyOrgType extends BaseProperty {
	public final static int MANAGEMENT_TYPE = 0;
	public final static int LEADER_GROUP_TYPE = 1;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		properties.add(getInternalProperty(MANAGEMENT_TYPE, "综治办"));
		properties.add(getInternalProperty(LEADER_GROUP_TYPE, "专项工作领导小组"));
		return properties;
	}

	public final static String DEPARTMENTPARTY_TYPE_KEY = PropertyTypes.DEPARTMENTPARTY_TYPE;
}
