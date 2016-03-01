package com.tianque.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.IssueAttachFileDao;
import com.tianque.issue.domain.IssueAttachFile;

@Repository("issueAttachFileDao")
public class IssueAttachFileDaoImpl extends AbstractBaseDao implements IssueAttachFileDao {

	@Override
	public IssueAttachFile addIssueAttachFile(IssueAttachFile attachFile) {
		Long id = (Long) getSqlMapClientTemplate().insert("attachFile.addIssueAttachFile",
				attachFile);
		return getIssueAttachFileById(id);
	}

	@Override
	public IssueAttachFile getIssueAttachFileById(Long id) {
		return (IssueAttachFile) getSqlMapClientTemplate().queryForObject(
				"attachFile.getIssueAttachFileById", id);
	}

	@Override
	public List<IssueAttachFile> findIssueAttachFilesByIssueId(Long issueLog) {
		return getSqlMapClientTemplate().queryForList("attachFile.getIssueAttachFilesByIssueId",
				issueLog);
	}

	@Override
	public List<IssueAttachFile> findIssueLogAttachFilesByIssueId(Long issueLog) {
		return getSqlMapClientTemplate().queryForList("attachFile.getIssueLogAttachFilesByIssueId",
				issueLog);
	}

	@Override
	public void deleteAttachFilesByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete("attachFile.deleteAttachFilesByIssueId", issueId);
	}

	@Override
	public void deleteAttachFileById(Long id) {
		getSqlMapClientTemplate().delete("attachFile.deleteAttachFileById", id);
	}
}
