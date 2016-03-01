package com.tianque.plugin.weChat.domain.user;

import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

/**
 * 素材管理

 *  @date  2014年5月12日
 */
public class WeChatSource extends BaseDomain {
	//图片路径
	private String path;
	//文本内容
	private String content;
	// 图文消息名称  
	private String title;
	// 图文消息描述  
	private String description;
	// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致  
	private String picUrl;
	// 点击图文消息跳转链接  
	private String url;
	//服务号
	private String weChatUserId;
	//素材描述（1：文本，2：图片，3：图文 ,4：语音）
	private String sourceDescription;

	private Organization org;

	/** 微信类型*/
	private PropertyDict sourceTypeDict;
	private List<WeChatSourceAttachment> sourceAttachmentList;

	public List<WeChatSourceAttachment> getSourceAttachmentList() {
		return sourceAttachmentList;
	}

	public void setSourceAttachmentList(List<WeChatSourceAttachment> sourceAttachmentList) {
		this.sourceAttachmentList = sourceAttachmentList;
	}

	public PropertyDict getSourceTypeDict() {
		return sourceTypeDict;
	}

	public void setSourceTypeDict(PropertyDict sourceTypeDict) {
		this.sourceTypeDict = sourceTypeDict;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(String weChatUserId) {
		this.weChatUserId = weChatUserId;
	}

	public String getSourceDescription() {
		return sourceDescription;
	}

	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

}
