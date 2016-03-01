package com.tianque.threeRecordsIssue.dataTrans;

import java.util.HashMap;
import java.util.Map;

import com.tianque.plugin.account.constants.CompleteType;

public final class DataConvertDefine {

	/*
	 * 民生信息
	 */
	public static final int PEOPLEASPIRATION = 1;
	/*
	 * 困难群众
	 */
	public static final int POORPEOPLE = 2;
	/*
	 * 安定
	 */
	public static final int STEADYWORK = 3;
	/*
	 * 水利
	 */
	public static final int PEOPLEASPIRATION_WATER = 11;
	/*
	 * 交通
	 */
	public static final int PEOPLEASPIRATION_TRAFFIC = 12;
	/*
	 * 教育
	 */
	public static final int PEOPLEASPIRATION_EDUCATION = 13;
	/*
	 * 医疗
	 */
	public static final int PEOPLEASPIRATION_MEDICAL = 14;
	/*
	 * 农业
	 */
	public static final int PEOPLEASPIRATION_AGRICULTURE = 15;
	/*
	 * 能源
	 */
	public static final int PEOPLEASPIRATION_ENERGY = 16;
	/*
	 * 劳动
	 */
	public static final int PEOPLEASPIRATION_LABOR = 17;
	/*
	 * 环境信息
	 */
	public static final int PEOPLEASPIRATION_ENVIRONMENT = 18;
	/*
	 * 城乡规划建设信息
	 */
	public static final int PEOPLEASPIRATION_TOWN = 19;
	/*
	 * 科技文体
	 */
	public static final int PEOPLEASPIRATION_SCIENCE = 110;
	/*
	 * 其它信息
	 */

	public static final int PEOPLEASPIRATION_OTHER = 111;

	static Map<String, String> defines = new HashMap<String, String>();

	static Map<String, Integer> types = new HashMap<String, Integer>();

	static Map<String, String> compeletedTypes = new HashMap<String, String>();

	static Map<String, Integer> compeletedTypesCode = new HashMap<String, Integer>();

	static Map<String, Map<Integer, String>> convertMap = new HashMap<String, Map<Integer, String>>();
	static {
		defines.put(DataTransferConstants.PEOPLEASPIRATION_DATA,
				"peopleAspirationsDataConverter");
		defines.put(DataTransferConstants.STEP_DATA,
				"threeRecordsIssueStepDataConverter");
		defines.put(DataTransferConstants.STEPLOG_DATA,
				"threeRecordsIssueStepLogDataConverter");
		defines.put(DataTransferConstants.STEPGROUP_DATA,
				"threeRecordsIssueStepGroupDataConverter");
		defines.put(DataTransferConstants.WATERRESOURCE_DATA,
				"waterResourceDataConverter");
		defines.put(DataTransferConstants.TRAFFIC_DATA, "trafficDataConverter");
		defines.put(DataTransferConstants.ENERGY_DATA, "energyDataConverter");

		defines.put(DataTransferConstants.EDUCATION_DATA,
				"educationDataConverter");
		defines.put(DataTransferConstants.SCIENCE_DATA, "scienceDataConverter");
		defines.put(DataTransferConstants.MEDICAL_DATA, "medicalDataConverter");
		defines.put(DataTransferConstants.LABOR_DATA, "laborDataConverter");
		defines.put(DataTransferConstants.ENVIRONMENT_DATA,
				"environmentDataConverter");
		defines.put(DataTransferConstants.TOWN_DATA, "townDataConverter");
		defines.put(DataTransferConstants.AGRICULTURE_DATA,
				"agricultureDataConverter");
		defines.put(DataTransferConstants.OTHER_DATA, "otherDataConverter");
		defines.put(DataTransferConstants.POOR_PEOPLE_DATA,
				"poorPeopleDataConverter");
		defines.put(DataTransferConstants.POOR_MEMBER_DATA,
				"poorMemberDataConverter");
		defines.put(DataTransferConstants.STEADY_WORK_DATA,
				"steadyWorkDataConverter");
		defines.put(DataTransferConstants.STEADY_WORK_PEOPLE_DATA,
				"steadyWorkPeopleDataConverter");

		types
				.put(DataTransferConstants.PEOPLEASPIRATION_DATA,
						PEOPLEASPIRATION);

		types.put(DataTransferConstants.WATERRESOURCE_DATA,
				PEOPLEASPIRATION_WATER);
		types.put(DataTransferConstants.TRAFFIC_DATA, PEOPLEASPIRATION_TRAFFIC);
		types.put(DataTransferConstants.ENERGY_DATA, PEOPLEASPIRATION_ENERGY);

		types.put(DataTransferConstants.EDUCATION_DATA,
				PEOPLEASPIRATION_EDUCATION);
		types.put(DataTransferConstants.SCIENCE_DATA, PEOPLEASPIRATION_SCIENCE);
		types.put(DataTransferConstants.MEDICAL_DATA, PEOPLEASPIRATION_MEDICAL);
		types.put(DataTransferConstants.LABOR_DATA, PEOPLEASPIRATION_LABOR);
		types.put(DataTransferConstants.ENVIRONMENT_DATA,
				PEOPLEASPIRATION_ENVIRONMENT);
		types.put(DataTransferConstants.TOWN_DATA, PEOPLEASPIRATION_TOWN);
		types.put(DataTransferConstants.AGRICULTURE_DATA,
				PEOPLEASPIRATION_AGRICULTURE);
		types.put(DataTransferConstants.OTHER_DATA, PEOPLEASPIRATION_OTHER);
		types.put(DataTransferConstants.POOR_PEOPLE_DATA, POORPEOPLE);
		types.put(DataTransferConstants.STEADY_WORK_DATA, STEADYWORK);

		convertMap.put(DataTransferConstants.WATERRESOURCE_DATA,
				getConvertMap(DataTransferConstants.WATERRESOURCE_DATA));
		convertMap.put(DataTransferConstants.TRAFFIC_DATA,
				getConvertMap(DataTransferConstants.TRAFFIC_DATA));
		convertMap.put(DataTransferConstants.ENERGY_DATA,
				getConvertMap(DataTransferConstants.ENERGY_DATA));
		convertMap.put(DataTransferConstants.EDUCATION_DATA,
				getConvertMap(DataTransferConstants.EDUCATION_DATA));
		convertMap.put(DataTransferConstants.SCIENCE_DATA,
				getConvertMap(DataTransferConstants.SCIENCE_DATA));
		convertMap.put(DataTransferConstants.MEDICAL_DATA,
				getConvertMap(DataTransferConstants.MEDICAL_DATA));
		convertMap.put(DataTransferConstants.LABOR_DATA,
				getConvertMap(DataTransferConstants.LABOR_DATA));
		convertMap.put(DataTransferConstants.ENVIRONMENT_DATA,
				getConvertMap(DataTransferConstants.ENVIRONMENT_DATA));
		convertMap.put(DataTransferConstants.TOWN_DATA,
				getConvertMap(DataTransferConstants.TOWN_DATA));
		convertMap.put(DataTransferConstants.AGRICULTURE_DATA,
				getConvertMap(DataTransferConstants.AGRICULTURE_DATA));
		convertMap.put(DataTransferConstants.OTHER_DATA,
				getConvertMap(DataTransferConstants.OTHER_DATA));
		convertMap.put(DataTransferConstants.POOR_PEOPLE_DATA,
				getConvertMap(DataTransferConstants.POOR_PEOPLE_DATA));
		convertMap.put(DataTransferConstants.POOR_MEMBER_DATA,
				getConvertMap(DataTransferConstants.POOR_MEMBER_DATA));
		convertMap.put(DataTransferConstants.STEADY_WORK_DATA,
				getConvertMap(DataTransferConstants.STEADY_WORK_DATA));
		convertMap.put(DataTransferConstants.STEADY_WORK_PEOPLE_DATA,
				getConvertMap(DataTransferConstants.STEADY_WORK_PEOPLE_DATA));
		compeletedTypes.put("33", "实质办结");
		compeletedTypes.put("34", "落实帮扶、落实项目");
		compeletedTypes.put("35", "整合项目");
		compeletedTypes.put("36", "纳入项目库");
		compeletedTypes.put("37", "问题中止");
		compeletedTypes.put("38", "呈报乡镇");
		compeletedTypes.put("39", "呈报县台账办");
		compeletedTypes.put("40", "申报县台账工作联席会议");
		compeletedTypes.put("41", "申报县委县政府");
		compeletedTypes.put("42", "其他方式");
		compeletedTypes.put("609", "政策解答");
		compeletedTypes.put("778", "脱贫");
		compeletedTypes.put("779", "纳入低保");
		compeletedTypes.put("780", "纳入五保");
		compeletedTypes.put("792", "实质性解决困难");
		compeletedTypes.put("793", "临时救助");
		compeletedTypes.put("794", "实质化解");
		compeletedTypes.put("795", "实质性终结");
		compeletedTypes.put("796", "程序性终结");
		compeletedTypes.put("797", "稳控中");

		compeletedTypesCode.put("33", CompleteType.COMPLETE);
		compeletedTypesCode.put("34", CompleteType.IMPLEMENT);
		compeletedTypesCode.put("35", CompleteType.INTEGRATE_PROJECT);
		compeletedTypesCode.put("36", CompleteType.ITEM);
		compeletedTypesCode.put("37", CompleteType.QUESTIONEND);
		compeletedTypesCode.put("42", CompleteType.OTHER);
		compeletedTypesCode.put("609", CompleteType.POLICYSOLUTION);
		compeletedTypesCode.put("778", CompleteType.NOT_POOR);
		compeletedTypesCode.put("779", CompleteType.LOW_INDEMNITY);
		compeletedTypesCode.put("780", CompleteType.FIVE_INDEMNITY);
		compeletedTypesCode.put("792", CompleteType.REAL_SOLVE_TROUBLE);
		compeletedTypesCode.put("793", CompleteType.LOW_INDEMNITY);
		compeletedTypesCode.put("794", CompleteType.REALCOMPLETE_SOLVE);
		compeletedTypesCode.put("795", CompleteType.COMPLETE);
		compeletedTypesCode.put("796", CompleteType.PROGRAM_COMPLETE);
		compeletedTypesCode.put("797", CompleteType.STEADY_CONTROL);

	}

	public static String getConvertBeanDefine(String data_type) {
		return defines.get(data_type);
	}

	public static String getCompeletedCode(String type) {
		return compeletedTypesCode.get(type) == null ? null
				: compeletedTypesCode.get(type).toString();
	}

	public static String getLogDesc(String type) {
		return compeletedTypes.get(type) == null ? type : compeletedTypes
				.get(type);
	}

	public static Map<Integer, String> getConvertMapBeanDefine(String data_type) {
		return convertMap.get(data_type);
	}

	public static int getType(String data_type) {
		Integer type = types.get(data_type);
		return type == null ? 0 : type.intValue();
	}

	private static Map<Integer, String> getConvertMap(String dataType) {
		Map<Integer, String> rtMap = new HashMap<Integer, String>();
		if (dataType.equals(DataTransferConstants.POOR_PEOPLE_DATA)) {
			rtMap.put(0, DataTransferConstants.POOR_PEOPLE_DATA);
			rtMap.put(1, DataTransferConstants.POOR_MEMBER_DATA);
		} else if (dataType.equals(DataTransferConstants.STEADY_WORK_DATA)) {
			rtMap.put(0, DataTransferConstants.STEADY_WORK_DATA);
			rtMap.put(1, DataTransferConstants.STEADY_WORK_PEOPLE_DATA);
		} else {
			rtMap.put(0, DataTransferConstants.PEOPLEASPIRATION_DATA);
			rtMap.put(1, dataType);
		}

		rtMap.put(2, DataTransferConstants.STEP_DATA);
		rtMap.put(3, DataTransferConstants.STEPLOG_DATA);
		rtMap.put(4, DataTransferConstants.STEPGROUP_DATA);

		return rtMap;
	}
}
