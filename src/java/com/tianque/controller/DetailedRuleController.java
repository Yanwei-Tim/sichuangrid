package com.tianque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.vo.DailyLogVo;
import com.tianque.core.base.BaseAction;
import com.tianque.domain.DetailedRule;
import com.tianque.service.DetailedRuleService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.service.WorkingRecordService;

@Transactional
@Scope("prototype")
@Controller("detailedRuleController")
public class DetailedRuleController extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DetailedRule detailedRule;
	private String selectedDailyIds;
	private Long[] dialyLogTypeInternalIds;

	@Autowired
	private DetailedRuleService detailedRuleService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private WorkingRecordService workingRecordService;

	private List<DailyLogVo> dailyLogs;

	public String findDailyLogsByDetailedRuleId() throws Exception {
		dailyLogs = workingRecordService
				.findDailyLogVoByDetailedRuleId(detailedRule.getId());
		return SUCCESS;
	}

	public String getDetailedRuleById() throws Exception {
		detailedRule = detailedRuleService
				.getSimpleDetailedRuleById(detailedRule.getId());
		detailedRule.setRuleType(propertyDictService
				.getPropertyDictById(detailedRule.getRuleType().getId()));
		return SUCCESS;
	}

	public String findDialyLogTypesByDetailedRuleId() throws Exception {
		dialyLogTypeInternalIds = detailedRuleService
				.findDialyLogTypesByDetailedRuleId(detailedRule.getId());
		return SUCCESS;
	}

	public DetailedRule getDetailedRule() {
		return detailedRule;
	}

	public void setDetailedRule(DetailedRule detailedRule) {
		this.detailedRule = detailedRule;
	}

	public String getSelectedDailyIds() {
		return selectedDailyIds;
	}

	public void setSelectedDailyIds(String selectedDailyIds) {
		this.selectedDailyIds = selectedDailyIds;
	}

	public Long[] getDialyLogTypeInternalIds() {
		return dialyLogTypeInternalIds;
	}

	public void setDialyLogTypeInternalIds(Long[] dialyLogTypeInternalIds) {
		this.dialyLogTypeInternalIds = dialyLogTypeInternalIds;
	}

	public List<DailyLogVo> getDailyLogs() {
		return dailyLogs;
	}

	public void setDailyLogs(List<DailyLogVo> dailyLogs) {
		this.dailyLogs = dailyLogs;
	}

}
