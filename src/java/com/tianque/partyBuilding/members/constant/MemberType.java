package com.tianque.partyBuilding.members.constant;

import java.util.ArrayList;
import java.util.List;

import com.tianque.partyBuilding.systemPartys.constant.SystemPartysType;

public final class MemberType {
	public static final int OFFICE_PARTY_ORG = 1;// 机关党组织
	public static final int TOWN_PARTY_ORG = 2;// 街道党组织
	public static final int TWO_NEW_ORGNIZATION_PARTY_ORG = 3;// 两新组织党组织

	public static final int REGION_PARTY_ORG = 5;// 区域党组织
	public static final int DOUBLE_REG_ACTIVITIES = 6;// 双报到
	public static final int VOLUNTEER_TEAM = 7;// 党员志愿者队伍

	public static final String ORGANSPARTY = "organsParty";// 机关党组织
	public static final String STREETPARTY = "streetParty";// 街道党组织
	public static final String TWONEWPARTYORGANIZATION = "twoNewPartyOrg";// 两新组织党组织

	public static final String REGIONALPARTY = "regionalParty";// 区域党组织
	public static final String DOUBLEREGISTRATIONACTIVITIES = "doubleRegActivities";// 双报到
	public static final String VOLUNTEERTEAM = "volunteerTeam";// 党员志愿者队伍

	public static final String PARTY_BRANCH = "党支部";

	public static final List<Integer> INTERACTIVE_PARTY = new ArrayList<Integer>();

	public static final boolean INTERACTIVE = true;// 标示党建党员是互斥的

	static {
		INTERACTIVE_PARTY.add(OFFICE_PARTY_ORG);
		INTERACTIVE_PARTY.add(TOWN_PARTY_ORG);
		// 系统党建拆分为3种
		INTERACTIVE_PARTY.add(SystemPartysType.BUSINESS_ORG);
		INTERACTIVE_PARTY.add(SystemPartysType.COLLECTIVE_ORG);
		INTERACTIVE_PARTY.add(SystemPartysType.TWO_NEW_PARTY);
	}
}
