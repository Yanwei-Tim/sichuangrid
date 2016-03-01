package com.tianque.baseInfo.base.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType;
import com.tianque.baseInfo.base.constant.OperateType;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.DuplicatePeople;
import com.tianque.baseInfo.base.util.PeopleDuplicateRemovalHelper;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.PopulationProccessorService;
import com.tianque.service.util.PopulationType;

/**
 * @Description:@Description:删除人口重复数据业务实现类 
 *                                         【包含baseinfo、householdstaffs、floatingpopulations和相关的业务表
 *                                         】
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-12 下午03:39:08
 */
@Service("peopleDuplicateRemovalService")
public class PeopleDuplicateRemovalServiceImpl implements
		PeopleDuplicateRemovalService {
	private final static Logger logger = LoggerFactory
			.getLogger(PeopleDuplicateRemovalServiceImpl.class);
	@Autowired
	private PoepleDuplicateRemovalLogService poepleDuplicateRemovalLogService;
	@Autowired
	private BaseInfoService baseInfoService;
	@Autowired
	private Map<String, PopulationProccessorService> proccessors;
	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	private FloatingPopulationService floatingPopulationService;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<DuplicatePeople> getAllBaseInfoDuplicatePeople() {
		try {
			return baseInfoService.getAllBaseInfoDuplicatePeople();
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PeopleDuplicateRemovalServiceImpl类的getAllBaseInfoDuplicatePeople方法错误：",
					"获取基础表的所有重复数据错误", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Integer getBaseInfoPopulationDuplicateTotal() {
		try {
			Integer countNum = poepleDuplicateRemovalLogService
					.getBaseIbfoPopulationDuplicateTotal();
			return countNum == null ? 0 : countNum;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PeopleDuplicateRemovalServiceImpl类的getBaseInfoPopulationDuplicateTotal方法错误：",
					"获取基础表的重复数据的总数错误", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void chanageAllPeopleBaseInfoIdByBaseInfo(
			List<DuplicatePeople> duplicatePeopleBaseInfos) {
		try {
			if (duplicatePeopleBaseInfos == null
					|| duplicatePeopleBaseInfos.size() < 1) {
				return;
			}

			/** baseInfo的标准 */
			DuplicatePeople result = PeopleDuplicateRemovalHelper
					.decideBusinessPopulationDuplicate(duplicatePeopleBaseInfos);
			if (result.getBaseInfoId() != null) {
				duplicatePeopleBaseInfos.remove(result);
				/** 修改业务信息的baseInfoId */
				changeBusinessPopulationBaseInfoId(duplicatePeopleBaseInfos,
						result);

				/** 修改流动的baseInfoId */
				changeActualPopulationBaseInfoId(duplicatePeopleBaseInfos,
						result, PopulationType.HOUSEHOLD_STAFF);
				/** 修改户籍的baseInfoId */
				changeActualPopulationBaseInfoId(duplicatePeopleBaseInfos,
						result, PopulationType.FLOATING_POPULATION);
				/** 删除baseInfo表的重复数据 */
				deleteBaseInfoDuplicate(duplicatePeopleBaseInfos);

			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PeopleDuplicateRemovalServiceImpl类的chanageAllPeopleBaseInfoIdByBaseInfo方法错误：",
					"根据一条重复的基础表去修改实口表错误", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Integer getAlonePopulationDuplicateTotal(String tableName) {
		try {
			Integer countNum = poepleDuplicateRemovalLogService
					.getAlonePopulationDuplicateTotal(tableName);
			return countNum == null ? 0 : countNum;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PeopleDuplicateRemovalServiceImpl类的getAlonePopulationDuplicateTotal方法错误：",
					"根据表名统计该表的重复数据的总数错误", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void removalBusinessPopulationDuplicateByPaging(int startRow,
			int endRow, String tableName) {
		if (StringUtils.isBlank(tableName)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			/** 各个表分组排序的重复数据分页处理 */
			List<DuplicatePeople> duplicatePeopleForGroupsByPaging = poepleDuplicateRemovalLogService
					.queryDuplicatePeopleByGroupByPagingForList(tableName,
							startRow, endRow);
			/** 分页后的数据进行去重 */
			removalBusinessPopulationDuplicateByOrgIdAndBaseInfoId(
					duplicatePeopleForGroupsByPaging,
					proccessors
							.get(BusinessPopulationTableAndServiceImplModeType.BUSINESS_POPULATION_SERVICES
									.get(tableName)));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PeopleDuplicateRemovalServiceImpl类的removalBusinessPopulationDuplicateByPaging方法错误：",
					"根据分页后去除业务重复数据错误", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void removalHouseholdStaffAndFloatingPopulationByPaging(
			int startRow, int endRow, String tableName) {
		if (StringUtils.isBlank(tableName)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			/** 各个表分组排序的重复数据分页处理 */
			List<DuplicatePeople> duplicatePeopleForGroupsByPaging = poepleDuplicateRemovalLogService
					.queryDuplicatePeopleByGroupByPagingForList(
							tableName + "s", 0, endRow);
			/** 分页后的数据进行去重 */
			removalActualPopulationsDuplicate(duplicatePeopleForGroupsByPaging,
					tableName);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PeopleDuplicateRemovalServiceImpl类的removalHouseholdStaffAndFloatingPopulationByPaging方法错误：",
					"根据分页后去除户籍、流动重复数据错误", e);
		}

	}

	/**
	 * 根据baseInfo的重复数据去去重户籍、流口的重复数据（返回户籍、流口人员的标准）
	 * 
	 * @param duplicatePeopleBaseInfos
	 * @return 户籍、流口人员的标准
	 */
	private void removalActualPopulationsDuplicate(
			List<DuplicatePeople> duplicatePeopleBaseInfos, String tableName) {
		/** 根据baseid所有的重复的数据的 */
		/** 不需要考虑是否是不同网格的（就算是不同网格的对应是关联baseId的，和网格无关） */
		for (DuplicatePeople duplicatePeople : duplicatePeopleBaseInfos) {
			/** 同网格 同baseid有重复的数据 orgId baseifoId */
			List<DuplicatePeople> actualPopulations = poepleDuplicateRemovalLogService
					.getDuplicatePeople(duplicatePeople.getBaseInfoId(),
							tableName + "s");

			/** 根据orgid和baseId去删除同一网格重复的数据 */
			removalActualPopulationDuplicateByOrgIdAndBaseInfoId(
					actualPopulations, tableName);
		}
	}

	/**
	 * 去除同一网格的户籍人口的重复数据
	 * 
	 * @param businessPopulation
	 * @return
	 */
	private void removalActualPopulationDuplicateByOrgIdAndBaseInfoId(
			List<DuplicatePeople> duplicatePeoples, String tableName) {

		if (duplicatePeoples != null && duplicatePeoples.size() > 0) {
			for (DuplicatePeople duplicatePeople : duplicatePeoples) {
				List<DuplicatePeople> actualPopulations = poepleDuplicateRemovalLogService
						.getActualPopulationByOrgIdAndBaseInfoId(
								duplicatePeople.getBaseInfoId(),
								duplicatePeople.getOrganization().getId(),
								duplicatePeople.getTableName());
				/** 由于在删除的时候会删除业务信息，所以要考虑业务信息 */
				DuplicatePeople result = decideActualPopulationDuplicate(
						actualPopulations, tableName);
				if (result != null) {
					actualPopulations.remove(result);
					/** 删除户籍、流动人员多余的信息 */
					deleteActualPopulationDuplicate(actualPopulations,
							tableName);

				}
			}
		}
	}

	/**
	 * 判断同网格同baseInfoId的数据的去重的标准
	 * 
	 * @param actualPopulations
	 */
	private DuplicatePeople decideActualPopulationDuplicate(
			List<DuplicatePeople> actualPopulations, String tableName) {
		DuplicatePeople result = null;
		if (actualPopulations != null && actualPopulations.size() > 0) {
			int resultNum = 0;
			for (DuplicatePeople duplicatePeople : actualPopulations) {
				int countBusiness = poepleDuplicateRemovalLogService
						.getActualPopulationHasBusinessTotal(
								duplicatePeople.getId(),
								duplicatePeople.getTableName(), tableName);
				if (countBusiness > resultNum) {
					result = duplicatePeople;
					resultNum = countBusiness;
				}
			}
		}

		if (result == null) {
			result = PeopleDuplicateRemovalHelper
					.decideBusinessPopulationDuplicate(actualPopulations);
		}
		return result;
	}

	/**
	 * 根据orgid和baseId去删除同一网格重复的数据 (单纯的处理重复数据 baseId是不变的)
	 * 
	 * 
	 * @param businessPopulation
	 * @return主要返回对应数据的orgId和baseid(重复数据的标准数据)
	 */
	private void removalBusinessPopulationDuplicateByOrgIdAndBaseInfoId(
			List<DuplicatePeople> businessPopulation,
			PopulationProccessorService proccessor) {
		if (businessPopulation != null && businessPopulation.size() > 0) {
			for (DuplicatePeople duplicatePeople : businessPopulation) {
				/** 根据Org和baseInfoId查询出的数据 */
				List<DuplicatePeople> businessPopulations = poepleDuplicateRemovalLogService
						.getBusinessPopulationByOrgIdAndBaseInfoId(
								duplicatePeople.getBaseInfoId(),
								duplicatePeople.getOrganization().getId(),
								duplicatePeople.getTableName());
				DuplicatePeople result = PeopleDuplicateRemovalHelper
						.decideBusinessPopulationDuplicate(businessPopulations);

				if (result != null) {
					businessPopulations.remove(result);
					/** 删除业务人员多余的信息 */
					deleteBusinessPopulationDuplicate(businessPopulations,
							proccessor);
				}
			}
		}
	}

	/**
	 * 修改业务表的baseInfoId
	 * 
	 * @param duplicatePeopleBaseInfos
	 * 
	 * @param result
	 */
	private void changeBusinessPopulationBaseInfoId(
			List<DuplicatePeople> duplicatePeopleBaseInfos,
			DuplicatePeople result) {
		if (duplicatePeopleBaseInfos != null
				&& duplicatePeopleBaseInfos.size() > 0) {
			long startTime = 0;
			for (String tableName : BusinessPopulationTableAndServiceImplModeType.BUSINESS_POPULATION_TABLES) {
				/** 根据实现类去获取对应的业务数据所对应的表名称 */
				startTime = System.currentTimeMillis();
				changePopulationBaseInfoId(duplicatePeopleBaseInfos, tableName,
						result);
				logger.error("花了" + (System.currentTimeMillis() - startTime)
						+ "去修改" + tableName + "表的身份证号码为："
						+ result.getIdCardNo() + "的数据的baseInfoId为"
						+ result.getBaseInfoId());
			}
		}
	}

	/**
	 * 修改户籍和流动的baseInfoId
	 * 
	 * @param duplicatePeopleBaseInfos
	 * @param result
	 */
	private void changeActualPopulationBaseInfoId(
			List<DuplicatePeople> duplicatePeopleBaseInfos,
			DuplicatePeople result, String tableName) {
		if (duplicatePeopleBaseInfos != null
				&& duplicatePeopleBaseInfos.size() > 0) {
			long startTime = System.currentTimeMillis();

			changePopulationBaseInfoId(duplicatePeopleBaseInfos, tableName
					+ "s", result);
			logger.error("花了" + (System.currentTimeMillis() - startTime)
					+ "去修改" + tableName + "表的身份证号码为：" + result.getIdCardNo()
					+ "的数据的baseInfoId为" + result.getBaseInfoId());
		}
	}

	/**
	 * 修改对应表的baseinfoid为标准的baseinfoid
	 * 
	 * @param duplicatePeopleBaseInfos
	 *            要修改的baseinfoid的集合
	 * @param tableName
	 *            要修改的表
	 * @param duplicatePeople
	 *            修改为的baseInfoid
	 */
	private void changePopulationBaseInfoId(
			List<DuplicatePeople> duplicatePeopleBaseInfos, String tableName,
			DuplicatePeople newDuplicatePeople) {
		int count = 0;
		for (DuplicatePeople duplicatePeople : duplicatePeopleBaseInfos) {
			recordChangedBaseInfoIdDuplicatePeople(
					duplicatePeople.getBaseInfoId(), tableName);
			count = poepleDuplicateRemovalLogService
					.updateBaseInfoIdByTableNameAndOldBaseInfoId(
							duplicatePeople.getBaseInfoId(), tableName,
							newDuplicatePeople.getBaseInfoId());
			logger.error("身份证" + newDuplicatePeople.getIdCardNo() + "在"
					+ tableName + "表对应baseInfoId为"
					+ duplicatePeople.getBaseInfoId() + "的总数为：" + count);
		}

	}

	/**
	 * 记录日志被修改的baseInfoId的数据
	 * 
	 * @param baseInfoId
	 * @param tableName
	 */

	private void recordChangedBaseInfoIdDuplicatePeople(Long baseInfoId,
			String tableName) {
		if (baseInfoId == null || StringUtils.isBlank(tableName)) {
			throw new BusinessValidationException(
					"记录日志被修改的baseInfoId的数据错误原因：参数错误");
		}
		List<DuplicatePeople> DuplicatePeopleForLogs = poepleDuplicateRemovalLogService
				.queryDuplicatePeopleToLogForList(baseInfoId, tableName);
		for (DuplicatePeople duplicatePeople : DuplicatePeopleForLogs) {
			poepleDuplicateRemovalLogService
					.addPoepleDuplicateRemovalLog(PeopleDuplicateRemovalHelper
							.fillPoepleDuplicateRemovalLogByDuplicatePeopleAndOperateType(
									duplicatePeople, OperateType.update.name()));
		}
	}

	/**
	 * 删除多余的重复数据（业务人员）
	 * 
	 * @param businessPopulations
	 */
	private void deleteBusinessPopulationDuplicate(
			List<DuplicatePeople> businessPopulations,
			PopulationProccessorService proccessor) {
		if (businessPopulations != null && businessPopulations.size() > 0) {
			for (DuplicatePeople duplicatePeople : businessPopulations) {
				poepleDuplicateRemovalLogService
						.addPoepleDuplicateRemovalLog(PeopleDuplicateRemovalHelper
								.fillPoepleDuplicateRemovalLogByDuplicatePeopleAndOperateType(
										duplicatePeople,
										OperateType.delete.name()));
				proccessor.deleteBusinessPopulationDuplicate(duplicatePeople
						.getId());
			}
		}

	}

	/**
	 * 删除多余的重复数据(户籍、流口)
	 * 
	 * @param actualPopulations
	 */
	private void deleteActualPopulationDuplicate(
			List<DuplicatePeople> actualPopulations, String tableName) {
		if (actualPopulations != null && actualPopulations.size() > 0) {
			for (DuplicatePeople duplicatePeople : actualPopulations) {
				poepleDuplicateRemovalLogService
						.addPoepleDuplicateRemovalLog(PeopleDuplicateRemovalHelper
								.fillPoepleDuplicateRemovalLogByDuplicatePeopleAndOperateType(
										duplicatePeople,
										OperateType.delete.name()));
				if (PopulationType.HOUSEHOLD_STAFF.equals(tableName)) {
					householdStaffService
							.deleteHouseholdStaffById(duplicatePeople.getId());
				} else if (PopulationType.FLOATING_POPULATION.equals(tableName)) {
					floatingPopulationService
							.deleteFloatingPopulationById(duplicatePeople
									.getId());
				}
			}
		}
	}

	/**
	 * 删除baseInfo的多余的重复数据
	 * 
	 * @param duplicatePeopleBaseInfos
	 */
	private void deleteBaseInfoDuplicate(
			List<DuplicatePeople> duplicatePeopleBaseInfos) {
		if (duplicatePeopleBaseInfos != null
				&& duplicatePeopleBaseInfos.size() > 0) {
			Countrymen countrymen = null;
			for (DuplicatePeople duplicatePeople : duplicatePeopleBaseInfos) {
				countrymen = baseInfoService.getBaseInfoById(duplicatePeople
						.getBaseInfoId());
				duplicatePeople.setCountrymen(countrymen);
				poepleDuplicateRemovalLogService
						.addPoepleDuplicateRemovalLog(PeopleDuplicateRemovalHelper
								.fillPoepleDuplicateRemovalLogByDuplicatePeopleAndOperateType(
										duplicatePeople,
										OperateType.delete.name()));
				baseInfoService.delete(duplicatePeople.getBaseInfoId());
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<DuplicatePeople> getAllBusinessPopulationDuplicatePeopleByPaging(
			int startRow, int endRow, String tableName) {
		if (StringUtils.isBlank(tableName)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return poepleDuplicateRemovalLogService
					.getAllBusinessPopulationDuplicatePeopleByPaging(startRow,
							endRow, tableName);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PeopleDuplicateRemovalServiceImpl类的getAllBusinessPopulationDuplicatePeopleByPaging方法错误：",
					"根据分页后查询业务重复数据错误", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<DuplicatePeople> getAllHouseholdStaffAndFloatingDuplicatePeopleByPaging(
			int startRow, int endRow, String tableName) {
		if (StringUtils.isBlank(tableName)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return poepleDuplicateRemovalLogService
					.getAllHouseholdStaffAndFloatingDuplicatePeopleByPaging(
							startRow, endRow, tableName);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PeopleDuplicateRemovalServiceImpl类的getAllHouseholdStaffAndFloatingDuplicatePeopleByPaging方法错误：",
					"根据分页后查询户籍、流动重复数据错误", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void removalHouseholdStaffAndFloatingPopulationByPagingByNew(
			List<DuplicatePeople> list, String tableName) {
		if (StringUtils.isBlank(tableName)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			/** 分页后的数据进行去重 */
			removalActualPopulationsDuplicate(list, tableName);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PeopleDuplicateRemovalServiceImpl类的removalHouseholdStaffAndFloatingPopulationByPagingByNew方法错误：",
					"分页后的户籍、流动数据进行去重错误错误", e);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void removalBusinessPopulationDuplicateByPagingByNew(
			List<DuplicatePeople> list, String tableName) {
		if (StringUtils.isBlank(tableName)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			/** 分页后的数据进行去重 */
			removalBusinessPopulationDuplicateByOrgIdAndBaseInfoId(
					list,
					proccessors
							.get(BusinessPopulationTableAndServiceImplModeType.BUSINESS_POPULATION_SERVICES
									.get(tableName)));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PeopleDuplicateRemovalServiceImpl类的removalBusinessPopulationDuplicateByPagingByNew方法错误：",
					"分页后的业务数据进行去重错误", e);
		}

	}
}
