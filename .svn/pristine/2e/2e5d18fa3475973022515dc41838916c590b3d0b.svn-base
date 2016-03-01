package com.tianque.plugin.orgchange.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.plugin.orgchange.service.OrgChangeInfoService;
import com.tianque.plugin.orgchange.service.OrgChangeLogInfoService;

@Namespace("/orgchange/orgChangeInfoLogManage")
@Scope("prototype")
@Controller("orgChangeInfoLogController")
public class OrgChangeInfoLogController extends BaseAction {

	@Autowired
	private OrgChangeInfoService orgChangeInfoService;

	@Autowired
	private OrgChangeLogInfoService orgChangeLogInfoService;
	private String logId;
	private String orgChangeId;
	private String detailedInfo;

	/***
	 * 查询所有模块迁移合并的操作信息
	 */
	@Action(value = "findAllChangeInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findAllChangeInfo() throws Exception {
		gridPage = new GridPage(orgChangeInfoService.findNoDealInfoList(null,
				page, rows, sidx, sord));
		return SUCCESS;
	}

	/***
	 * 根据mode跳转到不同界面
	 */

	@Action(value = "dispatch", results = { @Result(name = "viewLogInfo", location = "/template/orgchangeLogInfo/viewOrgChangeLogInfo.ftl") })
	public String dispatch() {
		return mode;
	}

	/***
	 * 根据changeInfo主键查询执行的日志信息
	 */
	@Action(value = "findOrgChangeLogInfoByChangeInfoId", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findOrgChangeLogInfoByChangeInfoId() throws Exception {
		gridPage = new GridPage(
				orgChangeLogInfoService.findOrgChangeLogInfoByChangeInfoId(
						Long.parseLong(orgChangeId), page, rows, sidx, sord));
		return SUCCESS;
	}

	/***
	 * 根据日志ID查询日志的详细信息
	 * 
	 * @return
	 */
	@Action(value = "findLogDetailedInfoById", results = { @Result(name = "success", location = "/template/orgchangeLogInfo/viewDetailedInfo.ftl") })
	public String findLogDetailedInfoById() throws Exception {
		detailedInfo = orgChangeLogInfoService.getOrgChangeLogById(
				Long.parseLong(logId)).getDescription();
		return SUCCESS;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getDetailedInfo() {
		return detailedInfo;
	}

	public void setDetailedInfo(String detailedInfo) {
		this.detailedInfo = detailedInfo;
	}

	public String getOrgChangeId() {
		return orgChangeId;
	}

	public void setOrgChangeId(String orgChangeId) {
		this.orgChangeId = orgChangeId;
	}

}
