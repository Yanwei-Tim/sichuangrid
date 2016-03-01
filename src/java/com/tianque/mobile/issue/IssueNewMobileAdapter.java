package com.tianque.mobile.issue;

public interface IssueNewMobileAdapter {

	public String getIssueAction() throws Exception;

	public String searchDoneIssues() throws Exception;

	public String dispatchDeal() throws Exception;

	public String dealIssue() throws Exception;

	public String enclosureUpload() throws Exception;

	public String prepareExistedEnabledIssueType() throws Exception;

	public String findPlaceList() throws Exception;

	public String findPopulationsByCardNoAndNameAndOrgId() throws Exception;

	public String addIssue() throws Exception;

	public String findNeedDoIssueList() throws Exception;

	public String viewIssueDetail() throws Exception;

	public String findMyJurisdictionsNeedDo() throws Exception;

	public String findJurisdictionsDoneIssues() throws Exception;

	public String doIssueAction() throws Exception;

}
