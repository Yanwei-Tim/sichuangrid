package com.tianque.plugin.account.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.AssignFormDubboService;
import com.tianque.account.api.ThreeRecordsIssueDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.plugin.account.domain.AssignForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.util.DialogMode;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;

@Scope("request")
@Namespace("/threeRecords/assignForm")
@Controller("assignFormController")
public class AssignFormController extends BaseAction {

	private Long id;
	private AssignForm assignForm;

	/** 台账处理记录实体类 */
	private ThreeRecordsIssueLogNew operation;

	/** 台账办理时的协办单位的id */
	private String tellOrgIds;

	/** 台账办理时的抄告单位的id */
	private String noticeOrgIds;

	/** 台账处理步骤keyId */
	private Long keyId;

	/** 从页面上提交过来的附件文件id(修改台账的时候)和名称 eg: id,fileName */
	private String[] attachFiles;

	@Autowired
	private AssignFormDubboService assignFormDubboService;

	@Autowired
	private ThreeRecordsIssueDubboService threeRecordsIssueDubboService;

	private String receivers;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions( { @Action(value = "dispatch", results = {
			@Result(name = "success", location = "/account/assignForm/viewAssignForm.jsp"),
			@Result(name = "view", location = "/account/assignForm/viewAssignForm.jsp"),
			@Result(name = "print", location = "/account/assignForm/printAssignFormDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			assignForm = assignFormDubboService.createTemporaryAssignForm(
					operation, ThreeRecordsIssueOperationUtil
							.parseStringToLongArray(tellOrgIds), keyId);
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)
				|| DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			assignForm = assignFormDubboService.getSimpleAssignFormByStepId(id);
			return mode;
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			if (assignForm != null) {
				assignForm.convertReceivers(receivers);
			}
//			List<ThreeRecordsIssueAttachFile> files = threeRecordsIssueDubboService
//					.combineIssueAttachFile(attachFiles);
			List<ThreeRecordsIssueAttachFile> files = AttachFileUtil.getIssueAttachFiles(attachFiles, threeRecordsIssueDubboService);
			assignForm = assignFormDubboService
					.saveAssignFormAndCompletePorcess(operation,
							ThreeRecordsIssueOperationUtil
									.parseStringToLongArray(tellOrgIds),
							ThreeRecordsIssueOperationUtil
									.parseStringToLongArray(noticeOrgIds),
							files, assignForm);
			return DialogMode.PRINT_MODE;
		}
		return SUCCESS;
	}

	/**
	 * 更新交办单
	 * 
	 * @return
	 */
	@Action(value = "updateAssignForm", results = {
			@Result(name = "success", type = "json", params = { "root",
					"opreationResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String updateAssignForm() throws Exception {
		if (assignForm == null) {
			errorMessage = "参数无效!";
			return ERROR;
		}
		opreationResult = assignFormDubboService.updateAssignForm(assignForm) != null;
		return SUCCESS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AssignForm getAssignForm() {
		return assignForm;
	}

	public void setAssignForm(AssignForm AssignForm) {
		this.assignForm = AssignForm;
	}

	public ThreeRecordsIssueLogNew getOperation() {
		return operation;
	}

	public void setOperation(ThreeRecordsIssueLogNew operation) {
		this.operation = operation;
	}

	public String getReceivers() {
		return receivers;
	}

	public void setReceivers(String receivers) {
		this.receivers = receivers;
	}

	public String getTellOrgIds() {
		return tellOrgIds;
	}

	public void setTellOrgIds(String tellOrgIds) {
		this.tellOrgIds = tellOrgIds;
	}

	public String getNoticeOrgIds() {
		return noticeOrgIds;
	}

	public void setNoticeOrgIds(String noticeOrgIds) {
		this.noticeOrgIds = noticeOrgIds;
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

	public AssignFormDubboService getAssignFormDubboService() {
		return assignFormDubboService;
	}

	public void setAssignFormDubboService(
			AssignFormDubboService assignFormDubboService) {
		this.assignFormDubboService = assignFormDubboService;
	}

}
