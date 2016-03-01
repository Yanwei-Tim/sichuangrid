package com.tianque.newVillage.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.newVillage.domain.ReportDataSummary;
import com.tianque.newVillage.service.NewVillageAcceptanceService;
import com.tianque.newVillage.vo.NewVillageAssessmentVo;

/***
 * 新农村验收结果报表处理
 * @author wangchao
 *
 */
@Scope("prototype")
@Namespace("/newVillageAcceptanceManage")
@Controller("newVillageAcceptanceController")
public class NewVillageAcceptanceController extends BaseAction{

	private Organization organization;
	private Integer year;
	private List<NewVillageAssessmentVo> listVo;
	@Autowired
	private  NewVillageAcceptanceService newVillageAcceptanceService;
	/**
	 * 查询考核表信息
	 */
	@Action(value = "findNewVillageAssessmentVoForList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"listVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findNewVillageAssessmentVoForList() throws Exception {
		if (year == null) {
			errorMessage = "查询出错，未获得年份信息";
			return ERROR;
		}
		listVo = newVillageAcceptanceService.findNewVillageAssessmentVoList(year);
		return SUCCESS;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public List<NewVillageAssessmentVo> getListVo() {
		return listVo;
	}
	public void setListVo(List<NewVillageAssessmentVo> listVo) {
		this.listVo = listVo;
	}
	
	
}
