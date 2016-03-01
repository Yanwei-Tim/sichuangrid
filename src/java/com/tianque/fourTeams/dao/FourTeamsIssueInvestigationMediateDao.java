package com.tianque.fourTeams.dao;

import java.util.List;

import com.tianque.domain.IssueInvestigationMediate;

/**
 * 矛盾纠纷排查调处情况
 */
public interface FourTeamsIssueInvestigationMediateDao {

	public void deleteIssueInvestigationMediateByStartDateAndOrg(int year,
			int month, String orgInternalCode);

	public void addIssueInvestigationMediates(
			List<IssueInvestigationMediate> datas);

	public List<IssueInvestigationMediate> sumIssueInvestigationMediateByOrgAndMonth(
			int year, int month, String orgInternalCode);

	public Long statYearTotalCountByIssueType(String orgInternalCode, int year,
			int month, Long typeId, String subset);

	public Long statYearTotalCountByTypeName(String orgInternalCode, int year,
			int month, String typeDisplayName, String subset);

}
