package com.tianque.working.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.domain.property.DailyDirectoryTypes;
import com.tianque.domain.property.StatementsReportedType;

public class WorkingReportResultTypes {

	private final static Map<Integer, String> resultTypes = new HashMap<Integer, String>();
	private final static List<Integer> statementsReportedType = new ArrayList<Integer>();
	private final static List<Integer> workingReportResultType = new ArrayList<Integer>();

	private WorkingReportResultTypes() {

	}

	static {
		statementsReportedType.add(DailyDirectoryTypes.STATEMENTS_REPORTED);
		statementsReportedType.add(DailyDirectoryTypes.SERVICE_MANAGEMENT);

		workingReportResultType.addAll(statementsReportedType);
		workingReportResultType.add(StatementsReportedType.CHECK);
		workingReportResultType.add(StatementsReportedType.ISSUEDEAL);
		workingReportResultType.add(StatementsReportedType.INVESTIGATION);
		workingReportResultType.add(StatementsReportedType.SIGNIFICANT_ISSUEDEAL);
		workingReportResultType.add(StatementsReportedType.INVESTIGATION_REMEDIATION);
		workingReportResultType.add(DailyDirectoryTypes.SOCIETY_SECURITY);
		workingReportResultType.add(DailyDirectoryTypes.SECURITY_PROPAGANDA);
		workingReportResultType.add(DailyDirectoryTypes.SERIES_SECURITY);
		workingReportResultType.add(DailyDirectoryTypes.GRID_MANAGEMENT_NORMAL);
		workingReportResultType.add(DailyDirectoryTypes.SERVICE_MANAGEMENT_CITY);
		workingReportResultType.add(DailyDirectoryTypes.SERVICE_MANAGEMENT_TOWN);
		workingReportResultType.add(DailyDirectoryTypes.SERVICE_MANAGEMENT_VILLAGE);

		resultTypes.put(StatementsReportedType.CHECK, "publicSecurity");
		resultTypes.put(StatementsReportedType.ISSUEDEAL, "socialConflict");
		resultTypes.put(StatementsReportedType.INVESTIGATION, "keyAreas");
		resultTypes.put(StatementsReportedType.SIGNIFICANT_ISSUEDEAL, "singificant");
		resultTypes.put(StatementsReportedType.INVESTIGATION_REMEDIATION,
				"investigationRemediation");
		resultTypes.put(DailyDirectoryTypes.SOCIETY_SECURITY, "societySecurity");
		resultTypes.put(DailyDirectoryTypes.SECURITY_PROPAGANDA, "securityPropaganda");
		resultTypes.put(DailyDirectoryTypes.SERIES_SECURITY, "seriesSecurity");
		resultTypes.put(DailyDirectoryTypes.GRID_MANAGEMENT_NORMAL, "gridManagementNormal");
		resultTypes.put(DailyDirectoryTypes.SERVICE_MANAGEMENT_CITY, "serviceManagementCity");
		resultTypes.put(DailyDirectoryTypes.SERVICE_MANAGEMENT_TOWN, "serviceManagementTown");
		resultTypes.put(DailyDirectoryTypes.SERVICE_MANAGEMENT_VILLAGE, "serviceManagementVillage");

	}

	public static boolean isStatementsReportedType(int key) {
		return statementsReportedType.contains(key);
	}

	public static boolean isWorkingReportType(int key) {
		return workingReportResultType.contains(key);
	}

	public static String getResultType(int key) {
		return resultTypes.get(key);
	}
}
