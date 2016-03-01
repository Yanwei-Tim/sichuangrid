package com.tianque.plugin.analyzing.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.UsedInfo;
import com.tianque.plugin.analyzing.service.UsedInfoService;

@Scope("request")
@Namespace("/baseInfoStat/systemUsedReportManage")
@Controller("systemUsedReportController")
public class SystemUsedReportController extends BaseAction {

	private List<UsedInfo> dataList;
	private Long orgId;// 当前所选择的组织机构Id

	@Autowired
	private UsedInfoService usedInfoService;

	@Action(value = "getSystemUsedReportForList", results = { @Result(name = "success", type = "json", params = {
			"root", "dataList", "ignoreHierarchy", "false" }) })
	public String getSystemUsedReportForList() throws Exception {
		if (orgId == null) {
			errorMessage = "查询失败，组织机构信息未获得";
			return ERROR;
		}
		dataList = usedInfoService.findUsedInfoData(orgId);
		return SUCCESS;
	}

	public List<UsedInfo> getDataList() {
		return dataList;
	}

	public void setDataList(List<UsedInfo> dataList) {
		this.dataList = dataList;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
