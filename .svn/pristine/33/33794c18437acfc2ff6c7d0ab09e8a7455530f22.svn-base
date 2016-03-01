package com.tianque.xichang.working.flow.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.flow.domain.EvaluateFeedbacks;
import com.tianque.xichang.working.flow.service.EvaluateFeedbacksService;

/**
 * 我的台账的的一办结的反馈评价
 */

@Scope("request")
@Namespace("/account/evaluateFeedbacksManage")
@Controller("evaluateFeedbacksController")
public class EvaluateFeedbacksController extends BaseAction {

	@Autowired
	private EvaluateFeedbacksService evaluateFeedbacksService;

	// 原来的dubboService包下的
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Organization organization;
	private Long accountId;
	private String accountType;
	private Boolean isCanEvaluate;
	private String evaluateMode;
	private EvaluateFeedbacks evaluateFeedbacks;
	private String mode;
	private Long orgId;

	/**
	 * 验证是否已经存在反馈评论
	 * 
	 * @return
	 */
	@Action(value = "isCanEvaluatePeopleAspirationByIdAndAccountType", results = {
			@Result(name = "success", type = "json", params = { "root",
					"isCanEvaluate", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String isCanEvaluatePeopleAspirationByIdAndAccountType()
			throws Exception {
		if (evaluateFeedbacks == null
				|| evaluateFeedbacks.getAccountId() == null
				|| evaluateFeedbacks.getAccountType() == null) {
			errorMessage = "编号不能为空";
			return ERROR;
		}
		isCanEvaluate = evaluateFeedbacksService
				.isCanEvaluatePeopleAspirationByIdAndAccountType(
						evaluateFeedbacks.getAccountId(),
						evaluateFeedbacks.getAccountType());
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/xichang/working/flow/viewMaintainEvaluateDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String dispatch() throws Exception {
		if (evaluateFeedbacks.getAccountId() == null
				|| evaluateFeedbacks.getAccountType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		if (AccountType.EVALUATEMODE_VIEW.equals(evaluateMode)) {
			evaluateFeedbacks = evaluateFeedbacksService
					.getEvaluateFeedbacksByAccountIdAndType(
							evaluateFeedbacks.getAccountId(),
							evaluateFeedbacks.getAccountType());
		}
		return SUCCESS;
	}

	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/xichang/working/flow/accountLogsTab.jsp"),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String dispatchOperate() throws Exception {

		if (accountId == null || accountType == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		isCanEvaluate = evaluateFeedbacksService
				.isCanEvaluatePeopleAspirationByIdAndAccountType(accountId,
						accountType);
		return SUCCESS;

	}

	/**
	 * 增加反馈评论
	 * 
	 * @return
	 */
	@PermissionFilters({
			@PermissionFilter(ename = "evaluatePeopleAspiration", actionName = "addEvaluateFeedbacks"),
			@PermissionFilter(ename = "evaluatePoorPeople", actionName = "addEvaluateFeedbacks"),
			@PermissionFilter(ename = "evaluateSteadyWork", actionName = "addEvaluateFeedbacks") })
	@Action(value = "addEvaluateFeedbacks", results = {
			@Result(name = "success", type = "json", params = { "root",
					"opreationResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String addEvaluateFeedbacks() throws Exception {
		if (evaluateFeedbacks.getAccountId() == null
				|| evaluateFeedbacks.getAccountType() == null
				|| organization.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		evaluateFeedbacks.setEvaluateOrganization(organization);
		opreationResult = evaluateFeedbacksService
				.addEvaluateFeedbacks(evaluateFeedbacks);
		return SUCCESS;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Boolean getIsCanEvaluate() {
		return isCanEvaluate;
	}

	public void setIsCanEvaluate(Boolean isCanEvaluate) {
		this.isCanEvaluate = isCanEvaluate;
	}

	public EvaluateFeedbacks getEvaluateFeedbacks() {
		return evaluateFeedbacks;
	}

	public void setEvaluateFeedbacks(EvaluateFeedbacks evaluateFeedbacks) {
		this.evaluateFeedbacks = evaluateFeedbacks;
	}

	public String getEvaluateMode() {
		return evaluateMode;
	}

	public void setEvaluateMode(String evaluateMode) {
		this.evaluateMode = evaluateMode;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
