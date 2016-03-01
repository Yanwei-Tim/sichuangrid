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
import com.tianque.working.domain.SecurityPropaganda;
import com.tianque.working.service.DailyAttachFileService;
import com.tianque.working.service.SecurityPropagandaService;

@SuppressWarnings("serial")
@Controller("securityPropagandaController")
@Scope("prototype")
@Transactional
@Namespace("/workingRecord/securityPropagandaManage")
public class SecurityPropagandaController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SecurityPropagandaService securityPropagandaService;
	@Autowired
	private DailyAttachFileService dailyAttachFileService;

	private DailyAttachFiles dailyAttachFile;
	private Organization organization;
	private Long dailyDirectoryId;
	private String[] attachFiles;
	private SecurityPropaganda securityPropaganda;
	private String securityPropagandaIds;

	@Action(value = "securityPropagandaList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String securityPropagandaList() throws Exception {
		if (organization == null || organization.getId() == null
				|| dailyDirectoryId == null || dailyDirectoryId.intValue() == 0) {
			gridPage = new GridPage(new PageInfo<SecurityPropaganda>());
			return SUCCESS;
		}
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						securityPropagandaService
								.findSecurityPropagandasForPageByOrgIdAndDailyDirectoryId(
										dailyDirectoryId, organization.getId(),
										page, rows, sidx, sord),
						organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/daily/securityPropaganda/maintainSecurityPropagandaDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {
			securityPropaganda = new SecurityPropaganda();
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(getMode())) {
			return "search";
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())
				|| DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
			if (securityPropaganda == null
					|| securityPropaganda.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			securityPropaganda = securityPropagandaService
					.getSecurityPropagandaById(securityPropaganda.getId());

			securityPropaganda
					.setDailyAttachFiles(dailyAttachFileService
							.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
									securityPropaganda.getId(),
									getDailyDirectoryType()));
		}
		return SUCCESS;
	}

	private Long getDailyDirectoryType() {
		return securityPropagandaService.getDailyDirectoryType();
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

	@Action(value = "addSecurityPropaganda", results = {
			@Result(name = "success", type = "json", params = { "root",
					"securityPropaganda" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addSecurityPropaganda() throws Exception {
		if (validateInput()) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		securityPropaganda.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		securityPropaganda = securityPropagandaService
				.addSecurityPropaganda(securityPropaganda);
		addDailyAttachFile(securityPropaganda, attachFiles);
		return SUCCESS;
	}

	private boolean validateInput() {
		return securityPropaganda == null;
	}

	private boolean addDailyAttachFile(SecurityPropaganda securityPropaganda,
			String[] attachFiles) {
		if (attachFiles == null || attachFiles.length == 0) {
			return true;
		}
		for (String fileName : attachFiles) {
			if (!addAttachFile(securityPropaganda, fileName))
				return false;
		}
		return true;
	}

	private boolean addAttachFile(SecurityPropaganda securityPropaganda,
			String fileName) {
		DailyAttachFiles dailyAttachFile = new DailyAttachFiles();
		dailyAttachFile.setDailyId(securityPropaganda.getId());
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
		securityPropaganda.getDailyAttachFiles().add(
				dailyAttachFileService.addDailyAttachFiles(dailyAttachFile));
		return true;
	}

	@Action(value = "updateSecurityPropaganda", results = {
			@Result(name = "success", type = "json", params = { "root",
					"securityPropaganda" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateSecurityPropaganda() throws Exception {
		if (validateInput()) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		securityPropaganda = securityPropagandaService
				.updateSecurityPropaganda(securityPropaganda);
		if (!margeAttachFiles(securityPropaganda))
			return ERROR;
		securityPropaganda.setDailyAttachFiles(dailyAttachFileService
				.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
						securityPropaganda.getId(), getDailyDirectoryType()));
		return SUCCESS;
	}

	private boolean margeAttachFiles(SecurityPropaganda securityPropaganda) {
		if (attachFiles == null || attachFiles.length == 0)
			return true;
		List<DailyAttachFiles> dailyAttachFiles = dailyAttachFileService
				.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
						securityPropaganda.getId(), getDailyDirectoryType());
		List<String> dailyAttachFileName = new ArrayList<String>();
		for (DailyAttachFiles dailyAttachFile : dailyAttachFiles) {
			dailyAttachFileName.add(dailyAttachFile.getFileName());
		}
		for (String fileName : attachFiles) {
			if (!dailyAttachFileName.contains(fileName))
				if (!addAttachFile(securityPropaganda, fileName))
					return false;
		}
		return true;
	}

	@Action(value = "deleteSecurityPropaganda", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteSecurityPropaganda() throws Exception {
		String[] ids = securityPropagandaIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			if ("".equals(ids[i])) {
				continue;
			}
			Long id = Long.valueOf(ids[i]);
			dailyAttachFileService
					.deleteDailyAttachFilesByByDailyIdAndDailyDirectoryType(id,
							getDailyDirectoryType());
			securityPropagandaService.deleteSecurityPropagandaById(id);
		}
		return SUCCESS;
	}

	@Action(value = "getSecurityPropagandaById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"securityPropaganda" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getSecurityPropagandaById() throws Exception {
		securityPropaganda = securityPropagandaService
				.getSecurityPropagandaById(securityPropaganda.getId());
		if (securityPropaganda != null && securityPropaganda.getId() != null) {
			securityPropaganda
					.setDailyAttachFiles(dailyAttachFileService
							.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
									securityPropaganda.getId(),
									getDailyDirectoryType()));
		}
		if (securityPropaganda == null) {
			securityPropaganda = new SecurityPropaganda();
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

	public SecurityPropaganda getSecurityPropaganda() {
		return securityPropaganda;
	}

	public void setSecurityPropaganda(SecurityPropaganda securityPropaganda) {
		this.securityPropaganda = securityPropaganda;
	}

	public String getSecurityPropagandaIds() {
		return securityPropagandaIds;
	}

	public void setSecurityPropagandaIds(String securityPropagandaIds) {
		this.securityPropagandaIds = securityPropagandaIds;
	}
}
