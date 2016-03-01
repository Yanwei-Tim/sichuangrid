package com.tianque.xichang.working.parameterEvaluation.deductionStandard.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.xichang.working.parameterEvaluation.deductionStandard.domain.ParameterConfig;
import com.tianque.xichang.working.parameterEvaluation.deductionStandard.service.ParameterConfigService;

@Controller("parameterConfigController")
@Scope("request")
@Namespace("/parameterConfigManage")
public class ParameterConfigController extends BaseAction {

	@Autowired
	private ParameterConfigService parameterConfigService;

	private ParameterConfig parameterConfig;

	@PermissionFilter(ename = "saveDeductionStandard")
	@Action(value = "saveDeductionStandard", results = {
			@Result(name = "success", type = "json", params = { "root", "parameterConfig",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String saveDeductionStandard() throws Exception {
		parameterConfig = parameterConfigService.saveDeductionStandard(parameterConfig);
		return SUCCESS;
	}

	@Action(value = "dispatch", results = { @Result(name = "success", location = "/xichang/working/parameterEvaluation/deductionStandard/parameterConfigList.jsp") })
	public String dispatch() throws Exception {
		parameterConfig = parameterConfigService.getParameterConfig();
		if (parameterConfig == null) {
			parameterConfig = new ParameterConfig();
		}
		return SUCCESS;
	}

	public void setParameterConfig(ParameterConfig parameterConfig) {
		this.parameterConfig = parameterConfig;
	}

	public ParameterConfig getParameterConfig() {
		return parameterConfig;
	}

}
