package com.tianque.plugin.account.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.AcceptFormDubboService;
import com.tianque.account.api.ThreeRecordsIssueDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.domain.AcceptForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;
import com.tianque.plugin.account.util.DialogMode;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.AcceptFormVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Scope("request")
@Namespace("/threeRecords/acceptForm")
@Controller("acceptFormController")
public class AcceptFormController extends BaseAction {

	private Long id;
	private AcceptForm acceptForm;

	private AcceptFormVo acceptFormVo;

	/** 台账处理记录实体类 */
	private ThreeRecordsIssueLogNew operation;

	/** 台账办理时的协办单位的id */
	private String tellOrgIds;

	/** 台账处理步骤keyId */
	private Long keyId;
	private Long ledgerType;

	/** 从页面上提交过来的附件文件id(修改台账的时候)和名称 eg: id,fileName */
	private String[] attachFiles;

	@Autowired
	private AcceptFormDubboService acceptFormDubboService;

	@Autowired
	private ThreeRecordsIssueDubboService threeRecordsIssueDubboService;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatch", results = {
			@Result(name = "success", location = "/account/acceptForm/viewAcceptForm.jsp"),
			@Result(name = "view", location = "/account/acceptForm/viewAcceptForm.jsp"),
			@Result(name = "print", location = "/account/acceptForm/printAcceptFormDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			acceptForm = acceptFormDubboService.createTemporaryAcceptForm(
					keyId, ledgerType);
			acceptForm.setAcceptOrg(getCurrentLoginedOrg());
			acceptForm.setDealUserName(ThreadVariable.getUser().getName());
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			List<ThreeRecordsIssueAttachFile> files = threeRecordsIssueDubboService
					.combineIssueAttachFile(attachFiles);
			initOperation();
			acceptForm = acceptFormDubboService
					.saveAcceptFormAndCompletePorcess(operation,
							ThreeRecordsIssueOperationUtil
									.parseStringToLongArray(tellOrgIds), files,
							acceptForm);
			acceptForm.setAcceptOrg(getCurrentLoginedOrg());
			acceptForm.setFormType(propertyDictDubboService.getPropertyDictById(acceptForm.getFormType().getId()));
			return DialogMode.PRINT_MODE;
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)
				|| DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			acceptForm = acceptFormDubboService.getSimpleAcceptFormByStepId(id);
			if(acceptForm != null && acceptForm.getFormType() != null && acceptForm.getFormType().getId() != null){
				acceptForm.setFormType(propertyDictDubboService.getPropertyDictById(acceptForm.getFormType().getId()));
			}
			return mode;
		}
		return SUCCESS;
	}

	private void initOperation() {
		ThreeRecordsIssueStep step = threeRecordsIssueDubboService
				.getIssueStepById(keyId);
		operation = new ThreeRecordsIssueLogNew();
		operation.setDealUserName(ThreadVariable.getUser().getName());
		operation.setMobile(ThreadVariable.getUser().getMobile());
		operation.setDealOrg(getCurrentLoginedOrg());
		operation.setLedgerType(step.getLedgerType());
		operation.setDealOrg(step.getSource());
		operation.setIssueStep(step);
	}

	private Organization getCurrentLoginedOrg() {
		return organizationDubboService.getFullOrgById(ThreadVariable
				.getSession().getOrganization().getId());
	}

	/**
	 * 更新呈报单
	 * 
	 * @return
	 */
	@Action(value = "updateAcceptForm", results = {
			@Result(name = "success", type = "json", params = { "root",
					"opreationResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String updateAcceptForm() throws Exception {
		if (acceptForm == null) {
			errorMessage = "参数无效!";
			return ERROR;
		}
		opreationResult = acceptFormDubboService.updateAcceptForm(acceptForm) != null;
		return SUCCESS;
	}

	@Action(value = "findAcceptForms", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findAcceptForms() throws Exception {
		PageInfo<AcceptForm> list = acceptFormDubboService.findAcceptForms(
				acceptFormVo, page, rows);
		gridPage = new GridPage(list);
		return SUCCESS;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AcceptForm getAcceptForm() {
		return acceptForm;
	}

	public void setAcceptForm(AcceptForm acceptForm) {
		this.acceptForm = acceptForm;
	}

	public AcceptFormVo getAcceptFormVo() {
		return acceptFormVo;
	}

	public void setAcceptFormVo(AcceptFormVo acceptFormVo) {
		this.acceptFormVo = acceptFormVo;
	}

	public ThreeRecordsIssueLogNew getOperation() {
		return operation;
	}

	public void setOperation(ThreeRecordsIssueLogNew operation) {
		this.operation = operation;
	}

	public String getTellOrgIds() {
		return tellOrgIds;
	}

	public void setTellOrgIds(String tellOrgIds) {
		this.tellOrgIds = tellOrgIds;
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public AcceptFormDubboService getAcceptFormDubboService() {
		return acceptFormDubboService;
	}

	public void setAcceptFormDubboService(
			AcceptFormDubboService acceptFormDubboService) {
		this.acceptFormDubboService = acceptFormDubboService;
	}

	public Long getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(Long ledgerType) {
		this.ledgerType = ledgerType;
	}

}
