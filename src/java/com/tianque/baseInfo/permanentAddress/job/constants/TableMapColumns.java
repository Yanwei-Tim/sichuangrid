package com.tianque.baseInfo.permanentAddress.job.constants;

import java.util.ArrayList;
import java.util.List;

import com.tianque.baseInfo.permanentAddress.domain.TableMapColumn;

public class TableMapColumns {
	private final static List<TableMapColumn> tableMapColumns = new ArrayList<TableMapColumn>();
	static {
		tableMapColumns.add(new TableMapColumn("BASEINFO", "PROVINCE", "CITY",
				"DISTRICT"));// 实口基础表
		tableMapColumns.add(new TableMapColumn("FLOATINGPOPULATIONS",
				"INFLOWINGPROVINCE", "INFLOWINGCITY", "INFLOWINGDISTRICT"));// 流动人口
		tableMapColumns.add(new TableMapColumn("UNSETTLEDPOPULATIONS",
				"PROVINCE", "CITY", "DISTRICT"));// 未落户
		tableMapColumns.add(new TableMapColumn("DM_HOUSEHOLDSTAFFS_TEMP",
				"PROVINCE", "CITY", "DISTRICT"));
//		tableMapColumns.add(new TableMapColumn("DM_FLOATINGPOPULATIONS_TEMP",
//				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_FLOATINGPOPULATIONS_TEMP",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_FLOATINGPOPULATIONS_TEMP",
				"INFLOWINGPROVINCE", "INFLOWINGCITY", "INFLOWINGDISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_UNSETTLEDPOPULATIONS_TEMP",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_POSITIVEINFOS_TEMP",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_RECTIFICATIVEPERSONS_TEMP",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_MENTALPATIENTS_TEMP",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_DRUGGYS_TEMP", "PROVINCE",
				"CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_AIDSPOPULATIONS_TEMP",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_IDLEYOUTHS_TEMP",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_superiorVisits_Temp",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn(
				"DM_dangerousPractitioners_Temp", "PROVINCE", "CITY",
				"DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_otherAttentionPers_Temp",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_elderlyPeople_Temp",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_handicappeds_Temp",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_optimalObjects_Temp",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_AIDNEEDPOPULATION_TEMP",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_unemployedPeople_Temp",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("DM_NURTURESWOMEN_TEMP",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("attentionObjects",
				"NATIVEPROVINCE", "NATIVECITY", "NATIVEDISTRICT"));
		tableMapColumns.add(new TableMapColumn("CENSUSREGISTERFAMILYS",
				"PROVINCE", "CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("NURTURESWOMEN", "MANPROVINCE",
				"MANCITY", "MANDISTRICT"));
		tableMapColumns.add(new TableMapColumn("religionBelief", "PROVINCE",
				"CITY", "DISTRICT"));
		tableMapColumns.add(new TableMapColumn("party_members", "PROVINCE",
				"CITY", "DISTRICT"));

	}

	public static List<TableMapColumn> getTableMapColumns() {
		return tableMapColumns;
	}
}
