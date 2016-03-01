package com.tianque.newVillage.service;

import java.util.List;

import com.tianque.newVillage.domain.ScoringRules;

/**
 * @ClassName: ScoringRulesService
 * @Description: 评分规则
 */
public interface ScoringRulesService {
	/**
	 * 增加评分规则 数据
	 * 
	 * @param scoringRules
	 * @return ScoringRules
	 */
	public void addScoringRules(String[] correspondingValue,
			String[] calculationSymbol, String[] scoringRangeStart,
			String[] scoringRangeEnd, String[] fraction, Integer maxFraction);

	/**
	 * 根据id获得 评分规则数据
	 * 
	 * @param id
	 * @return ScoringRules
	 */
	public ScoringRules getScoringRulesById(Long id);

	/**
	 * 根据id删除评分规则数据
	 * 
	 * @param id
	 */
	public void deleteScoringRules();

	/**
	 * 修改评分规则数据
	 * 
	 * @param scoringRules
	 * @return ScoringRules
	 */
	public void updateScoringRules(ScoringRules scoringRules);

	/**
	 * 根据条件查询评分集合数据
	 * 
	 * @param year
	 *            年
	 * @return
	 */
	public List<ScoringRules> getScoringRulesListByValues();

	/**
	 * 检查该字段名中，规则是否存在
	 * 
	 * @param correspondingValue
	 *            字段名
	 * @param calculationSymbol
	 *            规则运算符
	 * @return
	 */
	public ScoringRules checkValueIsSave(String correspondingValue,
			Integer calculationSymbol);
}
