package com.tianque.report.builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.plugin.analyzing.dao.OrgLoginStanalsDao;
import com.tianque.plugin.analyzing.domain.OrgLoginStanals;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.service.WorkCalendarService;
import com.tianque.service.vo.MonthAndWeekWorkDayInfo;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;
import com.tianque.ticket.StatDataTicket;
import com.tianque.ticket.service.TicketService;

public class OrgLoginStanalsBuilder {
	private WorkCalendarService workCalendarService;
	private OrgLoginStanalsDao orgLoginStanalsDao;
	private TicketService ticketService;
	private TableManageService tableService;

	// private String ticketId;
	// private int orgTypeId;

	public OrgLoginStanalsBuilder(WorkCalendarService workCalendarService,
			PropertyDictService propertyDictService,
			OrgLoginStanalsDao orgLoginStanalsDao, TicketService ticketService,
			TableManageService tableService) {
		this.workCalendarService = workCalendarService;
		this.orgLoginStanalsDao = orgLoginStanalsDao;
		this.ticketService = ticketService;
		this.tableService = tableService;
	}

	// public void createOrgLoginStatData(int year, int month) {
	// // 每个月的日期集合
	// List<Date> weekdatelist = workCalendarService.getWeekDayByCalendar(
	// year, month);
	// // 将每周的集合拆分成数个周的list
	// List<Date[]> workdatelistByWeek = getWorkdayByWeekList(weekdatelist);
	//
	// // 俩个对象每个月按周进行拼接的集合，一个是每周具体的工作日期集合，一个是每周工作日的总数
	// List<List<String>> objectList = new ArrayList<List<String>>();
	//
	// int monthWorkDays = 0;
	// int[] weekWorkDays = new int[workdatelistByWeek.size()];
	// int index = 0;
	// for (Date[] weekday : workdatelistByWeek) {
	// Date startworkdate = weekday[0];
	//
	// // 每周具体的工作日
	// List<String> searchdatelist = new ArrayList<String>();
	// // 按照每周日期进行遍历
	// for (int i = 0; i < 7; i++) {
	// if (workCalendarService.isWorkDayByDate(CalendarUtil.format(
	// "yyyy-M-d", startworkdate))) {
	// searchdatelist.add(CalendarUtil.format(startworkdate));
	// monthWorkDays++;
	// }
	// startworkdate = new Date(
	// startworkdate.getTime() + 3600 * 24 * 1000);
	// }
	// objectList.add(searchdatelist);
	// weekWorkDays[index] = searchdatelist.size();
	// index++;
	// }
	// // 查出当前系统中所有的用户
	// List<OrgLoginStanals> orgLoginStanalsList = orgLoginStanalsDao
	// .findUsersByCreateDate(year * 100 + month);
	// Map<String, OrgLoginStanals> orgLoginStanalsMap = new HashMap<String,
	// OrgLoginStanals>();
	// Date date = new Date();
	// for (OrgLoginStanals orgLoginStanals : orgLoginStanalsList) {
	// orgLoginStanals.setWorkday_month(monthWorkDays);
	// orgLoginStanals.setWorkDay(weekWorkDays);
	// orgLoginStanals.setYear(year);
	// orgLoginStanals.setMonth(month);
	// orgLoginStanals.setLoginStanalDate(date);
	// orgLoginStanalsMap.put(orgLoginStanals.getUserName(),
	// orgLoginStanals);
	// }
	// // 统计每个用户每周的登录情况
	// index = 0;
	// for (List<String> workDateList : objectList) {
	// List<Map<String, Object>> result = orgLoginStanalsDao
	// .getWorkDay(workDateList);
	// for (Map<String, Object> map : result) {
	// OrgLoginStanals orgLoginStanals = orgLoginStanalsMap.get(map
	// .get("username"));
	// if (orgLoginStanals != null) {
	// orgLoginStanals.setLoggedDay(index, workDateList.size(),
	// Integer.valueOf(map.get("num").toString()));
	// }
	// }
	// index++;
	// }
	// for (OrgLoginStanals orgLoginStanals : orgLoginStanalsList) {
	// orgLoginStanalsDao.addOrgLoginStanals(orgLoginStanals);
	// }
	// /*List<Organization> orgList = orgLoginStanalsDao
	// .findOrganizationsByOrgInternalCode(
	// rootOrg.getOrgInternalCode(), orgTypeId);
	// Map<Long, OrgLoginStanals> map = new HashMap<Long, OrgLoginStanals>();
	// for (Organization org : orgList) {
	// map.put(org.getId(),
	// getWorkdaySettingWithWeekdateList(
	// initOrgLoginStanals(org, new Date(), year, month),
	// objectList));
	// }
	// // 扫描所有部门，查询所有工作日登录情况统计，算法很不好
	// // updateTicketInfo(ticketId, "{msg:'当月工作日历初始化完毕'}");
	// // 1日志扫描 2更新部门信息 3保存
	// Date startdate = CalendarUtil.parseDate(
	// CalendarUtil.format(weekdatelist.get(0)), "yyyy-MM-dd");
	// Date enddate = new Date(CalendarUtil.parseDate(
	// CalendarUtil.format(weekdatelist.get(weekdatelist.size() - 1)),
	// "yyyy-MM-dd").getTime()
	// + 24 * 60 * 60 * 1000);
	// List<OrgLoginLog> orgLoginLogList = orgLoginStanalsDao
	// .getOrgLoginLogListByStartAndEndWorkDay(
	// rootOrg.getOrgInternalCode(), orgTypeId, startdate,
	// enddate);
	// map = changeIssueStatueStanals(map, orgLoginLogList, objectList);
	// saveOrgLoginStanalsByMap(map);*/
	// }
	public void createOrgLoginStatData(int year, int month) {
		// 查出当前系统中所有的用户
		List<OrgLoginStanals> orgLoginStanalsList = orgLoginStanalsDao
				.findUsersByCreateDate(year * 100 + month);
		Date date = new Date();
		MonthAndWeekWorkDayInfo monthAndWeekWorkDayInfo;
		Map dataMap = new HashMap();
		for (OrgLoginStanals orgLoginStanals : orgLoginStanalsList) {
			monthAndWeekWorkDayInfo = workCalendarService
					.getMonthAndWeekWorkDays(year, month, orgLoginStanals
							.getOrganization().getId());
			orgLoginStanals.setWorkday_month(monthAndWeekWorkDayInfo
					.getMonthWorkDays());
			orgLoginStanals.setWorkDay(monthAndWeekWorkDayInfo
					.getWeekWorkDays());
			orgLoginStanals.setYear(year);
			orgLoginStanals.setMonth(month);
			orgLoginStanals.setLoginStanalDate(date);
			setLoggedDay(orgLoginStanals,
					monthAndWeekWorkDayInfo.getWeekWorkDayList(), dataMap,
					year, month);
		}
		dataMap.clear();
		for (OrgLoginStanals orgLoginStanals : orgLoginStanalsList) {
			orgLoginStanalsDao.addOrgLoginStanals(orgLoginStanals);
		}
		/*
		 * List<Organization> orgList = orgLoginStanalsDao
		 * .findOrganizationsByOrgInternalCode( rootOrg.getOrgInternalCode(),
		 * orgTypeId); Map<Long, OrgLoginStanals> map = new HashMap<Long,
		 * OrgLoginStanals>(); for (Organization org : orgList) {
		 * map.put(org.getId(), getWorkdaySettingWithWeekdateList(
		 * initOrgLoginStanals(org, new Date(), year, month), objectList)); } //
		 * 扫描所有部门，查询所有工作日登录情况统计，算法很不好 // updateTicketInfo(ticketId,
		 * "{msg:'当月工作日历初始化完毕'}"); // 1日志扫描 2更新部门信息 3保存 Date startdate =
		 * CalendarUtil.parseDate( CalendarUtil.format(weekdatelist.get(0)),
		 * "yyyy-MM-dd"); Date enddate = new Date(CalendarUtil.parseDate(
		 * CalendarUtil.format(weekdatelist.get(weekdatelist.size() - 1)),
		 * "yyyy-MM-dd").getTime() + 24 * 60 * 60 * 1000); List<OrgLoginLog>
		 * orgLoginLogList = orgLoginStanalsDao
		 * .getOrgLoginLogListByStartAndEndWorkDay(
		 * rootOrg.getOrgInternalCode(), orgTypeId, startdate, enddate); map =
		 * changeIssueStatueStanals(map, orgLoginLogList, objectList);
		 * saveOrgLoginStanalsByMap(map);
		 */
	}

	/*
	 * private Map<Long, OrgLoginStanals> changeIssueStatueStanals( Map<Long,
	 * OrgLoginStanals> map, List<OrgLoginLog> orgLoginLogList, List<Object[]>
	 * objectList) { for (OrgLoginLog orgLoginLog : orgLoginLogList) {
	 * OrgLoginStanals orgLoginStanals = map.get(orgLoginLog.getOrgId());
	 * OrgLoginStanals changeOrgLoginStanals =
	 * changeOrgLoginStanalsByOrgLoginLog( orgLoginLog, orgLoginStanals,
	 * objectList); map.put(orgLoginLog.getOrgId(), changeOrgLoginStanals); }
	 * return map; }
	 * 
	 * private OrgLoginStanals changeOrgLoginStanalsByOrgLoginLog( OrgLoginLog
	 * orgLoginLog, OrgLoginStanals orgLoginStanals, List<Object[]> objectList)
	 * {
	 * 
	 * orgLoginStanals = getWorkdaySettingWithWeekdateList(orgLoginStanals,
	 * objectList); // 登录天数设置 for (int n = 0; n < objectList.size(); n++) {
	 * Object[] object = objectList.get(n);
	 * 
	 * @SuppressWarnings("unchecked") List<Date> datelist = (List<Date>)
	 * object[0]; int logdaycount = 0; for (Date date : datelist) { // 如果该天有登录日志
	 * if (CalendarUtil.format(date).equalsIgnoreCase(
	 * CalendarUtil.format(orgLoginLog.getLastLogginDate()))) { logdaycount =
	 * logdaycount + 1; break; } } if (n == 0) {
	 * orgLoginStanals.setLoggedday_week1(orgLoginStanals .getLoggedday_week1()
	 * + logdaycount); } else if (n == 1) {
	 * orgLoginStanals.setLoggedday_week2(orgLoginStanals .getLoggedday_week2()
	 * + logdaycount); } else if (n == 2) {
	 * orgLoginStanals.setLoggedday_week3(orgLoginStanals .getLoggedday_week3()
	 * + logdaycount); } else if (n == 3) {
	 * orgLoginStanals.setLoggedday_week4(orgLoginStanals .getLoggedday_week4()
	 * + logdaycount); orgLoginStanals.setLoggedday_month(orgLoginStanals
	 * .getLoggedday_week1() + orgLoginStanals.getLoggedday_week2() +
	 * orgLoginStanals.getLoggedday_week3() +
	 * orgLoginStanals.getLoggedday_week4()); } else if (n == 4) {
	 * orgLoginStanals.setLoggedday_week5(orgLoginStanals .getLoggedday_week5()
	 * + logdaycount); orgLoginStanals.setLoggedday_month(orgLoginStanals
	 * .getLoggedday_week1() + orgLoginStanals.getLoggedday_week2() +
	 * orgLoginStanals.getLoggedday_week3() +
	 * orgLoginStanals.getLoggedday_week4() +
	 * orgLoginStanals.getLoggedday_week5()); } } return orgLoginStanals; }
	 */

	/** 针对一条统计信息设置每周工作日和月度总工作日 */
	/*
	 * private OrgLoginStanals getWorkdaySettingWithWeekdateList(
	 * OrgLoginStanals orgLoginStanals, List<Object[]> objectList) {
	 * 
	 * orgLoginStanals.setLoginStanalDate(new Date());
	 * orgLoginStanals.setWorkday_week1(((Integer) objectList.get(0)[1]));
	 * orgLoginStanals.setWorkday_week2(((Integer) objectList.get(1)[1]));
	 * orgLoginStanals.setWorkday_week3(((Integer) objectList.get(2)[1]));
	 * orgLoginStanals.setWorkday_week4(((Integer) objectList.get(3)[1])); if
	 * (objectList.size() == 5) { orgLoginStanals.setWorkday_week5(((Integer)
	 * objectList.get(4)[1]));
	 * orgLoginStanals.setWorkday_month(orgLoginStanals.getWorkday_week1() +
	 * orgLoginStanals.getWorkday_week2() + orgLoginStanals.getWorkday_week3() +
	 * orgLoginStanals.getWorkday_week4() + orgLoginStanals.getWorkday_week5());
	 * } else {
	 * orgLoginStanals.setWorkday_month(orgLoginStanals.getWorkday_week1() +
	 * orgLoginStanals.getWorkday_week2() + orgLoginStanals.getWorkday_week3() +
	 * orgLoginStanals.getWorkday_week4()); } return orgLoginStanals; }
	 */

	/*
	 * private void saveOrgLoginStanalsByMap(Map<Long, OrgLoginStanals> map) {
	 * int count = map.size(); int tempcount = 0; int tempShow = 0;
	 * Iterator<Entry<Long, OrgLoginStanals>> entryKeyIterator = map
	 * .entrySet().iterator(); while (entryKeyIterator.hasNext()) {
	 * OrgLoginStanals orgLoginStanals = entryKeyIterator.next() .getValue();
	 * orgLoginStanalsDao.addOrgLoginStanals(orgLoginStanals); tempcount++;
	 * tempShow++; if (tempShow == 100) { tempShow = 0; String info = "共有 " +
	 * count + " 条数据要生成, 目前已经处理 " + tempcount + " 条"; //
	 * updateTicketInfo(ticketId, "{msg:'" + info + "'}"); } } }
	 */

	/** 获得每周开始时间和结束时间 的集合 */
	private List<Date[]> getWorkdayByWeekList(List<Date> workdatelist) {
		List<Date[]> workdayByWeekList = new ArrayList<Date[]>();

		if (workdatelist == null || workdatelist.size() == 0)
			return null;
		Date[] startAndEndDay = new Date[2];
		Date startdate = workdatelist.get(0);
		Date enddate = workdatelist.get(workdatelist.size() - 1);

		long firstweek = startdate.getTime() + 24 * 60 * 60 * 1000 * 6 * 1;
		startAndEndDay[0] = (startdate);
		startAndEndDay[1] = (new Date(firstweek));
		workdayByWeekList.add(startAndEndDay);
		startAndEndDay = new Date[2];

		firstweek = firstweek + 24 * 60 * 60 * 1000 * 7 * 1;
		startAndEndDay[0] = (new Date(firstweek - 6 * 24 * 60 * 60 * 1000));
		startAndEndDay[1] = (new Date(firstweek));
		workdayByWeekList.add(startAndEndDay);
		startAndEndDay = new Date[2];

		firstweek = firstweek + 24 * 60 * 60 * 1000 * 7 * 1;
		startAndEndDay[0] = (new Date(firstweek - 6 * 24 * 60 * 60 * 1000));
		startAndEndDay[1] = (new Date(firstweek));
		workdayByWeekList.add(startAndEndDay);
		startAndEndDay = new Date[2];

		firstweek = firstweek + 24 * 60 * 60 * 1000 * 7 * 1;
		startAndEndDay[0] = (new Date(firstweek - 6 * 24 * 60 * 60 * 1000));
		startAndEndDay[1] = (new Date(firstweek));
		workdayByWeekList.add(startAndEndDay);
		startAndEndDay = new Date[2];
		if (workdatelist.size() > 28) {
			startAndEndDay[0] = (new Date(firstweek + 1 * 24 * 60 * 60 * 1000));
			startAndEndDay[1] = (enddate);
			workdayByWeekList.add(startAndEndDay);
		}
		return workdayByWeekList;
	}

	public void clearExistedStatData(int year, int month) {
		orgLoginStanalsDao.clearPeriodOrgLoginStanalsByDate(year, month);
	}

	/** 初始化Map中服务办事统计信息 */
	/*
	 * private OrgLoginStanals initOrgLoginStanals(Organization organization,
	 * Date date, int year, int month) { OrgLoginStanals orgLoginStanals = new
	 * OrgLoginStanals(); orgLoginStanals.setOrgName(organization.getOrgName());
	 * orgLoginStanals.setOrganization(organization);
	 * orgLoginStanals.setOrgInternalCode(organization.getOrgInternalCode());
	 * orgLoginStanals.setWorkday_month(0);
	 * orgLoginStanals.setLoggedday_month(0);
	 * orgLoginStanals.setWorkday_week1(0);
	 * orgLoginStanals.setLoggedday_week1(0);
	 * orgLoginStanals.setWorkday_week2(0);
	 * orgLoginStanals.setLoggedday_week2(0);
	 * orgLoginStanals.setWorkday_week3(0);
	 * orgLoginStanals.setLoggedday_week3(0);
	 * orgLoginStanals.setWorkday_week4(0);
	 * orgLoginStanals.setLoggedday_week4(0);
	 * orgLoginStanals.setWorkday_week5(0);
	 * orgLoginStanals.setLoggedday_week5(0);
	 * orgLoginStanals.setYear(Long.valueOf(year));
	 * orgLoginStanals.setMonth(Long.valueOf(month));
	 * orgLoginStanals.setLoginStanalDate(date); return orgLoginStanals; }
	 */

	public void updateTicketInfo(String ticketId, String description) {
		StatDataTicket ticket = (StatDataTicket) ticketService
				.getTicketById(ticketId);
		ticket.setDescription(description);
		ticketService
				.updateTicket(ticket, TicketService.DEFAULT_EXPRIED_SECOND);
	}

	private void setLoggedDay(OrgLoginStanals orgLoginStanals,
			List<List<String>> objectList, Map dataMap, int year, int month) {
		// 判断当前年份的月份的工作日是否跨月
		boolean isTransMonth = checkMonday(year, month);
		String firstTableName = "";
		String secondeTableName = "";
		if (!isTransMonth) {
			firstTableName = AnalyseUtil.SYSTEMLOGS_TABLE_NAME + "_" + year
					+ "_" + month;
			if (month == 1) {
				secondeTableName = AnalyseUtil.SYSTEMLOGS_TABLE_NAME + "_"
						+ (year - 1) + "_" + 12;
			} else {
				secondeTableName = AnalyseUtil.SYSTEMLOGS_TABLE_NAME + "_"
						+ year + "_" + (month - 1);
			}
		} else {
			firstTableName = AnalyseUtil.SYSTEMLOGS_TABLE_NAME + "_" + year
					+ "_" + month;
		}
		if (firstTableName != "") {
			tableService.createAnalyseTable(AnalyseUtil.SYSTEMLOGS_TABLE_NAME,
					AnalyseUtil.SYSTEMLOGS_TABLE_NAME_SQL, year, month);
		}
		if (secondeTableName != "") {
			if (month == 1) {
				tableService.createAnalyseTable(
						AnalyseUtil.SYSTEMLOGS_TABLE_NAME,
						AnalyseUtil.SYSTEMLOGS_TABLE_NAME_SQL, year - 1, 12);
			} else {
				tableService.createAnalyseTable(
						AnalyseUtil.SYSTEMLOGS_TABLE_NAME,
						AnalyseUtil.SYSTEMLOGS_TABLE_NAME_SQL, year,
						(month - 1));
			}
		}
		for (int i = 0; i < objectList.size(); i++) {
			List<Map<String, Object>> result = null;
			if (objectList.get(i) != null && objectList.get(i).size() > 0) {
				result = orgLoginStanalsDao.getWorkDay(objectList.get(i),
						dataMap, firstTableName, secondeTableName);
			}
			if (null == result)
				continue;
			for (Map<String, Object> map : result) {
				if (orgLoginStanals.getUserName().equals(
						map.get("username").toString())) {
					orgLoginStanals.setLoggedDay(i, objectList.get(i).size(),
							Integer.valueOf(map.get("num").toString()));
					break;
				}
			}
		}
	}

	/***
	 * 
	 * @param year
	 * @param month
	 * @return true 未跨月 false 跨月
	 */
	private boolean checkMonday(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
	}
}
