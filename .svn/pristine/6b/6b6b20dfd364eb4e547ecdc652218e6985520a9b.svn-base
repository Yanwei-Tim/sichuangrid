package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class ElderPersonType extends BaseProperty {

	public final static int UNKNOWN_ELDER = 1;
	public final static int NOFAMILY_ELDER = 2;
	public final static int PRODUCTER_ELDER = 3;
	public final static int LOWERINCOME_ELDER = 4;
	public final static int LABORHERO_ELDER = 5;
	public final static int PROVERTY_ELDER = 6;
	public final static int TERRIBLYILL_ELDER = 7;
	public final static int ELDERLY_ELDER = 8;
	public final static int OLDWORKER_ELDER = 9;
	public final static int PENSION_ELDER = 10;
	public final static int SELFCARE_ELDER = 11;
	public final static int OTHER = 12;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(UNKNOWN_ELDER, "未知"));
		properties.add(getInternalProperty(NOFAMILY_ELDER, "孤寡老人"));
		properties.add(getInternalProperty(PRODUCTER_ELDER, "生产"));
		properties.add(getInternalProperty(LOWERINCOME_ELDER, "低保"));
		properties.add(getInternalProperty(LABORHERO_ELDER, "劳模"));
		properties.add(getInternalProperty(PROVERTY_ELDER, "特困"));
		properties.add(getInternalProperty(TERRIBLYILL_ELDER, "重病"));
		properties.add(getInternalProperty(ELDERLY_ELDER, "70岁以上高龄老人"));
		properties.add(getInternalProperty(OLDWORKER_ELDER, "建国前参加工作"));
		properties.add(getInternalProperty(PENSION_ELDER, "一老养一老"));
		properties.add(getInternalProperty(SELFCARE_ELDER, "自理"));
		properties.add(getInternalProperty(OTHER, "其他"));
		return properties;
	}

	private static Map<String, String> myVisitRecordAidReasonType = new HashMap<String, String>();

	public static Map<String, String> myVisitRecordAidReasonType() {
		if (myVisitRecordAidReasonType.size() > 0) {
			return myVisitRecordAidReasonType;
		}
		myVisitRecordAidReasonType.put("孤寡老人", "孤寡老人");
		myVisitRecordAidReasonType.put("重病", "重病老人");
		myVisitRecordAidReasonType.put("低保", "低保老人");
		myVisitRecordAidReasonType.put("特困", "特困老人");
		return myVisitRecordAidReasonType;
	}

}
