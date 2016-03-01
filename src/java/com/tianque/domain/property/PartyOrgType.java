package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

/**
 * 党组织类型属性
 * 
 */
public class PartyOrgType extends BaseProperty {
	public final static String PARTYORGTYPE_KEY = PropertyTypes.PARTYORGTYPE;

	public final static int COMMITTEE = 0;
	public final static int ALL_BRANCH = 1;
	public final static int BRANCH = 2;

	public final static String COMMITTEE_NAME = "党委";
	public final static String ALL_BRANCH_NAME = "党总支";
	public final static String BRANCH_NAME = "党支部";

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(COMMITTEE, COMMITTEE_NAME));
		properties.add(getInternalProperty(ALL_BRANCH, ALL_BRANCH_NAME));
		properties.add(getInternalProperty(BRANCH, BRANCH_NAME));
		return properties;
	}
}
