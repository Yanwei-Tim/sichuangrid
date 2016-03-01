package com.tianque.working.dao;

import java.util.List;

import com.tianque.working.domain.SignificantIssuedealAttachFiles;

public interface SignificantIssuedealAttachFileDao {

	public SignificantIssuedealAttachFiles addSignificantIssuedealAttachFiles(
			SignificantIssuedealAttachFiles significantIssuedealAttachFiles);

	public SignificantIssuedealAttachFiles getSimpleSignificantIssuedealAttachFilesById(Long id);

	public List<SignificantIssuedealAttachFiles> getSimpleSignificantIssuedealAttachFilesByDisputeIssuedealId(
			Long id);

	public void deleteSignificantIssuedealAttachFilesById(Long id);

	public void deleteSignificantIssuedealAttachFilesByDisputeIssuedealId(Long id);

	public Long sumAllFileSizeByAdvisingId(Long id);

	public List<Long> findAllNotDuplicateSignificantIssuedealIds();
}
