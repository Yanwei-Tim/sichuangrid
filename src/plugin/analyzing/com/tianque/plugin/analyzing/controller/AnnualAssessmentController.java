package com.tianque.plugin.analyzing.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.plugin.analyzing.domain.AnnualAssessment;
import com.tianque.plugin.analyzing.service.AnnualAssessmentService;

@Scope("request")
@Namespace("/baseInfoStat/annualAssessmentManage")
@Controller("annualAssessmentController")
public class AnnualAssessmentController extends BaseAction {

	private List<AnnualAssessment> dataList;

	@Autowired
	private AnnualAssessmentService annualAssessmentService;

	@Action(value = "getAnnualAssessmentReportForList", results = { @Result(name = "success", type = "json", params = {
			"root", "dataList", "ignoreHierarchy", "false" }) })
	public String getAnnualAssessmentReportForList() throws Exception {
		dataList = annualAssessmentService.getAnnualAssessmentForList();
		return SUCCESS;
	}

	public List<AnnualAssessment> getDataList() {
		return dataList;
	}

	public void setDataList(List<AnnualAssessment> dataList) {
		this.dataList = dataList;
	}

}
