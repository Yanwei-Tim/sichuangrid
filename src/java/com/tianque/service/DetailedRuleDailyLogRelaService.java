package com.tianque.service;

import java.util.List;

import com.tianque.domain.DetailedRule;
import com.tianque.domain.DetailedRuleDailyLogRela;

public interface DetailedRuleDailyLogRelaService {

	public DetailedRuleDailyLogRela addDetailedRuleDailyLogRela(
			DetailedRuleDailyLogRela detailedRuleDailyLogRela);

	public void deleteDetailedRuleDailyLogRelasByDetailedRuleId(Long detailedRuleId);

	public DetailedRule selfAssessment(Long detailedRuleId, Integer selfAssessmentScore,
			String summary, String[] dailyIds);

	public DetailedRuleDailyLogRela getDetailedRuleDailyLogRelaByWorkingRecordId(Long dailyLogId);

	public List<DetailedRuleDailyLogRela> getDetailedRuleDailyLogRelaByWorkingRecordIds(
			Long dailyLogType, String dailyLogIds);
}
