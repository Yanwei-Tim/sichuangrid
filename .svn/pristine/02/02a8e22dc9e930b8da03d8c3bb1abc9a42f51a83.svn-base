package com.tianque.plugin.analysisNew.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.domain.IssueDealStat;
import com.tianque.domain.Organization;
import com.tianque.plugin.analysisNew.service.IssueStatNewService;
import com.tianque.report.builder.IssueDealStatBuilder;
import com.tianque.service.CommonService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Scope("prototype")
@Controller("issueDealStatNewController")
@SuppressWarnings({ "unchecked", "serial" })
public class IssueDealStatNewController extends BaseAction {
	private Long orgType;
	private int queryYear;
	private int queryMonth;
	private Long queryOrgId;
	private int realtime = 0;

	private int minYear;
	private int maxYear;
	private int defaultYear;
	private int defaultMonth;

	@Autowired
	private CommonService commonService;

	@Autowired
	private IssueStatNewService issueStatNewService;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	public IssueDealStatNewController() {
		this.rows = 500;
		maxYear = CalendarUtil.getNowYear();
		defaultYear = CalendarUtil.getLastMonthYearValue();
		// defaultMonth = CalendarUtil.getLastMonth()+1;
		defaultMonth = CalendarUtil.getLastMonth() + 1;
		if (defaultMonth == 1) {
			defaultYear = defaultYear - 1;
			defaultMonth = 12;
		} else {
			defaultMonth = defaultMonth - 1;
		}
	}

	public int getRealtime() {
		return realtime;
	}

	public void setRealtime(int realtime) {
		this.realtime = realtime;
	}

	public Long getQueryOrgId() {
		return queryOrgId;
	}

	public void setQueryOrgId(Long queryOrgId) {
		this.queryOrgId = queryOrgId;
	}

	public int getDefaultYear() {
		return defaultYear;
	}

	public void setDefaultYear(int defaultYear) {
		this.defaultYear = defaultYear;
	}

	public int getDefaultMonth() {
		return defaultMonth;
	}

	public void setDefaultMonth(int defaultMonth) {
		this.defaultMonth = defaultMonth;
	}

	public Long getOrgType() {
		return orgType;
	}

	public void setOrgType(Long orgType) {
		this.orgType = orgType;
	}

	public int getQueryYear() {
		return queryYear;
	}

	public void setQueryYear(int queryYear) {
		this.queryYear = queryYear;
	}

	public int getQueryMonth() {
		return queryMonth;
	}

	public void setQueryMonth(int queryMonth) {
		this.queryMonth = queryMonth;
	}

	public int getMinYear() {
		if (minYear == 0) {
			if (ThreadVariable.getUser().isAdmin()) {
				minYear = 2010;
			} else {
				minYear = commonService.getSysBeginUseYear();
			}
		}
		return minYear;
	}

	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}

	public int getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}

	@PermissionFilter(ename = "issueDealStatReport")
	public String monthStat() throws Exception {
		// 暂时隐掉
		// Date date = new Date();
		// if (queryYear == Integer.valueOf(new
		// SimpleDateFormat("yyyy").format(date))
		// || queryMonth == Integer.valueOf(new
		// SimpleDateFormat("MM").format(date))) {
		// gridPage = new GridPage(queryIssueDealRealtimeStats());
		// return SUCCESS;
		// }
		gridPage = new GridPage(queryIssueDealStats());
		return SUCCESS;
	}

	@PermissionFilter(ename = "issueDealStatReport")
	public String realtimeStat() throws Exception {
		gridPage = new GridPage(queryIssueDealRealtimeStats());
		return SUCCESS;
	}

	@PermissionFilter(ename = "generatedIssueDealStat")
	public String rebuildStatInfo() throws Exception {
		issueStatNewService.buildIssueDealStatsByMonth(queryOrgId, queryYear,
				queryMonth);
		return SUCCESS;
	}

	@PermissionFilter(ename = "exportIssueDealStat")
	public String exportToExcel() throws Exception {
		if (queryOrgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		Date date = new Date();
		if (queryYear == Integer.valueOf(new SimpleDateFormat("yyyy")
				.format(date))
				&& queryMonth == Integer.valueOf(new SimpleDateFormat("MM")
						.format(date))) {
			List<IssueDealStat> records = queryIssueDealRealtimeStats();
			Date now = new Date();
			String dateDesc = "截止" + CalendarUtil.getYear(now) + "年"
					+ CalendarUtil.getMonth(now) + "月"
					+ CalendarUtil.getDay(now) + "日";
			inputStream = exportIssueDealStatsToExcel(records, dateDesc);
			downloadFileName = new String((getReportFileCHNName(orgType) + "("
					+ dateDesc + ")").getBytes("gbk"), "ISO8859-1")
					+ ".xls";
		} else {
			List<IssueDealStat> records = queryIssueDealStats();
			String dateDesc = queryYear + "年" + queryMonth + "月";
			inputStream = exportIssueDealStatsToExcel(records, dateDesc);
			downloadFileName = new String((getReportFileCHNName(orgType) + "("
					+ dateDesc + ")").getBytes("gbk"), "ISO8859-1")
					+ ".xls";
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "exportIssueDealStat")
	public String exportRealTimeStatToExcel() throws Exception {
		if (queryOrgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			List<IssueDealStat> records = queryIssueDealRealtimeStats();
			Date now = new Date();
			String dateDesc = "截止" + CalendarUtil.getYear(now) + "年"
					+ CalendarUtil.getMonth(now) + "月"
					+ CalendarUtil.getDay(now) + "日";
			inputStream = exportIssueDealStatsToExcel(records, dateDesc);
			downloadFileName = new String((getReportFileCHNName(orgType) + "("
					+ dateDesc + ")").getBytes("gbk"), "ISO8859-1")
					+ ".xls";
		}
		return SUCCESS;
	}

	private String getReportFileCHNName(Long orgType) {
		if (orgType == 1) {
			return "事件处理办理统计表";
		} else {
			return "部门事件处理办理统计表";
		}
	}

	private InputStream exportIssueDealStatsToExcel(
			List<IssueDealStat> records, String dateDesc) throws Exception {
		ExcelWriter writer = constructExcelWriter();
		writer.addWorkSheet("事件处理办理统计表");
		writer.addCell(0, 0, "事件处理办理统计表(" + dateDesc + ")").mergeTo(0, 14)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(1, 1, "名称").setFont("宋体", 12, true, false, false, false);
		writer.addCell(1, 1, "本月立案").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(1, 2, "本年累计立案").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(1, 3, "累计立案").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(1, 4, "本月正常在办").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(1, 5, "本月超期在办").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(1, 6, "本月办结").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(1, 7, "本月超期办结").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(1, 8, "本年累计办结").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(1, 9, "累计办结").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(1, 10, "本月已办结立案").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(1, 11, "本年累计已办结立案").setFont("宋体", 12, true, false,
				false, false);
		writer.addCell(1, 12, "累计已办结立案").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(1, 13, "年度办结率").setFont("宋体", 12, true, false, false,
				false);
		writer.addCell(1, 14, "累计办结率").setFont("宋体", 12, true, false, false,
				false);
		for (int index = 0; index < records.size(); index++) {
			IssueDealStat record = records.get(index);
			writer.addCell(index + 2, 0, record.getOrg().getOrgName());
			writer.addCell(index + 2, 1, record.getMonthAddCount());
			writer.addCell(index + 2, 2, record.getYearAddCount());
			writer.addCell(index + 2, 3, record.getTotalAddCount());
			writer.addCell(index + 2, 4, record.getMonthNomalDealingCount());
			writer.addCell(index + 2, 5, record.getMonthOvertimeDealingCount());
			writer.addCell(index + 2, 6, record.getMonthNomalCompletedCount());
			writer.addCell(index + 2, 7,
					record.getMonthOvertimeCompletedCount());
			writer.addCell(index + 2, 8, record.getYearCompletedCount());
			writer.addCell(index + 2, 9, record.getTotalCompletedCount());
			writer.addCell(index + 2, 10, record.getMonthComplededAddCount());
			writer.addCell(index + 2, 11, record.getYearComplededAddCount());
			writer.addCell(index + 2, 12, record.getTotalComplededAddCount());
			writer.addCell(index + 2, 13, record.getTotalOrgYearCompleteRata());
			writer.addCell(index + 2, 14, record.getTotalOrgCompleteRata());
		}
		return new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
	}

	private ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}

	public String dispatch() {
		if (orgType != 2) {
			if (realtime == 1) {
				return "realtimeAdminOrgStat";
			} else {
				return "adminOrgStat";
			}
		} else {
			if (realtime == 1) {
				return "realtimeFunOrgStat";
			} else {
				return "funOrgStat";
			}
		}
	}

	private List<IssueDealStat> queryIssueDealRealtimeStats() {
		List<IssueDealStat> result = new ArrayList<IssueDealStat>();
		if (orgType == 1) {
			result = issueStatNewService
					.findRealTimeAdminstrateOrgIssueDealStats(queryOrgId);
		} else {
			result = issueStatNewService
					.findRealTimeFunctionOrgIssueDealStats(queryOrgId);
		}
		result = ControllerHelper.processAllOrgName(result,
				organizationDubboService, new String[] { "org" });
		IssueDealStat totalStat = IssueDealStatBuilder.createEmptyDealStatInfo(
				createMockTotalOrg(), 1900, 12);
		populateTotalRecord(result, totalStat);
		totalStat
				.setStatStartTime((result != null && result.size() > 0) ? result
						.get(0).getStatStartTime() : CalendarUtil.today());
		totalStat.setStatEndTime((result != null && result.size() > 0) ? result
				.get(0).getStatEndTime() : CalendarUtil.today());
		result.add(totalStat);
		return result;
	}

	private List<IssueDealStat> queryIssueDealStats() {
		List<IssueDealStat> result = new ArrayList<IssueDealStat>();
		if (orgType == 1) {
			result = issueStatNewService
					.findAdminstrateOrgIssueDealStatsByMonth(queryOrgId,
							queryYear, queryMonth);
		} else {
			result = issueStatNewService
					.findFunctionOrgIssueDealStatsByParentIdMonth(queryOrgId,
							queryYear, queryMonth);
		}
		result = ControllerHelper.processAllOrgName(result,
				organizationDubboService, new String[] { "org" });
		IssueDealStat totalStat = IssueDealStatBuilder.createEmptyDealStatInfo(
				createMockTotalOrg(), queryYear, queryMonth);
		populateTotalRecord(result, totalStat);
		result.add(totalStat);
		return result;
	}

	private void populateTotalRecord(List<IssueDealStat> stats,
			IssueDealStat totalStat) {
		for (IssueDealStat record : stats) {
			totalStat.setMonthAddCount(totalStat.getMonthAddCount()
					+ record.getMonthAddCount());
			totalStat.setYearAddCount(totalStat.getYearAddCount()
					+ record.getYearAddCount());
			totalStat.setTotalAddCount(totalStat.getTotalAddCount()
					+ record.getTotalAddCount());
			totalStat.setMonthNomalDealingCount(totalStat
					.getMonthNomalDealingCount()
					+ record.getMonthNomalDealingCount());
			totalStat.setMonthOvertimeDealingCount(totalStat
					.getMonthOvertimeDealingCount()
					+ record.getMonthOvertimeDealingCount());
			totalStat.setMonthNomalCompletedCount(totalStat
					.getMonthNomalCompletedCount()
					+ record.getMonthNomalCompletedCount());
			totalStat.setMonthOvertimeCompletedCount(totalStat
					.getMonthOvertimeCompletedCount()
					+ record.getMonthOvertimeCompletedCount());
			totalStat.setYearCompletedCount(totalStat.getYearCompletedCount()
					+ record.getYearCompletedCount());
			totalStat.setTotalCompletedCount(totalStat.getTotalCompletedCount()
					+ record.getTotalCompletedCount());
			totalStat.setMonthComplededAddCount(totalStat
					.getMonthComplededAddCount()
					+ record.getMonthComplededAddCount());
			totalStat.setYearComplededAddCount(totalStat
					.getYearComplededAddCount()
					+ record.getYearComplededAddCount());
			totalStat.setTotalComplededAddCount(totalStat
					.getTotalComplededAddCount()
					+ record.getTotalComplededAddCount());
		}
	}

	private Organization createMockTotalOrg() {
		Organization result = new Organization();
		result.setOrgName("合  计");
		result.setOrgInternalCode("999999999");
		return result;
	}

}
