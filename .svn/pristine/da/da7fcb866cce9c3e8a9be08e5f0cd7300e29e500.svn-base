package com.tianque.baseInfo.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.constant.BusinessPopulationTableAndServiceImplModeType;
import com.tianque.baseInfo.base.dao.PoepleDuplicateRemovalLogDAO;
import com.tianque.baseInfo.base.domain.DuplicatePeople;
import com.tianque.baseInfo.base.domain.PoepleDuplicateRemovalLog;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;

/**
 * @Description:删除重复数据记录日志service实现【只做日志的记录】
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-6 上午09:06:16
 */
@Service("poepleDuplicateRemovalLogService")
@Transactional
public class PoepleDuplicateRemovalLogServiceImpl implements
		PoepleDuplicateRemovalLogService {
	@Autowired
	private PoepleDuplicateRemovalLogDAO poepleDuplicateRemovalLogDAO;

	@Override
	public PoepleDuplicateRemovalLog addPoepleDuplicateRemovalLog(
			PoepleDuplicateRemovalLog poepleDuplicateRemovalLog) {
		if (poepleDuplicateRemovalLog == null
				|| poepleDuplicateRemovalLog.getDataId() == null
				|| poepleDuplicateRemovalLog.getIdCardNo() == null
				|| "".equals(poepleDuplicateRemovalLog.getIdCardNo())
				|| poepleDuplicateRemovalLog.getOperateType() == null
				|| "".equals(poepleDuplicateRemovalLog.getOperateType())) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Long id = poepleDuplicateRemovalLogDAO
					.addPoepleDuplicateRemovalLog(poepleDuplicateRemovalLog);

			return this.getPoepleDuplicateRemovalLogById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的addPoepleDuplicateRemovalLog方法错误：",
					"人口去重添加日志错误", e);
		}
	}

	@Override
	public PoepleDuplicateRemovalLog getPoepleDuplicateRemovalLogById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return poepleDuplicateRemovalLogDAO
					.getPoepleDuplicateRemovalLogById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的getPoepleDuplicateRemovalLogById方法错误：",
					"根据id获取人口去重日志错误", e);
		}
	}

	@Override
	public List<DuplicatePeople> getDuplicatePeople(Long baseInfoId,
			String tableName) {
		if (baseInfoId == null || tableName == null || "".equals(tableName)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("baseInfoId", baseInfoId);
			map.put("tableName", tableName);
			return poepleDuplicateRemovalLogDAO
					.queryDuplicatePeopleBaseInfoForList(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的getDuplicatePeople方法错误：",
					"人口去重根据baseId去查询对应的业务表的的重复数据错误", e);
		}
	}

	@Override
	public List<DuplicatePeople> getBusinessPopulationByOrgIdAndBaseInfoId(
			Long baseInfoId, Long orgId, String tableName) {
		if (baseInfoId == null || orgId == null || tableName == null
				|| "".equals(tableName)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("baseInfoId", baseInfoId);
			map.put("tableName", tableName);
			map.put("orgId", orgId);

			return poepleDuplicateRemovalLogDAO
					.queryDuplicatePeopleAllForList(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的getBusinessPopulationByOrgIdAndBaseInfoId方法错误：",
					"人口去重根据基础的重复数据的标准去获取全部对应网格对应baseId的多余的数据（业务人员）错误", e);
		}
	}

	@Override
	public List<DuplicatePeople> getActualPopulationByOrgIdAndBaseInfoId(
			Long baseInfoId, Long orgId, String tableName) {
		if (baseInfoId == null || orgId == null || tableName == null
				|| "".equals(tableName)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("baseInfoId", baseInfoId);
			map.put("tableName", tableName);
			map.put("orgId", orgId);

			return poepleDuplicateRemovalLogDAO
					.queryDuplicatePeopleAllActualForList(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的getActualPopulationByOrgIdAndBaseInfoId方法错误：",
					"人口去重根据基础的重复数据的标准去获取全部对应网格对应baseId的多余的数据(户籍、流动)错误", e);
		}
	}

	@Override
	public Integer getAlonePopulationDuplicateTotal(String tableName) {
		if (StringUtils.isBlank(tableName)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", tableName);
			return poepleDuplicateRemovalLogDAO
					.getAlonePopulationDuplicateTotal(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的getAlonePopulationDuplicateTotal方法错误：",
					"人口去重查询单张表的同网格同baseInfoId的重复数据的总数错误", e);
		}
	}

	@Override
	public List<DuplicatePeople> queryDuplicatePeopleByGroupByPagingForList(
			String tableName, int startRow, int endRow) {
		if (StringUtils.isBlank(tableName)
				|| endRow <= 0
				|| (endRow - startRow) != BusinessPopulationTableAndServiceImplModeType.PAGE_SIZE) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", tableName);
			map.put("endRow", endRow);
			map.put("startRow", startRow);
			return poepleDuplicateRemovalLogDAO
					.queryDuplicatePeopleByGroupByPagingForList(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的queryDuplicatePeopleByGroupByPagingForList方法错误：",
					"人口去重查询根据分组的重复数据（ordId和baseInfoId）并且分页错误", e);
		}
	}

	@Override
	public Integer getBaseIbfoPopulationDuplicateTotal() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "baseInfo");
		try {
			return poepleDuplicateRemovalLogDAO
					.getBaseIbfoPopulationDuplicateTotal(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的getBaseIbfoPopulationDuplicateTotal方法错误：",
					"人口去重统计出baseInfo表的重复数据有多少条错误", e);
		}
	}

	@Override
	public Integer getActualPopulationHasBusinessTotal(Long actualId,
			String tableName, String actualType) {
		if (StringUtils.isBlank(tableName) || actualId == null
				|| StringUtils.isBlank(actualType)) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", tableName);
			map.put("actualId", actualId);
			map.put("actualType", actualType);
			return poepleDuplicateRemovalLogDAO
					.getActualPopulationHasBusinessTotal(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的getActualPopulationHasBusinessTotal方法错误：",
					"人口去重根据户籍和流动去统计所有业务信息的个数错误", e);
		}
	}

	@Override
	public Integer updateBaseInfoIdByTableNameAndOldBaseInfoId(
			Long oldBaseInfoId, String tableName, Long newBaseInfoId) {
		if (StringUtils.isBlank(tableName) || newBaseInfoId == null
				|| oldBaseInfoId == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", tableName);
			map.put("newBaseInfoId", newBaseInfoId);
			map.put("oldBaseInfoId", oldBaseInfoId);
			return (Integer) poepleDuplicateRemovalLogDAO
					.updateBaseInfoIdByTableNameAndOldBaseInfoId(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的updateBaseInfoIdByTableNameAndOldBaseInfoId方法错误：",
					"人口去重根据表和老的baseInfoId修改表的baseInfoId错误", e);
		}
	}

	@Override
	public List<DuplicatePeople> queryDuplicatePeopleToLogForList(
			Long baseInfoId, String tableName) {
		if (StringUtils.isBlank(tableName) || baseInfoId == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", tableName);
			map.put("baseInfoId", baseInfoId);
			return poepleDuplicateRemovalLogDAO
					.queryDuplicatePeopleToLogForList(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的queryDuplicatePeopleToLogForList方法错误：",
					"人口去重在修改basInfoId之前记录日志是信息（根据baseId查询数据）错误", e);
		}
	}

	@Override
	public List<DuplicatePeople> getAllBusinessPopulationDuplicatePeopleByPaging(
			int startRow, int endRow, String tableName) {
		if (StringUtils.isBlank(tableName)
				|| endRow <= 0
				|| (endRow - startRow) != BusinessPopulationTableAndServiceImplModeType.PAGE_SIZE) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", tableName);
			map.put("endRow", endRow);
			map.put("startRow", startRow);
			return poepleDuplicateRemovalLogDAO
					.queryAllBusinessPopulationDuplicatePeopleByPagingForList(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的queryDuplicatePeopleByGroupByPagingForList方法错误：",
					"人口去重查询根据分组的重复数据（ordId和baseInfoId）并且分页错误", e);
		}
	}

	@Override
	public List<DuplicatePeople> getAllHouseholdStaffAndFloatingDuplicatePeopleByPaging(
			int startRow, int endRow, String tableName) {
		if (StringUtils.isBlank(tableName)
				|| endRow <= 0
				|| (endRow - startRow) != BusinessPopulationTableAndServiceImplModeType.PAGE_SIZE) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", tableName);
			map.put("endRow", endRow);
			map.put("startRow", startRow);
			return poepleDuplicateRemovalLogDAO
					.queryAllHouseholdStaffAndFloatingDuplicatePeopleByPagingForList(map);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"PoepleDuplicateRemovalLogServiceImpl类的queryDuplicatePeopleByGroupByPagingForList方法错误：",
					"人口去重查询根据分组的重复数据（ordId和baseInfoId）并且分页错误", e);
		}
	}
}
