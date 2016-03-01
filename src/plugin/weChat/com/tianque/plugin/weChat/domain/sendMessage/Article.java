/**
 * 
 */
package com.tianque.plugin.weChat.domain.sendMessage;

/**
 * 响应消息-> 图文model 
 * @author 
 *  @date  2014年4月16日
 */
public class Article extends BaseMessage {

	// 图文消息名称  
	private String Title;
	// 图文消息描述  
	private String Description;
	// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致  
	private String PicUrl;
	// 点击图文消息跳转链接  
	private String Url;
	//类型 对应web(关注回复0，或是自定义回复1，关注回复只能存在一条)
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}
