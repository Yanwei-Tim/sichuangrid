package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;
import com.tianque.issue.constants.IssueTypes;

public class SecurityTrouble extends BaseProperty {
	public final static String SECURITYTROUBLE_KEY = IssueTypes.SECURITYTROUBLE;

	public final static int ROB = 1;
	public final static int LOOT = 2;
	public final static int STEAL = 3;
	public final static int BET = 4;
	public final static int BAWDRY = 5;
	public final static int DRUG = 6;
	public final static int GANGLAND = 7;
	public final static int FIRESECURITY = 8;
	public final static int SAFEINPRODUCTION = 9;
	public final static int FOODHEALTH = 10;
	public final static int ENVIRONMENTAL_POLLUTION = 11;
	public final static int PUBLIC_SEFETY = 12;
	public final static int OTHER = 13;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(ROB, "抢劫"));
		properties.add(getInternalProperty(LOOT, "抢夺"));
		properties.add(getInternalProperty(STEAL, "盗窃"));
		properties.add(getInternalProperty(BET, "赌博"));
		properties.add(getInternalProperty(BAWDRY, "卖淫嫖娼"));
		properties.add(getInternalProperty(DRUG, "毒品"));
		properties.add(getInternalProperty(GANGLAND, "黑恶势力"));
		properties.add(getInternalProperty(FIRESECURITY, "消防安全"));
		properties.add(getInternalProperty(SAFEINPRODUCTION, "安全生产"));
		properties.add(getInternalProperty(FOODHEALTH, "食品卫生"));
		properties.add(getInternalProperty(ENVIRONMENTAL_POLLUTION, "环境污染"));
		properties.add(getInternalProperty(PUBLIC_SEFETY, "公共安全"));
		properties.add(getInternalProperty(OTHER, "其他"));
		return properties;
	}
}
