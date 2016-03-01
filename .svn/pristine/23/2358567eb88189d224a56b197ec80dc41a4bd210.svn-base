package com.tianque.newVillage.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.newVillage.domain.EnvironmentalReform;
import com.tianque.newVillage.service.EnvironmentalReformService;

@Scope("prototype")
@Namespace("/baseinfo/environmentalReformManage")
@Controller("environmentalReformController")
public class EnvironmentalReformController extends BaseAction {
	private EnvironmentalReform environmentalReform;
	@Autowired
	private EnvironmentalReformService environmentalReformService;

	@Action(value = "maintainEnvironmentalReform", results = {
			@Result(name = "success", type = "json", params = { "root",
					"environmentalReform", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainEnvironmentalReform() throws Exception {
		if (environmentalReform == null
				|| environmentalReform.getOrganization() == null
				|| environmentalReform.getOrganization().getId() == null) {
			errorMessage = "新增出错，请重试";
			return ERROR;
		}
		if (environmentalReform.getId() != null) {
			environmentalReformService
					.updateEnvironmentalReform(environmentalReform);
		} else {
			environmentalReform = environmentalReformService
					.addEnvironmentalReform(environmentalReform);
		}
		return SUCCESS;
	}

	public EnvironmentalReform getEnvironmentalReform() {
		return environmentalReform;
	}

	public void setEnvironmentalReform(EnvironmentalReform environmentalReform) {
		this.environmentalReform = environmentalReform;
	}

}
