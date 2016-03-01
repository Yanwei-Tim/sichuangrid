package com.tianque.peopleLog.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.PeopleLogAttachFiles;
import com.tianque.peopleLog.service.PeopleLogService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 民情日志控制类
 */

@Namespace("/peopleLog/peopleLogManage")
@Transactional
@Scope("request")
@Controller("peopleLogController")
public class PeopleLogController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(PeopleLogController.class);

	private String logIds;

	private PeopleLog peopleLog;

	private Boolean isComment;

	private Long logId;

	private Long orgId;

	private Long userId;

	private String orginternalCode;

	private String[] attachFiles;// 附件

	private PeopleLogAttachFiles peopleLogAttachFile;
	@Autowired
	private PeopleLogService peopleLogService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/**
	 * id加密实现页面跳转
	 */
	@EncryptAnnotation
	@Action(value = "dispatchByEncrypt", results = {
			@Result(name = "success", location = "/peopleLog/myLog/peopleLogDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperateByEncrypt() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			processPeopleLogOrgAndUserId();
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			peopleLog = peopleLogService.getPeopleLogById(logId);
			if (peopleLog.getIsAttachment()) {
				peopleLog.setPeopleLogFiles(peopleLogService
						.findPeopleLogAttachFilesByPeopleLogId(peopleLog
								.getId()));
			}
			processPeopleLogOrgAndUserId();
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		}

		return SUCCESS;
	}

	/**
	 * 实现页面跳转
	 */
	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/peopleLog/myLog/peopleLogDlg.jsp"),
			@Result(name = "search", location = "/peopleLog/myLog/searchPeopleLogDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			processPeopleLogOrgAndUserId();
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			peopleLog = peopleLogService.getPeopleLogById(logId);
			if (peopleLog.getIsAttachment()) {
				peopleLog.setPeopleLogFiles(peopleLogService
						.findPeopleLogAttachFilesByPeopleLogId(peopleLog
								.getId()));
			}
			processPeopleLogOrgAndUserId();
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		}

		return SUCCESS;
	}

	private void processPeopleLogOrgAndUserId() throws Exception {
		Organization org = organizationDubboService.getSimpleOrgById(ThreadVariable
				.getOrganization().getId());
		if (peopleLog == null) {
			peopleLog = new PeopleLog();
		}
		peopleLog.setOrganization(org);
	}

	@Action(value = "maintainPeopleLog", results = {
			@Result(name = "success", type = "json", params = { "root",
					"peopleLog", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainPeopleLog() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			peopleLog = peopleLogService.addPeopleLog(peopleLog);
			if (peopleLog.getIsAttachment()) {
				peopleLog.setPeopleLogFiles(peopleLogService
						.addAttachFileByPeopleLogId(peopleLog.getId(),
								attachFiles));
			}
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			peopleLog = peopleLogService
					.updatePeopleLog(peopleLog, attachFiles);
			return SUCCESS;
		}
		return ERROR;
	}

	@Action(value = "maintainPeopleLogFromServiceRecord", results = {
			@Result(name = "success", type = "json", params = { "root",
					"peopleLog", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainPeopleLogFromServiceRecord() throws Exception {
		if (null == peopleLog || null == peopleLog.getServiceRecordId()) {
			errorMessage = "参数传值不正确";
			return ERROR;
		}
		peopleLog = peopleLogService.addPeopleLogForServiceRecord(peopleLog,
				attachFiles);
		return SUCCESS;
	}

	@Action(value = "addpreDevelopPeopleLog", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addpreDevelopPeopleLog() throws Exception {
		if (null == peopleLog || null == peopleLog.getServiceRecordId()) {
			errorMessage = "参数传值不正确";
			return ERROR;
		}
		peopleLog = peopleLogService.addPeopleLogForServiceRecord(peopleLog,
				attachFiles);
		return SUCCESS;
	}

	@Action(value = "deletePeopleLogAttachFile", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePeopleLogAttachFile() throws Exception {
		if (null == peopleLogAttachFile || null == peopleLogAttachFile.getId()) {
			errorMessage = "所传参数不正确";
			return ERROR;
		}
		peopleLogService.deletePeopleLogAttachFileById(peopleLogAttachFile
				.getId());
		return SUCCESS;
	}

	/**
	 * id加密删除单条记录
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "deletePeopleLogByEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root",
					"logIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePeopleLogByEncrypt() throws Exception {
		peopleLogService.deletePeopleLogByIds(analyzeLogIds());
		return SUCCESS;
	}

	@Action(value = "deletePeopleLog", results = {
			@Result(name = "success", type = "json", params = { "root",
					"logIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePeopleLog() throws Exception {
		peopleLogService.deletePeopleLogByIds(analyzeLogIds());
		return SUCCESS;
	}

	@Action(value = "view", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String view() throws Exception {
		peopleLog = peopleLogService.getPeopleLogById(peopleLog.getId());
		return "success";
	}

	@Action(value = "getPeopleLogById", results = { @Result(type = "json", name = "success", params = {
			"root", "peopleLog", "ignoreHierarchy", "false" }) })
	public String getPeopleLogById() throws Exception {
		peopleLog = peopleLogService.getPeopleLogById(peopleLog.getId());
		return "success";
	}

	/** 获取当前登录用户的日志 */
	@Action(value = "peopleLogList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPeopleLogForPageByUserId() throws Exception {
		Integer commentNums = null;
		if (isComment) {
			commentNums = 0;
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				peopleLogService
						.findPeopleLogForPageByUserId(ThreadVariable.getUser()
								.getId(), page, rows, sidx, sord, commentNums),
				organizationDubboService, new String[] { "organization" }, logId));
		return SUCCESS;
	}

	@Action(value = "peopleLogList4Bench", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPeopleLogForPageByOrgInternalCode4Bench()
			throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				peopleLogService.findPeopleLogForPageByOrgInternalCode4Bench(
						ThreadVariable.getSession().getOrgInternalCode(), page,
						rows, sidx, sord, ControllerHelper
								.getOrganizationRelativeName(ThreadVariable
										.getUser().getOrganization().getId(),
										organizationDubboService)),
				organizationDubboService, new String[] { "organization" }, logId));
		return SUCCESS;
	}

	@Action(value = "downloadPeopleLogAttachFile")
	public String downloadPeopleLogAttachFile() throws Exception {
		if (null == peopleLogAttachFile || null == peopleLogAttachFile.getId()) {
			errorMessage = "参数不正确";
			return ERROR;
		}
		peopleLogAttachFile = peopleLogService
				.getPeopleLogAttachFileById(peopleLogAttachFile.getId());
		if (null == peopleLogAttachFile) {
			errorMessage = "民情日志附件不存在";
			return ERROR;
		}
		inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
				+ File.separator + peopleLogAttachFile.getFileActualUrl());
		downloadFileName = new String(peopleLogAttachFile.getFileName()
				.getBytes("gbk"), "ISO8859-1");
		return STREAM_SUCCESS;
	}

	public PeopleLog getPeopleLog() {
		return peopleLog;
	}

	public void setPeopleLog(PeopleLog peopleLog) {
		this.peopleLog = peopleLog;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public String getLogIds() {
		return logIds;
	}

	public void setLogIds(String logIds) {
		this.logIds = logIds;
	}

	public void setOrginternalCode(String orginternalCode) {
		this.orginternalCode = orginternalCode;
	}

	public String getOrginternalCode() {
		return orginternalCode;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getLogId() {
		return logId;
	}

	public Boolean getIsComment() {
		return isComment;
	}

	public void setIsComment(Boolean isComment) {
		this.isComment = isComment;
	}

	private List<Long> analyzeLogIds() {
		String[] deleteId = logIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		return idList;
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public PeopleLogAttachFiles getPeopleLogAttachFile() {
		return peopleLogAttachFile;
	}

	public void setPeopleLogAttachFile(PeopleLogAttachFiles peopleLogAttachFile) {
		this.peopleLogAttachFile = peopleLogAttachFile;
	}

}
