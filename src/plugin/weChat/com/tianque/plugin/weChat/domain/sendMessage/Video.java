/**
 * 
 */
package com.tianque.plugin.weChat.domain.sendMessage;

/**
 * 视频model
 * @author 
 *  @date  2014年4月17日
 */
public class Video {
	//媒体文件id
	private String MediaId;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	//缩略图的媒体id
	private String ThumbMediaId;

}
