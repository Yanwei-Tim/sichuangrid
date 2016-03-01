package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class SourceType extends BaseProperty {
	public final static int LAW = 0;
	public final static int RULE = 1;
	public final static int POLICY = 2;
	public final static int EXPERIENCE = 3;
	public final static int RESEARCH = 4;
	public final static int BRIEFING = 5;

	public final static String LAW_REGULATION = "法律法规";
	public final static String RULE_REGULATION = "规章制度";
	public final static String POLICY_DOCUMENT = "政策文件";
	public final static String EXPERIENCE_MATERIAL = "经验材料";
	public final static String RESEARCH_REPORT = "调研报告";
	public final static String BRIEFINGS = "简报";

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(LAW, LAW_REGULATION));
		properties.add(getInternalProperty(RULE, RULE_REGULATION));
		properties.add(getInternalProperty(POLICY, POLICY_DOCUMENT));
		properties.add(getInternalProperty(EXPERIENCE, EXPERIENCE_MATERIAL));
		properties.add(getInternalProperty(RESEARCH, RESEARCH_REPORT));
		properties.add(getInternalProperty(BRIEFING, BRIEFINGS));
		return properties;
	}

	public final static String DAILY_DIRECTORY_TYPES_KEY = PropertyTypes.FILE_SOURCE;
}
