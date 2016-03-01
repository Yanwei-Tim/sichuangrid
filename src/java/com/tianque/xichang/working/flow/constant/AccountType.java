package com.tianque.xichang.working.flow.constant;

import java.util.ArrayList;
import java.util.List;

public class AccountType {

	public static final String PEOPLEASPIRATION = "PEOPLEASPIRATION";
	public static final String POORPEOPLE = "POORPEOPLE";
	public static final String STEADYWORK = "STEADYWORK";
	public static final String CREATETABLE = "createTable";
	public static final String ORGANIZATION_TYPE = "organizationType";
	public static final String FOURTEAM_TYPE = "fourTeamType";

	public static final String PEOPLEASPIRATION_STR = "民生诉求";
	public static final String POORPEOPLE_STR = "困难群众";
	public static final String STEADYWORK_STR = "稳定工作";

	public static final String XICHANG = "西昌市";
	public static final Integer REPORTTOTAL = 7;

	public static final String EVALUATEMODE_VIEW = "view";
	public static final String SEARCH_TARGET = "searchTarget";

	/** 省和市级 */
	public static final String ORGCODEFINDLEVEL_PROVINCE_AND_CITY_AND_DISTRICT = "1,3";
	public static final String ORGCODEFINDLEVEL_DISTRICT = "3";
	public static final String ORGCODEFINDLEVEL_TOWN = "4";
	public static final String ORGCODEFINDLEVEL_VILLAGE = "5";
	/** 网格 */
	public static final String ORGCODEFINDLEVEL_GRID = "6";

	public static final int perPage = 300;

	/** 社区的每次循环的个数 */
	public static final int PER_PAGE_VILLAGE = 1000;
	/** 社区统计的，总共分多少页（也即是要开的线程的个数） */
	public static final int PAGE_TOTAL_VILLAGE = 4;
	/** 社区统计的，报表的类型的值 */
	public static final int VILLAGE_REPORT_TYPE = 4;
	/** 乡镇1统计的报表类型 */
	public static final int TOWN_ONE_REPORT_TYPE = 2;
	/** 乡镇2统计的报表类型 */
	public static final int TOWN_TOW_REPORT_TYPE = 3;
	/** 县区级统计的报表类型 */
	public static final int DISTRICT_REPORT_TYPE = 1;
	/** 西昌1统计的报表类型 */
	public static final int XICHANG_ONE_REPORT_TYPE = 5;
	/** 西昌2统计的报表类型 */
	public static final int XICHANG_TWO_REPORT_TYPE = 6;
	/** 西昌3统计的报表类型 */
	public static final int XICHANG_THREE_REPORT_TYPE = 7;

	/** 三本台账时限考核成绩每一次查询的数据条数（每页多少条） */
	public static final int ACCOUNT_TIMEOUTS_PAGE_SIZE = 1000;
	public static final List<String> accountTypes = new ArrayList<String>();
	public static final List<Integer> TOWN_ACCOUNT_TYPES = new ArrayList<Integer>();
	public static final List<Integer> DISTRICT_ACCOUNT_TYPES = new ArrayList<Integer>();
	static {
		accountTypes.add(PEOPLEASPIRATION);
		accountTypes.add(POORPEOPLE);
		accountTypes.add(STEADYWORK);

		TOWN_ACCOUNT_TYPES.add(TOWN_ONE_REPORT_TYPE);
		TOWN_ACCOUNT_TYPES.add(TOWN_TOW_REPORT_TYPE);

		DISTRICT_ACCOUNT_TYPES.add(DISTRICT_REPORT_TYPE);
		DISTRICT_ACCOUNT_TYPES.add(XICHANG_ONE_REPORT_TYPE);
		DISTRICT_ACCOUNT_TYPES.add(XICHANG_TWO_REPORT_TYPE);
		DISTRICT_ACCOUNT_TYPES.add(XICHANG_THREE_REPORT_TYPE);

	}

}
