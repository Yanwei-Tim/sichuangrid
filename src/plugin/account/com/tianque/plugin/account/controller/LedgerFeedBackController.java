package com.tianque.plugin.account.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.LedgerFeedBackDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.domain.LedgerFeedBack;

@Scope("request")
@Namespace("/threeRecords/feedBack")
@Controller("ledgerFeedBackController")
public class LedgerFeedBackController extends BaseAction {
	private Organization organization;
	private Long ledgerId;
	private int ledgerType;
	private Boolean isCanEvaluate;
	private String evaluateMode;
	private LedgerFeedBack ledgerFeedBack;
	private Long orgId;
	private Long keyId;

	@Autowired
	private LedgerFeedBackDubboService ledgerFeedBackDubboService;

	/**
	 * 验证是否已经存在反馈评论
	 * 
	 * @return
	 */
	@Action(value = "isCanFeedBack", results = {
			@Result(name = "success", type = "json", params = { "root",
					"isCanEvaluate", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String isCanFeedBack() throws Exception {
		if (ledgerId == null) {
			errorMessage = "编号不能为空";
			return ERROR;
		}
		isCanEvaluate = ledgerFeedBackDubboService
				.isCanLedgerFeedBackByLedgerIdAndType(ledgerId, ledgerType);
		return SUCCESS;
	}

	@Action(value = "dispatch", results = { @Result(name = "success", location = "/account/viewMaintainFeedBackDlg.jsp") })
	public String dispatch() throws Exception {
		return SUCCESS;
	}

	/**
	 * 增加反馈评论
	 * 
	 * @return
	 */
	@Action(value = "addFeedBack", results = {
			@Result(name = "success", type = "json", params = { "root",
					"opreationResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String addFeedBack() throws Exception {
		if (ledgerFeedBack == null || ledgerFeedBack.getLedgerId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		// isCanEvaluate = ledgerFeedBackDubboService
		// .isCanLedgerFeedBackByLedgerIdAndType(ledgerFeedBack
		// .getLedgerId(), ledgerType);
		// if (!isCanEvaluate) {
		// errorMessage = "已反馈，请刷新页面";
		// return ERROR;
		// }
		opreationResult = ledgerFeedBackDubboService
				.addLedgerFeedBack(ledgerFeedBack);
		return SUCCESS;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(Long ledgerId) {
		this.ledgerId = ledgerId;
	}

	public int getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(int ledgerType) {
		this.ledgerType = ledgerType;
	}

	public Boolean getIsCanEvaluate() {
		return isCanEvaluate;
	}

	public void setIsCanEvaluate(Boolean isCanEvaluate) {
		this.isCanEvaluate = isCanEvaluate;
	}

	public String getEvaluateMode() {
		return evaluateMode;
	}

	public void setEvaluateMode(String evaluateMode) {
		this.evaluateMode = evaluateMode;
	}

	public LedgerFeedBack getLedgerFeedBack() {
		return ledgerFeedBack;
	}

	public void setLedgerFeedBack(LedgerFeedBack ledgerFeedBack) {
		this.ledgerFeedBack = ledgerFeedBack;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

}
