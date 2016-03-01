package com.tianque.plugin.judgmentAnalysis.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * 日志主表
 * 
 * @author n-233
 * 
 */
public class ScheduleLog extends BaseDomain {
	// 任务名称
	private String name;
	// 业务模型名称
	private String modelName;
	// 维度表名
	private String tableName;
	// 开始时间
	private Date startTime;
	// 结束时间
	private Date endTime;
	// 下次执行时间

	private Date nextStartTime;
	// 状态
	private Integer status;
	private String currentCycle;

	private String cronExpression;

	private String exceptionDescription;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getNextStartTime() {
		return nextStartTime;
	}

	public void setNextStartTime(Date nextStartTime) {
		this.nextStartTime = nextStartTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCurrentCycle() {
		return currentCycle;
	}

	public void setCurrentCycle(String currentCycle) {
		this.currentCycle = currentCycle;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getExceptionDescription() {
		return exceptionDescription;
	}

	public void setExceptionDescription(String exceptionDescription) {
		this.exceptionDescription = exceptionDescription;
	}
}