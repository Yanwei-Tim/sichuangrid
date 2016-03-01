package com.tianque.leaderAnalysis.service;

import java.util.List;

import com.tianque.leaderAnalysis.domain.IssueAnalysisToMobile;

/**
 * @Description:手机事件的研判分析service
 * @author zhangyouwei@hztianque.com
 * @date: 2015-3-31 下午05:11:35
 */
public interface IssueAnalysisToMobileService {

	/**
	 * job调用生成各个大类对用的办理情况
	 */
	public void createIssueAnalysisToMobileData();

	/**
	 * 事件各个大类对应的列表
	 * 
	 * @param issueType
	 * @param orgId
	 * @param year
	 * @param month
	 * @return
	 */
	public List<IssueAnalysisToMobile> getIssueDatasList(String issueType,
			Long orgId, int year, int month);

	/**
	 * 获取某个组织机构下辖的统计总数
	 * 
	 * @param issueType
	 * @param orgId
	 * @param year
	 * @param month
	 * @return
	 */
	public IssueAnalysisToMobile getIssueDatasListTotalByOrgParentId(
			String issueType, Long orgId, int year, int month);

	/**
	 * 根据年月生成各个大类对用的办理情况
	 * 
	 * @param year
	 * @param month
	 */

	public void createIssueAnalysisToMobileDataByTime(int year, int month);
}
