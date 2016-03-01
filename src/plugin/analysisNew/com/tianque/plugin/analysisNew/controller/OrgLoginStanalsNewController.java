package com.tianque.plugin.analysisNew.controller;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.controller.vo.FullReportVo;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.domain.Organization;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.analysisNew.domain.OrgLoginStanals;
import com.tianque.plugin.analysisNew.service.OrgLoginStanalsNewService;
import com.tianque.service.CommonService;
import com.tianque.service.WorkCalendarService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.ticket.StatDataTicket;
import com.tianque.ticket.Ticket;
import com.tianque.ticket.TicketIdGenerator;
import com.tianque.ticket.service.TicketService;

@Transactional
@SuppressWarnings("serial")
@Controller("orgLoginStanalsNewController")
@Scope("prototype")
public class OrgLoginStanalsNewController extends BaseAction {
	@Autowired
	private OrgLoginStanalsNewService orgLoginStanalsNewService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private WorkCalendarService workCalendarService;

	private Long orgId;
	private OrgLoginStanals orgLoginStanals;
	private Integer minYear;
	private Integer minMonth;
	private Integer maxYear;
	private Integer maxMonth;

	private Integer nowYear;
	private Integer nowMonth;
	private Integer internalId;
	private String ticketId;
	private FullReportVo fullReportVo;

	public String findOrgLoginStanalsByOrgIdForListPage() throws Exception {

		if (orgId == null || nowYear == null || nowMonth == null) {

			return ERROR;
		}
		fullReportVo = new FullReportVo();
		fullReportVo.setBigTitle(organizationDubboService.getSimpleOrgById(
				orgId).getOrgName()
				+ " " + nowYear + "年" + nowMonth + "月" + "登录统计");

		List<OrgLoginStanals> orgLoginStanalsList = new ArrayList<OrgLoginStanals>();
		orgLoginStanalsList = orgLoginStanalsNewService
				.getOrgLoginStanalsForList(nowYear, nowMonth, orgId, internalId);

		fullReportVo.setColumnCaption(getColumnCaption(nowYear, nowMonth));
		fullReportVo.setObjectDataList(orgLoginStanalsList);
		return SUCCESS;
	}

	/**
	 * 根据日期遍历工作日历 获得工作周
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private String[] getColumnCaption(int year, int month) throws Exception {
		String[] columnTitle = new String[6];
		List<Date> datelist = workCalendarService.getWeekDayByCalendar(year,
				month);
		if (datelist == null || datelist.size() == 0)
			return null;
		Date startdate = datelist.get(0);
		Date enddate = datelist.get(datelist.size() - 1);
		columnTitle[0] = "本月工作日(" + CalendarUtil.format("MM.dd", startdate)
				+ "-" + CalendarUtil.format("MM.dd", enddate) + ")";
		long firstweek = startdate.getTime() + 24 * 60 * 60 * 1000 * 6 * 1;
		columnTitle[1] = "第一周(" + CalendarUtil.format("MM.dd", startdate) + "-"
				+ CalendarUtil.format("MM.dd", new Date(firstweek)) + ")";
		firstweek = firstweek + 24 * 60 * 60 * 1000 * 7 * 1;
		columnTitle[2] = "第二周("
				+ CalendarUtil.format("MM.dd", new Date(firstweek - 6 * 24 * 60
						* 60 * 1000)) + "-"
				+ CalendarUtil.format("MM.dd", new Date(firstweek)) + ")";
		firstweek = firstweek + 24 * 60 * 60 * 1000 * 7 * 1;
		columnTitle[3] = "第三周("
				+ CalendarUtil.format("MM.dd", new Date(firstweek - 6 * 24 * 60
						* 60 * 1000)) + "-"
				+ CalendarUtil.format("MM.dd", new Date(firstweek)) + ")";
		firstweek = firstweek + 24 * 60 * 60 * 1000 * 7 * 1;
		columnTitle[4] = "第四周("
				+ CalendarUtil.format("MM.dd", new Date(firstweek - 6 * 24 * 60
						* 60 * 1000)) + "-"
				+ CalendarUtil.format("MM.dd", new Date(firstweek)) + ")";
		if (datelist.size() > 28)
			columnTitle[5] = "第五周("
					+ CalendarUtil.format("MM.dd", new Date(firstweek + 1 * 24
							* 60 * 60 * 1000)) + "-"
					+ CalendarUtil.format("MM.dd", enddate) + ")";
		return columnTitle;
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "exportAdminLoginStanals", actionName = "downloadOrgLoginStanals"),
			@PermissionFilter(ename = "exportFuncationLoginStanals", actionName = "downloadOrgLoginStanals") })
	public String downloadOrgLoginStanals() throws Exception {
		if (orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			List<OrgLoginStanals> records = new ArrayList<OrgLoginStanals>();
			records = orgLoginStanalsNewService.getOrgLoginStanalsForList(
					nowYear, nowMonth, orgId, internalId);
			inputStream = exportOrgLoginStanals(records);
			downloadFileName = new String("部门登录情况统计".getBytes("gbk"),
					"ISO8859-1") + ".xls";
		}
		return SUCCESS;
	}

	private InputStream exportOrgLoginStanals(List<OrgLoginStanals> records)
			throws Exception {
		ExcelWriter writer = constructExcelWriter();
		writer.addWorkSheet("部门登录情况统计");
		String[] coulumnTitle = getColumnCaption(nowYear, nowMonth);
		appendTableHead(writer, organizationDubboService
				.getSimpleOrgById(orgId).getOrgName()
				+ " "
				+ nowYear
				+ "年"
				+ nowMonth + "月" + "登录统计", coulumnTitle);
		for (int index = 0; index < records.size(); index++) {
			OrgLoginStanals orgLoginStanals = records.get(index);
			appendRow(writer, index + 3, orgLoginStanals, coulumnTitle);
		}
		return new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
	}

	private void appendRow(ExcelWriter writer, int index,
			OrgLoginStanals orgLoginStanals, String[] coulumnTitle) {
		writer.addCell(index, 0, orgLoginStanals.getOrgName());
		writer.addCell(index, 1, orgLoginStanals.getName());
		writer.addCell(index, 2, orgLoginStanals.getWorkday_month());
		writer.addCell(index, 3, orgLoginStanals.getLoggedday_month());
		writer.addCell(index, 4, orgLoginStanals.getWorkday_week1());
		writer.addCell(index, 5, orgLoginStanals.getLoggedday_week1());
		writer.addCell(index, 6, orgLoginStanals.getWorkday_week2());
		writer.addCell(index, 7, orgLoginStanals.getLoggedday_week2());
		writer.addCell(index, 8, orgLoginStanals.getWorkday_week3());
		writer.addCell(index, 9, orgLoginStanals.getLoggedday_week3());
		writer.addCell(index, 10, orgLoginStanals.getWorkday_week4());
		writer.addCell(index, 11, orgLoginStanals.getLoggedday_week4());

		if (coulumnTitle[5] != null) {
			writer.addCell(index, 12, orgLoginStanals.getWorkday_week5());
			writer.addCell(index, 13, orgLoginStanals.getLoggedday_week5());
		}
		// if(coulumnTitle.length==5 && coulumnTitle[4]!=null){
		// writer.addCell(index, 11, orgLoginStanals.getWorkday_week5());
		// writer.addCell(index, 12, orgLoginStanals.getLoggedday_week5());
		// }
	}

	private ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}

	private void appendTableHead(ExcelWriter writer, String bigTitle,
			String[] columnCaption) {
		int maxlength = 0;
		for (String ss : columnCaption)
			if (ss != null)
				maxlength = maxlength + 2;
		maxlength = maxlength + 1;
		writer.addCell(0, 0, bigTitle).mergeTo(0, maxlength)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);

		writer.addCell(1, 0, "组织结构名称")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 0)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		writer.addCell(1, 1, "用户名")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 1)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		for (int n = 0; n < columnCaption.length; n++) {

			String caption = columnCaption[n];
			if (caption == null)
				break;
			writer.addCell(1, 2 + 2 * n, caption)
					.setFont("宋体", 12, true, false, false, false)
					.mergeTo(1, 3 + 2 * n)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
			writer.addCell(2, 2 + 2 * n, "工作天数");
			writer.addCell(2, 3 + 2 * n, "登录天数");
		}
	}

	public String searchOrgLoginStanals() throws Exception {
		return findOrgLoginStanalsByOrgIdForListPage();
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "reProduceDataAdminLoginStanals", actionName = "reProduceDate"),
			@PermissionFilter(ename = "reProduceDataFuncationLoginStanals", actionName = "reProduceDate") })
	public void reProduceDate() throws Exception {
		try {
			if (orgId != null) {
				orgLoginStanals = new OrgLoginStanals();
				Organization org = organizationDubboService
						.getSimpleOrgById(orgId);

				orgLoginStanals.setOrganization(org);
				orgLoginStanals.setYear(nowYear);
				orgLoginStanals.setMonth(nowMonth);
				Ticket ticket = generateDataImportTicket();
				ticketId = ticket.getTicketId();
				StatAnalyseOrgLoginNewThread statAnalyseThread = new StatAnalyseOrgLoginNewThread(
						ThreadVariable.getSession(), orgLoginStanalsNewService,
						orgLoginStanals, ticketId, ticketService, internalId);
				statAnalyseThread.start();
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("text/html;charset=GBK");
				PrintWriter printWriter = response.getWriter();
				printWriter.print("{ticketId:'" + ticket.getTicketId() + "'}");

			}
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			throw new ServiceValidationException(e.toString(), e);
		}

	}

	private Ticket generateDataImportTicket() {
		StatDataTicket dataImportTicket = new StatDataTicket();
		dataImportTicket.setTicketId(TicketIdGenerator.generateTicket());
		dataImportTicket.setDescription("");
		return ticketService.addTicket(dataImportTicket, 0);
	}

	public String dispatch() throws Exception {
		if (mode.equals("produceDate")) {
			minYear = commonService.getSysBeginUseYear();
			minMonth = commonService.getSysBeginUseMonth();

			Calendar endCalendar = Calendar.getInstance();
			maxYear = endCalendar.get(Calendar.YEAR);
			maxMonth = endCalendar.get(Calendar.MONTH) + 1;

			String nowString = CalendarUtil.format(CalendarUtil
					.now("yyyy-MM-dd"));
			nowYear = new Integer(nowString.split("-")[0]);
			nowMonth = new Integer(nowString.split("-")[1]);

			return "produceDate";
		} else if (mode.equals("initDate")) {
			minYear = commonService.getSysBeginUseYear();
			minMonth = commonService.getSysBeginUseMonth();

			Calendar endCalendar = Calendar.getInstance();
			maxYear = endCalendar.get(Calendar.YEAR);
			maxMonth = endCalendar.get(Calendar.MONTH) + 1;

			String nowString = CalendarUtil.format(CalendarUtil
					.now("yyyy-MM-dd"));
			nowYear = new Integer(nowString.split("-")[0]);
			nowMonth = new Integer(nowString.split("-")[1]);

			return SUCCESS;
		} else if (mode.equals("loginManage")) {
			minYear = commonService.getSysBeginUseYear();
			minMonth = commonService.getSysBeginUseMonth();

			Calendar endCalendar = Calendar.getInstance();
			maxYear = endCalendar.get(Calendar.YEAR);
			maxMonth = endCalendar.get(Calendar.MONTH) + 1;

			String nowString = CalendarUtil.format(CalendarUtil
					.now("yyyy-MM-dd"));
			nowYear = new Integer(nowString.split("-")[0]);
			nowMonth = new Integer(nowString.split("-")[1]);

			return "loginManage";
		} else {
			minYear = commonService.getSysBeginUseYear();
			minMonth = commonService.getSysBeginUseMonth();

			Calendar endCalendar = Calendar.getInstance();
			maxYear = endCalendar.get(Calendar.YEAR);
			maxMonth = endCalendar.get(Calendar.MONTH) + 1;

			String nowString = CalendarUtil.format(CalendarUtil
					.now("yyyy-MM-dd"));
			nowYear = new Integer(nowString.split("-")[0]);
			nowMonth = new Integer(nowString.split("-")[1]);

			return SUCCESS;
		}
	}

	public Integer getInternalId() {
		return internalId;
	}

	public void setInternalId(Integer internalId) {
		this.internalId = internalId;
	}

	public Integer getNowYear() {
		return nowYear;
	}

	public void setNowYear(Integer nowYear) {
		this.nowYear = nowYear;
	}

	public Integer getNowMonth() {
		return nowMonth;
	}

	public void setNowMonth(Integer nowMonth) {
		this.nowMonth = nowMonth;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public OrgLoginStanals getOrgLoginStanals() {
		return orgLoginStanals;
	}

	public void setOrgLoginStanals(OrgLoginStanals orgLoginStanals) {
		this.orgLoginStanals = orgLoginStanals;
	}

	public Integer getMinYear() {
		return minYear;
	}

	public void setMinYear(Integer minYear) {
		this.minYear = minYear;
	}

	public Integer getMinMonth() {
		return minMonth;
	}

	public void setMinMonth(Integer minMonth) {
		this.minMonth = minMonth;
	}

	public Integer getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(Integer maxYear) {
		this.maxYear = maxYear;
	}

	public Integer getMaxMonth() {
		return maxMonth;
	}

	public void setMaxMonth(Integer maxMonth) {
		this.maxMonth = maxMonth;
	}

	public FullReportVo getFullReportVo() {
		return fullReportVo;
	}

	public void setFullReportVo(FullReportVo fullReportVo) {
		this.fullReportVo = fullReportVo;
	}

}
