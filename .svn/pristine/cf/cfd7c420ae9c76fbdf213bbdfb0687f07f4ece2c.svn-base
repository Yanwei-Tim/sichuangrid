package com.tianque.plugin.orgchange.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.orgchange.domain.BackupBaseData;
import com.tianque.plugin.orgchange.service.BackupBaseDataService;

@Namespace("/orgchange/orgChangeInfoBackupManage")
@Scope("prototype")
@Controller("orgChangeBackupInfoController")
public class OrgChangeBackupInfoController extends BaseAction {

	@Autowired
	private BackupBaseDataService backupBaseDataService;

	public String details;

	@Action(value = "findAllBackupInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findAllBackupInfo() throws Exception {
		gridPage = new GridPage(backupBaseDataService.findAllBackupInfos(null,
				page, rows, "orgChangeId", sord));
		return SUCCESS;
	}

	@Action(value = "findBackupDetaileById", results = { @Result(name = "success", location = "/template/orgchangeBackupInfo/viewBackupDetaileInfo.ftl") })
	public String findBackupDetaileById() {
		if (id == null) {
			throw new BusinessValidationException("ID数据为空");
		}
		BackupBaseData back = backupBaseDataService.getBackupInfoById(id);
		if (back != null) {
			details = back.getExpansionData();
		}
		return SUCCESS;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
