package com.tianque.newVillage.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.newVillage.domain.IndustryDevelopment;
import com.tianque.newVillage.service.IndustryDevelopmentService;

@Scope("prototype")
@Namespace("/baseinfo/industryDevelopmentManage")
@Controller("industryDevelopmentController")
public class IndustryDevelopmentController extends BaseAction {
	private IndustryDevelopment industryDevelopment;
	@Autowired
	private IndustryDevelopmentService industryDevelopmentService;

	@Action(value = "maintainIndustryDevelopment", results = {
			@Result(name = "success", type = "json", params = { "root",
					"industryDevelopment", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainIndustryDevelopment() throws Exception {
		if (industryDevelopment == null
				|| industryDevelopment.getOrganization() == null
				|| industryDevelopment.getOrganization().getId() == null) {
			errorMessage = "新增出错，请重试";
			return ERROR;
		}
		if (industryDevelopment.getId() != null) {
			industryDevelopmentService
					.updateIndustryDevelopment(industryDevelopment);
		} else {
			industryDevelopment = industryDevelopmentService
					.addIndustryDevelopment(industryDevelopment);
		}
		return SUCCESS;
	}

	public IndustryDevelopment getIndustryDevelopment() {
		return industryDevelopment;
	}

	public void setIndustryDevelopment(IndustryDevelopment industryDevelopment) {
		this.industryDevelopment = industryDevelopment;
	}

}
