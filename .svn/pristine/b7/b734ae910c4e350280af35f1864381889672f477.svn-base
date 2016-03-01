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

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.service.WorkCalendarService;
import com.tianque.statRegrad.util.RegradedPointUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tableManage.service.TableManageService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.ParametersConvertUtil;
import com.tianque.xichang.working.flow.constant.FinishType;
import com.tianque.xichang.working.flow.domain.AccountLogs;
import com.tianque.xichang.working.flow.domain.AccountStep;
import com.tianque.xichang.working.flow.service.AccountLogsService;
import com.tianque.xichang.working.flow.service.AccountStepService;
import com.tianque.xichang.working.parameterEvaluation.deductionStandard.domain.ParameterConfig;
import com.tianque.xichang.working.parameterEvaluation.deductionStandard.service.ParameterConfigService;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.domain.Parametertimelimit;
import com.tianque.xichang.working.parameterEvaluation.timeLimit.service.ParametertimelimitService;
import com.tianque.xichang.working.statisticsAccountTimeout.dao.StatisticsAccountTimeoutDao;
import com.tianque.xichang.working.statisticsAccountTimeout.domain.StatisticsAccountTimeout;
import com.tianque.xichang.working.statisticsAccountTimeout.service.StatisticsAccountTimeoutService;

/**
 * 时限考核成绩业务实现
 */
@Service("statisticsAccountTimeoutService")
@Transactional
public class StatisticsAccountTimeoutServiceImpl extends AbstractBaseService
		implements StatisticsAccountTimeoutService {
	private static Logger logger = LoggerFactory
			.getLogger(StatisticsAccountTimeoutServiceImpl.class);
	@Autowired
	private StatisticsAccountTimeoutDao statisticsAccountTimeoutDao;
	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	PropertyDictService propertyDictService;
	@Autowired
	AccountStepService accountStepService;
	@Autowired
	AccountLogsService accountLogsService;
	@Autowired
	WorkCalendarService workCalendarService;
	@Autowired
	ParameterConfigService parameterConfigService;
	@Autowired
	ParametertimelimitService parametertimelimitService;
	@Autowired
	private TableManageService tableManageService;

	@Override
	public GridPage<StatisticsAccountTimeout> findStatisticsAccountTimeoutByParentOrgIdAndTime(
			Long parentOrgId, int internalId, Integer year, Integer month,
			String sortField, String sord) {
		GridPage<StatisticsAccountTimeout> gridPage;
		try {
			gridPage = new GridPage<StatisticsAccountTimeout>();
			List<StatisticsAccountTimeout> statisticsAccountTimeouts;
			boolean isNowMonth = isNowMonth(year, month);
			long orgType = RegradedPointUtil.getOrganizationTypeIdByInternalId(
					propertyDictService, internalId);
			// 当前时间没有超出21号即使统计
			if (isNowMonth && CalendarUtil.getNowDay() < 21) {
				statisticsAccountTimeouts = realTimeAnalyzeStatisticsAccountTimeout(
						parentOrgId, orgType);
				gridPage.setRows(statisticsAccountTimeouts);
			} else {
				tableManageService
						.createAnalyseTable(
								AnalyseUtil.STATISTICS_ACCOUNT_TIMEOUT,
								AnalyseUtil.STATISTICS_ACCOUNT_TIMEOUT_SQL,
								year, month);
				// 超过21号或者等于21号说明当前月job已经执行过那么就去数据
				statisticsAccountTimeouts = statisticsAccountTimeoutDao
						.findStatisticsAccountTimeoutByParentOrgIdAndTime(
								parentOrgId, orgType, year, month, sortField,
								sord);
				for (StatisticsAccountTimeout statisticsAccountTimeout : statisticsAccountTimeouts) {
					statisticsAccountTimeout
							.setOrganization(organizationDubboService
									.getFullOrgById(statisticsAccountTimeout
											.getOrganization().getId()));
				}
				gridPage.setRows(statisticsAccountTimeouts);

			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类StatisticsAccountTimeoutServiceImpl的findStatisticsAccountTimeoutByParentOrgIdAndTime方法出现异常，原因：",
					"三本台账显示成绩出现错误", e);
		}
		return gridPage;
	}

	/** 实时统计成绩 */
	private List<StatisticsAccountTimeout> realTimeAnalyzeStatisticsAccountTimeout(
			Long parentOrgId, Long orgType) {
		List<StatisticsAccountTimeout> statisticsAccountTimeouts = new ArrayList<StatisticsAccountTimeout>();
		List<Organization> organizations = organizationDubboService
				.findOrganizationByParentIdAndOrgType(parentOrgId, orgType);
		Map<String, Date> date = getStartdDateAndEndDate();
		for (Organization organization : organizations) {
			StatisticsAccountTimeout statisticsAccountTimeout = new StatisticsAccountTimeout();
			statisticsAccountTimeout.setYear(CalendarUtil.getNowYear());
			statisticsAccountTimeout.setMonth(CalendarUtil.getNowMonth());
			statisticsAccountTimeout.setOrganization(organization);
			statisticsAccountTimeout.setParentOrgId(organization.getParentOrg()
					.getId());
			statisticsAccountTimeout = fullStatisticsAccountTimeoutByOrganization(
					date, statisticsAccountTimeout);
			statisticsAccountTimeouts.add(statisticsAccountTimeout);
		}
		return statisticsAccountTimeouts;
	}

	@Override
	public void addStatisticsAccountTimeoutData() {
		// 统计开始日期
		Map<String, Date> date = getStartdDateAndEndDate();
		List<Organization> organizations = findAllOrganizationExcludeCountry();
		for (Organization organization : organizations) {
			try {

				StatisticsAccountTimeout statisticsAccountTimeout = new StatisticsAccountTimeout();
				statisticsAccountTimeout.setYear(CalendarUtil.getNowYear());
				statisticsAccountTimeout.setMonth(CalendarUtil.getNowMonth());
				statisticsAccountTimeout.setOrganization(organization);
				statisticsAccountTimeout.setParentOrgId(organization
						.getParentOrg().getId());
				statisticsAccountTimeout = fullStatisticsAccountTimeoutByOrganization(
						date, statisticsAccountTimeout);

				statisticsAccountTimeoutDao
						.addStatisticsAccountTimeout(statisticsAccountTimeout);
			} catch (Exception e) {
				throw new ServiceValidationException(
						"类StatisticsAccountTimeoutServiceImpl的addStatisticsAccountTimeoutData方法出现异常，原因：",
						"三本台账统计成绩出现错误", e);
			}
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

	/** 根据处理时间和步骤判断该条 台账【流转】是否超期 （超期逻辑和已办结一样） */
	private boolean analyzeTransferScore(AccountStep accountStep,
			int transferTimeLimit) {
		// TODO Auto-generated method stub
		return false;
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

	/** 查询出所有的组织，包括行政部门，和职能部门 不包括中国层级 */
	private List<Organization> findAllOrganizationExcludeCountry() {
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		List<Organization> organizations = new ArrayList<Organization>();
		for (PropertyDict orgLevel : orgLevels) {
			if (orgLevel.getInternalId() != OrganizationLevel.COUNTRY) {
				organizations.addAll(organizationDubboService
						.fingOrganizationforLevel(orgLevel.getId()));
			}
		}
		return organizations;
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
	private boolean isNowMonth(Integer year, Integer month) {
		int nowMonth = CalendarUtil.getNowMonth();
		int nowYear = CalendarUtil.getNowYear();
		if (nowYear == year && nowMonth == month) {
			return true;
		}
		return false;
	}

	@Override
	public void addStatisticsAccountTimeout(
			StatisticsAccountTimeout statisticsAccountTimeout) {
		if (statisticsAccountTimeout == null
				|| statisticsAccountTimeout.getDoing() == null
				|| statisticsAccountTimeout.getDone() == null
				|| statisticsAccountTimeout.getTransfer() == null) {
			throw new BusinessValidationException("成绩统计错误");
		}
		try {
			statisticsAccountTimeoutDao
					.addStatisticsAccountTimeout(statisticsAccountTimeout);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类StatisticsAccountTimeoutServiceImpl的addStatisticsAccountTimeout方法出现异常，原因：",
					"三本台账统计成绩新增出现错误", e);

		}

	}

	@Override
	public void updateStatisticsAccountTimeoutByStatisticsAccountTimeout(
			StatisticsAccountTimeout statisticsAccountTimeout) {
		if (statisticsAccountTimeout == null
				|| statisticsAccountTimeout.getOrganization() == null
				|| statisticsAccountTimeout.getOrganization().getId() == null
				|| statisticsAccountTimeout.getYear() == null
				|| statisticsAccountTimeout.getMonth() == null) {
			throw new BusinessValidationException("参数错误");
		}
		statisticsAccountTimeoutDao
				.updateStatisticsAccountTimeoutByStatisticsAccountTimeout(statisticsAccountTimeout);

	}

	@Override
	public StatisticsAccountTimeout getStatisticsAccountTimeoutByOrgIdAndMonthAndYear(
			Long orgId, Integer year, Integer month) {
		if (orgId == null || year == null || month == null) {
			throw new BusinessValidationException("参数错误");
		}
		return statisticsAccountTimeoutDao
				.getStatisticsAccountTimeoutByOrgIdAndMonthAndYear(orgId, year,
						month);
	}

	@Override
	public void processReportStatisticsAccountTimeout(
			StatisticsAccountTimeout statisticsAccountTimeout) {
		if (statisticsAccountTimeout == null
				|| statisticsAccountTimeout.getOrganization() == null
				|| statisticsAccountTimeout.getOrganization().getId() == null
				|| statisticsAccountTimeout.getYear() == null
				|| statisticsAccountTimeout.getMonth() == null) {
			throw new BusinessValidationException("参数错误");
		}
		StatisticsAccountTimeout temp = getStatisticsAccountTimeoutByOrgIdAndMonthAndYear(
				statisticsAccountTimeout.getOrganization().getId(),
				statisticsAccountTimeout.getYear(),
				statisticsAccountTimeout.getMonth());
		if (null != temp) {
			updateStatisticsAccountTimeoutByStatisticsAccountTimeout(statisticsAccountTimeout);
		} else {
			addStatisticsAccountTimeout(statisticsAccountTimeout);
		}

	}

	@Override
	public Integer countDirtyDataByModel(List<Long> idModList,
			String taskParameter, int year, int month) {

		if (idModList == null || idModList.size() <= 0) {
			logger.error("类StatisticsAccountTimeoutServiceImpl的countDirtyDataByModel方法错误原因是传入的参数错误");
			return null;
		}
		try {
			OrganizationVo organizationVo = new OrganizationVo();
			organizationVo.setTaskParameter(taskParameter);
			organizationVo.setIsParentOrgIdNull(true);
			organizationVo.setOrgIdsList(ParametersConvertUtil
					.convertToListString(idModList));
			List<Long> orgIdList = organizationDubboService
					.findOrgIdsBySearchVo(organizationVo);
			return statisticsAccountTimeoutDao.countDirtyDataByModel(orgIdList,
					year, month);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类StatisticsAccountTimeoutServiceImpl的countDirtyDataByModel方法出现异常，原因：",
					"三本台账时限考核成绩统计是否有脏数据出现错误", e);
		}
	}

	@Override
	public void deleteDirtyDataByModel(List<Long> idModList,
			String taskParameter, int year, int month) {
		if (idModList == null || idModList.size() <= 0) {
			logger.error("类StatisticsAccountTimeoutServiceImpl的deleteDirtyDataByModel方法错误原因是传入的参数错误");
			return;
		}
		try {
			OrganizationVo organizationVo = new OrganizationVo();
			organizationVo.setTaskParameter(taskParameter);
			organizationVo.setIsParentOrgIdNull(true);
			organizationVo.setOrgIdsList(ParametersConvertUtil
					.convertToListString(idModList));
			List<Long> orgIdList = organizationDubboService
					.findOrgIdsBySearchVo(organizationVo);
			statisticsAccountTimeoutDao.deleteDirtyDataByModel(orgIdList, year,
					month);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类StatisticsAccountTimeoutServiceImpl的countDirtyDataByModel方法出现异常，原因：",
					"三本台账时限考核成绩删除脏数据出现错误", e);
		}
	}

	@Override
	public void batchAddDate(
			List<StatisticsAccountTimeout> statisticsAccountTimeouts) {
		try {
			if (statisticsAccountTimeouts != null
					&& statisticsAccountTimeouts.size() != 0) {
				statisticsAccountTimeoutDao
						.batchAddDate(statisticsAccountTimeouts);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类StatisticsAccountTimeoutServiceImpl的batchAddDate方法出现异常，原因：",
					"三本台账时限考核成绩批量新增出现错误", e);
		}
	}
}
