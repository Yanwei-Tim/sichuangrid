/**
 * 
 */
package com.tianque.plugin.weChat.domain.event;

/**
 * @author 
 *  @date  2014年4月17日
 *  事件基类
 */
public class BaseEvent {
	// 开发者微信号  
	private String ToUserName;
	// 发送方帐号（一个OpenID）  
	private String FromUserName;
	// 消息创建时间 （整型）  
	private long CreateTime;

	// 消息类型（text/image/location/link）  
	private String MsgType;

	// 消息id，64位整型  
	private long MsgId;

	public long getCreateTime() {
		return CreateTime;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public long getMsgId() {
		return MsgId;
	}

	public String getMsgType() {
		return MsgType;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
}
