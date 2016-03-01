package com.tianque.newVillage.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.newVillage.domain.BasicYearInfo;
import com.tianque.newVillage.service.BasicYearInfoService;

@Scope("prototype")
@Namespace("/baseinfo/basicYearInfoManage")
@Controller("basicYearInfoController")
public class BasicYearInfoController extends BaseAction {
	private BasicYearInfo basicYearInfo;
	@Autowired
	private BasicYearInfoService basicYearInfoService;

	@Action(value = "maintainBasicYearInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"BasicYearInfo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainBasicYearInfo() throws Exception {
		if (basicYearInfo == null || basicYearInfo.getOrganization() == null
				|| basicYearInfo.getOrganization().getId() == null) {
			errorMessage = "新增出错，请重试";
			return ERROR;
		}
		if (basicYearInfo.getId() != null) {
			basicYearInfoService.updateBasicYearInfo(basicYearInfo);
		} else {
			basicYearInfo = basicYearInfoService
					.addBasicYearInfo(basicYearInfo);
		}
		return SUCCESS;
	}

	public BasicYearInfo getBasicYearInfo() {
		return basicYearInfo;
	}

	public void setBasicYearInfo(BasicYearInfo basicYearInfo) {
		this.basicYearInfo = basicYearInfo;
	}

}
