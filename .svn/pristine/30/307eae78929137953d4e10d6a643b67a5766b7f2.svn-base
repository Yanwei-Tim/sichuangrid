package com.tianque.xichang.working.report.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.xichang.working.flow.constant.FinishType;

public class ReportRow {
	public static final String CREATE = "新建";
	public static final String YESTERYEAR = "上年接转";
	public static final String OTHERREPORT = "其他台账转入";
	public static final String HIGHERLEVEL = "上级主管部门和市级领导班子有关领导交办(市级部门选填)";
	public static final String MUNICIPALPARTY = "市委市政府及市级领导班子有关领导交办(乡镇选填)";
	public static final String PEOPLECONGRESS = "市人大议案、建议、意见交办";
	public static final String POLITICALCONSULTATIVE = "市政协提案、建议、意见交办";

	/** 网格类型 */
	public static final String TOWNTOCITY_STREET_STR = "行政区域";
	public static final String FUNCTIONAL_STR = "职能部门";

	public static final String PRECEDINGMONTHUNFINISH = "上月办理中";
	public static final String THISMONTHUNFINISH = "本月办理中";

	public static final Long OTHERREPORTFINISHED = 1L;
	public static final String OTHERREPORTFINISHED_STR = "已办结其他台账转入";

	public static final Long ESSENCE_END = (long) FinishType.ESSENCE_END;// 实质性办结
	public static final Long PROGRAM_END = (long) FinishType.PROGRAM_END;// 程序性办结
	public static final String ESSENCE_END_STR = "实质性办结";
	public static final String PROGRAM_END_STR = "程序性办结";
	public static final Long REPORT_TO_TOWN_END = (long) FinishType.REPORT_TO_TOWN_END;// 呈报乡镇
	public static final Long REPORT_TO_CITY_END = (long) FinishType.REPORT_TO_CITY_END;// 呈报市
	public static final Long CITY_REPORT_TO_TOWN_END = (long) FinishType.CITY_REPORT_TO_TOWN_END;// 区市县呈报乡镇
	public static final String REPORT_TO_TOWN_END_STR = "呈报乡镇";
	public static final String REPORT_TO_CITY_END_STR = "呈报市";
	public static final String CITY_REPORT_TO_TOWN_END_STR = "流转乡镇";
	public static final Long REPORT_TO_TOWN_FINISH = (long) FinishType.REPORT_TO_TOWN_END;// 呈报乡镇并且乡镇已办结
	public static final String REPORT_TO_TOWN_FINISH_STR = "呈报乡镇并且乡镇已办结";
	public static final Long TOWNTOCITY = 1L;// 乡镇建账呈报市台账办
	public static final Long VILLAGETOCITY = 2L;// 村、社区呈报乡镇呈报市台账办
	public static final String TOWNTOCITY_STR = "乡镇建账呈报市台账办";
	public static final String VILLAGETOCITY_STR = "村、社区呈报乡镇呈报市台账办";
	public static final Long OWNER_ORG_CREATE = 1L; // 市县区级建账 当前仅指西昌市级
	public static final Long SUB_ORG_CREATE = 2L; // 市县区级下辖乡镇街道建账
	public static final Long LEVEL2SUB_ORG_CREATE = 3L; // 市县区级下辖村、社区建账
	public static final String OWNER_ORG_CREATE_STR = "当前层级建账";
	public static final String SUB_ORG_CREATE_STR = "当前层级下辖第一层级建账";
	public static final String LEVEL2SUB_ORG_CREATE_STR = "当前层级下辖第二层级建账";
	public static final Long OWNER_ORG_FINISH = 1L; // 市县区级办结
	public static final Long SUB_ORG_FINISH = 2L; // 市县区级下辖乡镇街道办结
	public static final Long LEVEL2SUB_ORG_FINISH = 3L; // 市县区级下辖村、社区办结
	public static final String OWNER_ORG_FINISH_STR = "当前层级办结";
	public static final String SUB_ORG_FINISH_STR = "当前层级下辖第一层级办结";
	public static final String LEVEL2SUB_ORG_FINISH_STR = "当前层级下辖第二层级办结";

	/** 已办结其他台账转入 */
	private static final Map<Long, String> REPORTFISINISHKEYS = new HashMap<Long, String>();
	/** 办结类型 */
	private static final Map<Long, String> ENDTYPE = new HashMap<Long, String>();
	/** 呈报市 */
	private static final Map<Long, String> REPORTTOCITY = new HashMap<Long, String>();
	/** 呈报乡镇 */
	private static final Map<Long, String> REPORTTOTOWN = new HashMap<Long, String>();
	/** 呈报乡镇并且乡镇已办结 */
	private static final Map<Long, String> REPORTTOTOWNFINISH = new HashMap<Long, String>();
	/** （村、社区呈报乡镇 或 乡镇建账） 呈报市台账办 */
	private static final Map<Long, String> WHOREPORTTOCITY = new HashMap<Long, String>();
	/** 市级建账、市辖乡镇街道建账、市辖村社区建账 */
	private static final Map<Long, String> CREATEORGLEVEL = new HashMap<Long, String>();
	/** 市级办结、市辖乡镇街道办结、市辖村社区办结 */
	private static final Map<Long, String> FINISHORGLEVEL = new HashMap<Long, String>();

	static {
		REPORTFISINISHKEYS.put(OTHERREPORTFINISHED, OTHERREPORTFINISHED_STR);

		ENDTYPE.put(ESSENCE_END, ESSENCE_END_STR);
		ENDTYPE.put(PROGRAM_END, PROGRAM_END_STR);

		REPORTTOTOWN.put(REPORT_TO_TOWN_END, REPORT_TO_TOWN_END_STR);

		REPORTTOTOWNFINISH
				.put(REPORT_TO_TOWN_FINISH, REPORT_TO_TOWN_FINISH_STR);

		REPORTTOCITY.put(REPORT_TO_CITY_END, REPORT_TO_CITY_END_STR);

		WHOREPORTTOCITY.put(TOWNTOCITY, TOWNTOCITY_STR);
		WHOREPORTTOCITY.put(VILLAGETOCITY, VILLAGETOCITY_STR);

		CREATEORGLEVEL.put(OWNER_ORG_CREATE, OWNER_ORG_CREATE_STR);
		CREATEORGLEVEL.put(SUB_ORG_CREATE, SUB_ORG_CREATE_STR);
		CREATEORGLEVEL.put(LEVEL2SUB_ORG_CREATE, LEVEL2SUB_ORG_CREATE_STR);

		FINISHORGLEVEL.put(OWNER_ORG_FINISH, OWNER_ORG_FINISH_STR);
		FINISHORGLEVEL.put(SUB_ORG_FINISH, SUB_ORG_FINISH_STR);
		FINISHORGLEVEL.put(LEVEL2SUB_ORG_FINISH, LEVEL2SUB_ORG_FINISH_STR);
	}

	public static Map<Long, String> getIsFinishKeys() {
		return REPORTFISINISHKEYS;
	}

	public static Map<Long, String> getEndType() {
		return ENDTYPE;
	}

	public static Map<Long, String> getReportToTown() {
		return REPORTTOTOWN;
	}

	public static Map<Long, String> getReportToTownFinish() {
		return REPORTTOTOWNFINISH;
	}

	public static Map<Long, String> getReportToCity() {
		return REPORTTOCITY;
	}

	public static Map<Long, String> getWhoReportToCity() {
		return WHOREPORTTOCITY;
	}

	public static Map<Long, String> getCreateOrgLevel() {
		return CREATEORGLEVEL;
	}

	public static Map<Long, String> getFinishOrgLevel() {
		return FINISHORGLEVEL;
	}

	public static List<ReportRow> getDistrictReportRowList() {
		return District.REPORTROWLIST;
	}

	public static List<ReportRow> getDistrictReportRowYearList() {
		return District.REPORTROWLISTYEAR;
	}

	public static List<ReportRow> getXiChangTwoReportRowYearList() {
		return XiChangTwo.REPORTROWLISTYEAR;
	}

	public static List<ReportRow> getXiChangThreeReportRowList() {
		return XiChangThree.REPORTROWLIST;
	}

	public static List<ReportRow> getTownOneReportRowList() {
		return TownOne.REPORTROWLIST;
	}

	public static List<ReportRow> getTownOneReportRowYearList() {
		return TownOne.REPORTROWLISTYEAR;
	}

	public static List<ReportRow> getTownTwoReportRowList() {
		return TownTwo.REPORTROWLIST;
	}

	public static List<ReportRow> getTownTwoReportRowYearList() {
		return TownTwo.REPORTROWLISTYEAR;
	}

	public static List<ReportRow> getVillageReportRowList() {
		return Village.REPORTROWLIST;
	}

	public static List<ReportRow> getVillageReportRowYearList() {
		return Village.REPORTROWLISTYEAR;
	}

	// 西昌1
	public static List<ReportRow> getXiChangOneReportRowListCollection() {
		return XiChangOne.REPORTROWLISTCOLLECTION;
	}

	public static List<ReportRow> getXiChangOneReportRowListYearCollection() {
		return XiChangOne.REPORTROWLISTYEARCOLLECTION;
	}

	public static List<ReportRow> getXiChangOneReportRowListEnd() {
		return XiChangOne.REPORTROWLISTEND;
	}

	public static List<ReportRow> getXiChangOneReportRowListYearEnd() {
		return XiChangOne.REPORTROWLISTYEAREND;
	}

	static class District {
		private static final List<ReportRow> REPORTROWLISTYEAR = new ArrayList<ReportRow>();
		private static final List<ReportRow> REPORTROWLIST = new ArrayList<ReportRow>();
		static {
			List temp = null;

			temp = new ArrayList<String>();
			temp.add(OTHERREPORT);
			temp.add(CREATE);
			temp.add(HIGHERLEVEL);
			temp.add(PEOPLECONGRESS);
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLISTYEAR.add(new ReportRow("row0", temp));

			temp = new ArrayList<String>();
			temp.add(OTHERREPORT);
			REPORTROWLISTYEAR.add(new ReportRow("row1", temp));

			temp = new ArrayList<String>();
			temp.add(CREATE);
			temp.add(HIGHERLEVEL);
			temp.add(PEOPLECONGRESS);
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLISTYEAR.add(new ReportRow("row2", temp));

			temp = new ArrayList<String>();
			temp.add(CREATE);
			REPORTROWLISTYEAR.add(new ReportRow("row3", temp));

			temp = new ArrayList<String>();
			temp.add(HIGHERLEVEL);
			REPORTROWLISTYEAR.add(new ReportRow("row4", temp));

			temp = new ArrayList<String>();
			temp.add(PEOPLECONGRESS);
			REPORTROWLISTYEAR.add(new ReportRow("row5", temp));

			temp = new ArrayList<String>();
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLISTYEAR.add(new ReportRow("row6", temp));

			temp = new ArrayList<String>();
			temp.add(OTHERREPORTFINISHED_STR);
			temp.add(ESSENCE_END_STR);
			temp.add(PROGRAM_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row7", temp));

			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			temp.add(PROGRAM_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row8", temp));

			temp = new ArrayList<String>();
			temp.add(OTHERREPORTFINISHED_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row9", temp));

			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row10", temp));

			temp = new ArrayList<String>();
			temp.add(PROGRAM_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row11", temp));

			temp = new ArrayList<String>();
			temp.add(REPORT_TO_CITY_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row13", temp));

			temp = new ArrayList<String>();
			temp.add(PRECEDINGMONTHUNFINISH);
			REPORTROWLIST.add(new ReportRow("row14", temp));

			temp = new ArrayList<String>();
			temp.add(OTHERREPORT);
			temp.add(CREATE);
			temp.add(HIGHERLEVEL);
			temp.add(PEOPLECONGRESS);
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLIST.add(new ReportRow("row15", temp));

			temp = new ArrayList<String>();
			temp.add(OTHERREPORT);
			REPORTROWLIST.add(new ReportRow("row16", temp));

			temp = new ArrayList<String>();
			temp.add(CREATE);
			temp.add(HIGHERLEVEL);
			temp.add(PEOPLECONGRESS);
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLIST.add(new ReportRow("row17", temp));

			temp = new ArrayList<String>();
			temp.add(CREATE);
			REPORTROWLIST.add(new ReportRow("row18", temp));

			temp = new ArrayList<String>();
			temp.add(HIGHERLEVEL);
			REPORTROWLIST.add(new ReportRow("row19", temp));

			temp = new ArrayList<String>();
			temp.add(PEOPLECONGRESS);
			REPORTROWLIST.add(new ReportRow("row20", temp));

			temp = new ArrayList<String>();
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLIST.add(new ReportRow("row21", temp));

			temp = new ArrayList<String>();
			temp.add(OTHERREPORTFINISHED_STR);
			temp.add(ESSENCE_END_STR);
			temp.add(PROGRAM_END_STR);
			REPORTROWLIST.add(new ReportRow("row22", temp));

			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			temp.add(PROGRAM_END_STR);
			REPORTROWLIST.add(new ReportRow("row23", temp));

			temp = new ArrayList<String>();
			temp.add(OTHERREPORTFINISHED_STR);
			REPORTROWLIST.add(new ReportRow("row24", temp));

			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			REPORTROWLIST.add(new ReportRow("row25", temp));

			temp = new ArrayList<String>();
			temp.add(PROGRAM_END_STR);
			REPORTROWLIST.add(new ReportRow("row26", temp));

			temp = new ArrayList<String>();
			temp.add(REPORT_TO_CITY_END_STR);
			REPORTROWLIST.add(new ReportRow("row28", temp));

			temp = new ArrayList<String>();
			temp.add(THISMONTHUNFINISH);
			REPORTROWLIST.add(new ReportRow("row29", temp));

		}
	}

	// 西昌1的行类
	static class XiChangOne {
		// 按年统计的收集
		private static final List<ReportRow> REPORTROWLISTYEARCOLLECTION = new ArrayList<ReportRow>();
		// 按年统计的办结
		private static final List<ReportRow> REPORTROWLISTYEAREND = new ArrayList<ReportRow>();
		// 按月统计数收集
		private static final List<ReportRow> REPORTROWLISTCOLLECTION = new ArrayList<ReportRow>();
		// 按月统计数办结
		private static final List<ReportRow> REPORTROWLISTEND = new ArrayList<ReportRow>();
		static {
			List temp = null;
			// 累计收集合计(年)
			temp = new ArrayList<String>();
			temp.add(TOWNTOCITY_STREET_STR);
			temp.add(FUNCTIONAL_STR);
			temp.add(HIGHERLEVEL);
			REPORTROWLISTYEARCOLLECTION.add(new ReportRow("row0", temp));

			// 本年合计（乡镇、街道呈报数）
			temp = new ArrayList<String>();
			temp.add(TOWNTOCITY_STREET_STR);
			REPORTROWLISTYEARCOLLECTION.add(new ReportRow("row1", temp));
			// 市级机关、企事业单位呈报数年
			temp = new ArrayList<String>();
			temp.add(FUNCTIONAL_STR);
			REPORTROWLISTYEARCOLLECTION.add(new ReportRow("row2", temp));

			// 市级领导交办数(年)
			temp = new ArrayList<String>();
			temp.add(HIGHERLEVEL);
			REPORTROWLISTYEARCOLLECTION.add(new ReportRow("row3", temp));

			// 累计办结合计(年)
			temp = new ArrayList<String>();
			temp.add(TOWNTOCITY_STREET_STR);
			temp.add(FUNCTIONAL_STR);
			REPORTROWLISTYEAREND.add(new ReportRow("row5", temp));
			// 本年合计 转办乡镇、街道数 办结
			temp = new ArrayList<String>();
			temp.add(TOWNTOCITY_STREET_STR);
			REPORTROWLISTYEAREND.add(new ReportRow("row6", temp));
			// 转办市级机关、企事业单位数 年
			temp = new ArrayList<String>();
			temp.add(FUNCTIONAL_STR);
			REPORTROWLISTYEAREND.add(new ReportRow("row7", temp));

			// 上月办理中
			temp = new ArrayList<String>();
			temp.add(PRECEDINGMONTHUNFINISH);
			REPORTROWLISTCOLLECTION.add(new ReportRow("row9", temp));
			// 累计收集合计(月)
			temp = new ArrayList<String>();
			temp.add(TOWNTOCITY_STREET_STR);
			temp.add(FUNCTIONAL_STR);
			temp.add(HIGHERLEVEL);
			REPORTROWLISTCOLLECTION.add(new ReportRow("row10", temp));
			// 本年合计（乡镇、街道呈报数）（月）
			temp = new ArrayList<String>();
			temp.add(TOWNTOCITY_STREET_STR);
			REPORTROWLISTCOLLECTION.add(new ReportRow("row11", temp));
			// 市级机关、企事业单位呈报数（月）
			temp = new ArrayList<String>();
			temp.add(FUNCTIONAL_STR);
			REPORTROWLISTCOLLECTION.add(new ReportRow("row12", temp));

			// 市级领导交办数(月)
			temp = new ArrayList<String>();
			temp.add(HIGHERLEVEL);
			REPORTROWLISTCOLLECTION.add(new ReportRow("row13", temp));

			// 累计办结(月)
			temp = new ArrayList<String>();
			temp.add(TOWNTOCITY_STREET_STR);
			temp.add(FUNCTIONAL_STR);

			REPORTROWLISTEND.add(new ReportRow("row15", temp));
			// 转办乡镇、街道数（月）
			temp = new ArrayList<String>();
			temp.add(TOWNTOCITY_STREET_STR);
			REPORTROWLISTEND.add(new ReportRow("row16", temp));
			// 转办市级机关、企事业单位数（月）
			temp = new ArrayList<String>();
			temp.add(FUNCTIONAL_STR);
			REPORTROWLISTEND.add(new ReportRow("row17", temp));

			// 本月办理中
			temp = new ArrayList<String>();
			temp.add(THISMONTHUNFINISH);
			REPORTROWLISTCOLLECTION.add(new ReportRow("row19", temp));
		}

	}

	static class XiChangTwo {
		private static final List<ReportRow> REPORTROWLISTYEAR = new ArrayList<ReportRow>();
		static {
			List temp = null;

			temp = new ArrayList<String>();
			temp.add(LEVEL2SUB_ORG_CREATE_STR);
			temp.add(SUB_ORG_CREATE_STR);
			temp.add(OWNER_ORG_CREATE_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row0", temp));

			temp = new ArrayList<String>();
			temp.add(LEVEL2SUB_ORG_CREATE_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row1", temp));

			temp = new ArrayList<String>();
			temp.add(SUB_ORG_CREATE_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row2", temp));

			temp = new ArrayList<String>();
			temp.add(OWNER_ORG_CREATE_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row3", temp));

			temp = new ArrayList<String>();
			temp.add(LEVEL2SUB_ORG_FINISH_STR);
			temp.add(SUB_ORG_FINISH_STR);
			temp.add(OWNER_ORG_FINISH_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row4", temp));

			temp = new ArrayList<String>();
			temp.add(LEVEL2SUB_ORG_FINISH_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row5", temp));

			temp = new ArrayList<String>();
			temp.add(SUB_ORG_FINISH_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row6", temp));

			temp = new ArrayList<String>();
			temp.add(OWNER_ORG_FINISH_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row7", temp));

			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row8", temp));

			temp = new ArrayList<String>();
			temp.add(PROGRAM_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row9", temp));

			temp = new ArrayList<String>();
			temp.add(REPORT_TO_CITY_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row10", temp));

		}
	}

	static class XiChangThree {
		private static final List<ReportRow> REPORTROWLIST = new ArrayList<ReportRow>();
		static {
			List temp = null;

			temp = new ArrayList<String>();
			temp.add(LEVEL2SUB_ORG_CREATE_STR);
			temp.add(SUB_ORG_CREATE_STR);
			temp.add(OWNER_ORG_CREATE_STR);
			REPORTROWLIST.add(new ReportRow("row0", temp));

			temp = new ArrayList<String>();
			temp.add(LEVEL2SUB_ORG_CREATE_STR);
			REPORTROWLIST.add(new ReportRow("row1", temp));

			temp = new ArrayList<String>();
			temp.add(SUB_ORG_CREATE_STR);
			REPORTROWLIST.add(new ReportRow("row2", temp));

			temp = new ArrayList<String>();
			temp.add(OWNER_ORG_CREATE_STR);
			REPORTROWLIST.add(new ReportRow("row3", temp));

			temp = new ArrayList<String>();
			temp.add(LEVEL2SUB_ORG_FINISH_STR);
			temp.add(SUB_ORG_FINISH_STR);
			temp.add(OWNER_ORG_FINISH_STR);
			REPORTROWLIST.add(new ReportRow("row4", temp));

			temp = new ArrayList<String>();
			temp.add(LEVEL2SUB_ORG_FINISH_STR);
			REPORTROWLIST.add(new ReportRow("row5", temp));

			temp = new ArrayList<String>();
			temp.add(SUB_ORG_FINISH_STR);
			REPORTROWLIST.add(new ReportRow("row6", temp));

			temp = new ArrayList<String>();
			temp.add(OWNER_ORG_FINISH_STR);
			REPORTROWLIST.add(new ReportRow("row7", temp));

			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			REPORTROWLIST.add(new ReportRow("row8", temp));

			temp = new ArrayList<String>();
			temp.add(PROGRAM_END_STR);
			REPORTROWLIST.add(new ReportRow("row9", temp));

			temp = new ArrayList<String>();
			temp.add(REPORT_TO_CITY_END_STR);
			REPORTROWLIST.add(new ReportRow("row10", temp));

		}
	}

	static class TownOne {
		private static final List<ReportRow> REPORTROWLISTYEAR = new ArrayList<ReportRow>();
		private static final List<ReportRow> REPORTROWLIST = new ArrayList<ReportRow>();
		static {
			List temp = null;
			temp = new ArrayList<String>();
			temp.add(CREATE);
			temp.add(YESTERYEAR);
			temp.add(OTHERREPORT);
			temp.add(HIGHERLEVEL);
			temp.add(MUNICIPALPARTY);
			temp.add(PEOPLECONGRESS);
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLISTYEAR.add(new ReportRow("row0", temp));
			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			temp.add(PROGRAM_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row1", temp));
			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row2", temp));
			temp = new ArrayList<String>();
			temp.add(PROGRAM_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row3", temp));
			temp = new ArrayList<String>();
			temp.add(REPORT_TO_TOWN_FINISH_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row4", temp));
			temp = new ArrayList<String>();
			temp.add(PRECEDINGMONTHUNFINISH);
			REPORTROWLIST.add(new ReportRow("row5", temp));
			temp = new ArrayList<String>();
			temp.add(CREATE);
			temp.add(YESTERYEAR);
			temp.add(OTHERREPORT);
			temp.add(HIGHERLEVEL);
			temp.add(MUNICIPALPARTY);
			temp.add(PEOPLECONGRESS);
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLIST.add(new ReportRow("row6", temp));
			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			temp.add(PROGRAM_END_STR);
			REPORTROWLIST.add(new ReportRow("row7", temp));
			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			REPORTROWLIST.add(new ReportRow("row8", temp));
			temp = new ArrayList<String>();
			temp.add(PROGRAM_END_STR);
			REPORTROWLIST.add(new ReportRow("row9", temp));
			temp = new ArrayList<String>();
			temp.add(REPORT_TO_TOWN_FINISH_STR);
			REPORTROWLIST.add(new ReportRow("row10", temp));
			temp = new ArrayList<String>();
			temp.add(THISMONTHUNFINISH);
			REPORTROWLIST.add(new ReportRow("row11", temp));
		}
	}

	static class TownTwo {
		private static final List<ReportRow> REPORTROWLISTYEAR = new ArrayList<ReportRow>();
		private static final List<ReportRow> REPORTROWLIST = new ArrayList<ReportRow>();
		static {
			List temp = null;

			temp = new ArrayList<String>();
			temp.add(REPORT_TO_TOWN_END_STR);
			temp.add(CREATE);
			temp.add(MUNICIPALPARTY);
			temp.add(PEOPLECONGRESS);
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLISTYEAR.add(new ReportRow("row0", temp));

			temp = new ArrayList<String>();
			temp.add(REPORT_TO_TOWN_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row1", temp));

			temp = new ArrayList<String>();
			temp.add(CREATE);
			temp.add(MUNICIPALPARTY);
			temp.add(PEOPLECONGRESS);
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLISTYEAR.add(new ReportRow("row2", temp));

			temp = new ArrayList<String>();
			temp.add(CREATE);
			REPORTROWLISTYEAR.add(new ReportRow("row3", temp));

			temp = new ArrayList<String>();
			temp.add(MUNICIPALPARTY);
			REPORTROWLISTYEAR.add(new ReportRow("row4", temp));

			temp = new ArrayList<String>();
			temp.add(PEOPLECONGRESS);
			REPORTROWLISTYEAR.add(new ReportRow("row5", temp));

			temp = new ArrayList<String>();
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLISTYEAR.add(new ReportRow("row6", temp));

			temp = new ArrayList<String>();
			temp.add(REPORT_TO_TOWN_FINISH_STR);
			temp.add(ESSENCE_END_STR);
			temp.add(PROGRAM_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row7", temp));

			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			temp.add(PROGRAM_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row8", temp));

			temp = new ArrayList<String>();
			temp.add(REPORT_TO_TOWN_FINISH_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row9", temp));

			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row10", temp));

			temp = new ArrayList<String>();
			temp.add(PROGRAM_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row11", temp));

			temp = new ArrayList<String>();
			temp.add(TOWNTOCITY_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row12", temp));

			temp = new ArrayList<String>();
			temp.add(VILLAGETOCITY_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row13", temp));

			temp = new ArrayList<String>();
			temp.add(PRECEDINGMONTHUNFINISH);
			REPORTROWLIST.add(new ReportRow("row14", temp));

			temp = new ArrayList<String>();
			temp.add(REPORT_TO_TOWN_END_STR);
			temp.add(CREATE);
			temp.add(MUNICIPALPARTY);
			temp.add(PEOPLECONGRESS);
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLIST.add(new ReportRow("row15", temp));

			temp = new ArrayList<String>();
			temp.add(REPORT_TO_TOWN_END_STR);
			REPORTROWLIST.add(new ReportRow("row16", temp));

			temp = new ArrayList<String>();
			temp.add(CREATE);
			temp.add(MUNICIPALPARTY);
			temp.add(PEOPLECONGRESS);
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLIST.add(new ReportRow("row17", temp));

			temp = new ArrayList<String>();
			temp.add(CREATE);
			REPORTROWLIST.add(new ReportRow("row18", temp));

			temp = new ArrayList<String>();
			temp.add(MUNICIPALPARTY);
			REPORTROWLIST.add(new ReportRow("row19", temp));

			temp = new ArrayList<String>();
			temp.add(PEOPLECONGRESS);
			REPORTROWLIST.add(new ReportRow("row20", temp));

			temp = new ArrayList<String>();
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLIST.add(new ReportRow("row21", temp));

			temp = new ArrayList<String>();
			temp.add(REPORT_TO_TOWN_FINISH_STR);
			temp.add(ESSENCE_END_STR);
			temp.add(PROGRAM_END_STR);
			REPORTROWLIST.add(new ReportRow("row22", temp));

			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			temp.add(PROGRAM_END_STR);
			REPORTROWLIST.add(new ReportRow("row23", temp));

			temp = new ArrayList<String>();
			temp.add(REPORT_TO_TOWN_FINISH_STR);
			REPORTROWLIST.add(new ReportRow("row24", temp));

			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			REPORTROWLIST.add(new ReportRow("row25", temp));

			temp = new ArrayList<String>();
			temp.add(PROGRAM_END_STR);
			REPORTROWLIST.add(new ReportRow("row26", temp));

			temp = new ArrayList<String>();
			temp.add(TOWNTOCITY_STR);
			REPORTROWLIST.add(new ReportRow("row27", temp));

			temp = new ArrayList<String>();
			temp.add(VILLAGETOCITY_STR);
			REPORTROWLIST.add(new ReportRow("row28", temp));

			temp = new ArrayList<String>();
			temp.add(THISMONTHUNFINISH);
			REPORTROWLIST.add(new ReportRow("row29", temp));

		}
	}

	static class Village {
		private static final List<ReportRow> REPORTROWLISTYEAR = new ArrayList<ReportRow>();
		private static final List<ReportRow> REPORTROWLIST = new ArrayList<ReportRow>();
		static {
			List temp = null;
			temp = new ArrayList<String>();
			temp.add(CREATE);
			temp.add(YESTERYEAR);
			temp.add(OTHERREPORT);
			temp.add(HIGHERLEVEL);
			temp.add(MUNICIPALPARTY);
			temp.add(PEOPLECONGRESS);
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLISTYEAR.add(new ReportRow("row0", temp));
			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			temp.add(PROGRAM_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row1", temp));
			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row2", temp));
			temp = new ArrayList<String>();
			temp.add(PROGRAM_END_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row3", temp));
			temp = new ArrayList<String>();
			temp.add(REPORT_TO_TOWN_FINISH_STR);
			REPORTROWLISTYEAR.add(new ReportRow("row4", temp));
			temp = new ArrayList<String>();
			temp.add(PRECEDINGMONTHUNFINISH);
			REPORTROWLIST.add(new ReportRow("row5", temp));
			temp = new ArrayList<String>();
			temp.add(CREATE);
			temp.add(YESTERYEAR);
			temp.add(OTHERREPORT);
			temp.add(HIGHERLEVEL);
			temp.add(MUNICIPALPARTY);
			temp.add(PEOPLECONGRESS);
			temp.add(POLITICALCONSULTATIVE);
			REPORTROWLIST.add(new ReportRow("row6", temp));
			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			temp.add(PROGRAM_END_STR);
			REPORTROWLIST.add(new ReportRow("row7", temp));
			temp = new ArrayList<String>();
			temp.add(ESSENCE_END_STR);
			REPORTROWLIST.add(new ReportRow("row8", temp));
			temp = new ArrayList<String>();
			temp.add(PROGRAM_END_STR);
			REPORTROWLIST.add(new ReportRow("row9", temp));
			temp = new ArrayList<String>();
			temp.add(REPORT_TO_TOWN_FINISH_STR);
			REPORTROWLIST.add(new ReportRow("row10", temp));
			temp = new ArrayList<String>();
			temp.add(THISMONTHUNFINISH);
			REPORTROWLIST.add(new ReportRow("row11", temp));
		}
	}

	private String rowI;
	private List<String> rowContents;

	public String getRowI() {
		return rowI;
	}

	public void setRowI(String rowI) {
		this.rowI = rowI;
	}

	public List<String> getRowContents() {
		return rowContents;
	}

	public void setRowContents(List<String> rowContents) {
		this.rowContents = rowContents;
	}

	public ReportRow() {
	}

	public ReportRow(String rowI, List<String> rowContents) {
		this.rowI = rowI;
		this.rowContents = rowContents;
	}
}
