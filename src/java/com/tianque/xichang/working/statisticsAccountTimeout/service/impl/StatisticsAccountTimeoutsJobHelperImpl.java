package com.tianque.xichang.working.statisticsAccountTimeout.service.impl;

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
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.service.WorkCalendarService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tableManage.service.TableManageService;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.flow.constant.FinishType;
import com.tianque.xichang.working.flow.domain.AccountLogs;
import com.tianque.xichang.working.flow.domain.AccountStep;
import com.tianque.xichang.working.flow.service.AccountLogsService;
import com.tianque.xichang.working.flow.service.AccountStepService;
import com.tianque.xichang.working.parameterEvaluation.deductionStandard.domain.ParameterConfig;
import com.tianque.xichang.working.parameterEvaluation.deductionStandard.service.ParameterConfigService;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.Parametertimelimit;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.service.ParametertimelimitService;
import com.tianque.xichang.working.statisticsAccountTimeout.domain.StatisticsAccountTimeout;
import com.tianque.xichang.working.statisticsAccountTimeout.service.StatisticsAccountTimeoutService;
import com.tianque.xichang.working.statisticsAccountTimeout.service.StatisticsAccountTimeoutsJobHelper;

/**
 * 三本台账时限成绩job帮助实现类
 * 
 * @author zhangyouwei
 */
@Service("statisticsAccountTimeoutsJobHelper")
@Transactional
public class StatisticsAccountTimeoutsJobHelperImpl implements
		StatisticsAccountTimeoutsJobHelper {
	private static Logger logger = LoggerFactory
			.getLogger(StatisticsAccountTimeoutsJobHelperImpl.class);
	@Autowired
	private StatisticsAccountTimeoutService statisticsAccountTimeoutService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	AccountStepService accountStepService;
	@Autowired
	ParameterConfigService parameterConfigService;
	@Autowired
	ParametertimelimitService parametertimelimitService;
	@Autowired
	AccountLogsService accountLogsService;
	@Autowired
	WorkCalendarService workCalendarService;
	@Autowired
	private TableManageService tableManageService;

	@Override
	public int executeDataByModel(List<Long> idModList, String taskParameter) {
		if (idModList == null || idModList.size() == 0
				|| !StringUtil.isStringAvaliable(taskParameter)) {
			return 0;
		}
		int year = CalendarUtil.getNowYear();
		int month = CalendarUtil.getNowMonth();

		// 根据取模去删除原有的数据脏数据
		deleteDirtyDataByModel(idModList, taskParameter, year, month);

		// 手动分页去新增数据
		return executeDataOnPaging(idModList, taskParameter, year, month);
	}

	/**
	 * 清除脏数据
	 * 
	 * @param idModList
	 * @param taskParameter
	 * @param year
	 * @param month
	 */
	private void deleteDirtyDataByModel(List<Long> idModList,
			String taskParameter, int year, int month) {

		if (idModList == null || idModList.size() <= 0) {
			logger.error("类StatisticsAccountTimeoutsJobHelperImpl的deleteDirtyDataByModel方法错误原因是传入的参数错误");
			return;
		}
		try {
			boolean isCreate = tableManageService.createAnalyseTable(
					AnalyseUtil.STATISTICS_ACCOUNT_TIMEOUT,
					AnalyseUtil.STATISTICS_ACCOUNT_TIMEOUT_SQL, year, month);
			if (!isCreate) {
				int sum = statisticsAccountTimeoutService
						.countDirtyDataByModel(idModList, taskParameter, year,
								month);

				if (sum > 0) {
					statisticsAccountTimeoutService.deleteDirtyDataByModel(
							idModList, taskParameter, year, month);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类StatisticsAccountTimeoutsJobHelperImpl的addGridStatisticsAccountTimeoutData方法出现异常，原因：",
					"三本台账统计网格级成绩job成绩出现错误", e);
		}

	}

	/**
	 * 由于数据量可能很多所以需要分页处理
	 * 
	 * @param idModList
	 * @param taskParameter
	 * @param year
	 * @param month
	 */
	private int executeDataOnPaging(List<Long> idModList, String taskParameter,
			int year, int month) {
		int count = organizationDubboService
				.countOrgIdsByModelForStatisticsAccountTimeouts(idModList,
						taskParameter);
		if (count > 0) {
			String tableName = AnalyseUtil.STATISTICS_ACCOUNT_TIMEOUT + "_"
					+ year + "_" + month;
			int pageSize = AccountType.ACCOUNT_TIMEOUTS_PAGE_SIZE;
			int page = count / pageSize + 1;
			List<Long> orgIds;
			Map<String, Date> date = getStartdDateAndEndDate();
			List<StatisticsAccountTimeout> statisticsAccountTimeouts = new ArrayList<StatisticsAccountTimeout>();
			for (int i = 0; i < page; i++) {
				orgIds = organizationDubboService
						.queryOrgIdsByModelForStatisticsAccountTimeouts(
								idModList, taskParameter, pageSize, tableName);
				if (orgIds != null && orgIds.size() > 0) {
					StatisticsAccountTimeout statisticsAccountTimeout = null;
					for (Long orgId : orgIds) {
						Organization organization = organizationDubboService
								.getSimpleOrgById(orgId);
						statisticsAccountTimeout = new StatisticsAccountTimeout();
						statisticsAccountTimeout.setYear(CalendarUtil
								.getNowYear());
						statisticsAccountTimeout.setMonth(CalendarUtil
								.getNowMonth());
						statisticsAccountTimeout.setOrganization(organization);
						statisticsAccountTimeout.setParentOrgId(organization
								.getParentOrg().getId());
						statisticsAccountTimeout = fullStatisticsAccountTimeoutByOrganization(
								date, statisticsAccountTimeout);
						if (statisticsAccountTimeout != null) {
							addStatisticsAccountTimeoutData(statisticsAccountTimeout);
							// statisticsAccountTimeouts
							// .add(statisticsAccountTimeout);
						}
					}
					statisticsAccountTimeoutService
							.batchAddDate(statisticsAccountTimeouts);
					statisticsAccountTimeouts.clear();

				}
			}
		}
		return count;
	}

	private void addStatisticsAccountTimeoutData(
			StatisticsAccountTimeout statisticsAccountTimeout) {
		if (statisticsAccountTimeout == null
				|| statisticsAccountTimeout.getDoing() == null
				|| statisticsAccountTimeout.getDone() == null
				|| statisticsAccountTimeout.getTransfer() == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			statisticsAccountTimeoutService
					.processReportStatisticsAccountTimeout(statisticsAccountTimeout);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类StatisticsAccountTimeoutsJobHelperImpl的addStatisticsAccountTimeoutData方法出现异常，原因：",
					"三本台账统计成绩job帮助类新增成绩出现错误", e);
		}

	}

	/**
	 * 根据开始时间和结束时间和 organization填充StatisticsAccountTimeout
	 */
	private StatisticsAccountTimeout fullStatisticsAccountTimeoutByOrganization(
			Map<String, Date> date,
			StatisticsAccountTimeout statisticsAccountTimeout) {
		List<AccountStep> accountSteps = accountStepService
				.getAccountStepByDateAndTargetOrgId(date,
						statisticsAccountTimeout.getOrganization().getId());
		return analyzeStatisticsAccountTimeout(statisticsAccountTimeout,
				accountSteps, date);

	}

	/** 分析accountsteps计算出当前组织机构的各个扣分情况 */
	private StatisticsAccountTimeout analyzeStatisticsAccountTimeout(
			StatisticsAccountTimeout statisticsAccountTimeout,
			List<AccountStep> accountSteps, Map<String, Date> date) {

		ParameterConfig parameterConfig = parameterConfigService
				.getParameterConfig();
		Parametertimelimit parametertimelimit = parametertimelimitService
				.getParametertimelimitByOrgId(statisticsAccountTimeout
						.getOrganization().getId());
		int doingCount = 0, doneCount = 0, transferCount = 0;
		// 办理时限
		int doingTimeLimit = parametertimelimit.getHandlelimit();
		// 办结时限
		int doneTimeLimit = parametertimelimit.getTransferredlimit();
		// 流转时限
		int transferTimeLimit = parametertimelimit.getCirculationlimit();
		// 办理扣分
		double doingScoreBase = parameterConfig.getHandleScore();
		// 办结扣分
		double doneScoreBase = parameterConfig.getTransferredScore();
		// 流转扣分
		double transferScoreBase = parameterConfig.getCirculationScore();

		for (AccountStep accountStep : accountSteps) {
			// 该条台账办理是否超期
			try {
				if (analyzeDoingScore(accountStep, doingTimeLimit)) {
					doingCount++;
				}
			} catch (Exception e) {
				throw new ServiceValidationException(
						"类StatisticsAccountTimeoutServiceImpl的analyzeStatisticsAccountTimeout方法出现异常，原因：",
						"三本台账统计办理成绩出现错误", e);
			}

			// 只有实质性办结才能够去判断台账办结是否超期
			try {
				if (accountStep.getIsFinish()
						&& accountStep.getFinishType() != null
						&& null != accountStep.getRealOrCirculation()
						&& FinishType.REAL_FINISH == accountStep
								.getRealOrCirculation()) {
					// 该条台账办结是否超期
					if (analyzeDoneScore(accountStep, doneTimeLimit)) {
						doneCount++;
					}
				}
			} catch (Exception e) {
				throw new ServiceValidationException(
						"类StatisticsAccountTimeoutServiceImpl的analyzeStatisticsAccountTimeout方法出现异常，原因：",
						"三本台账统计办结成绩出现错误", e);
			}
			// 只有是流转的台账才能判断是否流转超期
			try {
				if (accountStep.getIsFinish()
						&& accountStep.getFinishType() != null
						&& null != accountStep.getRealOrCirculation()
						&& FinishType.CIRCULATION_FINISH == accountStep
								.getRealOrCirculation()) {
					// 该条台账流转是否超期
					if (analyzeDoneScore(accountStep, transferTimeLimit)) {
						transferCount++;
					}
				}
			} catch (Exception e) {
				throw new ServiceValidationException(
						"类StatisticsAccountTimeoutServiceImpl的analyzeStatisticsAccountTimeout方法出现异常，原因：",
						"三本台账统计流转成绩出现错误", e);
			}

		}

		statisticsAccountTimeout.setDoing(doingScoreBase * doingCount);
		statisticsAccountTimeout.setDone(doneScoreBase * doneCount);
		statisticsAccountTimeout.setTransfer(transferScoreBase * transferCount);
		return statisticsAccountTimeout;
	}

	/** 根据处理时间和步骤判断该条台账 【办结】或者【流转】是否超期 */
	private boolean analyzeDoneScore(AccountStep accountStep, int doneTimeLimit) {
		List<AccountLogs> accountLogs = accountLogsService
				.findAccountLogsByAccountIdAndAccountType(accountStep
						.getAccountId(), accountStep.getAccountType(),
						accountStep.getTargetOrg().getId());
		Date earliest = CalendarUtil.now();
		Date doneTime = CalendarUtil.now();
		if (accountLogs != null && accountLogs.size() > 0) {
			for (int i = 0; i < accountLogs.size(); i++) {
				earliest = accountLogs.get(0).getCreateDate();
				if (earliest.after(accountLogs.get(i).getCreateDate())) {
					earliest = accountLogs.get(i).getCreateDate();
				}
				if (accountLogs.get(i).getIsFinish() != null
						&& accountLogs.get(i).getIsFinish()) {
					doneTime = accountLogs.get(i).getCreateDate();
				}
			}
		}
		// 办理工作日
		int doneWorkDays = workCalendarService
				.getWorkDaysFromStartTimeToEndTime(earliest, doneTime);

		if ((doneWorkDays - doneTimeLimit > 1)) {
			return true;
		}
		return false;
	}

	/** 根据处理时间和步骤判断该条台账 【办理】 是否超期 */
	private boolean analyzeDoingScore(AccountStep accountStep,
			int doingTimeLimit) {
		List<AccountLogs> accountLogs = accountLogsService
				.findAccountLogsByAccountIdAndAccountType(accountStep
						.getAccountId(), accountStep.getAccountType(),
						accountStep.getTargetOrg().getId());
		Date earliest = CalendarUtil.now();
		if (accountLogs != null && accountLogs.size() > 0) {
			for (int i = 0; i < accountLogs.size(); i++) {
				earliest = accountLogs.get(0).getCreateDate();
				if (earliest.after(accountLogs.get(i).getCreateDate())) {
					earliest = accountLogs.get(i).getCreateDate();
				}
			}
		}
		// 办理工作日
		int doingWorkDays = workCalendarService
				.getWorkDaysFromStartTimeToEndTime(accountStep.getCreateDate(),
						earliest);

		if ((doingWorkDays - doingTimeLimit > 1)) {
			return true;
		}
		return false;
	}

	/**
	 * 得到统计数据开始的日期（开始日期之前的数据不予以统计）
	 */
	private Map<String, Date> getStartdDateAndEndDate() {
		Map<String, Date> date = new HashMap<String, Date>();

		int month = CalendarUtil.getNowMonth();
		int year = CalendarUtil.getNowYear();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		// 当前年份的当前月份20号23:59:59【当月月报表结束时间】
		Date date0 = calendar.getTime();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 2);
		// 当前月份的统计的开始时间（ 上个月的20号23:59:59【当月月报表开始时间，上月月报表结束时间】）
		Date date1 = calendar.getTime();

		date.put("START_DATE", date1);
		date.put("END_DATE", date0);
		return date;
	}

	/**
	 * 判断是否是当前月份
	 */
	/*private boolean isNowMonth(Integer year, Integer month) {
		int nowMonth = CalendarUtil.getNowMonth();
		int nowYear = CalendarUtil.getNowYear();
		if (nowYear == year && nowMonth == month) {
			return true;
		}
		return false;
	}*/

}
