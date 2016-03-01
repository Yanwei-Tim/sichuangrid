package com.tianque.service;

import java.util.List;

import com.tianque.controller.vo.DetailedRuleTreeGridData;
import com.tianque.domain.AddDetailedRuleCondition;
import com.tianque.domain.DetailedRule;
import com.tianque.domain.DetailedRuleEvaluateResult;
import com.tianque.domain.vo.TmpDetailedRule;

public interface DetailedRuleService {

	public DetailedRule addDetailedRuleRecursion(DetailedRule detailedRule);

	public DetailedRule getSimpleDetailedRuleById(Long id);

	public List<DetailedRuleTreeGridData> getDetailedRulesByEvaluateId(Long id);

	public void deleteDetailedRuleByIdRecursion(Long id);

	public DetailedRule updateDetailedRuleRecursion(DetailedRule detailedRule);

	public DetailedRule updateDetailedRule(DetailedRule detailedRule);

	public void saveDetailedRuleResult(
			DetailedRuleEvaluateResult detailedRuleEvaluateResult);

	public DetailedRule getSimpleDetailedRuleByContent(
			DetailedRule detailedRule, Long id);

	public List<DetailedRule> findDetailedRulesByEvaluateId(Long evaluateId);

	public void deleteDetailedRuleByEvaluateId(Long id);

	public Long[] findDialyLogTypesByDetailedRuleId(Long detailedRuleId);

	public Integer getCountStandardScoreByEvaluateId(Long evaluateId);

	public Integer getSumSelfAssessmentScoreByParentDetailedRuleId(
			Long parentDetailedRuleId);

	public Integer getSumSelfAssessmentScoreByEvaluateId(Long evaluateId);

	public Integer getSumTotalScoreByEvaluateId(Long evaluateId);

	public void calculateFirstLevelDetailedRule(Long evaluateId);

	public void addDetailedRules(List<DetailedRule> detailedRules);

	public List<TmpDetailedRule> getTmpIdsAndParentIds(Long evaluateId);

	public Long getCountSelfAssessmentScore(Long evaluateId);

	public DetailedRule addNormalDetailedRule(DetailedRule detailedRule);

	public void updateDetailedRuleScoreByValidateLeaf(
			DetailedRule detailedRule, Integer sumChildrenScore);

	public void updateDetailedRuleSelfAssessmentScoreByValidateLeaf(
			DetailedRule detailedRule, Integer sumChildrenScore);

	public AddDetailedRuleCondition validateTypeOfChildDetailedRule(
			DetailedRule detailedRule);

}
