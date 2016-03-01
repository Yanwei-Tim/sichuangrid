package com.tianque.issue.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("orgToolsController")
@Scope("prototype")
@Namespace("/tools/org")
public class OrgToolsController extends ActionSupport {
	/** 要比较的部门id */
	private Long orgId;
	/** 目标层级 */
	private int orgLevel;
	/** 比较结果 0、和目标层级相等 1、大于目标层级 -1、小于目标层级 */
	private long numResult;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	/***
	 * 选择事件发生网格时 校验所选部门是否符合指定的层级
	 * 
	 * @return
	 */
	@Action(value = "levelCompare", results = { @Result(name = "success", type = "json", params = {
			"root", "numResult", "ignoreHierarchy", "false" }) })
	public String compareOrgLevel() {
		if (null != orgId) {
			Organization org = organizationDubboService.getFullOrgById(orgId);
			numResult = OrganizationLevel.levelCompare(org.getOrgLevel().getInternalId(), orgLevel);
		} else {
			numResult = 1;
		}
		return "success";
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public int getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(int orgLevel) {
		this.orgLevel = orgLevel;
	}

	public long getNumResult() {
		return numResult;
	}

	public void setNumResult(long numResult) {
		this.numResult = numResult;
	}

}
