package com.tianque.sms.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class MobileShortMessage extends com.tianque.core.base.BaseDomain
		implements java.io.Serializable {

	/**
	 * 等待发送
	 */
	public final static int WAITING_TO_SEND = 1;
	/**
	 * 系统正在处理
	 */
	public final static int IS_SENDING = 2;
	/**
	 * 成功发送到网关
	 */
	public final static int SENT_TO_SERVER = 3;
	/**
	 * 成功发送到移动终端
	 */
	public final static int SENT_TO_END = 4;
	/**
	 * 没有成功发送到网关
	 */
	public final static int CAN_NOT_SEND_TO_GATEWAY_ERROR = 5;
	/**
	 * 无法恢复的错误，比如手机号码出错，号码不存在
	 */
	public final static int CAN_NOT_RECOVER_ERROR = 6;
	/**
	 * 未发送到终端
	 */
	public final static int CAN_NOT_SENT_TO_END = 7;

	/**
	 * 移动短信
	 */
	public final static int MOBILE = 1;

	/**
	 * 联通短信
	 */
	public final static int UNICOM = 2;

	/**
	 * 小灵通短信
	 */
	public final static int PHS = 3;

	/**
	 * CDMA短信
	 */
	public final static int CDMA = 4;

	/**
	 * 短信息来源的标识
	 */
	private Long fromId;
	/**
	 * 短信息来源系统
	 */
	private String fromSystem = "";
	/**
	 * 短信息远端ID
	 */
	private String serverId = "";
	/**
	 * 消息体
	 */
	private String message = "";
	/**
	 * 是否需要结果报告
	 */
	private boolean requestReport;
	/**
	 * 发送人
	 */
	private String sender = "";
	/**
	 * 接收者，号码
	 */
	private String receiver = "";
	/**
	 * 下发状态 1：未下发到服务器 2: 系统处理中 3：下发到服务器 4：下发到终端成功 5：未成功发送到终端
	 */
	private int status = WAITING_TO_SEND;

	/**
	 * 上下发时间
	 */
	private Date sentTime;

	private Date createTime;

	/**
	 * 短信优先级 系统消息默认为：0 优先级逐步递减：1，2，3，4，5，6
	 */
	private int priority = 8;

	/**
	 * 短信类型 现有类型：移动 1，联通 2，小灵通 3，CDMA 4
	 */
	private int type;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public Long getFromId() {
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}

	public String getFromSystem() {
		return fromSystem;
	}

	public void setFromSystem(String fromSystem) {
		this.fromSystem = fromSystem;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRequestReport() {
		return requestReport;
	}

	public void setRequestReport(boolean requestReport) {
		this.requestReport = requestReport;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

}
