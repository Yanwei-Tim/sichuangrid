package com.tianque.issueAbutmentJoint.service;

import java.util.List;

import com.tianque.issueAbutmentJoint.domain.IssueJointLog;

/**
 * @Description: 事件对接 日志业务层接口
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-22 上午11:25:18
 */
public interface IssueJointLogService {

	/**
	 * 对接事件导入添加日志信息
	 * 
	 * @param issueJointLogCreate
	 * @return
	 */
	public IssueJointLog addIssueJointLogByIssueJointByImport(
			IssueJointLog issueJointLog);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public IssueJointLog getIssueJointLogById(Long id);

	/**
	 * 根据对接事件Id查询所有的日志
	 * 
	 * @param issueJointId
	 * @return
	 */
	public List<IssueJointLog> queryIssueJointLogByIssueJointIdForList(
			Long issueJointId);

}
