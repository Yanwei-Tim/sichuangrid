package com.tianque.dao;

import java.util.List;

import com.tianque.controller.vo.DetailedRuleTreeGridData;
import com.tianque.domain.DetailedRule;
import com.tianque.domain.vo.TmpDetailedRule;

public interface DetailedRuleDao {

	public DetailedRule addDetailedRule(DetailedRule detailedRule);

	public DetailedRule getSimpleDetailedRuleById(Long id);

	public List<DetailedRuleTreeGridData> getDetailedRulesByEvaluateId(Long id);

	public void deleteDetailedRuleByid(Long id);

	public DetailedRule updateDetailedRule(DetailedRule detailedRule);

	public DetailedRule getSimpleDetailedRuleByContent(
			DetailedRule detailedRule, Long id);

	public List<DetailedRule> findDetailedRulesByEvaluateId(Long evaluateId);

	public void deleteDetailedRuleByEvaluateId(Long id);

	public Long[] findDialyLogTypesByDetailedRuleId(Long detailedRuleId);

	public Integer getCountStandardScoreByParentRuleId(Long detailedRuleId);

	public Integer getCountStandardScoreByEvaluateId(Long evaluateId);

	public Integer getSumSelfAssessmentScoreByParentDetailedRuleId(
			Long parentDetailedRuleId);

	public Integer getSumSelfAssessmentScoreByEvaluateId(Long evaluateId);

	public Integer getSumDetailedRuleResultByParentDetailedRuleId(
			Long parentDetailedRuleId);

	public Integer getSumTotalScoreByEvaluateId(Long evaluateId);

	public List<TmpDetailedRule> getTmpIdsAndParentIds(Long evaluateId);

	public void addDetailedRules(List<DetailedRule> detailedRules);

	public void updateFirstLevelStandardScore(Long evaluateId);

	public Long getCountSelfAssessmentScore(Long evaluateId);

	public List<DetailedRule> findDetailedRulesByParentId(Long detailedRuleId);
}
