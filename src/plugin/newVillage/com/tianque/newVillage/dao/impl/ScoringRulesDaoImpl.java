package com.tianque.newVillage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.newVillage.dao.ScoringRulesDao;
import com.tianque.newVillage.domain.ScoringRules;

/**
 * @ClassName: ScoringRulesDaoImpl
 * @Description: 评分规则
 */
@Repository("scoringRulesDao")
public class ScoringRulesDaoImpl extends AbstractBaseDao implements
		ScoringRulesDao {

	@Override
	public void addScoringRules(ScoringRules scoringRules) {
		getSqlMapClientTemplate().insert("scoringRules.addScoringRules",
				scoringRules);
	}

	@Override
	public ScoringRules getScoringRulesById(Long id) {
		return (ScoringRules) getSqlMapClientTemplate().queryForObject(
				"scoringRules.getScoringRules", id);
	}

	@Override
	public void deleteScoringRules() {
		getSqlMapClientTemplate().delete("scoringRules.deleteScoringRules");
	}

	@Override
	public void updateScoringRules(ScoringRules scoringRules) {
		if (scoringRules != null) {
			scoringRules.setUpdateUser(ThreadVariable.getUser().getName());
		}
		getSqlMapClientTemplate().update("scoringRules.updateScoringRules",
				scoringRules);
	}

	@Override
	public List<ScoringRules> getScoringRulesListByValues() {
		return (List<ScoringRules>) getSqlMapClientTemplate().queryForList(
				"scoringRules.getScoringRulesList");
	}

	@Override
	public ScoringRules checkValueIsSave(String correspondingValue,
			Integer calculationSymbol) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("correspondingValue", correspondingValue);
		map.put("calculationSymbol", calculationSymbol);
		return (ScoringRules) getSqlMapClientTemplate().queryForObject(
				"scoringRules.checkValueIsSave", map);
	}

}
