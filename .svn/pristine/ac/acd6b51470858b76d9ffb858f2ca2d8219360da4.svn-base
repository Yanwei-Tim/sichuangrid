package com.tianque.plugin.account.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.ReportFormDubboService;
import com.tianque.account.api.ThreeRecordsIssueDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.account.domain.ReportForm;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.util.DialogMode;
import com.tianque.plugin.account.util.ThreeRecordsIssueOperationUtil;
import com.tianque.plugin.account.vo.ReportFormVo;

@Scope("request")
@Namespace("/threeRecords/reportForm")
@Controller("reportFormController")
public class ReportFormController extends BaseAction {

	private Long id;
	private ReportForm reportForm;

	private ReportFormVo reportFormVo;

	/** 台账处理记录实体类 */
	private ThreeRecordsIssueLogNew operation;

	/** 台账办理时的协办单位的id */
	private String tellOrgIds;

	/** 台账处理步骤keyId */
	private Long keyId;

	/** 从页面上提交过来的附件文件id(修改台账的时候)和名称 eg: id,fileName */
	private String[] attachFiles;

	@Autowired
	private ReportFormDubboService reportFormDubboService;

	@Autowired
	private ThreeRecordsIssueDubboService threeRecordsIssueDubboService;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions( { @Action(value = "dispatch", results = {
			@Result(name = "success", location = "/account/reportForm/viewReportForm.jsp"),
			@Result(name = "view", location = "/account/reportForm/viewReportForm.jsp"),
			@Result(name = "print", location = "/account/reportForm/printReportFormDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			reportForm = reportFormDubboService.createTemporaryReportForm(
					operation, keyId);
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
//			List<ThreeRecordsIssueAttachFile> files = threeRecordsIssueDubboService
//					.combineIssueAttachFile(attachFiles);
			List<ThreeRecordsIssueAttachFile> files = AttachFileUtil.getIssueAttachFiles(attachFiles, threeRecordsIssueDubboService);
			reportForm = reportFormDubboService
					.saveReportFormAndCompletePorcess(operation,
							ThreeRecordsIssueOperationUtil
									.parseStringToLongArray(tellOrgIds), files,
							reportForm);
			return DialogMode.PRINT_MODE;
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)
				|| DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			reportForm = reportFormDubboService.getSimpleReportFormByStepId(id);
			return mode;
		}
		return SUCCESS;
	}

	/**
	 * 更新呈报单
	 * 
	 * @return
	 */
	@Action(value = "updateReportForm", results = {
			@Result(name = "success", type = "json", params = { "root",
					"opreationResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String updateReportForm() throws Exception {
		if (reportForm == null) {
			errorMessage = "参数无效!";
			return ERROR;
		}
		opreationResult = reportFormDubboService.updateReportForm(reportForm) != null;
		return SUCCESS;
	}

	@Action(value = "findReportForms", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findReportForms() throws Exception {
		PageInfo<ReportForm> list = reportFormDubboService.findReportForms(
				reportFormVo, page, rows);
		gridPage = new GridPage(list);
		return SUCCESS;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReportForm getReportForm() {
		return reportForm;
	}

	public void setReportForm(ReportForm reportForm) {
		this.reportForm = reportForm;
	}

	public ReportFormVo getReportFormVo() {
		return reportFormVo;
	}

	public void setReportFormVo(ReportFormVo reportFormVo) {
		this.reportFormVo = reportFormVo;
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

	public ReportFormDubboService getReportFormDubboService() {
		return reportFormDubboService;
	}

	public void setReportFormDubboService(
			ReportFormDubboService reportFormDubboService) {
		this.reportFormDubboService = reportFormDubboService;
	}

}
