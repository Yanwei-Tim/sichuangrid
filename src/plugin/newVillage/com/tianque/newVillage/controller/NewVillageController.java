package com.tianque.newVillage.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.newVillage.domain.NewVillage;
import com.tianque.newVillage.service.NewVillageService;

@Scope("prototype")
@Namespace("/baseinfo/newVillageManage")
@Controller("newVillageController")
public class NewVillageController extends BaseAction {
	private NewVillage newVillage;
	@Autowired
	private NewVillageService newVillageService;

	@Action(value = "maintainNewVillage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"newVillage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainNewVillage() throws Exception {
		if (newVillage == null || newVillage.getOrganization() == null
				|| newVillage.getOrganization().getId() == null) {
			errorMessage = "新增出错，请重试";
			return ERROR;
		}
		if (newVillage.getId() != null) {
			newVillageService.updateNewVillage(newVillage);
		} else {
			newVillage = newVillageService.addNewVillage(newVillage);
		}
		return SUCCESS;
	}

	public NewVillage getNewVillage() {
		return newVillage;
	}

	public void setNewVillage(NewVillage newVillage) {
		this.newVillage = newVillage;
	}

}
