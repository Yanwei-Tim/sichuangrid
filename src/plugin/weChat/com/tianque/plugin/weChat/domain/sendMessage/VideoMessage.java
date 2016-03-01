/**
 * 
 */
package com.tianque.plugin.weChat.domain.sendMessage;

/**
 * 视频
 * @author 
 *  @date  2014年4月17日
 */
public class VideoMessage extends BaseMessage {
	//视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
