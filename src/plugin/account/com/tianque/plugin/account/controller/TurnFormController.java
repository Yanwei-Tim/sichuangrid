package com.tianque.plugin.account.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.ThreeRecordsIssueDubboService;
import com.tianque.account.api.TurnFormDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.TurnForm;
import com.tianque.plugin.account.util.DialogMode;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.TurnFormVo;

@Scope("request")
@Namespace("/threeRecords/turnForm")
@Controller("turnFormController")
public class TurnFormController extends BaseAction {

	private Long id;
	private TurnForm turnForm;

	private TurnFormVo turnFormVo;

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
	private TurnFormDubboService turnFormDubboService;

	@Autowired
	private ThreeRecordsIssueDubboService threeRecordsIssueDubboService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions( { @Action(value = "dispatch", results = {
			@Result(name = "success", location = "/account/turnForm/viewTurnForm.jsp"),
			@Result(name = "view", location = "/account/turnForm/viewTurnForm.jsp"),
			@Result(name = "print", location = "/account/turnForm/printTurnFormDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			turnForm = turnFormDubboService.createTemporaryTurnForm(operation,
					keyId);
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
//			List<ThreeRecordsIssueAttachFile> files = threeRecordsIssueDubboService
//					.combineIssueAttachFile(attachFiles);
			List<ThreeRecordsIssueAttachFile> files = AttachFileUtil.getIssueAttachFiles(attachFiles, threeRecordsIssueDubboService);
			turnForm = turnFormDubboService.saveTurnFormAndCompletePorcess(
					operation, ThreeRecordsIssueOperationUtil
							.parseStringToLongArray(tellOrgIds),
					ThreeRecordsIssueOperationUtil
							.parseStringToLongArray(noticeOrgIds), files,
					turnForm);
			return DialogMode.PRINT_MODE;
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)
				|| DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			turnForm = turnFormDubboService.getSimpleTurnFormByStepId(id);
			return mode;
		}
		return SUCCESS;
	}

	/**
	 * 更新转办单
	 * 
	 * @return
	 */
	@Action(value = "updateTurnForm", results = {
			@Result(name = "success", type = "json", params = { "root",
					"opreationResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String updateTurnForm() throws Exception {
		if (turnForm == null) {
			errorMessage = "参数无效!";
			return ERROR;
		}
		opreationResult = turnFormDubboService.updateTurnForm(turnForm) != null;
		return SUCCESS;
	}

	@Action(value = "findTurnForms", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findTurnForms() throws Exception {
		PageInfo<TurnForm> list = turnFormDubboService.findTurnForms(
				turnFormVo, page, rows);
		gridPage = new GridPage(list);
		return SUCCESS;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TurnForm getTurnForm() {
		return turnForm;
	}

	public void setTurnForm(TurnForm turnForm) {
		this.turnForm = turnForm;
	}

	public TurnFormVo getTurnFormVo() {
		return turnFormVo;
	}

	public void setTurnFormVo(TurnFormVo turnFormVo) {
		this.turnFormVo = turnFormVo;
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

	public String getNoticeOrgIds() {
		return noticeOrgIds;
	}

	public void setNoticeOrgIds(String noticeOrgIds) {
		this.noticeOrgIds = noticeOrgIds;
	}

	public TurnFormDubboService getTurnFormDubboService() {
		return turnFormDubboService;
	}

	public void setTurnFormDubboService(
			TurnFormDubboService turnFormDubboService) {
		this.turnFormDubboService = turnFormDubboService;
	}

}
