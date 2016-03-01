package com.tianque.systemOperateLog.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.vo.PageInfo;
import com.tianque.service.util.PopulationType;
import com.tianque.systemOperateLog.domain.SystemOperateLog;
import com.tianque.systemOperateLog.util.DistinguishSearchTypes;
import com.tianque.systemOperateLog.util.SystemOperateLogToHbase;
import com.tianque.systemOperateLog.util.TransferUtilPop;
import com.tianque.systemOperateLog.vo.SystemOperateLogVo;

@Repository("systemOperateLogDao")
public class SystemOperateLogDaoImpl extends AbstractBaseDao implements
		SystemOperateLogDao {

	private Integer countNum = 0;

	@Override
	public SystemOperateLog addSystemOperateLog(
			SystemOperateLog systemOperateLog) {
		if (PopulationType.AIDSPOPULATIONS_KEY.equals(systemOperateLog
				.getBusinessType())) {
			systemOperateLog
					.setBusinessType(NewBaseInfoTables.AIDSPOPULATIONS_KEY);
		}
		if (PopulationType.OVERSEA_STAFF.equals(systemOperateLog
				.getBusinessType())) {
			systemOperateLog
					.setBusinessType(NewBaseInfoTables.OVERSEAPERSONNEL_KEY);
		}
		if (PopulationType.AIDSPOPULATIONS_KEY.equals(systemOperateLog
				.getOperateSource())) {
			systemOperateLog
					.setOperateSource(NewBaseInfoTables.AIDSPOPULATIONS_KEY);
		}
		if (PopulationType.OVERSEA_STAFF.equals(systemOperateLog
				.getOperateSource())) {
			systemOperateLog
					.setOperateSource(NewBaseInfoTables.OVERSEAPERSONNEL_KEY);
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"systemOperateLog.addSystemOperateLog", systemOperateLog);
		return this.getSystemOperateLogById(id);
	}

	@Override
	public PageInfo<SystemOperateLog> findSystemLogsForPageImportToHbase(
			int pageNum, int pageSize, String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startId", new Long(49978332));
		map.put("endId", new Long(54411590));
		if (pageNum == 1) {
			countNum = (Integer) getSqlMapClientTemplate()
					.queryForObject(
							"systemOperateLog.countSystemOperateLogsImportToHbase",
							map);
			SystemOperateLogToHbase.logger.error("起始ID：" + map.get("startId")
					+ "，结束ID：" + map.get("endId") + "，总数：" + countNum);
		}
		int pageCount = 0;
		if (pageSize != 0 && countNum > 0)
			pageCount = (countNum - 1) / pageSize + 1;
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		map.put("startRow", (pageNum - 1) * pageSize);
		map.put("endRow", pageNum * pageSize);
		List<SystemOperateLog> list = getSqlMapClientTemplate().queryForList(
				"systemOperateLog.findSystemOperateLogsImporToHbase", map);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<SystemOperateLog> findAllSystemLogsForPage(
			SystemOperateLogVo systemOperateLogVo, int pageNum, int pageSize,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (systemOperateLogVo != null) {
			map.put("startDate", systemOperateLogVo.getStartDate());
			map.put("endDate", systemOperateLogVo.getEndDate());
			map.put("dataKeyword", systemOperateLogVo.getDataKeyword());
			if (PopulationType.AIDSPOPULATIONS_KEY.equals(systemOperateLogVo
					.getBusinessType())) {
				map.put("businessType", NewBaseInfoTables.AIDSPOPULATIONS_KEY);
			} else if (PopulationType.OVERSEA_STAFF.equals(systemOperateLogVo
					.getBusinessType())) {
				map.put("businessType", NewBaseInfoTables.OVERSEAPERSONNEL_KEY);
			} else {
				map.put("businessType", systemOperateLogVo.getBusinessType());
			}
			if (PopulationType.AIDSPOPULATIONS_KEY.equals(systemOperateLogVo
					.getSearchType())) {
				map.put("searchType", NewBaseInfoTables.AIDSPOPULATIONS_KEY);
			} else if (PopulationType.OVERSEA_STAFF.equals(systemOperateLogVo
					.getSearchType())) {
				map.put("searchType", NewBaseInfoTables.OVERSEAPERSONNEL_KEY);
			} else {
				map.put("searchType", systemOperateLogVo.getSearchType());
			}
			map.put("operateType", systemOperateLogVo.getOperateType());

			if (StringUtils.isNotBlank(systemOperateLogVo.getSearchType())) {
				if (TransferUtilPop.isAllAttentionPopulation(systemOperateLogVo
						.getSearchType())) {
					map.put("distinguishType", DistinguishSearchTypes.PEOPLE);
				} else if (NewBaseInfoTables.ACTUALHOUSE_KEY
						.equals(systemOperateLogVo.getSearchType())
						|| NewBaseInfoTables.RENTALHOUSE_KEY
								.equals(systemOperateLogVo.getSearchType())) {
					map.put("distinguishType", DistinguishSearchTypes.HOUSEINFO);
				} else if (NewBaseInfoTables.COMPANYPLACEKEY
						.equals(systemOperateLogVo.getSearchType())) {
					map.put("distinguishType",
							DistinguishSearchTypes.COMPANYPLACE);
				}
			}
			map.put("dataId", systemOperateLogVo.getDataId());

			map.put("searchBusinessType",
					systemOperateLogVo.getSearchBusinessType());

			map.put("searchDataKeyword",
					systemOperateLogVo.getSearchDataKeyword());
		}

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"systemOperateLog.countSystemOperateLogsByQueryBuilder", map);

		if (isStrotFieldAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);

		List<SystemOperateLog> list = getSqlMapClientTemplate().queryForList(
				"systemOperateLog.findSystemOperateLogsByQueryBuilder", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<SystemOperateLog> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List<SystemOperateLog> list) {
		PageInfo<SystemOperateLog> pageInfo = new PageInfo<SystemOperateLog>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@Override
	public SystemOperateLog getSystemOperateLogById(Long id) {
		return (SystemOperateLog) getSqlMapClientTemplate().queryForObject(
				"systemOperateLog.getSystemOperateLogById", id);
	}

	@Override
	public SystemOperateLog updateSystemOperateLogById(
			SystemOperateLog systemOperateLog) {
		getSqlMapClientTemplate().update(
				"systemOperateLog.updateSystemOperateLog", systemOperateLog);
		return getSystemOperateLogById(systemOperateLog.getId());
	}

	public Long getSystemOperateLogKey() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"systemOperateLog.getSystemOperateLogKey");
	}

	@Override
	public void updateSystemOperateDataKeyWord(Long orgId, Long dataId,
			String moduleType, String businessType, String beforeDataKeyWord,
			String afterDataKeyWord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("dataId", dataId);
		map.put("moduleType", moduleType);
		map.put("businessType", businessType);
		map.put("beforeDataKeyWord", beforeDataKeyWord);
		map.put("afterDataKeyWord", afterDataKeyWord);
		getSqlMapClientTemplate().update(
				"systemOperateLog.updateSystemOperateDataKeyWord", map);

	}

}
