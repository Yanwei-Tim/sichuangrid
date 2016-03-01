package com.tianque.plugin.weChat.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 微信响应
 * @ClassName: WeChatResponse 
 * @author: hesimin
 * @date: 2015年10月28日 下午5:45:26
 */
public class WeChatResponse extends BaseDomain {
	private Organization org;
	private String sendType;//发送红袖套分类
	private String mediaId;
	private Long sourceId;
	private String wechatUserName;
	private String msgId;
	private String text;
	private String status;
	private Integer totalCount;
	private Integer filterCount;
	private Integer sentCount;
	private Integer errorCount;
	private Date resultDate;
	private Long userId;
	private String userName;
	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getSendType() {
		return sendType;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getWechatUserName() {
		return wechatUserName;
	}
	public void setWechatUserName(String wechatUserName) {
		this.wechatUserName = wechatUserName;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getFilterCount() {
		return filterCount;
	}
	public void setFilterCount(Integer filterCount) {
		this.filterCount = filterCount;
	}
	public Integer getSentCount() {
		return sentCount;
	}
	public void setSentCount(Integer sentCount) {
		this.sentCount = sentCount;
	}
	public Integer getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getResultDate() {
		return resultDate;
	}
	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
