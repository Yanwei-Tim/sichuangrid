package com.tianque.plugin.analyzing.dao;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.analyzing.domain.BaseInfoStatType;

public interface BaseInfoStatTypeDao {
	void addBaseInfoStatType(BaseInfoStatType baseInfoStatType);

	List<Map<String, Object>> findBaseInfoStatTypeForOrg(String orgInternalCode,
			String baseInfoType, int year, int month);

	Integer getCountObjectByOrgInternalCodeAndMap(Map<String, Object> map);

	int getOBjectSumOrMonthCreate(Map<String, Object> map);

	void deleteByYearAndMonth(String baseinfotype, List<String> types, int year, int month);

}
