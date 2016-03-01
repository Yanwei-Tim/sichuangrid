package com.tianque.working.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.working.dao.SignificantIssuedealAttachFileDao;
import com.tianque.working.domain.SignificantIssuedealAttachFiles;
import com.tianque.working.service.SignificantIssuedealAttachFileService;

@Service("significantIssuedealAttachFileService")
@Transactional
public class SignificantIssuedealAttachFileServiceImpl implements
		SignificantIssuedealAttachFileService {

	@Autowired
	private SignificantIssuedealAttachFileDao significantIssuedealAttachFileDao;

	@Override
	public SignificantIssuedealAttachFiles addSignificantIssuedealAttachFiles(
			SignificantIssuedealAttachFiles significantIssuedealAttachFiles) {
		if (!validate(significantIssuedealAttachFiles))
			throw new BusinessValidationException();
		return significantIssuedealAttachFileDao
				.addSignificantIssuedealAttachFiles(significantIssuedealAttachFiles);
	}

	@Override
	public SignificantIssuedealAttachFiles getSimpleSignificantIssuedealAttachFilesById(
			Long id) {
		return significantIssuedealAttachFileDao
				.getSimpleSignificantIssuedealAttachFilesById(id);
	}

	@Override
	public List<SignificantIssuedealAttachFiles> getSimpleSignificantIssuedealAttachFilesByDisputeIssuedealId(
			Long id) {
		return significantIssuedealAttachFileDao
				.getSimpleSignificantIssuedealAttachFilesByDisputeIssuedealId(id);
	}

	@Override
	public void deleteSignificantIssuedealAttachFilesById(Long id) {
		deleteAttachFileById(id);
		significantIssuedealAttachFileDao
				.deleteSignificantIssuedealAttachFilesById(id);
	}

	@Override
	public void deleteSignificantIssuedealAttachFilesByDisputeIssuedealId(
			Long id) {
		deleteAttachFileByDisputeIssuedealId(id);
		significantIssuedealAttachFileDao
				.deleteSignificantIssuedealAttachFilesByDisputeIssuedealId(id);
	}

	@Override
	public Long sumAllFileSizeByAdvisingId(Long id) {
		return significantIssuedealAttachFileDao.sumAllFileSizeByAdvisingId(id);
	}

	private boolean validate(
			SignificantIssuedealAttachFiles significantIssuedealAttachFiles) {
		if (significantIssuedealAttachFiles == null)
			return false;
		if (significantIssuedealAttachFiles.getFileName() == null
				|| "".equals(significantIssuedealAttachFiles.getFileName()
						.trim()))
			return false;
		if (significantIssuedealAttachFiles.getFileactualUrl() == null
				|| "".equals(significantIssuedealAttachFiles.getFileactualUrl()
						.trim()))
			return false;
		return true;
	}

	private void deleteAttachFileById(Long id) {
		deleteAttachFile(significantIssuedealAttachFileDao
				.getSimpleSignificantIssuedealAttachFilesById(id));
	}

	private void deleteAttachFile(
			SignificantIssuedealAttachFiles significantIssuedealAttachFiles) {
		if (significantIssuedealAttachFiles != null) {
			File file = new File(
					significantIssuedealAttachFiles.getFileactualUrl());
			if (file.exists())
				file.delete();
		}
	}

	private void deleteAttachFileByDisputeIssuedealId(Long id) {
		List<SignificantIssuedealAttachFiles> significantIssuedealAttachFiles = significantIssuedealAttachFileDao
				.getSimpleSignificantIssuedealAttachFilesByDisputeIssuedealId(id);
		if (significantIssuedealAttachFiles == null)
			return;
		for (SignificantIssuedealAttachFiles significantIssuedealAttachFile : significantIssuedealAttachFiles) {
			deleteAttachFile(significantIssuedealAttachFile);
		}
	}

}
