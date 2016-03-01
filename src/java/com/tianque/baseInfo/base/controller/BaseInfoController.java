package com.tianque.baseInfo.base.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.core.base.BaseAction;

@Namespace("/baseinfo/baseinfoPopulation")
@Controller("baseInfoController")
@Scope("prototype")
@Transactional
public class BaseInfoController extends BaseAction {
	@Autowired
	private BaseInfoService baseInfoService;

	private Countrymen countrymen;
	private String idCardNo;

	@Action(value = "getBaseInfoByIdCardNo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"countrymen", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getBaseInfoByIdCardNo() throws Exception {
		if ("".equals(idCardNo)) {
			return SUCCESS;
		}
		countrymen = baseInfoService.existBaseInfo(idCardNo);
		return SUCCESS;
	}

	public Countrymen getCountrymen() {
		return countrymen;
	}

	public void setCountrymen(Countrymen countrymen) {
		this.countrymen = countrymen;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

}
