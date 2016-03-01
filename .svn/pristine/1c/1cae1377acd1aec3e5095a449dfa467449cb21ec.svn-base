package com.tianque.openLayersMap.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.openLayersMap.service.SysTableOperateService;

/**
 * 类型管理表名及字段的校验
 * 
 * @author yubin
 */
@Namespace("/gis/tableOperateManage")
@Transactional
@SuppressWarnings("serial")
@Scope("prototype")
@Controller("sysTableOperateController")
public class SysTableOperateController extends BaseAction {
	@Autowired
	private SysTableOperateService tableOperateService;

	private String tableName;
	private String fields;
	private String result;

	@Action(value = "existTableFindByTableName", results = {
			@Result(name = "success", type = "json", params = { "root", "result",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String existTableFindByTableName() throws Exception {
		result = tableOperateService.existTableFindByTableName(tableName);
		return SUCCESS;
	}

	@Action(value = "existTableFieldsFindByTableNameAndFields", results = {
			@Result(name = "success", type = "json", params = { "root", "result",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String existTableFieldsFindByTableNameAndFields() throws Exception {
		String[] fieldsArray = fields.split(",");
		result = tableOperateService.existTableFieldsFindByTableNameAndFields(tableName,
				fieldsArray);
		return SUCCESS;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
