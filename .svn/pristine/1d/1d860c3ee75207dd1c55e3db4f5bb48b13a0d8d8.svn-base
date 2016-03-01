package com.tianque.plugin.account.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.account.api.PeopleAspirationDubboService;
import com.tianque.account.api.ThreeRecordsIssueDubboService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.plugin.account.domain.LedgerPeopleAspirations;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;

@Scope("request")
@Namespace("/threeRecords/peopleAspirationManage")
@Controller("threeRecordPeopleAspirationController")
public class PeopleAspirationController extends BaseAction {

	private LedgerPeopleAspirations peopleAspirations;
	private Organization organization;
	private String[] attachFiles;

	@Autowired
	private PeopleAspirationDubboService peopleAspirationDubboService;
	@Autowired
	private ThreeRecordsIssueDubboService threeRecordsIssueDubboService;

	/**
	 * peopleAspriation
	 * 
	 * @return
	 */
	@Action(value = "addPeopleAspiration", results = {
			@Result(name = "success", type = "json", params = { "root",
					"peopleAspirations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addPeopleAspiration() throws Exception {
		if (peopleAspirations == null
				|| !checkOrganization(peopleAspirations.getOrganization())
				|| !checkOrganization(peopleAspirations.getOccurOrg())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		
//		List<ThreeRecordsIssueAttachFile> files = threeRecordsIssueDubboService
//				.combineIssueAttachFile(attachFiles);
		List<ThreeRecordsIssueAttachFile> files = AttachFileUtil.getIssueAttachFiles(attachFiles, threeRecordsIssueDubboService);
		peopleAspirations = peopleAspirationDubboService.addPeopleAspirations(
				peopleAspirations, files);
		return SUCCESS;
	}
	
	@Action(value = "updatePeopleAspiration", results = {
			@Result(name = "success", type = "json", params = { "root",
					"peopleAspirations", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updatePeopleAspiration() throws Exception {
		if (peopleAspirations == null || peopleAspirations.getId() == null
				|| !checkOrganization(peopleAspirations.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
//		List<ThreeRecordsIssueAttachFile> files = threeRecordsIssueDubboService
//				.combineIssueAttachFile(attachFiles);
		List<ThreeRecordsIssueAttachFile> files = AttachFileUtil.getIssueAttachFiles(attachFiles, threeRecordsIssueDubboService);
		peopleAspirations = peopleAspirationDubboService
				.updatePeopleAspirations(peopleAspirations, files);

		return SUCCESS;
	}

	// 验证组织机构
	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public LedgerPeopleAspirations getPeopleAspirations() {
		return peopleAspirations;
	}

	public void setPeopleAspirations(LedgerPeopleAspirations peopleAspirations) {
		this.peopleAspirations = peopleAspirations;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

}
