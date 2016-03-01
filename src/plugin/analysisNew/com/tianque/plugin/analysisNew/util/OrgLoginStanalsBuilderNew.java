package com.tianque.plugin.analysisNew.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.plugin.analysisNew.dao.OrgLoginStanalsNewDao;
import com.tianque.plugin.analysisNew.domain.OrgLoginStanals;
import com.tianque.service.WorkCalendarService;
import com.tianque.service.vo.MonthAndWeekWorkDayInfo;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;
import com.tianque.ticket.StatDataTicket;
import com.tianque.ticket.service.TicketService;

public class OrgLoginStanalsBuilderNew {
	private WorkCalendarService workCalendarService;
	private OrgLoginStanalsNewDao orgLoginStanalsDao;
	private TicketService ticketService;
	private TableManageService tableService;

	// private String ticketId;
	// private int orgTypeId;

	public OrgLoginStanalsBuilderNew(WorkCalendarService workCalendarService,
			PropertyDictService propertyDictService,
			OrgLoginStanalsNewDao orgLoginStanalsDao,
			TicketService ticketService, TableManageService tableService) {
		this.workCalendarService = workCalendarService;
		this.orgLoginStanalsDao = orgLoginStanalsDao;
		this.ticketService = ticketService;
		this.tableService = tableService;
	}

	public void createOrgLoginStatData(int year, int month) {
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
	}

	private void setLoggedDay(OrgLoginStanals orgLoginStanals,
			List<List<String>> objectList, Map dataMap, int year, int month) {
		// 判断当前年份的月份的工作日是否跨月
		boolean isTransMonth = checkMonday(year, month);
		String firstTableName = "";
		String secondeTableName = "";
		if (!isTransMonth) {
			firstTableName = AnalyseUtilNew.SYSTEMLOGS_TABLE_NAME + "_" + year
					+ "_" + month;
			if (month == 1) {
				secondeTableName = AnalyseUtilNew.SYSTEMLOGS_TABLE_NAME + "_"
						+ (year - 1) + "_" + 12;
			} else {
				secondeTableName = AnalyseUtilNew.SYSTEMLOGS_TABLE_NAME + "_"
						+ year + "_" + (month - 1);
			}
		} else {
			firstTableName = AnalyseUtilNew.SYSTEMLOGS_TABLE_NAME + "_" + year
					+ "_" + month;
		}
		if (firstTableName != "") {
			tableService.createAnalyseTable(
					AnalyseUtilNew.SYSTEMLOGS_TABLE_NAME,
					AnalyseUtilNew.SYSTEMLOGS_TABLE_NAME_SQL, year, month);
		}
		if (secondeTableName != "") {
			if (month == 1) {
				tableService.createAnalyseTable(
						AnalyseUtilNew.SYSTEMLOGS_TABLE_NAME,
						AnalyseUtilNew.SYSTEMLOGS_TABLE_NAME_SQL, year - 1, 12);
			} else {
				tableService.createAnalyseTable(
						AnalyseUtilNew.SYSTEMLOGS_TABLE_NAME,
						AnalyseUtilNew.SYSTEMLOGS_TABLE_NAME_SQL, year,
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

	public void updateTicketInfo(String ticketId, String description) {
		StatDataTicket ticket = (StatDataTicket) ticketService
				.getTicketById(ticketId);
		ticket.setDescription(description);
		ticketService
				.updateTicket(ticket, TicketService.DEFAULT_EXPRIED_SECOND);
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
