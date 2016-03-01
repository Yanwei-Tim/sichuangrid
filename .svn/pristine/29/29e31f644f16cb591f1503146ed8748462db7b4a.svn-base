package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.vo.DetailedRuleTreeGridData;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.EvaluateDao;
import com.tianque.dao.ScoresStandDao;
import com.tianque.domain.AddDetailedRuleCondition;
import com.tianque.domain.DetailedRule;
import com.tianque.domain.DetailedRuleEvaluateResult;
import com.tianque.domain.Evaluate;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.ScoresStand;
import com.tianque.domain.User;
import com.tianque.domain.property.EvaluateType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.ScoresStandardsType;
import com.tianque.domain.vo.TmpDetailedRule;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.OperationFailedException;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.DetailedRuleDailyLogRelaService;
import com.tianque.service.DetailedRuleService;
import com.tianque.service.EvaluateService;
import com.tianque.service.state.EvaluateState;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("evaluateService")
@Transactional
public class EvaluateServiceImpl extends AbstractBaseService implements
		EvaluateService {

	@Autowired
	private EvaluateDao evaluateDao;
	@Autowired
	private DetailedRuleService detailedRuleService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DetailedRuleDailyLogRelaService detailedRuleDailyLogRelaService;
	@Autowired
	private ScoresStandDao scoresStandDao;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PlatformMessageService platformaMessageService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;

	@Override
	public Evaluate addEvaluate(Evaluate evaluate) {
		return evaluateDao.addEvaluate(evaluate);
	}

	public void updateRule(DetailedRule detailrule) {
		detailedRuleService.updateDetailedRuleRecursion(detailrule);
	}

	public void deleteDetailedRuleById(long dtid) {
		detailedRuleService.deleteDetailedRuleByIdRecursion(dtid);
	}

	@Override
	public void deleteEvaluateById(Long id) {
		List<DetailedRule> detailedRules = detailedRuleService
				.findDetailedRulesByEvaluateId(id);
		for (int i = 0; i < detailedRules.size(); i++) {
			detailedRuleDailyLogRelaService
					.deleteDetailedRuleDailyLogRelasByDetailedRuleId(detailedRules
							.get(i).getId());
		}
		detailedRuleService.deleteDetailedRuleByEvaluateId(id);
		scoresStandDao.deleteScoresStandByEvaluateId(id);
		evaluateDao.deleteEvaluateById(id);
	}

	@Override
	public Evaluate getSimpleEvaluateById(Long id) {
		return evaluateDao.getSimpleEvaluateById(id);
	}

	@Override
	public Evaluate updateEvaluate(Evaluate evaluate) {
		if (null != evaluate.getTotalScore()) {
			if (evaluate.getState() >= EvaluateState.REPORT) {
				setTotalScoreLevel(evaluate);
			} else {
				evaluate.setEvaluateResult("");
			}
		}
		return evaluateDao.updateEvaluate(evaluate);
	}

	private void setTotalScoreLevel(Evaluate evaluate) {
		List<ScoresStand> scoresStands = scoresStandDao
				.findScoresStandByEvaluateId(evaluate.getId());
		for (ScoresStand scoresStand : scoresStands) {
			if (evaluate.getTotalScore() >= scoresStand.getEndScore()
					&& evaluate.getTotalScore() <= scoresStand.getStartScore()) {
				if (evaluate.getTotalScore()
						.equals(scoresStand.getStartScore())) {
					continue;
				}
				evaluate.setEvaluateResult(propertyDictService
						.getPropertyDictById(
								scoresStand.getPropertyDict().getId())
						.getDisplayName());
				break;
			}
		}
	}

	@Override
	public PageInfo<Evaluate> findPigeOnHoleEvaluateResultsByOrgIdAndYearAndTitle(
			Long orgId, String year, String title, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						EvaluateType.EVALUATE_TYPE_KEY,
						EvaluateType.NORMAL_EVALUATE);

		return evaluateDao.findPigeOnHoleEvaluateResultsByOrgIdAndYearAndTitle(
				orgId, year, title, propertyDicts.get(0).getId(), pageNum,
				pageSize, sidx, sord);
	}

	public List<Evaluate> findEvaluateResultsByOrgId(Long orgId) {
		return evaluateDao.findEvaluateResultsByOrgId(orgId);
	}

	@Override
	public PageInfo<Evaluate> findStandardEvaluatesByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						EvaluateType.EVALUATE_TYPE_KEY,
						EvaluateType.STANDARD_EVALUATE);
		return evaluateDao.findEvaluateResultsByOrgIdAndYearAndTitle(orgId,
				null, null, propertyDicts.get(0).getId(), pageNum, pageSize,
				sidx, sord);
	}

	@Override
	public DetailedRule getSimpleDetailedRuleById(Long id) {
		return detailedRuleService.getSimpleDetailedRuleById(id);
	}

	@Override
	public List<DetailedRuleTreeGridData> getDetailedRulesByEvaluateId(Long id) {
		List<DetailedRuleTreeGridData> detailedRules = detailedRuleService
				.getDetailedRulesByEvaluateId(id);
		for (int i = 0; i < detailedRules.size(); i++) {
			DetailedRuleTreeGridData detailedRuleTreeGridData = detailedRules
					.get(i);
			Long detailedRuleId = detailedRuleTreeGridData.getId();
			Long[] dialyLogTypes = detailedRuleService
					.findDialyLogTypesByDetailedRuleId(detailedRuleId);
			detailedRuleTreeGridData.setDialyLogTypes(dialyLogTypes);
		}
		return detailedRules;
	}

	@Override
	public void pigeonholeEvaluate(Long id) {
		Evaluate evaluate = getSimpleEvaluateById(id);
		evaluate.setState(EvaluateState.PIGEONHOLE);
		evaluateDao.updateEvaluate(evaluate);
	}

	@Override
	public Evaluate copyDetailedRulesToNewEvaluate(Evaluate newEvaluate,
			Long[] detailedRuleIds) {
		Evaluate evaluateSave = addNewEvaluate(newEvaluate);
		if (null != detailedRuleIds) {
			for (Long detailedRuleId : detailedRuleIds) {
				DetailedRule detailedRule = copyDetailedRule(evaluateSave,
						detailedRuleId);
				if (detailedRule.getParentRule() != null) {
					DetailedRule detailed = detailedRuleService
							.getSimpleDetailedRuleById(detailedRule
									.getParentRule().getId());
					detailed = detailedRuleService
							.getSimpleDetailedRuleByContent(detailed,
									detailedRule.getId());
					detailedRule.setParentRule(detailed);
					updateDetailedRule(detailedRule);
				}
			}
		}
		evaluateSave = updateStandardTotalScore(evaluateSave);
		return evaluateSave;
	}

	private Evaluate updateStandardTotalScore(Evaluate evaluateSave) {
		List<DetailedRule> detailedRules = detailedRuleService
				.findDetailedRulesByEvaluateId(evaluateSave.getId());
		Integer totalScore = 0;
		for (DetailedRule detailedRule : detailedRules) {
			if ((detailedRule.getParentRule() == null || detailedRule
					.getParentRule().getId() == null)
					&& detailedRule.getStandardScore() != null) {
				totalScore = totalScore + detailedRule.getStandardScore();
			}
		}
		evaluateSave.setTemplateTotalScore(totalScore);
		evaluateSave = evaluateDao.updateEvaluate(evaluateSave);
		return evaluateSave;
	}

	private DetailedRule copyDetailedRule(Evaluate evaluateSave,
			Long detailedRuleId) {
		DetailedRule detailedRule = detailedRuleService
				.getSimpleDetailedRuleById(detailedRuleId);
		detailedRule.setEvaluate(evaluateSave);
		detailedRule.setScore(null);
		detailedRule.setSummary(null);
		detailedRule = addNormalDetailedRule(detailedRule);

		return detailedRule;
	}

	private Evaluate addNewEvaluate(Evaluate newEvaluate) {
		if (null == newEvaluate.getOrganization()
				|| null == newEvaluate.getOrganization().getId()) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(ThreadVariable.getUser()
							.getOrganization().getId());
			newEvaluate.setOrganization(organization);
			newEvaluate.setOrgInternalCode(organization.getOrgInternalCode());
		}
		if (null == newEvaluate.getEvaluateType()
				|| null == newEvaluate.getEvaluateType().getId()) {
			List<PropertyDict> propertyDicts = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							EvaluateType.EVALUATE_TYPE_KEY,
							EvaluateType.STANDARD_EVALUATE);
			newEvaluate.setEvaluateType(propertyDicts.get(0));
		}
		newEvaluate.setTotalScore(null);
		Evaluate evaluateSave = evaluateDao.addEvaluate(newEvaluate);
		return evaluateSave;
	}

	@Override
	public void saveDetailedRuleResult(Long evaluateId,
			DetailedRuleEvaluateResult detailedRuleEvaluateResult) {
		detailedRuleService.saveDetailedRuleResult(detailedRuleEvaluateResult);
		Evaluate evaluate = getSimpleEvaluateById(evaluateId);
		evaluate.setTotalScore(getSumTotalScoreByEvaluateId(evaluateId));
		updateEvaluate(evaluate);
	}

	private Integer getSumTotalScoreByEvaluateId(Long evaluateId) {
		return detailedRuleService.getSumTotalScoreByEvaluateId(evaluateId);
	}

	@Override
	public Evaluate getEvaluateResultByOrgId(Long orgId) {
		if (null == orgId) {
			return null;
		}
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						EvaluateType.EVALUATE_TYPE_KEY,
						EvaluateType.NORMAL_EVALUATE);
		PropertyDict propertyDict = propertyDicts.get(0);
		return evaluateDao.getLastEvaluateResultByOrgIdAndEvaluateType(orgId,
				propertyDict.getId(), EvaluateState.ACTIVE);
	}

	@Override
	public Evaluate getReportedEvaluateResultByOrgId(Long orgId) {
		if (null == orgId) {
			return null;
		}
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						EvaluateType.EVALUATE_TYPE_KEY,
						EvaluateType.NORMAL_EVALUATE);
		PropertyDict propertyDict = propertyDicts.get(0);
		return evaluateDao.getLastEvaluateResultByOrgIdAndEvaluateType(orgId,
				propertyDict.getId(), EvaluateState.REPORT);
	}

	private void copyPopedomEvaluate(Long evaluateId,
			Evaluate standardEvaluate, List<ScoresStand> scoresStands,
			List<DetailedRule> detailedRules, List<PropertyDict> propertyDicts,
			Organization org) {
		Map<Long, TmpDetailedRule> tmpDetailedRules = getTmpIdsAndParentIds(evaluateId);
		Evaluate evaluate = addEvaluate(propertyDicts.get(0), org,
				standardEvaluate);
		addDetailedRules(evaluate, detailedRules, tmpDetailedRules,
				propertyDicts.get(0));
		calculateFirstLevelDetailedRule(evaluate.getId());
		calculateEvaluateStandardScore(evaluate.getId());
		for (ScoresStand scoresStand : scoresStands) {
			scoresStand.setEvaluate(evaluate);
			scoresStandDao.addScoresStand(scoresStand);
		}
	}

	private void calculateFirstLevelDetailedRule(Long evaluateId) {
		detailedRuleService.calculateFirstLevelDetailedRule(evaluateId);
	}

	private void calculateEvaluateStandardScore(Long id) {
		evaluateDao.updateTotalStandardScoreByEvaluateId(id);
	}

	private Evaluate addEvaluate(PropertyDict propertyDict,
			Organization childOrg, Evaluate standardEvaluate) {
		Evaluate evaluate = new Evaluate();
		try {
			PropertyUtils.copyProperties(evaluate, standardEvaluate);
		} catch (Exception e) {
			throw new OperationFailedException(e.getMessage());
		}
		evaluate.setOrganization(childOrg);
		evaluate.setOrgInternalCode(childOrg.getOrgInternalCode());
		evaluate.setEvaluateType(propertyDict);
		evaluate.setTotalScore(standardEvaluate.getTemplateTotalScore());
		evaluate.setSelfAssessmentTotalScore(standardEvaluate
				.getTemplateTotalScore());
		evaluate.setStandardEvaluate(standardEvaluate);
		evaluate.setCreateDate(Calendar.getInstance().getTime());
		evaluate.setCreateUser(ThreadVariable.getUser().getUserName());
		evaluate.setUpdateDate(null);
		evaluate.setUpdateUser(null);

		evaluate = addEvaluate(evaluate);
		return evaluate;
	}

	private void addDetailedRules(Evaluate evaluate,
			List<DetailedRule> detailedRules,
			Map<Long, TmpDetailedRule> tmpDetailedRules,
			PropertyDict propertyDict) {
		List<DetailedRule> newDetailedRules = new ArrayList<DetailedRule>();
		for (DetailedRule detailedRule : detailedRules) {
			TmpDetailedRule tmpDetailedRule = tmpDetailedRules.get(detailedRule
					.getId());
			DetailedRule newDetailedRule = new DetailedRule();
			try {
				PropertyUtils.copyProperties(newDetailedRule, detailedRule);
			} catch (Exception e) {
				throw new OperationFailedException(e.getMessage());
			}
			newDetailedRule.setId(tmpDetailedRule.getTmpId());
			DetailedRule parentRule = new DetailedRule();
			parentRule.setId(tmpDetailedRule.getNewParentId());
			newDetailedRule.setParentRule(parentRule);
			newDetailedRule.setUpdateDate(null);
			newDetailedRule.setUpdateUser(null);
			newDetailedRule.setCreateUser(ThreadVariable.getUser()
					.getUserName());
			newDetailedRule.setCreateDate(null);
			newDetailedRule.setEvaluate(evaluate);
			newDetailedRule.setScore(detailedRule.getStandardScore());
			newDetailedRule.setSelfAssessmentScore(detailedRule
					.getStandardScore());
			newDetailedRules.add(newDetailedRule);
		}
		detailedRuleService.addDetailedRules(newDetailedRules);
	}

	private Map<Long, TmpDetailedRule> getTmpIdsAndParentIds(Long evaluateId) {
		List<TmpDetailedRule> tmpDetailedRules = detailedRuleService
				.getTmpIdsAndParentIds(evaluateId);
		Map<Long, TmpDetailedRule> map = new HashMap<Long, TmpDetailedRule>();
		for (TmpDetailedRule tmpDetailedRule : tmpDetailedRules) {
			map.put(tmpDetailedRule.getId(), tmpDetailedRule);
		}
		for (Entry<Long, TmpDetailedRule> entry : map.entrySet()) {
			TmpDetailedRule tmpDetailedRule = entry.getValue();
			if (tmpDetailedRule.getParentId() != null) {
				tmpDetailedRule.setNewParentId(map.get(
						tmpDetailedRule.getParentId()).getTmpId());
			}
		}
		return map;
	}

	private Evaluate updateStandardEvaluateToActiveState(Evaluate evaluate) {
		evaluate.setState(EvaluateState.ACTIVE);// 将对应evaluate变为激活状态
		evaluate = evaluateDao.updateEvaluate(evaluate);
		return evaluate;
	}

	@Override
	public PageInfo<Evaluate> findStandardEvaluateByOrgIdAndYearAndTitle(
			Long orgId, String year, String title, Integer page, Integer rows,
			String sidx, String sord) {
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						EvaluateType.EVALUATE_TYPE_KEY,
						EvaluateType.STANDARD_EVALUATE);
		return evaluateDao.findEvaluateResultsByOrgIdAndYearAndTitle(orgId,
				year, title, propertyDicts.get(0).getId(), page, rows, sidx,
				sord);
	}

	@Override
	public PageInfo<Evaluate> findEvaluatesByOrgIdAndYearAndTitleAndState(
			Long orgId, String year, String title, int state, Integer page,
			Integer rows, String sidx, String sord) {
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						EvaluateType.EVALUATE_TYPE_KEY,
						EvaluateType.STANDARD_EVALUATE);
		return evaluateDao.findEvaluatesByOrgIdAndYearAndTitleAndState(orgId,
				year, title, propertyDicts.get(0).getId(), state, page, rows,
				sidx, sord);
	}

	@Override
	public PageInfo<Evaluate> findChildEvaluateResultByOrgIdAndYear(Long orgId,
			String year, Integer page, Integer rows, String sidx, String sord) {
		if (null == orgId) {
			return null;
		}
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(orgId);
		if (null == organizations) {
			return null;
		}
		List<Evaluate> evaluateResult = new ArrayList<Evaluate>();
		Long count = 0L;
		for (Organization o : organizations) {
			List<Evaluate> evaluates = evaluateDao
					.findEvaluateResultByOrgIdAndYear(o.getId(), year, page,
							rows, sidx, sord);
			count = count
					+ evaluateDao.countEvaluateResultByOrgIdAndYear(o.getId(),
							year);
			for (Evaluate e : evaluates) {
				e.setOrganization(o);
				evaluateResult.add(e);
			}
		}
		PageInfo<Evaluate> pageInfo = new PageInfo<Evaluate>();
		pageInfo.setResult(evaluateResult);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		pageInfo.setTotalRowSize(count);
		return pageInfo;
	}

	@Override
	public Evaluate ispigeonholeEvaluateById(Evaluate evaluate) {
		return evaluateDao.ispigeonholeEvaluateById(evaluate);
	}

	@Override
	public void addScoresStands(Evaluate evaluate, ScoresStand great,
			ScoresStand good, ScoresStand qualified, ScoresStand failed,
			String username) {
		setScoresStand(evaluate, great, username);
		setScoresStand(evaluate, good, username);
		setScoresStand(evaluate, qualified, username);
		setScoresStand(evaluate, failed, username);
		scoresStandDao.addScoresStand(great);
		scoresStandDao.addScoresStand(good);
		scoresStandDao.addScoresStand(qualified);
		scoresStandDao.addScoresStand(failed);
	}

	private void setScoresStand(Evaluate evaluate, ScoresStand scoreStandard,
			String username) {
		int[] scoreStandardTypes = { ScoresStandardsType.GREAT,
				ScoresStandardsType.GOOD, ScoresStandardsType.FAILED,
				ScoresStandardsType.QULIFIED };
		for (int i = 0; i < scoreStandardTypes.length; i++) {
			PropertyDict propertyDict = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.SCORES_STANDARDS,
							scoreStandardTypes[i]).get(0);
			if (scoreStandard.getScoresStandardType() == scoreStandardTypes[i]) {
				scoreStandard.setPropertyDict(propertyDict);
				scoreStandard.setEvaluate(evaluate);
				scoreStandard.setCreateDate(Calendar.getInstance().getTime());
				scoreStandard.setCreateUser(username);
			}
		}

	}

	@Override
	public void activeStandardEvaluate(Long evaluateId) {
		Evaluate standardEvaluate = evaluateDao
				.getSimpleEvaluateById(evaluateId);
		/** 允许启用多个模板，所以不删除 */
		updateStandardEvaluateToActiveState(standardEvaluate);

		List<ScoresStand> scoresStands = scoresStandDao
				.findScoresStandByEvaluateId(standardEvaluate.getId());

		List<DetailedRule> detailedRules = detailedRuleService
				.findDetailedRulesByEvaluateId(standardEvaluate.getId());

		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						EvaluateType.EVALUATE_TYPE_KEY,
						EvaluateType.NORMAL_EVALUATE);

		List<Organization> childOrg = organizationDubboService
				.findAdminOrgsByParentId(ThreadVariable.getUser()
						.getOrganization().getId());

		PlatformMessage pm = platformMessageFactory
				.createActiveStandardEvaluatePlatformMessage(standardEvaluate);

		for (int i = 0; i < childOrg.size(); i++) {
			copyPopedomEvaluate(evaluateId, standardEvaluate, scoresStands,
					detailedRules, propertyDicts, childOrg.get(i));
			sendPlatformMessageToUser(pm, childOrg.get(i).getId(),
					"selfEvaluate");
		}

	}

	@Override
	public void reportSelfAssessmentResult(Long id,
			Integer selfAssessmentTotalScore) {
		Evaluate evaluate = evaluateDao.getSimpleEvaluateById(id);
		evaluate.setState(EvaluateState.REPORT);
		evaluate.setSelfAssessmentTotalScore(selfAssessmentTotalScore);
		evaluateDao.updateEvaluate(evaluate);
		// 上报后给上级部门的用户发送平台消息
		PlatformMessage pm = platformMessageFactory
				.createEvaluateReportPlatformMessage(evaluate);

		Organization org = organizationDubboService.getSimpleOrgById(evaluate
				.getOrganization().getId());

		sendPlatformMessageToUser(pm, org.getParentOrg().getId(), "evaluating");

	}

	@Override
	public void backEvaluate(Long id) {
		Evaluate evaluate = new Evaluate();
		evaluate.setId(id);
		evaluate.setState(EvaluateState.ACTIVE);
		evaluateDao.updateEvaluate(evaluate);
		evaluate = evaluateDao.getSimpleEvaluateById(id);
		// 回退发送平台消息
		PlatformMessage pm = platformMessageFactory
				.createEvaluateBackPlatformMessage(evaluate);

		sendPlatformMessageToUser(pm, evaluate.getOrganization().getId(),
				"selfEvaluate");

	}

	private void sendPlatformMessageToUser(PlatformMessage pm, Long orgId,
			String ename) {

		List<User> users = permissionService.findChildUsersByEnameAndOrgCode(
				ename, String.valueOf(orgId));

		if (users != null && users.size() > 0) {

			for (User user : users) {

				pm.setReceiverId(user.getId());

				platformaMessageService.sendPlatformMessageToUser(pm);
			}
		}
	}

	/*** 催报发送平台消息 */
	@Override
	public void urgeEvaluate(List<Long> orgIds, List<Long> evaluateIds) {

		if (evaluateIds == null || evaluateIds.size() == 0
				|| evaluateIds == null || evaluateIds.size() == 0) {
			throw new BusinessValidationException("参数错误");
		}

		List<User> users = null;

		PlatformMessage pm = null;

		for (int i = 0; i < orgIds.size(); i++) {
			users = permissionService.findChildUsersByEnameAndOrgCode(
					"selfEvaluate", String.valueOf(orgIds.get(i)));

			if (users != null && users.size() > 0) {
				for (User user : users) {

					Evaluate evaluate = getSimpleEvaluateById(evaluateIds
							.get(i));

					pm = platformMessageFactory
							.createEvaluateUrgePlatformMessage(evaluate, user);

					platformaMessageService.sendPlatformMessageToUser(pm);
				}
			}

		}
	}

	@Override
	public DetailedRule addDetailedRule(DetailedRule detailedRule) {
		detailedRule = detailedRuleService
				.addDetailedRuleRecursion(detailedRule);
		Evaluate evaluate = getSimpleEvaluateById(detailedRule.getEvaluate()
				.getId());
		evaluate.setTemplateTotalScore(detailedRuleService
				.getCountStandardScoreByEvaluateId(detailedRule.getEvaluate()
						.getId()));
		updateEvaluate(evaluate);
		return detailedRule;
	}

	@Override
	public DetailedRule addNormalDetailedRule(DetailedRule detailedRule) {
		detailedRule = detailedRuleService.addNormalDetailedRule(detailedRule);
		Evaluate evaluate = getSimpleEvaluateById(detailedRule.getEvaluate()
				.getId());
		evaluate.setTemplateTotalScore(detailedRuleService
				.getCountStandardScoreByEvaluateId(detailedRule.getEvaluate()
						.getId()));
		updateEvaluate(evaluate);
		return detailedRule;
	}

	@Override
	public DetailedRule updateDetailedRule(DetailedRule detailedRule) {
		detailedRule = detailedRuleService
				.updateDetailedRuleRecursion(detailedRule);
		Evaluate evaluate = getSimpleEvaluateById(detailedRule.getEvaluate()
				.getId());
		evaluate.setTemplateTotalScore(detailedRuleService
				.getCountStandardScoreByEvaluateId(detailedRule.getEvaluate()
						.getId()));
		updateEvaluate(evaluate);
		return detailedRule;
	}

	private void updateSelfAssessmentScoreByParentId(Long parentDetailedRuleId) {
		DetailedRule detailedRule = detailedRuleService
				.getSimpleDetailedRuleById(parentDetailedRuleId);
		Integer sumSelfScoreNow = detailedRuleService
				.getSumSelfAssessmentScoreByParentDetailedRuleId(detailedRule
						.getId());
		detailedRuleService
				.updateDetailedRuleSelfAssessmentScoreByValidateLeaf(
						detailedRule, sumSelfScoreNow);
		detailedRuleService.updateDetailedRule(detailedRule);
		if (null != detailedRule.getParentRule()
				&& null != detailedRule.getParentRule().getId()) {
			updateSelfAssessmentScoreByParentId(detailedRule.getParentRule()
					.getId());
		}
	}

	private Integer getSumSelfAssessmentScoreByEvaluateId(Long evaluateId) {
		return detailedRuleService
				.getSumSelfAssessmentScoreByEvaluateId(evaluateId);
	}

	@Override
	public DetailedRule selfAssessment(Long id, Integer selfAssessmentScore,
			String summary, String[] dailyIds) {
		DetailedRule detailedRule = detailedRuleDailyLogRelaService
				.selfAssessment(id, selfAssessmentScore, summary, dailyIds);
		if (detailedRule.getParentRule() != null
				&& detailedRule.getParentRule().getId() != null) {
			updateSelfAssessmentScoreByParentId(detailedRule.getParentRule()
					.getId());
		}
		Evaluate evaluate = getSimpleEvaluateById(detailedRule.getEvaluate()
				.getId());
		evaluate.setSelfAssessmentTotalScore(getSumSelfAssessmentScoreByEvaluateId(detailedRule
				.getEvaluate().getId()));
		evaluate.setIsEvaluateUsed(true);
		updateEvaluate(evaluate);
		return detailedRule;
	}

	@Override
	public boolean isPopedomEvaluateUsedByYear(String year) {
		Long countUsedEvaluate = evaluateDao.countUsedEvaluateByYear(year);
		if (countUsedEvaluate > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void unActiveEvaluate(Long evaluateId) {
		evaluateDao.updateUnActiveEvaluateByEvaluateId(evaluateId);
		List<Evaluate> evaluates = evaluateDao
				.findEvaluatesByParentId(evaluateId);
		for (Evaluate evaluate : evaluates) {
			deleteEvaluateById(evaluate.getId());
		}
	}

	@Override
	public boolean isPopedomEvaluateUsedByEvaluateId(Long id) {
		Long countUsedEvaluate = evaluateDao.countUsedEvaluateByEvaluateId(id);
		if (countUsedEvaluate > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void updateEvaluateTitleAndYearById(Long evaluateId, String year,
			String title) {
		Evaluate evaluate = evaluateDao.getSimpleEvaluateById(evaluateId);
		evaluate.setTitle(title);
		evaluate.setYear(year);
		evaluateDao.updateEvaluate(evaluate);
	}

	@Override
	public List<Evaluate> findUseEvaluatesByOrgIdAndYearAndState(String year,
			Long orgId, int state) {
		return evaluateDao.findUseEvaluatesByOrgIdAndYearAndState(year, orgId,
				state);
	}

	@Override
	public Long getCountSelfAssessmentScore(Evaluate evaluate) {
		return detailedRuleService
				.getCountSelfAssessmentScore(evaluate.getId());
	}

	@Override
	public List<Evaluate> findEvaluatesByStandardEvaluateId(Long id) {
		if (null == id) {
			return null;
		}
		return evaluateDao.findEvaluatesByStandardEvaluateId(id);
	}

	@Override
	public boolean validateUnactive(Long id) {
		List<Evaluate> evaluates = findEvaluatesByStandardEvaluateId(id);
		for (Evaluate evaluateResult : evaluates) {
			if (evaluateResult.getState() == EvaluateState.PIGEONHOLE) {
				return false;
			}
		}
		return true;
	}

	@Override
	public PageInfo<Evaluate> findAllLowerEvaluateResultList(Long orgId,
			String year, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (null == orgId || null == year) {
			return null;
		}
		PageInfo<Evaluate> pageInfo = evaluateDao
				.findAllLowerEvaluateResultList(orgId, year, pageNum, pageSize,
						sidx, sord);
		setOrganizationInPageInfo(pageInfo);
		return pageInfo;
	}

	private void setOrganizationInPageInfo(PageInfo<Evaluate> pageInfo) {
		for (Evaluate evaluate : pageInfo.getResult()) {
			evaluate.setOrganization(organizationDubboService
					.findOrganizationsByOrgInternalCode(
							evaluate.getOrgInternalCode()).get(0));
		}
	}

	@Override
	public PageInfo<Evaluate> findReportAllLowerEvaluateResultList(Long orgId,
			String year, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (null == orgId || null == year) {
			return null;
		}
		PageInfo<Evaluate> pageInfo = evaluateDao
				.findReportAllLowerEvaluateResultList(orgId, year, pageNum,
						pageSize, sidx, sord);
		setOrganizationInPageInfo(pageInfo);
		return pageInfo;
	}

	@Override
	public PageInfo<Evaluate> findNotReportAllLowerEvaluateResultList(
			Long orgId, String year, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (null == orgId || null == year) {
			return null;
		}
		PageInfo<Evaluate> pageInfo = evaluateDao
				.findNotReportAllLowerEvaluateResultList(orgId, year, pageNum,
						pageSize, sidx, sord);
		setOrganizationInPageInfo(pageInfo);
		return pageInfo;
	}

	@Override
	public AddDetailedRuleCondition validateTypeOfChildDetailedRule(
			DetailedRule detailedRule) {

		return detailedRuleService
				.validateTypeOfChildDetailedRule(detailedRule);
	}
}
