package com.tianque.working.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.working.dao.SignificantIssuedealAttachFileDao;
import com.tianque.working.domain.SignificantIssuedealAttachFiles;

@Repository("significantIssuedealAttachFileDao")
public class SignificantIssuedealAttachFileDaoImpl extends AbstractBaseDao implements
		SignificantIssuedealAttachFileDao {

	@Override
	public SignificantIssuedealAttachFiles addSignificantIssuedealAttachFiles(
			SignificantIssuedealAttachFiles significantIssuedealAttachFiles) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"significantIssuedealAttachFiles.addSignificantIssuedealAttachFiles",
				significantIssuedealAttachFiles);
		return this.getSimpleSignificantIssuedealAttachFilesById(id);
	}

	@Override
	public SignificantIssuedealAttachFiles getSimpleSignificantIssuedealAttachFilesById(Long id) {
		return (SignificantIssuedealAttachFiles) getSqlMapClientTemplate().queryForObject(
				"significantIssuedealAttachFiles.getSimpleSignificantIssuedealAttachFilesById", id);
	}

	@Override
	public List<SignificantIssuedealAttachFiles> getSimpleSignificantIssuedealAttachFilesByDisputeIssuedealId(
			Long id) {
		return getSqlMapClientTemplate()
				.queryForList(
						"significantIssuedealAttachFiles.getSimpleSignificantIssuedealAttachFilesByDisputeIssuedealId",
						id);
	}

	@Override
	public void deleteSignificantIssuedealAttachFilesById(Long id) {
		getSqlMapClientTemplate().delete(
				"significantIssuedealAttachFiles.deleteSignificantIssuedealAttachFilesById", id);
	}

	@Override
	public void deleteSignificantIssuedealAttachFilesByDisputeIssuedealId(Long id) {
		getSqlMapClientTemplate()
				.delete("significantIssuedealAttachFiles.deleteSignificantIssuedealAttachFilesByDisputeIssuedealId",
						id);
	}

	@Override
	public Long sumAllFileSizeByAdvisingId(Long id) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"significantIssuedealAttachFiles.sumAllFileSizeByAdvisingId", id);
	}

	@Override
	public List<Long> findAllNotDuplicateSignificantIssuedealIds() {
		return getSqlMapClientTemplate().queryForList(
				"significantIssuedealAttachFiles.findAllNotDuplicateSignificantIssuedealIds");
	}

}
