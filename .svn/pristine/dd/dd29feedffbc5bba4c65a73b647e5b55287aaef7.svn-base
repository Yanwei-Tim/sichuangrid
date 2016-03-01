package com.tianque.service.vo;

import java.util.HashMap;
import java.util.Map;

import com.tianque.domain.ReportProperty;
import com.tianque.domain.vo.ImportTemplatesVo;

public class ReportInfoTableTypes {
	public final static Map<String, String> importantsReportTables = new HashMap<String, String>();
	private final static Map<String, String> importantsReportDisplayNames = new HashMap<String, String>();

	public static final String TREATMENT_TEAM = String.valueOf(ReportProperty.TREATMENT_TEAM);
	public static final String SOCIETY_TEAM = String.valueOf(ReportProperty.SOCIETY_TEAM);

	public static final String TREATMENT_TEAM_DISPLAYNAME = "综治办整体情况";
	public static final String SOCIETY_TEAM_DISPLAYNAME = "社会治安群防群治队伍";

	static {
		importantsReportTables
				.put(TREATMENT_TEAM, "com.tianque.service.vo.PreventionTreatmentTeam");
		importantsReportTables.put(SOCIETY_TEAM, "com.tianque.service.vo.SocietySyntheticalTeam");

		importantsReportDisplayNames.put(TREATMENT_TEAM, TREATMENT_TEAM_DISPLAYNAME);
		importantsReportDisplayNames.put(SOCIETY_TEAM, SOCIETY_TEAM_DISPLAYNAME);
	}

	public static String getReportDisplayNames(String key) {
		return importantsReportDisplayNames.get(key);
	}

	public static String getReportBeanNames(String key) {
		return importantsReportTables.get(key);
	}

	public final static Map<String, ImportTemplatesVo> keyReportImportTemplates = new HashMap<String, ImportTemplatesVo>();

	private static String reportDir = "resource/reporttemplate/";
	static {
		keyReportImportTemplates.put(TREATMENT_TEAM, new ImportTemplatesVo("1.0", reportDir
				+ TREATMENT_TEAM + ".xls"));
		keyReportImportTemplates.put(SOCIETY_TEAM, new ImportTemplatesVo("1.0", reportDir
				+ SOCIETY_TEAM + ".xls"));
	}

	public static ImportTemplatesVo getImportTemplatesReportVo(String key) {
		return keyReportImportTemplates.get(key);
	}
}
