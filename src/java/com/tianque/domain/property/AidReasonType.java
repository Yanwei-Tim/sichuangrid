package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class AidReasonType extends BaseProperty {

	public final static int Single_Child_NoLabor = 1;
	public final static int Medical_Spending_Much = 2;
	public final static int EMERGENCY_INCIDENT = 3;
	public final static int Single_Elder_NoLabor = 4;
	public final static int Handicapped_NoLabor = 5;
	public final static int Ill_NoLabor = 6;
	public final static int Handicapped_LessLabor = 7;
	public final static int Ill_LessLabor = 8;
	public final static int Not_Full_Employment = 9;
	public final static int Unemployment = 10;
	public final static int Education_Spending_Much = 11;
	public final static int OTHER = 12;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(Single_Child_NoLabor, "无劳力（孤幼）"));
		properties.add(getInternalProperty(Medical_Spending_Much, "医疗支出过大"));
		properties.add(getInternalProperty(EMERGENCY_INCIDENT, "突发性事件"));
		properties.add(getInternalProperty(Single_Elder_NoLabor, "无劳力（孤老）"));
		properties.add(getInternalProperty(Handicapped_NoLabor, "无劳力（因残）"));
		properties.add(getInternalProperty(Ill_NoLabor, "无劳力（因病）"));
		properties.add(getInternalProperty(Handicapped_LessLabor, "劳力不全（因残）"));
		properties.add(getInternalProperty(Ill_LessLabor, "劳力不全（因病）"));
		properties.add(getInternalProperty(Not_Full_Employment, "未充分就业"));
		properties.add(getInternalProperty(Unemployment, "未就业"));
		properties.add(getInternalProperty(Education_Spending_Much, "教育支出过大"));
		properties.add(getInternalProperty(OTHER, "其他"));
		return properties;
	}

	private static Map<String, String> myVisitRecordAidReasonType = new HashMap<String, String>();

	public static Map<String, String> myVisitRecordAidReasonType() {
		myVisitRecordAidReasonType.put("无劳力（孤幼）", "无劳力（孤幼）");
		myVisitRecordAidReasonType.put("医疗支出过大", "医疗支出过大");
		myVisitRecordAidReasonType.put("突发性事件", "突发性事件");
		myVisitRecordAidReasonType.put("教育支出过大", "教育支出过大");
		return myVisitRecordAidReasonType;
	}

}
