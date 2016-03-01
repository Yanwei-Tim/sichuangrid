package com.tianque.plugin.weChat.domain.messageTemplate;

import com.tianque.core.base.BaseDomain;

/**
 * 模板消息
 */
public class MessageTemplate extends BaseDomain {
	/**微信公众（连接号）*/
	private String weChatUserId;
	/**发送对象的openId*/
	private String openId;
	/**消息模板id*/
	private String template_id;
	/**url详情链接*/
	private String url;

	/**详细内容*/
	private String firstWord;//first模板消息首行内容（一般为问候语）

	private String keywordOne;//详细内容中的关键字1
	private String keywordTow;//详细内容中的关键字2
	private String keywordThree;//详细内容中的关键字3
	private String remark;//末尾备注（一般为感谢您...）

	public String getFirstWord() {
		return firstWord;
	}

	public void setFirstWord(String firstWord) {
		this.firstWord = firstWord;
	}

	public String getKeywordOne() {
		return keywordOne;
	}

	public void setKeywordOne(String keywordOne) {
		this.keywordOne = keywordOne;
	}

	public String getKeywordTow() {
		return keywordTow;
	}

	public void setKeywordTow(String keywordTow) {
		this.keywordTow = keywordTow;
	}

	public String getKeywordThree() {
		return keywordThree;
	}

	public void setKeywordThree(String keywordThree) {
		this.keywordThree = keywordThree;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(String weChatUserId) {
		this.weChatUserId = weChatUserId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
