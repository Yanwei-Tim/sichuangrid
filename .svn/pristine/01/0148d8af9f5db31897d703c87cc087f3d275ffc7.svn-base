package com.tianque.service;

import java.util.Date;
import java.util.List;

import com.tianque.domain.BaseInfoStanalObject;
import com.tianque.domain.StatAnalyseIssue;

public interface StatAnalyseIssueStatueService {

	List<BaseInfoStanalObject> statIssueNormalNotDone(Long orgId, Date startDate, Date endDate);

	List<BaseInfoStanalObject> statIssueOverTimeNotDone(Long orgId, Date startDate, Date endDate);

	List<BaseInfoStanalObject> statIssueNormalDone(Long orgId, Date startDate, Date endDate);

	List<BaseInfoStanalObject> statIssueOverTimeDone(Long orgId, Date startDate, Date endDate);

	public List<StatAnalyseIssue> getIssueState(StatAnalyseIssue statAnalyseIssue);

	public List showLine(StatAnalyseIssue statAnalyseIssue);
}
