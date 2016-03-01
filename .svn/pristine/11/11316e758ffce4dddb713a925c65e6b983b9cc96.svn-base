package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.utils.Utils;
import com.tianque.controller.vo.DetailedRuleTreeGridData;
import com.tianque.dao.DetailedRuleDao;
import com.tianque.domain.AddDetailedRuleCondition;
import com.tianque.domain.DetailedRule;
import com.tianque.domain.DetailedRuleEvaluateResult;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.TmpDetailedRule;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.DetailedRuleService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("detailedRuleService")
@Transactional
public class DetailedRuleServiceImpl implements DetailedRuleService {

	@Autowired
	private DetailedRuleDao detailedRuleDao;

	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public DetailedRule addDetailedRuleRecursion(DetailedRule detailedRule) {
		validate(detailedRule);
		detailedRuleDao.addDetailedRule(detailedRule);
		validateCaculateDetailedRule(detailedRule);
		return detailedRule;
	}

	@Override
	public DetailedRule getSimpleDetailedRuleById(Long id) {
		return detailedRuleDao.getSimpleDetailedRuleById(id);
	}

	@Override
	public List<DetailedRuleTreeGridData> getDetailedRulesByEvaluateId(Long id) {
		return detailedRuleDao.getDetailedRulesByEvaluateId(id);
	}

	@Override
	public void saveDetailedRuleResult(
			DetailedRuleEvaluateResult detailedRuleEvaluateResult) {
		DetailedRule detailedRule = null;
		if (null == detailedRuleEvaluateResult.getDetailedRuleIds()) {
			return;
		}
		for (int i = 0; i < detailedRuleEvaluateResult.getDetailedRuleIds().length; i++) {
			detailedRule = getSimpleDetailedRuleById(detailedRuleEvaluateResult
					.getDetailedRuleIds()[i]);
			if (detailedRuleEvaluateResult.getScores()[i] != null
					&& !"".equals(detailedRuleEvaluateResult.getScores()[i]
							.trim())) {
				detailedRule.setScore(Integer
						.valueOf(detailedRuleEvaluateResult.getScores()[i]));
			}
			if (detailedRuleEvaluateResult.getScoreReason()[i] != null
					&& !"".equals(detailedRuleEvaluateResult.getScoreReason()[i]
							.trim())) {
				detailedRule.setScoreReason(detailedRuleEvaluateResult
						.getScoreReason()[i]);
			}
			detailedRule = updateDetailedRule(detailedRule);
			if (detailedRule.getParentRule() != null
					&& detailedRule.getParentRule().getId() != null) {
				updateSumDetailedRuleResultByParentDetailedRuleId(detailedRule);
			}
		}

	}

	@Override
	public void deleteDetailedRuleByIdRecursion(Long id) {
		DetailedRule detailedRule = detailedRuleDao
				.getSimpleDetailedRuleById(id);
		detailedRuleDao.deleteDetailedRuleByid(id);
		validateCaculateDetailedRule(detailedRule);
	}

	@Override
	public DetailedRule updateDetailedRuleRecursion(DetailedRule detailedRule) {
		validate(detailedRule);
		detailedRuleDao.updateDetailedRule(detailedRule);
		validateCaculateDetailedRule(detailedRule);
		return detailedRule;
	}

	private void validateCaculateDetailedRule(DetailedRule detailedRule) {
		if (detailedRule.getParentRule() != null
				&& detailedRule.getParentRule().getId() != null)
			calculateDetailedRuleStandardScore(detailedRule.getParentRule()
					.getId());
	}

	private void validate(DetailedRule detailedRule) {
		if (detailedRule == null)
			throw new BusinessValidationException("细则不能为空");
		if (detailedRule.getContent() == null
				|| "".equals(detailedRule.getContent()))
			throw new BusinessValidationException("必须填写细则内容");
		if (detailedRule.getStandardScore() != null
				&& detailedRule.getStandardScore().longValue() <= 0L)
			throw new BusinessValidationException("标准分必须大于零");
	}

	private void updateSumDetailedRuleResultByParentDetailedRuleId(
			DetailedRule detailedRule) {
		DetailedRule parentDetailedRule = getSimpleDetailedRuleById(detailedRule
				.getParentRule().getId());
		Integer sumChildrenScore = detailedRuleDao
				.getSumDetailedRuleResultByParentDetailedRuleId(parentDetailedRule
						.getId());
		refreshDetailedScore(parentDetailedRule, sumChildrenScore);
	}

	private void refreshDetailedScore(DetailedRule parentDetailedRule,
			Integer sumChildrenScore) {
		updateDetailedRuleScoreByValidateLeaf(parentDetailedRule,
				sumChildrenScore);
		parentDetailedRule = updateDetailedRuleRecursion(parentDetailedRule);
		if (parentDetailedRule.getParentRule() != null
				&& parentDetailedRule.getParentRule().getId() != null) {
			updateSumDetailedRuleResultByParentDetailedRuleId(parentDetailedRule);
		}
	}

	@Override
	public void updateDetailedRuleScoreByValidateLeaf(
			DetailedRule detailedRule, Integer sumChildrenScore) {
		if (null != detailedRuleDao.findDetailedRulesByParentId(detailedRule
				.getId()) && validateChildIsConditionDetailedRule(detailedRule)) {
			detailedRule.setScore(detailedRule.getStandardScore()
					+ sumChildrenScore);
		} else {
			detailedRule.setScore(sumChildrenScore);
		}
	}

	@Override
	public void updateDetailedRuleSelfAssessmentScoreByValidateLeaf(
			DetailedRule detailedRule, Integer sumChildrenAssessmentScore) {
		if (null != detailedRuleDao.findDetailedRulesByParentId(detailedRule
				.getId()) && validateChildIsConditionDetailedRule(detailedRule)) {
			detailedRule.setSelfAssessmentScore(detailedRule.getStandardScore()
					+ sumChildrenAssessmentScore);
		} else {
			detailedRule.setSelfAssessmentScore(sumChildrenAssessmentScore);
		}
	}

	private boolean validateChildIsConditionDetailedRule(
			DetailedRule parentDetailedRule) {
		List<DetailedRule> childDetailedRules = detailedRuleDao
				.findDetailedRulesByParentId(parentDetailedRule.getId());
		for (DetailedRule _detailedRule : childDetailedRules) {
			List result = detailedRuleDao
					.findDetailedRulesByParentId(_detailedRule.getId());
			if (null != result && result.size() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public DetailedRule getSimpleDetailedRuleByContent(
			DetailedRule detailedRule, Long id) {
		return detailedRuleDao.getSimpleDetailedRuleByContent(detailedRule, id);
	}

	@Override
	public List<DetailedRule> findDetailedRulesByEvaluateId(Long evaluateId) {
		return detailedRuleDao.findDetailedRulesByEvaluateId(evaluateId);
	}

	@Override
	public void deleteDetailedRuleByEvaluateId(Long id) {
		detailedRuleDao.deleteDetailedRuleByEvaluateId(id);
	}

	@Override
	public Long[] findDialyLogTypesByDetailedRuleId(Long detailedRuleId) {
		Long[] dialyLogTypes = detailedRuleDao
				.findDialyLogTypesByDetailedRuleId(detailedRuleId);
		List<PropertyDict> dictList = propertyDictService
				.findPropertyDictByIds(dialyLogTypes);
		List<Long> dictIds = null;
		if (dictList != null && dictList.size() > 0) {
			dictIds = new ArrayList<Long>();
			for (PropertyDict dict : dictList) {
				dictIds.add(dict.getId());
			}
		}
		return Utils.analyticalIds(dictIds);
	}

	public Integer getCountStandardScoreByEvaluateId(Long evaluateId) {
		return detailedRuleDao.getCountStandardScoreByEvaluateId(evaluateId);
	}

	private void calculateDetailedRuleStandardScore(Long detailedRuleId) {
		DetailedRule detailedRule = detailedRuleDao
				.getSimpleDetailedRuleById(detailedRuleId);
		Integer standardScore = detailedRuleDao
				.getCountStandardScoreByParentRuleId(detailedRuleId);
		detailedRule.setStandardScore(standardScore);
		detailedRuleDao.updateDetailedRule(detailedRule);
		validateCaculateDetailedRule(detailedRule);
	}

	@Override
	public Integer getSumSelfAssessmentScoreByParentDetailedRuleId(
			Long parentDetailedRuleId) {
		return detailedRuleDao
				.getSumSelfAssessmentScoreByParentDetailedRuleId(parentDetailedRuleId);
	}

	@Override
	public Integer getSumSelfAssessmentScoreByEvaluateId(Long evaluateId) {
		return detailedRuleDao
				.getSumSelfAssessmentScoreByEvaluateId(evaluateId);
	}

	@Override
	public Integer getSumTotalScoreByEvaluateId(Long evaluateId) {
		return detailedRuleDao.getSumTotalScoreByEvaluateId(evaluateId);
	}

	@Override
	public List<TmpDetailedRule> getTmpIdsAndParentIds(Long evaluateId) {
		return detailedRuleDao.getTmpIdsAndParentIds(evaluateId);
	}

	@Override
	public void addDetailedRules(List<DetailedRule> detailedRules) {
		detailedRuleDao.addDetailedRules(detailedRules);
	}

	@Override
	public void calculateFirstLevelDetailedRule(Long evaluateId) {
		detailedRuleDao.updateFirstLevelStandardScore(evaluateId);
	}

	@Override
	public Long getCountSelfAssessmentScore(Long evaluateId) {
		if (null == evaluateId) {
			return null;
		}
		return detailedRuleDao.getCountSelfAssessmentScore(evaluateId);
	}

	@Override
	public DetailedRule addNormalDetailedRule(DetailedRule detailedRule) {
		validate(detailedRule);
		detailedRuleDao.addDetailedRule(detailedRule);
		return detailedRule;
	}

	@Override
	public DetailedRule updateDetailedRule(DetailedRule detailedRule) {
		detailedRuleDao.updateDetailedRule(detailedRule);
		return detailedRule;
	}

	@Override
	public AddDetailedRuleCondition validateTypeOfChildDetailedRule(
			DetailedRule detailedRule) {
		if (null == detailedRule.getId()) {
			throw new BusinessValidationException("无parentId");
		}
		List<DetailedRule> detailedRules = detailedRuleDao
				.findDetailedRulesByParentId(detailedRule.getId());
		if (null == detailedRules || detailedRules.size() == 0) {
			return new AddDetailedRuleCondition(
					AddDetailedRuleCondition.HAVENOCHILD);
		}
		for (DetailedRule detailedRuleResult : detailedRules) {
			if (null == detailedRuleResult.getStandardScore()) {
				return new AddDetailedRuleCondition(
						AddDetailedRuleCondition.HAVENOSCORE);
			}
		}
		return new AddDetailedRuleCondition(AddDetailedRuleCondition.HAVESCORE);
	}
}

// private void refreshScore(DetailedRule parentDetailedRule, double
// sumChildrenScore) {
// if(sumChildrenScore < 0){
// parentDetailedRule.setScore(parentDetailedRule.getStandardScore() +
// sumChildrenScore);
// }else if(sumChildrenScore == 0){
// if(getSimpleDetailedRuleById(parentDetailedRule.getParentRule().getId()).getParentRule()
// == null){
// parentDetailedRule.setScore(parentDetailedRule.getStandardScore());
// }else{
// parentDetailedRule.setScore(0d);
// }
// }else{
// parentDetailedRule.setScore(sumChildrenScore);
// }
// parentDetailedRule = updateDetailedRuleRecursion(parentDetailedRule);
// if(parentDetailedRule.getParentRule()!=null &&
// parentDetailedRule.getParentRule().getId()!=null){
// updateSumDetailedRuleResultByParentDetailedRuleId(parentDetailedRule);
// }
// }

// @Override
// public void updateDetailedRuleStandardScoreByDetailedRuleId(
// Long detailedRuleId, Double standardScore) {
// DetailedRule detailedRule = detailedRuleDao
// .getSimpleDetailedRuleById(detailedRuleId);
// detailedRule.setStandardScore(standardScore);
// detailedRuleDao.updateDetailedRule(detailedRule);
// calculateDetailedRuleStandardScore(detailedRule.getParentRule().getId());
//
// }