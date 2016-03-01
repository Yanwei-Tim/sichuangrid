package com.tianque.plugin.judgmentAnalysis.domain;

import com.tianque.core.base.BaseDomain;

public class ScheduleJobInfo extends BaseDomain implements Comparable<ScheduleJobInfo> {

	private ScheduleJob scheduleJob;

	private Integer ordered;

	private String executor;

	private Integer type;

	private Integer sqlType;

	private String params;

	private Integer batchNum; //批量数

	private Integer saveFlag; //保存标志 1 保存 0 不保存

	private String groupName; //组织机构分组  ORGID

	public Integer getOrdered() {
		return ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSqlType() {
		return sqlType;
	}

	public void setSqlType(Integer sqlType) {
		this.sqlType = sqlType;
	}

	@Override
	public int compareTo(ScheduleJobInfo o) {
		return this.ordered.compareTo(o.getOrdered());
	}

	public ScheduleJob getScheduleJob() {
		return scheduleJob;
	}

	public void setScheduleJob(ScheduleJob scheduleJob) {
		this.scheduleJob = scheduleJob;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(Integer batchNum) {
		this.batchNum = batchNum;
	}

	public Integer getSaveFlag() {
		return saveFlag;
	}

	public void setSaveFlag(Integer saveFlag) {
		this.saveFlag = saveFlag;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
