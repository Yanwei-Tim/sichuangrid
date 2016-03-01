package com.tianque.plugin.analysisNew.dao;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.analysisNew.domain.BaseInfoStatType;

public interface BaseInfoStatTypeNewDao {
	void addBaseInfoStatType(BaseInfoStatType baseInfoStatType);

	List<Map<String, Object>> findBaseInfoStatTypeForOrg(Long orgId,
			String baseInfoType, int year, int month);

	Integer getCountObjectByOrgInternalCodeAndMap(Map<String, Object> map);

	int getOBjectSumOrMonthCreate(Map<String, Object> map);

	void deleteByYearAndMonth(String baseinfotype, List<String> types,
			int year, int month);

}
