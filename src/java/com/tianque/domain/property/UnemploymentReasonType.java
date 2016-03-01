package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class UnemploymentReasonType extends BaseProperty {

	public final static int Leave_School = 1;
	public final static int Remove_Enterprise = 2;
	public final static int Out_Institutions = 3;
	public final static int Lost_Land = 4;
	public final static int Ex_serviceman = 5;
	public final static int Release_After_Serving = 6;
	public final static int Reeducation_Through_Labor = 7;
	public final static int Individual_Business_Down = 8;
	public final static int Flexible_Employment = 9;
	public final static int Rural_Labor_Force = 10;
	public final static int Foreign_Unemployed_Population = 11;
	public final static int OTHER = 12;
	public final static int Other_Community_Correction = 13;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(Leave_School, "年满16周岁，从各类学校毕业、肄业、未能继续升学的"));
		properties.add(getInternalProperty(Remove_Enterprise, "与企业解除或终止劳动关系的"));
		properties.add(getInternalProperty(Out_Institutions, "从机关事业单位辞职或被辞退解聘的"));
		properties.add(getInternalProperty(Lost_Land, "由农业户口转为非农业户口，并失去承包土地的"));
		properties.add(getInternalProperty(Ex_serviceman, "军人退出现役、且未纳入国家统一安置的"));
		properties.add(getInternalProperty(Release_After_Serving, "刑满释放（未成年人除外）"));
		properties.add(getInternalProperty(Reeducation_Through_Labor, "劳动教养期满或提前解除劳动教养的（未成年人除外）"));
		properties.add(getInternalProperty(Individual_Business_Down, "个体工商户业主或私营业主停止经营的"));
		properties.add(getInternalProperty(Flexible_Employment, "灵活就业失业的"));
		properties.add(getInternalProperty(Rural_Labor_Force, "本地区农村劳动力"));
		properties.add(getInternalProperty(Foreign_Unemployed_Population, "外来失业人口"));
		properties.add(getInternalProperty(OTHER, "其他"));
		properties.add(getInternalProperty(Other_Community_Correction, "假释、缓刑、暂予监外外执行、管制、剥夺政治权利等社区矫正人员（未成年人除外）"));
		return properties;
	}

	private static Map<String, String> myVisitRecordAidReasonType = new HashMap<String, String>();

	public static Map<String, String> myVisitRecordAidReasonType() {
		myVisitRecordAidReasonType.put("军人退出现役、且未纳入国家统一安置的", "军人退役未纳入安置");
		myVisitRecordAidReasonType.put("刑满释放（未成年人除外）", "刑满释放、监外执行");
		myVisitRecordAidReasonType.put("劳动教养期满或提前解除劳动教养的（未成年人除外）", "劳动教养期满或提前解除");
		myVisitRecordAidReasonType.put("年满16周岁，从各类学校毕业、肄业、未能继续升学的", "年满16周岁未继续升学");
		return myVisitRecordAidReasonType;
	}

}
