package com.tianque.init.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.domain.DetailedRule;
import com.tianque.domain.Evaluate;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.DetailedRuleType;
import com.tianque.domain.property.EvaluateType;
import com.tianque.init.Initialization;
import com.tianque.service.DetailedRuleService;
import com.tianque.service.EvaluateService;
import com.tianque.service.state.EvaluateState;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

public class DetailedRuleInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private DetailedRuleService detailedRuleService;
	private EvaluateService evaluateService;
	private OrganizationDubboService organizationDubboService;
	private PropertyDictService propertyDictService;

	public DetailedRuleInitialization(DetailedRuleService detailedRuleService,
			EvaluateService evaluateService, OrganizationDubboService organizationDubboService,
			PropertyDictService propertyDictService) {
		this.detailedRuleService = detailedRuleService;
		this.evaluateService = evaluateService;
		this.organizationDubboService = organizationDubboService;
		this.propertyDictService = propertyDictService;
	}

	@Override
	public void init() throws Exception {
		Evaluate evaluate = createMockEvaluate(organizationDubboService
				.findOrganizationsByParentId(null).get(0));
		DetailedRule detailedRule1 = createMockDetailedRule(evaluate, "落实综治领导责任制情况考核", 80, 1L, null);
		DetailedRule detailedRule11 = createMockDetailedRule(evaluate, "党委、政府对综治工作领导有力", 60, 1L,
				detailedRule1);
		createMockDetailedRule(evaluate, "党委、政府研究综治工作少于2次的每少一次扣5分", null, 1L, detailedRule11);
		createMockDetailedRule(evaluate, "对中央和省委、省综治委有关综治工作重大部署，党委、政府在一个月内未落实传达、贯彻措施的，扣10分", null,
				2L, detailedRule11);
		createMockDetailedRule(evaluate, "党政主要领导实地调研、指导综治工作少于2次的，每少一次扣5分", null, 3L, detailedRule11);
		createMockDetailedRule(evaluate, "党委、政府分管领导不明确的，扣10分；因人事变动，分管领导3个月内未明确的，扣5分", null, 4L,
				detailedRule11);
		createMockDetailedRule(
				evaluate,
				"党委、政府未按照省综治委、省纪委、省委组织部、省人力社保厅、声监察厅等五部门以及省综治委与省委组织部下发的有关文件要求，将社会治安综合治理工作纳入党政领导干部的实绩考核内容的，扣5分",
				null, 5L, detailedRule11);
		createMockDetailedRule(evaluate, "未建立综治实绩档案的，扣5分", null, 6L, detailedRule11);
		createMockDetailedRule(evaluate,
				"未落实组织部门在提拔考核干部时，或者人事部门在办理干部评先、受奖、晋级时，书面征求综治部门意见规定的，分别扣5分", null, 7L,
				detailedRule11);
		createMockDetailedRule(evaluate, "未表彰奖励综治工作成绩突出的地方、单位和个人的，扣5分", null, 8L, detailedRule11);
		createMockDetailedRule(evaluate, "对发生造成重大社会影响的案（事）件，按照有关政策法律规定应予责任查究和“一票否决”而未执行的，扣5分",
				null, 9L, detailedRule11);

		createMockDetailedRule(evaluate, "定期签订综治目标管理责任书", 20, 2L, detailedRule1);
		createMockDetailedRule(evaluate, "预防化解矛盾纠纷工作情况", 170, 2L, null);
		createMockDetailedRule(evaluate, "基层平安创建和社会治安防控体系建设情况考核", 300, 3L, null);
		createMockDetailedRule(evaluate, "综治基层基础工作情况", 300, 4L, null);
		createMockDetailedRule(evaluate, "综治工作制度建设情况", 90, 5L, null);
		createMockDetailedRule(evaluate, "人民群众对社会治安满意程度", 40, 6L, null);
		createMockDetailedRule(evaluate, "综治工作创新", 10, 7L, null);
	}

	private DetailedRule createMockDetailedRule(Evaluate evaluate, String content,
			Integer standardSocre, Long seq, DetailedRule parentRule) {
		DetailedRule detailedRule = new DetailedRule();
		detailedRule.setStandardScore(standardSocre);
		detailedRule.setSeq(seq);
		detailedRule.setContent(content);
		detailedRule.setEvaluate(evaluate);
		detailedRule.setParentRule(parentRule);

		detailedRule.setRuleType(propertyDictService.findPropertyDictByDomainNameAndInternalId(
				DetailedRuleType.DETAILED_RULE_TYPE_KEY, DetailedRuleType.OTHER_TYPE).get(0));
		return detailedRuleService.addDetailedRuleRecursion(detailedRule);
	}

	private Evaluate createMockEvaluate(Organization organization) {
		Evaluate evaluate = new Evaluate();
		evaluate.setYear("2011");
		evaluate.setTitle("2011年度全国考核标准");
		evaluate.setTotalScore(90);
		evaluate.setState(EvaluateState.NONE);
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(EvaluateType.EVALUATE_TYPE_KEY,
						EvaluateType.STANDARD_EVALUATE);
		evaluate.setEvaluateType(propertyDicts.get(0));
		return evaluateService.addEvaluate(evaluate);
	}

}
