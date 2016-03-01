package com.tianque.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.StatAnalyseIssueDao;
import com.tianque.domain.BaseInfoStanalObject;

@Repository("statAnalyseIssueDao")
@SuppressWarnings("unchecked")
public class StatAnalyseIssueDaoImpl extends AbstractBaseDao implements StatAnalyseIssueDao {

	@Override
	public List<BaseInfoStanalObject> statIssueNormalNotDone(Long orgId, Date startDate,
			Date endDate) {
		Map query = new HashMap();
		query.put("orgId", orgId);
		query.put("startDate", startDate);
		query.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList(
				"statAnalyseIssueStatue.statIssueNormalNotDone", query);
	}

	@Override
	public List<BaseInfoStanalObject> statIssueOverTimeNotDone(Long orgId, Date startDate,
			Date endDate) {
		Map query = new HashMap();
		query.put("orgId", orgId);
		query.put("startDate", startDate);
		query.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList(
				"statAnalyseIssueStatue.statIssueOverTimeNotDone", query);
	}

	@Override
	public List<BaseInfoStanalObject> statIssueNormalDone(Long orgId, Date startDate, Date endDate) {
		Map query = new HashMap();
		query.put("orgId", orgId);
		query.put("startDate", startDate);
		query.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList("statAnalyseIssueStatue.statIssueNormalDone",
				query);
	}

	@Override
	public List<BaseInfoStanalObject> statIssueOverTimeDone(Long orgId, Date startDate, Date endDate) {
		Map query = new HashMap();
		query.put("orgId", orgId);
		query.put("startDate", startDate);
		query.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList(
				"statAnalyseIssueStatue.statIssueOverTimeDone", query);
	}
}
