package com.tianque.issue.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueMap;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.vo.IssueViewObject;

/**
 * 事件历史查询
 * 
 */
public interface IssueHistoryService {
	public PageInfo<IssueViewObject> findJurisdictionsSkipIssueHistory(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	public PageInfo<IssueViewObject> findJurisdictionsDoneHistory(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	public PageInfo<IssueViewObject> findJurisdictionsAssginHistory(
			String seachValue, Organization org, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, int orgCodeFindLevel, Long searchOrgId,
			String searchOrgCode);

	public PageInfo<IssueViewObject> findJurisdictionsSubmitHistory(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	/**
	 * 获取事件的所有的处理记录
	 * 
	 * @param issueid
	 *            事件id
	 * @return
	 */
	public List<IssueLogNew> loadIssueOperationLogsHistoryByIssueId(Long id);

	/**
	 * 根据id获取完整事件对象
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	public IssueNew getFullIssueHistoryById(Long id);

	public List<IssueMap> getIssueHistoryMap(Long issueId);

	public List<IssueLogNew> getIssueDealLogHistory(IssueMap issueMap);

	public List<IssueStep> searchIssueStepsHistoryByIssueId(Long issueId);

	public List<IssueStep> searchAllIssueStepsHistoryByStepId(Long stepId);

	/**
	 * 根据历史事件id删除历史事件
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	boolean deleteIssueHistoryByIssueId(Long issueId);

	/**
	 * 根据事件处理步骤id获取完整事件对象
	 * 
	 * @param stepId
	 *            事件处理步骤id
	 * @return
	 */
	IssueNew getFullIssueByHistoryStepId(Long stepId);

	public IssueStep getSimpleIssueStepHistoryById(Long stepId);

}
