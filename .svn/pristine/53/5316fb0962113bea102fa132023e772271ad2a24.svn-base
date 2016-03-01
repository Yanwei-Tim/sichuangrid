package com.tianque.service;

import java.util.List;

import com.tianque.controller.vo.DetailedRuleTreeGridData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.AddDetailedRuleCondition;
import com.tianque.domain.DetailedRule;
import com.tianque.domain.DetailedRuleEvaluateResult;
import com.tianque.domain.Evaluate;
import com.tianque.domain.ScoresStand;

public interface EvaluateService {

	public Evaluate addEvaluate(Evaluate evaluate);

	public Evaluate updateEvaluate(Evaluate evaluate);

	public void deleteEvaluateById(Long id);

	public Evaluate getSimpleEvaluateById(Long id);

	public List<DetailedRuleTreeGridData> getDetailedRulesByEvaluateId(Long id);

	public List<Evaluate> findEvaluateResultsByOrgId(Long orgId);

	public void pigeonholeEvaluate(Long id);

	PageInfo<Evaluate> findStandardEvaluatesByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public Evaluate copyDetailedRulesToNewEvaluate(Evaluate evaluate,
			Long[] detailedRuleIds);

	public void saveDetailedRuleResult(Long evaluateId,
			DetailedRuleEvaluateResult detailedRuleEvaluateResult);

	public Evaluate getEvaluateResultByOrgId(Long orgId);

	public void activeStandardEvaluate(Long evaluateId);

	public void deleteDetailedRuleById(long dtid);

	public DetailedRule getSimpleDetailedRuleById(Long id);

	public PageInfo<Evaluate> findStandardEvaluateByOrgIdAndYearAndTitle(
			Long orgId, String year, String title, Integer page, Integer rows,
			String sidx, String sord);

	public Evaluate ispigeonholeEvaluateById(Evaluate evaluate);

	public void reportSelfAssessmentResult(Long id,
			Integer selfAssessmentTotalScore);

	public void addScoresStands(Evaluate evaluate, ScoresStand great,
			ScoresStand good, ScoresStand quilified, ScoresStand failed,
			String username);

	public Evaluate getReportedEvaluateResultByOrgId(Long id);

	public PageInfo<Evaluate> findChildEvaluateResultByOrgIdAndYear(Long orgId,
			String year, Integer page, Integer rows, String sidx, String sord);

	public DetailedRule addDetailedRule(DetailedRule detailedRule);

	public DetailedRule updateDetailedRule(DetailedRule detailedRule);

	public DetailedRule selfAssessment(Long id, Integer selfAssessmentScore,
			String summary, String[] dailyIds);

	public void backEvaluate(Long id);

	public boolean isPopedomEvaluateUsedByYear(String year);

	public void unActiveEvaluate(Long evaluateId);

	public boolean isPopedomEvaluateUsedByEvaluateId(Long id);

	public void updateEvaluateTitleAndYearById(Long evaluateId, String year,
			String title);

	public List<Evaluate> findUseEvaluatesByOrgIdAndYearAndState(String year,
			Long orgId, int state);

	public Long getCountSelfAssessmentScore(Evaluate evaluate);

	public List<Evaluate> findEvaluatesByStandardEvaluateId(Long id);

	public boolean validateUnactive(Long id);

	public DetailedRule addNormalDetailedRule(DetailedRule detailedRule);

	public PageInfo<Evaluate> findAllLowerEvaluateResultList(Long orgId,
			String year, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	public PageInfo<Evaluate> findReportAllLowerEvaluateResultList(Long orgId,
			String year, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	public PageInfo<Evaluate> findNotReportAllLowerEvaluateResultList(
			Long orgId, String year, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public PageInfo<Evaluate> findPigeOnHoleEvaluateResultsByOrgIdAndYearAndTitle(
			Long orgId, String year, String title, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public PageInfo<Evaluate> findEvaluatesByOrgIdAndYearAndTitleAndState(
			Long orgId, String year, String title, int state, Integer page,
			Integer rows, String sidx, String sord);

	public void urgeEvaluate(List<Long> checkBoxSelect, List<Long> evaluateIds);

	public AddDetailedRuleCondition validateTypeOfChildDetailedRule(
			DetailedRule detailedRule);

}
