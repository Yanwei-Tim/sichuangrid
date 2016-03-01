package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

/**
 * 短信收件箱(短信下行)表
 */
public class SmsReceivedBox extends BaseDomain {
	private static final long serialVersionUID = 1L;
	/** 所属责任区 */
	private Organization organization;
	/** 短信处理人 */
	private User handleUser;
	/** 短信处理人名称 */
	private String handlePerson;
	/** 短信处理人联系手机 */
	private String handleMobile;
	/** 处理情况 */
	private String disposition;
	/** 应用程序处理标志: 0-未处理，1-已处理 */
	private boolean processFlag;
	/** 短信内容 */
	private String smsContent;
	/** 发送者手机号码 */
	private String sourceMobile;
	/** 目标手机号码 */
	private String targetMobile;
	/** 短信发上来的时间(来自下行发送手机) */
	private Date receiverTime;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public User getHandleUser() {
		return handleUser;
	}

	public void setHandleUser(User handleUser) {
		this.handleUser = handleUser;
	}

	public String getHandlePerson() {
		return handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	public String getHandleMobile() {
		return handleMobile;
	}

	public void setHandleMobile(String handleMobile) {
		this.handleMobile = handleMobile;
	}

	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public boolean isProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(boolean processFlag) {
		this.processFlag = processFlag;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getSourceMobile() {
		return sourceMobile;
	}

	public void setSourceMobile(String sourceMobile) {
		this.sourceMobile = sourceMobile;
	}

	public String getTargetMobile() {
		return targetMobile;
	}

	public void setTargetMobile(String targetMobile) {
		this.targetMobile = targetMobile;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getReceiverTime() {
		return receiverTime;
	}

	public void setReceiverTime(Date receiverTime) {
		this.receiverTime = receiverTime;
	}

}
