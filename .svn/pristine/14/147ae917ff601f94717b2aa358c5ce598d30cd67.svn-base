package com.tianque.partyBuilding.activityRecords.constant;

import java.util.HashMap;
import java.util.Map;

public class OrganizationType {
	// 区域党组织 partyOrganization
	public static final String PARTY_ORGANIZATION = "PARTYORGINFOS";
	public static final String PARTY_ORGANIZATION_NAME = "PARTYBRANCHNAME";
	// 两新组织
	public static final String NEW_PARTY_ORGANIZATION = "NEW_PARTY_ORG";
	public static final String NEW_PARTY_ORGANIZATION_NAME = "NAME";
	// 街道党组织
	public static final String TOWN_PARTY_ORGANIZATION = "TOWN_PARTY_ORG";
	public static final String TOWN_PARTY_ORGANIZATION_NAME = "NAME";
	private static Map<String, String> organizationNames = new HashMap<String, String>();
	static {
		organizationNames.put(PARTY_ORGANIZATION, PARTY_ORGANIZATION_NAME);
		organizationNames.put(NEW_PARTY_ORGANIZATION, NEW_PARTY_ORGANIZATION_NAME);
		organizationNames.put(TOWN_PARTY_ORGANIZATION, TOWN_PARTY_ORGANIZATION_NAME);

	}

	public static String getOrganizationNameByType(String key) {
		return organizationNames.get(key);
	}
}
