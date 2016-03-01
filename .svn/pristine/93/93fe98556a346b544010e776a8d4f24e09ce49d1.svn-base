package com.tianque.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.AmchartBean;
import com.tianque.domain.BaseInfoStanalObject;
import com.tianque.domain.IssueStatueStanal;
import com.tianque.domain.Organization;
import com.tianque.domain.StatAnalyseIssue;
import com.tianque.domain.vo.AmchartKetSet;
import com.tianque.domain.vo.StatAnalyseIssueVo;
import com.tianque.service.CommonService;
import com.tianque.service.IssueStatueStanalService;
import com.tianque.service.StatAnalyseIssueStatueService;
import com.tianque.state.IssueStanalState;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.AmchartUtil;

@Controller("statAnalyseIssueStatueController")
@SuppressWarnings({ "serial", "unused", "unchecked" })
@Scope("prototype")
@Transactional
public class StatAnalyseIssueStatueController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(StatAnalyseIssueStatueController.class);

	@Autowired
	private StatAnalyseIssueStatueService statAnalyseIssueStatueService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private IssueStatueStanalService issueStatueStanalService;
	@Autowired
	private AmchartUtil amchartUtil;

	private StatAnalyseIssue statAnalyseIssue;
	private IssueStatueStanal issueStatueStanal;
	private List<StatAnalyseIssue> list;
	private Long orgId;
	private Organization sourceOrganization;
	private GridPage gridPage;
	private Date startDate;
	private Date endDate;
	private Long state;

	private int minYear;
	private int minMonth;
	private int maxYear;
	private int maxMonth;

	private int year;
	private int month;
	AmchartKetSet keys;

	public AmchartKetSet getKeys() {
		return keys;
	}

	public void setKeys(AmchartKetSet keys) {
		this.keys = keys;
	}

	public GridPage getGridPage() {
		return gridPage;
	}

	public void setGridPage(GridPage gridPage) {
		this.gridPage = gridPage;
	}

	public String dispatch() throws Exception {
		if ("search".equals(mode)) {
			minYear = commonService.getSysBeginUseYear();
			minMonth = commonService.getSysBeginUseMonth();

			Calendar endCalendar = Calendar.getInstance();
			maxYear = endCalendar.get(Calendar.YEAR);
			maxMonth = endCalendar.get(Calendar.MONTH) + 1;
			return "search";
		} else {
			minYear = commonService.getSysBeginUseYear();
			minMonth = commonService.getSysBeginUseMonth();

			Calendar endCalendar = Calendar.getInstance();
			maxYear = endCalendar.get(Calendar.YEAR);
			maxMonth = endCalendar.get(Calendar.MONTH) + 1;
			return SUCCESS;
		}
	}

	public String getIssueStateList() throws Exception {
		initDate();
		return SUCCESS;
	}

	public String reGenerateData() throws Exception {
		if (orgId != null) {
			sourceOrganization = organizationDubboService.getSimpleOrgById(orgId);
		}
		issueStatueStanal.setOrganization(sourceOrganization);
		issueStatueStanalService.addIssueStatueStanal(issueStatueStanal);
		return SUCCESS;
	}

	private void initDate() throws Exception {
		minYear = commonService.getSysBeginUseYear();
		minMonth = commonService.getSysBeginUseMonth();

		Calendar endCalendar = Calendar.getInstance();
		maxYear = endCalendar.get(Calendar.YEAR);
		maxMonth = endCalendar.get(Calendar.MONTH) + 1;
	}

	public String getIssueState() throws Exception {
		initStartDateAndEndDate();
		gridPage = new GridPage(
				statAnalyseIssueStatueService.getIssueState(statAnalyseIssue));
		return SUCCESS;
	}

	private void initStartDateAndEndDate() {
		if (orgId != null) {
			sourceOrganization = organizationDubboService.getSimpleOrgById(orgId);
		}
		if (statAnalyseIssue == null) {
			statAnalyseIssue = new StatAnalyseIssue();
			statAnalyseIssue.setOrgInternalCode(sourceOrganization
					.getOrgInternalCode());
			statAnalyseIssue.setOrganization(sourceOrganization);
		}
		if (minYear == 0 || minMonth == 0 || maxYear == 0 || maxMonth == 0) {
			minYear = commonService.getSysBeginUseYear();
			minMonth = commonService.getSysBeginUseMonth();

			Calendar endCalendar = Calendar.getInstance();
			maxYear = endCalendar.get(Calendar.YEAR);
			minYear = maxYear;
			maxMonth = endCalendar.get(Calendar.MONTH);
			minMonth = endCalendar.get(Calendar.MONTH);
		}

		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(minYear, minMonth, 1);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.set(maxYear, maxMonth, 1);

		Date startDate = null;
		Date endDate = null;

		statAnalyseIssue.setStartDate(CalendarUtil.parseDate(
				CalendarUtil.format("yyyy-MM-dd", startCalendar.getTime()),
				"yyyy-MM-dd"));
		statAnalyseIssue.setEndDate(CalendarUtil.parseDate(CalendarUtil.format(
				"yyyy-MM-dd HH:mm:ss", endDate(endCalendar).getTime()),
				"yyyy-MM-dd HH:mm:ss"));

	}

	private void initDates(IssueStatueStanal issueStatueStanal) {
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(issueStatueStanal.getYear().intValue(),
				issueStatueStanal.getMonth().intValue(), 1);

		issueStatueStanal.setEndDate(CalendarUtil.parseDate(
				CalendarUtil.format("yyyy-MM-dd HH:mm:ss",
						endDate(startCalendar).getTime()),
				"yyyy-MM-dd HH:mm:ss"));
	}

	private static Calendar endDate(Calendar startCalendar) {
		startCalendar.set(startCalendar.get(Calendar.YEAR),
				startCalendar.get(Calendar.MONTH),
				startCalendar.getActualMaximum(Calendar.DAY_OF_MONTH),
				startCalendar.getActualMaximum(Calendar.HOUR_OF_DAY),
				startCalendar.getActualMaximum(Calendar.SECOND),
				startCalendar.getActualMaximum(Calendar.MINUTE));
		return startCalendar;
	}

	public String showLine() throws Exception {
		List<AmchartBean> daylist = new ArrayList<AmchartBean>();
		initStartDateAndEndDate();
		java.text.SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = df.format(statAnalyseIssue.getStartDate());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(statAnalyseIssue.getEndDate());
		// calendar.add(Calendar.DATE, -1);
		List list = statAnalyseIssueStatueService.showLine(statAnalyseIssue);
		daylist.add(createLine(1L, "待办(超期)", "#FF0000", (List) list.get(0)));
		daylist.add(createLine(2L, "已办(超期)", "#FFC0CB", (List) list.get(1)));
		daylist.add(createLine(3L, "待办(正常)", "#40E0D0", (List) list.get(2)));
		daylist.add(createLine(4L, "已办(正常)", "#9ACD32", (List) list.get(3)));

		if (keys == null)
			keys = new AmchartKetSet();
		keys.setTitle_key2(amchartUtil.setTtile("事件处理状态统计月度趋势图(" + startDate
				+ "至" + df.format(calendar.getTime()) + ")", this.getClass()
				.getName()));
		keys.setData_key2(amchartUtil.setChartData(daylist, this.getClass()
				.getName()));

		return SUCCESS;
	}

	@SuppressWarnings("static-access")
	private AmchartBean createLine(Long id, String name, String color,
			List<StatAnalyseIssueVo> list) {
		AmchartBean bean = new AmchartBean();
		bean.setId(id);
		bean.setName(name);
		bean.setColor(color);
		Map<String, Integer> dataMap = new TreeMap<String, Integer>();

		for (StatAnalyseIssueVo statAnalyseIssueVo : list) {
			String month = "";
			Calendar can = Calendar.getInstance();
			can.setTime(formatDate(statAnalyseIssueVo.getYear(),
					statAnalyseIssueVo.getMonth()));
			int nowMonth = (can.get(can.MONTH)) + 1;
			if (nowMonth < 10) {
				month = "0" + nowMonth;
			} else {
				month = nowMonth + "";
			}
			dataMap.put(can.get(can.YEAR) + "-" + month,
					statAnalyseIssueVo.getCount());
		}
		bean.setDataMap(dataMap);
		return bean;
	}

	private static Date formatDate(int year, int month) {
		Calendar can = Calendar.getInstance();
		can.set(year, month, 1, 0, 0, 0);
		Date date = can.getTime();
		return date;
	}

	public String showAll() throws Exception {
		showPie();
		showLine();
		if (mode.equals("returnToGraph")) {
			return mode;
		}
		return "success";
	}

	public void showPie() throws Exception {
		List<AmchartBean> daylist = new ArrayList<AmchartBean>();
		java.text.SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		daylist.add(createPie(1L, "事件处理状态", "#FF0000"));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(statAnalyseIssue.getEndDate());
		// calendar.add(Calendar.DATE, -1);

		if (keys == null)
			keys = new AmchartKetSet();
		keys.setTitle_key1(amchartUtil.setTtile(
				"事件处理状态统计(" + df.format(statAnalyseIssue.getStartDate()) + "至"
						+ df.format(calendar.getTime()) + ")", this.getClass()
						.getName()));
		keys.setData_key1(amchartUtil.setChartData(daylist, this.getClass()
				.getName()));

	}

	private AmchartBean createPie(Long id, String name, String color) {
		AmchartBean bean = new AmchartBean();
		bean.setId(id);
		bean.setName(name);
		bean.setColor(color);
		Map<String, Integer> dataMap = new TreeMap<String, Integer>();
		initStartDateAndEndDate();
		List<StatAnalyseIssue> issueList = statAnalyseIssueStatueService
				.getIssueState(statAnalyseIssue);
		for (int i = 0; i < issueList.size(); i++) {
			StatAnalyseIssue statAnalyseIssue = issueList.get(i);
			dataMap.put(statAnalyseIssue.getStateName(),
					statAnalyseIssue.getCount());
		}
		bean.setDataMap(dataMap);
		return bean;
	}

	public String statIssuePie() throws Exception {
		if (IssueStanalState.NORMALNOTDONE.equals(state)) {
			statIssueNormalNotDonePie();
		} else if (IssueStanalState.NORMALDONE.equals(state)) {
			statIssueNormalDonePie();
		} else if (IssueStanalState.OVERTIMENOTDONE.equals(state)) {
			statIssueOverTimeNotDonePie();
		} else if (IssueStanalState.OVERTIMEDONE.equals(state)) {
			statIssueOverTimeDonePie();
		}
		if (mode.equals("returnToGraph")) {
			return mode;
		}
		return SUCCESS;
	}

	/*
	 * 待办（正常）事件处理饼图
	 */
	private void statIssueNormalNotDonePie() throws Exception {
		List<AmchartBean> issueData = new ArrayList<AmchartBean>();
		List<BaseInfoStanalObject> issueDataList = statAnalyseIssueStatueService
				.statIssueNormalNotDone(orgId, startDate, endDate);
		AmchartBean bean = createBaseInfoBean(1L, "事件处理正常待办", "#FF0000");
		fillMapData(issueData, issueDataList, bean);

		if (keys == null)
			keys = new AmchartKetSet();
		keys.setTitle_key1(amchartUtil.setTtile("事件处理正常待办分布图", this.getClass()
				.getName()));
		keys.setData_key1(amchartUtil.setChartData(issueData, this.getClass()
				.getName()));
		ActionContext.getContext().put("data_key1",
				amchartUtil.setChartData(issueData, this.getClass().getName()));
		ActionContext.getContext().put("title_key1",
				amchartUtil.setTtile("事件处理正常待办分布图", this.getClass().getName()));

	}

	/*
	 * 待办（超期）事件处理饼图
	 */
	private void statIssueOverTimeNotDonePie() throws Exception {
		List<AmchartBean> issueData = new ArrayList<AmchartBean>();
		List<BaseInfoStanalObject> issueDataList = statAnalyseIssueStatueService
				.statIssueOverTimeNotDone(orgId, startDate, endDate);
		AmchartBean bean = createBaseInfoBean(1L, "事件处理超期待办", "#FF0000");
		fillMapData(issueData, issueDataList, bean);
		if (keys == null)
			keys = new AmchartKetSet();
		keys.setTitle_key1(amchartUtil.setTtile("事件处理超期待办分布图", this.getClass()
				.getName()));
		keys.setData_key1(amchartUtil.setChartData(issueData, this.getClass()
				.getName()));
		ActionContext.getContext().put("data_key1",
				amchartUtil.setChartData(issueData, this.getClass().getName()));
		ActionContext.getContext().put("title_key1",
				amchartUtil.setTtile("事件处理超期待办分布图", this.getClass().getName()));

	}

	/*
	 * 已办（正常）事件处理饼图
	 */
	private void statIssueNormalDonePie() throws Exception {
		List<AmchartBean> issueData = new ArrayList<AmchartBean>();
		List<BaseInfoStanalObject> issueDataList = statAnalyseIssueStatueService
				.statIssueNormalDone(orgId, startDate, endDate);
		AmchartBean bean = createBaseInfoBean(1L, "事件处理正常已办", "#FF0000");
		fillMapData(issueData, issueDataList, bean);
		if (keys == null)
			keys = new AmchartKetSet();
		keys.setTitle_key1(amchartUtil.setTtile("事件处理正常已办分布图", this.getClass()
				.getName()));
		keys.setData_key1(amchartUtil.setChartData(issueData, this.getClass()
				.getName()));

		ActionContext.getContext().put("data_key1",
				amchartUtil.setChartData(issueData, this.getClass().getName()));
		ActionContext.getContext().put("title_key1",
				amchartUtil.setTtile("事件处理正常已办分布图", this.getClass().getName()));

	}

	/*
	 * 已办（超期）事件处理饼图
	 */
	private void statIssueOverTimeDonePie() throws Exception {
		List<AmchartBean> issueData = new ArrayList<AmchartBean>();
		List<BaseInfoStanalObject> issueDataList = statAnalyseIssueStatueService
				.statIssueOverTimeDone(orgId, startDate, endDate);
		AmchartBean bean = createBaseInfoBean(1L, "事件处理超期已办", "#FF0000");
		fillMapData(issueData, issueDataList, bean);

		if (keys == null)
			keys = new AmchartKetSet();
		keys.setTitle_key1(amchartUtil.setTtile("事件处理超期已办分布图", this.getClass()
				.getName()));
		keys.setData_key1(amchartUtil.setChartData(issueData, this.getClass()
				.getName()));
		ActionContext.getContext().put("data_key1",
				amchartUtil.setChartData(issueData, this.getClass().getName()));
		ActionContext.getContext().put("title_key1",
				amchartUtil.setTtile("事件处理超期已办分布图", this.getClass().getName()));

	}

	private AmchartBean createBaseInfoBean(Long id, String name, String color) {
		AmchartBean bean = new AmchartBean();
		bean.setId(id);
		bean.setName(name);
		bean.setColor(color);
		return bean;
	}

	private void fillMapData(List<AmchartBean> issueData,
			List<BaseInfoStanalObject> issueDataList, AmchartBean bean) {
		Map<String, Integer> dataMap = new TreeMap<String, Integer>();
		if (issueDataList != null && issueDataList.size() > 0) {
			for (BaseInfoStanalObject object : issueDataList) {
				dataMap.put(object.getOrgName(), object.getCount());
			}
		}
		bean.setDataMap(dataMap);
		issueData.add(bean);
	}

	private GridPage getGridPage(List<StatAnalyseIssue> list) {
		GridPage gridPage = new GridPage(list);
		return gridPage;
	}

	public Organization getSourceOrganization() {
		return sourceOrganization;
	}

	public void setSourceOrganization(Organization sourceOrganization) {
		this.sourceOrganization = sourceOrganization;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<StatAnalyseIssue> getList() {
		return list;
	}

	public void setList(List<StatAnalyseIssue> list) {
		this.list = list;
	}

	public StatAnalyseIssue getStatAnalyseIssue() {
		return statAnalyseIssue;
	}

	public void setStatAnalyseIssue(StatAnalyseIssue statAnalyseIssue) {
		this.statAnalyseIssue = statAnalyseIssue;
	}

	public int getMinYear() {
		return minYear;
	}

	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}

	public int getMinMonth() {
		return minMonth;
	}

	public void setMinMonth(int minMonth) {
		this.minMonth = minMonth;
	}

	public int getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}

	public int getMaxMonth() {
		return maxMonth;
	}

	public void setMaxMonth(int maxMonth) {
		this.maxMonth = maxMonth;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public IssueStatueStanal getIssueStatueStanal() {
		return issueStatueStanal;
	}

	public void setIssueStatueStanal(IssueStatueStanal issueStatueStanal) {
		this.issueStatueStanal = issueStatueStanal;
	}

}
