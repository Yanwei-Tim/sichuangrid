package com.tianque.plugin.judgmentAnalysis.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * 
 * @author lu
 *
 */
public class ScheduleJobException extends BaseDomain {

	private ScheduleJobLog scheduleJobLog;

	private String appName;//运行服务器名称

	private String jobName;//调度名称
	
	
	private String modelName;//业务模型名称
	
	private String tableName;//维度表名
	
	private Date errorTime;

	private String description;

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(Date errorTime) {
		this.errorTime = errorTime;
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
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ScheduleJobLog getScheduleJobLog() {
		return scheduleJobLog;
	}

	public void setScheduleJobLog(ScheduleJobLog scheduleJobLog) {
		this.scheduleJobLog = scheduleJobLog;
	}

}
