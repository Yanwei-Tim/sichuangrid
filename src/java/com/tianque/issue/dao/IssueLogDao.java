package com.tianque.issue.dao;

import java.util.List;

import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueMap;
import com.tianque.issue.state.IssueStepGroup;

public interface IssueLogDao {
	/**
	 * 新增事件处理记录
	 * 
	 * @param log
	 *            事件处理记录
	 * @return
	 */
	IssueLogNew addLog(IssueLogNew log);

	/**
	 * 根据id查询办理记录
	 * 
	 * @param id
	 *            处理记录id
	 * @return
	 */
	IssueLogNew getIssueLogById(Long id);

	/**
	 * 获取事件的所有的处理记录
	 * 
	 * @param issueid
	 *            事件id
	 * @return
	 */
	List<IssueLogNew> loadIssueOperationLogsByIssueId(Long issueid);

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
	List<IssueLogNew> getIssueLogsByStepId(Long stepid);

	/**
	 * 获取事件的在某个部门的处理记录
	 * 
	 * @param issueMap
	 * @param group
	 * @return
	 */
	List<IssueLogNew> findIssueDealLogByIssueMapAndIssueStepGroup(
			IssueMap issueMap, IssueStepGroup group);

	public IssueLogNew updateLog(IssueLogNew log);

}
