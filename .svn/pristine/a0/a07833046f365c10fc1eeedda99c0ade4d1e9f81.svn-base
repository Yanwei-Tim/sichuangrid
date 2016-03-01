package com.tianque.plugin.account.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.LedgerConvertDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.domain.LedgerConvert;
import com.tianque.plugin.account.util.DialogMode;
import com.tianque.plugin.account.vo.LedgerConvertVo;

@Scope("request")
@Namespace("/threeRecords/ledgerConvert")
@Controller("ledgerConvertController")
public class LedgerConvertController extends BaseAction {

	private Long id;
	private Organization organization;

	private LedgerConvert ledgerConvert;

	private LedgerConvertVo searchVo;

	@Autowired
	private LedgerConvertDubboService ledgerConvertDubboService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions( { @Action(value = "dispatch", results = {
			@Result(name = "success", location = "/account/ledgerConvert/viewLedgerConvert.jsp"),
			@Result(name = "view", location = "/account/ledgerConvert/viewLedgerConvert.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {

		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			ledgerConvert = ledgerConvertDubboService
					.getSimpleLedgerConvertById(id);
			return DialogMode.VIEW_MODE;
		}
		return SUCCESS;
	}

	@Action(value = "getLedgerConvert", results = { @Result(name = "success", location = "/account/ledgerConvert/ledgerConvertDlg.jsp") })
	public String viewConvert() throws Exception {
		if (id == null) {
			errorMessage = "参数无效!";
			return ERROR;
		}
		ledgerConvert = ledgerConvertDubboService
				.getSimpleLedgerConvertById(id);
		return SUCCESS;
	}

	@Action(value = "canConvert", results = {
			@Result(name = "success", type = "json", params = { "root",
					"ledgerConvert", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String canConvert() throws Exception {
		if (id == null) {
			errorMessage = "参数无效!";
			return ERROR;
		}
		ledgerConvert = ledgerConvertDubboService
				.getSimpleLedgerConvertById(id);
		return SUCCESS;
	}

	@Action(value = "findLedgerConverts", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findLedgerConverts() throws Exception {
		PageInfo<LedgerConvert> list = ledgerConvertDubboService
				.findLedgerConverts(searchVo, page, rows);

		gridPage = new GridPage(list);
		return SUCCESS;

	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LedgerConvert getLedgerConvert() {
		return ledgerConvert;
	}

	public void setLedgerConvert(LedgerConvert ledgerConvert) {
		this.ledgerConvert = ledgerConvert;
	}

	public LedgerConvertDubboService getLedgerConvertDubboService() {
		return ledgerConvertDubboService;
	}

	public void setLedgerConvertDubboService(
			LedgerConvertDubboService ledgerConvertDubboService) {
		this.ledgerConvertDubboService = ledgerConvertDubboService;
	}

	public LedgerConvertVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(LedgerConvertVo searchVo) {
		this.searchVo = searchVo;
	}

}
