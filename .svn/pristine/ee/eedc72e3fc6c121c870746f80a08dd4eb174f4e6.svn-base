package com.tianque.newVillage.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.newVillage.domain.FarmerPerIncomeInfo;
import com.tianque.newVillage.service.FarmerPerIncomeInfoService;

@Scope("prototype")
@Namespace("/baseinfo/farmerPerIncomeInfoManage")
@Controller("farmerPerIncomeInfoController")
public class FarmerPerIncomeInfoController extends BaseAction {
	private FarmerPerIncomeInfo farmerPerIncomeInfo;
	@Autowired
	private FarmerPerIncomeInfoService farmerPerIncomeInfoService;

	@Action(value = "maintainFarmerPerIncomeInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"farmerPerIncomeInfo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainFarmerPerIncomeInfo() throws Exception {
		if (farmerPerIncomeInfo == null
				|| farmerPerIncomeInfo.getOrganization() == null
				|| farmerPerIncomeInfo.getOrganization().getId() == null) {
			errorMessage = "新增出错，请重试";
			return ERROR;
		}
		if (farmerPerIncomeInfo.getId() != null) {
			farmerPerIncomeInfoService
					.updateFarmerPerIncomeInfo(farmerPerIncomeInfo);
		} else {
			farmerPerIncomeInfo = farmerPerIncomeInfoService
					.addFarmerPerIncomeInfo(farmerPerIncomeInfo);
		}
		return SUCCESS;
	}

	public FarmerPerIncomeInfo getFarmerPerIncomeInfo() {
		return farmerPerIncomeInfo;
	}

	public void setFarmerPerIncomeInfo(FarmerPerIncomeInfo farmerPerIncomeInfo) {
		this.farmerPerIncomeInfo = farmerPerIncomeInfo;
	}

}
