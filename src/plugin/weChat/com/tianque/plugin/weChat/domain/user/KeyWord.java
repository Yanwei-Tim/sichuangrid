package com.tianque.plugin.weChat.domain.user;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class KeyWord extends BaseDomain {
	private String keyName;
	private String keyRemark;
	private String sourceId;
	private String sourceDescription;

	private String weChatUserId;
	private Organization org;
	/** 微信素材类型*/
	private PropertyDict sourceTypeDict;

	public PropertyDict getSourceTypeDict() {
		return sourceTypeDict;
	}

	public void setSourceTypeDict(PropertyDict sourceTypeDict) {
		this.sourceTypeDict = sourceTypeDict;
	}

	public String getSourceDescription() {
		return sourceDescription;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public void setSourceDescription(String sourceDescription) {
		this.sourceDescription = sourceDescription;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyRemark() {
		return keyRemark;
	}

	public void setKeyRemark(String keyRemark) {
		this.keyRemark = keyRemark;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(String weChatUserId) {
		this.weChatUserId = weChatUserId;
	}

}
