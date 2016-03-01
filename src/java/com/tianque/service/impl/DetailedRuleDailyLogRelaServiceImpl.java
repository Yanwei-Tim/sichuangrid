package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.dao.DetailedRuleDailyLogRelaDao;
import com.tianque.domain.DetailedRule;
import com.tianque.domain.DetailedRuleDailyLogRela;
import com.tianque.service.DetailedRuleDailyLogRelaService;
import com.tianque.service.DetailedRuleService;

@Service("detailedRuleDailyLogRelaService")
public class DetailedRuleDailyLogRelaServiceImpl extends AbstractBaseService implements
		DetailedRuleDailyLogRelaService {

	@Autowired
	private DetailedRuleDailyLogRelaDao detailedRuleDailyLogRelaDao;
	@Autowired
	private DetailedRuleService detailedRuleService;

	@Override
	public DetailedRuleDailyLogRela addDetailedRuleDailyLogRela(
			DetailedRuleDailyLogRela detailedRuleDailyLogRela) {
		return detailedRuleDailyLogRelaDao.addDetailedRuleDailyLogRela(detailedRuleDailyLogRela);
	}

	@Override
	public void deleteDetailedRuleDailyLogRelasByDetailedRuleId(Long detailedRuleId) {
		detailedRuleDailyLogRelaDao.deleteDetailedRuleDailyLogRelasByDetailedRuleId(detailedRuleId);
	}

	@Override
	public DetailedRule selfAssessment(Long detailedRuleId, Integer selfAssessmentScore,
			String summary, String[] workingRecordIds) {
		DetailedRule detailedRule = detailedRuleService.getSimpleDetailedRuleById(detailedRuleId);
		detailedRule.setSelfAssessmentScore(selfAssessmentScore);
		detailedRule.setSummary(summary);
		detailedRuleService.updateDetailedRuleRecursion(detailedRule);
		deleteDetailedRuleDailyLogRelasByDetailedRuleId(detailedRule.getId());

		for (int i = 0; workingRecordIds != null && i < workingRecordIds.length; i++) {
			DetailedRuleDailyLogRela detailedRuleDailyLogRela = new DetailedRuleDailyLogRela();

			detailedRuleDailyLogRela
					.setDailyLogId(Long.parseLong(workingRecordIds[i].substring(2)));
			detailedRuleDailyLogRela.setDailyLogType(Long.parseLong(workingRecordIds[i].substring(
					0, 1)));
			detailedRuleDailyLogRela.setDetailedRule(detailedRule);
			addDetailedRuleDailyLogRela(detailedRuleDailyLogRela);
		}
		return detailedRule;
	}

	@Override
	public DetailedRuleDailyLogRela getDetailedRuleDailyLogRelaByWorkingRecordId(Long dailyLogId) {

		return detailedRuleDailyLogRelaDao.getDetailedRuleDailyLogRelaByWorkingRecordId(dailyLogId);
	}

	@Override
	public List<DetailedRuleDailyLogRela> getDetailedRuleDailyLogRelaByWorkingRecordIds(
			Long dailyLogType, String dailyLogIds) {

		return detailedRuleDailyLogRelaDao.getDetailedRuleDailyLogRelaByWorkingRecordIds(
				dailyLogType, dailyLogIds);
	}

}
