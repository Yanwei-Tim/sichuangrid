package com.tianque.plugin.tbSchedule.domain;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * @ClassName: TBScheduleLog
 * @Description: 任务调度实体
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年11月14日 下午4:10:11
 */
public class TBScheduleLog extends BaseDomain {

	private String jobName;
	private String taskParameter;
	private String ownSign;
	private Integer taskItemNum;
	private String taskItems;
	private Integer eachFetchDataNum;
	private String appName;
	private Date startTime;
	private Date endTime;
	private String remark;
	private Integer listSize;
	private Integer exceptionNum;
	private List<TBScheduleLogException> exceptionList;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getTaskParameter() {
		return taskParameter;
	}

	public void setTaskParameter(String taskParameter) {
		this.taskParameter = taskParameter;
	}

	public String getOwnSign() {
		return ownSign;
	}

	public void setOwnSign(String ownSign) {
		this.ownSign = ownSign;
	}

	public Integer getTaskItemNum() {
		return taskItemNum;
	}

	public void setTaskItemNum(Integer taskItemNum) {
		this.taskItemNum = taskItemNum;
	}

	public String getTaskItems() {
		return taskItems;
	}

	public void setTaskItems(String taskItems) {
		this.taskItems = taskItems;
	}

	public Integer getEachFetchDataNum() {
		return eachFetchDataNum;
	}

	public void setEachFetchDataNum(Integer eachFetchDataNum) {
		this.eachFetchDataNum = eachFetchDataNum;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getExceptionNum() {
		return exceptionNum;
	}

	public void setExceptionNum(Integer exceptionNum) {
		this.exceptionNum = exceptionNum;
	}

	public List<TBScheduleLogException> getExceptionList() {
		return exceptionList;
	}

	public void setExceptionList(List<TBScheduleLogException> exceptionList) {
		this.exceptionList = exceptionList;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Integer getListSize() {
		return listSize;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}
}
