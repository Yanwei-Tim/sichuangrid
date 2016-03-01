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
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.working.domain.DailyAttachFiles;
import com.tianque.working.domain.ServiceManagement;
import com.tianque.working.service.DailyAttachFileService;
import com.tianque.working.service.ServiceManagementService;

@SuppressWarnings("serial")
@Controller("serviceManagementController")
@Scope("prototype")
@Transactional
@Namespace("/workingRecord/serviceManagementManage")
public class ServiceManagementController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ServiceManagementService serviceManagementService;
	@Autowired
	private DailyAttachFileService dailyAttachFileService;

	private DailyAttachFiles dailyAttachFile;
	private Organization organization;
	private Long dailyDirectoryId;
	private String[] attachFiles;
	private ServiceManagement serviceManagement;
	private String serviceManagementIds;

	@Action(value = "serviceManagementList", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String serviceManagementList() throws Exception {
		if (organization == null || organization.getId() == null
				|| dailyDirectoryId == null) {
			gridPage = new GridPage(new PageInfo<ServiceManagement>());
			return SUCCESS;
		}
		gridPage = new GridPage(
				ControllerHelper.proccessRelativeOrgNameByPageInfo(
						serviceManagementService
								.findServiceManagementsForPageByOrgIdAndDailyDirectoryId(
										dailyDirectoryId, organization.getId(),
										page, rows, sidx, sord),
						organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/daily/serviceManagement/maintainTownServiceManagementDlg.jsp"),
			@Result(name = "city", location = "/daily/serviceManagement/maintainCityServiceManagementDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(getMode())) {
			if (dailyDirectoryId == null || dailyDirectoryId.intValue() == 0) {
				errorMessage = "参数错误";
				return ERROR;
			}
			serviceManagement = new ServiceManagement();
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(getMode())) {
			return "search";
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(getMode())
				|| DialogMode.EDIT_MODE.equalsIgnoreCase(getMode())) {
			if (serviceManagement == null || serviceManagement.getId() == null) {
				errorMessage = "参数错误";
				return ERROR;
			}
			serviceManagement = serviceManagementService
					.getServiceManagementById(serviceManagement.getId());

			serviceManagement
					.setDailyAttachFiles(dailyAttachFileService
							.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
									serviceManagement.getId(),
									getDailyDirectoryType()));
		}
		return buildResultType();
	}

	private String buildResultType() {
		if (validateOrg()) {
			return ERROR;
		}
		int orgLevel = serviceManagementService
				.checkCurrentOrgLevel(organization.getId());
		if (orgLevel >= OrganizationLevel.DISTRICT) {
			return "city";
		}
		return SUCCESS;
	}

	private Long getDailyDirectoryType() {
		return serviceManagementService.getDailyDirectoryType(organization
				.getId());
	}

	private boolean validateOrg() {
		return organization == null || organization.getId() == null;
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

	@Action(value = "addServiceManagement", results = {
			@Result(name = "success", type = "json", params = { "root",
					"serviceManagement" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addServiceManagement() throws Exception {
		if (validateInput()) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		serviceManagement.setOrganization(ThreadVariable.getUser()
				.getOrganization());
		serviceManagement = serviceManagementService
				.addServiceManagement(serviceManagement);
		addDailyAttachFile(serviceManagement, attachFiles);
		return SUCCESS;
	}

	private boolean validateInput() {
		return serviceManagement == null;
	}

	private boolean addDailyAttachFile(ServiceManagement serviceManagement,
			String[] attachFiles) {
		if (attachFiles == null || attachFiles.length == 0) {
			return true;
		}
		for (String fileName : attachFiles) {
			if (!addAttachFile(serviceManagement, fileName))
				return false;
		}
		return true;
	}

	private boolean addAttachFile(ServiceManagement serviceManagement,
			String fileName) {
		DailyAttachFiles dailyAttachFile = new DailyAttachFiles();
		dailyAttachFile.setDailyId(serviceManagement.getId());
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
		serviceManagement.getDailyAttachFiles().add(
				dailyAttachFileService.addDailyAttachFiles(dailyAttachFile));
		return true;
	}

	@Action(value = "updateServiceManagement", results = {
			@Result(name = "success", type = "json", params = { "root",
					"serviceManagement" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateServiceManagement() throws Exception {
		if (validateInput()) {
			this.errorMessage = "参数不正确";
			return ERROR;
		}
		serviceManagement = serviceManagementService
				.updateServiceManagement(serviceManagement);
		if (!margeAttachFiles(serviceManagement))
			return ERROR;
		serviceManagement.setDailyAttachFiles(dailyAttachFileService
				.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
						serviceManagement.getId(), getDailyDirectoryType()));
		return SUCCESS;
	}

	private boolean margeAttachFiles(ServiceManagement serviceManagement) {
		if (attachFiles == null || attachFiles.length == 0)
			return true;
		List<DailyAttachFiles> dailyAttachFiles = dailyAttachFileService
				.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
						serviceManagement.getId(), getDailyDirectoryType());
		List<String> dailyAttachFileName = new ArrayList<String>();
		for (DailyAttachFiles dailyAttachFile : dailyAttachFiles) {
			dailyAttachFileName.add(dailyAttachFile.getFileName());
		}
		for (String fileName : attachFiles) {
			if (!dailyAttachFileName.contains(fileName))
				if (!addAttachFile(serviceManagement, fileName))
					return false;
		}
		return true;
	}

	@Action(value = "deleteServiceManagement", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteServiceManagement() throws Exception {
		String[] ids = serviceManagementIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			if ("".equals(ids[i])) {
				continue;
			}
			Long id = Long.valueOf(ids[i]);
			dailyAttachFileService
					.deleteDailyAttachFilesByByDailyIdAndDailyDirectoryType(id,
							getDailyDirectoryType());
			serviceManagementService.deleteServiceManagementById(id);
		}
		return SUCCESS;
	}

	@Action(value = "getServiceManagementById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"serviceManagement" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getServiceManagementById() throws Exception {
		serviceManagement = serviceManagementService
				.getServiceManagementById(serviceManagement.getId());
		if (serviceManagement != null && serviceManagement.getId() != null) {
			serviceManagement
					.setDailyAttachFiles(dailyAttachFileService
							.getSimpleDailyAttachFilesByDailyIdAndDailyDirectoryType(
									serviceManagement.getId(),
									getDailyDirectoryType()));
		}
		if (serviceManagement == null) {
			serviceManagement = new ServiceManagement();
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

	public ServiceManagement getServiceManagement() {
		return serviceManagement;
	}

	public void setServiceManagement(ServiceManagement serviceManagement) {
		this.serviceManagement = serviceManagement;
	}

	public String getServiceManagementIds() {
		return serviceManagementIds;
	}

	public void setServiceManagementIds(String serviceManagementIds) {
		this.serviceManagementIds = serviceManagementIds;
	}
}
