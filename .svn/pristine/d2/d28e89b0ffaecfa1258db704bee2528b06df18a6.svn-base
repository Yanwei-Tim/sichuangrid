package com.tianque.workBench.jurisdictionWork.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.util.PluginServiceHelpUtil;

@Namespace("/workBench/jurisdictionWork")
@Transactional
@Scope("request")
@Controller("jurisdictionWorkController")
public class JurisdictionWorkController extends BaseAction {

	private Integer teamAmount;
	private Integer memberAmount;
	private Integer recordAmount;
	private Integer objectAmount;
	private Integer objectHasRecordAmount;

	/**
	 * 查找日常工作数据用于显示
	 */
	@Action(value = "findJurisdictionWorkInfo", results = {
			@Result(name = "success", location = "/workBench/module/workBench-centre/myAttention.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findJurisdictionWorkInfo() throws Exception {
		Organization org = ThreadVariable.getSession().getOrganization();
		Map<String, Integer> map = new HashMap();
		try {
			map = (Map<String, Integer>) PluginServiceHelpUtil
					.doServiceGetResult("routerService",
							"getServiceTeamDataForWorkBench",
							new Class[] { Organization.class }, org);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 当前层级及以下团队数量
		teamAmount = map.get("teamAmount");
		// 在团队中的成员数量
		memberAmount = map.get("memberAmount");
		// 记录数量
		recordAmount = map.get("recordAmount");
		// 累计服务人次
		objectHasRecordAmount = map.get("objectHasRecordAmount");
		// 对象数量
		objectAmount = map.get("objectAmount");
		return SUCCESS;
	}

	public Integer getTeamAmount() {
		return teamAmount;
	}

	public void setTeamAmount(Integer teamAmount) {
		this.teamAmount = teamAmount;
	}

	public Integer getMemberAmount() {
		return memberAmount;
	}

	public void setMemberAmount(Integer memberAmount) {
		this.memberAmount = memberAmount;
	}

	public Integer getRecordAmount() {
		return recordAmount;
	}

	public void setRecordAmount(Integer recordAmount) {
		this.recordAmount = recordAmount;
	}

	public Integer getObjectAmount() {
		return objectAmount;
	}

	public void setObjectAmount(Integer objectAmount) {
		this.objectAmount = objectAmount;
	}

	public Integer getObjectHasRecordAmount() {
		return objectHasRecordAmount;
	}

	public void setObjectHasRecordAmount(Integer objectHasRecordAmount) {
		this.objectHasRecordAmount = objectHasRecordAmount;
	}

}
