package com.tianque.plugin.analysisNew.controller;

import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.plugin.analysisNew.domain.BaseSituationStatement;
import com.tianque.plugin.analysisNew.service.BaseSituationStatementNewService;

@Scope("prototype")
@Namespace("/baseInfo/baseSituationStatementNew")
@Controller("baseSituationStatementNewController")
public class BaseSituationStatementNewController extends StatisticAction {

	private List<BaseSituationStatement> listColumnVo;
	@Autowired
	private BaseSituationStatementNewService baseSituationStatementNewService;

	private Integer statisticsType;

	private String sortName;// 排序的字段
	private String sort;// 升序还是降序

	@Action(value = "baseSituationStatementList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"listColumnVo" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String baseSituationStatementList() throws Exception {
		if (orgId == null) {
			errorMessage = "组织机构参数不存在";
			return ERROR;
		}
		if (validateOtherDate()) {
			errorMessage = "所选时间不正确，请重新选择";
			return ERROR;
		}
		listColumnVo = baseSituationStatementNewService
				.findBaseSituationStatementListByCondition(orgId, year, month,
						statisticsType);
		return SUCCESS;
	}

	/***
	 * 生成报表
	 */
	@Action(value = "baseSituationStatementReportGeneration", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String baseSituationStatementReportGeneration() throws Exception {
		if (validateOtherDate()) {
			errorMessage = "所选时间不正确，请重新选择";
			return ERROR;
		}
		baseSituationStatementNewService.addBaseStituationStatementHistory(
				year, month);
		return SUCCESS;
	}

	/***
	 * 字段排序 除了按组织机构排序外，所有字段排序通用
	 */
	@Action(value = "basesituationStatementSort", results = {
			@Result(name = "success", type = "json", params = { "root",
					"listColumnVo" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String basesituationStatementSort() throws Exception {
		if (orgId == null) {
			errorMessage = "请选择组织机构信息";
			return ERROR;
		}
		if (validateOtherDate()) {
			errorMessage = "所选时间不正确，请重新选择";
			return ERROR;
		}
		listColumnVo = baseSituationStatementNewService
				.findBaseSituationStatementListBySort(orgId, year, month,
						statisticsType, sortName, sort);
		return SUCCESS;
	}

	/***
	 * 验证所统计的时间段是否大于当前月
	 * 
	 * @return
	 */
	private boolean validateOtherDate() {
		return year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public List<BaseSituationStatement> getListColumnVo() {
		return listColumnVo;
	}

	public void setListColumnVo(List<BaseSituationStatement> listColumnVo) {
		this.listColumnVo = listColumnVo;
	}

	public Integer getStatisticsType() {
		return statisticsType;
	}

	public void setStatisticsType(Integer statisticsType) {
		this.statisticsType = statisticsType;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
