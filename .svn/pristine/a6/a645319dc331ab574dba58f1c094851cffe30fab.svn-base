package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class HandicappedType extends BaseProperty {

	public final static int EYESIGHT_DEFORMITY = 1;
	public final static int WIT_DEFORMITY = 2;
	public final static int LIMB_DEFORMITY = 3;
	public final static int SPIRIT_DEFORMITY = 4;
	public final static int EAR_DEFORMITY = 5;
	public final static int SPEECH_DEFORMITY = 6;
	public final static int MULTIPLE_DEFORMITY = 7;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(EYESIGHT_DEFORMITY, "视力残疾"));
		properties.add(getInternalProperty(WIT_DEFORMITY, "智力残疾"));
		properties.add(getInternalProperty(LIMB_DEFORMITY, "肢体残疾"));
		properties.add(getInternalProperty(SPIRIT_DEFORMITY, "精神残疾"));
		properties.add(getInternalProperty(EAR_DEFORMITY, "听力残疾"));
		properties.add(getInternalProperty(SPEECH_DEFORMITY, "言语残疾"));
		properties.add(getInternalProperty(MULTIPLE_DEFORMITY, "多重残疾"));
		return properties;
	}

	private static Map<String, String> myVisitRecordAidReasonType = new HashMap<String, String>();

	public static Map<String, String> myVisitRecordAidReasonType() {
		if (myVisitRecordAidReasonType.size() > 0) {
			return myVisitRecordAidReasonType;
		}
		myVisitRecordAidReasonType.put("视力残疾", "视力残疾");
		myVisitRecordAidReasonType.put("智力残疾", "智力残疾");
		myVisitRecordAidReasonType.put("肢体残疾", "肢体残疾");
		myVisitRecordAidReasonType.put("精神残疾", "精神残疾");
		myVisitRecordAidReasonType.put("听力残疾", "听力残疾");
		myVisitRecordAidReasonType.put("言语残疾", "言语残疾");
		myVisitRecordAidReasonType.put("多重残疾", "多重残疾");
		return myVisitRecordAidReasonType;
	}
}
