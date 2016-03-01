package com.tianque.xichang.working.report.controller;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.xichang.working.report.domain.AccountReport;
import com.tianque.xichang.working.report.domain.AccountReportVo;
import com.tianque.xichang.working.report.service.AccountReportService;
import com.tianque.xichang.working.report.service.CreateAccountReportDataService;
import com.tianque.xichang.working.steadyWork.controller.SteadyWorkController;

/**
 * 台账报表-控制器
 * 
 * @author N73
 */
@Controller("accountReportController")
@Namespace("/account/reportManage")
@Scope("request")
public class AccountReportController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(SteadyWorkController.class);

	@Autowired
	private AccountReportService accountReportService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CreateAccountReportDataService createAccountReportDataService;

	private AccountReport accountReport;
	private AccountReportVo searchVo;

	@Action(value = "editAccountReport", results = {
			@Result(name = "success", type = "json", params = { "root",
					"accountReport", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String editAccountReport() throws Exception {
		if (accountReport == null || accountReport.getOrganization() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService
				.getSimpleOrgById(accountReport.getOrganization().getId());
		accountReport.setOrganization(org);
		accountReport.setOrgInternalCode(org.getOrgInternalCode());
		accountReport = accountReportService.editAccountReport(accountReport);
		return SUCCESS;
	}

	@Action(value = "saveAccountReport", results = {
			@Result(name = "success", type = "json", params = { "root",
					"accountReport", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String saveAccountReport() throws Exception {
		if (accountReport == null || accountReport.getOrganization() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService
				.getSimpleOrgById(accountReport.getOrganization().getId());
		accountReport.setOrganization(org);
		accountReport.setOrgInternalCode(org.getOrgInternalCode());
		accountReport = accountReportService.saveAccountReport(accountReport);

		return SUCCESS;
	}

	@Action(value = "refershAccountReport", results = {
			@Result(name = "success", type = "json", params = { "root",
					"accountReport", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String refershAccountReport() throws Exception {
		if (accountReport == null || accountReport.getOrganization() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization org = organizationDubboService
				.getSimpleOrgById(accountReport.getOrganization().getId());
		accountReport.setOrganization(org);
		accountReport.setOrgInternalCode(org.getOrgInternalCode());
		accountReport = accountReportService
				.refershAccountReport(accountReport);

		return SUCCESS;
	}

	@Action(value = "findAccountReportBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"accountReport", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findAccountReportBySearchVo() throws Exception {
		accountReport = accountReportService
				.findAccountReportBySearchVo(searchVo);
		accountReport.setContentObject(JSONObject.fromObject(accountReport
				.getContent()));
		return SUCCESS;
	}

	@Action(value = "createAccountReportData", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String createAccountReportData() throws Exception {
		if (searchVo == null || searchVo.getReportMonth() == null
				|| searchVo.getReportYear() == null
				|| searchVo.getReportType() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		createAccountReportDataService.createAccountReportData(
				searchVo.getReportYear(), searchVo.getReportMonth(),
				searchVo.getReportType());
		return SUCCESS;
	}

	public AccountReport getAccountReport() {
		return accountReport;
	}

	public void setAccountReport(AccountReport accountReport) {
		this.accountReport = accountReport;
	}

	public AccountReportVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(AccountReportVo searchVo) {
		this.searchVo = searchVo;
	}

}
