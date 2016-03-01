package com.tianque.working.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.working.domain.DailyAttachFiles;
import com.tianque.working.domain.SocietySecurity;
import com.tianque.working.service.DailyAttachFileService;
import com.tianque.working.service.SocietySecurityService;

@SuppressWarnings("serial")
@Controller("societySecurityController")
@Scope("prototype")
@Transactional
@Namespace("/workingRecord/societySecurityManage")
public class SocietySecurityController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SocietySecurityService societySecurityService;
	@Autowired
	private DailyAttachFileService dailyAttachFileService;

	private DailyAttachFiles dailyAttachFile;
	private Organization organization;
	private Long dailyDirectoryId;
	private String[] attachFiles;
	private SocietySecurity societySecurity;
	private String societySecurityIds;

	@Action(value = "societySecurityList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String societySecurityList() throws Exception {
		if (organization == null || organization.getId() == null
				|| dailyDirectoryId == null || dailyDirectoryId.intValue() == 0) {
			gridPage = new GridPage(new PageInfo<SocietySecurity>());
			return SUCCESS;
		}
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						societySecurityService
								.findSocietySecuritysForPageByOrgIdAndDailyDirectoryId(
										dailyDirectoryId, organization.getId(),
										page, rows, sidx, sord),
						organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/daily/societySecurity/maintainSocietySecurityDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {
			societySecurity = new SocietySecurity();
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(getMode())) {
			return "search";
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())
				|| DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
			if (societySecurity == null || societySecurity.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			societySecurity = societySecurityService
					.getSocietySecurityById(societySecurity.getId());

			societySecurity.setDailyAttachFiles(dailyAttachFileService
					.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
							societySecurity.getId(), getDailyDirectoryType()));
		}
		return SUCCESS;
	}

	private Long getDailyDirectoryType() {
		return societySecurityService.getDailyDirectoryType();
	}

	@Action(value = "downloadDailyAttachFile")
	public void downloadDailyAttachFile() throws Exception {
		if (dailyAttachFile == null || dailyAttachFile.getId() == null) {
			this.errorMessage = "参数错误";
			return;
		}
		dailyAttachFile = dailyAttachFileService
				.getSimpleDailyAttachFilesById(dailyAttachFile.getId());
		if (dailyAttachFile == null) {
			this.errorMessage = "未找到对应的附件";
			return;
		}
		inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
				+ File.separator + dailyAttachFile.getFileactualUrl());
		downloadFileName = new String(dailyAttachFile.getFileName().getBytes(
				"gbk"), "ISO8859-1");
		OutputStream outputStream;
		outputStream = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().addHeader("Content-Disposition",
				"inline; filename=\"" + downloadFileName + "\"");
		ServletActionContext.getResponse().addHeader("content-type",
				"text/plain;charset=GBK");
		int read;
		while ((read = inputStream.read()) != -1) {
			outputStream.write(read);
		}
	}

	@Action(value = "deleteDailyAttachFile", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteDailyAttachFile() throws Exception {
		if (dailyAttachFile == null || dailyAttachFile.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		dailyAttachFileService.deleteDailyAttachFilesById(dailyAttachFile
				.getId());
		return SUCCESS;
	}

	@Action(value = "addSocietySecurity", results = {
			@Result(name = "success", type = "json", params = { "root",
					"societySecurity" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addSocietySecurity() throws Exception {
		if (validateInput()) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		societySecurity.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		societySecurity = societySecurityService
				.addSocietySecurity(societySecurity);
		addDailyAttachFile(societySecurity, attachFiles);
		return SUCCESS;
	}

	private boolean validateInput() {
		return societySecurity == null;
	}

	private boolean addDailyAttachFile(SocietySecurity societySecurity,
			String[] attachFiles) {
		if (attachFiles == null || attachFiles.length == 0) {
			return true;
		}
		for (String fileName : attachFiles) {
			if (!addAttachFile(societySecurity, fileName))
				return false;
		}
		return true;
	}

	private boolean addAttachFile(SocietySecurity societySecurity,
			String fileName) {
		DailyAttachFiles dailyAttachFile = new DailyAttachFiles();
		dailyAttachFile.setDailyId(societySecurity.getId());
		dailyAttachFile.setDailyDirectoryType(getDailyDirectoryType());
		StoredFile storedFile = null;
		try {
			storedFile = FileUtil.copyTmpFileToStoredFile(fileName,
					GridProperties.DAILYLOG_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dailyAttachFile.setFileSize(storedFile.getFileSize());
		dailyAttachFile.setFileactualUrl(storedFile.getStoredFilePath()
				+ File.separator + storedFile.getStoredFileName());
		dailyAttachFile.setFileName(fileName);
		societySecurity.getDailyAttachFiles().add(
				dailyAttachFileService.addDailyAttachFiles(dailyAttachFile));
		return true;
	}

	@Action(value = "updateSocietySecurity", results = {
			@Result(name = "success", type = "json", params = { "root",
					"societySecurity" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateSocietySecurity() throws Exception {
		if (validateInput()) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		societySecurity = societySecurityService
				.updateSocietySecurity(societySecurity);
		if (!margeAttachFiles(societySecurity))
			return ERROR;
		societySecurity.setDailyAttachFiles(dailyAttachFileService
				.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
						societySecurity.getId(), getDailyDirectoryType()));
		return SUCCESS;
	}

	private boolean margeAttachFiles(SocietySecurity societySecurity) {
		if (attachFiles == null || attachFiles.length == 0)
			return true;
		List<DailyAttachFiles> dailyAttachFiles = dailyAttachFileService
				.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
						societySecurity.getId(), getDailyDirectoryType());
		List<String> dailyAttachFileName = new ArrayList<String>();
		for (DailyAttachFiles dailyAttachFile : dailyAttachFiles) {
			dailyAttachFileName.add(dailyAttachFile.getFileName());
		}
		for (String fileName : attachFiles) {
			if (!dailyAttachFileName.contains(fileName))
				if (!addAttachFile(societySecurity, fileName))
					return false;
		}
		return true;
	}

	@Action(value = "deleteSocietySecurity", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteSocietySecurity() throws Exception {
		String[] ids = societySecurityIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			if ("".equals(ids[i])) {
				continue;
			}
			Long id = Long.valueOf(ids[i]);
			dailyAttachFileService
					.deleteDailyAttachFilesByByDailyIdAndDailyDirectoryType(id,
							getDailyDirectoryType());
			societySecurityService.deleteSocietySecurityById(id);
		}
		return SUCCESS;
	}

	@Action(value = "getSocietySecurityById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"societySecurity" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getSocietySecurityById() throws Exception {
		societySecurity = societySecurityService
				.getSocietySecurityById(societySecurity.getId());
		if (societySecurity != null && societySecurity.getId() != null) {
			societySecurity.setDailyAttachFiles(dailyAttachFileService
					.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
							societySecurity.getId(), getDailyDirectoryType()));
		}
		if (societySecurity == null) {
			societySecurity = new SocietySecurity();
		}
		return SUCCESS;
	}

	public DailyAttachFiles getDailyAttachFile() {
		return dailyAttachFile;
	}

	public void setDailyAttachFile(DailyAttachFiles dailyAttachFile) {
		this.dailyAttachFile = dailyAttachFile;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getDailyDirectoryId() {
		return dailyDirectoryId;
	}

	public void setDailyDirectoryId(Long dailyDirectoryId) {
		this.dailyDirectoryId = dailyDirectoryId;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public SocietySecurity getSocietySecurity() {
		return societySecurity;
	}

	public void setSocietySecurity(SocietySecurity societySecurity) {
		this.societySecurity = societySecurity;
	}

	public String getSocietySecurityIds() {
		return societySecurityIds;
	}

	public void setSocietySecurityIds(String societySecurityIds) {
		this.societySecurityIds = societySecurityIds;
	}
}
