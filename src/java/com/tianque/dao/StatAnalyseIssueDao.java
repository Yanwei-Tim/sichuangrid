package com.tianque.dao;

import java.util.Date;
import java.util.List;

import com.tianque.domain.BaseInfoStanalObject;

public interface StatAnalyseIssueDao {

	public List<BaseInfoStanalObject> statIssueNormalNotDone(Long orgId, Date startDate,
			Date endDate);

	public List<BaseInfoStanalObject> statIssueOverTimeNotDone(Long orgId, Date startDate,
			Date endDate);

	public List<BaseInfoStanalObject> statIssueNormalDone(Long orgId, Date startDate, Date endDate);

	public List<BaseInfoStanalObject> statIssueOverTimeDone(Long orgId, Date startDate, Date endDate);

}
