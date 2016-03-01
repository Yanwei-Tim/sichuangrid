package com.tianque.plugin.weChat.domain.receiveMessage;

/** 图片消息 */
public class ImageReceiveMessage extends BaseReceiveMessage {
	/**媒体ID*/
	private String MediaId;
	/**图片链接*/
	private String PicUrl;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}
