package com.tianque.plugin.judgmentAnalysis.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * 
 * @author lu
 * 
 */
public class ScheduleJobLog extends BaseDomain {

	private ScheduleJob scheduleJob;

	private ScheduleLog scheduleLog;

	private ScheduleJobInfo scheduleJobInfo;

	private Date startTime;

	private Date endTime;

	private String appName;

	private String jobName;

	private Integer exceptionNum;

	private String taskParamter;

	private String ownSign;

	private String taskItems;

	private Integer eachfetchData;

	private Integer listSize;

	private String remark;

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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getTaskParamter() {
		return taskParamter;
	}

	public void setTaskParamter(String taskParamter) {
		this.taskParamter = taskParamter;
	}

	public String getOwnSign() {
		return ownSign;
	}

	public void setOwnSign(String ownSign) {
		this.ownSign = ownSign;
	}

	public String getTaskItems() {
		return taskItems;
	}

	public void setTaskItems(String taskItems) {
		this.taskItems = taskItems;
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

	public Integer getListSize() {
		return listSize;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	public ScheduleJobInfo getScheduleJobInfo() {
		return scheduleJobInfo;
	}

	public void setScheduleJobInfo(ScheduleJobInfo scheduleJobInfo) {
		this.scheduleJobInfo = scheduleJobInfo;
	}

	public ScheduleJob getScheduleJob() {
		return scheduleJob;
	}

	public void setScheduleJob(ScheduleJob scheduleJob) {
		this.scheduleJob = scheduleJob;
	}

	public ScheduleLog getScheduleLog() {
		return scheduleLog;
	}

	public void setScheduleLog(ScheduleLog scheduleLog) {
		this.scheduleLog = scheduleLog;
	}

	public Integer getEachfetchData() {
		return eachfetchData;
	}

	public void setEachfetchData(Integer eachfetchData) {
		this.eachfetchData = eachfetchData;
	}

}
