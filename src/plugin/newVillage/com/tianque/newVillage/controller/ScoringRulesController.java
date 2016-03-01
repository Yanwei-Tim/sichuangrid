package com.tianque.newVillage.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.newVillage.domain.ScoringRules;
import com.tianque.newVillage.service.ScoringRulesService;

@Scope("prototype")
@Namespace("/baseinfo/scoringRulesManage")
@Controller("scoringRulesController")
public class ScoringRulesController extends BaseAction {
	private ScoringRules scoringRules;
	@Autowired
	private ScoringRulesService scoringRulesService;

	private String ids = "";// 记录删除id

	private String[] delCorrespondingValue;// 删除的对应字段名

	private Integer[] delCalculationSymbol;// 删除的对应计算符

	private List<ScoringRules> list;

	private String[] correspondingValue;// 对应字段名

	private String[] calculationSymbol;// 计算符

	private String[] scoringRangeStart;// 范围值始

	private String[] scoringRangeEnd;// 范围值末

	private String[] fraction;// 分数

	private Integer maxFraction;// 得分总值

	/**
	 * 新增评分规则信息
	 * 
	 * @return
	 */
	@Action(value = "saveScoringRules", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String saveScoringRules() {
		// 新增时删除老数据
		scoringRulesService.addScoringRules(correspondingValue,
				calculationSymbol, scoringRangeStart, scoringRangeEnd,
				fraction, maxFraction);
		return SUCCESS;
	}

	/**
	 * 获得评分规则列表信息
	 * 
	 * @return
	 */
	@Action(value = "getListInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "list",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String getListInfo() {
		list = scoringRulesService.getScoringRulesListByValues();
		return SUCCESS;
	}

	public ScoringRules getScoringRules() {
		return scoringRules;
	}

	public void setScoringRules(ScoringRules scoringRules) {
		this.scoringRules = scoringRules;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<ScoringRules> getList() {
		return list;
	}

	public void setList(List<ScoringRules> list) {
		this.list = list;
	}

	public String[] getCorrespondingValue() {
		return correspondingValue;
	}

	public void setCorrespondingValue(String[] correspondingValue) {
		this.correspondingValue = correspondingValue;
	}

	public String[] getCalculationSymbol() {
		return calculationSymbol;
	}

	public void setCalculationSymbol(String[] calculationSymbol) {
		this.calculationSymbol = calculationSymbol;
	}

	public String[] getScoringRangeStart() {
		return scoringRangeStart;
	}

	public void setScoringRangeStart(String[] scoringRangeStart) {
		this.scoringRangeStart = scoringRangeStart;
	}

	public String[] getScoringRangeEnd() {
		return scoringRangeEnd;
	}

	public void setScoringRangeEnd(String[] scoringRangeEnd) {
		this.scoringRangeEnd = scoringRangeEnd;
	}

	public String[] getFraction() {
		return fraction;
	}

	public void setFraction(String[] fraction) {
		this.fraction = fraction;
	}

	public String[] getDelCorrespondingValue() {
		return delCorrespondingValue;
	}

	public void setDelCorrespondingValue(String[] delCorrespondingValue) {
		this.delCorrespondingValue = delCorrespondingValue;
	}

	public Integer[] getDelCalculationSymbol() {
		return delCalculationSymbol;
	}

	public void setDelCalculationSymbol(Integer[] delCalculationSymbol) {
		this.delCalculationSymbol = delCalculationSymbol;
	}

	public Integer getMaxFraction() {
		return maxFraction;
	}

	public void setMaxFraction(Integer maxFraction) {
		this.maxFraction = maxFraction;
	}

}
