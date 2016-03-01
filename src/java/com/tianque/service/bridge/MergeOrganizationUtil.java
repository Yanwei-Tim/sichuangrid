package com.tianque.service.bridge;

import java.util.ArrayList;
import java.util.List;

public class MergeOrganizationUtil {
	public static List<String> tableList = new ArrayList<String>();
	public static List<String> orgLevelTableList = new ArrayList<String>();
	static {
		tableList.add("superiorVisits");
		tableList.add("attentionObjects");
		tableList.add("trampResidents");
		tableList.add("druggys");
		tableList.add("dangerousGoodsPractitioners");
		tableList.add("idleYouths");
		tableList.add("mentalPatients");
		tableList.add("positiveinfos");
		tableList.add("rectificativePersons");
		tableList.add("residents");
		tableList.add("poorpeople");
		tableList.add("newSocietyFederations");
		tableList.add("lettingHouses");
		tableList.add("otherLocales");
		tableList.add("schools");
		tableList.add("hospitals");
		tableList.add("specialTrades");
		tableList.add("commonComplexPlaces");
		tableList.add("rectificativePersons");
		tableList.add("enterprises");
		tableList.add("residents");
		tableList.add("primaryorganizations");
		tableList.add("users");
		orgLevelTableList.add("primaryorganizations");
		orgLevelTableList.add("users");
	}
}
