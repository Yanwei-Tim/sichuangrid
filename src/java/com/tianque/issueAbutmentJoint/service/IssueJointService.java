package com.tianque.issueAbutmentJoint.service;

import org.oproject.framework.orm.PageResult;

import com.tianque.issueAbutmentJoint.domain.IssueJoint;
import com.tianque.issueAbutmentJoint.domain.vo.IssueJointVo;

/**
 * @Description:事件对接 事件业务层接口
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-22 上午11:26:01
 */
public interface IssueJointService {

	/**
	 * 导入的事件对接【需要进行填充并且剥离出log和step】
	 * 
	 * @param domain
	 * @return
	 */
	public IssueJoint addIssueJointByImport(IssueJoint issueJoint);

	/**
	 * 根据对接事件搜索类查询
	 * 
	 * @param issueJointVo
	 * @param sidx
	 * @param sord
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageResult<IssueJoint> queryIssueJointByIssueJointVoForPageResult(
			IssueJointVo issueJointVo, String sidx, String sord, int pageNum,
			int pageSize);

	/**
	 * 根据Id查询
	 * 
	 * @param id
	 * @return
	 */
	public IssueJoint getIssueJointById(Long id);
}
