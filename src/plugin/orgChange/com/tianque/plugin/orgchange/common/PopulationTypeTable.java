package com.tianque.plugin.orgchange.common;

import java.util.HashMap;
import java.util.Map;

import com.tianque.core.util.BaseInfoTables;

/**
 * 人口业务表关联
 * 
 * 
 */
public class PopulationTypeTable {
	public final static Map<String, String> populationTypeTable = new HashMap<String, String>();
	static {
		populationTypeTable.put(BaseInfoTables.POSITIVEINFO_KEY,
				"positiveinfos");
		populationTypeTable.put(BaseInfoTables.RECTIFICATIVEPERSON_KEY,
				"rectificativepersons");
		populationTypeTable.put(BaseInfoTables.MENTALPATIENT_KEY,
				"mentalpatients");
		populationTypeTable.put(BaseInfoTables.DRUGGY_KEY, "druggys");
		populationTypeTable.put(BaseInfoTables.AIDSPOPULATIONS_KEY,
				"aidspopulations");
		populationTypeTable.put(BaseInfoTables.IDLEYOUTH_KEY, "idleyouths");
		populationTypeTable.put(BaseInfoTables.SUPERIORVISIT_KEY,
				"superiorVisits");
		populationTypeTable.put(BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY,
				"dangerousGoodsPractitioners");
		populationTypeTable.put(BaseInfoTables.OTHERATTENTIONPERSONNEL_KEY,
				"otherAttentionPersonnels");
		populationTypeTable.put(BaseInfoTables.ELDERLYPEOPLE_KEY,
				"elderlypeople");
		populationTypeTable.put(BaseInfoTables.HANDICAPPED_KEY, "handicappeds");
		populationTypeTable.put(BaseInfoTables.OPTIMALOBJECT_KEY,
				"optimalObjects");
		populationTypeTable.put(BaseInfoTables.AIDNEEDPOPULATION_KEY,
				"aidneedpopulation");
		populationTypeTable.put(BaseInfoTables.UNEMPLOYEDPEOPLE_KEY,
				"unemployedpeople");
		populationTypeTable.put(BaseInfoTables.NURTURESWOMEN_KEY,
				"nurtureswomen");
		populationTypeTable.put(BaseInfoTables.FPERSONNEL_KEY, "fPersonnels");
		populationTypeTable.put(BaseInfoTables.QPERSONNEL_KEY, "qPersonnels");
		populationTypeTable.put(BaseInfoTables.MPERSONNEL_KEY, "mPersonnels");
	}
}
