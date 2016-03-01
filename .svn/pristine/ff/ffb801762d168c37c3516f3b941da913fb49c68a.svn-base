package com.tianque.plugin.analysisNew.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.cache.service.CacheService;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.plugin.analysisNew.dao.IssueAnalysisChartNewDao;
import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.HighchartDataColumnVo;
import com.tianque.plugin.analysisNew.domain.IssueLineColumnVo;
import com.tianque.plugin.analysisNew.domain.IssueLineDataColumnVo;
import com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 
 * @author 龙振东
 * @说明：事件研判分析统计图service层
 */
@Service("issueAnalysisChartNewService")
public class IssueAnalysisChartNewServiceImpl implements
		IssueAnalysisChartNewService {
	private static Logger logger = LoggerFactory
			.getLogger(IssueAnalysisChartNewServiceImpl.class);
	private static final String STATISTICS_TYPE = "issueStatistics";
	@Autowired
	private IssueAnalysisChartNewDao issueAnalysisChartDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CacheService cacheService;

	private static final String START_DATE = "startDate";
	private static final String END_TDATE = "endDate";

	@Override
	public HighchartColumnVo getStatisticColumn(Long orgId, Integer year,
			Integer month, Integer quarter, String searchType) {
		if (null == orgId) {
			throw new BusinessValidationException("参数错误");
		}
		String key = "";
		if (IssueAnalysisSearchType.SEARCH_BY_MONTH.equals(searchType)) {
			key = STATISTICS_TYPE + "_" + searchType + "_"
					+ ModulTypes.STATISTICSCOLUMN + "_" + orgId + "_" + year
					+ "_" + month;
		} else if (IssueAnalysisSearchType.SEARCH_BY_QUARTER.equals(searchType)) {
			key = STATISTICS_TYPE + "_" + searchType + "_"
					+ ModulTypes.STATISTICSCOLUMN + "_" + orgId + "_" + year
					+ "_" + quarter;
		} else if (IssueAnalysisSearchType.SEARCH_BY_YEAR.equals(searchType)) {
			key = STATISTICS_TYPE + "_" + searchType + "_"
					+ ModulTypes.STATISTICSCOLUMN + "_" + orgId + "_" + year;
		}
		HighchartColumnVo highchartColumnVo = null;
		highchartColumnVo = (HighchartColumnVo) cacheService.get(key);
		if (highchartColumnVo != null) {
			return highchartColumnVo;
		}
		if (IssueAnalysisSearchType.SEARCH_BY_MONTH.equals(searchType)) {
			if (year == Calendar.getInstance().get(Calendar.YEAR)// 按月统计
					&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
				highchartColumnVo = getRealTimeStatisticInfo(orgId, year,
						month, quarter, searchType);
				cacheService.set(key, ModulTypes.CACHETIME, highchartColumnVo);
				return highchartColumnVo;
			}
			highchartColumnVo = getStatisticInfoFromHistory(orgId, year, month,
					null, searchType);
			cacheService.set(key, ModulTypes.CACHETIME, highchartColumnVo);
			return highchartColumnVo;
		} else if (IssueAnalysisSearchType.SEARCH_BY_QUARTER.equals(searchType)) {// 按季度统计
			if (quarter == (Calendar.getInstance().get(Calendar.MONTH) / 3 + 1)) {
				highchartColumnVo = getRealTimeStatisticInfo(orgId, year,
						month, quarter, searchType);
				cacheService.set(key, ModulTypes.CACHETIME, highchartColumnVo);
				return highchartColumnVo;
			}
			highchartColumnVo = getStatisticInfoFromHistory(orgId, year, null,
					quarter, searchType);
			cacheService.set(key, ModulTypes.CACHETIME, highchartColumnVo);
			return highchartColumnVo;
		} else {// 按年统计
			if (year == Calendar.getInstance().get(Calendar.YEAR)) {
				highchartColumnVo = getRealTimeStatisticInfo(orgId, year,
						month, quarter, searchType);
				cacheService.set(key, ModulTypes.CACHETIME, highchartColumnVo);
				return highchartColumnVo;
			}
			highchartColumnVo = getStatisticInfoFromHistory(orgId, year, null,
					null, searchType);
			cacheService.set(key, ModulTypes.CACHETIME, highchartColumnVo);
			return highchartColumnVo;
		}

	}

	@Override
	public List<Object[]> getStatisticPie(Long orgId, Integer year,
			Integer month, Integer quarter, String searchType,
			Long issueTypeDomainId) {
		if (null == orgId) {
			throw new BusinessValidationException("参数错误");
		}
		String key = "";
		if (IssueAnalysisSearchType.SEARCH_BY_MONTH.equals(searchType)) {
			key = STATISTICS_TYPE + "_" + searchType + "_"
					+ ModulTypes.STATISTICSPIE + "_" + orgId + "_"
					+ issueTypeDomainId + "_" + year + "_" + month;
		} else if (IssueAnalysisSearchType.SEARCH_BY_QUARTER.equals(searchType)) {
			key = STATISTICS_TYPE + "_" + searchType + "_"
					+ ModulTypes.STATISTICSPIE + "_" + orgId + "_"
					+ issueTypeDomainId + "_" + year + "_" + quarter;
		} else {
			key = STATISTICS_TYPE + "_" + searchType + "_"
					+ ModulTypes.STATISTICSPIE + "_" + orgId + "_"
					+ issueTypeDomainId + "_" + year;
		}

		List<Object[]> list = (List<Object[]>) cacheService.get(key);
		if (list != null) {
			return list;
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (issueTypeDomainId.equals(new Long(0))) {
			list = getAllIssueDomainPieInfo(org.getOrgInternalCode(), year,
					month, quarter, searchType);
		} else {
			list = getAllIssueTypePieInfo(org.getOrgInternalCode(), year,
					month, quarter, searchType, issueTypeDomainId);
		}
		cacheService.set(key, ModulTypes.CACHETIME, list);
		return list;
	}

	@Override
	public void createIssueAnalysisChartData() {
		issueAnalysisChartDao.createIssueAnalysisChartData();
	}

	/**
	 * 事件趋势图
	 */
	@Override
	public HighchartColumnVo getIssueTendencyChart(Long orgId, String searchType) {
		if (null == orgId) {
			throw new BusinessValidationException("参数错误");
		}

		String key = STATISTICS_TYPE + "_" + searchType + "_" + orgId + "_"
				+ ModulTypes.STATISTICSTREND;
		HighchartColumnVo highchartColumnVo = (HighchartColumnVo) cacheService
				.get(key);
		if (highchartColumnVo != null) {
			return highchartColumnVo;
		}

		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		highchartColumnVo = new HighchartColumnVo();
		List<HighchartDataColumnVo> series = new ArrayList<HighchartDataColumnVo>();
		HighchartDataColumnVo highchartDataColumnVo = new HighchartDataColumnVo();
		String[] time = getTime(searchType);
		highchartColumnVo.setCategories(time);
		highchartColumnVo.setModuleName("事件数量增长图");
		highchartDataColumnVo.setName("事件数量");
		highchartColumnVo.setSeries(series);
		series.add(highchartDataColumnVo);
		int[] data = new int[time.length];
		if (IssueAnalysisSearchType.SEARCH_BY_MONTH.equals(searchType)) {// 按月份统计
			for (int i = 0; i < time.length; i++) {
				String[] tempTime = time[i].split("-");
				int year = Integer.parseInt(tempTime[0]);
				int month = Integer
						.parseInt(tempTime[1].startsWith("0") ? tempTime[1]
								.substring(1) : tempTime[1]);
				data[i] = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year, month, null);
			}
			highchartDataColumnVo.setData(data);
		} else if (IssueAnalysisSearchType.SEARCH_BY_QUARTER.equals(searchType)) {// 按季度统计
			for (int i = 0; i < time.length; i++) {
				String[] tempTime = time[i].split("-");
				int year = Integer.parseInt(tempTime[0]);
				int quarter = Integer.parseInt(tempTime[1].substring(0, 1));
				data[i] = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year, null, quarter);
			}
			highchartDataColumnVo.setData(data);
		} else {// 按年统计
			for (int i = 0; i < time.length; i++) {
				int year = Integer.parseInt(time[i]);
				data[i] = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year, null, null);
			}
			highchartDataColumnVo.setData(data);
		}
		cacheService.set(key, ModulTypes.CACHETIME, highchartColumnVo);
		return highchartColumnVo;
	}

	@Override
	public IssueLineColumnVo getIssueComparedSameChart(Long orgId,
			Integer year, Integer month, Integer quarter, String searchType) {
		IssueLineColumnVo issueLineColumnVo = new IssueLineColumnVo();
		List<IssueLineDataColumnVo> series = new ArrayList<IssueLineDataColumnVo>();
		IssueLineDataColumnVo rateDataColumnVo = new IssueLineDataColumnVo();// 同比增长率
		IssueLineDataColumnVo speedDataColumnVo = new IssueLineDataColumnVo();// 同比增长速度
		issueLineColumnVo.setModuleName("事件数量同比增长情况");
		issueLineColumnVo.setSeries(series);
		series.add(rateDataColumnVo);
		series.add(speedDataColumnVo);
		rateDataColumnVo.setName("同比增长率(%)");
		speedDataColumnVo.setName("同比增长速度(%)");
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (IssueAnalysisSearchType.SEARCH_BY_MONTH.equals(searchType)) {
			String[] time = getMonthArrayByYearAndMonth(year, month);
			issueLineColumnVo.setCategories(time);
			Object[] rateData = new Object[time.length];
			Object[] speedData = new Object[time.length];
			for (int i = 0; i < time.length; i++) {
				String[] tempTime = time[i].split("-");
				year = Integer.parseInt(tempTime[0]);
				month = Integer
						.parseInt(tempTime[1].startsWith("0") ? tempTime[1]
								.substring(1) : tempTime[1]);
				Integer thisCount = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year, month, null);// 本期数
				Integer thatCount = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year - 1, month, null);// 同期数
				setComparedData(thisCount - thatCount, thatCount, rateData, i);
				setComparedData(thisCount, thatCount, speedData, i);
			}
			rateDataColumnVo.setData(rateData);
			speedDataColumnVo.setData(speedData);

		} else if (IssueAnalysisSearchType.SEARCH_BY_QUARTER.equals(searchType)) {
			String[] time = getQuarterArrayByYearAndQuarter(year, quarter);
			issueLineColumnVo.setCategories(time);
			Object[] rateData = new Object[time.length];
			Object[] speedData = new Object[time.length];
			for (int i = 0; i < time.length; i++) {
				String[] tempTime = time[i].split("-");
				year = Integer.parseInt(tempTime[0]);
				quarter = Integer.parseInt(tempTime[1].substring(0, 1));
				Integer thisCount = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year, null, quarter);// 本期数
				Integer thatCount = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year - 1, null,
								quarter);// 同期数
				setComparedData(thisCount - thatCount, thatCount, rateData, i);
				setComparedData(thisCount, thatCount, speedData, i);
			}
			rateDataColumnVo.setData(rateData);
			speedDataColumnVo.setData(speedData);
		}
		return issueLineColumnVo;
	}

	@Override
	public IssueLineColumnVo getIssueSequentialChart(Long orgId, Integer year,
			Integer month, Integer quarter, String searchType) {

		IssueLineColumnVo issueLineColumnVo = new IssueLineColumnVo();
		List<IssueLineDataColumnVo> series = new ArrayList<IssueLineDataColumnVo>();
		IssueLineDataColumnVo rateDataColumnVo = new IssueLineDataColumnVo();// 环比增长率
		IssueLineDataColumnVo speedDataColumnVo = new IssueLineDataColumnVo();// 环比增长速度
		issueLineColumnVo.setModuleName("事件数量环比增长情况");
		issueLineColumnVo.setSeries(series);
		series.add(rateDataColumnVo);
		series.add(speedDataColumnVo);
		rateDataColumnVo.setName("环比增长率(%)");
		speedDataColumnVo.setName("环比增长速度(%)");
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (IssueAnalysisSearchType.SEARCH_BY_MONTH.equals(searchType)) {
			String[] time = getMonthArrayByYearAndMonth(year, month);
			issueLineColumnVo.setCategories(time);
			Object[] rateData = new Object[time.length];
			Object[] speedData = new Object[time.length];
			for (int i = 0; i < time.length; i++) {
				String[] tempTime = time[i].split("-");
				year = Integer.parseInt(tempTime[0]);
				month = Integer
						.parseInt(tempTime[1].startsWith("0") ? tempTime[1]
								.substring(1) : tempTime[1]);
				Integer thisCount = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year, month, null);// 本期数
				if (month == 1) {// 如果是一月 就跨年
					year = year - 1;
					month = 12;
				} else {
					month = month - 1;
				}
				Integer thatCount = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year, month, null);// 上期数
				setComparedData(thisCount - thatCount, thatCount, rateData, i);
				setComparedData(thisCount, thatCount, speedData, i);
			}
			rateDataColumnVo.setData(rateData);
			speedDataColumnVo.setData(speedData);

		} else if (IssueAnalysisSearchType.SEARCH_BY_QUARTER.equals(searchType)) {
			String[] time = getQuarterArrayByYearAndQuarter(year, quarter);
			issueLineColumnVo.setCategories(time);
			Object[] rateData = new Object[time.length];
			Object[] speedData = new Object[time.length];
			for (int i = 0; i < time.length; i++) {
				String[] tempTime = time[i].split("-");
				year = Integer.parseInt(tempTime[0]);
				quarter = Integer.parseInt(tempTime[1].substring(0, 1));
				Integer thisCount = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year, null, quarter);// 本期数
				if (quarter == 1) {// 如果是一月 就跨年
					year = year - 1;
					quarter = 4;
				} else {
					quarter = quarter - 1;
				}
				Integer thatCount = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year - 1, null,
								quarter);// 上期数
				setComparedData(thisCount - thatCount, thatCount, rateData, i);
				setComparedData(thisCount, thatCount, speedData, i);
			}
			rateDataColumnVo.setData(rateData);
			speedDataColumnVo.setData(speedData);
		} else if (IssueAnalysisSearchType.SEARCH_BY_YEAR.equals(searchType)) {
			String[] time = getYearArrayByYear(year);
			issueLineColumnVo.setCategories(time);
			Object[] rateData = new Object[time.length];
			Object[] speedData = new Object[time.length];
			for (int i = 0; i < time.length; i++) {
				year = Integer.parseInt(time[i]);
				Integer thisCount = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year, null, null);// 本期数
				Integer thatCount = issueAnalysisChartDao
						.getIssueTendencyCountFromHistory(
								org.getOrgInternalCode(), year - 1, null, null);// 上期数
				setComparedData(thisCount - thatCount, thatCount, rateData, i);
				setComparedData(thisCount, thatCount, speedData, i);
			}
			rateDataColumnVo.setData(rateData);
			speedDataColumnVo.setData(speedData);
		}
		return issueLineColumnVo;
	}

	/** 根据事件小类查询出数据 */
	private List<Object[]> getAllIssueTypePieInfo(String orgCode, Integer year,
			Integer month, Integer quarter, String searchType,
			Long issueTypeDomainId) {
		Map<String, Date> dateMap = getDateMap(year, month, quarter, searchType);
		Date startDate = dateMap.get(START_DATE);
		Date endDate = dateMap.get(END_TDATE);
		if (IssueAnalysisSearchType.SEARCH_BY_MONTH.equals(searchType)) {
			if (year == Calendar.getInstance().get(Calendar.YEAR)// 按月统计
					&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
				return createPieInfo(issueAnalysisChartDao
						.getAllIssueTypeStatisticPieFromRealTime(orgCode,
								startDate, endDate, issueTypeDomainId));
			}
			return createPieInfo(issueAnalysisChartDao
					.getAllIssueTypeStatisticPieFromHistory(orgCode, year,
							month, null, issueTypeDomainId));
		} else if (IssueAnalysisSearchType.SEARCH_BY_QUARTER.equals(searchType)) {// 按季度统计
			if (quarter == (Calendar.getInstance().get(Calendar.MONTH) / 3 + 1)) {
				return createPieInfo(issueAnalysisChartDao
						.getAllIssueTypeStatisticPieFromRealTime(orgCode,
								startDate, endDate, issueTypeDomainId));
			}
			return createPieInfo(issueAnalysisChartDao
					.getAllIssueTypeStatisticPieFromHistory(orgCode, year,
							null, quarter, issueTypeDomainId));
		} else {// 按年统计
			if (year == Calendar.getInstance().get(Calendar.YEAR)) {
				return createPieInfo(issueAnalysisChartDao
						.getAllIssueTypeStatisticPieFromRealTime(orgCode,
								startDate, endDate, issueTypeDomainId));
			}
			return createPieInfo(issueAnalysisChartDao
					.getAllIssueTypeStatisticPieFromHistory(orgCode, year,
							null, null, issueTypeDomainId));
		}
	}

	/***
	 * @说明:根据事件大类查询出数据 ，分实时数据和历史数据两种
	 */
	private List<Object[]> getAllIssueDomainPieInfo(String orgCode,
			Integer year, Integer month, Integer quarter, String searchType) {
		Map<String, Date> dateMap = getDateMap(year, month, quarter, searchType);
		Date startDate = dateMap.get(START_DATE);
		Date endDate = dateMap.get(END_TDATE);
		if (IssueAnalysisSearchType.SEARCH_BY_MONTH.equals(searchType)) {
			if (year == Calendar.getInstance().get(Calendar.YEAR)// 按月统计
					&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
				return createPieInfo(issueAnalysisChartDao
						.getAllIssueDomainStatisticPieFromRealTime(orgCode,
								startDate, endDate));
			}
			return createPieInfo(issueAnalysisChartDao
					.getAllIssueDomainStatisticPieFromHistory(orgCode, year,
							month, null));
		} else if (IssueAnalysisSearchType.SEARCH_BY_QUARTER.equals(searchType)) {// 按季度统计
			if (quarter == (Calendar.getInstance().get(Calendar.MONTH) / 3 + 1)) {
				return createPieInfo(issueAnalysisChartDao
						.getAllIssueDomainStatisticPieFromRealTime(orgCode,
								startDate, endDate));
			}
			return createPieInfo(issueAnalysisChartDao
					.getAllIssueDomainStatisticPieFromHistory(orgCode, year,
							null, quarter));
		} else {// 按年统计
			if (year == Calendar.getInstance().get(Calendar.YEAR)) {
				return createPieInfo(issueAnalysisChartDao
						.getAllIssueDomainStatisticPieFromRealTime(orgCode,
								startDate, endDate));
			}
			return createPieInfo(issueAnalysisChartDao
					.getAllIssueDomainStatisticPieFromHistory(orgCode, year,
							null, null));
		}
	}

	/** 创建饼图数据 */
	private List<Object[]> createPieInfo(List<Map<String, Object>> list) {
		if (list == null || list.size() == 0) {
			return new ArrayList<Object[]>();
		}
		List<Object[]> resultList = new ArrayList<Object[]>();
		double count = 0;// 总数
		for (int i = 0; i < list.size(); i++) {
			count += ((BigDecimal) list.get(i).get("ISSUESUM")).doubleValue();
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] objs = new Object[2];
			int countValue = Integer.valueOf(((BigDecimal) list.get(i).get(
					"ISSUESUM")).toString());
			objs[0] = list.get(i).get("DOMAINNAME").toString() + "( "
					+ new java.text.DecimalFormat("#").format(countValue)
					+ " )";
			if (countValue == 0) {
				objs[1] = Double.parseDouble("0");
			} else {
				objs[1] = Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(countValue / count * 100));
			}
			resultList.add(objs);
		}
		return resultList;
	}

	/*** 柱状图获取实时数据 */
	private HighchartColumnVo getRealTimeStatisticInfo(Long orgId,
			Integer year, Integer month, Integer quarter, String searchType) {
		Map<String, Date> dateMap = getDateMap(year, month, quarter, searchType);
		Date startDate = dateMap.get(START_DATE);
		Date endDate = dateMap.get(END_TDATE);
		return createHighchartColumnVo(orgId, "getStatisticColumnFromRealTime",
				new Object[] { orgId, startDate, endDate, null }, new Class[] {
						Long.class, Date.class, Date.class, String.class });
	}

	/** 柱状图获取历史数据 */
	private HighchartColumnVo getStatisticInfoFromHistory(Long orgId,
			Integer year, Integer month, Integer quarter, String searchType) {

		return createHighchartColumnVo(orgId, "getStatisticColumnFromHistory",
				new Object[] { orgId, year, month, quarter, null },
				new Class[] { Long.class, Integer.class, Integer.class,
						Integer.class, String.class });
	}

	private HighchartColumnVo createHighchartColumnVo(Long orgId,
			String methodName, Object[] arguments, Class... parameterTypes) {
		HighchartColumnVo highchartColumnVo = new HighchartColumnVo();
		try {
			highchartColumnVo.setModuleName("事件处理");
			List<HighchartDataColumnVo> series = new ArrayList<HighchartDataColumnVo>();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (String key : IssueTypes.map.keySet()) {
				Method method = issueAnalysisChartDao.getClass().getMethod(
						methodName, parameterTypes);
				arguments[arguments.length - 1] = IssueTypes.map.get(key);
				list = (List<Map<String, Object>>) method.invoke(
						issueAnalysisChartDao, arguments);
				HighchartDataColumnVo highchartDataColumnVo = new HighchartDataColumnVo();
				highchartDataColumnVo.setStack("事件类型");
				highchartDataColumnVo.setName(IssueTypes.map.get(key));
				List<Integer> datas = new ArrayList<Integer>();
				for (int i = 0; i < list.size(); i++) {
					datas.add(((BigDecimal) list.get(i).get("ISSUESUM"))
							.intValue());
				}
				highchartDataColumnVo.setData(getIntByInteger(datas
						.toArray(new Integer[datas.size()])));
				series.add(highchartDataColumnVo);
			}
			List<String> orgList = new ArrayList<String>();
			if (null == list || list.size() == 0) {
				List<Organization> orgs = organizationDubboService
						.findOrganizationsByParentId(orgId);
				for (int i = 0; i < orgs.size(); i++) {
					orgList.add(orgs.get(i).getOrgName());
				}
			} else {
				for (int i = 0; i < list.size(); i++) {
					orgList.add((String) list.get(i).get("ORGNAME"));
				}
			}
			highchartColumnVo.setSeries(series);
			highchartColumnVo.setCategories(orgList.toArray(new String[orgList
					.size()]));
		} catch (Exception e) {
			logger.error("查询出错", e);
			throw new BusinessValidationException("查询出错");
		}

		return highchartColumnVo;
	}

	/** 获取时间 */
	private Map<String, Date> getDateMap(Integer year, Integer month,
			Integer quarter, String searchType) {
		Map<String, Date> dateMap = new HashMap<String, Date>();
		Calendar calendar = Calendar.getInstance();
		if (IssueAnalysisSearchType.SEARCH_BY_MONTH.equals(searchType)) {
			calendar.set(year, month - 1, 1, 0, 0);// 从该月的一号零点开始
			dateMap.put(START_DATE, calendar.getTime());
			dateMap.put(END_TDATE, new Date());
		} else if (IssueAnalysisSearchType.SEARCH_BY_QUARTER.equals(searchType)) {
			setCalendarForQuarter(calendar, year, month, quarter);
			dateMap.put(START_DATE, calendar.getTime());
			setCalendarForQuarter(calendar, year, month, quarter + 1);
			dateMap.put(END_TDATE, calendar.getTime());
		} else if (IssueAnalysisSearchType.SEARCH_BY_YEAR.equals(searchType)) {
			calendar.set(year, 0, 1, 0, 0);// 从一月一日 零点开始
			dateMap.put(START_DATE, calendar.getTime());
			dateMap.put(END_TDATE, new Date());
		}
		return dateMap;
	}

	/** 获取季度时间 用于获取季度实时数据 */
	private void setCalendarForQuarter(Calendar calendar, Integer year,
			Integer month, Integer quarter) {
		switch (quarter) {
		case 1:
			calendar.set(year, 0, 1, 0, 0);
			break;
		case 2:
			calendar.set(year, 3, 1, 0, 0);
			break;
		case 3:
			calendar.set(year, 6, 1, 0, 0);
			break;
		case 4:
			calendar.set(year, 9, 1, 0, 0);
			break;
		case 5:
			calendar.set(year + 1, 0, 1, 0, 0);
			break;
		default:
			break;
		}
	}

	private int[] getIntByInteger(Integer[] integers) {
		int len = integers.length;
		int[] results = new int[len];
		for (int i = 0; i < len; i++) {
			if (null == integers[i]) {
				results[i] = 0;
			} else {
				results[i] = integers[i];
			}
		}
		return results;
	}

	private String[] getTime(String searchType) {
		String[] time = null;
		Calendar nowCalendar = Calendar.getInstance();
		if (IssueAnalysisSearchType.SEARCH_BY_MONTH.equals(searchType)) {
			time = new String[12];
			Date nowDate = new Date();
			nowCalendar.setTime(nowDate);
			getMonthArray(nowCalendar, time);
		} else if (IssueAnalysisSearchType.SEARCH_BY_QUARTER.equals(searchType)) {
			time = new String[4];
			getQuarterArray(nowCalendar, time);
		} else if (IssueAnalysisSearchType.SEARCH_BY_YEAR.equals(searchType)) {
			time = new String[10];
			int year = nowCalendar.get(Calendar.YEAR);
			int j = 9;
			for (int i = year - 1; i >= year - 10; i--) {
				time[j] = String.valueOf(i);
				j--;
			}
		}

		return time;
	}

	private void getMonthArray(Calendar calendar, String[] time) {
		calendar.add(Calendar.MONTH, -12);
		for (int i = 0; i < 12; i++) {
			SimpleDateFormat timePattern = new SimpleDateFormat("yyyy-MM");
			time[i] = timePattern.format(calendar.getTime());
			calendar.add(Calendar.MONTH, 1);
		}
	}

	private void getQuarterArray(Calendar calendar, String[] time) {
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		if (month == 1 || month == 2 || month == 3) {
			for (int i = 0; i < 4; i++) {
				time[i] = (year - 1) + "-" + (i + 1) + "季度";
			}
		} else if (month == 4 || month == 5 || month == 6) {
			time[0] = (year - 1) + "-" + "2季度";
			time[1] = (year - 1) + "-" + "3季度";
			time[2] = (year - 1) + "-" + "4季度";
			time[3] = year + "-" + "1季度";
		} else if (month == 7 || month == 8 || month == 9) {
			time[0] = (year - 1) + "-" + "3季度";
			time[1] = (year - 1) + "-" + "4季度";
			time[2] = year + "-" + "1季度";
			time[3] = year + "-" + "2季度";
		} else if (month == 10 || month == 11 || month == 12) {
			time[0] = (year - 1) + "-" + "4季度";
			time[1] = year + "-" + "1季度";
			time[2] = year + "-" + "2季度";
			time[3] = year + "-" + "3季度";
		}
	}

	/**
	 * 
	 * @说明：以下是同比和环比的跨季度、跨月的时间轴的设定
	 */

	private String[] getMonthArrayByYearAndMonth(int year, int month) {
		String[] time = new String[12];
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month + 1, 0, 0, 0);
		getMonthArray(calendar, time);// 设定月份
		return time;
	}

	private String[] getQuarterArrayByYearAndQuarter(int year, int quarter) {
		String[] time = new String[4];
		if (quarter == 1) {
			time[0] = (year - 1) + "-" + "2季度";
			time[1] = (year - 1) + "-" + "3季度";
			time[2] = (year - 1) + "-" + "4季度";
			time[3] = year + "-" + quarter + "季度";
		} else if (quarter == 2) {
			time[0] = (year - 1) + "-" + "3季度";
			time[1] = (year - 1) + "-" + "4季度";
			time[2] = year + "-" + "1季度";
			time[3] = year + "-" + quarter + "季度";
		} else if (quarter == 3) {
			time[0] = (year - 1) + "-" + "1季度";
			time[1] = year + "-" + "1季度";
			time[2] = year + "-" + "2季度";
			time[3] = year + "-" + quarter + "季度";
		} else if (quarter == 4) {
			time[0] = year + "-" + "1季度";
			time[1] = year + "-" + "2季度";
			time[2] = year + "-" + "3季度";
			time[3] = year + "-" + quarter + "季度";
		}
		return time;

	}

	private String[] getYearArrayByYear(int year) {
		String[] time = new String[10];
		int j = 0;
		for (int i = year - 9; i <= year; i++) {
			time[j] = String.valueOf(i);
			j++;
		}
		return time;
	}

	private void setComparedData(Integer differCount, Integer thatCount,
			Object[] data, int index) {
		if (differCount == 0) {
			data[index] = Double.parseDouble("0");
		} else if (thatCount == 0) {
			data[index] = Double
					.parseDouble(new java.text.DecimalFormat("#.00")
							.format(100));
		} else {
			data[index] = Double
					.parseDouble(new java.text.DecimalFormat("#.00")
							.format(differCount / thatCount * 100));
		}
	}

}
