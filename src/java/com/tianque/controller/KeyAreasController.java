package com.tianque.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.workingRecord.KeyAreasOfInvestigationInfoWorkingRecord;
import com.tianque.service.KeyAreasOfInvestigationInfoWorkingRecordService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyLogAttachFile;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyLogAttachFileService;

@SuppressWarnings("serial")
@Controller("keyAreasController")
@Scope("prototype")
@Transactional
public class KeyAreasController extends BaseAction {
	@Autowired
	private KeyAreasOfInvestigationInfoWorkingRecordService keyAreasOfInvestigationInfoWorkingRecordService;

	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private DailyLogAttachFileService dailyLogAttachFileService;

	private KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord;
	private Organization organization;
	private DailyDirectory dailyDirectory;
	private Long dailyDirectoryId;
	private String[] attachFiles;

	public String addKeyAreaWorkingRecord() throws Exception {
		keyAreasOfInvestigationInfoWorkingRecord.setOrganization(ThreadVariable
				.getUser().getOrganization());
		keyAreasOfInvestigationInfoWorkingRecord = keyAreasOfInvestigationInfoWorkingRecordService
				.addKeyAreasOfInvestigationInfoWorkingRecord(keyAreasOfInvestigationInfoWorkingRecord);
		addDailyLogAttachFile(keyAreasOfInvestigationInfoWorkingRecord,
				attachFiles);
		return SUCCESS;
	}

	public String updateKeyAreaWorkingRecord() throws Exception {
		keyAreasOfInvestigationInfoWorkingRecord.setOrganization(ThreadVariable
				.getUser().getOrganization());
		keyAreasOfInvestigationInfoWorkingRecord = keyAreasOfInvestigationInfoWorkingRecordService
				.updateKeyAreasOfInvestigationInfoWorkingRecord(keyAreasOfInvestigationInfoWorkingRecord);
		if (!margeAttachFiles(keyAreasOfInvestigationInfoWorkingRecord))
			return ERROR;
		keyAreasOfInvestigationInfoWorkingRecord
				.setDailyLogAttachFile(dailyLogAttachFileService
						.getSimpleDailyLogAttachFileByDailyLogId(keyAreasOfInvestigationInfoWorkingRecord
								.getId()));
		return SUCCESS;
	}

	public String deleteKeyAreaWorkingRecord() throws Exception {
		keyAreasOfInvestigationInfoWorkingRecordService
				.deleteKeyAreasOfInvestigationInfoWorkingRecord(keyAreasOfInvestigationInfoWorkingRecord
						.getId());
		return SUCCESS;
	}

	private boolean margeAttachFiles(
			KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord) {
		if (attachFiles == null || attachFiles.length == 0)
			return true;
		List<DailyLogAttachFile> dailyLogAttachFiles = dailyLogAttachFileService
				.getSimpleDailyLogAttachFileByDailyLogId(keyAreasOfInvestigationInfoWorkingRecord
						.getId());
		List<String> dailyLogAttachFileName = new ArrayList<String>();
		for (DailyLogAttachFile dailyLogAttachFile : dailyLogAttachFiles) {
			dailyLogAttachFileName.add(dailyLogAttachFile.getFileName());
		}
		for (String fileName : attachFiles) {
			if (!dailyLogAttachFileName.contains(fileName))
				if (!addAttachFile(keyAreasOfInvestigationInfoWorkingRecord,
						fileName))
					return false;
		}
		return true;
	}

	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			prepareAddKeyAreaRecord();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			prepareUpdateKeyAreaWorkingRecord();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			viewKeyAreaWorkingRecord();
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			viewDailyLog();
			return DialogMode.PRINT_MODE;
		}
		return SUCCESS;
	}

	private boolean addDailyLogAttachFile(
			KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord,
			String[] attachFiles) {
		if (attachFiles == null || attachFiles.length == 0) {
			return true;
		}
		for (String fileName : attachFiles) {
			if (!addAttachFile(keyAreasOfInvestigationInfoWorkingRecord,
					fileName))
				return false;
		}
		return true;
	}

	private boolean addAttachFile(
			KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord,
			String fileName) {
		DailyLogAttachFile dailyLogAttachFile = new DailyLogAttachFile();
		dailyLogAttachFile
				.setWorkingRecord(keyAreasOfInvestigationInfoWorkingRecord);
		StoredFile storedFile = null;
		try {
			storedFile = FileUtil.copyTmpFileToStoredFile(fileName,
					GridProperties.DAILYLOG_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dailyLogAttachFile.setFileSize(storedFile.getFileSize());
		dailyLogAttachFile.setFileActualUrl(storedFile.getStoredFilePath()
				+ File.separator + storedFile.getStoredFileName());
		dailyLogAttachFile.setFileName(fileName);
		dailyLogAttachFile
				.setDailyYear(keyAreasOfInvestigationInfoWorkingRecord
						.getDailyYear());
		keyAreasOfInvestigationInfoWorkingRecord.getDailyLogAttachFile().add(
				dailyLogAttachFileService
						.addDailyLogAttachFile(dailyLogAttachFile));
		return true;
	}

	private void viewDailyLog() {
		getKeyAreaWorkingRecodeDetail();
	}

	private void viewKeyAreaWorkingRecord() {
		getKeyAreaWorkingRecodeDetail();
	}

	private void getKeyAreaWorkingRecodeDetail() {
		if (dailyDirectory != null) {
			dailyDirectory = dailyDirectoryService
					.getSimpleDailyDirectoryById(dailyDirectory.getId());
		}
		keyAreasOfInvestigationInfoWorkingRecord = keyAreasOfInvestigationInfoWorkingRecordService
				.getKeyAreasOfInvestigationInfoWorkingRecordById(keyAreasOfInvestigationInfoWorkingRecord
						.getId());
	}

	private void prepareUpdateKeyAreaWorkingRecord() {
		getKeyAreaWorkingRecodeDetail();
	}

	private void prepareAddKeyAreaRecord() {
		dailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory.getId());
	}

	public String keyAreasList() throws Exception {
		gridPage = new GridPage(
				keyAreasOfInvestigationInfoWorkingRecordService
						.findKeyAreasOfInvestigationInfoWorkingRecordForPageByOrgInternalCode(
								organization.getId(), page, rows, sidx, sord,
								dailyDirectoryId));
		return SUCCESS;
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

	public DailyDirectory getDailyDirectory() {
		return dailyDirectory;
	}

	public void setDailyDirectory(DailyDirectory dailyDirectory) {
		this.dailyDirectory = dailyDirectory;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public KeyAreasOfInvestigationInfoWorkingRecord getKeyAreasOfInvestigationInfoWorkingRecord() {
		return keyAreasOfInvestigationInfoWorkingRecord;
	}

	public void setKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord) {
		this.keyAreasOfInvestigationInfoWorkingRecord = keyAreasOfInvestigationInfoWorkingRecord;
	}
}
