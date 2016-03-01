package com.tianque.xichang.working.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;
import com.tianque.domain.property.PropertyTypes;

public class ItemCategory extends BaseProperty {
	public final static int WATER_RESOURCE = 0;
	public final static int TRAFFIC_CATEGORIES = 1;
	public final static int ENERGY_SOURCES = 2;
	public final static int EDUCATION_TECHNOLOGY = 3;
	public final static int GENRE_CLASS = 4;
	public final static int HEALTH_CARE = 5;
	public final static int LABOUR_SOCIAL_PROTECTION = 6;
	public final static int ENVIRONMENT_PROTECTION = 7;
	public final static int URBAN_RURAL_PLANNING_CONSTRUCTION = 8;
	public final static int AGRICULTURE_RELATED_SCIENCES = 9;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		properties.add(getInternalProperty(WATER_RESOURCE, "水利类"));
		properties.add(getInternalProperty(TRAFFIC_CATEGORIES, "交通类"));
		properties.add(getInternalProperty(ENERGY_SOURCES, "能源类"));
		properties.add(getInternalProperty(EDUCATION_TECHNOLOGY, "教育科技类"));
		properties.add(getInternalProperty(GENRE_CLASS, "文体类"));
		properties.add(getInternalProperty(HEALTH_CARE, "医疗卫生"));
		properties.add(getInternalProperty(LABOUR_SOCIAL_PROTECTION, "劳动和社会保障"));
		properties.add(getInternalProperty(ENVIRONMENT_PROTECTION, "环境保护类"));
		properties.add(getInternalProperty(URBAN_RURAL_PLANNING_CONSTRUCTION, "城乡规划建设管理类"));
		properties.add(getInternalProperty(AGRICULTURE_RELATED_SCIENCES, "农业类"));
		return properties;
	}

	public final static String ITEM_CATEGORY_KEY = PropertyTypes.ITEM_CATEGORY;

}
