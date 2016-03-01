package com.tianque.partyBuilding.organizations.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.partyBuilding.organizations.service.DistrictPartyService;

@Namespace("/districtPartyManage")
@Scope("request")
@Controller("districtPartyController")
public class DistrictPartyController extends BaseAction {

	@Autowired
	private DistrictPartyService districtPartyService;

	private Long organizationId;

	@Action(value = "findDistrictPartyStatistics", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findDistrictPartyStatistics() throws Exception {
		if (null == organizationId) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage(
				districtPartyService.findDistrictPartyStatistics(
						organizationId, rows, page));
		return SUCCESS;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
}
