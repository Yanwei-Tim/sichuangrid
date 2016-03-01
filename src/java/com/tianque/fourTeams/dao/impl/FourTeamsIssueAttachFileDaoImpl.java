package com.tianque.fourTeams.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.fourTeams.dao.FourTeamsIssueAttachFileDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;

@Repository("fourTeamsIssueAttachFileDao")
public class FourTeamsIssueAttachFileDaoImpl extends AbstractBaseDao implements
		FourTeamsIssueAttachFileDao {

	@Override
	public FourTeamsIssueAttachFile addIssueAttachFile(FourTeamsIssueAttachFile attachFile) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"attachFile.addIssueAttachFile", attachFile);
		return getIssueAttachFileById(id);
	}

	@Override
	public FourTeamsIssueAttachFile getIssueAttachFileById(Long id) {
		return (FourTeamsIssueAttachFile) getSqlMapClientTemplate().queryForObject(
				"attachFile.getIssueAttachFileById", id);
	}

	@Override
	public List<FourTeamsIssueAttachFile> findIssueAttachFilesByIssueId(Long issueLog) {
		return getSqlMapClientTemplate().queryForList(
				"attachFile.getIssueAttachFilesByIssueId", issueLog);
	}

	@Override
	public List<FourTeamsIssueAttachFile> findIssueLogAttachFilesByIssueId(Long issueLog) {
		return getSqlMapClientTemplate().queryForList(
				"attachFile.getIssueLogAttachFilesByIssueId", issueLog);
	}

	@Override
	public void deleteAttachFilesByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete(
				"attachFile.deleteAttachFilesByIssueId", issueId);
	}

	@Override
	public void deleteAttachFileById(Long id) {
		getSqlMapClientTemplate().delete("attachFile.deleteAttachFileById", id);
	}
}
