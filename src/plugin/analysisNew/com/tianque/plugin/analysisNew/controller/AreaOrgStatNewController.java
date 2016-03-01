package com.tianque.plugin.analysisNew.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.Organization;
import com.tianque.plugin.analysisNew.domain.AreaOrgStatDataVo;
import com.tianque.plugin.analysisNew.service.AreaOrgStatNewService;

/**
 * 组织机构统计的控制类
 * 
 * @author T26
 */
@Namespace("/baseInfoStat/areaOrgStatManageNew")
@Controller("areaOrgStatNewController")
public class AreaOrgStatNewController extends BaseAction {
	private List<AreaOrgStatDataVo> dataList;
	private Long orgId;
	@Autowired
	private AreaOrgStatNewService areaOrgStatNewService;

	@Action(value = "getAreaOrgStatDateList", results = { @Result(name = "success", type = "json", params = {
			"root", "dataList", "ignoreHierarchy", "false" }) })
	public String getAreaOrgStatDateList() {
		dataList = areaOrgStatNewService.getAreaOrgStatDateList(orgId);

		addSumNum(dataList);

		return SUCCESS;
	}

	private void addSumNum(List<AreaOrgStatDataVo> dataList) {
		AreaOrgStatDataVo sum = dataList.get(dataList.size() - 1);
		Organization sumOrg = sum.getOrg();
		sumOrg.setOrgName(sumOrg.getOrgName() + " ( " + (dataList.size() - 1)
				+ " )");
	}

	public List<AreaOrgStatDataVo> getDataList() {
		return dataList;
	}

	public void setDataList(List<AreaOrgStatDataVo> dataList) {
		this.dataList = dataList;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
}
