package com.tianque.service.bridge.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDomain;
import com.tianque.dao.IssueNewDao;
import com.tianque.dao.IssueTypeDao;
import com.tianque.domain.IssueType;
import com.tianque.service.bridge.DomainDeleter;

@Repository("deleteIssueType")
public class IssueTypeDelete implements DomainDeleter {

	@Autowired
	private IssueTypeDao issueTypeDao;

	@Autowired
	private IssueNewDao issueDao;

	@Override
	public boolean checkCanDelete(BaseDomain domain) {
		IssueType issueType = (IssueType) domain;
		Integer count = issueDao.countIssueByIssueTypeId(issueType.getId());
		if (count != null && count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(BaseDomain domain) {
		IssueType issueType = (IssueType) domain;
		if (!checkCanDelete(issueType)) {
			issueTypeDao.deleteIssueTypeById(issueType.getIssueTypeDomain().getId(),
					issueType.getId());
			return true;
		}
		return false;
	}
}
