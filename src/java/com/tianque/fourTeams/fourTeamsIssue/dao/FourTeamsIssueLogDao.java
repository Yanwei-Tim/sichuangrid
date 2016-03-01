package com.tianque.fourTeams.fourTeamsIssue.dao;

import java.util.List;

import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMap;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;

public interface FourTeamsIssueLogDao {
	/**
	 * 新增事件处理记录
	 * 
	 * @param log
	 *            事件处理记录
	 * @return
	 */
	FourTeamsIssueLogNew addLog(FourTeamsIssueLogNew log);

	/**
	 * 根据id查询办理记录
	 * 
	 * @param id
	 *            处理记录id
	 * @return
	 */
	FourTeamsIssueLogNew getIssueLogById(Long id);

	/**
	 * 获取事件的所有的处理记录
	 * 
	 * @param issueid
	 *            事件id
	 * @return
	 */
	List<FourTeamsIssueLogNew> loadIssueOperationLogsByIssueId(Long issueid);

	/**
	 * 删除事件处理记录
	 * 
	 * @param issueId
	 *            事件id
	 * @return
	 */
	boolean deleteIssueLogByIssueId(Long issueId);

	/***
	 * 根据处理步骤id获取处理记录
	 * 
	 * @param stepid
	 *            步骤id
	 * @return
	 */
	List<FourTeamsIssueLogNew> getIssueLogsByStepId(Long stepid);

	/**
	 * 获取事件的在某个部门的处理记录
	 * 
	 * @param issueMap
	 * @param group
	 * @return
	 */
	List<FourTeamsIssueLogNew> findIssueDealLogByIssueMapAndIssueStepGroup(
			FourTeamsIssueMap issueMap, FourTeamsIssueStepGroup group);

	public FourTeamsIssueLogNew updateLog(FourTeamsIssueLogNew log);

}
