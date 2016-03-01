package com.tianque.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.OverTimeIssueLogDao;

@Repository("overTimeIssueLogDao")
public class OverTimeIssueLogDaoImpl extends AbstractBaseDao implements OverTimeIssueLogDao {

	@Override
	public void deleteOverTimeIssueLogsByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete("overTimeIssueLog.deleteOverTimeIssueLogsByIssueId",
				issueId);
	}

}
