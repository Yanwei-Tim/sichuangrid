package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

public class SmsMessageMark extends BaseDomain {
	/** 短信消息模板　 */
	private String model;
	/** 消息的操作类型（办理，领导批示，阅读，督办，受理等） */
	private Integer operationtType;
	/** 消息的处理类型（办理中，上报，交办，验证等） */
	private Integer dealType;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getOperationtType() {
		return operationtType;
	}

	public void setOperationtType(Integer operationtType) {
		this.operationtType = operationtType;
	}

	public Integer getDealType() {
		return dealType;
	}

	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}

}
