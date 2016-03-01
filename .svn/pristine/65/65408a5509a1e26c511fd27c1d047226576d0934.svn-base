package com.tianque.plugin.analyzing.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.analyzing.domain.BaseInfoStatType;

@Repository("baseInfoStatTypeDao")
public class BaseInfoStatTypeDaoImpl extends AbstractBaseDao implements BaseInfoStatTypeDao {

	public void addBaseInfoStatType(BaseInfoStatType baseInfoStatType) {
		getSqlMapClientTemplate().insert("baseInfoStatType.addBaseInfoStat", baseInfoStatType);

	}

	@Override
	public Integer getCountObjectByOrgInternalCodeAndMap(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"baseInfoStatType.getCountObjectByOrgInternalCodeAndMap", map);
	}

	@Override
	public List<Map<String, Object>> findBaseInfoStatTypeForOrg(String orgInternalCode,
			String baseInfoType, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("baseInfoType", baseInfoType);
		map.put("year", year);
		map.put("month", month);
		return (List<Map<String, Object>>) getSqlMapClientTemplate().queryForList(
				"baseInfoStatType.findBaeInfo", map);
	}

	public int getOBjectSumOrMonthCreate(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"baseInfoStatType.getOBjectSumOrMonthCreate", map);
	}

	@Override
	public void deleteByYearAndMonth(String baseinfotype, List<String> types, int year, int month) {
		Map map = new HashMap();
		map.put("year", year);
		map.put("month", month);
		map.put("baseinfotype", baseinfotype);
		map.put("types", types);
		getSqlMapClientTemplate().delete("baseInfoStatType.deleteByYearAndMonth", map);

	}

}
