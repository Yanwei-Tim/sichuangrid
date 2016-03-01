/**
 * 
 */
package com.tianque.plugin.weChat.domain.sendMessage;

/**
 * 图片消息
 * @author 
 *  @date  2014年4月17日
 */
public class ImageMessage extends BaseMessage {
	private Image Image;

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

}
