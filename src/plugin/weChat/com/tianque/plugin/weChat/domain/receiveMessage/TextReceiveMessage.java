package com.tianque.plugin.weChat.domain.receiveMessage;

/** 文本消息 */
public class TextReceiveMessage extends BaseReceiveMessage {
	/**消息内容*/
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}