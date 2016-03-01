package com.tianque.sms.domain.vo;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.domain.User;

/**
 * job短信发送对象sql:辅助类
 */
public class SmsJobSqlVo {
	/**
	 * 记录id
	 */
	private Long id;
	/**
	 * 短信优先级级逐步递减：1，2，3。 为-1时sql语句已经执行
	 */
	private Long smsLevel;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 查询发送对象的sql
	 */
	private String sql;
	/**
	 * 短信内容
	 */
	private String content;
	/**
	 * 发送人
	 */
	private User sender;
	/**
	 * 发送时间
	 */
	private Date sendTime;
	/**
	 * 短信发件箱Id
	 */
	private Long smsuplinkId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSmsLevel() {
		return smsLevel;
	}

	public void setSmsLevel(Long smsLevel) {
		this.smsLevel = smsLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Long getSmsuplinkId() {
		return smsuplinkId;
	}

	public void setSmsuplinkId(Long smsuplinkId) {
		this.smsuplinkId = smsuplinkId;
	}

}
