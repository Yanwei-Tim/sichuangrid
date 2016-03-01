package com.tianque.newVillage.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.newVillage.domain.CommonServiceInfo;
import com.tianque.newVillage.service.CommonServiceInfoService;

@Scope("prototype")
@Namespace("/baseinfo/commonServiceInfoManage")
@Controller("commonServiceInfoController")
public class CommonServiceInfoController extends BaseAction {
	private CommonServiceInfo commonServiceInfo;
	@Autowired
	private CommonServiceInfoService commonServiceInfoService;

	@Action(value = "maintainCommonServiceInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"commonServiceInfo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainCommonServiceInfo() throws Exception {
		if (commonServiceInfo == null
				|| commonServiceInfo.getOrganization() == null
				|| commonServiceInfo.getOrganization().getId() == null) {
			errorMessage = "新增出错，请重试";
			return ERROR;
		}
		if (commonServiceInfo.getId() != null) {
			commonServiceInfoService.updateCommonServiceInfo(commonServiceInfo);
		} else {
			commonServiceInfo = commonServiceInfoService
					.addCommonServiceInfo(commonServiceInfo);
		}
		return SUCCESS;
	}

	public CommonServiceInfo getCommonServiceInfo() {
		return commonServiceInfo;
	}

	public void setCommonServiceInfo(CommonServiceInfo commonServiceInfo) {
		this.commonServiceInfo = commonServiceInfo;
	}

}
