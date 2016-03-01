package com.tianque.newVillage.constant;

import java.util.HashMap;
import java.util.Map;

public class ComprehensiveInfoConstant {

	/***
	 * 上报
	 */
	public static final Integer IS_REPORT_KEY = 0;
	/***
	 * 规划
	 */
	public static final Integer IS_PLANING = 1;

	/** 基本信息 */
	public static final Integer BASICYEARINFO_KEY = 0;

	/** 新村建设 */
	public static final Integer NEWVILLAGE_KEY = 1;

	/** 产业发展 */
	public static final Integer INDUSTRYDEVELOPMENT_KEY = 2;

	/** 基础设施 */
	public static final Integer INFRASTRUCTURE_KEY = 3;

	/** 公共服务 */
	public static final Integer COMMONSERVICEINFO_KEY = 4;

	/** 环境改造 */
	public static final Integer ENVIRONMENTALREFORM_KEY = 5;

	/** 基层组织 */
	public static final Integer ORGANIZATIONCONSTRUCTION_KEY = 6;

	/** 资金投入 */
	public static final Integer CAPITALINVESTED_KEY = 7;

	/** 农民人均收入 */
	public static final Integer FARMERPERINCOMEINFO_KEY = 8;

	/** 基本信息 */
	public static final String BASICYEARINFO_DISPLAYNAME = "basicYearInfo";

	/** 新村建设 */
	public static final String NEWVILLAGE_DISPLAYNAME = "newVillage";

	/** 产业发展 */
	public static final String INDUSTRYDEVELOPMENT_DISPLAYNAME = "industryDevelopment";

	/** 基础设施 */
	public static final String INFRASTRUCTURE_DISPLAYNAME = "infrastructure";

	/** 公共服务 */
	public static final String COMMONSERVICEINFO_DISPLAYNAME = "commonServiceInfo";

	/** 环境改造 */
	public static final String ENVIRONMENTALREFORM_DISPLAYNAME = "environmentalReform";

	/** 基层组织 */
	public static final String ORGANIZATIONCONSTRUCTION_DISPLAYNAME = "organizationConstruction";

	/** 资金投入 */
	public static final String CAPITALINVESTED_DISPLAYNAME = "capitalInvested";

	/** 农民人均收入 */
	public static final String FARMERPERINCOMEINFO_DISPLAYNAME = "farmerPerIncomeInfo";

	public static final Map<Integer, String> map = new HashMap<Integer, String>();

	public static final Integer REPORT_NOT = 0;// 未上报
	public static final Integer REPORT_YES = 1;// 已上报

	public static final Integer CHECK_NOT = 0;// 未审核
	public static final Integer CHECK_YES = 1;// 审核通过
	public static final Integer CHECK_BACK = 2;// 审核不通过被回退

	public static final Integer YES = 1; //是
	public static final Integer NO = 0; //否
	
	/** 运算符大于 */
	public static final String GREATERTHAN_KEY = "大于";
	/** 运算符小于 */
	public static final String LESSTHAN_KEY = "小于";
	/** 运算符等于 */
	public static final String EQUAL_KEY = "等于";
	/** 运算符不等于 */
	public static final String NOTEQUAL_KEY = "不等于";
	/** 运算符介于 */
	public static final String BETWEEN_KEY = "介于";
	/** 运算符大于等于 */
	public static final String GREATEROREQUAL_KEY = "大于等于";
	/** 运算符小于等于 */
	public static final String LESSOREQUAL_KEY = "小于等于";

	/** 运算符 */
	public static final Map<Integer, String> operationMap = new HashMap<Integer, String>();

	/** 运算符大于 标示值 */
	public static final Integer GREATERTHAN_VALUE = 0;
	/** 运算符小于 标示值 */
	public static final Integer LESSTHAN_VALUE = 1;
	/** 运算符等于 标示值 */
	public static final Integer EQUAL_VALUE = 2;
	/** 运算符不等于 标示值 */
	public static final Integer NOTEQUAL_VALUE = 3;
	/** 运算符介于 标示值 */
	public static final Integer BETWEEN_VALUE = 4;
	/** 运算符大于等于 标示值 */
	public static final Integer GREATEROREQUAL_VALUE = 5;
	/** 运算符小于等于 标示值 */
	public static final Integer LESSOREQUAL_VALUE = 6;
	/**规则字段英文名*/
	//计算特色产业 完成度
	public static final String CHARACTERISTICPLANTING="characteristicPlanting";
	//农业主导产业收入占农村家庭经营收入的比重
	public static final String PROPORTIONOFINCOME="proportionOfIncome";
	//人均可支配收入
	public static final String FARMERPERINCOME="farmerPerIncome";
	//人均安全住房面积
	public static final String CAPITAHOUSINGAREA="capitaHousingArea";
	//无房户、危房户、住房困难户住房问题全部解决
	public static final String HOUSINGSOLUTION="housingSolution";
	//九年义务教育目标人群全覆盖
	public static final String EDUCATIONCOUNTFULLCOVERAGE="educationCountFullCoverage";
	//新农合参保率
	public static final String INSUREDPROPORTION="insuredProportion";
	//新农保应保尽保
	public static final String HASBUYINSURANCE="hasBuyInsurance";
	//每年组织群众性文体活动
	public static final String ACTIVITIESIDENTIFICATION="activitiesIdentification";
	//公共活动服务中心设施面积
	public static final String SOCIALWORKCENTERAREA="socialWorkCenterArea";
	//基层党组织/党员调查满意度
	public static final String SURVEYSATISFACTION="surveySatisfaction";
	//三务公开满意度
	public static final String THREESATISFACTION="threeSatisfaction";
	//新村建设全覆盖
	public static final String ISALLSTANDING="isAllStanding";
	//通村通组道路硬化率
	public static final String ROADHARDENINGRATE="roadHardeningRate";
	//安全饮水全覆盖
	public static final String DRINKINGCOVERAGE="drinkingCoverage";
	//电网改造全覆盖
	public static final String ISPOWERGRID="isPowerGrid";
	//面源污染有效治理
	public static final String TREATMENTPOLLUTION="treatmentPollution";
	//生活垃圾处理全覆盖
	public static final String GARBAGEDISPOSAL="garbageDisposal";
	//生活污水处理覆盖率
	public static final String SEWAGETREATMENTCOVERAGE="sewageTreatmentCoverage";
	//农村院落整治全覆盖
	public static final String COURTYARDRENOVATIONCOVERAGE="courtyardRenovationCoverage";
	
	/**新农村 验收表是否符合标准*/
	public static final String NEW_VILLAGE_ISACCEPTANCE = "符合标准";
	public static final String NEW_VILLAGE_NOTACCEPTANCE = "不符合标准";
	
	static {
		map.put(NEWVILLAGE_KEY, NEWVILLAGE_DISPLAYNAME);
		map.put(INDUSTRYDEVELOPMENT_KEY, INDUSTRYDEVELOPMENT_DISPLAYNAME);
		map.put(INFRASTRUCTURE_KEY, INFRASTRUCTURE_DISPLAYNAME);
		map.put(COMMONSERVICEINFO_KEY, COMMONSERVICEINFO_DISPLAYNAME);
		map.put(ENVIRONMENTALREFORM_KEY, ENVIRONMENTALREFORM_DISPLAYNAME);
		map.put(ORGANIZATIONCONSTRUCTION_KEY,
				ORGANIZATIONCONSTRUCTION_DISPLAYNAME);
		map.put(CAPITALINVESTED_KEY, CAPITALINVESTED_DISPLAYNAME);
		map.put(FARMERPERINCOMEINFO_KEY, FARMERPERINCOMEINFO_DISPLAYNAME);
		map.put(BASICYEARINFO_KEY, BASICYEARINFO_DISPLAYNAME);
		
		operationMap.put(GREATERTHAN_VALUE, GREATERTHAN_KEY);
		operationMap.put(LESSTHAN_VALUE, LESSTHAN_KEY);
		operationMap.put(EQUAL_VALUE, EQUAL_KEY);
		operationMap.put(NOTEQUAL_VALUE, NOTEQUAL_KEY);
		operationMap.put(BETWEEN_VALUE, BETWEEN_KEY);
		operationMap.put(GREATEROREQUAL_VALUE, GREATEROREQUAL_KEY);
		operationMap.put(LESSOREQUAL_VALUE, LESSOREQUAL_KEY);
	}
}
