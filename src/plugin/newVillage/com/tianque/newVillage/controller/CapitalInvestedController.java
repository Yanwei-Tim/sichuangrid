package com.tianque.newVillage.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.newVillage.domain.CapitalInvested;
import com.tianque.newVillage.service.CapitalInvestedService;

@Scope("prototype")
@Namespace("/baseinfo/capitalInvestedManage")
@Controller("capitalInvestedController")
public class CapitalInvestedController extends BaseAction {
	private CapitalInvested capitalInvested;
	@Autowired
	private CapitalInvestedService capitalInvestedService;

	@Action(value = "maintainCapitalInvested", results = {
			@Result(name = "success", type = "json", params = { "root",
					"capitalInvested", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainCapitalInvested() throws Exception {
		if (capitalInvested == null
				|| capitalInvested.getOrganization() == null
				|| capitalInvested.getOrganization().getId() == null) {
			errorMessage = "新增出错，请重试";
			return ERROR;
		}
		if (capitalInvested.getId() != null) {
			capitalInvestedService.updateCapitalInvested(capitalInvested);
		} else {
			capitalInvested = capitalInvestedService
					.addCapitalInvested(capitalInvested);
		}
		return SUCCESS;
	}

	public CapitalInvested getCapitalInvested() {
		return capitalInvested;
	}

	public void setCapitalInvested(CapitalInvested capitalInvested) {
		this.capitalInvested = capitalInvested;
	}

}
