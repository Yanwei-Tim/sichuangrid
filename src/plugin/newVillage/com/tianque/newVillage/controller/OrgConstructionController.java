package com.tianque.newVillage.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.newVillage.domain.OrganizationConstruction;
import com.tianque.newVillage.service.OrganizationConstructionService;

@Scope("prototype")
@Namespace("/baseinfo/orgConstructionManage")
@Controller("orgConstructionController")
public class OrgConstructionController extends BaseAction {
	private OrganizationConstruction orgConstruction;
	@Autowired
	private OrganizationConstructionService orgConstructionService;

	@Action(value = "maintainOrgConstruction", results = {
			@Result(name = "success", type = "json", params = { "root",
					"orgConstruction", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainOrgConstruction() throws Exception {
		if (orgConstruction == null
				|| orgConstruction.getOrganization() == null
				|| orgConstruction.getOrganization().getId() == null) {
			errorMessage = "新增出错，请重试";
			return ERROR;
		}
		if (orgConstruction.getId() != null) {
			orgConstructionService.updateOrgConstruction(orgConstruction);
		} else {
			orgConstruction = orgConstructionService
					.addOrgConstruction(orgConstruction);
		}
		return SUCCESS;
	}

	public OrganizationConstruction getOrgConstruction() {
		return orgConstruction;
	}

	public void setOrgConstruction(OrganizationConstruction orgConstruction) {
		this.orgConstruction = orgConstruction;
	}

}
