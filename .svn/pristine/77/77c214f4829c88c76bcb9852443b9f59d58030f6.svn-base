/**
 * 
 */
package com.tianque.plugin.weChat.domain;

import com.tianque.plugin.weChat.util.MessageUtil;

/**
 * 媒体文件信息(用于文件上传)
 * @author 
 *  @date  2014年4月18日
 */
public class WeiXinMedia {
	//类型
	private String type;
	//文件标识
	private String mediaId;

	@Override
	public String toString() {
		String temp = "微信文件上传" + "type:" + type + "\r\n" + "mediaId:" + mediaId + "\r\n"
				+ "createDate:" + MessageUtil.formatTime(String.valueOf(createdAt));
		return temp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public int getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}

	//上传时间
	private int createdAt;

}
