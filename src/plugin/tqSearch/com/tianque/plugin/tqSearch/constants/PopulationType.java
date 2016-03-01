package com.tianque.plugin.tqSearch.constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class PopulationType {
	public static Map<String, String> populationType = new LinkedHashMap<String, String>();
	
	public final static String POSITIVEINFO_KEY = "positiveInfo";
	public final static String POSITIVEINFO_NAME = "刑释人员";
	public final static String RECTIFICATIVEPERSON_KEY = "rectificativePerson";
	public final static String RECTIFICATIVEPERSON_NAME = "社区矫正人员";
	public final static String MENTALPATIENT_KEY = "mentalPatient";
	public final static String MENTALPATIENT_NAME = "严重精神障碍患者";
	public final static String DRUGGY_KEY = "druggy";
	public final static String DRUGGY_NAME = "吸毒人员";
	public final static String IDLEYOUTH_KEY = "idleYouth";
	public final static String IDLEYOUTH_NAME = "重点青少年";
	public final static String SUPERIORVISIT_KEY = "superiorVisit";
	public final static String SUPERIORVISIT_NAME = "重点上访人员";
	public final static String DANGEROUSGOODSPRACTITIONER_KEY = "dangerousGoodsPractitioner";
	public final static String DANGEROUSGOODSPRACTITIONER_NAME = "危险品从业人员";
	public final static String OTHERATTENTIONPERSONNEL_KEY = "otherAttentionPersonnel";
	public final static String OTHERATTENTIONPERSONNEL_NAME = "其他人员";
	public final static String ELDERLYPEOPLE_KEY = "elderlyPeople";
	public final static String ELDERLYPEOPLE_NAME = "老年人";
	public final static String HANDICAPPED_KEY = "handicapped";
	public final static String HANDICAPPED_NAME = "残疾人";
	public final static String OPTIMALOBJECT_KEY = "optimalObject";
	public final static String OPTIMALOBJECT_NAME = "优抚对象";
	public final static String AIDNEEDPOPULATION_KEY = "aidNeedPopulation";
	public final static String AIDNEEDPOPULATION_NAME = "需救助人员";
	public final static String UNEMPLOYEDPEOPLE_KEY = "unemployedPeople";
	public final static String UNEMPLOYEDPEOPLE_NAME = "失业人员";
	public final static String NURTURESWOMEN_KEY = "nurturesWomen";
	public final static String NURTURESWOMEN_NAME = "育龄妇女";
	
	static {
		populationType.put(POSITIVEINFO_KEY, POSITIVEINFO_NAME);
		populationType.put(RECTIFICATIVEPERSON_KEY, RECTIFICATIVEPERSON_NAME);
		populationType.put(MENTALPATIENT_KEY, MENTALPATIENT_NAME);
		populationType.put(DRUGGY_KEY, DRUGGY_NAME);
		populationType.put(IDLEYOUTH_KEY, IDLEYOUTH_NAME);
		populationType.put(SUPERIORVISIT_KEY, SUPERIORVISIT_NAME);
		populationType.put(DANGEROUSGOODSPRACTITIONER_KEY, DANGEROUSGOODSPRACTITIONER_NAME);
		populationType.put(OTHERATTENTIONPERSONNEL_KEY, OTHERATTENTIONPERSONNEL_NAME);
		populationType.put(ELDERLYPEOPLE_KEY, ELDERLYPEOPLE_NAME);
		populationType.put(HANDICAPPED_KEY, HANDICAPPED_NAME);
		populationType.put(OPTIMALOBJECT_KEY, OPTIMALOBJECT_NAME);
		populationType.put(AIDNEEDPOPULATION_KEY, AIDNEEDPOPULATION_NAME);
		populationType.put(UNEMPLOYEDPEOPLE_KEY, UNEMPLOYEDPEOPLE_NAME);
		populationType.put(NURTURESWOMEN_KEY, NURTURESWOMEN_NAME);
	}
}
