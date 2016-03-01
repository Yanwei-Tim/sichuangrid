package com.tianque.issueAbutmentJoint.service;

import com.tianque.issueAbutmentJoint.domain.IssueJointStep;

/**
 * @Description:事件对接 步骤业务层接口
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-22 上午11:26:27
 */
public interface IssueJointStepService {

	/**
	 * 导入对接事件的步骤新增
	 * 
	 * @param issueJointStep
	 * @return
	 */
	public IssueJointStep addIssueJointStepByIssueJointByImport(
			IssueJointStep issueJointStep);

	/**
	 * 根据Id查询
	 * 
	 * @param id
	 * @return
	 */
	public IssueJointStep getIssueJointStepById(Long id);

}
