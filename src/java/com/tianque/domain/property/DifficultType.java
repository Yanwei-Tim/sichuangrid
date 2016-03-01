package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class DifficultType extends BaseProperty {

	public final static String DIFFICULT_KEY = PropertyTypes.DIFFICULT_TYPE;
	public final static int POVERTYFORTEACH = 0;
	public final static int SUDDENDIFFICULTY = 1;
	public final static int OBJECTOFTHREENOES = 2;
	public final static int FIVEGURANTEESSUPPORTINGOBJECT = 3;
	public final static int LOWHOUSEHOLD = 4;
	public final static int STREAMLINEWORKER = 5;
	public final static int STRESSANDSCRARE = 6;
	public final static int POORWORKER = 7;
	public final static int POORDISABLED = 8;
	public final static int EDGELOW = 9;
	public final static int POVERTYBYILLNESS = 10;
	public final static int OTHERDIFFICULTY = 11;
	public final static int UNKNOWN = 12;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(POVERTYFORTEACH, "因教致贫"));
		properties.add(getInternalProperty(SUDDENDIFFICULTY, "突发困难"));
		properties.add(getInternalProperty(OBJECTOFTHREENOES, "三无对象"));
		properties.add(getInternalProperty(FIVEGURANTEESSUPPORTINGOBJECT, "五保对象"));
		properties.add(getInternalProperty(LOWHOUSEHOLD, "低保户"));
		properties.add(getInternalProperty(STREAMLINEWORKER, "精减职工"));
		properties.add(getInternalProperty(STRESSANDSCRARE, "重点优抚"));
		properties.add(getInternalProperty(POORWORKER, "特困职工"));
		properties.add(getInternalProperty(POORDISABLED, "特困残疾"));
		properties.add(getInternalProperty(EDGELOW, "低保边缘"));
		properties.add(getInternalProperty(POVERTYBYILLNESS, "因病致贫"));
		properties.add(getInternalProperty(OTHERDIFFICULTY, "其他困难"));
		properties.add(getInternalProperty(UNKNOWN, "未知"));
		return properties;
	}

}
