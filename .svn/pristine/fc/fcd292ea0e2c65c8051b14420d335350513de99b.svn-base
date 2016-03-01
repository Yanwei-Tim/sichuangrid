package com.tianque.plugin.tbSchedule.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * @ClassName: TBScheduleLogException
 * @Description: 任务调度异常日志实体
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年11月14日 下午4:10:26
 */
public class TBScheduleLogException extends BaseDomain {

	private String errorMsg;
	private Date errorTime;
	private TBScheduleLog tbScheduleLog;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(Date errorTime) {
		this.errorTime = errorTime;
	}

	public TBScheduleLog getTbScheduleLog() {
		return tbScheduleLog;
	}

	public void setTbScheduleLog(TBScheduleLog tbScheduleLog) {
		this.tbScheduleLog = tbScheduleLog;
	}
}
