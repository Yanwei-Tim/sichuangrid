package com.tianque.newVillage.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.newVillage.domain.Infrastructure;
import com.tianque.newVillage.service.InfrastructureService;

@Scope("prototype")
@Namespace("/baseinfo/infrastructureManage")
@Controller("infrastructureController")
public class InfrastructureController extends BaseAction {
	private Infrastructure infrastructure;
	@Autowired
	private InfrastructureService infrastructureService;

	@Action(value = "maintainInfrastructure", results = {
			@Result(name = "success", type = "json", params = { "root",
					"infrastructure", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainInfrastructure() throws Exception {
		if (infrastructure == null || infrastructure.getOrganization() == null
				|| infrastructure.getOrganization().getId() == null) {
			errorMessage = "新增出错，请重试";
			return ERROR;
		}
		if (infrastructure.getId() != null) {
			infrastructureService.updateInfrastructure(infrastructure);
		} else {
			infrastructure = infrastructureService
					.addInfrastructure(infrastructure);
		}
		return SUCCESS;
	}

	public Infrastructure getInfrastructure() {
		return infrastructure;
	}

	public void setInfrastructure(Infrastructure infrastructure) {
		this.infrastructure = infrastructure;
	}

}
