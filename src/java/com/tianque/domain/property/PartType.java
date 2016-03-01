package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class PartType extends BaseProperty{
	public final static int PUBLIC_FACILITIES = 0;
	public final static int ROAD_TRAFFIC = 1;
	public final static int CITY_ENVIRONMENT = 2;
	public final static int LANDSCAPING = 3;
	public final static int HOUSELAND = 4;
	public final static int OTHER_FACILITIES = 5;
	public final static int EXPANSION_COMPONENTS = 6;
	/*
	public final static String  ORGSCOPE_SAMEGRADE = "sameGrade";
	public final static String  ORGSCOPE_ALLURISDICTION = "allJurisdiction";
	public final static String  ORGSCOPE_DIRECTJURISDICTION = "directJurisdiction";
	*/
	private static Map<Integer, String> positionRelation = new HashMap<Integer, String>();
	
	static {
		positionRelation.put(PUBLIC_FACILITIES, PropertyTypesYinchuan.PUBLIC_FACILITIES);
		positionRelation.put(ROAD_TRAFFIC, PropertyTypesYinchuan.ROAD_TRAFFIC);
		positionRelation.put(CITY_ENVIRONMENT, PropertyTypesYinchuan.CITY_ENVIRONMENT);
		positionRelation.put(LANDSCAPING, PropertyTypesYinchuan.LANDSCAPING);
		positionRelation.put(HOUSELAND, PropertyTypesYinchuan.HOUSELAND);
		positionRelation.put(OTHER_FACILITIES, PropertyTypesYinchuan.OTHER_FACILITIES);
		positionRelation.put(EXPANSION_COMPONENTS, PropertyTypesYinchuan.EXPANSION_COMPONENTS);
	}
	
	
	private static List<GridInternalProperty> properties=new ArrayList<GridInternalProperty>();
	public static List<GridInternalProperty> getInternalProperties() {
		if(properties.size()>0){
			return properties;
		}
		properties.add(getInternalProperty(PUBLIC_FACILITIES,"公共设施类"));
		properties.add(getInternalProperty(ROAD_TRAFFIC,"道路交通类"));
		properties.add(getInternalProperty(CITY_ENVIRONMENT,"市容环境类"));
		properties.add(getInternalProperty(LANDSCAPING,"园林绿化类"));
		properties.add(getInternalProperty(HOUSELAND,"房屋土地类"));
		properties.add(getInternalProperty(OTHER_FACILITIES,"其他设施类"));
		properties.add(getInternalProperty(EXPANSION_COMPONENTS,"扩展部件类"));
		return properties;
	}
	
	public static String getPositionDemainName(int internalId) {
		return positionRelation.get(internalId);
	}
}
