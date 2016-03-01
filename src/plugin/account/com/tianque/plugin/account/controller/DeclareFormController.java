package com.tianque.plugin.account.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.DeclareFormDubboService;
import com.tianque.account.api.ThreeRecordsIssueDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.DeclareForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.util.DialogMode;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.DeclareFormVo;

@Scope("request")
@Namespace("/threeRecords/declareForm")
@Controller("declareFormController")
public class DeclareFormController extends BaseAction {

	private Long id;
	private DeclareForm declareForm;

	private DeclareFormVo declareFormVo;

	/** 台账处理记录实体类 */
	private ThreeRecordsIssueLogNew operation;

	/** 台账办理时的协办单位的id */
	private String tellOrgIds;

	/** 台账处理步骤keyId */
	private Long keyId;

	/** 从页面上提交过来的附件文件id(修改台账的时候)和名称 eg: id,fileName */
	private String[] attachFiles;

	@Autowired
	private DeclareFormDubboService declareFormDubboService;

	@Autowired
	private ThreeRecordsIssueDubboService threeRecordsIssueDubboService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions( { @Action(value = "dispatch", results = {
			@Result(name = "success", location = "/account/declareForm/viewDeclareForm.jsp"),
			@Result(name = "view", location = "/account/declareForm/viewDeclareForm.jsp"),
			@Result(name = "print", location = "/account/declareForm/printDeclareFormDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			declareForm = declareFormDubboService.createTemporaryDeclareForm(
					operation, keyId);
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
//			List<ThreeRecordsIssueAttachFile> files = threeRecordsIssueDubboService
//					.combineIssueAttachFile(attachFiles);
			List<ThreeRecordsIssueAttachFile> files = AttachFileUtil.getIssueAttachFiles(attachFiles, threeRecordsIssueDubboService);
			declareForm = declareFormDubboService
					.saveDeclareFormAndCompletePorcess(operation,
							ThreeRecordsIssueOperationUtil
									.parseStringToLongArray(tellOrgIds), files,
							declareForm);
			return DialogMode.PRINT_MODE;
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)
				|| DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			declareForm = declareFormDubboService
					.getSimpleDeclareFormByStepId(id);
			return mode;
		}
		return SUCCESS;
	}

	/**
	 * 更新申报单
	 * 
	 * @return
	 */
	@Action(value = "updateDeclareForm", results = {
			@Result(name = "success", type = "json", params = { "root",
					"opreationResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String updateDeclareForm() throws Exception {
		if (declareForm == null) {
			errorMessage = "参数无效!";
			return ERROR;
		}
		opreationResult = declareFormDubboService
				.updateDeclareForm(declareForm) != null;
		return SUCCESS;
	}

	@Action(value = "findDeclareForms", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findDeclareForms() throws Exception {
		PageInfo<DeclareForm> list = declareFormDubboService.findDeclareForms(
				declareFormVo, page, rows);
		gridPage = new GridPage(list);
		return SUCCESS;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DeclareForm getDeclareForm() {
		return declareForm;
	}

	public void setDeclareForm(DeclareForm declareForm) {
		this.declareForm = declareForm;
	}

	public DeclareFormVo getDeclareFormVo() {
		return declareFormVo;
	}

	public void setDeclareFormVo(DeclareFormVo declareFormVo) {
		this.declareFormVo = declareFormVo;
	}

	public ThreeRecordsIssueLogNew getOperation() {
		return operation;
	}

	public void setOperation(ThreeRecordsIssueLogNew operation) {
		this.operation = operation;
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public DeclareFormDubboService getDeclareFormDubboService() {
		return declareFormDubboService;
	}

	public String getTellOrgIds() {
		return tellOrgIds;
	}

	public void setTellOrgIds(String tellOrgIds) {
		this.tellOrgIds = tellOrgIds;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public ThreeRecordsIssueDubboService getThreeRecordsIssueDubboService() {
		return threeRecordsIssueDubboService;
	}

	public void setThreeRecordsIssueDubboService(
			ThreeRecordsIssueDubboService threeRecordsIssueDubboService) {
		this.threeRecordsIssueDubboService = threeRecordsIssueDubboService;
	}

	public void setDeclareFormDubboService(
			DeclareFormDubboService declareFormDubboService) {
		this.declareFormDubboService = declareFormDubboService;
	}

}
