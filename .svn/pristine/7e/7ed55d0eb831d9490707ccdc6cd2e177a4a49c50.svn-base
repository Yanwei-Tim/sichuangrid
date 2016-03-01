package com.tianque.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.vo.DetailedRuleTreeGridData;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.AddDetailedRuleCondition;
import com.tianque.domain.DetailedRule;
import com.tianque.domain.DetailedRuleEvaluateResult;
import com.tianque.domain.Evaluate;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.ScoresStand;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.ScoresStandardsType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.EvaluateService;
import com.tianque.service.ScoresStandService;
import com.tianque.service.state.EvaluateState;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.WorkingRecord;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.WorkingRecordService;

@Scope("request")
@SuppressWarnings("serial")
@Controller("evaluateController")
public class EvaluateController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(EvaluateController.class);
	@Autowired
	private EvaluateService evaluateService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ScoresStandService scoresStandService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private WorkingRecordService workingRecordService;

	private PageInfo<Evaluate> pageInfo;
	private Evaluate evaluate;
	private List<Evaluate> evaluates;
	private Long parentId;
	private Long[] detailedRuleIds;
	private String[] scores;
	private String[] summarys;
	private String[] scoreReason;
	private List<Long> standards = new ArrayList<Long>();// 优秀等等级标准分
	private ScoresStand great = new ScoresStand();
	private ScoresStand good = new ScoresStand();
	private ScoresStand qualified = new ScoresStand();
	private ScoresStand failed = new ScoresStand();
	private List<PropertyDict> scoreStandards;
	private DetailedRule detailedRule;
	private String selectedDailyIds;
	private int[] optionYears = new int[2];
	private List<Long> checkBoxSelect = new ArrayList<Long>();
	private List<Long> evaluateIds = new ArrayList<Long>();
	private Boolean isHaveActive = false;
	private boolean popedomEvaluateUsed;
	private HashSet<String> evaluateYears = new HashSet<String>();
	private List<String> evaluateYearsList = new ArrayList<String>();
	private String isChildOrgGetYears = "false";
	private int state;
	private int condition;
	private WorkingRecord workingRecord;
	private DailyDirectory dailyDirectory;
	private AddDetailedRuleCondition addDetailedRuleCondition = null;
	private String returnSize;

	public String dispatchOperate() {

		return SUCCESS;
	}

	public String selfAssessment() throws Exception {
		String[] dailyIds = null;
		if (selectedDailyIds != null && !"".equals(selectedDailyIds.trim())) {
			dailyIds = selectedDailyIds.split("-");
		}
		detailedRule = evaluateService.selfAssessment(detailedRule.getId(),
				detailedRule.getSelfAssessmentScore(),
				detailedRule.getSummary(), dailyIds);
		return SUCCESS;
	}

	public String unActiveEvaluate() throws Exception {
		if (null != this.checkBoxSelect && this.checkBoxSelect.size() > 0) {
			for (int i = 0; i < checkBoxSelect.size(); i++) {
				if (null == checkBoxSelect.get(i)) {
					continue;
				}
				if (!evaluateService.validateUnactive(checkBoxSelect.get(i))) {
					return ERROR;
				}
			}
			for (int i = 0; i < checkBoxSelect.size(); i++) {
				if (null == checkBoxSelect.get(i)) {
					continue;
				}
				evaluateService.unActiveEvaluate(checkBoxSelect.get(i));
			}
		} else {
			evaluateService.unActiveEvaluate(evaluate.getId());
		}
		return SUCCESS;
	}

	public String getEvaluateResultByOrgId() throws Exception {
		evaluate = evaluateService.getEvaluateResultByOrgId(evaluate
				.getOrganization().getId());
		return SUCCESS;
	}

	/**
	 * 新增考核细则
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "addDetailedRule")
	public String addDetailedRule() throws Exception {
		detailedRule = evaluateService.addDetailedRule(detailedRule);
		return SUCCESS;
	}

	/**
	 * 修改考核细则
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "updateDetailedRule")
	public String updateDetailedRule() throws Exception {
		detailedRule = evaluateService.updateDetailedRule(detailedRule);
		return SUCCESS;
	}

	public String getReportedEvaluateResultByOrgId() throws Exception {
		evaluate = evaluateService.getReportedEvaluateResultByOrgId(evaluate
				.getOrganization().getId());
		return SUCCESS;
	}

	public String isHaveActiveEvaluate() throws Exception {
		Evaluate e = evaluateService.getSimpleEvaluateById(evaluate.getId());
		PageInfo<Evaluate> p = evaluateService
				.findChildEvaluateResultByOrgIdAndYear(e.getOrganization()
						.getId(), e.getYear(), page, rows, sidx, sord);
		List<Evaluate> nowEvaluates = p.getResult();
		for (Evaluate ee : nowEvaluates) {
			if (ee.getState() == EvaluateState.ACTIVE) {
				isHaveActive = true;
				return SUCCESS;
			}
		}
		return SUCCESS;
	}

	public String getEvaluateById() throws Exception {
		evaluate = evaluateService.getSimpleEvaluateById(evaluate.getId());
		scoreStandards = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.SCORES_STANDARDS);
		for (PropertyDict propertyDict : scoreStandards) {
			if (propertyDict.getInternalId() == ScoresStandardsType.GREAT) {
				great = scoresStandService
						.getScoresStandByEvaluateIdAndPropertyDictId(
								evaluate.getId(), propertyDict.getId());
				great.setName(propertyDict.getDisplayName());
			} else if (propertyDict.getInternalId() == ScoresStandardsType.GOOD) {
				good = scoresStandService
						.getScoresStandByEvaluateIdAndPropertyDictId(
								evaluate.getId(), propertyDict.getId());
				good.setName(propertyDict.getDisplayName());
			} else if (propertyDict.getInternalId() == ScoresStandardsType.QULIFIED) {
				qualified = scoresStandService
						.getScoresStandByEvaluateIdAndPropertyDictId(
								evaluate.getId(), propertyDict.getId());
				qualified.setName(propertyDict.getDisplayName());
			} else if (propertyDict.getInternalId() == ScoresStandardsType.FAILED) {
				failed = scoresStandService
						.getScoresStandByEvaluateIdAndPropertyDictId(
								evaluate.getId(), propertyDict.getId());
				failed.setName(propertyDict.getDisplayName());
			}
		}
		return SUCCESS;
	}

	/**
	 * 保存评分结果
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "saveEvaluate")
	public String saveDetailedRuleResult() throws Exception {
		if (evaluate.getId() == null) {
			return SUCCESS;
		}
		DetailedRuleEvaluateResult detailedRuleEvaluateResult = new DetailedRuleEvaluateResult();
		detailedRuleEvaluateResult.setDetailedRuleIds(detailedRuleIds);
		detailedRuleEvaluateResult.setScoreReason(scoreReason);
		detailedRuleEvaluateResult.setScores(scores);
		detailedRuleEvaluateResult.setSummarys(summarys);
		evaluateService.saveDetailedRuleResult(evaluate.getId(),
				detailedRuleEvaluateResult);
		evaluate = evaluateService.getSimpleEvaluateById(evaluate.getId());
		evaluateService.updateEvaluate(evaluate);// 同时做了评分等级的判断
		return SUCCESS;
	}

	public String dispatchEvaluate() throws Exception {
		long orgid = ThreadVariable.getSession().getOrganization().getId();
		evaluates = evaluateService.findEvaluateResultsByOrgId(orgid);
		return SUCCESS;
	}

	/**
	 * 删除考核细则
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "deleteDetailedRule")
	public String deleteDetailedRuleById() throws Exception {
		try {
			evaluateService.deleteDetailedRuleById(detailedRule.getId());
		} catch (Exception e) {
			logger.error("deleteDetailedRuleById ", e);
			throw new BusinessValidationException("请先删除子细则");
		}
		return SUCCESS;
	}

	public String evaluateResultList() throws Exception {
		pageInfo = evaluateService
				.findPigeOnHoleEvaluateResultsByOrgIdAndYearAndTitle(evaluate
						.getOrganization().getId(), evaluate.getYear(),
						evaluate.getTitle(), page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String myEvaluateResultList() throws Exception {
		pageInfo = evaluateService
				.findPigeOnHoleEvaluateResultsByOrgIdAndYearAndTitle(evaluate
						.getOrganization().getId(), evaluate.getYear(),
						evaluate.getTitle(), page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String standardEvaluateList() throws Exception {
		getPageInfoByState();
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private PageInfo getPageInfoByState() {
		if (condition == 0) {
			pageInfo = evaluateService
					.findStandardEvaluateByOrgIdAndYearAndTitle(evaluate
							.getOrganization().getId(), evaluate.getYear(),
							evaluate.getTitle(), page, rows, sidx, sord);
			gridPage = new GridPage(pageInfo);
		} else {
			pageInfo = evaluateService
					.findEvaluatesByOrgIdAndYearAndTitleAndState(evaluate
							.getOrganization().getId(), evaluate.getYear(),
							evaluate.getTitle(), condition - 1, page, rows,
							sidx, sord);
		}
		return pageInfo;
	}

	public String findTemplatesByOrgId() throws Exception {
		setScoreStandard();
		setScoreRange(great);
		setScoreRange(good);
		setScoreRange(qualified);
		setScoreRange(failed);
		PageInfo<Evaluate> defaultTemplate = evaluateService
				.findStandardEvaluatesByOrgId(null, page, rows, sidx, sord);
		pageInfo = evaluateService.findStandardEvaluatesByOrgId(evaluate
				.getOrganization().getId(), page, rows, sidx, sord);
		pageInfo.getResult().addAll(0, defaultTemplate.getResult());
		scoreStandards = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.SCORES_STANDARDS);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private void setScoreRange(ScoresStand scoresStand) {
		ScoresStand result = scoresStandService
				.getScoresStandByEvaluateIdAndPropertyDictId(evaluate.getId(),
						scoresStand.getPropertyDict().getId());
		if (null != result) {
			scoresStand.setStartScore(result.getStartScore());
			scoresStand.setEndScore(result.getEndScore());
		}
	}

	private void setScoreStandard() {
		scoreStandards = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.SCORES_STANDARDS);
		for (PropertyDict propertyDict : scoreStandards) {
			if (propertyDict.getInternalId() == ScoresStandardsType.GREAT) {
				great.setName(propertyDict.getDisplayName());
				great.setPropertyDict(propertyDict);
			} else if (propertyDict.getInternalId() == ScoresStandardsType.GOOD) {
				good.setName(propertyDict.getDisplayName());
				good.setPropertyDict(propertyDict);
			} else if (propertyDict.getInternalId() == ScoresStandardsType.QULIFIED) {
				qualified.setName(propertyDict.getDisplayName());
				qualified.setPropertyDict(propertyDict);
			} else if (propertyDict.getInternalId() == ScoresStandardsType.FAILED) {
				failed.setName(propertyDict.getDisplayName());
				failed.setPropertyDict(propertyDict);
			}
		}
	}

	public String getYears() throws Exception {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		optionYears[0] = year;
		optionYears[1] = year - 1;
		return SUCCESS;
	}

	public int[] getOptionYears() {
		return optionYears;
	}

	public void setOptionYears(int[] optionYears) {
		this.optionYears = optionYears;
	}

	public String updateScoresStand() throws Exception {
		evaluateService.updateEvaluateTitleAndYearById(evaluate.getId(),
				evaluate.getYear(), evaluate.getTitle());
		great.setScoresStandardType(ScoresStandardsType.GREAT);
		good.setScoresStandardType(ScoresStandardsType.GOOD);
		qualified.setScoresStandardType(ScoresStandardsType.QULIFIED);
		failed.setScoresStandardType(ScoresStandardsType.FAILED);
		scoresStandService.updateScoresStandByEvaluateIdAndPropertyDictId(
				evaluate, great);
		scoresStandService.updateScoresStandByEvaluateIdAndPropertyDictId(
				evaluate, good);
		scoresStandService.updateScoresStandByEvaluateIdAndPropertyDictId(
				evaluate, qualified);
		scoresStandService.updateScoresStandByEvaluateIdAndPropertyDictId(
				evaluate, failed);

		evaluateService.updateEvaluate(evaluate);
		return SUCCESS;
	}

	public String urgeEvaluate() throws Exception {
		evaluateService.urgeEvaluate(checkBoxSelect, evaluateIds);
		return SUCCESS;
	}

	public String findAllLowerEvaluateResultList() throws Exception {
		if (state == 0) {
			ignoreStateEvaluateResultList();
		} else if (state == 2) {
			reportEvaluateResultList();
		} else if (state == 1) {
			notReportEvaluateResultList();
		}
		return SUCCESS;
	}

	private void notReportEvaluateResultList() {
		PageInfo<Evaluate> pageInfo = evaluateService
				.findNotReportAllLowerEvaluateResultList(evaluate
						.getOrganization().getId(), evaluate.getYear(), page,
						rows, selectedDailyIds, isChildOrgGetYears);
		gridPage = new GridPage(pageInfo);
	}

	private void reportEvaluateResultList() {
		PageInfo<Evaluate> pageInfo = evaluateService
				.findReportAllLowerEvaluateResultList(evaluate
						.getOrganization().getId(), evaluate.getYear(), page,
						rows, selectedDailyIds, isChildOrgGetYears);
		gridPage = new GridPage(pageInfo);
	}

	private void ignoreStateEvaluateResultList() {
		PageInfo<Evaluate> pageInfo = evaluateService
				.findAllLowerEvaluateResultList(evaluate.getOrganization()
						.getId(), evaluate.getYear(), page, rows,
						selectedDailyIds, isChildOrgGetYears);
		gridPage = new GridPage(pageInfo);
	}

	public String deleteEvaluateById() throws Exception {
		evaluateService.deleteEvaluateById(evaluate.getId());
		return SUCCESS;
	}

	@EncryptAnnotation
	public String deleteEvaluateByIdByEncrypt() {
		evaluateService.deleteEvaluateById(evaluate.getId());
		return SUCCESS;
	}

	public String deleteMultiEvaluateByIds() throws Exception {
		for (Long evaluateId : checkBoxSelect) {
			if (null == evaluateId) {
				continue;
			}
			evaluateService.deleteEvaluateById(evaluateId);
		}
		return SUCCESS;
	}

	public String copyStandardEvaluate() throws Exception {
		evaluate = evaluateService.copyDetailedRulesToNewEvaluate(evaluate,
				detailedRuleIds);
		great.setScoresStandardType(ScoresStandardsType.GREAT);
		good.setScoresStandardType(ScoresStandardsType.GOOD);
		qualified.setScoresStandardType(ScoresStandardsType.QULIFIED);
		failed.setScoresStandardType(ScoresStandardsType.FAILED);
		evaluateService.addScoresStands(evaluate, great, good, qualified,
				failed, ThreadVariable.getUser().getName());
		return SUCCESS;
	}

	public String pigeonholeEvaluate() throws Exception {
		evaluateService.pigeonholeEvaluate(evaluate.getId());
		return SUCCESS;
	}

	public String reportSelfAssessmentResult() throws Exception {
		evaluateService.reportSelfAssessmentResult(evaluate.getId(),
				evaluate.getSelfAssessmentTotalScore());
		return SUCCESS;
	}

	public String activeStandardEvaluate() throws Exception {
		evaluateService.activeStandardEvaluate(evaluate.getId());
		return SUCCESS;
	}

	public String popedomEvaluateUsedByYear() throws Exception {
		evaluate = evaluateService.getSimpleEvaluateById(evaluate.getId());
		popedomEvaluateUsed = evaluateService
				.isPopedomEvaluateUsedByYear(evaluate.getYear());
		return SUCCESS;
	}

	public String popedomEvaluateUsedByEvaluateId() throws Exception {
		popedomEvaluateUsed = evaluateService
				.isPopedomEvaluateUsedByEvaluateId(evaluate.getId());
		return SUCCESS;
	}

	public String dispatchDetailedRule() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
		} else {
			detailedRule = evaluateService
					.getSimpleDetailedRuleById(detailedRule.getId());
			if (DialogMode.EDIT_MODE.equals(mode)) {
				return "edit";
			}
		}
		return SUCCESS;
	}

	public String getDetailedRulesByEvaluateId() throws Exception {
		List<DetailedRuleTreeGridData> rules = evaluateService
				.getDetailedRulesByEvaluateId(evaluate.getId());
		for (DetailedRuleTreeGridData detailedRuleTreeGridData : rules) {
			detailedRuleTreeGridData.setDailyLogs(workingRecordService
					.findDailyLogVoByDetailedRuleId(detailedRuleTreeGridData
							.getId()));

		}
		gridPage = new GridPage();
		gridPage.setRows(rules);
		return SUCCESS;
	}

	@EncryptAnnotation
	public String getDetailedRulesByEvaluateIdByEncrypt() throws Exception {
		List<DetailedRuleTreeGridData> rules = evaluateService
				.getDetailedRulesByEvaluateId(evaluate.getId());
		for (DetailedRuleTreeGridData detailedRuleTreeGridData : rules) {
			detailedRuleTreeGridData.setDailyLogs(workingRecordService
					.findDailyLogVoByDetailedRuleId(detailedRuleTreeGridData
							.getId()));

		}
		gridPage = new GridPage();
		gridPage.setRows(rules);
		return SUCCESS;
	}

	public String dailogSize() throws Exception {
		if (workingRecord != null && workingRecord.getDailyDirectory() != null
				&& workingRecord.getDailyDirectory().getId() != null) {
			dailyDirectory = dailyDirectoryService
					.getSimpleDailyDirectoryById(workingRecord
							.getDailyDirectory().getId());
			judgeSize(dailyDirectory);
			return SUCCESS;

		}
		return ERROR;
	}

	private void judgeSize(DailyDirectory dailyDirectory) {
		if (dailyDirectory.getType() != null) {
			PropertyDict propertyDict = propertyDictService
					.getPropertyDictById(dailyDirectory.getType().getId());
			switch (propertyDict.getInternalId()) {
			case 0:
				returnSize = "650_370";
				break;
			case 1:
				returnSize = "650_370";
				break;
			case 2:
				returnSize = "650_370";
				break;
			case 3:
				returnSize = "850_500";
				break;
			case 4:
				judgeReportSize();
				break;
			case 5:
				returnSize = "650_370";
				break;
			case 6:
				returnSize = "650_370";
				break;
			default:
				returnSize = "650_370";
			}
		}
	}

	private void judgeReportSize() {
		Organization organization = null;
		if (null != evaluate) {
			organization = organizationDubboService.getSimpleOrgById(evaluate
					.getOrganization().getId());
		} else {
			organization = organizationDubboService.getSimpleOrgById(ThreadVariable
					.getUser().getOrganization().getId());
		}

		PropertyDict propertyDict = propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId());

		switch (propertyDict.getInternalId()) {
		case 1:
			returnSize = "800_500";
			break;
		case 2:
			returnSize = "820_500";
			break;
		case 3:
			returnSize = "980_500";
			break;
		case 4:
			returnSize = "1000_500";
			break;
		case 5:
			returnSize = "1000_500";
			break;
		case 6:
			returnSize = "1000_500";
			break;
		default:
			returnSize = "980_600";
		}
	}

	public String redirectForWorkRecord() throws Exception {
		if (workingRecord != null && workingRecord.getDailyDirectory() != null
				&& workingRecord.getDailyDirectory().getId() != null) {
			dailyDirectory = dailyDirectoryService
					.getSimpleDailyDirectoryById(workingRecord
							.getDailyDirectory().getId());
			if (null != evaluate)
				workingRecord.setDailyDirectory(dailyDirectory);
			return prepareSubItem(dailyDirectory);
		}
		return ERROR;
	}

	private String prepareSubItem(DailyDirectory dailyDirectory) {
		String returnString = null;
		if (dailyDirectory.getType() != null) {
			PropertyDict propertyDict = propertyDictService
					.getPropertyDictById(dailyDirectory.getType().getId());
			switch (propertyDict.getInternalId()) {
			case 0:
				returnString = "meet";
				break;
			case 1:
				returnString = "file";
				break;
			case 2:
				returnString = "activities";
				break;
			case 3:
				returnString = "check";
				break;
			case 4:
				returnString = "issuedeal";
				break;
			case 5:
				returnString = "investigation";
				break;
			case 6:
				returnString = "significantissuedeal";
				break;
			default:
				returnString = ERROR;
			}
		}
		return returnString;
	}

	public String getReportedDetailedRulesByEvaluateId() throws Exception {
		Evaluate evaluateResult = evaluateService
				.getSimpleEvaluateById(evaluate.getId());
		if (evaluateResult.getState() != EvaluateState.REPORT) {
			return ERROR;
		}
		List<DetailedRuleTreeGridData> rules = evaluateService
				.getDetailedRulesByEvaluateId(evaluate.getId());
		for (DetailedRuleTreeGridData detailedRuleTreeGridData : rules) {
			detailedRuleTreeGridData.setDailyLogs(workingRecordService
					.findDailyLogVoByDetailedRuleId(detailedRuleTreeGridData
							.getId()));
		}
		gridPage = new GridPage();
		gridPage.setRows(rules);
		return SUCCESS;
	}

	/**
	 * 对评判结果进行归档
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "pigeonholeEvaluate")
	public String ispigeonholeEvaluateById() throws Exception {
		evaluate.setState(EvaluateState.PIGEONHOLE);
		evaluate = evaluateService.ispigeonholeEvaluateById(evaluate);
		evaluate = evaluateService.getSimpleEvaluateById(evaluate.getId());
		evaluateService.updateEvaluate(evaluate);// 同时做了评分等级的判断
		return SUCCESS;
	}

	public String backEvaluate() throws Exception {
		if (null == evaluate.getId()) {
			return ERROR;
		}
		evaluateService.backEvaluate(evaluate.getId());
		return SUCCESS;
	}

	public String getEvaluateAllYears() throws Exception {

		List<Evaluate> evaluateForYearList = null;

		if (Boolean.valueOf(isChildOrgGetYears)
				&& organizationDubboService.getSimpleOrgById(
						evaluate.getOrganization().getId()).getParentOrg() != null) {
			evaluateForYearList = evaluateService
					.findEvaluateResultsByOrgId(organizationDubboService
							.getSimpleOrgById(
									evaluate.getOrganization().getId())
							.getParentOrg().getId());
		} else {
			evaluateForYearList = evaluateService
					.findEvaluateResultsByOrgId(evaluate.getOrganization()
							.getId());
		}
		for (Evaluate evaluate : evaluateForYearList) {
			if (null == evaluate.getStandardEvaluate())
				evaluateYears.add(evaluate.getYear());
		}
		return SUCCESS;
	}

	public String findSelfEvaluatesByOrgIdAndYearAndState() throws Exception {
		evaluates = evaluateService.findUseEvaluatesByOrgIdAndYearAndState(
				evaluate.getYear(), evaluate.getOrganization().getId(),
				EvaluateState.ACTIVE);
		if (!evaluates.isEmpty()) {
			evaluate = evaluates.get(0);
		}
		return SUCCESS;
	}

	public String findReportEvaluatesByOrgIdAndYearAndState() throws Exception {
		Long level = organizationDubboService
				.getOrgAndLevelInfo(evaluate.getOrganization().getId())
				.getOrgLevel().getId();
		Long userLevel = organizationDubboService
				.getOrgAndLevelInfo(
						ThreadVariable.getSession().getOrganization().getId())
				.getOrgLevel().getId();
		// 如果选择的层级等于当前用户的层级，直接返回不用查询
		if (level.equals(userLevel)) {
			return SUCCESS;
		}
		evaluates = evaluateService.findUseEvaluatesByOrgIdAndYearAndState(
				evaluate.getYear(), evaluate.getOrganization().getId(),
				EvaluateState.REPORT);
		if (!evaluates.isEmpty()) {
			evaluate = evaluates.get(0);
		}
		return SUCCESS;
	}

	public String getCountSelfAssessmentScore() throws Exception {
		Long count = evaluateService.getCountSelfAssessmentScore(evaluate);
		if (count == 0) {
			return SUCCESS;
		}
		return ERROR;
	}

	public String validateUnactive() throws Exception {
		List<Evaluate> evaluates = evaluateService
				.findEvaluatesByStandardEvaluateId(evaluate.getId());
		for (Evaluate evaluateResult : evaluates) {
			if (evaluateResult.getState() == EvaluateState.PIGEONHOLE) {
				return ERROR;
			}
		}
		return SUCCESS;
	}

	public String validateTypeOfChildDetailedRule() throws Exception {
		addDetailedRuleCondition = evaluateService
				.validateTypeOfChildDetailedRule(detailedRule);
		return SUCCESS;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long[] getDetailedRuleIds() {
		return detailedRuleIds;
	}

	public void setDetailedRuleIds(Long[] detailedRuleIds) {
		this.detailedRuleIds = detailedRuleIds;
	}

	public String[] getScores() {
		return scores;
	}

	public void setScores(String[] scores) {
		this.scores = scores;
	}

	public String[] getSummarys() {
		return summarys;
	}

	public void setSummarys(String[] summarys) {
		this.summarys = summarys;
	}

	public List<Evaluate> getEvaluates() {
		return evaluates;
	}

	public void setEvaluates(List<Evaluate> evaluates) {
		this.evaluates = evaluates;
	}

	public DetailedRule getDetailedRule() {
		return detailedRule;
	}

	public void setDetailedRule(DetailedRule detailedRule) {
		this.detailedRule = detailedRule;
	}

	public ScoresStand getGreat() {
		return great;
	}

	public void setGreat(ScoresStand great) {
		this.great = great;
	}

	public ScoresStand getGood() {
		return good;
	}

	public void setGood(ScoresStand good) {
		this.good = good;
	}

	public ScoresStand getQualified() {
		return qualified;
	}

	public void setQualified(ScoresStand qualified) {
		this.qualified = qualified;
	}

	public ScoresStand getFailed() {
		return failed;
	}

	public void setFailed(ScoresStand failed) {
		this.failed = failed;
	}

	public List<Long> getStandards() {
		return standards;
	}

	public void setStandards(List<Long> standards) {
		this.standards = standards;
	}

	public List<PropertyDict> getScoreStandards() {
		return scoreStandards;
	}

	public void setScoreStandards(List<PropertyDict> scoreStandards) {
		this.scoreStandards = scoreStandards;
	}

	public PageInfo<Evaluate> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<Evaluate> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}

	public String getSelectedDailyIds() {
		return selectedDailyIds;
	}

	public void setSelectedDailyIds(String selectedDailyIds) {
		this.selectedDailyIds = selectedDailyIds;
	}

	public void setPopedomEvaluateUsed(boolean popedomEvaluateUsed) {
		this.popedomEvaluateUsed = popedomEvaluateUsed;
	}

	public boolean isPopedomEvaluateUsed() {
		return popedomEvaluateUsed;
	}

	public Boolean getIsHaveActive() {
		return isHaveActive;
	}

	public void setIsHaveActive(Boolean isHaveActive) {
		this.isHaveActive = isHaveActive;
	}

	public List<Long> getCheckBoxSelect() {
		return checkBoxSelect;
	}

	public void setCheckBoxSelect(List<Long> checkBoxSelect) {
		this.checkBoxSelect = checkBoxSelect;
	}

	public WorkingRecord getWorkingRecord() {
		return workingRecord;
	}

	public void setWorkingRecord(WorkingRecord workingRecord) {
		this.workingRecord = workingRecord;
	}

	public String getReturnSize() {
		return returnSize;
	}

	public void setReturnSize(String returnSize) {
		this.returnSize = returnSize;
	}

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public AddDetailedRuleCondition getAddDetailedRuleCondition() {
		return addDetailedRuleCondition;
	}

	public void setAddDetailedRuleCondition(
			AddDetailedRuleCondition addDetailedRuleCondition) {
		this.addDetailedRuleCondition = addDetailedRuleCondition;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public List<Long> getEvaluateIds() {
		return evaluateIds;
	}

	public void setEvaluateIds(List<Long> evaluateIds) {
		this.evaluateIds = evaluateIds;
	}

	public String[] getScoreReason() {
		return scoreReason;
	}

	public void setScoreReason(String[] scoreReason) {
		this.scoreReason = scoreReason;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getIsChildOrgGetYears() {
		return isChildOrgGetYears;
	}

	public void setIsChildOrgGetYears(String isChildOrgGetYears) {
		this.isChildOrgGetYears = isChildOrgGetYears;
	}

	public HashSet<String> getEvaluateYears() {
		return evaluateYears;
	}

	public void setEvaluateYears(HashSet<String> evaluateYears) {
		this.evaluateYears = evaluateYears;
	}

	public List<String> getEvaluateYearsList() {
		return evaluateYearsList;
	}

	public void setEvaluateYearsList(List<String> evaluateYearsList) {
		this.evaluateYearsList = evaluateYearsList;
	}

}
