package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class OptimalObjectType extends BaseProperty {

	public final static int NONE = 0;
	public final static int UNKNOWN = 1;
	public final static int DiedOnduty_Armyman_Family = 2;
	public final static int DiedOnill_Armyman_Family = 3;
	public final static int Veteran = 4;
	public final static int Exserviceman = 5;
	public final static int Veteran_WithIll = 6;
	public final static int Psychopath = 7;
	public final static int Armyman_Family = 8;
	public final static int Orphan = 9;
	public final static int NO_FAMILY_ELDER = 10;
	public final static int Handicapped = 11;
	public final static int Disable_Armyman = 12;
	public final static int Disable_International_Staff = 13;
	public final static int Disable_Police = 14;
	public final static int Disable_Militia = 15;
	public final static int Died_Armyman_Family = 16;
	public final static int OTHER = 17;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(UNKNOWN, "未知"));
		properties.add(getInternalProperty(DiedOnduty_Armyman_Family, "因公牺牲军人、家属"));
		properties.add(getInternalProperty(DiedOnill_Armyman_Family, "病故军人、家属"));
		properties.add(getInternalProperty(Veteran, "在乡退伍军人、老战士"));
		properties.add(getInternalProperty(Exserviceman, "在乡复员军人"));
		properties.add(getInternalProperty(Veteran_WithIll, "带病回乡退伍军人"));
		properties.add(getInternalProperty(Psychopath, "严重精神障碍患者"));
		properties.add(getInternalProperty(Armyman_Family, "军属"));
		properties.add(getInternalProperty(Orphan, "孤儿"));
		properties.add(getInternalProperty(NO_FAMILY_ELDER, "孤寡老人"));
		properties.add(getInternalProperty(Handicapped, "残疾人"));
		properties.add(getInternalProperty(Disable_Armyman, "伤残革命军人"));
		properties.add(getInternalProperty(Disable_International_Staff, "伤残国际机关工作人员"));
		properties.add(getInternalProperty(Disable_Police, "伤残人民警察"));
		properties.add(getInternalProperty(Disable_Militia, "伤残民兵、民工"));
		properties.add(getInternalProperty(Died_Armyman_Family, "烈士家属"));
		properties.add(getInternalProperty(OTHER, "其他"));
		return properties;
	}

	private static Map<String, String> myVisitRecordAidReasonType = new HashMap<String, String>();

	public static Map<String, String> myVisitRecordAidReasonType() {
		myVisitRecordAidReasonType.put("伤残革命军人", "残疾军人");
		myVisitRecordAidReasonType.put("在乡复员军人", "在乡复员军人");
		myVisitRecordAidReasonType.put("带病回乡退伍军人", "带病回乡退伍军人");
		myVisitRecordAidReasonType.put("因公牺牲军人家属", "因公牺牲军人遗属");
		myVisitRecordAidReasonType.put("病故军人家属", "病故军人遗属");
		return myVisitRecordAidReasonType;
	}

}
