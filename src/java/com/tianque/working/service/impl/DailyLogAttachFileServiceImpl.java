package com.tianque.working.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.working.dao.DailyLogAttachFileDao;
import com.tianque.working.domain.DailyLogAttachFile;
import com.tianque.working.domain.WorkingRecord;
import com.tianque.working.service.DailyLogAttachFileService;

@Service("dailyLogAttachFileService")
@Transactional
public class DailyLogAttachFileServiceImpl extends AbstractBaseService implements
		DailyLogAttachFileService {
	@Autowired
	private DailyLogAttachFileDao dailyLogAttachFileDao;

	@Override
	public void updateDailyLogAttachFile(WorkingRecord workingRecord, String[] attachFiles) {
		try {
			if (null != workingRecord && null != workingRecord.getId() && null != attachFiles
					&& attachFiles.length > 0) {
				List<DailyLogAttachFile> dailyLogAttachFileList = dailyLogAttachFileDao
						.getSimpleDailyLogAttachFileByDailyLogId(workingRecord.getId());
				List<String> dailyLogAttachFileNames = new ArrayList<String>();
				for (DailyLogAttachFile dailyLogAttachFile : dailyLogAttachFileList) {
					dailyLogAttachFileNames.add(dailyLogAttachFile.getFileName());
				}
				for (String fileName : attachFiles) {
					if (!dailyLogAttachFileNames.contains(fileName)) {
						addAttachFile(workingRecord, fileName);
					}
				}
			}
			// return
			// publicNoticeAttachFileDao.listAttachFileByPublicNoticeId(publicNoticeId);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类DailyLogAttachFileServiceImpl的updateDailyLogAttachFile方法出现异常，原因：",
					"修改日志附件信息出现错误", e);
		}

	}

	private void addAttachFile(WorkingRecord workingRecord, String fileName) throws Exception {
		DailyLogAttachFile dailyLogAttachFile = new DailyLogAttachFile();
		dailyLogAttachFile.setWorkingRecord(workingRecord);
		StoredFile storeFile = FileUtil.copyTmpFileToStoredFile(fileName,
				GridProperties.PUBLICNOTICE_ATTACHFILE);
		dailyLogAttachFile.setFileActualUrl(storeFile.getStoredFilePath() + File.separator
				+ storeFile.getStoredFileName());
		dailyLogAttachFile.setFileName(storeFile.getStoredTruthFileName());
		dailyLogAttachFileDao.addDailyLogAttachFile(dailyLogAttachFile);
	}

	@Override
	public DailyLogAttachFile addDailyLogAttachFile(DailyLogAttachFile dailyLogAttachFile) {
		if (!validate(dailyLogAttachFile))
			throw new BusinessValidationException();
		return dailyLogAttachFileDao.addDailyLogAttachFile(dailyLogAttachFile);
	}

	@Override
	public void deleteDailyLogAttachFileByDailyLogId(Long id) {
		deleteAttachFileByDailyLogId(id);
		dailyLogAttachFileDao.deleteDailyLogAttachFileByDailyLogId(id);
	}

	@Override
	public void deleteDailyLogAttachFileById(Long id) {
		deleteAttachFileById(id);
		dailyLogAttachFileDao.deleteDailyLogAttachFileById(id);
	}

	@Override
	public List<DailyLogAttachFile> getSimpleDailyLogAttachFileByDailyLogId(Long id) {
		return dailyLogAttachFileDao.getSimpleDailyLogAttachFileByDailyLogId(id);
	}

	@Override
	public DailyLogAttachFile getSimpleDailyLogAttachFileById(Long id) {
		return dailyLogAttachFileDao.getSimpleDailyLogAttachFileById(id);
	}

	private boolean validate(DailyLogAttachFile dailyLogAttachFile) {
		if (dailyLogAttachFile == null)
			return false;
		if (dailyLogAttachFile.getFileName() == null
				|| "".equals(dailyLogAttachFile.getFileName().trim()))
			return false;
		if (dailyLogAttachFile.getFileActualUrl() == null
				|| "".equals(dailyLogAttachFile.getFileActualUrl().trim()))
			return false;
		// if (dailyLogAttachFile.getDailyLog() == null
		// || dailyLogAttachFile.getDailyYear() == null)
		// return false;
		return true;
	}

	private void deleteAttachFileById(Long id) {
		deleteAttachFile(dailyLogAttachFileDao.getSimpleDailyLogAttachFileById(id));
	}

	private void deleteAttachFile(DailyLogAttachFile dailyLogAttachFile) {
		if (dailyLogAttachFile != null) {
			File file = new File(dailyLogAttachFile.getFileActualUrl());
			if (file.exists())
				file.delete();
		}
	}

	private void deleteAttachFileByDailyLogId(Long id) {
		List<DailyLogAttachFile> dailyLogAttachFiles = dailyLogAttachFileDao
				.getSimpleDailyLogAttachFileByDailyLogId(id);
		if (dailyLogAttachFiles == null)
			return;
		for (DailyLogAttachFile dailyLogAttachFile : dailyLogAttachFiles) {
			deleteAttachFile(dailyLogAttachFile);
		}
	}

	@Override
	public Long sumAllFileSizeByDailyLogId(Long id) {
		return dailyLogAttachFileDao.sumAllFileSizeByDailyLogId(id);
	}

}
