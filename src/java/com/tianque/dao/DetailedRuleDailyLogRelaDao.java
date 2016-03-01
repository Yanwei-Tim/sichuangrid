package com.tianque.dao;

import java.util.List;

import com.tianque.domain.DetailedRuleDailyLogRela;

public interface DetailedRuleDailyLogRelaDao {

	public DetailedRuleDailyLogRela addDetailedRuleDailyLogRela(
			DetailedRuleDailyLogRela detailedRuleDailyLogRela);

	public void deleteDetailedRuleDailyLogRelasByDetailedRuleId(Long detailedRuleId);

	public DetailedRuleDailyLogRela getDetailedRuleDailyLogRelaByWorkingRecordId(Long dailyLogId);

	public List<DetailedRuleDailyLogRela> getDetailedRuleDailyLogRelaByWorkingRecordIds(
			Long dailyLogType, String dailyLogIds);
}
