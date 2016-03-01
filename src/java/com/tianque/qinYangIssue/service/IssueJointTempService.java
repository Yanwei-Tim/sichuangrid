package com.tianque.qinYangIssue.service;

import com.tianque.domain.PropertyDict;
import com.tianque.qinYangIssue.domain.IssueJointTemp;

/**
 * @Description:青羊事件对接临时表接口
 * @author zhangyouwei@hztianque.com
 * @date: 2014-12-14 下午03:11:08
 */
public interface IssueJointTempService {

	/**
	 * 青羊事件对接数据同步到社管job调用分页处理，当时间超出早上七点以后跳出job以后执行
	 * 
	 * @param isWorkTimeBreak
	 *            是否工作时间跳出
	 */
	public void syncIssueJointTempData(Boolean isWorkTimeBreak);

	/**
	 * 根据对接数据同步数据到社管（单条）
	 * 
	 * @param issueJointTemp
	 * @param sourceKind
	 *            事件录入方式为同步
	 */
	public void addIssueByIssueJointTemp(IssueJointTemp issueJointTemp,
			PropertyDict sourceKind);

	/**
	 * 社管对接事件当前处理状态同步到青羊事件对接数据,当时间超出早上七点以后跳出job以后执行
	 */
	public void syncIssueStatusToIssueJointTempData(Boolean isWorkTimeBreak);

}
