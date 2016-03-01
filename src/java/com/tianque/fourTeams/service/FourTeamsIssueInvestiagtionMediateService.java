package com.tianque.fourTeams.service;

import java.util.List;

import com.tianque.report.vo.IssueInvestigationMediateVo;

/**
 * 矛盾纠纷排查调处
 */
public interface FourTeamsIssueInvestiagtionMediateService {

	public void rebuildMonthIssueInvestigationMediate(int year, int month, Long orgId);

	public List<IssueInvestigationMediateVo> findIssueInvestigationMediates(int year, int month,
			Long orgId);

}
