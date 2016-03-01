package com.tianque.plugin.account.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.ReplyFormDubboService;
import com.tianque.account.api.ThreeRecordsIssueDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.ReplyForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.util.DialogMode;
import com.tianque.plugin.account.vo.ReplyFormVo;

@Scope("request")
@Namespace("/threeRecords/replyForm")
@Controller("replyFormController")
public class ReplyFormController extends BaseAction {

	private Long id;
	private ReplyForm replyForm;

	private ReplyFormVo replyFormVo;

	private String[] attachFiles;

	/** 台账处理记录实体类 */
	private ThreeRecordsIssueLogNew operation;

	@Autowired
	private ReplyFormDubboService replyFormDubboService;

	@Autowired
	private ThreeRecordsIssueDubboService threeRecordsIssueDubboService;
	
	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions( { @Action(value = "dispatch", results = {
			@Result(name = "success", location = "/account/replyForm/mainReplyForm.jsp"),
			@Result(name = "view", location = "/account/replyForm/viewReplyForm.jsp"),
			@Result(name = "print", location = "/account/replyForm/printReplyFormDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {

		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)
				|| DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			if (id == null) {
				errorMessage = "参数无效!";
				return ERROR;
			}
			replyForm = replyFormDubboService.getReplyFormAndFilesByReplyId(id);
			return mode;
		}
		return SUCCESS;
	}

	/**
	 * 添加回复单
	 * 
	 * @return
	 */
	@Action(value = "addReplyForm", results = {
			@Result(name = "success", type = "json", params = { "root",
					"opreationResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String addReplyForm() throws Exception {
		if (replyForm == null) {
			errorMessage = "参数无效!";
			return ERROR;
		}
		List<ThreeRecordsIssueAttachFile> files = AttachFileUtil.getIssueAttachFiles(attachFiles, threeRecordsIssueDubboService);
//		opreationResult = replyFormDubboService.addReplyForm(replyForm,
//				attachFiles) != null;
		opreationResult = replyFormDubboService.addReplyForm(replyForm,
				files) != null;
		return SUCCESS;
	}

	@Action(value = "findReplyForms", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findReplyForms() throws Exception {
		PageInfo<ReplyForm> list = replyFormDubboService.findReplyForms(
				replyFormVo, page, rows);
		gridPage = new GridPage(list);
		return SUCCESS;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReplyForm getReplyForm() {
		return replyForm;
	}

	public void setReplyForm(ReplyForm replyForm) {
		this.replyForm = replyForm;
	}

	public ReplyFormVo getReplyFormVo() {
		return replyFormVo;
	}

	public void setReplyFormVo(ReplyFormVo replyFormVo) {
		this.replyFormVo = replyFormVo;
	}

	public ThreeRecordsIssueLogNew getOperation() {
		return operation;
	}

	public void setOperation(ThreeRecordsIssueLogNew operation) {
		this.operation = operation;
	}

	public ReplyFormDubboService getReplyFormDubboService() {
		return replyFormDubboService;
	}

	public void setReplyFormDubboService(
			ReplyFormDubboService replyFormDubboService) {
		this.replyFormDubboService = replyFormDubboService;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

}
