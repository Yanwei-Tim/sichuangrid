package com.tianque.plugin.analysisNew.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.BusinessValidationException;

@Repository("statisticsBaseInfoNewDao")
public class StatisticsBaseInfoNewDaoImpl extends AbstractBaseDao implements
		StatisticsBaseInfoNewDao {

	@Autowired
	private CacheService cacheService;

	@Override
	public Integer getCountByOrgInternalCodeAndTableNameAndMap(
			String orgInternalCode, String tableName, Map<String, Object> map) {
		if (map == null || !StringUtil.isStringAvaliable(orgInternalCode)
				|| !StringUtil.isStringAvaliable(tableName)) {
			throw new BusinessValidationException("参数不正确");
		}
		map.put("orgInternalCode", orgInternalCode);
		tableName = map.get("tableName") == null ? tableName : (String) map
				.get("tableName");
		map.put("tableName", tableName);
		Integer sum = (Integer) cacheService.get(MemCacheConstant
				.getOldCompanyPlaceCachKey(orgInternalCode, tableName));
		if (sum == null) {
			sum = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(
							"statisticsBaseInfoNew.getCountByOrgInternalCodeAndTableNameAndMap",
							map);
			cacheService.set(MemCacheConstant.getOldCompanyPlaceCachKey(
					orgInternalCode, tableName), sum);
		}
		return sum;
	}

	@Override
	public int getCountFromHistory(Map<String, Object> map) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"statisticsBaseInfoNew.getCountFromHistory", map);

	}

	public List<Map<String, Object>> getHelpedCountFromHistory(Long orgId,
			String baseinfotype, String tableName, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("orgId", orgId);
		map.put("typeName", tableName);
		map.put("baseinfotype", baseinfotype);
		return getSqlMapClientTemplate().queryForList(
				"statisticsBaseInfoNew.getIsHelpCountFromHistory", map);
	}

	public int getIsHelpFromHistory(String orgInternalCode, String typeName,
			String baseinfotype, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("orgInternalCode", orgInternalCode);
		map.put("typeName", typeName);
		map.put("baseinfotype", baseinfotype);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"statisticsBaseInfoNew.getIsHelpFromHistory", map);
	}

	public int getNoHelpFromHistory(String orgInternalCode, String typeName,
			String baseinfotype, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("orgInternalCode", orgInternalCode);
		map.put("typeName", typeName);
		map.put("baseinfotype", baseinfotype);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"statisticsBaseInfoNew.getNoHelpFromHistory", map);
	}

}
