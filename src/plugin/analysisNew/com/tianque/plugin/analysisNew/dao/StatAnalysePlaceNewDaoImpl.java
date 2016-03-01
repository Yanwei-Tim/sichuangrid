package com.tianque.plugin.analysisNew.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.BaseInfoTables;

@Repository("statAnalysePlaceNewDao")
public class StatAnalysePlaceNewDaoImpl extends AbstractBaseDao implements
		StatAnalysePlaceNewDao {

	public int getStatAnalysePlace(String tableName, String orgInternalCode,
			String keyType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (BaseInfoTables.toString("ENTERPRISEKEY").equals(tableName)
				|| BaseInfoTables.toString("SAFETYPRODUCTIONKEY").equals(
						tableName)
				|| BaseInfoTables.toString("FIRESAFETYKEY").equals(tableName)
				|| BaseInfoTables.toString("SECURITYKEY").equals(tableName)
				|| BaseInfoTables.toString("ENTERPRISEDOWNKEY").equals(
						tableName)) {
			map.put("tableName", "enterprises");
			map.put("keyType", tableName);
		} else {
			map.put("tableName", tableName);
		}
		map.put("orgInternalCode", orgInternalCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"statanalysePlaceNew.getStatanalysePlace", map);
	}

	@Override
	public int getIsHelpStatAnalysePlace(String tableName,
			String orgInternalCode, String keyType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (BaseInfoTables.toString("ENTERPRISEKEY").equals(tableName)
				|| BaseInfoTables.toString("SAFETYPRODUCTIONKEY").equals(
						tableName)
				|| BaseInfoTables.toString("FIRESAFETYKEY").equals(tableName)
				|| BaseInfoTables.toString("SECURITYKEY").equals(tableName)
				|| BaseInfoTables.toString("ENTERPRISEDOWNKEY").equals(
						tableName)) {
			map.put("tableName", "enterprises");
			map.put("keyType", tableName);
		} else {
			map.put("tableName", tableName);
		}
		map.put("objectType", keyType);
		map.put("orgInternalCode", orgInternalCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"statanalysePlaceNew.getIsHelpStatAnalysePlace", map);
	}

	@Override
	public List<Map<String, Object>> getHelpedStatAnalysePlace(
			String tableName, long orgId, String keytype, Long orgType) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (BaseInfoTables.toString("ENTERPRISEKEY").equals(tableName)
				|| BaseInfoTables.toString("SAFETYPRODUCTIONKEY").equals(
						tableName)
				|| BaseInfoTables.toString("FIRESAFETYKEY").equals(tableName)
				|| BaseInfoTables.toString("SECURITYKEY").equals(tableName)
				|| BaseInfoTables.toString("ENTERPRISEDOWNKEY").equals(
						tableName)) {
			map.put("tableName", "enterprises");
			map.put("keyType", tableName);
		} else {
			map.put("tableName", tableName);
		}
		map.put("objectType", keytype);
		map.put("orgId", orgId);
		map.put("orgType", orgType);
		return getSqlMapClientTemplate().queryForList(
				"statanalysePlaceNew.getHelpedStatAnalysePlace", map);
	}

	@Override
	public void deleteByYearAndMonthAndType(int year, int month, String typeName) {
		Map map = new HashMap();
		map.put("year", year);
		map.put("month", month);
		map.put("typeName", typeName);
		getSqlMapClientTemplate().delete(
				"statanalysePlaceNew.deleteByYearAndMonthAndType", map);
	}
}
