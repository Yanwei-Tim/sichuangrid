package com.tianque.mobile.task;

/**
 * 提供手机端任务清单的公共请求
 * 
 * @author lanhaifeng
 * 
 */
public interface TaskListCommonMobleAdapter {
	/** 获取当前系统时间 **/
	public String getSystemNowDate() throws Exception;

	/** 签收 **/
	public String signRecord() throws Exception;

	/** 核实是否可签收(司法部门) **/
	public String checkJusticeDepartment() throws Exception;
	
	/** 核实是否可签收(公安部门) **/
	public String checkPoliceDepartment() throws Exception;
}
