package com.tianque.plugin.dataManage.common;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.base.BaseAction;

@Namespace("/plugin/dataManage/jobTest")
public class DataManageDBJobController extends BaseAction {
	@Autowired
	private DataManageDBJobService dataManageDBJobService;

	@Action(value = "doJob", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String doJob() {
		dataManageDBJobService.toDisposeDB();
		return SUCCESS;
	}
}
