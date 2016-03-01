package com.tianque.baseInfo.base.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType;
import com.tianque.baseInfo.base.domain.DuplicatePeople;
import com.tianque.baseInfo.base.util.PeopleDuplicateRemovalHelper;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.JobMonitorService;

/**
 * @Description:删除人口重复数据帮助service实现类
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-13 下午10:54:05
 */
@Service("peopleDuplicateRemovalHelperService")
public class PeopleDuplicateRemovalHelperServiceImpl implements
		PeopleDuplicateRemovalHelperService {
	private final static Logger logger = LoggerFactory
			.getLogger(PeopleDuplicateRemovalServiceImpl.class);

	@Autowired
	private PeopleDuplicateRemovalService peopleDuplicateRemovalService;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Override
	public void removalDuplicatePeople() {
		try {
			logger.error("人口去重开始");
			Long id = jobMonitorService.addJobMonitor("所有人口类型去重");
			long startTime = System.currentTimeMillis();
			/** 基础数据表有重复的数据 */
			logger.error("修改各个表的baseinfoid为统一的baseinfoId开始");
			chanageAllPeopleBaseInfoIdByBaseInfo();
			/** 此时baseinfo表里面没有重复的数据 */
			/** 去重各个表的数据 */
			logger.error("去重各个表数据开始");
			removalBaseInfoNotDuplicate();
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行所有人口类型去重", true);
			logger.error("花了" + (System.currentTimeMillis() - startTime)
					+ "执行人口去重");
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PeopleDuplicateRemovalServiceImpl类的removalDuplicatePeople方法错误：",
					"人口去重错误", e);
		}
	}

	@Override
	public void removalBusinessPopulationDuplicateByTableName(String tableName) {
		/** 分别处理各个业务人员的自己的重复数据不需要考虑其他网格的数据 */
		int pageSize = BusinessPopulationTableAndServiceImplModeType.PAGE_SIZE;
		int pages = 0;
		// job监控页面
		Long id = jobMonitorService.addJobMonitor(tableName + "人口类型去重");
		Long duplicateStartTime = System.currentTimeMillis();

		Integer countNum = peopleDuplicateRemovalService
				.getAlonePopulationDuplicateTotal(tableName);
		logger.error("删除各个表的重复数据，当前表为" + tableName + "当前表的重复数据总数是" + countNum
				+ "个");
		pages = countNum / pageSize;
		for (int page = 0; page <= pages; page++) {
			logger.error("删除各个表的重复数据，当前表为" + tableName + "当前表的重复数据总数是"
					+ countNum + "个正在处理第" + page * pageSize + "到" + (page + 1)
					* pageSize + "条");
			List<DuplicatePeople> allBusinessPopulationDuplicate = peopleDuplicateRemovalService
					.getAllBusinessPopulationDuplicatePeopleByPaging(0,
							pageSize, tableName);
			List<List<DuplicatePeople>> allBusinessPopulationDuplicateByGroup = PeopleDuplicateRemovalHelper
					.getAllActualPopulationDuplicatePeople(allBusinessPopulationDuplicate);
			long startTime = 0;
			startTime = System.currentTimeMillis();
			logger.error("本次去重" + tableName + "表的重复身份证号的总个数为"
					+ allBusinessPopulationDuplicateByGroup.size() + "个当前处理第"
					+ page * pageSize + "到" + (page + 1) * pageSize + "个");
			for (int i = 0; i < allBusinessPopulationDuplicateByGroup.size(); i++) {

				peopleDuplicateRemovalService
						.removalBusinessPopulationDuplicateByPagingByNew(
								allBusinessPopulationDuplicateByGroup.get(i),
								tableName);
			}
			logger.error("花了" + (System.currentTimeMillis() - startTime) + "去重"
					+ tableName + "第" + page * pageSize + "到" + (page + 1)
					* pageSize + "个重复数据");

		}

		jobMonitorService.updateJobMonitor(id,
				"花了" + (System.currentTimeMillis() - duplicateStartTime) + "执行"
						+ tableName + "人口类型去重", true);
	}

	/**
	 * 去重各个表的重复数据（先从业务数据开始）
	 */
	private void removalBaseInfoNotDuplicate() {
		/** 去重业务表的重复数据 */
		logger.error("去重各个业务表数据开始");
		long startTime = System.currentTimeMillis();
		removalBusinessPopulationDuplicate();
		logger.error("花了" + (System.currentTimeMillis() - startTime) + "去重业务人员");
		/** 去重户籍和流动的重复数据 */
		logger.error("去重户籍和流动数据开始");
		removalHouseholdStaffAndFloatingPopulationDuplicate();
	}

	/**
	 * 去重户籍和流口的重复数据
	 */
	private void removalHouseholdStaffAndFloatingPopulationDuplicate() {
		/** 户籍 */
		logger.error("去重户籍人员表数据开始");
		long startTime = System.currentTimeMillis();
		removalAloneActualPopulationDuplicate(PopulationType.HOUSEHOLD_STAFF);
		logger.error("花了" + (System.currentTimeMillis() - startTime) + "去重户籍人员");
		/** 流动 */
		logger.error("去重流动人员表数据开始");
		startTime = System.currentTimeMillis();
		removalAloneActualPopulationDuplicate(PopulationType.FLOATING_POPULATION);
		logger.error("花了" + (System.currentTimeMillis() - startTime) + "去重流动人员");
	}

	/**
	 * 分页分组去去重户籍和流动自己的重复数据（不考虑baseInfo）
	 * 
	 * @param tableName
	 */
	private void removalAloneActualPopulationDuplicate(String tableName) {
		Integer countNum = peopleDuplicateRemovalService
				.getAlonePopulationDuplicateTotal(tableName + "s");
		int pageSize = BusinessPopulationTableAndServiceImplModeType.PAGE_SIZE;
		int pages = countNum / pageSize;
		logger.error("删除各个表的重复数据，当前表为" + tableName + "当前表的重复数据总数是" + countNum
				+ "个");
		long startTime = 0;
		for (int page = 0; page <= pages; page++) {
			logger.error("删除各个表的重复数据，当前表为" + tableName + "当前表的重复数据总数是"
					+ countNum + "个正在处理第" + page * pageSize + "到" + (page + 1)
					* pageSize + "条");
			List<DuplicatePeople> allHouseholdStaffAndFloating = peopleDuplicateRemovalService
					.getAllHouseholdStaffAndFloatingDuplicatePeopleByPaging(0,
							pageSize, tableName + "s");
			List<List<DuplicatePeople>> allHouseholdStaffAndFloatingByGroup = PeopleDuplicateRemovalHelper
					.getAllActualPopulationDuplicatePeople(allHouseholdStaffAndFloating);
			startTime = System.currentTimeMillis();
			logger.error("本次去重" + tableName + "表的重复身份证号的总个数为"
					+ allHouseholdStaffAndFloatingByGroup.size() + "个当前处理第"
					+ page * pageSize + "到" + (page + 1) * pageSize);
			for (int i = 0; i < allHouseholdStaffAndFloatingByGroup.size(); i++) {

				peopleDuplicateRemovalService
						.removalHouseholdStaffAndFloatingPopulationByPagingByNew(
								allHouseholdStaffAndFloatingByGroup.get(i),
								tableName);
			}
			logger.error("花了" + (System.currentTimeMillis() - startTime) + "去重"
					+ tableName + "第" + page * pageSize + "到" + (page + 1)
					* pageSize + "个重复数据");
		}
	}

	/**
	 * 去重业务数据的重复数据
	 */
	private void removalBusinessPopulationDuplicate() {
		/** 分别处理各个业务人员的自己的重复数据不需要考虑其他网格的数据 */
		int pageSize = BusinessPopulationTableAndServiceImplModeType.PAGE_SIZE;
		int pages = 0;
		for (String tableName : BusinessPopulationTableAndServiceImplModeType.BUSINESS_POPULATION_TABLES) {
			Integer countNum = peopleDuplicateRemovalService
					.getAlonePopulationDuplicateTotal(tableName);
			logger.error("删除各个表的重复数据，当前表为" + tableName + "当前表的重复数据总数是"
					+ countNum + "个");
			pages = countNum / pageSize;
			for (int page = 0; page <= pages; page++) {
				logger.error("删除各个表的重复数据，当前表为" + tableName + "当前表的重复数据总数是"
						+ countNum + "个正在处理第" + page * pageSize + "到"
						+ (page + 1) * pageSize + "条");
				List<DuplicatePeople> allBusinessPopulationDuplicate = peopleDuplicateRemovalService
						.getAllBusinessPopulationDuplicatePeopleByPaging(0,
								pageSize, tableName);
				List<List<DuplicatePeople>> allBusinessPopulationDuplicateByGroup = PeopleDuplicateRemovalHelper
						.getAllActualPopulationDuplicatePeople(allBusinessPopulationDuplicate);
				long startTime = 0;
				startTime = System.currentTimeMillis();
				logger.error("本次去重" + tableName + "表的重复身份证号的总个数为"
						+ allBusinessPopulationDuplicateByGroup.size()
						+ "个当前处理第" + page * pageSize + "到" + (page + 1)
						* pageSize + "个");
				for (int i = 0; i < allBusinessPopulationDuplicateByGroup
						.size(); i++) {

					peopleDuplicateRemovalService
							.removalBusinessPopulationDuplicateByPagingByNew(
									allBusinessPopulationDuplicateByGroup
											.get(i), tableName);
				}
				logger.error("花了" + (System.currentTimeMillis() - startTime)
						+ "去重" + tableName + "第" + page * pageSize + "到"
						+ (page + 1) * pageSize + "个重复数据");
			}
		}
	}

	/**
	 * 根据baseInfo的重复数据去修改所有表的baseInfoId为统一的baseInfoId
	 */
	private void chanageAllPeopleBaseInfoIdByBaseInfo() {
		List<DuplicatePeople> allBaseInfoDuplicate = peopleDuplicateRemovalService
				.getAllBaseInfoDuplicatePeople();

		List<List<DuplicatePeople>> allBaseInfoDuplicateByGroup = PeopleDuplicateRemovalHelper
				.getAllBaseInfoDuplicatePeople(allBaseInfoDuplicate);
		if (allBaseInfoDuplicateByGroup != null
				&& allBaseInfoDuplicateByGroup.size() > 0) {

			logger.error("本次去重baseInfo表的重复身份证号的个数为"
					+ allBaseInfoDuplicateByGroup.size() + "个");
			long startTime = 0;
			for (int i = 0; i < allBaseInfoDuplicateByGroup.size(); i++) {
				startTime = System.currentTimeMillis();
				logger.error("本次去重baseInfo表的重复身份证号的总个数为"
						+ allBaseInfoDuplicateByGroup.size() + "个当前处理第"
						+ (i + 1) + "个");
				peopleDuplicateRemovalService
						.chanageAllPeopleBaseInfoIdByBaseInfo(allBaseInfoDuplicateByGroup
								.get(i));
				logger.error("花了" + (System.currentTimeMillis() - startTime)
						+ "去重baseInfo第" + (i + 1) + "个重复数据");
			}
		}
	}
}
