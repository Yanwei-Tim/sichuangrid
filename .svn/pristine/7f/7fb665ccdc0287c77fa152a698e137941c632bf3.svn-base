package com.tianque.partyBuilding.systemPartys.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.Pager;
import com.tianque.partyBuilding.systemPartys.domain.SystemParty;
import com.tianque.partyBuilding.systemPartys.service.SystemPartyService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("request")
@Namespace("/partyBuildng/systemPartyManage")
@Controller("systemPartyController")
public class SystemPartyController extends BaseAction {
	@Autowired
	private SystemPartyService systemPartyService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private SystemParty systemParty;
	private String ids;

	@Action(value = "addSystemParty", results = { @Result(name = "success", type = "json", params = {
			"root", "systemParty", "ignoreHierarchy", "false" }) })
	public String addSystemParty() throws Exception {
		if (systemParty == null || systemParty.getPartyOrgType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		systemParty = systemPartyService.addSystemParty(systemParty);
		return SUCCESS;
	}

	@Action(value = "updateSystemParty", results = { @Result(name = "success", type = "json", params = {
			"root", "systemParty", "ignoreHierarchy", "false" }) })
	public String updateSystemParty() throws Exception {
		if (systemParty == null || systemParty.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		systemParty = systemPartyService.updateSystemParty(systemParty);
		return SUCCESS;
	}

	/**
	 * id加密控制跳转
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "dispatchByEncrypt", results = { @Result(name = "success", location = "/partyBuilding/systems/maintainSystemPartyDlg.jsp") })
	public String dispatchByEncrypt() throws Exception {
		if (systemParty == null || systemParty.getPartyOrgType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		if (DialogMode.ADD_MODE.equals(mode)) {
		} else if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			systemParty = systemPartyService.getSystemPartyById(systemParty);
		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = { @Result(name = "success", location = "/partyBuilding/systems/maintainSystemPartyDlg.jsp") })
	public String dispatch() throws Exception {
		if (systemParty == null || systemParty.getPartyOrgType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		if (DialogMode.ADD_MODE.equals(mode)) {
		} else if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			systemParty = systemPartyService.getSystemPartyById(systemParty);
		}
		return SUCCESS;
	}

	@Action(value = "findSystemPartysForPageByCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findSystemPartysForPageByCondition() throws Exception {
		if (systemParty == null || systemParty.getPartyOrgType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		gridPage = new GridPage<SystemParty>(
				systemPartyService.findSystemPartysForPage(systemParty,
						new Pager(page, rows, sidx, sord)));
		return SUCCESS;
	}

	/**
	 * id加密删除
	 * 
	 * @return
	 */
	@Action(value = "deleteSystemPartyByIdsByEncrypt", results = { @Result(name = "success", type = "json", params = {
			"root", "opreationResult", "ignoreHierarchy", "false" }) })
	@EncryptAnnotation
	public String deleteSystemPartyByIdsByEncrypt() throws Exception {
		if (!StringUtil.isStringAvaliable(ids) || systemParty == null
				|| systemParty.getPartyOrgType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		opreationResult = systemPartyService.deleteSystemPartyByIds(
				systemParty.getPartyOrgType(), analyzeToList(ids));
		return SUCCESS;
	}

	@Action(value = "deleteSystemPartyByIds", results = { @Result(name = "success", type = "json", params = {
			"root", "opreationResult", "ignoreHierarchy", "false" }) })
	public String deleteSystemPartyByIds() throws Exception {
		if (!StringUtil.isStringAvaliable(ids) || systemParty == null
				|| systemParty.getPartyOrgType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		opreationResult = systemPartyService.deleteSystemPartyByIds(
				systemParty.getPartyOrgType(), analyzeToList(ids));
		return SUCCESS;
	}

	@Action(value = "exsistedSystemParty", results = { @Result(name = "success", type = "json", params = {
			"root", "opreationResult", "ignoreHierarchy", "false" }) })
	public String exsistedSystemParty() throws Exception {
		opreationResult = systemPartyService.isExsistedSystemPartyOrg(
				systemParty.getPartyName(), systemParty.getPartyOrgType());
		return SUCCESS;
	}

	public SystemParty getSystemParty() {
		return systemParty;
	}

	public void setSystemParty(SystemParty systemParty) {
		this.systemParty = systemParty;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
