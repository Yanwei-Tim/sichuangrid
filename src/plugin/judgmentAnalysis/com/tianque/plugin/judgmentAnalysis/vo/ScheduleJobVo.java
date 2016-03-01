package com.tianque.plugin.judgmentAnalysis.vo;

import java.io.Serializable;
import java.util.List;

import com.tianque.plugin.judgmentAnalysis.domain.ScheduleJob;
import com.tianque.plugin.judgmentAnalysis.domain.ScheduleJobInfo;

/**
* @ClassName: ScheduleJobVo
* @Description: 调度任务vo
* @author wangxiaohu wsmalltiger@163.com
* @date 2015年3月20日 下午4:47:24 
*/
public class ScheduleJobVo implements Serializable {
	private ScheduleJob scheduleJob;
	private Integer eachFetchDataNum;
	private List<Long> taskItems;

	private ScheduleJobInfo scheduleJobInfo;

	private Long jobLogId;
	private String appName;
	private String taskParam;
	private String ownsign;

	public ScheduleJob getScheduleJob() {
		return scheduleJob;
	}

	public void setScheduleJob(ScheduleJob scheduleJob) {
		this.scheduleJob = scheduleJob;
	}

	public Integer getEachFetchDataNum() {
		return eachFetchDataNum;
	}

	public void setEachFetchDataNum(Integer eachFetchDataNum) {
		this.eachFetchDataNum = eachFetchDataNum;
	}

	public List<Long> getTaskItems() {
		return taskItems;
	}

	public void setTaskItems(List<Long> taskItems) {
		this.taskItems = taskItems;
	}

	public Long getJobLogId() {
		return jobLogId;
	}

	public void setJobLogId(Long jobLogId) {
		this.jobLogId = jobLogId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getTaskParam() {
		return taskParam;
	}

	public void setTaskParam(String taskParam) {
		this.taskParam = taskParam;
	}

	public String getOwnsign() {
		return ownsign;
	}

	public void setOwnsign(String ownsign) {
		this.ownsign = ownsign;
	}

	public ScheduleJobInfo getScheduleJobInfo() {
		return scheduleJobInfo;
	}

	public void setScheduleJobInfo(ScheduleJobInfo scheduleJobInfo) {
		this.scheduleJobInfo = scheduleJobInfo;
	}

}
