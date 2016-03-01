package com.tianque.plugin.analysisNew.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.analysisNew.domain.BaseInfoStatType;

@Repository("baseInfoStatTypeNewDao")
public class BaseInfoStatTypeNewDaoImpl extends AbstractBaseDao implements
		BaseInfoStatTypeNewDao {

	public void addBaseInfoStatType(BaseInfoStatType baseInfoStatType) {
		getSqlMapClientTemplate().insert("baseInfoStatTypeNew.addBaseInfoStat",
				baseInfoStatType);

	}

	@Override
	public Integer getCountObjectByOrgInternalCodeAndMap(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"baseInfoStatTypeNew.getCountObjectByOrgInternalCodeAndMap",
				map);
	}

	@Override
	public List<Map<String, Object>> findBaseInfoStatTypeForOrg(Long orgId,
			String baseInfoType, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("baseInfoType", baseInfoType);
		map.put("year", year);
		map.put("month", month);
		return (List<Map<String, Object>>) getSqlMapClientTemplate()
				.queryForList("baseInfoStatTypeNew.findBaeInfo", map);
	}

	public int getOBjectSumOrMonthCreate(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"baseInfoStatTypeNew.getOBjectSumOrMonthCreate", map);
	}

	@Override
	public void deleteByYearAndMonth(String baseinfotype, List<String> types,
			int year, int month) {
		Map map = new HashMap();
		map.put("year", year);
		map.put("month", month);
		map.put("baseinfotype", baseinfotype);
		map.put("types", types);
		getSqlMapClientTemplate().delete(
				"baseInfoStatTypeNew.deleteByYearAndMonth", map);

	}

}
