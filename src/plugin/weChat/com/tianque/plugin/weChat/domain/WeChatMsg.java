package com.tianque.plugin.weChat.domain;

import java.util.Map;

import com.tianque.jms.OperateMode;
import com.tianque.jms.msg.BaseMsg;
import com.tianque.plugin.weChat.domain.user.TencentUser;

public class WeChatMsg extends BaseMsg {

	public WeChatMsg() {
	}

	public WeChatMsg(Map<String, String> messageMap, TencentUser tencentUser, OperateMode mode) {
		setWeChatMsgType(messageMap.get("MsgType"));
		setCreateTime(Long.parseLong(messageMap.get("CreateTime")));
		setFromUserName(messageMap.get("FromUserName"));
		setMsgId(Long.parseLong(messageMap.get("MsgId")));
		setToUserName(messageMap.get("ToUserName"));
		setContent(messageMap.get("Content"));
		setOrgId(tencentUser.getOrganization().getId());
		this.mode = mode;
		this.msgType = "weChat";
	}
	
	public WeChatMsg(Map<String, String> messageMap, TencentUser tencentUser, String mediaId, String token, OperateMode mode) {
		setWeChatMsgType(messageMap.get("MsgType"));
		setCreateTime(Long.parseLong(messageMap.get("CreateTime")));
		setFromUserName(messageMap.get("FromUserName"));
		setMsgId(Long.parseLong(messageMap.get("MsgId")));
		setToUserName(messageMap.get("ToUserName"));
		setContent(messageMap.get("Content"));
		setOrgId(tencentUser.getOrganization().getId());
		setMediaId(mediaId);
		setToken(token);
		this.mode = mode;
		this.msgType = "weChat";
	}
	private static final long serialVersionUID = 1L;
	private String weChatMsgType;
	private String toUserName;
	private String fromUserName;
	private Long msgId;
	private Long createTime;
	private String content;
	private String mediaId;
	private String token;
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	


	
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}


	public String getContent() {
		return content;
	}


	public String getFromUserName() {
		return fromUserName;
	}

	public Long getMsgId() {
		return msgId;
	}

	public String getToUserName() {
		return toUserName;
	}

	public String getWeChatMsgType() {
		return weChatMsgType;
	}

	public void setContent(String content) {
		this.content = content;
	}




	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}


	public void setWeChatMsgType(String weChatMsgType) {
		this.weChatMsgType = weChatMsgType;
	}

	


}
