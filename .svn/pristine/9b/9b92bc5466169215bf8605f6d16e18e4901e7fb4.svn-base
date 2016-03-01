package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class SuperviseLog extends BaseDomain {

	private Date superviseDate;// 自动督办日期

	private boolean success;// 是否督办成功

	private Long dealType;// 督办日志操作类型，如：新增、受理。。

	private Integer days;// 督办几天前的数据

	private Long overType;

	private String superviseType;

	public Long getOverType() {
		return overType;
	}

	public void setOverType(Long overType) {
		this.overType = overType;
	}

	public String getSuperviseType() {
		return superviseType;
	}

	public void setSuperviseType(String superviseType) {
		this.superviseType = superviseType;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Date getSuperviseDate() {
		return superviseDate;
	}

	@JSON(format = "yyyymmdd HH:mm:ss")
	public void setSuperviseDate(Date superviseDate) {
		this.superviseDate = superviseDate;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Long getDealType() {
		return dealType;
	}

	public void setDealType(Long dealType) {
		this.dealType = dealType;
	}
}
