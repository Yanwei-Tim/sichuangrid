package com.tianque.plugin.account.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.tianque.account.api.ThreeRecordsIssueDubboService;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.plugin.account.domain.ThreeRecordsIssueAttachFile;

public class AttachFileUtil {
	public static List<ThreeRecordsIssueAttachFile> getIssueAttachFiles(String[] fileNameAndIdS,ThreeRecordsIssueDubboService threeRecordsIssueDubboService){
		if (fileNameAndIdS == null) {
			return null;
		}
		List<ThreeRecordsIssueAttachFile> list = new ArrayList<ThreeRecordsIssueAttachFile>();
		ThreeRecordsIssueAttachFile issueAttachFile = null;
		for (String fileNameAndId : fileNameAndIdS) {
			if (!StringUtil.isStringAvaliable(fileNameAndId)) {
				continue;
			}
			String[] fileName = fileNameAndId.split(",");
			if (fileNameAndId.indexOf(",") == 0
					&& fileName[1].indexOf(".") == -1) {
				fileNameAndId = fileNameAndId.substring(1);
			}
			String[] id_fileName = fileNameAndId.split(",");
			String id = id_fileName[0];
			issueAttachFile = new ThreeRecordsIssueAttachFile();
			if (StringUtil.isStringAvaliable(id)) {
				issueAttachFile = threeRecordsIssueDubboService.getIssueAttachFileById(Long
						.parseLong(id));
			} else {
				StoredFile sf = null;
				sf = FileUtil.copyTmpFileToStoredFile(id_fileName[1], "uploadFile" + File.separator + "threeRecords");
				issueAttachFile.setFileActualUrl(sf.getFullName());
				issueAttachFile.setFileName(id_fileName[1]);
			}
			list.add(issueAttachFile);
		}
		return list;
	}
	
	
	public static void removeIssueAllAttachFiles(Long issueId, int ledgerType, ThreeRecordsIssueDubboService threeRecordsIssueDubboService) {
		List<ThreeRecordsIssueAttachFile> issueAttachFiles = threeRecordsIssueDubboService.loadLedgerAndLogAttachFilesByLedgerIdAndType(
				issueId, ledgerType);
		if (issueAttachFiles != null && issueAttachFiles.size() > 0) {
			String webRootPath = FileUtil.getWebRoot();
			for (ThreeRecordsIssueAttachFile issueFile : issueAttachFiles) {
				File file = new File(webRootPath + File.separator
						+ issueFile.getFileActualUrl());
				if (file.exists()) {
					file.delete();
				}
			}
		}
	}
}
