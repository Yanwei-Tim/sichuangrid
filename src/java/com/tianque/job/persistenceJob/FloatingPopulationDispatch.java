package com.tianque.job.persistenceJob;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.baseInfo.elderlyPeople.service.ElderlyPeopleService;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.job.JobHelper;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.PropertyUtil;

/**
 * @Description:流口转老年人job
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 下午04:46:43
 */
@Component("floatingPopulationDispatch")
public class FloatingPopulationDispatch implements Serializable {
	private static Logger logger = LoggerFactory
			.getLogger(FloatingPopulationDispatch.class);
	@Autowired
	private FloatingPopulationService floatingPopulationService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private ElderlyPeopleService elderlyPeopleService;
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
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private ShardConversion shardConversion;
	private final static int pageSize = GridProperties.POPULATIONJOB_PAGESIZE;
	private final static int maxPage = GridProperties.DATAMANAGEDBJOB_MAXPAGE;
	private final static Boolean workTimeBreak = GridProperties.WORKTIMEBREAK;

	public void updateFloatingPopulationLogOut() {
		// 只有第二种和第三种人口模式配置才执行job
		logger.error("FloatingPopulationJob开始执行");
		ExcelImportHelper.isImport.set(true);
		if (!GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			long startTime = System.currentTimeMillis();
			JobHelper.createMockAdminSession();
			ThreadVariable.setUser(permissionService
					.findUserByUserName("admin"));
			changeElderlyPeople();
			Long endFloatingPopulationId = jobMonitorService.addJobMonitor(this
					.getClass().getName());
			startTime = System.currentTimeMillis();
			try {
				endFloatingPopulation();
				jobMonitorService.updateJobMonitor(endFloatingPopulationId,
						"花了" + (System.currentTimeMillis() - startTime)
								+ "执行流动人口job的提醒到期流动人口！", true);
			} catch (Exception e) {
				logger.error("流动人口job的提醒到期流动人口执行错误：", e);
				jobMonitorService.updateJobMonitor(endFloatingPopulationId,
						"流动人口job的提醒到期流动人口执行错误:" + e.getMessage(), false);
			}

		}
	}

	// 到期的流动人口
	public void endFloatingPopulation() {
		Integer count = floatingPopulationService
				.countFloatingPopulationByExpectedDatedue();
		int pages = count / pageSize;
		for (int page = 0; page <= pages && page < maxPage; page++) {
			// 获得到期的流动人口
			List<FloatingPopulation> floatingPopulationList = floatingPopulationService
					.findFloatingPopulationByExpectedDatedue(0, pageSize);
			List<FloatingPopulation> floatingPopulationSuccessList = new ArrayList<FloatingPopulation>();
			if (floatingPopulationList != null
					&& floatingPopulationList.size() > 0) {
				Set<Long> groups = new HashSet<Long>();
				for (FloatingPopulation population : floatingPopulationList) {
					floatingPopulationService.updateIsRemindByid(population
							.getId());
					population
							.setGender(propertyDictService
									.getPropertyDictById(population.getGender()
											.getId()));
					population.setOrganization(organizationDubboService
							.getFullOrgById(population.getOrganization()
									.getId()));
					population.setActualtype("流动人口");
					// 把到期的流动人口按照所属网格id分组
					groups.add(population.getOrganization().getId());
					floatingPopulationSuccessList.add(population);
				}

				List<ActualPopulation> dataList = new ArrayList<ActualPopulation>();
				for (Long orgId : groups) {
					dataList.clear();
					for (FloatingPopulation population : floatingPopulationSuccessList) {
						if (orgId.equals(population.getOrganization().getId())) {
							dataList.add(population);
						}
					}
					sendEndFloatingPopulation(orgId, dataList);
				}
			}
		}
	}

	private boolean workTimeBreak() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 7);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		if (System.currentTimeMillis() > calendar.getTimeInMillis()
				&& workTimeBreak) {
			return true;
		}
		return false;
	}

	// 变为老年人的流动
	public void changeElderlyPeople() {
		long startTime = System.currentTimeMillis();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			int successCount = 0;
			List<Organization> cityOrgs = shardConversion
					.getAllShardCodeOrganization();
			for (Organization org : cityOrgs) {
				List<FloatingPopulation> floatingPopulationfList = null;
				do {
					if (workTimeBreak()) {
						break;
					}
					floatingPopulationfList = floatingPopulationService
							.findFloatingPopulationsWhenIsOld(pageSize, org);
					if (floatingPopulationfList != null
							&& floatingPopulationfList.size() > 0) {
						for (FloatingPopulation population : floatingPopulationfList) {
							try {
								population
										.setHouseId(housePopulationMaintanceService.getPopulationLivingHouseId(
												PopulationCatalog
														.parse(BaseInfoTables.FLOATINGPOPULATION_KEY),
												population.getId()));
								elderlyPeopleService.addElderlyPeopleForJob(
										population,
										ConstantsProduct.SourcesState.JOB);
								successCount++;
							} catch (Exception e) {
								logger.error("该身份证" + population.getIdCardNo()
										+ "在户籍人口job执行错误：", e);
							}
						}
					}
				} while (floatingPopulationfList != null
						&& !floatingPopulationfList.isEmpty());
			}
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行流动人口job的自动新增大于60岁的人！成功人数：" + successCount,
					true);
		} catch (Exception e) {
			logger.error("流动人口job的自动新增大于60岁的人执行错误：", e);
			jobMonitorService.updateJobMonitor(id, "流动人口job的自动新增大于60岁的人执行错误:"
					+ e.getMessage(), false);
		}

	}

	private ElderlyPeople convertFloatingPopulationToElderlyPeople(
			FloatingPopulation population) throws Exception {
		ElderlyPeople elderlyPeople = new ElderlyPeople();
		PropertyUtil.copyPropertiesWithRecursion(Countrymen.class,
				elderlyPeople, population, new String[] { "id" });
		elderlyPeople.setSourcesState(ConstantsProduct.SourcesState.JOB);
		return elderlyPeople;
	}

	private void sendMessage(Long orgId, List<ActualPopulation> list) {

		PlatformMessage pm = platformMessageFactory
				.createElderlyPeoplePlatformMessage(list, "流动人口");

		platformMessageService.sendPlatformMessageToOrg(orgId, pm);

	}

	private void sendEndFloatingPopulation(Long orgId,
			List<ActualPopulation> list) {
		PlatformMessage pm = platformMessageFactory
				.createEndFloatingPopulationPlatformMessage(list, "流动人口");
		platformMessageService.sendPlatformMessageToOrg(orgId, pm);
	}

}
