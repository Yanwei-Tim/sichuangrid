package com.tianque.job.realization;

import java.util.Calendar;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.baseInfo.nurturesWomen.service.NurturesWomenService;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.job.JobHelper;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("nurturesWomenJob")
public class NurturesWomenJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private FloatingPopulationService floatingPopulationService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private PlatformMessageService platformMessageService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private NurturesWomenService nurturesWomenService;
	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private ShardConversion shardConversion;
	private final static int pageSize = GridProperties.POPULATIONJOB_PAGESIZE;
	private final static Boolean workTimeBreak = GridProperties.WORKTIMEBREAK;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		nurturesWomenLogout();
	}

	public void nurturesWomenLogout() {
		updateNurturesWomenFloatingPopulationLogOut();
		updateNurturesWomenHouseholdStaffLogOut();
	}

	// 流动人口中育龄妇女
	public void updateNurturesWomenFloatingPopulationLogOut() {
		// 只有第二种和第三种人口模式配置才执行job
		if (!GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			long startTime = System.currentTimeMillis();
			logger.error("流动人口中的育龄妇女的JOB开始执行");
			JobHelper.createMockAdminSession();
			int successCount = 0;
			ThreadVariable.setUser(permissionService
					.findUserByUserName("admin"));
			Long id = jobMonitorService
					.addJobMonitor(this.getClass().getName());

			try {
				
				List<Organization> cityOrgs = shardConversion.getAllShardCodeOrganization();
				for (Organization org : cityOrgs) {
					List<FloatingPopulation> floatingPopulationList = null;
					do {
						if (workTimeBreak()) {
							break;
						}
						floatingPopulationList = floatingPopulationService
								.findFloatingPopulationsWhenIsNurtuesWomen(
										pageSize, org);
						if (floatingPopulationList != null
								&& floatingPopulationList.size() > 0) {

							for (FloatingPopulation population : floatingPopulationList) {
								try {
									population
											.setHouseId(housePopulationMaintanceService
													.getPopulationLivingHouseId(
															PopulationCatalog
																	.parse(BaseInfoTables.HOUSEHOLDSTAFF_KEY),
															population.getId()));
									nurturesWomenService
											.addNurturesWomenForJob(
													population,
													ConstantsProduct.SourcesState.JOB);
									successCount++;
								} catch (Exception e) {
									logger.error(
											"该身份证" + population.getIdCardNo()
													+ "在户籍人口job执行错误：", e);
								}
							}
						}
					} while (floatingPopulationList != null
							&& !floatingPopulationList.isEmpty());
				}
				
				jobMonitorService.updateJobMonitor(id,
						"花了" + (System.currentTimeMillis() - startTime)
								+ "执行流动人口中的育龄妇女job！处理人数:" + successCount, true);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("流动人口中的育龄妇女job执行错误：", e);
				jobMonitorService.updateJobMonitor(id,
						"流动人口中的育龄妇女job执行错误:" + e.getMessage(), false);
			}
		}

	}

	private boolean workTimeBreak() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 7);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Calendar endcalendar = Calendar.getInstance();
		endcalendar.set(Calendar.HOUR_OF_DAY, 21);
		endcalendar.set(Calendar.MINUTE, 0);
		endcalendar.set(Calendar.SECOND, 0);
		if (System.currentTimeMillis() > calendar.getTimeInMillis()
				&& System.currentTimeMillis() < endcalendar.getTimeInMillis()
				&& workTimeBreak) {
			return true;
		}

		return true;
	}

	// 实有人口中的育龄妇女
	public void updateNurturesWomenHouseholdStaffLogOut() {
		// logger.error("HouseholdStaffJob开始执行");
		// 只有第二种和第三种人口模式配置才执行job
		if (!GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			long startTime = System.currentTimeMillis();
			logger.info("户籍人口的育龄妇女job开始执行！");
			JobHelper.createMockAdminSession();
			int successCount = 0;
			ThreadVariable.setUser(permissionService
					.findUserByUserName("admin"));
			Long id = jobMonitorService
					.addJobMonitor(this.getClass().getName());

			try {
				List<String> shardCodeList = shardConversion.getAllShardCode();
				for (String shardCode : shardCodeList) {
					List<HouseholdStaff> householdStaffList = null;
					do {
						if (workTimeBreak()) {
							break;
						}
						householdStaffList = householdStaffService
								.findhouseholdStaffWhenIsNurturesWomen(
										pageSize, shardCode);
						if (householdStaffList != null
								&& householdStaffList.size() > 0) {

							for (HouseholdStaff population : householdStaffList) {
								try {
									population
											.setHouseId(housePopulationMaintanceService
													.getPopulationLivingHouseId(
															PopulationCatalog
																	.parse(BaseInfoTables.HOUSEHOLDSTAFF_KEY),
															population.getId()));
									nurturesWomenService
											.addNurturesWomenForJob(
													population,
													ConstantsProduct.SourcesState.JOB);
									successCount++;
								} catch (Exception e) {
									logger.error(
											"该身份证" + population.getIdCardNo()
													+ "在户籍人口job执行错误：", e);
								}
							}
						}
					} while (householdStaffList != null
							&& !householdStaffList.isEmpty());
				}
				jobMonitorService.updateJobMonitor(id,
						"花了" + (System.currentTimeMillis() - startTime)
								+ "执行户籍人口中的育龄妇女job！处理人数:" + successCount, true);
			} catch (Exception e) {
				logger.error("户籍人口的育龄妇女job执行错误：", e);
				jobMonitorService.updateJobMonitor(id,
						"户籍人口的育龄妇女job执行错误:" + e.getMessage(), false);
			}
		}
	}

}
