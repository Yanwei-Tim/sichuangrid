package com.tianque.threeRecordsIssue.dataTrans;

import java.util.HashMap;
import java.util.Map;

import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.domain.vo.ImportTemplatesVo;

public class ImportTemplatesSetting {

	private static String dir = "resource/datatemplate/";
	public final static Map<String, ImportTemplatesVo> keyImportTemplates = new HashMap<String, ImportTemplatesVo>();

	static {
		keyImportTemplates.put("WaterResourceData", new ImportTemplatesVo(
				"1.0", dir + "WaterResourceData".toUpperCase() + ".xls"));
		keyImportTemplates.put("TrafficData", new ImportTemplatesVo(
				"1.0", dir + "TrafficData".toUpperCase() + ".xls"));
		keyImportTemplates.put("AgricultureData", new ImportTemplatesVo(
				"1.0", dir + "AgricultureData".toUpperCase() + ".xls"));
		keyImportTemplates.put("EducationData", new ImportTemplatesVo(
				"1.0", dir + "EducationData".toUpperCase() + ".xls"));
		keyImportTemplates.put("EnergyData", new ImportTemplatesVo(
				"1.0", dir + "EnergyData".toUpperCase() + ".xls"));
		keyImportTemplates.put("EnvironmentData", new ImportTemplatesVo(
				"1.0", dir + "EnvironmentData".toUpperCase() + ".xls"));
		keyImportTemplates.put("LaborData", new ImportTemplatesVo(
				"1.0", dir + "LaborData".toUpperCase() + ".xls"));
		keyImportTemplates.put("MedicalData", new ImportTemplatesVo(
				"1.0", dir + "MedicalData".toUpperCase() + ".xls"));
		keyImportTemplates.put("OtherData", new ImportTemplatesVo(
				"1.0", dir + "OtherData".toUpperCase() + ".xls"));
		keyImportTemplates.put("ScienceData", new ImportTemplatesVo(
				"1.0", dir + "ScienceData".toUpperCase() + ".xls"));
		keyImportTemplates.put("TownData", new ImportTemplatesVo(
				"1.0", dir + "TownData".toUpperCase() + ".xls"));
	}

	public static ImportTemplatesVo getImportTemplatesVo(String key) {
		ImportTemplatesVo tv = null;
		for (String initKey : keyImportTemplates.keySet()) {
			if (key.equals(initKey)) {
				tv = keyImportTemplates.get(initKey);
				return tv;
			}
		}
		for (String initKey : keyImportTemplates.keySet()) {
			if (key.startsWith(initKey)) {
				tv = keyImportTemplates.get(initKey);
				if (key.endsWith(GlobalSetting.NOT_ADD_POPULATION))
					tv.setUrl(dir + initKey + "1.xls");
				else if (key.endsWith(GlobalSetting.SYNC_ACTUAL_POPULATION))
					tv.setUrl(dir + initKey + "2.xls");
				else if (key.endsWith(GlobalSetting.NOT_DEPENDENT))
					tv.setUrl(dir + initKey + ".xls");
				break;
			}
		}
		return tv;
	}
}
