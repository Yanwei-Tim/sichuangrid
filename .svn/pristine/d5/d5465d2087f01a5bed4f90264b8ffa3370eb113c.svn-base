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
import com.tianque.working.domain.GridManagementNormal;
import com.tianque.working.service.DailyAttachFileService;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.GridManagementNormalService;

@SuppressWarnings("serial")
@Controller("gridManagementNormalController")
@Scope("prototype")
@Transactional
@Namespace("/workingRecord/gridManagementNormalManage")
public class GridManagementNormalController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private GridManagementNormalService gridManagementNormalService;
	@Autowired
	private DailyAttachFileService dailyAttachFileService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;

	private DailyAttachFiles dailyAttachFile;
	private Organization organization;
	private Long dailyDirectoryId;
	private String[] attachFiles;
	private GridManagementNormal gridManagementNormal;
	private String gridManagementNormalIds;

	@Action(value = "gridManagementNormalList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String gridManagementNormalList() throws Exception {
		if (organization == null || organization.getId() == null
				|| dailyDirectoryId == null || dailyDirectoryId.intValue() == 0) {
			gridPage = new GridPage(new PageInfo<GridManagementNormal>());
			return SUCCESS;
		}
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						gridManagementNormalService
								.findGridManagementNormalsForPageByOrgIdAndDailyDirectoryId(
										dailyDirectoryId, organization.getId(),
										page, rows, sidx, sord),
						organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/daily/gridManagementNormal/maintainGridManagementNormalDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {
			gridManagementNormal = new GridManagementNormal();
			gridManagementNormal.setDailyDirectoryId(dailyDirectoryId);
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(getMode())) {
			return "search";
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())
				|| DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
			if (gridManagementNormal == null
					|| gridManagementNormal.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			gridManagementNormal = gridManagementNormalService
					.getGridManagementNormalById(gridManagementNormal.getId());

			gridManagementNormal.setDailyAttachFiles(dailyAttachFileService
					.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
							gridManagementNormal.getId(),
							getDailyDirectoryType()));
		}
		return SUCCESS;
	}

	private Long getDailyDirectoryType() {
		return gridManagementNormalService.getDailyDirectoryType();
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

	@Action(value = "addGridManagementNormal", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridManagementNormal" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addGridManagementNormal() throws Exception {
		if (validateInput()) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		gridManagementNormal.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		gridManagementNormal = gridManagementNormalService
				.addGridManagementNormal(gridManagementNormal);
		addDailyAttachFile(gridManagementNormal, attachFiles);
		return SUCCESS;
	}

	private boolean validateInput() {
		return gridManagementNormal == null;
	}

	private boolean addDailyAttachFile(
			GridManagementNormal gridManagementNormal, String[] attachFiles) {
		if (attachFiles == null || attachFiles.length == 0) {
			return true;
		}
		for (String fileName : attachFiles) {
			if (!addAttachFile(gridManagementNormal, fileName))
				return false;
		}
		return true;
	}

	private boolean addAttachFile(GridManagementNormal gridManagementNormal,
			String fileName) {
		DailyAttachFiles dailyAttachFile = new DailyAttachFiles();
		dailyAttachFile.setDailyId(gridManagementNormal.getId());
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
		gridManagementNormal.getDailyAttachFiles().add(
				dailyAttachFileService.addDailyAttachFiles(dailyAttachFile));
		return true;
	}

	@Action(value = "updateGridManagementNormal", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridManagementNormal" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateGridManagementNormal() throws Exception {
		if (validateInput()) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		gridManagementNormal = gridManagementNormalService
				.updateGridManagementNormal(gridManagementNormal);
		if (!margeAttachFiles(gridManagementNormal))
			return ERROR;
		gridManagementNormal.setDailyAttachFiles(dailyAttachFileService
				.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
						gridManagementNormal.getId(), getDailyDirectoryType()));
		return SUCCESS;
	}

	private boolean margeAttachFiles(GridManagementNormal gridManagementNormal) {
		if (attachFiles == null || attachFiles.length == 0)
			return true;
		List<DailyAttachFiles> dailyAttachFiles = dailyAttachFileService
				.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
						gridManagementNormal.getId(), getDailyDirectoryType());
		List<String> dailyAttachFileName = new ArrayList<String>();
		for (DailyAttachFiles dailyAttachFile : dailyAttachFiles) {
			dailyAttachFileName.add(dailyAttachFile.getFileName());
		}
		for (String fileName : attachFiles) {
			if (!dailyAttachFileName.contains(fileName))
				if (!addAttachFile(gridManagementNormal, fileName))
					return false;
		}
		return true;
	}

	@Action(value = "deleteGridManagementNormal", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteGridManagementNormal() throws Exception {
		String[] ids = gridManagementNormalIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			if ("".equals(ids[i])) {
				continue;
			}
			Long id = Long.valueOf(ids[i]);
			dailyAttachFileService
					.deleteDailyAttachFilesByByDailyIdAndDailyDirectoryType(id,
							getDailyDirectoryType());
			gridManagementNormalService.deleteGridManagementNormalById(id);
		}
		return SUCCESS;
	}

	@Action(value = "getGridManagementNormalById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridManagementNormal" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getGridManagementNormalById() throws Exception {
		gridManagementNormal = gridManagementNormalService
				.getGridManagementNormalById(gridManagementNormal.getId());
		if (gridManagementNormal != null
				&& gridManagementNormal.getId() != null) {
			gridManagementNormal.setDailyAttachFiles(dailyAttachFileService
					.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
							gridManagementNormal.getId(),
							getDailyDirectoryType()));
		}
		if (gridManagementNormal == null) {
			gridManagementNormal = new GridManagementNormal();
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

	public GridManagementNormal getGridManagementNormal() {
		return gridManagementNormal;
	}

	public void setGridManagementNormal(
			GridManagementNormal gridManagementNormal) {
		this.gridManagementNormal = gridManagementNormal;
	}

	public String getGridManagementNormalIds() {
		return gridManagementNormalIds;
	}

	public void setGridManagementNormalIds(String gridManagementNormalIds) {
		this.gridManagementNormalIds = gridManagementNormalIds;
	}
}
