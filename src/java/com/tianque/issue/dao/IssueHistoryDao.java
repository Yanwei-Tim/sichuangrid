package com.tianque.issue.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueMap;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.state.IssueStepGroup;
import com.tianque.issue.vo.IssueViewObject;

/**
 * @author n-235
 * 
 */
public interface IssueHistoryDao {

	/**
	 * @dis 查询历史越级
	 * @return
	 */
	public PageInfo<IssueViewObject> findJurisdictionsSkipIssueHistory(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	/**
	 * @dis 查询历史已办
	 * @return
	 */
	public PageInfo<IssueViewObject> findJurisdictionsDoneHistory(
			Organization org, Integer page, Integer rows, String sidx,
			String sord, Long issueType, Long orgLevel, String leaderView,
			Long functionalOrgType, Integer viewProcess, Long sourceType,
			int orgCodeFindLevel, Long searchOrgId, String searchOrgCode);

	/**
	 * @dis 查询历史交办
	 * @return
	 */
	public PageInfo<IssueViewObject> findJurisdictionsAssginHistory(
			String seachValue, Organization org, Integer page, Integer rows,
			String sidx, String sord, Long issueType, Long orgLevel,
			String leaderView, Long functionalOrgType, Integer viewProcess,
			Long sourceType, int orgCodeFindLevel, Long searchOrgId,
			String searchOrgCode);

	/**
	 * @dis 查询历史上报
	 * @return
	 */
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

	/***
	 * @dis 根据事件id获取事件组
	 * @param id
	 * @return
	 */
	public List<IssueStepGroup> getIssueStepGroupHistoryByIssueId(Long id);

	/***
	 * @dis 根据步骤组查询事件步骤图
	 * @param issueStepGroup
	 * @return
	 */
	public IssueMap getIssueMapHistoryByStepGroup(IssueStepGroup issueStepGroup);

	/***
	 * @dis 根据事件步骤组id查询步骤组
	 * @param id
	 * @return
	 */
	public IssueStepGroup getSimpleIssueStepHistoryGroupById(Long id);

	/***
	 * @dis 查询事件办理日志
	 * @param issueMap
	 * @param group
	 * @return
	 */
	public List<IssueLogNew> findIssueDealLogHistoryByIssueMapAndIssueStepGroup(
			IssueMap issueMap, IssueStepGroup group);

	public List<IssueStep> searchIssueStepsHistoryByIssueId(Long issueId);

	public List<IssueStep> searchAllIssueStepsHistoryByStepId(Long stepId);

	public boolean deleteIssueLogHistoryByIssueId(Long issueId);

	/**
	 * 根据id删除事件
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	public boolean deleteIssueHistoryById(Long issueId);

	public boolean deleteIssueStepsHistoryByIssueId(Long issueId);

	public IssueNew getFullIssueByHistoryStepId(Long stepId);

	public IssueStep getSimpleIssueStepHistoryById(Long id);
}
