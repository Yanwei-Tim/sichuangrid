package com.tianque.domain;

import java.util.Date;
import java.util.List;

import com.tianque.core.base.BaseDomain;

/**
 * 短信发件箱(短信下行)表
 */
public class SmsSendBox extends BaseDomain {
	private static final long serialVersionUID = 1L;
	/** 发短信人id */
	private User sendUser;
	/** "下发状态: 1-未下发到服务器,2-系统处理中,3-下发到服务器,4-下发到终端成功,5-未成功发送到终端 */
	private int sendStatus;
	/** 系统消息默认为：0；优先级逐步递减：1，2，3，4，5，6; */
	private Long sendLevel;
	/** 发短信人(系统登录用户联系手机) */
	private String sendMobile;
	/** 发短信人(系统登录用户) */
	private String sender;
	/** 收短信人(支持多个例如：可以同时发送给系统用户、通讯录用户、用户组) */
	private String receiver;
	/** 短信内容 */
	private String smsContent;
	/** 提交到行业网关的时间 */
	private Date sentTime;
	/** 目标通讯录 */
	private List<Contacter> Contacts;

	public User getSendUser() {
		return sendUser;
	}

	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}

	public int getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(int sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Long getSendLevel() {
		return sendLevel;
	}

	public void setSendLevel(Long sendLevel) {
		this.sendLevel = sendLevel;
	}

	public String getSendMobile() {
		return sendMobile;
	}

	public void setSendMobile(String sendMobile) {
		this.sendMobile = sendMobile;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public List<Contacter> getContacts() {
		return Contacts;
	}

	public void setContacts(List<Contacter> contacts) {
		Contacts = contacts;
	}

}
