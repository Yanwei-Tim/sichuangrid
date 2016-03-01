package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class BuildDatasType extends BaseProperty {

	public final static String KEY = PropertyTypes.BUILDDATAS_TYPE;
	public final static int COMMON_BUILD = 0;
	public final static int STORE = 1;
	public final static int OFFICE_BUILDING = 2;
	public final static int HIGH_BUILDING = 3;
	public final static int OTHER_BUILDING = 4;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(COMMON_BUILD, "普通住宅"));
		properties.add(getInternalProperty(STORE, "商店"));
		properties.add(getInternalProperty(OFFICE_BUILDING, "写字楼"));
		properties.add(getInternalProperty(HIGH_BUILDING, "高层建筑"));
		properties.add(getInternalProperty(OTHER_BUILDING, "其他"));
		return properties;
	}
}
