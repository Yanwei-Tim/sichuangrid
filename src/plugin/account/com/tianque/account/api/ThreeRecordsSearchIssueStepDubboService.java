package com.tianque.account.api;

import java.util.List;

import com.tianque.plugin.account.domain.ThreeRecordsIssueStep;

public interface ThreeRecordsSearchIssueStepDubboService {

	List<ThreeRecordsIssueStep> searchIssueStepsByIssueId(Long issueId,
			int ledgerType);

	List<ThreeRecordsIssueStep> searchAllIssueStepsByStepId(Long stepId);

	boolean hasPermission(Long ledgerId, int ledgerType, Long stepId,
			String userOrgCode);

}
