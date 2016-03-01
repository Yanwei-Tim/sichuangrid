package com.tianque.issueAbutmentJoint.service;

import java.util.Date;
import java.util.List;

import com.tianque.domain.Organization;
import com.tianque.issueAbutmentJoint.domain.IssueDataColumnVo;

/**
 * @Description:对接事件已办结统计业务层
 * @author zhangyouwei@hztianque.com
 * @date: 2015-3-24 下午04:04:27
 */
public interface IssueDataColumnVoService {
	/**
	 * 事件对接的也统计到对应层级
	 */
	public void createIssueJointAnalysisChartData();

	/**
	 * 事件对接的也统计到对应层级
	 * 
	 * @param endDate
	 * @param startDate
	 */
	public IssueDataColumnVo findIssueHandleStatsToCTJD(
			Organization organization, Date startDate, Date endDate);

	/**
	 * 事件对接的也统计到对应层级
	 * 
	 * @param endDate
	 * @param startDate
	 */
	public List<IssueDataColumnVo> findIssueClassIficationStatToCTJD(
			Organization organization, Date startDate, Date endDate);

	/**
	 * 获取事件对接的组织机构
	 * 
	 * @return
	 */
	public Organization findOrg(String orgName, String orgFullName, int orgLevel);
}
