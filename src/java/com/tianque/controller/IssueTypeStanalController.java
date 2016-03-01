package com.tianque.controller;

import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.statanalysethread.StatAnalyseIssueTypeThread;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.DataExportHelper;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.domain.AmchartBean;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.IssueTypeStanal;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.AmchartKetSet;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.HighchartDataColumnVo;
import com.tianque.service.CommonService;
import com.tianque.service.IssueTypeStanalService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.ticket.StatDataTicket;
import com.tianque.ticket.Ticket;
import com.tianque.ticket.TicketIdGenerator;
import com.tianque.ticket.service.TicketService;

@Transactional
@Scope("prototype")
@Controller("issueTypeStanalController")
public class IssueTypeStanalController extends BaseAction {
	@Autowired
	private IssueTypeStanalService issueTypeStanalService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private PropertyDictService propertyDictService;

	private String ticketId;
	private static final String MONTH_STARTTIME = "startTime";
	private static final String MONTH_ENDTIME = "endTime";
	private static final String MONTH_SEARCH_STARTTIME = "ms_startTime";
	private static final String MONTH_SEARCH_ENDTIME = "ms_endTime";

	private String title_key1;
	private String data_key1;
	private Long scopeId;

	private HighchartColumnVo highchartColumnVo;

	@PermissionFilter(ename = "issueTypeStat")
	public String findIssueTypeStanalByOrgIdForListPage() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		Map<String, Date> map = initSearchMonthStartEndTime();
		List<IssueTypeStanal> issueTypeStanalList = issueTypeStanalService
				.getPeriodIssueTypeStatInfoListByOrgGroupByTypeDomain(orgId,
						map.get(MONTH_SEARCH_STARTTIME),
						map.get(MONTH_SEARCH_ENDTIME), scopeId);
		Collections.sort(issueTypeStanalList,
				new Comparator<IssueTypeStanal>() {
					@Override
					public int compare(IssueTypeStanal o1, IssueTypeStanal o2) {
						if (o1.getIssueTypeDomain().getId() > o2
								.getIssueTypeDomain().getId())
							return 1;
						else
							return 0;
					}
				});
		PageInfo<IssueTypeStanal> pageInfo = new PageInfo<IssueTypeStanal>();
		pageInfo.setResult(issueTypeStanalList);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@PermissionFilter(ename = "exportIssueTypeStat")
	public String downloadIssueTypeStanal() throws Exception {
		if (orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			List<IssueTypeStanal> records = new ArrayList<IssueTypeStanal>();
			Map<String, Date> map = initSearchMonthStartEndTime();
			records = issueTypeStanalService
					.getPeriodIssueTypeStatInfoListByOrgGroupByTypeDomain(
							orgId, map.get(MONTH_SEARCH_STARTTIME),
							map.get(MONTH_SEARCH_ENDTIME), scopeId);
			inputStream = exportIssueTypeStanal(records);
			downloadFileName = new String("事件处理类别统计".getBytes("gbk"),
					"ISO8859-1") + ".xls";
		}
		return SUCCESS;
	}

	private InputStream exportIssueTypeStanal(List<IssueTypeStanal> records)
			throws Exception {
		ExcelWriter writer = constructExcelWriter();
		DataExportHelper helper = constructDataExportHelper();
		writer.addWorkSheet("事件处理类别统计");
		appendTitle(writer, "事件处理类别统计表");
		appendTableHead(writer);
		for (int index = 0; index < records.size(); index++) {
			IssueTypeStanal issueTypeStanal = records.get(index);
			appendRow(writer, helper, index + 3, issueTypeStanal);
		}
		return new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
	}

	private void appendRow(ExcelWriter writer, DataExportHelper helper,
			int index, IssueTypeStanal issueTypeStanal) {
		writer.addCell(index, 0, issueTypeStanal.getDomainName());
		writer.addCell(index, 1, issueTypeStanal.getCount());
	}

	private ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}

	private DataExportHelper constructDataExportHelper() {
		DataExportHelper helper = new DataExportHelper();
		helper.setOrganizationService(organizationDubboService);
		helper.setPropertyDictService(propertyDictService);
		return helper;
	}

	private void appendTitle(ExcelWriter write, String title) {
		write.addCell(0, 0, title).mergeTo(0, 1)
				.setFont("宋体", 20, true, false, false, false)
				.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
	}

	private void appendTableHead(ExcelWriter writer) {
		writer.addCell(1, 0, "统计类型")
				.setFont("宋体", 12, true, false, false, false).mergeTo(2, 0);
		writer.addCell(1, 1, "总数").setFont("宋体", 12, true, false, false, false)
				.mergeTo(2, 1);
	}

	private Date getDefaultStartTime() {
		minYear = commonService.getSysBeginUseYear();
		minMonth = commonService.getSysBeginUseMonth();

		Calendar endCalendar = Calendar.getInstance();
		maxYear = endCalendar.get(Calendar.YEAR);
		maxMonth = endCalendar.get(Calendar.MONTH);

		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(minYear, minMonth - 1, 1);
		return startCalendar.getTime();
	}

	private Date getDefaultEndTime() {
		minYear = commonService.getSysBeginUseYear();
		minMonth = commonService.getSysBeginUseMonth();

		Calendar endCalendar = Calendar.getInstance();
		maxYear = endCalendar.get(Calendar.YEAR);
		maxMonth = endCalendar.get(Calendar.MONTH);

		Calendar endCalendars = Calendar.getInstance();
		endCalendars.set(maxYear, maxMonth - 1, 1);
		return endCalendars.getTime();
	}

	@PermissionFilter(ename = "searchIssueTypeStat")
	public String searchIssueTypeStanal() throws Exception {
		return findIssueTypeStanalByOrgIdForListPage();
	}

	// 手动生成
	@PermissionFilter(ename = "generatedDataType")
	public void reProduceDate() throws Exception {
		if (orgId != null) {
			issueTypeStanal = new IssueTypeStanal();
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			issueTypeStanal.setOrg(org);
			issueTypeStanal.setYear(year.intValue());
			issueTypeStanal.setMonth(month.intValue());
			Ticket ticket = generateDataImportTicket();
			ticketId = ticket.getTicketId();
			StatAnalyseIssueTypeThread statAnalyseThread = new StatAnalyseIssueTypeThread(
					ThreadVariable.getSession(), issueTypeStanalService,
					issueTypeStanal, ticketId, ticketService);
			statAnalyseThread.start();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=GBK");
			PrintWriter printWriter = response.getWriter();
			printWriter.print("{ticketId:'" + ticket.getTicketId() + "'}");
		}
	}

	private Ticket generateDataImportTicket() {
		StatDataTicket dataImportTicket = new StatDataTicket();
		dataImportTicket.setTicketId(TicketIdGenerator.generateTicket());
		dataImportTicket.setDescription("");
		return ticketService.addTicket(dataImportTicket, 0);
	}

	private PageInfo<IssueTypeStanal> emptyPage(int pageSize) {
		PageInfo<IssueTypeStanal> pageInfo = new PageInfo<IssueTypeStanal>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<IssueTypeStanal>());
		return pageInfo;
	}

	private Map<String, Date> createMonthStartEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Map<String, Date> map = new HashMap<String, Date>();
		map.put(MONTH_STARTTIME, initStartCal((Calendar) cal.clone()).getTime());
		map.put(MONTH_ENDTIME, initEndCal((Calendar) cal.clone()).getTime());
		return map;
	}

	private Calendar initStartCal(Calendar startCal) {
		startCal.set(startCal.get(Calendar.YEAR), startCal.get(Calendar.MONTH),
				startCal.getActualMinimum(Calendar.DAY_OF_MONTH),
				startCal.getActualMinimum(Calendar.HOUR_OF_DAY),
				startCal.getActualMinimum(Calendar.MINUTE),
				startCal.getActualMinimum(Calendar.SECOND));
		startCal.set(Calendar.MILLISECOND, 0);
		return startCal;
	}

	private Map<String, Date> createSearchMonthStartEndTime() {
		Map<String, Date> map = new HashMap<String, Date>();
		Date startCal = CalendarUtil.parseDate(String.valueOf(minYear)
				+ minMonth, "yyyyM");
		Calendar tempCal = Calendar.getInstance();

		Date endCal = CalendarUtil.parseDate(
				String.valueOf(maxYear) + maxMonth, "yyyyM");
		tempCal.setTime(endCal);
		tempCal.add(Calendar.MONTH, 1);
		tempCal.add(Calendar.DAY_OF_MONTH, -1);
		map.put(MONTH_SEARCH_STARTTIME, startCal);
		map.put(MONTH_SEARCH_ENDTIME, tempCal.getTime());

		return map;
	}

	private Map<String, Date> createSearchShowLineDate() {
		initYearAndMonth();
		Map<String, Date> map = new HashMap<String, Date>();
		Date startCal = CalendarUtil.parseDate(String.valueOf(minYear)
				+ minMonth, "yyyyM");
		Calendar tempCal = Calendar.getInstance();

		Date endCal = CalendarUtil.parseDate(
				String.valueOf(maxYear) + maxMonth, "yyyyM");
		tempCal.setTime(endCal);
		tempCal.add(Calendar.MONTH, 1);
		tempCal.add(Calendar.DAY_OF_MONTH, -1);
		map.put(MONTH_SEARCH_STARTTIME, startCal);
		map.put(MONTH_SEARCH_ENDTIME, tempCal.getTime());

		return map;
	}

	private void initYearAndMonth() {
		int middleYear = maxYear - minYear;
		int middleMonth = maxMonth - minMonth;
		if (middleYear == 0) {
			if (middleMonth == 0) {
				initMiddleMonth(middleMonth);
			}
		} else {
			initDate(middleYear);
		}
	}

	private void initDate(int middleYear) {
		int totalMonth = middleYear * 12 - (minMonth - 1) + maxMonth;
		if (totalMonth == 1) {
			if (!commonService.getSysBeginUseMonth().equals(minMonth)) {
				minMonth = minMonth - 1;
			} else if (commonService.getSysBeginUseMonth().equals(maxMonth)) {
				maxMonth = maxMonth + 1;
			}
		}
	}

	private void initMiddleMonth(int middleMonth) {
		if (!commonService.getSysBeginUseMonth().equals(minMonth)) {
			minMonth = minMonth - 1;
		} else if (commonService.getSysBeginUseMonth().equals(maxMonth)) {
			maxMonth = maxMonth + 1;
		}
	}

	private Map<String, Date> initSearchShowLine() {
		initYearAndMonth();
		Map<String, Date> map = new HashMap<String, Date>();
		if (validateYearMonth()) {
			map.put(MONTH_SEARCH_STARTTIME, getDefaultStartTime());
			map.put(MONTH_SEARCH_ENDTIME, getDefaultEndTime());
		} else {
			Date startCal = CalendarUtil.parseDate(String.valueOf(minYear)
					+ minMonth, "yyyyM");
			Calendar tempCal = Calendar.getInstance();

			Date endCal = CalendarUtil.parseDate(String.valueOf(maxYear)
					+ maxMonth, "yyyyM");
			tempCal.setTime(endCal);
			tempCal.add(Calendar.MONTH, 1);
			tempCal.add(Calendar.DAY_OF_MONTH, -1);
			map.put(MONTH_SEARCH_STARTTIME, startCal);
			map.put(MONTH_SEARCH_ENDTIME, tempCal.getTime());
		}

		return map;
	}

	private Map<String, Date> initSearchMonthStartEndTime() {
		Map<String, Date> map = new HashMap<String, Date>();
		if (validateYearMonth()) {
			map.put(MONTH_SEARCH_STARTTIME, getDefaultStartTime());
			map.put(MONTH_SEARCH_ENDTIME, getDefaultEndTime());
		} else {
			Date startCal = CalendarUtil.parseDate(String.valueOf(minYear)
					+ minMonth, "yyyyM");
			Calendar tempCal = Calendar.getInstance();

			Date endCal = CalendarUtil.parseDate(String.valueOf(maxYear)
					+ maxMonth, "yyyyM");
			tempCal.setTime(endCal);
			tempCal.add(Calendar.MONTH, 1);
			tempCal.add(Calendar.DAY_OF_MONTH, -1);
			map.put(MONTH_SEARCH_STARTTIME, startCal);
			map.put(MONTH_SEARCH_ENDTIME, tempCal.getTime());
		}

		return map;
	}

	private Calendar initEndCal(Calendar endCal) {
		endCal.set(endCal.get(Calendar.YEAR), endCal.get(Calendar.MONTH),
				endCal.getActualMaximum(Calendar.DAY_OF_MONTH),
				endCal.getActualMaximum(Calendar.HOUR_OF_DAY),
				endCal.getActualMaximum(Calendar.MINUTE),
				endCal.getActualMaximum(Calendar.SECOND));
		return endCal;
	}

	private boolean validateYearMonth() {
		return (minYear == null || minMonth == null || maxYear == null
				|| maxMonth == null || minYear == 0 || minMonth == 0
				|| maxYear == 0 || maxMonth == 0);
	}

	private String joinStr(Integer i) {
		String str = String.valueOf(i);
		return str.length() < 2 ? "0" + str : str;
	}

	public String showIssueColumn() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (validateYearMonth()) {
			Map<String, Date> searchMap = initSearchMonthStartEndTime();
			List<IssueTypeStanal> columnIssueTypeStanalList = issueTypeStanalService
					.getPeriodIssueTypeStatInfoListByOrgGroupByTypeDomain(
							orgId, searchMap.get(MONTH_SEARCH_STARTTIME),
							searchMap.get(MONTH_SEARCH_ENDTIME), scopeId);

			Map<String, Date> map = createMonthStartEndTime();

			createShowIssueColumn(columnIssueTypeStanalList,
					sdf.format(map.get(MONTH_STARTTIME)),
					sdf.format(map.get(MONTH_ENDTIME)));
		} else {
			Map<String, Date> map = createSearchMonthStartEndTime();
			List<IssueTypeStanal> columnIssueTypeStanalList = issueTypeStanalService
					.getPeriodIssueTypeStatInfoListByOrgGroupByTypeDomain(
							orgId, map.get(MONTH_SEARCH_STARTTIME),
							map.get(MONTH_SEARCH_ENDTIME), scopeId);
			createShowIssueColumn(columnIssueTypeStanalList,
					sdf.format(map.get(MONTH_SEARCH_STARTTIME)),
					sdf.format(map.get(MONTH_SEARCH_ENDTIME)));
		}
		return SUCCESS;
	}

	public String showIssueLine() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (validateYearMonth()) {
			Map<String, Date> searchMap = initSearchShowLine();
			List<List<IssueTypeStanal>> lineIssueTypeStanalList = issueTypeStanalService
					.getPeriodIssueTypeStatInfoByOrgGroupByTypeDomain(orgId,
							searchMap.get(MONTH_SEARCH_STARTTIME),
							searchMap.get(MONTH_SEARCH_ENDTIME), scopeId);

			Map<String, Date> map = createMonthStartEndTime();

			createShowIssueLine(lineIssueTypeStanalList,
					sdf.format(map.get(MONTH_STARTTIME)),
					sdf.format(map.get(MONTH_ENDTIME)));
		} else {
			Map<String, Date> map = createSearchShowLineDate();
			List<List<IssueTypeStanal>> lineIssueTypeStanalList = issueTypeStanalService
					.getPeriodIssueTypeStatInfoByOrgGroupByTypeDomain(orgId,
							map.get(MONTH_SEARCH_STARTTIME),
							map.get(MONTH_SEARCH_ENDTIME), scopeId);

			createShowIssueLine(lineIssueTypeStanalList,
					sdf.format(map.get(MONTH_SEARCH_STARTTIME)),
					sdf.format(map.get(MONTH_SEARCH_ENDTIME)));
		}
		return SUCCESS;
	}

	private void createShowIssueColumn(
			List<IssueTypeStanal> issueTypeStanalList, String startStr,
			String endStr) {

		highchartColumnVo = new HighchartColumnVo();
		highchartColumnVo.setModuleName("事件处理-类别统计(" + startStr + "至" + endStr
				+ ")");

		List<AmchartBean> list = new ArrayList<AmchartBean>();
		for (IssueTypeStanal issueTypeStanal : issueTypeStanalList) {
			list.add(createBean(issueTypeStanal.getId(),
					issueTypeStanal.getDomainName(), null, "按类别统计",
					issueTypeStanal.getCount()));
		}

		highchartColumnVo = parseAmchartBeanToHighchartColumnvo(
				highchartColumnVo, list);
	}

	private AmchartBean createBean(Long id, String name, String color,
			String str, Integer count) {
		AmchartBean bean = new AmchartBean();
		bean.setId(id);
		bean.setName(name);
		bean.setColor(color);
		Map<String, Integer> dataMap = new TreeMap<String, Integer>();
		if (validateYearMonth()) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			dataMap.put(initStartCal(cal).get(Calendar.YEAR) + "-"
					+ joinStr((initStartCal(cal).get(Calendar.MONTH) + 1)),
					count);
		} else {
			dataMap.put(minYear + "-" + joinStr(minMonth) + "~" + maxYear + "-"
					+ joinStr(maxMonth), count);
		}
		bean.setDataMap(dataMap);
		return bean;
	}

	private void createShowIssueLine(
			List<List<IssueTypeStanal>> issueTypeStanalList, String startStr,
			String endStr) {
		highchartColumnVo = new HighchartColumnVo();
		highchartColumnVo.setModuleName("月度趋势图(" + startStr + "至" + endStr
				+ ")");

		List<AmchartBean> list = new ArrayList<AmchartBean>();
		list = createBeanLine(issueTypeStanalList, null);

		highchartColumnVo = parseAmchartBeanToHighchartColumnvo(
				highchartColumnVo, list);
	}

	private List<AmchartBean> createBeanLine(
			List<List<IssueTypeStanal>> issueTypeStanals, String color) {
		List<AmchartBean> amchartBeans = new ArrayList<AmchartBean>();

		List<IssueTypeDomain> allDomains = issueTypeStanalService
				.findIssueTypeDomains();

		for (IssueTypeDomain issueTypeDomain : allDomains) {
			AmchartBean bean = new AmchartBean();
			bean.setColor(color);
			bean.setId(issueTypeDomain.getId());
			bean.setName(issueTypeDomain.getDomainName());
			Map<String, Integer> dataMap = new TreeMap<String, Integer>();
			for (List<IssueTypeStanal> list : issueTypeStanals) {
				for (IssueTypeStanal issueTypeStanal : list)
					if (issueTypeStanal.getIssueTypeDomain().getId()
							.equals(issueTypeDomain.getId())) {
						dataMap.put(issueTypeStanal.getYear() + "-"
								+ issueTypeStanal.getMonth(),
								issueTypeStanal.getCount());
					}
			}
			bean.setDataMap(dataMap);
			amchartBeans.add(bean);
		}
		return amchartBeans;
	}

	public String showIssueTypeSonColumn() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Date> map = null;
		if (validateYearMonth()) {
			map = createMonthStartEndTime();
			List<IssueTypeStanal> issueTypeStanalList = issueTypeStanalService
					.getPeriodIssueTypeStatDetailInfoByOrgAndTypeDomain(orgId,
							issueTypeStanal.getIssueTypeDomain().getId(),
							map.get(MONTH_SEARCH_STARTTIME),
							map.get(MONTH_SEARCH_ENDTIME), scopeId);
			showIssueSonTypeColumn(issueTypeStanalList,
					sdf.format(map.get(MONTH_STARTTIME)),
					sdf.format(map.get(MONTH_ENDTIME)));
		} else {
			map = createSearchMonthStartEndTime();
			List<IssueTypeStanal> issueTypeStanalList = issueTypeStanalService
					.getPeriodIssueTypeStatDetailInfoByOrgAndTypeDomain(orgId,
							issueTypeStanal.getIssueTypeDomain().getId(),
							map.get(MONTH_SEARCH_STARTTIME),
							map.get(MONTH_SEARCH_ENDTIME), scopeId);
			showIssueSonTypeColumn(issueTypeStanalList,
					sdf.format(map.get(MONTH_SEARCH_STARTTIME)),
					sdf.format(map.get(MONTH_SEARCH_ENDTIME)));
		}
		return SUCCESS;
	}

	private void showIssueSonTypeColumn(
			List<IssueTypeStanal> issueTypeStanalList, String startStr,
			String endStr) {
		highchartColumnVo = new HighchartColumnVo();
		highchartColumnVo.setModuleName("事件处理-子类别(" + startStr + "至" + endStr
				+ ")");

		List<AmchartBean> list = new ArrayList<AmchartBean>();
		list = fillAmchartBeanTypeList(list, issueTypeStanalList);

		highchartColumnVo = parseAmchartBeanToHighchartColumnvo(
				highchartColumnVo, list);
	}

	private List<AmchartBean> fillAmchartBeanTypeList(List<AmchartBean> list,
			List<IssueTypeStanal> issueTypeStanalList) {
		for (IssueTypeStanal issueTypeStanal : issueTypeStanalList) {
			list.add(createBean(issueTypeStanal.getId(),
					issueTypeStanal.getTypeName(), null, "按子类型统计",
					issueTypeStanal.getCount()));
		}
		return list;
	}

	@PermissionFilter(ename = "issueTypeStat")
	public String dispatch() throws Exception {
		if (mode.equals("produceDate")) {
			minYear = commonService.getSysBeginUseYear();
			minMonth = commonService.getSysBeginUseMonth();

			Calendar endCalendar = Calendar.getInstance();
			maxYear = endCalendar.get(Calendar.YEAR);
			maxMonth = endCalendar.get(Calendar.MONTH) + 1;
			return "produceDate";
		} else {
			minYear = commonService.getSysBeginUseYear();
			minMonth = commonService.getSysBeginUseMonth();

			Calendar endCalendar = Calendar.getInstance();
			maxYear = endCalendar.get(Calendar.YEAR);
			maxMonth = endCalendar.get(Calendar.MONTH) + 1;
			if (maxMonth == 1) {
				maxMonth = 12;
				maxYear = maxYear - 1;
			} else {
				maxMonth = maxMonth - 1;
			}
			return SUCCESS;
		}
	}

	public static Map.Entry[] getSortedHashtableByKey(Map h) {
		Set set = h.entrySet();
		Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
				.size()]);
		Arrays.sort(entries, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				try {
					Date key1 = new SimpleDateFormat("yyyy-MM")
							.parse(((Map.Entry) arg0).getKey().toString());
					Date key2 = new SimpleDateFormat("yyyy-MM")
							.parse(((Map.Entry) arg1).getKey().toString());
					return ((Comparable) key1).compareTo(key2);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return 0;
			}

		});

		return entries;
	}

	private HighchartColumnVo parseAmchartBeanToHighchartColumnvo(
			HighchartColumnVo highchartColumnVo2, List<AmchartBean> daylist) {

		List<HighchartDataColumnVo> listHighchartDataColumnVo = new ArrayList<HighchartDataColumnVo>();
		// X轴的数据值，对应时间轴
		String[] categories = null;
		for (int n = 0; n < daylist.size(); n++) {
			AmchartBean bean = daylist.get(n);
			Map<String, Integer> dataMap = bean.getDataMap();
			int countAll = 0;
			HighchartDataColumnVo vo1 = new HighchartDataColumnVo();
			Map.Entry[] entries = getSortedHashtableByKey(dataMap);
			int i = 0;
			int[] chirddata = new int[dataMap.size()];
			categories = new String[dataMap.size()];
			for (int j = 0; j < entries.length; j++) {
				if (n == 0) {
					categories[i] = String.valueOf(entries[j].getKey());
					highchartColumnVo2.setCategories(categories);
				}
				chirddata[i] = Integer.parseInt(entries[j].getValue()
						.toString());
				countAll = countAll
						+ Integer.parseInt(entries[j].getValue().toString());
				i++;
			}
			vo1.setData(chirddata);
			vo1.setName(bean.getName());
			vo1.setStack("123456");
			listHighchartDataColumnVo.add(vo1);
		}
		highchartColumnVo2.setSeries(listHighchartDataColumnVo);
		return highchartColumnVo2;
	}

	public AmchartKetSet getKeys() {
		return keys;
	}

	public void setKeys(AmchartKetSet keys) {
		this.keys = keys;
	}

	private Long orgId;
	private IssueTypeStanal issueTypeStanal;
	private Integer minYear;
	private Integer minMonth;
	private Integer maxYear;
	private Integer maxMonth;
	private Long year;
	private Long month;

	AmchartKetSet keys;

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public IssueTypeStanal getIssueTypeStanal() {
		return issueTypeStanal;
	}

	public void setIssueTypeStanal(IssueTypeStanal issueTypeStanal) {
		this.issueTypeStanal = issueTypeStanal;
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

	public String getTitle_key1() {
		return title_key1;
	}

	public void setTitle_key1(String title_key1) {
		this.title_key1 = title_key1;
	}

	public String getData_key1() {
		return data_key1;
	}

	public void setData_key1(String data_key1) {
		this.data_key1 = data_key1;
	}

	public Long getScopeId() {
		return scopeId;
	}

	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}

	public HighchartColumnVo getHighchartColumnVo() {
		return highchartColumnVo;
	}

	public void setHighchartColumnVo(HighchartColumnVo highchartColumnVo) {
		this.highchartColumnVo = highchartColumnVo;
	}
}
